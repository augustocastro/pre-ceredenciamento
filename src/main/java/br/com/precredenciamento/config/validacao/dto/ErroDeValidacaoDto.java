package br.com.precredenciamento.config.validacao.dto;

public class ErroDeValidacaoDto {

	private String campo;
	private String erro;

	public ErroDeValidacaoDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
}
