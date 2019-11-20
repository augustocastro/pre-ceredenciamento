package br.com.infobtc.controller.vo;

import java.util.List;
import java.util.stream.Collectors;

import br.com.infobtc.model.Perfil;

public class UsuarioPerfilPorcentagemVo {
	
	private String perfil;
	private long qt_ususarios;
	private double percentual_usuarios;
	private List<String> funcionalidades;

	public UsuarioPerfilPorcentagemVo(Perfil perfil, long qt_ususarios_perfil, long percentual_usuarios) {
		this.qt_ususarios = qt_ususarios_perfil;
		this.percentual_usuarios = percentual_usuarios;
		this.perfil = perfil != null ? perfil.getNome() : null;
		
		this.funcionalidades = perfil
				 != null ? perfil
				.getFuncionalidades()
				.stream()
				.map(funcionalidade -> funcionalidade.getFuncionalidade())
				.collect(Collectors.toList()) : null;
	}


	public String getPerfil() {
		return perfil;
	}
	
	public long getQt_ususarios() {
		return qt_ususarios;
	}
	
	public double getPercentual_usuarios() {
		return percentual_usuarios;
	}
	
	public List<String> getFuncionalidades() {
		return funcionalidades;
	}

}
