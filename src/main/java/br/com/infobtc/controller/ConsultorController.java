package br.com.infobtc.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.infobtc.controller.form.ConsultorForm;
import br.com.infobtc.controller.form.EnderecoForm;
import br.com.infobtc.controller.form.UsuarioForm;
import br.com.infobtc.model.Consultor;
import br.com.infobtc.model.Endereco;
import br.com.infobtc.model.Usuario;
import br.com.infobtc.repository.ConsultorRepository;
import br.com.infobtc.repository.EnderecoRepository;
import br.com.infobtc.repository.PerfilRepository;
import br.com.infobtc.repository.UsuarioRepository;

@RestController
@RequestMapping("/consultor")
public class ConsultorController {

	@Autowired
	private ConsultorRepository consultorRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PerfilRepository perfilRepository;  
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@PostMapping
	@Transactional
	public ResponseEntity<Consultor> cadastrar(@RequestBody @Valid ConsultorForm consltorForm, UriComponentsBuilder uriComponentsBuilder) {
		Consultor consultor = new Consultor();
		Endereco endereco = new Endereco();
		Usuario usuario = new Usuario();
		
		EnderecoForm enderecoForm = consltorForm.getEndereco();
		UsuarioForm usuarioForm = consltorForm.getUsuario();
		
		usuarioForm.setarPropriedades(usuario, perfilRepository);
		enderecoForm.setarPropriedades(endereco);
		consltorForm.setarPropriedades(consultor);
		
		consultor.setEndereco(endereco);	
		usuario.setConsultor(consultor);
		
		enderecoRepository.save(endereco);
		consultorRepository.save(consultor);
		usuarioRepository.save(usuario);
		
		URI uri = uriComponentsBuilder.path("/consultor/{id}").buildAndExpand(consultor.getId()).toUri();
		return ResponseEntity.created(uri).body(consultor);
	}
	
	@GetMapping("/todos")
	public ResponseEntity<List<Consultor>> buscarTodos() {
		List<Consultor> consultores = consultorRepository.findAll();
		return ResponseEntity.ok(consultores);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Consultor> buscarPorId(@PathVariable Long id) {
		Optional<Consultor> consultor = consultorRepository.findById(id);

		if (consultor.isPresent()) {
			return ResponseEntity.ok(consultor.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if (consultorRepository.findById(id).isPresent()) {
			consultorRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
