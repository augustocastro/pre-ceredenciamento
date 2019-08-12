package br.com.infobtc.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.infobtc.controller.form.InvestidorPessoaJuridicaForm;
import br.com.infobtc.service.S3Service;

@RestController
@RequestMapping("/picture")
public class PictureController {

	@Autowired
	private S3Service s3Service;

	@PostMapping
	ResponseEntity<Void> uploadFile(String investidor, List<MultipartFile> arquivos) {
		ObjectMapper mapper = new ObjectMapper();
		
		InvestidorPessoaJuridicaForm investidorPessoaJuridica = null;
		
		try {
			investidorPessoaJuridica = mapper.readValue(investidor, InvestidorPessoaJuridicaForm.class);
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

		System.out.println(investidor);
		
		for (MultipartFile arquivo : arquivos) {
			System.out.println(arquivo.getOriginalFilename());
		}
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
