package br.com.infobtc.controller.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, isGetterVisibility = JsonAutoDetect.Visibility.ANY)
public class UsuarioPerfilVo {

	private String nomePerfil;
	private String emailUsuario;
	private String nomeUsuario;
	private double porcentagemPerfil;

	public UsuarioPerfilVo() {

	}

	public UsuarioPerfilVo(String nomePerfil, String emailConsultor, String nomeConsultor, double porcentagemOerfil) {
		this.nomePerfil = nomePerfil;
		this.emailUsuario = emailConsultor;
		this.nomeUsuario = nomeConsultor;
		this.porcentagemPerfil = porcentagemOerfil;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public double getPorcentagemPerfil() {
		return porcentagemPerfil;
	}

}
