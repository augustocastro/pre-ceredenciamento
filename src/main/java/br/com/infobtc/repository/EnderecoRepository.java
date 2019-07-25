package br.com.infobtc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
