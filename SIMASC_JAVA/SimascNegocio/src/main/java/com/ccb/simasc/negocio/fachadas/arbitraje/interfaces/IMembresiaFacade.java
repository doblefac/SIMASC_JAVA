package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.MembresiaDTO;
import com.ccb.simasc.transversal.entidades.Membresia;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Membresia}
 * @author sMartinez
 *
 */
@Local
public interface IMembresiaFacade extends IAccesoFacade<Membresia, Long, MembresiaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public List<MembresiaDTO> consultarMembresiasPersona( Long idPersona );
	
	
	/**
	 * Metodo para crear una nueva membresia
	 * @param membresia
	 * @param idPersonaModificación
	 * @return void()
	 */
	public void crearMembresia(MembresiaDTO membresia, String idPersonaModificacion);
	
	// protected region metodos adicionales end
	
}
