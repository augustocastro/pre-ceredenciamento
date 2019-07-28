package br.com.infobtc.controller.form;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.Consultor;
import br.com.infobtc.model.EstadoCivil;
import br.com.infobtc.model.InvestidorPessoaFisica;
import br.com.infobtc.model.Sexo;
import br.com.infobtc.repository.ConsultorRepository;
import br.com.infobtc.repository.EnderecoRepository;
import br.com.infobtc.repository.InvestidorPessoaFisicaRepository;

public class InvestidorPessoaFisicaForm {

	@NotNull
	@NotEmpty
	private String cpf;

	@NotNull
	private LocalDate dt_nascimento;

	@NotNull
	private Sexo sexo;

	@NotNull
	@NotEmpty
	private String profissao;

	@NotNull
	@NotEmpty
	private String documento;

	@NotNull
	@NotEmpty
	private String orgao_emissor_uf;

	@NotNull
	@NotEmpty
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

	@NotNull
	private Long id_consultor;

	@NotBlank
	@NotNull
	private String nacionalidade;

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

	public void setIdConsultor(Long idConsultor) {
		this.id_consultor = idConsultor;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public InvestidorPessoaFisica atualizar(Long id, InvestidorPessoaFisicaRepository pessoaFisicaRepository,
			EnderecoRepository enderecoRepository, ConsultorRepository consultorRepository) {
		InvestidorPessoaFisica investidor = pessoaFisicaRepository.getOne(id);

		this.endereco.atualizar(investidor.getEndereco().getId(), enderecoRepository);
		setarPropriedades(investidor, consultorRepository);

		return investidor;
	}

	public void setarPropriedades(InvestidorPessoaFisica investidor, ConsultorRepository consultorRepository) {
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
		
		Optional<Consultor> consultor = consultorRepository.findById(id_consultor);
		
		if (consultor.isPresent()) {
			investidor.setConsultor(consultor.get());
		}
	}

}
