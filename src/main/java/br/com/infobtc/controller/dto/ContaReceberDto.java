package br.com.infobtc.controller.dto;

import java.time.format.DateTimeFormatter;

import br.com.infobtc.model.Contrato;

public class ContaReceberDto {
	private String consultor;
	private String investidor;
	private Long id_contrato;
	private double valor_bruto_investido;
	private double valor_liquido_consultor;
	private String dt_inicio;
	private String dt_termino;
	private int quantidade_meses;
	
	
	public ContaReceberDto(Contrato contrato) {
		this.consultor = contrato.getConsultor().getNome();
		this.investidor = contrato.getInvestidor().getNome();
		this.id_contrato = contrato.getId();
		this.valor_bruto_investido = contrato.getValor().doubleValue();
		this.valor_liquido_consultor = contrato.getValor().doubleValue() * 0.01;
		this.dt_inicio = contrato.getDtInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dt_termino = contrato.getDtTermino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.quantidade_meses = contrato.getQuantidadeMeses();
	}

	public String getConsultor() {
		return consultor;
	}
	
	public String getInvestidor() {
		return investidor;
	}
	
	public Long getId_contrato() {
		return id_contrato;
	}

	public double getValor_bruto_investido() {
		return valor_bruto_investido;
	}

	public double getValor_liquido_consultor() {
		return valor_liquido_consultor;
	}

	public String getDt_inicio() {
		return dt_inicio;
	}

	public String getDt_termino() {
		return dt_termino;
	}

	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}

	public void setValor_bruto_investido(double valor_bruto_investido) {
		this.valor_bruto_investido = valor_bruto_investido;
	}

	public void setValor_liquido_consultor(double valor_liquido_consultor) {
		this.valor_liquido_consultor = valor_liquido_consultor;
	}

	public void setDt_inicio(String dt_inicio) {
		this.dt_inicio = dt_inicio;
	}

	public void setDt_termino(String dt_termino) {
		this.dt_termino = dt_termino;
	}
	
	public int getQuantidade_meses() {
		return quantidade_meses;
	}

}
