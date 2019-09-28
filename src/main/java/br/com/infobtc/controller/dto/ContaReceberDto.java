package br.com.infobtc.controller.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.infobtc.model.Contrato;

public class ContaReceberDto {

	private String consultor;
	private String investidor;
	private Long id_contrato;
	private double valor_investido;
	private double valor_consultor;
	private double valor_investidor;
	private double valor_escritorio;
	private String dt_inicio;
	private String dt_termino;
	private int quantidade_meses;

	public ContaReceberDto(Contrato contrato, LocalDate dtInicio) {
		this.consultor = contrato.getConsultor().getNome();
		this.investidor = contrato.getInvestidor().getNome();
		this.id_contrato = contrato.getId();
		this.valor_investido = contrato.getValor().doubleValue();
		this.dt_inicio = contrato.getDtInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dt_termino = contrato.getDtTermino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.quantidade_meses = contrato.getQuantidadeMeses();
		if (contrato.getDtInicio().getMonth() != dtInicio.getMonth()) {
			this.valor_consultor = contrato.getValor().doubleValue() * 0.01;
			this.valor_investidor = contrato.getValor().doubleValue() * 0.1;
		} else {
			this.valor_escritorio = contrato.getValor().doubleValue() * 0.01;
		}
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

	public double getValor_investido() {
		return valor_investido;
	}

	public double getValor_consultor() {
		return valor_consultor;
	}

	public double getValor_investidor() {
		return valor_investidor;
	}

	public double getValor_escritorio() {
		return valor_escritorio;
	}

	public String getDt_inicio() {
		return dt_inicio;
	}

	public String getDt_termino() {
		return dt_termino;
	}

	public int getQuantidade_meses() {
		return quantidade_meses;
	}

	public void setConsultor(String consultor) {
		this.consultor = consultor;
	}

	public void setInvestidor(String investidor) {
		this.investidor = investidor;
	}

	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}

	public void setValor_investido(double valor_investido) {
		this.valor_investido = valor_investido;
	}

	public void setValor_consultor(double valor_consultor) {
		this.valor_consultor = valor_consultor;
	}

	public void setValor_investidor(double valor_investidor) {
		this.valor_investidor = valor_investidor;
	}

	public void setValor_escritorio(double valor_escritorio) {
		this.valor_escritorio = valor_escritorio;
	}

	public void setDt_inicio(String dt_inicio) {
		this.dt_inicio = dt_inicio;
	}

	public void setDt_termino(String dt_termino) {
		this.dt_termino = dt_termino;
	}

	public void setQuantidade_meses(int quantidade_meses) {
		this.quantidade_meses = quantidade_meses;
	}

}
