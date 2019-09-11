package br.com.infobtc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.ContratoReinvestimento;

public interface ContratoReinvestimentoRepository extends JpaRepository<ContratoReinvestimento, Long> {

	Page<ContratoReinvestimento> findByValid1(boolean valid1, Pageable paginacao);

	Page<ContratoReinvestimento> findByValid2(boolean valid2, Pageable paginacao);

	Page<ContratoReinvestimento> findByValid1AndValid2(boolean valid1, boolean valid2, Pageable paginacao);
}
