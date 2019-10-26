package br.com.infobtc.controller.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import br.com.infobtc.model.Status;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, isGetterVisibility = JsonAutoDetect.Visibility.ANY)
public class InvestidorEnderecoVo {

	private String tipo;
	private Status status_investidor;
	private String nome;
	private String cpf_or_cnpj;
	private String telefone;
	private String endereço;
	private String dt_cadastramento;
	private String dt_nascimento;

	public InvestidorEnderecoVo(String tipo, Status status_investidor, LocalDate dt_cadastramento,
			LocalDate dt_nascimento, String nome, String cpf, String cnpj, String telefone, String endereço) {
		this.tipo = tipo;
		this.status_investidor = status_investidor;
		this.dt_cadastramento = dt_cadastramento != null ? dt_cadastramento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
		this.dt_nascimento = dt_nascimento != null ? dt_nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
		this.nome = nome;
		this.cpf_or_cnpj = cpf != null ? cpf : cnpj;
		this.telefone = telefone;
		this.endereço = endereço;
	}

	public String getTipo() {
		return tipo;
	}

	public Status getStatus_investidor() {
		return status_investidor;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf_or_cnpj() {
		return cpf_or_cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEndereço() {
		return endereço;
	}

	public String getDt_cadastramento() {
		return dt_cadastramento;
	}

	public String getDt_nascimento() {
		return dt_nascimento;
	}

}
