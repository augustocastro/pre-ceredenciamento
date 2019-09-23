package br.com.infobtc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.InvestidorPessoaFisica;

public interface InvestidorPessoaFisicaRepository extends JpaRepository<InvestidorPessoaFisica, Long> {

	List<InvestidorPessoaFisica> findByAprovado(boolean aprovado);

}
