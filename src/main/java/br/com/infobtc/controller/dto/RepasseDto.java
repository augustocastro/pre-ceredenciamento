package br.com.infobtc.controller.dto;

import java.time.format.DateTimeFormatter;

import br.com.infobtc.model.Repasse;
import br.com.infobtc.model.Status;

public class RepasseDto {

	private double valor;
	private String observacao;
	private String anexo;
	private Status status;
	private Long id_contrato;
	private String data;
	
	public RepasseDto(Repasse repasse) {
		super();
		this.valor = repasse.getValor();
		this.observacao = repasse.getObservacao();
		this.anexo = repasse.getAnexo();
		this.status = repasse.getStatus();
		this.id_contrato = repasse.getContrato().getId();
		this.data = repasse.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public double getValor() {
		return valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public String getAnexo() {
		return anexo;
	}

	public Status getStatus() {
		return status;
	}

	public Long getId_contrato() {
		return id_contrato;
	}

	public String getData() {
		return data;
	}
}
