package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ObligacionDTO;
import com.ccb.simasc.transversal.dto.SeguimientoObligacionesDTO;
import com.ccb.simasc.transversal.entidades.Obligacion;
import com.ccb.simasc.transversal.entidades.ObligacionPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Obligacion}
 * @author sMartinez
 *
 */
@Local
public interface IObligacionFacade extends IAccesoFacade<Obligacion, ObligacionPK, ObligacionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Consulta las obligaciones del caso cuando esta en estado Acuerdo
	 * CON-F-076
	 * @author LRUIZ
	 * @param idCaso
	 * @return
	 */
	public List<SeguimientoObligacionesDTO> consultarObligacionesCaso(Long idCaso);
	// protected region metodos adicionales end
	
}
