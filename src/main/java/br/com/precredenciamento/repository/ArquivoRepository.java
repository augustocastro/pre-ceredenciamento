package br.com.precredenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.precredenciamento.model.Arquivo;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, String> {


}
