package br.com.infobtc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.infobtc.dao.result.UsuarioPerfilCustomResult;

@Repository
public class PerfilDao {

	@PersistenceContext
    private EntityManager manager;
	
	public List<UsuarioPerfilCustomResult> buscarUsuariosAgrupandoPorPerfil() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT NEW br.com.infobtc.dao.result.UsuarioPerfilCustomResult(c.nome, u.email, p.nome, p.porcentagem) ");
		query.append("FROM Usuario u ");
		query.append("JOIN u.perfis p ");
		query.append("INNER JOIN Consultor c ON c.usuario.id = u.id ");
		
		TypedQuery<UsuarioPerfilCustomResult> typedQuery = manager.createQuery(query.toString(), UsuarioPerfilCustomResult.class);
        return typedQuery.getResultList();
    }
	
}




