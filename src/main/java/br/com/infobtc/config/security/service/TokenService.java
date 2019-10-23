package br.com.infobtc.config.security.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.infobtc.controller.dto.UsuarioTokenDto;
import br.com.infobtc.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${infobtc.jwt.expiration}")
	private String expiration;
	
	@Value("${infobtc.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();		
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder().setIssuer("API InfoBTC")
				.claim("usuario", new UsuarioTokenDto(logado))
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	public String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
	
	public String encondePassord(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
	
	public boolean matchesPassord(String rawPassword, String encodedPassword) {
		return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
	}

}
