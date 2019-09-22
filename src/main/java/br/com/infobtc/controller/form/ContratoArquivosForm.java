package br.com.infobtc.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ContratoArquivosForm {

	@NotBlank
	@NotNull
	private String contrato;
	
	private MultipartFile[] arquivos;

	public MultipartFile[] getArquivos() {
		return arquivos;
	}

	public void setArquivos(MultipartFile[] arquivos) {
		this.arquivos = arquivos;
	}
	
	public String getContrato() {
		return contrato;
	}
	
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
}
