package br.com.infobtc.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.infobtc.model.Conta;
import br.com.infobtc.model.StatusConta;

public class ContaForm {

	@NotNull
	@NotBlank
	private String centro_de_custo;

	@NotNull
	@NotBlank
	private String fornecedor;

	@NotNull
	@NotBlank
	private String numero_doc;

	@NotNull
	@NotBlank
	private String conta_contabil;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@NotNull
	private LocalDate dt_vencimento;

	private String historico;

	@NotNull
	private BigDecimal valor;

	private String juros;
	private BigDecimal desconto;

	@NotNull
	private BigDecimal valor_total;

	public String getCentro_de_custo() {
		return centro_de_custo;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public String getNumero_doc() {
		return numero_doc;
	}

	public String getConta_contabil() {
		return conta_contabil;
	}

	public LocalDate getDt_vencimento() {
		return dt_vencimento;
	}

	public String getHistorico() {
		return historico;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getJuros() {
		return juros;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public BigDecimal getValor_total() {
		return valor_total;
	}

	public Conta setarPropriedades(Conta conta) {
		conta.setCentroDeCusto(centro_de_custo);
		conta.setFornecedor(fornecedor);
		conta.setContaContabil(conta_contabil);
		conta.setDesconto(desconto);
		conta.setDtVencimento(dt_vencimento);
		conta.setHistorico(historico);
		conta.setJuros(juros);
		conta.setValor(valor);
		conta.setValorTotal(valor_total);
		conta.setNumeroDoc(numero_doc);
		conta.setStatus(StatusConta.EM_ABERTO);
		conta.setValorPago(new BigDecimal(0));
		return conta;
	}

	public void atualizar(Conta conta) {
		setarPropriedades(conta);
	}

}
