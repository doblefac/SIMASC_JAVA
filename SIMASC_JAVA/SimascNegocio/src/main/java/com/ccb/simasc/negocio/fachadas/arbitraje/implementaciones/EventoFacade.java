package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorEvento;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUsuarioFacade;
import com.ccb.simasc.transversal.dto.EventoDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosBasicosCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.RutaDelCasoDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Usuario;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Evento}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class EventoFacade extends AccesoFacade<Evento, Long, EventoDTO> implements IEventoFacade {
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@EJB
	private IDominioFacade fachadaDominios;
	
	@EJB
	private ManejadorCaso manejadorCaso;
	
	@EJB
	private ManejadorEvento manejadorEvento;
	

	@EJB
	private IPersonaFacade personaFacade;
	
	@EJB
	private IUsuarioFacade usuarioFacade;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorEvento;
	}

	@Override
	public EventoDTO transformarSinDependencias(Evento obj) {
		EventoDTO dto = new EventoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public EventoDTO transformarConDependencias(Evento obj) {
		EventoDTO dto = new EventoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Evento transformarEntidadConDependencias(Evento obj) {
		
		return new EventoDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Evento transformarEntidadSinDependencias(Evento obj) {
		
		return new EventoDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	@Override
	public void registrarEvento(Caso caso, String tipoEvento, String observaciones, String idUsuarioModifica, Date fechaEvento, String estadoRegistro) throws SIMASCNegocioExcepcion{
		Evento evento = new Evento();
		evento.setCaso(caso);
		evento.setIdCaso(caso.getIdCaso());
		if(null != tipoEvento && !tipoEvento.isEmpty()){
			evento.setTipoEvento(tipoEvento);			
		}
		else {
			tipoEvento = null;
			evento.setTipoEvento(tipoEvento);
		}
			
		evento.setObservaciones(observaciones);
		
		if(null != estadoRegistro && !estadoRegistro.isEmpty())
			evento.setEstadoRegistro(estadoRegistro);
		else
			evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		evento.setIdUsuarioModificacion(idUsuarioModifica);
		if(fechaEvento!=null)
			evento.setFechaEvento(fechaEvento);
		else
			evento.setFechaEvento(Calendar.getInstance().getTime());
		evento.setFechaUltimaModificacion(Calendar.getInstance().getTime());
		try {
			manejadorEvento.crear(evento);			
		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
	}
	
	@Override
	public void registrarEvento(Long idCaso, String tipoEvento, String observaciones, String idUsuarioModifica, Date fechaEvento, String estadoRegistro){
		
		Evento evento = new Evento();
		Caso caso = manejadorCaso.buscar(idCaso);
		evento.setCaso(caso);
		evento.setIdCaso(caso.getIdCaso());
		evento.setTipoEvento(tipoEvento);
		evento.setObservaciones(observaciones);  
		
		if(null != estadoRegistro && !estadoRegistro.isEmpty()) {
			evento.setEstadoRegistro(estadoRegistro);
			
		}
		else {
			evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			
		}
		evento.setIdUsuarioModificacion(idUsuarioModifica);
		if(fechaEvento!=null) {
			evento.setFechaEvento(fechaEvento);
			
		}
		else {
			evento.setFechaEvento(Calendar.getInstance().getTime());
			
		}
		evento.setFechaUltimaModificacion(Calendar.getInstance().getTime());
		manejadorEvento.crear(evento);
	}
	
	
	@Override
	public void registrarEvento(Long idCaso, String tipoEvento, String observaciones, String idUsuarioModifica){
		Evento evento = new Evento();
		evento.setIdCaso(idCaso);
		evento.setTipoEvento(tipoEvento);
		evento.setObservaciones(observaciones);  
		evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);	
		evento.setIdUsuarioModificacion(idUsuarioModifica);
		evento.setFechaEvento(Calendar.getInstance().getTime());
		evento.setFechaUltimaModificacion(Calendar.getInstance().getTime());
		manejadorEvento.crear(evento);
	}
	
	public List<RutaDelCasoDTO> consultarEventosDeCaso(Long idCaso){
		List<Evento> eventos = manejadorEvento.consultarEventosCaso(idCaso);
		List<RutaDelCasoDTO> rutas = new ArrayList<RutaDelCasoDTO>();
	
		for (Evento evento : eventos) {
			RutaDelCasoDTO ruta = new RutaDelCasoDTO();
			ruta.setFechaEvento((java.util.Date)evento.getFechaEvento());
			ruta.setDescripcion(evento.getObservaciones());
			ruta.setTipo(fachadaDominios.getNombreDominio(UtilDominios.DOMINIO_TIPO_EVENTO,evento.getTipoEvento()));
			Long idPersona = null;
			try{
				Usuario usuario =  usuarioFacade.buscar(evento.getIdUsuarioModificacion());
				if(usuario.getIdPersona() != null){
					idPersona = usuario.getIdPersona();
					Persona p = (idPersona!=null)?personaFacade.buscar(idPersona):null;
					ruta.setUsuario((p==null)?evento.getIdUsuarioModificacion():p.getNombreCompleto());
				}else{
					ruta.setUsuario(UtilConstantes.USUARIO_DEFECTO_SIMASC);
				}
				
			}catch(Exception e){
				ruta.setUsuario(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			}
			
			rutas.add(ruta);
			
		}	
		return rutas;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade#
	 * consultarEventosCasoPorTipoEvento(java.lang.Long, java.util.List)
	 */
	@Override
	public List<Evento> consultarEventosCasoPorTipoEvento(Long idCaso, List<String> tiposEvento) {
		return manejadorEvento.consultarEventoCaso(idCaso, tiposEvento);
	}
	
	public List<RutaDelCasoDTO> consultarEventosCasoTipo(Long idCaso){
		return manejadorEvento.consultarEventosCasoTipo(idCaso);
	}
	
	@Override
	public ArrayList<RutaDelCasoDTO> consultarEntidadesEventosDeCaso(Long idCaso){
		List<Evento> eventos = manejadorEvento.consultarEventosCaso(idCaso);
		ArrayList<RutaDelCasoDTO> rutas = new ArrayList<>();
	
		for (Evento evento : eventos) {
			RutaDelCasoDTO ruta = new RutaDelCasoDTO();
			ruta.setFechaEvento((java.util.Date)evento.getFechaEvento());
			ruta.setDescripcion(evento.getObservaciones());
			ruta.setTipo(evento.getTipoEvento());
			rutas.add(ruta);
		}	
		return rutas;
	}
	
	@Override
	public List<EventoDTO> consultarUltimoEventoPorTipos(List<String> tipoEventos,Long idCaso){
		return manejadorEvento.consultarUltimoEventoPorTipos(tipoEventos, idCaso);
	}

	@Override
	public void creaEventoReAperturaCaso(DatosBasicosCasoDTO datosBasicosCasoDTO, String usuarioModificacion) {
		
		List<String> tipoEventos = new ArrayList<String>();
		tipoEventos.add(UtilDominios.TIPO_EVENTO_CASO_CERRADO);
		
		List<Evento> eventosCaso = consultarEventosCasoPorTipoEvento(datosBasicosCasoDTO.getCaso().getIdCaso(), tipoEventos);
		if(eventosCaso != null && !eventosCaso.isEmpty()) {
			manejadorEvento.eliminar(eventosCaso.get(0));
		}
		 
		Date fechaActual = new Date();

		Evento evento = new Evento();
		evento.setCaso(datosBasicosCasoDTO.getCaso());
		evento.setIdCaso(datosBasicosCasoDTO.getCaso().getIdCaso());
		evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		evento.setIdUsuarioModificacion(usuarioModificacion);
		evento.setObservaciones(datosBasicosCasoDTO.getObservacionesReAperturaCaso());
		evento.setTipoEvento(UtilDominios.TIPO_EVENTO_CASO_REABIERTO);
		evento.setFechaEvento(fechaActual);
		evento.setFechaUltimaModificacion(fechaActual);

		manejadorEvento.crear(evento);
		
	}
	
	@Override
	public List<EventoDTO> consultarEventosPorTipoOrdernadoFechaDesc(List<String> tipoEventos,Long idCaso){
		return manejadorEvento.consultarEventosPorTipoOrdernadoFechaDesc(tipoEventos, idCaso);
	}
	
	
	// protected region metodos adicionales end
	
}
