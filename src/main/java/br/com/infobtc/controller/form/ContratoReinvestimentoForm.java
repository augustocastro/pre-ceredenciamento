package br.com.infobtc.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.model.ContratoReinvestimento;
import br.com.infobtc.repository.BancoRepository;
import br.com.infobtc.repository.ContratoInvestimentoRepository;
import br.com.infobtc.repository.ContratoReinvestimentoRepository;
import javassist.NotFoundException;

public class ContratoReinvestimentoForm {
	
	@NotNull
	private String dt_inicio;
	
	@NotNull
	private String dt_termino;
	
	@NotNull
	private Integer quantidade_meses;
	
	@NotNull
	@NotEmpty
	private String valor;
	
	@NotNull
	private Long investimento_id;
	
	@NotNull
	@NotEmpty
	private String alinea;
	
	@Valid
	@NotNull
	private BancoForm banco;
	
	public String getDt_inicio() {
		return dt_inicio;
	}

	public String getDt_termino() {
		return dt_termino;
	}

	public Integer getQuantidade_meses() {
		return quantidade_meses;
	}

	public String getValor() {
		return valor;
	}

	public BancoForm getBanco() {
		return banco;
	}
	
	public String getAlinea() {
		return alinea;
	}
	
	public void setAlinea(String alinea) {
		this.alinea = alinea;
	}
	public void setInvestimento_id(Long investimento_id) {
		this.investimento_id = investimento_id;
	}
	
	public void setarPropriedades(ContratoReinvestimento contrato, ContratoInvestimentoRepository contratoInvestimentoRepository) throws NotFoundException {
		contrato.setValor(new BigDecimal(valor));
		contrato.setDtInicio(LocalDate.parse(dt_inicio));
		contrato.setDtTermino(LocalDate.parse(dt_termino));
		contrato.setQuantidadeMeses(quantidade_meses);	
		contrato.setAlinea(alinea);
		
		Optional<ContratoInvestimento> contratoInvestimento = contratoInvestimentoRepository.findById(investimento_id);
		
		if (contratoInvestimento.isPresent()) {
			contrato.setInvestimento(contratoInvestimento.get());
			contrato.setConsultor(contratoInvestimento.get().getConsultor());
			contrato.setInvestidor(contratoInvestimento.get().getInvestidor());
		} else {
			throw new NotFoundException(String.format("O investimento de id %s não foi encontrado.", investimento_id));
		}
	}

	public ContratoReinvestimento atualizar(Long id, ContratoInvestimentoRepository contratoInvestimentoRepository, ContratoReinvestimentoRepository contratoReinvestimentoRepository, BancoRepository bancoRepository) throws NotFoundException {
		ContratoReinvestimento contrato = contratoReinvestimentoRepository.getOne(id);
		setarPropriedades(contrato, contratoInvestimentoRepository);
		banco.atualizar(contrato.getBanco().getId(), bancoRepository);
		return contrato;
	}
	
}
