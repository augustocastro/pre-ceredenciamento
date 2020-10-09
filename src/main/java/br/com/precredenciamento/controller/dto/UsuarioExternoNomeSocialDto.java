package br.com.precredenciamento.controller.dto;

import java.time.LocalDate;

import br.com.precredenciamento.model.UsuarioExternoNomeSocial;

public class UsuarioExternoNomeSocialDto {

	public Long id;
	public String nome;
	public String rg;
	public String orgaoEmissorRg;
	public LocalDate dataEmissaoRg;
	public String cpf;
	public String habilitacaoSesc;
	public String nomeRl;
	public String rgRl;
	public String orgaoEmissorRl;
	public LocalDate dataEmissaoRgRl;
	public String cpfRl;
	public String habilitacaoSescRl;
	public String nomeSocial;
	public String tipoRequerimento;


	public UsuarioExternoNomeSocialDto(UsuarioExternoNomeSocial usuarioExternoNomeSocial) {
		if (usuarioExternoNomeSocial != null) {
			id = usuarioExternoNomeSocial.getId();
			nome = usuarioExternoNomeSocial.getNome();
			rg = usuarioExternoNomeSocial.getRg();
			orgaoEmissorRg = usuarioExternoNomeSocial.getOrgaoEmissorRg();
			dataEmissaoRg = usuarioExternoNomeSocial.getDataEmissaoRg();
			cpf = usuarioExternoNomeSocial.getCpf();
			habilitacaoSesc = usuarioExternoNomeSocial.getHabilitacaoSesc();
			nomeRl = usuarioExternoNomeSocial.getNomeRl();
			rgRl = usuarioExternoNomeSocial.getRgRl();
			orgaoEmissorRl = usuarioExternoNomeSocial.getOrgaoEmissorRl();
			dataEmissaoRgRl = usuarioExternoNomeSocial.getDataEmissaoRgRl();
			cpfRl = usuarioExternoNomeSocial.getCpfRl();
			habilitacaoSescRl = usuarioExternoNomeSocial.getHabilitacaoSescRl();
			nomeSocial = usuarioExternoNomeSocial.getNomeSocial();
			tipoRequerimento = usuarioExternoNomeSocial.getTipoRequerimento();
		}
	}

}
