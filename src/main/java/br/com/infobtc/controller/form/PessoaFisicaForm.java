package br.com.infobtc.controller.form;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.PessoaFisica;
import br.com.infobtc.model.Sexo;
import br.com.infobtc.repository.EnderecoRepository;
import br.com.infobtc.repository.PessoaFisicaRepository;

public class PessoaFisicaForm {

	@NotNull
	@NotEmpty
	private String cpf;

	@NotNull
	private LocalDate dt_nascimento;

	@Enumerated(EnumType.STRING)
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

	public PessoaFisica atualizar(Long id, PessoaFisicaRepository pessoaFisicaRepository, EnderecoRepository enderecoRepository) {
		PessoaFisica pessoaFisica = pessoaFisicaRepository.getOne(id);
		
		this.endereco.atualizar(pessoaFisica.getEndereco().getId(), enderecoRepository);
		setarPropriedades(pessoaFisica);
		
		return pessoaFisica;
	}
	
	public void setarPropriedades(PessoaFisica pessoaFisica) {
		pessoaFisica.setCpf(cpf);
		pessoaFisica.setDocumento(documento);
		pessoaFisica.setDt_nascimento(dt_nascimento);
		pessoaFisica.setEmail(email);
		pessoaFisica.setNome(nome);
		pessoaFisica.setProfissao(profissao);
		pessoaFisica.setSexo(sexo);
		pessoaFisica.setTelefone(telefone);
		pessoaFisica.setRegime_bens(regime_bens);
		pessoaFisica.setOrgao_emissor_uf(orgao_emissor_uf);
	}

}
