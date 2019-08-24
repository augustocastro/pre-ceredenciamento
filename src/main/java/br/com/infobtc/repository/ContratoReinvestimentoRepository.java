package br.com.infobtc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.infobtc.model.ContratoReinvestimento;

public interface ContratoReinvestimentoRepository extends JpaRepository<ContratoReinvestimento, Long> {

	@Query("SELECT c FROM ContratoReinvestimento as c WHERE c.valid2 = false and c.valid1 = :valid")
	Page<ContratoReinvestimento>findByValid1(boolean valid,  Pageable paginacao);
	
	@Query("SELECT c FROM ContratoReinvestimento as c WHERE c.valid1 = true and c.valid2 = :valid")
	Page<ContratoReinvestimento>findByValid2(boolean valid,  Pageable paginacao);
}
