package br.com.infobtc.controller.form;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.Consultor;
import br.com.infobtc.repository.ConsultorRepository;
import br.com.infobtc.repository.EnderecoRepository;
import br.com.infobtc.repository.PerfilRepository;
import br.com.infobtc.repository.UsuarioRepository;

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

	public Consultor atualizar(Long id, ConsultorRepository consultorRepository, EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) {
		Consultor consultor = consultorRepository.getOne(id);
		consultor.setNome(nome);
		consultor.setTelefone(telefone);
		
		this.usuario.atualizar(consultor.getUsuario().getId(), usuarioRepository, perfilRepository);
		this.endereco.atualizar(consultor.getEndereco().getId(), enderecoRepository);
		return consultor;
	}

}
