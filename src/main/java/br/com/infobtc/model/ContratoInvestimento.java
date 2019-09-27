package br.com.infobtc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.infobtc.controller.dto.ContratoInvestimentoDetalhadoDto;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ContratoInvestimento extends Contrato {

	private String tipoRendimento;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	private List<ContratoReinvestimento> reinvestimentos = new ArrayList<ContratoReinvestimento>();


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

	@Override
	public Object criaDto() {
		return new ContratoInvestimentoDetalhadoDto(this);
	}

}
