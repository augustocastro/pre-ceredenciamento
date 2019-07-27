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

	public void setarPropriedades(Usuario usuario, PerfilRepository perfilRepository) {
		Set<Perfil> perfis = new HashSet<Perfil>();
		setarPerfis(perfis, perfilRepository);
		
		usuario.setEmail(email);
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		usuario.setPerfis(perfis);
	}

	private Set<Perfil> setarPerfis(Set<Perfil> perfis, PerfilRepository perfilRepository) {		
		for (Long id : this.perfis) {
			Optional<Perfil> perfil = perfilRepository.findById(id);
			if (perfil.isPresent()) {
				perfis.add(perfil.get());
			}
		}

		return perfis;
	}

	public void atualizar(Long id, UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) {
		Usuario usuario = usuarioRepository.getOne(id);
		usuario.setEmail(email);
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		setarPerfis(usuario.getPerfis(), perfilRepository);
	}
}
