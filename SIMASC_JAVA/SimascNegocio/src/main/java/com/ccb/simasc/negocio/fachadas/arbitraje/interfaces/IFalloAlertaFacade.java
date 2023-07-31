package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.FalloAlertaDTO;
import com.ccb.simasc.transversal.entidades.FalloAlerta;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link FalloAlerta}
 * @author sMartinez
 *
 */
@Local
public interface IFalloAlertaFacade extends IAccesoFacade<FalloAlerta, Long, FalloAlertaDTO> {


	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Crea el registro de fallo de alerta
	 * @param idAlerta
	 * @param idProgramacion
	 * @param estado
	 */
	void crearFalloAlertas(Long idAlerta, Long idProgramacion, String estado);


	// protected region metodos adicionales end
	
}
