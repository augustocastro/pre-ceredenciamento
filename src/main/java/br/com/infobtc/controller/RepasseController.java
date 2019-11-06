package br.com.infobtc.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import br.com.infobtc.controller.dto.ErroDto;
import br.com.infobtc.controller.dto.RepasseDto;
import br.com.infobtc.controller.form.RepasseForm;
import br.com.infobtc.model.Repasse;
import br.com.infobtc.model.TipoRepasse;
import br.com.infobtc.repository.ParcelaRepository;
import br.com.infobtc.repository.RepasseRepository;
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
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@Valid @ModelAttribute RepasseForm repasseForm, UriComponentsBuilder uriComponentsBuilder) throws MalformedURLException {
		
		Repasse repasse = new Repasse();
		
		try {
			repasseForm.setarPropriedades(repasse, parcelaRepository);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(e.getMessage()));
		}
		if(repasseForm.getAnexo() != null) {
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
}
