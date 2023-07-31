package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.PagoHonorariosDTO;
import com.ccb.simasc.transversal.entidades.PagoHonorarios;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link PagoHonorarios}
 * @author sMartinez
 *
 */
@Local
public interface IPagoHonorariosFacade extends IAccesoFacade<PagoHonorarios, Long, PagoHonorariosDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	/**
	 * Registra el pago de una parte 
	 * @param pagoHonorariosDTO
	 */
	public void registrarPagoHonorarios(PagoHonorariosDTO pagoHonorariosDTO);

	// protected region metodos adicionales end
	
}
