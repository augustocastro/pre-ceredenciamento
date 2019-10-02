package br.com.infobtc.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class PagamentoForm {

	private BigDecimal juros;
	private BigDecimal desconto;
	private BigDecimal multa;

	@NotNull
	private BigDecimal valor_pago;

	@NotNull
	private BigDecimal valor_total;

	@NotNull
	@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
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

	public void setJuros(BigDecimal juros) {
		this.juros = juros;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public void setMulta(BigDecimal multa) {
		this.multa = multa;
	}

	public void setValor_pago(BigDecimal valor_pago) {
		this.valor_pago = valor_pago;
	}

	public void setValor_total(BigDecimal valor_total) {
		this.valor_total = valor_total;
	}

	public void setDt_pagamento(LocalDate dt_pagamento) {
		this.dt_pagamento = dt_pagamento;
	}

}
