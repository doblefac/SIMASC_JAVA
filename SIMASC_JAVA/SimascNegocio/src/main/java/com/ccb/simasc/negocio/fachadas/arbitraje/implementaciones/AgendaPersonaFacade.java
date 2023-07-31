package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorAgendaPersona;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.transversal.dto.AgendaPersonaDTO;
import com.ccb.simasc.transversal.entidades.AgendaPersona;
import com.ccb.simasc.transversal.entidades.EventoCcb;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaEventoCcb;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link AgendaPersona}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class AgendaPersonaFacade extends AccesoFacade<AgendaPersona, Long, AgendaPersonaDTO> implements IAgendaPersonaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorAgendaPersona manejadorAgendaPersona; 
	
	@EJB
	private IPersonaFacade personaFacade;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;

	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorAgendaPersona;
	}

	@Override
	public AgendaPersonaDTO transformarSinDependencias(AgendaPersona obj) {
		return new AgendaPersonaDTO().transformarSinDependencias(obj);
	}

	@Override
	public AgendaPersonaDTO transformarConDependencias(AgendaPersona obj) {
		return new AgendaPersonaDTO().transformarConDependencias(obj);
	}

	@Override
	public AgendaPersona transformarEntidadConDependencias(AgendaPersona obj) {
		return new AgendaPersonaDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public AgendaPersona transformarEntidadSinDependencias(AgendaPersona obj) {
		return new AgendaPersonaDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 */
	@Override
	public  List<Persona> consultaFuncionariosAgenda(Long idCentro) {
			return manejadorAgendaPersona.buscaPersonasAgenda(idCentro);
	}
	
	@Override
	public void liberarAgendamientoPersona(Long idPersona, Long idAudiencia){
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if(appContext!= null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null  ){
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}
		List<InformacionFiltro> filtros = new ArrayList<InformacionFiltro>();
		InformacionFiltro filtro1 = new InformacionFiltro(TipoFiltro.EXACTO,AgendaPersona.ENTIDAD_AGENDA_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		InformacionFiltro filtro2 = new InformacionFiltro(TipoFiltro.EXACTO,AgendaPersona.ENTIDAD_AGENDA_PERSONA_ID_PERSONA, idPersona);
		InformacionFiltro filtro3 = new InformacionFiltro(TipoFiltro.EXACTO,AgendaPersona.ENTIDAD_AGENDA_PERSONA_ID_AUDIENCIA, idAudiencia);
		filtros.add(filtro1);
		filtros.add(filtro2);
		filtros.add(filtro3);
		List<AgendaPersona> agendaPersona = manejadorAgendaPersona.consultar(filtros, null,null);
		if(agendaPersona != null){
			for (AgendaPersona agendaFor : agendaPersona) {
				agendaFor.setEstado(UtilDominios.ESTADO_AGENDA_PERSONA_CANCELADA);
				agendaFor.setFechaUltimaModificacion(new Date());
				agendaFor.setIdUsuarioModificacion(usuarioModificacion);
				manejadorAgendaPersona.actualizar(agendaFor);
			}			
		}		
	}
	
	@Override
	public void bloquearAgendaPersona(Long idPersona, String estado, String tipoActividad, Date fechaInicio, Date fechaFin,
			Long idAudiencia,String observaciones){
		AgendaPersona agenda = new AgendaPersona();
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;

		if(appContext!= null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null  ){
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}	
		agenda.setIdPersona(idPersona);
		agenda.setTipoActividadAgenda(tipoActividad);
		agenda.setFechaFin(fechaFin);
		agenda.setFechaInicio(fechaInicio);
		agenda.setFechaUltimaModificacion(new Date());
		agenda.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		agenda.setIdUsuarioModificacion(usuarioModificacion);
		
		if(idAudiencia != null){
			agenda.setIdAudiencia(idAudiencia);
		}
		if(observaciones != null){
			agenda.setObservaciones(observaciones);
		}
		if(estado != null){
			agenda.setEstado(estado);
		}
		manejadorAgendaPersona.crear(agenda);
		
	}
	
	@Override
	public void eliminarAgendaFechaPersona(Long idAgenda){
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.EXACTO, AgendaPersona.ENTIDAD_AGENDA_PERSONA_PK,
				idAgenda);
		List<InformacionFiltro> filtrosAgendaPersona = new ArrayList<>();
		filtrosAgendaPersona.add(filtro);
		
		if(appContext!= null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null  ){
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}	
		
		List<AgendaPersona> agendaPersona = manejadorAgendaPersona.consultar(filtrosAgendaPersona, null, null);
		
		for (AgendaPersona element : agendaPersona) {
			element.setEstado(UtilDominios.ESTADO_AGENDA_PERSONA_CANCELADA);
			element.setFechaUltimaModificacion(new Date());
			element.setIdUsuarioModificacion(usuarioModificacion);
			manejadorAgendaPersona.crear(element);
		}	
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendaPersonaFacade
	 * #actualizarAgendaPersona(com.ccb.simasc.transversal.dto.AgendaPersonaDTO)
	 */
	@Override
	public void actualizarAgendaPersona(AgendaPersonaDTO agendaPersonaDTO) {
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if(appContext!= null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null  )
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		try {
			AgendaPersona agendaPersona = buscar(agendaPersonaDTO.getIdAgendaPersona());
			agendaPersona.setObservaciones(agendaPersonaDTO.getObservaciones());
			agendaPersona.setEstado(UtilDominios.ESTADO_AGENDA_PERSONA_VALIDADA);
			agendaPersona.setFechaUltimaModificacion(new Date());
			agendaPersona.setIdUsuarioModificacion(usuarioModificacion);
			agendaPersona.setJustificacionValida(agendaPersonaDTO.getJustificacionValida());
			agendaPersona.setCumplioHorario(agendaPersonaDTO.getCumplioHorario());
			actualizar(agendaPersona);
		}catch(Exception e) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR550.toString()));
		}
	}

	@Override
	public void liberarAgendaEvento(Long idEventoCcb, List<Long> personas) {
		List<AgendaPersona> agendas = manejadorAgendaPersona.obtenerAgendaEvento(idEventoCcb, personas);
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if(appContext!= null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null  )
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		for (AgendaPersona agendaPersona : agendas) {
			agendaPersona.setIdUsuarioModificacion(usuarioModificacion);
			agendaPersona.setFechaUltimaModificacion(new Date());
			agendaPersona.setEstado(UtilDominios.ESTADO_AGENDA_PERSONA_CANCELADA);
			actualizar(agendaPersona);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendaPersonaFacade
	 * #crearAgendaPersona(com.ccb.simasc.transversal.entidades.AgendaPersona)
	 */
	@Override
	public void crearAgendaPersona(AgendaPersona agenda) {

		if (manejadorAgendaPersona.validarDisponibilidadPersona(agenda.getIdPersona(), agenda.getFechaInicio(), agenda.getFechaFin()))
			crear(agenda);
		else
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR463.toString()));
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendaPersonaFacade
	 * #validarDisponibilidadPersonas(java.util.List, java.util.Date,
	 * java.util.Date)
	 */
	public String validarDisponibilidadPersonas(List<PersonaEventoCcb> personas, Date fechaInicio, Date fechaFin, Date fechaInicioExistente, Date fechaFinExistente) {
		StringBuilder nombres = new StringBuilder();
		for (PersonaEventoCcb personaEventoCcb : personas) {
			if ((personaEventoCcb.getEstadoRegistro() != null
					? UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(personaEventoCcb.getEstadoRegistro())
					: true) && !manejadorAgendaPersona.validarDisponibilidadPersonaExcluirPeriodo(
							personaEventoCcb.getPersonaEventoCcbPK().getIdPersona(), fechaInicio, fechaFin,
							fechaInicioExistente, fechaFinExistente)) {
				Persona persona = personaFacade.buscar(personaEventoCcb.getPersonaEventoCcbPK().getIdPersona());
				nombres.append(persona.getNombreCompleto()).append(", ");
			}
		}
		return nombres.toString().length() > 2 ? nombres.toString().substring(0, nombres.toString().length() - 2)
				: null;
	}


	// protected region metodos adicionales end
	
}
