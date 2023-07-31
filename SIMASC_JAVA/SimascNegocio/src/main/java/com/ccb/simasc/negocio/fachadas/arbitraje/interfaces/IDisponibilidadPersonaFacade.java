package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DisponibilidadPersonaDTO;
import com.ccb.simasc.transversal.entidades.DisponibilidadPersona;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link DisponibilidadPersona}
 * @author sMartinez
 *
 */
@Local
public interface IDisponibilidadPersonaFacade extends IAccesoFacade<DisponibilidadPersona, Long, DisponibilidadPersonaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Metodo para consultar la disponibilidad 
	 * @param idPersona
	 * @return List<DisponibilidadPersonaDTO>
	 */
	public List<DisponibilidadPersonaDTO> consultarDisponibilidadPersona(Long idPersona);
	
	/**
	 * Metodo para actualizar la disponibilidad 
	 * @param disponibilidad
	 * @return List<DisponibilidadPersonaDTO>
	 */
	public void  actualizarDisponibilidadPersona(DisponibilidadPersonaDTO disponibilidad, String idUsuario);
	// protected region metodos adicionales end
	
}
