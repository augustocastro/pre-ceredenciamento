package br.com.infobtc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Banco;

public interface BancoRepository extends JpaRepository<Banco, Long> {

}
