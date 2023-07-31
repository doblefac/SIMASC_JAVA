package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DetalleReliquidacionDTO;
import com.ccb.simasc.transversal.entidades.DetalleReliquidacion;
import com.ccb.simasc.transversal.entidades.DetalleReliquidacionPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link DetalleReliquidacion}
 * @author sMartinez
 *
 */
@Local
public interface IDetalleReliquidacionFacade extends IAccesoFacade<DetalleReliquidacion, DetalleReliquidacionPK, DetalleReliquidacionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Método que realiza la creación del detalle de una reliquidacion
	 * @param idReliquidacion
	 * @param itemId
	 * @param valor
	 * @param servicioCaja
	 */
	public void crearDetalleReliquidacion(Long idReliquidacion, Long itemId, Long valor, String servicioCaja);
	// protected region metodos adicionales end
	
}
