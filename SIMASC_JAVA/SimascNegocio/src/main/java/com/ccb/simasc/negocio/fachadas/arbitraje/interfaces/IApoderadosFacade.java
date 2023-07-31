package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ApoderadosDTO;
import com.ccb.simasc.transversal.entidades.Apoderados;
import com.ccb.simasc.transversal.entidades.ApoderadosPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Apoderados}
 * @author sMartinez
 *
 */
@Local
public interface IApoderadosFacade extends IAccesoFacade<Apoderados, ApoderadosPK, ApoderadosDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	public void eliminarApoderados(Long idCaso, Long idPersona);
	
	// protected region metodos adicionales end
	
}
