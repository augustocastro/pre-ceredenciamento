package br.com.infobtc.controller.dto;

import br.com.infobtc.controller.vo.UsuarioPerfilVo;

public class UsuarioPerfilDto {

	private String email_usuario;
	private String nome_usuario;
	private double porcentagem_perfil;

	public UsuarioPerfilDto(UsuarioPerfilVo usuarioPerfilVo) {
		this.email_usuario = usuarioPerfilVo.getEmailUsuario();
		this.nome_usuario = usuarioPerfilVo.getNomeUsuario();
		this.porcentagem_perfil = usuarioPerfilVo.getPorcentagemPerfil();
	}

	public String getEmail_usuario() {
		return email_usuario;
	}

	public String getNome_usuario() {
		return nome_usuario;
	}

	public double getPorcentagem_perfil() {
		return porcentagem_perfil;
	}

}
