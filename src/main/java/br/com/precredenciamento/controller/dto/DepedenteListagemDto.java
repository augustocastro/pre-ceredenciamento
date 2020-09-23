package br.com.precredenciamento.controller.dto;

import br.com.precredenciamento.model.Dependente;

public class DepedenteListagemDto {
	
	public Long id;
	public String cpf;
	public String nomeCompleto;
	public String parentesco;
	public Long titular;
	
	public DepedenteListagemDto converter(Dependente usuario) {
		id = usuario.getId();
		cpf = usuario.getCpf();
		nomeCompleto = usuario.getNomeCompleto();
		parentesco = usuario.getParentesco();
		titular = usuario.getTitular().getId();
		return this;
	}
	
}
