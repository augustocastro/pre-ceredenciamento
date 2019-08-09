package br.com.infobtc.controller.dto;

import br.com.infobtc.model.DadosHash;

public class DadosHashDto {

	private Long id;
	private String hash;
	private String nome;
	private String email;

	public DadosHashDto(DadosHash dadosHash) {
		this.id = dadosHash.getId();
		this.email = dadosHash.getEmail();
		this.nome = dadosHash.getNome();
		this.hash = dadosHash.getHash();
	}
	
	public Long getId() {
		return id;
	}

	public String getHash() {
		return hash;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

}
