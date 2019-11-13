package br.com.infobtc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infobtc.controller.vo.TotalContratoInvestidoresVolumeCapitalVo;
import br.com.infobtc.dao.RelatorioDao;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

	@Autowired
	private RelatorioDao relatorioDao;

	@GetMapping("/totais-mensal")
	public ResponseEntity<?> buscarTodos() {
		TotalContratoInvestidoresVolumeCapitalVo resultado = relatorioDao.calculaTotaisMensal();
		return ResponseEntity.ok(resultado);
	}
}
