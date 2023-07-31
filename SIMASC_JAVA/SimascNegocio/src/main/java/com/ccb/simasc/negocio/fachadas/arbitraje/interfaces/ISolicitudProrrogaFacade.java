package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.SolicitudProrrogaCierreDTO;
import com.ccb.simasc.transversal.dto.SolicitudProrrogaDTO;
import com.ccb.simasc.transversal.entidades.SolicitudProrroga;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link SolicitudProrroga}
 * @author sMartinez
 *
 */
@Local
public interface ISolicitudProrrogaFacade extends IAccesoFacade<SolicitudProrroga, Long, SolicitudProrrogaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 *  Método para consultar las solicitudes prorroga de un caso
	 * 
	 * @param idCaso:
	 *            Id del caso.
	
	 * @return List<SolicitudProrrogaCierreDTO>
	 */
	public SolicitudProrrogaCierreDTO consultarSolicitudesProrroga(Long idCaso, String idUsuarioModificacion);
	
	/**
	 *  Método para modificar una solicitud prorroga de un caso
	 * 
	 * @param solicitud:
	 *            DTO que contiene la información de la solicitud
	 *            
	 *        
	 */
	public void modificarSolicitudeProrroga(SolicitudProrrogaDTO solicitud, String idUsuarioModificacion);
	
	/**
	 * Metodo que valida si la fecha que en ingresó como parametro se encuentra dentro de 
	 * la prorroga vigente, adicionalmente retornará false si no tiene prorroga 
	 * @author prendon
	 * @param fecha
	 * @return boolean 
	 */
	public boolean validarProrrogaCasoVigentePorFecha( Long idCaso, Date fecha );
	
	// protected region metodos adicionales end
	
}
