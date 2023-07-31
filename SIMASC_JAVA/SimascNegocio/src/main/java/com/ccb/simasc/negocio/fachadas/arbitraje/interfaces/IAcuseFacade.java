package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.AcuseDTO;
import com.ccb.simasc.transversal.entidades.Acuse;
import com.ccb.simasc.transversal.entidades.AcusePK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Acuse}
 * @author sMartinez
 *
 */
@Local
public interface IAcuseFacade extends IAccesoFacade<Acuse, AcusePK, AcuseDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Obtiene el acuse por tipo de un CorreoRolPersonaCaso
	 * 
	 * @param idCorreoRolPersonaCaso
	 * @param tipo
	 * @return
	 */
	public AcuseDTO obtenerAcuseCorreoRolPersonaCaso(Long idCorreoRolPersonaCaso, String tipo);

	// protected region metodos adicionales end
	
}
