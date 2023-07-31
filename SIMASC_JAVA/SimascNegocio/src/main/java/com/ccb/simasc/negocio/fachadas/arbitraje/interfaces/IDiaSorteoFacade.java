package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DiaSorteoDTO;
import com.ccb.simasc.transversal.entidades.DiaSorteo;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link DiaSorteo}
 * @author sMartinez
 *
 */
@Local
public interface IDiaSorteoFacade extends IAccesoFacade<DiaSorteo, String, DiaSorteoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Método que obtiene todos los días de sorteo
	 * @return
	 */
	public List<DiaSorteoDTO> consultarDiasSorteo();
	
	// protected region metodos adicionales end
	
}
