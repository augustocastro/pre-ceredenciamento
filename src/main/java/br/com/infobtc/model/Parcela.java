package br.com.infobtc.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Parcela {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data;
	private int parcela;

	@OneToOne
	private Contrato contrato;

	@OneToOne
	private Repasse repasse;

	public Parcela() {
		
	}
	
	public Parcela(LocalDate data, int parcela, Contrato contrato) {
		this.data = data;
		this.parcela = parcela;
		this.contrato = contrato;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public int getParcela() {
		return parcela;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setParcela(int parcela) {
		this.parcela = parcela;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Repasse getRepasse() {
		return repasse;
	}

	public void setRepasse(Repasse repasse) {
		this.repasse = repasse;
	}

	@Override
	public String toString() {
		return "Parcela [data=" + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", parcela=" + parcela
				+ ", contrato=" + contrato + "]";
	}

}
