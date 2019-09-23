package br.com.infobtc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.InvestidorPessoaJuridica;

public interface InvestidorPessoaJuridicaRepository extends JpaRepository<InvestidorPessoaJuridica, Long> {

	List<InvestidorPessoaJuridica> findByAprovado(boolean aprovado);

}
