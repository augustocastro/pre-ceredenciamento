package br.com.infobtc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infobtc.model.DadosHash;

public interface DadosHashRepository extends JpaRepository<DadosHash, Long>{

	Optional<DadosHash> findByHash(String hash);
}
