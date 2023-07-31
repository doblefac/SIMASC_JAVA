package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta sección sus modificaciones

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorTranscripcion;
import com.ccb.simasc.integracion.manejadores.ManejadorUsuario;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IOrquestadorAlertasFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITranscripcionFacade;
import com.ccb.simasc.transversal.dto.AudienciaPendienteTranscripcionDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.DatosReasignacionTranscripcionDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.TranscripcionDTO;
import com.ccb.simasc.transversal.dto.TranscripcionPendienteDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Transcripcion;
import com.ccb.simasc.transversal.entidades.Usuario;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import co.org.ccb.simasc.comun.correos.ArrayOfAdjuntoDTO;
import co.org.ccb.simasc.comun.correos.ArrayOfString;
import co.org.ccb.simasc.comun.correos.EnvioCorreoInDTO;
import co.org.ccb.simasc.comun.correos.EnvioCorreoServiceSoapProxy;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Transcripcion}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class TranscripcionFacade extends AccesoFacade<Transcripcion, Long, TranscripcionDTO>
		implements ITranscripcionFacade {

	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	private static final Logger logger = LogManager.getLogger(TranscripcionFacade.class.getName());

	@EJB
	private ManejadorTranscripcion manejadorTranscripcion;

	@EJB
	private ManejadorUsuario manejadorUsuario;

	@EJB
	private ManejadorAudiencia manejadorAudiencia;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorDocumento manejadorDocumento;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private IEventoFacade eventoFacade;

	@EJB
	private IDominioFacade dominioFacade;

	@EJB
	private IPersonaFacade personaFacade;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	@EJB
	private IOrquestadorAlertasFacade orquestadorAlertasFacade;
	
	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;
	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorTranscripcion;
	}

	@Override
	public TranscripcionDTO transformarSinDependencias(Transcripcion obj) {
		TranscripcionDTO dto = new TranscripcionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public TranscripcionDTO transformarConDependencias(Transcripcion obj) {
		TranscripcionDTO dto = new TranscripcionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Transcripcion transformarEntidadConDependencias(Transcripcion obj) {
		Transcripcion dto = new Transcripcion();
		dto = new TranscripcionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Transcripcion transformarEntidadSinDependencias(Transcripcion obj) {
		Transcripcion dto = new Transcripcion();
		dto = new TranscripcionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	@Override
	public void crearTranscripcion(Transcripcion transcripcion, ContextoDeSesion sesion) {
		Usuario usuario = manejadorUsuario.buscar(sesion.getIdUsuario());
		transcripcion.setIdPersona(usuario.getIdPersona());
		// transcripcion.setEstado(UtilDominios.ESTADO_TRANSCRIPCION_POR_TRANSCRIBIR);
		transcripcion.setEstado(UtilDominios.ESTADO_TRANSCRIPCION_PENDIENTE);
		transcripcion.setTiempoFinal(new Long(0));
		transcripcion.setTiempoInicial(new Long(0));
		transcripcion.setTiempoTranscrito(new Long(0));
		transcripcion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		transcripcion.setIdUsuarioModificacion(sesion.getIdUsuario());
		transcripcion.setFechaUltimaModificacion(new Date());
		manejadorTranscripcion.crear(transcripcion);
	}

	@Override
	public void cargarTranscripciones(List<Transcripcion> transcripciones, ContextoDeSesion sesion) {
		Usuario usuario = manejadorUsuario.buscar(sesion.getIdUsuario());
		Audiencia audiencia = null;
		Caso caso = null;
		for (Transcripcion t : transcripciones) {
			crearTranscripcion(t, sesion);
		}

		if (!transcripciones.isEmpty()) {
			Documento documento = manejadorDocumento.buscar(transcripciones.get(0).getIdDocAudioFuente());
			audiencia = documento.getAudiencia();
			caso = documento.getCaso();
		}

		// ******************** GENERACION DE EVENTO ********************
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String tipoEventoDev = UtilDominios.TIPO_EVENTO_CARGUE_TRANSCRIPCION_AUDIO;
		String observaciones = "Se realizó el cargue de la(s) transcripción(es) del(los) audio(s) de la audiencia"
				+ " ["
				+ dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_AUDIENCIA, audiencia.getTipoAudiencia())
				+ " - " + audiencia.getIdAudiencia() + "] realizada el [" + df.format(audiencia.getHoraInicio()) + "]";
		eventoFacade.registrarEvento(caso.getIdCaso(), tipoEventoDev, observaciones, usuario.getUsuarioLogin());

		// ******************** ENVIO DE CORREO AL ABOGADO **************
		List<String> cuerpoCorreo = new ArrayList<>();
		cuerpoCorreo.add(" Se ha realizado cargue de la(s) transcripción(es) del(los) audio(s) de la audiencia ["
				+ dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_AUDIENCIA, audiencia.getTipoAudiencia())
				+ "] " + "realizada el [" + df.format(audiencia.getHoraInicio()) + "] del caso [" + caso.getIdCaso()
				+ " – " + caso.getNombre() + "]  ");

		List<Persona> abogados = (List<Persona>) personaFacade.consultarPorRolCaso(caso.getIdCaso(),
				UtilDominios.ROL_PERSONA_ABOGADO);
		List<CorreoElectronico> correosAbogado = correoElectronicoFacade
				.consultaCorreosPersona(abogados.get(0).getIdPersona());
		if (correosAbogado.isEmpty()) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR152.toString()));
		}
		List<CorreoElectronicoDTO> correosAbogadoDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade
				.transformarColeccionConDependencias(correosAbogado, new ArrayList<CorreoElectronicoDTO>());

		List<Persona> secretarios = (List<Persona>) personaFacade.consultarPorRolCaso(caso.getIdCaso(),
				UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
		if (!secretarios.isEmpty()) {
			List<CorreoElectronico> correosSecretario = correoElectronicoFacade
					.consultaCorreosPersona(secretarios.get(0).getIdPersona());
			List<CorreoElectronicoDTO> correosSecretarioDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade
					.transformarColeccionConDependencias(correosSecretario, new ArrayList<CorreoElectronicoDTO>());
			List<DocumentoDTO> adjuntos = new ArrayList<DocumentoDTO>();

			// Parametros
			ParametrosEnvioCorreoDTO parametrosCorreo = new ParametrosEnvioCorreoDTO();
			parametrosCorreo.setAsunto("Cargue de transcripción de audio");
			parametrosCorreo.setCuerpoCorreo(cuerpoCorreo);
			parametrosCorreo.setRolPersonaCaso(correosSecretarioDTO);
			parametrosCorreo.setRemitente(correosAbogadoDTO.get(0));
			parametrosCorreo.setIdCaso(caso.getIdCaso());
			parametrosCorreo.setAdjuntos(adjuntos);
			parametrosCorreo.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);

			correoRolPersonaCasoFacade.enviarCorreo(parametrosCorreo);
		}
	}

	@Override
	public List<AudienciaPendienteTranscripcionDTO> casosAudienciasPendientesTranscripciones(String idUsuario) {
		List<AudienciaPendienteTranscripcionDTO> list = manejadorTranscripcion
				.casosAudienciasPendientesTranscripciones(idUsuario);
		return list;
	}

	@Override
	public List<AudienciaPendienteTranscripcionDTO> actualizarCasosAudienciasPendientesTranscripciones(
			List<AudienciaPendienteTranscripcionDTO> listAudienciaPendienteTranscripcionDTO, String idUsuario) {

		for (AudienciaPendienteTranscripcionDTO dto : listAudienciaPendienteTranscripcionDTO) {
			Transcripcion transcripcion = manejadorTranscripcion.buscar(dto.getIdTranscricpion().longValue());
			transcripcion.setTiempoTranscrito(dto.getMinutosTranscritos().longValue());
			transcripcion.setFechaEntregaTranscripcion(dto.getFechaEntregaTranscripcion());
			transcripcion.setFechaInicioTranscripcion(dto.getFechaInicioTranscripcion());
			transcripcion.setFechaUltimaModificacion(new Date());
			Documento documento = manejadorDocumento.buscar(dto.getIdDocumento().longValue());
			documento.setDuracion(dto.getMinutosTranscribir());
			if (transcripcion.getTiempoTranscrito() == documento.getDuracion().longValue()) {
				// documento.setEstadoDigitalizacion(UtilDominios.ESTADO_TRANSCRIPCION_TRANSCRIPCION_FINALIZADA);
				transcripcion.setEstado(UtilDominios.ESTADO_TRANSCRIPCION_FINALIZADA);
			}
			manejadorTranscripcion.actualizar(transcripcion);
			manejadorDocumento.actualizar(documento);
			
			//Alerta A18 SobreAsignación 
			orquestadorAlertasFacade.alertaSOBASGM(transcripcion.getIdPersona());

		}
		List<AudienciaPendienteTranscripcionDTO> list = casosAudienciasPendientesTranscripciones(idUsuario);
		return list;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITranscripcionFacade
	 * #consultarTranscripcionesPendientes(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<TranscripcionPendienteDTO> consultarTranscripcionesPendientes(Long idCaso, Long idAudiencia) {
		return manejadorTranscripcion.consultarTranscripcionesPendientes(idCaso, idAudiencia);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITranscripcionFacade
	 * #reasignarTranscripcion(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void reasignarTranscripcionCompleta(TranscripcionDTO transcripcionDTO) throws SIMASCNegocioExcepcion {
		// 1. Consulta la transcripción a reasignar
		Transcripcion transcripcion = manejadorTranscripcion.buscar(transcripcionDTO.getIdTranscripcion());
		Long idPersona = transcripcion.getIdPersona();

		// 2. Valida que la transcripción haya sido encontrada y que la persona
		// a la cual se le esta reasignando no sea la encargada actualmente
		if (transcripcion != null
				&& transcripcion.getIdPersona().intValue() != transcripcionDTO.getIdPersona().intValue()) {

			// 3. Busca a la persona a la cual se le va a asignar la
			// transcripción
			Persona persona = manejadorPersona.buscar(transcripcionDTO.getIdPersona());

			// 4. Valida que la persona a la cual se le va a asignar la
			// transcripción exista
			if (persona != null) {

				// 5. Actualiza la transcripción asignando a la nueva persona
				transcripcion.setPersona(persona);
				transcripcion.setIdPersona(transcripcionDTO.getIdPersona());
				transcripcion.setIdUsuarioModificacion(transcripcionDTO.getIdUsuarioModificacion());
				transcripcion.setFechaUltimaModificacion(new Date());
				manejadorTranscripcion.actualizar(transcripcion);
				
				// 6. Genera las notificaciones de reasignación de la transcripción
				List<DatosReasignacionTranscripcionDTO> datosReasignacionTranscripcion = new ArrayList<DatosReasignacionTranscripcionDTO>();

				DatosReasignacionTranscripcionDTO datosReasignacionTranscripcionDTO = new DatosReasignacionTranscripcionDTO();
				datosReasignacionTranscripcionDTO.setIdPersona(persona.getIdPersona());
				
				Long tiempoATranscribir = (transcripcion.getTiempoFinal() - transcripcion.getTiempoInicial())
						- transcripcion.getTiempoTranscrito();
				datosReasignacionTranscripcionDTO.setTiempoATranscribir(tiempoATranscribir);
				
				datosReasignacionTranscripcion.add(datosReasignacionTranscripcionDTO);

				enviarCorreoReasignacionTranscripcion(idPersona,
						generarNotificacionReasignacionTranscripcion(transcripcion, datosReasignacionTranscripcion));
				
				enviarCorreoReasignacionTranscripcion(persona.getIdPersona(),
						generarNotificacionAsignacionTranscripcion(transcripcion, idPersona, persona.getIdPersona(),
								datosReasignacionTranscripcionDTO.getTiempoATranscribir()));
			}
		}
	}
	
	public Map<String, String> generarNotificacionAsignacionTranscripcion(Transcripcion transcripcion, Long idPersonaAsignada,
			Long idPersonaAsignar, Long tiempoAsignado) {
		Map<String, String> parametrosNotificacion = new HashMap<String, String>();
		
		Documento audioFuente = manejadorDocumento.buscar(transcripcion.getIdDocAudioFuente());
		
		Audiencia audiencia = manejadorAudiencia.buscar(audioFuente.getIdAudiencia());
		String tipoAudiencia = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_AUDIENCIA, audiencia.getTipoAudiencia());
		String fechaAudiencia = new SimpleDateFormat("dd/MM/yyyy").format(audiencia.getHoraInicio());
		
		Persona personaAsignada = manejadorPersona.buscar(idPersonaAsignada);
		
		parametrosNotificacion.put("Asunto", UtilConstantes.ASUNTO_CORREO_ASIGNACION_TRANSCRIPCION);
		
		StringBuffer contenido = new StringBuffer();

		contenido.append("Nos permitimos informarle que ha sido designado para realizar la transcripción de ");
		contenido.append(tiempoAsignado / 60);
		contenido.append(" minutos, del audio ");
		contenido.append(audioFuente.getNombre());
		contenido.append(" de la audiencia ");
		contenido.append(tipoAudiencia);
		contenido.append(" realizada el ");
		contenido.append(fechaAudiencia);
		contenido.append(" del caso ");
		contenido.append(audiencia.getCaso() != null ? audiencia.getCaso().getIdCaso().toString()
				: UtilConstantes.CADENA_VACIA);
		contenido.append(" - ");
		contenido.append(
				audiencia.getCaso() != null ? audiencia.getCaso().getNombre() : UtilConstantes.CADENA_VACIA);
		contenido.append(", el cual se encuentra asignado a ");
		contenido.append(personaAsignada.getNombreCompleto());
		contenido.append(UtilConstantes.CARACTER_PUNTO);
		
		parametrosNotificacion.put("Contenido", contenido.toString());
		
		return parametrosNotificacion;
	}
	
	public Map<String, String> generarNotificacionReasignacionTranscripcion(Transcripcion transcripcion,
			List<DatosReasignacionTranscripcionDTO> datosReasignacionTranscripcion) {
		Map<String, String> parametrosNotificacion = new HashMap<String, String>();

		Documento audioFuente = manejadorDocumento.buscar(transcripcion.getIdDocAudioFuente());

		Audiencia audiencia = manejadorAudiencia.buscar(audioFuente.getIdAudiencia());
		String tipoAudiencia = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_AUDIENCIA,
				audiencia.getTipoAudiencia());
		String fechaAudiencia = new SimpleDateFormat("dd/MM/yyyy").format(audiencia.getHoraInicio());

		parametrosNotificacion.put("Asunto", UtilConstantes.ASUNTO_CORREO_REASIGNACION_TRANSCRIPCION);

		StringBuffer contenido = new StringBuffer();

		contenido.append("Se ha realizado la reasignación de la transcripción del audio de la audiencia ");
		contenido.append(tipoAudiencia != null && !(UtilConstantes.CADENA_VACIA.equals(tipoAudiencia)) ? tipoAudiencia
				: UtilConstantes.CADENA_VACIA);
		contenido.append(" realizada el ");
		contenido.append(fechaAudiencia != null && !(UtilConstantes.CADENA_VACIA.equals(fechaAudiencia))
				? fechaAudiencia : UtilConstantes.CADENA_VACIA);
		contenido.append(" del caso ");
		contenido.append(
				audiencia.getCaso() != null ? audiencia.getCaso().getIdCaso().toString() : UtilConstantes.CADENA_VACIA);
		contenido.append(" - ");
		contenido.append(audiencia.getCaso() != null ? audiencia.getCaso().getNombre() : UtilConstantes.CADENA_VACIA);
		contenido.append(" a los siguientes Funcionarios ");

		String funcionariosAsignados = UtilConstantes.CADENA_VACIA;

		for (DatosReasignacionTranscripcionDTO datosReasignacionTranscripcionDTO : datosReasignacionTranscripcion) {
			Persona funcionario = manejadorPersona.buscar(datosReasignacionTranscripcionDTO.getIdPersona());
			funcionariosAsignados += funcionario.getNombreCompleto() + UtilConstantes.CARACTER_COMA
					+ UtilConstantes.CARACTER_ESPACIO + (datosReasignacionTranscripcionDTO.getTiempoATranscribir() / 60)
					+ UtilConstantes.CARACTER_COMA + UtilConstantes.CARACTER_ESPACIO;
		}

		funcionariosAsignados = funcionariosAsignados.substring(0, funcionariosAsignados.length() - 2)
				+ UtilConstantes.CARACTER_PUNTO;

		contenido.append(funcionariosAsignados);

		parametrosNotificacion.put("Contenido", contenido.toString());
		
		return parametrosNotificacion;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITranscripcionFacade
	 * #reasignarTranscripcionParcial(java.util.List)
	 */
	@Override
	public void reasignarTranscripcionParcial(Long idTranscripcionOriginal,
			List<DatosReasignacionTranscripcionDTO> datosTranscripcionesNuevas) throws SIMASCNegocioExcepcion {
		// 1. Consulta la transcripción a reasignar parcialmente para
		// actualizarla
		Transcripcion transcripcionOriginal = manejadorTranscripcion.buscar(idTranscripcionOriginal);
		Persona personaAsignadaTranscripcionOriginal = manejadorPersona.buscar(transcripcionOriginal.getIdPersona());
		List<DatosReasignacionTranscripcionDTO> datosTranscripcionesNuevasAsignadas = new ArrayList<DatosReasignacionTranscripcionDTO>();

		// 2. Crea las nuevas transcripciones a partir de los datos básicos
		for (DatosReasignacionTranscripcionDTO datosReasignacionTranscripcionDTO : datosTranscripcionesNuevas) {

			// 3. Valida que la persona asignada a la nueva transcripción no sea
			// la misma de la original
			if (datosReasignacionTranscripcionDTO.getIdPersona() != transcripcionOriginal.getIdPersona()) {
				Transcripcion transcripcionNueva = new Transcripcion();
				Persona persona = manejadorPersona.buscar(datosReasignacionTranscripcionDTO.getIdPersona());

				// 4. Valida que la persona a la cual se le va a asignar la
				// transcripción exista
				if (persona != null) {
					transcripcionNueva.setIdPersona(datosReasignacionTranscripcionDTO.getIdPersona());

					// Cálculo del tiempo inicial de la nueva transcripción
					transcripcionNueva.setTiempoInicial(transcripcionOriginal.getTiempoFinal()
							- datosReasignacionTranscripcionDTO.getTiempoATranscribir());

					// Cálculo del nuevo tiempo final de la transcripción
					// original
					transcripcionOriginal.setTiempoFinal(transcripcionOriginal.getTiempoFinal()
							- datosReasignacionTranscripcionDTO.getTiempoATranscribir());

					// Cálculo del tiempo final de la nueva transcripción
					transcripcionNueva.setTiempoFinal(transcripcionNueva.getTiempoInicial()
							+ datosReasignacionTranscripcionDTO.getTiempoATranscribir());

					transcripcionNueva.setTiempoTranscrito(0L);
					transcripcionNueva
							.setFechaEntregaTranscripcion(transcripcionOriginal.getFechaEntregaTranscripcion());
					transcripcionNueva.setFechaPrevistaDeEntrega(transcripcionOriginal.getFechaPrevistaDeEntrega());
					// transcripcionNueva.setEstado(UtilDominios.ESTADO_TRANSCRIPCION_POR_TRANSCRIBIR);
					transcripcionNueva.setEstado(UtilDominios.ESTADO_TRANSCRIPCION_PENDIENTE);
					
					String idSesion = null;
					if (appContext != null && appContext.getContextoSesion() != null
							&& appContext.getContextoSesion().getNombreUsuario() != null) {
						idSesion = appContext.getContextoSesion().getNombreUsuario();
					}
					transcripcionNueva.setIdUsuarioModificacion(idSesion);
					transcripcionNueva.setFechaUltimaModificacion(new Date());
					transcripcionNueva.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					transcripcionNueva.setIdDocAudioFuente(transcripcionOriginal.getIdDocAudioFuente());

					manejadorTranscripcion.crear(transcripcionNueva);
					
					// 5. Envia notificación de asignación de nueva transcripción
					Long tiempoATranscribir = transcripcionNueva.getTiempoFinal() - transcripcionNueva.getTiempoInicial(); 
					enviarCorreoReasignacionTranscripcion(persona.getIdPersona(),
							generarNotificacionAsignacionTranscripcion(transcripcionNueva,
									personaAsignadaTranscripcionOriginal.getIdPersona(), persona.getIdPersona(),
									tiempoATranscribir));

					datosTranscripcionesNuevasAsignadas.add(datosReasignacionTranscripcionDTO);
				}
			}
		}
		
		// 6. Actualiza la transcripción original
		String idSesion = null;
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			idSesion = appContext.getContextoSesion().getNombreUsuario();
		}
		transcripcionOriginal.setIdUsuarioModificacion(idSesion);
		transcripcionOriginal.setFechaUltimaModificacion(new Date());
		manejadorTranscripcion.actualizar(transcripcionOriginal);
		
		// 7. Envía notificación de reasignación parcial de transcripción
		enviarCorreoReasignacionTranscripcion(personaAsignadaTranscripcionOriginal.getIdPersona(),
				generarNotificacionReasignacionTranscripcion(transcripcionOriginal,
						datosTranscripcionesNuevasAsignadas));
	}

	/**
	 * Método encargado de enviar la notificación de la reasignación de la
	 * transcripción
	 */
	private void enviarCorreoReasignacionTranscripcion(Long idPersona, Map<String, String> map) throws SIMASCNegocioExcepcion {
		EnvioCorreoServiceSoapProxy correoServiceSoapProxy = new EnvioCorreoServiceSoapProxy();

		Persona sistema = manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA,
				UtilDominios.ESTADO_PERSONA_ACTIVO);
		CorreoElectronico correoElectronicoSistema = sistema.getCorreoElectronicoList().get(0);

		Persona receptor = manejadorPersona.buscar(idPersona);
		List<CorreoElectronico> correosElectronicosReceptor = receptor.getCorreoElectronicoList();

		CorreoElectronico correoElectronicoReceptor = null;
		for (CorreoElectronico correoElectronico : correosElectronicosReceptor) {
			if (UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL.equals(correoElectronico.getTipo())) {
				correoElectronicoReceptor = correoElectronico;
			}
		}

		EnvioCorreoInDTO correoInDTO = new EnvioCorreoInDTO();

		if (correoElectronicoSistema != null && correoElectronicoSistema.getDireccion() != null) {
			correoInDTO.setDe(correoElectronicoSistema.getDireccion());
		}
		
		if (correoElectronicoReceptor != null && correoElectronicoReceptor.getDireccion() != null) {
			ArrayOfString destinos = new ArrayOfString();
			destinos.getString().add(correoElectronicoReceptor.getDireccion());
			correoInDTO.setPara(destinos);
		}

		correoInDTO.setAsunto(map.get("Asunto"));
		correoInDTO.setContenido(map.get("Contenido"));

		ArrayOfAdjuntoDTO listaAdjuntos = new ArrayOfAdjuntoDTO();
		correoInDTO.setAdjuntos(listaAdjuntos);

		try {
			correoServiceSoapProxy.envioCorreo(correoInDTO);
		} catch (Exception e) {
			logger.error("Error: ", e);
		}

	}
	
	@Override
	public List<Transcripcion> consultarTranscripcionesPorDocumento(Long idDocumento) {
		return manejadorTranscripcion.consultarTranscripcionesPorDocumento(idDocumento);
	}

	@Override
	public void actualizarTranscripcion(Transcripcion transcripcion, String usuario) {
		transcripcion.setIdUsuarioModificacion(usuario);
		transcripcion.setFechaUltimaModificacion(new Date());
		manejadorTranscripcion.actualizar(transcripcion);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITranscripcionFacade
	 * #crearTranscripcionDocumento(java.lang.Long)
	 */
	@Override
	public void crearTranscripcionDocumento(Long idDocumento) {
		Documento documento = manejadorDocumento.buscar(idDocumento);
		if (documento != null) {
			Transcripcion transcripcion = new Transcripcion();
			transcripcion.setFechaPrevistaDeEntrega(documento.getFechaDeGrabacion());
			transcripcion.setIdDocAudioFuente(documento.getIdDocumento());
			transcripcion.setEstado(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
			transcripcion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			transcripcion.setFechaUltimaModificacion(new Date());
			transcripcion.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			Caso caso = documento.getCaso();
			if (caso != null) {
				RolPersonaCaso rpc = manejadorRolPersonaCaso.consultarPersonaPorRolCasoPrestador(caso.getIdCaso(),
						UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
				if (rpc != null)
					transcripcion.setIdPersona(rpc.getRolPersonaCasoPK().getIdPersona());
				else {
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR501.toString()));
				}
			}
			transcripcion.setTiempoInicial(0L);
			transcripcion.setTiempoFinal(documento.getDuracion().longValue());
			transcripcion.setTiempoTranscrito(0L);
			manejadorTranscripcion.crear(transcripcion);
		}
	}
	
	
	// protected region metodos adicionales end

}
