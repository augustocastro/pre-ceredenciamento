package br.com.infobtc.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.Investidor;

public class InvestidorDto {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String tipo;

	public InvestidorDto() {
	}

	public InvestidorDto(Investidor pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.email = pessoa.getEmail();
		this.telefone = pessoa.getTelefone();
		this.tipo = pessoa.getTipo();
	}

	public Long getId() {
		return id;
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

	public String getTipo() {
		return tipo;
	}

	public List<InvestidorDto> converter(List<Investidor> pessoas) {
		return pessoas.stream().map(InvestidorDto::new).collect(Collectors.toList());
	}

}
