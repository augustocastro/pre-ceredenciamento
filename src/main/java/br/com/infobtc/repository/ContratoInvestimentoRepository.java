package br.com.infobtc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.infobtc.model.ContratoInvestimento;

public interface ContratoInvestimentoRepository extends JpaRepository<ContratoInvestimento, Long> {
	
	Page<ContratoInvestimento>findByValid(boolean valid,  Pageable paginacao);
	
	@Modifying
	@Query("update ContratoInvestimento investimento set investimento.valid = :valid where investimento.id = :id")
	int validarInvestimento(Long id, boolean valid);	
}
