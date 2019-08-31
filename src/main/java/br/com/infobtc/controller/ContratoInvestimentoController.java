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

import br.com.infobtc.controller.dto.ContratoInvestimentoDetalhadoDto;
import br.com.infobtc.controller.dto.ErroDto;
import br.com.infobtc.controller.form.BancoForm;
import br.com.infobtc.controller.form.ContratoInvestimentoForm;
import br.com.infobtc.model.Banco;
import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.repository.BancoRepository;
import br.com.infobtc.repository.ConsultorRepository;
import br.com.infobtc.repository.ContratoInvestimentoRepository;
import br.com.infobtc.repository.InvestidorRepository;
import javassist.NotFoundException;

@RestController
@RequestMapping("/investimento")
public class ContratoInvestimentoController {

	@Autowired
	private BancoRepository bancoRepository; 
	
	@Autowired
	private ContratoInvestimentoRepository contratoInvestimentoRepository; 
	
	@Autowired
	private InvestidorRepository investidorRepository;
	
	@Autowired
	private ConsultorRepository consultorRepository; 
		
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ContratoInvestimentoForm form, UriComponentsBuilder uriComponentsBuilder) {
		ContratoInvestimento contrato = new ContratoInvestimento();
		Banco banco = new Banco();
		contrato.setBanco(banco);
		
		BancoForm bancoForm = form.getBanco();

		try {
			form.setarPropriedades(contrato, investidorRepository, consultorRepository);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(e.getMessage()));
		}
		bancoForm.setarPropriedades(banco);
		
		bancoRepository.save(banco);
		contratoInvestimentoRepository.save(contrato);
		
		URI uri = uriComponentsBuilder.path("/investimento/{id}").buildAndExpand(contrato.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContratoInvestimentoDetalhadoDto(contrato));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody ContratoInvestimentoForm form) {
		Optional<ContratoInvestimento> contrato = contratoInvestimentoRepository.findById(id);

		if (contrato.isPresent()) {
			ContratoInvestimento contratoAtualizado;
			try {
				contratoAtualizado = form.atualizar(id, contratoInvestimentoRepository, bancoRepository, investidorRepository, consultorRepository);
			} catch (NotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(e.getMessage()));
			}
			return ResponseEntity.ok(new ContratoInvestimentoDetalhadoDto(contratoAtualizado));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/valid1")
	public Page<ContratoInvestimentoDetalhadoDto> buscarTodosAprovados1(Boolean valid, @PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
		Page<ContratoInvestimento> contratos;
		
		if (valid == null) {
			contratos = contratoInvestimentoRepository.findAll(paginacao);
		} else {
			contratos = contratoInvestimentoRepository.findByValid1(valid, paginacao);
		}
		return new ContratoInvestimentoDetalhadoDto().converter(contratos);
	}
	
	
	@GetMapping("/valid2")
	public Page<ContratoInvestimentoDetalhadoDto> buscarTodosAprovados2(Boolean valid, @PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
		Page<ContratoInvestimento> contratos;
		
		if (valid == null) {
			contratos = contratoInvestimentoRepository.findByValid1(true, paginacao);
		} else {
			contratos = contratoInvestimentoRepository.findByValid2(valid, paginacao);
		}
		return new ContratoInvestimentoDetalhadoDto().converter(contratos);
	}

}
