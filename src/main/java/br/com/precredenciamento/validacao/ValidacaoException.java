package br.com.precredenciamento.validacao;

import java.util.ArrayList;

import br.com.precredenciamento.config.validacao.dto.ErroDeValidacaoDto;

public class ValidacaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<ErroDeValidacaoDto> erros = new ArrayList<ErroDeValidacaoDto>();
	
	public ValidacaoException(ArrayList<ErroDeValidacaoDto> erros) {
		this.erros = erros;
	}
	
	public ArrayList<ErroDeValidacaoDto> getErros() {
		return erros;
	}
	
}
