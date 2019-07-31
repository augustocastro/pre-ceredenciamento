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

import br.com.infobtc.controller.dto.ContratoInvestimentoDetalhadoDto;
import br.com.infobtc.controller.form.BancoForm;
import br.com.infobtc.controller.form.ContratoInvestimentoForm;
import br.com.infobtc.model.Banco;
import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.repository.BancoRepository;
import br.com.infobtc.repository.ContratoInvestimentoRepository;
import br.com.infobtc.repository.InvestidorRepository;

@RestController
@RequestMapping("/investimento")
public class ContratoInvestimentoController {

	@Autowired
	private BancoRepository bancoRepository; 
	
	@Autowired
	private ContratoInvestimentoRepository contratoInvestimentoRepository; 
	
	@Autowired
	private InvestidorRepository investidorRepository; 
	
	@PostMapping
	@Transactional
	public ResponseEntity<ContratoInvestimentoDetalhadoDto> cadastrar(@RequestBody @Valid ContratoInvestimentoForm form, UriComponentsBuilder uriComponentsBuilder) {
		ContratoInvestimento contrato = new ContratoInvestimento();
		Banco banco = new Banco();
		contrato.setBanco(banco);
		
		BancoForm bancoForm = form.getBanco();

		form.setarPropriedades(contrato, investidorRepository);
		bancoForm.setarPropriedades(banco);
		
		bancoRepository.save(banco);
		contratoInvestimentoRepository.save(contrato);
		
		URI uri = uriComponentsBuilder.path("/contrato-investimento/{id}").buildAndExpand(contrato.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContratoInvestimentoDetalhadoDto(contrato));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ContratoInvestimentoDetalhadoDto> atualizar(@PathVariable Long id, @Valid @RequestBody ContratoInvestimentoForm form) {
		Optional<ContratoInvestimento> contrato = contratoInvestimentoRepository.findById(id);

		if (contrato.isPresent()) {
			ContratoInvestimento contratoAtualizado = form.atualizar(id, contratoInvestimentoRepository, bancoRepository, investidorRepository);
			return ResponseEntity.ok(new ContratoInvestimentoDetalhadoDto(contratoAtualizado));
		}

		return ResponseEntity.notFound().build();
	}
	
	
	@GetMapping("/todos")
	public ResponseEntity<List<ContratoInvestimentoDetalhadoDto>> buscarTodos() {
		List<ContratoInvestimento> contratos = contratoInvestimentoRepository.findAll();
		return ResponseEntity.ok(new ContratoInvestimentoDetalhadoDto().converter(contratos));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContratoInvestimentoDetalhadoDto> buscarPorId(@PathVariable Long id) {
		Optional<ContratoInvestimento> contrato = contratoInvestimentoRepository.findById(id);

		if (contrato.isPresent()) {
			return ResponseEntity.ok(new ContratoInvestimentoDetalhadoDto(contrato.get()));
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if (contratoInvestimentoRepository.findById(id).isPresent()) {
			contratoInvestimentoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
