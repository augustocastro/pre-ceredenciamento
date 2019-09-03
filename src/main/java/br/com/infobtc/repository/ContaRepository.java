package br.com.infobtc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
