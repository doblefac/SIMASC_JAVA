package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DetallePagoCasoDTO;
import com.ccb.simasc.transversal.entidades.DetallePagoCaso;
import com.ccb.simasc.transversal.entidades.DetallePagoCasoPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link DetallePagoCaso}
 * @author sMartinez
 *
 */
@Local
public interface IDetallePagoCasoFacade extends IAccesoFacade<DetallePagoCaso, DetallePagoCasoPK, DetallePagoCasoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	 public List<DetallePagoCaso> obtenerDetallesPago(String numeroRecibo);
	// protected region metodos adicionales end
	
}
