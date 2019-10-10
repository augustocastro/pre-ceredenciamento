package br.com.infobtc.model;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Funcionalidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String funcionalidade;

	@ElementCollection
	private Set<String> permissoes;

	public Long getId() {
		return id;
	}

	public String getFuncionalidade() {
		return funcionalidade;
	}

	public Set<String> getPermissoes() {
		return permissoes;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public void setPermissoes(Set<String> permissoes) {
		this.permissoes = permissoes;
	}

}
