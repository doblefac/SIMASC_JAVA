package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorApoderadosSolicitud;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IApoderadosSolicitudFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.ApoderadosSolicitudDTO;
import com.ccb.simasc.transversal.entidades.ApoderadosSolicitud;
import com.ccb.simasc.transversal.entidades.ApoderadosSolicitudPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ApoderadosSolicitud}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ApoderadosSolicitudFacade extends AccesoFacade<ApoderadosSolicitud, ApoderadosSolicitudPK, ApoderadosSolicitudDTO> implements IApoderadosSolicitudFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorApoderadosSolicitud manejadorApoderadosSolicitud;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorApoderadosSolicitud;
	}

	@Override
	public ApoderadosSolicitudDTO transformarSinDependencias(ApoderadosSolicitud obj) {
		ApoderadosSolicitudDTO dto = new ApoderadosSolicitudDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ApoderadosSolicitudDTO transformarConDependencias(ApoderadosSolicitud obj) {
		ApoderadosSolicitudDTO dto = new ApoderadosSolicitudDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ApoderadosSolicitud transformarEntidadConDependencias(ApoderadosSolicitud obj) {
		ApoderadosSolicitud dto = new ApoderadosSolicitud();
		dto = new ApoderadosSolicitudDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ApoderadosSolicitud transformarEntidadSinDependencias(ApoderadosSolicitud obj) {
		ApoderadosSolicitud dto = new ApoderadosSolicitud();
		dto = new ApoderadosSolicitudDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	@Override
	public void eliminarApoderados(Long idSolicitudServicio, Long idPersona) {
		
		List<ApoderadosSolicitud> apoderadosToDelete = manejadorApoderadosSolicitud.obtenerApoderadoPorPersonaYCaso(idPersona, idSolicitudServicio);
		List<ApoderadosSolicitud> representadosToDelete = manejadorApoderadosSolicitud.obtenerRepresentadosPorPersonaCaso(idPersona, idSolicitudServicio);
		
		if (!apoderadosToDelete.isEmpty()) {
			
			for (ApoderadosSolicitud apoderado : apoderadosToDelete) {
				
				manejadorApoderadosSolicitud.eliminar(apoderado);
				
			}
			
		} else if (!representadosToDelete.isEmpty()) {
			
			for (ApoderadosSolicitud representado : representadosToDelete) {
				
				manejadorApoderadosSolicitud.eliminar(representado);
				
			}
			
		}
		
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
