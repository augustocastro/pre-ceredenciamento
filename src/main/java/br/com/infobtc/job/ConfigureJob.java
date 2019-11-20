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
	public JobDetail JobsDetails() {
		return JobBuilder.newJob(Job.class).withIdentity("jobs").storeDurably().build();
	}

	@Bean
	public Trigger JobsTrigger(JobDetail jobADetails) {
		return TriggerBuilder.newTrigger().forJob(jobADetails).withIdentity("jobs")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 ? * * *")).build();
	}

//	@Bean(name = "dois")
//	@Qualifier("dois")
//	@Primary
//	public JobDetail apagaHashDetails() {
//		return JobBuilder.newJob(ApagaHashJob.class).withIdentity("apagaHashs").storeDurably().build();
//	}
//
//	@Bean
//	public Trigger apagaHashTrigger(JobDetail jobADetails) {
//		return TriggerBuilder.newTrigger().forJob(jobADetails).withIdentity("apagaHashs")
//				.withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 ? * * *")).build();
//	}

}
