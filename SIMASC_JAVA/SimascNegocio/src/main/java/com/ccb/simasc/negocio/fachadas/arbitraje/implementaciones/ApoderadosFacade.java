package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorApoderados;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IApoderadosFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.ApoderadosDTO;
import com.ccb.simasc.transversal.entidades.Apoderados;
import com.ccb.simasc.transversal.entidades.ApoderadosPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Apoderados}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ApoderadosFacade extends AccesoFacade<Apoderados, ApoderadosPK, ApoderadosDTO> implements IApoderadosFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorApoderados manejadorApoderados;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorApoderados;
	}

	@Override
	public ApoderadosDTO transformarSinDependencias(Apoderados obj) {
		ApoderadosDTO dto = new ApoderadosDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ApoderadosDTO transformarConDependencias(Apoderados obj) {
		ApoderadosDTO dto = new ApoderadosDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Apoderados transformarEntidadConDependencias(Apoderados obj) {
		Apoderados dto = new Apoderados();
		dto = new ApoderadosDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Apoderados transformarEntidadSinDependencias(Apoderados obj) {
		Apoderados dto = new Apoderados();
		dto = new ApoderadosDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	@Override
	public void eliminarApoderados(Long idCaso, Long idPersona) {

		List<Apoderados> apoderadosToDelete = manejadorApoderados.obtenerApoderadoPorPersonaYCaso(idPersona, idCaso);
		List<Apoderados> representadosToDelete = manejadorApoderados.obtenerRepresentadosPorPersonaCaso(idPersona, idCaso);
		
		if (!apoderadosToDelete.isEmpty()) {
			
			for (Apoderados apoderado : apoderadosToDelete) {
				
				manejadorApoderados.eliminar(apoderado);
				
			}
			
		} else if (!representadosToDelete.isEmpty()) {
			
			for (Apoderados representado : representadosToDelete) {
				
				manejadorApoderados.eliminar(representado);
				
			}
			
		}
		
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
