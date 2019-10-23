package br.com.infobtc.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.infobtc.config.security.service.TokenService;
import br.com.infobtc.controller.dto.ConsultorDetalhadoDto;
import br.com.infobtc.controller.dto.ErroDto;
import br.com.infobtc.controller.form.ConsultorForm;
import br.com.infobtc.controller.form.EnderecoForm;
import br.com.infobtc.controller.form.TrocaSenhaForm;
import br.com.infobtc.controller.form.UsuarioForm;
import br.com.infobtc.model.Consultor;
import br.com.infobtc.model.Endereco;
import br.com.infobtc.model.Usuario;
import br.com.infobtc.repository.ConsultorRepository;
import br.com.infobtc.repository.EnderecoRepository;
import br.com.infobtc.repository.PerfilRepository;
import br.com.infobtc.repository.UsuarioRepository;
import javassist.NotFoundException;

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
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ConsultorForm consltorForm, UriComponentsBuilder uriComponentsBuilder) {
		Consultor consultor = new Consultor();
		Endereco endereco = new Endereco();
		Usuario usuario = new Usuario();
		
		EnderecoForm enderecoForm = consltorForm.getEndereco();
		UsuarioForm usuarioForm = consltorForm.getUsuario();
		
		try {
			usuarioForm.setarPropriedades(usuario, perfilRepository);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(e.getMessage()));
		}
		
		enderecoForm.setarPropriedades(endereco);
		consltorForm.setarPropriedades(consultor);
		
		consultor.setEndereco(endereco);	
		consultor.setUsuario(usuario);
		
		enderecoRepository.save(endereco);
		usuarioRepository.save(usuario);
		consultorRepository.save(consultor);
		
		URI uri = uriComponentsBuilder.path("/consultor/{id}").buildAndExpand(consultor.getId()).toUri();
		return ResponseEntity.created(uri).body(new ConsultorDetalhadoDto(consultor));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody ConsultorForm form) {
		Optional<Consultor> investidor = consultorRepository.findById(id);

		if (investidor.isPresent()) {
			Consultor consultorAtualizado;
			try {
				consultorAtualizado = form.atualizar(id, consultorRepository, enderecoRepository, usuarioRepository, perfilRepository);
			} catch (NotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(e.getMessage()));
			}
			ConsultorDetalhadoDto consultorDto = new ConsultorDetalhadoDto(consultorAtualizado);
			return ResponseEntity.ok(consultorDto);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/todos")
	public ResponseEntity<List<ConsultorDetalhadoDto>> buscarTodos() {
		List<Consultor> consultores = consultorRepository.findAll();
		return ResponseEntity.ok(new ConsultorDetalhadoDto().converter(consultores));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ConsultorDetalhadoDto> buscarPorId(@PathVariable Long id) {
		Optional<Consultor> consultor = consultorRepository.findById(id);

		if (consultor.isPresent()) {
			return ResponseEntity.ok(new ConsultorDetalhadoDto(consultor.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("usuario/{id}")
	public ResponseEntity<ConsultorDetalhadoDto> buscarPorUsuario(@PathVariable Long id) {
		Optional<Consultor> consultor = consultorRepository.findByUsuarioId(id);

		if (consultor.isPresent()) {
			return ResponseEntity.ok(new ConsultorDetalhadoDto(consultor.get()));
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
	
	@PatchMapping("/usuario/troca-senha")
	@Transactional
	public ResponseEntity<?> trocarSenha(HttpServletRequest request, @RequestBody @Valid TrocaSenhaForm trocaSenhaForm ) {
		Long idUsuario = tokenService.getUsuario(tokenService.recuperarToken(request));
		Usuario usuario = usuarioRepository.getOne(idUsuario);
		
		if (!tokenService.matchesPassord(trocaSenhaForm.getSenha_atual(), usuario.getSenha())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErroDto("A senha informada não bate com a senha atual."));
		} else if (!trocaSenhaForm.getNova_senha().equals(trocaSenhaForm.getConfirmacao_senha())) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErroDto("A nova senha não bate com a confirmação."));
		};
		
		
		usuario.setSenha(tokenService.encondePassord(trocaSenhaForm.getNova_senha()));
		
		System.out.println(idUsuario);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
