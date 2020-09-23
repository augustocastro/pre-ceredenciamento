package br.com.precredenciamento.controller.dto;

import br.com.precredenciamento.model.Arquivo;

public class ArquivoDto {

	public  String id;
	public String nome;
	public byte[] arquivo;
	
	public ArquivoDto (Arquivo arquivo) {
		if (arquivo != null ) {			
			id = arquivo.getId();
			nome = arquivo.getNome();
			this.arquivo = arquivo.getArquivo();
		}
	}
}
