package br.com.infobtc.controller.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.infobtc.model.Usuario;

public class UsuarioDto {
	
	private Long id;
	private String email;
	private Set<PerfilDto> perfis = new HashSet<PerfilDto>();

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.email = usuario.getEmail();
		this.perfis = new PerfilDto().converterPerfis(usuario.getPerfis());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public Set<PerfilDto> getPerfis() {
		return perfis;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPerfis(HashSet<PerfilDto> perfis) {
		this.perfis = perfis;
	}

}
