package br.com.infobtc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.Rescisao;
import br.com.infobtc.model.Status;

public interface RescisaoRepository extends JpaRepository<Rescisao, Long> {

	List<Rescisao> findByStatus(Status status);
	
}
