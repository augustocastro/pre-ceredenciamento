package br.com.infobtc.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.infobtc.controller.dto.InvestidorPessoaFisicaDto;
import br.com.infobtc.controller.form.EnderecoForm;
import br.com.infobtc.controller.form.InvestidorPessoaFisicaForm;
import br.com.infobtc.model.Endereco;
import br.com.infobtc.model.InvestidorPessoaFisica;
import br.com.infobtc.repository.ConsultorRepository;
import br.com.infobtc.repository.EnderecoRepository;
import br.com.infobtc.repository.InvestidorPessoaFisicaRepository;

@RestController
@RequestMapping("/investidor-pessoa-fisica")
public class InvestidorPessoaFisicaControler {

	@Autowired
	private InvestidorPessoaFisicaRepository investidorPessoaFisicaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ConsultorRepository consultorRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<InvestidorPessoaFisicaDto> cadastrar(@RequestBody @Valid InvestidorPessoaFisicaForm pessoaForm, UriComponentsBuilder uriComponentsBuilder) {
		InvestidorPessoaFisica pessoaFisica = new InvestidorPessoaFisica();
		Endereco endereco = new Endereco();
		EnderecoForm enderecoForm = pessoaForm.getEndereco();

		pessoaFisica.setEndereco(endereco);
		
		enderecoForm.setarPropriedades(endereco);
		pessoaForm.setarPropriedades(pessoaFisica, consultorRepository);
		
		enderecoRepository.save(endereco);
		investidorPessoaFisicaRepository.save(pessoaFisica);

		URI uri = uriComponentsBuilder.path("/investidor-pessoa-fisica/{id}").buildAndExpand(pessoaFisica.getId()).toUri();
		InvestidorPessoaFisicaDto pessoaFisicaDto = new InvestidorPessoaFisicaDto(pessoaFisica);
		return ResponseEntity.created(uri).body(pessoaFisicaDto);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<InvestidorPessoaFisicaDto> atualizar(@PathVariable Long id, @Valid @RequestBody InvestidorPessoaFisicaForm form) {
		Optional<InvestidorPessoaFisica> investidor = investidorPessoaFisicaRepository.findById(id);

		if (investidor.isPresent()) {
			InvestidorPessoaFisica investidorAtualizado = form.atualizar(id, investidorPessoaFisicaRepository, enderecoRepository, consultorRepository);
			InvestidorPessoaFisicaDto investidorDto = new InvestidorPessoaFisicaDto(investidorAtualizado);
			return ResponseEntity.ok(investidorDto);
		}

		return ResponseEntity.notFound().build();
	}
	

	@GetMapping("/todos")
	public ResponseEntity<List<InvestidorPessoaFisicaDto>> buscarTodos() {
		List<InvestidorPessoaFisica> investidores = investidorPessoaFisicaRepository.findAll();
		return ResponseEntity.ok(new InvestidorPessoaFisicaDto().converter(investidores));
	}

}