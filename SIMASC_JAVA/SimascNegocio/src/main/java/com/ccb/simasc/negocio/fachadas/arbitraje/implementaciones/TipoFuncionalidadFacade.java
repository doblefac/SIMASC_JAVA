package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorTipoFuncionalidad;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITipoFuncionalidadFacade;
import com.ccb.simasc.transversal.dto.TipoFuncionalidadDTO;
import com.ccb.simasc.transversal.entidades.TipoFuncionalidad;
import com.ccb.simasc.transversal.entidades.TipoFuncionalidadPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link TipoFuncionalidad}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class TipoFuncionalidadFacade extends AccesoFacade<TipoFuncionalidad, TipoFuncionalidadPK, TipoFuncionalidadDTO> implements ITipoFuncionalidadFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorTipoFuncionalidad manejadorTipoFuncionalidad;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorTipoFuncionalidad;
	}

	@Override
	public TipoFuncionalidadDTO transformarSinDependencias(TipoFuncionalidad obj) {
		TipoFuncionalidadDTO dto = new TipoFuncionalidadDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public TipoFuncionalidadDTO transformarConDependencias(TipoFuncionalidad obj) {
		TipoFuncionalidadDTO dto = new TipoFuncionalidadDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public TipoFuncionalidad transformarEntidadConDependencias(TipoFuncionalidad obj) {
		TipoFuncionalidad dto = new TipoFuncionalidad();
		dto = new TipoFuncionalidadDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public TipoFuncionalidad transformarEntidadSinDependencias(TipoFuncionalidad obj) {
		TipoFuncionalidad dto = new TipoFuncionalidad();
		dto = new TipoFuncionalidadDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
