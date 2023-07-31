package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import javax.ejb.Local;

@Local
public interface IOrquestadorAlertasFacade {


	/**
	 * Metodo encrargado de la carga y ejecucion de las tareas programadas
	 */

	public void ejecutarAlertasParametrizadas();

	/**
	 * Metodo encargado de cargar y ejecutar las alertas de negocio
	 */
	public void ejecutarAlertasProgramadasNegocio();

	/**
	 * Alerta de notificacion del conciliador
	 * @param idPersona
	 * @param metodoNombramiento
	 */
	public void alertaNOT_NPC(Long idCaso, Long idPersona, String metodoNombramiento);
	
	/**
	 * Alerta de sobreasignación de minutos de transcripción A-18 
	 * @param 
	 * @param metodoNombramiento
	 */
	public void alertaSOBASGM(Long idPersona);
	
	
	/**
	 * Alerta de fijacion de audiencia primera de tramite 
	 * @param idCaso
	 */
	public void alertaFIJAUT(Long idCaso); 

}
