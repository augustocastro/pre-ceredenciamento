package br.com.infobtc.controller.dto;

import java.time.format.DateTimeFormatter;

import br.com.infobtc.model.Rescisao;
import br.com.infobtc.model.Status;

public class RescisaoDto {

	private String justificativa;
	private String dt_cadastramento;
	private Long contrato_id;
	private Status status;
	private Long id;

	public RescisaoDto() {
		
	}
	
	public RescisaoDto(Rescisao rescisao) {
		this.justificativa = rescisao.getJustificativa();
		this.dt_cadastramento = rescisao.getDtCadastramento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.contrato_id = rescisao.getContrato().getId();
		this.status = rescisao.getStatus();
		this.id = rescisao.getId();
	}

	public String getJustificativa() {
		return justificativa;
	}

	public String getDt_cadastramento() {
		return dt_cadastramento;
	}

	public Long getContrato_id() {
		return contrato_id;
	}

	public Status getStatus() {
		return status;
	}

	public Long getId() {
		return id;
	}

}
