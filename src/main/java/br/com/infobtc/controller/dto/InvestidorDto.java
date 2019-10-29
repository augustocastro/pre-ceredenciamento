package br.com.infobtc.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.Investidor;
import br.com.infobtc.model.InvestidorPessoaFisica;
import br.com.infobtc.model.InvestidorPessoaJuridica;

public class InvestidorDto {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String tipo;
	private String cpf_or_cnpj;
	private String status_investidor;
	
	public InvestidorDto() {
	}

	public InvestidorDto(Investidor investidor) {
		this.id = investidor.getId();
		this.nome = investidor.getNome();
		this.email = investidor.getEmail();
		this.telefone = investidor.getTelefone();
		this.tipo = investidor.getTipo();
		this.status_investidor = investidor.getStatusInvestidor().toString();
		
		if (investidor.getTipo().equals("pessoa_fisica")) {
			InvestidorPessoaFisica investidorPessoaFisica = (InvestidorPessoaFisica)investidor;
			this.cpf_or_cnpj = investidorPessoaFisica.getCpf();
		} else {
			InvestidorPessoaJuridica investidorPessoaJuridica = (InvestidorPessoaJuridica)investidor;
			this.cpf_or_cnpj = investidorPessoaJuridica.getCnpj();
		}
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

	public String getCpf_or_cnpj() {
		return cpf_or_cnpj;
	}
	
	public String getStatus_investidor() {
		return status_investidor;
	}
	
	public List<InvestidorDto> converter(List<Investidor> pessoas) {
		return pessoas.stream().map(InvestidorDto::new).collect(Collectors.toList());
	}

}
