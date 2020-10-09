package br.com.precredenciamento.controller;

import java.io.IOException;
import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.precredenciamento.controller.dto.ErroDto;
import br.com.precredenciamento.controller.dto.UsuarioExternoDto;
import br.com.precredenciamento.controller.dto.UsuarioExternoListagemDto;
import br.com.precredenciamento.controller.form.AtualizarUsuarioExternoFormData;
import br.com.precredenciamento.controller.form.CadastrarUsuarioExternoForm;
import br.com.precredenciamento.controller.service.UsuarioExternoService;
import br.com.precredenciamento.dao.UsuarioSMADao;
import br.com.precredenciamento.model.UsuarioExterno;
import br.com.precredenciamento.validacao.ValidacaoException;
import javassist.NotFoundException;

@RestController
@RequestMapping("/usuario-externo")
public class UsuarioExternoController {

	@Autowired
	private UsuarioExternoService usuarioExternoService;
	
	@Autowired
	private UsuarioSMADao smaDao;

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@Valid @RequestBody CadastrarUsuarioExternoForm form, UriComponentsBuilder uriComponentsBuilder) {
		try {
			UsuarioExterno usuario = usuarioExternoService.salvar(form);
			UsuarioExternoDto dto = new UsuarioExternoDto().converter(usuario);
			URI uri = uriComponentsBuilder.path("/usuario-externo/{id}").buildAndExpand(usuario.getId()).toUri();
			return ResponseEntity.created(uri).body(dto);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new ErroDto("Erro Desconhecido!"));
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable String id, @ModelAttribute AtualizarUsuarioExternoFormData formData, UriComponentsBuilder uriComponentsBuilder) {
		try {
			UsuarioExterno usuario = usuarioExternoService.atualizar(formData);
			UsuarioExternoDto dto = new UsuarioExternoDto().converter(usuario);			
			URI uri = uriComponentsBuilder.path("/usuario-externo/{id}").buildAndExpand(usuario.getId()).toUri();
			return ResponseEntity.created(uri).body(dto);
		} catch (JsonParseException e) {
			return ResponseEntity.status(500).body(new ErroDto("Erro ao converter JSON para objeto Java"));
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(new ErroDto("Erro na desserialização do JSON devido a erros de mapeamento."));
		} catch (IOException e) {
			return ResponseEntity.status(500).body(new ErroDto("Erro nos arquivos enviados."));
		} catch (ValidacaoException e) {
			return ResponseEntity.status(400).body(e.getErros());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(400).body(new ErroDto(e.getMessage(), false));
		}
	}
	
	@GetMapping("/todos")
	public Page<UsuarioExternoListagemDto> buscarTodos(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
		return usuarioExternoService.buscarTodos(paginacao);
	}
	
	@GetMapping("/{cpf}")
	@Transactional
	public ResponseEntity<?> buscarPorCpf(@PathVariable String cpf) {
		try {
			UsuarioExterno usuario = this.usuarioExternoService.buscarPorCpf(cpf);
			UsuarioExternoDto dto = new UsuarioExternoDto().converter(usuario);
			return ResponseEntity.status(200).body(dto);
		} catch (NotFoundException e) {
			return ResponseEntity.status(404).body(new ErroDto(e.getMessage(), false));
		}
	}
	
	@GetMapping
	@Transactional
	public ResponseEntity<?> buscarPorCpfAndId(@NotBlank @RequestParam(value = "id") Long id, @NotBlank @RequestParam(value = "cpf") String cpf) {
		try {
			UsuarioExterno usuario = this.usuarioExternoService.buscarPorCpfAndId(cpf, id);
			UsuarioExternoDto dto = new UsuarioExternoDto().converter(usuario);
			return ResponseEntity.status(200).body(dto);
		} catch (NotFoundException e) {
			return ResponseEntity.status(404).body(new ErroDto(e.getMessage(), false));
		}
	}
	
	@GetMapping("/buscarUsuarioNoSma")
	@Transactional
	public ResponseEntity<?> buscarPorNome() {
		String nome = smaDao.buscarUsuarioPorNome();
		return ResponseEntity.status(200).body(nome);
	}
}