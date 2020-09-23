package br.com.precredenciamento.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RecuperarSenhaUsuarioExternoForm {

	@NotEmpty
	@NotNull
	private String cpf;

	@NotEmpty
	@NotNull
	private String email;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
