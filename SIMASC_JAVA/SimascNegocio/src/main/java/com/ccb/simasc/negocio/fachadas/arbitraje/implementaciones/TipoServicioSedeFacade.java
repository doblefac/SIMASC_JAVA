package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorTipoServicioSede;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITipoServicioSedeFacade;
import com.ccb.simasc.transversal.dto.TipoServicioSedeDTO;
import com.ccb.simasc.transversal.entidades.TipoServicioSede;
import com.ccb.simasc.transversal.entidades.TipoServicioSedePK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link TipoServicioSede}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class TipoServicioSedeFacade extends AccesoFacade<TipoServicioSede, TipoServicioSedePK, TipoServicioSedeDTO> implements ITipoServicioSedeFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorTipoServicioSede manejadorTipoServicioSede;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorTipoServicioSede;
	}

	@Override
	public TipoServicioSedeDTO transformarSinDependencias(TipoServicioSede obj) {
		TipoServicioSedeDTO dto = new TipoServicioSedeDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public TipoServicioSedeDTO transformarConDependencias(TipoServicioSede obj) {
		TipoServicioSedeDTO dto = new TipoServicioSedeDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public TipoServicioSede transformarEntidadConDependencias(TipoServicioSede obj) {
		TipoServicioSede dto = new TipoServicioSede();
		dto = new TipoServicioSedeDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public TipoServicioSede transformarEntidadSinDependencias(TipoServicioSede obj) {
		TipoServicioSede dto = new TipoServicioSede();
		dto = new TipoServicioSedeDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
