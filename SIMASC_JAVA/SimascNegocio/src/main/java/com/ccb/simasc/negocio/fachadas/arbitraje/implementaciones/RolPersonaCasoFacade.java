package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

// protected region imports fachada on begin
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.comun.fachada.interfaces.IRealizacionSorteoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorAgendaPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorDisponibilidadPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorEstadoPersonaRol;
import com.ccb.simasc.integracion.manejadores.ManejadorEvento;
import com.ccb.simasc.integracion.manejadores.ManejadorEventoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoEstadoPersonaRol;
import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoPersonaServicioMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorNombramientoPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroServicioSorteo;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaServicioMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorPreseleccionado;
import com.ccb.simasc.integracion.manejadores.ManejadorProgramacionAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorPronunciamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorRelacionadoConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorSala;
import com.ccb.simasc.integracion.manejadores.ManejadorSorteo;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefono;
import com.ccb.simasc.integracion.manejadores.ManejadorUbicacion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionAsignacion;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendamientoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICentroFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEstadoPersonaTipoServicioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INombramientoPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INotificacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrizarServicioRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroServicioSorteoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPreseleccionadoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPronunciamientoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRecusacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISorteoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionFacade;
import com.ccb.simasc.negocio.fachadas.reparto.implementaciones.RepartoSvc;
import com.ccb.simasc.transversal.dto.AudienciaDTO;
import com.ccb.simasc.transversal.dto.CambioConciliadorDTO;
import com.ccb.simasc.transversal.dto.CambioEstadoSuplenteDTO;
import com.ccb.simasc.transversal.dto.CasoDTO;
import com.ccb.simasc.transversal.dto.CasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.DatosEntradaRepartoDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.ParametroDeServicioDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.PartesSeguimientoDTO;
import com.ccb.simasc.transversal.dto.PendientePronunciamientoDTO;
import com.ccb.simasc.transversal.dto.PersonaAleatoriaDTO;
import com.ccb.simasc.transversal.dto.PersonaCasoListDTO;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.RecusacionDTO;
import com.ccb.simasc.transversal.dto.RolPersonaCasoDTO;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.dto.cartas.LotesCartasDTO;
import com.ccb.simasc.transversal.dto.formularios.CambioEstadoRPC_DTO;
import com.ccb.simasc.transversal.dto.formularios.CasoAsignadoDTO;
import com.ccb.simasc.transversal.dto.formularios.DetalleArbitroDTO;
import com.ccb.simasc.transversal.dto.formularios.LotesCartasFiltrosDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.dto.formularios.RepartoDTO;
import com.ccb.simasc.transversal.dto.formularios.RolPersonaCasoDesignacionDTO;
import com.ccb.simasc.transversal.dto.formularios.VinculacionPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.Agendamiento;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRol;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRolPK;
import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.HistoricoPersonaServicioMateria;
import com.ccb.simasc.transversal.entidades.NombramientoPersona;
import com.ccb.simasc.transversal.entidades.ParametrizarServicioRol;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;
import com.ccb.simasc.transversal.entidades.Preseleccionado;
import com.ccb.simasc.transversal.entidades.PreseleccionadoPK;
import com.ccb.simasc.transversal.entidades.Pronunciamiento;
import com.ccb.simasc.transversal.entidades.Recusacion;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.Sorteo;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.entidades.Ubicacion;
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
 * Implementacion de fachada RESTFULL para {@link RolPersonaCaso}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class RolPersonaCasoFacade extends AccesoFacade<RolPersonaCaso, RolPersonaCasoPK, RolPersonaCasoDTO>
		implements IRolPersonaCasoFacade {
	
	private static final Logger logger = LogManager.getLogger(RolPersonaCasoFacade.class.getName());
	// protected region atributos on begin
	private static final String FILTROS_BUSQUEDA_VACIOS = "Filtros de busqueda vacios";

	// Escriba en esta sección sus modificaciones

	@EJB
	private ManejadorAudiencia manejadorAudiencia;

	@EJB
	private RolFacade rolFacade;

	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorDocumento manejadorDocumento;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorRolPersona manejadorRolPersona;

	@EJB
	private ManejadorEventoRolPersonaCaso manejadorEventoRolPersonaCaso;

	@EJB
	private ManejadorPersonaServicioMateria manejadorPersonaServicioMateria;

	@EJB
	private ManejadorHistoricoPersonaServicioMateria manejadorHistoricoPersonaServicioMateria;

	@EJB
	private ManejadorPronunciamiento manejadorPronunciamiento;

	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	@EJB
	private ManejadorSorteo manejadorSorteo;

	@EJB
	private ManejadorTelefono manejadorTelefono;

	@EJB
	private ManejadorCorreoElectronico manejadorCorreoElectronico;

	@EJB
	private ManejadorNombramientoPersona manejadorNombramientoPersona;

	@EJB
	private ManejadorUbicacion manejadorUbicacion;

	@EJB
	private ManejadorAgendaPersona manejadorAgendaPersona;

	@EJB
	private ManejadorParametroServicioSorteo manejadorParametroServicioSorteo;

	@EJB
	private ManejadorPreseleccionado manejadorPreseleccionado;

	@EJB
	private INombramientoPersonaFacade nombramientoPersonasFacade;

	@EJB
	private IEventoRolPersonaCasoFacade eventoRolPersonaCasoFacade;

	@EJB
	private IPersonaFacade personaFacade;

	@EJB
	private IEventoFacade eventoFacade;

	@EJB
	private ICentroFacade centroFacade;

	@EJB
	private IPronunciamientoFacade pronunciamientoFacade;

	@EJB
	private IRecusacionFacade recusacionFacade;

	@EJB
	private ICasoFacade casoFacade;

	@EJB
	private IDominioFacade dominioFacade;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;

	@EJB
	private IRealizacionSorteoFacade realizacionSorteoFacade;

	@EJB
	private ISorteoFacade sorteoFacade;

	@EJB
	private IPreseleccionadoFacade preseleccionadoFacade;

	@EJB
	private IUbicacionFacade ubicacionFacade;

	@EJB
	private IDiaFestivoFacade diaFestivoFacade;

	@EJB
	private ManejadorEvento manejadorEvento;

	@EJB
	private IEstadoPersonaTipoServicioFacade estadoPersonaTipoServicioFacade;

	@EJB
	private IParametrizarServicioRolFacade parametrizarServicioRolFacade;

	@EJB
	private IAgendamientoFacade agendamientoFacade;

	@EJB
	private IAgendaPersonaFacade agendaPersonaFacade;

	@EJB
	private RepartoSvc repartosvcFacade;

	@EJB
	private ManejadorSala manejadorSala;

	@EJB
	private ManejadorEstadoPersonaRol manejadorEstadoPersonaRol;

	@EJB
	private ManejadorHistoricoEstadoPersonaRol manejadorHistoricoEstadoPersonaRol;

	@EJB
	private ManejadorDisponibilidadPersona manejadorDisponibilidadPersona;

	@EJB
	private ManejadorRelacionadoConvenio manejadorRelacionadoConvenio;

	@EJB
	private ManejadorProgramacionAlerta manejadorProgramacionAlerta;

	@EJB
	private ParametroDeServicioFacade parametroDeServicioFacade;

	@EJB
	private INotificacionFacade notificacionFacade;

	@EJB
	private IParametroServicioSorteoFacade parametroServicioSorteoFacade;
	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorRolPersonaCaso;
	}

	@Override
	public RolPersonaCasoDTO transformarSinDependencias(RolPersonaCaso obj) {
		RolPersonaCasoDTO dto = new RolPersonaCasoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public RolPersonaCasoDTO transformarConDependencias(RolPersonaCaso obj) {
		RolPersonaCasoDTO dto = new RolPersonaCasoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public RolPersonaCaso transformarEntidadConDependencias(RolPersonaCaso obj) {
		return new RolPersonaCasoDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public RolPersonaCaso transformarEntidadSinDependencias(RolPersonaCaso obj) {
		return new RolPersonaCasoDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * 
	 */
	@Override
	public void eliminarRolPersonasCaso(List<Persona> persona, Long idCaso, String rolPersona)
			throws SIMASCNegocioExcepcion {
		try {
			for (Persona persona2 : persona) {
				RolPersonaCaso personaEliminar = new RolPersonaCaso();
				Caso caso = new Caso();
				Rol rol = new Rol();
				RolPersonaCasoPK rolPersonaCasoPk = new RolPersonaCasoPK();
				caso = manejadorCaso.buscar(idCaso);
				rol = manejadorRol.consultarRolPorNombre(rolPersona);
				personaEliminar.setPersona(persona2);
				personaEliminar.setCaso(caso);
				personaEliminar.setRol(rol);
				rolPersonaCasoPk.setIdCaso(caso.getIdCaso());
				rolPersonaCasoPk.setIdPersona(persona2.getIdPersona());
				rolPersonaCasoPk.setIdRol(rol.getIdRol());
				personaEliminar.setRolPersonaCasoPK(rolPersonaCasoPk);
				personaEliminar.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
				personaEliminar.setEstado(UtilDominios.ESTADO_PERSONA_INACTIVO);
				personaEliminar = manejadorRolPersonaCaso.crearRolPersonaCaso(personaEliminar);
				// EventoRolPersonaCaso eventoRolPersonaCaso = new
				// EventoRolPersonaCaso();
				// eventoRolPersonaCaso.setEstadoAsignado(estadoAsignado);
				// eventoRolPersonaCaso.setEstadoRegistro(estadoRegistro);
				// eventoRolPersonaCaso.setFechaDeAsignacion(fechaDeAsignacion);
				// eventoRolPersonaCaso.setFechaUltimaModificacion(fechaUltimaModificacion);
				// eventoRolPersonaCaso.setIdCaso(idCaso);
				// eventoRolPersonaCaso.setIdPersona(persona2.getIdPersona());
				// eventoRolPersonaCaso.setIdUsuarioModificacion("1");//Se
				// registra un 1 quemado
				// eventoRolPersonaCaso.setRolPersonaCaso(personaEliminar);

			}
		} catch (SIMASCNegocioExcepcion e) {
			throw new SIMASCNegocioExcepcion(0, e.getMessage());
		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(500, e.getMessage());
		}

	}

	@Override
	public void crearRolPersonaCaso(Long idPersona, Long idCaso, String nombreRol, String estado) {
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;

		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}

		Rol rol = manejadorRol.consultarRolPorNombre(nombreRol);

		// Adiciona el rol de la persona al caso en la tabla rolPersonaCaso
		RolPersonaCasoPK pk = new RolPersonaCasoPK(idPersona, idCaso, rol.getIdRol());
		RolPersonaCaso rolPersonaCaso = manejadorRolPersonaCaso.buscar(pk);
		if (rolPersonaCaso == null) {
			rolPersonaCaso = new RolPersonaCaso();
			rolPersonaCaso.setRolPersonaCasoPK(pk);
		}
		rolPersonaCaso.setEstado(estado);
		rolPersonaCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);

		rolPersonaCaso.setIdUsuarioModificacion(usuarioModificacion);
		rolPersonaCaso.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));

		actualizar(rolPersonaCaso);
	}

	@Override
	public Collection<RolPersonaCaso> consultarArbitrosPorRolCaso(Long idCaso) {
		List<RolPersonaCaso> rolPersonaCaso = new ArrayList<>();
		return transformarEntidadesColeccionConDependencias(
				manejadorRolPersonaCaso.consultarPersonasAsignadasCasoParaPreseleccion(idCaso), rolPersonaCaso);
	}

	@Override
	public void eliminarRolPersonasCasoPorId(Long idCaso, Long idPersona, String rolPersona)
			throws SIMASCNegocioExcepcion {
		Rol rol = new Rol();
		rol = manejadorRol.consultarRolPorNombre(rolPersona);
		Caso caso = new Caso();
		caso = manejadorCaso.buscar(idCaso);
		Persona persona = new Persona();
		persona = manejadorPersona.buscar(idPersona);
		RolPersonaCaso rolPersonaCasoActual = new RolPersonaCaso();
		rolPersonaCasoActual.setPersona(persona);
		rolPersonaCasoActual.setCaso(caso);
		rolPersonaCasoActual.setRol(rol);
		rolPersonaCasoActual.setEstado(UtilDominios.ESTADO_PERSONA_INACTIVO);
		rolPersonaCasoActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
		rolPersonaCasoActual.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
		rolPersonaCasoActual.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		manejadorRolPersonaCaso.crearRolPersonaCaso(rolPersonaCasoActual);
		if (UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO.equals(rol.getNombre())
				|| UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE.equals(rol.getNombre())
				|| UtilDominios.ROL_PERSONA_APODERADO_ACREEDOR_RECUPERACION.equals(rol.getNombre())
				|| UtilDominios.ROL_PERSONA_APODERADO_DEUDOR_RECUPERACION.equals(rol.getNombre())) {

			estadoPersonaTipoServicioFacade.notificacionArbitroLitigante(idPersona,
					UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA, UtilDominios.ESTADO_ARBITROS_INACTIVO_LITIGANDO, idCaso);

		}

	}

	@Override
	public void eliminarArbitrosCaso(Long idCaso, String rolPersona) {
		Rol rol = new Rol();
		rol = manejadorRol.consultarRolPorNombre(rolPersona);
		// Consulta el caso por el id del caso
		List<RolPersonaCaso> lstArbitrosActuales = manejadorRolPersonaCaso.consultarArbitrosPorRolCaso(idCaso,
				rol.getNombre());
		for (RolPersonaCaso rolPersonaCasoActual : lstArbitrosActuales) {
			rolPersonaCasoActual.setEstado(UtilDominios.ESTADO_PERSONA_INACTIVO);
			rolPersonaCasoActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			manejadorRolPersonaCaso.crearRolPersonaCaso(rolPersonaCasoActual);
			// manejadorRolPersonaCaso.eliminar(rolPersonaCasoActual);
		}

	}

	/**
	 * Solo aplica para registrar partes
	 */
	@Override
	public void cambiarRolAPersonaEnCaso(Long idCaso, Long idPersona, Long idRol) {

		InformacionFiltro filtroActivo = new InformacionFiltro(TipoFiltro.EXACTO,
				RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		InformacionFiltro filtroCaso = new InformacionFiltro(TipoFiltro.EXACTO,
				RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_PK_ID_CASO, idCaso);
		InformacionFiltro filtroPersona = new InformacionFiltro(TipoFiltro.EXACTO,
				RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_PK_ID_PERSONA, idPersona);
		InformacionFiltro filtroEstado = new InformacionFiltro(TipoFiltro.EXACTO,
				RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, UtilDominios.ESTADO_ROL_PERSONA_CASO_NO_APLICA);

		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(filtroActivo);
		filtros.add(filtroCaso);
		filtros.add(filtroPersona);
		filtros.add(filtroEstado);

		InformacionAsignacion asignacionRol = new InformacionAsignacion(
				"p." + RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_PK_ID_ROL, idRol);
		List<InformacionAsignacion> asignaciones = new ArrayList<>();
		asignaciones.add(asignacionRol);
		List<RolPersonaCaso> listRolAnterior = manejadorRolPersonaCaso.consultar(filtros, null, null);
		if(listRolAnterior.size() > 0) {
			RolPersonaCaso rpAnterior = listRolAnterior.get(0);
			if (rpAnterior.getRolPersonaCasoPK().getIdRol() == UtilConstantes.ID_ROL_CONVOCANTE
					|| rpAnterior.getRolPersonaCasoPK().getIdRol() == UtilConstantes.ID_ROL_CONVOCADO
					|| rpAnterior.getRolPersonaCasoPK().getIdRol() == UtilConstantes.ID_ROL_APODERADO_CONVOCANTE
					|| rpAnterior.getRolPersonaCasoPK().getIdRol() == UtilConstantes.ID_ROL_APODERADO_CONVOCADO) {
				this.duplicarRolPersonaCaso(idCaso, idPersona, idRol);
			} else {
				manejadorRolPersonaCaso.actualizarPorFiltros(asignaciones, filtros);
			}
		}	
	}

	/**
	 * Método para replicar registro a inactivar en rolPersonaCaso
	 */
	public void duplicarRolPersonaCaso(Long idCaso, Long idPersona, Long idRol) {
		InformacionFiltro filtroActivo = new InformacionFiltro(TipoFiltro.EXACTO,
				RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		InformacionFiltro filtroCaso = new InformacionFiltro(TipoFiltro.EXACTO,
				RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_PK_ID_CASO, idCaso);
		InformacionFiltro filtroPersona = new InformacionFiltro(TipoFiltro.EXACTO,
				RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_PK_ID_PERSONA, idPersona);
		InformacionFiltro filtroEstado = new InformacionFiltro(TipoFiltro.EXACTO,
				RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, UtilDominios.ESTADO_ROL_PERSONA_CASO_NO_APLICA);

		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(filtroActivo);
		filtros.add(filtroCaso);
		filtros.add(filtroPersona);
		filtros.add(filtroEstado);
		try {
			RolPersonaCaso rpAnterior = manejadorRolPersonaCaso.consultar(filtros, null, null).get(0);
			RolPersonaCaso rpNuevoA = new RolPersonaCaso();
			rpNuevoA.setRolPersonaCasoPK(new RolPersonaCasoPK());
			rpNuevoA.getRolPersonaCasoPK().setIdCaso(idCaso);
			rpNuevoA.getRolPersonaCasoPK().setIdPersona(idPersona);
			rpNuevoA.getRolPersonaCasoPK().setIdRol(idRol);
			rpNuevoA.setEstado(rpAnterior.getEstado());
			rpNuevoA.setMotivoInactivacion(rpAnterior.getMotivoInactivacion());
			rpNuevoA.setTipoNombramiento(rpAnterior.getTipoNombramiento());
			rpNuevoA.setOrdenDeAsignacion(rpAnterior.getOrdenDeAsignacion());
			rpNuevoA.setMetodoNombramiento(rpAnterior.getMetodoNombramiento());
			rpNuevoA.setTipoSuplencia(rpAnterior.getTipoSuplencia());
			rpNuevoA.setEsPresidente(rpAnterior.getEsPresidente());
			rpNuevoA.setIdUsuarioModificacion(rpAnterior.getIdUsuarioModificacion());
			rpNuevoA.setFechaUltimaModificacion(new Date());
			rpNuevoA.setEstadoRegistro(rpAnterior.getEstadoRegistro());
			rpNuevoA.setIdRolApoderado(rpAnterior.getIdRolApoderado());
			rpNuevoA.setIdPersonaApoderado(rpAnterior.getIdPersonaApoderado());
			rpNuevoA.setIdCasoApoderado(rpAnterior.getIdCasoApoderado());
			rpNuevoA.setIdSorteo(rpAnterior.getIdSorteo());
			rpNuevoA.setIdPronunciamiento(rpAnterior.getIdPronunciamiento());
			rpNuevoA.setIdRolPrincipalReemplazado(rpAnterior.getIdRolPrincipalReemplazado());
			rpNuevoA.setIdPersonaPrincipalReemplazado(rpAnterior.getIdPersonaPrincipalReemplazado());
			rpNuevoA.setIdCasoPrincipalReemplazado(rpAnterior.getIdCasoPrincipalReemplazado());
			rpNuevoA.setIdPersonaTercero(rpAnterior.getIdPersonaTercero());
			manejadorRolPersonaCaso.crear(rpNuevoA);
			rpAnterior.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			manejadorRolPersonaCaso.actualizar(rpAnterior);
		} catch (Exception e) {
			throw new SimascException(e);
		}
	}

	@Override
	public void eliminarRolPersonaCaso(Long idCaso, Long idPersona, String nombreRol) {
		Rol rol = new Rol();
		rol = manejadorRol.consultarRolPorNombre(nombreRol);
		RolPersonaCaso rolPersonaCaso = new RolPersonaCaso();
		RolPersonaCasoPK rolpk = new RolPersonaCasoPK(idPersona, idCaso, rol.getIdRol());
		rolPersonaCaso = manejadorRolPersonaCaso.buscar(rolpk);
		rolPersonaCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
		rolPersonaCaso.setFechaUltimaModificacion(new Date());
		rolPersonaCaso.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		manejadorRolPersonaCaso.actualizar(rolPersonaCaso);

	}

	/**
	 * Metodo que elimina del pacto arbitral a un tercero o autoridad judicial
	 * 
	 * @param idCaso
	 * @param idPersona
	 * @param nombreRol
	 */
	@Override
	public void eliminarPactoTerceroOAutoridadJudicial(Long idCaso, Long idPersona, String nombreRol) {
		try {
			eliminarRolPersonaCaso(idCaso, idPersona, nombreRol);
			List<InformacionFiltro> filtros = new ArrayList<InformacionFiltro>();
			InformacionFiltro filtro1 = new InformacionFiltro(TipoFiltro.EXACTO,
					NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_ID_CASO, idCaso);
			InformacionFiltro filtro2 = new InformacionFiltro(TipoFiltro.EXACTO,
					NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_ID_PERSONA, idPersona);
			InformacionFiltro filtro3 = new InformacionFiltro(TipoFiltro.EXACTO,
					NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			filtros.add(filtro1);
			filtros.add(filtro2);
			filtros.add(filtro3);
			List<NombramientoPersona> np = manejadorNombramientoPersona.consultar(filtros, null, null);
			for (NombramientoPersona nombramientoPersona : np) {
				nombramientoPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
				manejadorNombramientoPersona.actualizar(nombramientoPersona);
			}
		} catch (Error e) {
			throw new SIMASCNegocioExcepcion(0, e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IRolPersonaCasoFacade#reparto(com.ccb.simasc.transversal.dto.formularios.
	 * RepartoDTO)
	 */
	@Override
	public boolean reparto(RepartoDTO reparto) throws SIMASCNegocioExcepcion {
		Caso caso = casoFacade.transformarEntidadSinDependencias(casoFacade.buscar(reparto.getIdCaso()));
		Rol rol = rolFacade.transformarEntidadSinDependencias(manejadorRol.consultarRolPorNombre(reparto.getRol()));
		return reparto(caso, rol);
	}

	/**
	 * Actualizar el documento para el reparto BUG - 2858
	 * 
	 * @author sMartinez
	 * @param documento
	 * @param asignado
	 * @param caso
	 */
	private void actualizarDocumentoReparto(Documento documento, RolPersona asignado, Caso caso) {
		documento.setIdDigitalizador(asignado.getPersona().getIdPersona());
		documento.setFechaAsignacion(new Date());
		documento.setIdUsuarioModificacion(caso.getIdUsuarioModificacion());
		documento.setFechaUltimaModificacion(new Date());
		manejadorDocumento.actualizar(documento);
	}

	/**
	 * Comunica al digitalizador su asignación al documento BUG - 2858
	 * 
	 * @author sMartinez
	 * @param caso
	 * @param asignado
	 */
	private void comunicarRepartoDigitalizador(Caso caso, RolPersona asignado) {
		String asunto = UtilConstantes.DESIGNACION;
		StringBuilder textoCorreo = new StringBuilder();
		List<String> args = new ArrayList<>();
		String nombreRol = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA,
				asignado.getRol().getNombre());
		args.add(nombreRol);
		args.add(String.valueOf(caso.getIdCaso()));
		args.add(caso.getNombre());
		textoCorreo.append(String.format(
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO186.toString()), args.toArray()));
		List<String> cuerpoCorreo = new ArrayList<>();
		cuerpoCorreo.add(textoCorreo.toString());
		List<Persona> personas = new ArrayList<Persona>();
		personas.add(asignado.getPersona());
		try {
			correoRolPersonaCasoFacade.envioDeCorreo(asunto, personas, null, null, cuerpoCorreo, caso.getIdCaso(), null,
					null, false);
			eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_ASIGNACION_DIGITALIZADOR,
					UtilConstantes.ASIGNACION_FUNCIONARIO + UtilConstantes.CARACTER_ESPACIO
							+ asignado.getPersona().getNombreCompleto(),
					UtilConstantes.USUARIO_DEFECTO_SIMASC, new Date(), UtilDominios.ESTADO_REGISTRO_ACTIVO);
		} catch (Exception e) {
		}
	}

	/**
	 * Realiza el reparto para el rol de digitalizador BUG - 2858
	 * 
	 * @author sMartinez
	 * @param caso
	 * @param rolPersonas
	 * @param posicion
	 */
	private void repartoDigitalizador(Caso caso, List<Number> rolPersonas, ParametrosGenerales posicion) {
		List<Documento> documentos = manejadorDocumento.consultarDocumentosActivos(caso.getIdCaso());
		for (Documento documento : documentos) {
			if (documento.getEstadoDigitalizacion() != null
					&& documento.getEstadoDigitalizacion().equals(UtilDominios.ESTADO_DIGITALIZACION_POR_DIGITALIZAR)
					&& documento.getIdDigitalizador() == null) {
				RolPersona asignado = realizarAsignacion(rolPersonas, posicion);
				actualizarDocumentoReparto(documento, asignado, caso);
				comunicarRepartoDigitalizador(caso, asignado);
				manejadorProgramacionAlerta.crearProgramacionAlerta(caso.getIdCaso(), asignado.getIdPersona(),
						UtilDominios.CODIGO_ALERTA_DOCUMENTOS_SIN_DIGITALIZAR, documento.getIdDocumento());

			}
		}
	}

	/**
	 * Devuelve el tipo de evento correspondiente al prestador de servicios BUG -
	 * 2858
	 * 
	 * @author sMartinez
	 * @param rol
	 * @return
	 */
	private String tipoEventoPorRolPrestador(Rol rol) {
		String tipoEvento = null;
		if (rol.getNombre().equals(UtilDominios.ROL_PERSONA_ABOGADO))
			tipoEvento = UtilDominios.TIPO_EVENTO_ASIGNACION_ABOGADO;
		else if (rol.getNombre().equals(UtilDominios.ROL_PERSONA_AUXILIAR_ADM))
			tipoEvento = UtilDominios.TIPO_EVENTO_ASIGNACION_AUXILIAR;
		else if (rol.getNombre().equals(UtilDominios.ROL_PERSONA_CONCILIADOR_EN_INSOLVENCIA))
			tipoEvento = UtilDominios.TIPO_EVENTO_DESIGNACION;
		else
			tipoEvento = UtilDominios.TIPO_EVENTO_ASIGNACION_ABOGADO;
		return tipoEvento;
	}

	/**
	 * BUG - 2858
	 * 
	 * @author sMartinez
	 * @param caso
	 * @param rol
	 * @param rpc
	 */
	private void registrarEventoPrestadorDeServicio(Caso caso, Rol rol, RolPersonaCaso rpc) {
		String tipoEvento = tipoEventoPorRolPrestador(rol);
		eventoFacade.registrarEvento(caso, tipoEvento,
				UtilConstantes.ASIGNACION_FUNCIONARIO + UtilConstantes.CARACTER_ESPACIO
						+ rpc.getPersona().getNombreCompleto(),
				UtilConstantes.USUARIO_DEFECTO_SIMASC, new Date(), UtilDominios.ESTADO_REGISTRO_ACTIVO);
	}

	/**
	 * Registra los eventos del reparto para el prestador de servicio y comunica al
	 * prestador BUG - 2858
	 * 
	 * @author sMartinez
	 * @param caso
	 * @param rol
	 * @param rpc
	 */
	private void comunicarRepartoPrestadorDeServicio(Caso caso, Rol rol, RolPersonaCaso rpc) {
		// Registra Evento
		registrarEventoPrestadorDeServicio(caso, rol, rpc);
		// Prepara Correo
		String asunto = UtilConstantes.DESIGNACION + UtilConstantes.CARACTER_ESPACIO + caso.getIdCaso();
		StringBuilder textoCorreo = new StringBuilder();
		List<String> args = new ArrayList<>();
		args.add(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA, rol.getNombre()));
		args.add(String.valueOf(caso.getIdCaso()));
		args.add(caso.getNombre());
		textoCorreo.append(String.format(
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO186.toString()), args.toArray()));
		List<String> cuerpoCorreo = new ArrayList<>();
		cuerpoCorreo.add(textoCorreo.toString());
		List<RolPersonaCaso> rolPersonaCaso = new ArrayList<RolPersonaCaso>();
		rolPersonaCaso.add(rpc);
		// Envia correo
		correoRolPersonaCasoFacade.envioDeCorreo(asunto, null, rolPersonaCaso, null, cuerpoCorreo, caso.getIdCaso(),
				null, null, false);

	}

	/**
	 * Realiza el reparto para prestadoreas de servicio BUG - 2858
	 * 
	 * @author sMartinez
	 * @param caso
	 * @param rolPersonas
	 * @param posicion
	 * @param rol
	 */
	private void repartoPrestadorServicio(Caso caso, List<Number> rolPersonas, ParametrosGenerales posicion, Rol rol) {
		RolPersona asignado = realizarAsignacion(rolPersonas, posicion);
		if (asignado != null) {
			RolPersonaCaso rpc = saveRPC(caso, rol, asignado.getPersona(),
					UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);
			comunicarRepartoPrestadorDeServicio(caso, rol, rpc);
			if (rol.getNombre().equals(UtilDominios.ROL_PERSONA_ABOGADO)) {
				manejadorProgramacionAlerta.crearProgramacionAlerta(caso.getIdCaso(), asignado.getIdPersona(),
						UtilDominios.CODIGO_ALERTA_SIN_TRAMITAR_POR_ABOGADO1, null);
				manejadorProgramacionAlerta.crearProgramacionAlerta(caso.getIdCaso(), asignado.getIdPersona(),
						UtilDominios.CODIGO_ALERTA_SIN_TRAMITAR_POR_ABOGADO2, null);
			}

		} else {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR468.toString()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IRolPersonaCasoFacade#reparto(com.ccb.simasc.transversal.entidades.Caso,
	 * com.ccb.simasc.transversal.entidades.Rol)
	 */
	@Override
	public boolean reparto(Caso caso, Rol rol) throws SIMASCNegocioExcepcion {
		if (!rol.getNombre().equals(UtilDominios.ROL_PERSONA_DIGITALIZADOR) && manejadorRolPersonaCaso
				.consultarPersonasAsignadasCasoPorRol(caso.getIdCaso(), rol.getNombre()).size() > 0)
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR201.toString()));
		try {
			List<String> roles = new ArrayList<>();
			if (rol.getNombre().equals(UtilDominios.ROL_PERSONA_DIGITALIZADOR)) {
				ParametrosGenerales posicion = obtenerSiguiente(rol);
				roles.add(rol.getNombre());
				List<Number> rolPersonas = manejadorRolPersona.consultarRolPersonasPorRolesIds(roles);
				repartoDigitalizador(caso, rolPersonas, posicion);
			} else {
				ParametrosGenerales posicion = null;
				if (UtilDominios.ROL_PERSONA_ABOGADO.equals(rol.getNombre())) {
					Rol rolARepartirPorServicio = obtenerRolARepartirPorServicio(caso);
					if (rolARepartirPorServicio == null)
						throw new SIMASCNegocioExcepcion(
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR449.toString()));
					posicion = obtenerSiguiente(rolARepartirPorServicio);
					roles.add(rolARepartirPorServicio.getNombre());
				} else {
					posicion = obtenerSiguiente(rol);
					roles.add(rol.getNombre());
				}
				List<Number> rolPersonas = manejadorRolPersona.consultarRolPersonasPorRolesIds(roles);
				repartoPrestadorServicio(caso, rolPersonas, posicion, rol);
			}
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IRolPersonaCasoFacade#obtenerSiguiente(com.ccb.simasc.transversal.
	 * entidades.Rol)
	 */
	@Override
	public ParametrosGenerales obtenerSiguiente(Rol rol) {
		ParametrosGenerales paramG = manejadorParametrosGenerales.obtenerParametrosPorCodigoYTipo(
				UtilParamGenerales.PREFIJO_PARAMETROS_REPARTO + rol.getNombre(),
				UtilParamGenerales.TIPO_REPARTO_EQUITATIVO);

		if (paramG == null) {
			paramG = new ParametrosGenerales();
			paramG.setCodigo(UtilParamGenerales.PREFIJO_PARAMETROS_REPARTO + rol.getNombre());
			paramG.setTipo(UtilParamGenerales.TIPO_REPARTO_EQUITATIVO);
			paramG.setValorNumerico(Long.valueOf(1));
			paramG.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			manejadorParametrosGenerales.crear(paramG);
		}

		return paramG;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IRolPersonaCasoFacade#realizarAsignacion(java.util.List,
	 * com.ccb.simasc.transversal.entidades.ParametrosGenerales)
	 */
	@Override
	public RolPersona realizarAsignacion(List<Number> rolPersonas, ParametrosGenerales posicion) {
		RolPersona rpc = null;
		// Casos de excepción
		// Si la posición está previamente definida y se puede realizar la
		// asignación
		if (posicion.getValorNumerico() != null && !rolPersonas.isEmpty()) {
			for (int i = 0; i < rolPersonas.size(); i++) {
				if (rolPersonas.get(i).longValue() == posicion.getValorNumerico()) {
					if (i + 1 == rolPersonas.size()) {
						rpc = manejadorRolPersona.buscar(Long.valueOf(rolPersonas.get(0).longValue()));
						posicion.setValorNumerico(rolPersonas.get(0).longValue());
					} else {
						rpc = manejadorRolPersona.buscar(Long.valueOf(rolPersonas.get(i + 1).longValue()));
						posicion.setValorNumerico(rolPersonas.get(i + 1).longValue());
					}
					manejadorParametrosGenerales.actualizar(posicion);
					return rpc;
				}
			}
			if (rpc == null) {
				rpc = manejadorRolPersona.buscar(Long.valueOf(rolPersonas.get(0).longValue()));
				posicion.setValorNumerico(rolPersonas.get(0).longValue());
				manejadorParametrosGenerales.actualizar(posicion);
			}
		} else if (posicion.getValorNumerico() == null && !rolPersonas.isEmpty()) {
			rpc = manejadorRolPersona.buscar(rolPersonas.get(0).longValue());
			posicion.setValorNumerico(rpc.getIdRolPersona());
			manejadorParametrosGenerales.actualizar(posicion);
		}
		return rpc;
	}

	public RolPersonaCaso saveRPC(Caso caso, Rol rol, Persona persona, String estado) {
		RolPersonaCaso rpc = new RolPersonaCaso();
		rpc.setEstado(estado);
		rpc.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		rpc.setFechaUltimaModificacion(new Date(new Date().getTime()));
		rpc.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		rpc.setCaso(caso);
		rpc.setRol(rol);
		rpc.setPersona(persona);
		if (caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_INSOLVENCIA)) {
			rpc.setTipoNombramiento(UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		}

		RolPersonaCasoPK rpcPK = new RolPersonaCasoPK();
		rpcPK.setIdCaso(caso.getIdCaso());
		rpcPK.setIdRol(rol.getIdRol());
		rpcPK.setIdPersona(persona.getIdPersona());
		rpc.setRolPersonaCasoPK(rpcPK);
		manejadorRolPersonaCaso.crear(rpc);
		return rpc;
	}

	// @Override
	// public void designarArbitroCaso(Persona persona, RolPersonaCaso rpc, Date
	// fechaDesignacion) throws SIMASCNegocioExcepcion {
	// Persona arbitro = persona;
	// if(persona.getIdPersona() != null)
	// arbitro = manejadorPersona.buscar(persona.getIdPersona());
	//
	// validarNombramientoArbitro(arbitro, rpc);
	// rpc.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO);
	// adicionarRolPersonaCasoConEvento(arbitro, rpc,fechaDesignacion, false);
	// }

	@Override
	public void designarArbitroCaso(DetalleArbitroDTO detalleArbitroDTO) throws SIMASCNegocioExcepcion {
		Date date = new Date();
		RolPersonaCaso rpc = detalleArbitroDTO.getRolPersonaCaso();
		Persona arbitro = detalleArbitroDTO.getPersona();
		Rol rol = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_ARBITRO);
		Caso caso = manejadorCaso.buscar(rpc.getRolPersonaCasoPK().getIdCaso());
		Persona arbitroDesignado = new Persona();
		List<EventoRolPersonaCaso> eventoRpc = manejadorEventoRolPersonaCaso.consultarEventosPorRPC(rol.getIdRol(),
				arbitro.getIdPersona(), rpc.getRolPersonaCasoPK().getIdCaso(),
				UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		if (eventoRpc != null && !eventoRpc.isEmpty()) {			
			EventoRolPersonaCaso erpc = eventoRpc.get(eventoRpc.size() - 1);
			if (!erpc.getFechaDeAsignacion().before(detalleArbitroDTO.getEventoDesignacion().getFechaDeAsignacion())) {
				String fechaEvento = UtilOperaciones.formatearFechaFormato(erpc.getFechaDeAsignacion(), "dd-MM-YYYY");
				throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia()
						.getMensajePorKey(MensajesEnum.ERROR038.toString()).replace("<fechaDesignacion>", fechaEvento));
			}
		}
		Integer porNombrar = nombramientoPersonasFacade.obtenerNumeroArbitrosPorNombrar(caso.getIdCaso(),
				rpc.getMetodoNombramiento(), rpc.getTipoNombramiento(), rpc.getIdPersonaTercero());
		if (porNombrar < 1) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR033.toString()));
		}
		// trae el arbitro en caso de que ya extista en la base de datos
		if (arbitro.getIdPersona() != null) {
			arbitroDesignado = manejadorPersona.buscar(arbitro.getIdPersona());
		} else {
			// si el arbitro nuevo ya exite muestra error
			Boolean isActualizar = false;
			if (arbitro.getTipoDocumento() != null && arbitro.getNumeroDocumento() != null) {
				Persona persona = manejadorPersona.consultarPersonaPorIdentificacion(arbitro.getTipoDocumento(),
						arbitro.getNumeroDocumento());
				arbitroDesignado = (persona != null) ? persona : arbitroDesignado;
				isActualizar = !(persona == null);
			}
			// Actualizar Modelo
			arbitroDesignado.setNumeroDocumento(arbitro.getNumeroDocumento());
			arbitroDesignado.setTipoDocumento(arbitro.getTipoDocumento());
			arbitroDesignado.setPrimerNombreORazonSocial(arbitro.getPrimerNombreORazonSocial());
			arbitroDesignado.setSegundoNombre(arbitro.getSegundoNombre());
			arbitroDesignado.setSegundoApellido(arbitro.getSegundoApellido());
			arbitroDesignado.setPrimerApellido(arbitro.getPrimerApellido());
			arbitroDesignado.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			arbitroDesignado.setEstadoPersona(UtilDominios.ESTADO_PERSONA_ACTIVO);
			arbitroDesignado.setTipoPersona(UtilDominios.TIPO_PERSONA_NATURAL);
			arbitroDesignado.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			arbitroDesignado.setFechaUltimaModificacion(new Timestamp(date.getTime()));
			arbitroDesignado.setTipoFuncionario(UtilDominios.PERSONA_FUNCIONARIO_EXTERNO);
			if (isActualizar)
				manejadorPersona.actualizar(arbitroDesignado);
			else
				arbitroDesignado = manejadorPersona.crearPersona(arbitroDesignado);

			if (detalleArbitroDTO.getPersona() != null && detalleArbitroDTO.getPersona().getUbicacionList() != null) {
				for (Ubicacion ubicacion : detalleArbitroDTO.getPersona().getUbicacionList()) {
					UbicacionDTO ubicacionDTO = new UbicacionDTO();
					if (detalleArbitroDTO.getPais() != null && detalleArbitroDTO.getDireccion() != null) {
						if (detalleArbitroDTO.getCiudad() != null) {
							ubicacionDTO.setIdZonaGeografica(detalleArbitroDTO.getCiudad().getIdZonaGeografica());
						} else {
							ubicacionDTO.setIdZonaGeografica(detalleArbitroDTO.getPais().getIdZonaGeografica());
						}

						ubicacionDTO.setDireccion(ubicacion.getDireccion());
						ubicacionDTO.setIdPersona(arbitroDesignado.getIdPersona());
						ubicacionDTO.setTipo(ubicacion.getTipo());
						ubicacionDTO.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
						ubicacionFacade.adicionarDireccion(ubicacionDTO);

					}
				}
			}

			if (detalleArbitroDTO.getPersona().getTelefonoList() != null
					&& detalleArbitroDTO.getPersona().getTelefonoList().size() > 0) {
				for (Telefono telefonoFor : detalleArbitroDTO.getPersona().getTelefonoList()) {
					if (telefonoFor.getNumero() != null && telefonoFor.getTipoTelefono() != null) {
						Telefono nuevoTelefono = new Telefono();
						nuevoTelefono.setNumero(telefonoFor.getNumero());
						nuevoTelefono.setTipoTelefono(telefonoFor.getTipoTelefono());
						nuevoTelefono.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
						nuevoTelefono.setFechaUltimaModificacion(new Date());
						nuevoTelefono.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
						nuevoTelefono.setIdPersona(arbitroDesignado.getIdPersona());
						manejadorTelefono.crear(nuevoTelefono);
					}

				}
			}

			if (detalleArbitroDTO.getPersona().getCorreoElectronicoList() != null
					&& detalleArbitroDTO.getPersona().getCorreoElectronicoList().size() > 0) {
				for (CorreoElectronico correoFor : detalleArbitroDTO.getPersona().getCorreoElectronicoList()) {
					if (correoFor.getTipo() != null && correoFor.getDireccion() != null) {
						CorreoElectronico correoElectronico = new CorreoElectronico();
						correoElectronico.setDireccion(correoFor.getDireccion());
						correoElectronico.setTipo(correoFor.getTipo());
						correoElectronico.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
						correoElectronico.setFechaUltimaModificacion(new Date());
						correoElectronico.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
						correoElectronico.setIdPersona(arbitroDesignado.getIdPersona());
						manejadorCorreoElectronico.crear(correoElectronico);
					}

				}
			}

		}

		rpc.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		rpc.setFechaUltimaModificacion(new Timestamp(date.getTime()));
		rpc.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		rpc.setPersona(arbitroDesignado);
		rpc.getRolPersonaCasoPK().setIdPersona(arbitroDesignado.getIdPersona());
		rpc.setRol(rol);
		rpc.setCaso(caso);
		rpc.getRolPersonaCasoPK().setIdRol(rol.getIdRol());

		if (UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE
				.equals(detalleArbitroDTO.getRolPersonaCaso().getTipoNombramiento())) {
			rpc.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO);
		} else {
			rpc.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		}

		validarNombramientoArbitro(rpc);
		rpc = manejadorRolPersonaCaso.crearRolPersonaCaso(rpc);
		rpc.setIdPronunciamiento(null);

		manejadorRolPersonaCaso.actualizar(rpc);

		EventoRolPersonaCaso erpc = new EventoRolPersonaCaso();
		erpc.setRolPersonaCaso(rpc);
		erpc.setIdCaso(rpc.getCaso().getIdCaso());
		erpc.setIdPersona(rpc.getPersona().getIdPersona());
		erpc.setIdRol(rpc.getRol().getIdRol());
		erpc.setFechaDeAsignacion(detalleArbitroDTO.getEventoDesignacion().getFechaDeAsignacion());
		if (UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE
				.equals(detalleArbitroDTO.getRolPersonaCaso().getTipoNombramiento())) {
			erpc.setEstadoAsignado(UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO);

		} else {
			erpc.setEstadoAsignado(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
			// Se crea nuevo evento para la ruta del caso
			String observaciones = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO550.toString());
			eventoFacade.registrarEvento(rpc.getCaso().getIdCaso(),
					UtilDominios.TIPO_EVENTO_DESIGNACION_ARBITRO_PRINCIPAL, observaciones,
					UtilConstantes.USUARIO_DEFECTO_SIMASC,
					detalleArbitroDTO.getEventoDesignacion().getFechaDeAsignacion(),
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
		}
		erpc.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		erpc.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		erpc.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));

		manejadorEventoRolPersonaCaso.crearEventoRolPersonaCaso(erpc);

	}

	public void validarNombramientoArbitro(RolPersonaCaso rpc) throws SIMASCNegocioExcepcion {
		if (rpc.getPersona().getNumeroDocumento() != null) {
			Persona personaExiste = manejadorPersona.getConsultarPersonaPorDocumento(
					rpc.getPersona().getTipoDocumento(), rpc.getPersona().getNumeroDocumento());
			if (personaExiste != null && rpc.getPersona().getIdPersona() == null) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR034.toString())
								.replace("<personaExiste>", personaExiste.getNombreCompleto()));
			}
		}

		Persona arbitroExiste = manejadorPersona.getConsultarPersonaPorCedulaPorRolPorCaso(
				rpc.getPersona().getNumeroDocumento(), UtilDominios.ROL_PERSONA_ARBITRO,
				rpc.getRolPersonaCasoPK().getIdCaso());
		if (arbitroExiste != null && !esArbitroInactivoEnCaso(rpc.getPersona().getNumeroDocumento(),
				rpc.getRol().getNombre(), rpc.getRolPersonaCasoPK().getIdCaso())) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR034.toString())
							.replace("<personaExiste>", arbitroExiste.getNombreCompleto()));
		}

		if (manejadorRolPersonaCaso.existeArbitroNombramientoOrden(rpc))
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR035.toString()));
	}

	@Override
	public String habilitarArbitro(Long idCaso, Long idArbitro, String motivoHabilitacion)
			throws SIMASCNegocioExcepcion {
		try{
			RolPersonaCaso rolPersonaCaso = manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(idArbitro, idCaso);
	
			Caso caso = manejadorCaso.buscarCasoActivo(idCaso);
			PersonaServicioMateria personaServicioMateria = manejadorPersonaServicioMateria.consultaPersonServicioMateriaByIdPersonaIdCaso(idArbitro, idCaso);
	
			if(personaServicioMateria != null)
			{
				this.cambiarEstadoArbitroSorteo(rolPersonaCaso, caso, motivoHabilitacion,
										UtilDominios.ESTADO_REGISTRO_ACTIVO);
			}
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		}
		return null;
	}

	@Override
	public void cambiarEstadoArbitroSorteo(RolPersonaCaso rolPersonaCaso, Caso caso, String observaciones,
			String estadoSorteo) {
		PersonaServicioMateria personaServicioMateria = null;
		if(estadoSorteo.equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)){
			personaServicioMateria = manejadorPersonaServicioMateria.consultaPersonServicioMateriaByIdPersonaIdCaso(rolPersonaCaso.getPersona().getIdPersona(), caso.getIdCaso());			
		}else{
			Sorteo sorteo = sorteoFacade.buscar(rolPersonaCaso.getIdSorteo());
			Long idMateria = null;
			Long idServicio = sorteo.getServicioCaso();
			
			//para arbitraje abreviado se debe buscar el servicio en el cual se inactivo			
			List<Audiencia> audiencias = sorteo.getAudienciaList();
			String tipoSorteo = null;
			for (Audiencia it1 : audiencias) {
				if (it1.getTipoAudiencia().equals(UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_DE_DESIGNACION)
						||it1.getTipoAudiencia().equalsIgnoreCase(UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_INTERNACIONAL_DE_DESIGNACION)) {
					tipoSorteo = it1.getTipoSorteo();
				}
			}

			if(tipoSorteo != null){
				ParametroServicioSorteo parametro = parametroServicioSorteoFacade.buscar(Long.valueOf(tipoSorteo));
				if(parametro != null){
					idServicio = parametro.getIdServicio();
					if(!parametro.getSorteoConMateria()){
						idMateria = UtilConstantes.ID_SIN_MATERIA;
					}else{
						//Cuando se inactiva al operador se debe buscar el servicio y la materia
						//Se debe tener en cuenta que cuando hay preselección por la ccb, esta puede hacerse en varias materias
						if(!sorteo.isPreseleccion()){
							idMateria = sorteo.getMateriaCaso();
						}else{
							PreseleccionadoPK pk = new PreseleccionadoPK(caso.getIdCaso(), rolPersonaCaso.getPersona().getIdPersona());
							Preseleccionado preseleccionado = preseleccionadoFacade.buscar(pk);
							idMateria = preseleccionado.getIdMateria();
						}
					}
				}
			}			

			if(idMateria != null){
				personaServicioMateria = manejadorPersonaServicioMateria.consultarPersonaServicioMateriaPorServicioMateriaPersona(idServicio, idMateria, rolPersonaCaso.getPersona().getIdPersona()); 
			}			
		}

		if(personaServicioMateria != null)
		{
			StringBuilder observacion = new StringBuilder();
			personaServicioMateria.setEstadoParaSorteo(estadoSorteo);
			if (estadoSorteo.equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
				personaServicioMateria.setMotivoEstadoSorteo(null);
				personaServicioMateria.setIdCaso(null);
			}else{
				personaServicioMateria.setMotivoEstadoSorteo(UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
			}
			personaServicioMateria.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			personaServicioMateria.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			personaServicioMateria.setFechaUltimaModificacion(new Date());
			manejadorPersonaServicioMateria.actualizar(personaServicioMateria);

			HistoricoPersonaServicioMateria hpsm = new HistoricoPersonaServicioMateria();
			hpsm.setIdPersonaServicioMateria(personaServicioMateria.getIdPersonaServicioMateria());
			hpsm.setPersonaServicioMateria(personaServicioMateria);
			hpsm.setEstadoParaSorteo(estadoSorteo);
			hpsm.setMotivoEstadoSorteo(null);
			if (estadoSorteo.equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
				hpsm.setMotivoEstadoSorteo(null);
				hpsm.setIdCaso(null);
			}else{
				personaServicioMateria.setMotivoEstadoSorteo(UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
				personaServicioMateria.setIdCaso(caso.getIdCaso());
			}
			hpsm.setFechaAsignacionEstado(new Date());
			if (observaciones.equals(UtilDominios.ESTADO_CASO_CERRADO)) {
				String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO094.toString());
				observacion.append(mensaje).append(UtilConstantes.CARACTER_ESPACIO).append(caso.getIdCaso()).append(UtilConstantes.CARACTER_ESPACIO).append(caso.getNombre());
				hpsm.setObservaciones(observacion.toString());
			} else {
				observacion.append(observaciones).append(UtilConstantes.CARACTER_ESPACIO).append(caso.getIdCaso()).append(UtilConstantes.CARACTER_ESPACIO).append(caso.getNombre());
				hpsm.setObservaciones(observacion.toString());
			}
			hpsm.setFechaUltimaModificacion(new Date());
			hpsm.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			hpsm.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			manejadorHistoricoPersonaServicioMateria.crear(hpsm);
		}		
	}

	public List<DetalleArbitroDTO> consultarDetallesArbitros(Long idCaso, Boolean conMotivoInactivacion, Boolean conSecretarios)
			throws Exception {
		List<DetalleArbitroDTO> arbitrosDTOs = new ArrayList<DetalleArbitroDTO>();
		List<Object[]> detalleArbitros = manejadorRolPersonaCaso.consultarDetallesArbitros(idCaso,
				conMotivoInactivacion, conSecretarios);
		for (Object[] o : detalleArbitros) {
			DetalleArbitroDTO detalleArbitroDTO = new DetalleArbitroDTO();

			RolPersonaCaso rpc = (RolPersonaCaso) o[0];

			detalleArbitroDTO.setPersona(personaFacade.transformarEntidadSinDependencias(rpc.getPersona()));

			detalleArbitroDTO.setRolDescripcion(dominioFacade.obtenerDominioRol(rpc.getRolPersonaCasoPK().getIdRol()));
			List<CorreoElectronico> correos = rpc.getPersona().getCorreoElectronicoList();
			if (!correos.isEmpty()) {
				detalleArbitroDTO.setEmailUno(correos.get(0).getDireccion());
			}

			List<Telefono> telefonos = manejadorTelefono
					.consultarTelefonosPersona(rpc.getRolPersonaCasoPK().getIdPersona(), true);
			if (telefonos != null && !telefonos.isEmpty()) {

				for (Telefono tel : telefonos) {
					if (tel.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_CELULAR)) {
						detalleArbitroDTO.setNumeroCelular(tel.getNumero());

					} else {
						if (tel.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
							detalleArbitroDTO.setNumeroTelefono(tel.getNumero());
						}
					}
				}
			}

			List<Ubicacion> ubicaciones = manejadorUbicacion
					.consultarPorIdPersona(rpc.getRolPersonaCasoPK().getIdPersona());
			if (ubicaciones != null && !ubicaciones.isEmpty()) {
				Ubicacion primerUbicacion = ubicaciones.get(0);
				detalleArbitroDTO.setDireccion(primerUbicacion.getDireccion());

			}

			detalleArbitroDTO.setNombreCompleto(rpc.getPersona().getNombreCompleto());
			rpc = rpc != null ? transformarEntidadSinDependencias(rpc) : null;
			detalleArbitroDTO.setRolPersonaCaso(rpc);

			List<EventoRolPersonaCaso> eventosdelRpc = manejadorEventoRolPersonaCaso.consultarEventosPorRPC(
					rpc.getRolPersonaCasoPK().getIdRol(), rpc.getRolPersonaCasoPK().getIdPersona(),
					rpc.getRolPersonaCasoPK().getIdCaso(), null);

			if (eventosdelRpc != null && !eventosdelRpc.isEmpty()) {
				for (EventoRolPersonaCaso eventoRolPersonaCasoFor : eventosdelRpc) {
					if (UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO
							.equals(eventoRolPersonaCasoFor.getEstadoAsignado())
							|| UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO
									.equals(eventoRolPersonaCasoFor.getEstadoAsignado())) {
						o[1] = eventoRolPersonaCasoFor;
					} else if (UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO
							.equals(eventoRolPersonaCasoFor.getEstadoAsignado())) {
						o[5] = eventoRolPersonaCasoFor;
					} else if (UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE.equals(rpc.getTipoNombramiento())
							&& UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO
									.equals(eventoRolPersonaCasoFor.getEstadoAsignado())) {
						o[2] = eventoRolPersonaCasoFor;
					} else if (UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL.equals(rpc.getTipoNombramiento())
							&& UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR
									.equals(eventoRolPersonaCasoFor.getEstadoAsignado())) {
						o[2] = eventoRolPersonaCasoFor;
					}

				}
			}

			EventoRolPersonaCaso eventoDesignacion = (EventoRolPersonaCaso) o[2];
			eventoDesignacion = eventoDesignacion != null
					? eventoRolPersonaCasoFacade.transformarEntidadSinDependencias(eventoDesignacion)
					: null;
			detalleArbitroDTO.setEventoDesignacion(eventoDesignacion);

			EventoRolPersonaCaso eventoComunicacion = (EventoRolPersonaCaso) o[5];
			eventoComunicacion = eventoComunicacion != null
					? eventoRolPersonaCasoFacade.transformarEntidadSinDependencias(eventoComunicacion)
					: null;

			SimpleDateFormat formatter = new SimpleDateFormat(UtilConstantes.FORMATO_FECHA_DIA_MES_ANIO);

			if (eventoDesignacion != null && eventoComunicacion != null
					&& (formatter.parse(formatter.format(eventoComunicacion.getFechaDeAsignacion()))
							.after(formatter.parse(formatter.format(eventoDesignacion.getFechaDeAsignacion())))
							|| formatter.parse(formatter.format(eventoComunicacion.getFechaDeAsignacion())).equals(
									formatter.parse(formatter.format(eventoDesignacion.getFechaDeAsignacion()))))) {
				detalleArbitroDTO.setEventoComunicacion(eventoComunicacion);
			}

			EventoRolPersonaCaso eventoPronunciamiento = (EventoRolPersonaCaso) o[1];
			eventoPronunciamiento = eventoPronunciamiento != null
					? eventoRolPersonaCasoFacade.transformarEntidadSinDependencias(eventoPronunciamiento)
					: null;

			if (eventoDesignacion != null && eventoPronunciamiento != null
					&& (formatter.parse(formatter.format(eventoPronunciamiento.getFechaDeAsignacion()))
							.after(formatter.parse(formatter.format(eventoDesignacion.getFechaDeAsignacion())))
							|| formatter.parse(formatter.format(eventoPronunciamiento.getFechaDeAsignacion())).equals(
									formatter.parse(formatter.format(eventoDesignacion.getFechaDeAsignacion()))))) {
				detalleArbitroDTO.setEventoPronunciamiento(eventoPronunciamiento);
			}

			Pronunciamiento p = (Pronunciamiento) o[3];
			p = p != null ? pronunciamientoFacade.transformarEntidadSinDependencias(p) : null;
			detalleArbitroDTO.setPronunciamiento(p);

			List<Recusacion> recusaciones = new ArrayList<>();

			if (o[4] != null)
				if (!(o[4] instanceof Recusacion))
					recusaciones.addAll((List<Recusacion>) o[4]);
				else
					recusaciones.add((Recusacion) o[4]);

			if (recusaciones != null && !recusaciones.isEmpty()) {
				List<RecusacionDTO> recusacionesDTO = (List<RecusacionDTO>) recusacionFacade
						.transformarColeccionSinDependencias(recusaciones, new ArrayList<RecusacionDTO>());
				detalleArbitroDTO.setRecusacion((ArrayList<RecusacionDTO>) recusacionesDTO);
			}
			boolean recusado = rpc.getEstado().equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_RECUSADO)
					|| (rpc.getEstado().equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO)
							&& rpc.getMotivoInactivacion() != null
							&& rpc.getMotivoInactivacion().equals(UtilDominios.MOTIVO_RECUSACION));
			detalleArbitroDTO.setRecusado(recusado);
			Date fechaComunicacion = detalleArbitroDTO.getEventoComunicacion() == null ? null
					: detalleArbitroDTO.getEventoComunicacion().getFechaDeAsignacion();
			Date fechaPronunciamiento = detalleArbitroDTO.getEventoPronunciamiento() == null ? null
					: detalleArbitroDTO.getEventoPronunciamiento().getFechaDeAsignacion();
			boolean cumplePlazo = false;
			boolean muestraPlazo = false;

			List<String> nombreParametros = new ArrayList<String>();
			nombreParametros.add(UtilConstantes.TIEMPO_MAX_PRONUNCIAMIENTO_CASO);

			List<ParametroDeServicioDTO> parametroDeServicioList = parametroDeServicioFacade
					.consultarParametroDeServicio(nombreParametros, rpc.getIdServicio(),
							UtilConstantes.TIPO_PARAMETRO_TIEMPO_PRONUNCIAMIENTO_CASO);

			int diasMaximo = UtilConstantes.DIAS_MAXIMOS_PARA_PRONUNCIAMIENTO;

			if (parametroDeServicioList != null && !parametroDeServicioList.isEmpty()
					&& parametroDeServicioList.get(0).getValor() != null
					&& !parametroDeServicioList.get(0).getValor().isEmpty()) {
				diasMaximo = Integer.parseInt(parametroDeServicioList.get(0).getValor());
			}

			if (fechaComunicacion != null) {
				Date FechaComunicacionLimite = diaFestivoFacade.sumarDiasHabilesAFecha(fechaComunicacion,
						Long.valueOf(diasMaximo));
				if (FechaComunicacionLimite.after(new Date()) || FechaComunicacionLimite.equals(new Date())) {
					cumplePlazo = true;
				}
				if (fechaPronunciamiento != null) {
					if (formatter.parse(formatter.format(fechaPronunciamiento))
							.before(formatter.parse(formatter.format(FechaComunicacionLimite)))
							|| formatter.parse(formatter.format(fechaPronunciamiento))
									.equals(formatter.parse(formatter.format(FechaComunicacionLimite)))) {
						cumplePlazo = true;
					} else {
						cumplePlazo = false;
					}
				}
				muestraPlazo = true;
			}

			detalleArbitroDTO.setCumplePlazoPronunciamiento(cumplePlazo);
			detalleArbitroDTO.setMuestraPlazoPronunciamiento(muestraPlazo);

			arbitrosDTOs.add(detalleArbitroDTO);
		}
		return arbitrosDTOs;
	}

	@Override
	public void eliminarRolPersonaCasoConEvento(Long idCaso, Long idPersona, String rolPersona, String tipoEvento,
			String observaciones) throws SIMASCNegocioExcepcion {
		RolPersonaCasoPK pk = new RolPersonaCasoPK();
		pk.setIdCaso(idCaso);
		pk.setIdPersona(idPersona);
		Rol rol = manejadorRol.consultarRolPorNombre(rolPersona);
		pk.setIdRol(rol.getIdRol());
		RolPersonaCaso rolPersonaCasoActual = manejadorRolPersonaCaso.buscar(pk);
		rolPersonaCasoActual.setEstado(UtilDominios.ESTADO_PERSONA_INACTIVO);
		rolPersonaCasoActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
		rolPersonaCasoActual.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
		rolPersonaCasoActual.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		manejadorRolPersonaCaso.crearRolPersonaCaso(rolPersonaCasoActual);

		Caso caso = manejadorCaso.buscar(idCaso);
		eventoFacade.registrarEvento(caso, tipoEvento, observaciones, UtilConstantes.USUARIO_DEFECTO_SIMASC,
				new Timestamp(new Date().getTime()), UtilDominios.ESTADO_REGISTRO_ACTIVO);

	}

	@Override
	public void validarCambioEstadoArbitros(CambioEstadoRPC_DTO cambioEstadoDTO) throws SIMASCNegocioExcepcion {
		// estados que pueden cambiar
		List<String> estadosIniciales = new ArrayList<>();
		estadosIniciales.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		estadosIniciales.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		estadosIniciales.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
		estadosIniciales.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO);
		estadosIniciales.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_RECUSADO);

		Caso caso = casoFacade.buscar(cambioEstadoDTO.getIdCaso());
		if (!cambioEstadoDTO.getIdPersonas().isEmpty() && caso.getEstadoCaso().equals(UtilDominios.ESTADO_CASO_CERRADO)
				&& cambioEstadoDTO.getMotivo().equals(UtilDominios.MOTIVO_SUSPENDIDO)) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR030.toString());
			throw new SIMASCNegocioExcepcion(mensaje);
		}

		List<RolPersonaCaso> rpcs = manejadorRolPersonaCaso.consultarPersonasPorRolCaso(cambioEstadoDTO.getIdPersonas(),
				cambioEstadoDTO.getIdCaso());
		for (RolPersonaCaso rpc : rpcs) {
			Persona persona = personaFacade.buscar(rpc.getRolPersonaCasoPK().getIdPersona());
			if (!estadosIniciales.contains(rpc.getEstado())) {
				String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR031.toString());
				throw new SIMASCNegocioExcepcion(mensaje.replace("<nombre>", persona.getNombreCompleto()));
			}

			List<EventoRolPersonaCaso> erpcDesignacion = manejadorEventoRolPersonaCaso.consultarEvento(
					manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_ARBITRO).getIdRol(),
					rpc.getRolPersonaCasoPK().getIdPersona(), cambioEstadoDTO.getIdCaso(),
					UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);

			if (!erpcDesignacion.isEmpty()) {
				EventoRolPersonaCaso eventoDesignacion = erpcDesignacion.get(0);
				if (eventoDesignacion.getFechaDeAsignacion().after(cambioEstadoDTO.getFechaCambio())) {
					String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR032.toString());
					throw new SIMASCNegocioExcepcion(mensaje.replace("<nombre>", persona.getNombreCompleto()));
				}
			}
		}
	}

	@Override
	public void cambiarEstadoArbitros(CambioEstadoRPC_DTO cambioEstadoDTO) throws SIMASCNegocioExcepcion {
		validarCambioEstadoArbitros(cambioEstadoDTO);
		String nombresArbitros = "";
		Caso caso = casoFacade.buscar(cambioEstadoDTO.getIdCaso());
		manejadorRolPersonaCaso.actualizarEstadoPersonasCaso(cambioEstadoDTO.getIdPersonas(),
				cambioEstadoDTO.getIdCaso(), UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO,
				cambioEstadoDTO.getMotivo());
		List<RolPersonaCaso> rpcs = manejadorRolPersonaCaso.consultarPersonasPorRolCaso(cambioEstadoDTO.getIdPersonas(),
				cambioEstadoDTO.getIdCaso());

		for (RolPersonaCaso rpc : rpcs) {
			Persona persona = personaFacade.buscar(rpc.getRolPersonaCasoPK().getIdPersona());
			nombresArbitros += persona.getNombreCompleto() + ", ";
			eventoRolPersonaCasoFacade.crearEventoRolPersonaCaso(UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO,
					cambioEstadoDTO.getMotivo(), cambioEstadoDTO.getFechaCambio(), UtilDominios.ESTADO_REGISTRO_ACTIVO,
					rpc.getRolPersonaCasoPK().getIdRol(), rpc.getRolPersonaCasoPK().getIdPersona(),
					cambioEstadoDTO.getIdCaso());

			if (rpc.getMetodoNombramiento() != null
					&& rpc.getMetodoNombramiento().equals(UtilDominios.NOMBRAMIENTO_POR_LA_CCB)
					&& rpc.getTipoNombramiento().equalsIgnoreCase(UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL)) {
				// Se remite el código del caso y motivo de liberación (Reversar
				// designación de suplente), Se hace el llamado al caso de uso
				// SIMASC - CU Arbitraje– Habilitar arbitro.
				habilitarArbitro(cambioEstadoDTO.getIdCaso(), persona.getIdPersona(),
				dominioFacade.getNombreDominio(UtilDominios.DOMINIO_MOTIVOS_INACTIVACION_ARBITRO, cambioEstadoDTO.getMotivo()));
			}

			if (manejadorRolPersonaCaso.consultarNumeroArbitrosNombrados(cambioEstadoDTO.getIdCaso(),
					UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE) > 0) {
				// NOMBRAR ARBITRO SUPLENTE COMO PRINCIPAL
				CambioEstadoSuplenteDTO cambioEstado = new CambioEstadoSuplenteDTO();
				cambioEstado.setIdCaso(caso.getIdCaso());
				cambioEstado.setIdPersona(rpc.getPersona().getIdPersona());
				cambioEstado.setIdUsuario(cambioEstadoDTO.getIdUsuario().toString());
				nombrarSuplentePrincipal(cambioEstado);
			}else{
				boolean bloqueaSuplente = manejadorParametroServicioSorteo.bloqueaSuplente(cambioEstadoDTO.getIdCaso());
				if(rpc.getTipoNombramiento().equalsIgnoreCase(UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE) && bloqueaSuplente ){
					habilitarArbitro(cambioEstadoDTO.getIdCaso(), persona.getIdPersona(),
							dominioFacade.getNombreDominio(UtilDominios.DOMINIO_MOTIVOS_INACTIVACION_ARBITRO, cambioEstadoDTO.getMotivo()) );
				}
			}

		}
		if (nombresArbitros.length() > 2)
			nombresArbitros = nombresArbitros.substring(0, nombresArbitros.length() - 2);
		// Se crea el evento
		String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO020.toString());
		String Observaciones = mensaje.replace("<estado>", UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO)
				.replace("<nombresArbitros>", nombresArbitros).replace("<motivos>", cambioEstadoDTO.getObservaciones());

		eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_MODIFICACION_DE_ESTADO_ARBITRO, Observaciones,
				UtilConstantes.USUARIO_DEFECTO_SIMASC, new Date(), UtilDominios.ESTADO_REGISTRO_ACTIVO);

	}

	@Override
	public Collection<RolPersonaCasoDesignacionDTO> consultarArbitrosDesignados(Long idCaso, String nombreRol)
			throws SIMASCNegocioExcepcion {
		List<RolPersonaCaso> persona = new ArrayList<>();
		persona = (List<RolPersonaCaso>) transformarEntidadesColeccionConDependencias(
				manejadorRolPersonaCaso.consultarArbitrosDesignados(idCaso, nombreRol), persona);
		List<RolPersonaCasoDesignacionDTO> lstPersonaDesignacion = new ArrayList<>();
		for (RolPersonaCaso rolPersonaCaso : persona) {
			RolPersonaCasoDesignacionDTO personaDesignacion = new RolPersonaCasoDesignacionDTO();
			personaDesignacion.setIdPersona(rolPersonaCaso.getPersona().getIdPersona());
			personaDesignacion.setNombreCompleto(rolPersonaCaso.getPersona().getNombreCompleto());
			personaDesignacion.setMetodoNombramiento(rolPersonaCaso.getMetodoNombramiento());
			personaDesignacion.setTipoNombramiento(rolPersonaCaso.getTipoNombramiento());
			lstPersonaDesignacion.add(personaDesignacion);
		}
		return lstPersonaDesignacion;

	}

	@Override
	public List<Persona> consultarArbitrosPrincipales(Long idCaso) {
		return manejadorRolPersonaCaso.consultarArbitrosPrincipales(idCaso, UtilDominios.ROL_PERSONA_ARBITRO);
	}

	@Override
	public boolean nombrarSuplentePrincipal(CambioEstadoSuplenteDTO cambioEstadoSuplenteDto) {
		boolean suplenteDisponible = true;

		try {
			RolPersonaCaso rolPersonaCasoArbitroTitular = manejadorRolPersonaCaso.consultarRolPersonaCaso(
					cambioEstadoSuplenteDto.getIdPersona(), cambioEstadoSuplenteDto.getIdCaso(), null);

			EventoRolPersonaCaso eventoRolArbitroTitular = rolPersonaCasoArbitroTitular
					.traerEventoAsignacionPorAceptar();
			EventoRolPersonaCaso eventoRechazoArbitroTitual = rolPersonaCasoArbitroTitular
					.traerUtilmaAsignacionPorEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
			Date fechaAsignacion = new Date();
			if (eventoRechazoArbitroTitual != null && eventoRechazoArbitroTitual.getFechaDeAsignacion() != null) {
				fechaAsignacion = eventoRechazoArbitroTitual.getFechaDeAsignacion();
			}

			// Objetos del suplente
			EventoRolPersonaCaso eventoRolPersonaCasoSuplente = new EventoRolPersonaCaso();
			RolPersonaCaso rolpersonaCasoSuplente = new RolPersonaCaso();
			RolPersonaCaso rolpersonaPrincipalRemplazo = new RolPersonaCaso();

			eventoRolPersonaCasoSuplente = manejadorRolPersonaCaso
					.asignacionArbitroSuplente(rolPersonaCasoArbitroTitular, eventoRolArbitroTitular);
			if (eventoRolPersonaCasoSuplente == null) {
				suplenteDisponible = false;
			} else {
				//Validar que el id_remplaza_principal no haya sido ya asignado para realizar la actualizacion del suplente
				rolpersonaPrincipalRemplazo = manejadorRolPersonaCaso.consultarPersonaPrincipalRemplazoCaso(
						rolPersonaCasoArbitroTitular.getPersona().getIdPersona(), eventoRolPersonaCasoSuplente.getIdCaso());
				
				if(rolpersonaPrincipalRemplazo == null || rolpersonaPrincipalRemplazo.getIdPersonaPrincipalReemplazado()==null) {
					
					rolpersonaCasoSuplente = manejadorRolPersonaCaso.consultarRolPersonaCaso(
							eventoRolPersonaCasoSuplente.getIdPersona(), eventoRolPersonaCasoSuplente.getIdCaso(), null);
	
					rolpersonaCasoSuplente.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
					rolpersonaCasoSuplente.setFechaUltimaModificacion(new Date());
					rolpersonaCasoSuplente.setIdUsuarioModificacion(cambioEstadoSuplenteDto.getIdUsuario());
					rolpersonaCasoSuplente.setTipoNombramiento(UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
					rolpersonaCasoSuplente.setRolPersonaCaso(rolPersonaCasoArbitroTitular);
					rolpersonaCasoSuplente
							.setIdPersonaPrincipalReemplazado(rolPersonaCasoArbitroTitular.getPersona().getIdPersona());
					rolpersonaCasoSuplente
							.setIdCasoPrincipalReemplazado(rolPersonaCasoArbitroTitular.getCaso().getIdCaso());
					rolpersonaCasoSuplente.setIdRolPrincipalReemplazado(rolPersonaCasoArbitroTitular.getRol().getIdRol());
	
					manejadorRolPersonaCaso.actualizar(rolpersonaCasoSuplente);
	
					eventoRolPersonaCasoFacade.crearEventoRolPersonaCaso(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR,
							null, fechaAsignacion, UtilDominios.ESTADO_REGISTRO_ACTIVO,
							eventoRolPersonaCasoSuplente.getIdRol(), eventoRolPersonaCasoSuplente.getIdPersona(),
							eventoRolPersonaCasoSuplente.getIdCaso());
	
					// generacion de mensajes
	
					StringBuilder observacion = new StringBuilder();
					observacion.append("Se nombró al suplente ");
					observacion.append(rolpersonaCasoSuplente.getPersona().getNombreCompleto());
					observacion.append(" como principal, el cual remplazará a ");
					observacion.append(rolPersonaCasoArbitroTitular.getPersona().getNombreCompleto());
					// si es nombrado por la camara cambiar el estado del sorteo
	
					// Creacion del evento del Caso
					eventoFacade.registrarEvento(cambioEstadoSuplenteDto.getIdCaso(),
							UtilDominios.TIPO_EVENTO_CAMBIO_TIPO_NOMBRAMIENTO, observacion.toString(),
							cambioEstadoSuplenteDto.getIdUsuario());
	
					if (rolpersonaCasoSuplente.getMetodoNombramiento().equals(UtilDominios.NOMBRAMIENTO_POR_LA_CCB)
							&& rolpersonaCasoSuplente.getIdSorteo() != null
							&& (!rolpersonaCasoSuplente.getCaso().getPreseleccion()
									|| (rolpersonaCasoSuplente.getCaso().getPreseleccion()
											&& rolpersonaCasoSuplente.getCaso().getQuienPreselecciona().equals(
													UtilDominios.PRESELECCIONADO_POR_LA_CAMARA_DE_COMERCIO_BOGOTA)))) {
						cambiarEstadoArbitroSorteo(rolpersonaCasoSuplente, rolpersonaCasoSuplente.getCaso(), observacion.toString(), UtilDominios.ESTADO_PERSONA_SORTEO_INACTIVO);														
					}
				}
			}
		} catch (SimascException e) {

			throw SIMASCNegocioExcepcion.transformTopException(e);
		}

		return suplenteDisponible;
	}


	@Override
	public CasosAsignadosDTO obtenerRolDeCaso(CasosAsignadosDTO casosAsignadosDTO) throws SIMASCNegocioExcepcion {
		RolPersonaCaso casoAsignadoDTO = manejadorRolPersonaCaso.obtenerRolDePersona(casosAsignadosDTO);
		CasosAsignadosDTO caso = new CasosAsignadosDTO();
		caso.setIdCaso(casoAsignadoDTO.getCaso().getIdCaso());
		caso.setIdPersona(casoAsignadoDTO.getPersona().getIdPersona());
		caso.setNombreRol(casoAsignadoDTO.getRol().getNombre());
		return caso;
	}

	/**
	 * Permite a una persona natural o jurídica consultar si se encuentra vinculado
	 * a un caso de arbitraje
	 * 
	 * @param tipoDoc
	 * @param identificacion
	 * @param nombre
	 * @return VinculacionPersonaCasoDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	@Override
	public List<VinculacionPersonaCasoDTO> consultaVinculacionPersonaCaso(String tipoDoc, String identificacion,
			String nombre) throws SIMASCNegocioExcepcion {

		List<VinculacionPersonaCasoDTO> vinculacionPersonaList = new ArrayList<VinculacionPersonaCasoDTO>();
		List<RolPersonaCaso> rolPersonaCasoList = manejadorRolPersonaCaso.consultaVinculacionPersonaCaso(tipoDoc,
				identificacion, nombre);
		if (rolPersonaCasoList != null && rolPersonaCasoList.size() > 0) {
			for (RolPersonaCaso rolPersonaCaso : rolPersonaCasoList) {
				Caso casoRPC = rolPersonaCaso.getCaso();
				VinculacionPersonaCasoDTO vinculacionPersonaCasoDTO = new VinculacionPersonaCasoDTO(
						casoRPC.getServicioMateria().getServicio().getNombre(), casoRPC.getIdCaso(),
						casoRPC.getNombre(), casoRPC.getFechaRadicacion(), casoRPC.getEstadoCaso());
				vinculacionPersonaCasoDTO.setCaso(casoFacade.transformarEntidadSinDependencias(casoRPC));
				vinculacionPersonaList.add(vinculacionPersonaCasoDTO);
			}
		} else {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO061.toString()));
		}
		return vinculacionPersonaList;
	}

	@Override
	public void pronunciamientoSecretario(Pronunciamiento pronunciamiento, String idPersonaModificacion, Long idCaso)
			throws SIMASCNegocioExcepcion {

		Long idPersona = Long.valueOf(idPersonaModificacion);

		try {
			// creacion del documento -- modificado en el nuevo manejo de
			// archivos

			// creacion del pronunciamiento
			pronunciamiento.setFecha(new Date());
			pronunciamiento.setFechaUltimaModificacion(new Date());
			pronunciamiento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			pronunciamiento.setIdUsuarioModificacion(idPersonaModificacion);
			manejadorPronunciamiento.crear(pronunciamiento);

			RolPersonaCaso rolPersonaCaso = manejadorRolPersonaCaso.consultarSecretarioDelCaso(idCaso);

			// 2. Cambia el estado del Secretario, obtiene el tipo de evento,
			// las
			// observaciones del evento y guarda la información
			String tipoEvento = null;
			String observaciones = rolPersonaCaso.getPersona().getNombreCompleto() + UtilConstantes.CARACTER_COMA
					+ UtilConstantes.CARACTER_ESPACIO;

			if (UtilDominios.TIPO_PRONUNCIAMIENTO_ACEPTA.equals(pronunciamiento.getPronunciamiento())) {
				rolPersonaCaso.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
				tipoEvento = UtilDominios.TIPO_EVENTO_ACEPTACION_DESIGNACION_ARBITRO;
				observaciones += MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO050.toString());
			} else if (UtilDominios.TIPO_PRONUNCIAMIENTO_DECLINA.equals(pronunciamiento.getPronunciamiento())) {
				rolPersonaCaso.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
				tipoEvento = UtilDominios.TIPO_EVENTO_DECLINACION_DESIGNACION_ARBITRO;
				observaciones += MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO051.toString());
			}
			rolPersonaCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			rolPersonaCaso.setIdUsuarioModificacion(idPersonaModificacion);
			rolPersonaCaso.setFechaUltimaModificacion(new Date());
			rolPersonaCaso.setIdPronunciamiento(pronunciamiento.getIdPronunciamiento());
			rolPersonaCaso.setTipoSuplencia(null);
			manejadorRolPersonaCaso.actualizar(rolPersonaCaso);

			// 3. Registra el evento del cambio de estado del arbitro

			this.registrarEventoRolPersonaCaso(rolPersonaCaso, idPersonaModificacion, rolPersonaCaso.getEstado(), null);

			// 4. Actualiza la ruta del caso (Evento)
			eventoFacade.registrarEvento(rolPersonaCaso.getCaso(), tipoEvento, observaciones, idPersonaModificacion,
					new Date(), UtilDominios.ESTADO_REGISTRO_ACTIVO);

			List<String> nombresRoles = new ArrayList<String>();
			nombresRoles.add(UtilDominios.ROL_PERSONA_ABOGADO);
			nombresRoles.add(UtilDominios.ROL_PERSONA_ARBITRO);
			nombresRoles.add(UtilDominios.ROL_PERSONA_ARBITRO_EXTERNO);
			nombresRoles.add(UtilDominios.ROL_PERSONA_AUXILIAR_ADM);

			// 6. Genera notificación al abogado del arbitraje asignado al caso
			// informando el pronunciamiento del ÃƒÂ¡rbitro.
			List<RolPersonaCaso> personasDelCaso = manejadorRolPersonaCaso.consultarPersonasAsignadasCasoPorRol(idCaso,
					nombresRoles);
			List<RolPersonaCaso> personasAceptadas = new ArrayList<RolPersonaCaso>();

			// filtra por las persnas que ya estan con estado aceptado
			for (RolPersonaCaso rolPersonaFor : personasDelCaso) {
				if (rolPersonaFor != null
						&& UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO.equals(rolPersonaFor.getEstado())) {
					personasAceptadas.add(rolPersonaFor);
				}

			}
			RolPersonaCaso secretario = manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(idPersona, idCaso);
			// Creacion de correo
			String asuntoCorreo = "Pronunciamiento Secretario de Tribunal";
			StringBuilder textoCorreo = new StringBuilder("El Sr. ")
					.append(secretario.getPersona().getNombreCompleto());

			if (UtilDominios.TIPO_PRONUNCIAMIENTO_ACEPTA.equals((pronunciamiento.getPronunciamiento()))) {
				textoCorreo.append(" ha Aceptado la designación como Secretario de tribunal para el caso ");
			} else {
				textoCorreo.append(" ha Declinado la designación como Secretario de tribunal para el caso ");
			}
			textoCorreo.append(secretario.getCaso().getIdCaso()).append(" - ").append(secretario.getCaso().getNombre());

			// datos para el envio del correo
			List<String> cuerpoCorreo = new ArrayList<String>();
			List<CorreoElectronico> correosEnviar = new ArrayList<CorreoElectronico>();
			List<CorreoElectronico> correoEmisor = new ArrayList<CorreoElectronico>();

			for (RolPersonaCaso rolPersonaFor : personasAceptadas) {
				correosEnviar.addAll(
						correoElectronicoFacade.consultaCorreosPersona(rolPersonaFor.getPersona().getIdPersona()));
			}

			cuerpoCorreo.add(textoCorreo.toString());

			// traer personas del sistema
			Persona sistema = manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA,
					UtilDominios.ESTADO_PERSONA_ACTIVO);
			correoEmisor = correoElectronicoFacade.consultaCorreosPersona(sistema.getIdPersona());

			List<CorreoElectronicoDTO> correosEviarDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade
					.transformarColeccionConDependencias(correosEnviar, new ArrayList<CorreoElectronicoDTO>());
			List<CorreoElectronicoDTO> correosEmisorDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade
					.transformarColeccionConDependencias(correoEmisor, new ArrayList<CorreoElectronicoDTO>());

			if (correosEviarDTO.size() > 0) {
				// Parametros del correo
				ParametrosEnvioCorreoDTO parametrosCorreo = new ParametrosEnvioCorreoDTO();
				parametrosCorreo.setCuerpoCorreo(cuerpoCorreo);
				parametrosCorreo.setRolPersonaCaso(correosEviarDTO);
				parametrosCorreo.setRemitente(correosEmisorDTO.get(0));
				parametrosCorreo.setIdCaso(idCaso);
				parametrosCorreo.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);
				parametrosCorreo.setAsunto(asuntoCorreo);
				parametrosCorreo.setAdjuntos(new ArrayList<DocumentoDTO>());
				parametrosCorreo.setCertificado(true);
				// envia el correo revisar el correo.
				correoRolPersonaCasoFacade.enviarCorreo(parametrosCorreo);

			}

		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString());
			throw new SimascException(mensajeError);
		}

	}

	/*
	 * Método encargado de registrar el evento del cambio de estado de un arbitro
	 * 
	 * @param rolPersonaCaso
	 */
	private void registrarEventoRolPersonaCaso(RolPersonaCaso rolPersonaCaso, String usuario, String estadoAsignado,
			String motivoInactivacion) {
		EventoRolPersonaCaso eventoRolPersonaCaso = new EventoRolPersonaCaso();
		eventoRolPersonaCaso.setEstadoAsignado(estadoAsignado);
		if (motivoInactivacion != null) {
			eventoRolPersonaCaso.setMotivoInactivacion(motivoInactivacion);
		}
		eventoRolPersonaCaso.setRolPersonaCaso(rolPersonaCaso);
		eventoRolPersonaCaso.setIdCaso(rolPersonaCaso.getCaso().getIdCaso());
		eventoRolPersonaCaso.setIdPersona(rolPersonaCaso.getPersona().getIdPersona());
		eventoRolPersonaCaso.setIdRol(rolPersonaCaso.getRol().getIdRol());
		eventoRolPersonaCaso.setEstadoAsignado(rolPersonaCaso.getEstado());
		eventoRolPersonaCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		eventoRolPersonaCaso.setIdUsuarioModificacion(usuario);
		eventoRolPersonaCaso.setFechaUltimaModificacion(new Date());
		eventoRolPersonaCaso.setFechaDeAsignacion(new Date());

		manejadorEventoRolPersonaCaso.crearEventoRolPersonaCaso(eventoRolPersonaCaso);
	}

	public void nombrarSuplenteEspecificoPrincipal(CambioEstadoSuplenteDTO cambioEstadoSuplenteDto)
			throws SIMASCNegocioExcepcion {
		// buscar el arbitro titular de el suplente ingresado
		RolPersonaCaso arbitroSuplente = manejadorRolPersonaCaso.consultarRolPersonaCaso(
				cambioEstadoSuplenteDto.getIdPersona(), cambioEstadoSuplenteDto.getIdCaso(), null);

		int numeroPrincipales = manejadorNombramientoPersona.consultarNumeroArbitrosPactados(
				cambioEstadoSuplenteDto.getIdCaso(), arbitroSuplente.getMetodoNombramiento(),
				UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL, arbitroSuplente.getIdPersonaTercero());

		List<String> metodosNombramiento = new ArrayList<String>();
		metodosNombramiento.add(arbitroSuplente.getMetodoNombramiento());
		List<String> estados = new ArrayList<String>();
		estados.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		estados.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);

		List<RolPersonaCaso> rolPersonaCasoTitular = manejadorRolPersonaCaso.consultarArbitros(
				cambioEstadoSuplenteDto.getIdCaso(), UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL, metodosNombramiento,
				estados);

		if (numeroPrincipales <= rolPersonaCasoTitular.size()) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR245.toString()));
		}

		// modificacion rolpersona caso, creacion del evento_rol_persona_caso
		arbitroSuplente.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		arbitroSuplente.setFechaUltimaModificacion(new Date());
		arbitroSuplente.setIdUsuarioModificacion(cambioEstadoSuplenteDto.getIdUsuario());
		arbitroSuplente.setTipoNombramiento(UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		manejadorRolPersonaCaso.actualizar(arbitroSuplente);
		this.registrarEventoRolPersonaCaso(arbitroSuplente, cambioEstadoSuplenteDto.getIdUsuario(),
				UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR, null);

		// Creacion del evento del Caso
		String observacion = "Se nombró al árbitro suplente " + arbitroSuplente.getPersona().getNombreCompleto()
				+ " como principal.";
		eventoFacade.registrarEvento(arbitroSuplente.getCaso().getIdCaso(),
				UtilDominios.TIPO_EVENTO_CAMBIO_TIPO_NOMBRAMIENTO, observacion, cambioEstadoSuplenteDto.getIdUsuario());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IRolPersonaCasoFacade#consultarDetalleArbitrosCaso(java.lang.Long)
	 */
	@Override
	public List<DetalleArbitroDTO> consultarDetalleArbitrosCaso(Long idCaso) throws Exception {
		List<DetalleArbitroDTO> detalleArbitrosCaso = new ArrayList<DetalleArbitroDTO>();

		// 1. Obtiene el detalle de todos los árbitros del caso que su estado
		// sea diferente de 'Inactivo'
		List<DetalleArbitroDTO> detalleArbitros = consultarDetallesArbitros(idCaso, false, false);

		// 2. Válida que la lista de detalle de árbitros del caso contenga
		// resultados
		if (detalleArbitros != null && !detalleArbitros.isEmpty()) {
			for (DetalleArbitroDTO detalleArbitroDTO : detalleArbitros) {

				// 3. Filtra la lista de detalle de árbitros teniendo en cuenta
				// unicamente los que tengan estado 'Aceptado' o 'Por aceptar'
				if (detalleArbitroDTO.getRolPersonaCaso().getEstado() != null && (detalleArbitroDTO.getRolPersonaCaso()
						.getEstado().equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO)
						|| detalleArbitroDTO.getRolPersonaCaso().getEstado()
								.equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR))) {
					detalleArbitrosCaso.add(detalleArbitroDTO);
				}
			}
		}

		return detalleArbitrosCaso;
	}

	@Override
	public Long consultarSecretarioCaso(Long idCaso) {
		Long idPersona = null;
		Caso caso = casoFacade.buscar(idCaso);
		List<RolPersonaCaso> rpcs = caso.getRolPersonaCasoList();
		for (RolPersonaCaso rpc : rpcs) {
			Rol rol = rpc.getRol();
			if (rol != null && UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL.equals(rol.getNombre())
					&& rpc.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO))
				return rpc.getRolPersonaCasoPK().getIdPersona();
		}
		return idPersona;
	}

	/**
	 * Método encargado de validar si un arbitro ya estuvo asignado a un caso y que
	 * si lo estuvo el estado sea INACTIVO
	 * 
	 * @param numeroDocumento
	 * @param nombreRol
	 * @param idCaso
	 * @return
	 */
	private boolean esArbitroInactivoEnCaso(String numeroDocumento, String nombreRol, Long idCaso) {
		boolean arbitroInactivo = true;

		List<RolPersonaCaso> rolesPersonaCaso = manejadorRolPersonaCaso.consultarListaRolPersonaCaso(numeroDocumento,
				nombreRol, idCaso);

		for (RolPersonaCaso rolPersonaCaso : rolesPersonaCaso) {
			if (rolPersonaCaso.getEstado() != null
					&& !UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO.equals(rolPersonaCaso.getEstado())) {
				arbitroInactivo = false;
				break;
			}
		}

		return arbitroInactivo;
	}

	@Override
	public List<PendientePronunciamientoDTO> casosPendientesPronunciamentoConciliador(Long idPersona, String rol) {
		return manejadorRolPersonaCaso.casosPendientesPronunciamentoConciliador(idPersona, rol);

	}

	@Override
	public RolPersonaCaso modificarEstadoRolPersonaCaso(Long idRol, Long idPersona, Long idCaso, String estado,
			String usuarioModificacion, String tipoNombramiento, Long pronunciamiento) {
		RolPersonaCaso rpcActual = manejadorRolPersonaCaso.buscar(new RolPersonaCasoPK(idPersona, idCaso, idRol));
		rpcActual.setEstado(estado);
		if (tipoNombramiento != null) {
			rpcActual.setTipoNombramiento(tipoNombramiento);
		}
		if (pronunciamiento != null) {
			rpcActual.setIdPronunciamiento(pronunciamiento);
		}
		rpcActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_ACTIVO);
		rpcActual.setFechaUltimaModificacion(new Date());
		rpcActual.setIdUsuarioModificacion(usuarioModificacion);
		manejadorRolPersonaCaso.actualizar(rpcActual);
		eventoRolPersonaCasoFacade.crearEventoRolPersonaCaso(estado, null, new Date(),
				UtilDominios.ESTADO_REGISTRO_ACTIVO, idRol, idPersona, idCaso);
		return rpcActual;
	}

	/**
	 * Obtiene el rol que se debe repartir como abogado según el sevicio del caso
	 * que esta siendo radicado
	 * 
	 * @param caso
	 * @return
	 */
	private Rol obtenerRolARepartirPorServicio(Caso caso) {
		Rol rolARepartirPorServicio = null;
		List<ParametrizarServicioRol> listParam = (List<ParametrizarServicioRol>) parametrizarServicioRolFacade
				.obtenerEntidadesTodos(new ArrayList<ParametrizarServicioRol>(), true);
		for (ParametrizarServicioRol param : listParam) {
			if (param.getParametrizarServicioRolPK().getIdServicio() == caso.getIdServicio()) {
				rolARepartirPorServicio = manejadorRol.buscar(param.getParametrizarServicioRolPK().getIdRol());
				break;
			}
		}
		return rolARepartirPorServicio;
	}

	@Override
	public List<PersonaBasicaDTO> consultarArbitrosDisponiblesSorteo(Long idCaso) {
		return manejadorRolPersonaCaso.consultarArbitrosDisponiblesSorteo(idCaso);
	}

	@Override
	public Long nombrarSuplenteConciliacion(PendientePronunciamientoDTO pendientePronunciamiento, Audiencia audiencia) {
		Long suplenteNombrado = -1L;
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;

		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null)
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		// buscar suplente
		String[] roles = { UtilDominios.ROL_PERSONA_CONCILIADOR };
		List<String> nombresRoles = Arrays.asList(roles);
		String[] estadosRol = { UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO };
		List<String> estados = Arrays.asList(estadosRol);
		List<RolPersonaCaso> conciliadores = manejadorRolPersonaCaso
				.consultarPersonasoPorRoleEstado(pendientePronunciamiento.getIdCaso(), nombresRoles, estados, false);

		if (conciliadores.size() > 1) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR290.toString()));
		} else if (conciliadores.size() == 1) {
			RolPersonaCaso conciliador = conciliadores.get(0);

			boolean agendaValida = audiencia == null
					|| consultaAgendaSuplente(conciliador.getRolPersonaCasoPK().getIdPersona(), audiencia);
			if (agendaValida) {
				suplenteNombrado = conciliador.getRolPersonaCasoPK().getIdPersona();
				this.modificarEstadoRolPersonaCaso(conciliador.getRolPersonaCasoPK().getIdRol(),
						conciliador.getRolPersonaCasoPK().getIdPersona(), conciliador.getRolPersonaCasoPK().getIdCaso(),
						UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR, usuarioModificacion,
						UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL, null);
				if (audiencia != null) {
					this.alistamientoBloqueoAgenda(suplenteNombrado, audiencia);
				}

				// envia el correo de nombramiento del suplente
				correoRolPersonaCasoFacade.envioCorreoNombramientoConciliador(
						conciliador.getRolPersonaCasoPK().getIdCaso(),
						conciliador.getRolPersonaCasoPK().getIdPersona());
			} else {
				// si no tiene agenda realiza el pronunciamiento del suplente
				PendientePronunciamientoDTO pronSuplente = new PendientePronunciamientoDTO();
				pronSuplente.setPronunciamiento(UtilDominios.TIPO_PRONUNCIAMIENTO_DECLINA);
				pronSuplente.setMotivoDeclinacion(UtilDominios.MOTIVO_RECHAZO_CONCILIADOR_NO_DISPONIBLE);
				Pronunciamiento pronunSuplente = pronunciamientoFacade.crearPronunciamiento(pronSuplente);
				this.modificarEstadoRolPersonaCaso(conciliador.getRolPersonaCasoPK().getIdRol(),
						conciliador.getRolPersonaCasoPK().getIdPersona(), conciliador.getRolPersonaCasoPK().getIdCaso(),
						UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO, usuarioModificacion, null,
						pronunSuplente.getIdPronunciamiento());
			}
		}
		return suplenteNombrado;

	}

	private boolean consultaAgendaSuplente(Long idConciliador, Audiencia audiencia) {
		Agendamiento agendamientoActual = null;
		for (Agendamiento agendaFor : audiencia.getAgendamientoList()) {
			if (agendamientoActual == null
					|| UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(agendaFor.getEstadoRegistro())) {
				agendamientoActual = agendaFor;
			}
		}
		if (agendamientoActual == null) {
			throw new SimascException("error con el agendamiento");
		}
		return agendamientoFacade.validarDisponibilidadConciliador(idConciliador, audiencia.getHoraInicio(),
				audiencia.getHoraInicio(), audiencia.getHoraFin(), agendamientoActual.getSala().getIdSede());
	}

	private void alistamientoBloqueoAgenda(Long idPersona, Audiencia audiencia) {
		String estado = UtilDominios.ESTADO_AGENDA_PERSONA_PROGRAMADA;
		String tipoActividad = UtilDominios.TIPO_ACTIVIDAD_AGENDA_AUDIENCIA_CONCILIACION;
		agendaPersonaFacade.bloquearAgendaPersona(idPersona, estado, tipoActividad, audiencia.getHoraInicio(),
				audiencia.getHoraFin(), audiencia.getIdAudiencia(), null);

	}

	@Override
	public List<LotesCartasDTO> lotesCartasByFiltros(LotesCartasFiltrosDTO filtros) {
		if (filtros.getFechaInicio() != null && filtros.getFechaFinal() != null && filtros.getTipoPartes() != null)
			return manejadorRolPersonaCaso.lotesCartasByFiltros(filtros);
		else
			throw new SIMASCNegocioExcepcion(FILTROS_BUSQUEDA_VACIOS);
	}

	@Override
	public List<PersonaCasoListDTO> consultarPersonasAsignadasCaso(Long idCaso, List<Long> idServicios) {
		return manejadorRolPersonaCaso.consultarPersonasAsignadasCaso(idCaso, idServicios);
	}

	@Override
	public List<PersonaBasicaDTO> consultarPersonaCasoPorRolCentro(List<String> centros, List<String> nombreRoles) {
		List<PersonaBasicaDTO> personasBasicasDTO = new ArrayList<PersonaBasicaDTO>();
		List<Persona> personas = manejadorRolPersonaCaso.consultarPersonaCasoPorRolCentro(centros, nombreRoles);
		if (!personas.isEmpty()) {
			personasBasicasDTO = (List<PersonaBasicaDTO>) PersonaBasicaDTO.newListaPersonaBasicaDTO(personas);
		}
		return personasBasicasDTO;
	}

	@Override
	public void cambiarConciliador(CambioConciliadorDTO informacionNuevoConciliador, String idUsuarioModificacion)
			throws EstadosCasoException {

		// Estados del conciliador principal a eliminar (DESIGNADO)
		List<String> estados = Arrays.asList(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR,
				UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		// Consulta conciliador principal a eliminar
		List<RolPersonaCaso> conciliador = manejadorRolPersonaCaso.consultaConciliadoresCasoEstadoNombramiento(
				informacionNuevoConciliador.getIdCaso(), estados, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);

		if (!conciliador.isEmpty()) {

			// Valida si existe un registro del conciliador seleccionado
			// manualmente en el caso
			if (informacionNuevoConciliador.getIdConciliador() != null
					&& manejadorRolPersonaCaso.validarPersonaCaso(informacionNuevoConciliador.getIdConciliador(),
							informacionNuevoConciliador.getIdCaso(), null, false)) {
				throw new SIMASCNegocioExcepcion(
						String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO351.toString())));
			}

			else {
				// Elimina del caso el Conciliador previamente designado.
				this.modificarEstadoRolPersonaCaso(conciliador.get(0).getRolPersonaCasoPK().getIdRol(),
						conciliador.get(0).getRolPersonaCasoPK().getIdPersona(),
						conciliador.get(0).getRolPersonaCasoPK().getIdCaso(),
						UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO, idUsuarioModificacion, null, null);

				// Busca Audiencia Pendiente
				List<Audiencia> audienciaPendiente = this.manejadorAudiencia.consultarAudienciasCasoPorTipoYEstado(
						conciliador.get(0).getRolPersonaCasoPK().getIdCaso(), null,
						UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);

				// Datos del reparto
				DatosEntradaRepartoDTO datosReparto = new DatosEntradaRepartoDTO();
				datosReparto.setIdCaso(informacionNuevoConciliador.getIdCaso());
				datosReparto.setInvocadoDesdeCambioConciliador(true);
				datosReparto.setUsuario(idUsuarioModificacion);
				datosReparto.setIdRol(conciliador.get(0).getRolPersonaCasoPK().getIdRol());

				// Libera la agenda del conciliador para la fecha y hora de la
				// audiencia.
				if (!audienciaPendiente.isEmpty()) {

					this.agendaPersonaFacade.liberarAgendamientoPersona(
							conciliador.get(0).getRolPersonaCasoPK().getIdPersona(),
							audienciaPendiente.get(0).getIdAudiencia());
					// Se consulta la sede de la audiencia para envío a reparto
					Long sedeAudiencia = manejadorSala.consultarSedeAudiencia(
							audienciaPendiente.get(0).getIdAudiencia(), informacionNuevoConciliador.getIdCaso());
					datosReparto.setFechaAudiencia(audienciaPendiente.get(0).getHoraInicio());
					datosReparto.setHoraAudiencia(audienciaPendiente.get(0).getHoraInicio());
					if (sedeAudiencia != null)
						datosReparto.setIdSede(sedeAudiencia);
				}

				// Log en ruta de caso
				Date fechaEvento = new Date();
				if (informacionNuevoConciliador.getIdConciliador() != null) {

					// Si el cambio es manual se setea el idConciliador para
					// enviar a reparto
					datosReparto.setIdConciliador(informacionNuevoConciliador.getIdConciliador());
					this.casoFacade.cambiarEstadoCaso(conciliador.get(0).getRolPersonaCasoPK().getIdCaso(),
							UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION, fechaEvento,
							UtilDominios.TIPO_EVENTO_CAMBIO_CONCILIADOR_PRINCIPAL_MANUAL,
							informacionNuevoConciliador.getObservaciones());
				} else {
					this.casoFacade.cambiarEstadoCaso(conciliador.get(0).getRolPersonaCasoPK().getIdCaso(),
							UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION, fechaEvento,
							UtilDominios.TIPO_EVENTO_CAMBIO_CONCILIADOR_PRINCIPAL_AUTOMATICO,
							informacionNuevoConciliador.getObservaciones());
				}

				try {

					PendientePronunciamientoDTO pendientePronunciamiento = new PendientePronunciamientoDTO();
					pendientePronunciamiento.setIdCaso(informacionNuevoConciliador.getIdCaso());
					if(nombrarSuplenteConciliacion(pendientePronunciamiento, audienciaPendiente.get(0)) == -1){
						repartosvcFacade.reparto(datosReparto);
					}
					
				} catch (Exception e) {
					logger.error("Error en el cambio de conciliador: ", e);
					throw new SimascException(e.getMessage());
				}
			}
		} else {
			throw new SIMASCNegocioExcepcion(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO350.toString())));
		}

	}

	@Override
	public List<CambioConciliadorDTO> obtenerConciliadoresCambio(Long idCaso) {

		// Lista de Conciliadores Cambio Manual
		List<CambioConciliadorDTO> conciliadoresCambio;

		// Consultar Ultima Audiencia
		AudienciaDTO audiencia = manejadorAudiencia.consultarUltimaAudienciaEstadoCaso(idCaso, null);
		if (audiencia == null || audiencia.getIdAudiencia() == null) {
			throw new SIMASCNegocioExcepcion(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO352.toString())));
		}

		// Consultar Sede ultima audiencia
		Long sedeUltimaAudiencia = manejadorSala.consultarSedeAudiencia(audiencia.getIdAudiencia(), idCaso);

		// Estados del conciliador principal a eliminar (DESIGNADO-ACEPTADO)
		List<String> estados = Arrays.asList(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR,
				UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		// Consulta Conciliador Asignado al Caso
		List<RolPersonaCaso> conciliador = manejadorRolPersonaCaso.consultaConciliadoresCasoEstadoNombramiento(idCaso,
				estados, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);

		if (conciliador.isEmpty()) {
			throw new SIMASCNegocioExcepcion(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO350.toString())));
		}

		// Parametros valorPretenciones
		ParametrosGenerales valorSalarioMinimo = this.manejadorParametrosGenerales
				.buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_SALARIO_MINIMO);
		ParametrosGenerales parValorPretenciones = this.manejadorParametrosGenerales
				.buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_VALOR_PRETENCIONES);
		Long parValorPretencionesCalculado = valorSalarioMinimo.getValorNumerico()
				* parValorPretenciones.getValorNumerico();
		// Consultar Caso
		Caso caso = manejadorCaso.buscar(idCaso);
		Long valorPretencionesLong = null;

		if (caso.getValorPretensiones() != null) {
			if (!caso.getValorPretensiones().trim().equals("")) {
				valorPretencionesLong = Long.valueOf(caso.getValorPretensiones());
			}
		}

		if (sedeUltimaAudiencia == null) {
			throw new SIMASCNegocioExcepcion(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO355.toString())));
		}

		else if (audiencia.getIdAudiencia() == null || sedeUltimaAudiencia == null) {
			throw new SIMASCNegocioExcepcion(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO352.toString())));
		}

		// Caso es de Convenio
		else if (caso.getIdServicio().longValue() == UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO.longValue()) {

			// Audiencia Pendiente
			if (audiencia.getEstado().equals(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE)) {

				// Conciliadores Inscritos al Convenio
				List<PersonaBasicaDTO> listaConciliadoresConvenio = manejadorRelacionadoConvenio
						.consultarConciliadoresRelacionadoConvenio(caso.getIdConvenio(), null,
								UtilDominios.ESTADO_CONCILIADOR_ACTIVO);

				conciliadoresCambio = retornarListaConDisponibilidad(listaConciliadoresConvenio, audiencia,
						sedeUltimaAudiencia, idCaso, conciliador);

			} else {

				// Consultar Ultima Audiencia Realizada
				AudienciaDTO audienciaRealizada = manejadorAudiencia.consultarUltimaAudienciaEstadoCaso(idCaso,
						UtilDominios.ESTADO_AUDIENCIA_REALIZADA);

				// Sede Ultima Audiencia Realizada
				Long sedeUltimaAudienciaRealizada = manejadorSala
						.consultarSedeAudiencia(audienciaRealizada.getIdAudiencia(), idCaso);
				// Conciliadores Inscritos al Convenio
				List<PersonaBasicaDTO> listaConciliadoresConvenio = manejadorRelacionadoConvenio
						.consultarConciliadoresRelacionadoConvenio(caso.getIdConvenio(), sedeUltimaAudienciaRealizada,
								UtilDominios.ESTADO_CONCILIADOR_ACTIVO);

				conciliadoresCambio = retornarLista(listaConciliadoresConvenio, idCaso, conciliador);
			}

		}
		// Audiencia Pendiente por Realizar
		else if (audiencia.getEstado().equals(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE)) {

			List<PersonaBasicaDTO> listaConciliadoresSinValidacionDisponibilidad = manejadorPersona
					.consultarConciliadoresAsignadosASedeAudiencia(caso.getIdMateria(),
							conciliador.get(0).getRolPersonaCasoPK().getIdRol(), caso.getTipoCuantia(),
							caso.getIdServicio(), sedeUltimaAudiencia, valorPretencionesLong,
							parValorPretencionesCalculado);

			conciliadoresCambio = retornarListaConDisponibilidad(listaConciliadoresSinValidacionDisponibilidad,
					audiencia, sedeUltimaAudiencia, idCaso, conciliador);

		}

		// Audiencia Diferente a Pendiente por Realizar
		else {

			// Consultar Ultima Audiencia Realizada
			AudienciaDTO audienciaRealizada = manejadorAudiencia.consultarUltimaAudienciaEstadoCaso(idCaso,
					UtilDominios.ESTADO_AUDIENCIA_REALIZADA);

			// Sede Ultima Audiencia Realizada
			Long sedeUltimaAudienciaRealizada = manejadorSala
					.consultarSedeAudiencia(audienciaRealizada.getIdAudiencia(), idCaso);

			List<PersonaBasicaDTO> listaConciliadoresAudienciaRealizada = manejadorPersona
					.consultarConciliadoresAsignadosASedeAudiencia(caso.getIdMateria(),
							conciliador.get(0).getRolPersonaCasoPK().getIdRol(), caso.getTipoCuantia(),
							caso.getIdServicio(), sedeUltimaAudienciaRealizada, valorPretencionesLong,
							parValorPretencionesCalculado);

			conciliadoresCambio = retornarLista(listaConciliadoresAudienciaRealizada, idCaso, conciliador);
		}

		return conciliadoresCambio;
	}

	private List<CambioConciliadorDTO> retornarLista(List<PersonaBasicaDTO> listaConciliadores, Long idCaso,
			List<RolPersonaCaso> conci) {

		List<CambioConciliadorDTO> listaDTOConciliadores = new ArrayList<CambioConciliadorDTO>();

		for (int i = 0; i < listaConciliadores.size(); i++) {

			CambioConciliadorDTO conciliadorValido = new CambioConciliadorDTO();
			conciliadorValido.setIdCaso(idCaso);
			conciliadorValido.setIdConciliador(listaConciliadores.get(i).getIdPersona());
			conciliadorValido.setIdRol(conci.get(0).getRolPersonaCasoPK().getIdRol());

			conciliadorValido.setNombreCompleto(listaConciliadores.get(i).getNombreCompleto());

			listaDTOConciliadores.add(conciliadorValido);

		}

		return listaDTOConciliadores;
	}

	private List<CambioConciliadorDTO> retornarListaConDisponibilidad(List<PersonaBasicaDTO> listaConciliadores,
			AudienciaDTO audiencia, Long sedeUltimaAudiencia, Long idCaso, List<RolPersonaCaso> conci) {

		List<CambioConciliadorDTO> listaDTOConciliadores = new ArrayList<CambioConciliadorDTO>();

		for (int i = 0; i < listaConciliadores.size(); i++) {

			if (agendamientoFacade.validarDisponibilidadAgenda(listaConciliadores.get(i).getIdPersona(),
					audiencia.getHoraInicio(), audiencia.getHoraInicio(), audiencia.getHoraFin(),
					sedeUltimaAudiencia)) {

				CambioConciliadorDTO conciliadorValido = new CambioConciliadorDTO();
				conciliadorValido.setIdCaso(idCaso);
				conciliadorValido.setIdConciliador(listaConciliadores.get(i).getIdPersona());
				conciliadorValido.setIdRol(conci.get(0).getRolPersonaCasoPK().getIdRol());

				conciliadorValido.setNombreCompleto(listaConciliadores.get(i).getNombreCompleto());

				listaDTOConciliadores.add(conciliadorValido);

			}
		}

		return listaDTOConciliadores;
	}

	@Override
	public List<PersonaBasicaDTO> obtenerPersonaBasicaConciliadorCasoEstadoNombramiento(Long idCaso,
			List<String> estados, String tipoNombramiento) {
		List<RolPersonaCaso> conciliadoresRPC = manejadorRolPersonaCaso
				.consultaConciliadoresCasoEstadoNombramiento(idCaso, estados, tipoNombramiento);
		List<Persona> conciliadores = new ArrayList<Persona>();
		for (RolPersonaCaso conciliadorRPC : conciliadoresRPC) {
			conciliadores.add(conciliadorRPC.getPersona());
		}

		return (List<PersonaBasicaDTO>) PersonaBasicaDTO.newListaPersonaBasicaDTO(conciliadores);
	}

	@Override
	public List<PartesSeguimientoDTO> consultarPartesSeguimiento(Long idCaso) {
		return manejadorRolPersonaCaso.consultarPartesSeguimiento(idCaso);
	}

	@Override
	public List<RolPersonaCasoDTO> consultarCasosPersonaRolEstadoTipo(Long idPersona, List<String> nombreRol,
			List<String> estados, List<String> tipoNombramientos) {
		List<RolPersonaCaso> casosPersona = manejadorRolPersonaCaso.consultarCasosPersonaRolEstadoTipo(idPersona,
				nombreRol, estados, tipoNombramientos);
		return (List<RolPersonaCasoDTO>) new RolPersonaCasoDTO().transformarColeccionSinDependencias(casosPersona);
	}

	@Override
	public void reasignarAnalistaControlLegalidad(Long idCaso, Long idPersona, Date fechaLimiteEstudio) {
		// rol analista de conciliacion
		List<String> roles = Arrays.asList(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION);
		// estado asignacion
		List<String> estados = Arrays.asList(UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);

		// consultar analista actualmente asignado al caso
		List<RolPersonaCaso> analista = manejadorRolPersonaCaso.consultarPersonasoPorRoleEstado(idCaso, roles, estados,
				true);

		Rol rolAnalista = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION);

		if (analista != null && !analista.isEmpty()) {

			// Valida si existe un registro del analista a reasignar en el caso
			if (idPersona != null && manejadorRolPersonaCaso.validarPersonaCaso(idPersona, idCaso,
					analista.get(0).getRol().getIdRol(), true)) {
				throw new SIMASCNegocioExcepcion(
						String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO307.toString())));
			} else {

				// inactiva al analista actualmente asignado.
				this.eliminarRolPersonasCasoPorId(analista.get(0).getCaso().getIdCaso(),
						analista.get(0).getPersona().getIdPersona(), UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION);
			}
		}
		// reasigna el analista seleccionado en el caso
		crearAsignacionFuncionario(idPersona, idCaso, rolAnalista.getIdRol(), null, null,
				UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);

		// realizar la notificacion
		notificarAnalistaReasignado(idCaso, idPersona, fechaLimiteEstudio);
	}

	/**
	 * Asigna el analista a un caso.
	 * 
	 * @param idPersona: persona a asignar.
	 * @param idCaso:    identificador del caso.
	 * @param idRol:     rol de la persona a asignar.
	 */
	private void crearAsignacionFuncionario(Long idPersona, Long idCaso, Long idRol, String metodoNombramiento,
			String tipoNombramiento, String estado) {
		RolPersonaCasoPK rpcPk = new RolPersonaCasoPK();
		rpcPk.setIdCaso(idCaso);
		rpcPk.setIdPersona(idPersona);
		rpcPk.setIdRol(idRol);

		RolPersonaCaso rpc = manejadorRolPersonaCaso.buscar(rpcPk);
		if (rpc == null) {
			rpc = new RolPersonaCaso();
			rpc.setRolPersonaCasoPK(rpcPk);
		}
		rpc.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		rpc.setFechaUltimaModificacion(new Date());
		rpc.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		rpc.setEsPresidente(Boolean.FALSE);
		rpc.setMetodoNombramiento(metodoNombramiento);
		rpc.setTipoNombramiento(tipoNombramiento);
		rpc.setEstado(estado);

		manejadorRolPersonaCaso.actualizar(rpc);
	}

	/**
	 * Realiza la notificacion al analista reasignado para control de legalidad.
	 */
	private void notificarAnalistaReasignado(Long idCaso, Long idPersona, Date fechaLimiteEstudio) {
		Caso caso = manejadorCaso.buscar(idCaso);
		String asunto = UtilConstantes.ASIGNACION_CASO_CONTROL_LEGALIDAD;
		StringBuilder textoCorreo = new StringBuilder();
		List<String> args = new ArrayList<>();
		args.add(String.valueOf(caso.getIdCaso()));
		args.add(caso.getNombre());
		if (fechaLimiteEstudio != null) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");
			args.add(dateFormatter.format(fechaLimiteEstudio));
		}
		textoCorreo.append(String.format(
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO308.toString()), args.toArray()));
		List<String> lstCuerpo = new ArrayList<>();
		lstCuerpo.add(textoCorreo.toString());
		List<Persona> lstPersonas = new ArrayList<Persona>();
		lstPersonas.add(manejadorPersona.buscar(idPersona));
		// Envia correo
		correoRolPersonaCasoFacade.envioDeCorreo(asunto, lstPersonas, null, null, lstCuerpo, caso.getIdCaso(), null,
				null, Boolean.FALSE);
	}

	@Override
	public List<CasoAsignadoDTO> consultarVinculacionClienteCaso(PersonaDTO cliente) {
		return manejadorRolPersonaCaso.consultarVinculacionClienteCaso(cliente);
	}

	/**
	 * Metodo que permite crear la asignacion por centro de los casos registrados
	 * con resultado acuerdo a los auxiliares administrativos. Se realiza ajuste de
	 * auxiliar por secretaria de conciliación
	 */
	public List<InfoBasicaAlertasDTO> crearAsignacionCasosAuxiliaresADM() throws SIMASCNegocioExcepcion {
		List<CentroDTO> centros = centroFacade.obtenerCentros();
		List<String> estadosCasos = Arrays.asList(UtilDominios.ESTADO_CASO_REGISTRADO);
		List<String> tipoEventos = Arrays.asList(UtilDominios.TIPO_EVENTO_CASO_REGISTRADO);
		List<String> roles = Arrays.asList(UtilDominios.ROL_PERSONA_SECRETARIA_DE_CONCILIACION);
		List<InfoBasicaAlertasDTO> infoCasosAsignados = new ArrayList<InfoBasicaAlertasDTO>();
		for (CentroDTO centro : centros) {
			Long idCentro = centro.getIdCentro();
			List<CasoDTO> casos = manejadorCaso.obtenerCasosCerrados(UtilDominios.RESULTADO_CASO_CONCILIACION_ACUERDO,
					idCentro, estadosCasos, tipoEventos, roles);
			if (!casos.isEmpty()) {
				List<Long> centroFiltro = new ArrayList<Long>(Arrays.asList(idCentro));
				List<PersonaAleatoriaDTO> auxiliares = manejadorRolPersona.consultarPersonasAleatorioPorCentro(roles,
						centroFiltro, new Date());
				if (!auxiliares.isEmpty()) {
					crearAsignacionCasos(auxiliares, casos, idCentro, infoCasosAsignados);
				} else {
					crearEventoCasos(casos);
				}

			}
		}
		return infoCasosAsignados;

	}

	/**
	 * Metodo que permite crear la asignacion de los casos equitativo a los
	 * auxiliares administrativos.
	 * 
	 * @param auxiliares: Auxiliares sin asignacion.
	 * @param casos:      Casos a asignar.
	 * @param idCentro:   Centro de los casos.
	 * @return List<InfoBasicaAlertasDTO>: Lista de informacion basica de alertas.
	 */
	private void crearAsignacionCasos(List<PersonaAleatoriaDTO> auxiliares, List<CasoDTO> casos, Long idCentro,
			List<InfoBasicaAlertasDTO> infoCasosAsignados) {
		List<CasoDTO> casosToAsignar = new ArrayList<CasoDTO>(casos);
		while (!casosToAsignar.isEmpty()) {
			for (PersonaAleatoriaDTO auxiliar : auxiliares) {
				if (!casosToAsignar.isEmpty()) {
					CasoDTO caso = casosToAsignar.get(0);
					crearAsignacionFuncionario(auxiliar.getIdPersona(), caso.getIdCaso(), auxiliar.getIdRol(), null,
							null, UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);
					InfoBasicaAlertasDTO infoCasoAsignado = crearInfoCasoAsignado(caso, auxiliar, idCentro);
					infoCasosAsignados.add(infoCasoAsignado);
					casosToAsignar.remove(0);
				} else {
					break;
				}
			}
		}
	}

	/**
	 * Metodo que permite crear la informacion basica del caso asignado.
	 * 
	 * @param caso:     Caso asignado.
	 * @param auxiliar: Auxiliar con asignacion.
	 * @param idCentro: Centro del caso.
	 * @return InfoBasicaAlertasDTO.
	 */
	private InfoBasicaAlertasDTO crearInfoCasoAsignado(CasoDTO caso, PersonaAleatoriaDTO auxiliar, Long idCentro) {
		InfoBasicaAlertasDTO infoCasoAsignado = new InfoBasicaAlertasDTO();
		infoCasoAsignado.setIdCaso(caso.getIdCaso());
		infoCasoAsignado.setNombreCaso(caso.getNombre());
		infoCasoAsignado.setIdPersona(auxiliar.getIdPersona());
		infoCasoAsignado.setNombrePersona(auxiliar.getNombrePersona());
		infoCasoAsignado.setIdCentro(idCentro);
		return infoCasoAsignado;
	}

	/**
	 * Metodo que permite crear el evento de los casos cuando no se puede realizar
	 * el reparto porque no hay auxiliares.
	 * 
	 * @param casos: Lista de casos que no se pudieron repartir.
	 */
	private void crearEventoCasos(List<CasoDTO> casos) {
		String observaciones = UtilConstantes.CASO_SIN_ASIGNACION_DE_SECRETARIA_CONCILIACION;
		for (CasoDTO casoDTO : casos) {
			Caso caso = new Caso();
			caso.setIdCaso(casoDTO.getIdCaso());
			caso.setNombre(casoDTO.getNombre());
			eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_CASO_SIN_ASIGNACION, observaciones,
					UtilConstantes.USUARIO_DEFECTO_SIMASC, new Date(), UtilDominios.ESTADO_REGISTRO_ACTIVO);
		}
	}

	@Override
	public Boolean inactivacionSegunMotivo(Long idPersona, String Motivo, Long idRol, Long idServicio) {
		Boolean inactivacion = false;
		EstadoPersonaRolPK pId = new EstadoPersonaRolPK();
		pId.setIdServicio(idServicio);
		pId.setIdRol(idRol);
		pId.setIdPersona(idPersona);
		EstadoPersonaRol estadoPersonaRol = manejadorEstadoPersonaRol.buscar(pId);
		if (estadoPersonaRol != null) {
			estadoPersonaRol.setIdMotivo(Motivo);
			estadoPersonaRol.setFechaUltimaModificacion(new Date());
			estadoPersonaRol.setEstadoRegistro(UtilDominios.ESTADO_PERSONA_ACTIVO);
			estadoPersonaRol.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);	
			manejadorEstadoPersonaRol.actualizar(estadoPersonaRol);
			// Generamos un Historico.
			manejadorHistoricoEstadoPersonaRol.crearHistoricoEstadoPersonaServicio(idPersona, idRol, Motivo,
					new Date(), idServicio);
			inactivacion = true;
		} else {
			estadoPersonaRol = new EstadoPersonaRol();
			estadoPersonaRol.setEstadoPersonaRolPK(pId);
			estadoPersonaRol.setIdMotivo(Motivo);
			estadoPersonaRol.setFechaUltimaModificacion(new Date());
			estadoPersonaRol.setEstadoRegistro(UtilDominios.ESTADO_PERSONA_ACTIVO);
			estadoPersonaRol.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			manejadorEstadoPersonaRol.crear(estadoPersonaRol);
			// Generamos un Historico.
			manejadorHistoricoEstadoPersonaRol.crearHistoricoEstadoPersonaServicio(idPersona, idRol, Motivo,
					new Date(), idServicio);
			inactivacion = true;
		}
		return inactivacion;
	}

	/**
	 * verifica la existencia de una persona en un caso dada su idPersona y el
	 * idCaso
	 * 
	 * @param idPersona
	 * @param idCaso
	 * @return
	 */
	@Override
	public boolean personaExisteEnCaso(Long idPersona, Long idCaso) {
		try {
			RolPersonaCaso rpc = manejadorRolPersonaCaso.consultaRolPersonaId(idPersona, idCaso, null);
			return (rpc != null && rpc.getRolPersonaCasoPK() != null);
		} catch (Exception e) {
			return false;
		}

	}

	// protected region metodos adicionales end

	@Override
	public List<RolPersonaCaso> consultarPartesCaso(Long idCaso) {
		return manejadorRolPersonaCaso.obtenerPersonasAsociadasACaso(idCaso);
	}

	@Override
	public RolPersonaCaso consultaPersonaAsignadaCaso(Long idPersona, Long idCaso) {
		return manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(idPersona, idCaso);
	}

	public void liberarSuplentes(Long idCaso) {
		List<RolPersonaCaso> rpc = manejadorRolPersonaCaso.consultarArbitros(idCaso,
				UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE, null, null);
		for (RolPersonaCaso rolPersona : rpc) {
			if(manejadorRolPersonaCaso.ultimoCasoDesignadoSuplente(rolPersona.getRolPersonaCasoPK().getIdPersona(), idCaso)){
				this.habilitarArbitro(idCaso, rolPersona.getRolPersonaCasoPK().getIdPersona(),
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO055.toString()));
			}			
		}
	}

	@Override
	public void repartoInsolvencia(Caso caso) {

		PersonaServicioMateria personaServicioMateria = manejadorPersonaServicioMateria
				.consultaPersonServicioMateriaByIdServicioAndNombreRol(
						UtilDominios.ROL_PERSONA_CONCILIADOR_EN_INSOLVENCIA, caso.getIdServicio());
		
		String asunto = UtilConstantes.DESIGNACION + UtilConstantes.CARACTER_ESPACIO + caso.getIdCaso();

		Rol rol = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_CONCILIADOR_EN_INSOLVENCIA);
		
		Persona persona = manejadorPersona.buscar(personaServicioMateria.getIdPersona());		
		
		RolPersonaCaso rolPersonaCaso = saveRPC(caso, rol, persona, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);

		registrarEventoPrestadorDeServicio(caso, rol, rolPersonaCaso);
		registrarEventoRolPersonaCaso(rolPersonaCaso, UtilConstantes.USUARIO_DEFECTO_SIMASC, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR,	null);

		notificacionFacade.notificaUsuarioPlantilla(
				UtilDominios.NOMBRE_PLANTILLA_CARTA_NOTIFICACION_ASIGNACION_CONCILIADOR, rolPersonaCaso, asunto);

		if (personaServicioMateria.getCantidadCasosAsignados() != null) {
			personaServicioMateria.setCantidadCasosAsignados(personaServicioMateria.getCantidadCasosAsignados() + 1);
		} else {
			personaServicioMateria.setCantidadCasosAsignados(Long.valueOf(1));
		}

		manejadorPersonaServicioMateria.actualizar(personaServicioMateria);
		
		caso.setEstadoCaso(UtilDominios.ESTADO_CASO_CONCILIADOR_DESIGNADO);
		
		manejadorCaso.actualizar(caso);

		manejadorProgramacionAlerta.crearProgramacionesAceptacionConciliacion(caso.getIdCaso(), persona.getIdPersona(),
				UtilDominios.NOMBRAMIENTO_POR_LA_CCB);

	}

}
