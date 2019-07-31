package br.com.infobtc.controller.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.ContratoReinvestimento;

public class ContratoReinvestimentoDto {

	private Long id;
	private String nome;
	private String data_inicio;
	private String dt_termino;
	private long quantidade_meses;
	private BigDecimal valor;
	private String alinea;
	private Long investimento_id;
	private BancoDto banco;

	public ContratoReinvestimentoDto() {

	}

	public ContratoReinvestimentoDto(ContratoReinvestimento contratoReinvestimento) {
		this.id = contratoReinvestimento.getId();
		this.nome = contratoReinvestimento.getNome();
		this.data_inicio = contratoReinvestimento.getDtInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dt_termino = contratoReinvestimento.getDtTermino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.quantidade_meses = contratoReinvestimento.getQuantidadeMeses();
		this.valor = contratoReinvestimento.getValor();
		this.banco = new BancoDto(contratoReinvestimento.getBanco());
		this.alinea = contratoReinvestimento.getAlinea();
		this.investimento_id = contratoReinvestimento.getInvestimento().getId();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getData_inicio() {
		return data_inicio;
	}

	public String getDt_termino() {
		return dt_termino;
	}

	public long getQuantidade_meses() {
		return quantidade_meses;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getAlinea() {
		return alinea;
	}

	public Long getInvestimento_id() {
		return investimento_id;
	}

	public BancoDto getBanco() {
		return banco;
	}

	public List<ContratoReinvestimentoDto> converter(List<ContratoReinvestimento> contratos) {
		return contratos.stream().map(ContratoReinvestimentoDto::new).collect(Collectors.toList());
	}
}
