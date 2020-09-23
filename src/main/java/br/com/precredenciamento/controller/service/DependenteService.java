package br.com.precredenciamento.controller.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.precredenciamento.controller.dto.DepedenteListagemDto;
import br.com.precredenciamento.controller.form.DependenteForm;
import br.com.precredenciamento.controller.form.DependenteFormData;
import br.com.precredenciamento.model.Arquivo;
import br.com.precredenciamento.model.Dependente;
import br.com.precredenciamento.model.UsuarioExterno;
import br.com.precredenciamento.repository.DependenteRepository;
import br.com.precredenciamento.repository.UsuarioExternoRepository;
import br.com.precredenciamento.validacao.ValidacaoException;

@Service
public class DependenteService {

	@Autowired
	private UsuarioExternoRepository usuarioExternoRepository;
	
	@Autowired
	private DependenteRepository dependenteRepository; 
	
	@Autowired
	private ArquivoService arquivoService;

	@Autowired
	private Validator validator;
	
	public Dependente salvar(DependenteFormData formData) throws JsonParseException, JsonMappingException, IOException, ValidacaoException {
		DependenteForm form = new ObjectMapper().readValue(formData.getDependente(), DependenteForm.class);
		
		Dependente dependente = new Dependente();
		
		if (form.id != null) {
			java.util.Optional<Dependente> opDependente = this.dependenteRepository.findById(form.id);
			dependente = opDependente.isPresent() ? opDependente.get() : new Dependente();
		}
		
		UsuarioExterno titular = usuarioExternoRepository.findById(form.titular).orElseThrow(() -> {
			throw new RuntimeException(String.format("Titular de id \"%d\" não encontrado", form.titular));
		});
		
		dependente.setTitular(titular);
		
		form.setarPropriedades(dependente, validator);

		if (formData.getFotoPerfil() != null) {
			if (dependente.getFotoPerfil() != null) {				
				String idFotoPerfilAntiga = dependente.getFotoPerfil().getId();
				arquivoService.removerPorId(idFotoPerfilAntiga);
			}
			Arquivo fotoPerfil = arquivoService.salvar(formData.getFotoPerfil());
			dependente.setFotoPerfil(fotoPerfil);
		}
		
		dependenteRepository.save(dependente);

		return dependente;
	}
	
	public List<DepedenteListagemDto> buscarPorTitular(Long titular) {
		List<Dependente> dependentes = dependenteRepository.findByTitularId(titular);
		return dependentes.stream().map(d -> new DepedenteListagemDto().converter(d)).collect(Collectors.toList());
	}

	public Dependente buscarPorId(Long id) {
		return dependenteRepository.findById(id).orElseThrow(() -> {
			throw new RuntimeException(String.format("Dependente de id \"%d\" não encontrado", id));
		});
	}
	
	public void removerPorId(Long id) {
		this.buscarPorId(id);
		dependenteRepository.deleteById(id);
	}
}