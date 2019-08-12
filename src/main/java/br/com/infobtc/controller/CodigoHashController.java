package br.com.infobtc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infobtc.controller.dto.DadosHashDto;
import br.com.infobtc.controller.form.DadosHashForm;
import br.com.infobtc.model.DadosHash;
import br.com.infobtc.repository.DadosHashRepository;
import br.com.infobtc.service.EmailService;
import br.com.infobtc.service.HashService;

@RestController
@RequestMapping("/hash")
public class CodigoHashController {

	@Autowired
	private HashService hashService;
	
	@Autowired
	private DadosHashRepository dadosHashController;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping
	public ResponseEntity<DadosHashDto> gerarCodigoHash(@RequestBody @Valid DadosHashForm dadosHashForm) {
		String hash = hashService.gerarCodigoHash();
		
		DadosHash dadosHash = new DadosHash();
		dadosHash.setHash(hash);
		dadosHashForm.setarPropriedades(dadosHash);
		
		dadosHashController.save(dadosHash);
		
		emailService.send(dadosHash.getNome(), dadosHash.getEmail(), dadosHash.getHash());
		return ResponseEntity.ok(new DadosHashDto(dadosHash));
	}

}
