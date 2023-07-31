package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorEspecialidad;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEspecialidadFacade;
import com.ccb.simasc.transversal.dto.EspecialidadDTO;
import com.ccb.simasc.transversal.entidades.Especialidad;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Especialidad}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class EspecialidadFacade extends AccesoFacade<Especialidad, Long, EspecialidadDTO> implements IEspecialidadFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorEspecialidad manejadorEspecialidad;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorEspecialidad;
	}

	@Override
	public EspecialidadDTO transformarSinDependencias(Especialidad obj) {
		EspecialidadDTO dto = new EspecialidadDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public EspecialidadDTO transformarConDependencias(Especialidad obj) {
		EspecialidadDTO dto = new EspecialidadDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Especialidad transformarEntidadConDependencias(Especialidad obj) {
		Especialidad dto = new Especialidad();
		dto = new EspecialidadDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Especialidad transformarEntidadSinDependencias(Especialidad obj) {
		Especialidad dto = new Especialidad();
		dto = new EspecialidadDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
