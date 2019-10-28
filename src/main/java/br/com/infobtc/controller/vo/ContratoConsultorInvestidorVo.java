package br.com.infobtc.controller.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.infobtc.model.Status;

public class ContratoConsultorInvestidorVo {

	private Long id;
	private String status_contrato;
	private String status_financeiro;
	private String investidor;
	private String consultor;
	private String dt_cadastramento;
	private double valor;
	private String prazo;

	public ContratoConsultorInvestidorVo(Long id, Status status_contrato, Status status_financeiro, String investidor,
			String consultor, LocalDate dt_cadastramento, BigDecimal valor, LocalDate dtInicio, LocalDate dtTermino) {
		this.id = id;
		this.status_contrato = status_contrato.toString();
		this.status_financeiro = status_financeiro.toString();
		this.investidor = investidor;
		this.consultor = consultor;
		this.dt_cadastramento = dt_cadastramento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.valor = valor.doubleValue();
		this.prazo = dtInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " a " + dtInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public Long getId() {
		return id;
	}

	public String getStatus_contrato() {
		return status_contrato;
	}

	public String getStatus_financeiro() {
		return status_financeiro;
	}

	public String getInvestidor() {
		return investidor;
	}

	public String getConsultor() {
		return consultor;
	}

	public String getDt_cadastramento() {
		return dt_cadastramento;
	}

	public double getValor() {
		return valor;
	}

	public String getPrazo() {
		return prazo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStatus_contrato(String status_contrato) {
		this.status_contrato = status_contrato;
	}

	public void setStatus_financeiro(String status_financeiro) {
		this.status_financeiro = status_financeiro;
	}

	public void setInvestidor(String investidor) {
		this.investidor = investidor;
	}

	public void setConsultor(String consultor) {
		this.consultor = consultor;
	}

	public void setDt_cadastramento(String dt_cadastramento) {
		this.dt_cadastramento = dt_cadastramento;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

}
