package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin

// Escriba en esta sección sus modificaciones

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.AreaAsuntoClasificacionDTO;
import com.ccb.simasc.transversal.entidades.AreaAsuntoClasificacion;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de
 * {@link AreaAsuntoClasificacion}
 * 
 * @author sMartinez
 *
 */
@Local
public interface IAreaAsuntoClasificacionFacade
		extends IAccesoFacade<AreaAsuntoClasificacion, Long, AreaAsuntoClasificacionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * Metodo que permite consultar el areaAsuntoClasificacion de un caso.
	 * 
	 * @param idCaso
	 *            : Identificador del caso.
	 * @return AreaAsuntoClasificacion.
	 */
	public AreaAsuntoClasificacion consultarAreaAsuntoClasificacionPorCaso(Long idCaso);
	// protected region metodos adicionales end

}
