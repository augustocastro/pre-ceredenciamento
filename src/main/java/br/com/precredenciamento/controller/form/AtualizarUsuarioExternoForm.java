package br.com.precredenciamento.controller.form;

import java.time.LocalDate;

import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.precredenciamento.model.UsuarioExterno;
import br.com.precredenciamento.validacao.ValidacaoException;

public class AtualizarUsuarioExternoForm extends Form {

	@NotNull
	public Long id;
	
	@NotBlank
	public String cpf;

	@NotBlank
	public String sexo;

	@NotBlank
	public String nomeCompleto;

	public String nomeSocial;

	@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	@NotNull
	public LocalDate dataNascimento;

	@NotBlank
	public String estadoCivil;

	@NotBlank
	public String grauInstrucao;

	@NotBlank
	public String rg;

	@NotBlank
	public String orgaoEmissor;

	@NotBlank
	public String naturalidade;

	@NotBlank
	public String nacionalidade;

	@NotBlank
	public String profissao;

	public String numeroCarteiraTrabalho;

	public String nomePai;

	public String nomeMae;

	@NotBlank
	public String telefoneCelular;

	@NotBlank
	public String telefoneResidencial;

	@NotBlank
	public String telefoneComercial;
	
	public String email;

	@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	@NotNull
	public LocalDate dataAdmisao;

	@NotBlank
	public String cnpjEmpregador;

	@NotBlank
	public String nomeEmpresa;

	@NotNull
	public double renda;
	
	@NotNull
	public boolean carteiraRegistrada;
	
	@NotBlank
	public String tipoDocEmpregador;
	
	@NotNull
	public EnderecoForm endereco;
	
	public UsuarioExternoNomeSocialForm cadastroNomeSocial;
	
	public void setarPropriedades(UsuarioExterno usuario, Validator validator) throws ValidacaoException {
		validar(validator);
		usuario.setId(id);
		usuario.setCpf(cpf);
		usuario.setSexo(sexo);
		usuario.setNomeCompleto(nomeCompleto);
		usuario.setNomeSocial(nomeSocial);
		usuario.setDataNascimento(dataNascimento);
		usuario.setEstadoCivil(estadoCivil);
		usuario.setGrauInstrucao(grauInstrucao);
		usuario.setRg(rg);
		usuario.setOrgaoEmissor(orgaoEmissor);
		usuario.setNaturalidade(naturalidade);
		usuario.setNacionalidade(nacionalidade);
		usuario.setProfissao(profissao);
		usuario.setNumeroCarteiraTrabalho(numeroCarteiraTrabalho);
		usuario.setNomePai(nomePai);
		usuario.setNomeMae(nomeMae);
		usuario.setTelefoneCelular(telefoneCelular);
		usuario.setTelefoneResidencial(telefoneResidencial);
		usuario.setTelefoneComercial(telefoneComercial);
		usuario.setEmail(email);
		usuario.setDataAdmisao(dataAdmisao);
		usuario.setDataAdmisao(dataAdmisao);
		usuario.setCnpjEmpregador(cnpjEmpregador);
		usuario.setNomeEmpresa(nomeEmpresa);
		usuario.setRenda(renda);
		usuario.setCarteiraRegistrada(carteiraRegistrada);
		usuario.setTipoDocEmpregador(tipoDocEmpregador);
	}
}
