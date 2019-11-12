package br.com.infobtc.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.infobtc.controller.vo.RepasseParcelaVo;
import br.com.infobtc.model.StatusRepasse;
import br.com.infobtc.model.TipoRecebedor;

@Repository
public class RepasseDao {

	@PersistenceContext
    private EntityManager manager;
	
	public List<RepasseParcelaVo> buscarRepassseAplicandoFiltros(TipoRecebedor tipoRecebedor, StatusRepasse statusRepasse, LocalDate dtInicio, LocalDate dtTermino, Long contratoId) {
		StringBuilder query = new StringBuilder();
		
		String campos = "repasse.tipoRecebedor, parcela.status, parcela.parcela, repasse.data, parcela.data, consultor.nome, investidor.nome, contrato.id, parcela.id, contrato.valor";
		
		query.append("SELECT NEW br.com.infobtc.controller.vo.RepasseParcelaVo("+campos+") ");
		query.append("FROM Parcela parcela ");
		query.append("LEFT JOIN parcela.repasses repasse ");
		query.append("LEFT JOIN repasse.usuario usuario ");
		query.append("LEFT JOIN Consultor consultor ON consultor.usuario.id = usuario.id ");
		query.append("INNER JOIN Contrato contrato ON contrato.id = parcela.contrato.id ");
		query.append("INNER JOIN Investidor investidor ON investidor.id = contrato.investidor.id ");
		query.append("WHERE 1 = 1");
		query.append(String.format("AND %s ", contratoId != null ? "contrato.id = :contratoId ": "1 = 1 "));
		query.append(String.format("AND %s ", tipoRecebedor != null ? "repasse.tipoRecebedor = :tipoRecebedor": "1 = 1 "));
		query.append(String.format("AND %s ", statusRepasse != null ? "parcela.status= :statusRepasse": "1 = 1 "));
		query.append(String.format("AND %s ", dtInicio != null && dtTermino != null ? "parcela.data BETWEEN :dtInicio AND :dtTermino ": "1 = 1 "));
		query.append("ORDER BY parcela.parcela");
		
		TypedQuery<RepasseParcelaVo> typedQuery = manager.createQuery(query.toString(), RepasseParcelaVo.class);
        
		if (contratoId != null) typedQuery.setParameter("contratoId", contratoId);
		if (tipoRecebedor != null) typedQuery.setParameter("tipoRecebedor", tipoRecebedor);
		if (statusRepasse != null) typedQuery.setParameter("statusRepasse", statusRepasse);
		
		if (dtInicio != null && dtTermino != null) {
			typedQuery.setParameter("dtInicio", dtInicio);
			typedQuery.setParameter("dtTermino", dtTermino);
		}
		
		return typedQuery.getResultList();
	}
}
