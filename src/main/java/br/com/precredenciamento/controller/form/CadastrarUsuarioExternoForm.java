package br.com.precredenciamento.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.precredenciamento.model.UsuarioExterno;

public class CadastrarUsuarioExternoForm {

	@NotBlank
	public String cpf;
	
	@NotBlank
	public String nomeCompleto;
	
	@NotBlank
	public String email;
	
	@NotBlank
	public String senha;
	
	public void setarPropriedades(UsuarioExterno usuario) {
		usuario.setCpf(cpf);
		usuario.setNomeCompleto(nomeCompleto);
		usuario.setEmail(email);
		usuario.setSenha(senha);
	}
}
