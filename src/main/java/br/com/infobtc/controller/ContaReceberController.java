package br.com.infobtc.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.infobtc.controller.vo.ContratoParcelaVo;
import br.com.infobtc.dao.ContratoDao;

@RestController
@RequestMapping("/conta-receber")
public class ContaReceberController {
	
	@Autowired
	private ContratoDao contratoDao;
	
	@GetMapping()
	public ResponseEntity<?> consultarContasPorIntervaloEConsultor(Long idConsultor, @RequestParam(required = true) String dtInicio, 
			@RequestParam(required = true) String dtTermino, Boolean repassado) {
		LocalDate dtInicioParse =  LocalDate.parse(dtInicio);
		LocalDate dtTerminoParse =  LocalDate.parse(dtTermino);
		
		List<ContratoParcelaVo> parcelas = contratoDao.consultarParcelasPorIntervaloEConsultor(dtInicioParse, dtTerminoParse, idConsultor, false);	
		return ResponseEntity.ok(parcelas);
	}

}
