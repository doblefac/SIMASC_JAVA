package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorApoderados;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDiaFestivo;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.ManejadorEventoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroServicioSorteo;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPlantillaCarta;
import com.ccb.simasc.integracion.manejadores.ManejadorProgramacionAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorPronunciamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorUsuario;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IArbitroCasoLiberacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICartaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPronunciamientoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISorteoFacade;
import com.ccb.simasc.negocio.fachadas.reparto.implementaciones.RepartoSvc;
import com.ccb.simasc.transversal.dto.ApoderadosParteDTO;
import com.ccb.simasc.transversal.dto.AudienciaDTO;
import com.ccb.simasc.transversal.dto.CambioEstadoSuplenteDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.DatosEntradaRepartoDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.PendientePronunciamientoDTO;
import com.ccb.simasc.transversal.dto.PronunciamientoDTO;
import com.ccb.simasc.transversal.dto.formularios.InfoCasoParaPronunciamientoDTO;
import com.ccb.simasc.transversal.dto.formularios.InfoRegistroPronunciamientoDTO;
import com.ccb.simasc.transversal.dto.formularios.ParteCasoDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.DominioPK;
import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.ProgramacionAlerta;
import com.ccb.simasc.transversal.entidades.Pronunciamiento;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Sorteo;
import com.ccb.simasc.transversal.excepciones.EstadosCasoException;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Pronunciamiento}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PronunciamientoFacade extends AccesoFacade<Pronunciamiento, Long, PronunciamientoDTO>
		implements IPronunciamientoFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	private static final Logger logger = LogManager.getLogger(PronunciamientoFacade.class.getName());

	@EJB
	private ManejadorPronunciamiento manejadorPronunciamiento;

	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorRolPersona manejadorRolPersona;

	@EJB
	private ManejadorEventoRolPersonaCaso manejadorEventoRolPersonaCaso;

	@EJB
	private ManejadorDocumento manejadorDocumento;

	@EJB
	private ManejadorAudiencia manejadorAudiencia;

	@EJB
	private IDominioFacade dominioFacade;

	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;

	@EJB
	private IEventoFacade eventoFacade;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private AlmacenamientoDocumentosFacade almacenamientoFacade;

	@EJB
	private ManejadorUsuario manejadorUsuario;

	@EJB
	private IDocumentoFacade documentoFacade;

	@EJB
	private ICartaPersonaFacade cartaPersonaFacade;

	@EJB
	private PersonaFacade personaFacade;

	@EJB
	private ManejadorPlantillaCarta manejadorPlantillaCarta;

	@EJB
	private ICasoFacade casoFacade;

	@EJB
	private ISorteoFacade sorteoFacade;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorDominio manejadorDominio;

	@EJB
	private IAgendaPersonaFacade agendaPersonaFacade;

	@EJB
	private ManejadorProgramacionAlerta manejadorProgramacionAlerta;

	@EJB
	private ManejadorAlerta manejadorAlerta;

	@EJB
	private ManejadorDiaFestivo manejadorDiaFestivo;

	@EJB
	private ManejadorParametroServicioSorteo manejadorParametroServicioSorteo;
	
	@EJB
	private ManejadorApoderados manejadorApoderados;
	
	@EJB
	private IArbitroCasoLiberacionFacade arbitroCasoLiberacionFacade;
	
	@EJB
	private RepartoSvc repartoSvc;

	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;

