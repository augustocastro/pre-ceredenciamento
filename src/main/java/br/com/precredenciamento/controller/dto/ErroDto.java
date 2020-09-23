package br.com.precredenciamento.controller.dto;

public class ErroDto {
	
	private String mensagem;
	private String tipo;
	private boolean intercecptar;
	private String redirecionarPara;

	public ErroDto() {
		this("Erro Desconhecido!");
	}
	
	public ErroDto(String mensagem) {
		this(mensagem, true);
	}
	
	public ErroDto(String mensagem, boolean intercecptar) {
		this.mensagem = mensagem;
		this.intercecptar = intercecptar;
		this.tipo = "erro";
	}

	public ErroDto(String mensagem, boolean intercecptar, String redirecionarPara) {
		this.mensagem = mensagem;
		this.intercecptar = intercecptar;
		this.redirecionarPara = redirecionarPara;
		this.tipo = "erro";
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public String getTipo() {
		return tipo;
	}
	
	public boolean isIntercecptar() {
		return intercecptar;
	}
	
	public String getRedirecionarPara() {
		return redirecionarPara;
	}
}
