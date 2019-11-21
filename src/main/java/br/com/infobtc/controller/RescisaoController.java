package br.com.infobtc.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.infobtc.controller.dto.ErroDto;
import br.com.infobtc.controller.dto.RescisaoContratoDto;
import br.com.infobtc.controller.dto.RescisaoDto;
import br.com.infobtc.controller.form.RescisaoForm;
import br.com.infobtc.model.Rescisao;
import br.com.infobtc.model.Status;
import br.com.infobtc.repository.ContratoRepository;
import br.com.infobtc.repository.RescisaoRepository;

@RestController
@RequestMapping("/rescisao")
public class RescisaoController {
	
	@Autowired
	private ContratoRepository contratoRespository;

	@Autowired
	private RescisaoRepository rescisaoRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid RescisaoForm rescisaoForm, UriComponentsBuilder uriComponentsBuilder) {
		try {
			Rescisao rescisao = new Rescisao();
			rescisaoForm.setarPropriedades(rescisao, contratoRespository);
			rescisaoRepository.save(rescisao);

			URI uri = uriComponentsBuilder.path("/rescisao/{id}").buildAndExpand(rescisao.getId()).toUri();
			return ResponseEntity.created(uri).body(new RescisaoDto(rescisao));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(e.getMessage()));
		}
	}
	
	
	@GetMapping("/rescisoes")
	@Transactional
	public ResponseEntity<?> consultar(Status status) {
		try {
			List<Rescisao> rescisoes;
			
			if (status != null) {				
				rescisoes = rescisaoRepository.findByStatus(status);
			} else {
				rescisoes = rescisaoRepository.findAll();
			}
			
			return ResponseEntity.ok(new RescisaoContratoDto().converterRepasses(rescisoes));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(e.getMessage()));
		}
	}
	
	@PatchMapping("/aprovar/{id}")
	@Transactional
	public ResponseEntity<?> aprovarRescisao(@PathVariable Long id, @RequestParam(required = true) Status status) {
		Optional<Rescisao> optional = rescisaoRepository.findById(id);

		if (optional.isPresent()) {
			Rescisao rescisao = optional.get();
			rescisao.setStatus(status);
			return ResponseEntity.ok(new RescisaoDto(rescisao));
		}
		return ResponseEntity.notFound().build();
	}
}
