package br.com.infobtc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.infobtc.model.Conta;
import br.com.infobtc.model.StatusConta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

	List<Conta> findByStatus(StatusConta status);

	List<Conta> findByDtPagamento(LocalDate dtPagamento);

	List<Conta> findByDtPagamentoBetween(LocalDate localInicio, LocalDate localFim);
	
	@Query("SELECT c FROM Conta c WHERE (c.dtVencimento BETWEEN ?1 AND ?2) AND (c.valorPago < c.valorTotal)")
	List<Conta> buscarContasPedentesSemana(LocalDate dataHoje, LocalDate dateDaquiUmaUmaSemana);
}
