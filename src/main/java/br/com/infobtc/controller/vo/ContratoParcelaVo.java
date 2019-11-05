package br.com.infobtc.controller.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.infobtc.model.Consultor;

public class ContratoParcelaVo {

	private String consultor;
	private String investidor;
	private Long contrato_id;
	private Long parcela_id;
	private double valor_investido;
	private double valor_consultor;
	private double valor_investidor;
	private double valor_escritorio;
	private String dt_inicio;
	private String dt_termino;
	private String dt_parcela;
	private int numero_parcela;
	private int quantidade_meses;

	public ContratoParcelaVo(String investidor, Long contrato_id, Long parcela_id,
			BigDecimal valor_total, LocalDate dt_inicio, LocalDate dt_termino, LocalDate dt_parcela, int numero_parcela,
			int quantidade_meses, Consultor consultor) {
		
		this.consultor = consultor.getNome();
		this.investidor = investidor;
		this.contrato_id = contrato_id;
		this.parcela_id = parcela_id;
		this.valor_investido = valor_total.doubleValue();
		this.dt_inicio = dt_inicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dt_termino = dt_termino.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dt_parcela = dt_parcela.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.quantidade_meses = quantidade_meses;
		this.numero_parcela = numero_parcela;
		if (numero_parcela == 1) {
			this.valor_investidor = valor_total.doubleValue() * 0.1;
			this.valor_escritorio = valor_total.doubleValue() * 0.01;
		} else {
			this.valor_investidor = valor_total.doubleValue() * 0.1;
			this.valor_consultor = valor_total.doubleValue() * consultor.getPorcentagem();
		}
	}

	public String getConsultor() {
		return consultor;
	}

	public String getInvestidor() {
		return investidor;
	}

	public Long getContrato_id() {
		return contrato_id;
	}

	public Long getParcela_id() {
		return parcela_id;
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

	public String getDt_parcela() {
		return dt_parcela;
	}

	public int getNumero_parcela() {
		return numero_parcela;
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

	public void setContrato_id(Long contrato_id) {
		this.contrato_id = contrato_id;
	}

	public void setParcela_id(Long parcela_id) {
		this.parcela_id = parcela_id;
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

	public void setDt_parcela(String dt_parcela) {
		this.dt_parcela = dt_parcela;
	}

	public void setNumero_parcela(int numero_parcela) {
		this.numero_parcela = numero_parcela;
	}

	public void setQuantidade_meses(int quantidade_meses) {
		this.quantidade_meses = quantidade_meses;
	}

}
