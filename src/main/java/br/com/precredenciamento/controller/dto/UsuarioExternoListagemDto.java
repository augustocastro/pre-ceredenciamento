package br.com.precredenciamento.controller.dto;

import org.springframework.data.domain.Page;

import br.com.precredenciamento.model.UsuarioExterno;

public class UsuarioExternoListagemDto {
	
	public String cpf;
	public String nomeCompleto;
	public String email;
	
	public UsuarioExternoListagemDto() {
		// TODO Auto-generated constructor stub
	}
	
	public UsuarioExternoListagemDto(UsuarioExterno usuario) {
		this.cpf = usuario.getCpf();
		this.nomeCompleto = usuario.getNomeCompleto();
		this.email = usuario.getEmail();
	}

	public Page<UsuarioExternoListagemDto> converter(Page<UsuarioExterno> usuarios) {
		return usuarios.map(UsuarioExternoListagemDto::new);
	}
	
}
