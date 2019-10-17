package br.com.infobtc.controller.form;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import br.com.infobtc.model.Contrato;
import br.com.infobtc.model.Repasse;
import br.com.infobtc.model.Status;
import br.com.infobtc.repository.ContratoRepository;
import javassist.NotFoundException;

public class RepasseForm {

	private double valor;
	private String observacao;
	private MultipartFile anexo;
	private Status status;
	private Long id_contrato;
//	
//	@JsonDeserialize(using = LocalDateDeserializer.class)
//	@JsonSerialize(using = LocalDateSerializer.class)
	private String data;

	public void setarPropriedades(Repasse repasse, ContratoRepository contratoRepository) throws NotFoundException {
		repasse.setValor(valor);
		repasse.setObservacao(observacao);
		repasse.setStatus(status);
		repasse.setData(LocalDate.parse(data));

		Optional<Contrato> contrato = contratoRepository.findById(id_contrato);
		if (contrato.isPresent()) {
			repasse.setContrato(contrato.get());
		} else {
			throw new NotFoundException(String.format("O contrato de id %s não foi encontrado.", id_contrato));
		}
	}

	public double getValor() {
		return valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public MultipartFile getAnexo() {
		return anexo;
	}

	public Status getStatus() {
		return status;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void setAnexo(MultipartFile anexo) {
		this.anexo = anexo;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}

	public Long getId_contrato() {
		return id_contrato;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

}
