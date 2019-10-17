package br.com.infobtc.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.infobtc.controller.dto.ContaReceberDto;
import br.com.infobtc.dao.ContratoDao;
import br.com.infobtc.model.Contrato;

@RestController
@RequestMapping("/conta-receber")
public class ContaReceberController {

//	@Autowired
//	private ContratoRepository contratoRespository;
	
	@Autowired
	private ContratoDao contratoDao; 

	@GetMapping("/{id}")
	public ResponseEntity<?> contasReceberConsultor(Long idConsultor, @RequestParam(required = true) String dtInicio, @RequestParam(required = true) String dtTermino, Boolean repassado) {
		LocalDate dtInicioParse =  LocalDate.parse(dtInicio);
		LocalDate dtTerminoParse =  LocalDate.parse(dtTermino);
		List<Contrato> contratos;
		
//		if (idConsultor != null && repassado != null) {
//			if (!consultorRepository.findById(idConsultor).isPresent()) {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(String.format("O consultor de id %s não foi encontrado.", idConsultor)));
//			}			
//			contratos = contratoRespository.findByIntervalDate(idConsultor, repassado);
//		} else if (idConsultor != null && repassado == null) {
//			contratos = contratoRespository.findByIntervalDate(idConsultor);
//		} else if(idConsultor == null && repassado != null) {
//			contratos = contratoRespository.findByIntervalDate(repassado);
//		} else {
			contratos = contratoDao.finfByInterval(dtInicioParse, dtTerminoParse, idConsultor, false);
//		}
		
		List<ContaReceberDto> contas = contratos
			.stream()
			.filter(contrato -> ChronoUnit.MONTHS.between(contrato.getDtInicio(), dtTerminoParse) >= 1)
			.map(contrato -> new ContaReceberDto(contrato, dtInicioParse, dtTerminoParse)).collect(Collectors.toList());
		
		return ResponseEntity.ok(contas);
	}
	
//	private boolean estaNoItervalo(LocalDate dtInicioContratro, LocalDate dtInicio, LocalDate dtTermino) {
//		if(dtInicio.getDayOfMonth() <= dtInicioContratro.getDayOfMonth() && dtInicioContratro.getDayOfMonth() <= dtTermino.getDayOfMonth()) {
//			return true;
//		} else if (dtTermino.getMonthValue() > dtInicio.getMonthValue()
//				&& (dtInicioContratro.getDayOfMonth() <= dtInicio.getDayOfMonth()
//				&& dtTermino.getDayOfMonth() >= dtInicioContratro.getDayOfMonth() 
//				|| dtInicioContratro.getDayOfMonth() <= dtInicio.getDayOfMonth() 
//				&& dtTermino.getDayOfMonth() >= dtInicioContratro.getDayOfMonth())) {
//			return true;
//		}else {
//			return false;
//		}
//	}

}
