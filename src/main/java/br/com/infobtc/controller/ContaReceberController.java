package br.com.infobtc.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.infobtc.controller.dto.ContaReceberDto;
import br.com.infobtc.controller.dto.ErroDto;
import br.com.infobtc.model.Contrato;
import br.com.infobtc.repository.ConsultorRepository;
import br.com.infobtc.repository.ContratoRepository;

@RestController
@RequestMapping("/conta-receber")
public class ContaReceberController {

	@Autowired
	private ContratoRepository contratoRespository;

	@Autowired
	private ConsultorRepository consultorRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> contasReceberConsultor(Long idConsultor, @RequestParam(required = true) String dtInicio, @RequestParam(required = true) String dtTermino) {
		LocalDate dtInicioParse =  LocalDate.parse(dtInicio);
		List<Contrato> contratos;
		
		if (idConsultor != null) {
			if (!consultorRepository.findById(idConsultor).isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(String.format("O consultor de id %s não foi encontrado.", idConsultor)));
			}			
			contratos = contratoRespository.findByIntervalDate(idConsultor, dtInicioParse, LocalDate.parse(dtTermino));
		} else {
			contratos = contratoRespository.findByIntervalDate(dtInicioParse, LocalDate.parse(dtTermino));
		}
		
		List<ContaReceberDto> contas = contratos
			.stream()
			.filter(contrato -> ChronoUnit.MONTHS.between(contrato.getDtInicio(), dtInicioParse) >= 1 && dtInicioParse.getDayOfMonth() == contrato.getDtInicio().getDayOfMonth())
			.map(contrato -> new ContaReceberDto(contrato, dtInicioParse)).collect(Collectors.toList());
		
		return ResponseEntity.ok(contas);
	}

}
