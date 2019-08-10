package br.com.infobtc.controller.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.infobtc.model.Usuario;

public class UsuarioTokenDto {
	
	private String email;
	private Set<PerfilDto> perfis = new HashSet<PerfilDto>();

	public UsuarioTokenDto(Usuario usuario) {
		this.email = usuario.getEmail();
		this.perfis = new PerfilDto().converterPerfis(usuario.getPerfis());
	}

	public String getEmail() {
		return email;
	}

	public Set<PerfilDto> getPerfis() {
		return perfis;
	}

}
