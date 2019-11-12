package br.com.infobtc.controller.form;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.infobtc.model.Consultor;
import br.com.infobtc.model.EstadoCivil;
import br.com.infobtc.model.InvestidorPessoaFisica;
import br.com.infobtc.model.Sexo;
import br.com.infobtc.model.Status;
import br.com.infobtc.repository.EnderecoRepository;
import br.com.infobtc.repository.InvestidorPessoaFisicaRepository;

public class InvestidorPessoaFisicaForm {

	private String cpf;

	@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	@NotNull
	private LocalDate dt_nascimento;

	@NotNull
	private Sexo sexo;

	@NotNull
	@NotEmpty
	private String profissao;

	private String documento;
	
	private String passaporte;

	private String orgao_emissor_uf;

	private String regime_bens;

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@NotEmpty
	private String email;

	@NotNull
	@NotEmpty
	private String telefone;

	@Valid
	@NotNull
	private EnderecoForm endereco;

	@NotNull
	private EstadoCivil estado_civil;

	@NotBlank
	@NotNull
	private String nacionalidade;

	private String facebook;
	
	private String instagram;
	
	@Valid
	@NotNull
	private boolean declaracao_licitude;
	
	@Valid
	@NotNull
	private boolean declaracao_politicamente_exposta;
	
	public String getCpf() {
		return cpf;
	}

	public LocalDate getDt_nascimento() {
		return dt_nascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public String getProfissao() {
		return profissao;
	}

	public String getDocumento() {
		return documento;
	}
	
	public String getPassaporte() {
		return passaporte;
	}

	public String getOrgao_emissor_uf() {
		return orgao_emissor_uf;
	}

	public String getRegime_bens() {
		return regime_bens;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public EnderecoForm getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoForm endereco) {
		this.endereco = endereco;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDt_nascimento(LocalDate dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public void setOrgao_emissor_uf(String orgao_emissor_uf) {
		this.orgao_emissor_uf = orgao_emissor_uf;
	}

	public void setRegime_bens(String regime_bens) {
		this.regime_bens = regime_bens;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estado_civil = estadoCivil;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	

	public EstadoCivil getEstado_civil() {
		return estado_civil;
	}

	public String getFacebook() {
		return facebook;
	}
	
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagram() {
		return instagram;
	}
	
	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public void setDeclaracao_licitude(boolean declaracao_licitude) {
		this.declaracao_licitude = declaracao_licitude;
	}
	
	public boolean isDeclaracao_licitude() {
		return declaracao_licitude;
	}
	
	public void setDeclaracao_politicamente_exposta(boolean declaracao_politicamente_exposta) {
		this.declaracao_politicamente_exposta = declaracao_politicamente_exposta;
	}
	public boolean isDeclaracao_politicamente_exposta() {
		return declaracao_politicamente_exposta;
	}
	
	public InvestidorPessoaFisica atualizar(Long id, InvestidorPessoaFisicaRepository pessoaFisicaRepository, EnderecoRepository enderecoRepository) {
		InvestidorPessoaFisica investidor = pessoaFisicaRepository.getOne(id);

		this.endereco.atualizar(investidor.getEndereco().getId(), enderecoRepository);
		setarPropriedades(investidor);

		return investidor;
	}

	public void setarPropriedades(InvestidorPessoaFisica investidor, Consultor consultor) {
		setarPropriedades(investidor);
		investidor.setConsultor(consultor);
	}

	public void setarPropriedades(InvestidorPessoaFisica investidor) {
		investidor.setCpf(cpf);
		investidor.setDocumento(documento);
		investidor.setDt_nascimento(dt_nascimento);
		investidor.setEmail(email);
		investidor.setNome(nome);
		investidor.setProfissao(profissao);
		investidor.setSexo(sexo);
		investidor.setTelefone(telefone);
		investidor.setRegime_bens(regime_bens);
		investidor.setOrgao_emissor_uf(orgao_emissor_uf);
		investidor.setNacionalidade(nacionalidade);
		investidor.setEstadoCivil(estado_civil);
		investidor.setFacebook(facebook);
		investidor.setInstagram(instagram);
		investidor.setDeclaracaoLicitude(declaracao_licitude);
		investidor.setDeclaracaoPoliticamenteExposta(declaracao_politicamente_exposta);
		investidor.setPassaporte(passaporte);
		investidor.setStatusInvestidor(Status.EM_ANALISE);
	}
	
}
