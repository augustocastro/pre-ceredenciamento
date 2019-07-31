package br.com.infobtc.controller.dto;

import br.com.infobtc.model.Consultor;

public class ConsultorDto {

	private Long id;
	private String nome;
	private String telefone;

	public ConsultorDto(Consultor consultor) {
		this.id = consultor.getId();
		this.nome = consultor.getNome();
		this.telefone = consultor.getTelefone();
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

}
