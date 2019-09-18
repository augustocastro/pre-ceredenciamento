package br.com.infobtc.controller.form;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.InvestidorPessoaJuridica;
import br.com.infobtc.repository.EnderecoRepository;
import br.com.infobtc.repository.InvestidorPessoaJuridicaRepository;

public class InvestidorPessoaJuridicaForm {

	@NotNull
	@NotEmpty
	private String cnpj;

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@NotEmpty
	private String email;

	@NotNull
	@NotEmpty
	private String telefone;

	@NotNull
	@NotEmpty
	private String inscricao;

	@Valid
	@NotNull
	private EnderecoForm endereco;

	@Valid
	@NotNull
	private String facebook;

	@Valid
	@NotNull
	private String instragam;

	@Valid
	@NotNull
	private boolean declaracao_licitude;

	@Valid
	@NotNull
	private boolean declaracao_politicamente_exposta;

	public String getCnpj() {
		return cnpj;
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

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public EnderecoForm getEndereco() {
		return endereco;
	}

	public String getFacebook() {
		return facebook;
	}

	public String getInstragam() {
		return instragam;
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

	public InvestidorPessoaJuridica atualizar(Long id, InvestidorPessoaJuridicaRepository pessoaJuridicaRepository,
			EnderecoRepository enderecoRepository) {
		InvestidorPessoaJuridica investidor = pessoaJuridicaRepository.getOne(id);

		this.endereco.atualizar(investidor.getEndereco().getId(), enderecoRepository);
		setarPropriedades(investidor);

		return investidor;
	}

	public void setarPropriedades(InvestidorPessoaJuridica investidor) {
		investidor.setCnpj(cnpj);
		investidor.setEmail(email);
		investidor.setNome(nome);
		investidor.setTelefone(telefone);
		investidor.setInscricao(inscricao);
		investidor.setFacebook(facebook);
		investidor.setInstragam(instragam);
		investidor.setDeclaracaoLicitude(declaracao_licitude);
		investidor.setDeclaracaoPoliticamenteExposta(declaracao_politicamente_exposta);
	}

}
