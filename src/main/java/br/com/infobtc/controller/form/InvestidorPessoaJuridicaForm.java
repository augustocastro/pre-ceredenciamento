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
	
	public InvestidorPessoaJuridica atualizar(Long id, InvestidorPessoaJuridicaRepository pessoaJuridicaRepository, EnderecoRepository enderecoRepository) {
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
	}

}
