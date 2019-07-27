package br.com.infobtc.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PerfilForm {
	
	@NotNull
	@NotBlank
	private String nome;
	
	public String getNome() {
		return nome;
	}
	
}
