package br.com.infobtc.controller.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParcelaVo {

	private String data;
	private int numero_parcela;
	private Long contrato_id;
	private boolean repassado;
	private String dt_repasse;
	private double valor_repassado;

	public ParcelaVo(LocalDate data, int numero_parcela, boolean repassado, LocalDate dt_repasse, double valor_repassado, Long contrato_id) {
		this.data = data != null ? data.format(DateTimeFormatter.ofPattern("dd/MM/yyy")) : null;
		this.numero_parcela = numero_parcela;
		this.repassado = repassado;
		this.dt_repasse = dt_repasse != null ? dt_repasse.format(DateTimeFormatter.ofPattern("dd/MM/yyy")) : null;
		this.valor_repassado = valor_repassado;
		this.contrato_id = contrato_id;
	}

	public String getData() {
		return data;
	}

	public int getNumero_parcela() {
		return numero_parcela;
	}

	public boolean isRepassado() {
		return repassado;
	}

	public String getDt_repasse() {
		return dt_repasse;
	}

	public double getValor_repassado() {
		return valor_repassado;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setNumero_parcela(int numero_parcela) {
		this.numero_parcela = numero_parcela;
	}

	public void setRepassado(boolean repassado) {
		this.repassado = repassado;
	}

	public void setDt_repasse(String dt_repasse) {
		this.dt_repasse = dt_repasse;
	}

	public void setValor_repassado(double valor_repassado) {
		this.valor_repassado = valor_repassado;
	}
	
	public Long getContrato_id() {
		return contrato_id;
	}

}
