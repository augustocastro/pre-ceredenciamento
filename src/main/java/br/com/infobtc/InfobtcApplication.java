package br.com.infobtc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.infobtc.repository.ContratoInvestimentoRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class InfobtcApplication implements CommandLineRunner {
	
	@Autowired ContratoInvestimentoRepository contratoInvestimentoRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(InfobtcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("RODANDO");
	}
	
}
