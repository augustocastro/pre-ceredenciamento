package br.com.infobtc.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.infobtc.config.security.service.TokenService;
import br.com.infobtc.controller.dto.ErroDto;
import br.com.infobtc.controller.dto.InvestidorDto;
import br.com.infobtc.controller.dto.InvestidorPessoaFisicaDto;
import br.com.infobtc.controller.dto.InvestidorPessoaJuridicaDto;
import br.com.infobtc.controller.vo.InvestidorEnderecoVo;
import br.com.infobtc.dao.InvestidorDao;
import br.com.infobtc.model.Consultor;
import br.com.infobtc.model.Investidor;
import br.com.infobtc.model.InvestidorPessoaFisica;
import br.com.infobtc.model.InvestidorPessoaJuridica;
import br.com.infobtc.model.Status;
import br.com.infobtc.repository.ConsultorRepository;
import br.com.infobtc.repository.InvestidorRepository;
import br.com.infobtc.service.S3Service;

@RestController
@RequestMapping("/investidor")
public class InvestidorController {

	@Autowired
	private InvestidorRepository investidorRepository;

	@Autowired
	private InvestidorDao investidorDao;
	
	@Autowired
	private ConsultorRepository consultorRepository;
	
	@Autowired
	private S3Service s3Service;

	@Autowired
	private TokenService tokenService; 
	
	@GetMapping("/investidores")
	public ResponseEntity<List<InvestidorDto>> buscarComFiltros(Status statusInvestidor, Long consultorId) {
		List<Investidor> investidores;
		
		if (statusInvestidor != null && consultorId != null) {
			investidores = investidorRepository.findByStatusInvestidorAndConsultorId(statusInvestidor, consultorId);
		} else if (consultorId != null){
			investidores = investidorRepository.findByConsultorId(consultorId);
		} else {
			investidores = investidorRepository.findByStatusInvestidorAndConsultorId(Status.EM_ANALISE, null);
		}
		
		return ResponseEntity.ok(new InvestidorDto().converter(investidores));
	}
	
	
//	@GetMapping("/consultor/{consultorId}")
//	public ResponseEntity<List<InvestidorDto>> buscarTodos(@PathVariable Long consultorId) {
//		List<Investidor> investidores = investidorRepository.findByConsultorId(consultorId);
//		return ResponseEntity.ok(new InvestidorDto().converter(investidores));
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Optional<Investidor> optional = investidorRepository.findById(id);

		if (optional.isPresent()) {
			Investidor investidor= optional.get();
			
			if (optional.get() instanceof InvestidorPessoaFisica) {
				return ResponseEntity.ok(new InvestidorPessoaFisicaDto((InvestidorPessoaFisica)investidor));
			}
			
			return ResponseEntity.ok(new InvestidorPessoaJuridicaDto((InvestidorPessoaJuridica)investidor));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if (investidorRepository.findById(id).isPresent()) {
			investidorRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("arquivo/{id}")
	@Transactional
	public ResponseEntity<?> removerArquivo(@PathVariable Long id, @RequestParam String arquivo) {
		Optional<Investidor> investidor = investidorRepository.findById(id);
		String nomeArquivo = arquivo.split("/")[3];

		if (investidor.isPresent()) {
			investidor.get().getArquivosUrl().removeIf(file -> file.contains(nomeArquivo));
			s3Service.remover(nomeArquivo);	
			
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("arquivo/{id}")
	@Transactional
	public ResponseEntity<?> adicionarArquivos(@PathVariable Long id, @RequestParam("arquivos") MultipartFile[] arquivos) {
		Optional<Investidor> optional = investidorRepository.findById(id);

		try {
			if (optional.isPresent()) {
				Investidor investidor = optional.get();
				
				if (arquivos != null && arquivos.length > 0) {
					for (MultipartFile file : arquivos) {
						URI uploadFile = s3Service.uploadFile(file);
						investidor.getArquivosUrl().add(uploadFile.toURL().toString());
					}
					return ResponseEntity.ok(investidor);
				} 
			}
			return ResponseEntity.notFound().build();
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErroDto("Erro nos arquivos enviados."));
		}
	}
	
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<?> aprovar(HttpServletRequest request, @PathVariable Long id, @RequestParam(required = true) Status statusInvestidor, String justificativa) {
		Optional<Investidor> optional = investidorRepository.findById(id);
		String token = tokenService.recuperarToken(request);
				
		if (optional.isPresent()) {
			Investidor investidor = optional.get();
			investidor.setJustificativaReprovacao(justificativa != null && statusInvestidor == Status.REPROVADO ? justificativa : investidor.getJustificativaReprovacao());
			investidor.setStatusInvestidor(statusInvestidor);
			setarConsultor(token, investidor);
			
			return ResponseEntity.ok(investidor);
		} 
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/relatorio/todos")
	public ResponseEntity<List<?>> consultarTodos() {
		List<InvestidorEnderecoVo> investidores = investidorDao.consultarTodos();
		return ResponseEntity.ok(investidores);
	}
	
	private void setarConsultor(String token, Investidor investidor) {
		Long usuarioId = tokenService.getUsuario(token);
		Consultor consultor = consultorRepository.findByUsuarioId(usuarioId).isPresent() ? consultorRepository.findByUsuarioId(usuarioId).get() : null ; 
		investidor.setConsultor(consultor);
	}
}
