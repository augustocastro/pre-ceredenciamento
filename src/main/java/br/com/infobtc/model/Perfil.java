package br.com.infobtc.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	private String nome;
	private double porcentagem;
	
	@Enumerated(EnumType.STRING) 
	private TipoPerfil tipoPerfil;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Funcionalidade> funcionalidades;
	
	public Perfil() {
	}

	public Perfil(String nome, double porcentagem) {
		this.nome = nome;
		this.porcentagem = porcentagem;
	}
	
	public Perfil(String nome) {
		this.nome = nome;
		this.porcentagem = 0.01;
	}
	

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPorcentagem(double porcentagem) {
		this.porcentagem = porcentagem;
	}

	public double getPorcentagem() {
		return porcentagem;
	}
	
	public TipoPerfil getTipoPerfil() {
		return tipoPerfil;
	}
	
	public void setTipoPerfil(TipoPerfil tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
	}

	@Override
	public String getAuthority() {
		return nome;
	}

	public void setFuncionalidades(Set<Funcionalidade> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}
	
	public Set<Funcionalidade> getFuncionalidades() {
		return funcionalidades;
	}

}
