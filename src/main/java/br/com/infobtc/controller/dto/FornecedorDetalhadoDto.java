package br.com.infobtc.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.Fornecedor;

public class FornecedorDetalhadoDto {

	private Long id;
	private String nome;
	private String cnpj;
	private String telefone;
	private String email;
	private EnderecoDto endereco;

	public FornecedorDetalhadoDto(Fornecedor fornecedor) {
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

	public String getCnpj() {
		return cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public EnderecoDto getEndereco() {
		return endereco;
	}
	
	public List<FornecedorDetalhadoDto> converter(List<Fornecedor> fornecedores) {
		return fornecedores.stream().map(FornecedorDetalhadoDto::new).collect(Collectors.toList());
	}

}
