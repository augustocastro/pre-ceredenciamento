package br.com.infobtc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Repasse;
import br.com.infobtc.model.TipoRepasse;

public interface RepasseRepository extends JpaRepository<Repasse, Long> {

	List<Repasse> findByContratoId(Long idContrato);
	
	List<Repasse> findByContratoIdAndTipoRepasse(Long idContrato, TipoRepasse tipoRepasse);
	
	List<Repasse> findByTipoRepasse(TipoRepasse tipoRepasse);
}
