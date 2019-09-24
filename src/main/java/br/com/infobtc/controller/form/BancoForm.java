package br.com.infobtc.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.Banco;
import br.com.infobtc.repository.BancoRepository;

public class BancoForm {

	@NotNull
	@NotEmpty
	private String instituicao_financeira;

	@NotNull
	@NotEmpty
	private String agencia;

	@NotNull
	@NotEmpty
	private String conta;

	@NotNull
	@NotEmpty
	private String tipo_conta;

	private Integer codigo;

	@NotNull
	@NotEmpty
	private String titular;

	@NotNull
	@NotEmpty
	private String cpf_or_cnpj_titular;

	public String getInstituicao_financeira() {
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

	public void setarPropriedades(Banco banco) {
		banco.setInstituicaoFinanceira(instituicao_financeira);
		banco.setAgencia(agencia);
		banco.setConta(conta);
		banco.setTipoConta(tipo_conta);
		banco.setCodigo(codigo);
		banco.setTitular(titular);
		banco.setCpfOrCnpjTitular(cpf_or_cnpj_titular);
	}

	public void atualizar(Long id, BancoRepository bancoRepository) {
		Banco banco = bancoRepository.getOne(id);
		setarPropriedades(banco);
	}

}
