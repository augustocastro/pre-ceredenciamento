package br.com.infobtc.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.Fornecedor;

public class FornecedorDto {

	private Long id;
	private String nome;
	private String telefone;
	private String email;
	private String cnpj;
	private EnderecoDto endereco;

	public FornecedorDto() {
	}

	public FornecedorDto(Fornecedor fornecedor) {
		this.id = fornecedor.getId();
		this.nome = fornecedor.getNome();
		this.cnpj = fornecedor.getCnpj();
		this.telefone = fornecedor.getTelefone();
		this.email = fornecedor.getEmail();
		this.endereco = new EnderecoDto(fornecedor.getEndereco());
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public List<FornecedorDto> converter(List<Fornecedor> fornecedores) {
		return fornecedores.stream().map(FornecedorDto::new).collect(Collectors.toList());
	}

}
