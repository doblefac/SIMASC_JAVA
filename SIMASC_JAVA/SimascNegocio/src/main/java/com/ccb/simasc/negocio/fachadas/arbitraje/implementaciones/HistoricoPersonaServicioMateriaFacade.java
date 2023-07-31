package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoPersonaServicioMateria;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IHistoricoPersonaServicioMateriaFacade;
import com.ccb.simasc.transversal.dto.HistoricoPersonaServicioMateriaDTO;
import com.ccb.simasc.transversal.entidades.HistoricoPersonaServicioMateria;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link HistoricoPersonaServicioMateria}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class HistoricoPersonaServicioMateriaFacade extends AccesoFacade<HistoricoPersonaServicioMateria, Long, HistoricoPersonaServicioMateriaDTO> implements IHistoricoPersonaServicioMateriaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorHistoricoPersonaServicioMateria manejadorHistoricoPersonaServicioMateria;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorHistoricoPersonaServicioMateria;
	}

	@Override
	public HistoricoPersonaServicioMateriaDTO transformarSinDependencias(HistoricoPersonaServicioMateria obj) {
		HistoricoPersonaServicioMateriaDTO dto = new HistoricoPersonaServicioMateriaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public HistoricoPersonaServicioMateriaDTO transformarConDependencias(HistoricoPersonaServicioMateria obj) {
		HistoricoPersonaServicioMateriaDTO dto = new HistoricoPersonaServicioMateriaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public HistoricoPersonaServicioMateria transformarEntidadConDependencias(HistoricoPersonaServicioMateria obj) {
		HistoricoPersonaServicioMateria dto = new HistoricoPersonaServicioMateria();
		dto = new HistoricoPersonaServicioMateriaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public HistoricoPersonaServicioMateria transformarEntidadSinDependencias(HistoricoPersonaServicioMateria obj) {
		HistoricoPersonaServicioMateria dto = new HistoricoPersonaServicioMateria();
		dto = new HistoricoPersonaServicioMateriaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
