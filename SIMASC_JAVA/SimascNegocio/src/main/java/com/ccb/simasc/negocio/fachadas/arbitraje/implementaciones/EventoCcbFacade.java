package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.integracion.manejadores.ManejadorEventoCcb;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaEventoCcb;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICentroFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDesarrolloProfesionalFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoCcbFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaEventoCcbFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.EventoCcbDTO;
import com.ccb.simasc.transversal.entidades.Centro;
import com.ccb.simasc.transversal.entidades.DesarrolloProfesional;
import com.ccb.simasc.transversal.entidades.EventoCcb;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaEventoCcb;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link EventoCcb}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class EventoCcbFacade extends AccesoFacade<EventoCcb, Long, EventoCcbDTO> implements IEventoCcbFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorEventoCcb manejadorEventoCcb;
	
	@EJB
	private ManejadorRolPersona manejadorRolPersona;
	
	@EJB
	private ManejadorPersonaEventoCcb manejadorPersonaEventoCcb;
	
	@EJB
	private IPersonaFacade personaFacade;
	
	@EJB
	private IAgendaPersonaFacade agendaPersonaFacade;
	
	@EJB
	private IPersonaEventoCcbFacade personaEventoCcbFacade;
	
	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	@EJB
	private IDesarrolloProfesionalFacade desarrolloProfesionalFacade;
	
	@EJB
	private ICentroFacade centroFacade;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorEventoCcb;
	}

	@Override
	public EventoCcbDTO transformarSinDependencias(EventoCcb obj) {
		return new EventoCcbDTO().transformarSinDependencias(obj);
	}

	@Override
	public EventoCcbDTO transformarConDependencias(EventoCcb obj) {
		return new EventoCcbDTO().transformarConDependencias(obj);
	}

	@Override
	public EventoCcb transformarEntidadConDependencias(EventoCcb obj) {
		return new EventoCcbDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public EventoCcb transformarEntidadSinDependencias(EventoCcb obj) {
		return new EventoCcbDTO().transformarEntidadSinDependencias(obj);
	}

	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoCcbFacade#
	 * consultarEventos(java.util.Date, java.util.Date)
	 */
	@Override
	public List<EventoCcbDTO> consultarEventos(Date fechaDesde, Date fechaHasta) {
		return (List<EventoCcbDTO>) transformarColeccionSinDependencias(
				manejadorEventoCcb.consultarEventos(fechaDesde, fechaHasta), new ArrayList<EventoCcbDTO>());
	}
	
	@Override
	public EventoCcb consultarEvento(Long idEventoCcb) {
		return transformarEntidadConDependencias(buscar(idEventoCcb));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoCcbFacade#
	 * cancelarEvento(java.lang.Long)
	 */
	@Override
	public void cancelarEvento(Long idEventoCcb) {
		EventoCcb evento = buscar(idEventoCcb);
		liberarAgendaEventoConciliadores(evento, evento.getPersonaEventoCcbList(), null, true);
		evento.setFechaUltimaModificacion(new Date());
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null)
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		evento.setIdUsuarioModificacion(usuarioModificacion);
		evento.setEstado(UtilDominios.ESTADO_EVENTO_CCB_CANCELADO);
		actualizar(evento);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoCcbFacade#
	 * liberarAgendaEventoConciliadores(com.ccb.simasc.transversal.entidades.
	 * EventoCcb, java.util.List, java.util.List, boolean)
	 */
	@Override
	public boolean liberarAgendaEventoConciliadores(EventoCcb eventoCcb, List<PersonaEventoCcb> conciliadores,
			List<Long> centros, boolean desasociar) {
		List<Long> personas = null;
		if (conciliadores != null && !conciliadores.isEmpty()) {
			personas = new ArrayList<Long>();
			for (PersonaEventoCcb personaEventoCcb : conciliadores) {
				personas.add(personaEventoCcb.getPersonaEventoCcbPK().getIdPersona());
				personaEventoCcbFacade.actualizarAsociacion(eventoCcb.getIdEventoCcb(),
						personaEventoCcb.getPersonaEventoCcbPK().getIdPersona(), null,
						UtilDominios.ESTADO_REGISTRO_INACTIVO);
			}
		}
		agendaPersonaFacade.liberarAgendaEvento(eventoCcb.getIdEventoCcb(), personas);
		return !desasociar ? desasociar
				: enviarCorreoPorTipo(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO302.toString(),
								eventoCcb.getDescripcion()),
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO305.toString(),
								eventoCcb.getDescripcion(),
								UtilOperaciones.formatearFechaFormato(eventoCcb.getFechaInicio(),
										UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA),
								eventoCcb.getLugar(), UtilOperaciones.formatearFechaFormato(eventoCcb.getFechaInicio(),
										UtilConstantes.FORMATO_FECHA_HORA_MINUTOS)),
						conciliadores, centros);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoCcbFacade#
	 * actualizarEvento(com.ccb.simasc.transversal.entidades.EventoCcb,
	 * java.util.List)
	 */
	@Override
	public boolean actualizarEvento(EventoCcb evento, List<Long> centros) {
		EventoCcb eventoCcb = transformarEntidadSinDependencias(evento);
		boolean envio = false;
		if (eventoCcb.getIdEventoCcb() == null) {
			eventoCcb.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			crear(eventoCcb);
			envio = notificarCreacionEvento(evento.getPersonaEventoCcbList(), eventoCcb, centros);
		} else {
			EventoCcb[] eventos = new EventoCcb[3]; 
			List<PersonaEventoCcb> notificados = null;
			EventoCcb eventoExistente = buscar(eventoCcb.getIdEventoCcb());
			if (eventoExistente == null)
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR569.toString()));

			List<PersonaEventoCcb> eliminados = obtenerConciliadoresEliminados(evento.getPersonaEventoCcbList(),
					eventoExistente.getPersonaEventoCcbList());
			eventos[0] = eventoExistente;
			liberarAgendaEventoConciliadores(eventoExistente, eliminados, centros, false);
			eventoExistente = buscar(eventoCcb.getIdEventoCcb());
			if (!eventoExistente.comparar(eventoCcb)) {
				notificados = notificarModificacionEvento(eventoExistente, eventoCcb, centros);
				actualizar(eventoCcb);
			}
			List<PersonaEventoCcb> adicionados = notificarConciliadoresAdicionados(evento.getPersonaEventoCcbList(),
					eventoExistente.getPersonaEventoCcbList(), eventoCcb, centros);
			eventos[1] = eventoCcb;
			
			envio = enviarNotificaciones(eventos, eliminados, adicionados, notificados, centros);
		}
		return envio;
	}
	
	/**
	 * Método que se encarga de realizar el envio de correos, tanto programacion como modificacion y cancelacion
	 * cuando se presenta una modificacion de un evento
	 * @param eventos
	 * @param eliminados
	 * @param adicionados
	 * @param notificados
	 * @param centros
	 * @return
	 */
	private boolean enviarNotificaciones(EventoCcb[] eventos, List<PersonaEventoCcb> eliminados,
			List<PersonaEventoCcb> adicionados, List<PersonaEventoCcb> notificados, List<Long> centros) {
		boolean envio = false;
		// notificar personas que se retiraron del evento
		if (!eliminados.isEmpty()) {
			envio = envio
					|| enviarCorreoPorTipo(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO302.toString(),
									eventos[0].getDescripcion()),
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO305.toString(),
									eventos[0].getDescripcion(),
									UtilOperaciones.formatearFechaFormato(eventos[0].getFechaInicio(),
											UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA),
									eventos[0].getLugar(), UtilOperaciones.formatearFechaFormato(
											eventos[0].getFechaInicio(), UtilConstantes.FORMATO_FECHA_HORA_MINUTOS)),
							eliminados, centros);
		}
		// notificar a los conciliadores que ya se encontraban asociados de la
		// modificacion del evento
		if (notificados != null) {
			envio = envio
					|| enviarCorreoPorTipo(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO301.toString(),
									eventos[1].getDescripcion()),
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO304.toString(),
									eventos[1].getDescripcion(),
									UtilOperaciones.formatearFechaFormato(eventos[1].getFechaInicio(),
											UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA),
									eventos[1].getLugar(), UtilOperaciones.formatearFechaFormato(
											eventos[1].getFechaInicio(), UtilConstantes.FORMATO_FECHA_HORA_MINUTOS)),
							notificados, centros);
		}
		// notificar a los conciliadores asociados al evento
		if (!adicionados.isEmpty()) {
			envio = envio
					|| enviarCorreoPorTipo(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO300.toString(),
									eventos[1].getDescripcion()),
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO303.toString(),
									eventos[1].getDescripcion(),
									UtilOperaciones.formatearFechaFormato(eventos[1].getFechaInicio(),
											UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA),
									eventos[1].getLugar(), UtilOperaciones.formatearFechaFormato(
											eventos[1].getFechaInicio(), UtilConstantes.FORMATO_FECHA_HORA_MINUTOS)),
							notificados, centros);
		}

		return envio;
	}

	/**
	 * Método que realiza la asociacion los conciliadores al caso y regresa el
	 * listado de los conciliadores asociados
	 * 
	 * @param buscarAsociacion
	 *            si se envia en true verifica si el conciliador ya se encuentra
	 *            asociado a la lista de conciliadores existentes
	 * @param conciliadores
	 *            lista de conciliadores a asociar
	 * @param conciliadoresExistentes
	 *            lista de conciliadores asociados al evento al momento
	 * @param evento
	 *            evento programado por la ccb
	 * @return lista de conciliadores que se adicionaron al evento
	 */
	private List<PersonaEventoCcb> crearAsociacionPersonasEvento(boolean buscar, List<PersonaEventoCcb> conciliadores,
			List<PersonaEventoCcb> conciliadoresExistentes, EventoCcb evento, boolean bloquear) {
		List<PersonaEventoCcb> notificados = new ArrayList<PersonaEventoCcb>();
		for (PersonaEventoCcb personaEventoCcb : conciliadores) {
			if (buscar && !buscarAsociacion(conciliadoresExistentes,
					personaEventoCcb.getPersonaEventoCcbPK().getIdPersona(), true)) {
				personaEventoCcbFacade.actualizarAsociacion(evento.getIdEventoCcb(),
						personaEventoCcb.getPersonaEventoCcbPK().getIdPersona(), null,
						UtilDominios.ESTADO_REGISTRO_ACTIVO);
				if(bloquear)
					agendaPersonaFacade.bloquearAgendaPersona(personaEventoCcb.getPersonaEventoCcbPK().getIdPersona(),
						UtilDominios.ESTADO_AGENDA_PERSONA_PROGRAMADA, evento.getTipoEventoCcb(),
						evento.getFechaInicio(), evento.getFechaFin(), null, null);
				notificados.add(personaEventoCcb);
			}
		}
		return notificados;
	}

	/**
	 * Método que retorna el listado de personas que se eliminaron del evento
	 * 
	 * @param conciliadores
	 *            lista de conciliadores modificada
	 * @param conciliadoresExistentes
	 *            lista de conciliadores asociados al evento
	 * @return lista de conciliadores que se eliminaron del evento
	 */
	private List<PersonaEventoCcb> obtenerConciliadoresEliminados(List<PersonaEventoCcb> conciliadores,
			List<PersonaEventoCcb> conciliadoresExistentes) {
		List<PersonaEventoCcb> notificados = new ArrayList<PersonaEventoCcb>();
		for (PersonaEventoCcb personaEventoCcb : conciliadoresExistentes) {
			if (!buscarAsociacion(conciliadores, personaEventoCcb.getPersonaEventoCcbPK().getIdPersona(), false))
				notificados.add(personaEventoCcb);
		}
		return notificados;
	}

	/**
	 * Método que realiza el proceso cuando se programa un nuevo evento y se
	 * encarga de enviar la notificacion
	 * 
	 * @param conciliadoresRecibidos
	 * @param evento
	 * @return false si el envio de las notificaciones fue correcto
	 */
	private boolean notificarCreacionEvento(List<PersonaEventoCcb> conciliadoresRecibidos, EventoCcb evento,
			List<Long> centros) {
		String personasCruce = agendaPersonaFacade.validarDisponibilidadPersonas(conciliadoresRecibidos,
				evento.getFechaInicio(), evento.getFechaFin(), null , null);
		if (personasCruce != null)
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR570.toString(), personasCruce));

		List<PersonaEventoCcb> notificados = crearAsociacionPersonasEvento(true, conciliadoresRecibidos,
				new ArrayList<PersonaEventoCcb>(), evento, true);

		return enviarCorreoPorTipo(
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO300.toString(),
						evento.getDescripcion()),
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO303.toString(), evento.getDescripcion(),
						UtilOperaciones.formatearFechaFormato(evento.getFechaInicio(),
								UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA),
						evento.getLugar(), UtilOperaciones.formatearFechaFormato(evento.getFechaInicio(),
								UtilConstantes.FORMATO_FECHA_HORA_MINUTOS)),
				notificados, centros);
	}

	/**
	 * Método que realiza el proceso cuando se Modifican los datos de un evento
	 * programado por la CCB y se encarga de enviar la notificacion
	 * 
	 * @param conciliadoresExistentes
	 * @param evento
	 * @return false si el envio de las notificaciones fue correcto
	 */
	private List<PersonaEventoCcb> notificarModificacionEvento(EventoCcb eventoExistente, EventoCcb evento, List<Long> centros) {
		String personasCruce= agendaPersonaFacade.validarDisponibilidadPersonas(
				eventoExistente.getPersonaEventoCcbList(), evento.getFechaInicio(), evento.getFechaFin(),
				eventoExistente.getFechaInicio(), eventoExistente.getFechaFin());
		if (personasCruce != null)
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR570.toString(), personasCruce));

		liberarAgendaEventoConciliadores(eventoExistente, eventoExistente.getPersonaEventoCcbList(), null, false);
		List<PersonaEventoCcb> notificados = crearAsociacionPersonasEvento(true, eventoExistente.getPersonaEventoCcbList(),
				new ArrayList<PersonaEventoCcb>(), evento, true);
		
		return notificados;
	}

	/**
	 * Método que realiza el proceso cuando se adicionan conciliadores al evento
	 * y se encarga de enviar la notificacion
	 * 
	 * @param conciliadoresRecibidos
	 * @param conciliadoresExistentes
	 * @param evento
	 * @return false si el envio de las notificaciones fue correcto
	 */
	private List<PersonaEventoCcb> notificarConciliadoresAdicionados(List<PersonaEventoCcb> conciliadoresRecibidos,
			List<PersonaEventoCcb> conciliadoresExistentes, EventoCcb evento, List<Long> centros) {
		List<PersonaEventoCcb> notificados = crearAsociacionPersonasEvento(true, conciliadoresRecibidos,
				conciliadoresExistentes, evento, false);

		String personasCruce = agendaPersonaFacade.validarDisponibilidadPersonas(notificados, evento.getFechaInicio(),
				evento.getFechaFin(), null, null);
		if (personasCruce != null)
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR570.toString(), personasCruce));
		
		for (PersonaEventoCcb personaEventoCcb : notificados) {
			agendaPersonaFacade.bloquearAgendaPersona(personaEventoCcb.getPersonaEventoCcbPK().getIdPersona(),
				UtilDominios.ESTADO_AGENDA_PERSONA_PROGRAMADA, evento.getTipoEventoCcb(),
				evento.getFechaInicio(), evento.getFechaFin(), null, null);
		}
		return notificados;
	}

	/**
	 * Método que verifica si el conciliador ya se encuentra en la lista de
	 * personas asociadas al evento
	 * 
	 * @param conciliadores
	 *            lista de conciliadores asociados al evento
	 * @param idConciliador
	 * @return true si se encuentra en la lista
	 */
	private boolean buscarAsociacion(List<PersonaEventoCcb> conciliadores, Long idConciliador, boolean estado) {
		for (PersonaEventoCcb personaEventoCcb : conciliadores) {
			if (personaEventoCcb.getPersonaEventoCcbPK().getIdPersona().equals(idConciliador) && (estado
					? UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(personaEventoCcb.getEstadoRegistro()) : true))
				return true;
		}
		return false;
	}

	/**
	 * Método que se encarga de realizar el envio de los correos para los
	 * conciliadores recibidos y de igual forma enviar el correo de control al
	 * jefe de conciliación
	 * 
	 * @param asunto
	 *            asunto del correo
	 * @param cuerpoCorreo
	 *            texto del correo
	 * @param personaEvento
	 *            listado de personas a notificar
	 * @return false si el envio fue correcto
	 */
	private boolean enviarCorreoPorTipo(String asunto, String cuerpoCorreo, List<PersonaEventoCcb> personaEvento,
			List<Long> centros) {
		List<Persona> personas = new ArrayList<Persona>();
		for (PersonaEventoCcb persona : personaEvento) {
			personas.add(personaFacade.buscar(persona.getPersonaEventoCcbPK().getIdPersona()));
		}

		personas.addAll(manejadorRolPersona.consultarPesonasPorRolPersonaCentro(
				Arrays.asList(UtilDominios.ROL_PERSONA_JEFE_DE_CONCILIACION), centros, new Date()));

		return !personaEvento.isEmpty() ? correoRolPersonaCasoFacade.envioDeCorreo(asunto, personas, null, null,
				Arrays.asList(cuerpoCorreo), null, null, null, false) : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoCcbFacade#
	 * registrarResultadoEvento(com.ccb.simasc.transversal.entidades.EventoCcb)
	 */
	@Override
	public void registrarResultadoEvento(EventoCcb eventoCcb) {
		EventoCcb evento = transformarEntidadSinDependencias(eventoCcb);
		evento.setEstado(UtilDominios.ESTADO_EVENTO_CCB_REALIZADO);
		actualizar(evento);
		manejadorPersonaEventoCcb.marcarNoAsistencia(evento.getIdEventoCcb());

		for (PersonaEventoCcb personaEvento : eventoCcb.getPersonaEventoCcbList()) {
			if(UtilDominios.ESTADO_REGISTRO_INACTIVO.equals(personaEvento.getEstadoRegistro())) continue;
			personaEventoCcbFacade.actualizarAsociacion(evento.getIdEventoCcb(),
					personaEvento.getPersonaEventoCcbPK().getIdPersona(), UtilDominios.ESTADO_ASISTENCIA_EVENTO_ASISTE,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			if (eventoCcb.isEducacionContinua()) {
				DesarrolloProfesional desarrolloProfesional = new DesarrolloProfesional();
				desarrolloProfesional.setIdPersona(personaEvento.getPersonaEventoCcbPK().getIdPersona());
				desarrolloProfesional.setTipoCurso(evento.getTipoCapacitacion());
				desarrolloProfesional.setMateriaCurso(evento.getMateria());
				desarrolloProfesional.setMateriaCursoNoDefinida(evento.getMateriaOtros());
				Centro centro = centroFacade.buscar(eventoCcb.getCentros().get(0));
				desarrolloProfesional.setInstitucion(centro.getNombre());
				desarrolloProfesional.setFechaInicial(evento.getFechaInicio());
				desarrolloProfesional.setFechaFinal(evento.getFechaFin());
				desarrolloProfesional.setNumeroHoras(
						UtilOperaciones.calcularDiasEntreFechasI(evento.getFechaInicio(), evento.getFechaFin()) * 24);
				desarrolloProfesional.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				desarrolloProfesional.setNombre(evento.getDescripcion());
				desarrolloProfesional.setTipoDesarrolloProfesional(UtilDominios.CODIGO_TIPO_DESARROLLO_PROFESIONAL_CURSO);
				desarrolloProfesionalFacade.crear(desarrolloProfesional);
			}
		}
	}
	
	// protected region metodos adicionales end
}
