package br.com.infobtc.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.infobtc.controller.dto.ContratoReinvestimentoDto;
import br.com.infobtc.controller.dto.ErroDto;
import br.com.infobtc.controller.form.BancoForm;
import br.com.infobtc.controller.form.ContratoArquivosForm;
import br.com.infobtc.controller.form.ContratoReinvestimentoForm;
import br.com.infobtc.model.Banco;
import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.model.ContratoReinvestimento;
import br.com.infobtc.model.Status;
import br.com.infobtc.repository.BancoRepository;
import br.com.infobtc.repository.ContratoInvestimentoRepository;
import br.com.infobtc.repository.ContratoReinvestimentoRepository;
import br.com.infobtc.service.S3Service;
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

	@Autowired
	private S3Service s3Service;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@Valid @ModelAttribute ContratoArquivosForm contratoForm, UriComponentsBuilder uriComponentsBuilder) {
		ContratoReinvestimento contrato = new ContratoReinvestimento();
		Banco banco = new Banco();
		contrato.setBanco(banco);
	
		try {
			ContratoReinvestimentoForm form = new ObjectMapper().readValue(contratoForm.getContrato(), ContratoReinvestimentoForm.class);
			BancoForm bancoForm = form.getBanco();

			try {
				form.setarPropriedades(contrato, contratoInvestimentoRepository);
			} catch (NotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(e.getMessage()));
			}
			
			if (contratoForm.getArquivos() != null && contratoForm.getArquivos().length > 0) {
				for (MultipartFile file : contratoForm.getArquivos()) {
					URI uploadFile = s3Service.uploadFile(file);
					contrato.getArquivosUrl().add(uploadFile.toURL().toString());
				}
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
		} catch (JsonParseException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErroDto("Erro ao converter JSON para objeto Java"));
		} catch (JsonMappingException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErroDto("Erro na desrealização do JSON devido a erros de mapeamento."));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErroDto("Erro nos arquivos enviados."));
		}
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
	public Page<ContratoReinvestimentoDto> buscarTodos(Status statusContrato, Status statusFinanceiro, @PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
		Page<ContratoReinvestimento> contratos;
		
		if (statusContrato == null && statusFinanceiro == null) {
			contratos = contratoReinvestimentoRepository.findAll(paginacao);
		} else {
			if (statusContrato != null && statusFinanceiro == null) {
				contratos = contratoReinvestimentoRepository.findByStatusContrato(statusContrato, paginacao);
			} else if (statusFinanceiro != null && statusContrato == null) {
				contratos = contratoReinvestimentoRepository.findByStatusFinanceiro(statusFinanceiro, paginacao);
			} else {
				contratos = contratoReinvestimentoRepository.findByStatusContratoAndStatusFinanceiro(statusContrato, statusFinanceiro, paginacao);
			}
		}
		return new ContratoReinvestimentoDto().converter(contratos);
	}

}
