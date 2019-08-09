package br.com.infobtc.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.DadosHash;

public class DadosHashForm {

	@NotBlank
	@NotNull
	private String nome;
	
	@NotBlank
	@NotNull
	private String email;

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
	
	public void setarPropriedades(DadosHash codigoHash) {
		codigoHash.setEmail(email);
		codigoHash.setNome(nome);
	}

}
