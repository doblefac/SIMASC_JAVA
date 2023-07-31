package co.org.ccb.simasc.alertas.ejecucion;

import java.util.Date;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.integracion.manejadores.ManejadorFalloAlerta;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IOrquestadorAlertasFacade;

/**
 * EJB de negocio encargado de ejecutar los procesos de negocio
 * correspondiente a cada una de las tareas programadas
 * @author lvalbuena
 *
 */
@Stateless
@LocalBean
@Asynchronous
public class SimascJobEjb {
	private static final Logger logger = LogManager.getLogger(SimascJobEjb.class.getName());
	/**
	 * Permite realizar la cosnulta y envio de correo
	 */
	
	@EJB
	private ManejadorFalloAlerta manejadorFalloAlerta;
	
	@EJB
	private IOrquestadorAlertasFacade orquestadorAlertasFacade;
	
//	@EJB
//	private IDocumentoFacade documentoFacade;
	
	/**
	 * Inicia los procesos de negocio para las tareas programadas
	 */
	public Future<String> ejecutarProcesos() {
		logger.info("========== EJECUCION JOB 1.0: " + new Date() + " =============");
		this.orquestadorAlertasNegocio();		
		this.orquestadorAlertasParametrizadas();
		this.orquestadorActualizarFallos();
		return new AsyncResult("Meaningful_Result_String");
		
	}


	/**
	 * llama el metodo de ejecucion de alertas parametrizadas
	 */
	public void orquestadorAlertasParametrizadas() {
		logger.info("Ejecucion Orquestador Parametrizadas -------------------------------------");
		orquestadorAlertasFacade.ejecutarAlertasParametrizadas();
	}
	
	/**
	 * Llama el metodo de ejecucion de alertas de negocio
	 */
	public void orquestadorAlertasNegocio(){
		logger.info("Ejecucion Orquestador Negocio -----------------------------------------");
		orquestadorAlertasFacade.ejecutarAlertasProgramadasNegocio();
	}	
	
	/**
	 * Llama el metodo de ejecucion limpieza de datos
	 */
	public void orquestadorActualizarFallos(){
		logger.info("Ejecucion Orquestador actualizacion de fallos------------------------------------");
		manejadorFalloAlerta.actualizarFallosAlerta();
	}	



}
