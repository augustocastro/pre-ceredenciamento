package br.com.infobtc.controller.dto;

import br.com.infobtc.model.Banco;

public class BancoDto {

	private String instituicao_financeira;
	private String agencia;
	private String conta;
	private String tipo_conta;
	private Integer codigo;
	private String titular;
	private String cpf_or_cnpj_titular;

	public BancoDto(Banco banco) {
		this.instituicao_financeira = banco.getInstituicaoFinanceira();
		this.agencia = banco.getAgencia();
		this.conta = banco.getConta();
		this.tipo_conta = banco.getTipoConta();
		this.codigo = banco.getCodigo();
		this.titular = banco.getTitular();
		this.cpf_or_cnpj_titular = banco.getCpfOrCnpjTitular();
	}

	public String getInstituicao_finaceira() {
		return instituicao_financeira;
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
