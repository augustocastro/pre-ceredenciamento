package br.com.infobtc.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ContratoReinvestimento extends Contrato {

	@JsonBackReference
	@ManyToOne
	private ContratoInvestimento investimento;
	private String alinea;

	public ContratoInvestimento getInvestimento() {
		return investimento;
	}

	public String getAlinea() {
		return alinea;
	}

	public void setInvestimento(ContratoInvestimento investimento) {
		this.investimento = investimento;
	}

	public void setAlinea(String alinea) {
		this.alinea = alinea;
	}
}
