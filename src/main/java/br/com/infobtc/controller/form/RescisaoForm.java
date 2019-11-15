package br.com.infobtc.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.Contrato;
import br.com.infobtc.model.Rescisao;
import br.com.infobtc.repository.ContratoRepository;
import javassist.NotFoundException;

public class RescisaoForm {

	@NotNull
	private Long contrato_id;
	
	@NotNull
	@NotBlank
	private String justificativa;

	public Long getContrato_id() {
		return contrato_id;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setContrato_id(Long contrato_id) {
		this.contrato_id = contrato_id;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public void setarPropriedades(Rescisao rescisao, ContratoRepository contratoRespository) throws NotFoundException {
		rescisao.setJustificativa(justificativa);
		
		Optional<Contrato> optional = contratoRespository.findById(contrato_id); 
		
		if (optional.isPresent()) {
			Contrato contrato = optional.get();
			contrato.setRescisao(rescisao);
			rescisao.setContrato(contrato);
		} else {
			throw new NotFoundException(String.format("O contrato de id %s não foi encontrado.", contrato_id));
		}
	}
	
}
