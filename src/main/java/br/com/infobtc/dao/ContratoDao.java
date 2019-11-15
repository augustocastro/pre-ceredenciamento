package br.com.infobtc.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.infobtc.controller.vo.ContratoConsultorInvestidorVo;
import br.com.infobtc.controller.vo.ContratoParcelaVo;
import br.com.infobtc.controller.vo.ParcelaVo;
import br.com.infobtc.model.StatusRepasse;

@Repository
public class ContratoDao {
	
	@PersistenceContext
    private EntityManager manager;
	
	public List<ContratoParcelaVo> consultarParcelasPorIntervaloEConsultor(LocalDate dtInicio, LocalDate dtTermino, Long idConsultor, StatusRepasse statusRepasse) {
		String campos = "c.investidor.nome, c.id, p.id, c.valor, c.dtInicio, c.dtTermino, p.data, p.parcela, c.quantidadeMeses, c.consultor";
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT NEW br.com.infobtc.controller.vo.ContratoParcelaVo("+campos+")");
		query.append("FROM Contrato c ");
		query.append("JOIN c.parcelas p ");
		query.append("LEFT JOIN c.rescisao r ");
		query.append("WHERE (r IS NULL OR (r IS NOT NULL AND r.status != 'APROVADO' )) ");
		query.append(String.format("AND %s ", dtInicio != null && dtTermino != null ? "p.data BETWEEN :dtInicio AND :dtTermino ": "1 = 1 "));
		query.append(String.format("AND %s ", idConsultor != null ? "c.consultor.id = :idConsultor ": "1 = 1 "));
		query.append(String.format("AND %s ", statusRepasse != null ? "p.status = :statusRepasse ":  "1 = 1 "));
		query.append("ORDER BY p.data ASC");
		TypedQuery<ContratoParcelaVo> typedQuery = manager.createQuery(query.toString(), ContratoParcelaVo.class);
		
		if (dtInicio != null && dtTermino != null) {
			typedQuery.setParameter("dtInicio", dtInicio);
			typedQuery.setParameter("dtTermino", dtTermino);
		}
		
		if (idConsultor != null && idConsultor != 0) typedQuery.setParameter("idConsultor", idConsultor);
		if (statusRepasse != null) typedQuery.setParameter("statusRepasse", statusRepasse);
	
        return typedQuery.getResultList();
    }
	
	public List<ContratoConsultorInvestidorVo> buscarRelacaoContratos(Long idConsultor, LocalDate dtInicio, LocalDate dtTermino) {
		String campos = "c.id, c.statusContrato, c.statusFinanceiro, c.investidor.nome, c.consultor.nome, c.dtCadastro, c.valor, c.dtInicio, c.dtTermino";

		StringBuilder query = new StringBuilder();
		query.append("SELECT NEW br.com.infobtc.controller.vo.ContratoConsultorInvestidorVo("+campos+") ");
		query.append("FROM Contrato c ");
//		query.append("JOIN c.parcelas p ");
		query.append("WHERE c.statusContrato != 'EM_ANALISE' AND c.statusFinanceiro != 'EM_ANALISE' ");
		query.append(String.format("AND %s ", dtInicio != null && dtTermino != null ? "p.data BETWEEN :dtInicio AND :dtTermino ": "1 = 1 "));
		query.append(String.format("AND %s ", idConsultor != null ? "c.consultor.id = :idConsultor ": "1 = 1 "));
//		query.append("ORDER BY p.data ASC");

		TypedQuery<ContratoConsultorInvestidorVo> typedQuery = manager.createQuery(query.toString(), ContratoConsultorInvestidorVo.class);
		
		if (dtInicio != null && dtTermino != null) {
			typedQuery.setParameter("dtInicio", dtInicio);
			typedQuery.setParameter("dtTermino", dtTermino);
		}
		
		if (idConsultor != null && idConsultor != 0) typedQuery.setParameter("idConsultor", idConsultor);
		
        return typedQuery.getResultList();
    }

	public List<ParcelaVo> buscarParcelas(Long idContrato, Boolean repassado) {
		String campos = "p.data, p.parcela, (CASE WHEN r.tipoRecebedor = 'INVESTIDOR' THEN true ELSE false END), (CASE WHEN r.tipoRecebedor = 'INVESTIDOR' THEN r.data ELSE null END), (CASE WHEN r != null THEN r.valor ELSE 0 END), p.contrato.id";
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT NEW br.com.infobtc.controller.vo.ParcelaVo("+campos+") ");
		query.append("FROM Parcela p ");
		query.append("JOIN p.contrato c ");
		query.append("LEFT JOIN p.repasses r ");
		query.append("WHERE c.statusContrato = 'APROVADO' AND c.statusFinanceiro = 'APROVADO' ");
		query.append(String.format("AND %s ", repassado != null && repassado == true ? "r.tipoRecebedor = 'INVESTIDOR' " :  "1 = 1 "));
		query.append(String.format("AND %s ", idContrato != null ? "p.contrato.id = :idContrato ": "1 = 1 "));
		query.append("ORDER BY p.data ASC");
		
		TypedQuery<ParcelaVo> typedQuery = manager.createQuery(query.toString(), ParcelaVo.class);

		if (idContrato != null && idContrato != 0) typedQuery.setParameter("idContrato", idContrato);
		
        return typedQuery.getResultList();
    }
	
}