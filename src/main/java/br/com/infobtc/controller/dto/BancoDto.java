package br.com.infobtc.controller.dto;

import br.com.infobtc.model.Banco;

public class BancoDto {

	private String instruicaoFinaceira;
	private String agencia;
	private String conta;
	private String tipoConta;
	private Integer codigo;
	private String titular;
	private String cpfOrCnpjTitular;

	public BancoDto(Banco banco) {
		this.instruicaoFinaceira = banco.getInstruicaoFinaceira();
		this.agencia = banco.getAgencia();
		this.conta = banco.getConta();
		this.tipoConta = banco.getTipoConta();
		this.codigo = banco.getCodigo();
		this.titular = banco.getTitular();
		this.cpfOrCnpjTitular = banco.getCpfOrCnpjTitular();
	}

	public String getInstruicaoFinaceira() {
		return instruicaoFinaceira;
	}

	public String getAgencia() {
		return agencia;
	}

	public String getConta() {
		return conta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getTitular() {
		return titular;
	}

	public String getCpfOrCnpjTitular() {
		return cpfOrCnpjTitular;
	}

}
