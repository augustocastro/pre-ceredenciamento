package br.com.infobtc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.infobtc.controller.dto.ContratoInvestimentoDetalhadoDto;
import br.com.infobtc.mapper.ContratoMapper;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ContratoInvestimento extends Contrato implements ContratoMapper {

	@Enumerated(EnumType.STRING)
	private TipoRendimento tipoRendimento;
	private double valorRedimento;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	private List<ContratoReinvestimento> reinvestimentos = new ArrayList<ContratoReinvestimento>();


	public TipoRendimento getTipoRendimento() {
		return tipoRendimento;
	}

	public void setTipoRendimento(TipoRendimento tipoRendimento) {
		this.tipoRendimento = tipoRendimento;
	}

	public List<ContratoReinvestimento> getReinvestimentos() {
		return reinvestimentos;
	}

	public void setReinvestimentos(List<ContratoReinvestimento> reinvestimentos) {
		this.reinvestimentos = reinvestimentos;
	}
	
	public double getValorRedimento() {
		return valorRedimento;
	}
	
	public void setValorRedimento(double valorRedimento) {
		this.valorRedimento += valorRedimento;
	}

	@Override
	public Object criaDto() {
		return new ContratoInvestimentoDetalhadoDto(this);
	}

}
