package br.com.infobtc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DadosHash {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String hash;
	private String nome;
	private String email;
	private Long usuarioConsultorId;
	
	public Long getId() {
		return id;
	}

	public String getHash() {
		return hash;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getUsuarioConsultorId() {
		return usuarioConsultorId;
	}
	
	public void setUsuario_consultor_id(Long usuarioConsultorId) {
		this.usuarioConsultorId = usuarioConsultorId;
	}

}
