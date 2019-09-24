package br.com.infobtc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.infobtc.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

	@Query("SELECT c FROM Contrato c WHERE month(c.dtInicio) = month(current_date()) AND year(c.dtInicio) = year(current_date()) AND (day(c.dtInicio) < day(current_date()) OR day(c.dtInicio) = day(current_date())) AND (c.valid1 = true AND c.valid2 = true)")
	List<Contrato> getThisMonth();

	@Query("SELECT c FROM Contrato c WHERE c.dtInicio BETWEEN ?1 AND ?2 AND (c.valid1 = true AND c.valid2 = true)")
	List<Contrato> findByIntervalDate(LocalDate dtInicio, LocalDate dtTermino);
}
