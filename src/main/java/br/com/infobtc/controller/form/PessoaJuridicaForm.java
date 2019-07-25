package br.com.infobtc.controller.form;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.PessoaJuridica;
import br.com.infobtc.repository.EnderecoRepository;
import br.com.infobtc.repository.PessoaJuridicaRepository;

public class PessoaJuridicaForm {

	@NotNull
	@NotEmpty
	private String cnpj;
	
	@NotNull
	@NotEmpty
	private String nome;
	
	@NotNull
	@NotEmpty
	private String email;
	
	@NotNull
	@NotEmpty
	private String telefone;
	
	@Valid
	@NotNull
	private EnderecoForm endereco;

	public String getCnpj() {
		return cnpj;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public EnderecoForm getEndereco() {
		return endereco;
	}

	public PessoaJuridica atualizar(Long id, PessoaJuridicaRepository pessoaJuridicaRepository, EnderecoRepository enderecoRepository) {
		PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.getOne(id);
		
		this.endereco.atualizar(pessoaJuridica.getEndereco().getId(), enderecoRepository);
		setarPropriedades(pessoaJuridica);
		
		return pessoaJuridica;
	}
	
	public void setarPropriedades(PessoaJuridica pessoaJuridica) {
		pessoaJuridica.setCnpj(cnpj);
		pessoaJuridica.setEmail(email);
		pessoaJuridica.setNome(nome);
		pessoaJuridica.setTelefone(telefone);
	}

}
