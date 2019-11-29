package br.com.infobtc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.infobtc.controller.vo.RepasseConsultorVo;
import br.com.infobtc.model.TipoRecebedor;

@Repository
public class ParcelaDao {

	@PersistenceContext
	private EntityManager manager;

//	public List<Repasse> buscarRepassePorParcela(Long id, TipoRecebedor tipoRecebedor) {
//		StringBuilder query = new StringBuilder();
//		query.append("SELECT r FROM Parcela p ");
//		query.append("JOIN p.repasses r ");
//		query.append("WHERE r.tipoRepasse = 'REPASSE' ");
//		query.append("AND p.id = :id ");
//		query.append(String.format("AND %s ", tipoRecebedor != null ? "r.tipoRecebedor = :tipoRecebedor " : "1 = 1 "));
//
//		TypedQuery<Repasse> typedQuery = manager.createQuery(query.toString(), Repasse.class);
//
//		typedQuery.setParameter("id", id);
//		
//		if (tipoRecebedor != null) typedQuery.setParameter("tipoRecebedor", tipoRecebedor);
//
//		return typedQuery.getResultList();
//	}
	
	public List<RepasseConsultorVo> buscarRepassePorParcela(Long id, TipoRecebedor tipoRecebedor) {
		String campos = "r.valor, r.observacao, r.anexo, r.status, r.parcela.contrato.id, r.parcela.id, r.data, r.tipoRecebedor, r.tipoRepasse, r.recebedor, c.nome";
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT NEW br.com.infobtc.controller.vo.RepasseConsultorVo("+campos+") ");
		query.append("FROM Parcela p ");
		query.append("JOIN p.repasses r ");
		query.append("INNER JOIN Consultor c ON c.usuario.id = r.usuario.id ");
		query.append("WHERE r.tipoRepasse = 'REPASSE' ");
		query.append("AND p.id = :id ");
		query.append(String.format("AND %s ", tipoRecebedor != null ? "r.tipoRecebedor = :tipoRecebedor " : "1 = 1 "));

		TypedQuery<RepasseConsultorVo> typedQuery = manager.createQuery(query.toString(), RepasseConsultorVo.class);

		typedQuery.setParameter("id", id);
		
		if (tipoRecebedor != null) typedQuery.setParameter("tipoRecebedor", tipoRecebedor);

		return typedQuery.getResultList();
	}
}
