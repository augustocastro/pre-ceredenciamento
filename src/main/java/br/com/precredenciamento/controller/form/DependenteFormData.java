package br.com.precredenciamento.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class DependenteFormData {

	@NotBlank
	@NotNull
	private String dependente;
	private MultipartFile fotoPerfil;

	public String getDependente() {
		return dependente;
	}

	public MultipartFile getFotoPerfil() {
		return fotoPerfil;
	}

	public void setDependente(String dependente) {
		this.dependente = dependente;
	}

	public void setFotoPerfil(MultipartFile fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

}
