package br.com.infobtc.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infobtc.controller.dto.InvestidorDto;
import br.com.infobtc.model.Investidor;
import br.com.infobtc.repository.InvestidorRepository;

@RestController
@RequestMapping("/investidor")
public class InvestidorController {
	
	@Autowired
	private InvestidorRepository investidorRepository;

	@GetMapping("/todos")
	public ResponseEntity<List<InvestidorDto>> buscarTodos() {
		List<Investidor> investidores = investidorRepository.findAll();
		return ResponseEntity.ok(new InvestidorDto().converter(investidores));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Investidor> buscarPorId(@PathVariable Long id) {
		Optional<Investidor> investidor = investidorRepository.findById(id);

		if (investidor.isPresent()) {
			return ResponseEntity.ok(investidor.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if (investidorRepository.findById(id).isPresent()) {
			investidorRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
}
