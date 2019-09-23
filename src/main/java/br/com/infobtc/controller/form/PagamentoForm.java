package br.com.infobtc.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class PagamentoForm {
	
	private BigDecimal juros;
	private BigDecimal desconto;
	private BigDecimal multa;
	
	@NotNull
	private BigDecimal valor_pago;
	
	@NotNull
	private BigDecimal valor_total;
	
	@NotNull
	private LocalDate dt_pagamento;
	
	public BigDecimal getJuros() {
		return juros;
	}
	
	public BigDecimal getDesconto() {
		return desconto;
	}
	
	public BigDecimal getMulta() {
		return multa;
	}

	public BigDecimal getValor_pago() {
		return valor_pago;
	}
	
	public BigDecimal getValor_total() {
		return valor_total;
	}
	
	public LocalDate getDt_pagamento() {
		return dt_pagamento;
	}
	
}
