package br.com.precredenciamento.controller.form;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.precredenciamento.model.Endereco;
import br.com.precredenciamento.repository.EnderecoRepository;
import br.com.precredenciamento.validacao.ValidacaoException;

@Valid
public class EnderecoForm extends Form {

	public Long id;
	
	@NotNull
	@NotEmpty
	public String cep;

	@NotNull
	@NotEmpty
	public String endereco;

	public String complemento;

	@NotNull
	@NotEmpty
	public String bairro;

	@NotNull
	@NotEmpty
	public String uf;

	@NotNull
	@NotEmpty
	public String cidade;

	@NotNull
	@NotEmpty
	public String habitacao;

	public Endereco atualizar(Long id, EnderecoRepository enderecoRepository, Validator validator) throws ValidacaoException {
		Endereco endereco = enderecoRepository.getOne(id);
		setarPropriedades(endereco, validator);
		return endereco;
	}
	
	public Endereco atualizar(Long id, EnderecoRepository enderecoRepository) throws ValidacaoException {
		Endereco endereco = enderecoRepository.getOne(id);
		setarPropriedades(endereco);
		return endereco;
	}

	public void setarPropriedades(Endereco endereco, Validator validator) throws ValidacaoException {
		validar(validator);
		setarPropriedades(endereco);
	}
	
	public void setarPropriedades(Endereco endereco) throws ValidacaoException {
		endereco.setId(id);
		endereco.setCep(cep);
		endereco.setEndereco(this.endereco);
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setUf(uf);
		endereco.setCidade(cidade);
		endereco.setHabitacao(habitacao);
	}

}
