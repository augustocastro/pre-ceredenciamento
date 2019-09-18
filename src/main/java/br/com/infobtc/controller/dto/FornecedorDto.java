package br.com.infobtc.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.Fornecedor;

public class FornecedorDto {

	private Long id;
	private String nome;
	private String cnpj;

	public FornecedorDto() {
	}

	public FornecedorDto(Fornecedor fornecedor) {
		this.id = fornecedor.getId();
		this.nome = fornecedor.getNome();
		this.cnpj = fornecedor.getCnpj();
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

	public List<FornecedorDto> converter(List<Fornecedor> fornecedores) {
		return fornecedores.stream().map(FornecedorDto::new).collect(Collectors.toList());
	}

}
