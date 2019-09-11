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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.infobtc.controller.dto.ContratoReinvestimentoDto;
import br.com.infobtc.controller.dto.ErroDto;
import br.com.infobtc.controller.form.BancoForm;
import br.com.infobtc.controller.form.ContratoReinvestimentoForm;
import br.com.infobtc.model.Banco;
import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.model.ContratoReinvestimento;
import br.com.infobtc.repository.BancoRepository;
import br.com.infobtc.repository.ContratoInvestimentoRepository;
import br.com.infobtc.repository.ContratoReinvestimentoRepository;
import javassist.NotFoundException;

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
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ContratoReinvestimentoForm form, UriComponentsBuilder uriComponentsBuilder) {
		ContratoReinvestimento contrato = new ContratoReinvestimento();
		Banco banco = new Banco();
		contrato.setBanco(banco);
		
		BancoForm bancoForm = form.getBanco();

		try {
			form.setarPropriedades(contrato, contratoInvestimentoRepository);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(e.getMessage()));
		}
		
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
	public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody ContratoReinvestimentoForm form) {
		Optional<ContratoReinvestimento> contrato = contratoReinvestimentoRepository.findById(id);

		if (contrato.isPresent()) {
			ContratoReinvestimento contratoAtualizado;
			try {
				contratoAtualizado = form.atualizar(id, contratoInvestimentoRepository, contratoReinvestimentoRepository, bancoRepository);
			} catch (NotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(e.getMessage()));
			}
			return ResponseEntity.ok(new ContratoReinvestimentoDto(contratoAtualizado));
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@GetMapping("/todos")
	public Page<?> buscarTodos(Boolean valid1, Boolean valid2, @PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
		Page<ContratoReinvestimento> contratos;
		
		if (valid1 == null && valid2 == null) {
			contratos = contratoReinvestimentoRepository.findAll(paginacao);
		} else {
			if (valid1 != null && valid2 == null) {
				contratos = contratoReinvestimentoRepository.findByValid1(valid1, paginacao);
			} else if (valid2 != null && valid1 == null) {
				contratos = contratoReinvestimentoRepository.findByValid2(valid2, paginacao);
			} else {
				contratos = contratoReinvestimentoRepository.findByValid1AndValid2(valid1, valid2, paginacao);
			}
		}
		return new ContratoReinvestimentoDto().converter(contratos);

	}

}
