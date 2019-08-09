package br.com.infobtc.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureJob {

	@Bean
	public JobDetail apagaHashDetails() {
		return JobBuilder.newJob(ApagaHashJob.class).withIdentity("removeAllHash").storeDurably().build();
	}

	@Bean
	public Trigger apagaHashTrigger(JobDetail jobADetails) {
//		TESTE: Dispara de 2 em 2 segundos
//		"0/2 * * ? * * *"
//		"0 0 0 ? * * *"
		return TriggerBuilder.newTrigger().forJob(jobADetails).withIdentity("removeAllHash")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 ? * * *")).build();
	}
}
