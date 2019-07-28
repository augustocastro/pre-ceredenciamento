package br.com.infobtc.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class InvestidorPessoaFisica extends Investidor {
	
	private String cpf;
	private String profissao;
	private String documento;
	private String orgao_emissor_uf;
	private String regime_bens;
	private String nacionalidade;
	private LocalDate dt_nascimento;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;

	public InvestidorPessoaFisica() {
		this.setTipo("pessoa_fisica");
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(LocalDate dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getOrgao_emissor_uf() {
		return orgao_emissor_uf;
	}

	public void setOrgao_emissor_uf(String orgao_emissor_uf) {
		this.orgao_emissor_uf = orgao_emissor_uf;
	}

	public String getRegime_bens() {
		return regime_bens;
	}

	public void setRegime_bens(String regime_bens) {
		this.regime_bens = regime_bens;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

}
