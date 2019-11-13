package br.com.infobtc.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.infobtc.controller.vo.TotalContratoInvestidoresVolumeCapitalVo;

@Repository
public class RelatorioDao {
	
	@PersistenceContext
    private EntityManager manager;
	
	public TotalContratoInvestidoresVolumeCapitalVo calculaTotaisMensal() {
		String queryCountContratos = "SELECT COUNT(c.id) FROM Contrato c WHERE c.dtCadastro BETWEEN :start AND :end ";
		String queryCountInvestidores = "SELECT COUNT(i.id) FROM Investidor i WHERE i.dtCadastramento BETWEEN :start AND :end ";
		String querySomaVolumeCapital = "SELECT SUM(c.valor) FROM Contrato c WHERE c.dtCadastro BETWEEN :start AND :end ";
		
		Long totalContratos = (Long) executaQuery(queryCountContratos);
		Long totalInvestidores = (Long) executaQuery(queryCountInvestidores);
		BigDecimal somaValorCapital = (BigDecimal) executaQuery(querySomaVolumeCapital);		

		return new TotalContratoInvestidoresVolumeCapitalVo(totalContratos, totalInvestidores, somaValorCapital);
	}
	
	private Number executaQuery(String query) {
		LocalDate initial = LocalDate.now();
		LocalDate start = initial.withDayOfMonth(1);
		LocalDate end = initial.with(TemporalAdjusters.lastDayOfMonth());
		
		TypedQuery<Number> typedQuery = manager.createQuery(query, Number.class); 
		typedQuery.setParameter("start", start);
		typedQuery.setParameter("end", end);
		
		return typedQuery.getSingleResult();
	}
	
}
