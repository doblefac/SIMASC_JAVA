package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorParamEstadoArbitroPreseleccion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParamEstadoArbitroPreseleccionFacade;
import com.ccb.simasc.transversal.dto.ParamEstadoArbitroPreseleccionDTO;
import com.ccb.simasc.transversal.entidades.ParamEstadoArbitroPreseleccion;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ParamEstadoArbitroPreseleccion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ParamEstadoArbitroPreseleccionFacade extends AccesoFacade<ParamEstadoArbitroPreseleccion, String, ParamEstadoArbitroPreseleccionDTO> implements IParamEstadoArbitroPreseleccionFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorParamEstadoArbitroPreseleccion manejadorParamEstadoArbitroPreseleccion;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorParamEstadoArbitroPreseleccion;
	}

	@Override
	public ParamEstadoArbitroPreseleccionDTO transformarSinDependencias(ParamEstadoArbitroPreseleccion obj) {
		ParamEstadoArbitroPreseleccionDTO dto = new ParamEstadoArbitroPreseleccionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ParamEstadoArbitroPreseleccionDTO transformarConDependencias(ParamEstadoArbitroPreseleccion obj) {
		ParamEstadoArbitroPreseleccionDTO dto = new ParamEstadoArbitroPreseleccionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ParamEstadoArbitroPreseleccion transformarEntidadConDependencias(ParamEstadoArbitroPreseleccion obj) {
		ParamEstadoArbitroPreseleccion dto = new ParamEstadoArbitroPreseleccion();
		dto = new ParamEstadoArbitroPreseleccionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ParamEstadoArbitroPreseleccion transformarEntidadSinDependencias(ParamEstadoArbitroPreseleccion obj) {
		ParamEstadoArbitroPreseleccion dto = new ParamEstadoArbitroPreseleccion();
		dto = new ParamEstadoArbitroPreseleccionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
