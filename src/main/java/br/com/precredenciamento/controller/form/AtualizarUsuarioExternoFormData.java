package br.com.precredenciamento.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class AtualizarUsuarioExternoFormData {

	@NotBlank
	@NotNull
	private String usuario;
	private MultipartFile fotoPerfil;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public MultipartFile getFotoPerfil() {
		return fotoPerfil;
	}
	
	public void setFotoPerfil(MultipartFile fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

}
