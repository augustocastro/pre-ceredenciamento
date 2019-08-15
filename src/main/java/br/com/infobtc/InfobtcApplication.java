package br.com.infobtc;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.infobtc.model.Banco;
import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.model.InvestidorPessoaJuridica;
import br.com.infobtc.repository.ContratoInvestimentoRepository;
import br.com.infobtc.repository.InvestidorRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class InfobtcApplication implements CommandLineRunner {
	
	
	@Autowired
	private InvestidorRepository investidorRepository; 
	
	@Autowired ContratoInvestimentoRepository contratoInvestimentoRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(InfobtcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		InvestidorPessoaJuridica investidor = new InvestidorPessoaJuridica();
		
		ContratoInvestimento contratoInvestimento = new ContratoInvestimento();
		contratoInvestimento.setInvestidor(investidor);
		contratoInvestimento.setDtInicio(LocalDate.now());
		contratoInvestimento.setDtTermino(LocalDate.now());
		contratoInvestimento.setQuantidadeMeses(10);
		contratoInvestimento.setValor(new BigDecimal(10));
		contratoInvestimento.setBanco(new Banco());
		
		investidorRepository.save(investidor);
		contratoInvestimentoRepository.save(contratoInvestimento);
	}
	
}
