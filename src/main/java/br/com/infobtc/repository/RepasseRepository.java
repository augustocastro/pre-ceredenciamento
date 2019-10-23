package br.com.infobtc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Repasse;

public interface RepasseRepository extends JpaRepository<Repasse, Long> {

	List<Repasse> findByContratoId(Long idContrato);
}
