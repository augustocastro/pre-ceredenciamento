package br.com.infobtc.job;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.infobtc.model.StatusConta;
import br.com.infobtc.repository.ContaRepository;
import br.com.infobtc.repository.DadosHashRepository;

public class Job implements org.quartz.Job {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private DadosHashRepository dadosHashRepository;

	@Override
	public void execute(JobExecutionContext context) {
		System.out.println("##########################################");
		System.out.println("ATRASANDO CONTAS: " + LocalDateTime.now().toString());
		
		contaRepository.buscaContasAtrasadas(LocalDate.now()).forEach(conta -> {
			System.out.println("Conta: " + conta.getId());
			conta.setStatus(StatusConta.EM_ATRASO);
			contaRepository.save(conta);
		});

		System.out.println("##########################################");
		
    	System.out.println("##########################################");
    	System.out.println("APAGANDO HASHS: " + LocalDateTime.now().toString());
    	dadosHashRepository.deleteAll();
    	System.out.println("##########################################");
	}

}
