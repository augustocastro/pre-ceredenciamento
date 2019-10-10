package br.com.infobtc.controller.form;

import java.util.Set;

import br.com.infobtc.model.Funcionalidade;

public class FuncionalidadePermissaoForm {

	private String funcionalidade;
	private Set<String> permissoes;

	public String getFuncionalidade() {
		return funcionalidade;
	}

	public Set<String> getPermissoes() {
		return permissoes;
	}

	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public void setPermissoes(Set<String> permissoes) {
		this.permissoes = permissoes;
	}

	public void setarPropriedades(Funcionalidade funcionalidade) {
		funcionalidade.setFuncionalidade(this.funcionalidade);
		funcionalidade.setPermissoes(this.permissoes);
	}

}
