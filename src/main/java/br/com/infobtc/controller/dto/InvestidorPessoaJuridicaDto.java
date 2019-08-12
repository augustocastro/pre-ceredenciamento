package br.com.infobtc.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.InvestidorPessoaJuridica;

public class InvestidorPessoaJuridicaDto {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String tipo;
	private String cnpj;
	private String inscricao;
	private EnderecoDto endereco;
	private ConsultorDto consultor;
	private List<String> arquivos_url = new ArrayList<String>();
	
	public InvestidorPessoaJuridicaDto() {
	}

	public InvestidorPessoaJuridicaDto(InvestidorPessoaJuridica investidor) {
		this.id = investidor.getId();
		this.nome = investidor.getNome();
		this.email = investidor.getEmail();
		this.telefone = investidor.getTelefone();
		this.tipo = investidor.getTipo();
		this.cnpj = investidor.getCnpj();
		this.inscricao = investidor.getInscricao();
		this.endereco = new EnderecoDto(investidor.getEndereco());
		this.consultor = new ConsultorDto(investidor.getConsultor());
		this.arquivos_url = investidor.getArquivosUrl();
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

	public String getCnpj() {
		return cnpj;
	}

	public String getInscricao() {
		return inscricao;
	}

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public ConsultorDto getConsultor() {
		return consultor;
	}

	public List<String> getArquivos_url() {
		return arquivos_url;
	}

	public void setArquivos_url(List<String> arquivos_url) {
		this.arquivos_url = arquivos_url;
	}

	public List<InvestidorPessoaJuridicaDto> converter(List<InvestidorPessoaJuridica> investidores) {
		return investidores.stream().map(InvestidorPessoaJuridicaDto::new).collect(Collectors.toList());
	}

}
