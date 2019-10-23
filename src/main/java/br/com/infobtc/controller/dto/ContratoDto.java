package br.com.infobtc.controller.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.Contrato;

public class ContratoDto {

	private Long id;
	private String dt_inicio;
	private String dt_termino;
	private long quantidade_meses;
	private BigDecimal valor;
	private String investidor;
	private String consultor;

	public ContratoDto() {
		
	}
	
	public ContratoDto(Contrato contrato) {
		this.id = contrato.getId();
		this.dt_inicio = contrato.getDtInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dt_termino = contrato.getDtTermino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.quantidade_meses = contrato.getQuantidadeMeses();
		this.valor = contrato.getValor();
		this.investidor = contrato.getInvestidor().getNome(	);
		this.consultor = contrato.getConsultor().getNome();
	}

	public Long getId() {
		return id;
	}

	public String getDt_inicio() {
		return dt_inicio;
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

	public String getInvestidor() {
		return investidor;
	}

	public String getConsultor() {
		return consultor;
	}

	public List<ContratoDto> converter(List<Contrato> contratos) {
		return contratos.stream().map(ContratoDto::new).collect(Collectors.toList());
	}

}
