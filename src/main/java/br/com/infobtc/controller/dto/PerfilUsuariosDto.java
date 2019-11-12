package br.com.infobtc.controller.dto;

import java.util.List;

public class PerfilUsuariosDto {

	private String nome;
	private List<UsuarioPerfilDto> usuarios;

	public PerfilUsuariosDto(String nome, List<UsuarioPerfilDto> usuarios) {
		this.nome = nome;
		this.usuarios = usuarios;
	}

	public String getNome() {
		return nome;
	}

	public List<UsuarioPerfilDto> getUsuarios() {
		return usuarios;
	}

}
