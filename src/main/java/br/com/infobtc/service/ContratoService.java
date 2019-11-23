package br.com.infobtc.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infobtc.model.Contrato;
import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.model.Status;
import br.com.infobtc.repository.ContratoRepository;

@Service
public class ContratoService {

	@Autowired
	private ContratoRepository contratoRepository;
	
	public void aplicarRedimentoComposto(List<ContratoInvestimento> contratos) {
		contratos.forEach(contrato -> {
			double valorComRendimento = contrato.getValor()
					.add(new BigDecimal(contrato.getValorRedimento())).doubleValue();
			
			contrato.setValorRedimento(valorComRendimento * 0.1);
			contratoRepository.save(contrato);
		});
	}
	
	public void finalizarContrato(List<Contrato> contratos) {
		contratos.forEach(contrato -> {
			contrato.setStatusContrato(Status.ENCERRADO);
			contrato.setStatusFinanceiro(Status.ENCERRADO);
			contratoRepository.save(contrato);
		});
	}
}
