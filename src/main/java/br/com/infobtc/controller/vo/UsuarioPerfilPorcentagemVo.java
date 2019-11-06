package br.com.infobtc.controller.vo;

import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.Perfil;

public class UsuarioPerfilPorcentagemVo {
	
	private String nome_perfil;
	private long qt_ususarios_perfil;
	private long porcentagem_perfil;
	private List<String> funcionalidades;

	public UsuarioPerfilPorcentagemVo(Perfil perfil, long qt_ususarios_perfil, long porcentagem_perfil) {
		this.qt_ususarios_perfil = qt_ususarios_perfil;
		this.porcentagem_perfil = porcentagem_perfil;
		this.nome_perfil = perfil != null ? perfil.getNome() : null;
		
		this.funcionalidades = perfil
				 != null ? perfil
				.getFuncionalidades()
				.stream()
				.map(funcionalidade -> funcionalidade.getFuncionalidade())
				.collect(Collectors.toList()) : null;
	}

	public String getNome_perfil() {
		return nome_perfil;
	}

	public long getQt_ususarios_perfil() {
		return qt_ususarios_perfil;
	}

	public long getPorcentagem_perfil() {
		return porcentagem_perfil;
	}

	public List<String> getFuncionalidades() {
		return funcionalidades;
	}

}
