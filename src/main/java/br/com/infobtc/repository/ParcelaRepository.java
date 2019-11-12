package br.com.infobtc.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.infobtc.model.Parcela;
import br.com.infobtc.model.Repasse;
import br.com.infobtc.model.TipoRecebedor;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {

	@Query("SELECT r FROM Parcela p JOIN p.repasses r WHERE p.id = ?1 AND r.tipoRecebedor = ?2")
	Optional<Repasse> buscarRepassePorParcela(Long id, TipoRecebedor tipoRecebedor);

	@Query("SELECT p FROM Parcela p LEFT JOIN p.repasses r WHERE ?1 > p.data AND r.tipoRecebedor IS NULL")
	List<Parcela> buscarParcelasAtrasadas(LocalDate hoje);
}
