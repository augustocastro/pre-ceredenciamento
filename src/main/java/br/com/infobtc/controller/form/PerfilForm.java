package br.com.infobtc.controller.form;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.Perfil;

public class PerfilForm {

	@NotNull
	@NotBlank
	private String nome;
	private double porcentagem;
	private Set<FuncionalidadePermissaoForm> funcionalidades;

	public String getNome() {
		return nome;
	}

	public double getPorcentagem() {
		return porcentagem;
	}
	
	public Set<FuncionalidadePermissaoForm> getFuncionalidades() {
		return funcionalidades;
	}
	
	public void setFuncionalidades(Set<FuncionalidadePermissaoForm> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public void setarPropriedades(Perfil perfil) {
		System.out.println(perfil.getPorcentagem());
		perfil.setNome(nome);
		perfil.setPorcentagem(porcentagem != 0 ? porcentagem : 0.01);
	}

}
