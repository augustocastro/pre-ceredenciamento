package br.com.infobtc.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infobtc.controller.dto.ContaReceberDto;
import br.com.infobtc.model.Contrato;
import br.com.infobtc.repository.ContratoRepository;

@RestController
@RequestMapping("/conta-receber")
public class ContaReceberController {

	@Autowired
	private ContratoRepository contratoRespository;

	@GetMapping("/{id}")
	public ResponseEntity<?> contasReceberConsultor(@PathVariable Long id, String dtInicio, String dtTermino) {
		LocalDate dtInicioParse =  LocalDate.parse(dtInicio);
		List<Contrato> contratos = contratoRespository.findByIntervalDate(id, dtInicioParse, LocalDate.parse(dtTermino));
		
		int year = LocalDate.now().getYear();
		Month month = LocalDate.now().getMonth();
		
		List<ContaReceberDto> contas = contratos
				.stream()
				.filter(contrato -> {LocalDate plusMonths = contrato.getDtInicio().plusMonths(1);
					return plusMonths.getMonth() == month && plusMonths.getYear() == year && dtInicioParse.getDayOfMonth() >= plusMonths.getDayOfMonth();
				})
				.map(contrato -> new ContaReceberDto(contrato, dtInicioParse)).collect(Collectors.toList());
		
		return ResponseEntity.ok(contas);
	}

}
