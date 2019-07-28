package br.com.infobtc.controller.form;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.Consultor;
import br.com.infobtc.model.InvestidorPessoaJuridica;
import br.com.infobtc.repository.ConsultorRepository;
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

	@NotNull
	private Long id_consultor;
	
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

	public Long getId_consultor() {
		return id_consultor;
	}

	public void setId_consultor(Long id_consultor) {
		this.id_consultor = id_consultor;
	}

	public InvestidorPessoaJuridica atualizar(Long id, InvestidorPessoaJuridicaRepository pessoaJuridicaRepository, 
			EnderecoRepository enderecoRepository, ConsultorRepository consultorRepository) {
		InvestidorPessoaJuridica investidor = pessoaJuridicaRepository.getOne(id);
		
		this.endereco.atualizar(investidor.getEndereco().getId(), enderecoRepository);
		setarPropriedades(investidor, consultorRepository);
		
		return investidor;
	}
	
	public void setarPropriedades(InvestidorPessoaJuridica investidor, ConsultorRepository consultorRepository) {
		investidor.setCnpj(cnpj);
		investidor.setEmail(email);
		investidor.setNome(nome);
		investidor.setTelefone(telefone);
		investidor.setInscricao(inscricao);
		
		Optional<Consultor> consultor = consultorRepository.findById(id_consultor);
		
		if (consultor.isPresent()) {
			investidor.setConsultor(consultor.get());
		}
	}

}
