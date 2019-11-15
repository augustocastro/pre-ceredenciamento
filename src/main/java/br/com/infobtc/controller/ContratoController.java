package br.com.infobtc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.DocumentException;

import br.com.infobtc.controller.dto.ContratoDto;
import br.com.infobtc.controller.dto.ErroDto;
import br.com.infobtc.controller.vo.ContratoConsultorInvestidorVo;
import br.com.infobtc.controller.vo.ParcelaVo;
import br.com.infobtc.dao.ContratoDao;
import br.com.infobtc.model.Contrato;
import br.com.infobtc.model.Status;
import br.com.infobtc.repository.ContratoRepository;
import br.com.infobtc.service.ContratoPDFService;
import br.com.infobtc.service.S3Service;

@RestController
@RequestMapping("/contrato")
public class ContratoController<T> {

	@Autowired
	private ContratoRepository contratoRespository;

	@Autowired
	private ContratoDao contratoDao; 
	
	@Autowired
	private ContratoPDFService contratoPDFService;
	
	@Autowired
	private S3Service s3Service;
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if (contratoRespository.findById(id).isPresent()) {
			contratoRespository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Optional<Contrato> contrato = contratoRespository.findById(id);

		if (contrato.isPresent()) {
			return ResponseEntity.ok(contrato.get().criaDto());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/gerar-pdf/{id}")
	public ResponseEntity<?> gerarPdf(@PathVariable Long id) throws Docx4JException, IOException, DocumentException {
		Optional<Contrato> contrato = contratoRespository.findById(id);

		if (contrato.isPresent()) {
			try {
				File file = contratoPDFService.gerarPdf(contrato.get());
				InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
				
				return ResponseEntity.ok().contentLength(file.length()).contentType(MediaType.parseMediaType("application/pdf")).body(resource);
			} catch (IOException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErroDto("Erro ao ler arquivo para gerar PDF."));
			} catch (DocumentException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErroDto("Erro ao gerar PDF."));
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("arquivo/{id}")
	@Transactional
	public ResponseEntity<?> removerArquivo(@PathVariable Long id, @RequestParam String arquivo) {
		Optional<Contrato> contrato = contratoRespository.findById(id);
		String nomeArquivo = arquivo.split("/")[3];

		if (contrato.isPresent()) {
			contrato.get().getArquivosUrl().removeIf(file -> file.contains(nomeArquivo));
			s3Service.remover(nomeArquivo);	
			
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("arquivo/{id}")
	@Transactional
	public ResponseEntity<?> adicionarArquivos(@PathVariable Long id, @RequestParam("arquivos") MultipartFile[] arquivos) {
		Optional<Contrato> optional = contratoRespository.findById(id);

		try {
			if (optional.isPresent()) {
				Contrato contrato = optional.get();
				
				if (arquivos != null && arquivos.length > 0) {
					for (MultipartFile file : arquivos) {
						URI uploadFile = s3Service.uploadFile(file);
						contrato.getArquivosUrl().add(uploadFile.toURL().toString());
					}
					return ResponseEntity.ok(contrato);
				} 
			}
			return ResponseEntity.notFound().build();
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErroDto("Erro nos arquivos enviados."));
		}
	}
	
	@PatchMapping("/aprovar-contrato/{id}")
	@Transactional
	public ResponseEntity<?> aprovarContrato(@PathVariable Long id, @RequestParam(required = true) Status statusContrato, String justificativa) {
		Optional<Contrato> optional = contratoRespository.findById(id);

		if (optional.isPresent()) {
			Contrato contrato = optional.get();
			contrato.setStatusContrato(statusContrato);
			contrato.setJustificativaReprovacao(justificativa != null && statusContrato == Status.REPROVADO ? justificativa : contrato.getJustificativaReprovacao());
			return ResponseEntity.ok(contrato.criaDto());
		}
		return ResponseEntity.notFound().build();
	}

	@PatchMapping("/aprovar-financeiro/{id}")
	@Transactional
	public ResponseEntity<?> aprovarFinanceiro(@PathVariable Long id, @RequestParam(required = true) Status statusFinanceiro, String justificativa) {
		Optional<Contrato> optional = contratoRespository.findById(id);

		if (optional.isPresent()) {
			Contrato contrato = optional.get();
			contrato.setStatusFinanceiro(statusFinanceiro);
			contrato.setJustificativaReprovacao(justificativa != null && statusFinanceiro == Status.REPROVADO ? justificativa : contrato.getJustificativaReprovacao());
			return ResponseEntity.ok(optional.get().criaDto());	
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("relatorio/contratos-semana")
	public ResponseEntity<?> buscarContratosSemana() {
		LocalDate dataHoje= LocalDate.now();
		LocalDate dateUmaSemanaAtras= LocalDate.now().minusDays(7);
		
		List<Contrato> contratos = contratoRespository.buscarContratosSemana(dateUmaSemanaAtras, dataHoje);
		return ResponseEntity.ok(new ContratoDto().converter(contratos));
	}
	
	@GetMapping("relatorio/relacao-contratos")
	public ResponseEntity<?> consultarFiltrandoPorConsultorEIntervalo(Long idConsultor, String dtInicio, String dtTermino) {
		LocalDate dataIncio = dtInicio != null ? LocalDate.parse(dtInicio) : null;
		LocalDate dataTermino=  dtInicio != null ? LocalDate.parse(dtTermino) : null;
		
		List<ContratoConsultorInvestidorVo> contratos = contratoDao.buscarRelacaoContratos(idConsultor, dataIncio, dataTermino);
		return ResponseEntity.ok(contratos);
	}
	
	@GetMapping("parcela/parcelas")
	public ResponseEntity<?> buscarParcelas(Long idContrato, Boolean repassado) {
		
		List<ParcelaVo> contratos = contratoDao.buscarParcelas(idContrato, repassado);
		contratos.removeIf(contrato -> contrato.getValor_repassado() != 0 && !contrato.isRepassado());
		
		return ResponseEntity.ok(contratos);
	}
	
	
	@PatchMapping("rescisao/{id}")
	@Transactional
	public ResponseEntity<?> solicitarRescisao(@PathVariable Long id) {
		Optional<Contrato> optional = contratoRespository.findById(id);
		
		if (optional.isPresent()) {
			Contrato contrato = optional.get();
			contrato.setStatusContrato(Status.CANCELADO);
			contrato.setStatusFinanceiro(Status.CANCELADO);
			
			return ResponseEntity.ok(contrato.criaDto());
		}
		
		return ResponseEntity.notFound().build();
	}

}
