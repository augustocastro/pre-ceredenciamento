package br.com.infobtc.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class InvestidorArquivosForm {

	@NotBlank
	@NotNull
	private String investidor;
	private MultipartFile[] arquivos;
	
	public String getInvestidor() {
		return investidor;
	}

	public MultipartFile[] getArquivos() {
		return arquivos;
	}

	public void setInvestidor(String investidor) {
		this.investidor = investidor;
	}

	public void setArquivos(MultipartFile[] arquivos) {
		this.arquivos = arquivos;
	}
}
