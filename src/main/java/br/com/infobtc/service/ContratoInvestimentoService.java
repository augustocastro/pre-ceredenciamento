package br.com.infobtc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.repository.ContratoRepository;

@Service
public class ContratoInvestimentoService {

	@Autowired
	private ContratoRepository contratoRepository;
	
	public void aplicarRedimentoComposto(List<ContratoInvestimento> contratos) {
		contratos.forEach(contrato -> {
			double valorInvestido = contrato.getValor().doubleValue();
			contrato.setValorRedimento(valorInvestido * 0.1);
			contratoRepository.save(contrato);
		});
	}
}
