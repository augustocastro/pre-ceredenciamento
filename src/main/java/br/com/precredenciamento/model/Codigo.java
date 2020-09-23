package br.com.precredenciamento.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "codigo")
public class Codigo {

	@Id
	private String codigo;
	private String cpf;

	public Codigo() {
		// TODO Auto-generated constructor stub
	}
	
	public Codigo(String codigo, String cpf) {
		this.codigo = codigo;
		this.cpf = cpf;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
