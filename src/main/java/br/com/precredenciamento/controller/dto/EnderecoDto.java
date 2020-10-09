package br.com.precredenciamento.controller.dto;

import br.com.precredenciamento.model.Endereco;

public class EnderecoDto {

	public Long id;
	public String cep;
	public String endereco;
	public String complemento;
	public String bairro;
	public String uf;
	public String cidade;
	public String habitacao;

	public EnderecoDto(Endereco endereco) {
		if (endereco != null) {
			this.id = endereco.getId();
			this.cep = endereco.getCep();
			this.endereco = endereco.getEndereco();
			this.complemento = endereco.getComplemento();
			this.bairro = endereco.getBairro();
			this.uf = endereco.getUf();
			this.cidade = endereco.getCidade();
			this.habitacao = endereco.getHabitacao();
		}
	}

}
