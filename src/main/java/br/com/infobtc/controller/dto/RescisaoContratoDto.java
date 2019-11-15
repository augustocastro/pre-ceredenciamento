package br.com.infobtc.controller.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.Rescisao;
import br.com.infobtc.model.Status;

public class RescisaoContratoDto {

	private Long id;
	private String justificativa;
	private String dt_cadastramento;
	private Status status;
	private ContratoDto contrato;

	public RescisaoContratoDto() {
	
	}
	
	public RescisaoContratoDto(Rescisao rescisao) {
		this.id = rescisao.getId();
		this.justificativa = rescisao.getJustificativa();
		this.dt_cadastramento = rescisao.getDtCadastramento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.status = rescisao.getStatus();
		this.contrato = new ContratoDto(rescisao.getContrato());
	}
	
	public Long getId() {
		return id;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public String getDt_cadastramento() {
		return dt_cadastramento;
	}

	public Status getStatus() {
		return status;
	}

	public ContratoDto getContrato() {
		return contrato;
	}

	public List<RescisaoContratoDto> converterRepasses(List<Rescisao> rescisoes) {
		return rescisoes.stream().map(RescisaoContratoDto::new).collect(Collectors.toList());
	}
	
}
