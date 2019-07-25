package br.com.infobtc.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.infobtc.controller.form.EnderecoForm;
import br.com.infobtc.controller.form.PessoaFisicaForm;
import br.com.infobtc.model.Endereco;
import br.com.infobtc.model.PessoaFisica;
import br.com.infobtc.repository.EnderecoRepository;
import br.com.infobtc.repository.PessoaFisicaRepository;

@RestController
@RequestMapping("/pessoa-fisica")
public class PessoaFisicaControler {

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<PessoaFisica> cadastrar(@RequestBody @Valid PessoaFisicaForm pessoaForm, UriComponentsBuilder uriComponentsBuilder) {
		PessoaFisica pessoaFisica = new PessoaFisica();
		Endereco endereco = new Endereco();
		EnderecoForm enderecoForm = pessoaForm.getEndereco();

		pessoaFisica.setEndereco(endereco);
		
		enderecoForm.setarPropriedades(endereco);
		pessoaForm.setarPropriedades(pessoaFisica);
		
		enderecoRepository.save(endereco);
		pessoaFisicaRepository.save(pessoaFisica);

		URI uri = uriComponentsBuilder.path("/pessoa-fisica/{id}").buildAndExpand(pessoaFisica.getId()).toUri();
		return ResponseEntity.created(uri).body(pessoaFisica);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PessoaFisica> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaFisicaForm form) {
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findById(id);

		if (pessoaFisica.isPresent()) {
			PessoaFisica pessoaFisicaAtualizada = form.atualizar(id, pessoaFisicaRepository, enderecoRepository);
			return ResponseEntity.ok(pessoaFisicaAtualizada);
		}

		return ResponseEntity.notFound().build();
	}

}
