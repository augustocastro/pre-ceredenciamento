package br.com.infobtc.controller.dto;

import br.com.infobtc.model.Endereco;

public class EnderecoDto {

	private String endereco;
	private String cidade;
	private String estado;
	private String pais;
	private String cep;

	public EnderecoDto(Endereco endereco) {
		this.endereco = endereco.getEndereco();
		this.cidade = endereco.getCidade();
		this.estado = endereco.getEstado();
		this.pais = endereco.getPais();
		this.cep = endereco.getCep();
	}

	public String getEndereco() {
		return endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public String getPais() {
		return pais;
	}

	public String getCep() {
		return cep;
	}

}
