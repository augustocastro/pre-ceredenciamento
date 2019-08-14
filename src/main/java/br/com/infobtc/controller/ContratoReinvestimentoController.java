package br.com.infobtc.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.infobtc.controller.dto.ContratoReinvestimentoDto;
import br.com.infobtc.controller.form.BancoForm;
import br.com.infobtc.controller.form.ContratoReinvestimentoForm;
import br.com.infobtc.model.Banco;
import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.model.ContratoReinvestimento;
import br.com.infobtc.repository.BancoRepository;
import br.com.infobtc.repository.ContratoInvestimentoRepository;
import br.com.infobtc.repository.ContratoReinvestimentoRepository;

@RestController
@RequestMapping("/reinvestimento")
public class ContratoReinvestimentoController {

	@Autowired
	private BancoRepository bancoRepository; 
	
	@Autowired
	private ContratoInvestimentoRepository contratoInvestimentoRepository; 
	
	@Autowired
	private ContratoReinvestimentoRepository contratoReinvestimentoRepository; 

	
	@PostMapping
	@Transactional
	public ResponseEntity<ContratoReinvestimentoDto> cadastrar(@RequestBody @Valid ContratoReinvestimentoForm form, UriComponentsBuilder uriComponentsBuilder) {
		ContratoReinvestimento contrato = new ContratoReinvestimento();
		Banco banco = new Banco();
		contrato.setBanco(banco);
		
		BancoForm bancoForm = form.getBanco();

		form.setarPropriedades(contrato, contratoInvestimentoRepository);
		bancoForm.setarPropriedades(banco);
		
		bancoRepository.save(banco);
		contratoReinvestimentoRepository.save(contrato);
		
		Optional<ContratoInvestimento> investimento = contratoInvestimentoRepository.findById(contrato.getInvestimento().getId());
		
		if (investimento.isPresent()) {
			investimento.get().getReinvestimentos().add(contrato);
		}
		
		URI uri = uriComponentsBuilder.path("/reinvestimento/{id}").buildAndExpand(contrato.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContratoReinvestimentoDto(contrato));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ContratoReinvestimentoDto> atualizar(@PathVariable Long id, @Valid @RequestBody ContratoReinvestimentoForm form) {
		Optional<ContratoReinvestimento> contrato = contratoReinvestimentoRepository.findById(id);

		if (contrato.isPresent()) {
			ContratoReinvestimento contratoAtualizado = form.atualizar(id, contratoInvestimentoRepository, contratoReinvestimentoRepository, bancoRepository);
			return ResponseEntity.ok(new ContratoReinvestimentoDto(contratoAtualizado));
		}

		return ResponseEntity.notFound().build();
	}

	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<ContratoReinvestimentoDto> validar(@PathVariable Long id) {
		Optional<ContratoReinvestimento> contrato = contratoReinvestimentoRepository.findById(id);

		if (contrato.isPresent()) {
			contrato.get().setValid(true);
			return ResponseEntity.ok(new ContratoReinvestimentoDto(contrato.get()));
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/todos")
	public Page<ContratoReinvestimentoDto> buscarTodos(Boolean valid, @PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
		Page<ContratoReinvestimento> contratos;
		
		if (valid == null) {
			contratos = contratoReinvestimentoRepository.findAll(paginacao);
		} else {
			contratos = contratoReinvestimentoRepository.findByValid(valid, paginacao);
		}
		
		return new ContratoReinvestimentoDto().converter(contratos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContratoReinvestimentoDto> buscarPorId(@PathVariable Long id) {
		Optional<ContratoReinvestimento> contrato = contratoReinvestimentoRepository.findById(id);

		if (contrato.isPresent()) {
			return ResponseEntity.ok(new ContratoReinvestimentoDto(contrato.get()));
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if (contratoReinvestimentoRepository.findById(id).isPresent()) {
			contratoReinvestimentoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
