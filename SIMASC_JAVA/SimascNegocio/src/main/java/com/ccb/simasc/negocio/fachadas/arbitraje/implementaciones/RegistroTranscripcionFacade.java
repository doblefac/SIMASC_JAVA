package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorRegistroTranscripcion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRegistroTranscripcionFacade;
import com.ccb.simasc.transversal.dto.RegistroTranscripcionDTO;
import com.ccb.simasc.transversal.entidades.RegistroTranscripcion;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link RegistroTranscripcion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class RegistroTranscripcionFacade extends AccesoFacade<RegistroTranscripcion, Long, RegistroTranscripcionDTO> implements IRegistroTranscripcionFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorRegistroTranscripcion manejadorRegistroTranscripcion;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorRegistroTranscripcion;
	}

	@Override
	public RegistroTranscripcionDTO transformarSinDependencias(RegistroTranscripcion obj) {
		RegistroTranscripcionDTO dto = new RegistroTranscripcionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public RegistroTranscripcionDTO transformarConDependencias(RegistroTranscripcion obj) {
		RegistroTranscripcionDTO dto = new RegistroTranscripcionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public RegistroTranscripcion transformarEntidadConDependencias(RegistroTranscripcion obj) {
		RegistroTranscripcion dto = new RegistroTranscripcion();
		dto = new RegistroTranscripcionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public RegistroTranscripcion transformarEntidadSinDependencias(RegistroTranscripcion obj) {
		RegistroTranscripcion dto = new RegistroTranscripcion();
		dto = new RegistroTranscripcionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
