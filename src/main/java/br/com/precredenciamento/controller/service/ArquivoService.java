package br.com.precredenciamento.controller.service;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.precredenciamento.model.Arquivo;
import br.com.precredenciamento.repository.ArquivoRepository;

@Service
@Transactional
public class ArquivoService {

	@Autowired
	private ArquivoRepository arquivoRepository;

	public Arquivo salvar(MultipartFile file) {
		try {
//			Blob blob = Hibernate.getLobCreator(sessionFactory.getCurrentSession()).createBlob(file.getBytes());
			Arquivo arquivo = new Arquivo();
			arquivo.setNome(file.getOriginalFilename());
			arquivo.setArquivo(file.getBytes());			
			return arquivoRepository.save(arquivo);
		} catch (HibernateException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void removerPorId(String id) {
		arquivoRepository.deleteById(id);
	}
}
