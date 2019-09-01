package br.com.infobtc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

import br.com.infobtc.controller.dto.ErroDto;
import br.com.infobtc.model.Contrato;
import br.com.infobtc.repository.ContratoRepository;
import br.com.infobtc.service.ContratoPDFService;

@RestController
@RequestMapping("/contrato")
public class ContratoController<T> {

	@Autowired
	private ContratoRepository contratoRespository;
	
	@Autowired
	private ContratoPDFService contratoPDFService; 
	
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
	
	@GetMapping("/gerar-pdf/{id}")
	public ResponseEntity<?> gerarPdf(@PathVariable Long id) throws Docx4JException, IOException, DocumentException {
		Optional<Contrato> contrato = contratoRespository.findById(id);
		
		if (contrato.isPresent()) {
			
			File file = contratoPDFService.gerarPdf(contrato.get());
		    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			  return ResponseEntity.ok()
			            .contentLength(file.length())
			            .contentType(MediaType.parseMediaType("application/pdf"))
			            .body(resource);
		}
			return ResponseEntity.notFound().build();
		
		
	}
}
