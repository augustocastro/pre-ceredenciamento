package br.com.precredenciamento.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UsuarioSMADao {

	@Autowired
	@Qualifier("dataSourceSMA") 
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String buscarUsuarioPorNome() {
		String nome = jdbcTemplate.queryForObject("SELECT NOME FROM GE_USUAR WHERE MATRICULA = 3662", String.class);
		return nome;
	}
	
}
