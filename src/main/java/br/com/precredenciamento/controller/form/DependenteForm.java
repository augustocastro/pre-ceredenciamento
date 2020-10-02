package br.com.precredenciamento.controller.form;

import java.time.LocalDate;

import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.precredenciamento.model.Dependente;
import br.com.precredenciamento.validacao.ValidacaoException;

public class DependenteForm extends Form {

	public Long id;
	
	@NotBlank
	public String cpf;

	@NotBlank
	public String sexo;

	@NotBlank
	public String nomeCompleto;

	@NotNull
	@NotBlank
	public String nomeSocial;

	@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	@NotNull
	public LocalDate dataNascimento;
	
	@NotNull
	@NotBlank
	public String parentesco;

	@NotBlank
	public String estadoCivil;

//	@NotBlank
//	public String grauInstrucao;

	@NotBlank
	public String rg;

	@NotBlank
	public String orgaoEmissor;

	@NotBlank
	public String naturalidade;

	@NotBlank
	public String nacionalidade;

	@NotNull
	public double renda;
	
	public Long titular;
	
	public void setarPropriedades(Dependente dependente, Validator validator) throws ValidacaoException {
		validar(validator);
		dependente.setId(id);
		dependente.setCpf(cpf);
		dependente.setSexo(sexo);
		dependente.setNomeCompleto(nomeCompleto);
		dependente.setNomeSocial(nomeSocial);
		dependente.setDataNascimento(dataNascimento);
		dependente.setParentesco(parentesco);
		dependente.setEstadoCivil(estadoCivil);
//		dependente.setGrauInstrucao(grauInstrucao);
		dependente.setRg(rg);
		dependente.setOrgaoEmissor(orgaoEmissor);
		dependente.setNaturalidade(naturalidade);
		dependente.setNacionalidade(nacionalidade);
		dependente.setRenda(renda);
	}
}
