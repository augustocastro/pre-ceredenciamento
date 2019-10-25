package br.com.infobtc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.infobtc.controller.vo.UsuarioPerfilVo;

@Repository
public class PerfilDao {

	@PersistenceContext
    private EntityManager manager;
	
	public List<UsuarioPerfilVo> buscarUsuariosAgrupandoPorPerfil() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT NEW br.com.infobtc.controller.vo.UsuarioPerfilVo(c.nome, u.email, p.nome, p.porcentagem) ");
		query.append("FROM Usuario u ");
		query.append("JOIN u.perfis p ");
		query.append("INNER JOIN Consultor c ON c.usuario.id = u.id ");
		
		TypedQuery<UsuarioPerfilVo> typedQuery = manager.createQuery(query.toString(), UsuarioPerfilVo.class);
        return typedQuery.getResultList();
    }
	
}




