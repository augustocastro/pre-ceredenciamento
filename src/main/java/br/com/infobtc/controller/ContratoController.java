package br.com.infobtc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
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

import br.com.infobtc.controller.dto.ErroDto;
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
	
//	@GetMapping("/relatorio-finaceiro")
//	public ResponseEntity<?> gerarRelatorioFinanceiro(String dtInicio, String dtTermino) {
//		List<Contrato> contratos;
//		
//		if (dtInicio != null && dtTermino != null) {
//			contratos = contratoRespository.findByIntervalDate(LocalDate.parse(dtInicio), LocalDate.parse(dtTermino));
//		} else {
//			 contratos = contratoRespository.getThisMonth();
//		}
//		
//		double valorTotalLiquido = contratos.stream().mapToDouble(contrato -> contrato.getValor().doubleValue() * 0.01).sum();
//		List<Long> ids = contratos.stream().map(contrato -> contrato.getId()).collect(Collectors.toList());
//		return ResponseEntity.ok(new ContaReceberFinanceiroDto(contratos.size(), valorTotalLiquido, ids));
//	}
	
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
	public ResponseEntity<?> validarContrato(@PathVariable Long id, @RequestParam(required = true) Status statusContrato) {
		Optional<Contrato> optional = contratoRespository.findById(id);

		if (optional.isPresent()) {
			Contrato contrato = optional.get();
			if (contrato.getStatusContrato() != Status.REPROVADO && contrato.getStatusContrato() != Status.APROVADO) {
				contrato.setStatusContrato(statusContrato);
				return ResponseEntity.ok(contrato.criaDto());
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
						new ErroDto("Após o cadastro de investimento ser aprovado ou reprovado o status do mesmo não pode ser alterado."));
			}

		}
		return ResponseEntity.notFound().build();
	}

	@PatchMapping("/aprovar-financeiro/{id}")
	@Transactional
	public ResponseEntity<?> validarFinanceiro(@PathVariable Long id, @RequestParam(required = true) Status statusFinanceiro) {
		Optional<Contrato> optional = contratoRespository.findById(id);

		if (optional.isPresent()) {
			Contrato contrato = optional.get();
			
			if (contrato.getStatusFinanceiro() != Status.REPROVADO && contrato.getStatusFinanceiro() != Status.APROVADO) {
				optional.get().setStatusFinanceiro(statusFinanceiro);
				return ResponseEntity.ok(optional.get().criaDto());
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
						new ErroDto("Após o cadastro de investimento ser aprovado ou reprovado o status do mesmo não pode ser alterado."));
			}
		}

		return ResponseEntity.notFound().build();
	}

}
