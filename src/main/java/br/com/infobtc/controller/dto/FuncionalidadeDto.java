package br.com.infobtc.controller.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.infobtc.model.Funcionalidade;

public class FuncionalidadeDto {

	private String funcionalidade;
	private Set<String> permissoes;

	public FuncionalidadeDto() {
	}
	
	public FuncionalidadeDto(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade.getFuncionalidade();
		this.permissoes = funcionalidade.getPermissoes();
	}

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

	
	public Set<FuncionalidadeDto> converterSet(Set<Funcionalidade> funcionalidades) {
		return funcionalidades.stream().map(FuncionalidadeDto::new).collect(Collectors.toSet());
	}

}
