package br.com.infobtc.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.infobtc.controller.dto.ContratoReinvestimentoDto;
import br.com.infobtc.mapper.ContratoMapper;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ContratoReinvestimento extends Contrato implements ContratoMapper {

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.REMOVE)
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

	@Override
	public Object criaDto() {
		return new ContratoReinvestimentoDto(this);
	}
}
