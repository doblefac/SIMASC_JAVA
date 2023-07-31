package co.org.ccb.simasc.alertas.parametrizacion;


import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import co.org.ccb.simasc.alertas.ejecucion.SimascJobEjb;

/**
 * Clase que se ejecuta basado en la programacion del Job de Quartz
 * @author lvalbuena
 *
 */
public class SimascJob implements Job {

	private static final Logger logger = LogManager.getLogger(SimascJob.class.getName());
	/**
	 * Clase de negocio encargada de orquestar las tareas del sistema
	 */
	private SimascJobEjb simascJobEjb;
	
	
	/**
	 * 
	 */
	public SimascJob() {
		this.instanciarEjb();
		
	}

	/**
	 * Metodo que se dispara basado en la programacion del Job de Quartz
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("Ejecucion de la instancia" + new Date());
		this.simascJobEjb.ejecutarProcesos();

	}
	
	/**
	 * Realiza la instancia de la clase EJB a traves de JDNI
	 */	
	private void instanciarEjb() {	
		try {
			Context ctx = new InitialContext();
			simascJobEjb = (SimascJobEjb) ctx.lookup("java:module/SimascJobEjb");
		} catch(NamingException  e) {
			logger.error("Error: ", e);			
		}		
	}
		
	

}
