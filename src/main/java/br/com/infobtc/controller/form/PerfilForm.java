package br.com.infobtc.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.Perfil;

public class PerfilForm {

	@NotNull
	@NotBlank
	private String nome;
	private double porcentagem;

	public String getNome() {
		return nome;
	}

	public double getPorcentagem() {
		return porcentagem;
	}

	public void setarPropriedades(Perfil perfil) {
		System.out.println(perfil.getPorcentagem());
		perfil.setNome(nome);
		perfil.setPorcentagem(porcentagem != 0 ? porcentagem : 0.01);
	}

}
