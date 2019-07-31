package br.com.infobtc.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.Consultor;

public class ConsultorDetalhadoDto {

	private Long id;
	private String nome;
	private String telefone;
	private UsuarioDto usuario;
	private EnderecoDto endereco;

	public ConsultorDetalhadoDto() {
	}

	public ConsultorDetalhadoDto(Consultor consultor) {
		this.id = consultor.getId();
		this.nome = consultor.getNome();
		this.telefone = consultor.getTelefone();
		this.usuario = new UsuarioDto(consultor.getUsuario());
		this.endereco = new EnderecoDto(consultor.getEndereco());
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public List<ConsultorDetalhadoDto> converter(List<Consultor> consultores) {
		return consultores.stream().map(ConsultorDetalhadoDto::new).collect(Collectors.toList());
	}

}
