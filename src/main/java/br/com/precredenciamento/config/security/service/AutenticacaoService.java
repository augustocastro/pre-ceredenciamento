package br.com.precredenciamento.config.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

//	@Autowired
//	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		
//		if (usuario.isPresent()) {
//			return usuario.get();
//		}
//		
//		throw new UsernameNotFoundException("Dados Inválidos!");
		
		return null;
	}
	
}
