package br.com.infobtc.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Repasse {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double valor;
	private String observacao;
	private String anexo;
	private Status status;
	private LocalDate data;
	
	@OneToOne
	private Contrato contrato;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	public Contrato getContrato() {
		return contrato;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}

}
