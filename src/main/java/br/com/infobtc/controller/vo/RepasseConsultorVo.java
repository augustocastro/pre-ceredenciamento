package br.com.infobtc.controller.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.infobtc.model.Status;
import br.com.infobtc.model.TipoRecebedor;
import br.com.infobtc.model.TipoRepasse;

public class RepasseConsultorVo {

	private double valor;
	private String observacao;
	private String anexo;
	private Status status;
	private Long id_contrato;
	private Long id_parcela;
	private String data;
	private TipoRecebedor tipo_recebedor;
	private TipoRepasse tipo_repasse;
	private String recebedor;
	private String repassador;

	public RepasseConsultorVo(double valor, String observacao, String anexo, Status status, Long id_contrato, Long id_parcela,  LocalDate data, TipoRecebedor tipo_recebedor, TipoRepasse tipo_repasse,
			String recebedor, String repassador) {
		this.valor = valor;
		this.observacao = observacao;
		this.anexo = anexo;
		this.status = status;
		this.id_contrato = id_contrato;
		this.id_parcela = id_parcela;
		this.data = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.tipo_recebedor = tipo_recebedor;
		this.recebedor = recebedor;
		this.tipo_repasse = tipo_repasse;
		this.repassador = repassador;
	}

	public double getValor() {
		return valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public String getAnexo() {
		return anexo;
	}

	public Status getStatus() {
		return status;
	}

	public Long getId_contrato() {
		return id_contrato;
	}

	public Long getId_parcela() {
		return id_parcela;
	}

	public String getData() {
		return data;
	}

	public TipoRecebedor getTipo_recebedor() {
		return tipo_recebedor;
	}

	public String getRecebedor() {
		return recebedor;
	}

	public TipoRepasse getTipo_repasse() {
		return tipo_repasse;
	}

	public String getRepassador() {
		return repassador;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}

	public void setId_parcela(Long id_parcela) {
		this.id_parcela = id_parcela;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setTipo_recebedor(TipoRecebedor tipo_recebedor) {
		this.tipo_recebedor = tipo_recebedor;
	}

	public void setRecebedor(String recebedor) {
		this.recebedor = recebedor;
	}

	public void setTipo_repasse(TipoRepasse tipo_repasse) {
		this.tipo_repasse = tipo_repasse;
	}

	public void setRepassador(String repassador) {
		this.repassador = repassador;
	}

}
