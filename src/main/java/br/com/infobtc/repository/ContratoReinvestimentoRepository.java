package br.com.infobtc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.ContratoReinvestimento;
import br.com.infobtc.model.Status;

public interface ContratoReinvestimentoRepository extends JpaRepository<ContratoReinvestimento, Long> {

	Page<ContratoReinvestimento> findByStatusContrato(Status statusContrato, Pageable paginacao);

	Page<ContratoReinvestimento> findByStatusFinanceiro(Status statusFinanceiro, Pageable paginacao);

	Page<ContratoReinvestimento> findByStatusContratoAndStatusFinanceiro(Status statusContrato, Status statusFinanceiro, Pageable paginacao);
}
