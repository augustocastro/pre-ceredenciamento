package br.com.precredenciamento.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.precredenciamento.model.UsuarioExterno;

public class CadastrarUsuarioExternoForm {

	public Long id;
	
	@NotBlank
	public String cpf;
	
	@NotBlank
	public String nomeCompleto;
	
	public String email;
	
	@NotBlank
	public String senha;
	
	public void setarPropriedades(UsuarioExterno usuario) {
		usuario.setId(id);
		usuario.setCpf(cpf);
		usuario.setNomeCompleto(nomeCompleto);
		usuario.setEmail(email);
		usuario.setSenha(senha);
	}
}
