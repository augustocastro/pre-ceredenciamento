package br.com.infobtc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.infobtc.controller.dto.ContratoInvestimentoDetalhadoDto;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ContratoInvestimento extends Contrato {

	private String tipoRendimento;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	private List<ContratoReinvestimento> reinvestimentos = new ArrayList<ContratoReinvestimento>();

	@OneToOne
	private Investidor investidor;

	@OneToOne
	private Consultor consultor;

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

	public Consultor getConsultor() {
		return consultor;
	}

	public void setConsultor(Consultor consultor) {
		this.consultor = consultor;
	}

	@Override
	public Object criaDto(Contrato contrato) {
		ContratoInvestimento contratoInvestimento = (ContratoInvestimento) contrato;
		return new ContratoInvestimentoDetalhadoDto(contratoInvestimento);
	}

}
