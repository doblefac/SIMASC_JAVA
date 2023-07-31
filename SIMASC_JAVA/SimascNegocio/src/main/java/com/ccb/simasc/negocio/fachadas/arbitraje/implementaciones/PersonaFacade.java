package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

//Escriba en esta sección sus modificaciones

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.comun.fachada.interfaces.IIntegracionSWFacade;
import com.ccb.simasc.comun.seguridad.fachada.interfaces.ISeguridadFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorApoderados;
import com.ccb.simasc.integracion.manejadores.ManejadorApoderadosSolicitud;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.ManejadorEstadoPersonaRol;
import com.ccb.simasc.integracion.manejadores.ManejadorEstadoPersonaTipoServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoEstadoPersonaRol;
import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoEstadoPersonaTipoServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorIdioma;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroServicioSorteo;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaServicioMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaSolicitud;
import com.ccb.simasc.integracion.manejadores.ManejadorPreseleccionado;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorSolicitudServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefono;
import com.ccb.simasc.integracion.manejadores.ManejadorTipoDeServicioRol;
import com.ccb.simasc.integracion.manejadores.ManejadorUbicacion;
import com.ccb.simasc.integracion.manejadores.ManejadorUsuario;
import com.ccb.simasc.integracion.manejadores.ManejadorZonaGeografica;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IApoderadosFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IArbitroCasoLiberacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoPersonaSolicitudFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEstadoPersonaRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroDeServicioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPreseleccionadoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITelefonoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionPersonaSolicitudFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUsuarioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IZonaGeograficaFacade;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoEnmascaradoDTO;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.DominioEnmascaradoDTO;
import com.ccb.simasc.transversal.dto.DominioPKDTO;
import com.ccb.simasc.transversal.dto.FiltosPreseleccionArbitros;
import com.ccb.simasc.transversal.dto.FiltrosPreseleccionArbitrosCCB;
import com.ccb.simasc.transversal.dto.InformacionPrestadorDTO;
import com.ccb.simasc.transversal.dto.ParametroDeServicioDTO;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.PersonaMateriaAsignadaDTO;
import com.ccb.simasc.transversal.dto.PreferenciasDTO;
import com.ccb.simasc.transversal.dto.PreseleccionadoDTO;
import com.ccb.simasc.transversal.dto.PreseleccionadoDesignadoDTO;
import com.ccb.simasc.transversal.dto.TelefonoDTO;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.formularios.BusquedaRolesActivosDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosBasicosCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.DetalleArbitroDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltrosSeleccionConciliadorDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioDatosClienteDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteEnmascaradoDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.dto.formularios.ParteCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.dto.formularios.RadicacionSolicitudDTO;
import com.ccb.simasc.transversal.dto.formularios.RadicacionSolicitudEnmascaradoDTO;
import com.ccb.simasc.transversal.entidades.Apoderados;
import com.ccb.simasc.transversal.entidades.ApoderadosPK;
import com.ccb.simasc.transversal.entidades.ApoderadosSolicitud;
import com.ccb.simasc.transversal.entidades.ApoderadosSolicitudPK;
import com.ccb.simasc.transversal.entidades.ArbitroCasoLiberacion;
import com.ccb.simasc.transversal.entidades.ArbitroCasoLiberacionPK;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.CorreoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRol;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRolPK;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.PersonaSolicitudPK;
import com.ccb.simasc.transversal.entidades.Preseleccionado;
import com.ccb.simasc.transversal.entidades.PreseleccionadoPK;
import com.ccb.simasc.transversal.entidades.Profesion;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.entidades.SolicitudServicio;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.entidades.UbicacionRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilMascaraTexto;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;
// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Persona}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PersonaFacade extends AccesoFacade<Persona, Long, PersonaDTO> implements IPersonaFacade {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	private static final Logger logger = LogManager.getLogger(ManejadorCrud.class.getName());

	@EJB
	private ManejadorRolPersona manejadorRolPersona;

	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorCorreoElectronico manejadorCorreo;

	@EJB
	private ManejadorHistoricoEstadoPersonaRol manejadorHistoricoEstadoPersonaRol;

	@EJB
	private ManejadorEstadoPersonaRol manejadorEstadoPersonaRol;

	@EJB
	private ManejadorUbicacion manejadorUbicacion;

	@EJB
	private ManejadorTelefono manejadorTelefono;

	@EJB
	private ManejadorParametroServicioSorteo manejadorParametroServicioSorteo;

	@EJB
	private ManejadorPersonaSolicitud manejadorPersonaSolicitud;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorPreseleccionado manejadorPreseleccionado;

	@EJB
	private ManejadorCorreoRolPersonaCaso manejadorCorreoRolPersonaCaso;

	@EJB
	private ManejadorIdioma manejadorIdioma;

	@EJB
	private ManejadorZonaGeografica manejadorZonaGeografica;

	@EJB
	private ManejadorUsuario manejadorUsuario;

	@EJB
	private ManejadorEstadoPersonaTipoServicio manejadorEstadoPersonaTipoServicio;

	@EJB
	private ManejadorHistoricoEstadoPersonaTipoServicio manejadorHistoricoEstadoPersonaTipoServicio;

	@EJB
	private ManejadorTipoDeServicioRol manejadorTipoDeServicioRol;

	@EJB
	private ManejadorApoderados manejadorApoderados;

	@EJB
	private ManejadorApoderadosSolicitud manejadorApoderadosSolicitud;

	@EJB
	private ManejadorPersonaSolicitud manejadorSolicitudPersona;

	@EJB
	private ManejadorServicio manejadorServicio;

	@EJB
	private ManejadorDominio manejadorDominio;

	@EJB
	private ManejadorSolicitudServicio manejadorSolicitudServicio;

	@EJB
	private ManejadorPersonaServicioMateria manejadorPersonaServicioMateria;

	@EJB
	private IEstadoPersonaRolFacade estadoPersonaRolFacade;

	@EJB
	private IDominioFacade dominioFacade;

	@EJB
	private IZonaGeograficaFacade zonaGeograficaFacade;

	@EJB
	private IUbicacionFacade ubicacionFacade;

	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	@EJB
	private ITelefonoFacade telefonoFacade;

	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;

	@EJB
	private IUsuarioFacade usuarioFacade;

	@EJB
	private ISeguridadFacade seguridadFacade;

	@EJB
	private IIntegracionSWFacade integracionSWFacade;

	@EJB
	private IApoderadosFacade apoderadosFacade;

	@EJB
	private ApoderadosSolicitudFacade apoderadosSolicitudFacade;

	@EJB
	private ICorreoElectronicoRolPersonaCasoFacade correoElectronicoRolPersonaCasoFacade;

	@EJB
	private ICorreoElectronicoPersonaSolicitudFacade correoElectronicoPersonaSolicitudFacade;

	@EJB
	private IUbicacionRolPersonaCasoFacade ubicacionRolPersonaCasoFacade;

	@EJB
	private IUbicacionPersonaSolicitudFacade ubicacionPersonaSolicitudFacade;

	@EJB
	private AgrupamientoRolFacade agrupamientoRolFacade;

	@EJB
	private IParametroDeServicioFacade parametroDeServicioFacade;

	@EJB
	private PersonaServicioMateriaFacade personaServicioMateriaFacade;

	@EJB
	private IRolPersonaFacade rolPersonaFacade;

	@EJB
	private IArbitroCasoLiberacionFacade arbitroCasoLiberacionFacade;

	@EJB
	private IPreseleccionadoFacade preseleccionadoFacade;

	@EJB
	private IAudienciaFacade audienciaFacade;

	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;

	@PersistenceContext
	private EntityManager em;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPersona;
	}

	@Override
	public PersonaDTO transformarSinDependencias(Persona obj) {
		PersonaDTO dto = new PersonaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public PersonaDTO transformarConDependencias(Persona obj) {
		PersonaDTO dto = new PersonaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Persona transformarEntidadConDependencias(Persona obj) {
		return new PersonaDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Persona transformarEntidadSinDependencias(Persona obj) {
		if (obj != null) {
			return new PersonaDTO().transformarEntidadSinDependencias(obj);
		}
		return obj;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	@Override
	public List<Persona> consultarPersonasPorRol(String nombreRol) {
		List<Persona> personas = new ArrayList<Persona>();
		List<Persona> coleccion = manejadorPersona.consultarPersonasPorRol(nombreRol);
		return (List<Persona>) transformarEntidadesColeccionSinDependencias(coleccion, personas);
	}

	@Override
	public List<PersonaBasicaDTO> consultarPersonasBasicaPorRol(String nombreRol) {
		List<PersonaBasicaDTO> lstPersonaBasica = new ArrayList<>();
		List<Persona> lstPersona = (List<Persona>) transformarEntidadesColeccionSinDependencias(
				manejadorPersona.consultarPersonasPorRol(nombreRol), new ArrayList<Persona>());
		for (Persona persona : lstPersona) {
			PersonaBasicaDTO personaBasica = new PersonaBasicaDTO();
			personaBasica.setIdPersona(persona.getIdPersona());
			personaBasica.setNombreCompleto(persona.getNombreCompleto());
			lstPersonaBasica.add(personaBasica);
		}
		return lstPersonaBasica;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade#
	 * consultarAbogadosPorServicio(java.lang.Long)
	 */
	@Override
	public List<PersonaBasicaDTO> consultarAbogadosPorServicio(Long idServicio) {
		List<PersonaBasicaDTO> lstPersonaBasica = new ArrayList<>();
		List<Persona> lstPersona = (List<Persona>) transformarEntidadesColeccionSinDependencias(
				manejadorPersona.consultarAbogadosPorServicio(idServicio), new ArrayList<Persona>());
		for (Persona persona : lstPersona) {
			PersonaBasicaDTO personaBasica = new PersonaBasicaDTO();
			personaBasica.setIdPersona(persona.getIdPersona());
			personaBasica.setNombreCompleto(persona.getNombreCompleto());
			lstPersonaBasica.add(personaBasica);
		}
		return lstPersonaBasica;
	}

	@Override
	public List<GenericoDTO> consultarArbitrosPreseleccion(String tipoServicio) {
		List<GenericoDTO> genericoList = new ArrayList<GenericoDTO>();
		try {
			List<Persona> personas = manejadorPersona.consultarArbitrosPreseleccion(tipoServicio);
			for (Persona it : personas) {
				GenericoDTO genericoDTO = new GenericoDTO();
				genericoDTO.setId(it.getIdPersona().toString());
				genericoDTO.setNombre(it.getNombreCompleto());
				genericoList.add(genericoDTO);
			}
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		return genericoList;
	}

	@Override
	public void adicionarTerceroOAutoridad(Persona persona, Long idCaso, String nombreRol) {
		Persona datosPersona = persona;
		Date date = new Date();
		Rol rol = manejadorRol.consultarRolPorNombre(nombreRol);
		// Consulta el caso por el id del caso
		Caso caso = manejadorCaso.buscar(idCaso);
		// Validacion que se realiza por si el tercero es nuevo o existente
		if (datosPersona.getIdPersona() != null)
			datosPersona = manejadorPersona.buscar(datosPersona.getIdPersona());

		// Adiciona persona nueva en la tabla persona
		if (nombreRol.equals(UtilDominios.ROL_PERSONA_TERCERO))
			datosPersona.setTipoPersona(UtilDominios.TIPO_PERSONA_NATURAL);
		else
			datosPersona.setTipoPersona(UtilDominios.TIPO_PERSONA_JURIDICO);
		if (datosPersona.getIdPersona() == null) {
			List<CorreoElectronico> lstCorreoElectronico = (datosPersona.getCorreoElectronicoList() != null
					? datosPersona.getCorreoElectronicoList()
					: new ArrayList<CorreoElectronico>());
			List<Telefono> lsTelefono = (datosPersona.getTelefonoList() != null ? datosPersona.getTelefonoList()
					: new ArrayList<Telefono>());
			datosPersona.setPrimerApellido(null);
			datosPersona.setSegundoApellido(null);
			datosPersona.setUbicacionList(null);
			datosPersona.setTelefonoList(null);
			datosPersona.setCorreoElectronicoList(null);
			datosPersona.setEstadoPersona(UtilDominios.ESTADO_PERSONA_ACTIVO);
			datosPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			datosPersona.setFechaUltimaModificacion(new Date());
			datosPersona.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			datosPersona = manejadorPersona.crearPersona(datosPersona);
			datosPersona.setTipoFuncionario(UtilDominios.PERSONA_FUNCIONARIO_NO_FUNCIONARIO);
			int i = 0;
			for (CorreoElectronico correoElectronico : lstCorreoElectronico) {
				CorreoElectronico correoExiste = manejadorCorreo
						.buscarPorDireccionPersona(correoElectronico.getDireccion(), null);
				if (correoExiste != null)
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO102.toString()));
				correoElectronico.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				correoElectronico.setFechaUltimaModificacion(new Date());
				correoElectronico.setIdPersona(datosPersona.getIdPersona());
				correoElectronico.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
				correoElectronico.setPersona(datosPersona);
				if (i < 1)
					correoElectronico.setTipo(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
				else
					correoElectronico.setTipo(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO);
				manejadorCorreo.crear(correoElectronico);
				i++;
			}

			for (Telefono telefono : lsTelefono) {
				telefono.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				telefono.setFechaUltimaModificacion(new Date());
				telefono.setIdPersona(datosPersona.getIdPersona());
				telefono.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
				telefono.setPersona(datosPersona);
				telefono.setTipoTelefono(UtilDominios.TIPO_TELEFONO_CELULAR);
				manejadorTelefono.crear(telefono);
			}
			// Adiciona el rol de la persona en la tabla rolPersona
			RolPersona rolPersona = new RolPersona();
			rolPersona.setPersona(datosPersona);
			rolPersona.setRol(rol);
			rolPersona.setIdRol(rol.getIdRol());
			rolPersona.setIdPersona(datosPersona.getIdPersona());
			rolPersona.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			rolPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			rolPersona.setFechaUltimaModificacion(new Timestamp(date.getTime()));
			rolPersona.setFechaInicioVigencia(new Timestamp(date.getTime()));
			rolPersona.setIdCentro(1L);
			manejadorRolPersona.crearRolPersona(rolPersona);
		} else {
			if (manejadorRolPersonaCaso.validaPersonaExisteCaso(datosPersona.getIdPersona(), idCaso, rol.getIdRol())) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO103.toString()));
			}
		}
		// Adiciona el rol de la persona al caso en la tabla rolPersonaCaso
		RolPersonaCaso rolPersonaCaso = new RolPersonaCaso();
		rolPersonaCaso.setCaso(caso);
		rolPersonaCaso.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		rolPersonaCaso.setRol(rol);
		rolPersonaCaso.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		rolPersonaCaso.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
		rolPersonaCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		rolPersonaCaso.setPersona(datosPersona);
		manejadorRolPersonaCaso.crearRolPersonaCaso(rolPersonaCaso);
	}

	@Override
	public void adicionarArbitroCaso(List<Persona> personas, Long idCaso, String nombreRol) {
		java.util.Date date = new java.util.Date();
		Rol rol = manejadorRol.consultarRolPorNombre(nombreRol);
		// Consulta el caso por el id del caso
		Caso caso = manejadorCaso.buscar(idCaso);
		// Adiciona el rol de la persona al caso en la tabla rolPersonaCaso
		RolPersonaCaso rolPersonaCaso = new RolPersonaCaso();
		rolPersonaCaso.setCaso(caso);
		rolPersonaCaso.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		rolPersonaCaso.setRol(rol);
		rolPersonaCaso.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);// Se
																						// quema
																						// el
																						// usuario
																						// que
		// modifica el registro
		rolPersonaCaso.setFechaUltimaModificacion(new Timestamp(date.getTime()));
		rolPersonaCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);

		for (Persona persona : personas) {
			rolPersonaCaso.setPersona(persona);
			manejadorRolPersonaCaso.crearRolPersonaCaso(rolPersonaCaso);
		}

	}

	@Override
	public Collection<Persona> consultarPorRolCaso(Long idCaso, String nombreRol) {
		PersonaDTO dto = new PersonaDTO();
		return dto.transformarColeccionEntidadesConDependencias(
				manejadorRolPersonaCaso.consultarPersonasPorRolCaso(idCaso, nombreRol));
	}

	@Override
	public FormularioParteDTO consultarPorIdPersonaPorCaso(Long idPersona, Long idCaso) {
		FormularioParteDTO parteDTO = new FormularioParteDTO();

		try {
			Persona persona = manejadorPersona.getConsultarPartePoridPersonaPorCaso(idPersona, idCaso);
			parteDTO.setIdCaso(idCaso);
			String nombreRol = null;
			Long idRol = null;
			List<CorreoElectronicoRolPersonaCaso> correosRolPersonaCaso = null;
			List<UbicacionRolPersonaCaso> ubicacionesRolPersonaCaso = null;

			Dominio dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_PERSONA,
					persona.getTipoPersona());
			parteDTO.setTipoPersona(dominio);

			if (!persona.getRolPersonaCasoList().isEmpty()) {
				for (RolPersonaCaso it : persona.getRolPersonaCasoList()) {
					if ((it.getCaso().getIdCaso().compareTo(idCaso) == 0)
							&& it.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
						nombreRol = it.getRol().getNombre();
						idRol = it.getRol().getIdRol();
						correosRolPersonaCaso = it.getCorreoElectronicoRolPersonaCasoList();
						ubicacionesRolPersonaCaso = it.getUbicacionRolPersonaCasoList();
					}
				}

				dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ROL_PERSONA, nombreRol);
				parteDTO.setRol(dominio);
			}

			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA,
					persona.getTipoDocumento());
			parteDTO.setTipoIdentificacion(dominio);
			parteDTO.setNumeroIdentificacion(persona.getNumeroDocumento());
			ZonaGeografica zonaGeografica = new ZonaGeografica();
			if (persona.getCiudadDelDocumento() != null)
				zonaGeografica = zonaGeograficaFacade.buscar(persona.getCiudadDelDocumento());
			if (zonaGeografica != null)
				parteDTO.setCiudadIdentificacion(
						zonaGeograficaFacade.transformarEntidadSinDependencias(zonaGeografica));
			parteDTO.setNacionalidad(persona.getIdPaisOrigen());
			parteDTO.setPrimerNombre(persona.getPrimerNombreORazonSocial());
			parteDTO.setSegundoNombre(persona.getSegundoNombre());
			parteDTO.setPrimerApellidoORazonSocial(persona.getPrimerApellido());
			parteDTO.setSegundoApellido(persona.getSegundoApellido());
			parteDTO.setIdPersona(persona.getIdPersona());

			/*
			 * Consultar Ubicaciones
			 */

			List<Ubicacion> ubicacionesPersona = new ArrayList<Ubicacion>();

			// Ubicación Principal
			for (Ubicacion ubicacion : persona.getUbicacionList()) {

				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(ubicacion.getEstadoRegistro())
						&& UtilDominios.TIPO_UBICACION_PRINCIPAL.equals(ubicacion.getTipo())) {
					ubicacionesPersona.add(ubicacion);
					break;
				}
			}

			// Ubicaciones Secundarias :: Obtenidas desde la asociación con Rol Persona Caso
			if (ubicacionesRolPersonaCaso != null && ubicacionesRolPersonaCaso.size() > 0) {
				for (UbicacionRolPersonaCaso ubicacionRPC : ubicacionesRolPersonaCaso) {
					ubicacionesPersona.add(ubicacionRPC.getUbicacion());
				}
			}

			// Transformar ubicaciones a DTO
			List<UbicacionDTO> lstUbicacionDTO = new ArrayList<>();

			for (Ubicacion it : ubicacionesPersona) {
				UbicacionDTO ubicacionDTO = new UbicacionDTO();
				ubicacionDTO.setIdUbicacion(it.getIdUbicacion());
				ubicacionDTO.setDireccion(it.getDireccion());
				ubicacionDTO.setIdZonaGeografica(it.getIdZonaGeografica());
				ZonaGeografica ciudad = manejadorZonaGeografica.buscar(it.getIdZonaGeografica());
				ZonaGeografica pais = manejadorZonaGeografica.buscar(ciudad.getIdZonaGeograficaPadre());
				ubicacionDTO.setCiudad(ciudad);
				ubicacionDTO.setPais(pais);
				ubicacionDTO.setTipo(it.getTipo());
				lstUbicacionDTO.add(ubicacionDTO);
			}

			// Setear ubicaciones de la persona a los datos del formulario
			parteDTO.setLstUbicacion(lstUbicacionDTO);

			/*
			 * Telefonos
			 */

			int i = 0;

			for (Telefono it2 : telefonoFacade.transformarEntidadesColeccionSinDependencias(persona.getTelefonoList(),
					new ArrayList<Telefono>())) {

				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(it2.getEstadoRegistro())) {

					if (it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_CELULAR)) {
						parteDTO.setNumeroCelular(it2.getNumero());
					}
					if (i == 0 && it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
						parteDTO.setNumeroTelefonoUno(it2.getNumero());
						i++;
					} else if (i == 1 && it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
						parteDTO.setNumeroTelefonoDos(it2.getNumero());
						i++;
					}

				}

			}

			/*
			 * Correos Electronicos
			 */

			// Correo Principal
			for (CorreoElectronico it3 : correoElectronicoFacade.transformarEntidadesColeccionSinDependencias(
					persona.getCorreoElectronicoList(), new ArrayList<CorreoElectronico>())) {

				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(it3.getEstadoRegistro())
						&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL.equals(it3.getTipo())) {
					parteDTO.setEmailUno(it3.getDireccion());
					parteDTO.setEmailUnoAnterior(it3.getDireccion());
					break;
				}
			}

			// Correos Secundario y Terciario :: Obtenidos desde la asociación con Rol
			// Persona Caso
			if (correosRolPersonaCaso != null && correosRolPersonaCaso.size() > 0) {
				// CorreoElectronico correoElectronico =
				// correosRolPersonaCaso.get(0).getCorreoElectronico();
				//
				// if(correoElectronico != null) {
				// parteDTO.setEmailDos(correoElectronico.getDireccion());
				// parteDTO.setEmailDosAnterior(correoElectronico.getDireccion());
				// }

				for (CorreoElectronicoRolPersonaCaso correoElectronicoRolPersonaCaso : correosRolPersonaCaso) {
					CorreoElectronico correoElectronico = correoElectronicoRolPersonaCaso.getCorreoElectronico();
					if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
							&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO
									.equals(correoElectronico.getTipo())) {
						parteDTO.setEmailDos(correoElectronico.getDireccion());
						parteDTO.setEmailDosAnterior(correoElectronico.getDireccion());
					} else if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
							&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_TERCIARIO
									.equals(correoElectronico.getTipo())) {
						parteDTO.setEmailTres(correoElectronico.getDireccion());
						parteDTO.setEmailTresAnterior(correoElectronico.getDireccion());
					}

					if (parteDTO.getEmailDos() != null && parteDTO.getEmailTres() != null) {
						break;
					}
				}
			}

			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SEXOS, persona.getSexo());
			parteDTO.setTipoSexo(dominio);
			parteDTO.setFechaNacimiento(persona.getFechaDeNacimiento());
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESTRATOS, persona.getEstrato());
			parteDTO.setEstrato(dominio);
			parteDTO.setProfesion(persona.getProfesion());

			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESCOLARIDADES,
					persona.getEscolaridad());
			parteDTO.setEscolaridad(dominio);
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_INSTITUCIONES_EDUCATIVAS,
					persona.getInstitucionEducativa());
			parteDTO.setInstitucionEducativa(dominio);
			parteDTO.setFechaGrado(persona.getFechaDeGrado());
			parteDTO.setNumeroTarjetaProfesional(persona.getNumeroTarjetaProfesional());
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ENTIDAD_TARJETA_PROFESIONAL,
					persona.getEntidadExpideTarjetaProfesional());
			parteDTO.setEntidadTarjetaProfesional(dominio);
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_EMPRESA,
					persona.getTipoDeEmpresa());
			parteDTO.setTipoEmpresa(dominio);
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_ENTIDAD_PUBLICA,
					persona.getTipoDeEntidadPublica());
			parteDTO.setTipoEntidadPublica(dominio);
			parteDTO.setRepresentanteLegal(persona.getRepresentanteLegal());
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SECTOR_EMPRESA,
					persona.getSectorDeLaEmpresa());
			parteDTO.setSectorEmpresa(dominio);
			parteDTO.setPaginaWeb(persona.getPaginaWeb());			
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_CONSULTA_PREVIA,
					persona.getTieneEmpleo());
			parteDTO.setTieneEmpleo(dominio);
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_DE_EMPLEO,
					persona.getTipoEmpleo());
			parteDTO.setTipoEmpleo(dominio);

			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SECTOR_EMPRESA,
					persona.getSectorEconomico());
			parteDTO.setSectorEconomico(dominio);

			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESTADOS_CIVILES,
					persona.getEstadoCivil());
			parteDTO.setEstadoCivil(dominio);
			
			if(persona.getNumeroPersonasAcargo() != null) {
				parteDTO.setNumeroPersonasAcargo(Integer.parseInt(persona.getNumeroPersonasAcargo()));
			}
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_CONSULTA_PREVIA,
					persona.getTieneSociedadConyugal());
			parteDTO.setTieneSociedadConyugal(dominio);
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_CONSULTA_PREVIA,
					persona.getSociedadConyugalVigente());
			parteDTO.setSociedadConyugalVigente(dominio);
			
			if (idRol != null) {

				List<Persona> personasRepresentadas = new ArrayList<>();

				List<Apoderados> representadosList = manejadorApoderados.obtenerRepresentadosPorApoderado(idRol,
						persona.getIdPersona(), idCaso);

				for (Apoderados representado : representadosList) {

					Persona personaRepresentada = manejadorPersona
							.buscar(representado.getApoderadosPK().getIdPersonaRepresentado());
					personasRepresentadas.add(personaRepresentada);

				}

				parteDTO.setRepresentada((List<Persona>) transformarEntidadesColeccionSinDependencias(
						personasRepresentadas, new ArrayList<Persona>()));
			}
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		} catch (Exception e) {
			logger.error(e);
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		return parteDTO;
	}

	@Override
	public FormularioParteDTO consultarPartePorCedulaPorCaso(String numeroCedula, Long idCaso) {
		FormularioParteDTO parteDTO = new FormularioParteDTO();

		try {
			Persona persona = manejadorPersona.getConsultarPartePorCedulaPorCaso(numeroCedula, idCaso);
			parteDTO.setIdCaso(idCaso);
			String nombreRol = null;
			List<CorreoElectronicoRolPersonaCaso> correosRolPersonaCaso = null;
			List<UbicacionRolPersonaCaso> ubicacionesRolPersonaCaso = null;

			Dominio dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_PERSONA,
					persona.getTipoPersona());
			parteDTO.setTipoPersona(dominio);

			if (!persona.getRolPersonaCasoList().isEmpty()) {
				for (RolPersonaCaso it : persona.getRolPersonaCasoList()) {
					if ((it.getCaso().getIdCaso().compareTo(idCaso) == 0)
							&& it.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
						nombreRol = it.getRol().getNombre();
						correosRolPersonaCaso = it.getCorreoElectronicoRolPersonaCasoList();
						ubicacionesRolPersonaCaso = it.getUbicacionRolPersonaCasoList();
					}
				}
				dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ROL_PERSONA, nombreRol);
				parteDTO.setRol(dominio);
			}

			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA,
					persona.getTipoDocumento());
			parteDTO.setTipoIdentificacion(dominio);
			parteDTO.setNumeroIdentificacion(persona.getNumeroDocumento());
			ZonaGeografica zonaGeografica = new ZonaGeografica();
			if (persona.getCiudadDelDocumento() != null)
				zonaGeografica = zonaGeograficaFacade.buscar(persona.getCiudadDelDocumento());
			if (zonaGeografica != null)
				parteDTO.setCiudadIdentificacion(
						zonaGeograficaFacade.transformarEntidadSinDependencias(zonaGeografica));
			parteDTO.setNacionalidad(persona.getIdPaisOrigen());
			parteDTO.setPrimerNombre(persona.getPrimerNombreORazonSocial());
			parteDTO.setSegundoNombre(persona.getSegundoNombre());
			parteDTO.setPrimerApellidoORazonSocial(persona.getPrimerApellido());
			parteDTO.setSegundoApellido(persona.getSegundoApellido());
			parteDTO.setIdPersona(persona.getIdPersona());

			/*
			 * Consultar Ubicaciones
			 */

			List<Ubicacion> ubicacionesPersona = new ArrayList<Ubicacion>();

			// Ubicación Principal
			for (Ubicacion ubicacion : persona.getUbicacionList()) {

				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(ubicacion.getEstadoRegistro())
						&& UtilDominios.TIPO_UBICACION_PRINCIPAL.equals(ubicacion.getTipo())) {
					ubicacionesPersona.add(ubicacion);
					break;
				}
			}

			// Ubicaciones Secundarias :: Obtenidas desde la asociación con Rol Persona Caso
			if (ubicacionesRolPersonaCaso != null && ubicacionesRolPersonaCaso.size() > 0) {
				for (UbicacionRolPersonaCaso ubicacionRPC : ubicacionesRolPersonaCaso) {
					ubicacionesPersona.add(ubicacionRPC.getUbicacion());
				}
			}

			// Transformar ubicaciones a DTO
			List<UbicacionDTO> lstUbicacionDTO = new ArrayList<>();

			for (Ubicacion it : ubicacionesPersona) {
				UbicacionDTO ubicacionDTO = new UbicacionDTO();
				ubicacionDTO.setIdUbicacion(it.getIdUbicacion());
				ubicacionDTO.setDireccion(it.getDireccion());
				ubicacionDTO.setIdZonaGeografica(it.getIdZonaGeografica());
				ZonaGeografica ciudad = manejadorZonaGeografica.buscar(it.getIdZonaGeografica());
				ZonaGeografica pais = manejadorZonaGeografica.buscar(ciudad.getIdZonaGeograficaPadre());
				ubicacionDTO.setCiudad(ciudad);
				ubicacionDTO.setPais(pais);
				ubicacionDTO.setTipo(it.getTipo());
				lstUbicacionDTO.add(ubicacionDTO);
			}

			// Setear ubicaciones de la persona a los datos del formulario
			parteDTO.setLstUbicacion(lstUbicacionDTO);

			/*
			 * Telefonos
			 */
			int i = 0;

			for (Telefono it2 : telefonoFacade.transformarEntidadesColeccionSinDependencias(persona.getTelefonoList(),
					new ArrayList<Telefono>())) {
				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(it2.getEstadoRegistro())) {
					if (it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_CELULAR)) {
						parteDTO.setNumeroCelular(it2.getNumero());
					}
					if (i == 0 && it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
						parteDTO.setNumeroTelefonoUno(it2.getNumero());
						i++;
					} else if (i == 1 && it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
						parteDTO.setNumeroTelefonoDos(it2.getNumero());
						i++;
					}

				}
			}

			/*
			 * Correos Electronicos
			 */

			// Correo Principal
			for (CorreoElectronico it3 : correoElectronicoFacade.transformarEntidadesColeccionSinDependencias(
					persona.getCorreoElectronicoList(), new ArrayList<CorreoElectronico>())) {

				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(it3.getEstadoRegistro())
						&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL.equals(it3.getTipo())) {
					parteDTO.setEmailUno(it3.getDireccion());
					parteDTO.setEmailUnoAnterior(it3.getDireccion());
					break;
				}
			}

			// Correo Secundario y Terciario :: Obtenidos desde la asociación con Rol
			// Persona Caso
			if (correosRolPersonaCaso != null && correosRolPersonaCaso.size() > 0) {
				// CorreoElectronico correoElectronico =
				// correosRolPersonaCaso.get(0).getCorreoElectronico();
				//
				// if(correoElectronico != null) {
				// parteDTO.setEmailDos(correoElectronico.getDireccion());
				// parteDTO.setEmailDosAnterior(correoElectronico.getDireccion());
				// }

				for (CorreoElectronicoRolPersonaCaso correoElectronicoRolPersonaCaso : correosRolPersonaCaso) {
					CorreoElectronico correoElectronico = correoElectronicoRolPersonaCaso.getCorreoElectronico();
					if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
							&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO
									.equals(correoElectronico.getTipo())) {
						parteDTO.setEmailDos(correoElectronico.getDireccion());
						parteDTO.setEmailDosAnterior(correoElectronico.getDireccion());
					} else if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
							&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_TERCIARIO
									.equals(correoElectronico.getTipo())) {
						parteDTO.setEmailTres(correoElectronico.getDireccion());
						parteDTO.setEmailTresAnterior(correoElectronico.getDireccion());
					}

					if (parteDTO.getEmailDos() != null && parteDTO.getEmailTres() != null) {
						break;
					}
				}
			}

			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SEXOS, persona.getSexo());
			parteDTO.setTipoSexo(dominio);
			parteDTO.setFechaNacimiento(persona.getFechaDeNacimiento());
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESTRATOS, persona.getEstrato());
			parteDTO.setEstrato(dominio);
			parteDTO.setProfesion(persona.getProfesion());
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESCOLARIDADES,
					persona.getEscolaridad());
			parteDTO.setEscolaridad(dominio);
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_INSTITUCIONES_EDUCATIVAS,
					persona.getInstitucionEducativa());
			parteDTO.setInstitucionEducativa(dominio);
			parteDTO.setFechaGrado(persona.getFechaDeGrado());
			parteDTO.setNumeroTarjetaProfesional(persona.getNumeroTarjetaProfesional());
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ENTIDAD_TARJETA_PROFESIONAL,
					persona.getEntidadExpideTarjetaProfesional());
			parteDTO.setEntidadTarjetaProfesional(dominio);
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_EMPRESA,
					persona.getTipoDeEmpresa());
			parteDTO.setTipoEmpresa(dominio);
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_ENTIDAD_PUBLICA,
					persona.getTipoDeEntidadPublica());
			parteDTO.setTipoEntidadPublica(dominio);
			parteDTO.setRepresentanteLegal(persona.getRepresentanteLegal());
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SECTOR_EMPRESA,
					persona.getSectorDeLaEmpresa());
			parteDTO.setSectorEmpresa(dominio);
			parteDTO.setPaginaWeb(persona.getPaginaWeb());
			if (nombreRol != null) {
				String rolParte = obtenerRolParteRepresentada(nombreRol);
				List<Persona> personasRepresentadas = manejadorPersona.getConsultarPartesCasoPorRol(idCaso, rolParte);
				parteDTO.setRepresentada((List<Persona>) transformarEntidadesColeccionSinDependencias(
						personasRepresentadas, new ArrayList<Persona>()));
			}
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		} catch (Exception e) {
			logger.error(e);
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		return parteDTO;
	}

	@Override
	public FormularioParteDTO consultarPartePorIdPorRol(Long idPersona, String nombreRol, Long idCaso) {
		Persona persona = manejadorPersona.buscar(idPersona);
		FormularioParteDTO parteDTO = new FormularioParteDTO();
		parteDTO.setIdPersona(persona.getIdPersona());
		parteDTO.setIdCaso(idCaso);

		List<CorreoElectronicoRolPersonaCaso> correosRolPersonaCaso = null;

		if (!persona.getRolPersonaCasoList().isEmpty()) {
			for (RolPersonaCaso it : persona.getRolPersonaCasoList()) {
				if ((it.getCaso().getIdCaso().compareTo(idCaso) == 0)
						&& it.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
					correosRolPersonaCaso = it.getCorreoElectronicoRolPersonaCasoList();
					break;
				}
			}
		}

		Dominio dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_PERSONA,
				persona.getTipoPersona());
		parteDTO.setTipoPersona(dominio);
		dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ROL_PERSONA, nombreRol);
		parteDTO.setRol(dominio);
		dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA,
				persona.getTipoDocumento());
		parteDTO.setTipoIdentificacion(dominio);
		parteDTO.setNumeroIdentificacion(persona.getNumeroDocumento());
		ZonaGeografica zonaGeografica = new ZonaGeografica();
		if (persona.getCiudadDelDocumento() != null) {
			parteDTO.setCiudadIdentificacion(zonaGeograficaFacade.transformarEntidadSinDependencias(zonaGeografica));
		}
		parteDTO.setNacionalidad(persona.getIdPaisOrigen());

		parteDTO.setPrimerNombre(persona.getPrimerNombreORazonSocial());
		parteDTO.setSegundoNombre(persona.getSegundoNombre());
		parteDTO.setPrimerApellidoORazonSocial(persona.getPrimerApellido());

		for (Ubicacion it : persona.getUbicacionList()) {
			if (it.getZonaGeografica().getIdZonaGeograficaPadre() == null)
				parteDTO.setPais(zonaGeograficaFacade.transformarEntidadSinDependencias(it.getZonaGeografica()));
			else {
				parteDTO.setDireccion(it.getDireccion());
				parteDTO.setCiudad(zonaGeograficaFacade.transformarEntidadSinDependencias(it.getZonaGeografica()));
			}
		}

		int i = 0;

		for (Telefono it2 : telefonoFacade.transformarEntidadesColeccionSinDependencias(persona.getTelefonoList(),
				new ArrayList<Telefono>())) {
			if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(it2.getEstadoRegistro())) {
				if (it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_CELULAR)) {
					parteDTO.setNumeroCelular(it2.getNumero());
				} else if (it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
					parteDTO.setNumeroTelefonoUno(it2.getNumero());
				} else if (i > 0 && it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
					parteDTO.setNumeroTelefonoUno(it2.getNumero());
				}
				i++;
			}

		}

		/*
		 * Correos Electronicos
		 */

		// Correo Principal
		for (CorreoElectronico it3 : correoElectronicoFacade.transformarEntidadesColeccionSinDependencias(
				persona.getCorreoElectronicoList(), new ArrayList<CorreoElectronico>())) {

			if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(it3.getEstadoRegistro())
					&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL.equals(it3.getTipo())) {
				parteDTO.setEmailUno(it3.getDireccion());
				parteDTO.setEmailUnoAnterior(it3.getDireccion());
				break;
			}
		}

		// Correos Secundario y Terciario :: Obtenidos desde la asociación con Rol
		// Persona Caso
		if (correosRolPersonaCaso != null && correosRolPersonaCaso.size() > 0) {
			// CorreoElectronico correoElectronico =
			// correosRolPersonaCaso.get(0).getCorreoElectronico();
			//
			// if(correoElectronico != null) {
			// parteDTO.setEmailDos(correoElectronico.getDireccion());
			// parteDTO.setEmailDosAnterior(correoElectronico.getDireccion());
			// }

			for (CorreoElectronicoRolPersonaCaso correoElectronicoRolPersonaCaso : correosRolPersonaCaso) {
				CorreoElectronico correoElectronico = correoElectronicoRolPersonaCaso.getCorreoElectronico();
				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
						&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO.equals(correoElectronico.getTipo())) {
					parteDTO.setEmailDos(correoElectronico.getDireccion());
					parteDTO.setEmailDosAnterior(correoElectronico.getDireccion());
				} else if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
						&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_TERCIARIO.equals(correoElectronico.getTipo())) {
					parteDTO.setEmailTres(correoElectronico.getDireccion());
					parteDTO.setEmailTresAnterior(correoElectronico.getDireccion());
				}

				if (parteDTO.getEmailDos() != null && parteDTO.getEmailTres() != null) {
					break;
				}
			}
		}

		dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SEXOS, persona.getSexo());
		parteDTO.setTipoSexo(dominio);
		parteDTO.setFechaNacimiento(persona.getFechaDeNacimiento());
		dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESTRATOS, persona.getEstrato());
		parteDTO.setEstrato(dominio);
		parteDTO.setProfesion(persona.getProfesion());
		dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESCOLARIDADES,
				persona.getEscolaridad());
		parteDTO.setEscolaridad(dominio);
		dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_INSTITUCIONES_EDUCATIVAS,
				persona.getInstitucionEducativa());
		parteDTO.setInstitucionEducativa(dominio);
		parteDTO.setFechaGrado(persona.getFechaDeGrado());
		parteDTO.setNumeroTarjetaProfesional(persona.getNumeroTarjetaProfesional());
		dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ENTIDAD_TARJETA_PROFESIONAL,
				persona.getEntidadExpideTarjetaProfesional());
		parteDTO.setEntidadTarjetaProfesional(dominio);
		dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_EMPRESA,
				persona.getTipoDeEmpresa());
		parteDTO.setTipoEmpresa(dominio);
		dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_ENTIDAD_PUBLICA,
				persona.getTipoDeEntidadPublica());
		parteDTO.setTipoEntidadPublica(dominio);
		parteDTO.setRepresentanteLegal(persona.getRepresentanteLegal());
		dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SECTOR_EMPRESA,
				persona.getSectorDeLaEmpresa());
		parteDTO.setSectorEmpresa(dominio);
		parteDTO.setPaginaWeb(persona.getPaginaWeb());
		List<Persona> personasRepresentadas = manejadorPersona.getConsultarPartesCasoPorRol(idCaso,
				obtenerRolParteRepresentada(nombreRol));
		parteDTO.setRepresentada((List<Persona>) transformarEntidadesColeccionSinDependencias(personasRepresentadas,
				new ArrayList<Persona>()));
		

		return parteDTO;
	}

	@Override
	public Long guardarInformacionParte(FormularioParteDTO formularioParteDTO) {

		if (UtilDominios.ROL_PERSONA_REPRESENTANTE_DE_PARTE
				.equalsIgnoreCase(formularioParteDTO.getRol().getDominioPK().getCodigo())) {

			compararRepresentados(formularioParteDTO.getRepresentada(), formularioParteDTO.getIdCaso(),
					formularioParteDTO.getIdSolicitudServicio());

		}		
		Persona persona = new Persona();
		Persona personaExiste = new Persona();
		boolean isSinID = UtilDominios.TIPO_DOCUMENTO_PERSONA_SIN_IDENTIFICACION
				.equals(formularioParteDTO.getTipoIdentificacion().getDominioPK().getCodigo());
		
		if(!isSinID && (formularioParteDTO.getNumeroIdentificacion().isEmpty() ||
				formularioParteDTO.getNumeroIdentificacion() == null)) {
			String mensajeError = String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR500.toString()),
					"El numero de documento no puede ser vacio o null");
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		
		if (formularioParteDTO.getTipoIdentificacion() != null
				&& formularioParteDTO.getTipoIdentificacion().getDominioPK() != null && !isSinID) {

			personaExiste = manejadorPersona.consultarPersonaPorIdentificacion(
					(formularioParteDTO.getTipoIdentificacion() == null
							|| formularioParteDTO.getTipoIdentificacion().getDominioPK() == null
							|| formularioParteDTO.getTipoIdentificacion().getDominioPK().getCodigo() == null) ? null
									: formularioParteDTO.getTipoIdentificacion().getDominioPK().getCodigo(),
					formularioParteDTO.getNumeroIdentificacion());
			if (personaExiste != null) {				
				if (formularioParteDTO.getIdPersona() == null) {
					persona.setIdPersona(personaExiste.getIdPersona());
				} else if (!personaExiste.getIdPersona().equals(formularioParteDTO.getIdPersona())) {
					List<String> parametrosMensaje = new ArrayList<String>();
					parametrosMensaje.add(personaExiste.getNombreCompleto());
					String mensajeError = String.format(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR500.toString()),
							parametrosMensaje.toArray());
					throw new SIMASCNegocioExcepcion(mensajeError);
				} else {
					persona.setIdPersona(formularioParteDTO.getIdPersona());
				}
			} else if (formularioParteDTO.getIdPersona() != null) {
				persona.setIdPersona(formularioParteDTO.getIdPersona());
			}
		} else if (isSinID && formularioParteDTO.getIdPersona() != null) {
			persona.setIdPersona(formularioParteDTO.getIdPersona());
		}		
		if(persona.getIdPersona() != null && UtilMascaraTexto.hasEmailOnlyDots(formularioParteDTO.getEmailUno())) {
			
			List<CorreoElectronico> correoPrincipal = correoElectronicoFacade
					.consultaCorreosPersona(persona.getIdPersona(), false, UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
			
			formularioParteDTO.setEmailUno(correoPrincipal.get(0).getDireccion());
			formularioParteDTO.setEmailUnoAnterior(correoPrincipal.get(0).getDireccion());			
		}
		
		persona.setTipoDocumento((formularioParteDTO.getTipoIdentificacion() == null
				|| formularioParteDTO.getTipoIdentificacion().getDominioPK() == null
				|| formularioParteDTO.getTipoIdentificacion().getDominioPK().getCodigo() == null) ? null
						: formularioParteDTO.getTipoIdentificacion().getDominioPK().getCodigo());
		persona.setNumeroDocumento(formularioParteDTO.getNumeroIdentificacion());

		persona.setPrimerNombreORazonSocial(formularioParteDTO.getPrimerNombre().toUpperCase());
		persona.setSegundoNombre(formularioParteDTO.getSegundoNombre() == null ? UtilConstantes.CADENA_VACIA
				: formularioParteDTO.getSegundoNombre().toUpperCase());
		persona.setPrimerApellido(
				formularioParteDTO.getPrimerApellidoORazonSocial() == null ? UtilConstantes.CADENA_VACIA
						: formularioParteDTO.getPrimerApellidoORazonSocial().toUpperCase());
		persona.setSegundoApellido(formularioParteDTO.getSegundoApellido() == null ? UtilConstantes.CADENA_VACIA
				: formularioParteDTO.getSegundoApellido().toUpperCase());
		persona.setTipoPersona((formularioParteDTO.getTipoPersona() == null
				|| formularioParteDTO.getTipoPersona().getDominioPK() == null
				|| formularioParteDTO.getTipoPersona().getDominioPK().getCodigo() == null) ? null
						: formularioParteDTO.getTipoPersona().getDominioPK().getCodigo());
		
		persona.setTipoFuncionario(UtilConstantes.ROL_PERSONA_EXTERNO);
		persona.setNumeroTarjetaProfesional(formularioParteDTO.getNumeroTarjetaProfesional());
		persona.setEstadoPersona(UtilDominios.ESTADO_FUNC_EXTERNO_ACTIVO);

		persona.setIdPaisOrigen(formularioParteDTO.getNacionalidad());
		persona.setCiudadDelDocumento((formularioParteDTO.getCiudadIdentificacion() == null
				|| formularioParteDTO.getCiudadIdentificacion().getIdZonaGeografica() == null) ? null
						: formularioParteDTO.getCiudadIdentificacion().getIdZonaGeografica());
		persona.setFechaDeNacimiento(formularioParteDTO.getFechaNacimiento());
		
		if(formularioParteDTO.getEscolaridad() == null
				|| formularioParteDTO.getEscolaridad().getDominioPK() == null
				|| formularioParteDTO.getEscolaridad().getDominioPK().getCodigo() == null) {
			persona.setEscolaridad(null);
		}else if(UtilMascaraTexto.hasOnlyDots(formularioParteDTO.getEscolaridad().getDominioPK().getCodigo())) {			
			persona.setEscolaridad(personaExiste != null ? personaExiste.getEscolaridad() : null);					
		}else {
			persona.setEscolaridad(formularioParteDTO.getEscolaridad().getDominioPK().getCodigo());
		}		
		if(formularioParteDTO.getEstrato() == null 
				|| formularioParteDTO.getEstrato().getDominioPK() == null
				|| formularioParteDTO.getEstrato().getDominioPK().getCodigo() == null) {
			persona.setEstrato(null);
			
		}else if(UtilMascaraTexto.hasOnlyDots(formularioParteDTO.getEstrato().getDominioPK().getCodigo())) {				
			persona.setEstrato(personaExiste != null ?  personaExiste.getEstrato() : null);
		}else {
			persona.setEstrato(formularioParteDTO.getEstrato().getDominioPK().getCodigo());
		}		
		if (formularioParteDTO.getProfesion() != null) {
			Profesion profesion = new Profesion();
			profesion.setIdProfesion(formularioParteDTO.getProfesion().getIdProfesion());
			persona.setProfesion(profesion);
			persona.setIdProfesion(formularioParteDTO.getProfesion().getIdProfesion());
		}
		persona.setSexo(
				(formularioParteDTO.getTipoSexo() == null || formularioParteDTO.getTipoSexo().getDominioPK() == null
						|| formularioParteDTO.getTipoSexo().getDominioPK().getCodigo() == null) ? null
								: formularioParteDTO.getTipoSexo().getDominioPK().getCodigo());
		
		if(formularioParteDTO.getInstitucionEducativa() == null
				|| formularioParteDTO.getInstitucionEducativa().getDominioPK() == null
				|| formularioParteDTO.getInstitucionEducativa().getDominioPK().getCodigo() == null) {			
			persona.setInstitucionEducativa(null);			
		}else if(UtilMascaraTexto.hasOnlyDots(formularioParteDTO.getInstitucionEducativa().getDominioPK().getCodigo())) {			
			persona.setInstitucionEducativa(personaExiste != null ? personaExiste.getInstitucionEducativa() : null);			
		}else {
			persona.setInstitucionEducativa(formularioParteDTO.getInstitucionEducativa().getDominioPK().getCodigo());
		}		
		persona.setFechaDeGrado(formularioParteDTO.getFechaGrado());
		persona.setEntidadExpideTarjetaProfesional((formularioParteDTO.getEntidadTarjetaProfesional() == null
				|| formularioParteDTO.getEntidadTarjetaProfesional().getDominioPK() == null
				|| formularioParteDTO.getEntidadTarjetaProfesional().getDominioPK().getCodigo() == null) ? null
						: formularioParteDTO.getEntidadTarjetaProfesional().getDominioPK().getCodigo());

		String idSesion = null;
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			idSesion = appContext.getContextoSesion().getNombreUsuario();
		} else {
			idSesion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		}		
		persona.setIdUsuarioModificacion(idSesion);
		persona.setFechaUltimaModificacion(new Date());
		persona.setEstadoRegistro(UtilDominios.ESTADO_PERSONA_ACTIVO);

		if (formularioParteDTO.getTipoPersona().getDominioPK().getCodigo().equals(UtilDominios.TIPO_PERSONA_JURIDICO)) {
			persona.setTipoDeEmpresa((formularioParteDTO.getTipoEmpresa() == null
					|| formularioParteDTO.getTipoEmpresa().getDominioPK() == null
					|| formularioParteDTO.getTipoEmpresa().getDominioPK().getCodigo() == null) ? null
							: formularioParteDTO.getTipoEmpresa().getDominioPK().getCodigo());
			persona.setTipoDeEntidadPublica((formularioParteDTO.getTipoEntidadPublica() == null
					|| formularioParteDTO.getTipoEntidadPublica().getDominioPK() == null
					|| formularioParteDTO.getTipoEntidadPublica().getDominioPK().getCodigo() == null) ? null
							: formularioParteDTO.getTipoEntidadPublica().getDominioPK().getCodigo());
			persona.setRepresentanteLegal(formularioParteDTO.getRepresentanteLegal());
			persona.setSectorDeLaEmpresa((formularioParteDTO.getSectorEmpresa() == null
					|| formularioParteDTO.getSectorEmpresa().getDominioPK() == null
					|| formularioParteDTO.getSectorEmpresa().getDominioPK().getCodigo() == null) ? null
							: formularioParteDTO.getSectorEmpresa().getDominioPK().getCodigo());
			persona.setPaginaWeb(formularioParteDTO.getPaginaWeb());
		}		
		persona.setTieneEmpleo(
				(formularioParteDTO.getTieneEmpleo() == null || formularioParteDTO.getTieneEmpleo().getDominioPK() == null
						|| formularioParteDTO.getTieneEmpleo().getDominioPK().getCodigo() == null) ? null
								: formularioParteDTO.getTieneEmpleo().getDominioPK().getCodigo());
		persona.setTipoEmpleo(
				(formularioParteDTO.getTipoEmpleo() == null || formularioParteDTO.getTipoEmpleo().getDominioPK() == null
						|| formularioParteDTO.getTipoEmpleo().getDominioPK().getCodigo() == null) ? null
								: formularioParteDTO.getTipoEmpleo().getDominioPK().getCodigo());
		persona.setSectorEconomico(
				(formularioParteDTO.getSectorEconomico() == null || formularioParteDTO.getSectorEconomico().getDominioPK() == null
						|| formularioParteDTO.getSectorEconomico().getDominioPK().getCodigo() == null) ? null
								: formularioParteDTO.getSectorEconomico().getDominioPK().getCodigo());
		persona.setEstadoCivil(
				(formularioParteDTO.getEstadoCivil() == null || formularioParteDTO.getEstadoCivil().getDominioPK() == null
						|| formularioParteDTO.getEstadoCivil().getDominioPK().getCodigo() == null) ? null
								: formularioParteDTO.getEstadoCivil().getDominioPK().getCodigo());
		persona.setTieneSociedadConyugal(
				(formularioParteDTO.getTieneSociedadConyugal() == null || formularioParteDTO.getTieneSociedadConyugal().getDominioPK() == null
						|| formularioParteDTO.getTieneSociedadConyugal().getDominioPK().getCodigo() == null) ? null
								: formularioParteDTO.getTieneSociedadConyugal().getDominioPK().getCodigo());
		persona.setSociedadConyugalVigente(
				(formularioParteDTO.getSociedadConyugalVigente() == null || formularioParteDTO.getSociedadConyugalVigente().getDominioPK() == null
						|| formularioParteDTO.getSociedadConyugalVigente().getDominioPK().getCodigo() == null) ? null
								: formularioParteDTO.getSociedadConyugalVigente().getDominioPK().getCodigo());
		persona.setSociedadConyugalVigente(
				(formularioParteDTO.getSociedadConyugalVigente() == null || formularioParteDTO.getSociedadConyugalVigente().getDominioPK() == null
						|| formularioParteDTO.getSociedadConyugalVigente().getDominioPK().getCodigo() == null) ? null
								: formularioParteDTO.getSociedadConyugalVigente().getDominioPK().getCodigo());
		
		persona.setOcupacion((formularioParteDTO.getOcupacion() == null
				|| formularioParteDTO.getOcupacion().getDominioPK() == null
				|| formularioParteDTO.getOcupacion().getDominioPK().getCodigo() == null) ? null
						: formularioParteDTO.getOcupacion().getDominioPK().getCodigo());
		
		persona.setActividadEconomica((formularioParteDTO.getActividadEconomica() == null
				|| formularioParteDTO.getActividadEconomica().getDominioPK() == null
				|| formularioParteDTO.getActividadEconomica().getDominioPK().getCodigo() == null) ? null
						: formularioParteDTO.getActividadEconomica().getDominioPK().getCodigo());
		
		persona.setNombreNegocio((formularioParteDTO.getNombreNegocio() == null
				|| formularioParteDTO.getNombreNegocio().getDominioPK() == null
				|| formularioParteDTO.getNombreNegocio().getDominioPK().getCodigo() == null) ? null
						: formularioParteDTO.getNombreNegocio().getDominioPK().getCodigo());
		
		String nitEmpresa=(formularioParteDTO.getNitEmpresa()==null)?"":formularioParteDTO.getNitEmpresa();
		persona.setNitEmpresa(nitEmpresa);
		
		String nombreEmpresa=(formularioParteDTO.getNombreEmpresa()==null)?"":formularioParteDTO.getNombreEmpresa();
		persona.setNombreEmpresa(nombreEmpresa);
		
		persona.setOtraActividadEconomica(formularioParteDTO.getOtraActividadEconomica());
		persona.setOtraNombreNegocio(formularioParteDTO.getOtraNombreNegocio());
		
		
		
		persona.setNumeroPersonasAcargo(""+formularioParteDTO.getNumeroPersonasAcargo());		
		if (formularioParteDTO.getTipoIdentificacion() != null
				&& formularioParteDTO.getTipoIdentificacion().getDominioPK() != null
				&& UtilDominios.TIPO_DOCUMENTO_PERSONA_SIN_IDENTIFICACION
						.equals(formularioParteDTO.getTipoIdentificacion().getDominioPK().getCodigo())) {
			persona.setNumeroDocumento(null);
		}		
		if (persona.getIdPersona() == null) {
			persona = manejadorPersona.crearPersona(persona);
		} else {
			manejadorPersona.actualizar(persona);
			if (isSinID)
				manejadorPersona.actulizarNumeroIdentificacionEnNull(persona.getIdPersona());
		}		
		/**
		 * ARB-F-109 Si el formulario de partes contiene el identificador de la
		 * solicitud de servicio, quiere decir que el consumo del servicio se hace desde
		 * el módulo público de radicación virtual y la información de la persona debe
		 * ser almacenada en la tabla 'PERSONA_SOLICITUD', de lo contrario la
		 * información es almacenada en la tabla 'ROL_PERSONA_CASO'
		 */
		Rol rol;
		PersonaSolicitud personaSolicitud = null;
		Caso caso = null;

		if (formularioParteDTO.getIdSolicitudServicio() != null) {
			rol = manejadorRol.consultarRolPorNombre(formularioParteDTO.getRol().getDominioPK().getCodigo());
			personaSolicitud = crearPersonaSolicitud(rol.getIdRol(), persona.getIdPersona(),
					formularioParteDTO.getIdSolicitudServicio());
			asignarApoderadosSolicitud(formularioParteDTO, rol, persona);
			
			String rolAbogadoSolicitud = formularioParteDTO.getRol().getDominioPK().getCodigo();
			asignarRepresentadosSolicitudTramiteOrdinario(rolAbogadoSolicitud, formularioParteDTO,
					formularioParteDTO.getIdSolicitudServicio(), rol, persona);
						
		} else {			
			// Obtener el caso actual
			caso = manejadorCaso.buscar(formularioParteDTO.getIdCaso());
			// Obtener el rol (Apoderado demandante o demandado).
			rol = manejadorRol.consultarRolPorNombre(formularioParteDTO.getRol().getDominioPK().getCodigo());
			// Validar si está creada la persona en RolPersonaCaso, de lo contrario lo crea.
			if (!manejadorRolPersonaCaso.obtenerRolesPersonaCaso(persona.getIdPersona(), caso.getIdCaso(), null)) {
				if (manejadorRolPersonaCaso.validaRolPersonaExistioCaso(persona.getIdPersona(), caso.getIdCaso(),
						rol.getIdRol())) {					
					RolPersonaCaso rolPersonaCasoAnterior = manejadorRolPersonaCaso
							.consultarPersonaAsignadaCaso(persona.getIdPersona(), caso.getIdCaso());
					rolPersonaCasoAnterior.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO);
					rolPersonaCasoAnterior.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
					rolPersonaCasoFacade.actualizar(rolPersonaCasoAnterior);

					RolPersonaCaso rolPersonaCasoNuevo = manejadorRolPersonaCaso.consultarPersonasPorRolCasoInactivo(
							persona.getIdPersona(), caso.getIdCaso(), rol.getNombre());
					rolPersonaCasoNuevo.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_NO_APLICA);
					rolPersonaCasoNuevo.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					rolPersonaCasoFacade.actualizar(rolPersonaCasoNuevo);
				} else {					
					apoderadosFacade.eliminarApoderados(caso.getIdCaso(), persona.getIdPersona());
					rolPersonaCasoFacade.cambiarRolAPersonaEnCaso(caso.getIdCaso(), persona.getIdPersona(),
							rol.getIdRol());
				}
			} else {				
				this.crearRolPersonaCaso(caso, rol, persona);
			}
			
			// Rol Abogado.
			String rolAbogado = formularioParteDTO.getRol().getDominioPK().getCodigo();

			// Asignar los representados del apoderado.
			asignarRepresentados(rolAbogado, formularioParteDTO, caso, rol, persona);
		}
		
		/*
		 * Registro de Información de contacto
		 */
		if (formularioParteDTO.getIdSolicitudServicio() != null) {
			// Registro de la información de contacto de la Persona asociada a una Solicitud
			// de Servicio
									
			adicionarInformacionContactoPersona(formularioParteDTO, persona, null, personaSolicitud, personaExiste);
		} else {			
			// Consultar Rol Persona Caso asignado a la Persona
			RolPersonaCaso rolPersonaCaso = manejadorRolPersonaCaso.estaPersonaAsignadaCaso(persona.getIdPersona(),
					formularioParteDTO.getIdCaso());			
			

			// Registro de la información de contacto de la Persona asociada a un Caso			
			adicionarInformacionContactoPersona(formularioParteDTO, persona, rolPersonaCaso, null, personaExiste);
		}		
		if ((rol != null && (UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO.equals(rol.getNombre())
				|| UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE.equals(rol.getNombre())
				|| UtilDominios.ROL_PERSONA_APODERADO_ACREEDOR_RECUPERACION.equals(rol.getNombre())
				|| UtilDominios.ROL_PERSONA_APODERADO_DEUDOR_RECUPERACION.equals(rol.getNombre())
				|| UtilDominios.ROL_PERSONA_REPRESENTANTE_DE_PARTE.equals(rol.getNombre()))) && caso != null) {
			this.inactivacionLitigante(persona.getIdPersona(), caso.getIdServicio());
		}		
		return persona.getIdPersona();
	}

	private void compararRepresentados(List<Persona> representados, Long idCaso, Long idSolicitudServicio)
			throws SIMASCNegocioExcepcion {

		List<RolPersonaCaso> representadosDemandante = new ArrayList<>();
		List<RolPersonaCaso> representadosDemandados = new ArrayList<>();

		List<PersonaSolicitud> representadosDemandantesSolicitudServicio = new ArrayList<>();
		List<PersonaSolicitud> representadosDemandadosSolicitudServicio = new ArrayList<>();

		if (idSolicitudServicio == null) {
			for (Persona representado : representados) {

				RolPersonaCaso representadoRolPersonaCaso = manejadorRolPersonaCaso
						.consultarPersonaAsignadaCaso(representado.getIdPersona(), idCaso);
				if (representadoRolPersonaCaso.getRol().getNombre()
						.equalsIgnoreCase(UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE))
					representadosDemandante.add(representadoRolPersonaCaso);
				else if (representadoRolPersonaCaso.getRol().getNombre()
						.equalsIgnoreCase(UtilDominios.ROL_PERSONA_PARTE_DEMANDADA))
					representadosDemandados.add(representadoRolPersonaCaso);

			}

		} else {
			for (Persona representado : representados) {
				List<PersonaSolicitud> representadoSolicitud = manejadorPersonaSolicitud
						.consultarPersonaSolicitud(idSolicitudServicio, representado.getIdPersona(), null);
				for (PersonaSolicitud personaSolicitud : representadoSolicitud) {
					if (personaSolicitud.getRol().getNombre()
							.equalsIgnoreCase(UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE))
						representadosDemandantesSolicitudServicio.add(personaSolicitud);

					else if (personaSolicitud.getRol().getNombre()
							.equalsIgnoreCase(UtilDominios.ROL_PERSONA_PARTE_DEMANDADA))
						representadosDemandadosSolicitudServicio.add(personaSolicitud);
				}
			}
		}

		// if (!representadosDemandados.isEmpty() && !representadosDemandante.isEmpty())
		// {}

		if ((!representadosDemandados.isEmpty() && !representadosDemandante.isEmpty())
				|| (!representadosDemandantesSolicitudServicio.isEmpty()
						&& !representadosDemandadosSolicitudServicio.isEmpty())) {

			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR586.toString()));
		}
	}

	@Override
	public Boolean inactivacionSegunMotivo(Long idPersona, String Motivo, Long idRol, Long idServicio) {
		EstadoPersonaRolPK pId = new EstadoPersonaRolPK();
		pId.setIdRol(idRol);
		pId.setIdPersona(idPersona);
		pId.setIdServicio(idServicio);
		EstadoPersonaRol estadoPersonaRol = new EstadoPersonaRol();
		estadoPersonaRol.setEstadoPersonaRolPK(pId);
		estadoPersonaRol.setIdMotivo(Motivo);
		estadoPersonaRol.setFechaUltimaModificacion(new Date());
		estadoPersonaRol.setEstadoRegistro(UtilDominios.ESTADO_PERSONA_ACTIVO);
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			estadoPersonaRol.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
		} else {
			estadoPersonaRol.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		}
		estadoPersonaRolFacade.cambiarEstadoFuncionario(estadoPersonaRol);
		return true;
	}

	@Override
	public Boolean inactivacionLitigante(Long idPersona, Long idServicio) {
		Long idRol = null;
		Boolean inactivacion = false;
		idRol = manejadorRolPersona.consultarRolOperadorPorServicio(idPersona, idServicio);
		if (idRol != null) {
			inactivacion = this.inactivacionSegunMotivo(idPersona, UtilDominios.ESTADO_ARBITROS_INACTIVO_LITIGANDO,
					idRol, idServicio);
		}
		if (idServicio != Long.parseLong(UtilConstantes.ID_SERVICIO_ARBITRAJE_INTERNACIONAL)) {
			inactivacionLitigante(idPersona, Long.parseLong(UtilConstantes.ID_SERVICIO_ARBITRAJE_INTERNACIONAL));
		}
		return inactivacion;
	}

	@Override
	public Boolean inactivacionNoContestacion(Long idPersona, Long idServicio) {
		Boolean inactivacion = false;
//		EstadoPersonaTipoServicio estadoPersonaTipoServicio;
		// Obtenemos los roles por persona activos y que se encuentran en los parametros
		// del sorteo
		List<Rol> rolPersona = manejadorRolPersona
				.consultarRolesPersonaActivosPorPersonaEnParametroServicios(idPersona);
		for (Rol objRol : rolPersona) {
			// Para Cada Rol se actualiza un registro
			inactivacion = this.inactivacionSegunMotivo(idPersona, UtilDominios.MOTIVO_NO_CONTESTACION,
					objRol.getIdRol(), idServicio);
		}
		return inactivacion;
	}

	/**
	 * Asigna las partes representadas para un apoderado
	 * 
	 * @param rolAbogado
	 * @param formularioParteDTO
	 */
	private void asignarRepresentados(String rolAbogado, FormularioParteDTO formularioParteDTO, Caso caso, Rol rol,
			Persona persona) {

		if (rolAbogado.equalsIgnoreCase(UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO)
				|| rolAbogado.equalsIgnoreCase(UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE)
				|| rolAbogado.equalsIgnoreCase(UtilDominios.ROL_PERSONA_REPRESENTANTE_DE_PARTE)) {

			// Obtener el rol del representado (Demandado o Demandante)

			Rol rolParteRepresentada = new Rol();

			if (rolAbogado.equalsIgnoreCase(UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO)) {

				rolParteRepresentada = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);

			} else if (rolAbogado.equalsIgnoreCase(UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE)) {

				rolParteRepresentada = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);

			} else if (rolAbogado.equalsIgnoreCase(UtilDominios.ROL_PERSONA_REPRESENTANTE_DE_PARTE)) {

				Long idPersonaRepresentada = formularioParteDTO.getRepresentada().get(0).getIdPersona();
				RolPersonaCaso rolPersonaRepresentada = manejadorRolPersonaCaso.consultaRolPersonaId(
						idPersonaRepresentada, caso.getIdCaso(), UtilDominios.ESTADO_ROL_PERSONA_CASO_NO_APLICA);
				rolParteRepresentada = rolPersonaRepresentada.getRol();

			}

			List<Persona> parteRepresentada = formularioParteDTO.getRepresentada();
			List<RolPersonaCaso> representados = new ArrayList<>();

			for (Persona representada : parteRepresentada) {

				RolPersonaCaso representado = rolPersonaCasoFacade.buscar(new RolPersonaCasoPK(
						representada.getIdPersona(), caso.getIdCaso(), rolParteRepresentada.getIdRol()));
				;
				if (representado != null) {
					representados.add(representado);
				} else {
					representado = crearRolPersonaCaso(caso, rolParteRepresentada, representada);
					representados.add(representado);
				}

			}

			// obtener el apoderado
			RolPersonaCaso apoderado = rolPersonaCasoFacade
					.buscar(new RolPersonaCasoPK(persona.getIdPersona(), caso.getIdCaso(), rol.getIdRol()));

			// crear los nuevos representados.
			List<Apoderados> nuevosRepresentados = crearRepresentados(apoderado, representados, caso);

			// obtener la lista de representados actuales.
			List<Apoderados> representadosList = manejadorApoderados.obtenerRepresentadosPorApoderado(
					apoderado.getRol().getIdRol(), apoderado.getPersona().getIdPersona(), caso.getIdCaso());

			if (!representadosList.isEmpty()) {
				eliminarRepresentados(nuevosRepresentados, representadosList);
			}

		}
	}

	/**
	 * Permite obtener el rol de la parte representada.
	 * 
	 * @param rolApoderado: El rol apoderado arbitraje/conciliacion.
	 * @return rol parte representada.
	 */
	private String obtenerRolParteRepresentada(String rolApoderado) {
		String rolRepresentada = null;

		switch (rolApoderado) {

		case UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO:
			rolRepresentada = UtilDominios.ROL_PERSONA_PARTE_DEMANDADA;
			break;
		case UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE:
			rolRepresentada = UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE;
			break;
		case UtilDominios.ROL_PERSONA_APODERADO_CONVOCADO:
			rolRepresentada = UtilDominios.ROL_PERSONA_CONVOCADO;
			break;
		case UtilDominios.ROL_PERSONA_APODERADO_CONVOCANTE:
			rolRepresentada = UtilDominios.ROL_PERSONA_CONVOCANTE;
			break;

		default:
			break;
		}
		return rolRepresentada;
	}

	/**
	 * Elimina todos los representados de la parte
	 * 
	 * @param persona
	 * @param caso
	 */
	private void eliminarRepresentados(List<Apoderados> nuevosRepresentados, List<Apoderados> representadosActuales) {

		for (Apoderados representadoActual : representadosActuales) {

			if (!nuevosRepresentados.contains(representadoActual)) {
				representadoActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
				representadoActual.setFechaUltimaModificacion(new Date());
				representadoActual.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
				manejadorApoderados.eliminar(representadoActual);
			}

		}

	}

	private List<Apoderados> crearRepresentados(RolPersonaCaso apoderado, List<RolPersonaCaso> representados,
			Caso caso) {

		List<Apoderados> nuevosRepresentados = new ArrayList<>();

		for (RolPersonaCaso representado : representados) {

			Long idRolApoderado = apoderado.getRolPersonaCasoPK().getIdRol();
			Long idPersonaApoderado = apoderado.getRolPersonaCasoPK().getIdPersona();
			Long idCasoApoderado = caso.getIdCaso();
			Long idRolRepresentado = representado.getRolPersonaCasoPK().getIdRol();
			Long idPersonaRepresentado = representado.getRolPersonaCasoPK().getIdPersona();
			Long idCasoRepresentado = caso.getIdCaso();
			ApoderadosPK nuevaLlave = new ApoderadosPK(idRolApoderado, idPersonaApoderado, idCasoApoderado,
					idRolRepresentado, idPersonaRepresentado, idCasoRepresentado);
			Apoderados entity = manejadorApoderados.buscar(nuevaLlave);
			if (entity != null) {
				entity.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				entity.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
				entity.setFechaUltimaModificacion(new Date());
				manejadorApoderados.actualizar(entity);
				nuevosRepresentados.add(entity);
			} else {
				Apoderados nuevoRegistro = new Apoderados();
				nuevoRegistro.setApoderadosPK(nuevaLlave);
				nuevoRegistro.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				nuevoRegistro.setFechaUltimaModificacion(new Date());
				nuevoRegistro.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
				manejadorApoderados.crear(nuevoRegistro);
				nuevosRepresentados.add(nuevoRegistro);
			}

		}

		return nuevosRepresentados;

	}

	private void eliminarRepresentadosSolicitud(List<ApoderadosSolicitud> nuevosRepresentados,
			List<ApoderadosSolicitud> representadosActuales) {

		for (ApoderadosSolicitud representadoActual : representadosActuales) {

			if (!nuevosRepresentados.contains(representadoActual)) {
				representadoActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
				representadoActual.setFechaUltimaModificacion(new Date());
				representadoActual.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
				manejadorApoderadosSolicitud.eliminar(representadoActual);
			}

		}

	}

	private List<ApoderadosSolicitud> crearRepresentadosSolicitud(PersonaSolicitud apoderado,
			List<PersonaSolicitud> personasSolicitudRepresentadas, Long idSolicitudServicio) {

		List<ApoderadosSolicitud> nuevosRepresentados = new ArrayList<>();

		for (PersonaSolicitud representado : personasSolicitudRepresentadas) {

			Long idRolApoderado = apoderado.getPersonaSolicitudPK().getIdRol();
			Long idPersonaApoderado = apoderado.getPersonaSolicitudPK().getIdPersona();
			Long idRolRepresentado = representado.getPersonaSolicitudPK().getIdRol();
			Long idPersonaRepresentado = representado.getPersonaSolicitudPK().getIdPersona();
			ApoderadosSolicitudPK nuevaLlave = new ApoderadosSolicitudPK(idPersonaApoderado, idSolicitudServicio,
					idRolApoderado, idPersonaRepresentado, idSolicitudServicio, idRolRepresentado);
			ApoderadosSolicitud entity = manejadorApoderadosSolicitud.buscar(nuevaLlave);
			if (entity != null) {
				entity.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				entity.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
				entity.setFechaUltimaModificacion(new Date());
				manejadorApoderadosSolicitud.actualizar(entity);
				nuevosRepresentados.add(entity);
			} else {
				ApoderadosSolicitud nuevoRegistro = new ApoderadosSolicitud();
				nuevoRegistro.setApoderadosSolicitudPK(nuevaLlave);
				nuevoRegistro.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				nuevoRegistro.setFechaUltimaModificacion(new Date());
				nuevoRegistro.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
				manejadorApoderadosSolicitud.crear(nuevoRegistro);
				nuevosRepresentados.add(nuevoRegistro);
			}

		}

		return nuevosRepresentados;

	}

	/**
	 * Asigna las partes representadas para un apoderado
	 * 
	 * @param rolAbogado
	 * @param formularioParteDTO
	 */
	private void asignarRepresentadosSolicitudTramiteOrdinario(String rolAbogado, FormularioParteDTO formularioParteDTO,
			Long idSolicitud, Rol rol, Persona persona) {
		if (rolAbogado.equals(UtilDominios.ROL_PERSONA_APODERADO_CONVOCADO)
				|| rolAbogado.equals(UtilDominios.ROL_PERSONA_APODERADO_CONVOCANTE)) {
			Rol rolParteRepresentada = manejadorRol.consultarRolPorNombre(
					rolAbogado.equals(UtilDominios.ROL_PERSONA_APODERADO_CONVOCADO) ? UtilDominios.ROL_PERSONA_CONVOCADO
							: UtilDominios.ROL_PERSONA_CONVOCANTE);
			eliminarRepresentadosSolicitudTramiteOrdinario(persona, idSolicitud);
			for (Persona iterator : formularioParteDTO.getRepresentada()) {
				crearSolicitudPersonaApoderada(idSolicitud, rolParteRepresentada, iterator, rol.getIdRol(),
						persona.getIdPersona());
			}
		}
	}

	/**
	 * Elimina todos los representados de la parte
	 * 
	 * @param persona
	 * @param caso
	 */
	private void eliminarRepresentadosSolicitudTramiteOrdinario(Persona persona, Long idSolicitud) {
		List<PersonaSolicitud> apoderados = manejadorSolicitudPersona
				.consultarApoderadosPorIdPersonaEnCaso(persona.getIdPersona(), idSolicitud);
		for (PersonaSolicitud sp : apoderados) {
			sp.setIdPersonaApoderado(null);
			manejadorSolicitudPersona.actualizar(sp);
		}
	}

	private Map<Long, RolPersonaCaso> mapearRoles(List<RolPersonaCaso> lstRolPersonaCaso, List<Persona> partesCaso) {
		Map<Long, RolPersonaCaso> map = new HashMap<>();
		for (RolPersonaCaso rolPersonaCaso : lstRolPersonaCaso) {
			Long idPersona = rolPersonaCaso.getPersona().getIdPersona();
			partesCaso.add(transformarEntidadSinDependencias(rolPersonaCaso.getPersona()));
			map.put(idPersona, rolPersonaCaso);
		}
		return map;
	}

	@Override
	public List<FormularioParteDTO> consultarPartesCaso(Long idCaso) {
		Caso caso = manejadorCaso.buscar(idCaso);
		Servicio servicio = manejadorServicio.buscar(caso.getIdServicio());	
		boolean isConciliacion = false; 
		boolean isEquidad = false;
		if(servicio != null && servicio.getTipo().equals(UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS)) {
			isConciliacion = true;
			logger.info("BanderaConciliacion");
		}else if (servicio != null && servicio.getTipo().equals(UtilDominios.TIPO_SERVICIO_CONVIVENCIA)) {
			isEquidad = true;
			logger.info("BanderaEquidad");
		}
		List<String> lstRoles = obtenerRolesParte(isConciliacion, isEquidad);
		List<RolPersonaCaso> lstRolPersonaCaso = manejadorRolPersonaCaso.consultarPersonasAsignadasCasoPorRol(idCaso,
				lstRoles);
		List<Persona> partesCaso = new ArrayList<>();
		Map<Long, RolPersonaCaso> rpcMap = mapearRoles(lstRolPersonaCaso, partesCaso);
		
		List<FormularioParteDTO> partesApoderadoDemandante = new ArrayList<FormularioParteDTO>();
		List<FormularioParteDTO> partesDemandado = new ArrayList<FormularioParteDTO>();
		List<FormularioParteDTO> partesApoderadoDemandado = new ArrayList<FormularioParteDTO>();
		List<FormularioParteDTO> partesProcuradoJudicial = new ArrayList<FormularioParteDTO>();
		List<FormularioParteDTO> partesCuradorAdLiterm = new ArrayList<FormularioParteDTO>();
		List<FormularioParteDTO> partesAgencia = new ArrayList<FormularioParteDTO>();
		List<FormularioParteDTO> partesRepresentanteDeParte = new ArrayList<FormularioParteDTO>();

		List<FormularioParteDTO> parteDTOs = new ArrayList<FormularioParteDTO>();
		DominioDTO transformador = new DominioDTO();
		for (Persona it : partesCaso) {
			FormularioParteDTO formularioParteDTO = new FormularioParteDTO();
			formularioParteDTO.setIdPersona(it.getIdPersona());
			formularioParteDTO.setPrimerNombre(it.getPrimerNombreORazonSocial());
			formularioParteDTO.setSegundoNombre(it.getSegundoNombre());
			formularioParteDTO.setPrimerApellidoORazonSocial(it.getPrimerApellido());
			formularioParteDTO.setSegundoApellido(it.getSegundoApellido());
			formularioParteDTO.setNombreCompleto(it.getNombreCompleto());
			formularioParteDTO.setNumeroIdentificacion(it.getNumeroDocumento());
			formularioParteDTO.setIdCaso(idCaso);
			formularioParteDTO.setNacionalidad(it.getIdPaisOrigen());
			Dominio dominioTipoPersona = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_PERSONA,
					it.getTipoPersona());
			formularioParteDTO.setTipoPersona(transformador.transformarEntidadSinDependencias(dominioTipoPersona));
			Dominio dominioTipoDocumento = dominioFacade
					.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA, it.getTipoDocumento());
			formularioParteDTO
					.setTipoIdentificacion(transformador.transformarEntidadSinDependencias(dominioTipoDocumento));
			RolPersonaCaso rol = rpcMap.get(it.getIdPersona());
			String nombreRol = rol.getRol().getNombre();
			logger.info("nombre rol: "+ nombreRol);
			Dominio dominioRol;
			if (!isConciliacion) {
				if(!isEquidad) {
					dominioRol = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ROL_PERSONA_PARTE,
						nombreRol);
				}else {
					dominioRol = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ROL_PERSONA_PARTE_EQUIDAD,
							nombreRol);
				}
				
			} else {
				dominioRol = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ROL_PERSONA_PARTE_CON,
						nombreRol);
			}

			formularioParteDTO.setRol(transformador.transformarEntidadSinDependencias(dominioRol));
			if (nombreRol != null) {
				String nrol = nombreRol;
				if(isConciliacion) {
					nrol =obtenerRolParteRepresentadaConciliacion(nombreRol);
				}else if(!isEquidad) {
					nrol = obtenerRolParteRepresentadaArbitraje(nombreRol);
				}
				logger.info("nrol rol: "+ nrol);
				List<Persona> personasRepresentadas = manejadorPersona.getConsultarPartesCasoPorRolApoderado(idCaso,
						nrol, formularioParteDTO.getIdPersona());
				formularioParteDTO.setRepresentada((List<Persona>) transformarEntidadesColeccionSinDependencias(
						personasRepresentadas, new ArrayList<Persona>()));
				// llenamos la lista con todos los roles de partes
				List<Dominio> roles = dominioFacade.getDominios(UtilDominios.DOMINIO_ROL_PERSONA_PARTE);
				roles.addAll(dominioFacade.getDominios(UtilDominios.DOMINIO_ROL_PERSONA_PARTE_CON));
				roles.addAll(dominioFacade.getDominios(UtilDominios.DOMINIO_ROL_PERSONA_PARTE_EQUIDAD));
				for (Dominio obj : roles) {
					if (obj.getDominioPK().getCodigo().equals(nombreRol)) {
						parteDTOs.add(formularioParteDTO);
						break;
					}
				}
//				switch (nombreRol) {
//				case UtilDominios.ROL_PERSONA_CONVOCANTE:
//					partesDemandante.add(formularioParteDTO);
//					break;
//				case UtilDominios.ROL_PERSONA_APODERADO_CONVOCANTE:
//					partesApoderadoDemandante.add(formularioParteDTO);
//					break;
//				case UtilDominios.ROL_PERSONA_CONVOCADO:
//					partesDemandado.add(formularioParteDTO);
//					break;
//				case UtilDominios.ROL_PERSONA_APODERADO_CONVOCADO:
//					partesApoderadoDemandado.add(formularioParteDTO);
//					break;
//				case UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE:
//					partesDemandante.add(formularioParteDTO);
//					break;
//				case UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE:
//					partesApoderadoDemandante.add(formularioParteDTO);
//					break;
//				case UtilDominios.ROL_PERSONA_PARTE_DEMANDADA:
//					partesDemandado.add(formularioParteDTO);
//					break;
//				case UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO:
//					partesApoderadoDemandado.add(formularioParteDTO);
//					break;
//				case UtilDominios.ROL_PERSONA_PARTE_PROCURADOR_JUDICIAL:
//					partesProcuradoJudicial.add(formularioParteDTO);
//					break;
//				case UtilDominios.ROL_PERSONA_PARTE_CURADOR_AD_LITERM:
//					partesCuradorAdLiterm.add(formularioParteDTO);
//					break;
//				case UtilDominios.ROL_PERSONA_PARTE_AGENCIA:
//					partesAgencia.add(formularioParteDTO);
//					break;
//				case UtilDominios.ROL_PERSONA_REPRESENTANTE_DE_PARTE:
//					partesRepresentanteDeParte.add(formularioParteDTO);
//					break;
//				default:
//					break;
//				}
			}
		}
