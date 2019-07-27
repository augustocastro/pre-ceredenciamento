package br.com.infobtc.controller.form;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.Consultor;

public class ConsultorForm {

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@NotEmpty
	private String telefone;

	@Valid
	@NotNull
	private UsuarioForm usuario;

	@Valid
	@NotNull
	private EnderecoForm endereco;
	
	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public UsuarioForm getUsuario() {
		return usuario;
	}

	public EnderecoForm getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoForm endereco) {
		this.endereco = endereco;
	}

	public void setarPropriedades(Consultor consultor) {
		consultor.setNome(nome);
		consultor.setTelefone(telefone);
	}

}
