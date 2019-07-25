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

import br.com.infobtc.model.Pessoa;
import br.com.infobtc.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping("/todos")
	public ResponseEntity<List<Pessoa>> buscarTodos() {
		List<Pessoa> pessoaFisica = pessoaRepository.findAll();
		return ResponseEntity.ok(pessoaFisica);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);

		if (pessoa.isPresent()) {
			return ResponseEntity.ok(pessoa.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if (pessoaRepository.findById(id).isPresent()) {
			pessoaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
