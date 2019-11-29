package br.com.infobtc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.infobtc.controller.vo.InvestimentoConsultorBanco;


@Repository
public class ContratoInvestimentoDao {
	
	@PersistenceContext
    private EntityManager manager;
	
	public List<InvestimentoConsultorBanco> buscarInvestimentos() {
		String campos = "ci.dtInicio, ci.dtTermino, i.nome, b.instituicaoFinanceira, b.titular, ipf.nome, ipj.nome, ipf.cpf, ipj.cnpj, b.cpfOrCnpjTitular, b.conta, ci.tipoRendimento, co.nome";
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT NEW br.com.infobtc.controller.vo.InvestimentoConsultorBanco("+campos+") ");
		query.append("FROM ContratoInvestimento ci ");
		query.append("JOIN ci.investidor i ");
		query.append("JOIN ci.banco b ");
		query.append("JOIN ci.consultor co ");
		query.append("LEFT JOIN  InvestidorPessoaFisica ipf ON ipf.id = i.id ");
		query.append("LEFT JOIN InvestidorPessoaJuridica ipj ON ipj.id = i.id ");
		query.append("WHERE ci.statusContrato = 'APROVADO' AND ci.statusFinanceiro = 'APROVADO'");
		
		TypedQuery<InvestimentoConsultorBanco> typedQuery = manager.createQuery(query.toString(), InvestimentoConsultorBanco.class);
        return typedQuery.getResultList();
	}
}
