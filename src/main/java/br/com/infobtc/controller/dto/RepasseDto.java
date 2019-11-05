package br.com.infobtc.controller.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.Repasse;
import br.com.infobtc.model.Status;
import br.com.infobtc.model.TipoRecebedor;
import br.com.infobtc.model.TipoRepasse;

public class RepasseDto {

	private double valor;
	private String observacao;
	private String anexo;
	private Status status;
	private Long id_contrato;
	private Long id_parcela;
	private String data;
	private TipoRecebedor tipo_recebedor;
	private String recebedor;
	private TipoRepasse tipo_repasse;

	public RepasseDto() {

	}

	public RepasseDto(Repasse repasse) {
		this.valor = repasse.getValor();
		this.observacao = repasse.getObservacao();
		this.anexo = repasse.getAnexo();
		this.status = repasse.getStatus();
		this.id_contrato = repasse.getParcela().getContrato().getId();
		this.id_parcela = repasse.getParcela().getId();
		this.data = repasse.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.tipo_recebedor = repasse.getTipoRecebedor();
		this.recebedor = repasse.getRecebedor();
		this.tipo_repasse = repasse.getTipoRepasse();
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

	public Long getId_parcela() {
		return id_parcela;
	}

	public String getData() {
		return data;
	}

	public String getRecebedor() {
		return recebedor;
	}

	public TipoRecebedor getTipo_recebedor() {
		return tipo_recebedor;
	}

	public TipoRepasse getTipo_repasse() {
		return tipo_repasse;
	}

	public List<RepasseDto> converterPerfis(List<Repasse> repasses) {
		return repasses.stream().map(RepasseDto::new).collect(Collectors.toList());
	}
}
