package br.com.infobtc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

}
