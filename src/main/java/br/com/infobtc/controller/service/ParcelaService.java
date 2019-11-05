package br.com.infobtc.controller.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infobtc.model.Contrato;
import br.com.infobtc.model.Parcela;
import br.com.infobtc.repository.ParcelaRepository;

@Service
public class ParcelaService {

	@Autowired
	private ParcelaRepository parcelaRepository;
	
	public List<Parcela> criarParcelas(Contrato contrato) {
	
		List<Parcela> parcelas = new ArrayList<Parcela>();
		
		for (int i = 1; i <= contrato.getQuantidadeMeses(); i++) {
			Parcela parcela = new Parcela(contrato.getDtInicio().plusMonths(i), i, contrato);
			parcelaRepository.save(parcela);
			parcelas.add(parcela);
		}
		
		contrato.setParcelas(parcelas);
		return parcelas;
	}
	
}



