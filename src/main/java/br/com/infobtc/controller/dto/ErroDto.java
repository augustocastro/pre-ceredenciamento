package br.com.infobtc.controller.dto;

public class ErroDto {
	private String mensagem;

	public ErroDto(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

}
