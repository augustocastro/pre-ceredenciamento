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
	
	@Autowired
	private ContratoDao contratoDao; 

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarContasReceberPorConsultor(Long idConsultor, @RequestParam(required = true) String dtInicio, @RequestParam(required = true) String dtTermino, Boolean repassado) {
		LocalDate dtInicioParse =  LocalDate.parse(dtInicio);
		LocalDate dtTerminoParse =  LocalDate.parse(dtTermino);
		List<Contrato> contratos;
		
		contratos = contratoDao.finfByInterval(dtInicioParse, dtTerminoParse, idConsultor, false);
		
		List<ContaReceberDto> contas = contratos
			.stream()
			.filter(contrato -> ChronoUnit.MONTHS.between(contrato.getDtInicio(), dtTerminoParse) >= 1)
			.map(contrato -> new ContaReceberDto(contrato, dtInicioParse, dtTerminoParse)).collect(Collectors.toList());
		
		return ResponseEntity.ok(contas);
	}

}
