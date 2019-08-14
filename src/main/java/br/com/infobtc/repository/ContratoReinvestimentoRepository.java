package br.com.infobtc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.ContratoReinvestimento;

public interface ContratoReinvestimentoRepository extends JpaRepository<ContratoReinvestimento, Long> {

	Page<ContratoReinvestimento>findByValid(boolean valid,  Pageable paginacao);
}
