package br.com.infobtc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

}
