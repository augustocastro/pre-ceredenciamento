package br.com.infobtc.controller.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.Contrato;
import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.model.ContratoReinvestimento;

public class ContratoParcelasDto {

	private Long id;
	private String dt_inicio;
	private String dt_termino;
//	private String investidor;
	private String consultor;
	private String tipo_rendimento;
	private int quantidade_parcelas;

	public ContratoParcelasDto() {
	}
	
	public ContratoParcelasDto(Contrato contrato) {
		this.id = contrato.getId();
		this.dt_inicio = contrato.getDtInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dt_termino = contrato.getDtTermino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//		this.investidor = contrato.getInvestidor().getNome();
		this.consultor = contrato.getConsultor().getNome();
		this.quantidade_parcelas = contrato.getParcelas().size();
		
		if (contrato instanceof ContratoInvestimento) {
			ContratoInvestimento contratoInvestimento =  (ContratoInvestimento)contrato;
			this.tipo_rendimento = contratoInvestimento.getTipoRendimento().toString();
		} else if (contrato instanceof ContratoReinvestimento) {
			ContratoReinvestimento contratoReinvestimento =  (ContratoReinvestimento)contrato;
			this.tipo_rendimento = contratoReinvestimento.getInvestimento().getTipoRendimento().toString();
		}
	}

	public Long getId() {
		return id;
	}

	public String getDt_inicio() {
		return dt_inicio;
	}

	public String getDt_termino() {
		return dt_termino;
	}

//	public String getInvestidor() {
//		return investidor;
//	}

	public String getConsultor() {
		return consultor;
	}

	public String getTipo_rendimento() {
		return tipo_rendimento;
	}

	public int getQuantidade_parcelas() {
		return quantidade_parcelas;
	}
	
	public List<ContratoParcelasDto> converter(List<Contrato> contratos) {
		return contratos.stream().map(ContratoParcelasDto::new).collect(Collectors.toList());
	}

}
