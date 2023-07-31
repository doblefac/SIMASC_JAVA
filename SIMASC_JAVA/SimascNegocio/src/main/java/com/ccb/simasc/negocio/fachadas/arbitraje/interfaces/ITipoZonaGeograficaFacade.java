package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.TipoZonaGeograficaDTO;
import com.ccb.simasc.transversal.entidades.TipoZonaGeografica;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link TipoZonaGeografica}
 * @author sMartinez
 *
 */
@Local
public interface ITipoZonaGeograficaFacade extends IAccesoFacade<TipoZonaGeografica, Integer, TipoZonaGeograficaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
