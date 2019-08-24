package br.com.infobtc.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.infobtc.config.security.service.TokenService;
import br.com.infobtc.model.Usuario;
import br.com.infobtc.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = tokenService.recuperarToken(request);
		boolean valido = tokenService.isTokenValido(token);

		if (valido) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getUsuario(token);
		Usuario usuario = usuarioRepository.findById(idUsuario).get();

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