//	 @PersistenceContext
//	 private transient EntityManager em;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPronunciamiento;
	}

	@Override
	public PronunciamientoDTO transformarSinDependencias(Pronunciamiento obj) {
		PronunciamientoDTO dto = new PronunciamientoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public PronunciamientoDTO transformarConDependencias(Pronunciamiento obj) {
		PronunciamientoDTO dto = new PronunciamientoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Pronunciamiento transformarEntidadConDependencias(Pronunciamiento obj) {
		Pronunciamiento dto = new Pronunciamiento();
		dto = new PronunciamientoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Pronunciamiento transformarEntidadSinDependencias(Pronunciamiento obj) {
		Pronunciamiento dto = new Pronunciamiento();
		dto = new PronunciamientoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * ARB-F-052 Pronunciamiento del árbitro a la designación Consulta el caso al
	 * cual ha sido asignado el operador y que está pendiente de pronunciamiento.
	 * 
	 * En caso que haya más de un caso asignado se consultan los casos en los que ha
	 * sido asignado el operador que se pasa como parámetro y en caso de que sea más
	 * de uno se devuelve el caso en el que se asigno de primeras (la fecha más
	 * vieja).
	 * 
	 * @param idPersona el id del usuario operador para el cual se va a consultar su
	 *                  asignación
	 * @return El código del caso pendiente de pronunciamiento. Si no se encuentra
	 *         ningún caso o el rol no esta habilitado para pronunciamiento se
	 *         devuelve el número -1.
	 */
	public Long consultarCasoPendienteDePronunciamiento(Long idPersona) throws SIMASCNegocioExcepcion {

		Long casoAsignado = Long.valueOf(-1);
		try {
			List<String> nombreRoles = manejadorParametroServicioSorteo.obtenerRolesParametroServicioSorteo();
			List<RolPersona> rolPersonaConsulta = manejadorRolPersona.consultarRolesAsignados(idPersona, nombreRoles);

			if (rolPersonaConsulta != null && !rolPersonaConsulta.isEmpty()) {
				casoAsignado = manejadorPronunciamiento.consultarAsignacionPendienteDePronunciamiento(idPersona,
						nombreRoles);
			}
		} catch (SimascException e) {
			throw SIMASCNegocioExcepcion.transformTopException(e);
		}

		return casoAsignado;
	}

	/**
	 * Requerimiento ARB-F-072. Crea o actualiza la información del pronunciamiento
	 * del Ã¡rbitro
	 * 
	 * @param infoPronunciamientoDTO
	 * @param idPersona
	 * @param idCaso
	 * @param idUsuario
	 * @throws SIMASCNegocioExcepcion
	 * @throws  
	 */
	public Long guardarPronunciamiento(InfoRegistroPronunciamientoDTO infoPronunciamiento, Long idPersona, Long idCaso)
			throws SIMASCNegocioExcepcion {

		RolPersonaCaso arbitro = manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(idPersona, idCaso);
		Caso caso = manejadorCaso.buscar(idCaso);
		Pronunciamiento pronunciamiento = new PronunciamientoDTO()
				.transformarEntidadConDocumento(infoPronunciamiento.getPronunciamiento());
		String idUsuario;
		try {
			idUsuario = appContext.getContextoSesion().getNombreUsuario();
		} catch (Exception e) {
			idUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		}

		if (infoPronunciamiento.getPronunciamiento().getFecha() == null) {
			infoPronunciamiento.getPronunciamiento().setFecha(new Date());
		}

		// 1. Validación del pronunciamiento
		this.validarRegistroPronunciamiento(infoPronunciamiento);

		// dias transcurridos desde la comunicacion.
		boolean extemporanea = this.superaFechaPronunciamiento(infoPronunciamiento.getFechaComunicacion(),
				infoPronunciamiento.getPronunciamiento().getFecha());
		this.registrarPronunciamiento(extemporanea, pronunciamiento, idPersona, idCaso, idUsuario);
		// indica si el arbitro fue nombrado por la ccb		
		boolean nombradoCCB = arbitro.getMetodoNombramiento() == null ? false : arbitro.getMetodoNombramiento().equals(UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
		// 2. Actualización de fecha de comunicación
//		registrarEventoRolPersonaCaso(UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO, arbitro, 
//				idUsuario, infoPronunciamiento.getFechaComunicacion());
		
		// 3. Actualización de la fecha de designación
		registrarEventoRolPersonaCaso(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR, arbitro, idUsuario,
				infoPronunciamiento.getFechaDesignacion());
		
		if(arbitro.getRol().getNombre().equals(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL)) {
			registrarEventoRolPersonaCaso(UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO, arbitro, idUsuario,
					infoPronunciamiento.getFechaComunicacion());
		}
		
		// Actualización de la fecha de pronunciamiento
		// 3. Actualización de la fecha de designación
		if (infoPronunciamiento.getPronunciamiento().getFecha() != null
				|| infoPronunciamiento.getPronunciamiento().getPronunciamiento() != null) {
			// RQ12 15/01/2019
			// si el árbitro se pronuncia y la fecha de pronunciamiento es mayor a 5 días
			// hábiles a partir de la fecha de comunicación, se debe cambiar el estado a No
			// sorteable
			if (extemporanea && nombradoCCB && manejadorParametroServicioSorteo.suspendeExtemporaneo(idCaso) && !UtilDominios.TIPO_PRONUNCIAMIENTO_NO_SE_PRONUNCIA
					.equals(pronunciamiento.getPronunciamiento())) {

				RolPersona rolPersonaJefe = (manejadorRolPersona.obtenerRolPersona(
						arbitro.getRolPersonaCasoPK().getIdRol(), arbitro.getRolPersonaCasoPK().getIdPersona())).get(0);
				Date fechaEjecucion = UtilOperaciones.adicionarMesesFecha(new Date(), 6);
				Alerta alerta = manejadorAlerta.obtenerAlertaPorCodigo(UtilDominios.CODIGO_ALERTA_RE_ACTIVAR_ARBITRO)
						.get(0);

				// se crea la alerta
				ProgramacionAlerta programacionAlerta = new ProgramacionAlerta();
				programacionAlerta.setEstado(UtilDominios.PROGRAMACION_ALERTA_PENDIENTE);
				programacionAlerta.setIdCaso(arbitro.getRolPersonaCasoPK().getIdCaso());
				programacionAlerta.setIdPersona(arbitro.getRolPersonaCasoPK().getIdPersona());
				programacionAlerta.setFechaEjecucion(fechaEjecucion);
				// aqui llamo al manejador alerta y consulto la alerta por codigo.
				programacionAlerta.setIdAlerta(alerta.getIdAlerta());
				manejadorProgramacionAlerta.crear(programacionAlerta);
				personaFacade.inactivacionSegunMotivo(idPersona, UtilDominios.MOTIVO_CONTESTACION_EXTEMPORANEA,
						arbitro.getRolPersonaCasoPK().getIdRol(), caso.getIdServicio());

				// se envia la notificación
				enviarNotificacionJefeArbitraje(arbitro.getPersona().getNombreCompleto(),
						infoPronunciamiento.getPronunciamiento().getPronunciamiento(), rolPersonaJefe.getIdCentro(),
						arbitro.getRolPersonaCasoPK().getIdCaso(), UtilDominios.MOTIVO_CONTESTACION_EXTEMPORANEA);
			}
			// Control de Cambios CC005 Punto 11
			if (UtilDominios.TIPO_PRONUNCIAMIENTO_NO_SE_PRONUNCIA
					.equals(infoPronunciamiento.getPronunciamiento().getPronunciamiento())) {
				registrarEventoRolPersonaCaso(UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO, arbitro, idUsuario,
						infoPronunciamiento.getPronunciamiento().getFecha());
			} else if (UtilDominios.TIPO_PRONUNCIAMIENTO_DECLINA
					.equals(infoPronunciamiento.getPronunciamiento().getPronunciamiento())) {
				registrarEventoRolPersonaCaso(UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO, arbitro, idUsuario,
						infoPronunciamiento.getPronunciamiento().getFecha());
			} else {
				registrarEventoRolPersonaCaso(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO, arbitro, idUsuario,
						infoPronunciamiento.getPronunciamiento().getFecha());
			}
		} else {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR054.toString()));
		}

		// Actualización del pronunciamiento
		pronunciamiento.setDocumento(null);
		pronunciamiento.setIdUsuarioModificacion(idUsuario);
		pronunciamiento.setFechaUltimaModificacion(new Date());
		pronunciamiento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if (pronunciamiento.getIdPronunciamiento() == null) {
			manejadorPronunciamiento.crear(pronunciamiento);
		} else {
			manejadorPronunciamiento.actualizar(pronunciamiento);
		}

		arbitro.setIdPronunciamiento(pronunciamiento.getIdPronunciamiento());

		// Si se realizo actualización de documento se elimina el documento anterior
		if (infoPronunciamiento.getIdDocumentoAnterior() != null) {
			documentoFacade.eliminarDocumento(infoPronunciamiento.getIdDocumentoAnterior(), idUsuario);
		}
				
		return pronunciamiento.getIdPronunciamiento();
	}

	public boolean superaFechaPronunciamiento(Date fechaComunicacion, Date fechaPronunciamiento) {
		if (fechaComunicacion == null) {
			fechaComunicacion = new Date();
		}
		if (fechaPronunciamiento == null) {
			fechaPronunciamiento = new Date();
		}
		int diasPronunciamiento = manejadorDiaFestivo.obtenerDiasEntreDosFechas(fechaComunicacion,
				fechaPronunciamiento);
		return diasPronunciamiento > 5;
	}

	/**
	 * ARBF072 Actualiza o crea el EventoRolPersonaCaso asociado al estado que se
	 * pasa como parámetro con la fecha fechaEvento.
	 * 
	 * @param estadoEvento
	 * @param personaEvento
	 * @param usuario
	 * @param fechaEvento
	 */
	private void registrarEventoRolPersonaCaso(String estadoEvento, RolPersonaCaso personaEvento, String usuario,
			Date fechaEvento) {
		EventoRolPersonaCaso evento = null;
		if (UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR.equals(estadoEvento)) {
			evento = personaEvento.consultarEventoAsignacion();
		} else if (UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO.equals(estadoEvento)) {
			evento = personaEvento.consultarEventoDeComunicacion();
		} else if (UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO.equals(estadoEvento)) {
			List<EventoRolPersonaCaso> eventos = personaEvento
					.consultarEventosPorEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
			if (eventos.size() > 0) {
				evento = eventos.get(0);
			}
		} else if (UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO.equals(estadoEvento)) {
			List<EventoRolPersonaCaso> eventos = personaEvento
					.consultarEventosPorEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
			if (eventos.size() > 0) {
				evento = eventos.get(0);
			}
		}

		if (evento != null) {
			if (!evento.getFechaDeAsignacion().equals(fechaEvento)) {
				evento.setFechaDeAsignacion(fechaEvento);
				evento.setFechaUltimaModificacion(new Date());
				evento.setIdUsuarioModificacion(usuario);
				manejadorEventoRolPersonaCaso.actualizar(evento);
			}
		} else {
			evento = new EventoRolPersonaCaso();
			evento.setEstadoAsignado(estadoEvento);
			evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			evento.setFechaDeAsignacion(fechaEvento);
			evento.setFechaUltimaModificacion(new Date());
			evento.setIdUsuarioModificacion(usuario);
			evento.setIdCaso(personaEvento.getRolPersonaCasoPK().getIdCaso());
			evento.setIdPersona(personaEvento.getRolPersonaCasoPK().getIdPersona());
			evento.setIdRol(personaEvento.getRolPersonaCasoPK().getIdRol());
			manejadorEventoRolPersonaCaso.crear(evento);
		}

	}

	/**
	 * Requerimiento ARB-F-072. Consulta el pronunciamiento del arbitro.
	 * 
	 * @param idPersona
	 * @param idCaso
	 * @throws SIMASCNegocioExcepcion
	 */
	public InfoRegistroPronunciamientoDTO consultarPronunciamiento(Long idPersona, Long idCaso)
			throws SIMASCNegocioExcepcion {
		RolPersonaCaso arbitro = manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(idPersona, idCaso);

		if (arbitro == null) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR054.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}

		return generarInfoRegistroPronunciamientoDTO(arbitro, idCaso, idPersona);
	}

	private InfoRegistroPronunciamientoDTO generarInfoRegistroPronunciamientoDTO(RolPersonaCaso arbitro, Long idCaso,
			Long idPersona) {

		InfoRegistroPronunciamientoDTO infoPronunciamiento = new InfoRegistroPronunciamientoDTO();

		infoPronunciamiento.setFechaComunicacion(arbitro.consultarFechaDeComunicacion());
		if (arbitro.traerEventoDeDesignacion() != null) {
			infoPronunciamiento.setFechaDesignacion(arbitro.traerEventoDeDesignacion().getFechaDeAsignacion());
		}

		infoPronunciamiento.setNombreArbitro(arbitro.getPersona().getNombreCompleto());
		infoPronunciamiento.setMetodoNombramiento(arbitro.getMetodoNombramiento());
		if (arbitro.getPronunciamiento() != null) {
			Pronunciamiento pronunciamiento = arbitro.getPronunciamiento();
			infoPronunciamiento
					.setPronunciamiento(new PronunciamientoDTO().transformarConDependencias(pronunciamiento));
		}

		return infoPronunciamiento;
	}

	/**
	 * ARBF052
	 */
	@Override
	public InfoCasoParaPronunciamientoDTO consultarInfoCasoAsignadoArbitro(Long idCaso, Long idPersona)
			throws SIMASCNegocioExcepcion {

		RolPersonaCaso arbitro = manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(idPersona, idCaso);
		if (arbitro == null) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR054.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}

		return generarInfoCasoDTO(arbitro, idCaso, idPersona);
	}

	private InfoCasoParaPronunciamientoDTO generarInfoCasoDTO(RolPersonaCaso arbitro, Long idCaso, Long idPersona) {

		InfoCasoParaPronunciamientoDTO infoCaso = new InfoCasoParaPronunciamientoDTO();
		Caso caso = arbitro.getCaso();

		infoCaso.setAbogado(consultarNombreAbogadoDelCaso(idCaso));
		infoCaso.setCuantia(
				dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_CUANTIA, arbitro.getCaso().getTipoCuantia()));
		infoCaso.setDemandados(consultarDemandadosCaso(idCaso));
		infoCaso.setDemandantes(consultarDemandantesCaso(idCaso));
		infoCaso.setApoderadosParte(consultarApoderadosParte(idCaso));
		
		
		infoCaso.setDescripcionPacto(caso.getDescripcionPacto());
		infoCaso.setDocumentoPacto(caso.getDocumentoPacto());
		infoCaso.setFechaRadicacion(caso.getFechaRadicacion());
		infoCaso.setMateria(caso.getServicioMateria().getMateria().getNombre());
		infoCaso.setOperadores(consultarNombresOperadores(idCaso, idPersona));
		infoCaso.setSede(caso.getSede().getNombre());
		if (caso.getPagoCasoList() != null) {
			for (PagoCaso pagoFor : caso.getPagoCasoList()) {
				if (UtilDominios.ESTADO_REGISTRO_CLAVE_ACTIVO.equals(pagoFor.getEstadoRegistro())) {
					infoCaso.setFechaPago(pagoFor.getFechaPago());
				}
			}
		}

		infoCaso.setTipoCaso(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_SERVICIO,
				caso.getServicioMateria().getServicio().getTipo()));
		infoCaso.setTipoOperador(
				dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA, arbitro.getRol().getNombre()));
		infoCaso.setTipoPacto(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_PACTO, caso.getTipoPacto()));
		infoCaso.setTipoRadicacion(
				dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_RADICACION, caso.getTipoRadicacion()));
		infoCaso.setTipoServicio(caso.getServicioMateria().getServicio().getNombre());
		infoCaso.setValorPretensiones(caso.getValorPretensiones());

		if (arbitro.getPronunciamiento() != null) {
			Pronunciamiento pronunciamiento = arbitro.getPronunciamiento();
			infoCaso.setPronunciamiento(new PronunciamientoDTO().transformarConDependencias(pronunciamiento));
		}
		infoCaso.setIdServicio(caso.getIdServicio());

		return infoCaso;
	}

	private List<ApoderadosParteDTO> consultarApoderadosParte(Long idCaso) {
		
		List<ApoderadosParteDTO> list = manejadorApoderados.obtenerApoderadosParte(idCaso);
		if(list.isEmpty()) {
			return new ArrayList<ApoderadosParteDTO>();
		}
		return list;
		
	}

	/**
	 * ARBF052
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long registrarPronunciamiento(boolean extemporaneo, Pronunciamiento pronunciamiento, Long idPersona,
			Long idCaso, String idUsuario) throws SIMASCNegocioExcepcion {

		// 0. Se setea a nulo el documento debido a que ya ha sido persistido a través
		// del gestor documental
		pronunciamiento.setDocumento(null);
		// 1. Validaciones de negocio del pronunciamiento
		validarPronunciamiento(pronunciamiento);
		if (pronunciamiento.getFecha() == null) {
			pronunciamiento.setFecha(new Date());
		}

		pronunciamiento.setFechaUltimaModificacion(new Date());
		pronunciamiento.setIdUsuarioModificacion(idUsuario);
		pronunciamiento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);

		if (pronunciamiento.getIdPronunciamiento() != null) {
			manejadorPronunciamiento.actualizar(pronunciamiento);
		} else {
			manejadorPronunciamiento.crear(pronunciamiento);
		}

		Caso caso = casoFacade.buscar(idCaso);
		RolPersonaCaso rolPersonaCaso = manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(idPersona, idCaso);
		List<Alerta> listaAlerta = manejadorAlerta.obtenerAlertaPorCodigo(UtilDominios.CODIGO_ALERTA_RE_ACTIVAR_ARBITRO);
		Alerta alerta = new Alerta();
		if(listaAlerta != null && listaAlerta.size() > 0) {
			alerta = listaAlerta.get(0);
		}

		// indica si fue nombrado por la ccb
		boolean nombradoCCB = rolPersonaCaso.getMetodoNombramiento() == null ? false : rolPersonaCaso.getMetodoNombramiento().equals(UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
		// Consulta del jefe de arbitraje para enviar notificación
		RolPersona rolPersonaJefe;
		// si es nombrado porla CCB se consulta el arbitro en rolPersonaCaso de lo
		// contrario este valor será nulo
		rolPersonaJefe = nombradoCCB
				? (manejadorRolPersona.obtenerRolPersona(rolPersonaCaso.getRolPersonaCasoPK().getIdRol(),
						rolPersonaCaso.getRolPersonaCasoPK().getIdPersona())).get(0)
				: null;
		// 2. Cambia el estado del árbitro, obtiene el tipo de evento, las
		// observaciones del evento y guarda la información
		String tipoEvento = null;
		String observaciones = rolPersonaCaso.getPersona().getNombreCompleto() + UtilConstantes.CARACTER_COMA
				+ UtilConstantes.CARACTER_ESPACIO;

		// Arbitro no se pronuncia
		if (UtilDominios.TIPO_PRONUNCIAMIENTO_NO_SE_PRONUNCIA.equals(pronunciamiento.getPronunciamiento())) {
			rolPersonaCaso.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
			tipoEvento = UtilDominios.TIPO_PRONUNCIAMIENTO_NO_SE_PRONUNCIA;
			observaciones += MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO560.toString());

			// RQ12 15/01/2019

			if (nombradoCCB && manejadorParametroServicioSorteo.suspendeNoPronunciamiento(idCaso)) {
				// vigencia inactivacion 6 meses a partir de la fecha
				Date fechaEjecucion = UtilOperaciones.adicionarMesesFecha(new Date(), 6);
				// Creación de alerta
				ProgramacionAlerta programacionAlerta = new ProgramacionAlerta();
				programacionAlerta.setEstado(UtilDominios.PROGRAMACION_ALERTA_PENDIENTE);
				programacionAlerta.setIdCaso(rolPersonaCaso.getRolPersonaCasoPK().getIdCaso());
				programacionAlerta.setIdPersona(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona());
				programacionAlerta.setFechaEjecucion(fechaEjecucion);
				programacionAlerta.setIdAlerta(alerta.getIdAlerta());
				programacionAlerta.setAlerta(alerta);
				manejadorProgramacionAlerta.crear(programacionAlerta);
				// Si el arbitro no se pronuncia se inactiva por no contestación.
				personaFacade.inactivacionSegunMotivo(idPersona, UtilDominios.MOTIVO_NO_CONTESTACION,
						rolPersonaCaso.getRolPersonaCasoPK().getIdRol(), caso.getIdServicio());
				// Se notifica al jefe de arbitraje
				enviarNotificacionJefeArbitraje(rolPersonaCaso.getPersona().getNombreCompleto(), tipoEvento,
						rolPersonaJefe.getIdCentro(), rolPersonaCaso.getRolPersonaCasoPK().getIdCaso(),
						UtilDominios.MOTIVO_NO_CONTESTACION);
			}

			// se corrige incidencia, no se habilitaba el árbitro cuando no se pronuncia
			rolPersonaCasoFacade.habilitarArbitro(idCaso, idPersona, observaciones);
			rolPersonaCasoFacade.nombrarSuplentePrincipal(new CambioEstadoSuplenteDTO(idCaso, idPersona, idUsuario));
		} else if (UtilDominios.TIPO_PRONUNCIAMIENTO_ACEPTA.equals(pronunciamiento.getPronunciamiento())) {
			
			
			
			if(caso != null && caso.getIdServicio() == UtilConstantes.ID_SERVICIO_ARBITRAJE) {
				personaFacade.asignarRolArbitroSocial(idPersona, UtilDominios.ESTADO_ARBITROS_HABILITADO,
						rolPersonaCaso.getRolPersonaCasoPK().getIdRol(), true);
			}			
			rolPersonaCaso.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
			tipoEvento = UtilDominios.TIPO_EVENTO_ACEPTACION_DESIGNACION_ARBITRO;
			observaciones += MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO050.toString());
		} else if (UtilDominios.TIPO_PRONUNCIAMIENTO_DECLINA.equals(pronunciamiento.getPronunciamiento())) {
			rolPersonaCaso.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
			tipoEvento = UtilDominios.TIPO_EVENTO_DECLINACION_DESIGNACION_ARBITRO;
			observaciones += MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO051.toString());

			// se corrige incidencia, no se habilitaba el árbitro cuando declina sin informar la razón
				rolPersonaCasoFacade.habilitarArbitro(idCaso, idPersona, observaciones);
				rolPersonaCasoFacade
						.nombrarSuplentePrincipal(new CambioEstadoSuplenteDTO(idCaso, idPersona, idUsuario));
			// RQ12 15/01/2019
			// Si el arbitro declina y no informa la razón
			if (UtilDominios.MOTIVO_NO_INFORMA_RAZON.equals(pronunciamiento.getMotivoDeclinacion()) && !extemporaneo && nombradoCCB && manejadorParametroServicioSorteo.suspendeRechazo(idCaso)) {
				// Para Rol se actualiza un registro
				// se inactiva por no aceptacion
				
				Date fechaEjecucion = UtilOperaciones.adicionarMesesFecha(new Date(), 6);
				ProgramacionAlerta programacionAlerta = new ProgramacionAlerta();
				programacionAlerta.setEstado(UtilDominios.PROGRAMACION_ALERTA_PENDIENTE);
				programacionAlerta.setIdCaso(rolPersonaCaso.getRolPersonaCasoPK().getIdCaso());
				programacionAlerta.setIdPersona(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona());
				programacionAlerta.setFechaEjecucion(fechaEjecucion);
				programacionAlerta.setIdAlerta(alerta.getIdAlerta());
				manejadorProgramacionAlerta.crear(programacionAlerta);
				personaFacade.inactivacionSegunMotivo(idPersona, UtilDominios.MOTIVO_NO_ACEPTACION,
						rolPersonaCaso.getRolPersonaCasoPK().getIdRol(), caso.getIdServicio());

				// se notifica al jefe de arbitraje
				enviarNotificacionJefeArbitraje(rolPersonaCaso.getPersona().getNombreCompleto(), tipoEvento,
						rolPersonaJefe.getIdCentro(), rolPersonaCaso.getRolPersonaCasoPK().getIdCaso(),
						UtilDominios.MOTIVO_NO_ACEPTACION);								
			} 
		}

		rolPersonaCaso.setPronunciamiento(pronunciamiento);
		rolPersonaCaso.setIdPronunciamiento(pronunciamiento.getIdPronunciamiento());
		rolPersonaCaso.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		rolPersonaCaso.setFechaUltimaModificacion(new Date());
		manejadorRolPersonaCaso.actualizar(rolPersonaCaso);

		// 3. Registra el evento del cambio de estado del operador
		registrarEventoRolPersonaCaso(rolPersonaCaso, idUsuario, null, null, pronunciamiento.getFecha());

		// 4. Actualiza la ruta del caso (Evento)
		eventoFacade.registrarEvento(rolPersonaCaso.getCaso(), tipoEvento, observaciones, idUsuario, new Date(),
				UtilDominios.ESTADO_REGISTRO_ACTIVO);


		Long idSesion;
		// 6. Genera notificación al abogado del arbitraje asignado al caso informando
		// el pronunciamiento del operador.
		RolPersonaCaso abogadoCaso = manejadorRolPersonaCaso.consultarAbogadoDelCaso(idCaso);
		try {
			idSesion = Long.parseLong(appContext.getContextoSesion().getIdUsuario());
		} catch (Exception e) {
			idSesion = -1L;
		}

		if (abogadoCaso != null && !idSesion.equals(abogadoCaso.getRolPersonaCasoPK().getIdPersona())) {
			enviarNotificacionPronunciamientoArbitro(abogadoCaso, idCaso,
					rolPersonaCaso.getPersona().getNombreCompleto(), tipoEvento);
		}
		
		if(UtilDominios.TIPO_PRONUNCIAMIENTO_ACEPTA.equals(pronunciamiento.getPronunciamiento()) && rolPersonaCaso.getMetodoNombramiento() != null ) {
			arbitroCasoLiberacionFacade.habilitaArbitroSorteo(idPersona, idCaso, idUsuario);
		}
		
		return pronunciamiento.getIdPronunciamiento() == null ? -1L : pronunciamiento.getIdPronunciamiento();
	}

	/**
	 * Método encargado de enviar al abogado del caso una notificación de
	 * pronunciamiento del arbitro asignado a dicho caso
	 * 
	 * @param rolPersonaCaso
	 * @param idCaso
	 * @param nombreArbitroDesignadoCaso
	 */
	private void enviarNotificacionJefeArbitraje(String nombreArbitro, String tipoEvento, Long idCentro, Long idCaso,
			String tipoInactivacion) {

		// String rolJefe =
		// List<RolPersona> jefes =
		// manejadorRolPersona.consultarRolPersonasPorRoles(roles);
		ParametrosEnvioCorreoDTO parametrosEnvioCorreoDTO = new ParametrosEnvioCorreoDTO();
		CorreoElectronicoDTO correoElectronicoDTO = new CorreoElectronicoDTO();
		List<String> nomTemp = (new ArrayList<String>());
		nomTemp.add(UtilDominios.ROL_PERSONA_JEFE_DE_ARBITRAJE);
		List<Long> idTemp = (new ArrayList<Long>());
		idTemp.add(idCentro);
		List<Persona> listaJefes = manejadorRolPersona.consultarPesonasPorRolPersonaCentro(nomTemp, idTemp, null);
		for (Persona persona : listaJefes) {
			List<CorreoElectronico> correosElectronicos = persona.getCorreoElectronicoList();

			for (CorreoElectronico correoElectronico : correosElectronicos) {
				if (UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL.equals(correoElectronico.getTipo())) {
					correoElectronicoDTO = correoElectronicoDTO.transformarSinDependencias(correoElectronico);
				}
			}

			parametrosEnvioCorreoDTO.setRemitente(correoElectronicoDTO);

			List<CorreoElectronicoDTO> correoElectronicoDTOs = new ArrayList<CorreoElectronicoDTO>();
			correoElectronicoDTOs.add(correoElectronicoDTO);

			parametrosEnvioCorreoDTO.setRolPersonaCaso(correoElectronicoDTOs);
			parametrosEnvioCorreoDTO.setIdCaso(idCaso);
			parametrosEnvioCorreoDTO.setAsunto((String) manejadorParametrosGenerales.obtenerValorParametroPorCodigoTipo(
					UtilParamGenerales.ASUNTO_CORREO, UtilParamGenerales.TIPO_PRONUNCIAMIENTO,
					UtilConstantes.RETORNO_PARAMETROS_VALOR_TEXTO));
			parametrosEnvioCorreoDTO.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);
			parametrosEnvioCorreoDTO.setAdjuntos(new ArrayList<DocumentoDTO>());

			Caso caso = manejadorCaso.buscar(idCaso);

			List<String> parametrosCuerpoCorreo = new ArrayList<String>();
			parametrosCuerpoCorreo.add(nombreArbitro);
			parametrosCuerpoCorreo.add(String.valueOf(idCaso));
			parametrosCuerpoCorreo.add(caso.getNombre());
			Dominio motivo = manejadorDominio
					.buscar(new DominioPK(tipoInactivacion, UtilDominios.DOMINIO_MOTIVO_ESTADO));
			parametrosCuerpoCorreo.add(motivo.getDescripcion());
			String cuerpoCorreo = String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO080.toString()),
					parametrosCuerpoCorreo.toArray());
			
			parametrosEnvioCorreoDTO.setCuerpoCorreo(Collections.singletonList(cuerpoCorreo));

			correoRolPersonaCasoFacade.enviarCorreo(parametrosEnvioCorreoDTO);
		}

	}

	/**
	 * Método encargado de enviar al abogado del caso una notificación de
	 * pronunciamiento del arbitro asignado a dicho caso
	 * 
	 * @param rolPersonaCaso
	 * @param idCaso
	 * @param nombreArbitroDesignadoCaso
	 */
	private void enviarNotificacionPronunciamientoArbitro(RolPersonaCaso rolPersonaCaso, Long idCaso,
			String nombreArbitroDesignadoCaso, String tipoEvento) {
		ParametrosEnvioCorreoDTO parametrosEnvioCorreoDTO = new ParametrosEnvioCorreoDTO();
		CorreoElectronicoDTO correoElectronicoDTO = new CorreoElectronicoDTO();
		List<CorreoElectronico> correosElectronicos = rolPersonaCaso.getPersona().getCorreoElectronicoList();

		for (CorreoElectronico correoElectronico : correosElectronicos) {
			if (UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL.equals(correoElectronico.getTipo())) {
				correoElectronicoDTO = correoElectronicoDTO.transformarSinDependencias(correoElectronico);
			}
		}

		parametrosEnvioCorreoDTO.setRemitente(correoElectronicoDTO);

		List<CorreoElectronicoDTO> correoElectronicoDTOs = new ArrayList<CorreoElectronicoDTO>();
		correoElectronicoDTOs.add(correoElectronicoDTO);

		parametrosEnvioCorreoDTO.setRolPersonaCaso(correoElectronicoDTOs);
		parametrosEnvioCorreoDTO.setIdCaso(idCaso);
		parametrosEnvioCorreoDTO.setAsunto((String) manejadorParametrosGenerales.obtenerValorParametroPorCodigoTipo(
				UtilParamGenerales.ASUNTO_CORREO, UtilParamGenerales.TIPO_PRONUNCIAMIENTO,
				UtilConstantes.RETORNO_PARAMETROS_VALOR_TEXTO));
		parametrosEnvioCorreoDTO.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);
		parametrosEnvioCorreoDTO.setAdjuntos(new ArrayList<DocumentoDTO>());

		Caso caso = manejadorCaso.buscar(idCaso);

		List<String> parametrosCuerpoCorreo = new ArrayList<String>();
		parametrosCuerpoCorreo.add(nombreArbitroDesignadoCaso);
		parametrosCuerpoCorreo.add(String.valueOf(idCaso));
		parametrosCuerpoCorreo.add(caso.getNombre());
		parametrosCuerpoCorreo
				.add(UtilDominios.TIPO_EVENTO_ACEPTACION_DESIGNACION_ARBITRO.equals(tipoEvento) ? "aceptó" : "rechazó");
		String cuerpoCorreo = String.format(
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO090.toString()),
				parametrosCuerpoCorreo.toArray());

		parametrosEnvioCorreoDTO.setCuerpoCorreo(Collections.singletonList(cuerpoCorreo));

		correoRolPersonaCasoFacade.enviarCorreo(parametrosEnvioCorreoDTO);
	}

	/*
	 * Método encargado de registrar el evento del cambio de estado de un arbitro
	 * 
	 * @param rolPersonaCaso
	 */
	private void registrarEventoRolPersonaCaso(RolPersonaCaso rolPersonaCaso, String usuario, String estadoAsignado,
			String motivoInactivacion, Date fechaDesignacion) {
		EventoRolPersonaCaso eventoRolPersonaCaso = new EventoRolPersonaCaso();
		eventoRolPersonaCaso.setEstadoAsignado(estadoAsignado);
		eventoRolPersonaCaso.setMotivoInactivacion(motivoInactivacion);
		eventoRolPersonaCaso.setRolPersonaCaso(rolPersonaCaso);
		eventoRolPersonaCaso.setIdCaso(rolPersonaCaso.getCaso().getIdCaso());
		eventoRolPersonaCaso.setIdPersona(rolPersonaCaso.getPersona().getIdPersona());
		eventoRolPersonaCaso.setIdRol(rolPersonaCaso.getRol().getIdRol());
		eventoRolPersonaCaso.setEstadoAsignado(rolPersonaCaso.getEstado());
		eventoRolPersonaCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		eventoRolPersonaCaso.setIdUsuarioModificacion(usuario);
		eventoRolPersonaCaso.setFechaUltimaModificacion(new Date());
		if (fechaDesignacion != null) {
			eventoRolPersonaCaso.setFechaDeAsignacion(fechaDesignacion);
		} else {
			eventoRolPersonaCaso.setFechaDeAsignacion(new Date());
		}

		boolean bandera = false;
		if (estadoAsignado != null && (estadoAsignado.equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR)
				|| UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO.equals(estadoAsignado))) {
			List<EventoRolPersonaCaso> eventos = manejadorEventoRolPersonaCaso.consultarEvento(
					eventoRolPersonaCaso.getIdRol(), eventoRolPersonaCaso.getIdPersona(),
					eventoRolPersonaCaso.getIdPersona(), estadoAsignado);
			for (EventoRolPersonaCaso evento : eventos) {
				String fechaAnterior = UtilOperaciones.formatearFechaReporte(evento.getFechaDeAsignacion());
				String fechaNueva = UtilOperaciones.formatearFechaReporte(eventoRolPersonaCaso.getFechaDeAsignacion());
				if (fechaAnterior.equals(fechaNueva)) {
					bandera = true;
					break;
				}
			}
		}
		if (bandera) {
			return;
		} else {
			manejadorEventoRolPersonaCaso.crearEventoRolPersonaCaso(eventoRolPersonaCaso);
		}
	}

	/**
	 * ARBF072 ARBF052 Método encargado de validar que los datos de un
	 * pronunciamiento sean coherentes.
	 * 
	 * @param pronunciamiento
	 * @return
	 */
	private void validarPronunciamiento(Pronunciamiento pronunciamiento) throws SIMASCNegocioExcepcion {
		
		if (UtilDominios.TIPO_PRONUNCIAMIENTO_DECLINA.equals(pronunciamiento.getPronunciamiento())
				&& (pronunciamiento.getMotivoDeclinacion() == null
						|| pronunciamiento.getMotivoDeclinacion().isEmpty())) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR051.toString()));
		}
	}

	/**
	 * ARBF072 Valida la consistencia de fechas y de definición del pronunciamiento
	 * que se está almacenando
	 * 
	 * @param infoPronunciamiento
	 */
	private void validarRegistroPronunciamiento(InfoRegistroPronunciamientoDTO infoPronunciamiento) {

		if (infoPronunciamiento.getPronunciamiento() == null) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR214.toString()));
		}
		if (infoPronunciamiento.getFechaDesignacion() == null) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR211.toString()));
		}
		if (infoPronunciamiento.getFechaComunicacion() == null) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR212.toString()));
		}
		if (infoPronunciamiento.getPronunciamiento().getFecha() == null) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR213.toString()));
		}
		if (UtilOperaciones.obtenerFechaComienzoDelDia(infoPronunciamiento.getFechaComunicacion())
				.before(UtilOperaciones.obtenerFechaComienzoDelDia(infoPronunciamiento.getFechaDesignacion()))) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR209.toString()));
		}
		if (UtilOperaciones.obtenerFechaComienzoDelDia(infoPronunciamiento.getPronunciamiento().getFecha())
				.before(UtilOperaciones.obtenerFechaComienzoDelDia(infoPronunciamiento.getFechaComunicacion()))) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR210.toString()));
		}

	}

	private String consultarNombreAbogadoDelCaso(Long idCaso) {

		RolPersonaCaso abogado = manejadorRolPersonaCaso.consultarAbogadoDelCaso(idCaso);

		return abogado == null ? null : abogado.getPersona().getNombreCompleto();

	}

	private String consultarNombreSecretarioDelCaso(Long idCaso) {

		RolPersonaCaso secrerario = manejadorRolPersonaCaso.consultarSecretarioDelCaso(idCaso);

		return secrerario == null ? null : secrerario.getPersona().getNombreCompleto();

	}

	/**
	 * Devuelve los nombres de todos los arbitros asignados al caso a excepción del
	 * arbitro con el id de persona que se pasa como parámetro
	 * 
	 * @param idCaso           Id del caso a consultar los arbitros asignados
	 * @param idPersonaArbitro Id de la entidad persona correspondiente a un arbitro
	 *                         asignado al caso
	 * @return List<String>
	 */
	private List<String> consultarNombresOperadores(Long idCaso, Long idPersonaArbitro) {

		List<String> operadores = new ArrayList<>();

		for (RolPersonaCaso rpc : manejadorRolPersonaCaso.consultarArbitros(idCaso,
				UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL, null,
				Arrays.asList(UtilConstantes.ESTADOS_ARBITROS_PARA_INFORMAR))) {
			if (!rpc.getPersona().getIdPersona().equals(idPersonaArbitro)) {
				operadores.add(rpc.getPersona().getNombreCompleto());
			}
		}

		return operadores;
	}

	private List<ParteCasoDTO> consultarDemandadosCaso(Long idCaso) {

		List<ParteCasoDTO> demandados = new ArrayList<>();
		for (RolPersonaCaso rpc : manejadorRolPersonaCaso.consultarDemandadosCaso(idCaso)) {
			demandados.add(convertirRolPersonaCasoAParteDTO(rpc));
		}

		return demandados;

	}

	private List<ParteCasoDTO> consultarDemandantesCaso(Long idCaso) {

		List<ParteCasoDTO> demandantes = new ArrayList<>();
		for (RolPersonaCaso rpc : manejadorRolPersonaCaso.consultarDemandantesCaso(idCaso)) {
			demandantes.add(convertirRolPersonaCasoAParteDTO(rpc));
		}

		return demandantes;

	}

	private ParteCasoDTO convertirRolPersonaCasoAParteDTO(RolPersonaCaso rpc) {
		ParteCasoDTO parteCaso = new ParteCasoDTO();
		if (rpc != null) {
			parteCaso.setNombre(rpc.getPersona().getNombreCompleto());
			parteCaso.setTipoIdentificacion(rpc.getPersona().getTipoDocumento());
			parteCaso.setNumeroIdentificacion(rpc.getPersona().getNumeroDocumento());
			parteCaso
					.setRol(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA, rpc.getRol().getNombre()));
		}

		return parteCaso;
	}

	@Override
	public List<String> reversarPronunciamiento(CambioEstadoSuplenteDTO cambioEstadoSuplenteDTO)
			throws SIMASCNegocioExcepcion {

		List<String> mensajes = new ArrayList<>();
		try {			
			/* Se obtiene el arbitro principal que rechazo */
			StringBuilder observaciones = new StringBuilder();
			RolPersonaCaso rpcArbitroPrincipalRechazo = manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(
					cambioEstadoSuplenteDTO.getIdPersona(), cambioEstadoSuplenteDTO.getIdCaso());
			if (rpcArbitroPrincipalRechazo == null) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR218.toString()));
			}
			Sorteo sorteo = sorteoFacade.buscar(rpcArbitroPrincipalRechazo.getIdSorteo());
			Boolean cambiarEstadoArbitros = false;
			if (UtilDominios.NOMBRAMIENTO_POR_LA_CCB.equals(rpcArbitroPrincipalRechazo.getMetodoNombramiento())
				&& (!sorteo.isPreseleccion() || (sorteo.isPreseleccion() && sorteo.getQuienPreselecciona().equals(UtilDominios.PRESELECCIONADO_POR_LA_CAMARA_DE_COMERCIO_BOGOTA))))
			{
				cambiarEstadoArbitros = true;
			}	

			/* Se obtiene el arbitro principal que fue suplente */
			RolPersonaCaso rpcArbitroPrincipalActual = manejadorRolPersonaCaso.consultarPersonaReemplazoCaso(
					cambioEstadoSuplenteDTO.getIdPersona(), cambioEstadoSuplenteDTO.getIdCaso(),
					UtilDominios.ROL_PERSONA_ARBITRO);

			List<String> parametrosMensaje = new ArrayList<String>();

			if (rpcArbitroPrincipalActual != null) {
				/* Eventos del arbitro principal que fue suplente */
				for (EventoRolPersonaCaso it1 : rpcArbitroPrincipalActual.getEventoRolPersonaCasoList()) {
					if (it1.getEstadoAsignado().equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR)) {
						it1.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
						manejadorEventoRolPersonaCaso.actualizarSinFlush(it1);
					}
				}
				parametrosMensaje.add(rpcArbitroPrincipalActual.getPersona().getNombreCompleto().trim());
				if(Boolean.TRUE.equals(cambiarEstadoArbitros)){
					/* Se habilita para sorteo el arbitro principal que fue suplente */
				
					observaciones.append(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_MOTIVOS_INACTIVACION_ARBITRO, UtilDominios.MOTIVO_REVERSAR_DESIGNACION_SUPLENTE));
					observaciones.append(" caso ");
					observaciones.append(rpcArbitroPrincipalRechazo.getCaso().getIdCaso());
					observaciones.append(" ");
					observaciones.append(rpcArbitroPrincipalRechazo.getCaso().getNombre());

					rolPersonaCasoFacade.habilitarArbitro(rpcArbitroPrincipalActual.getCaso().getIdCaso(),
							rpcArbitroPrincipalActual.getRolPersonaCasoPK().getIdPersona(),
							observaciones.toString());

				}
				
				/* Se setean los valores de arbitro principal para volver suplente */
				rpcArbitroPrincipalActual.setTipoNombramiento(UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE);
				rpcArbitroPrincipalActual.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO);
				rpcArbitroPrincipalActual.setIdUsuarioModificacion(
						cambioEstadoSuplenteDTO.getIdUsuario() != null ? cambioEstadoSuplenteDTO.getIdUsuario()
								: UtilConstantes.USUARIO_DEFECTO_SIMASC);
				rpcArbitroPrincipalActual.setFechaUltimaModificacion(new Date());
				rpcArbitroPrincipalActual.setIdCasoPrincipalReemplazado(null);
				rpcArbitroPrincipalActual.setIdPersonaPrincipalReemplazado(null);
				rpcArbitroPrincipalActual.setIdRolPrincipalReemplazado(null);

				manejadorRolPersonaCaso.actualizar(rpcArbitroPrincipalActual);
			}

			if (rpcArbitroPrincipalRechazo != null) {

				/* Evento del arbitro principal que rechazo */
				for (EventoRolPersonaCaso it2 : rpcArbitroPrincipalRechazo.getEventoRolPersonaCasoList()) {
					if (it2.getEstadoAsignado().equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO)) {
						it2.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
						it2.setFechaUltimaModificacion(new Date());
						manejadorEventoRolPersonaCaso.actualizarSinFlush(it2);
					}
					if (it2.getEstadoAsignado().equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR)) {
						it2.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
						it2.setFechaUltimaModificacion(new Date());
						manejadorEventoRolPersonaCaso.actualizarSinFlush(it2);
					}
				}

				if (rpcArbitroPrincipalActual != null) {
					parametrosMensaje.add(rpcArbitroPrincipalRechazo.getPersona().getNombreCompleto().trim());
					String observacionesReversionPrincipalActual = String.format(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO053.toString()),
							parametrosMensaje.toArray());
					// String observaciones =
					// MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.info053.toString());
					/* Actualiza la ruta del caso (Evento) */
					eventoFacade.registrarEvento(rpcArbitroPrincipalActual.getCaso(),
							UtilDominios.MOTIVO_REVERSAR_DESIGNACION_SUPLENTE, observacionesReversionPrincipalActual,
							UtilConstantes.USUARIO_DEFECTO_SIMASC, new Date(), UtilDominios.ESTADO_REGISTRO_ACTIVO);
					mensajes.add(observacionesReversionPrincipalActual);
				}

				String observacionesPrincipalRechazo = String.format(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO054.toString()),
						rpcArbitroPrincipalRechazo.getPersona().getNombreCompleto().trim());
				eventoFacade.registrarEvento(rpcArbitroPrincipalRechazo.getCaso(),
						UtilDominios.TIPO_EVENTO_REVERSAR_PRONUNCIAMIENTO, observacionesPrincipalRechazo,
						UtilConstantes.USUARIO_DEFECTO_SIMASC, new Date(), UtilDominios.ESTADO_REGISTRO_ACTIVO);
				mensajes.add(observacionesPrincipalRechazo);

				if (rpcArbitroPrincipalRechazo.getIdPronunciamiento() != null) {
					Pronunciamiento pronunciamiento = rpcArbitroPrincipalRechazo.getPronunciamiento();
					pronunciamiento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
					if (pronunciamiento.getIdDocumento() != null) {
						almacenamientoFacade.eliminarDocumento(pronunciamiento.getIdDocumento(),
								cambioEstadoSuplenteDTO.getIdUsuario());
					}
				}

				/*
				 * Se setean los valores de arbitro principal que rechazo para volver a
				 * principal
				 */
				rpcArbitroPrincipalRechazo.setIdPronunciamiento(null);
				rpcArbitroPrincipalRechazo.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
				rpcArbitroPrincipalRechazo.setMotivoInactivacion(null);
				rpcArbitroPrincipalRechazo.setFechaUltimaModificacion(new Date());
				rpcArbitroPrincipalRechazo.setIdUsuarioModificacion(
						cambioEstadoSuplenteDTO.getIdUsuario() != null ? cambioEstadoSuplenteDTO.getIdUsuario()
								: UtilConstantes.USUARIO_DEFECTO_SIMASC);
				rpcArbitroPrincipalRechazo.setTipoNombramiento(UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
				manejadorRolPersonaCaso.actualizarSinFlush(rpcArbitroPrincipalRechazo);

				if (Boolean.TRUE.equals(cambiarEstadoArbitros))
				{
					/* Se cambia el estado del arbitro principal que rechazo */
					observaciones = new StringBuilder();
					observaciones.append(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_MOTIVOS_INACTIVACION_ARBITRO, UtilDominios.MOTIVO_REVERSAR_PRONUNCIAMIENTO));
					observaciones.append(" caso ");
					observaciones.append(rpcArbitroPrincipalRechazo.getCaso().getIdCaso());
					observaciones.append(" ");
					observaciones.append(rpcArbitroPrincipalRechazo.getCaso().getNombre());
					
					rolPersonaCasoFacade.cambiarEstadoArbitroSorteo(rpcArbitroPrincipalRechazo,
							rpcArbitroPrincipalRechazo.getCaso(), observaciones.toString(),
							UtilDominios.ESTADO_PERSONA_SORTEO_INACTIVO);
				}

			}
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		}

		return mensajes;
	}

	@Override
	public void pronunciamientoConciliador(List<PendientePronunciamientoDTO> pronunciamientos, String usuario)
			throws EstadosCasoException {
		
		String estado, observaciones, mensajeCorreo;
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if (usuario != null) {
			usuarioModificacion = usuario;
		}

		for (PendientePronunciamientoDTO pronunciaFor : pronunciamientos) {
			Persona personaActual = manejadorPersona.buscar(pronunciaFor.getIdPersona());
			mensajeCorreo = "";			
			if (personaActual == null) {
				throw new SimascException(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR531.toString()));
			}
			
			
			RolPersonaCaso rolPersonaCaso = manejadorRolPersonaCaso.consultaRolPersonaId(pronunciaFor.getIdPersona(),
					pronunciaFor.getIdCaso(), null);
			
			
			
			if (rolPersonaCaso.getEstado().equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR)) {
		
				List<String> args = new ArrayList<>();
				args.add(personaActual.getNombreCompleto());
				if (UtilDominios.TIPO_PRONUNCIAMIENTO_ACEPTA.equals(pronunciaFor.getPronunciamiento())) {
					estado = UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO;
					observaciones = String.format(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO270.toString()),
							args.toArray());
					AudienciaDTO audiencia = manejadorAudiencia.consultarUltimaAudienciaEstadoCaso(
							pronunciaFor.getIdCaso(), UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
					if (audiencia != null)
						manejadorProgramacionAlerta.crearProgramacionAlerta(pronunciaFor.getIdCaso(),
								pronunciaFor.getIdPersona(), UtilDominios.CODIGO_ALERTA_PROGRAMACION_AUDIENCIA, null);
				} else {
					estado = UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO;					
					if (pronunciaFor.getMotivoDeclinacion() != null) {
						
						if(pronunciaFor.getIdServicio()!= null && UtilConstantes.ID_SERVICIO_EQUIDAD.equals(pronunciaFor.getIdServicio())){
							args.add(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_MOTIVO_RECHAZO_CONCILIADOR_EQ,
								pronunciaFor.getMotivoDeclinacion()));
							mensajeCorreo = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_MOTIVO_RECHAZO_CONCILIADOR_EQ,
									pronunciaFor.getMotivoDeclinacion());
						}else {
							args.add(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_MOTIVO_RECHAZO_CONCILIADOR,
									pronunciaFor.getMotivoDeclinacion()));
						}
						
						observaciones = String.format(
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO271.toString()),
								args.toArray());
						
						
					} else {
						observaciones = String.format(
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO272.toString()),
								args.toArray());

					}
					
					
					if(pronunciaFor.getObservaciones()!=null) {
						//Se agrega a la observaciones en la ruta del caso el texto ingresado de rechazo
						observaciones =   observaciones+ ". " +pronunciaFor.getObservaciones();
						mensajeCorreo = mensajeCorreo + ". " +pronunciaFor.getObservaciones();
					}
					
				}
				Pronunciamiento pronunciamiento = this.crearPronunciamiento(pronunciaFor);
				rolPersonaCasoFacade.modificarEstadoRolPersonaCaso(pronunciaFor.getIdRol(), pronunciaFor.getIdPersona(),
						pronunciaFor.getIdCaso(), estado, usuarioModificacion, null,
						pronunciamiento.getIdPronunciamiento());
				// modifica el estado del caso y genera el evento
				if (UtilDominios.TIPO_PRONUNCIAMIENTO_ACEPTA.equals(pronunciaFor.getPronunciamiento())) {
					casoFacade.cambiarEstadoCaso(pronunciaFor.getIdCaso(), UtilDominios.ESTADO_CASO_EN_CONCILIACION,
							new Date(), UtilDominios.TIPO_EVENTO_PRONUNCIAMIENTO_CONCILIADOR, observaciones);
				} else {
					this.finalizarRechazoPronunciamiento(pronunciaFor, observaciones, mensajeCorreo);
				}
				casoFacade.cambiaEstadoPronunciamientoCasoInsolvencia(pronunciaFor.getPronunciamiento(), pronunciaFor.getIdCaso(), pronunciaFor.getIdPersona());
			}
		}
	}

	/**
	 * metodo encargado realizar la logica del rechazo de un caso por parte del
	 * conciliador
	 * 
	 * @param pronunciamiento
	 * @param observaciones
	 * @throws EstadosCasoException
	 */
	private void finalizarRechazoPronunciamiento(PendientePronunciamientoDTO pronunciamiento, String observaciones, String mensajeCorreo)
			throws EstadosCasoException {
		Audiencia audiencia = this.consultaAudienciaPrincipal(pronunciamiento.getIdCaso());
		// libera agenda del conciliador
		if (audiencia != null) {
			agendaPersonaFacade.liberarAgendamientoPersona(pronunciamiento.getIdPersona(), audiencia.getIdAudiencia());
		}

		if(pronunciamiento.getIdServicio()!= null && UtilConstantes.ID_SERVICIO_EQUIDAD.equals(pronunciamiento.getIdServicio())){
			//reCHAZO eQUIDAD
			
			if (!UtilDominios.MOTIVO_RECHAZO_CONCILIADOR_EQU_CONFLICTO.equals(pronunciamiento.getMotivoDeclinacion())) {
				//para todos los motivos, excepto conflicto de interés, el caso queda cerrado por falta de competencia
				casoFacade.cerrarCasoConciliacionNoCompetencia(pronunciamiento.getIdCaso(), observaciones, true, mensajeCorreo);
			} else {
				//asignar un nuevo conciliador 
				//cambiar estado y llamar reparto equidad
				
				casoFacade.cambiarEstadoCaso(pronunciamiento.getIdCaso(),
						UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION, new Date(),
						UtilDominios.TIPO_EVENTO_PRONUNCIAMIENTO_CONCILIADOR, observaciones);
				
				
				DatosEntradaRepartoDTO datos = new DatosEntradaRepartoDTO();
				datos.setIdCaso(pronunciamiento.getIdCaso());			
				Rol rolConciliador = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_CONCILIADOR_EQUIDAD);
				datos.setIdRol(rolConciliador.getIdRol());
				datos.setUsuario(UtilConstantes.USUARIO_DEFECTO_SIMASC);				
				logger.info("llamarRepartoEquidad desde rechazo pronunciamiento");
				try {
					repartoSvc.repartoEquidad(datos);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			
			}
		} else {

			if (UtilDominios.MOTIVO_RECHAZO_CONCILIADOR_FALTA_COMPETENCIA.equals(pronunciamiento.getMotivoDeclinacion())) {
				casoFacade.cerrarCasoConciliacionNoCompetencia(pronunciamiento.getIdCaso(), observaciones, false, mensajeCorreo);
			} else {
				casoFacade.cambiarEstadoCaso(pronunciamiento.getIdCaso(),
						UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION, new Date(),
						UtilDominios.TIPO_EVENTO_PRONUNCIAMIENTO_CONCILIADOR, observaciones);
				Long idSuplenteNom = rolPersonaCasoFacade.nombrarSuplenteConciliacion(pronunciamiento, audiencia);
				if (!idSuplenteNom.equals(-1L)) {
					Persona conciliadorSuplente = manejadorPersona.buscar(idSuplenteNom);
					List<String> args = new ArrayList<>();
					args.add(conciliadorSuplente.getNombreCompleto());
					String obsSuplente = String.format(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO274.toString()),
							args.toArray());
					// modifica el estado del caso
					casoFacade.cambiarEstadoCaso(pronunciamiento.getIdCaso(),
							UtilDominios.ESTADO_CASO_CONCILIADOR_DESIGNADO, new Date(),
							UtilDominios.TIPO_EVENTO_DESIGNACION, obsSuplente);
					// se programan las alertas RECACE1 y RECACE , CSUPLEN
					manejadorProgramacionAlerta.crearProgramacionesAceptacionConciliacion(pronunciamiento.getIdCaso(),
							idSuplenteNom, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
	
				} else {
					String obsCambioEstado = String
							.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO278.toString()));
	
					casoFacade.cambiarEstadoCaso(pronunciamiento.getIdCaso(),
							UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION, new Date(),
							UtilDominios.TIPO_EVENTO_PENDIENTE_POR_DESIGNACION, obsCambioEstado);
					correoRolPersonaCasoFacade.envioCorreoCasoSinConciliador(pronunciamiento.getIdCaso());
				}
			}
		}
