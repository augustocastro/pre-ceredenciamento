package br.com.infobtc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Investidor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String tipo;
	private String facebook;
	private String instragam;

	@ElementCollection
	private List<String> arquivosUrl = new ArrayList<String>();

	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	public Investidor() {
	}

	public Investidor(String nome, String email, String telefone, Endereco endereco, String facebook,
			String instragam) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
		this.facebook = facebook;
		this.instragam = instragam;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstragam() {
		return instragam;
	}

	public void setInstragam(String instragam) {
		this.instragam = instragam;
	}

	public List<String> getArquivosUrl() {
		return arquivosUrl;
	}

	public void setArquivosUrl(List<String> arquivosUrl) {
		this.arquivosUrl = arquivosUrl;
	}

}
