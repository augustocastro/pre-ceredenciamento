package br.com.infobtc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.infobtc.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
	
//	@Query("SELECT c FROM Contrato c WHERE (?2 >= c.dtInicio AND ?2 <= ?3 AND ?2 <= c.dtTermino AND c.dtTermino >= ?3) AND (c.statusContrato = 'APROVADO' AND c.statusFinanceiro = 'APROVADO') AND c.consultor.id = ?1 ORDER BY c.consultor.nome")
//	List<Contrato> findByIntervalDate(Long idConsultor, LocalDate dtInicio, LocalDate dtTermino);
//	
//	@Query("SELECT c FROM Contrato c WHERE (?2 >= c.dtInicio AND ?2 <= ?3 AND ?2 <= c.dtTermino AND c.dtTermino >= ?3) AND (c.statusContrato = 'APROVADO' AND c.statusFinanceiro = 'APROVADO') AND c.consultor.id = ?1 AND c.repassado = ?4 ORDER BY c.consultor.nome")
//	List<Contrato> findByIntervalDate(Long idConsultor, LocalDate dtInicio, LocalDate dtTermino, boolean repassado);
//	
//	@Query("SELECT c FROM Contrato c WHERE (?1 >= c.dtInicio AND ?1 <= ?2 AND ?1 <= c.dtTermino AND c.dtTermino >= ?2) AND (c.statusContrato = 'APROVADO' AND c.statusFinanceiro = 'APROVADO') AND c.repassado = ?3 ORDER BY c.consultor.nome")
//	List<Contrato> findByIntervalDate(LocalDate dtInicio, LocalDate dtTermino, boolean repassado);
//	
//	@Query("SELECT c FROM Contrato c WHERE (?1 >= c.dtInicio AND ?1 <= ?2 AND ?1 <= c.dtTermino AND c.dtTermino >= ?2) AND (c.statusContrato = 'APROVADO' AND c.statusFinanceiro = 'APROVADO') ORDER BY c.consultor.nome")
//	List<Contrato> findByIntervalDate(LocalDate dtInicio, LocalDate dtTermino);
	
	@Query("SELECT c FROM Contrato c WHERE (c.statusContrato = 'APROVADO' AND c.statusFinanceiro = 'APROVADO') AND c.consultor.id = ?1 ORDER BY c.consultor.nome")
	List<Contrato> findByIntervalDate(Long idConsultor);
	
	@Query("SELECT c FROM Contrato c WHERE (c.statusContrato = 'APROVADO' AND c.statusFinanceiro = 'APROVADO') AND c.consultor.id = ?1 AND c.repassado = ?2 ORDER BY c.consultor.nome")
	List<Contrato> findByIntervalDate(Long idConsultor, boolean repassado);
	
	@Query("SELECT c FROM Contrato c WHERE(c.statusContrato = 'APROVADO' AND c.statusFinanceiro = 'APROVADO') AND c.repassado = ?1 ORDER BY c.consultor.nome")
	List<Contrato> findByIntervalDate(boolean repassado);
	
	
	@Query("SELECT c FROM Contrato c WHERE(c.statusContrato = 'APROVADO' AND c.statusFinanceiro = 'APROVADO') ORDER BY c.consultor.nome")
	List<Contrato> findByIntervalDate();
//	@Query("SELECT c FROM Contrato c WHERE (c.statusContrato = 'APROVADO' AND c.statusFinanceiro = 'APROVADO') ORDER BY c.consultor.nome")
//	List<Contrato> findByIntervalDate(LocalDate dtInicio, LocalDate dtTermino);
	
	@Query("SELECT c FROM Contrato c WHERE c.dtCadastro BETWEEN ?1 AND ?2 AND (c.statusContrato = 'APROVADO' AND c.statusFinanceiro = 'APROVADO')")
	List<Contrato> buscarContratosSemana(LocalDate dateUmaSemanaAtras, LocalDate dataHoje);
}
