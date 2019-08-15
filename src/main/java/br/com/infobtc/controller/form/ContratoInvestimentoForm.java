package br.com.infobtc.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.Consultor;
import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.model.Investidor;
import br.com.infobtc.repository.BancoRepository;
import br.com.infobtc.repository.ConsultorRepository;
import br.com.infobtc.repository.ContratoInvestimentoRepository;
import br.com.infobtc.repository.InvestidorRepository;

public class ContratoInvestimentoForm {

	@NotNull
	@NotEmpty
	private String nome;
	
	@NotNull
	private LocalDate dt_inicio;
	
	@NotNull
	private LocalDate dt_termino;
	
	@NotNull
	private Integer quantidade_meses;
	
	@NotNull
	@NotEmpty
	private String valor;
	
	@NotNull
	private Long investidor_id;

	
	@NotNull
	private Long consultor_id;

	@NotNull
	@NotEmpty
	private String tipo_rendimento;

	
	@Valid
	@NotNull
	private BancoForm banco;
	
	public String getNome() {
		return nome;
	}
	
	public LocalDate getDt_inicio() {
		return dt_inicio;
	}

	public LocalDate getDt_termino() {
		return dt_termino;
	}

	public Integer getQuantidade_meses() {
		return quantidade_meses;
	}

	public String getValor() {
		return valor;
	}

	public Long getInvestidor_id() {
		return investidor_id;
	}
	
	public String getTipo_rendimento() {
		return tipo_rendimento;
	}

	public Long getConsultor_id() {
		return consultor_id;
	}
	
	public BancoForm getBanco() {
		return banco;
	}
	

	public void setarPropriedades(ContratoInvestimento contrato, InvestidorRepository investidorRepository, ConsultorRepository consultorRepository) {
		contrato.setNome(nome);
		contrato.setTipoRendimento(tipo_rendimento);
		contrato.setValor(new BigDecimal(valor));
		contrato.setDtInicio(dt_inicio);
		contrato.setDtTermino(dt_termino);
		contrato.setQuantidadeMeses(quantidade_meses);	
		
		Optional<Investidor> investidor = investidorRepository.findById(investidor_id);
		Optional<Consultor> consultor = consultorRepository.findById(consultor_id);
		
		if (investidor.isPresent()) {
			contrato.setInvestidor(investidor.get());
		}
		
		if (investidor.isPresent()) {
			contrato.setConsultor(consultor.get());

		}
	}

	public ContratoInvestimento atualizar(Long id, ContratoInvestimentoRepository contratoInvestimentoRepository, 
			BancoRepository bancoRepository, InvestidorRepository investidorRepository, ConsultorRepository consultorRepository) {
		ContratoInvestimento contrato = contratoInvestimentoRepository.getOne(id);
		setarPropriedades(contrato, investidorRepository, consultorRepository);
		banco.atualizar(contrato.getBanco().getId(), bancoRepository);
		return contrato;
	}
	
}
