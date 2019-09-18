package br.com.infobtc.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.Fornecedor;
import br.com.infobtc.repository.EnderecoRepository;
import br.com.infobtc.repository.FornecedorRepository;

public class FornecedorForm {

	@NotBlank
	@NotNull
	private String nome;
	@NotBlank
	@NotNull
	private String cnpj;
	private String telefone;
	private String email;
	private EnderecoForm endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public EnderecoForm getEndereco() {
		return endereco;
	}
	
	public void setEndereco(EnderecoForm endereco) {
		this.endereco = endereco;
	}

	public void setarPropriedades(Fornecedor fornecedor) {
		fornecedor.setCnpj(cnpj);
		fornecedor.setEmail(email);
		fornecedor.setNome(nome);
		fornecedor.setTelefone(telefone);
	}

	public Fornecedor atualizar(Long id, FornecedorRepository fornecedorRepository, EnderecoRepository enderecoRepository) {
		Fornecedor fornecedor = fornecedorRepository.getOne(id);
		setarPropriedades(fornecedor);
		this.endereco.atualizar(fornecedor.getEndereco().getId(), enderecoRepository);
		return fornecedor;
	}

}
