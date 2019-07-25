package br.com.infobtc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
