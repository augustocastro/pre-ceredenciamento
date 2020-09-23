package br.com.precredenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.precredenciamento.model.UsuarioExterno;

@Repository
public interface UsuarioExternoRepository extends JpaRepository<UsuarioExterno, Long> {

	public UsuarioExterno findByCpf(String cpf);
	
	public UsuarioExterno findByCpfAndId(String cpf, Long id);
}
