package br.com.precredenciamento.controller.form;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.com.precredenciamento.config.validacao.dto.ErroDeValidacaoDto;
import br.com.precredenciamento.validacao.ValidacaoException;

public abstract class Form {
	
	public void validar(Validator validator) throws ValidacaoException {
		Set<ConstraintViolation<Form>> validate = validator.validate(this);
		ArrayList<ErroDeValidacaoDto> erros = new ArrayList<ErroDeValidacaoDto>(); 
	    if (validate.size() > 0) {
	    	validate.stream().forEach(erro -> {
	    		String message = erro.getMessage();
	    		String path = erro.getPropertyPath().toString();
	    		erros.add(new ErroDeValidacaoDto(path, message));
	    	});
	    	
	    	throw new ValidacaoException(erros);
	    }
	}
}