//		parteDTOs.addAll(partesDemandante);
//		parteDTOs.addAll(partesApoderadoDemandante);
//		parteDTOs.addAll(partesDemandado);
//		parteDTOs.addAll(partesApoderadoDemandado);
//		parteDTOs.addAll(partesProcuradoJudicial);
//		parteDTOs.addAll(partesCuradorAdLiterm);
//		parteDTOs.addAll(partesAgencia);
//		parteDTOs.addAll(partesRepresentanteDeParte);
		rpcMap.clear();
		partesDemandado.clear();
		partesApoderadoDemandante.clear();
		partesDemandado.clear();
		partesApoderadoDemandado.clear();
		partesProcuradoJudicial.clear();
		partesCuradorAdLiterm.clear();
		partesAgencia.clear();
		partesRepresentanteDeParte.clear();
		return parteDTOs;
	}

	private String obtenerRolParteRepresentadaArbitraje(String nombreRol) {
		return (UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO.equals(nombreRol)
				? UtilDominios.ROL_PERSONA_PARTE_DEMANDADA
				: UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
	}

	private String obtenerRolParteRepresentadaConciliacion(String nombreRol) {
		return (UtilDominios.ROL_PERSONA_APODERADO_CONVOCADO.equals(nombreRol) ? UtilDominios.ROL_PERSONA_CONVOCADO
				: UtilDominios.ROL_PERSONA_CONVOCANTE);
	}
		

	private List<String> obtenerRolesParte(boolean isConciliacion, boolean isEquidad) {
		List<String> lstRoles = new ArrayList<>();
		if (isConciliacion) {
			List<Dominio> roles = dominioFacade.getDominios(UtilDominios.DOMINIO_ROL_PERSONA_PARTE_CON);
			for (Dominio obj : roles) {
				lstRoles.add(obj.getDominioPK().getCodigo());
			}
		} else if(isEquidad) {
			List<Dominio> roles = dominioFacade.getDominios(UtilDominios.DOMINIO_ROL_PERSONA_PARTE_EQUIDAD);
			for (Dominio obj : roles) {
				lstRoles.add(obj.getDominioPK().getCodigo());
			}
		}
		else {		
			List<Dominio> roles = dominioFacade.getDominios(UtilDominios.DOMINIO_ROL_PERSONA_PARTE);
			for (Dominio obj : roles) {
				lstRoles.add(obj.getDominioPK().getCodigo());
			}
		}
		return lstRoles;
	}

	@Override
	public void crearCorreoPersona(String correoElectronico, String tipoCorreo, Long idPersona) {
		CorreoElectronico electronico = new CorreoElectronico();
		CorreoElectronico correoExiste = manejadorCorreo.consultarPorTipoCorreoIdPersona(idPersona, tipoCorreo);
		if (correoExiste != null) {
			electronico.setIdCorreo(correoExiste.getIdCorreo());
		}
		electronico.setDireccion(correoElectronico);
		electronico.setTipo(tipoCorreo);
		String usuarioActual = appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null
						? appContext.getContextoSesion().getNombreUsuario()
						: UtilConstantes.USUARIO_DEFECTO_SIMASC;
		electronico.setIdUsuarioModificacion(usuarioActual);
		electronico.setFechaUltimaModificacion(new Date());
		electronico.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		electronico.setIdPersona(idPersona);
		manejadorCorreo.crearCorreoElectronico(electronico);
	}

	/**
	 * Metodo que se encarga de crear un rol persona caso
	 * 
	 * @param caso
	 * @param rol
	 * @param persona
	 */
	@SuppressWarnings("unused")
	private void crearRolPersona(Persona persona, Rol rol) {
		RolPersona rolPersona = new RolPersona();
		rolPersona.setIdRol(rol.getIdRol());
		rolPersona.setIdPersona(persona.getIdPersona());
		rolPersona.setFechaInicioVigencia(new Date());
		rolPersona.setPersona(persona);
		rolPersona.setRol(rol);
		String usuarioActual = appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null
						? appContext.getContextoSesion().getNombreUsuario()
						: UtilConstantes.USUARIO_DEFECTO_SIMASC;
		rolPersona.setIdUsuarioModificacion(usuarioActual);
		rolPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		rolPersona.setFechaUltimaModificacion(new Date());
		manejadorRolPersona.crearRolPersona(rolPersona);
	}

	/**
	 * Metodo que se encarga de crear un rol persona caso
	 * 
	 * @param caso
	 * @param rol
	 * @param persona
	 */
	private RolPersonaCaso crearRolPersonaCaso(Caso caso, Rol rol, Persona persona) {
		RolPersonaCaso rolPersonaCaso = new RolPersonaCaso();
		rolPersonaCaso.setCaso(caso);
		rolPersonaCaso.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_NO_APLICA);
		rolPersonaCaso.setRol(rol);
		rolPersonaCaso.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		rolPersonaCaso.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
		rolPersonaCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		rolPersonaCaso.setPersona(persona);
		manejadorRolPersonaCaso.crearRolPersonaCaso(rolPersonaCaso);

		return rolPersonaCaso;
	}

	/**
	 * Metodo que se encarga de crear un rol persona caso
	 * 
	 * @param caso
	 * @param rol
	 * @param persona
	 */
	private void crearSolicitudPersonaApoderada(Long solicitudServicio, Rol rol, Persona persona, Long idRolApoderado,
			Long idPersonaApoderado) {
		PersonaSolicitud personaSolicitud = new PersonaSolicitud();
		personaSolicitud.getPersonaSolicitudPK().setIdSolicitudServicio(solicitudServicio);
		personaSolicitud.getPersonaSolicitudPK().setIdPersona(persona.getIdPersona());
		personaSolicitud.getPersonaSolicitudPK().setIdRol(rol.getIdRol());
		personaSolicitud.getPersonaSolicitudPK().setIdSolicitudServicio(solicitudServicio);
		personaSolicitud.setRol(rol);
		personaSolicitud.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		personaSolicitud.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
		personaSolicitud.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		personaSolicitud.setPersona(persona);
		personaSolicitud.setIdRolApoderado(idRolApoderado);
		personaSolicitud.setIdPersonaApoderado(idPersonaApoderado);
		manejadorPersonaSolicitud.crearPersonaSolicitud(personaSolicitud);
	}

	@Override
	public Ubicacion crearUbicacionPersona(String direccion, BigDecimal latitud, BigDecimal longitud,
			String idZonaGeografica, String tipo, Long idPersona, Long idUbicacion) throws SIMASCNegocioExcepcion {
		Ubicacion ubicacion = new Ubicacion();

		if (idUbicacion != null) {
			ubicacion.setIdUbicacion(idUbicacion);
		}

		Persona persona = manejadorPersona.buscar(idPersona);
		ubicacion.setPersona(persona);
		ubicacion.setDireccion(direccion);
		ubicacion.setLatitud(latitud);
		ubicacion.setLongitud(longitud);
		String usuarioActual = appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null
						? appContext.getContextoSesion().getNombreUsuario()
						: UtilConstantes.USUARIO_DEFECTO_SIMASC;
		ubicacion.setIdUsuarioModificacion(usuarioActual);
		ubicacion.setFechaUltimaModificacion(new Date());
		ubicacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		ubicacion.setIdZonaGeografica(idZonaGeografica);
		ubicacion.setTipo(tipo);
		ubicacion.setIdPersona(idPersona);

		return manejadorUbicacion.crearUbicacion(ubicacion);
	}

	@Override
	public Collection<Persona> consultarArbitrosPorRolCaso(Long idCaso, String nombreRol) {
		List<Persona> persona = new ArrayList<>();
		return transformarEntidadesColeccionConDependencias(
				manejadorPersona.consultarArbitrosPorRolCaso(idCaso, nombreRol), persona);
	}

	@Override
	public List<PersonaBasicaDTO> consultarPrestadoresDelServicio(String tipoServicio) {
		List<Persona> personas = manejadorPersona.consultarPrestadoresDelServicio(tipoServicio);
		List<PersonaBasicaDTO> basicaDTOs = new ArrayList<PersonaBasicaDTO>();
		for (Persona it : personas) {
			PersonaBasicaDTO personaBasicaDTO = new PersonaBasicaDTO();
			personaBasicaDTO.setIdPersona(it.getIdPersona());
			personaBasicaDTO.setNombreCompleto(it.getNombreCompleto());
			basicaDTOs.add(personaBasicaDTO);
		}
		return basicaDTOs;
	}

	@Override
	public Collection<PersonaBasicaDTO> consultarDatosBasicosPersonasPorRol(String nombreRol) {
		Collection<Persona> personas = manejadorPersona.consultarPersonasPorRol(nombreRol);
		return PersonaBasicaDTO.newListaPersonaBasicaDTO(personas);
	}

	@Override
	public Collection<PersonaBasicaDTO> consultarArbitrosPrincipales(Long idCaso, List<String> nombreRol) {
		Collection<Persona> lstArbitrosPrincipales = manejadorRolPersonaCaso.consultarArbitrosPrincipales(idCaso,
				nombreRol);
		return PersonaBasicaDTO.newListaPersonaBasicaDTO(lstArbitrosPrincipales);

	}

	@Override
	public boolean validarCompletitudPartes(Long idCaso) {
		boolean isDemandante = false;
		boolean isDemandado = false;
		List<String> lstRoles = new ArrayList<>();
		lstRoles.add(UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
		lstRoles.add(UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);

		List<RolPersonaCaso> lstRolPersonaCaso = manejadorRolPersonaCaso.consultarPersonasAsignadasCasoPorRol(idCaso,
				lstRoles);

		for (RolPersonaCaso rolPersonaCaso : lstRolPersonaCaso) {
			if (UtilConstantes.ID_SERVICIO_PERITAJE.equals(rolPersonaCaso.getCaso().getIdServicio())
					|| UtilConstantes.ID_SERVICIO_DESIGNACION_ARBITROS.equals(rolPersonaCaso.getCaso().getIdServicio()))
				isDemandado = true;
			if (UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE.equals(rolPersonaCaso.getRol().getNombre())) {
				isDemandante = true;
			} else if (UtilDominios.ROL_PERSONA_PARTE_DEMANDADA.equals(rolPersonaCaso.getRol().getNombre())) {
				isDemandado = true;
			}
		}

		return isDemandado && isDemandante;
	}

	private void actualizarPreseleccionado(Long idCaso, PreseleccionadoDTO old, GenericoDTO dto) {
		Preseleccionado preseleccionado = new Preseleccionado();
		preseleccionado.getPreseleccionadoPK()
				.setIdPersona((old != null) ? old.getIdPersona() : Long.valueOf(dto.getId()));
		preseleccionado.getPreseleccionadoPK().setIdCaso(idCaso);
		preseleccionado.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		preseleccionado.setFechaUltimaModificacion(new Date());
		preseleccionado.setEstadoRegistro(
				(old != null) ? UtilDominios.ESTADO_REGISTRO_INACTIVO : UtilDominios.ESTADO_REGISTRO_ACTIVO);

		if (dto != null && dto.getMateriasPreseleccion() != null && !dto.getMateriasPreseleccion().isEmpty()) {
			preseleccionado.setMateriasPreseleccion(UtilSimasc.listaToString(dto.getMateriasPreseleccion()));
		}

		if (dto != null && dto.getTipoPreseleccion() != null) {
			preseleccionado.setTipoPreseleccion(dto.getTipoPreseleccion());
		}
		manejadorPreseleccionado.crearPreseleccionado(preseleccionado);
	}

	@Override
	public void guardarArbitrosPreseleccionados(List<GenericoDTO> genericoList, Long idCaso) {
		try {
			List<PreseleccionadoDTO> listaPreseleccion = consultarPreseleccionadosCaso(idCaso);
			for (PreseleccionadoDTO old : listaPreseleccion)
				actualizarPreseleccionado(idCaso, old, null);

			for (GenericoDTO it : genericoList)
				actualizarPreseleccionado(idCaso, null, it);

		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
	}

	@Override
	public void eliminarArbitrosPreseleccionados(List<GenericoDTO> genericoList, Long idCaso) {
		try {
			for (GenericoDTO it : genericoList) {
				Preseleccionado preseleccionado = manejadorPreseleccionado
						.buscarPreseleccionado(Long.valueOf(it.getId()), idCaso);
				if (preseleccionado != null) {
					preseleccionado.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
					preseleccionado.setFechaUltimaModificacion(new Date());
					preseleccionado.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
					manejadorPreseleccionado.actualizar(preseleccionado);
				}
			}
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
	}

	@Override
	public List<PreseleccionadoDTO> consultarPreseleccionadosCaso(Long idCaso) {
		List<PreseleccionadoDTO> preseleccionadoDTOList = new ArrayList<PreseleccionadoDTO>();
		try {
			List<Preseleccionado> preseleccionadoList = manejadorPreseleccionado.consultarPreseleccionadosCaso(idCaso);
			for (Preseleccionado it : preseleccionadoList) {
				PreseleccionadoDTO preseleccionadoDTO = new PreseleccionadoDTO();
				preseleccionadoDTO.setIdPersona(it.getPreseleccionadoPK().getIdPersona());
				preseleccionadoDTO.setIdCaso(it.getPreseleccionadoPK().getIdCaso());
				preseleccionadoDTO.setNombreCompleto(it.getPersona().getNombreCompleto());
				preseleccionadoDTO.setTipoPreseleccion(it.getTipoPreseleccion());
				preseleccionadoDTO.setMateriasPreseleccionadasCaso(it.getMateriasPreseleccion());
				preseleccionadoDTOList.add(preseleccionadoDTO);
			}
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		return preseleccionadoDTOList;
	}

	@Override
	public ParteCasoDTO consultarParteCorreo(Long idCaso, Long idPersona, Long idAudiencia) {
		ParteCasoDTO parteCasoDTO = new ParteCasoDTO();
		try {
			RolPersonaCaso rolPersonaCaso = manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(idPersona, idCaso);

			if (rolPersonaCaso == null)
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString()));

			Caso caso = rolPersonaCaso.getCaso();
			Persona persona = rolPersonaCaso.getPersona();
			List<Audiencia> audiencias = caso.getAudienciaList();
			List<CorreoRolPersonaCaso> correoRolPersonaCasos = manejadorCorreoRolPersonaCaso
					.obtenerCorreos(UtilDominios.TIPO_ACUSE_FALLA, caso.getIdCaso(), idAudiencia, idPersona);

			parteCasoDTO.setIdPersona(idPersona);
			parteCasoDTO.setIdCaso(caso.getIdCaso());
			parteCasoDTO.setTipoCaso(caso.getServicioMateria().getServicio().getNombre());
			parteCasoDTO.setNombreCaso(caso.getNombre());
			String tipoParte = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_PARTE_CONTRAPARTE,
					rolPersonaCaso.getRol().getNombre());
			parteCasoDTO.setTipoParte(tipoParte);
			parteCasoDTO.setNombre(persona.getNombreCompleto());
			parteCasoDTO.setIdRol(rolPersonaCaso.getRol().getIdRol());
			parteCasoDTO.setIdAudiencia(idAudiencia);

			for (Audiencia it1 : audiencias) {
				if (it1.getEstadoRegistro().equalsIgnoreCase(UtilDominios.ESTADO_REGISTRO_ACTIVO)
						&& it1.getEstado().equalsIgnoreCase(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE)) {
					parteCasoDTO.setFechaAudiencia(it1.getHoraInicio());
				}
			}

			parteCasoDTO.setNombreSede(caso.getSede().getNombre());
			StringBuilder direcciones = new StringBuilder();
			StringBuilder telefonos = new StringBuilder();
			StringBuilder celulares = new StringBuilder();

			for (Ubicacion it2 : persona.getUbicacionList()) {
				direcciones.append(it2.getDireccion()).append(UtilConstantes.CARACTER_ESPACIO);
			}
			parteCasoDTO.setDirecciones(
					direcciones.toString().trim().replaceAll("\\s", UtilConstantes.CARACTER_GUION_ALTO));

			for (Telefono it3 : persona.getTelefonoList()) {
				if (it3.getTipoTelefono().equalsIgnoreCase(UtilDominios.TIPO_TELEFONO_FIJO))
					telefonos.append(it3.getNumero()).append(UtilConstantes.CARACTER_ESPACIO);
				else if (it3.getTipoTelefono().equalsIgnoreCase(UtilDominios.TIPO_TELEFONO_CELULAR))
					celulares.append(it3.getNumero()).append(UtilConstantes.CARACTER_ESPACIO);
			}
			parteCasoDTO
					.setTelefonos(telefonos.toString().trim().replaceAll("\\s", UtilConstantes.CARACTER_GUION_ALTO));
			parteCasoDTO
					.setCelulares(celulares.toString().trim().replaceAll("\\s", UtilConstantes.CARACTER_GUION_ALTO));

			List<CorreoElectronicoDTO> correos = new ArrayList<>();

			for (CorreoElectronico it4 : persona.getCorreoElectronicoList()) {
				CorreoElectronicoDTO electronicoDTO = new CorreoElectronicoDTO();
				iterador_dos: for (CorreoRolPersonaCaso it5 : correoRolPersonaCasos) {
					if (it4.getDireccion().equalsIgnoreCase(it5.getCorreoReceptor())) {
						electronicoDTO.setTipoAcuse(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_ACUSE,
								UtilDominios.TIPO_ACUSE_FALLA));
						electronicoDTO.setIdCorreo(it4.getIdCorreo());
						electronicoDTO.setDireccion(it4.getDireccion());
						correos.add(electronicoDTO);
						break iterador_dos;
					}
				}
			}
			parteCasoDTO.setCorreosElectronicos(correos);
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()));
		}
		return parteCasoDTO;
	}

	@Override
	public List<PersonaBasicaDTO> consultaSecretarioActivoCaso(Long idCaso) {
		List<PersonaBasicaDTO> lstPersonaBasica = new ArrayList<>();
		List<Persona> lstPersona = (List<Persona>) transformarEntidadesColeccionSinDependencias(
				manejadorPersona.consultaSecretarioActivoCaso(idCaso), new ArrayList<Persona>());
		for (Persona persona : lstPersona) {
			PersonaBasicaDTO personaBasica = new PersonaBasicaDTO();
			personaBasica.setIdPersona(persona.getIdPersona());
			personaBasica.setNombreCompleto(persona.getNombreCompleto());
			lstPersonaBasica.add(personaBasica);
		}
		return lstPersonaBasica;
	}

	@Override
	public Persona getPersonaSistema() {
		return manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA,
				UtilDominios.ESTADO_PERSONA_ACTIVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> consultarArbitrosCasoPorEstados(Long idCaso, List<String> estados) {
		PersonaDTO personaDTO = new PersonaDTO();
		return (List<Persona>) personaDTO.transformarColeccionEntidadesConDependencias(
				manejadorRolPersonaCaso.consultarArbitrosCasoPorEstados(idCaso, estados));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> consultarPartesDelCaso(Long idCaso) {
		List<String> listaRoles = new ArrayList<>();
		listaRoles.add(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
		listaRoles.add(UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
		listaRoles.add(UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
		listaRoles.add(UtilDominios.ROL_PERSONA_REPRESENTANTE_DE_PARTE);
		listaRoles.add(UtilDominios.ROL_PERSONA_TERCERO);
		listaRoles.add(UtilDominios.ROL_PERSONA_EXTERNO_AUTORIDAD_JUDICIAL);
		listaRoles.add(UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
		listaRoles.add(UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
		listaRoles.add(UtilDominios.ROL_PERSONA_PARTE_PROCURADOR_JUDICIAL);
		listaRoles.add(UtilDominios.ROL_PERSONA_PARTE_AGENCIA);
		listaRoles.add(UtilDominios.ROL_PERSONA_PARTE_CURADOR_AD_LITERM);
		listaRoles.add(UtilDominios.ROL_PERSONA_ABOGADO);

		List<Persona> partesDelCaso = (List<Persona>) new PersonaDTO().transformarColeccionEntidadesConDependencias(
				manejadorRolPersonaCaso.consultarPartesDelCaso(idCaso, listaRoles));

		for (Persona persona : partesDelCaso) {
			List<RolPersonaCaso> rolesPersonaCaso = new ArrayList<RolPersonaCaso>();
			for (RolPersonaCaso rolPersonaCaso : persona.getRolPersonaCasoList()) {
				if (rolPersonaCaso.getRolPersonaCasoPK() != null
						&& rolPersonaCaso.getRolPersonaCasoPK().getIdCaso() != null
						&& rolPersonaCaso.getRolPersonaCasoPK().getIdCaso().equals(idCaso)) {
					rolesPersonaCaso.add(rolPersonaCaso);
				}
			}
			persona.setRolPersonaCasoList(rolesPersonaCaso);
		}

		return partesDelCaso;
	}

	public Map<String, Object> obtenerListasParaAsignarArbitro(@PathParam("idCaso") Long idCaso) {
		Map<String, Object> listas = new HashMap<String, Object>();
		DominioDTO nombradoPorCCB = new DominioDTO().transformarSinDependencias(dominioFacade
				.getObtenerDominio(UtilDominios.METODOS_DE_NOMBRAMIENTO, UtilDominios.NOMBRAMIENTO_POR_LA_CCB));
		List<DominioDTO> metodosNombramiento = new DominioDTO()
				.transformarColeccionSinDependencias(dominioFacade.getDominios(UtilDominios.METODOS_DE_NOMBRAMIENTO));
		metodosNombramiento.remove(nombradoPorCCB);

		ArrayList<Persona> tercerosCaso = (ArrayList<Persona>) this.consultarPorRolCaso(idCaso,
				UtilDominios.ROL_PERSONA_TERCERO);
		ArrayList<Persona> autJudicialCaso = (ArrayList<Persona>) this.consultarPorRolCaso(idCaso,
				UtilDominios.ROL_PERSONA_TERCERO);

		listas.put("metodosNombramientos", metodosNombramiento);
		listas.put("terceros", tercerosCaso);
		listas.put("autoridadesJudiciales", autJudicialCaso);

		return listas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade#
	 * validarIdentificacionPersona(com.ccb.simasc.transversal.dto.formularios.
	 * PersonaBasicaDTO)
	 */
	public RadicacionSolicitudDTO validarIdentificacionPersona(PersonaBasicaDTO personaBasicaDTO) {
		Persona persona = consultarPersonaPorIdentificacion(personaBasicaDTO.getTipoDocumento(),
				personaBasicaDTO.getNumeroDocumento(), true);

		if (persona == null) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString()));
		}

		PersonaBasicaDTO personaBasicaSolicitud = persona.convertirEntidadToPersonaBasicoDTO(persona);
		RadicacionSolicitudDTO radicacionSolicitudDTO = new RadicacionSolicitudDTO();

		List<CorreoElectronicoDTO> correosEletronicosDTO = (List<CorreoElectronicoDTO>) new CorreoElectronicoDTO()
				.transformarColeccionConDependencias(
						manejadorCorreo.consultaCorreosPersona(personaBasicaSolicitud.getIdPersona()));

		radicacionSolicitudDTO.setPersonaBasicaDTO(personaBasicaSolicitud);
		radicacionSolicitudDTO.setCorreoElectronicoPersona(correosEletronicosDTO);

		return radicacionSolicitudDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade#
	 * consultarPersonaPorIdentificacion(String, String, boolean)
	 * 
	 */
	@Override
	public Persona consultarPersonaPorIdentificacion(String tipoDocumento, String numeroDocumento,
			boolean transformarDependencias) {
		Persona persona = manejadorPersona.consultarPersonaPorIdentificacion(tipoDocumento, numeroDocumento);

		if (persona != null && transformarDependencias) {
			persona = transformarEntidadConDependencias(persona);
		}
		return persona;
	}

	/**
	 * Método encargado de crear una Persona Solicitud de servicio
	 * 
	 * ARB-F-109
	 * 
	 * @param idRol
	 * @param idPersona
	 * @param idSolicitudServicio
	 * @return
	 */
	private PersonaSolicitud crearPersonaSolicitud(Long idRol, Long idPersona, Long idSolicitudServicio) {
		// Valida si la persona ya existe asociada a la solicitud sin importar
		// el rol

		// Elimina informacion de la tabla APODERADOS_SOLICITUD

		apoderadosSolicitudFacade.eliminarApoderados(idSolicitudServicio, idPersona);

		List<PersonaSolicitud> personaListaAnterior = manejadorPersonaSolicitud
				.consultarPersonaSolicitud(idSolicitudServicio, idPersona, null);

		// Si la persona ya existe asociada a la solicitud se elimina
		// logicamente
		for (PersonaSolicitud personaSolicitud : personaListaAnterior) {
			if (!UtilDominios.ROL_PERSONA_SOLICITANTE.equals(personaSolicitud.getRol().getNombre()))
				manejadorPersonaSolicitud.eliminarPersonaSolicitud(personaSolicitud);
		}

		PersonaSolicitud personaSolicitud = new PersonaSolicitud();

		// Campos obligatorios
		personaSolicitud.getPersonaSolicitudPK().setIdRol(idRol);
		personaSolicitud.getPersonaSolicitudPK().setIdPersona(idPersona);
		personaSolicitud.getPersonaSolicitudPK().setIdSolicitudServicio(idSolicitudServicio);
		personaSolicitud.setFechaUltimaModificacion(new Date());
		personaSolicitud.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return manejadorPersonaSolicitud.crearPersonaSolicitud(personaSolicitud);
	}

	/**
	 * Método encargado de la asignación de los apoderados de las partes de una
	 * solicitud de servicio
	 * 
	 * @param nombreRol
	 * @param formularioParteDTO
	 * @param idSolicitudServicio
	 * @param rolApoderado
	 * @param persona
	 */
	private void asignarApoderadosSolicitud(FormularioParteDTO formularioParteDTO, Rol rolApoderado, Persona persona) {

		if (rolApoderado.getNombre().equals(UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO)
				|| rolApoderado.getNombre().equals(UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE)
				|| rolApoderado.getNombre().equals(UtilDominios.ROL_PERSONA_APODERADO_DEUDOR)
				|| rolApoderado.getNombre().equals(UtilDominios.ROL_PERSONA_APODERADO_ACREEDOR)) {

			Long idSolicitudServicio = formularioParteDTO.getIdSolicitudServicio();
			Long idPersona = (null != formularioParteDTO.getIdPersona()) ? formularioParteDTO.getIdPersona()
					: persona.getIdPersona();
			Long idRol = rolApoderado.getIdRol();

			String parteRepresentada = "";

			if (rolApoderado.getNombre().equals(UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO)) {
				parteRepresentada = UtilDominios.ROL_PERSONA_PARTE_DEMANDADA;
			} else if (rolApoderado.getNombre().equals(UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE)) {
				parteRepresentada = UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE;
			} else if (rolApoderado.getNombre().equals(UtilDominios.ROL_PERSONA_APODERADO_DEUDOR)) {
				parteRepresentada = UtilDominios.ROL_PERSONA_DEUDOR;
			} else if (rolApoderado.getNombre().equals(UtilDominios.ROL_PERSONA_APODERADO_ACREEDOR)) {
				parteRepresentada = UtilDominios.ROL_PERSONA_ACREEDOR;
			}

			// Obtener el rol de la parte representada
			Rol rolParteRepresentada = manejadorRol.consultarRolPorNombre(parteRepresentada);

			List<PersonaSolicitud> personasSolicitudRepresentadas = new ArrayList<>();

			// Insertar los registros en la tabla de PersonaSolicitud
			for (Persona personaRepresentada : formularioParteDTO.getRepresentada()) {
				PersonaSolicitud representada = crearPersonaSolicitud(rolParteRepresentada.getIdRol(),
						personaRepresentada.getIdPersona(), idSolicitudServicio);
				personasSolicitudRepresentadas.add(representada);
			}

			PersonaSolicitud apoderado = manejadorPersonaSolicitud
					.buscar(new PersonaSolicitudPK(idRol, idPersona, idSolicitudServicio));

			// obtener la lista de representados actuales.
			List<ApoderadosSolicitud> representadosList = manejadorApoderadosSolicitud
					.obtenerRepresentadosPorApoderado(idRol, idPersona, idSolicitudServicio);

			List<ApoderadosSolicitud> nuevosRepresentados = crearRepresentadosSolicitud(apoderado,
					personasSolicitudRepresentadas, idSolicitudServicio);

			if (!personasSolicitudRepresentadas.isEmpty()) {
				eliminarRepresentadosSolicitud(nuevosRepresentados, representadosList);
			}

		}

	}

	@Override
	public void almacenarPreferencias(PreferenciasDTO preferenciasDTO) {
		Persona persona = manejadorPersona.buscar(preferenciasDTO.getIdPersona());
		if (persona == null) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR527.toString()));
		}
		persona.setFechaUltimaModificacion(new Date());
		persona.setIdUsuarioModificacion(preferenciasDTO.getUsuario());
		persona.setPreferenciasDeRefrigerio(preferenciasDTO.getPreferencia());
		manejadorPersona.actualizar(persona);

	}

	/**
	 * Método encargado de adicionar la información de contacto (Dirección,
	 * teléfonos, correos electronicos, etc.) a una persona en creación
	 * 
	 * @param formularioParteDTO
	 * @param persona
	 */
	private void adicionarInformacionContactoPersona(FormularioParteDTO formularioParteDTO, Persona persona,
			RolPersonaCaso rolPersonaCaso, PersonaSolicitud personaSolicitud, Persona personaExiste) {

		//Direcciones
		procesarListaUbicaciones(formularioParteDTO, rolPersonaCaso, personaSolicitud);
		
		//Telefonos	
		procesarTelefonos(formularioParteDTO, persona, personaExiste);
		
		/*
		 * Correos Electronicos Si la persona cuenta con un Rol Persona Caso asociado
		 * significa que la asignación de correos electronicos se realiza teniendo en
		 * cuenta un Caso Radicado. Por otra parte, si la persona no cuenta con un Rol
		 * Persona Caso y cuenta con una Persona Solicitud, se asume que la asignación
		 * de correos electronicos se debe realizar a partir de una Solicitud de
		 * Servicio.
		 */
		asociarCorreosElectronicosPersona(formularioParteDTO, rolPersonaCaso, personaSolicitud);

	}
	
	public void procesarListaUbicaciones(FormularioParteDTO formularioParteDTO, RolPersonaCaso rolPersonaCaso, PersonaSolicitud personaSolicitud) {	
		
		if (rolPersonaCaso != null) {
			ubicacionRolPersonaCasoFacade.asociarUbicacionesRolPersonaCaso(formularioParteDTO.getLstUbicacion(),
					rolPersonaCaso);
		} else if (personaSolicitud != null) {
			ubicacionPersonaSolicitudFacade.asociarUbicacionesPersonaSolicitud(formularioParteDTO.getLstUbicacion(),
					personaSolicitud);
		}
	}
	
	public void procesarTelefonos(FormularioParteDTO formularioParteDTO, Persona persona, Persona personaExiste) {
		
		int i = 0;
		
		if(personaExiste != null && personaExiste.getTelefonoList() != null) {
		
			for (Telefono telefono : telefonoFacade.transformarEntidadesColeccionSinDependencias(personaExiste.getTelefonoList(),
					new ArrayList<Telefono>())) {
				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(telefono.getEstadoRegistro())) {
					
					String tipoTelefono = telefono.getTipoTelefono();
					String numeroTelefono = telefono.getNumero();
					
					validarPuntosTelefonoCelular(tipoTelefono, numeroTelefono, formularioParteDTO);
					validarPuntosTelefonoFijo(i, tipoTelefono, numeroTelefono, formularioParteDTO);
					i++;
				}
			}
		}				
		crearTelefonosFijoCelular(formularioParteDTO, persona);			
	}
	
	public void validarPuntosTelefonoCelular(String tipoTelefono, String numeroTelefono, FormularioParteDTO formularioParteDTO) {
		
		if (tipoTelefono.equals(UtilDominios.TIPO_TELEFONO_CELULAR) &&
			UtilMascaraTexto.hasOnlyDots(formularioParteDTO.getNumeroCelular())) {			
			formularioParteDTO.setNumeroCelular(numeroTelefono);						
		}
	}
	
	public void validarPuntosTelefonoFijo(int indice, String tipoTelefono, String numeroTelefono, FormularioParteDTO formularioParteDTO) {
		
		if (indice == 0 && tipoTelefono.equals(UtilDominios.TIPO_TELEFONO_FIJO)
				&& UtilMascaraTexto.hasOnlyDots(formularioParteDTO.getNumeroTelefonoUno())) {					
			
			formularioParteDTO.setNumeroTelefonoUno(numeroTelefono);
			
		} else if (indice == 1 && tipoTelefono.equals(UtilDominios.TIPO_TELEFONO_FIJO)
				&& UtilMascaraTexto.hasOnlyDots(formularioParteDTO.getNumeroTelefonoDos())) {					
			
			formularioParteDTO.setNumeroTelefonoDos(numeroTelefono);					
		}
	}
	
	public void eliminarTelefonos(FormularioParteDTO formularioParteDTO, Persona persona) {
		
		if (formularioParteDTO.getIdPersona() != null) {
			telefonoFacade.eliminarTelefonosPersona(formularioParteDTO.getIdPersona());
		} else if (persona.getIdPersona() != null) {
			telefonoFacade.eliminarTelefonosPersona(persona.getIdPersona());			
		}
	}
	
	public void crearTelefonosFijoCelular(FormularioParteDTO formularioParteDTO, Persona persona) {
		
		Long idPersona = persona.getIdPersona();
		
		// Creación de Telefono :: FIJO
		crearTelefonoPorPersona(formularioParteDTO.getNumeroTelefonoUno(), idPersona, UtilDominios.TIPO_TELEFONO_FIJO);
		
		// Creación de Telefono :: FIJO
		crearTelefonoPorPersona(formularioParteDTO.getNumeroTelefonoDos(), idPersona, UtilDominios.TIPO_TELEFONO_FIJO);		

		// Creación de Telefono :: CELULAR
		crearTelefonoPorPersona(formularioParteDTO.getNumeroCelular(), idPersona, UtilDominios.TIPO_TELEFONO_CELULAR);		
	}
	
	public void crearTelefonoPorPersona(String numeroTelefono, Long idPersona, String tipo) {
		
		if (numeroTelefono != null && !numeroTelefono.isEmpty() &&
				!UtilMascaraTexto.hasOnlyDots(numeroTelefono)) {
			
			Long idTelefono = telefonoFacade.buscarTelefonosPersona(idPersona, tipo);
			
			telefonoFacade.crearTelefonoPersona(numeroTelefono,
					tipo, idPersona, (idTelefono.equals(0L) ? null : idTelefono),
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
		}
	}

	/**
	 * Método para realizar la creacion de los correos de la parte
	 * 
	 * @param formularioParteDTO
	 * @param rolPersonaCaso
	 * @param personaSolicitud
	 */
	private void asociarCorreosElectronicosPersona(FormularioParteDTO formularioParteDTO, RolPersonaCaso rolPersonaCaso,
			PersonaSolicitud personaSolicitud) {
		// Lista a Procesar
		List<CorreoElectronicoDTO> correoElectronicoList = new ArrayList<>();		
		if (formularioParteDTO.getCorreosElectronicos() != null
				&& !formularioParteDTO.getCorreosElectronicos().isEmpty()) {
			correoElectronicoList.addAll(formularioParteDTO.getCorreosElectronicos());
		} else if (formularioParteDTO.getEmailUno() != null || formularioParteDTO.getEmailDos() != null
				|| formularioParteDTO.getEmailTres() != null) {
			
			Long idPersona = formularioParteDTO.getIdPersona();
			
			// Asociar email uno como Correo Electronico Principal												
			asociarEmailSegunTipo(formularioParteDTO.getEmailUno(), 
					UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL, correoElectronicoList, idPersona);

			// Asociar email dos como Correo Electronico Secundario
			asociarEmailSegunTipo(formularioParteDTO.getEmailDos(),  
					UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO, correoElectronicoList, idPersona);			
						
			// Asociar email tres como Correo Electronico Terciario
			asociarEmailSegunTipo(formularioParteDTO.getEmailTres(),  
					UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_TERCIARIO, correoElectronicoList, idPersona);										
		}
		
		// Procesar lista de Correos Electronicos
		if (rolPersonaCaso != null) {
			correoElectronicoRolPersonaCasoFacade.asociarCorreosElectronicosRolPersonaCaso(correoElectronicoList,
					rolPersonaCaso);
		} else if (personaSolicitud != null) {
			correoElectronicoPersonaSolicitudFacade.asociarCorreosElectronicosPersonaSolicitud(correoElectronicoList,
					personaSolicitud);
		}
	}
	
	public void asociarEmailSegunTipo(String emailActual, String tipo, List<CorreoElectronicoDTO> correoElectronicoList, Long idPersona) {
		
		CorreoElectronicoDTO correoElectronico = new CorreoElectronicoDTO();	
		String emailAnterior = "";
		
		if(idPersona != null) {
			List<CorreoElectronico> correoPersona = manejadorCorreo.consultaCorreosPersona(idPersona, false, tipo);
			if(correoPersona.size()>0) {
				emailAnterior = correoPersona.get(0).getDireccion();
			}else {
				emailAnterior = emailActual;
			}
		}		
		
		if(emailActual != null && !UtilMascaraTexto.hasEmailOnlyDots(emailActual)) {
			correoElectronico.setDireccion(emailActual);
		}
		
		if(emailAnterior != null && !UtilMascaraTexto.hasEmailOnlyDots(emailAnterior)) {
			correoElectronico.setDireccionAnterior(emailAnterior);
		}
		
		if(correoElectronico.getDireccion() != null || correoElectronico.getDireccionAnterior() != null) {
			correoElectronico.setTipo(tipo);
			correoElectronicoList.add(correoElectronico);
		}	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade#
	 * validarIdentificacionPagadorSolicitud(com.ccb.simasc.transversal.dto.
	 * formularios.PersonaBasicaDTO)
	 */
	@Override
	public FormularioDatosClienteDTO validarIdentificacionPagadorSolicitud(PersonaBasicaDTO personaBasicaDTO) {
		FormularioDatosClienteDTO formularioDatosClienteDTO = null;

		// 1. Verificación de existencia de la persona en SIREP
		formularioDatosClienteDTO = integracionSWFacade.consultarDatosBasicosClienteSirep(
				personaBasicaDTO.getTipoDocumento(), personaBasicaDTO.getNumeroDocumento());

		// 2. Si el usuario no existe en SIREP se consulta en el sistema
		// SIMASC
		if (formularioDatosClienteDTO == null) {
			Persona persona = consultarPersonaPorIdentificacion(personaBasicaDTO.getTipoDocumento(),
					personaBasicaDTO.getNumeroDocumento(), false);

			// 2.1. Si el usuario existe en SIMASC se recuperan los datos
			// básicos de cliente
			if (persona != null) {
				formularioDatosClienteDTO = transformarPersonaAFormularioDatosClienteDTO(persona);
			} else {
				formularioDatosClienteDTO = new FormularioDatosClienteDTO();
			}
		}
		formularioDatosClienteDTO.setNumeroIdentificacion(personaBasicaDTO.getNumeroDocumento());
		formularioDatosClienteDTO.setTipoIdentificacion(personaBasicaDTO.getTipoDocumento());

		return formularioDatosClienteDTO;
	}

	/**
	 * Método encargado de transformar una persona en un objeto mas simple y sin
	 * dependencias
	 * 
	 * TRANS-F-041
	 * 
	 * @param persona
	 * @return
	 */
	private FormularioDatosClienteDTO transformarPersonaAFormularioDatosClienteDTO(Persona persona) {
		FormularioDatosClienteDTO formularioDatosClienteDTO = new FormularioDatosClienteDTO();

		formularioDatosClienteDTO.setIdPersona(persona.getIdPersona());
		formularioDatosClienteDTO.setPrimerNombre(persona.getPrimerNombreORazonSocial());
		formularioDatosClienteDTO.setSegundoNombre(persona.getSegundoNombre());
		formularioDatosClienteDTO.setPrimerApellido(persona.getPrimerApellido());
		formularioDatosClienteDTO.setSegundoApellido(persona.getSegundoApellido());
		formularioDatosClienteDTO.setTipoIdentificacion(persona.getTipoDocumento());

		Dominio dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA,
				persona.getTipoDocumento());
		formularioDatosClienteDTO.setDescTipoIdentificacion(dominio != null ? dominio.getDescripcion() : null);

		formularioDatosClienteDTO.setNumeroIdentificacion(persona.getNumeroDocumento());

		for (Telefono telefono : persona.getTelefonoList()) {
			if (UtilDominios.TIPO_TELEFONO_FIJO.equals(telefono.getTipoTelefono())) {
				formularioDatosClienteDTO.setNumeroTelefono(telefono.getNumero());
			}
		}

		if (persona.getCiudadDelDocumento() != null) {
			formularioDatosClienteDTO.setCiudad(persona.getCiudadDelDocumento());
			ZonaGeografica zonaGeografica = zonaGeograficaFacade.buscar(persona.getCiudadDelDocumento());
			if (zonaGeografica != null)
				formularioDatosClienteDTO.setCiudadIdentificacion(
						zonaGeograficaFacade.transformarEntidadSinDependencias(zonaGeografica));
		}

		UbicacionDTO ubicacion = manejadorUbicacion.consultarPrimeraUbicacionPersona(persona.getIdPersona());
		if (ubicacion != null) {
			formularioDatosClienteDTO.setCiudad(ubicacion.getIdZonaGeografica());
			formularioDatosClienteDTO.setDireccion(ubicacion.getDireccion());
		}

		for (CorreoElectronico correoElectronico : persona.getCorreoElectronicoList()) {
			if (UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL.equals(correoElectronico.getTipo())) {
				formularioDatosClienteDTO.setEmail(correoElectronico.getDireccion());
			}
		}

		return formularioDatosClienteDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade#
	 * crearDatosBasicosClienteSIREP(com.ccb.simasc.transversal.dto.formularios.
	 * FormularioDatosClienteDTO)
	 */
	@Override
	public Map<String, String> crearDatosBasicosClienteSIREP(FormularioDatosClienteDTO formularioDatosClienteDTO) {
		PersonaDTO persona = new PersonaDTO();
		persona.setIdPersona(formularioDatosClienteDTO.getIdPersona());
		persona.setTipoDocumento(formularioDatosClienteDTO.getTipoIdentificacion());
		persona.setNumeroDocumento(formularioDatosClienteDTO.getNumeroIdentificacion());
		persona.setPrimerNombreORazonSocial(formularioDatosClienteDTO.getPrimerNombre());
		persona.setSegundoNombre(formularioDatosClienteDTO.getSegundoNombre());
		persona.setPrimerApellido(formularioDatosClienteDTO.getPrimerApellido());
		persona.setSegundoApellido(formularioDatosClienteDTO.getSegundoApellido());
		persona.setTipoPersona(
				UtilDominios.TIPO_DOCUMENTO_PERSONA_NIT_VALOR.equals(formularioDatosClienteDTO.getTipoIdentificacion())
						? UtilDominios.TIPO_PERSONA_JURIDICO
						: UtilDominios.TIPO_PERSONA_NATURAL);
		persona.setCiudadDelDocumento(formularioDatosClienteDTO.getCiudad());
		persona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		persona.setEstadoPersona(UtilDominios.ESTADO_PERSONA_ACTIVO);

		if(formularioDatosClienteDTO.getNumeroTelefono()!=null) {
			TelefonoDTO telefono = new TelefonoDTO();
			telefono.setIdTelefono(formularioDatosClienteDTO.getIdTelefono());
			telefono.setNumero(formularioDatosClienteDTO.getNumeroTelefono());
			telefono.setTipoTelefono(UtilDominios.TIPO_TELEFONO_FIJO);
			telefono.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			persona.setTelefonos(Arrays.asList(telefono));
		}else {
			List<TelefonoDTO> telefonosDTO = (List<TelefonoDTO>) (new TelefonoDTO()).transformarColeccionSinDependencias(
					manejadorTelefono.consultarTelefonosPersona(persona.getIdPersona(), true));
			
			for (TelefonoDTO telefonoDTO : telefonosDTO) {
				if(telefonoDTO.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
					TelefonoDTO telefono = new TelefonoDTO();
					telefono.setIdTelefono(formularioDatosClienteDTO.getIdTelefono());
					telefono.setNumero(telefonoDTO.getNumero());
					telefono.setTipoTelefono(UtilDominios.TIPO_TELEFONO_FIJO);
					telefono.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					persona.setTelefonos(Arrays.asList(telefono));
				}
			}
		}
		
		if(formularioDatosClienteDTO.getDireccion() != null) {
			UbicacionDTO ubicacion = new UbicacionDTO();
			ubicacion.setIdUbicacion(formularioDatosClienteDTO.getIdDireccion());
			ubicacion.setDireccion(formularioDatosClienteDTO.getDireccion());
			ubicacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			ubicacion.setIdZonaGeografica(formularioDatosClienteDTO.getCiudad());
			ubicacion.setTipo(UtilDominios.TIPO_UBICACION_PRINCIPAL);
			persona.setDirecciones(Arrays.asList(ubicacion));
		}else {
			List<Ubicacion> ubicacionesSimasc = ubicacionFacade.consultarUbicacionesPorPersona(formularioDatosClienteDTO.getIdPersona(), false);
			for (Ubicacion ubicacionSimasc : ubicacionesSimasc) {
				if(ubicacionSimasc.getTipo().equals(UtilDominios.TIPO_UBICACION_PRINCIPAL)) {
					UbicacionDTO ubicacion = new UbicacionDTO();
					ubicacion.setIdUbicacion(formularioDatosClienteDTO.getIdDireccion());
					ubicacion.setDireccion(ubicacionSimasc.getDireccion());
					ubicacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					ubicacion.setIdZonaGeografica(formularioDatosClienteDTO.getCiudad());
					ubicacion.setTipo(UtilDominios.TIPO_UBICACION_PRINCIPAL);
					persona.setDirecciones(Arrays.asList(ubicacion));
				}
			}
		}
		
		if(formularioDatosClienteDTO.getEmail() != null) {
			System.out.println("************** CORREO ES DIFERENTE DE NULL **************"+formularioDatosClienteDTO.getEmail());
			CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
			correo.setIdCorreo(formularioDatosClienteDTO.getIdEmail());
			correo.setDireccion(formularioDatosClienteDTO.getEmail());
			correo.setTipo(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
			correo.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			persona.setCorreos(Arrays.asList(correo));
		}else {
			System.out.println("************** CORREO ES NULL **************");
			List<CorreoElectronico> correoSimasc = correoElectronicoFacade.consultaCorreosPersona(formularioDatosClienteDTO.getIdPersona());
			for (CorreoElectronico correoElectronico : correoSimasc) {
				System.out.println("**************TIPO CORREO**************"+correoElectronico.getTipo());
				if(correoElectronico.getTipo().equals(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL)) {
					System.out.println("**************DIRECCION**************"+correoElectronico.getDireccion());
					CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
					correo.setIdCorreo(formularioDatosClienteDTO.getIdEmail());
					correo.setDireccion(correoElectronico.getDireccion());
					correo.setTipo(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
					correo.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					persona.setCorreos(Arrays.asList(correo));
				}
			}
		}
		crearPersona(persona);
		return integracionSWFacade.crearDatosBasicosClienteSirep(formularioDatosClienteDTO);
	}

	@Override
	public void actualizaDatosContactoClienteSIREP(FormularioDatosClienteDTO formularioDatosClienteDTO) {
				
		Persona personaExiste = manejadorPersona.consultarPersonaPorIdentificacion(formularioDatosClienteDTO.getTipoIdentificacion(),
				formularioDatosClienteDTO.getNumeroIdentificacion());
		
		FormularioDatosClienteDTO formularioDatosClienteExisteDTO = transformarPersonaAFormularioDatosClienteDTO(personaExiste);
				
		if(UtilMascaraTexto.hasOnlyDots(formularioDatosClienteDTO.getDireccion())) {
			formularioDatosClienteDTO.setDireccion(formularioDatosClienteExisteDTO.getDireccion());
		}
		if(UtilMascaraTexto.hasOnlyDots(formularioDatosClienteDTO.getNumeroTelefono())) {
			formularioDatosClienteDTO.setNumeroTelefono((formularioDatosClienteExisteDTO.getNumeroTelefono()));
		}
		if(UtilMascaraTexto.hasEmailOnlyDots(formularioDatosClienteDTO.getEmail())) {
			formularioDatosClienteDTO.setEmail((formularioDatosClienteExisteDTO.getEmail()));
		}
		
		integracionSWFacade.actualizarDatosContactoClienteSirep(formularioDatosClienteDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade#
	 * adicionarPersonaNatural(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void adicionarPersonaNatural(Long idPersonaJuridica, Long idPersonaNatural) {
		// validar que la persona correspondiente al idPersonaJuridica sea de
		// tipo juridica
		Persona personaJuridica = manejadorPersona.buscar(idPersonaJuridica);
		if (personaJuridica == null || !personaJuridica.getTipoPersona().equals(UtilDominios.TIPO_PERSONA_JURIDICO)) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR100.toString()));
		}
		// validar que la persona correspondiente al idPersonaNatural sea una
		// persona natural
		// y no se encuentre asociado a una persona juridica
		Persona personaNatural = manejadorPersona.buscar(idPersonaNatural);
		if (personaNatural == null || !personaNatural.getTipoPersona().equals(UtilDominios.TIPO_PERSONA_NATURAL)
				|| personaNatural.getIdPersonaJuridica() != null) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR101.toString()));
		}
		personaNatural.setIdPersonaJuridica(idPersonaJuridica);
		manejadorPersona.actualizar(personaNatural);
	}

	/*
	 * Conciliación
	 */
	@Override
	public List<PersonaBasicaDTO> consultarConciliadoresFiltros(
			FiltrosSeleccionConciliadorDTO filtrosSeleccionConciliadorDTO) {
		List<Persona> personas = manejadorPersona.consultarConciliadores(filtrosSeleccionConciliadorDTO);
		List<PersonaBasicaDTO> basicaDTOs = new ArrayList<PersonaBasicaDTO>();

		for (Persona it : personas) {
			PersonaBasicaDTO personaBasicaDTO = it.convertirEntidadToPersonaBasicoDTO(it);
			basicaDTOs.add(personaBasicaDTO);
		}
		return basicaDTOs;
	}

	@Override
	public List<PersonaBasicaDTO> consultarConciliadoresPorCentros(BusquedaRolesActivosDTO filtros) {
		List<PersonaBasicaDTO> personaBasica = new ArrayList<PersonaBasicaDTO>();
		List<Persona> personas = manejadorPersona.consultarConciliadoresPorCentros(filtros);
		if (!personas.isEmpty()) {
			personaBasica = (List<PersonaBasicaDTO>) PersonaBasicaDTO.newListaPersonaBasicaDTO(personas);
		}
		return personaBasica;
	}

	@Override
	public List<PersonaBasicaDTO> consultarMediadoresPorCentros(BusquedaRolesActivosDTO filtros) {
		List<PersonaBasicaDTO> personaBasica = new ArrayList<PersonaBasicaDTO>();
		List<Persona> personas = manejadorPersona.consultarMediadoresPorCentros(filtros);
		if (!personas.isEmpty()) {
			personaBasica = (List<PersonaBasicaDTO>) PersonaBasicaDTO.newListaPersonaBasicaDTO(personas);
		}
		return personaBasica;
	}

	public List<PersonaMateriaAsignadaDTO> consultarMateriasAsignadas(Long idPersona) {
		return manejadorPersona.consultarMateriasAsignadas(idPersona);
	}

	@Override
	public void actualizarResumenHojaVida(PersonaDTO persona) {
		Persona personaActual = manejadorPersona.buscar(persona.getIdPersona());
		if (personaActual != null) {
			personaActual.setResumenHojaVida(persona.getResumenHojaVida());
			personaActual.setAutorizaPublicacionDatos(persona.getAutorizaPublicacionDatos());
			personaActual.setFechaUltimaModificacion(new Date());
			String usuarioActual = appContext != null && appContext.getContextoSesion() != null
					&& appContext.getContextoSesion().getNombreUsuario() != null
							? appContext.getContextoSesion().getNombreUsuario()
							: UtilConstantes.USUARIO_DEFECTO_SIMASC;
			personaActual.setIdUsuarioModificacion(usuarioActual);
			manejadorPersona.actualizar(personaActual);
		} else {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR527.toString()));
		}

	}

	@Override
	public void actualizarDatosPersonaPRE(Long idPersona, Long profesion, String numeroTarjetaPro) {

		Persona personaActual = manejadorPersona.buscar(idPersona);
		if (personaActual != null) {
			if (profesion != null && profesion != -1) {
				personaActual.setIdProfesion(profesion);
			}
			personaActual.setNumeroTarjetaProfesional(numeroTarjetaPro);
			personaActual.setFechaUltimaModificacion(new Date());
			String usuarioActual = appContext != null && appContext.getContextoSesion() != null
					&& appContext.getContextoSesion().getNombreUsuario() != null
							? appContext.getContextoSesion().getNombreUsuario()
							: UtilConstantes.USUARIO_DEFECTO_SIMASC;
			personaActual.setIdUsuarioModificacion(usuarioActual);
			manejadorPersona.actualizar(personaActual);
		} else {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR527.toString()));
		}
	}

	@Override
	public FormularioParteDTO getConsultarPartePorCedula(String numeroCedula, String tipoId) {
		Persona persona = manejadorPersona.getConsultarPartePorCedula(numeroCedula, tipoId);
		return crearFormularioParteDTO(persona, -1L);
	}

	@Override
	public List<FormularioParteDTO> consultarPartesSolicitudServicio(Long idSolicitudServicio) {
		List<String> roles = manejadorDominio.consultarCodigosDominio(UtilDominios.DOMINIO_ROL_PERSONA_PARTE_CON);
		List<PersonaSolicitud> personaSolicituds = manejadorPersonaSolicitud
				.consultarPersonasSolicitud(idSolicitudServicio, roles, false);
		List<FormularioParteDTO> listFormuPartes = new ArrayList<>();

		for (PersonaSolicitud itpersol : personaSolicituds) {
			FormularioParteDTO parteDTO = new FormularioParteDTO();
			Persona persona = itpersol.getPersona();
			parteDTO.setIdPersona(persona.getIdPersona());
			parteDTO.setPrimerNombre(persona.getPrimerNombreORazonSocial());
			parteDTO.setSegundoNombre(persona.getSegundoNombre());
			parteDTO.setPrimerApellidoORazonSocial(persona.getPrimerApellido());
			parteDTO.setSegundoApellido(persona.getSegundoApellido());
			parteDTO.setNombreCompleto(persona.getNombreCompleto());
			parteDTO.setNumeroIdentificacion(persona.getNumeroDocumento());
			parteDTO.setIdSolicitudServicio(idSolicitudServicio);
			parteDTO.setIdCentro(itpersol.getSolicitudServicio().getSede().getCentro().getIdCentro());

			Dominio dominioTipoPersona = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_TIPO_PERSONA,
					persona.getTipoPersona());
			parteDTO.setTipoPersona(new DominioDTO().transformarEntidadSinDependencias(dominioTipoPersona));
			Dominio dominioTipoDocumento = dominioFacade.getObtenerDominioSinDependencia(
					UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA, persona.getTipoDocumento());
			parteDTO.setTipoIdentificacion(new DominioDTO().transformarEntidadSinDependencias(dominioTipoDocumento));

			Dominio dominioRol = dominioFacade.getObtenerDominioSinDependencia(UtilDominios.DOMINIO_ROL_PERSONA,
					itpersol.getRol().getNombre());
			parteDTO.setRol(dominioRol);
			listFormuPartes.add(parteDTO);
		}
		return listFormuPartes;
	}

	/**
	 * Metodo encargado de obtener las partes asociadas a una Solicitud de Servicio
	 * de recuperacion empresarial
	 *
	 * CON-F-125
	 * 
	 * Y DE de equidad
	 *
	 * EQU-F-004
	 * 
	 * @param idSolicitudServicio
	 * @return List<FormularioParteDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	@Override
	public List<FormularioParteDTO> consultarPartesSolicitudServicioRec(Long idSolicitudServicio) {
		
		SolicitudServicio solicitudServicio = manejadorSolicitudServicio.buscar(idSolicitudServicio);		
		List<String> roles = null;
		if(solicitudServicio.getIdServicio().equals(UtilConstantes.ID_SERVICIO_INSOLVENCIA)) {			
			roles = manejadorDominio.consultarCodigosDominio(UtilDominios.DOMINIO_ROL_PERSONA_PARTE_CON);			
		}else if(solicitudServicio.getIdServicio().equals(UtilConstantes.ID_SERVICIO_EQUIDAD)) {			
			 roles = manejadorDominio.consultarCodigosDominio(UtilDominios.DOMINIO_ROL_PERSONA_PARTE_EQUIDAD);			
		}else {			
			 roles = manejadorDominio.consultarCodigosDominio(UtilDominios.DOMINIO_ROL_PERSONA_PARTE);			 		
		}
		
		List<PersonaSolicitud> personaSolicituds = manejadorPersonaSolicitud
				.consultarPersonasSolicitud(idSolicitudServicio, roles, false);
		
		List<FormularioParteDTO> listFormuPartes = new ArrayList<>();

		for (PersonaSolicitud itpersol : personaSolicituds) {
			FormularioParteDTO parteDTO = new FormularioParteDTO();
			Persona persona = itpersol.getPersona();
			parteDTO.setIdPersona(persona.getIdPersona());
			parteDTO.setPrimerNombre(persona.getPrimerNombreORazonSocial());
			parteDTO.setSegundoNombre(persona.getSegundoNombre());
			parteDTO.setPrimerApellidoORazonSocial(persona.getPrimerApellido());
			parteDTO.setSegundoApellido(persona.getSegundoApellido());
			parteDTO.setNombreCompleto(persona.getNombreCompleto());
			parteDTO.setNumeroIdentificacion(persona.getNumeroDocumento());
			parteDTO.setIdSolicitudServicio(idSolicitudServicio);
			parteDTO.setIdCentro(itpersol.getSolicitudServicio().getSede().getCentro().getIdCentro());

			Dominio dominioTipoPersona = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_TIPO_PERSONA,
					persona.getTipoPersona());
			parteDTO.setTipoPersona(new DominioDTO().transformarEntidadSinDependencias(dominioTipoPersona));
			Dominio dominioTipoDocumento = dominioFacade.getObtenerDominioSinDependencia(
					UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA, persona.getTipoDocumento());
			parteDTO.setTipoIdentificacion(new DominioDTO().transformarEntidadSinDependencias(dominioTipoDocumento));

			Dominio dominioRol = dominioFacade.getObtenerDominioSinDependencia(UtilDominios.DOMINIO_ROL_PERSONA,
					itpersol.getRol().getNombre());
			parteDTO.setRol(dominioRol);
			listFormuPartes.add(parteDTO);
		}		
		return listFormuPartes;
	}

	@Override
	public Boolean validarCompletitudPartesSolicitudServicio(Long idSolicitudServicio) {
		List<String> roles = manejadorDominio.consultarCodigosDominio(UtilDominios.DOMINIO_ROL_PERSONA_PARTE_CON);
		List<PersonaSolicitud> personaSolicituds = manejadorPersonaSolicitud
				.consultarPersonasSolicitud(idSolicitudServicio, roles, false);
		SolicitudServicio solicitudServicio = manejadorSolicitudServicio.buscar(idSolicitudServicio);
		boolean isConvocanteOrAcreedor = false;
		boolean isConvocadoOrDeudor = false;
		for (PersonaSolicitud itPerSol : personaSolicituds) {
			if (solicitudServicio.getIdServicio().equals(UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION)
					|| solicitudServicio.getIdServicio().equals(UtilConstantes.ID_SERVICIO_RECUPERACION_EMPRESARIAL)) {
				if (UtilDominios.ROL_PERSONA_DEUDOR.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					isConvocadoOrDeudor = true;
				} else if (UtilDominios.ROL_PERSONA_ACREEDOR.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					isConvocanteOrAcreedor = true;
				} else if (UtilDominios.ROL_PERSONA_DEUDOR_RECUPERACION
						.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					isConvocadoOrDeudor = true;
				} else if (UtilDominios.ROL_PERSONA_ACREEDOR_RECUPERACION
						.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					isConvocanteOrAcreedor = true;
				}

			} else {
				if (UtilDominios.ROL_PERSONA_CONVOCADO.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					isConvocadoOrDeudor = true;
				} else if (UtilDominios.ROL_PERSONA_CONVOCANTE.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					isConvocanteOrAcreedor = true;
				}
			}
			if (isConvocanteOrAcreedor && isConvocadoOrDeudor)
				break;
		}
		return isConvocanteOrAcreedor && isConvocadoOrDeudor;
	}

	@Override
	public Boolean validarCompletitudPartesSolicitudServicioRec(Long idSolicitudServicio) {

		SolicitudServicio solicitudServicio = manejadorSolicitudServicio.buscar(idSolicitudServicio);
		List<String> roles = null;
		if(solicitudServicio.getIdServicio().equals(UtilConstantes.ID_SERVICIO_INSOLVENCIA)) {
			 roles = manejadorDominio.consultarCodigosDominio(UtilDominios.DOMINIO_ROL_PERSONA_PARTE_CON);
		}else if(solicitudServicio.getIdServicio().equals(UtilConstantes.ID_SERVICIO_EQUIDAD)) {
			 roles = manejadorDominio.consultarCodigosDominio(UtilDominios.DOMINIO_ROL_PERSONA_PARTE_EQUIDAD);
		}else {
			 roles = manejadorDominio.consultarCodigosDominio(UtilDominios.DOMINIO_ROL_PERSONA_PARTE);
		}
		List<PersonaSolicitud> personaSolicituds = manejadorPersonaSolicitud
				.consultarPersonasSolicitud(idSolicitudServicio, roles, false);
		boolean isConvocanteOrAcreedor = false;
		boolean isConvocadoOrDeudor = false;
		int countAcredores = 0;
		int countConvocantes = 0;
		int countSolicitantes = 0;

		for (PersonaSolicitud itPerSol : personaSolicituds) {
			if (solicitudServicio.getIdServicio().equals(UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION)
					|| solicitudServicio.getIdServicio().equals(UtilConstantes.ID_SERVICIO_RECUPERACION_EMPRESARIAL)) {
				if (UtilDominios.ROL_PERSONA_DEUDOR.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					isConvocadoOrDeudor = true;
				} else if (UtilDominios.ROL_PERSONA_ACREEDOR.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					isConvocanteOrAcreedor = true;
				} else if (UtilDominios.ROL_PERSONA_DEUDOR_RECUPERACION
						.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					isConvocadoOrDeudor = true;
				} else if (UtilDominios.ROL_PERSONA_ACREEDOR_RECUPERACION
						.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					isConvocanteOrAcreedor = true;
				}

			} else if(solicitudServicio.getIdServicio().equals(UtilConstantes.ID_SERVICIO_INSOLVENCIA)) {
				if (UtilDominios.ROL_PERSONA_DEUDOR.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					isConvocadoOrDeudor = true;
				} else if (UtilDominios.ROL_PERSONA_ACREEDOR.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					countAcredores += 1;
					if(countAcredores>1) {
						isConvocanteOrAcreedor = true;
					}
				}
			//solicitante actuara como isConvocadoOrDeudor y convocado como acrededor isConvocanteOrAcreedor
			} else if(solicitudServicio.getIdServicio().equals(UtilConstantes.ID_SERVICIO_EQUIDAD)) {
				if (UtilDominios.ROL_PERSONA_SOLICITANTE_EQUIDAD.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					countSolicitantes=+1;
					if(countSolicitantes>=1) {
						isConvocadoOrDeudor = true;
					}
				} else if (UtilDominios.ROL_PERSONA_CONVOCADO_EQUIDAD.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					countConvocantes += 1;
					if(countConvocantes>=1) {
						isConvocanteOrAcreedor = true;
					}
				}
			}else {
				if (UtilDominios.ROL_PERSONA_CONVOCADO.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					isConvocadoOrDeudor = true;
				} else if (UtilDominios.ROL_PERSONA_CONVOCANTE.equalsIgnoreCase(itPerSol.getRol().getNombre())) {
					isConvocanteOrAcreedor = true;
				}
			}
			if (isConvocanteOrAcreedor && isConvocadoOrDeudor)
				break;
		}
		return isConvocanteOrAcreedor && isConvocadoOrDeudor;
	}

	public void eliminarParteSolicitudServicio(Long idSolicitudServicio, Long idPersona) {
		try {
			List<PersonaSolicitud> personaSolicitudList = manejadorPersonaSolicitud
					.consultarPersonaSolicitud(idSolicitudServicio, idPersona, null);

			boolean esParte = true;

			for (PersonaSolicitud personaSolicitud : personaSolicitudList) {
				if (!UtilDominios.ROL_PERSONA_SOLICITANTE.equals(personaSolicitud.getRol().getNombre())
						&& !UtilDominios.ROL_PERSONA_CONCILIADOR.equals(personaSolicitud.getRol().getNombre())) {
					personaSolicitud.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
					manejadorPersonaSolicitud.actualizar(personaSolicitud);
				} else {
					esParte = false;
				}
			}

			if (esParte) {
				Persona persona = personaSolicitudList.get(0).getPersona();

				List<Ubicacion> ubicacions = persona.getUbicacionList();
				List<Telefono> telefonos = persona.getTelefonoList();
				List<CorreoElectronico> correoElectronicos = persona.getCorreoElectronicoList();

				/* Al eliminar una parte no se deben inactivar direcciones, telefonos, correos ni la persona
				 * Solo se debe inactivar en solicitudPersona
				for (Ubicacion itubi : ubicacions) {
					itubi.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
					manejadorUbicacion.actualizar(itubi);
				}

				for (Telefono itele : telefonos) {
					itele.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
					manejadorTelefono.actualizar(itele);
				}

				for (CorreoElectronico itcorre : correoElectronicos) {
					itcorre.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
				}

				persona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
				manejadorPersona.actualizar(persona);*/
			}
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString());
			throw new SIMASCNegocioExcepcion(mensajeError, e);
		}
	}

	private FormularioParteDTO crearFormularioParteDTO(Persona persona, Long idCaso) {
		FormularioParteDTO parteDTO = new FormularioParteDTO();

		try {

			if (idCaso != -1)
				parteDTO.setIdCaso(idCaso);
			String nombreRol = null;
			Dominio dominio = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_TIPO_PERSONA,
					persona.getTipoPersona());
			parteDTO.setTipoPersona(dominio);

			if (!persona.getRolPersonaCasoList().isEmpty() && idCaso != -1) {
				for (RolPersonaCaso it : persona.getRolPersonaCasoList()) {
					if ((it.getCaso().getIdCaso().compareTo(idCaso) == 0)
							&& it.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
						nombreRol = it.getRol().getNombre();
					}
				}
				dominio = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_ROL_PERSONA, nombreRol);
				parteDTO.setRol(dominio);
			}
			dominio = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA,
					persona.getTipoDocumento());
			parteDTO.setTipoIdentificacion(dominio);
			parteDTO.setNumeroIdentificacion(persona.getNumeroDocumento());
			ZonaGeografica zonaGeografica = null;
			if (persona.getCiudadDelDocumento() != null)
				zonaGeografica = zonaGeograficaFacade.buscar(persona.getCiudadDelDocumento());
			if (zonaGeografica != null)
				parteDTO.setCiudadIdentificacion(
						zonaGeograficaFacade.transformarEntidadSinDependencias(zonaGeografica));
			parteDTO.setNacionalidad(persona.getIdPaisOrigen());
			parteDTO.setPrimerNombre(persona.getPrimerNombreORazonSocial());
			parteDTO.setSegundoNombre(persona.getSegundoNombre());
			parteDTO.setPrimerApellidoORazonSocial(persona.getPrimerApellido());
			parteDTO.setSegundoApellido(persona.getSegundoApellido());
			parteDTO.setIdPersona(persona.getIdPersona());
			List<UbicacionDTO> lstUbicacionDTO = new ArrayList<>();
			for (Ubicacion it : persona.getUbicacionList()) {
				UbicacionDTO ubicacionDTO = new UbicacionDTO();
				ubicacionDTO.setIdUbicacion(it.getIdUbicacion());
				ubicacionDTO.setDireccion(it.getDireccion());
				ubicacionDTO.setIdZonaGeografica(it.getIdZonaGeografica());
				ZonaGeografica ciudad = manejadorZonaGeografica.buscar(it.getIdZonaGeografica());
				ZonaGeografica pais = manejadorZonaGeografica.buscar(ciudad.getIdZonaGeograficaPadre());
				ubicacionDTO.setCiudad(ciudad);
				ubicacionDTO.setPais(pais);
				lstUbicacionDTO.add(ubicacionDTO);
			}
			parteDTO.setLstUbicacion(lstUbicacionDTO);
			int i = 0;

			for (Telefono it2 : telefonoFacade.transformarEntidadesColeccionSinDependencias(persona.getTelefonoList(),
					new ArrayList<Telefono>())) {
				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(it2.getEstadoRegistro())) {
					if (it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_CELULAR)) {
						parteDTO.setNumeroCelular(it2.getNumero());
					}
					if (i == 0 && it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
						parteDTO.setNumeroTelefonoUno(it2.getNumero());
						i++;
					} else if (i == 1 && it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
						parteDTO.setNumeroTelefonoDos(it2.getNumero());
						i++;
					}

				}

			}

			for (CorreoElectronico it3 : correoElectronicoFacade.transformarEntidadesColeccionSinDependencias(
					persona.getCorreoElectronicoList(), new ArrayList<CorreoElectronico>())) {

				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(it3.getEstadoRegistro())) {
					if (it3.getTipo().equals(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL))
						parteDTO.setEmailUno(it3.getDireccion());

					if (it3.getTipo().equals(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO)) {
						parteDTO.setEmailDos(it3.getDireccion());

					} else if (it3.getTipo().equals(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_TERCIARIO)) {
						parteDTO.setEmailTres(it3.getDireccion());
					}

					if (parteDTO.getEmailUno() != null && parteDTO.getEmailDos() != null
							&& parteDTO.getEmailTres() != null) {
						break;
					}
				}
			}

			dominio = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_SEXOS, persona.getSexo());
			parteDTO.setTipoSexo(dominio);
			parteDTO.setFechaNacimiento(persona.getFechaDeNacimiento());
			dominio = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_ESTRATOS, persona.getEstrato());
			parteDTO.setEstrato(dominio);
			parteDTO.setProfesion(persona.getProfesion());
			dominio = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_ESCOLARIDADES, persona.getEscolaridad());
			parteDTO.setEscolaridad(dominio);
			dominio = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_INSTITUCIONES_EDUCATIVAS,
					persona.getInstitucionEducativa());
			parteDTO.setInstitucionEducativa(dominio);
			parteDTO.setFechaGrado(persona.getFechaDeGrado());
			parteDTO.setNumeroTarjetaProfesional(persona.getNumeroTarjetaProfesional());
			dominio = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_ENTIDAD_TARJETA_PROFESIONAL,
					persona.getEntidadExpideTarjetaProfesional());
			parteDTO.setEntidadTarjetaProfesional(dominio);
			dominio = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_TIPO_EMPRESA, persona.getTipoDeEmpresa());
			parteDTO.setTipoEmpresa(dominio);
			dominio = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_TIPO_ENTIDAD_PUBLICA,
					persona.getTipoDeEntidadPublica());
			parteDTO.setTipoEntidadPublica(dominio);
			parteDTO.setRepresentanteLegal(persona.getRepresentanteLegal());
			dominio = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_SECTOR_EMPRESA,
					persona.getSectorDeLaEmpresa());
			parteDTO.setSectorEmpresa(dominio);
			parteDTO.setPaginaWeb(persona.getPaginaWeb());
			if (nombreRol != null) {
				List<Persona> personasRepresentadas = manejadorPersona.getConsultarPartesCasoPorRol(idCaso,
						obtenerRolParteRepresentada(nombreRol));
				parteDTO.setRepresentada((List<Persona>) transformarEntidadesColeccionSinDependencias(
						personasRepresentadas, new ArrayList<Persona>()));
			}
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		} catch (Exception e) {
			logger.error(e);
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		return parteDTO;
	}

	@Override
	public List<PersonaBasicaDTO> cosultarPersonaBasicaPorTipoFuncionario(List<String> tiposFuncionario) {
		List<PersonaBasicaDTO> personaBasica = new ArrayList<PersonaBasicaDTO>();
		List<Persona> personas = manejadorPersona.cosultarPersonaPorTipoFuncionario(tiposFuncionario);
		if (personas != null && !personas.isEmpty()) {
			personaBasica = (List<PersonaBasicaDTO>) PersonaBasicaDTO.newListaPersonaBasicaDTO(personas);
		}
		return personaBasica;
	}

	@Override
	public List<ParteCasoDTO> consultarPartesConciliacionCaso(Long idCaso, boolean sinIdentificacion, boolean bandera) {
		Boolean banderaConciliacion = true;
		if (bandera) {
			banderaConciliacion = false;
		}
		List<String> lstRoles = obtenerRolesParte(banderaConciliacion,bandera);
		List<RolPersonaCaso> lstRolPersonaCaso = manejadorRolPersonaCaso.consultarPersonasAsignadasCasoPorRol(idCaso,
				lstRoles, sinIdentificacion);
		List<ParteCasoDTO> partesCaso = new ArrayList<>();
		for (RolPersonaCaso rolPersonaCaso : lstRolPersonaCaso) {
			ParteCasoDTO parte = new ParteCasoDTO();
			parte.setIdPersona(rolPersonaCaso.getPersona().getIdPersona());
			parte.setIdRol(rolPersonaCaso.getRol().getIdRol());
			parte.setRol(rolPersonaCaso.getRol().getNombre());
			parte.setNombre(rolPersonaCaso.getPersona().getNombreCompleto());
			parte.setNumeroIdentificacion(rolPersonaCaso.getPersona().getNumeroDocumento());
			parte.setTipoIdentificacion(rolPersonaCaso.getPersona().getTipoDocumento());
			partesCaso.add(parte);
		}
		return partesCaso;
	}

	@Override
	public List<Persona> conciliadoresCasosPendienteCobro() {
		return manejadorPersona.conciliadoresCasosPendienteCobro();
	}

	public List<Persona> conciliadoresSeguimientoCasos(List<CentroDTO> centros) {
		List<Long> idCentros = new ArrayList<>();
		for (CentroDTO centro : centros) {
			idCentros.add(centro.getIdCentro());
		}
		String inCentros = UtilConsultasSQL.clausulaInSQLSNumeros(idCentros);
		return manejadorPersona.conciliadoresSeguimientoCasos(inCentros);
	}

	@Override
	public List<PersonaBasicaDTO> consultarConciliadoresConAudienciasProgramada(Date fechaAudiencia) {
		return manejadorPersona.consultarConciliadoresConAudienciasProgramada(fechaAudiencia);
	}

	public Persona consultarPersonaTipoYNumeroDocumento(Persona filtros) {
		Persona persona = manejadorPersona.consultarPersonaTipoYNumeroDocumento(filtros);
		if (persona != null)
			persona = new PersonaDTO().transformarEntidadSinDependencias(persona);
		return persona;
	}

	@Override
	public InformacionPrestadorDTO consultarInformacionPrestador(Long idPersona, Long idRol) {
		InformacionPrestadorDTO informacionPrestadorDTO = manejadorPersona.consultarInformacionPrestador(idPersona,
				idRol);
		if (informacionPrestadorDTO != null
				&& UtilDominios.ROL_PERSONA_PERITO.equals(informacionPrestadorDTO.getNombreRol())) {
			List<CorreoElectronicoDTO> correosElectronicosDTO = correoElectronicoFacade
					.consultarCorreosPersona(idPersona);
			if (!correosElectronicosDTO.isEmpty()) {
				List<String> direccionesCorreo = new ArrayList<String>();
				for (CorreoElectronicoDTO correo : correosElectronicosDTO) {
					if (correo != null)
						direccionesCorreo.add(correo.getDireccion());
				}
				informacionPrestadorDTO.setCorreosElectronicos(direccionesCorreo);
			}
		}
		return informacionPrestadorDTO;
	}

	@Override
	public Persona crearPersona(PersonaDTO personaDTO) {
		Persona persona = new Persona();
		boolean isSinID = UtilDominios.TIPO_DOCUMENTO_PERSONA_SIN_IDENTIFICACION.equals(personaDTO.getTipoDocumento());
		if (personaDTO.getTipoDocumento() != null && !isSinID) {

			Persona personaExiste = manejadorPersona.consultarPersonaPorIdentificacion(personaDTO.getTipoDocumento(),
					personaDTO.getNumeroDocumento());
			if (personaExiste != null) {
				if (personaDTO.getIdPersona() == null) {
					persona.setIdPersona(personaExiste.getIdPersona());
				} else if (!personaExiste.getIdPersona().equals(personaDTO.getIdPersona())) {
					List<String> parametrosMensaje = new ArrayList<String>();
					parametrosMensaje.add(personaExiste.getNombreCompleto());
					String mensajeError = String.format(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR500.toString()),
							parametrosMensaje.toArray());
					throw new SIMASCNegocioExcepcion(mensajeError);
				} else {
					persona.setIdPersona(personaDTO.getIdPersona());
				}
			} else if (personaDTO.getIdPersona() != null) {
				persona.setIdPersona(personaDTO.getIdPersona());
			}
		} else if (isSinID && personaDTO.getIdPersona() != null) {
			persona.setIdPersona(personaDTO.getIdPersona());
		}

		persona.setTipoPersona(personaDTO.getTipoPersona());
		persona.setEstadoRegistro(personaDTO.getEstadoRegistro());
		persona.setEstadoPersona(personaDTO.getEstadoPersona());
		persona.setPrimerNombreORazonSocial(personaDTO.getPrimerNombreORazonSocial());
		persona.setSegundoNombre(personaDTO.getSegundoNombre());
		persona.setPrimerApellido(personaDTO.getPrimerApellido());
		persona.setSegundoApellido(personaDTO.getSegundoApellido());
		persona.setTipoDocumento(personaDTO.getTipoDocumento());
		persona.setNumeroDocumento(personaDTO.getNumeroDocumento());
		persona.setFechaUltimaModificacion(new Date());
		persona.setIdPaisOrigen(personaDTO.getIdPaisOrigen());

		if (persona.getIdPersona() != null)
			actualizar(persona);
		else
			crear(persona);

		crearTelefonos(personaDTO.getTelefonos(), persona.getIdPersona());
		crearUbicaciones(personaDTO.getDirecciones(), persona.getIdPersona());
		crearCorreos(personaDTO.getCorreos(), persona.getIdPersona());
		return persona;
	}

	private void crearTelefonos(List<TelefonoDTO> telefonos, Long idPersona) {
		for (TelefonoDTO telefonoDTO : telefonos) {
			telefonoFacade.crearTelefonoPersona(telefonoDTO.getNumero(), telefonoDTO.getTipoTelefono(), idPersona,
					telefonoDTO.getIdTelefono(), telefonoDTO.getEstadoRegistro());
		}
	}

	private void crearUbicaciones(List<UbicacionDTO> ubicaciones, Long idPersona) {
		for (UbicacionDTO ubicacionDTO : ubicaciones) {
			ubicacionDTO.setIdPersona(idPersona);
			ubicacionFacade.adicionarDireccion(ubicacionDTO);
		}
	}

	private void crearCorreos(List<CorreoElectronicoDTO> correos, Long idPersona) {
		for (CorreoElectronicoDTO correoElectronicoDTO : correos) {
			correoElectronicoDTO.setIdPersona(idPersona);
			correoElectronicoFacade.crearCorreoElectronico(correoElectronicoDTO);
		}
	}

	@Override
	public List<Persona> consultarFuncionariosCAC() {
		List<String> roles = new ArrayList<>();
		roles.add(UtilDominios.ROL_PERSONA_DIRECTOR_CAC);
		roles.add(UtilDominios.ROL_PERSONA_SUBDIRECTOR_CAC);
		roles.add(UtilDominios.ROL_PERSONA_JEFE_DE_ARBITRAJE);
		roles.add(UtilDominios.ROL_PERSONA_DIGITALIZADOR);
		roles.add(UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
		roles.add(UtilDominios.ROL_PERSONA_ASISTENTE_ARBITRO);
		roles.add(UtilDominios.ROL_PERSONA_ABOGADO);
		roles.add(UtilDominios.ROL_PERSONA_ABOGADO_ARBITRAJE_INTERNACIONAL);
		roles.add(UtilDominios.ROL_PERSONA_ESTUDIANTE_ARBITRAJE);
		roles.add(UtilDominios.ROL_PERSONA_JUDICANTE);

		return manejadorRolPersona.consultarFuncionariosCAC(roles);
	}

	@Override
	public List<GenericoDTO> consultarArbitrosInternacionalPreseleccion(String tipoServicio,
			FiltosPreseleccionArbitros filtros) {
		List<GenericoDTO> genericoList = new ArrayList<GenericoDTO>();
		try {
			genericoList = manejadorPersona.consultarArbitrosInternacionalPreseleccion(tipoServicio, filtros);
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		return genericoList;
	}

	@Override
	public List<GenericoDTO> consultarArbitrosPreseleccionFiltros(String tipoServicio,
			FiltosPreseleccionArbitros filtros) {
		List<GenericoDTO> genericoList = new ArrayList<GenericoDTO>();
		try {
			genericoList = manejadorPersona.consultarArbitrosPreseleccionFiltros(tipoServicio, filtros);
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		return genericoList;
	}

	public void eliminarDefinitivoParteSolicitudServicio(Long idSolicitudServicio, Long idPersona) {
		try {
			List<PersonaSolicitud> personaSolicitudList = manejadorPersonaSolicitud
					.consultarPersonaSolicitud(idSolicitudServicio, idPersona, null);
			
			for (PersonaSolicitud personaSolicitud : personaSolicitudList) {
				manejadorPersonaSolicitud.eliminar(personaSolicitud);
			}
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString());
			throw new SIMASCNegocioExcepcion(mensajeError, e);
		}
	}

	@Override
	public Boolean asignarRolArbitroSocial(Long idPersona, String motivo, Long idRol, boolean pronunciamiento) {
		Boolean asignaRol = false;
		Integer cntCasosAbiertos = 0;
		if(!pronunciamiento){
			cntCasosAbiertos = manejadorRolPersonaCaso.consultarNumeroCasosAbiertosAceptados(idPersona);
		}else{
			cntCasosAbiertos = 1;
		}		

		if (esArbitroHabilitado(idPersona) && cntCasosAbiertos > 0) {

			List<String> nombreParametros = new ArrayList<String>();
			nombreParametros.add(UtilConstantes.ESTADOS_ARBITRO_SOCIAL);

			List<ParametroDeServicioDTO> parametroDeServicioList = parametroDeServicioFacade
					.consultarParametroDeServicio(nombreParametros, UtilConstantes.ID_SERVICIO_ARBITRAJE,
							UtilConstantes.TIPO_PARAMETRO_ROL_ARBITRO_SOCIAL);

			List<String> motivos = Arrays.asList(parametroDeServicioList.get(0).getValor().split(","));

			EstadoPersonaRolPK pId = new EstadoPersonaRolPK();

			pId.setIdRol(idRol);
			pId.setIdPersona(idPersona);
			pId.setIdServicio(UtilConstantes.ID_SERVICIO_ARBITRAJ_SOCIAL);
			EstadoPersonaRol estadoPersonaRol = manejadorEstadoPersonaRol.buscar(pId);

			if (estadoPersonaRol != null && estadoPersonaRol.getIdMotivo() != null
					&& motivos.contains(estadoPersonaRol.getIdMotivo())) {
				Date date = new Date();
				// consultar datos de la persona
				Persona datosPersona = manejadorPersona.consultaBasicaPersona(idPersona);
				// Consulta rol a asignar
				Rol rol = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_ARBITRO_SOCIAL);
				// Adiciona el rol de la persona en la tabla rolPersona
				List<RolPersona> rolAsignado = manejadorRolPersona.consultarRolesAsignados(idPersona,
						UtilDominios.ROL_PERSONA_ARBITRO_SOCIAL);
				RolPersona rolPersona = new RolPersona();
				if (rolAsignado.size() == 0) {
					rolPersona.setPersona(datosPersona);
					rolPersona.setRol(rol);
					rolPersona.setIdRol(rol.getIdRol());
					rolPersona.setIdPersona(datosPersona.getIdPersona());
					rolPersona.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
					rolPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					rolPersona.setFechaUltimaModificacion(new Timestamp(date.getTime()));
					rolPersona.setFechaInicioVigencia(new Timestamp(date.getTime()));
					rolPersona.setIdCentro(1L);
					manejadorRolPersona.crearRolPersona(rolPersona);
				} else {
					rolPersona = rolAsignado.get(0);
				}
				// se asigna estado al rol
				EstadoPersonaRolPK pIdRolArbitroSocial = new EstadoPersonaRolPK();
				pIdRolArbitroSocial.setIdRol(rol.getIdRol());
				pIdRolArbitroSocial.setIdPersona(idPersona);

				estadoPersonaRol = new EstadoPersonaRol();
				estadoPersonaRol.setEstadoPersonaRolPK(pIdRolArbitroSocial);
				estadoPersonaRol.setIdMotivo(motivo);
				estadoPersonaRol.setFechaUltimaModificacion(new Date());
				estadoPersonaRol.setEstadoRegistro(UtilDominios.ESTADO_PERSONA_ACTIVO);
				if (appContext != null && appContext.getContextoSesion() != null
						&& appContext.getContextoSesion().getNombreUsuario() != null) {
					estadoPersonaRol.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
				} else {
					estadoPersonaRol.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
				}
				manejadorEstadoPersonaRol.crearOActualizarEstadoPersonaEstadoRol(estadoPersonaRol);
				// Generamos un Historico.
				manejadorHistoricoEstadoPersonaRol.crearHistoricoEstadoPersonaServicio(idPersona, rol.getIdRol(),
						motivo, new Date(), UtilConstantes.ID_SERVICIO_ARBITRAJ_SOCIAL);

				List<PersonaServicioMateria> materiaPersona = manejadorPersonaServicioMateria
						.consultarPersonaServicioMateriaInscritaPorServicio(
								Long.parseLong(UtilConstantes.ID_SERVICIO_ARBITRAJE_SOCIAL),
								UtilConstantes.ID_SIN_MATERIA, idPersona);

				if (materiaPersona.size() == 0) {
					PersonaServicioMateria servicioMateria = new PersonaServicioMateria();
					servicioMateria.setIdPersona(idPersona);
					servicioMateria.setIdServicio(Long.parseLong(UtilConstantes.ID_SERVICIO_ARBITRAJE_SOCIAL));
					servicioMateria.setIdMateria(UtilConstantes.ID_SIN_MATERIA);
					servicioMateria.setFechaInicioVigencia(new Date());
					servicioMateria.setFechaFinDeVigencia(null);
					servicioMateria.setEstadoParaSorteo(UtilDominios.ESTADO_PERSONA_SORTEO_ACTIVO);
					if (appContext != null && appContext.getContextoSesion() != null
							&& appContext.getContextoSesion().getNombreUsuario() != null) {
						servicioMateria.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
					} else {
						servicioMateria.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
					}

					servicioMateria.setFechaUltimaModificacion(new Date());
					servicioMateria.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					servicioMateria.setCantidadCasosAsignados(null);
					servicioMateria.setIdEspecialidad(null);
					personaServicioMateriaFacade.crear(servicioMateria);
				}

				asignaRol = true;
			}
		}
		return asignaRol;
	}

	@Override
	public List<GenericoDTO> consultarArbitrosPreseleccionCCBFiltros(FiltrosPreseleccionArbitrosCCB filtros) {
		List<GenericoDTO> genericoList = new ArrayList<>();
		try {
			// consultar si el sorteo para el servicio se hace con lista
			if (manejadorParametroServicioSorteo.sorteoConLista(filtros.getIdServicio())) {
				if (filtros.getCuantia() != null && filtros.getCuantia().equals(UtilDominios.TIPO_CUANTIA_MENOR)) {
					filtros.setNombreLista(UtilDominios.TIPO_LISTA_B);
				} else {
					filtros.setNombreLista(UtilDominios.TIPO_LISTA_A);
				}
			} else {
				filtros.setNombreLista(null);
			}

			if (filtros.isAsignableTodasLasMaterias()) {
				filtros.setCantidadMateriasAsignadas(filtros.getIdMaterias().size() - 1);
			} else {
				filtros.setCantidadMateriasAsignadas(0);
			}

			if (filtros.isSorteableTodasLasMaterias()) {
				filtros.setCantidadMateriasSorteable(filtros.getIdMaterias().size() - 1);
			} else {
				filtros.setCantidadMateriasSorteable(0);
			}

			if (filtros.getIdMaterias() != null && !filtros.getIdMaterias().isEmpty()) {
				genericoList = manejadorPersona.consultarArbitrosPreseleccionCCBFiltros(filtros);
			}

		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		return genericoList;
	}

	// protected region metodos adicionales end

	public boolean esArbitroHabilitado(Long idPersona) {

		List<RolPersona> rolPersonaList = rolPersonaFacade.obtenerRolPersona(UtilConstantes.ID_ROL_ARBITRO, idPersona);

		EstadoPersonaRolPK pId = new EstadoPersonaRolPK();

		pId.setIdRol(UtilConstantes.ID_ROL_ARBITRO);
		pId.setIdPersona(idPersona);

		EstadoPersonaRol estadoPersonaRol = manejadorEstadoPersonaRol.buscar(pId);

		return (estadoPersonaRol != null
				&& estadoPersonaRol.getIdMotivo().equals(UtilDominios.ESTADO_ARBITROS_HABILITADO)
				&& rolPersonaList != null && !rolPersonaList.isEmpty());

	}

	@Override
	public void bloqueaArbitrosReaAperturaCaso(DatosBasicosCasoDTO datosBasicosCasoDTO, String usuarioModificacion)
			throws Exception {
		List<DetalleArbitroDTO> arbitrosAsignadosCaso = rolPersonaCasoFacade
				.consultarDetalleArbitrosCaso(datosBasicosCasoDTO.getCaso().getIdCaso());

		InformacionFiltro filtroServicio = new InformacionFiltro(TipoFiltro.EXACTO,
				ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ID_SERVICIO,
				datosBasicosCasoDTO.getCaso().getIdServicio());

		List<InformacionFiltro> filtrosConsulta = new ArrayList<>();
		filtrosConsulta.add(filtroServicio);

		ParametroServicioSorteo parametroServicioSorteo = manejadorParametroServicioSorteo
				.consultar(filtrosConsulta, null, null).get(0);

		List<Audiencia> audiencias = audienciaFacade.obtenerAudienciasCasoPorTipoYEstado(
				datosBasicosCasoDTO.getCaso().getIdCaso(), parametroServicioSorteo.getAudienciaLiberaSuplente(),
				UtilDominios.ESTADO_REGISTRO_ACTIVO);

		List<String> rolesABloquear = new ArrayList<>();
		rolesABloquear.add(UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);

		if (parametroServicioSorteo.getBloqueaSuplente() && (audiencias == null || audiencias.isEmpty())) {
			rolesABloquear.add(UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE);
		}

		for (DetalleArbitroDTO arbitroAsignado : arbitrosAsignadosCaso) {

			Persona persona = arbitroAsignado.getPersona();
			Long idMateria = datosBasicosCasoDTO.getCaso().getIdMateria();

			ArbitroCasoLiberacionPK arbitroCasoLiberacionPK = new ArbitroCasoLiberacionPK(persona.getIdPersona(),
					datosBasicosCasoDTO.getCaso().getIdCaso());
			ArbitroCasoLiberacion esArbitroLiberado = arbitroCasoLiberacionFacade.buscar(arbitroCasoLiberacionPK);

			if (arbitroAsignado.getRolPersonaCaso().getIdSorteo() != null && esArbitroLiberado == null
					&& rolesABloquear.contains(arbitroAsignado.getRolPersonaCaso().getTipoNombramiento())) {

				if (arbitroAsignado.getRolPersonaCaso().getSorteo().isPreseleccion()
						&& arbitroAsignado.getRolPersonaCaso().getSorteo().getTipoPreseleccion() != null
						&& arbitroAsignado.getRolPersonaCaso().getSorteo().getTipoPreseleccion()
								.equals(UtilDominios.PRESELECCIONADO_POR_LA_CAMARA_DE_COMERCIO_BOGOTA)) {

					PreseleccionadoPK preseleccionadoPK = new PreseleccionadoPK(
							datosBasicosCasoDTO.getCaso().getIdCaso(), persona.getIdPersona());
					Preseleccionado preseleccionado = preseleccionadoFacade.buscar(preseleccionadoPK);

					idMateria = preseleccionado.getIdMateria();

				}

				PersonaServicioMateria personaServicioMateria = manejadorPersonaServicioMateria
						.consultarPersonaServicioMateriaSinExcepcion(datosBasicosCasoDTO.getCaso().getIdServicio(),
								idMateria, persona.getIdPersona());

				if (personaServicioMateria != null) {
					
					persona.setIdPersonaServicioMateria(personaServicioMateria.getIdPersonaServicioMateria());

					manejadorPersonaServicioMateria.inactivarParaSorteo(persona,
							datosBasicosCasoDTO.getCaso().getIdCaso(), new Date(), usuarioModificacion,
							arbitroAsignado.getRolPersonaCaso().getTipoNombramiento());
				}

			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade#
	 * validarIdentificacionPersonaEnmascarando(com.ccb.simasc.transversal.dto.formularios.
	 * PersonaBasicaDTO)
	 */
	public RadicacionSolicitudEnmascaradoDTO validarIdentificacionPersonaEnmascarando(PersonaBasicaDTO personaBasicaDTO) {
		Persona persona = consultarPersonaPorIdentificacion(personaBasicaDTO.getTipoDocumento(),
				personaBasicaDTO.getNumeroDocumento(), true);

		if (persona == null) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString()));
		}

		PersonaBasicaDTO personaBasicaSolicitud = persona.convertirEntidadToPersonaBasicoDTO(persona);		
		RadicacionSolicitudEnmascaradoDTO radicacionSolicitudDTO = new RadicacionSolicitudEnmascaradoDTO();

		List<CorreoElectronicoEnmascaradoDTO> correosEletronicosDTO = (List<CorreoElectronicoEnmascaradoDTO>) new CorreoElectronicoDTO()
				.transformarColeccionEnmascarando(
						manejadorCorreo.consultaCorreosPersona(personaBasicaSolicitud.getIdPersona()));

		radicacionSolicitudDTO.setPersonaBasicaDTO(personaBasicaSolicitud);
		radicacionSolicitudDTO.setCorreoElectronicoPersona(correosEletronicosDTO);

		return radicacionSolicitudDTO;
	}
	
	@Override
	public FormularioParteEnmascaradoDTO consultarPartePorCedulaPorCasoEnmascarando(String numeroCedula, Long idCaso) {
		
		FormularioParteEnmascaradoDTO parteDTO = new FormularioParteEnmascaradoDTO();

		try {
			Persona persona = manejadorPersona.getConsultarPartePorCedulaPorCaso(numeroCedula, idCaso);
		
			parteDTO.setIdCaso(idCaso);
			String nombreRol = null;
			List<CorreoElectronicoRolPersonaCaso> correosRolPersonaCaso = null;
			List<UbicacionRolPersonaCaso> ubicacionesRolPersonaCaso = null;			
			DominioEnmascaradoDTO dominioEnmascaradoDTO = new DominioEnmascaradoDTO();
			
			Dominio dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_PERSONA,
					persona.getTipoPersona());
			parteDTO.setTipoPersona(dominio);
			
			if (!persona.getRolPersonaCasoList().isEmpty()) {
				for (RolPersonaCaso it : persona.getRolPersonaCasoList()) {
					if ((it.getCaso().getIdCaso().compareTo(idCaso) == 0)
							&& it.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
						nombreRol = it.getRol().getNombre();
						correosRolPersonaCaso = it.getCorreoElectronicoRolPersonaCasoList();
						ubicacionesRolPersonaCaso = it.getUbicacionRolPersonaCasoList();
					}
				}
				dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ROL_PERSONA, nombreRol);
				parteDTO.setRol(dominio);
			}			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA,
					persona.getTipoDocumento());
			parteDTO.setTipoIdentificacion(dominio);
			parteDTO.setNumeroIdentificacion(persona.getNumeroDocumento());
			ZonaGeografica zonaGeografica = new ZonaGeografica();
			if (persona.getCiudadDelDocumento() != null)
				zonaGeografica = zonaGeograficaFacade.buscar(persona.getCiudadDelDocumento());
			if (zonaGeografica != null)
				parteDTO.setCiudadIdentificacion(
						zonaGeograficaFacade.transformarEntidadSinDependencias(zonaGeografica));
			parteDTO.setNacionalidad(persona.getIdPaisOrigen());
			parteDTO.setPrimerNombre(persona.getPrimerNombreORazonSocial());
			parteDTO.setSegundoNombre(persona.getSegundoNombre());
			parteDTO.setPrimerApellidoORazonSocial(persona.getPrimerApellido());
			parteDTO.setSegundoApellido(persona.getSegundoApellido());
			parteDTO.setIdPersona(persona.getIdPersona());
			
			/*
			 * Consultar Ubicaciones
			 */

			List<Ubicacion> ubicacionesPersona = new ArrayList<>();

			// Ubicación Principal
			for (Ubicacion ubicacion : persona.getUbicacionList()) {

				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(ubicacion.getEstadoRegistro())
						&& UtilDominios.TIPO_UBICACION_PRINCIPAL.equals(ubicacion.getTipo())) {
					ubicacion.setDireccion(ubicacion.getDireccion());
					ubicacionesPersona.add(ubicacion);
					break;
				}
			}
			// Ubicaciones Secundarias :: Obtenidas desde la asociación con Rol Persona Caso
			if (ubicacionesRolPersonaCaso != null && ubicacionesRolPersonaCaso.size() > 0) {
				for (UbicacionRolPersonaCaso ubicacionRPC : ubicacionesRolPersonaCaso) {
					Ubicacion ubicacion = ubicacionRPC.getUbicacion();
					ubicacion.setDireccion(ubicacion.getDireccion());
					ubicacionesPersona.add(ubicacion);
				}
			}

			// Transformar ubicaciones a DTO
			List<UbicacionDTO> lstUbicacionDTO = new ArrayList<>();

			for (Ubicacion it : ubicacionesPersona) {
				UbicacionDTO ubicacionDTO = new UbicacionDTO();
				ubicacionDTO.setIdUbicacion(it.getIdUbicacion());
				ubicacionDTO.setDireccionEnmascarada(UtilMascaraTexto.replaceCharacterByDot(it.getDireccion()));
				ubicacionDTO.setIdZonaGeografica(it.getIdZonaGeografica());
				ZonaGeografica ciudad = manejadorZonaGeografica.buscar(it.getIdZonaGeografica());
				ZonaGeografica pais = manejadorZonaGeografica.buscar(ciudad.getIdZonaGeograficaPadre());
				ubicacionDTO.setCiudad(ciudad);
				ubicacionDTO.setPais(pais);
				ubicacionDTO.setTipo(it.getTipo());
				lstUbicacionDTO.add(ubicacionDTO);
			}

			// Setear ubicaciones de la persona a los datos del formulario
			parteDTO.setLstUbicacion(lstUbicacionDTO);

			/*
			 * Telefonos
			 */
			int i = 0;
			
			for (Telefono it2 : telefonoFacade.transformarEntidadesColeccionSinDependencias(persona.getTelefonoList(),
					new ArrayList<Telefono>())) {
				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(it2.getEstadoRegistro())) {
					if (it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_CELULAR)) {
						parteDTO.setNumeroCelularEnmascarado(UtilMascaraTexto.replaceCharacterByDot(it2.getNumero()));
					}
					if (i == 0 && it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
						parteDTO.setNumeroTelefonoUnoEnmascarado(UtilMascaraTexto.replaceCharacterByDot(it2.getNumero()));
						i++;
					} else if (i == 1 && it2.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
						parteDTO.setNumeroTelefonoDosEnmascarado(UtilMascaraTexto.replaceCharacterByDot(it2.getNumero()));
						i++;
					}

				}
			}
			
			/*
			 * Correos Electronicos
			 */

			// Correo Principal
			for (CorreoElectronico it3 : correoElectronicoFacade.transformarEntidadesColeccionSinDependencias(
					persona.getCorreoElectronicoList(), new ArrayList<CorreoElectronico>())) {

				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(it3.getEstadoRegistro())
						&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL.equals(it3.getTipo())) {
					parteDTO.setEmailUnoEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(it3.getDireccion()));
					parteDTO.setEmailUnoAnteriorEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(it3.getDireccion()));
					break;
				}
			}			
			// Correo Secundario y Terciario :: Obtenidos desde la asociación con Rol
			// Persona Caso
			if (correosRolPersonaCaso != null && correosRolPersonaCaso.size() > 0) {			

				for (CorreoElectronicoRolPersonaCaso correoElectronicoRolPersonaCaso : correosRolPersonaCaso) {
					CorreoElectronico correoElectronico = correoElectronicoRolPersonaCaso.getCorreoElectronico();
					if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
							&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO
									.equals(correoElectronico.getTipo())) {
						parteDTO.setEmailDosEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(correoElectronico.getDireccion()));
						parteDTO.setEmailDosAnteriorEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(correoElectronico.getDireccion()));
					} else if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
							&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_TERCIARIO
									.equals(correoElectronico.getTipo())) {
						parteDTO.setEmailTresEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(correoElectronico.getDireccion()));
						parteDTO.setEmailTresAnteriorEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(correoElectronico.getDireccion()));
					}

					if (parteDTO.getEmailDosEnmascarado() != null && parteDTO.getEmailTresEnmascarado() != null) {
						break;
					}
				}
			}			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SEXOS, persona.getSexo());				
			parteDTO.setTipoSexo(dominio);			
			if(persona.getFechaDeNacimiento() != null) {
				parteDTO.setFechaNacimientoEnmascarado(UtilMascaraTexto.replaceCharacterByDot(persona.getFechaDeNacimiento().toString()));
			}
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESTRATOS, persona.getEstrato());
			DominioEnmascaradoDTO dominioEstratoDTO = dominioEnmascaradoDTO.transformarSinDependencias(dominio);
			if(dominio.getDominioPK() != null) {											
				DominioPKDTO dominioPKEstrato = dominioEstratoDTO.getDominioPK();				
				if(dominioPKEstrato.getCodigo() != null) {
					String codigoEstratoSinEnmascarar = dominioPKEstrato.getCodigo();
					dominioPKEstrato.setCodigo(UtilMascaraTexto.replaceCharacterByDot(codigoEstratoSinEnmascarar));
					dominioEstratoDTO.setDominioPK(dominioPKEstrato);					
				}					
			}
			dominioEstratoDTO.setNombre(UtilMascaraTexto.replaceCharacterByDot("Estrato"+dominioEstratoDTO.getNombre()));
			dominioEstratoDTO.setDescripcion(UtilMascaraTexto.replaceCharacterByDot(dominioEstratoDTO.getDescripcion()));
			parteDTO.setEstrato(dominioEstratoDTO);
							
			parteDTO.setProfesion(persona.getProfesion());
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESCOLARIDADES,
					persona.getEscolaridad());	
			DominioEnmascaradoDTO dominioEscolaridadDTO = dominioEnmascaradoDTO.transformarSinDependencias(dominio);
			if(dominio.getDominioPK() != null) {				
				DominioPKDTO dominioPKEscolaridad = dominioEscolaridadDTO.getDominioPK();
				if(dominioPKEscolaridad.getCodigo() != null) {					
					dominioPKEscolaridad.setCodigo(UtilMascaraTexto.replaceCharacterByDot(dominioPKEscolaridad.getCodigo()));
					dominioEscolaridadDTO.setDominioPK(dominioPKEscolaridad);					
				}
			}
			dominioEscolaridadDTO.setNombre(UtilMascaraTexto.replaceCharacterByDot(dominioEscolaridadDTO.getNombre()));
			dominioEscolaridadDTO.setDescripcion(UtilMascaraTexto.replaceCharacterByDot(dominioEscolaridadDTO.getDescripcion()));
			parteDTO.setEscolaridad(dominioEscolaridadDTO);
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_INSTITUCIONES_EDUCATIVAS,
					persona.getInstitucionEducativa());	
			DominioEnmascaradoDTO dominioInstitucionEducativaDTO = dominioEnmascaradoDTO.transformarSinDependencias(dominio);
			if(dominio.getDominioPK() != null) {				
				DominioPKDTO dominioPKInstitucionEducativa = dominioInstitucionEducativaDTO.getDominioPK();				
				if(dominioPKInstitucionEducativa.getCodigo() != null) {					
					dominioPKInstitucionEducativa.setCodigo(UtilMascaraTexto.replaceCharacterByDot("Institucion "+dominioPKInstitucionEducativa.getCodigo()));
					dominioInstitucionEducativaDTO.setDominioPK(dominioPKInstitucionEducativa);					
				}
			}
			dominioInstitucionEducativaDTO.setNombre(UtilMascaraTexto.replaceCharacterByDot(dominioInstitucionEducativaDTO.getNombre()));
			dominioInstitucionEducativaDTO.setDescripcion(UtilMascaraTexto.replaceCharacterByDot(dominioInstitucionEducativaDTO.getDescripcion()));
			parteDTO.setInstitucionEducativa(dominioInstitucionEducativaDTO);
			
			parteDTO.setFechaGrado(persona.getFechaDeGrado());			
			parteDTO.setNumeroTarjetaProfesional(persona.getNumeroTarjetaProfesional());
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ENTIDAD_TARJETA_PROFESIONAL,
					persona.getEntidadExpideTarjetaProfesional());
			parteDTO.setEntidadTarjetaProfesional(dominio);
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_EMPRESA,
					persona.getTipoDeEmpresa());
			parteDTO.setTipoEmpresa(dominio);
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_ENTIDAD_PUBLICA,
					persona.getTipoDeEntidadPublica());
			parteDTO.setTipoEntidadPublica(dominio);
			parteDTO.setRepresentanteLegal(persona.getRepresentanteLegal());
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SECTOR_EMPRESA,
					persona.getSectorDeLaEmpresa());
			parteDTO.setSectorEmpresa(dominio);
			parteDTO.setPaginaWeb(persona.getPaginaWeb());			
			if (nombreRol != null) {				
				String rolParte = obtenerRolParteRepresentada(nombreRol);
				List<Persona> personasRepresentadas = manejadorPersona.getConsultarPartesCasoPorRol(idCaso, rolParte);
				parteDTO.setRepresentada((List<Persona>) transformarEntidadesColeccionSinDependencias(
						personasRepresentadas, new ArrayList<Persona>()));
			}		
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_PROPIO_NEGOCIO_DE_PARTE_EQUIDAD,
					persona.getNombreNegocio());
			parteDTO.setNombreNegocio(dominio);
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ACTIV_ECONOMICA_DE_PARTE_EQUIDAD,
					persona.getActividadEconomica());
			parteDTO.setActividadEconomica(dominio);			
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_OCUPACION_DE_PARTE_EQUIDAD,
					persona.getOcupacion());
			parteDTO.setOcupacion(dominio);
			
			parteDTO.setNombreEmpresa(persona.getNombreEmpresa());
			parteDTO.setNitEmpresa(persona.getNitEmpresa());
			parteDTO.setOtraActividadEconomica(persona.getOtraActividadEconomica());
			parteDTO.setOtraNombreNegocio(persona.getOtraNombreNegocio());
			
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		} catch (Exception e) {
			logger.error(e);
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		return parteDTO;
	}
	
	public String llenarParteDTORol(FormularioParteEnmascaradoDTO parteDTO, Long idCaso,			
			List<RolPersonaCaso> rolesPersonaCasos, List<Ubicacion> ubicaciones) {
		
		String nombreRol = null;		
		List<UbicacionRolPersonaCaso> ubicacionesRolPersonaCaso = new ArrayList<>();
		List<CorreoElectronicoRolPersonaCaso> correosRolPersonaCaso = new ArrayList<>();
				
		if (!rolesPersonaCasos.isEmpty()) {
			for (RolPersonaCaso it : rolesPersonaCasos) {
				if ((it.getCaso().getIdCaso().compareTo(idCaso) == 0)
						&& it.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
					nombreRol = it.getRol().getNombre();
					correosRolPersonaCaso = it.getCorreoElectronicoRolPersonaCasoList();
					ubicacionesRolPersonaCaso = it.getUbicacionRolPersonaCasoList();
				}
			}
			Dominio dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ROL_PERSONA, nombreRol);
			parteDTO.setRol(dominio);
		}
		consultarUbicaciones(ubicacionesRolPersonaCaso, ubicaciones, parteDTO);
		
		return nombreRol;
	}
	
	public void consultarUbicaciones(List<UbicacionRolPersonaCaso> ubicacionesRolPersonaCaso, List<Ubicacion> ubicaciones,
			FormularioParteEnmascaradoDTO parteDTO) {
		
		List<Ubicacion> ubicacionesPersona = new ArrayList<>();

		// Ubicación Principal
		for (Ubicacion ubicacion : ubicaciones) {

			if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(ubicacion.getEstadoRegistro())
					&& UtilDominios.TIPO_UBICACION_PRINCIPAL.equals(ubicacion.getTipo())) {
				ubicacion.setDireccion(ubicacion.getDireccion());
				ubicacionesPersona.add(ubicacion);
				break;
			}
		}
		// Ubicaciones Secundarias :: Obtenidas desde la asociación con Rol Persona Caso
		if (ubicacionesRolPersonaCaso != null && ubicacionesRolPersonaCaso.size() > 0) {
			for (UbicacionRolPersonaCaso ubicacionRPC : ubicacionesRolPersonaCaso) {
				Ubicacion ubicacion = ubicacionRPC.getUbicacion();
				ubicacion.setDireccion(ubicacion.getDireccion());
				ubicacionesPersona.add(ubicacion);
			}
		}
		
		transformarUbicacionesADTO(ubicacionesPersona, parteDTO);
	}
	
	public void transformarUbicacionesADTO(List<Ubicacion> ubicacionesPersona, FormularioParteEnmascaradoDTO parteDTO) {
		
		List<UbicacionDTO> lstUbicacionDTO = new ArrayList<>();

		for (Ubicacion it : ubicacionesPersona) {
			UbicacionDTO ubicacionDTO = new UbicacionDTO();
			ubicacionDTO.setIdUbicacion(it.getIdUbicacion());
			ubicacionDTO.setDireccionEnmascarada(UtilMascaraTexto.replaceCharacterByDot(it.getDireccion()));
			ubicacionDTO.setIdZonaGeografica(it.getIdZonaGeografica());
			ZonaGeografica ciudad = manejadorZonaGeografica.buscar(it.getIdZonaGeografica());
			ZonaGeografica pais = manejadorZonaGeografica.buscar(ciudad.getIdZonaGeograficaPadre());
			ubicacionDTO.setCiudad(ciudad);
			ubicacionDTO.setPais(pais);
			ubicacionDTO.setTipo(it.getTipo());
			lstUbicacionDTO.add(ubicacionDTO);
		}

		// Setear ubicaciones de la persona a los datos del formulario
		parteDTO.setLstUbicacion(lstUbicacionDTO);	
	}
	
	public void llenarParteDTOConTelefonosPersona(FormularioParteEnmascaradoDTO parteDTO, List<Telefono> telefonos) {
		
		int i = 0;
		Collection<Telefono> lstTelefono = telefonoFacade.transformarEntidadesColeccionSinDependencias(telefonos,
				new ArrayList<Telefono>());
		
		for (Telefono telefono : lstTelefono) {
			if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(telefono.getEstadoRegistro())) {
				if (telefono.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_CELULAR)) {
					parteDTO.setNumeroCelularEnmascarado(UtilMascaraTexto.replaceCharacterByDot(telefono.getNumero()));
				}
				if (i == 0 && telefono.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
					parteDTO.setNumeroTelefonoUnoEnmascarado(UtilMascaraTexto.replaceCharacterByDot(telefono.getNumero()));
					i++;
				} else if (i == 1 && telefono.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
					parteDTO.setNumeroTelefonoDosEnmascarado(UtilMascaraTexto.replaceCharacterByDot(telefono.getNumero()));
					i++;
				}
			}
		}
	}
	
	public void llenarParteDTOConCorreoPrincipal(FormularioParteEnmascaradoDTO parteDTO, List<CorreoElectronico> correosElectronicos) {
		
		for (CorreoElectronico it3 : correoElectronicoFacade.transformarEntidadesColeccionSinDependencias(
				correosElectronicos, new ArrayList<CorreoElectronico>())) {

			if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(it3.getEstadoRegistro())
					&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL.equals(it3.getTipo())) {
				parteDTO.setEmailUnoEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(it3.getDireccion()));
				parteDTO.setEmailUnoAnteriorEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(it3.getDireccion()));
				break;
			}
		}	
	}
	
	public void llenarParteDTOCorreoSecundarioTerciario(FormularioParteEnmascaradoDTO parteDTO, List<CorreoElectronicoRolPersonaCaso> correosRolPersonaCaso) {
				
		if (correosRolPersonaCaso != null && correosRolPersonaCaso.size() > 0) {			
			
			for (CorreoElectronicoRolPersonaCaso correoElectronicoRolPersonaCaso : correosRolPersonaCaso) {
				CorreoElectronico correoElectronico = correoElectronicoRolPersonaCaso.getCorreoElectronico();
				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
						&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO
								.equals(correoElectronico.getTipo())) {					
					parteDTO.setEmailDosEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(correoElectronico.getDireccion()));
					parteDTO.setEmailDosAnteriorEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(correoElectronico.getDireccion()));
				} else if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
						&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_TERCIARIO
								.equals(correoElectronico.getTipo())) {
					parteDTO.setEmailTresEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(correoElectronico.getDireccion()));
					parteDTO.setEmailTresAnteriorEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(correoElectronico.getDireccion()));
				}

				if (parteDTO.getEmailDosEnmascarado() != null && parteDTO.getEmailTresEnmascarado() != null) {
					break;
				}
			}
		}
	}
	
	@Override
	public FormularioDatosClienteDTO validarIdentificacionEnmascaradoPagadorSolicitud(PersonaBasicaDTO personaBasicaDTO) {		
		FormularioDatosClienteDTO formularioDatosClienteDTO = null;
		// 1. Verificación de existencia de la persona en SIREP
		formularioDatosClienteDTO = integracionSWFacade.consultarDatosBasicosClienteSirep(
				personaBasicaDTO.getTipoDocumento(), personaBasicaDTO.getNumeroDocumento());				
		
		// 2. Si el usuario no existe en SIREP se consulta en el sistema
		// SIMASC
		if (formularioDatosClienteDTO == null) {			
			Persona persona = consultarPersonaPorIdentificacion(personaBasicaDTO.getTipoDocumento(),
					personaBasicaDTO.getNumeroDocumento(), false);
			// 2.1. Si el usuario existe en SIMASC se recuperan los datos
			// básicos de cliente
			if (persona != null) {				
				formularioDatosClienteDTO = transformarPersonaAFormularioDatosClienteDTO(persona);				
			} else {				
				formularioDatosClienteDTO = new FormularioDatosClienteDTO();
			}
		}		
		System.out.print("*******************EMAIL DE SIREP**********"+formularioDatosClienteDTO.getEmail()+"*****");
		formularioDatosClienteDTO.setNumeroIdentificacion(personaBasicaDTO.getNumeroDocumento());
		formularioDatosClienteDTO.setTipoIdentificacion(personaBasicaDTO.getTipoDocumento());		
		formularioDatosClienteDTO.setDireccionEnmascarado(UtilMascaraTexto.replaceCharacterByDot(formularioDatosClienteDTO.getDireccion()));			
		formularioDatosClienteDTO.setNumeroTelefonoEnmascarado(UtilMascaraTexto.replaceCharacterByDot(formularioDatosClienteDTO.getNumeroTelefono()));		
		formularioDatosClienteDTO.setEmailEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(formularioDatosClienteDTO.getEmail()));
		formularioDatosClienteDTO.setDireccion(null);			
		formularioDatosClienteDTO.setNumeroTelefono(null);		
		formularioDatosClienteDTO.setEmail(null);
		return formularioDatosClienteDTO;
	}
	
	@Override
	public List<PreseleccionadoDesignadoDTO> consultarPreseleccionadosCasoDesignado(Long idCaso) {
		List<PreseleccionadoDesignadoDTO> preseleccionadoDTOList = new ArrayList<>();
		try {
			preseleccionadoDTOList = manejadorPreseleccionado.consultarPreseleccionadosCasoDesignado(idCaso);			
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		return preseleccionadoDTOList;
	}
}
