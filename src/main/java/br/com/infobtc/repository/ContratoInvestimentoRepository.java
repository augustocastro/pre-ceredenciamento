package br.com.infobtc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.infobtc.model.ContratoInvestimento;

public interface ContratoInvestimentoRepository extends JpaRepository<ContratoInvestimento, Long> {
	
	@Query("SELECT c FROM ContratoInvestimento as c WHERE c.valid2 = false and c.valid1 = :valid")
	Page<ContratoInvestimento>findByValid1(boolean valid,  Pageable paginacao);
	
	@Query("SELECT c FROM ContratoInvestimento as c WHERE c.valid1 = true and c.valid2 = :valid")
	Page<ContratoInvestimento>findByValid2(boolean valid,  Pageable paginacao);
	
//	@Modifying
//	@Query("update ContratoInvestimento investimento set investimento.valid = :valid where investimento.id = :id")
//	int validarInvestimento(Long id, boolean valid);	
}
