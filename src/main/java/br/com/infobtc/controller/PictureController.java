package br.com.infobtc.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.infobtc.controller.form.EnderecoForm;
import br.com.infobtc.controller.form.InvestidorPessoaJuridicaForm;
import br.com.infobtc.model.Endereco;
import br.com.infobtc.model.InvestidorPessoaJuridica;
import br.com.infobtc.repository.ConsultorRepository;
import br.com.infobtc.repository.EnderecoRepository;
import br.com.infobtc.repository.InvestidorPessoaJuridicaRepository;
import br.com.infobtc.service.S3Service;

@RestController
@RequestMapping("/picture")
public class PictureController {

	@Autowired
	private S3Service s3Service;

	@Autowired
	private InvestidorPessoaJuridicaRepository investidorPessoaJuridicaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ConsultorRepository consultorRepository;
	
	@Transactional
	@PostMapping
	ResponseEntity<Void> uploadFile(@RequestParam("investidor") String investidorStringJson, List<MultipartFile> arquivos) {
		ObjectMapper mapper = new ObjectMapper();

		InvestidorPessoaJuridicaForm investidorForm = null;

		try {
			investidorForm  = mapper.readValue(investidorStringJson, InvestidorPessoaJuridicaForm.class);
			InvestidorPessoaJuridica investidor = new InvestidorPessoaJuridica();
			Endereco endereco = new Endereco();
			EnderecoForm enderecoForm = investidorForm.getEndereco();
			
			investidor.setEndereco(endereco);
			
			enderecoForm.setarPropriedades(endereco);
			investidorForm.setarPropriedades(investidor, consultorRepository);
			
			enderecoRepository.save(endereco);
			
			for (MultipartFile file : arquivos) {
				URI uploadFile = s3Service.uploadFile(file);
				investidor.getArquivosUrl().add(uploadFile.toURL().toString());
			}
			
			investidorPessoaJuridicaRepository.save(investidor);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(investidorStringJson);

		for (MultipartFile arquivo : arquivos) {
			System.out.println(arquivo.getOriginalFilename());
		}

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
