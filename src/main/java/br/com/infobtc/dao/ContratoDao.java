package br.com.infobtc.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.infobtc.controller.vo.ContratoConsultorInvestidorVo;
import br.com.infobtc.controller.vo.ContratoParcelaVo;

@Repository
public class ContratoDao {
	
	@PersistenceContext
    private EntityManager manager;
	
	public List<ContratoParcelaVo> consultarParcelasPorIntervaloEConsultor(LocalDate dtInicio, LocalDate dtTermino, Long idConsultor, boolean repassado) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT NEW br.com.infobtc.controller.vo.ContratoParcelaVo(c.investidor.nome, c.id, p.id, c.valor, c.dtInicio, c.dtTermino, p.data, p.parcela, c.quantidadeMeses, c.consultor)");
		query.append("FROM Contrato c ");
		query.append("JOIN c.parcelas p ");
		query.append("WHERE p.repasse IS NULL AND c.statusContrato = 'APROVADO' AND c.statusFinanceiro = 'APROVADO' ");
		query.append(String.format("AND %s ", dtInicio != null && dtTermino != null ? "p.data BETWEEN :dtInicio AND :dtTermino ": "1 = 1 "));
		query.append(String.format("AND %s ", idConsultor != null ? "c.consultor.id = :idConsultor ": "1 = 1 "));
		
		TypedQuery<ContratoParcelaVo> typedQuery = manager.createQuery(query.toString(), ContratoParcelaVo.class);
		
		if (dtInicio != null && dtTermino != null) {
			typedQuery.setParameter("dtInicio", dtInicio);
			typedQuery.setParameter("dtTermino", dtTermino);
		}
		
		if (idConsultor != null && idConsultor != 0) {
			typedQuery.setParameter("idConsultor", idConsultor);
		}

        return typedQuery.getResultList();
    }
	
	public List<ContratoConsultorInvestidorVo> consultarFiltrandoPorConsultorEIntervalo(Long idConsultor, LocalDate dtInicio, LocalDate dtTermino) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT NEW br.com.infobtc.controller.vo.ContratoConsultorInvestidorVo(c.id, c.statusContrato, c.statusFinanceiro, c.investidor.nome, c.consultor.nome, c.dtCadastro, c.valor, c.dtInicio, c.dtTermino) ");
		query.append("FROM Contrato c ");
		query.append("JOIN c.parcelas p ");
		query.append("WHERE c.statusContrato != 'EM_ANALISE' AND c.statusFinanceiro != 'EM_ANALISE' ");
		query.append(String.format("AND %s ", dtInicio != null && dtTermino != null ? "p.data BETWEEN :dtInicio AND :dtTermino ": "1 = 1 "));
		query.append(String.format("AND %s ", idConsultor != null ? "c.consultor.id = :idConsultor ": "1 = 1 "));
		
		TypedQuery<ContratoConsultorInvestidorVo> typedQuery = manager.createQuery(query.toString(), ContratoConsultorInvestidorVo.class);
		
		if (dtInicio != null && dtTermino != null) {
			typedQuery.setParameter("dtInicio", dtInicio);
			typedQuery.setParameter("dtTermino", dtTermino);
		}
		
		if (idConsultor != null && idConsultor != 0) {
			typedQuery.setParameter("idConsultor", idConsultor);
		}
		
        return typedQuery.getResultList();
    }
}