package br.com.precredenciamento.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.precredenciamento.controller.dto.DepedenteDto;
import br.com.precredenciamento.controller.dto.DepedenteListagemDto;
import br.com.precredenciamento.controller.dto.ErroDto;
import br.com.precredenciamento.controller.form.DependenteFormData;
import br.com.precredenciamento.controller.service.DependenteService;
import br.com.precredenciamento.model.Dependente;
import br.com.precredenciamento.validacao.ValidacaoException;

@RestController
@RequestMapping("/dependente")
public class DependenteController {

	@Autowired
	private DependenteService dependenteService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> salvar(@ModelAttribute DependenteFormData formData, UriComponentsBuilder uriComponentsBuilder) {
		try {
			Dependente dependente = dependenteService.salvar(formData);
			DepedenteDto dto = new DepedenteDto().converter(dependente);			
			return ResponseEntity.ok(dto);
		} catch (JsonParseException e) {
			return ResponseEntity.status(500).body(new ErroDto("Erro ao converter JSON para objeto Java"));
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(new ErroDto("Erro na desserialização do JSON devido a erros de mapeamento."));
		} catch (IOException e) {
			return ResponseEntity.status(500).body(new ErroDto("Erro nos arquivos enviados."));
		} catch (ValidacaoException e) {
			return ResponseEntity.status(400).body(e.getErros());
		}
	}
	
	@GetMapping
	@Transactional
	public ResponseEntity<?> buscarPorTitular(@NotBlank @RequestParam(value = "titular") Long id) {
		List<DepedenteListagemDto> dependentes = this.dependenteService.buscarPorTitular(id);
		return ResponseEntity.status(200).body(dependentes);
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		try {
			Dependente dependente = this.dependenteService.buscarPorId(id);
			DepedenteDto dto = new DepedenteDto().converter(dependente);
			return ResponseEntity.status(200).body(dto);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new ErroDto(e.getMessage()));
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removerPorId(@PathVariable Long id) {
		try {
			this.dependenteService.removerPorId(id);
			return ResponseEntity.status(200).build();
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new ErroDto(e.getMessage()));
		}
	}
}