package br.com.infobtc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Conta;
import br.com.infobtc.model.Status;

public interface ContaRepository extends JpaRepository<Conta, Long> {

	List<Conta>findByStatus(Status status);
}
