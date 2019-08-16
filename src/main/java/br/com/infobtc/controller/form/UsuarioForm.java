package br.com.infobtc.controller.form;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.infobtc.model.Perfil;
import br.com.infobtc.model.Usuario;
import br.com.infobtc.repository.PerfilRepository;
import br.com.infobtc.repository.UsuarioRepository;
import javassist.NotFoundException;

public class UsuarioForm {
	
	@NotNull
	@NotEmpty
	private String email;

	@NotNull
	@NotEmpty
	private String senha;

	@NotNull
	private List<Long> perfis;

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public List<Long> getPerfis() {
		return perfis;
	}

	public void setarPropriedades(Usuario usuario, PerfilRepository perfilRepository) throws NotFoundException {
		usuario.setEmail(email);
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		usuario.setPerfis(setarPerfis(perfilRepository));
	}

	private Set<Perfil> setarPerfis(PerfilRepository perfilRepository) throws NotFoundException {		
		Set<Perfil> perfis = new HashSet<Perfil>();
		
		for (Long id : this.perfis) {
			Optional<Perfil> perfil = perfilRepository.findById(id);
			
			if (perfil.isPresent()) {
				perfis.add(perfil.get());
			} else {
				throw new NotFoundException(String.format("O perfil de id \"%s\" não foi encontrado.", id));
			}
		}

		return perfis;
	}

	public void atualizar(Long id, UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) throws NotFoundException {
		Usuario usuario = usuarioRepository.getOne(id);
		usuario.setEmail(email);
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		usuario.setPerfis(setarPerfis(perfilRepository));
	}
}
