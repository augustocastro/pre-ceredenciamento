package br.com.infobtc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Entity
public class Banco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String instruicaoFinaceira;
	private String agencia;
	private String conta;
	private String tipoConta;
	private Integer codigo;
	private String titular;
	private String cpfOrCnpjTitular;

	public Long getId() {
		return id;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setInstruicaoFinaceira(String instruicaoFinaceira) {
		this.instruicaoFinaceira = instruicaoFinaceira;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public void setCpfOrCnpjTitular(String cpfOrCnpjTitular) {
		this.cpfOrCnpjTitular = cpfOrCnpjTitular;
	}

}
