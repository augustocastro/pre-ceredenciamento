package br.com.infobtc.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.infobtc.model.Contrato;

@Repository
public class ContratoDao {
	
	@PersistenceContext
    private EntityManager manager;
	
	
	public List<Contrato> finfByInterval(LocalDate dtInicio, LocalDate dtTermino, Long idConsultor, boolean repassado) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT c FROM Contrato as c ");
		query.append("WHERE ((c.statusContrato = 'APROVADO' AND c.statusFinanceiro = 'APROVADO') ");
		query.append(String.format("AND %s) ", idConsultor != null ? "c.consultor.id = :idConsultor" : "1 = 1 "));
		query.append("AND (DAY(c.dtInicio) BETWEEN :dayDtInicio AND :dayDtTermino ");
		query.append("OR ((:monthDtTermino > :monthDtInicio OR :yearDtTermino > :yearDtInicio) AND (:dayDtTermino >= DAY(c.dtInicio) OR :dayDtInicio <= DAY(c.dtInicio))) ");
		query.append("AND c.dtTermino >= :dtTermino)");
		
		TypedQuery<Contrato> typedQuery = manager.createQuery(query.toString(),Contrato.class);
		typedQuery.setParameter("dtTermino", dtTermino);
		typedQuery.setParameter("dayDtInicio", dtInicio.getDayOfMonth());
		typedQuery.setParameter("dayDtTermino", dtTermino.getDayOfMonth());
		typedQuery.setParameter("monthDtInicio", dtInicio.getMonthValue());
		typedQuery.setParameter("monthDtTermino", dtTermino.getMonthValue());
		typedQuery.setParameter("yearDtInicio", dtInicio.getYear());
		typedQuery.setParameter("yearDtTermino", dtTermino.getYear());
		
		if (idConsultor != null) {
			typedQuery.setParameter("idConsultor", idConsultor);
		}
		
        return typedQuery.getResultList();
    }
}