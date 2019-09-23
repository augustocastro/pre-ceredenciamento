package br.com.infobtc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String centroDeCusto;
	private String numeroDoc;
	private String contaContabil;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dtCadastramento;
	private LocalDate dtVencimento;
	private LocalDate dtPagamento;
	private String historico;
	private BigDecimal valor;
	private BigDecimal valorPago;
	private BigDecimal desconto;
	private BigDecimal juros;
	private BigDecimal multa;
	private BigDecimal valorTotal;
	@Enumerated(EnumType.STRING)
	private StatusConta status;
	@OneToOne
	private Fornecedor fornecedor;

	public Conta() {
		this.dtCadastramento = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCentroDeCusto() {
		return centroDeCusto;
	}


	public String getNumeroDoc() {
		return numeroDoc;
	}

	public void setNumeroDoc(String numeroDoc) {
		this.numeroDoc = numeroDoc;
	}

	public String getContaContabil() {
		return contaContabil;
	}

	public LocalDate getDtCadastramento() {
		return dtCadastramento;
	}

	public LocalDate getDtVencimento() {
		return dtVencimento;
	}

	public LocalDate getDtPagamento() {
		return dtPagamento;
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
	
	public BigDecimal getMulta() {
		return multa;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setCentroDeCusto(String centroDeCusto) {
		this.centroDeCusto = centroDeCusto;
	}

	public void setContaContabil(String contaContabil) {
		this.contaContabil = contaContabil;
	}

	public void setDtCadastramento(LocalDate dtCadastramento) {
		this.dtCadastramento = dtCadastramento;
	}

	public void setDtVencimento(LocalDate dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public void setDtPagamento(LocalDate dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setJuros(BigDecimal juros) {
		this.juros = juros;
	}
	
	public void setMulta(BigDecimal multa) {
		this.multa = multa;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public StatusConta getStatus() {
		return status;
	}

	public void setStatus(StatusConta status) {
		this.status = status;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
