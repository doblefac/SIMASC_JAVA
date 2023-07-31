package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin

// Escriba en esta seccion sus modificaciones

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.AutorizacionTratamientoDatosDTO;
import com.ccb.simasc.transversal.entidades.AutorizacionTratamientoDatos;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de
 * {@link AutorizacionTratamientoDatos}
 * 
 * @author sMartinez
 *
 */
@Local
public interface IAutorizacionTratamientoDatosFacade
		extends IAccesoFacade<AutorizacionTratamientoDatos, Long, AutorizacionTratamientoDatosDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 *crea la autorizacion de datos retornando el idAutorizacion 
	 * @param autorizacion
	 * @return
	 */
	public Long crearAutorizacionDatos(AutorizacionTratamientoDatos autorizacion);

	// protected region metodos adicionales end

}
