package br.com.precredenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.precredenciamento.model.Codigo;

@Repository
public interface CodigoRepository extends JpaRepository<Codigo, String> {

	public Codigo findByCodigo(String codigo);

}
