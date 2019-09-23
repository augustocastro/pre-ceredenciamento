package br.com.infobtc.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.infobtc.controller.dto.ContaDto;
import br.com.infobtc.controller.dto.ErroDto;
import br.com.infobtc.controller.form.ContaForm;
import br.com.infobtc.controller.form.PagamentoForm;
import br.com.infobtc.model.Conta;
import br.com.infobtc.model.StatusConta;
import br.com.infobtc.repository.ContaRepository;
import br.com.infobtc.repository.FornecedorRepository;
import javassist.NotFoundException;

@RestController
@RequestMapping("/conta-pagar")
public class ContaPagarController {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository; 
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ContaForm contaForm, UriComponentsBuilder uriComponentsBuilder) {
		Conta conta = new Conta();
		try {
			contaForm.setarPropriedades(conta, fornecedorRepository);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(e.getMessage()));
		}

		contaRepository.save(conta);

		URI uri = uriComponentsBuilder.path("/conta-pagar/{id}").buildAndExpand(conta.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContaDto(conta));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody ContaForm contaForm) {
		Optional<Conta> conta = contaRepository.findById(id);

		if (conta.isPresent()) {
			try {
				contaForm.atualizar(conta.get(), fornecedorRepository);
			} catch (NotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(e.getMessage()));
			}
			return ResponseEntity.ok(new ContaDto(conta.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/todos")
	public ResponseEntity<List<ContaDto>> buscarTodos(StatusConta status) {
		List<Conta> contas;
		if (status != null) {
			contas = contaRepository.findByStatus(status);
		} else {
			contas = contaRepository.findAll();
		}
		return ResponseEntity.ok(new ContaDto().converter(contas));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContaDto> buscarPorId(@PathVariable Long id) {
		Optional<Conta> conta = contaRepository.findById(id);

		if (conta.isPresent()) {
			return ResponseEntity.ok(new ContaDto(conta.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<?> pagar(@PathVariable Long id,  @Valid @RequestBody PagamentoForm pagamentoForm) {
		Optional<Conta> optional = contaRepository.findById(id);

		if (optional.isPresent()) {
			Conta conta = optional.get();
			
			double valor = pagamentoForm.getValor_pago().doubleValue();
			double valorPago = conta.getValorPago().doubleValue();
			double valorTotal = conta.getValorTotal().doubleValue();
			
			conta.setMulta(pagamentoForm.getMulta() != null ? pagamentoForm.getMulta() : conta.getMulta() );
			conta.setDesconto(pagamentoForm.getDesconto() != null ? pagamentoForm.getDesconto() : conta.getDesconto());
			conta.setJuros(pagamentoForm.getJuros() != null ? pagamentoForm.getJuros() : conta.getJuros());
			conta.setValorTotal(pagamentoForm.getValor_total());
			conta.setDtPagamento(pagamentoForm.getDt_pagamento());

			if (valor > valorTotal || valor < 1 || (valor + valorPago) > valorTotal) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErroDto("O valor não pode ser maior que o valor da conta nem menor do que 0."));
			}  else if (valor < valorTotal) {
				if (LocalDate.now().isAfter(conta.getDtVencimento())) {
					conta.setStatus(StatusConta.EM_ATRASO);
				}
				conta.setValorPago(new BigDecimal(valorPago + valor));
			}
			
			valorPago = conta.getValorPago().doubleValue();
			valorTotal = conta.getValorTotal().doubleValue();
					
			if (valorTotal == valorPago || valorTotal == valor) {
				conta.setStatus(StatusConta.LIQUIDADO);
				
				if (valorTotal != valorPago) {
					conta.setValorPago(new BigDecimal(valor));
				}
			}
			
			return ResponseEntity.ok(new ContaDto(conta));
		}
		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if (contaRepository.findById(id).isPresent()) {
			contaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
