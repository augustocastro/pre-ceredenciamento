package br.com.infobtc.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class PessoaJuridica extends Pessoa {

	private String cnpj;

	public PessoaJuridica() {
		this.setTipo("pessoa_juridica");
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
