package br.com.precredenciamento.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.precredenciamento.model.Codigo;
import br.com.precredenciamento.repository.CodigoRepository;
import javassist.NotFoundException;

@Service
public class CodigoService {

	@Autowired
	private CodigoRepository codigoRepository;
	
	public Codigo buscarPorCodigo(String codigo) {
		return codigoRepository.findByCodigo(codigo);
	}
	
	public Codigo salvar(Codigo codigo) {
		return codigoRepository.save(codigo);
	}
	
	public Codigo validarCodigo(String codigo) throws NotFoundException {
		Codigo codigoRecuperado = this.buscarPorCodigo(codigo);
		if (codigoRecuperado != null) {				
			return codigoRecuperado;
		} else {
			throw new NotFoundException("Código não encontrado!");
		}
	}

	public void exluir(Codigo codigo) {
		Codigo codigoRecuperado = buscarPorCodigo(codigo.getCodigo());
		codigoRepository.deleteById(codigoRecuperado.getCodigo());
	}
}
