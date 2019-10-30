package br.com.infobtc.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.infobtc.model.DadosHash;
import br.com.infobtc.repository.DadosHashRepository;

@Service
public class HashService {

	@Autowired
	private DadosHashRepository dadosHashRepository;
	
	@Value("${infobtc.jwt.secret}")
	private String secret;
	
	public String gerarCodigoHash(Long idUsuario) {
		String palavraChave = LocalDateTime.now().toString() + secret;
		
		/*
		 * Algoritimos que podem ser usados para gerar o hash:
		 * MD5
		 * SHA-1
		 * SHA-256
		*/		
		
		return stringHexa(gerarHash(palavraChave, "SHA-256"));
	}
	
	private String stringHexa(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
			int parteBaixa = bytes[i] & 0xf;
			if (parteAlta == 0)
				s.append('0');
			s.append(Integer.toHexString(parteAlta | parteBaixa));
		}
		return s.toString();
	}

	public byte[] gerarHash(String frase, String algoritmo) {
		try {
			MessageDigest md = MessageDigest.getInstance(algoritmo);
			md.update(frase.getBytes());
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public boolean isHashCodeValido(HttpServletRequest request) {
		String hash = request.getHeader("HashCode");
		Optional<DadosHash> dadosHash = dadosHashRepository.findByHash(hash);
		
		if (dadosHash.isPresent() && request.getMethod().equals("POST")  && request.getRequestURL().toString().contains("/investidor-pessoa")) {
			return true;
		}
		return false;
	}

}
