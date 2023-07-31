package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

import java.math.BigDecimal;

// Escriba en esta sección sus modificaciones

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.comun.fachada.interfaces.IRealizacionSorteoFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.enumeraciones.TipoOrdenamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorAgendamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorAreaAsuntoClasificacion;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorAudienciaTurnoJornada;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorEvento;
import com.ccb.simasc.integracion.manejadores.ManejadorEventoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorInasistencia;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroDeServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroServicioSorteo;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPlantillaCarta;
import com.ccb.simasc.integracion.manejadores.ManejadorResultadoAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorSede;
import com.ccb.simasc.integracion.manejadores.ManejadorSolicitudProrroga;
import com.ccb.simasc.integracion.manejadores.ManejadorSorteo;
import com.ccb.simasc.integracion.manejadores.ManejadorTurnoJornada;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendamientoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICartaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IInvitadoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INombramientoPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IOrquestadorAlertasFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.transversal.dto.AudienciaDTO;
import com.ccb.simasc.transversal.dto.AudienciaSorteoDTO;
import com.ccb.simasc.transversal.dto.AudienciaTranscripcionesPendientesDTO;
import com.ccb.simasc.transversal.dto.CasoDTO;
import com.ccb.simasc.transversal.dto.CasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.CitacionDTO;
import com.ccb.simasc.transversal.dto.ConsultaAgendamientoDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.InasistenciaDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.RealizacionAudienciaDTO;
import com.ccb.simasc.transversal.dto.RolPersonaCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.AudienciasProgramadasDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosResultadoAudienciaDTO;
import com.ccb.simasc.transversal.dto.formularios.ParteCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.dto.formularios.ProgramacionAudienciaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosSorteadosDTO;
import com.ccb.simasc.transversal.entidades.Agendamiento;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.AudienciaTurnoJornada;
import com.ccb.simasc.transversal.entidades.AudienciaTurnoJornadaPK;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Convenio;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.CorreoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Inasistencia;
import com.ccb.simasc.transversal.entidades.InasistenciaPK;
import com.ccb.simasc.transversal.entidades.Invitado;
import com.ccb.simasc.transversal.entidades.NombramientoPersona;
import com.ccb.simasc.transversal.entidades.ParametroDeServicio;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;
import com.ccb.simasc.transversal.entidades.ParametroSorteo;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Preseleccionado;
import com.ccb.simasc.transversal.entidades.ResultadoAudiencia;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.entidades.Sorteo;
import com.ccb.simasc.transversal.entidades.TurnoJornada;
import com.ccb.simasc.transversal.excepciones.EstadosCasoException;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascNegocioPruebaException;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;
import com.ccb.simasc.transversal.utilidades.UtilParamServicio;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Audiencia}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class AudienciaFacade extends AccesoFacade<Audiencia, Long, AudienciaDTO> implements IAudienciaFacade {

	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada

	private static final Logger logger = LogManager.getLogger(AudienciaFacade.class.getName());
	private static final String LIBERA_SUPLENTES = "liberarSuplentes";
	private static final String BLOQUEA_SUPLENTES = "bloqueaSuplentes";

	@EJB
	private ManejadorAudiencia manejadorAudiencia;

	@EJB
	private ManejadorEvento manejadorEvento;

	@EJB
	private ManejadorSorteo manejadorSorteo;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorRolPersona manejadorRolPersona;
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	@EJB
	private ManejadorRol manejadorRol;
	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorCorreoElectronico manejadorCorreoElectronico;

	@EJB
	private ManejadorDocumento manejadorDocumento;

	@EJB
	private ManejadorCorreoRolPersonaCaso manejadorCorreoRolPersonaCaso;

	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	@EJB
	private IInvitadoFacade invitadoFacade;

	@EJB
	private IEventoFacade eventoFacade;

	@EJB
	private ManejadorAgendamiento manejadorAgendamiento;

	@EJB
	private RolFacade rolFacade;

	/**
	 * Bean de negocio encargado de realizar operaciones de sorteo
	 */
	@EJB
	private IRealizacionSorteoFacade sorteoFacade;

	@EJB
	private IAgendamientoFacade agendamientoFacade;

	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;

	@EJB
	private IDominioFacade dominioFacade;

	@EJB
	private ICartaPersonaFacade cartaPersonaFacade;

	@EJB
	private ManejadorPlantillaCarta manejadorPlantillaCarta;

	@EJB
	private ManejadorEventoRolPersonaCaso manejadorEventoRolPersonaCaso;

	@EJB
	private ManejadorParametroDeServicio manejadorParametroDeServicio;

	@EJB
	private ManejadorTurnoJornada manejadorTurnoJornada;

	@EJB
	private ManejadorAudienciaTurnoJornada manejadorAudienciaTurnoJornada;

	@EJB
	private ManejadorResultadoAudiencia manejadorResultadoAudiencia;

	@EJB
	private ManejadorAreaAsuntoClasificacion manejadorAreaAsuntoClasificacion;

	@EJB
	private ICasoFacade casoFacade;

	@EJB
	private ManejadorSede manejadorSede;

	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	@EJB
	private ManejadorSolicitudProrroga manejadorSolicitudProrroga;

	@EJB
	private ManejadorInasistencia manejadorInasistencia;

	@EJB
	private SolicitudProrrogaFacade solicitudProrrogaFacade;

	@EJB
	private AgendaPersonaFacade agendaPersonaFacade;

	@EJB
	private IDiaFestivoFacade diaFestivoFacade;

	@EJB
	private AlmacenamientoDocumentosFacade almacenamientoDocumentosFacade;

	@EJB
	private IOrquestadorAlertasFacade orquestadorAlertasFacade;

	@EJB
	private ManejadorConvenio manejadorConvenio;

	@EJB
	private INombramientoPersonaFacade nombramientoPersonaFacade;
	
	@EJB
	private ManejadorParametroServicioSorteo manejadorParametroServicioSorteo;

	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorAudiencia;
	}

	@Override
	public AudienciaDTO transformarSinDependencias(Audiencia obj) {
		return new AudienciaDTO().transformarSinDependencias(obj);
	}

	@Override
	public AudienciaDTO transformarConDependencias(Audiencia obj) {
		return new AudienciaDTO().transformarConDependencias(obj);
	}

	@Override
	public Audiencia transformarEntidadConDependencias(Audiencia obj) {
		return new AudienciaDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Audiencia transformarEntidadSinDependencias(Audiencia obj) {
		return new AudienciaDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaFacade#
	 * obtenerAudienciasCaso( java.lang.Long)
	 */
	@Override
	public List<Audiencia> obtenerAudienciasCaso(Long idCaso) {

		InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.EXACTO, Audiencia.ENTIDAD_AUDIENCIA_ID_CASO,
				idCaso);
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(filtro);

		return manejadorAudiencia.consultar(filtros, null, null);
	}

	@Override
	public List<AudienciaDTO> obtenerAudienciasCasoDTO(AudienciaDTO filtroAudiencia) {
		List<InformacionFiltro> filtros = new ArrayList<>();

		if (filtroAudiencia.getIdCaso() != null) {
			InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.EXACTO, Audiencia.ENTIDAD_AUDIENCIA_ID_CASO,
					filtroAudiencia.getIdCaso());
			filtros.add(filtro);
		}
		if (filtroAudiencia.getHoraInicio() != null) {
			InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.MAYOR_O_IGUAL,
					Audiencia.ENTIDAD_AUDIENCIA_HORA_INICIO, filtroAudiencia.getHoraInicio());
			filtros.add(filtro);
		}
		if (filtroAudiencia.getHoraFin() != null) {
			InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.MENOR_O_IGUAL,
					Audiencia.ENTIDAD_AUDIENCIA_HORA_INICIO, filtroAudiencia.getHoraFin());
			filtros.add(filtro);
		}
		if (filtroAudiencia.getNombreCaso() != null) {
			InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.LIKE, Caso.ENTIDAD_CASO_NOMBRE,
					filtroAudiencia.getNombreCaso());
			filtros.add(filtro);
		}
		InformacionFiltro filtroActivo = new InformacionFiltro(TipoFiltro.EXACTO,
				Audiencia.ENTIDAD_AUDIENCIA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		filtros.add(filtroActivo);

		List<InformacionOrdenamiento> filtrosOrdena = new ArrayList<>();
		InformacionOrdenamiento filtroOrdenamiento = new InformacionOrdenamiento(TipoOrdenamiento.DESCENDENTE,
				Audiencia.ENTIDAD_AUDIENCIA_HORA_INICIO);
		filtrosOrdena.add(filtroOrdenamiento);

		List<Audiencia> audiencias = manejadorAudiencia.consultar(filtros, filtrosOrdena, null);
		List<AudienciaDTO> listAudienciasDTO = new ArrayList<AudienciaDTO>();
		for (Audiencia audiencia : audiencias) {
			Caso caso = manejadorCaso.buscar(audiencia.getIdCaso());
			AudienciaDTO audienciaDTO = new AudienciaDTO();
			audienciaDTO.setEstado(audiencia.getEstado());
			audienciaDTO.setCantidadAsistentes(audiencia.getCantidadAsistentes());
			audienciaDTO.setEstadoRegistro(audiencia.getEstadoRegistro());
			audienciaDTO.setFechaUltimaModificacion(audiencia.getFechaUltimaModificacion());
			audienciaDTO.setHoraFin(audiencia.getHoraFin());
			audienciaDTO.setHoraInicio(audiencia.getHoraInicio());
			audienciaDTO.setIdAudiencia(audiencia.getIdAudiencia());
			audienciaDTO.setIdCaso(audiencia.getIdCaso());
			audienciaDTO.setEtapa(audiencia.getCaso().getEtapa());
			audienciaDTO.setNombreSala("Sin sala");
			Agendamiento agendamiento = manejadorAgendamiento.buscarAgendamientoAudiencia(audiencia.getIdAudiencia());
			if (agendamiento != null) {
				audienciaDTO.setIdSala(agendamiento.getIdSala());
				audienciaDTO.setNombreSala((agendamiento.getSala().getSede().getDireccion()) + " Sala "
						+ (agendamiento.getSala().getNumeroSala()));
			}

			audienciaDTO.setIdSorteo(audiencia.getIdSorteo());
			audienciaDTO.setIdUsuarioModificacion(audiencia.getIdUsuarioModificacion());
			audienciaDTO.setObservaciones(audiencia.getObservaciones());
			audienciaDTO.setTipoAudiencia(audiencia.getTipoAudiencia());

			audienciaDTO.setNombreCaso(caso.getNombre());
			listAudienciasDTO.add(audienciaDTO);

		}
		return listAudienciasDTO;
	}

	@Override
	public void crearEventoRutaCaso(Audiencia audiencia) {
		Evento evento = new Evento();
		evento.setCaso(audiencia.getCaso());
		evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		evento.setFechaEvento(new Date());
		evento.setFechaUltimaModificacion(new Date());
		// SE DEJA QUEMADO EL USUARIO DE SIMASC
		evento.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);

		if (audiencia.getEstado().equals(UtilDominios.ESTADO_AUDIENCIA_CANCELADA)) {
			String[] observaciones = audiencia.getObservaciones().split(";");
			for (String motivo : observaciones) {
				evento.setObservaciones(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO106.toString()) + motivo);
			}
			evento.setTipoEvento(UtilDominios.TIPO_EVENTO_CANCELACION_AUDIENCIA);
		}
		if (audiencia.getEstado().equals(UtilDominios.ESTADO_AUDIENCIA_APLAZADA)) {
			String[] observaciones = audiencia.getObservaciones().split(";");
			for (String motivo : observaciones) {
				evento.setObservaciones(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO111.toString()) + motivo);
			}
			evento.setTipoEvento(UtilDominios.TIPO_EVENTO_AUDIENCIA_APLAZADA);
		}
		if (audiencia.getEstado().equals(UtilDominios.ESTADO_AUDIENCIA_ELIMINADA)) {
			String[] observaciones = audiencia.getObservaciones().split(";");
			for (String motivo : observaciones) {
				evento.setObservaciones(motivo);
			}
			evento.setTipoEvento(UtilDominios.TIPO_EVENTO_AUDIENCIA_ELIMINADA);
		}
		evento.setIdCaso(audiencia.getIdCaso());
		manejadorEvento.crear(evento);
	}

	/**
	 * Consulta las audiencias dependiendo de los filtros ingresados
	 * 
	 * @param filtroAudiencias
	 */
	public List<AudienciaDTO> obtenerAudienciasFiltros(AudienciaDTO filtroAudiencias, ContextoDeSesion cs) {

		Rol rolUsuario = (cs != null) ? manejadorRol.buscar(Long.parseLong(cs.getRolPrincipal())) : null;

		if (rolUsuario != null && UtilDominios.ROL_PERSONA_AUXILIAR_ADM.equals(rolUsuario.getNombre())) {
			Long idUsuarioLogin = Long.parseLong(cs.getIdUsuario());
			filtroAudiencias.setIdRolSecretario(rolUsuario.getIdRol());
			filtroAudiencias.setIdUsuario(idUsuarioLogin);
		} else {
			filtroAudiencias.setIdUsuario(null);
		}

		if (filtroAudiencias.getHoraFin() != null && filtroAudiencias.getHoraInicio() != null
				&& filtroAudiencias.getHoraFin().compareTo(filtroAudiencias.getHoraInicio()) < 0) {
			// Las fechas fin no puede ser menor a la fecha inicial
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO038.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}

		return manejadorAudiencia.obtenerAudienciasFiltros(filtroAudiencias);
	}

	@Override
	public AudienciaDTO obtenerAudienciaFiltros(Long idCaso) {
		return manejadorAudiencia.obtenerAudienciaFiltros(idCaso);
	}

	@Override
	public void registrarResultadoAudiencia(DatosResultadoAudienciaDTO resultadoDTO) {
		try {
			Caso caso = manejadorCaso.buscar(resultadoDTO.getIdCaso());
			Audiencia audienciaResultado = manejadorAudiencia.buscar(resultadoDTO.getIdAudiencia());
			CorreoElectronico correoElectronicoRemitente;
			CorreoElectronicoDTO correoRemitente = new CorreoElectronicoDTO();
			List<CorreoElectronicoDTO> lstCorreoDestino = new ArrayList<>();
			if ((UtilDominios.TIPO_AUDIENCIA_AUDIENCIA_DE_INSTALACION.equals(resultadoDTO.getTipoAudiencia())
					|| UtilDominios.TIPO_AUDIENCIA_PREARBITRAL_APERTURA.equals(resultadoDTO.getTipoAudiencia())
					|| UtilDominios.TIPO_AUDIENCIA_PREARBITRAL_REUNION_INICIAL.equals(resultadoDTO.getTipoAudiencia()))
					&& UtilDominios.ESTADO_AUDIENCIA_REALIZADA.equals(resultadoDTO.getEstadoResultado())) {
				reglasAudienciaInstalacion(resultadoDTO);
				Rol rol = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
				Persona personaSecretario = manejadorPersona.buscar(resultadoDTO.getNombramientoSecretario());
				RolPersonaCaso rolAbogado = manejadorRolPersonaCaso.consultarPersonaPorRolCasoPrestador(
						resultadoDTO.getIdCaso(), UtilDominios.ROL_PERSONA_ABOGADO);
				if (personaSecretario != null) {
					CorreoElectronico correoPersonaSecretario = manejadorCorreoElectronico
							.consultarPorTipoCorreoIdPersona(personaSecretario.getIdPersona(),
									UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
					if (correoPersonaSecretario != null) {
						CorreoElectronicoDTO correoDestino = new CorreoElectronicoDTO();
						correoDestino.setDireccion(correoPersonaSecretario.getDireccion());
						correoDestino.setTipo(correoPersonaSecretario.getTipo());
						if (correoDestino.getDireccion() != null)
							lstCorreoDestino.add(correoDestino);
					}
				}
				if (rolAbogado != null) {
					correoElectronicoRemitente = manejadorCorreoElectronico.consultarPorTipoCorreoIdPersona(
							rolAbogado.getPersona().getIdPersona(),
							UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
					if (correoElectronicoRemitente != null) {
						correoRemitente.setDireccion(correoElectronicoRemitente.getDireccion());
						correoRemitente.setTipo(correoElectronicoRemitente.getTipo());
					}
				}
				// Actualiza el nombramiento en rol persona caso para el
				// presiente en el caso
				RolPersonaCaso rolPresidente = manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(
						resultadoDTO.getNombramientoPresidente(), resultadoDTO.getIdCaso());
				if (rolPresidente != null) {
					rolPresidente.setEsPresidente(true);
					manejadorRolPersonaCaso.actualizar(rolPresidente);
				}
				// Registra el nombramiento en rol persona caso para el
				// secretario
				if (personaSecretario != null) {
					if (!manejadorRolPersonaCaso.obtenerRolesPersonaCaso(personaSecretario.getIdPersona(),
							caso.getIdCaso(), new ArrayList<String>()))
						throw new SIMASCNegocioExcepcion(
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR910.toString()));
					RolPersonaCaso rolSecretario = new RolPersonaCaso();
					rolSecretario.setCaso(caso);
					rolSecretario.setIdCasoApoderado(resultadoDTO.getIdCaso());
					rolSecretario.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
					rolSecretario.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					rolSecretario.setFechaUltimaModificacion(new Date());
					rolSecretario.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
					audienciaResultado.setIdPersonaSecretario(personaSecretario.getIdPersona());
					rolSecretario.setRol(rol);
					rolSecretario.setPersona(personaSecretario);
					rolSecretario.setEsPresidente(false);
					rolSecretario.setTipoNombramiento(UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
					rolSecretario.setMetodoNombramiento(UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES);
					rolSecretario = manejadorRolPersonaCaso.crearRolPersonaCaso(rolSecretario);
					// registra el evento en evento rol persona caso
					EventoRolPersonaCaso eventoRolPersonaCaso = new EventoRolPersonaCaso();
					eventoRolPersonaCaso.setEstadoAsignado(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
					eventoRolPersonaCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					eventoRolPersonaCaso.setFechaDeAsignacion(new Date());
					eventoRolPersonaCaso.setFechaUltimaModificacion(new Date());
					eventoRolPersonaCaso.setIdPersona(rolSecretario.getPersona().getIdPersona());
					eventoRolPersonaCaso.setIdCaso(caso.getIdCaso());
					eventoRolPersonaCaso.setIdRol(rol.getIdRol());
					manejadorEventoRolPersonaCaso.crear(eventoRolPersonaCaso);
					// Realiza el reparto para el rol auxiliar
					Rol rolAuxiliar = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
					boolean tieneAuxiliar = false;
					for (RolPersonaCaso rolPersonaCaso : caso.getRolPersonaCasoList()) {
						if (rolPersonaCaso.getRol() != null
								&& rolPersonaCaso.getRol().getNombre().equals(rolAuxiliar.getNombre())) {
							tieneAuxiliar = true;
							break;
						}
					}
					if (!tieneAuxiliar) {
						repartoAuxiliar(caso, rolAuxiliar);
					}

				}
			}
			// Registra el resultado de la audiencia
			audienciaResultado.setObservaciones(resultadoDTO.getObservaciones());
			audienciaResultado.setFechaUltimaModificacion(new Date());
			audienciaResultado.setEstado(resultadoDTO.getEstadoResultado());
			audienciaResultado.setResultado(resultadoDTO.getResultado());
			Evento eventoAudiencia = new Evento();
			eventoAudiencia.setFechaEvento(new Date());
			eventoAudiencia.setFechaUltimaModificacion(new Date());
			eventoAudiencia.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			eventoAudiencia.setCaso(caso);
			eventoAudiencia.setIdCaso(resultadoDTO.getIdCaso());
			switch (resultadoDTO.getEstadoResultado()) {
			case UtilDominios.ESTADO_AUDIENCIA_REALIZADA: // Si la audiencia fue
															// Realizada:
				eventoAudiencia.setTipoEvento(UtilDominios.TIPO_EVENTO_AUDIENCIA_REALIZADA);
				eventoAudiencia.setObservaciones(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO112.toString())
								+ resultadoDTO.getDescripcionTipoAudiencia()
								+ MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO113.toString())
								+ resultadoDTO.getDescripcionResultado());
				break;
			case UtilDominios.ESTADO_AUDIENCIA_CANCELADA:
				eventoAudiencia.setTipoEvento(UtilDominios.TIPO_EVENTO_CANCELACION_AUDIENCIA);
				eventoAudiencia.setObservaciones(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO106.toString())
								+ resultadoDTO.getObservaciones());
				break;
			case UtilDominios.ESTADO_AUDIENCIA_SUSPENDIDA:
				eventoAudiencia.setTipoEvento(UtilDominios.TIPO_EVENTO_AUDIENCIA_SUSPENDIDA);
				if (resultadoDTO.getObservaciones() != null)
					eventoAudiencia.setObservaciones(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO108.toString())
									+ resultadoDTO.getObservaciones());
				eventoAudiencia.setObservaciones(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO109.toString()));
				break;
			case UtilDominios.ESTADO_AUDIENCIA_APLAZADA:
				eventoAudiencia.setTipoEvento(UtilDominios.TIPO_EVENTO_AUDIENCIA_APLAZADA);
				if (resultadoDTO.getObservaciones() != null)
					eventoAudiencia.setObservaciones(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO110.toString())
									+ resultadoDTO.getObservaciones());
				eventoAudiencia.setObservaciones(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO111.toString()));
				break;
			default:
				break;
			}
			manejadorEvento.actualizar(eventoAudiencia);
			Agendamiento agendamiento = manejadorAgendamiento
					.buscarAgendamientoAudiencia(audienciaResultado.getIdAudiencia());
			if (agendamiento != null) {
				agendamientoFacade.cancelarAgendamiento(agendamiento.getIdAgendamiento());
			}

			if (appContext != null && appContext.getContextoSesion() != null
					&& appContext.getContextoSesion().getNombreUsuario() != null) {
				audienciaResultado.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
			} else {
				audienciaResultado.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			}

			manejadorAudiencia.actualizar(audienciaResultado);
		} catch (Exception e) {
			logger.error("Error: ", e);
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString()));
		}

	}

	private void repartoAuxiliar(Caso caso, Rol rolAuxiliar) {
		try {
			rolPersonaCasoFacade.reparto(caso, rolAuxiliar);
		} catch (Exception ex) {
			logger.error(ex);
			// No es necesario ejecutar el reparto si el
			// auxiliar
			// administrativo ya se encuentra asignado
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IAudienciaFacade#tieneAudienciasPendientes(java.lang.Long)
	 */
	@Override
	public boolean tieneAudienciasPendientes(Long casoId) {
		List<Audiencia> audiencias = this.obtenerAudienciasCasoPorTipoYEstado(casoId, null,
				UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);

		return (audiencias != null && !audiencias.isEmpty());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IAudienciaFacade#validacionAudienciaLaudo()
	 */
	@Override

	public boolean validacionAudienciaLaudo(Audiencia audiencia) throws SIMASCNegocioExcepcion {
		List<Audiencia> audiencias = this.obtenerAudienciasCaso(audiencia.getIdCaso());

		// Variables para contar el número de audiencias de alegato y pruebas,
		// ademas de variables que cuentan audiencias realizadas de este tipo
		int numeroAudienciasAlegato = 0;
		int numeroAudienciasAlegatoRealizadas = 0;
		int numeroAudienciasPruebas = 0;
		int numeroAudienciasPruebasRealizadas = 0;
		int numeroAudienciasAlegatoPendientes = 0;
		int numeroAudienciasPruebasPendientes = 0;

		for (Audiencia audienciaSeleccionada : audiencias) {

			// Verificar si existen audiencias de alegatos y de pruebas en
			// estado realizada
			if (audienciaSeleccionada.getTipoAudiencia().equals(UtilDominios.TIPO_AUDIENCIA_ARBITRAL_PRUEBAS)) {
				numeroAudienciasPruebas++;
				if (audienciaSeleccionada.getEstado().equals(UtilDominios.ESTADO_AUDIENCIA_REALIZADA))
					numeroAudienciasPruebasRealizadas++;
				if (audienciaSeleccionada.getEstado().equals(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE))
					numeroAudienciasPruebasPendientes++;
			} else if (audienciaSeleccionada.getTipoAudiencia().equals(UtilDominios.TIPO_AUDIENCIA_ARBITRAL_ALEGATOS)) {
				numeroAudienciasAlegato++;
				if (audienciaSeleccionada.getEstado().equals(UtilDominios.ESTADO_AUDIENCIA_REALIZADA))
					numeroAudienciasAlegatoRealizadas++;
				if (audienciaSeleccionada.getEstado().equals(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE))
					numeroAudienciasAlegatoPendientes++;
			}
		}

		return (numeroAudienciasAlegato > 0 && numeroAudienciasPruebas > 0 && numeroAudienciasAlegatoRealizadas > 0
				&& numeroAudienciasPruebasRealizadas > 0)
				&& (numeroAudienciasAlegatoPendientes == 0 && numeroAudienciasPruebasPendientes == 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IAudienciaFacade#validarTipoAudiencia()
	 */
	@Override
	public void validarTipoAudiencia(Audiencia audiencia) throws SIMASCNegocioExcepcion {
		// Verifica si la audiencia que se desea registrar es de tipo pruebas o
		// alegatos.
		if (!(audiencia.getTipoAudiencia().equals(UtilDominios.TIPO_AUDIENCIA_ARBITRAL_PRUEBAS)
				|| audiencia.getTipoAudiencia().equals(UtilDominios.TIPO_AUDIENCIA_ARBITRAL_ALEGATOS))) {

			// Si la audiencia no es de tipo pruebas o alegatos, se verifica si el tipo de
			// audiencia a registrar es de tipo laudo
			if (audiencia.getTipoAudiencia().equals(UtilDominios.TIPO_AUDIENCIA_ARBITRAL_LAUDO)) {
				if (!validacionAudienciaLaudo(audiencia)) {
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR036.toString()));
				}

				List<Audiencia> audienciasLaudoPendientes = this.obtenerAudienciasCasoPorTipoYEstado(
						audiencia.getIdCaso(), UtilDominios.TIPO_AUDIENCIA_ARBITRAL_LAUDO,
						UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
				if (audienciasLaudoPendientes != null && !audienciasLaudoPendientes.isEmpty()) {
					String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR020.toString());
					throw new SIMASCNegocioExcepcion(msg);
				}
			} else if (tieneAudienciasPendientes(audiencia.getIdCaso())) {
				String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR020.toString());
				throw new SIMASCNegocioExcepcion(msg);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IAudienciaFacade#validacionProgramarAudiencia()
	 */
	@Override
	public void validacionProgramarAudiencia(Audiencia audiencia) throws SIMASCNegocioExcepcion {
		// Verifica si la audiencia que se desea registrar es de tipo pruebas o
		// alegatos.
		validarTipoAudiencia(audiencia);
		// Si el tipo de audiencia es Sorteo público de designación
		if (UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_DE_DESIGNACION
				.compareToIgnoreCase(audiencia.getTipoAudiencia()) == 0) {

			if (!sorteoFacade.validarDiaHabilitadoParaSorteo(UtilSimasc.toCalendar(audiencia.getHoraFin()))) {
				String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR096.toString());
				throw new SIMASCNegocioExcepcion(msg);
			}

			// validacion cant minima con simulacion de liberacion de lista
			Caso casoAudiencia = manejadorCaso.buscar(audiencia.getIdCaso());
			ParametroSorteo parametrosSort = sorteoFacade.consultarParametrosSorteo();
			for (ParametroServicioSorteo paramServicio : parametrosSort.getParametroServicioSorteoList()) {
				if (paramServicio.getIdServicio() == casoAudiencia.getIdServicio()) {
					casoAudiencia.setParametroServSorteo(paramServicio);
					break;
				}
			}
			casoAudiencia.setNombramientoSorteo(this.sorteoFacade.obtenerNombramientoPersonaCaso(casoAudiencia));

			if (casoAudiencia.getNombramientoSorteo().getIdNombramientoPersonas() != null) {
				List<Persona> personasSorteo = obtenerListaFuncionariosSorteo(casoAudiencia, audiencia.getTipoSorteo());
				if (!casoAudiencia.getPreseleccion() || casoAudiencia.getTipoPreseleccion() == null || casoAudiencia
						.getTipoPreseleccion().equals(UtilDominios.TIPO_PRESELECCION_PRESELECCION_UNICA)) {
					if (!sorteoFacade.validarCantidadMinArb(personasSorteo, audiencia, null)) {
						if (!sorteoFacade.simulariberacionLista(casoAudiencia, audiencia.getTipoSorteo())) {
							String msg = MensajesSimasc.getInstancia()
									.getMensajePorKey(MensajesEnum.ERROR124.toString());
							throw new SIMASCNegocioExcepcion(msg);
						}
					}
				} else {
					// validacion principales
					personasSorteo = sorteoFacade.obtenerPreseleccionados(casoAudiencia,
							UtilDominios.TIPO_PRESELECCIONADO_PRESELECCION_PRINCIPAL);
					if (!sorteoFacade.validarCantidadMinArb(personasSorteo, audiencia, null)) {
						if (!sorteoFacade.simulariberacionLista(casoAudiencia, audiencia.getTipoSorteo())) {
							String msg = MensajesSimasc.getInstancia()
									.getMensajePorKey(MensajesEnum.ERROR124.toString());
							throw new SIMASCNegocioExcepcion(msg);
						}
					}
					// validacion suplentes
					personasSorteo = sorteoFacade.obtenerPreseleccionados(casoAudiencia,
							UtilDominios.TIPO_PRESELECCIONADO_PRESELECCION_SUPLENTE);
					if (!sorteoFacade.validarCantidadMinArb(personasSorteo, audiencia, null)) {
						if (!sorteoFacade.simulariberacionLista(casoAudiencia, audiencia.getTipoSorteo())) {
							String msg = MensajesSimasc.getInstancia()
									.getMensajePorKey(MensajesEnum.ERROR124.toString());
							throw new SIMASCNegocioExcepcion(msg);
						}
					}
				}

			} else {
				// el caso no cuenta con nombrtamiento persona por la CCB
				String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR125.toString());
				throw new SIMASCNegocioExcepcion(msg);
			}
		}
	}

	/**
	 * Método encargado de obtemer la lista de funcionarios disponibles para un
	 * sorteo
	 * 
	 * @param caso
	 * @return
	 */
	private List<Persona> obtenerListaFuncionariosSorteo(Caso caso, String tipoSorteo) {
		List<Persona> personasSorteo = new ArrayList<Persona>();
		
		if (caso.getPreseleccion() && caso.getPreseleccionadoList() != null) {
			for (Preseleccionado preseleccionado : caso.getPreseleccionadoList()) {
				Persona personaSorteo = preseleccionado.getPersona();
				personasSorteo.add(personaSorteo);
			}
		} else {
			personasSorteo = sorteoFacade.getConformarListaFuncionarioSorteo(caso, false, tipoSorteo);
		}
		return personasSorteo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IAudienciaFacade#programarAudiencia(com.ccb.simasc.transversal.entidades.
	 * Audiencia)
	 */
	@Override
	public Long programarAudiencia(Audiencia audiencia, Boolean notificar) {
		
		List<Invitado> invitados = audiencia.getInvitadoList() != null ? audiencia.getInvitadoList()
				: new ArrayList<Invitado>();
		//modificacion Variables HU-06
		String liberarSuplentes = "";
		Boolean bloqueaSuplentes = false;
		
		audiencia.setInvitadoList(null);
		crear(audiencia);
		for (Invitado invitado : invitados) {
			invitado.setIdAudiencia(audiencia.getIdAudiencia());
			invitadoFacade.crear(invitado);
		}
		String tipoAudiencia = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_AUDIENCIA,
				audiencia.getTipoAudiencia());
		String nombreCaso = manejadorCaso.consultarNombreCaso(audiencia.getIdCaso());
		List<String> parametrosMsg = new ArrayList<String>();
		parametrosMsg.add("nueva de, tipo " + tipoAudiencia);
		parametrosMsg.add(nombreCaso);
		String fechaAudiencia = new SimpleDateFormat(UtilConstantes.FORMATO_FECHA_DIA_MES_ANIO_HORA)
				.format(audiencia.getHoraInicio());
		parametrosMsg.add(fechaAudiencia);
		String observaciones = String.format(
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO030.toString()),
				parametrosMsg.toArray());

		eventoFacade.registrarEvento(audiencia.getIdCaso(), UtilDominios.TIPO_EVENTO_AUDIENCIA_PROGRAMADA,
				observaciones, UtilConstantes.USUARIO_DEFECTO_SIMASC, null, null);
				
		HashMap<String, Object> estadosSuplentes = manejadorParametroServicioSorteo.audienciaTramitaSuplente(audiencia.getIdCaso());
		
		if(!estadosSuplentes.isEmpty()) {
			if(!((String) estadosSuplentes.get(LIBERA_SUPLENTES)).equals("") && 
			   estadosSuplentes.get(LIBERA_SUPLENTES) != null) {
				
				liberarSuplentes = (String)estadosSuplentes.get(LIBERA_SUPLENTES);
			}
			if(estadosSuplentes.get(BLOQUEA_SUPLENTES) != null) {
				bloqueaSuplentes = (boolean)estadosSuplentes.get(BLOQUEA_SUPLENTES);
			}
		}
		if(liberarSuplentes.equalsIgnoreCase(audiencia.getTipoAudiencia()) && bloqueaSuplentes) {
			rolPersonaCasoFacade.liberarSuplentes(audiencia.getIdCaso());			
		}

		// Alerta A-23 Fijación de Audiencia Primera de Tramite
		if (UtilDominios.TIPO_AUDIENCIA_ARBITRAL_PRIMERA.equals(audiencia.getTipoAudiencia())) {
			orquestadorAlertasFacade.alertaFIJAUT(audiencia.getIdCaso());
		}

		return audiencia.getIdAudiencia();
	}

	@Override
	public Audiencia consultarDatosBasicoAudiencia(Long idAudiencia) {
		return manejadorAudiencia.buscar(idAudiencia);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaFacade#
	 * consultarAudienciasTranscripcionesPendientes(java.lang.Long)
	 */
	@Override
	public List<AudienciaTranscripcionesPendientesDTO> consultarAudienciasTranscripcionesPendientes(Long idPersona,
			Long idRol) {
		List<AudienciaTranscripcionesPendientesDTO> resultado = new ArrayList<AudienciaTranscripcionesPendientesDTO>();
		Rol rol = manejadorRol.buscar(idRol);

		if (rol != null) {
			String nombreRol = rol.getNombre();
			if (UtilDominios.ROL_PERSONA_ASISTENTE_ARBITRO.equals(rol.getNombre())
					|| UtilDominios.ROL_PERSONA_AUXILIAR_ADM.equals(rol.getNombre()))
				resultado = manejadorAudiencia.consultarAudienciasTranscripcionesPendientes(idPersona, nombreRol);
		}

		return resultado;
	}

	/**
	 * Solución a bug 2966 El caso debe pasar a etapa Arbitral al registrarse el
	 * resultado de la audiencia de instalación
	 * 
	 * @param audiencia
	 */
	private void reglasAudienciaInstalacion(DatosResultadoAudienciaDTO audiencia) {
		if (UtilDominios.ESTADO_AUDIENCIA_REALIZADA.equals(audiencia.getEstadoResultado())) {
			Caso caso = manejadorCaso.buscar(audiencia.getIdCaso());
			Audiencia audienciaInstalacion = manejadorAudiencia.buscar(audiencia.getIdAudiencia());
			if (caso != null) {
				caso.setEtapa(UtilDominios.ETAPA_CASO_ARBITRAL);
				manejadorCaso.actualizarCaso(caso);
				// Se registra evento cuando se realiza la audiencia de instalación
				String observaciones = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO112.toString());
				observaciones = observaciones + " instalación ";
				eventoFacade.registrarEvento(caso.getIdCaso(), UtilDominios.TIPO_EVENTO_AUDIENCIA_INSTALACION_REALIZADA,
						observaciones, UtilConstantes.USUARIO_DEFECTO_SIMASC, audienciaInstalacion.getHoraFin(),
						UtilDominios.ESTADO_REGISTRO_ACTIVO);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaFacade.
	 * actualizarAudiencia(Audiencia)
	 */
	@Override
	public void actualizarAudiencia(Audiencia audiencia) {
		audiencia.setFechaUltimaModificacion(new Date());
		audiencia.setIdUsuarioModificacion(
				audiencia.getIdUsuarioModificacion() != null ? audiencia.getIdUsuarioModificacion()
						: UtilConstantes.USUARIO_DEFECTO_SIMASC);
		manejadorAudiencia.actualizar(audiencia);
		List<Agendamiento> agendamientos = manejadorAgendamiento
				.buscarAgendamientosAudiencia(audiencia.getIdAudiencia());

		for (Agendamiento agendamientoFor : agendamientos) {
			agendamientoFor.setFechaUltimaModificacion(new Date());
			agendamientoFor.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_INACTIVO);
			agendamientoFor.setIdUsuarioModificacion(
					audiencia.getIdUsuarioModificacion() != null ? audiencia.getIdUsuarioModificacion()
							: UtilConstantes.USUARIO_DEFECTO_SIMASC);
			manejadorAgendamiento.actualizar(agendamientoFor);

		}

		if (audiencia.enEstadoAplazado())
			this.crearEventoRutaCaso(audiencia);
		if (audiencia.enEstadoCancelado())
			this.crearEventoRutaCaso(audiencia);
	}

	@Override
	public List<AudienciaSorteoDTO> consultarAudienciasSorteoPendientes() {
		return manejadorAudiencia.consultarAudienciasSorteo(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaFacade#
	 * obtenerAudienciasCasoPorEstado(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<Audiencia> obtenerAudienciasCasoPorTipoYEstado(Long idCaso, String tipoAudiencia, String estado) {
		return manejadorAudiencia.consultarAudienciasCasoPorTipoYEstado(idCaso, tipoAudiencia, estado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaFacade#
	 * enviarNotificacionResultadoAudiencia(java.lang.Long, java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public void enviarNotificacionResultadoAudiencia(Long idCaso, Long idAudiencia, Long idPersona, Long idRol) {
		CasosAsignadosDTO casos = new CasosAsignadosDTO();
		casos.setIdPersona(idPersona);
		casos.setIdCaso(idCaso);
		// RolPersonaCaso rolActual =
		// manejadorRolPersonaCaso.obtenerRolDePersona(casos);
		Rol rolActual = manejadorRol.buscar(idRol);

		// zona envio de correo
		if (rolActual != null) {
			if (!rolActual.getNombre().equalsIgnoreCase(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL)) {
				ArrayList<String> roles = new ArrayList<String>();
				roles.add(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
				List<RolPersonaCaso> rpc = manejadorRolPersonaCaso.consultarPersonasAsignadasCasoPorRol(idCaso, roles);
				RolPersonaCaso secretario = rpc.get(0);
				List<CorreoElectronicoDTO> correoEnviarDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade
						.transformarColeccionConDependencias(
								correoElectronicoFacade.consultaCorreosPersona(secretario.getPersona().getIdPersona()),
								new ArrayList<CorreoElectronicoDTO>());
				ParametrosEnvioCorreoDTO correoDTO = new ParametrosEnvioCorreoDTO();
				correoDTO.setAdjuntos(new ArrayList<DocumentoDTO>());
				correoDTO.setAsunto(UtilConstantes.ASUNTO_CORREO_RESULTADO_AUDIENCIA);
				String cuerpoCorreo = UtilConstantes.CUERPO_CORREO_RESULTADO_AUDIENCIA_ACTA;
				Audiencia audiencia = consultarDatosBasicoAudiencia(idAudiencia);
				if (!manejadorDocumento.obtenerAudiosAudiencia(idCaso, idAudiencia).isEmpty())
					cuerpoCorreo += UtilConstantes.CUERPO_CORREO_RESULTADO_AUDIENCIA_AUDIOS;

				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				cuerpoCorreo += " "
						+ dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_AUDIENCIA,
								audiencia.getTipoAudiencia())
						+ " " + dateFormat.format(audiencia.getHoraInicio()) + " del caso "
						+ audiencia.getCaso().getIdCaso() + "-" + audiencia.getCaso().getNombre();
				correoDTO.setIdCaso(idCaso);
				ArrayList<String> cuerpos = new ArrayList<String>();
				cuerpos.add(cuerpoCorreo);
				correoDTO.setCuerpoCorreo(cuerpos);
				correoDTO.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);
				correoDTO.setRolPersonaCaso(correoEnviarDTO);
				CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
				Persona remitente = manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA,
						UtilDominios.ESTADO_PERSONA_ACTIVO);
				List<CorreoElectronico> correosR = manejadorCorreoElectronico
						.consultaCorreosPersona(remitente.getIdPersona());
				correo = correo.transformarConDependencias(correosR.get(0));
				correoDTO.setRemitente(correo);
				correoRolPersonaCasoFacade.enviarCorreo(correoDTO);
			}
		}
		// fin zona envio de correo
	}

	@Override
	public ProgramacionAudienciaDTO fechasProgramacionAudiencia(Long idCaso) {
		ProgramacionAudienciaDTO rangoFechas = new ProgramacionAudienciaDTO();
		Integer minDias = 0;
		Integer maxDias = 0;
		Caso caso = null;
		AudienciaDTO filtros = new AudienciaDTO();
		filtros.setIdCaso(idCaso);
		List<AudienciaDTO> audiencias = manejadorAudiencia.obtenerAudienciasFiltros(filtros);

		if (idCaso != null)
			caso = manejadorCaso.buscar(idCaso);

		if (audiencias != null && audiencias.size() > 0) {
			minDias = 0;
			maxDias = UtilConstantes.LIMITE_REPROGRAMACION_DIAS;
		} else {
			if (caso != null && UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO.equals(caso.getIdServicio())) {
				Convenio convenio = manejadorConvenio.buscar(caso.getIdConvenio());
				minDias = convenio.getLimiteInferiorDiasProgramacionAudiencias();
				maxDias = convenio.getLimiteSuperiorDiasProgramacionAudiencias();
			} else {
				Long materiaExpress = Long.valueOf((manejadorParametroDeServicio.consultarParametrosDeServicio(
						Arrays.asList(UtilParamServicio.MATERIA_CASOS_EXPRESS), caso.getIdServicio(),
						UtilParamServicio.TIPO_PARAMETRO_MATERIA_EXPRESS)).get(0).getValor());
				if (caso.getIdMateria().equals(materiaExpress) || UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION.equals(caso.getIdServicio())) {
					minDias = 0;
					maxDias = Integer.valueOf((manejadorParametroDeServicio.consultarParametrosDeServicio(
							Arrays.asList(UtilParamServicio.MAX_DIAS_PROGRAMACION_AUDIENCIA), caso.getIdServicio(),
							UtilParamServicio.TIPO_PARAMETRO_PROGRAMACION_AUDIENCIA)).get(0).getValor());
				} else {
					List<ParametroDeServicio> parametroDeServicios = manejadorParametroDeServicio
							.consultarParametrosDeServicio(UtilParamServicio.TIPO_PARAMETRO_PROGRAMACION_AUDIENCIA,
									UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO);
					for (ParametroDeServicio it : parametroDeServicios) {
						if (UtilParamServicio.MIN_DIAS_PROGRAMACION_AUDIENCIA
								.equalsIgnoreCase(it.getParametroDeServicioPK().getNombre())) {
							minDias = Integer.valueOf(it.getValor());
						} else if (UtilParamServicio.MAX_DIAS_PROGRAMACION_AUDIENCIA
								.equalsIgnoreCase(it.getParametroDeServicioPK().getNombre())) {
							maxDias = Integer.valueOf(it.getValor());
						}
					}
				}
			}
		}

		Date fechaInicioAudiencia = diaFestivoFacade.adicionarDiasHabilesFecha(new Date(), minDias);
		Date fechaFinAudiencia = diaFestivoFacade.adicionarDiasHabilesFecha(fechaInicioAudiencia, maxDias);
		rangoFechas.setFechaInicioProgramarAudiencia(fechaInicioAudiencia);
		rangoFechas.setFechaFinProgramarAudiencia(fechaFinAudiencia);
		return rangoFechas;
	}

	/**
	 * metodo que retorna true si existio una citacion de audiencia para el caso
	 * 
	 * @return
	 */
	@Override
	public boolean existeCitacionAudiencia(Long idCaso) {
		List<CorreoRolPersonaCaso> correosCitacion = manejadorCorreoRolPersonaCaso.correosCitacionAudiencia(idCaso);
		return !correosCitacion.isEmpty();
	}

	/**
	 * Metodo que retorna tre si se realizo una audiencia para el caso
	 * 
	 * @return
	 */
	@Override
	public boolean existeRealiazacionAudiencia(Long idCaso) {
		List<Audiencia> audiencias = this.obtenerAudienciasCasoPorTipoYEstado(idCaso, null,
				UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
		return !audiencias.isEmpty();
	}

	@Override
	public List<AudienciaDTO> consultarAudienciasPendientesPorJornada(Long idJornada) {
		return manejadorAudiencia.consultarAudienciasPendientesPorJornada(idJornada);
	}

	@Override
	public Long obtenerNumeroAudienciasCaso(Long idCaso, List<String> filtroEstados) {
		return manejadorAudiencia.obtenerNumeroAudienciasCaso(idCaso, filtroEstados);
	}

	@Override
	public CitacionDTO consultarInformacionCitacion(Long idCaso) {

		List<PersonaBasicaDTO> listaPartes;
		CitacionDTO informacionAudiencia = new CitacionDTO();

		List<CitacionDTO> audienciasPendientes = manejadorAudiencia.consultarInformacionAudienciaPendiente(idCaso);
		if (!audienciasPendientes.isEmpty()) {

			listaPartes = manejadorPersona.consultarCorreosElectronicosPorPersonaCaso(idCaso);
			informacionAudiencia.setListaPersonaBasica(listaPartes);
			AudienciaDTO audienciaPendiente = manejadorAudiencia.consultarUltimaAudienciaEstadoCaso(idCaso,
					UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
			Long numeroAudiencia = audienciaPendiente.getConsecutivo();
			informacionAudiencia.setNumeroAudiencia(numeroAudiencia);

			informacionAudiencia.setHoraFin(audienciasPendientes.get(0).getHoraFin());
			informacionAudiencia.setHoraInicio(audienciasPendientes.get(0).getHoraInicio());
			informacionAudiencia.setIdAudiencia(audienciasPendientes.get(0).getIdAudiencia());
			informacionAudiencia.setSedeAudiencia(audienciasPendientes.get(0).getSedeAudiencia());
		}

		return informacionAudiencia;
	}

	@Override
	public boolean programarAudiencias(Long idConvenio) {
		if (idConvenio == null)
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR019.toString()));
		boolean falloEnAsignacion = false;
		// [0] = idPersona, [1] = cantidad de casos asignados
		List<Object[]> personasCasos = manejadorRolPersonaCaso.obtenerListadoCasosPersona(idConvenio,
				UtilDominios.ROL_PERSONA_CONVOCANTE);
		// [0] = idTurnoJornada, [1] = cantidad de turnos disponibles
		List<Object[]> turnosConDisponibilidad = manejadorTurnoJornada.obtenerCupoDisponibleTurno(idConvenio);
		for (Object[] objects : personasCasos) {
			if ((int) objects[1] > turnosConDisponibilidad.size())
				falloEnAsignacion = true;
			asignarTurnoCasos(((BigDecimal) objects[0]).longValue(), turnosConDisponibilidad, idConvenio);
		}
		return falloEnAsignacion;
	}

	/**
	 * Método para realizar el llamado de la asignacion de la audiencia por cada
	 * caso de la persona que recibe como parametro
	 * 
	 * @param idPersona
	 * @param turnosConDisponibilidad
	 */
	private void asignarTurnoCasos(Long idPersona, List<Object[]> turnosConDisponibilidad, Long idConvenio) {
		List<Object> casos = manejadorCaso.obtenerCasosPersonaPorRol(idPersona.longValue(),
				UtilDominios.ROL_PERSONA_CONVOCANTE, idConvenio);
		int turnoActual = 0;
		for (int i = 0; i < casos.size(); i++) {
			if (i < turnosConDisponibilidad.size())
				turnoActual = asignarTurnoCaso(((BigDecimal) casos.get(i)).longValue(), turnosConDisponibilidad,
						turnoActual);
		}
	}

	/**
	 * Método que realiza la asignacion de la audiencia al caso si este no cuenta
	 * con una audiencia en estado pendiente
	 * 
	 * @param idCaso
	 * @param turnosConDisponibilidad
	 * @param turnoActual
	 * @return
	 */
	private int asignarTurnoCaso(Long idCaso, List<Object[]> turnosConDisponibilidad, int turnoActual) {
		int turno = turnoActual;
		boolean isAsignado = !manejadorAudiencia
				.consultarAudienciasCasoPorTipoYEstado(idCaso, null, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE).isEmpty();
		if (isAsignado)
			return turnoActual;

		for (int i = turnoActual; i < turnosConDisponibilidad.size(); i++) {
			if ((int) turnosConDisponibilidad.get(i)[1] > 0) {
				crearAudienciaDeTurno(idCaso, ((BigDecimal) turnosConDisponibilidad.get(i)[0]).longValue());
				turnosConDisponibilidad.get(i)[1] = ((int) turnosConDisponibilidad.get(i)[1]) - 1;
				turno = i + 1;
				break;
			}
		}
		return turno;
	}

	public void crearAudienciaDeTurno(Long idCaso, Long idTurnoJornada) {
		TurnoJornada turnoJornada = manejadorTurnoJornada.buscar(idTurnoJornada);
		Audiencia audiencia = new Audiencia();
		audiencia.setIdCaso(idCaso);
		audiencia.setHoraInicio(turnoJornada.getHoraInicio());
		audiencia.setHoraFin(turnoJornada.getHoraFin());
		audiencia.setTipoAudiencia(UtilDominios.TIPO_AUDIENCIA_ARBITRAL_CONCILIACION);
		audiencia.setEstado(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		audiencia.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		audiencia.setConsecutivo(1L);
		programarAudiencia(audiencia, false);

		AudienciaTurnoJornada audienciaTurno = new AudienciaTurnoJornada();
		AudienciaTurnoJornadaPK pk = new AudienciaTurnoJornadaPK();
		pk.setIdAudiencia(audiencia.getIdAudiencia());
		pk.setIdTurnoJornada(idTurnoJornada);
		audienciaTurno.setAudienciaTurnoJornadaPK(pk);
		audienciaTurno.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		manejadorAudienciaTurnoJornada.crear(audienciaTurno);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IAudienciaFacade#consultarInformacionAudienciaPendiente(com.ccb.simasc.
	 * transversal.entidades. Audiencia)
	 */
	@Override
	public List<CitacionDTO> consultarInformacionAudienciaPendiente(Long idCaso) {
		List<CitacionDTO> listaCitaciones = manejadorAudiencia.consultarInformacionAudienciaPendiente(idCaso);
		return listaCitaciones != null ? listaCitaciones : new ArrayList<CitacionDTO>();
	}

	/**
	 * CON-F-106
	 * 
	 * valida que la audiencia de conciliacion que se desea programar cumpla las
	 * reglas del CON-F-106
	 * 
	 * @param consultaAgendamiento
	 * @author prendon
	 */
	private void validarProgramacionAudienciaConciliacion(ConsultaAgendamientoDTO audienciaPorProgramar,
			CasoDTO casoDTO) throws SimascNegocioPruebaException {

		boolean angendamientoValido = agendamientoFacade.validarDisponibilidadConciliador(
				audienciaPorProgramar.getIdConciliador(), audienciaPorProgramar.getFechaInicio(),
				audienciaPorProgramar.getFechaInicio(), audienciaPorProgramar.getFechaFin(),
				audienciaPorProgramar.getIdSede());
		if (!angendamientoValido) {
			throw new SimascNegocioPruebaException(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR537.toString())));
		}

		if (casoDTO.getEstadoCaso().equals(UtilDominios.ESTADO_CASO_REGISTRADO)
				|| casoDTO.getEstadoCaso().equals(UtilDominios.ESTADO_CASO_SUSPENDIDO)) {
			throw new SimascNegocioPruebaException(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR489.toString())));
		}

		if (diaFestivoFacade.validarSiFechaEsFestivo(audienciaPorProgramar.getFechaInicio()))
			throw new SimascNegocioPruebaException(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR572.toString())));

		boolean prorrogaCasoVigentePorfecha = solicitudProrrogaFacade.validarProrrogaCasoVigentePorFecha(
				audienciaPorProgramar.getIdCaso(), audienciaPorProgramar.getFechaFin());

		Long diasEntreFechaAcualYProgramada = UtilOperaciones.calcularDiasEntreFechas(new Date(),
				audienciaPorProgramar.getFechaFin());
		Long diasTranscurridosCaso = manejadorCaso.consultarDiferenciaFechaCaso(audienciaPorProgramar.getIdCaso());
		List<String> nombreParametro = new ArrayList<String>();
		nombreParametro.add(UtilParamServicio.NUMERO_DIAS_CIERRE_CASO);

		Long parametroCierrecaso = null;

		List<ParametroDeServicio> parametroCierreList = manejadorParametroDeServicio.consultarParametrosDeServicio(
				nombreParametro, casoDTO.getIdServicio(), UtilParamServicio.TIPO_PARAMETRO_CIERRE_CASO);

		if (parametroCierreList != null && !parametroCierreList.isEmpty()) {
			parametroCierrecaso = Long.valueOf((parametroCierreList).get(0).getValor());
		} else {
			throw new SimascNegocioPruebaException(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR571.toString())));
		}

		if (diasTranscurridosCaso > parametroCierrecaso && !prorrogaCasoVigentePorfecha) {
			throw new SimascNegocioPruebaException(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR487.toString())));
		} else if ((diasTranscurridosCaso + diasEntreFechaAcualYProgramada) > parametroCierrecaso
				&& !prorrogaCasoVigentePorfecha) {
			throw new SimascNegocioPruebaException(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR488.toString())));
		}
	}

	@Override
	public AudienciaDTO consultarUltimaAudienciaRealizada(Long idCaso) {
		// Consultar Ultima Audiencia Realizada
		AudienciaDTO audienciaRealizada = manejadorAudiencia.consultarUltimaAudienciaEstadoCaso(idCaso,
				UtilDominios.ESTADO_AUDIENCIA_REALIZADA);

		if (audienciaRealizada == null) {
			throw new SIMASCNegocioExcepcion(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO353.toString())));
		}
