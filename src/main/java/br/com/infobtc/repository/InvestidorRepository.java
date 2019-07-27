package br.com.infobtc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Investidor;

public interface InvestidorRepository extends JpaRepository<Investidor, Long> {

}
