package br.com.infobtc.controller.form;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import br.com.infobtc.model.Contrato;
import br.com.infobtc.model.Parcela;
import br.com.infobtc.model.Repasse;
import br.com.infobtc.model.Status;
import br.com.infobtc.model.TipoRecebedor;
import br.com.infobtc.model.TipoRepasse;
import br.com.infobtc.repository.ParcelaRepository;
import javassist.NotFoundException;

public class RepasseForm {

	private double valor;
	private String observacao;
	private MultipartFile anexo;
	private Status status;
	private Long parcela_id;
	@NotNull
	private TipoRecebedor tipo_recebedor;
	@NotNull
	private TipoRepasse tipo_repasse;
	private String data;

	public void setarPropriedades(Repasse repasse, ParcelaRepository parcelaRepository) throws NotFoundException {
		repasse.setValor(valor);
		repasse.setObservacao(observacao);
		repasse.setStatus(status);
		repasse.setData(LocalDate.parse(data));
		repasse.setTipoRecebedor(tipo_recebedor);
		repasse.setTipoRepasse(tipo_repasse);

		Optional<Parcela> optional = parcelaRepository.findById(parcela_id);

		if (optional.isPresent()) {
			Parcela parcela = optional.get();
			Contrato contrato = parcela.getContrato();
			parcela.setRepasse(repasse);

			repasse.setParcela(parcela);

			if (tipo_recebedor == TipoRecebedor.CONSULTOR) {
				repasse.setRecebedor(contrato.getConsultor().getNome());
			} else if (tipo_recebedor == TipoRecebedor.INVESTIDOR) {
				repasse.setRecebedor(contrato.getInvestidor().getNome());
			} else if (tipo_recebedor == TipoRecebedor.ESCRITORIO) {
				repasse.setRecebedor("Escritório");
			}

		} else {
			throw new NotFoundException(String.format("O contrato de id %s não foi encontrado.", parcela_id));
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

	public Long getParcela_id() {
		return parcela_id;
	}

	public void setParcela_id(Long parcela_id) {
		this.parcela_id = parcela_id;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public TipoRecebedor getTipo_recebedor() {
		return tipo_recebedor;
	}

	public void setTipo_recebedor(TipoRecebedor tipo_recebedor) {
		this.tipo_recebedor = tipo_recebedor;
	}

	public void setTipo_repasse(TipoRepasse tipo_repasse) {
		this.tipo_repasse = tipo_repasse;
	}

	public TipoRepasse getTipo_repasse() {
		return tipo_repasse;
	}

}
