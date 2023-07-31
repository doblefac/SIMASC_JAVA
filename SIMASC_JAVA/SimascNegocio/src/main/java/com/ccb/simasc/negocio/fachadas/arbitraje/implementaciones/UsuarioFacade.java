package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.comun.seguridad.fachada.interfaces.ISeguridadFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorClave;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorHomologacionSistemaExterno;
import com.ccb.simasc.integracion.manejadores.ManejadorIdioma;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorProfesion;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefono;
import com.ccb.simasc.integracion.manejadores.ManejadorTipoDeServicioRol;
import com.ccb.simasc.integracion.manejadores.ManejadorUbicacion;
import com.ccb.simasc.integracion.manejadores.ManejadorUsuario;
import com.ccb.simasc.integracion.manejadores.ManejadorZonaGeografica;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlertaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICentroFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroDeServicioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPlantillaCartaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUsuarioFacade;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.FiltroBusquedaUsuarioSistemaDTO;
import com.ccb.simasc.transversal.dto.IdiomaDTO;
import com.ccb.simasc.transversal.dto.ParametroDeServicioDTO;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.RolPersonaDTO;
import com.ccb.simasc.transversal.dto.TelefonoDTO;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.UsuarioClaveDTO;
import com.ccb.simasc.transversal.dto.UsuarioDTO;
import com.ccb.simasc.transversal.dto.UsuarioSistemaConsultaDTO;
import com.ccb.simasc.transversal.dto.ZonaGeograficaDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Centro;
import com.ccb.simasc.transversal.entidades.Clave;
import com.ccb.simasc.transversal.entidades.ClavePK;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.Idioma;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.Profesion;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.entidades.TipoDeServicioRol;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.entidades.Usuario;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
//import com.sun.xml.internal.ws.util.UtilException;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Usuario}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class UsuarioFacade extends AccesoFacade<Usuario, String, UsuarioDTO> implements IUsuarioFacade {

	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	private static final Logger LOG = Logger.getLogger(UsuarioFacade.class.getName());

	@EJB
	private ManejadorUsuario manejadorUsuario;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorRolPersona manejadorRolPersona;

	@EJB
	private ICentroFacade centroFacade;

	@EJB
	private IRolFacade rolFacade;

	@EJB
	private IRolPersonaFacade rolPersonaFacade;

	@EJB
	private ISeguridadFacade seguridadFacade;

	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;

	@EJB
	private ManejadorCorreoElectronico manejadorCorreo;

	@EJB
	private ManejadorUbicacion manejadorUbicacion;

	@EJB
	private ManejadorTelefono manejadorTelefono;

	@EJB
	private ManejadorIdioma manejadorIdioma;

	@EJB
	private ManejadorClave manejadorClave;

	@EJB
	private ManejadorZonaGeografica manejadorZonaGeografica;

	@EJB
	private IDominioFacade dominioFacade;

	@EJB
	private ManejadorTipoDeServicioRol manejadorTipoDeServicioRol;

	@EJB
	private CorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	@EJB
	private ManejadorProfesion manejadorProfesion;

	@EJB
	private IParametroDeServicioFacade parametroDeServicioFacade;

	@EJB
	private IPersonaFacade personaFacade;

	@EJB
	private IPlantillaCartaFacade plantillaCartaFacade;

	@EJB
	private IAlertaFacade alertaFacade;

	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;

	@EJB
	private IParametrosGeneralesFacade parametrosGeneralesFacade;
	
	@EJB
	private ManejadorServicio manejadorServicio;
	
	@EJB
	private ManejadorHomologacionSistemaExterno manejadorHomologacionSistemaExterno;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorUsuario;
	}

	@Override
	public UsuarioDTO transformarSinDependencias(Usuario obj) {
		return new UsuarioDTO().transformarSinDependencias(obj);
	}

	@Override
	public UsuarioDTO transformarConDependencias(Usuario obj) {
		return new UsuarioDTO().transformarConDependencias(obj);
	}

	@Override
	public Usuario transformarEntidadConDependencias(Usuario obj) {
		return new UsuarioDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Usuario transformarEntidadSinDependencias(Usuario obj) {
		return new UsuarioDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * Consulta los usuarios del sistema para la consulta del caso de uso ADM-C-020
	 */
	public List<UsuarioSistemaConsultaDTO> consultarUsuariosSistema(String nombres, String apellidos, String nombreRol,
			String nombreRolUsuario) {

		FiltroBusquedaUsuarioSistemaDTO filtro = new FiltroBusquedaUsuarioSistemaDTO(nombres, apellidos, nombreRol,
				consultarGrupoConsultaUsuario(nombreRolUsuario));

		return manejadorUsuario.consultarUsuariosSistemaConsultaDTO(filtro);
	}

	private String consultarGrupoConsultaUsuario(String nombreRolUsuario) {
		String grupoUsuario = rolFacade.consultarGrupoUsuario(nombreRolUsuario);
		String grupoConsultaUsuario = "";
		switch (grupoUsuario) {
		case UtilDominios.AGRUPADOR_RPCU_ROLES_ARBITRAJE:
			grupoConsultaUsuario = UtilDominios.AGRUPADOR_RPCU_ROLES_PERMITIDOS_ARBITRAJE;
			break;
		case UtilDominios.AGRUPADOR_RPCU_ROLES_CONCILIACION:
			grupoConsultaUsuario = UtilDominios.AGRUPADOR_RPCU_ROLES_PERMITIDOS_CONCILIACION;
			break;
		case UtilDominios.AGRUPADOR_RPCU_ROLES_DIRECCION:
			grupoConsultaUsuario = UtilDominios.AGRUPADOR_RPCU_ROLES_PERMITIDOS_DIRECTOR_CAC;
			break;
		case UtilDominios.AGRUPADOR_RPCU_ROLES_EQUIDAD:
			grupoConsultaUsuario = UtilDominios.AGRUPADOR_RPCU_ROLES_PERMITIDOS_EQUIDAD;
			break;
		case UtilDominios.AGRUPADOR_RPCU_ROLES_INTERNACIONAL:
			grupoConsultaUsuario = UtilDominios.AGRUPADOR_RPCU_ROLES_PERMITIDOS_INTERNACIONAL;
			break;
		default:
			break;
		}

		return grupoConsultaUsuario;
	}

	@Override
	public void crearUsuarioParte(Long idPersona) throws SIMASCNegocioExcepcion {
		Usuario usuarioActual = manejadorUsuario.consultarUsuarioPorPersonaEstado(idPersona, null);
		Persona persona = manejadorPersona.buscar(idPersona);
		Boolean aplicaMuac = false;
		
		String tipoDocumentoHomologado = manejadorHomologacionSistemaExterno.
				consultarCodigosSistemaExterno(UtilConstantes.SISTEMA_EXTERNO_MAUC,
						persona.getTipoDocumento(), UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);

		if(UtilSimasc.validarUsuarioCrearEnMauc(persona) && tipoDocumentoHomologado != null
				&& !(persona.getTipoFuncionario().equalsIgnoreCase(UtilDominios.PERSONA_FUNCIONARIO_INTERNO))) {
			
			aplicaMuac = this.crearUsuarioMauc(persona);
		}
		
		if (usuarioActual == null) {
			usuarioActual = this.crearUsuario(idPersona);
			if(aplicaMuac) {
				usuarioActual.setAplicaMauc(aplicaMuac);
				manejadorUsuario.actualizar(usuarioActual);
			}
		} else {
			usuarioActual.setEstado(UtilDominios.ESTADO_USUARIO_ACTIVO);
			usuarioActual.setAplicaMauc(aplicaMuac);
			manejadorUsuario.actualizar(usuarioActual);
		}

		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setUsuarioLogin(usuarioActual.getUsuarioLogin());
		usuarioDto.setIdPersona(idPersona);
		usuarioDto.setAplicaMauc(usuarioActual.getAplicaMauc());
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			seguridadFacade.generarClave(usuarioDto, appContext.getContextoSesion().getNombreUsuario());
		} else {
			seguridadFacade.generarClave(usuarioDto, UtilConstantes.USUARIO_DEFECTO_SIMASC);
		}
	}

	/**
	 * Utilizado en ADM-C-021
	 */
	public Usuario crearUsuarioApersona(Long idPersona) {
		Persona persona = manejadorPersona.buscar(idPersona);
		if (persona == null || persona.getNumeroDocumento() == null) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR526.toString()));
		}
		Usuario usuario = new Usuario();
		usuario.setEstado(UtilDominios.ESTADO_USUARIO_ACTIVO);
		usuario.setIdPersona(idPersona);
		usuario.setUsuarioLogin(persona.getNumeroDocumento());
		manejadorUsuario.crear(usuario);
		return usuario;

	}

	/**
	 * ADM-C-021 Crea el usuario, a persona asociada al usuario en caso de que no
	 * exista y un rol en caso de que no lo tenga asignado. Devuelve el id de la
	 * persona en caso de que no exista. Devuelve -2 si la persona ya existía y se
	 * le asigno el rol. Devuelve excepción si tanto el usuario como el rol a
	 * asignar ya existían.
	 */
	public Long crearUsuarioSistema(FormularioParteDTO usuarioSistemaDTO) {
		Persona persona = this.transformarFormularioParteDTOAEntidadPersona(usuarioSistemaDTO);
		boolean personaExiste = false;

		if (persona.getIdPersona() == null) {
			persona = manejadorPersona.crearPersona(persona);
			usuarioSistemaDTO.getRolPersona().setIdPersona(persona.getIdPersona());
		} else {
			personaExiste = true;
			// Asignación de los atributos de auditoria
			if (appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null) {
				persona.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
				persona.setFechaUltimaModificacion(new Date());
			}
			manejadorPersona.actualizar(persona);
			if (usuarioSistemaDTO.getRolPersona() != null && usuarioSistemaDTO.getRolPersona().getIdPersona() == null) {
				usuarioSistemaDTO.getRolPersona().setIdPersona(persona.getIdPersona());
			}
		}

		manejadorCorreo.crearOActualizarCorreosElectronicosPersona(usuarioSistemaDTO.getCorreosElectronicos(),
				persona.getIdPersona());

		manejadorTelefono.crearOActualizarTelefonosPersona(usuarioSistemaDTO.getTelefonos(), persona.getIdPersona());

		manejadorIdioma.crearOActualizarIdiomasPersona(usuarioSistemaDTO.getIdiomas(), persona.getIdPersona());

		manejadorUbicacion.crearOActualizarUbicacionesPersona(usuarioSistemaDTO.getLstUbicacion(),
				persona.getIdPersona());

		RolPersona rolPersona = null;
		Boolean aplicaMuac = false;
		


		if (usuarioSistemaDTO.getRolPersona() != null) {
			try {
				rolPersona = asignarRolAPersona(usuarioSistemaDTO.getRolPersona());
			} catch (SIMASCNegocioExcepcion e) {
				lanzarSIMASCNegocioExcepcion(MensajesEnum.ERROR528.toString());
			}
		}

		//validamos si el usuario se debe crear en mauc o solo en simasc.
		if(!personaExiste) {
		//Cuando un usuario es nuevo se debe asignar el telefono y correo, datos basicos para MAUC
			List<Telefono> telefonos = new TelefonoDTO().transformarColeccionDTOAColeccionEntidades(usuarioSistemaDTO.getTelefonos());
			List<CorreoElectronico> correos = new CorreoElectronicoDTO().transformarColeccionDTOAColeccionEntidades(usuarioSistemaDTO.getCorreosElectronicos());
			persona.setTelefonoList(telefonos);
			persona.setCorreoElectronicoList(correos);
			
			if(!(persona.getTipoFuncionario().equalsIgnoreCase(UtilDominios.PERSONA_FUNCIONARIO_INTERNO)) && 
					rolPersona != null &&  rolPersona.getRol() != null && rolPersona.getRol().getAplicaMauc()) {
				String tipoDocumentoHomologado = manejadorHomologacionSistemaExterno.
						consultarCodigosSistemaExterno(UtilConstantes.SISTEMA_EXTERNO_MAUC,
								persona.getTipoDocumento(), UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);
				
				if(tipoDocumentoHomologado != null) {
					aplicaMuac = this.crearUsuarioMauc(persona);
				}
			}
			persona.setTelefonoList(null);
			persona.setCorreoElectronicoList(null);
		}else {			
			aplicaMuac = validarFuncionarioAplicaMauc(persona);		
		}
		
		Usuario usuario = crearUsuarioPersona(persona,aplicaMuac);

		if (usuario != null) {
			seguridadFacade.generarClaveUsuario(usuario);
		}

		if (rolPersona != null && manejadorRol.validarRolEsPrestadorDeServicio(rolPersona.getRol().getNombre())) {
			enviarCorreoJefesDeArea(rolPersona, persona);
		}

		Long codigoRetorno;
		if (!personaExiste) {
			codigoRetorno = persona.getIdPersona();
		} else {
			// La persona ya existía pero el rol no. Se añade el rol.
			codigoRetorno = -2L;
		}

		return codigoRetorno;

	}

	/**
	 * ADM-C-021 Si el rol del servicio es prestador de servicio se envía correo al
	 * jefe o jefes del area.
	 */
	public void enviarCorreoJefesDeArea(RolPersona rolAsignado, Persona persona) {
		String mensajeBase = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO210.toString());
		String nombreRolAsignado = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA,
				rolAsignado.getRol().getNombre());
		String textoCorreo = String.format(mensajeBase, persona.getNombreCompleto(), persona.getTipoDocumento(),
				persona.getNumeroDocumento(), nombreRolAsignado);
		List<String> asunto = new ArrayList<>();
		asunto.add(textoCorreo);

		List<TipoDeServicioRol> tiposDeServicioRol = manejadorTipoDeServicioRol.consultarTiposDeServicioRol(
				String.valueOf(rolAsignado.getIdRol()), TipoDeServicioRol.ENTIDAD_TIPO_DE_SERVICIO_ROL_PK_ID_ROL);

		for (TipoDeServicioRol tipoDeServicioRol : tiposDeServicioRol) {
			List<Dominio> dominio = dominioFacade.obtenerDominiosAgrupados(UtilDominios.DOMINIO_TIPO_SERVICIO,
					tipoDeServicioRol.getTipoDeServicioRolPK().getTipoServicio());
			List<String> rolJefe = new ArrayList<>();
			rolJefe.add(dominio.get(0).getDominioPK().getCodigo());
			List<RolPersona> rolPersonaJefes = manejadorRolPersona.consultarRolPersonasPorRoles(rolJefe);
			if (rolPersonaJefes != null && !rolPersonaJefes.isEmpty()) {
				List<Persona> jefes = RolPersonaDTO.obtenerPersonas(rolPersonaJefes);
				correoRolPersonaCasoFacade.envioDeCorreo(UtilConstantes.ASUNTO_CORREO_NOTIFICACION_CREACION_USUARIO,
						jefes, null, null, asunto, null, null, null, false);
			} else {
				lanzarSIMASCNegocioExcepcion(MensajesEnum.ERROR272.toString());
			}
		}

	}

	/**
	 * ADM-C-021 Identifica si la persona existe validando si existe una persona con
	 * el id persona y si no con el tipo y número de identificación. Si la persona
	 * existe asigna a la entidad que se pasa como parámetro el identificador de la
	 * persona en base de datos. Si el tipo de identificación es sin identificación
	 * y el id de la persona viene definido en formularioParteDTO se asigna ese id a
	 * la entidad persona a guardar.
	 * 
	 * @param formularioParteDTO
	 * @param persona
	 */
	private Persona crearOConsultarPersona(FormularioParteDTO formularioParteDTO) {
		boolean isSinID = UtilDominios.TIPO_DOCUMENTO_PERSONA_SIN_IDENTIFICACION
				.equals(formularioParteDTO.getTipoIdentificacion().getDominioPK().getCodigo());

		Persona persona = null;
		if (formularioParteDTO.getIdPersona() != null) {
			persona = manejadorPersona.buscar(formularioParteDTO.getIdPersona());
			if (persona == null) {
				lanzarSIMASCNegocioExcepcion(MensajesEnum.ERROR275.toString());
			}
		} else if (formularioParteDTO.getTipoIdentificacion() != null
				&& formularioParteDTO.getTipoIdentificacion().getDominioPK() != null && !isSinID) {
			// Si el tipo de identificación viene seteado y no es "Sin
			// identificación" es porque viene un número de identificación definido
			persona = manejadorPersona.consultarPersonaPorIdentificacion(
					UtilOperaciones.obtenerCodigoDominio(formularioParteDTO.getTipoIdentificacion()),
					formularioParteDTO.getNumeroIdentificacion());

			if (persona == null) {
				persona = new Persona();
			}

		} else if (isSinID && formularioParteDTO.getIdPersona() != null) {
			persona = manejadorPersona.buscar(formularioParteDTO.getIdPersona());
			if (persona == null) {
				List<String> parametrosMensaje = new ArrayList<String>();
				parametrosMensaje.add(formularioParteDTO.getIdPersona().toString());
				String mensajeError = String.format(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR500.toString()),
						parametrosMensaje.toArray());
				throw new SIMASCNegocioExcepcion(mensajeError);
			}
		}

		return persona;
	}

	/**
	 * ADM-C-021 Transforma la información que viene en FormularioParteDTO a las
	 * entidades de Persona, RolPersona, Telefono, CorreoElectrónico, Ubicación, e
	 * Idioma para almacenar la información en base de datos.
	 */
	private Persona transformarFormularioParteDTOAEntidadPersona(FormularioParteDTO formularioParteDTO) {

		Persona persona = this.crearOConsultarPersona(formularioParteDTO);
		if (persona == null) {
			String mensajeError = String
					.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR275.toString()));
			throw new SIMASCNegocioExcepcion(mensajeError);
		}

		persona.setTipoDocumento(UtilOperaciones.obtenerCodigoDominio(formularioParteDTO.getTipoIdentificacion()));
		persona.setNumeroDocumento(formularioParteDTO.getNumeroIdentificacion());
		persona.setPrimerNombreORazonSocial(formularioParteDTO.getPrimerNombre());
		if (formularioParteDTO.getSegundoNombre() != null) {
			persona.setSegundoNombre(formularioParteDTO.getSegundoNombre());
		}
		persona.setPrimerApellido(formularioParteDTO.getPrimerApellidoORazonSocial());
		if (formularioParteDTO.getSegundoApellido() != null) {
			persona.setSegundoApellido(formularioParteDTO.getSegundoApellido());
		}

		persona.setTipoPersona(UtilOperaciones.obtenerCodigoDominio(formularioParteDTO.getTipoPersona()));

		if (formularioParteDTO.getTipoFuncionario() != null && !formularioParteDTO.getTipoFuncionario().isEmpty()) {
			persona.setTipoFuncionario(formularioParteDTO.getTipoFuncionario());
		} else {
			persona.setTipoFuncionario(null);
		}

		if (formularioParteDTO.getNumeroTarjetaProfesional() != null) {
			persona.setNumeroTarjetaProfesional(formularioParteDTO.getNumeroTarjetaProfesional());
		}

		persona.setEstadoPersona(UtilDominios.ESTADO_FUNC_EXTERNO_ACTIVO);

		persona.setIdPaisOrigen(formularioParteDTO.getNacionalidad());

		if (formularioParteDTO.getCiudadIdentificacionTexto() != null
				&& !formularioParteDTO.getCiudadIdentificacionTexto().isEmpty()) {
			persona.setCiudadDelDocumento(formularioParteDTO.getCiudadIdentificacionTexto());
		} else {
			persona.setCiudadDelDocumento((formularioParteDTO.getCiudadIdentificacion() == null
					|| formularioParteDTO.getCiudadIdentificacion().getIdZonaGeografica() == null) ? null
							: formularioParteDTO.getCiudadIdentificacion().getIdZonaGeografica());
		}
		if (formularioParteDTO.getFechaNacimiento() != null) {
			persona.setFechaDeNacimiento(formularioParteDTO.getFechaNacimiento());
		}
		if (formularioParteDTO.getEscolaridad() != null) {
			persona.setEscolaridad(UtilOperaciones.obtenerCodigoDominio(formularioParteDTO.getEscolaridad()));
		}

		if (formularioParteDTO.getEstrato() != null) {
			persona.setEstrato(UtilOperaciones.obtenerCodigoDominio(formularioParteDTO.getEstrato()));
		}
		persona.setSexo(UtilOperaciones.obtenerCodigoDominio(formularioParteDTO.getTipoSexo()));
		if (formularioParteDTO.getInstitucionEducativa() != null) {
			persona.setInstitucionEducativa(
					UtilOperaciones.obtenerCodigoDominio(formularioParteDTO.getInstitucionEducativa()));
		}
		if (formularioParteDTO.getFechaGrado() != null) {
			persona.setFechaDeGrado(formularioParteDTO.getFechaGrado());
		}
		if (formularioParteDTO.getIdPolitica() != null) {
			persona.setIdPolitica(formularioParteDTO.getIdPolitica());
		}
		if (formularioParteDTO.getEntidadTarjetaProfesional() != null) {
			persona.setEntidadExpideTarjetaProfesional(
					UtilOperaciones.obtenerCodigoDominio(formularioParteDTO.getEntidadTarjetaProfesional()));
		}

		persona.setEstadoRegistro(UtilDominios.ESTADO_PERSONA_ACTIVO);

		if (UtilDominios.TIPO_PERSONA_JURIDICO
				.equals(UtilOperaciones.obtenerCodigoDominio(formularioParteDTO.getTipoPersona()))) {
			persona.setTipoDeEmpresa(UtilOperaciones.obtenerCodigoDominio(formularioParteDTO.getTipoEmpresa()));
			persona.setTipoDeEntidadPublica(
					UtilOperaciones.obtenerCodigoDominio(formularioParteDTO.getTipoEntidadPublica()));
			persona.setRepresentanteLegal(formularioParteDTO.getRepresentanteLegal());
			persona.setSectorDeLaEmpresa(UtilOperaciones.obtenerCodigoDominio(formularioParteDTO.getSectorEmpresa()));
			persona.setPaginaWeb(formularioParteDTO.getPaginaWeb());
		}

		if (UtilDominios.TIPO_DOCUMENTO_PERSONA_SIN_IDENTIFICACION
				.equals(UtilOperaciones.obtenerCodigoDominio(formularioParteDTO.getTipoIdentificacion()))) {
			persona.setNumeroDocumento(null);
		}

		if (formularioParteDTO.getProfesion() != null) {
			Profesion profesion = new Profesion();
			profesion.setIdProfesion(formularioParteDTO.getProfesion().getIdProfesion());
			persona.setProfesion(profesion);
			persona.setIdProfesion(formularioParteDTO.getProfesion().getIdProfesion());
		}

		return persona;
	}

	/**
	 * ADM-C-021 Crea el rol que se pasa como parámetro. Si la persona ya tiene
	 * asignado el rol se lanza excepción de negocio.
	 * 
	 * @param rolPersonaDTO
	 */
	private RolPersona asignarRolAPersona(RolPersonaDTO rolPersonaDTO) {

		if (rolPersonaDTO.getIdRol() == null && rolPersonaDTO.getNombreRol() != null) {
			Rol rol = manejadorRol.consultarRolPorNombre(rolPersonaDTO.getNombreRol());
			rolPersonaDTO.setIdRol(rol.getIdRol());
		}

		if (rolPersonaDTO.getNombreRol() == null || rolPersonaDTO.getNombreRol().isEmpty()) {
			rolPersonaDTO.setNombreRol(manejadorRol.consultarNombreRolPorId(rolPersonaDTO.getIdRol()));
		}

		// Se valida si el rol es de arbitraje debido a que para arbitraje no se
		// tiene manejo de varios centros
		boolean esRolArbitraje = dominioFacade.validarGrupoContieneDominioPorCodigo(
				UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA_CONSULTA_USUARIO,
				UtilDominios.AGRUPADOR_RPCU_ROLES_PERMITIDOS_ARBITRAJE, rolPersonaDTO.getNombreRol());
		boolean rolValidoAAsignar = false;

		// Esta validación se debe unificar cuando se incluyan los centro en
		// arbitraje
		if (esRolArbitraje) {
			rolValidoAAsignar = manejadorRolPersona.validarUsuarioNoTieneRol(rolPersonaDTO.getIdPersona(),
					rolPersonaDTO.getIdRol(), null);
		} else {
			rolValidoAAsignar = manejadorRolPersona.validarUsuarioNoTieneRol(rolPersonaDTO.getIdPersona(),
					rolPersonaDTO.getIdRol(), rolPersonaDTO.getIdCentro());
		}

		// Se setea la vigencia de la asignación del rol
		if (rolPersonaDTO.getFechaInicioVigencia() == null) {
			rolPersonaDTO.setFechaInicioVigencia(new Date());
		}
		rolPersonaDTO.setFechaFinVigencia(null);
		rolPersonaDTO.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		RolPersona rolPersona = null;
		if (rolValidoAAsignar) {
			rolPersona = RolPersonaDTO.transformarDTOAEntidad(rolPersonaDTO);
			manejadorRolPersona.crear(rolPersona);
			rolPersona.setRol(manejadorRol.buscar(rolPersona.getIdRol()));
		} else {
			lanzarSIMASCNegocioExcepcion(MensajesEnum.ERROR528.toString());
		}

		return rolPersona;
	}

	/**
	 * ADM-F-021 Consulta la información del usuario con tipo de identificación y
	 * número de identificación que se pasan como parámetro. Lanza excepción si la
	 * persona consultada es una persona jurídica debido a que está funcionalidad
	 * solo está pensada para personas naturales.
	 * 
	 * @param tipoIdentificacion
	 * @param numeroIdentificacion
	 * @return
	 */
	public FormularioParteDTO consultarUsuarioSistema(String tipoIdentificacion, String numeroIdentificacion) {
		Persona persona = manejadorPersona.consultarPersonaPorIdentificacion(tipoIdentificacion, numeroIdentificacion);

		FormularioParteDTO formularioParteDTO = new FormularioParteDTO();
		if (persona != null) {
			if (UtilDominios.TIPO_PERSONA_JURIDICO.equals(persona.getTipoPersona())) {
				String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR529.toString());
				throw new SIMASCNegocioExcepcion(mensajeError);
			}
			formularioParteDTO = transformarPersonaAFormularioParteDTO(persona);
		}

		return formularioParteDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUsuarioFacade#
	 * consultarUsuarioPorIdPersona(Long)
	 */
	@Override
	public FormularioParteDTO consultarUsuarioPorIdPersona(Long idPersona) {
		FormularioParteDTO formularioParteDTO = new FormularioParteDTO();

		Persona persona = manejadorPersona.buscar(idPersona);
		if (persona != null) {
			formularioParteDTO = transformarPersonaAFormularioParteDTO(persona);
		}

		return formularioParteDTO;
	}

	/**
	 * ADM-C-021 Transforma la entidad persona al DTO con la información a presentar
	 * en la vista
	 * 
	 * @param persona
	 * @return
	 */
	private FormularioParteDTO transformarPersonaAFormularioParteDTO(Persona persona) {
		FormularioParteDTO formularioParteDTO = new FormularioParteDTO();
		formularioParteDTO.setTipoPersona(
				dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_PERSONA, persona.getTipoPersona()));
		formularioParteDTO.setTipoIdentificacion(dominioFacade
				.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA, persona.getTipoDocumento()));
		formularioParteDTO.setNumeroIdentificacion(persona.getNumeroDocumento());
		formularioParteDTO.setCiudadIdentificacionTexto(persona.getCiudadDelDocumento());
		formularioParteDTO.setNacionalidad(persona.getZonaGeografica().getIdZonaGeografica());
		formularioParteDTO.setPrimerNombre(persona.getPrimerNombreORazonSocial());
		formularioParteDTO.setSegundoNombre(persona.getSegundoNombre());
		formularioParteDTO.setPrimerApellidoORazonSocial(persona.getPrimerApellido());
		formularioParteDTO.setSegundoApellido(persona.getSegundoApellido());
		formularioParteDTO
				.setTipoSexo(dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SEXOS, persona.getSexo()));
		formularioParteDTO.setFechaNacimiento(persona.getFechaDeNacimiento());
		formularioParteDTO.setEstrato(
				dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESTRATOS, persona.getEstrato()));
		formularioParteDTO.setProfesion(manejadorProfesion.buscar(persona.getIdProfesion()));
		formularioParteDTO.setEscolaridad(dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESCOLARIDADES,
				persona.getEscolaridad()));
		formularioParteDTO.setInstitucionEducativa(dominioFacade.getDominioSinClasificadores(
				UtilDominios.DOMINIO_INSTITUCIONES_EDUCATIVAS, persona.getInstitucionEducativa()));
		formularioParteDTO.setFechaGrado(persona.getFechaDeGrado());
		formularioParteDTO.setNumeroTarjetaProfesional(persona.getNumeroTarjetaProfesional());
		formularioParteDTO.setEntidadTarjetaProfesional(dominioFacade.getDominioSinClasificadores(
				UtilDominios.DOMINIO_ENTIDAD_TARJETA_PROFESIONAL, persona.getEntidadExpideTarjetaProfesional()));
		formularioParteDTO.setTipoEmpresa(dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_EMPRESA,
				persona.getTipoDeEmpresa()));
		formularioParteDTO.setTipoEntidadPublica(dominioFacade.getDominioSinClasificadores(
				UtilDominios.DOMINIO_TIPO_ENTIDAD_PUBLICA, persona.getTipoDeEntidadPublica()));
		formularioParteDTO.setRepresentanteLegal(persona.getRepresentanteLegal());
		formularioParteDTO.setSectorEmpresa(dominioFacade
				.getDominioSinClasificadores(UtilDominios.DOMINIO_SECTOR_EMPRESA, persona.getSectorDeLaEmpresa()));
		formularioParteDTO.setPaginaWeb(persona.getPaginaWeb());
		formularioParteDTO.setRepresentada((List<Persona>) new PersonaDTO()
				.transformarColeccionEntidadesSinDependencias(persona.getPersonasNaturalesList()));
		formularioParteDTO.setNombreCompleto(persona.getNombreCompleto());
		formularioParteDTO.setIdPersona(persona.getIdPersona());
		formularioParteDTO.setIdPolitica(persona.getIdPolitica());
		formularioParteDTO.setTipoFuncionario(persona.getTipoFuncionario());

		/*
		 * Consultar Ubicaciones
		 */

		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();

		// Ubicaciones Principales
		List<Ubicacion> ubicacionesPrincipalesPersona = manejadorUbicacion.consultarUbicacionesPersonaSinAsociacionCaso(
				persona.getIdPersona(), UtilDominios.TIPO_UBICACION_PRINCIPAL);

		if (ubicacionesPrincipalesPersona.size() > 0) {
			ubicaciones.addAll(ubicacionesPrincipalesPersona);
		}

		// Ubicaciones Secundarias
		List<Ubicacion> ubicacionesSecundariasPersona = manejadorUbicacion.consultarUbicacionesPersonaSinAsociacionCaso(
				persona.getIdPersona(), UtilDominios.TIPO_UBICACION_SECUNDARIA);

		if (ubicacionesSecundariasPersona.size() > 0) {
			ubicaciones.addAll(ubicacionesSecundariasPersona);
		}

		// Transformar ubicaciones a DTO
		List<UbicacionDTO> ubicacionesDTO = new ArrayList<>();

		for (Ubicacion ubicacion : ubicaciones) {
			ZonaGeografica ciudad = ubicacion.getZonaGeografica();
			if (ciudad != null && ciudad.getTipoZonaGeografica() != null
					&& UtilConstantes.TIPO_ZONA_GEOGRAFICA_CIUDAD == ciudad.getTipoZonaGeografica()
							.getIdTipoZonaGeografica()
					&& ubicacion.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {

				UbicacionDTO ubicacionDTO = (new UbicacionDTO()).transformarSinDependencias(ubicacion);
				ubicacionDTO.setCiudad(
						(new ZonaGeograficaDTO()).transformarEntidadSinDependencias(ubicacion.getZonaGeografica()));

				if (ciudad.getZonaGeografica() != null) {
					ZonaGeografica dep = ciudad.getZonaGeografica();

					if (dep != null && dep.getZonaGeografica() != null) {
						ubicacionDTO.setPais(
								(new ZonaGeograficaDTO()).transformarEntidadSinDependencias(dep.getZonaGeografica()));
						ubicacionesDTO.add(ubicacionDTO);
					}
				}
			}
		}

		// Setear ubicaciones de la persona a los datos del formulario
		formularioParteDTO.setLstUbicacion(ubicacionesDTO);

		/*
		 * Consultar Telefonos
		 */
		List<TelefonoDTO> telefonosDTO = (List<TelefonoDTO>) (new TelefonoDTO()).transformarColeccionSinDependencias(
				manejadorTelefono.consultarTelefonosPersona(persona.getIdPersona(), true));
		formularioParteDTO.setTelefonos(telefonosDTO);

		/*
		 * Consultar Correos Electronicos solo asociados a la Persona
		 */
		List<CorreoElectronicoDTO> correosDTO = new ArrayList<CorreoElectronicoDTO>();

		// Correos Principales
		List<CorreoElectronico> correosPrincipalesPersona = manejadorCorreo.consultarCorreosPersonaSinAsociacionCaso(
				persona.getIdPersona(), UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);

		if (!correosPrincipalesPersona.isEmpty()) {
			correosDTO.add((new CorreoElectronicoDTO()).transformarSinDependencias(correosPrincipalesPersona.get(0)));
		}

		// Correos Secundarios
		List<CorreoElectronico> correosSecundariosPersona = manejadorCorreo.consultarCorreosPersonaSinAsociacionCaso(
				persona.getIdPersona(), UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO);

		if (!correosSecundariosPersona.isEmpty()) {
			correosDTO.addAll(
					(new CorreoElectronicoDTO()).transformarColeccionSinDependencias(correosSecundariosPersona));
		}

		// Setear correos de la persona a los datos del formulario
		formularioParteDTO.setCorreosElectronicos(correosDTO);

		/*
		 * Idiomas
		 */

		formularioParteDTO.setIdiomas(obtenerDominiosIdiomasPersona(persona.getIdPersona()));

		return formularioParteDTO;
	}

	/**
	 * ADM-C-021 Devuelve los dominios correspondientes a los idiomas activos de la
	 * persona
	 * 
	 * @param idPersona
	 * @return
	 */
	private List<Dominio> obtenerDominiosIdiomasPersona(Long idPersona) {
		List<Idioma> idiomas = manejadorIdioma.consultarIdiomasPersona(idPersona, true);
		List<String> codigosIdiomas = IdiomaDTO.obtenerCodigosDominioIdiomas(idiomas);
		List<Dominio> dominiosIdiomas = dominioFacade.obtenerDominiosPorCodigo(UtilDominios.DOMINIO_IDIOMAS,
				codigosIdiomas);
		return (new DominioDTO()).transformarColeccionEntidadesSinDependencias(dominiosIdiomas);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUsuarioFacade#
	 * obtenerCentrosActor(java.lang.Long)
	 */
	@Override
	public Collection<CentroDTO> obtenerCentrosActor(Long codigoUsuario) {
		List<Centro> centros = manejadorUsuario.obtenerCentrosActor(codigoUsuario);
		return centroFacade.transformarColeccionSinDependencias(centros, new ArrayList<CentroDTO>());
	}

	/**
	 * ADM-C-021 Crea el usuario para la persona que se pasa como parámetro en caso
	 * de que no exista. Devuelve nulo si la persona ya tenía usuario (Independiente
	 * del estado).
	 * 
	 * @param persona
	 * @return
	 */
	private Usuario crearUsuarioPersona(Persona persona , boolean aplicaMauc) {
		Usuario usuario = null;
		if (persona.getUsuarioList() == null || persona.getUsuarioList().isEmpty()) {
			usuario = this.crearUsuarioApersona(persona.getIdPersona());
			if(aplicaMauc) {
				usuario.setAplicaMauc(aplicaMauc);
				manejadorUsuario.actualizar(usuario);
			}
		} else {
			// El usuario existe pero se valida que no haya cambiado el identificador de la
			// persona
			Usuario usuarioBD = persona.getUsuarioList().get(0);
			if (!usuarioBD.getUsuarioLogin().equals(persona.getNumeroDocumento())) {
				// Se elimina el usuario y sus claves almacenados en BD
				List<Clave> claves = usuarioBD.getClaveList();
				List<Clave> clavesNuevas = new ArrayList<>();
				manejadorClave.eliminarListaFisicamente(claves);
				manejadorUsuario.eliminarFisicamente(usuarioBD);
				// Se crea el nuevo usuario con las claves anteriormente almacenadas,
				// actualizando el nombre de usuario
				Usuario usuarioNuevo = new Usuario(persona.getNumeroDocumento(), UtilDominios.ESTADO_USUARIO_ACTIVO,
						persona.getIdPersona());
				usuarioNuevo.setAplicaMauc(aplicaMauc);
				try {
					manejadorUsuario.crear(usuarioNuevo);
					if(aplicaMauc) {
						usuarioNuevo.setAplicaMauc(aplicaMauc);
						manejadorUsuario.actualizar(usuarioNuevo);
					}
				} catch (Exception e) {
					lanzarSIMASCNegocioExcepcion(MensajesEnum.ERROR275.toString());
				}

				for (Clave clave : claves) {
					clavesNuevas.add(new Clave(persona.getNumeroDocumento(), clave.getClavePK().getClave(),
							clave.getClaveBloqueada(), clave.getFechaVencimiento(), clave.getEstadoRegistro()));
				}
				manejadorClave.crearLista(clavesNuevas);
			}
		}

		return usuario;
	}

	/**
	 * Método que consulta el estado de un usuario y el estado de sus claves
	 * 
	 * @param idPersona
	 * @return
	 */
	@Override
	public List<UsuarioClaveDTO> consultarEstadoUsuario(Long idPersona) {
		return manejadorUsuario.consultarEstadoUsuario(idPersona);
	}

	/**
	 * Método que para activar o inactivar una persona
	 * 
	 * @param usuario
	 * @return
	 */
	@Override
	public void cambiarEstadoUsuario(UsuarioClaveDTO usuario) {
		Usuario usuarioEncontrado = manejadorUsuario.buscar(usuario.getUsuarioLogin());
		if (usuarioEncontrado != null) {
			usuarioEncontrado.setEstado(usuario.getEstadoUsuario());
			if (appContext != null && appContext.getContextoSesion() != null
					&& appContext.getContextoSesion().getNombreUsuario() != null) {
				usuarioEncontrado.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
			} else {
				usuarioEncontrado.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			}
			usuarioEncontrado.setFechaUltimaModificacion(new Date());
			usuarioEncontrado.setObservaciones(usuario.getObservaciones());
			manejadorUsuario.actualizar(usuarioEncontrado);
		}
	}

	/**
	 * Método que desbloquea la clave de un usuario
	 * 
	 * @param usuario
	 * @return
	 */
	@Override
	public void desbloquearClaveUsuario(UsuarioClaveDTO usuario) {
		Usuario usuarioEncontrado = manejadorUsuario.buscar(usuario.getUsuarioLogin());
		ClavePK clavePK = new ClavePK();
		if (usuarioEncontrado != null) {
			List<Clave> listaClaves = usuarioEncontrado.getClaveList();
			Predicate<Clave> predicate = new Predicate<Clave>() {
				@Override
				public boolean apply(Clave input) {
					return input.getClaveBloqueada() && input.getFechaVencimiento().after(new Date());
				}

			};
			List<Clave> result = Lists.newArrayList(Collections2.filter(listaClaves, predicate));
			if (!result.isEmpty()) {
				clavePK = result.get(0).getClavePK();
			}
		}

		Clave claveUsuario = manejadorClave.buscar(clavePK);
		if (claveUsuario != null) {
			claveUsuario.setClaveBloqueada(false);
			claveUsuario.setFechaUltimaModificacion(new Date());
			manejadorClave.actualizar(claveUsuario);
		}
	}

	@Override
	public String crearUsuarioParteSinEnvioCorreo(Long idPersona,boolean aplicaMauc) {
		String clave = "";
		Usuario usuarioActual = manejadorUsuario.consultarUsuarioPorPersonaEstado(idPersona, null);
		
		if (usuarioActual == null) {
			usuarioActual = this.crearUsuarioApersona(idPersona);
			if(aplicaMauc) {
				usuarioActual.setAplicaMauc(aplicaMauc);
				manejadorUsuario.actualizar(usuarioActual);
			}
		} else {
			usuarioActual.setEstado(UtilDominios.ESTADO_USUARIO_ACTIVO);
			if(aplicaMauc) {
				usuarioActual.setAplicaMauc(aplicaMauc);
			}
			manejadorUsuario.actualizar(usuarioActual);
		}

		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setUsuarioLogin(usuarioActual.getUsuarioLogin());
		usuarioDto.setIdPersona(idPersona);
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			clave = seguridadFacade.generarClaveSinEnvioCorreo(usuarioDto,
					appContext.getContextoSesion().getNombreUsuario());
		} else {
			clave = seguridadFacade.generarClaveSinEnvioCorreo(usuarioDto, UtilConstantes.USUARIO_DEFECTO_SIMASC);
		}

		return clave;
	}

	@Override
	public void crearUsuarioParteConNotificacion(Caso caso, List<RolPersonaCaso> rolPersonaCasoList) {

		List<String> nombreParametros = new ArrayList<String>();
		nombreParametros.add(UtilConstantes.DEMANDANTES);

		List<ParametroDeServicioDTO> parametroDeServicioRolDemandanteList = parametroDeServicioFacade
				.consultarParametroDeServicio(nombreParametros, caso.getIdServicio(),
						UtilConstantes.TIPO_PARAMETRO_ROL_DEMANDANTES);

		nombreParametros = new ArrayList<String>();
		nombreParametros.add(UtilConstantes.DEMANDADOS);

		List<ParametroDeServicioDTO> parametroDeServicioRolDemandadosList = parametroDeServicioFacade
				.consultarParametroDeServicio(nombreParametros, caso.getIdServicio(),
						UtilConstantes.TIPO_PARAMETRO_ROL_DEMANDADOS);

		nombreParametros = new ArrayList<String>();
		nombreParametros.add(UtilConstantes.PERSONAS_CREACION_USUARIO);

		List<ParametroDeServicioDTO> parametroDeServicioRolPersonasCreacionUsuario = parametroDeServicioFacade
				.consultarParametroDeServicio(nombreParametros, caso.getIdServicio(),
						UtilConstantes.TIPO_PARAMETRO_PERSONAS_CREACION_USUARIO);

		if (parametroDeServicioRolDemandanteList != null && !parametroDeServicioRolDemandanteList.isEmpty()
				&& parametroDeServicioRolDemandadosList != null && !parametroDeServicioRolDemandadosList.isEmpty()
				&& parametroDeServicioRolPersonasCreacionUsuario != null
				&& !parametroDeServicioRolPersonasCreacionUsuario.isEmpty()) {

			List<String> rolesDemandantes = Arrays
					.asList(parametroDeServicioRolDemandanteList.get(0).getValor().split(","));
			List<String> rolesDemandados = Arrays
					.asList(parametroDeServicioRolDemandadosList.get(0).getValor().split(","));
			List<String> rolesPersonasCreacionUsuario = Arrays
					.asList(parametroDeServicioRolPersonasCreacionUsuario.get(0).getValor().split(","));

			ParametrosGenerales parametrosGenerales = parametrosGeneralesFacade
					.consultarPorCodigo(UtilDominios.CODIGO_PARAMETRO_GRAL_URL_ACCESO_SIMASC);

			StringBuilder demandantes = new StringBuilder();
			StringBuilder demandados = new StringBuilder();
			List<Persona> personaList = new ArrayList<Persona>();

			for (RolPersonaCaso rolPersonaCaso : rolPersonaCasoList) {

				Persona persona = null;

				if (rolesDemandantes.contains(rolPersonaCaso.getRol().getIdRol().toString())) {
					demandantes.append(rolPersonaCaso.getPersona().getNombreCompleto() + " ");
					persona = personaFacade.buscar(rolPersonaCaso.getPersona().getIdPersona());
				} else if (rolesDemandados.contains(rolPersonaCaso.getRol().getIdRol().toString())) {
					demandados.append(rolPersonaCaso.getPersona().getNombreCompleto() + " ");
				}

				if (!caso.isMedidasCautelares()
						&& rolesPersonasCreacionUsuario.contains(rolPersonaCaso.getRol().getIdRol().toString())) {
					persona = personaFacade.buscar(rolPersonaCaso.getPersona().getIdPersona());
				}
				if (persona != null) {
					personaList.add(persona);
				}

			}

			nombreParametros = new ArrayList<String>();
			nombreParametros.add(UtilConstantes.NOM_SERVICIO_PLANTILLA_NOTCREUSR);

			List<ParametroDeServicioDTO> parametroDeServicioTextoServicioPlantillaCreacionUsuario = parametroDeServicioFacade
					.consultarParametroDeServicio(nombreParametros, caso.getIdServicio(),
							UtilConstantes.TIPO_PARAMETRO_NOMBRE_SERVICIO_PLANTILLA_NOTCREUSR);

			Map<String, String> filtros = new HashMap<String, String>();
			filtros.put(UtilConstantes.PARAMETRO_DEMANDANTES, demandantes.toString());
			filtros.put(UtilConstantes.PARAMETRO_DEMANDADOS, demandados.toString());
			filtros.put(UtilConstantes.PARAMETRO_ENLACE, parametrosGenerales.getValorTexto());

			if (parametroDeServicioTextoServicioPlantillaCreacionUsuario != null
					&& !parametroDeServicioTextoServicioPlantillaCreacionUsuario.isEmpty()) {
				filtros.put(UtilConstantes.PARAMETRO_NOMBRE_SERVICIO,
						parametroDeServicioTextoServicioPlantillaCreacionUsuario.get(0).getValor());
			}

			List<PlantillaCarta> plantillaCartaList = new ArrayList<PlantillaCarta>();

			for (Persona persona : personaList) {
				if (persona.getNumeroDocumento() != null && !persona.getNumeroDocumento().isEmpty()) {
					List<CorreoElectronico> correoElectronicoList = correoElectronicoFacade.consultaCorreosPersona(
							persona.getIdPersona(), false, UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);

					if (correoElectronicoList != null && !correoElectronicoList.isEmpty()) {
						String claveUsuario = null;
						boolean aplicaMauc = false;
						
						if(this.validarServicioAplicaConMauc(caso.getIdServicio())){
						//if(this.validarServicioAplicaConMauc(idServicio)){
							if(this.validarTipoDocumentoHomologadoConMauc(persona) && !(persona.getTipoFuncionario().equalsIgnoreCase(UtilDominios.PERSONA_FUNCIONARIO_INTERNO))) {
								aplicaMauc	=this.crearUsuarioMauc(persona);
							}
						}
						
						claveUsuario = crearUsuarioParteSinEnvioCorreo(persona.getIdPersona(),aplicaMauc);

						if (claveUsuario != null && !claveUsuario.isEmpty() && !aplicaMauc) {

							plantillaCartaList = plantillaCartaFacade.consultarPlantillaNombre(
									UtilConstantes.NOMBRE_PLANTILLA_NOTIFICACION_GENERACION_CLAVE,
									UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
							filtros.put(UtilConstantes.PARAMETRO_CLAVE, claveUsuario);

						} else {
							plantillaCartaList = plantillaCartaFacade.consultarPlantillaNombre(
									UtilConstantes.NOMBRE_PLANTILLA_NOTIFICACION_SIN_GENERACION_CLAVE,
									UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
						}

						String plantilla = alertaFacade
								.reemplazarTextoAlertas(plantillaCartaList.get(0).getPlantillaHtml(), filtros);

						String asunto = UtilConstantes.ASUNTO_NOTIFICACION_USUARIO;

						asunto = asunto.replace(UtilConstantes.PARAMETRO_DEMANDANTES, demandantes);
						asunto = asunto.replace(UtilConstantes.PARAMETRO_DEMANDADOS, demandados);
						asunto = asunto.replace(UtilConstantes.PARAMETRO_CASO, caso.getIdCaso().toString());
												
						seguridadFacade.enviarCorreo(correoElectronicoList.get(0).getDireccion(),
								persona.getIdPersona(), asunto, plantilla);
					}

				}

			}
		}

	}

	public boolean validarTipoDocumentoHomologadoConMauc(Persona persona) {		
		String codigoExterno = manejadorHomologacionSistemaExterno.consultarCodigosSistemaExterno(
				UtilConstantes.SISTEMA_EXTERNO_MAUC,persona.getTipoDocumento(),
				UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);
		
		if(codigoExterno != null) {
			return true;
		}
		
		return false;
	}
	
	
	public boolean validarServicioAplicaConMauc(Long idServicio) {
		Servicio servicio = manejadorServicio.buscar(idServicio);
		LOG.info("El servicio aplica? :: "+servicio.getAplicaMauc());
		return servicio.getAplicaMauc();
	}
	
	
	
	/**
	 * //Metodo que valida si el usuario aplica o no la bandera Mauc 
		//Si tiene algun rol o servicio Mauc consumir api creacion usuario Mauc	y actualiza la bandera
	 */
	public boolean validarFuncionarioAplicaMauc(Persona persona) {				
		boolean aplica = false;		
		boolean rolAplica = false;
		boolean servicioAplica = false;
		
		if(persona.getTipoFuncionario().equalsIgnoreCase(UtilDominios.PERSONA_FUNCIONARIO_INTERNO)) {				
			aplica = false;
		} else {					
				//Revisar Roles de la persona			
				List<RolPersona> roles = manejadorRolPersona.obtenerRolPesonaPorPersona(persona.getIdPersona());	
				if(roles != null && roles.size() >0) {
					for (RolPersona obj : roles) {
						//Si un rol persona activo tiene bandera Mauc
						if (obj.getRol()!= null && obj.getEstadoRegistro()!= null && obj.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO) && 
								obj.getRol().getAplicaMauc()) {
							rolAplica = true;						
							break;
						}
					}
				}		
				
				if(!rolAplica) {
				//Revisar Persona Roles, para consultar si el Caso esta asociado a un Servicio aplica Mauc
					List<RolPersonaCaso> rolPersonaCasoList = manejadorRolPersonaCaso.consultaVinculacionPersonaCaso(persona.getTipoDocumento(),
							persona.getNumeroDocumento(), null);
					if (rolPersonaCasoList != null && rolPersonaCasoList.size() > 0) {
						for (RolPersonaCaso rolPersonaCaso : rolPersonaCasoList) {
							Caso casoRPC = rolPersonaCaso.getCaso();
							if(casoRPC!=null && casoRPC.getEstadoCaso()!= null && !casoRPC.getEstadoCaso().equals(UtilDominios.ESTADO_CASO_CERRADO) && 
									casoRPC.getServicioMateria()!=null && casoRPC.getServicioMateria().getServicio()!=null  && 
									casoRPC.getServicioMateria().getServicio().getAplicaMauc()){
								LOG.info("Servicio con bandera mauc");
								servicioAplica = true; 
								break;
							}	
						} 
					} 
				}
		
				if(rolAplica || servicioAplica) {				
				 //Consumir api creacion Mauc					
				 aplica = this.crearUsuarioMauc(persona); 
				}
			}
		this.cambiarBanderaMaucUsuario(persona,aplica);	
		return aplica;	
	}
	
	
	/**
	 * Método que actualiza la bandera aplica mauc de un usuario
	 * @param persona, bandera
	 * @return
	 */
	public void cambiarBanderaMaucUsuario(Persona persona, boolean bandera) {
		Usuario usuarioEncontrado = manejadorUsuario.buscar(persona.getNumeroDocumento());
		if (usuarioEncontrado != null) {			
			if (appContext != null && appContext.getContextoSesion() != null
					&& appContext.getContextoSesion().getNombreUsuario() != null) {
				usuarioEncontrado.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
			} else {
				usuarioEncontrado.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			}
			usuarioEncontrado.setAplicaMauc(bandera);
			manejadorUsuario.actualizar(usuarioEncontrado);
		}
	}
	
	/**
	 * Método de consumo Api creación/consulta usuarios Mauc
	 */
	public boolean crearUsuarioMauc(Persona persona) {
		String tipoDocumentoHomologado = manejadorHomologacionSistemaExterno.
		consultarCodigosSistemaExterno(UtilConstantes.SISTEMA_EXTERNO_MAUC,persona.getTipoDocumento(), UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);
	
		if(UtilSimasc.validarUsuarioCrearEnMauc(persona) && 
				this.validarTipoDocumentoHomologadoConMauc(persona)) {
			
		try {
		
			ParametrosGenerales urlServCase = parametrosGeneralesFacade
					.consultarPorNombre(UtilConstantes.URL_CREAR_USUARIO_MAUC);
			
			ParametrosGenerales idPlataforma = parametrosGeneralesFacade
					.consultarPorNombre(UtilConstantes.CODIGO_PLATAFORMA_MAUC);

			String url = urlServCase.getValorTexto();
			String celular = persona.getTelefonoList().get(0).getNumero();
			//Validar si existe un celular en la lista de telefonos y usar este dato para la api de mauc
			for(Telefono tel: persona.getTelefonoList()) {				
	            if(tel.getTipoTelefono()!= null && tel.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_CELULAR)) {
	            	celular = tel.getNumero();
	            	//Se remplazan todos los caracteres, solo se aceptan digitos, si la longitud es mayor a 10 se toman los ultimos 10 digitos
	            	celular = celular.replaceAll("[^\\d.]", "");
	            	if(celular.length() > 10) {
	            		 celular = celular.substring(celular.length()-10, celular.length()- 1);
	            	}
	               	break; 
	               	 
	            }	                	                
			}	
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json; charset=UTF-8");
			

			tipoDocumentoHomologado = manejadorHomologacionSistemaExterno.
					consultarCodigosSistemaExterno(UtilConstantes.SISTEMA_EXTERNO_MAUC,
							persona.getTipoDocumento(), UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);
			String json = "{\"consecutivo\":\"" + 1 + "\",\"tipoDocumento\":\""+tipoDocumentoHomologado+""
				+ "\",\"documento\":\""+persona.getNumeroDocumento()+"\",\"nombre1\":\""+persona.getPrimerNombreORazonSocial()+""
				+"\",\"nombre2\":\""+persona.getSegundoNombre()+"\",\"apellido1\":\""+persona.getPrimerApellido()+""
				+"\",\"apellido2\":\""+persona.getSegundoApellido()+"\",\"correo\":\""+persona.getCorreoElectronicoList().get(0).getDireccion()+""
				+"\",\"celular\":\""+celular+"\",\"nivel\":\""+1+""
				+"\",\"idplataforma\":\""+idPlataforma.getValorTexto()+""
				+"\"}";
			LOG.info(json);
			StringEntity params = new StringEntity(json ,"UTF-8");
			params.setContentType("application/json; charset=UTF-8");
					
			httpPost.setEntity(params);
			
			httpPost.getAllHeaders();
			HttpResponse response = httpClient.execute(httpPost);
			String resul = new BasicResponseHandler().handleResponse(response);
			
			LOG.info("STATUS MAUC :: "+ response.getStatusLine().getStatusCode());
			
			if (response.getStatusLine().getStatusCode() == 201) {
				LOG.info(resul);
				return true;
			}else {
				//Si el usuario ya existe en Mauc la bandera debe activarse
				if(resul!= null && resul.contains(UtilConstantes.RES_MAUC_USUARIO_EXISTE)) {
					LOG.info(resul);
					return true;
				}else {					
					LOG.info(resul);
					return false;
				}
			}
			
			
		} catch (Exception e) {
			LOG.info(e.toString());
		}
			
		}
		
		return false;

	
	}
	
	
		
	// protected region metodos adicionales end
	public Usuario crearUsuario(Long idPersona ) {
		Persona persona = manejadorPersona.buscar(idPersona);
		if (persona == null || persona.getNumeroDocumento() == null) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR526.toString()));
		}
		Usuario usuario = new Usuario();
		usuario.setEstado(UtilDominios.ESTADO_USUARIO_ACTIVO);
		usuario.setIdPersona(idPersona);
		usuario.setUsuarioLogin(persona.getNumeroDocumento());
		manejadorUsuario.crear(usuario);
		return usuario;

	}
	


	

}
