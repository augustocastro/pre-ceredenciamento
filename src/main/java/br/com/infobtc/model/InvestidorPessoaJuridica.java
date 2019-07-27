package br.com.infobtc.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class InvestidorPessoaJuridica extends Investidor {

	private String cnpj;

	public InvestidorPessoaJuridica() {
		this.setTipo("pessoa_juridica");
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