//		}else{
//			casoFacade.cambiarEstadoCaso(pronunciamiento.getIdCaso(), UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION, new Date(),
//					UtilDominios.TIPO_EVENTO_PRONUNCIAMIENTO_CONCILIADOR, observaciones);
//			correoRolPersonaCasoFacade.envioCorreoCasoSinConciliador(pronunciamiento.getIdCaso());
//		}

	}
	
	

	@Override
	public Pronunciamiento crearPronunciamiento(PendientePronunciamientoDTO pronunciamientos) {
		Pronunciamiento pronunciamiento = new Pronunciamiento();
		if (pronunciamientos.getFecha() != null) {
			pronunciamiento.setFecha(pronunciamientos.getFecha());
		} else {
			pronunciamiento.setFecha(new Date());
		}
		pronunciamiento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		pronunciamiento.setFechaUltimaModificacion(new Date());
		pronunciamiento.setMotivoDeclinacion(pronunciamientos.getMotivoDeclinacion());
		pronunciamiento.setObservaciones(pronunciamientos.getObservaciones());
		pronunciamiento.setPronunciamiento(pronunciamientos.getPronunciamiento());
		manejadorPronunciamiento.crear(pronunciamiento);

		return pronunciamiento;

	}

	private Audiencia consultaAudienciaPrincipal(Long idCaso) {
		Audiencia audiencia = null;
		List<Audiencia> audiencias = manejadorAudiencia.consultarAudienciasCasoPorTipoYEstado(idCaso, null,
				UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		if (!audiencias.isEmpty()) {
			audiencia = audiencias.get(0);
		}
		return audiencia;

	}

	@Override
	public void alertaNombramientoSuplente(Long idCaso, Long idPersona, Long idRol) throws EstadosCasoException {
		List<PendientePronunciamientoDTO> pronunciamientos = new ArrayList<PendientePronunciamientoDTO>();
		PendientePronunciamientoDTO pronunciamiento = new PendientePronunciamientoDTO();
		pronunciamiento.setIdPersona(idPersona);
		pronunciamiento.setIdCaso(idCaso);
		pronunciamiento.setIdRol(idRol);
		pronunciamiento.setPronunciamiento(UtilDominios.TIPO_PRONUNCIAMIENTO_NO_SE_PRONUNCIA);
		pronunciamiento.setObservaciones("Vencimiento tiempo de pronunciamiento");
		pronunciamientos.add(pronunciamiento);
		this.pronunciamientoConciliador(pronunciamientos, null);
	}

	
	@Override
	public void generarDocumentoPronunciamiento(Long idPersona, Long idCaso, Long idPronunciamiento) throws SIMASCNegocioExcepcion{
		
		
		try {
			InfoCasoParaPronunciamientoDTO infoCasoParaPronunciamientoDTO = consultarInfoCasoAsignadoArbitro(idCaso,
					idPersona);
			Pronunciamiento pronunciamiento = manejadorPronunciamiento.buscar(idPronunciamiento);
			Persona persona = manejadorPersona.buscar(idPersona);
			
			
			String nombre = UtilDominios.DOCUMENTO_PRONUNCIAMIENTO_OTRO;
			if(infoCasoParaPronunciamientoDTO.getIdServicio() == UtilConstantes.ID_SERVICIO_RECUPERACION_EMPRESARIAL) {
				nombre = UtilDominios.DOCUMENTO_PRONUNCIAMIENTO_RECUPERACION_EMPRESARIAL;
			}
			
			PlantillaCarta plantillaCarta = manejadorPlantillaCarta.consultaPlantillaPorNombre(nombre).get(0);
			documentoFacade.generarDocumentoPronunciamiento(infoCasoParaPronunciamientoDTO, pronunciamiento, idCaso,
					plantillaCarta, persona);

		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());

		}
		
	}
	
	// protected region metodos adicionales end

}
