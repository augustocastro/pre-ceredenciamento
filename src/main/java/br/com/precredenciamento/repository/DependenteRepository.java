package br.com.precredenciamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.precredenciamento.model.Dependente;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Long> {
	
	public List<Dependente> findByTitularId(Long id);
}
