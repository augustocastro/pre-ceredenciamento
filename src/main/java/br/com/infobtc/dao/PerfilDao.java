package br.com.infobtc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.infobtc.controller.vo.UsuarioPerfilPorcentagemVo;
import br.com.infobtc.controller.vo.UsuarioPerfilVo;

@Repository
public class PerfilDao {

	@PersistenceContext
    private EntityManager manager;
	
	public List<UsuarioPerfilVo> buscarUsuariosAgrupandoPorPerfil() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT NEW br.com.infobtc.controller.vo.UsuarioPerfilVo(p.nome, u.email, c.nome, p.porcentagem) ");
		query.append("FROM Usuario u ");
		query.append("JOIN u.perfis p ");
		query.append("INNER JOIN Consultor c ON c.usuario.id = u.id ");
		
		TypedQuery<UsuarioPerfilVo> typedQuery = manager.createQuery(query.toString(), UsuarioPerfilVo.class);
        return typedQuery.getResultList();
    }
	
	public List<UsuarioPerfilPorcentagemVo> buscarRelacaoPerfis() {
		StringBuilder query = new StringBuilder();
		
		String selectInterno = "SELECT COUNT(p.id) FROM Usuario u JOIN u.perfis p)";
		String campos = "perfil, COUNT(perfil.id), (COUNT(perfil.id)*100/("+selectInterno+")";

		query.append("SELECT NEW br.com.infobtc.controller.vo.UsuarioPerfilPorcentagemVo("+campos+") ");
		query.append("FROM Usuario usuario ");
		query.append("JOIN usuario.perfis perfil ");
		query.append("GROUP BY perfil.id ");
		
		TypedQuery<UsuarioPerfilPorcentagemVo> typedQuery = manager.createQuery(query.toString(), UsuarioPerfilPorcentagemVo.class);
        return typedQuery.getResultList();
    }
		
}