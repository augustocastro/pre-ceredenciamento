package br.com.infobtc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.ContratoInvestimento;

public interface ContratoInvestimentoRepository extends JpaRepository<ContratoInvestimento, Long> {

	Page<ContratoInvestimento> findByValid1(boolean valid1, Pageable paginacao);

	Page<ContratoInvestimento> findByValid2(boolean valid2, Pageable paginacao);

	Page<ContratoInvestimento> findByValid1AndValid2(boolean valid1, boolean valid2, Pageable paginacao);
}
