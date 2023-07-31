package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorEventoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoRolPersonaCasoFacade;
import com.ccb.simasc.transversal.dto.CasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.EventoRolPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;

// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link EventoRolPersonaCaso}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class EventoRolPersonaCasoFacade extends AccesoFacade<EventoRolPersonaCaso, Long, EventoRolPersonaCasoDTO> implements IEventoRolPersonaCasoFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada

	@EJB
	private ManejadorEventoRolPersonaCaso manejadorEventoRolPersonaCaso;
	
	@EJB
	private ManejadorPersona manejadorPersona;

	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorEventoRolPersonaCaso;
	}

	@Override
	public EventoRolPersonaCasoDTO transformarSinDependencias(EventoRolPersonaCaso obj) {
		EventoRolPersonaCasoDTO dto = new EventoRolPersonaCasoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public EventoRolPersonaCasoDTO transformarConDependencias(EventoRolPersonaCaso obj) {
		EventoRolPersonaCasoDTO dto = new EventoRolPersonaCasoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public EventoRolPersonaCaso transformarEntidadConDependencias(EventoRolPersonaCaso obj) {
		EventoRolPersonaCaso dto = new EventoRolPersonaCaso();
		dto = new EventoRolPersonaCasoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public EventoRolPersonaCaso transformarEntidadSinDependencias(EventoRolPersonaCaso obj) {
		EventoRolPersonaCaso dto = new EventoRolPersonaCaso();
		dto = new EventoRolPersonaCasoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	@Override
	public EventoRolPersonaCaso crearEventoRolPersonaCaso(String estadoAsignado, String motivoInactivacion,
			Date fechaDeAsignacion, String estadoRegistro, Long idRol, Long idPersona, Long idCaso) {
		EventoRolPersonaCaso eventoRolPersonaCaso = new EventoRolPersonaCaso();
		boolean bandera = false;
		if(estadoAsignado != null && (estadoAsignado.equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR) || UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO.equals(estadoAsignado))) {
			List<EventoRolPersonaCaso> eventos = manejadorEventoRolPersonaCaso.consultarEvento(idRol, idPersona, idCaso, estadoAsignado);
			for(EventoRolPersonaCaso evento : eventos){
				String fechaAnterior = UtilOperaciones.formatearFechaReporte(evento.getFechaDeAsignacion());
				String fechaNueva = UtilOperaciones.formatearFechaReporte(fechaDeAsignacion);
				if(fechaAnterior.equals(fechaNueva)){
					bandera = true;
					break;
				}
			}
		}
		eventoRolPersonaCaso.setEstadoAsignado(estadoAsignado);
		if(motivoInactivacion != null){
			eventoRolPersonaCaso.setMotivoInactivacion(motivoInactivacion);
		}		
		eventoRolPersonaCaso.setFechaDeAsignacion(fechaDeAsignacion);
		eventoRolPersonaCaso.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		eventoRolPersonaCaso.setFechaUltimaModificacion(new Date());
		eventoRolPersonaCaso.setEstadoRegistro(estadoRegistro);
		eventoRolPersonaCaso.setIdRol(idRol);
		eventoRolPersonaCaso.setIdPersona(idPersona);
		eventoRolPersonaCaso.setIdCaso(idCaso);
		if(bandera){
			return eventoRolPersonaCaso;
		}else{
			return manejadorEventoRolPersonaCaso.crearEventoRolPersonaCaso(eventoRolPersonaCaso);
		}
	}

	@Override
	public List<CasosAsignadosDTO> eventoRpcAsignado(CasosAsignadosDTO casosAsignadosDTO) {
		List<EventoRolPersonaCaso> listaEventos = new ArrayList<EventoRolPersonaCaso>();
		List<CasosAsignadosDTO> listaCasosAsignadosDto = new ArrayList<CasosAsignadosDTO>();

		try {

			listaEventos = manejadorEventoRolPersonaCaso.eventoRpcAsignado(casosAsignadosDTO);
			for (EventoRolPersonaCaso eventoFor : listaEventos) {
				CasosAsignadosDTO casoAsignado = new CasosAsignadosDTO();
				casoAsignado.setFechaAsignacion(eventoFor.getFechaDeAsignacion());
				casoAsignado.setNombreCaso(eventoFor.getRolPersonaCaso().getCaso().getNombre());
				casoAsignado.setIdCaso(eventoFor.getIdCaso());
				casoAsignado.setNombrePersona(eventoFor.getRolPersonaCaso().getPersona().getNombreCompleto());
				// trae la lista de jueces de ese caso
				List<Persona> listaJuecesCaso = new ArrayList<Persona>();
				listaJuecesCaso = manejadorPersona.consultarPersonasPorRolCasoEstado(eventoFor.getIdCaso(),
						UtilDominios.ROL_PERSONA_ARBITRO, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
				casoAsignado.setListaPersonaCaso(listaJuecesCaso);
				casoAsignado.setNombresArbitros(nombreConComas(listaJuecesCaso));

				listaCasosAsignadosDto.add(casoAsignado);
			}
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		}

		return listaCasosAsignadosDto;
	}
	
	private String nombreConComas(List<Persona> personas){
		StringBuilder nombres = new StringBuilder() ;
		if (personas.size()==1){
			nombres.append(personas.get(0).getNombreCompleto());
		}
		else{
			
			for (int i = 0; i < personas.size(); i++) {
				nombres.append(personas.get(i).getNombreCompleto());
				if(i<personas.size()-1){
					nombres.append(", ");
				}
			}
			
		}
		
		
		
		return nombres.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public EventoRolPersonaCaso consultarEventoComunicacionDesignacionArbitro(Long idRol, Long idPersona, Long idCaso) {
		EventoRolPersonaCaso eventoComunicacionDesignacion = null;

		List<EventoRolPersonaCaso> eventoRolPersonaCasos = (List<EventoRolPersonaCaso>) new EventoRolPersonaCasoDTO()
				.transformarColeccionEntidadesSinDependencias(manejadorEventoRolPersonaCaso.consultarEventosPorRPC(idRol,
						idPersona, idCaso, UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO));

		if (eventoRolPersonaCasos != null && !eventoRolPersonaCasos.isEmpty()) {
			eventoComunicacionDesignacion = eventoRolPersonaCasos.get(eventoRolPersonaCasos.size() - 1);
		}

		return eventoComunicacionDesignacion;
	}
	
	
	// protected region metodos adicionales end
	
}
