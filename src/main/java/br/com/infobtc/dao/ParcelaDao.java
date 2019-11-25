package br.com.infobtc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.infobtc.model.Repasse;
import br.com.infobtc.model.TipoRecebedor;

@Repository
public class ParcelaDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Repasse> buscarRepassePorParcela(Long id, TipoRecebedor tipoRecebedor) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT r FROM Parcela p ");
		query.append("JOIN p.repasses r ");
		query.append("WHERE r.tipoRepasse = 'REPASSE' ");
		query.append("AND p.id = :id ");
		query.append(String.format("AND %s ", tipoRecebedor != null ? "r.tipoRecebedor = :tipoRecebedor " : "1 = 1 "));

		TypedQuery<Repasse> typedQuery = manager.createQuery(query.toString(), Repasse.class);

		typedQuery.setParameter("id", id);
		
		if (tipoRecebedor != null) typedQuery.setParameter("tipoRecebedor", tipoRecebedor);

		return typedQuery.getResultList();
	}
}
