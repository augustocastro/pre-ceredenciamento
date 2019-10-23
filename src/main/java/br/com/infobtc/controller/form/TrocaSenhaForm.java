package br.com.infobtc.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TrocaSenhaForm {

	@NotBlank @NotNull private String senha_atual;
	@NotBlank @NotNull private String nova_senha;
	@NotBlank @NotNull private String confirmacao_senha;

	public String getSenha_atual() {
		return senha_atual;
	}

	public String getNova_senha() {
		return nova_senha;
	}

	public String getConfirmacao_senha() {
		return confirmacao_senha;
	}

	public void setSenha_atual(String senha_atual) {
		this.senha_atual = senha_atual;
	}

	public void setNova_senha(String nova_senha) {
		this.nova_senha = nova_senha;
	}

	public void setConfirmacao_senha(String confirmacao_senha) {
		this.confirmacao_senha = confirmacao_senha;
	}

}
