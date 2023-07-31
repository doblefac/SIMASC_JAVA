package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ApoderadosSolicitudDTO;
import com.ccb.simasc.transversal.entidades.ApoderadosSolicitud;
import com.ccb.simasc.transversal.entidades.ApoderadosSolicitudPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link ApoderadosSolicitud}
 * @author sMartinez
 *
 */
@Local
public interface IApoderadosSolicitudFacade extends IAccesoFacade<ApoderadosSolicitud, ApoderadosSolicitudPK, ApoderadosSolicitudDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	public void eliminarApoderados(Long idSolicitudServicio, Long idPersona);

	// protected region metodos adicionales end
	
}
