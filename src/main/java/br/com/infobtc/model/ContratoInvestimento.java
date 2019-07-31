package br.com.infobtc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ContratoInvestimento extends Contrato {

	private String tipoRendimento;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	private List<ContratoReinvestimento> reinvestimentos = new ArrayList<ContratoReinvestimento>();

	@OneToOne
	private Investidor investidor;

	public String getTipoRendimento() {
		return tipoRendimento;
	}

	public void setTipoRendimento(String tipoRendimento) {
		this.tipoRendimento = tipoRendimento;
	}

	public List<ContratoReinvestimento> getReinvestimentos() {
		return reinvestimentos;
	}

	public void setReinvestimentos(List<ContratoReinvestimento> reinvestimentos) {
		this.reinvestimentos = reinvestimentos;
	}

	public Investidor getInvestidor() {
		return investidor;
	}

	public void setInvestidor(Investidor investidor) {
		this.investidor = investidor;
	}

}
