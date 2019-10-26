package br.com.infobtc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.infobtc.controller.vo.FornecedorEnderecoVo;

@Repository
public class FornecedorDao {

	
	@PersistenceContext
    private EntityManager manager;

	public List<FornecedorEnderecoVo> consultarTodos() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT NEW br.com.infobtc.controller.vo.FornecedorEnderecoVo(f.nome, f.cnpj, f.telefone, e.endereco, f.dtCadastramento) ");
		query.append("FROM Fornecedor f ");
		query.append("INNER JOIN Endereco e ON e.id = f.endereco.id ");
		
		TypedQuery<FornecedorEnderecoVo> typedQuery = manager.createQuery(query.toString(), FornecedorEnderecoVo.class);
		
        return typedQuery.getResultList();
    }
}
