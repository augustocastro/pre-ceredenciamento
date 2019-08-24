package br.com.infobtc.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infobtc.controller.dto.ErroDto;
import br.com.infobtc.model.Contrato;
import br.com.infobtc.repository.ContratoRepository;

@RestController
@RequestMapping("/contrato")
public class ContratoController<T> {

	@Autowired
	private ContratoRepository contratoRespository;
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if (contratoRespository.findById(id).isPresent()) {
			contratoRespository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	
	@PatchMapping("/validar1/{id}")
	@Transactional
	public ResponseEntity<?> validar1(@PathVariable Long id) {
		Optional<Contrato> contrato = contratoRespository.findById(id);

		if (contrato.isPresent()) {
			contrato.get().setValid1(true);
			return ResponseEntity.ok(contrato.get().criaDto(contrato.get()));
		}

		return ResponseEntity.notFound().build();
	}
	
	@PatchMapping("/validar2/{id}")
	@Transactional
	public ResponseEntity<?> validar2(@PathVariable Long id) {
		Optional<Contrato> contrato = contratoRespository.findById(id);

		if (contrato.isPresent()) {
			if(contrato.get().isValid1()) {
				contrato.get().setValid2(true);
				return ResponseEntity.ok(contrato.get().criaDto(contrato.get()));
			}

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErroDto("Não é permitido fazer a segunda validação sem antes ter sido feita a primeira!"));
		}

		return ResponseEntity.notFound().build();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Optional<Contrato> contrato = contratoRespository.findById(id);

		if (contrato.isPresent()) {
			return ResponseEntity.ok(contrato.get().criaDto(contrato.get()));
		}

		return ResponseEntity.notFound().build();
	}
}
