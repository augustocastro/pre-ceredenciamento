package br.com.infobtc.controller.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.infobtc.model.Perfil;

public class PerfilDto {

	private Long id;
	private String nome;
	private double porcentagem;
	private Set<FuncionalidadeDto> funcionalidades;
	
	public PerfilDto() {
	}

	public PerfilDto(Perfil perfil) {
		this.id = perfil.getId();
		this.nome = perfil.getNome();
		this.porcentagem = perfil.getPorcentagem();
		this.funcionalidades = new FuncionalidadeDto().converterSet(perfil.getFuncionalidades());
	}

	public PerfilDto(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getPorcentagem() {
		return porcentagem;
	}

	public PerfilDto converter(Perfil perfil) {
		return new PerfilDto(perfil);
	}
	
	public void setFuncionalidades(Set<FuncionalidadeDto> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}
	
	public Set<FuncionalidadeDto> getFuncionalidades() {
		return funcionalidades;
	}

	public Set<PerfilDto> converterPerfis(Set<Perfil> perfis) {
		return perfis.stream().map(PerfilDto::new).collect(Collectors.toSet());
	}

	public List<PerfilDto> converterPerfisLista(List<Perfil> perfis) {
		return perfis.stream().map(PerfilDto::new).collect(Collectors.toList());
	}

}
