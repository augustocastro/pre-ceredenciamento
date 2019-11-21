package br.com.infobtc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.infobtc.model.Parcela;
import br.com.infobtc.model.Repasse;

public interface ParcelaRepository extends JpaRepository<Parcela, Long>{

	@Query("SELECT r FROM Parcela p INNER JOIN Repasse r ON p.repasse = r WHERE p.id = ?1")
	Optional<Repasse> buscarRepassePorParcela(Long id);
}
