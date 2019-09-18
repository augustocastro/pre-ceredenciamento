package br.com.infobtc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Conta;
import br.com.infobtc.model.StatusConta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

	List<Conta>findByStatus(StatusConta status);
}
