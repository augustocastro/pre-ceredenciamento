package br.com.infobtc.job;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.infobtc.repository.DadosHashRepository;

public class ApagaHashJob implements Job {

    @Autowired
    private DadosHashRepository dadosHashRepository;

    @Override
    public void execute(JobExecutionContext context) {
    	System.out.println("##########################################");
    	System.out.println("APAGANDO TODOS OS HASH: " + LocalDateTime.now().toString());
    	System.out.println("##########################################");
    	
    	dadosHashRepository.deleteAll();
    }
}
