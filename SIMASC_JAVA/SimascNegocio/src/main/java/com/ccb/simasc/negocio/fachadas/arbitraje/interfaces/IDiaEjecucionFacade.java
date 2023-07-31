package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DiaEjecucionDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.entidades.DiaEjecucion;
import com.ccb.simasc.transversal.entidades.DiaEjecucionPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link DiaEjecucion}
 * @author sMartinez
 *
 */
@Local
public interface IDiaEjecucionFacade extends IAccesoFacade<DiaEjecucion, DiaEjecucionPK, DiaEjecucionDTO> {


	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Metodo encargadp de actulizar la lista de dias de ejecucion
	 * @param alerta
	 * @param diaEjecucion
	 */
	public void actualizarDiasEjecucion(Alerta alerta, List<String> diaEjecucion);

	// protected region metodos adicionales end
	
}
