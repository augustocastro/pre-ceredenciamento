package br.com.infobtc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.infobtc.controller.vo.InvestidorEnderecoVo;

@Repository
public class InvestidorDao {

	@PersistenceContext
    private EntityManager manager;

	public List<InvestidorEnderecoVo> consultarTodos() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT NEW br.com.infobtc.controller.vo.InvestidorEnderecoVo(i.tipo, i.statusInvestidor, i.dtCadastramento, ipf.dtNascimento, i.nome, ipf.cpf, ipj.cnpj, i.telefone, e.endereco) ");
		query.append("FROM Investidor i ");
		query.append("INNER JOIN Endereco e ON e.id = i.endereco.id ");
		query.append("LEFT JOIN  InvestidorPessoaFisica ipf ON ipf.id = i.id ");
		query.append("LEFT JOIN InvestidorPessoaJuridica ipj ON ipj.id = i.id ");
		
		TypedQuery<InvestidorEnderecoVo> typedQuery = manager.createQuery(query.toString(),InvestidorEnderecoVo.class);
		
        return typedQuery.getResultList();
    }
}
