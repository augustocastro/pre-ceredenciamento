package br.com.infobtc.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
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

import br.com.infobtc.controller.dto.ContratoInvestimentoDetalhadoDto;
import br.com.infobtc.controller.dto.ContratoInvestimentoDto;
import br.com.infobtc.controller.dto.ErroDto;
import br.com.infobtc.controller.form.BancoForm;
import br.com.infobtc.controller.form.ContratoArquivosForm;
import br.com.infobtc.controller.form.ContratoInvestimentoForm;
import br.com.infobtc.controller.service.ParcelaService;
import br.com.infobtc.controller.vo.InvestimentoConsultorBanco;
import br.com.infobtc.dao.ContratoInvestimentoDao;
import br.com.infobtc.model.Banco;
import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.model.Status;
import br.com.infobtc.model.TipoRendimento;
import br.com.infobtc.repository.BancoRepository;
import br.com.infobtc.repository.ConsultorRepository;
import br.com.infobtc.repository.ContratoInvestimentoRepository;
import br.com.infobtc.repository.InvestidorRepository;
import br.com.infobtc.service.S3Service;
import javassist.NotFoundException;

@RestController
@RequestMapping("/investimento")
public class ContratoInvestimentoController {

	@Autowired
	private BancoRepository bancoRepository; 
	
	@Autowired
	private ContratoInvestimentoRepository contratoInvestimentoRepository; 
	
//	@Autowired
//	private ContratoDao contratoDao;
	
	@Autowired
	private InvestidorRepository investidorRepository;
	
	@Autowired
	private ConsultorRepository consultorRepository; 
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ParcelaService parcelaService;


	@Autowired
	private ContratoInvestimentoDao contratoInvestimentoDao;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@Valid @ModelAttribute ContratoArquivosForm contratoForm, UriComponentsBuilder uriComponentsBuilder) {
		ContratoInvestimento contrato = new ContratoInvestimento();
		Banco banco = new Banco();
		contrato.setBanco(banco);
		
		try {
			ContratoInvestimentoForm form = new ObjectMapper().readValue(contratoForm.getContrato(), ContratoInvestimentoForm.class);
			BancoForm bancoForm = form.getBanco();

			try {
				form.setarPropriedades(contrato, investidorRepository, consultorRepository);
				
				if (contrato.getInvestidor().getStatusInvestidor() != Status.APROVADO) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErroDto("Apenas investidores que tiveram o cadastro aprovado podem fazer investimentos."));
				}
			} catch (NotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto(e.getMessage()));
			}
			bancoForm.setarPropriedades(banco);
			
			if (contratoForm.getArquivos() != null && contratoForm.getArquivos().length > 0) {
				for (MultipartFile file : contratoForm.getArquivos()) {
					URI uploadFile = s3Service.uploadFile(file);
					contrato.getArquivosUrl().add(uploadFile.toURL().toString());
				}
			}
			
			bancoRepository.save(banco);
			contratoInvestimentoRepository.save(contrato);
			
			parcelaService.criarParcelas(contrato);
			
			URI uri = uriComponentsBuilder.path("/investimento/{id}").buildAndExpand(contrato.getId()).toUri();
			return ResponseEntity.created(uri).body(new ContratoInvestimentoDetalhadoDto(contrato));
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

	@GetMapping("/todos")
	public Page<?> buscarTodos(Status statusContrato, Status statusFinanceiro, @PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
		Page<ContratoInvestimento> contratos;
		
		if (statusContrato == null && statusFinanceiro == null) {
			contratos = contratoInvestimentoRepository.findAll(paginacao);
		} else {
			if (statusContrato != null && statusFinanceiro == null) {
				contratos = contratoInvestimentoRepository.findByStatusContrato(statusContrato, paginacao);
			} else if (statusFinanceiro != null && statusContrato == null) {
				contratos = contratoInvestimentoRepository.findByStatusFinanceiro(statusFinanceiro, paginacao);
			} else {
				contratos = contratoInvestimentoRepository.findByStatusContratoAndStatusFinanceiro(statusContrato, statusFinanceiro, paginacao);
			}
		}
		return new ContratoInvestimentoDetalhadoDto().converter(contratos);
	}
	
	
//	@GetMapping("/investimentos-composto-aniversario")
//	public List<?> buscarTodos() {
//		List<ContratoInvestimento> contratos = contratoDao.buscarContratosCompostoAniversario();
//		return new ContratoInvestimentoDetalhadoDto().converter(contratos);
//	}
	
//	Investimento: Data/ Nome (investidor)/ Banco/ Titular/ CPF OU CNPJ/ Agência/ Conta/ código / tipo Rendimento/ Consultor.

	
	@GetMapping("relatorio/investimentos-banco")
	public ResponseEntity<?> consultarInvestimentosComDadosBancarios() {
		List<InvestimentoConsultorBanco> contratos = contratoInvestimentoDao.buscarInvestimentos();
		return ResponseEntity.ok(contratos);
	}
	
	@GetMapping("relatorio/investimentos")
	public ResponseEntity<?> consultarInvestimentos(TipoRendimento tipoRendimento) {
		List<ContratoInvestimento> contratos;
		
		if (tipoRendimento != null) {
			contratos = contratoInvestimentoRepository
					.findByTipoRendimentoAndStatusContratoAndStatusFinanceiro(tipoRendimento, Status.APROVADO, Status.APROVADO);	
		} else {
			contratos = contratoInvestimentoRepository
					.findByStatusContratoAndStatusFinanceiro(Status.APROVADO, Status.APROVADO);
		}
			
		return ResponseEntity.ok(new ContratoInvestimentoDto().converter(contratos));
	}
	
}
