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
import br.com.infobtc.controller.form.PessoaJuridicaForm;
import br.com.infobtc.model.Endereco;
import br.com.infobtc.model.PessoaJuridica;
import br.com.infobtc.repository.EnderecoRepository;
import br.com.infobtc.repository.PessoaJuridicaRepository;

@RestController
@RequestMapping("/pessoa-juridica")
public class PessoaJuridicaControler {

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<PessoaJuridica> cadastrar(@RequestBody @Valid PessoaJuridicaForm pessoaForm, UriComponentsBuilder uriComponentsBuilder) {
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		Endereco endereco = new Endereco();
		EnderecoForm enderecoForm = pessoaForm.getEndereco();

		pessoaJuridica.setEndereco(endereco);
		
		enderecoForm.setarPropriedades(endereco);
		pessoaForm.setarPropriedades(pessoaJuridica);

		enderecoRepository.save(endereco);
		pessoaJuridicaRepository.save(pessoaJuridica);

		URI uri = uriComponentsBuilder.path("/pessoa-juridica/{id}").buildAndExpand(pessoaJuridica.getId()).toUri();
		return ResponseEntity.created(uri).body(pessoaJuridica);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PessoaJuridica> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaJuridicaForm form) {
		Optional<PessoaJuridica> pessoaFisica = pessoaJuridicaRepository.findById(id);

		if (pessoaFisica.isPresent()) {
			PessoaJuridica pessoaFisicaAtualizada = form.atualizar(id, pessoaJuridicaRepository, enderecoRepository);
			return ResponseEntity.ok(pessoaFisicaAtualizada);
		}

		return ResponseEntity.notFound().build();
	}
	
}
