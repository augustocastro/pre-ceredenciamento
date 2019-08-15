package br.com.infobtc.controller.dto;

import br.com.infobtc.model.Banco;

public class BancoDto {

	private String instruicao_finaceira;
	private String agencia;
	private String conta;
	private String tipo_conta;
	private Integer codigo;
	private String titular;
	private String cpf_or_cnpj_titular;

	public BancoDto(Banco banco) {
		this.instruicao_finaceira = banco.getInstruicaoFinaceira();
		this.agencia = banco.getAgencia();
		this.conta = banco.getConta();
		this.tipo_conta = banco.getTipoConta();
		this.codigo = banco.getCodigo();
		this.titular = banco.getTitular();
		this.cpf_or_cnpj_titular = banco.getCpfOrCnpjTitular();
	}

	public String getInstruicao_finaceira() {
		return instruicao_finaceira;
	}

	public String getAgencia() {
		return agencia;
	}

	public String getConta() {
		return conta;
	}

	public String getTipo_conta() {
		return tipo_conta;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getTitular() {
		return titular;
	}

	public String getCpf_or_cnpj_titular() {
		return cpf_or_cnpj_titular;
	}

}
