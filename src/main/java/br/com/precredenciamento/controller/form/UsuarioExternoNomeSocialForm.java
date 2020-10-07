package br.com.precredenciamento.controller.form;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.precredenciamento.model.UsuarioExternoNomeSocial;
import br.com.precredenciamento.validacao.ValidacaoException;

public class UsuarioExternoNomeSocialForm extends Form {

	private String nome;
	private String rg;
	private String orgaoEmissorRg;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dataEmissaoRg;
	private String cpf;
	private String habilitacaoSesc;
	private String nomeRl;
	private String rgRl;
	private String orgaoEmissorRl;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dataEmissaoRgRl;
	private String cpfRl;
	private String habilitacaoSescRl;
	private String nomeSocial;
	private String tipoRequerimento;


	public String getNome() {
		return nome;
	}

	public String getRg() {
		return rg;
	}

	public String getOrgaoEmissorRg() {
		return orgaoEmissorRg;
	}

	public LocalDate getDataEmissaoRg() {
		return dataEmissaoRg;
	}

	public String getCpf() {
		return cpf;
	}

	public String getHabilitacaoSesc() {
		return habilitacaoSesc;
	}

	public String getNomeRl() {
		return nomeRl;
	}

	public String getRgRl() {
		return rgRl;
	}

	public String getOrgaoEmissorRl() {
		return orgaoEmissorRl;
	}

	public LocalDate getDataEmissaoRgRl() {
		return dataEmissaoRgRl;
	}

	public String getCpfRl() {
		return cpfRl;
	}

	public String getHabilitacaoSescRl() {
		return habilitacaoSescRl;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public String getTipoRequerimento() {
		return tipoRequerimento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public void setOrgaoEmissorRg(String orgaoEmissorRg) {
		this.orgaoEmissorRg = orgaoEmissorRg;
	}

	public void setDataEmissaoRg(LocalDate dataEmissaoRg) {
		this.dataEmissaoRg = dataEmissaoRg;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setHabilitacaoSesc(String habilitacaoSesc) {
		this.habilitacaoSesc = habilitacaoSesc;
	}

	public void setNomeRl(String nomeRl) {
		this.nomeRl = nomeRl;
	}

	public void setRgRl(String rgRl) {
		this.rgRl = rgRl;
	}

	public void setOrgaoEmissorRl(String orgaoEmissorRl) {
		this.orgaoEmissorRl = orgaoEmissorRl;
	}

	public void setDataEmissaoRgRl(LocalDate dataEmissaoRgRl) {
		this.dataEmissaoRgRl = dataEmissaoRgRl;
	}

	public void setCpfRl(String cpfRl) {
		this.cpfRl = cpfRl;
	}

	public void setHabilitacaoSescRl(String habilitacaoSescRl) {
		this.habilitacaoSescRl = habilitacaoSescRl;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public void setTipoRequerimento(String tipoRequerimento) {
		this.tipoRequerimento = tipoRequerimento;
	}
	
	public void setarPropriedades(UsuarioExternoNomeSocial usuarioExternoNomeSocial) throws ValidacaoException {
		usuarioExternoNomeSocial.setNome(nome);
		usuarioExternoNomeSocial.setRg(rg);
		usuarioExternoNomeSocial.setOrgaoEmissorRg(orgaoEmissorRg);
		usuarioExternoNomeSocial.setDataEmissaoRg(dataEmissaoRg);
		usuarioExternoNomeSocial.setCpf(cpf);
		usuarioExternoNomeSocial.setHabilitacaoSesc(habilitacaoSesc);
		usuarioExternoNomeSocial.setNomeRl(nomeRl);
		usuarioExternoNomeSocial.setRgRl(rgRl);
		usuarioExternoNomeSocial.setOrgaoEmissorRl(orgaoEmissorRl);
		usuarioExternoNomeSocial.setDataEmissaoRgRl(dataEmissaoRgRl);
		usuarioExternoNomeSocial.setCpfRl(cpfRl);
		usuarioExternoNomeSocial.setHabilitacaoSescRl(habilitacaoSescRl);
		usuarioExternoNomeSocial.setNomeSocial(nomeSocial);
		usuarioExternoNomeSocial.setTipoRequerimento(tipoRequerimento);
	}

}
