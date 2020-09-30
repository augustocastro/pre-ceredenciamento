package br.com.precredenciamento.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class AtualizarUsuarioExternoFormData {

	@NotBlank
	@NotNull
	private String usuario;
	private MultipartFile fotoPerfil;
	private MultipartFile fotoCpf;
	private MultipartFile fotoRG;
	private MultipartFile fotoCarteiraTrabalho;
	private MultipartFile[] anexos;

	public String getUsuario() {
		return usuario;
	}

	public MultipartFile getFotoPerfil() {
		return fotoPerfil;
	}

	public MultipartFile getFotoCpf() {
		return fotoCpf;
	}

	public MultipartFile getFotoRG() {
		return fotoRG;
	}

	public MultipartFile getFotoCarteiraTrabalho() {
		return fotoCarteiraTrabalho;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setFotoPerfil(MultipartFile fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public void setFotoCpf(MultipartFile fotoCpf) {
		this.fotoCpf = fotoCpf;
	}

	public void setFotoRG(MultipartFile fotoRG) {
		this.fotoRG = fotoRG;
	}

	public void setFotoCarteiraTrabalho(MultipartFile fotoCarteiraTrabalho) {
		this.fotoCarteiraTrabalho = fotoCarteiraTrabalho;
	}
	
	public MultipartFile[] getAnexos() {
		return anexos;
	}
	
	public void setAnexos(MultipartFile[] anexos) {
		this.anexos = anexos;
	}
	
}
