package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.InasistenciaDTO;
import com.ccb.simasc.transversal.entidades.Inasistencia;
import com.ccb.simasc.transversal.entidades.InasistenciaPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Inasistencia}
 * @author sMartinez
 *
 */
@Local
public interface IInasistenciaFacade extends IAccesoFacade<Inasistencia, InasistenciaPK, InasistenciaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Metodo para consultar las inasistencias
	 * @param inasistenciaDTO
	 * @return List<Inasistencia>
	 */
	public List<InasistenciaDTO> consultarInasistencias(InasistenciaDTO inasistencia);
	// protected region metodos adicionales end
	
}
