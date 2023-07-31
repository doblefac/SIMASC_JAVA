package co.org.ccb.simasc.alertas.parametrizacion;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 
 * @author lvalbuena
 *
 */
@Singleton
@Startup
public class QuartzListener {
	private static final Logger logger = LogManager.getLogger(QuartzListener.class.getName());
	private static final String NAME_JOB = "simascJobB";
	private static final String GROUP_JOB = "jobsSimasc";
	private static final String NAME_TRIGGER = "triggerSimasc";
	

	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		logger.info("====================Inicio de singleton=====================" + new Date());
		//this.iniciacionDeAlertas();
		this.contextInitialized();
	}
	
	

	/**
	 * metodo encargando de orquetar las alertas de la aplicaicon
	 */
	public void iniciacionDeAlertas() { 


	}

	/**
	 * metodo de prueba para inciializar las alertas
	 */
	public void contextInitialized() {
		logger.info("----------------------------Inicio del contexto-----------"+ new Date());
		try {
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			JobKey jobKeyB = new JobKey(NAME_JOB, GROUP_JOB);
			 scheduler.deleteJob(jobKeyB);
			
			JobDetail jobB = JobBuilder.newJob(SimascJob.class).withIdentity(jobKeyB).requestRecovery()
					.build();

			Trigger triggerSimasc = TriggerBuilder.newTrigger().withIdentity(NAME_TRIGGER, GROUP_JOB)
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0/3 4-23 ? * * *"))
					.build();
			
			scheduler.start();			
			 scheduler.scheduleJob(jobB, triggerSimasc);
		} catch (SchedulerException e) {
			logger.error("Error: ", e);
		} 
	}	
	
}
