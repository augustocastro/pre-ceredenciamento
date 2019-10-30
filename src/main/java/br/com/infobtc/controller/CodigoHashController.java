package br.com.infobtc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infobtc.config.security.service.TokenService;
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
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<DadosHashDto> gerarCodigoHash(HttpServletRequest request, @RequestBody @Valid DadosHashForm dadosHashForm) {
		String token = tokenService.recuperarToken(request);
		Long usuarioId = tokenService.getUsuario(token);
		String hash = hashService.gerarCodigoHash(usuarioId);
		
		DadosHash dadosHash = new DadosHash();
		dadosHash.setUsuario_consultor_id(usuarioId);
		dadosHash.setHash(hash);
		dadosHashForm.setarPropriedades(dadosHash);
		
		dadosHashController.save(dadosHash);
		emailService.send(dadosHash.getNome(), dadosHash.getEmail(), dadosHash.getHash());
		
		return ResponseEntity.ok(new DadosHashDto(dadosHash));
	}

}
