package br.com.infobtc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Repasse;

public interface RepasseRepository extends JpaRepository<Repasse, Long> {

	Optional<Repasse> findByContratoId(Long idContrato);
}
