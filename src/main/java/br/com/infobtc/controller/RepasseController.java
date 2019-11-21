package br.com.infobtc.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.infobtc.config.security.service.TokenService;
import br.com.infobtc.controller.dto.ErroDto;
import br.com.infobtc.controller.dto.RepasseDto;
import br.com.infobtc.controller.form.RepasseForm;
import br.com.infobtc.model.Repasse;
import br.com.infobtc.model.TipoRepasse;
import br.com.infobtc.model.Usuario;
import br.com.infobtc.repository.ParcelaRepository;
import br.com.infobtc.repository.RepasseRepository;
import br.com.infobtc.repository.UsuarioRepository;
import br.com.infobtc.service.S3Service;
import javassist.NotFoundException;

@RestController
@RequestMapping("/repasse")
public class RepasseController {

	@Autowired
	private RepasseRepository repasseRepository;

	@Autowired
	private ParcelaRepository parcelaRepository;

	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TokenService tokenService; 

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(HttpServletRequest request, @Valid @ModelAttribute RepasseForm repasseForm,
			UriComponentsBuilder uriComponentsBuilder) throws MalformedURLException {
		Repasse repasse = new Repasse();
		
		try {
			if (parcelaRepository.buscarRepassePorParcela(repasseForm.getParcela_id()).isPresent()) {
				throw new NotFoundException(String.format("A parcela de id %s já foi repassada.", repasseForm.getParcela_id()));
			}
			
			repasseForm.setarPropriedades(repasse, parcelaRepository);
			setarUsuario(request, repasse);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(e.getMessage()));
		}
		if (repasseForm.getAnexo() != null) {
			URI uploadFile = s3Service.uploadFile(repasseForm.getAnexo());
			repasse.setAnexo(uploadFile.toURL().toString());
		}

		repasseRepository.save(repasse);

		URI uri = uriComponentsBuilder.path("/repasse/{id}").buildAndExpand(repasse.getId()).toUri();
		return ResponseEntity.created(uri).body(new RepasseDto(repasse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RepasseDto> buscarPorId(@PathVariable Long id) {
		Optional<Repasse> repasse = repasseRepository.findById(id);

		if (repasse.isPresent()) {
			return ResponseEntity.ok(new RepasseDto(repasse.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("parcela/{id}")
	public ResponseEntity<RepasseDto> buscarRepassePorParcela(@PathVariable Long id) {
		Optional<Repasse> repasse = parcelaRepository.buscarRepassePorParcela(id);

		if (repasse.isPresent()) {
			return ResponseEntity.ok(new RepasseDto(repasse.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("contrato/{id}")
	public ResponseEntity<List<RepasseDto>> buscarRepassesPorContrato(@PathVariable Long id, TipoRepasse tipoRepasse) {
		List<Repasse> repasses;

		if (id != null && tipoRepasse != null) {
			repasses = repasseRepository.findByParcelaContratoIdAndTipoRepasse(id, tipoRepasse);
		} else if (tipoRepasse != null) {
			repasses = repasseRepository.findByTipoRepasse(tipoRepasse);
		} else {
			repasses = repasseRepository.findByParcelaContratoId(id);
		}
		return ResponseEntity.ok(new RepasseDto().converterPerfis(repasses));
	}
	
	private void setarUsuario(HttpServletRequest request, Repasse repasse) throws NotFoundException {
		String token = tokenService.recuperarToken(request);
		Long usuarioId  = tokenService.getUsuario(token);
		
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		
		if (usuario.isPresent()) {
			repasse.setUsuario(usuario.get());
		} else {
			throw new NotFoundException(String.format("O usuário de id %s não foi encontrado.", usuarioId));
		}
	}
}
