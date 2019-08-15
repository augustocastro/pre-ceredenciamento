package br.com.infobtc.controller;

import java.io.IOException;
import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.infobtc.controller.form.EnderecoForm;
import br.com.infobtc.controller.form.InvestidorArquivosForm;
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
	ResponseEntity<Void> uploadFile(@Valid @ModelAttribute InvestidorArquivosForm investidorArquivosForm) {

		for (MultipartFile file : investidorArquivosForm.getArquivos()) {
			System.out.println(file.getOriginalFilename());
		}

		System.out.println(investidorArquivosForm.getInvestidor());

		try {
			InvestidorPessoaJuridicaForm investidorForm = new ObjectMapper().readValue(investidorArquivosForm.getInvestidor(), InvestidorPessoaJuridicaForm.class);
			EnderecoForm enderecoForm = investidorForm.getEndereco();

			InvestidorPessoaJuridica investidor = new InvestidorPessoaJuridica();
			Endereco endereco = new Endereco();

			investidor.setEndereco(endereco);
			enderecoForm.setarPropriedades(endereco);
			investidorForm.setarPropriedades(investidor, consultorRepository);

			for (MultipartFile file : investidorArquivosForm.getArquivos()) {
				URI uploadFile = s3Service.uploadFile(file);
				investidor.getArquivosUrl().add(uploadFile.toURL().toString());
			}

			enderecoRepository.save(endereco);
			investidorPessoaJuridicaRepository.save(investidor);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
