package br.com.infobtc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Consultor;

public interface ConsultorRepository extends JpaRepository<Consultor, Long> {

	Optional<Consultor> findByUsuarioId(Long id);
}
