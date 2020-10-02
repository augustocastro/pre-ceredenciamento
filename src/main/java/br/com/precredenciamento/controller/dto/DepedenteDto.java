package br.com.precredenciamento.controller.dto;

import java.time.LocalDate;

import br.com.precredenciamento.model.Dependente;

public class DepedenteDto {
	
	public Long id;
	public String cpf;
	public String sexo;
	public String nomeCompleto;
	public String nomeSocial;
	public LocalDate dataNascimento;
	public String parentesco;
	public String estadoCivil;
//	public String grauInstrucao;
	public String rg;
	public String orgaoEmissor;
	public String naturalidade;
	public String nacionalidade;
	public double renda;
	public Long titular;
	public ArquivoDto fotoPerfil;
	
	public DepedenteDto converter(Dependente usuario) {
		id = usuario.getId();
		cpf = usuario.getCpf();
		sexo = usuario.getSexo();
		nomeCompleto = usuario.getNomeCompleto();
		nomeSocial = usuario.getNomeSocial();
		dataNascimento = usuario.getDataNascimento();
		parentesco = usuario.getParentesco();
		estadoCivil = usuario.getEstadoCivil();
//		grauInstrucao = usuario.getGrauInstrucao();
		rg = usuario.getRg();
		orgaoEmissor = usuario.getOrgaoEmissor();
		naturalidade = usuario.getNaturalidade();
		nacionalidade = usuario.getNacionalidade();
		renda = usuario.getRenda();
		titular = usuario.getTitular().getId();
		fotoPerfil = new ArquivoDto(usuario.getFotoPerfil());
		return this;
	}
	
}
