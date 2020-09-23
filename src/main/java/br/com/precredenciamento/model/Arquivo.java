package br.com.precredenciamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "arquivo")
public class Arquivo {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String nome;
	
	
	@Lob
	private byte[] arquivo;

	public Arquivo() {
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getArquivo() {
		return arquivo;
	}
	
	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

}
