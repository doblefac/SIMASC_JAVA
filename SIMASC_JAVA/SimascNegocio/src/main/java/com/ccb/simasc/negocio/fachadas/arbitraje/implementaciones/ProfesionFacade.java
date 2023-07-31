package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorProfesion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IProfesionFacade;
import com.ccb.simasc.transversal.dto.ProfesionDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.entidades.Profesion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Profesion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ProfesionFacade extends AccesoFacade<Profesion, Long, ProfesionDTO> implements IProfesionFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorProfesion manejadorProfesion;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorProfesion;
	}

	@Override
	public ProfesionDTO transformarSinDependencias(Profesion obj) {
		ProfesionDTO dto = new ProfesionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ProfesionDTO transformarConDependencias(Profesion obj) {
		ProfesionDTO dto = new ProfesionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Profesion transformarEntidadConDependencias(Profesion obj) {
		Profesion dto = new Profesion();
		dto = new ProfesionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Profesion transformarEntidadSinDependencias(Profesion obj) {
		Profesion dto = new Profesion();
		dto = new ProfesionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	@Override
	public List<GenericoDTO> obtenerProfesiones() throws SIMASCNegocioExcepcion {
		List<GenericoDTO> genericoList = new ArrayList<GenericoDTO>();
		try {
			List<Profesion> profesiones = manejadorProfesion.consultar(null, null, null);
			for(Profesion it : profesiones){
				GenericoDTO genericoDTO = new GenericoDTO();
				genericoDTO.setId(it.getIdProfesion().toString());
				genericoDTO.setNombre(it.getNombre());
				genericoList.add(genericoDTO);
			}
		} catch (Exception e) {
			String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString());
			throw new SIMASCNegocioExcepcion(msg);
		}
		
		return genericoList;
	}
	// protected region metodos adicionales end
	
}
