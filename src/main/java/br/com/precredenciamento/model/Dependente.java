package br.com.precredenciamento.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dependente")
public class Dependente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpf;
	private String sexo;
	private String nomeCompleto;
	private String nomeSocial;
	private LocalDate dataNascimento;
	private String parentesco;
	private String estadoCivil;
	private String grauInstrucao;
	private String rg;
	private String orgaoEmissor;
	private String naturalidade;
	private String nacionalidade;
	private double renda;

	@ManyToOne
	private UsuarioExterno titular;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Arquivo fotoPerfil;

	public Long getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getParentesco() {
		return parentesco;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public String getGrauInstrucao() {
		return grauInstrucao;
	}

	public String getRg() {
		return rg;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public double getRenda() {
		return renda;
	}

	public UsuarioExterno getTitular() {
		return titular;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public void setGrauInstrucao(String grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public void setRenda(double renda) {
		this.renda = renda;
	}

	public void setTitular(UsuarioExterno titular) {
		this.titular = titular;
	}
	
	public Arquivo getFotoPerfil() {
		return fotoPerfil;
	}
	
	public void setFotoPerfil(Arquivo fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

}
