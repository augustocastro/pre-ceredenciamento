package br.com.precredenciamento.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NovaSenhaUsuarioExternoForm {

	@NotEmpty
	@NotNull
	private String novaSenha;
	
	@NotEmpty
	@NotNull
	private String confirmacaoSenha;
	
	@NotEmpty
	@NotNull
	private String codigo;

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
