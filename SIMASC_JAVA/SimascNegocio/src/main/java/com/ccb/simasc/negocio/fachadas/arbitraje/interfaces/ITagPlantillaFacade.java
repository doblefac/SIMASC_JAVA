package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.TagPlantillaDTO;
import com.ccb.simasc.transversal.entidades.TagPlantilla;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link TagPlantilla}
 * @author sMartinez
 *
 */
@Local
public interface ITagPlantillaFacade extends IAccesoFacade<TagPlantilla, Long, TagPlantillaDTO> {

		// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
		public List<TagPlantilla> obtieneTagPorTipoServicio(String tipoServicio) throws SIMASCNegocioExcepcion;

	// protected region metodos adicionales end
	
}
