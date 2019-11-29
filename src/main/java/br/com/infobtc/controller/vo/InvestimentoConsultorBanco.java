package br.com.infobtc.controller.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.infobtc.model.TipoRendimento;

public class InvestimentoConsultorBanco {

	private String dt_inicio;
	private String dt_termino;
	private String nome_investidor;
	private String banco;
	private String titular;
	private String cpf_or_cnpj;
	private String conta;
	private String tipo_rendimento;
	private String consultor;

	public InvestimentoConsultorBanco(LocalDate dt_inicio, LocalDate dt_termino, String nome_investidor, String banco, String titular,
			String titularPj, String titularPf, String cpf, String cnpj, String cpf_or_cnpj, String conta,
			TipoRendimento tipo_rendimento, String consultor) {
		
		this.dt_inicio = dt_inicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dt_termino = dt_termino.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.nome_investidor = nome_investidor;
		this.banco = banco;
		this.titular = titular != null ? titular : titularPj != null ? titularPj : titularPf;
		this.cpf_or_cnpj = cpf_or_cnpj != null ? cpf_or_cnpj : cpf != null ? cpf : cnpj;
		this.conta = conta;
		this.tipo_rendimento = tipo_rendimento.toString();
		this.consultor = consultor;
	}

	public String getDt_inicio() {
		return dt_inicio;
	}

	public String getDt_termino() {
		return dt_termino;
	}
	
	public String getNome_investidor() {
		return nome_investidor;
	}

	public String getBanco() {
		return banco;
	}

	public String getTitular() {
		return titular;
	}

	public String getCpf_or_cnpj() {
		return cpf_or_cnpj;
	}

	public String getConta() {
		return conta;
	}

	public String getTipo_rendimento() {
		return tipo_rendimento;
	}

	public String getConsultor() {
		return consultor;
	}

	public void setDt_inicio(String dt_inicio) {
		this.dt_inicio = dt_inicio;
	}

	public void setDt_termino(String dt_termino) {
		this.dt_termino = dt_termino;
	}
	
	public void setNome_investidor(String nome_investidor) {
		this.nome_investidor = nome_investidor;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public void setCpf_or_cnpj(String cpf_or_cnpj) {
		this.cpf_or_cnpj = cpf_or_cnpj;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public void setTipo_rendimento(String tipo_rendimento) {
		this.tipo_rendimento = tipo_rendimento;
	}

	public void setConsultor(String consultor) {
		this.consultor = consultor;
	}

}
