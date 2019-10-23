package br.com.infobtc.controller.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.Conta;

public class ContaDetalhadoDto {

	private Long id;
	private String centro_de_custo;
	private FornecedorDto fornecedor;
	private String numero_doc;
	private String conta_contabil;
	private String dt_cadastramento;
	private String dt_vencimento;
	private String dt_pagamento;
	private String status;
	private String historico;
	private BigDecimal valor;
	private BigDecimal juros;
	private BigDecimal desconto;
	private BigDecimal multa;
	private BigDecimal valor_total;
	private BigDecimal valor_pago;
	private List<String> arquivos_url = new ArrayList<String>();

	public ContaDetalhadoDto() {

	}

	public ContaDetalhadoDto(Conta conta) {
		this.id = conta.getId();
		this.centro_de_custo = conta.getCentroDeCusto();
		this.fornecedor = new FornecedorDto(conta.getFornecedor());
		this.conta_contabil = conta.getContaContabil();
		this.dt_cadastramento = conta.getDtCadastramento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dt_vencimento = conta.getDtVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.historico = conta.getHistorico();
		this.valor = conta.getValor();
		this.juros = conta.getJuros();
		this.desconto = conta.getDesconto();
		this.valor_total = conta.getValorTotal();
		this.numero_doc = conta.getNumeroDoc();
		this.status = conta.getStatus().name();
		this.multa = conta.getMulta();
		this.arquivos_url = conta.getArquivosUrl();
		if (conta.getDtPagamento() != null) {
			this.dt_pagamento = conta.getDtPagamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}

		if (conta.getValorPago() != null) {
			this.valor_pago = conta.getValorPago();
		}
	}

	public Long getId() {
		return id;
	}

	public String getCentro_de_custo() {
		return centro_de_custo;
	}

	public FornecedorDto getFornecedor() {
		return fornecedor;
	}

	public String getNumero_doc() {
		return numero_doc;
	}

	public String getConta_contabil() {
		return conta_contabil;
	}

	public String getDt_cadastramento() {
		return dt_cadastramento;
	}

	public String getDt_vencimento() {
		return dt_vencimento;
	}

	public String getDt_pagamento() {
		return dt_pagamento;
	}

	public String getStatus() {
		return status;
	}

	public String getHistorico() {
		return historico;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public BigDecimal getJuros() {
		return juros;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}
	
	public BigDecimal getMulta() {
		return multa;
	}

	public BigDecimal getValor_total() {
		return valor_total;
	}

	public BigDecimal getValor_pago() {
		return valor_pago;
	}

	public List<String> getArquivos_url() {
		return arquivos_url;
	}
	
	public List<ContaDetalhadoDto> converter(List<Conta> perfis) {
		return perfis.stream().map(ContaDetalhadoDto::new).collect(Collectors.toList());
	}

}
