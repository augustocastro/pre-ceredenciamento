package br.com.infobtc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.infobtc.model.Parcela;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {

	@Query("SELECT p FROM Parcela p LEFT JOIN p.repasses r WHERE ?1 > p.data AND r.tipoRecebedor IS NULL")
	List<Parcela> buscarParcelasAtrasadas(LocalDate hoje);
}
