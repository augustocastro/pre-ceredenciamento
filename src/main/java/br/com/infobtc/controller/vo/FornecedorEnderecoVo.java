package br.com.infobtc.controller.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, isGetterVisibility = JsonAutoDetect.Visibility.ANY)
public class FornecedorEnderecoVo {
	
	private String nome;
	private String cnpj;
	private String telefone;
	private String endereco;
	private String dt_cadastramento;

	public FornecedorEnderecoVo(String nome, String cnpj, String telefone, String endereco,
			LocalDate dt_cadastramento) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.endereco = endereco;
		this.dt_cadastramento = dt_cadastramento != null ? dt_cadastramento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
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

	public String getEndereco() {
		return endereco;
	}

	public String getDt_cadastramento() {
		return dt_cadastramento;
	}

}