//		else if (manejadorDocumento.consultarDocumentoCasoTipoDocumento(idCaso, UtilDominios.TIPO_DOCUMENTO_DIG_ACTA)
//				|| manejadorDocumento.consultarDocumentoCasoTipoDocumento(idCaso,
//						UtilDominios.TIPO_DOCUMENTO_DIG_CONSTANCIA_RESULTADO_AUDIENCIA)) {
//			throw new SIMASCNegocioExcepcion(
//					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO354.toString())));
//		} 
		else {

			audienciaRealizada.setResultado(
					manejadorAudiencia.consultarNombreResultadoAudiencia(audienciaRealizada.getIdAudiencia()));
		}

		return audienciaRealizada;
	}

	@Override
	public void revertirResultadoAudiencia(AudienciaDTO audiencia, String usuario) throws EstadosCasoException {

		List<ResultadoAudiencia> listaResultadoAudiencias = manejadorResultadoAudiencia
				.consultarResultadoAudiencias(audiencia.getIdAudiencia());

		List<Long> idDocumentosEliminar = new ArrayList<Long>();
		// Documentos a eliminar de resultados audiencia
		for (ResultadoAudiencia resultadoAudiencia : listaResultadoAudiencias) {
			if (resultadoAudiencia.getIdDocumento() != null) {
				idDocumentosEliminar.add(resultadoAudiencia.getIdDocumento());
			}
		}

		// Consulta inasistencias a eliminar
		InasistenciaDTO inasistencia = new InasistenciaDTO();
		InasistenciaPK inasistenciaPK = new InasistenciaPK();
		inasistenciaPK.setIdAudiencia(audiencia.getIdAudiencia());
		inasistencia.setInasistenciaPK(inasistenciaPK);
		inasistencia.setTipo(UtilDominios.DOMINIO_TIPO_INASISTENCIA);
		List<Inasistencia> listaInasistencias = manejadorInasistencia.consultarInasistencias(inasistencia);

		// Documentos de inasistencia a eliminar
		for (Inasistencia inasistenciaDocumento : listaInasistencias) {
			if (inasistenciaDocumento.getIdDocumento() != null) {
				idDocumentosEliminar.add(inasistenciaDocumento.getIdDocumento());
			}
		}

		// Elimina inasistencias y resultados de audiencia, obligacion, cuota y
		// obligación parte
		manejadorInasistencia.eliminarListaFisicamente(listaInasistencias);
		manejadorResultadoAudiencia.eliminarListaFisicamente(listaResultadoAudiencias);

		// Elimina los documentos de resultado audiencia e inasistencia
		for (Long idDocumento : idDocumentosEliminar) {
			try {
				almacenamientoDocumentosFacade.eliminarDocumento(idDocumento, usuario);
			} catch (Exception e) {
				logger.error(e);
			}
		}

		// cambia estado de la audiencia a Pendiente y resultado a null
		Audiencia cambioAudiencia = manejadorAudiencia.buscar(audiencia.getIdAudiencia());
		cambioAudiencia.setEstado(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		cambioAudiencia.setResultado(null);
		manejadorAudiencia.actualizar(cambioAudiencia);

		// Log de la ruta del caso
		Date fechaEvento = new Date();
		casoFacade.cambiarEstadoCaso(audiencia.getIdCaso(), UtilDominios.ESTADO_CASO_EN_CONCILIACION, fechaEvento,
				UtilDominios.TIPO_EVENTO_REVERSAR_RESULTADO_AUDIENCIA, audiencia.getObservaciones());

		// Cambio de estado del caso a ENCONCIL y resultado a null
		Caso estadoCaso = manejadorCaso.buscar(audiencia.getIdCaso());
		estadoCaso.setResultado(null);

		manejadorCaso.actualizar(estadoCaso);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IAudienciaFacade#programarAudienciaConciliacion(com.ccb.simasc.
	 * transversal.entidades. Audiencia)
	 */
	@Override
	public void programarAudienciaConciliacion(ConsultaAgendamientoDTO audienciaPorProgramar)
			throws SimascNegocioPruebaException {

		CasoDTO casoDTO = casoFacade.consultarCasoActivo(audienciaPorProgramar.getIdCaso());
		String descripcionAudiencia = "Audiencia para el caso " + casoDTO.getIdCaso().toString() + "-"
				+ casoDTO.getNombre();
		Convenio convenioCaso = null;

		AudienciaDTO ultimaAudiencia = manejadorAudiencia
				.consultarUltimaAudienciaEstadoCaso(audienciaPorProgramar.getIdCaso(), null);

		audienciaPorProgramar.setIdServicio(casoDTO.getIdServicio());

		if (audienciaPorProgramar.getTipoAudiencia() != null
				&& !audienciaPorProgramar.getTipoAudiencia().equals(UtilDominios.TIPO_AUDIENCIA_SORTEO_MEDIACION)) {
			validarProgramacionAudienciaConciliacion(audienciaPorProgramar, casoDTO);
		}

		Audiencia audienciaProgramada = new Audiencia();
		if (ultimaAudiencia != null && UtilDominios.ESTADO_AUDIENCIA_PENDIENTE.equals(ultimaAudiencia.getEstado())) {
			audienciaProgramada = new AudienciaDTO().transformarDTOAEntidad(ultimaAudiencia);

			if (audienciaProgramada.getHoraInicio().getTime() != audienciaPorProgramar.getFechaInicio().getTime()) {
				// si no son iguales se cancela la audiencia
				audienciaProgramada.setEstado(UtilDominios.ESTADO_AUDIENCIA_CANCELADA);
				manejadorAudiencia.actualizar(audienciaProgramada);
				// tras cancelarla, se libera la sala
				Agendamiento agendamiento = manejadorAgendamiento
						.buscarAgendamientoAudiencia(audienciaProgramada.getIdAudiencia());
				if (agendamiento != null) {
					agendamiento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
					manejadorAgendamiento.actualizar(agendamiento);
				}
				// se crea la nueva audiencia
				audienciaProgramada = crearAudienciaProgramada(audienciaPorProgramar, ultimaAudiencia);

				agendamientoFacade.crearAgendamientoAudienciaConciliacion(audienciaProgramada.getIdCaso(),
						audienciaProgramada.getIdAudiencia(), audienciaPorProgramar.getIdSede(),
						audienciaProgramada.getHoraInicio(), audienciaProgramada.getHoraFin(), descripcionAudiencia);
			} else {
				// actualiza los registros de auditoria
				manejadorAudiencia.actualizar(audienciaProgramada);
			}
		} else {
			audienciaProgramada = crearAudienciaProgramada(audienciaPorProgramar, ultimaAudiencia);

			agendamientoFacade.crearAgendamientoAudienciaConciliacion(audienciaProgramada.getIdCaso(),
					audienciaProgramada.getIdAudiencia(), audienciaPorProgramar.getIdSede(),
					audienciaProgramada.getHoraInicio(), audienciaProgramada.getHoraFin(), descripcionAudiencia);

			if (audienciaPorProgramar.getIdServicio() == UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION
					&& audienciaPorProgramar.getTipoAudiencia() != null
					&& audienciaPorProgramar.getTipoAudiencia().equals(UtilDominios.TIPO_AUDIENCIA_SORTEO_MEDIACION)) {
				NombramientoPersona nombramientoPersona = new NombramientoPersona();
				nombramientoPersona.setIdCaso(audienciaProgramada.getIdCaso());
				nombramientoPersona.setMetodoDeNombramiento(UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
				nombramientoPersona
						.setCantFuncionariosPrincipales(UtilConstantes.CANT_FUNCIONARIOS_PRINCIPALES_MEDIACION);
				nombramientoPersona.setCantFuncionariosSuplentes(UtilConstantes.CANT_FUNCIONARIOS_SUPLENTES_MEDIACION);
				nombramientoPersona.setManejoDeSuplencia(UtilDominios.TIPO_MANEJO_SUPLENCIA_NUMERICA);
				nombramientoPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				nombramientoPersonaFacade.crear(nombramientoPersona);

				Caso caso = casoFacade.buscar(audienciaPorProgramar.getIdCaso());
				caso.setCantFuncionariosPrincipales(UtilConstantes.CANT_FUNCIONARIOS_PRINCIPALES_MEDIACION);
				caso.setPreseleccion(UtilConstantes.PRESELECCION_MEDIACION);

				casoFacade.actualizar(caso);
			}
		}
		List<String> args = new ArrayList<>();
		
		args.add(UtilOperaciones.formatearFechaFormato(audienciaProgramada.getHoraInicio(), "dd/MM/yyyy"));
		args.add(UtilOperaciones.formatearFechaFormato(audienciaProgramada.getHoraInicio(), "HH:mm"));

		if (audienciaPorProgramar.getTipoAudiencia()== null || !audienciaPorProgramar.getTipoAudiencia().equals(UtilDominios.TIPO_AUDIENCIA_SORTEO_MEDIACION)) {
			agendaPersonaFacade.bloquearAgendaPersona(audienciaPorProgramar.getIdConciliador(),
					UtilDominios.ESTADO_AGENDA_PERSONA_PROGRAMADA,
					UtilDominios.TIPO_ACTIVIDAD_AGENDA_AUDIENCIA_CONCILIACION, audienciaPorProgramar.getFechaInicio(),
					audienciaPorProgramar.getFechaFin(), audienciaProgramada.getIdAudiencia(), descripcionAudiencia);

			args.add(manejadorPersona.buscar(audienciaPorProgramar.getIdConciliador()).getNombreCompleto());
		}
		

		String observaciones = (String
				.format(MensajesSimasc.getInstancia()
						.getMensajePorKey((audienciaPorProgramar.getTipoAudiencia() != null && audienciaPorProgramar
								.getTipoAudiencia().equals(UtilDominios.TIPO_AUDIENCIA_SORTEO_MEDIACION))
										? MensajesEnum.INFO608.toString()
										: MensajesEnum.INFO119.toString()),
						args.toArray()));
		eventoFacade.registrarEvento(audienciaProgramada.getIdCaso(), UtilDominios.TIPO_EVENTO_AUDIENCIA_PROGRAMADA,
				observaciones, null);

		Long numeroAudienciasCelebradas = obtenerNumeroAudienciasCaso(casoDTO.getIdCaso(),
				Arrays.asList(UtilDominios.ESTADO_AUDIENCIA_REALIZADA));

		// Ajustes para validar correctamente el numero de audiencias para recobro
		// cuando el caso es de convenio
		Long paramNumeroAudienciaRecobro = 0L;

		if (casoDTO.getIdConvenio() != null
				&& UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO.equals(casoDTO.getIdServicio()))
			convenioCaso = manejadorConvenio.buscar(casoDTO.getIdConvenio());

		if (convenioCaso != null && convenioCaso.getNumeroDeAudienciasRecobro() != null) {
			paramNumeroAudienciaRecobro = Long.valueOf(convenioCaso.getNumeroDeAudienciasRecobro());
			
		}else if(!UtilConstantes.ID_SERVICIO_EQUIDAD.equals(casoDTO.getIdServicio())){
			
			paramNumeroAudienciaRecobro = Long.valueOf((manejadorParametroDeServicio.consultarParametrosDeServicio(
					Arrays.asList(UtilParamServicio.NUMERO_AUDIENCIAS_PARA_RECOBRO), casoDTO.getIdServicio(),
					UtilParamServicio.TIPO_PARAMETRO_AUDIENCIAS_RECOBRO)).get(0).getValor());
		}
		
		if (paramNumeroAudienciaRecobro > 0 && numeroAudienciasCelebradas >= paramNumeroAudienciaRecobro) {
			correoRolPersonaCasoFacade.notificarSecretariaConciliacion(casoDTO, audienciaProgramada.getIdAudiencia());
		}

		if (casoDTO.getEstadoCaso().equals(UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION)
				&& !(audienciaProgramada.getConsecutivo() == 1)) {
			cartaPersonaFacade.generarCartasProgramacionAudiencias(audienciaProgramada.getIdCaso(),
					audienciaProgramada.getIdAudiencia(), audienciaProgramada.getConsecutivo());
		}

		try {
			if (!(UtilDominios.ESTADO_CASO_EN_CONCILIACION.equals(casoDTO.getEstadoCaso())
					|| UtilDominios.ESTADO_CASO_CONCILIADOR_DESIGNADO.equals(casoDTO.getEstadoCaso())))
				casoFacade.cambiarEstadoCaso(casoDTO.getIdCaso(), UtilDominios.ESTADO_CASO_EN_CONCILIACION, null,
						UtilDominios.TIPO_EVENTO_CASO_CONCILIACION,
						UtilConstantes.CAMBIO_ESTADO_CASO_POR_PROGRAMACION_AUDIENCIA);
		} catch (EstadosCasoException c) {
			throw new SimascNegocioPruebaException(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(c.getMensaje())));
		}

	}

	/**
	 * CON-F-106 Construye la entidad Audiencia a partir del DTO
	 * "ConsultaAgendamientoDTO" y hace la persistencia
	 * 
	 * @author prendon
	 * @return Audiencia.class
	 */
	private Audiencia crearAudienciaProgramada(ConsultaAgendamientoDTO audienciaPorProgramar,
			AudienciaDTO ultimaAudiencia) {
		Audiencia audiencia = new Audiencia();
		Sede sede = manejadorSede.buscar(audienciaPorProgramar.getIdSede());
		if (sede.getTipoSede().equals(UtilDominios.SEDE_VIRTUAL)) {
			audiencia.setVirtual(true);
		} else {
			audiencia.setVirtual(false);
		}
		audiencia.setEstado(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		audiencia.setHoraInicio(audienciaPorProgramar.getFechaInicio());
		audiencia.setHoraFin(audienciaPorProgramar.getFechaFin());		
		if (audienciaPorProgramar.getTipoAudiencia() != null
				&& audienciaPorProgramar.getTipoAudiencia().equals(UtilDominios.TIPO_AUDIENCIA_SORTEO_MEDIACION)) {
			audiencia.setTipoAudiencia(UtilDominios.TIPO_AUDIENCIA_SORTEO_MEDIACION);
			audiencia.setTipoSorteo(UtilDominios.TIPO_DE_SORTEO_PUBLICO_MEDIADORES);
		} else if (audienciaPorProgramar.getTipoAudiencia() != null
				&& audienciaPorProgramar.getTipoAudiencia().equals(UtilDominios.TIPO_AUDIENCIA_MEDIACION)) {
			audiencia.setTipoAudiencia(UtilDominios.TIPO_AUDIENCIA_MEDIACION);
		} else {
			audiencia.setTipoAudiencia(UtilDominios.TIPO_AUDIENCIA_ARBITRAL_CONCILIACION);
		}

		audiencia.setIdCaso(audienciaPorProgramar.getIdCaso());

		audiencia.setConsecutivo((ultimaAudiencia != null && ultimaAudiencia.getConsecutivo() != null)
				? ultimaAudiencia.getConsecutivo() + 1
				: 1L);
		audiencia.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		audiencia.setCantidadPrincipales(audienciaPorProgramar.getCantidadArbitrosPrincipales());
		audiencia.setCantidadSuplentes(audienciaPorProgramar.getCantidadArbitrosSuplentes());
		manejadorAudiencia.crear(audiencia);
		return audiencia;
	}

	@Override
	public List<AudienciasProgramadasDTO> consultarAudienciasProgramadasConciliador(Long idSede, Date fechaAudiencia,
			Long idPersona) {
		return manejadorAudiencia.consultarAudienciasProgramadasConciliador(idSede, fechaAudiencia, idPersona);
	}

	@Override
	public void registrarAudienciaRealizada(RealizacionAudienciaDTO realizacionAudienciaDTO,
			String idUsuarioModificacion) throws SIMASCNegocioExcepcion {

		Caso caso = manejadorCaso.buscar(realizacionAudienciaDTO.getIdCaso());
		String tipoResultado = realizacionAudienciaDTO.getTipoResultadoAudiencia();

		// se almacena el campo area_asunto_clasificacion
		actualizarAreaAsuntoClasificacion(realizacionAudienciaDTO, idUsuarioModificacion);

		if ((tipoResultado != null && (tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_PARCIAL)
				|| tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_TOTAL)
				|| tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_IMPOSIBILIDAD_DE_ACUERDO))) 
				&& !caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION)
				&& !validarParte(realizacionAudienciaDTO.getPartes())) {			
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR164.toString()));			
		}

		List<ParteCasoDTO> partes = realizacionAudienciaDTO.getPartesIdentificacion();
		if (partes != null && !partes.isEmpty())
			actualizarPartes(partes);

		// si resultado es reprogramacion o cancela se libera agentamiento y
		// agenda_persona
		if (tipoResultado != null && (tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_CANCELADA)
				|| tipoResultado.equals(UtilDominios.RESULTADO_AUDIENCIA_REPROGRAMACION))) {
			Agendamiento agendamiento = manejadorAgendamiento
					.buscarAgendamientoAudiencia(realizacionAudienciaDTO.getIdAudiencia());
			if (agendamiento != null) {
				agendamientoFacade.cancelarAgendamiento(agendamiento.getIdAgendamiento());
			}
			List<String> estados = new ArrayList<String>();
			estados.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
			estados.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
			List<PersonaBasicaDTO> conciliadorPrincipal = rolPersonaCasoFacade
					.obtenerPersonaBasicaConciliadorCasoEstadoNombramiento(realizacionAudienciaDTO.getIdCaso(), estados,
							UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
			// si resultado es reprogramacion o cancela se libera agenda_persona del
			// conciliador
			if (conciliadorPrincipal != null && !conciliadorPrincipal.isEmpty()
					&& conciliadorPrincipal.get(0) != null) {
				agendaPersonaFacade.liberarAgendamientoPersona(conciliadorPrincipal.get(0).getIdPersona(),
						realizacionAudienciaDTO.getIdAudiencia());
			}

		}

		if (tipoResultado != null && !realizacionAudienciaDTO.isEdicionResultado()) {
			String estadoAudiencia = obtenerEstadoAudiencia(tipoResultado);
			actualizarEstadoAudiencia(realizacionAudienciaDTO.getIdAudiencia(), estadoAudiencia, idUsuarioModificacion);
		}
	}

	/**
	 * Metodo que permite actualizar el area, asunto y clasificacion en el caso.
	 * 
	 * @param realizacionAudienciaDTO: DTO que identifica la informacion de
	 *                                 realizacionAudiencia.
	 * @param idUsuarioModificacion:   Usuario que modifica la informacion de
	 *                                 audiencia.
	 */
	private void actualizarAreaAsuntoClasificacion(RealizacionAudienciaDTO realizacionAudienciaDTO,
			String idUsuarioModificacion) {
		Caso caso = manejadorCaso.buscar(realizacionAudienciaDTO.getIdCaso());
		if (realizacionAudienciaDTO.getIdArea() != null && realizacionAudienciaDTO.getIdAsunto() != null) {

			Long idAreaAsuntoClasificacion = manejadorAreaAsuntoClasificacion
					.obtenerIdPorLlaves(realizacionAudienciaDTO.getIdAsunto(),
							realizacionAudienciaDTO.getIdClasificacion() != null
									? realizacionAudienciaDTO.getIdClasificacion()
									: 0L);

			caso.setIdAreaAsuntoClasificacion(idAreaAsuntoClasificacion);
			caso.setAreaAsuntoClasificacion(null);
			caso.setFechaUltimaModificacion(new Date());
			caso.setIdUsuarioModificacion(idUsuarioModificacion);
			manejadorCaso.actualizarCaso(caso);
		}
	}

	/**
	 * Metodo que permite validar que por lo menos haya un convocado y convocante
	 * con identificacion.
	 * 
	 * @param partes: Partes del caso.
	 * @return true si existe convocado y convocante con identificacion.
	 */
	private boolean validarParte(List<ParteCasoDTO> partes) {
		boolean validoConvocante = false;
		boolean validoConvocado = false;
		for (ParteCasoDTO parte : partes) {
			if (parte.getNumeroIdentificacion() != null) {
				if (!validoConvocado && (parte.getRol().equals(UtilDominios.ROL_PERSONA_CONVOCADO) 
					|| parte.getRol().equals(UtilDominios.ROL_PERSONA_CONVOCADO_EQUIDAD)
					|| parte.getRol().equals(UtilDominios.ROL_PERSONA_DEUDOR))) {
					validoConvocado = true;
				} else if (!validoConvocante && (parte.getRol().equals(UtilDominios.ROL_PERSONA_CONVOCANTE) 
					||parte.getRol().equals(UtilDominios.ROL_PERSONA_SOLICITANTE_EQUIDAD) 
					||parte.getRol().equals(UtilDominios.ROL_PERSONA_ACREEDOR) )) {
					validoConvocante = true;
				}

				if (validoConvocado && validoConvocante)
					break;
			}
		}
		return validoConvocado && validoConvocante;
	}

	/**
	 * Metodo que permite obtener el estado de la audiencia.
	 * 
	 * @param tipoResultado: resultado de la audiencia.
	 * @return estadoAudiencia.
	 */
	private String obtenerEstadoAudiencia(String tipoResultado) {
		String estadoAudiencia = null;
		switch (tipoResultado) {
		case UtilDominios.RESULTADO_AUDIENCIA_SUSPENSION:
			estadoAudiencia = UtilDominios.ESTADO_AUDIENCIA_SUSPENDIDA;
			break;
		case UtilDominios.RESULTADO_AUDIENCIA_REPROGRAMACION:
			estadoAudiencia = UtilDominios.ESTADO_AUDIENCIA_APLAZADA;
			break;
		case UtilDominios.RESULTADO_AUDIENCIA_CANCELADA:
			estadoAudiencia = UtilDominios.ESTADO_AUDIENCIA_CANCELADA;
			break;
		default:
			estadoAudiencia = UtilDominios.ESTADO_AUDIENCIA_REALIZADA;
			break;

		}
		return estadoAudiencia;

	}

	/**
	 * Metodo que permite actualizar el numero de identificacion de las partes.
	 * 
	 * @param partes: partes del caso.
	 */
	private void actualizarPartes(List<ParteCasoDTO> partes) {
		for (ParteCasoDTO parte : partes) {
			if (parte.getNumeroIdentificacion() != null && !parte.getNumeroIdentificacion().equals("")) {
				Persona persona = manejadorPersona.buscar(parte.getIdPersona());
				Persona existePersona = manejadorPersona.consultarPersonaPorIdentificacion(
						parte.getTipoIdentificacion(), parte.getNumeroIdentificacion());
				if (existePersona != null)
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR166.toString()));
				persona.setTipoDocumento(parte.getTipoIdentificacion());
				persona.setNumeroDocumento(parte.getNumeroIdentificacion());
				manejadorPersona.actualizar(persona);
			}

		}
	}

	/**
	 * Metodo que permite actualizar el estado de la audiencia.
	 * 
	 * @param idAudiencia:           Identificador de la audiencia.
	 * @param estadoAudiencia:       Estado de la audiencia.
	 * @param idUsuarioModificacion: Usuario que modifica el estado de la audiencia.
	 */
	private void actualizarEstadoAudiencia(Long idAudiencia, String estadoAudiencia, String idUsuarioModificacion) {
		Audiencia audiencia = manejadorAudiencia.buscar(idAudiencia);
		audiencia.setEstado(estadoAudiencia);
		audiencia.setFechaUltimaModificacion(new Date());
		audiencia.setIdUsuarioModificacion(idUsuarioModificacion);
		manejadorAudiencia.actualizar(audiencia);
	}

	@Override
	public List<Long> obtenerCantidadArbitrosDisponibles(Long idCaso) {
		List<Long> cantidades = manejadorAudiencia.consultarCantFuncionariosPendientes(idCaso);
		return cantidades;
	}

	/**
	 * Metodo encargado de generar el reporte de los casos que han sido sorteados
	 * 
	 * @param filtros
	 * @return List<ReporteCasosSorteadosDTO>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ReporteCasosSorteadosDTO> getReporteCasosSorteados(Date fechaInicial, Date fechaFinal, Long tipoCaso) {
		List<ReporteCasosSorteadosDTO> casosSorteados = new ArrayList<ReporteCasosSorteadosDTO>();

		List<Sorteo> sorteos = manejadorSorteo.consultarSorteosPublicosRealizados(fechaInicial, fechaFinal);

		for (Sorteo sorteo : sorteos) {
			Caso caso = sorteo.getCaso();
			if (caso != null) {
				ReporteCasosSorteadosDTO reporteDto = new ReporteCasosSorteadosDTO();
				reporteDto.setIdCaso(caso.getIdCaso());
				reporteDto.setNombreCaso(caso.getNombre());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				reporteDto.setFechaSorteo(
						sorteo.getFechaRealizacion() != null ? dateFormat.format(sorteo.getFechaRealizacion()) : null);
				reporteDto.setTipoCaso(caso.getServicioMateria().getServicio().getNombre());
				reporteDto.setMateria(caso.getServicioMateria().getMateria().getNombre());
				reporteDto.setValorPretensiones(caso.getValorPretensiones());
				reporteDto.setTipoCuantia(sorteo.getTipoCuantia());
				// se filtran los arbitros a mostrar
				List<RolPersonaCaso> arbitrosSelec = obtenerArbitrosSorteoCaso(sorteo, caso);

				reporteDto.setArbitros(arbitrosSelec);
				reporteDto.setPreseleccion(caso.getPreseleccion());
				casosSorteados.add(reporteDto);
			}
		}
		return casosSorteados;
	}

	/**
	 * Método encargado de obtener la lista de árbitros de un caso nombrados por
	 * sorteo y ordenados por el tipo de nombramiento al momento de la realización
	 * del mismo
	 * 
	 * @param sorteo
	 * @param caso
	 * @return
	 */
	private List<RolPersonaCaso> obtenerArbitrosSorteoCaso(Sorteo sorteo, Caso caso) {
		List<RolPersonaCaso> arbitrosSelec = manejadorRolPersonaCaso.obtenerArbitrosSorteoCaso(caso.getIdCaso(),
				sorteo.getIdSorteo());

		// Obtiene el tipo de nombramiento del árbitro resultado del sorteo y no
		// el actual
		RolPersonaCasoDTO dto = new RolPersonaCasoDTO();
		for (RolPersonaCaso rolPersonaCaso : arbitrosSelec) {
			rolPersonaCaso.obtenerNombramientoPorSorteo();
		}

		List<RolPersonaCaso> arbitrosSelecOrdenados = new ArrayList<RolPersonaCaso>(arbitrosSelec);

		// Ordena los árbitros de acuerdo al nombramiento resultado del sorteo
		Collections.sort(arbitrosSelecOrdenados, new Comparator<RolPersonaCaso>() {

			@Override
			public int compare(RolPersonaCaso o1, RolPersonaCaso o2) {
				return o1.getNombramientoSorteo().compareTo(o2.getNombramientoSorteo());
			}
		});
		arbitrosSelecOrdenados = (List<RolPersonaCaso>) dto
				.transformarColeccionEntidadesConDependencias(arbitrosSelecOrdenados);
		return arbitrosSelecOrdenados;
	}

	@Override
	public void eliminarAudiencia(Audiencia audiencia) {
		
		audiencia.setFechaUltimaModificacion(new Date());
		audiencia.setIdUsuarioModificacion(
				audiencia.getIdUsuarioModificacion() != null ? audiencia.getIdUsuarioModificacion()
						: UtilConstantes.USUARIO_DEFECTO_SIMASC);
		manejadorAudiencia.actualizar(audiencia);
		
		List<Agendamiento> agendamientos = manejadorAgendamiento.buscarAgendamientosAudiencia(audiencia.getIdAudiencia());

		for (Agendamiento agendamientoFor : agendamientos) {
			agendamientoFor.setFechaUltimaModificacion(new Date());
			agendamientoFor.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_INACTIVO);
			agendamientoFor.setIdUsuarioModificacion(
					audiencia.getIdUsuarioModificacion() != null ? audiencia.getIdUsuarioModificacion()
							: UtilConstantes.USUARIO_DEFECTO_SIMASC);
			manejadorAgendamiento.actualizar(agendamientoFor);			
		}	
		List<String> estados = new ArrayList<String>();
		estados.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		estados.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		
		List<PersonaBasicaDTO> conciliadorPrincipal = rolPersonaCasoFacade
				.obtenerPersonaBasicaConciliadorCasoEstadoNombramiento(audiencia.getIdCaso(), estados,
                        UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);

        if (conciliadorPrincipal != null && !conciliadorPrincipal.isEmpty() && conciliadorPrincipal.get(0) != null) {

            agendaPersonaFacade.liberarAgendamientoPersona(conciliadorPrincipal.get(0).getIdPersona(), audiencia.getIdAudiencia());
        }

		this.crearEventoRutaCaso(audiencia);
	}

	// protected region metodos adicionales end

}
