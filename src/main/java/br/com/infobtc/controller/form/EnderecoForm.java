package br.com.infobtc.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.infobtc.model.Endereco;
import br.com.infobtc.repository.EnderecoRepository;

public class EnderecoForm {

	@NotNull
	@NotEmpty
	private String endereco;
	
	@NotNull
	@NotEmpty
	private String cidade;
	@NotNull
	@NotEmpty
	private String estado;
	
	@NotNull
	@NotEmpty
	private String pais;
	
	@NotNull
	@NotEmpty
	private String cep;

	public String getEndereco() {
		return endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public String getPais() {
		return pais;
	}

	public String getCep() {
		return cep;
	}
	
	public Endereco atualizar(Long id, EnderecoRepository enderecoRepository) {
		Endereco endereco = enderecoRepository.getOne(id);
		setarPropriedades(endereco);
		
		return endereco;
	}
	
	public void setarPropriedades(Endereco endereco) {
		endereco.setCep(cep);
		endereco.setCidade(cidade);
		endereco.setEndereco(this.endereco);
		endereco.setPais(pais);
		endereco.setEstado(estado);
	}

}
