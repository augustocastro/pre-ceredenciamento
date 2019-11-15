package br.com.infobtc.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Rescisao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String justificativa;
	private LocalDate dtCadastramento;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToOne
	private Contrato contrato;
	
	public Rescisao() {
		dtCadastramento = LocalDate.now();
		status = Status.EM_ANALISE;
	}

	public Long getId() {
		return id;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public LocalDate getDtCadastramento() {
		return dtCadastramento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public void setDtCadastramento(LocalDate dtCadastramento) {
		this.dtCadastramento = dtCadastramento;
	}
	
	public Status getStatus() {
		return status;
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
	
}
