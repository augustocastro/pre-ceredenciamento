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

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<InvestidorDto> converter(List<Investidor> pessoas) {
		return pessoas.stream().map(InvestidorDto::new).collect(Collectors.toList());
	}
}
