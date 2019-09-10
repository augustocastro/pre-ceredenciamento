package br.com.infobtc.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class InvestidorPessoaFisica extends Investidor {
	
	private String cpf;
	private String profissao;
	private String documento;
	private String orgaoEmissorUf;
	private String regimeBens;
	private String nacionalidade;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dtNascimento;
	
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
		return dtNascimento;
	}

	public void setDt_nascimento(LocalDate dt_nascimento) {
		this.dtNascimento = dt_nascimento;
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
		return orgaoEmissorUf;
	}

	public void setOrgao_emissor_uf(String orgao_emissor_uf) {
		this.orgaoEmissorUf = orgao_emissor_uf;
	}

	public String getRegime_bens() {
		return regimeBens;
	}

	public void setRegime_bens(String regime_bens) {
		this.regimeBens = regime_bens;
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
