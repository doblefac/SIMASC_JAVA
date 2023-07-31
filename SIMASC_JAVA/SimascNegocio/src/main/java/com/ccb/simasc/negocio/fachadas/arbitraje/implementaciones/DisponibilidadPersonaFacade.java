package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorDisponibilidadPersona;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDisponibilidadPersonaFacade;
import com.ccb.simasc.transversal.dto.DisponibilidadPersonaDTO;
import com.ccb.simasc.transversal.entidades.DisponibilidadPersona;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link DisponibilidadPersona}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class DisponibilidadPersonaFacade extends AccesoFacade<DisponibilidadPersona, Long, DisponibilidadPersonaDTO> implements IDisponibilidadPersonaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorDisponibilidadPersona manejadorDisponibilidadPersona;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorDisponibilidadPersona;
	}

	@Override
	public DisponibilidadPersonaDTO transformarSinDependencias(DisponibilidadPersona obj) {
		DisponibilidadPersonaDTO dto = new DisponibilidadPersonaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public DisponibilidadPersonaDTO transformarConDependencias(DisponibilidadPersona obj) {
		DisponibilidadPersonaDTO dto = new DisponibilidadPersonaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public DisponibilidadPersona transformarEntidadConDependencias(DisponibilidadPersona obj) {
		DisponibilidadPersona dto = new DisponibilidadPersona();
		dto = new DisponibilidadPersonaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public DisponibilidadPersona transformarEntidadSinDependencias(DisponibilidadPersona obj) {
		DisponibilidadPersona dto = new DisponibilidadPersona();
		dto = new DisponibilidadPersonaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	
	@Override
	public List<DisponibilidadPersonaDTO> consultarDisponibilidadPersona(Long idPersona) {
		return manejadorDisponibilidadPersona.consultarDisponibilidadPersona(idPersona);
	}
	
	@Override
	public void actualizarDisponibilidadPersona(DisponibilidadPersonaDTO disponibilidad, String idUsuario) {
		
		DisponibilidadPersona disponibilidadPersona;
		if(disponibilidad.getIdDisponibildadPersona()!=null){
			
			disponibilidadPersona = manejadorDisponibilidadPersona.buscar(disponibilidad.getIdDisponibildadPersona());
			//Eliminar
			if(UtilDominios.ESTADO_REGISTRO_INACTIVO.equals(disponibilidad.getEstadoRegistro())){
				
				disponibilidadPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);	
			}
			//Actualizar
			else{
				disponibilidadPersona.setHoraInicio(disponibilidad.getHoraInicio());
				disponibilidadPersona.setHoraFin(disponibilidad.getHoraFin());
				disponibilidadPersona.setCodigo(disponibilidad.getCodigo());
			}
			
			disponibilidadPersona.setFechaUltimaModificacion(new Date());
			disponibilidadPersona.setIdUsuarioModificacion(idUsuario);
			manejadorDisponibilidadPersona.actualizar(disponibilidadPersona);
		}
		//Crea Disponibilidad
		else{
			disponibilidadPersona = new DisponibilidadPersona();
			disponibilidadPersona.setCodigo(disponibilidad.getCodigo());
			disponibilidadPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			disponibilidadPersona.setFechaUltimaModificacion(new Date());
			disponibilidadPersona.setHoraFin(disponibilidad.getHoraFin());
			disponibilidadPersona.setHoraInicio(disponibilidad.getHoraInicio());
			disponibilidadPersona.setIdPersona(disponibilidad.getIdPersona());
			disponibilidadPersona.setIdUsuarioModificacion(idUsuario);
			manejadorDisponibilidadPersona.crear(disponibilidadPersona);
		}
		
	}
	
	 
	// protected region metodos adicionales end
	
}
