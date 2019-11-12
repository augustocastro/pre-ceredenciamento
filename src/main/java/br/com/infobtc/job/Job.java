package br.com.infobtc.job;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.infobtc.model.StatusConta;
import br.com.infobtc.model.StatusRepasse;
import br.com.infobtc.repository.ContaRepository;
import br.com.infobtc.repository.DadosHashRepository;
import br.com.infobtc.repository.ParcelaRepository;

public class Job implements org.quartz.Job {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private DadosHashRepository dadosHashRepository;

	@Autowired
	private ParcelaRepository parcelaRepository; 
	
	@Override
	public void execute(JobExecutionContext context) {
		
		// CONTAS
		System.out.println("##########################################");
		System.out.println("ATRASANDO CONTAS: " + LocalDateTime.now().toString());
		
		contaRepository.buscaContasAtrasadas(LocalDate.now()).forEach(conta -> {
			System.out.println("Conta: " + conta.getId());
			conta.setStatus(StatusConta.EM_ATRASO);
			contaRepository.save(conta);
		});
		
		System.out.println("##########################################");
		
		System.out.println();
		
		// PARCELAS
		System.out.println("##########################################");
		System.out.println("ATRASANDO PARCELAS: " + LocalDateTime.now().toString());
		
		parcelaRepository.buscarParcelasAtrasadas(LocalDate.now()).forEach(parcela -> {
			System.out.println("Parcela: " + parcela.getId());
			parcela.setStatus(StatusRepasse.A_EXECUTAR);
			parcelaRepository.save(parcela);
		});

		System.out.println("##########################################");
		
		System.out.println();
		
		// HASHS
    	System.out.println("##########################################");
    	System.out.println("APAGANDO HASHS: " + LocalDateTime.now().toString());
    	dadosHashRepository.deleteAll();
    	System.out.println("##########################################");
	}

}
