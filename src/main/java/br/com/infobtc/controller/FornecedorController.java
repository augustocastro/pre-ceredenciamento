package br.com.infobtc.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.infobtc.controller.dto.FornecedorDetalhadoDto;
import br.com.infobtc.controller.dto.FornecedorDto;
import br.com.infobtc.controller.form.EnderecoForm;
import br.com.infobtc.controller.form.FornecedorForm;
import br.com.infobtc.model.Endereco;
import br.com.infobtc.model.Fornecedor;
import br.com.infobtc.repository.EnderecoRepository;
import br.com.infobtc.repository.FornecedorRepository;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

	@Autowired
	private FornecedorRepository fornecedorRepository; 
	

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<FornecedorDetalhadoDto> cadastrar(@RequestBody @Valid FornecedorForm fornecedorForm, UriComponentsBuilder uriComponentsBuilder) {
		Fornecedor fornecedor = new Fornecedor();
		Endereco endereco = new Endereco();
		EnderecoForm enderecoForm = fornecedorForm.getEndereco();
		
		if (enderecoForm != null) {
			enderecoForm.setarPropriedades(endereco);
			fornecedor.setEndereco(endereco);
			enderecoRepository.save(endereco);
		}
		
		fornecedorForm.setarPropriedades(fornecedor);
		fornecedorRepository.save(fornecedor);
		
		URI uri = uriComponentsBuilder.path("/fornecedor/{id}").buildAndExpand(fornecedor.getId()).toUri();
		return ResponseEntity.created(uri).body(new FornecedorDetalhadoDto(fornecedor));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<FornecedorDetalhadoDto> atualizar(@PathVariable Long id, @Valid @RequestBody FornecedorForm fornecedorForm) {
		Optional<Fornecedor> optional = fornecedorRepository.findById(id);

		if (optional.isPresent()) {
			Fornecedor fornecedor = fornecedorForm.atualizar(id, fornecedorRepository, enderecoRepository);
			return ResponseEntity.ok(new FornecedorDetalhadoDto(fornecedor));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/todos")
	public ResponseEntity<List<FornecedorDto>> buscarTodos() {
		List<Fornecedor> fornecedores = fornecedorRepository.findAll();
		return ResponseEntity.ok(new FornecedorDto().converter(fornecedores));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FornecedorDetalhadoDto> buscarPorId(@PathVariable Long id) {
		Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
		
		if (fornecedor.isPresent()) {
			return ResponseEntity.ok(new FornecedorDetalhadoDto(fornecedor.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if (fornecedorRepository.findById(id).isPresent()) {
			fornecedorRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
