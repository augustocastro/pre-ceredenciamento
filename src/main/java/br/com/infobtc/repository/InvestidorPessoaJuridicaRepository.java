package br.com.infobtc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.InvestidorPessoaJuridica;
import br.com.infobtc.model.Status;

public interface InvestidorPessoaJuridicaRepository extends JpaRepository<InvestidorPessoaJuridica, Long> {

	List<InvestidorPessoaJuridica> findByStatusInvestidor(Status statusInvestidor);

}
