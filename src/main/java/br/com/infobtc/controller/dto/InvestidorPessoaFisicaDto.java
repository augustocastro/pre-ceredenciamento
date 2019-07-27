package br.com.infobtc.controller.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.EstadoCivil;
import br.com.infobtc.model.InvestidorPessoaFisica;
import br.com.infobtc.model.Sexo;

public class InvestidorPessoaFisicaDto {
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String tipo;
	private String cpf;
	private String profissao;
	private String documento;
	private String orgao_emissor_uf;
	private String regime_bens;
	private String nacionalidade;
	private String dt_nascimento;
	private Sexo sexo;
	private EstadoCivil estado_civil;
	private ConsultorDto consultor;
	private EnderecoDto endereco;

	public InvestidorPessoaFisicaDto() {
	}
	
	public InvestidorPessoaFisicaDto(InvestidorPessoaFisica pessoaFisica) {
		this.id = pessoaFisica.getId();
		this.nome = pessoaFisica.getNome();
		this.email = pessoaFisica.getEmail();
		this.telefone = pessoaFisica.getTelefone();
		this.tipo = pessoaFisica.getTipo();
		this.cpf = pessoaFisica.getCpf();
		this.profissao = pessoaFisica.getProfissao();
		this.documento = pessoaFisica.getDocumento();
		this.orgao_emissor_uf = pessoaFisica.getOrgao_emissor_uf();
		this.regime_bens = pessoaFisica.getRegime_bens();
		this.nacionalidade = pessoaFisica.getNacionalidade();
		this.dt_nascimento = pessoaFisica.getDt_nascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.sexo = pessoaFisica.getSexo();
		this.estado_civil = pessoaFisica.getEstadoCivil();
		this.consultor = new ConsultorDto(pessoaFisica.getConsultor());
		this.endereco = new EnderecoDto(pessoaFisica.getEndereco());
	}

	public Long getId() {
		return id;
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

	public String getTipo() {
		return tipo;
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

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public String getProfissao() {
		return profissao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getOrgao_emissor_uf() {
		return orgao_emissor_uf;
	}

	public String getRegime_bens() {
		return regime_bens;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public String getDt_nascimento() {
		return dt_nascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public EstadoCivil getEstado_civil() {
		return estado_civil;
	}

	public ConsultorDto getConsultor() {
		return consultor;
	}

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public void setDt_nascimento(String dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setEstado_civil(EstadoCivil estado_civil) {
		this.estado_civil = estado_civil;
	}

	public void setConsultor(ConsultorDto consultor) {
		this.consultor = consultor;
	}

	public void setEndereco(EnderecoDto endereco) {
		this.endereco = endereco;
	}
	
	public List<InvestidorPessoaFisicaDto> converter(List<InvestidorPessoaFisica> investidores) {
		return investidores.stream().map(InvestidorPessoaFisicaDto::new).collect(Collectors.toList());
	}
}
