package br.com.infobtc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.model.Status;

public interface ContratoInvestimentoRepository extends JpaRepository<ContratoInvestimento, Long> {

	Page<ContratoInvestimento> findByStatusContrato(Status statusContrato, Pageable paginacao);

	Page<ContratoInvestimento> findByStatusFinanceiro(Status statusFinanceiro, Pageable paginacao);

	Page<ContratoInvestimento> findByStatusContratoAndStatusFinanceiro(Status statusContrato, Status statusFinanceiro, Pageable paginacao);

}
