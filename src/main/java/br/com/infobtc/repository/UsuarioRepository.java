package br.com.infobtc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
}
