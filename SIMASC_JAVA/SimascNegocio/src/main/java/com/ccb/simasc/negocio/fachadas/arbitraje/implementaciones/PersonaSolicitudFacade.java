package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorApoderados;
import com.ccb.simasc.integracion.manejadores.ManejadorApoderadosSolicitud;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronicoPersonaSolicitud;
import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaSolicitud;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorSolicitudServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefono;
import com.ccb.simasc.integracion.manejadores.ManejadorUbicacion;
import com.ccb.simasc.integracion.manejadores.ManejadorZonaGeografica;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgrupamientoRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaSolicitudFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITelefonoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IZonaGeograficaFacade;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoEnmascaradoDTO;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.DominioEnmascaradoDTO;
import com.ccb.simasc.transversal.dto.DominioPKDTO;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.PersonaSolicitudDTO;
import com.ccb.simasc.transversal.dto.TelefonoDTO;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteEnmascaradoDTO;
import com.ccb.simasc.transversal.entidades.Apoderados;
import com.ccb.simasc.transversal.entidades.ApoderadosPK;
import com.ccb.simasc.transversal.entidades.ApoderadosSolicitud;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoPersonaSolicitud;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.PersonaSolicitudPK;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.entidades.SolicitudServicio;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.entidades.UbicacionPersonaSolicitud;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilMascaraTexto;



// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link PersonaSolicitud}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PersonaSolicitudFacade extends AccesoFacade<PersonaSolicitud, PersonaSolicitudPK, PersonaSolicitudDTO>
		implements IPersonaSolicitudFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	private static final Logger LOGGER = LogManager.getLogger(PersonaSolicitudFacade.class.getName());

	@EJB
	private ManejadorPersonaSolicitud manejadorPersonaSolicitud;

	@EJB
	private ManejadorSolicitudServicio manejadorSolicitudServicio;
	
	@EJB
	private ManejadorApoderadosSolicitud manejadorApoderadosSolicitud;

	@EJB
	private ManejadorUbicacion manejadorUbicacion;

	@EJB
	private ManejadorTelefono manejadorTelefono;

	@EJB
	private ManejadorPersona manejadorPersona;
	
	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorZonaGeografica manejadorZonaGeografica;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private ManejadorServicio manejadorServicio;
	
	@EJB
	private ManejadorCorreoElectronico manejadorCorreoElectronico;
	
	@EJB
	private ManejadorDominio manejadorDominio;

	@EJB
	private IAgrupamientoRolFacade agrupamientoRolFacade;

	@EJB
	private IDominioFacade dominioFacade;

	@EJB
	private IZonaGeograficaFacade zonaGeograficaFacade;

	@EJB
	private ITelefonoFacade telefonoFacade;

	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;
	
	@EJB
	private ManejadorApoderados manejadorApoderados;
	
	@EJB
	private ICorreoElectronicoRolPersonaCasoFacade correoElectronicoRolPersonaCasoFacade;
	
	@EJB
	private IUbicacionRolPersonaCasoFacade ubicacionRolPersonaCasoFacade;

	@EJB
	private ManejadorCorreoElectronicoPersonaSolicitud manejadorCorreoElectronicoPersonaSolicitud;
	
	@EJB
	private PersonaFacade personaFacade;
	
	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPersonaSolicitud;
	}

	@Override
	public PersonaSolicitudDTO transformarSinDependencias(PersonaSolicitud obj) {
		PersonaSolicitudDTO dto = new PersonaSolicitudDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public PersonaSolicitudDTO transformarConDependencias(PersonaSolicitud obj) {
		PersonaSolicitudDTO dto = new PersonaSolicitudDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public PersonaSolicitud transformarEntidadConDependencias(PersonaSolicitud obj) {
		return new PersonaSolicitudDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public PersonaSolicitud transformarEntidadSinDependencias(PersonaSolicitud obj) {
		return new PersonaSolicitudDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IPersonaSolicitudFacade#consultarPartesSolicitudServicio(java.lang.Long)
	 */
	@Override
	public List<FormularioParteDTO> consultarPartesSolicitudServicio(Long idSolicitudServicio)
			throws SIMASCNegocioExcepcion {
	
		SolicitudServicio solicitudServicio = manejadorSolicitudServicio.buscar(idSolicitudServicio);
		String rolParte = UtilDominios.DOMINIO_ROL_PERSONA_PARTE_CON;
		if(UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA.equals(solicitudServicio.getServicioMateria().getServicio().getTipo())){
			rolParte = UtilDominios.DOMINIO_ROL_PERSONA_PARTE;
		}
		if(UtilDominios.TIPO_SERVICIO_CONVIVENCIA.equals(solicitudServicio.getServicioMateria().getServicio().getTipo())){
			rolParte = UtilDominios.DOMINIO_ROL_PERSONA_PARTE_EQUIDAD;
		}
		
		List<String> roles = manejadorDominio.consultarCodigosDominio(rolParte);
				
		List<PersonaSolicitud> personasSolicitud = manejadorPersonaSolicitud
				.consultarPersonasSolicitud(idSolicitudServicio, roles, false);
		List<FormularioParteDTO> listaFormularioPartes = new ArrayList<>();

		for (PersonaSolicitud personaSolicitud : personasSolicitud) {
			FormularioParteDTO formularioParteDTO = new FormularioParteDTO();
			Persona persona = personaSolicitud.getPersona();
			formularioParteDTO.setIdPersona(persona.getIdPersona());
			formularioParteDTO.setPrimerNombre(persona.getPrimerNombreORazonSocial());
			formularioParteDTO.setSegundoNombre(persona.getSegundoNombre());
			formularioParteDTO.setPrimerApellidoORazonSocial(persona.getPrimerApellido());
			formularioParteDTO.setSegundoApellido(persona.getSegundoApellido());
			formularioParteDTO.setNombreCompleto(persona.getNombreCompleto());
			formularioParteDTO.setNumeroIdentificacion(persona.getNumeroDocumento());
			formularioParteDTO.setIdSolicitudServicio(idSolicitudServicio);

			Dominio dominioTipoPersona = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_PERSONA,
					persona.getTipoPersona());
			formularioParteDTO
					.setTipoPersona(new DominioDTO().transformarEntidadSinDependencias(dominioTipoPersona));
			Dominio dominioTipoDocumento = dominioFacade
					.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA, persona.getTipoDocumento());
			formularioParteDTO.setTipoIdentificacion(
					new DominioDTO().transformarEntidadSinDependencias(dominioTipoDocumento));
			
			Dominio dominioRol = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ROL_PERSONA,
					personaSolicitud.getRol().getNombre());
			formularioParteDTO.setRol(dominioRol);
			
			ZonaGeografica ciudad = manejadorZonaGeografica.buscar(persona.getCiudadDelDocumento());
			if (ciudad != null)
				formularioParteDTO.setCiudadIdentificacionTexto(ciudad.getNombre());
			ZonaGeografica pais = manejadorZonaGeografica.buscar(persona.getIdPaisOrigen());
			if (pais != null)
				formularioParteDTO.setNacionalidad(pais.getNombre());
			
			if (UtilDominios.TIPO_PERSONA_NATURAL.equals(persona.getTipoPersona())) {
				formularioParteDTO.setFechaNacimiento(personaSolicitud.getPersona().getFechaDeNacimiento());
				formularioParteDTO.setFechaGrado(personaSolicitud.getPersona().getFechaDeGrado());
				formularioParteDTO.setNumeroTarjetaProfesional(personaSolicitud.getPersona().getNumeroTarjetaProfesional());			
				formularioParteDTO.setProfesion(personaSolicitud.getPersona().getProfesion());
				
				Dominio dominioSexo = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SEXOS, persona.getSexo());
				formularioParteDTO.setTipoSexo(dominioSexo);
				
				Dominio dominioEntidadTarjetaProfesional = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ENTIDAD_TARJETA_PROFESIONAL, persona.getSexo());
				formularioParteDTO.setEntidadTarjetaProfesional(dominioEntidadTarjetaProfesional);
				
				Dominio dominioInstitucion = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_INSTITUCIONES_EDUCATIVAS, persona.getInstitucionEducativa());
				formularioParteDTO.setInstitucionEducativa(dominioInstitucion);
				
				Dominio dominioEstrato = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESTRATOS, persona.getEstrato());
				formularioParteDTO.setEstrato(dominioEstrato);
				
				Dominio dominioEscolaridad = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESCOLARIDADES, persona.getEscolaridad());
				formularioParteDTO.setEscolaridad(dominioEscolaridad);
			} else {
				formularioParteDTO.setRepresentanteLegal(persona.getRepresentanteLegal());
				formularioParteDTO.setPaginaWeb(persona.getPaginaWeb());
				
				Dominio dominioSector = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SECTOR_EMPRESA, persona.getSectorDeLaEmpresa());
				formularioParteDTO.setSectorEmpresa(dominioSector);				
			}
			
			UbicacionDTO ubicacionPrincipal = manejadorUbicacion.consultarPrimeraUbicacionPersona(persona.getIdPersona());
			if (ubicacionPrincipal.getIdUbicacion() != null) {
				ZonaGeografica ciudadDireccion = manejadorZonaGeografica.buscar(ubicacionPrincipal.getIdZonaGeografica());
				ubicacionPrincipal.setCiudad(ciudadDireccion);
				ZonaGeografica paisDireccion = ciudadDireccion.getZonaGeografica().getZonaGeografica();
				ubicacionPrincipal.setPais(paisDireccion);
				formularioParteDTO.setLstUbicacion(Arrays.asList(ubicacionPrincipal));
			}
			
			List<Telefono> telefonos =  manejadorTelefono.consultarPorTipoYPersona(Arrays.asList(UtilDominios.TIPO_TELEFONO_FIJO), persona.getIdPersona());
			
			if(!telefonos.isEmpty()) {
				TelefonoDTO telefono = new TelefonoDTO().transformarSinDependencias(telefonos.get(0)); 
				formularioParteDTO.setTelefonos(Arrays.asList(telefono));
			}
			
			CorreoElectronico correoPrincipal = manejadorCorreoElectronico.consultarPorTipoCorreoIdPersona(
					persona.getIdPersona(), UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
			
			if (correoPrincipal != null) {
				formularioParteDTO.setCorreosElectronicos(Arrays.asList(new CorreoElectronicoDTO().transformarSinDependencias(correoPrincipal)));				
			}

			listaFormularioPartes.add(formularioParteDTO);
		}

		return listaFormularioPartes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IPersonaSolicitudFacade#eliminarParteSolicitudServicio(java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public void eliminarParteSolicitudServicio(Long idSolicitudServicio, Long idPersona) throws SIMASCNegocioExcepcion {
		personaFacade.eliminarParteSolicitudServicio(idSolicitudServicio, idPersona);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IPersonaSolicitudFacade#consultarParteSolicitudServicio(java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public FormularioParteDTO consultarParteSolicitudServicio(Long idSolicitudServicio, Long idPersona)
			throws SIMASCNegocioExcepcion {

		FormularioParteDTO formularioParteDTO = new FormularioParteDTO();

		try {
			List<PersonaSolicitud> personaListaAnterior = manejadorPersonaSolicitud
					.consultarPersonaSolicitud(idSolicitudServicio, idPersona, null);

			PersonaSolicitud personaSolicitud = null;
			for (PersonaSolicitud personaSolicitudL : personaListaAnterior) {
				if (!UtilDominios.ROL_PERSONA_SOLICITANTE.equals(personaSolicitudL.getRol().getNombre()))
					personaSolicitud = personaSolicitudL;
			}
			
			Persona persona = personaSolicitud.getPersona();

			formularioParteDTO.setIdSolicitudServicio(idSolicitudServicio);
			Dominio dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_PERSONA, persona.getTipoPersona());
			formularioParteDTO.setTipoPersona(dominio);

			Dominio dominioRol = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ROL_PERSONA,
					personaSolicitud.getRol().getNombre());
			formularioParteDTO.setRol(dominioRol);

			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA,
					persona.getTipoDocumento());
			formularioParteDTO.setTipoIdentificacion(dominio);
			formularioParteDTO.setNumeroIdentificacion(persona.getNumeroDocumento());

			// Ubicación de la parte
			asignarUbicacionParte(persona, formularioParteDTO);

			formularioParteDTO.setNacionalidad(persona.getIdPaisOrigen());
			formularioParteDTO.setPrimerNombre(persona.getPrimerNombreORazonSocial());
			formularioParteDTO.setSegundoNombre(persona.getSegundoNombre());
			formularioParteDTO.setPrimerApellidoORazonSocial(persona.getPrimerApellido());
			formularioParteDTO.setSegundoApellido(persona.getSegundoApellido());
			formularioParteDTO.setIdPersona(persona.getIdPersona());

			// Teléfonos y correos electrónicos de las partes
			asignarTelefonosYCorreosParte(persona, formularioParteDTO);

			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SEXOS, persona.getSexo());
			formularioParteDTO.setTipoSexo(dominio);
			formularioParteDTO.setFechaNacimiento(persona.getFechaDeNacimiento());
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESTRATOS, persona.getEstrato());
			formularioParteDTO.setEstrato(dominio);
			formularioParteDTO.setProfesion(persona.getProfesion());

			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESCOLARIDADES, persona.getEscolaridad());
			formularioParteDTO.setEscolaridad(dominio);
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_INSTITUCIONES_EDUCATIVAS,
					persona.getInstitucionEducativa());
			formularioParteDTO.setInstitucionEducativa(dominio);
			formularioParteDTO.setFechaGrado(persona.getFechaDeGrado());
			formularioParteDTO.setNumeroTarjetaProfesional(persona.getNumeroTarjetaProfesional());
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ENTIDAD_TARJETA_PROFESIONAL,
					persona.getEntidadExpideTarjetaProfesional());
			formularioParteDTO.setEntidadTarjetaProfesional(dominio);
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_EMPRESA, persona.getTipoDeEmpresa());
			formularioParteDTO.setTipoEmpresa(dominio);
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_ENTIDAD_PUBLICA,
					persona.getTipoDeEntidadPublica());
			formularioParteDTO.setTipoEntidadPublica(dominio);
			formularioParteDTO.setRepresentanteLegal(persona.getRepresentanteLegal());
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SECTOR_EMPRESA,
					persona.getSectorDeLaEmpresa());
			formularioParteDTO.setSectorEmpresa(dominio);
			formularioParteDTO.setPaginaWeb(persona.getPaginaWeb());
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_CONSULTA_PREVIA,
					persona.getTieneEmpleo());
			formularioParteDTO.setTieneEmpleo(dominio);
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_DE_EMPLEO,
					persona.getTipoEmpleo());
			formularioParteDTO.setTipoEmpleo(dominio);
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SECTOR_EMPRESA,
					persona.getSectorEconomico());
			formularioParteDTO.setSectorEconomico(dominio);
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESTADOS_CIVILES,
					persona.getEstadoCivil());
			formularioParteDTO.setEstadoCivil(dominio);
			
			if(persona.getNumeroPersonasAcargo() != null) {
				formularioParteDTO.setNumeroPersonasAcargo(Integer.parseInt(persona.getNumeroPersonasAcargo()));
			}
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_CONSULTA_PREVIA,
					persona.getTieneSociedadConyugal());
			formularioParteDTO.setTieneSociedadConyugal(dominio);
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_CONSULTA_PREVIA,
					persona.getSociedadConyugalVigente());
			formularioParteDTO.setSociedadConyugalVigente(dominio);
			
			
			// Obtiene las partes representadas de un apoderado en caso de que
			// la persona tenga el último rol dentro de la solicitud de servicio
			if (dominioRol != null) {
				obtenerRepresentadosParteSolicitudServicio(formularioParteDTO, idSolicitudServicio, personaSolicitud.getRol().getIdRol());
			}
			
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e);
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		return formularioParteDTO;
	}

	/**
	 * Método encargado de asignar la ubicación de la persona
	 * 
	 * @param persona
	 * @param formularioParteDTO
	 */
	private void asignarUbicacionParte(Persona persona, FormularioParteDTO formularioParteDTO) {
		ZonaGeografica zonaGeografica = new ZonaGeografica();
		if (persona.getCiudadDelDocumento() != null)
			zonaGeografica = zonaGeograficaFacade.buscar(persona.getCiudadDelDocumento());
		if (zonaGeografica != null)
			formularioParteDTO
					.setCiudadIdentificacion(zonaGeograficaFacade.transformarEntidadSinDependencias(zonaGeografica));

		List<UbicacionDTO> listaUbicacionDTO = new ArrayList<>();
		for (Ubicacion ubicacion : persona.getUbicacionList()) {
			UbicacionDTO ubicacionDTO = new UbicacionDTO();
			ubicacionDTO.setIdUbicacion(ubicacion.getIdUbicacion());
			ubicacionDTO.setDireccion(ubicacion.getDireccion());
			ubicacionDTO.setIdZonaGeografica(ubicacion.getIdZonaGeografica());
			ZonaGeografica ciudad = manejadorZonaGeografica.buscar(ubicacion.getIdZonaGeografica());
			ZonaGeografica pais = manejadorZonaGeografica.buscar(ciudad.getIdZonaGeograficaPadre());
			ubicacionDTO.setCiudad(ciudad);
			ubicacionDTO.setPais(pais);
			listaUbicacionDTO.add(ubicacionDTO);
		}

		formularioParteDTO.setLstUbicacion(listaUbicacionDTO);
	}
	
	/**
	 * Metodo encargado de asignar la ubicacion de la persona
	 * 
	 * @param persona
	 * @param formularioParteDTO
	 */
	private void asignarUbicacionEnmascaradaParte(Persona persona, FormularioParteEnmascaradoDTO formularioParteEnmascaradoDTO) {
		ZonaGeografica zonaGeografica = new ZonaGeografica();
		if (persona.getCiudadDelDocumento() != null)
			zonaGeografica = zonaGeograficaFacade.buscar(persona.getCiudadDelDocumento());
		if (zonaGeografica != null)
			formularioParteEnmascaradoDTO
					.setCiudadIdentificacion(zonaGeograficaFacade.transformarEntidadSinDependencias(zonaGeografica));

		List<UbicacionDTO> listaUbicacionDTO = new ArrayList<>();
		for (Ubicacion ubicacion : persona.getUbicacionList()) {
			UbicacionDTO ubicacionDTO = new UbicacionDTO();
			ubicacionDTO.setIdUbicacion(ubicacion.getIdUbicacion());
			if(ubicacion.getDireccion() != null) {
				ubicacionDTO.setDireccionEnmascarada(UtilMascaraTexto.replaceCharacterByDot(ubicacion.getDireccion()));
			}			
			ubicacionDTO.setIdZonaGeografica(ubicacion.getIdZonaGeografica());
			ZonaGeografica ciudad = manejadorZonaGeografica.buscar(ubicacion.getIdZonaGeografica());
			ZonaGeografica pais = manejadorZonaGeografica.buscar(ciudad.getIdZonaGeograficaPadre());
			ubicacionDTO.setCiudad(ciudad);
			ubicacionDTO.setPais(pais);
			listaUbicacionDTO.add(ubicacionDTO);
		}

		formularioParteEnmascaradoDTO.setLstUbicacion(listaUbicacionDTO);
	}

	/**
	 * Método encargado de asignar los teléfonos y correos de la persona
	 * 
	 * @param persona
	 * @param formularioParteDTO
	 */
	private void asignarTelefonosYCorreosParte(Persona persona, FormularioParteDTO formularioParteDTO) {
		int i = 0;

		for (Telefono telefono : telefonoFacade.transformarEntidadesColeccionSinDependencias(persona.getTelefonoList(),
				new ArrayList<Telefono>())) {
			if (telefono.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_CELULAR)) {
				formularioParteDTO.setNumeroCelular(telefono.getNumero());
			}
			if (i > 0 && telefono.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
				formularioParteDTO.setNumeroTelefonoDos(telefono.getNumero());
				i++;
			}
			if (i == 0 && telefono.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO)) {
				formularioParteDTO.setNumeroTelefonoUno(telefono.getNumero());
				i++;
			}
		}

		for (CorreoElectronico correoElectronico : correoElectronicoFacade.transformarEntidadesColeccionSinDependencias(
				persona.getCorreoElectronicoList(), new ArrayList<CorreoElectronico>())){
			if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
					&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL.equals(correoElectronico.getTipo())) {
				formularioParteDTO.setEmailUno(correoElectronico.getDireccion());
				break;
			}
		}
		
		PersonaSolicitudPK personaSolicitudPk = new PersonaSolicitudPK();
		personaSolicitudPk.setIdPersona(persona.getIdPersona());
		personaSolicitudPk.setIdRol(persona.getPersonaSolicitudList().get(0).getRol().getIdRol());
		personaSolicitudPk.setIdSolicitudServicio(formularioParteDTO.getIdSolicitudServicio());
		
		List<CorreoElectronico> correoElectronicoList = manejadorCorreoElectronicoPersonaSolicitud.consultarCorreosPorPersonaSolicitud(personaSolicitudPk);

		for (CorreoElectronico correoElectronico : correoElectronicoList ) {	

			if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
					&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO.equals(correoElectronico.getTipo())) {
				formularioParteDTO.setEmailDos(correoElectronico.getDireccion());
			}
			
			if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
					&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_TERCIARIO.equals(correoElectronico.getTipo())) {
				formularioParteDTO.setEmailTres(correoElectronico.getDireccion());
			}
			
			if (formularioParteDTO.getEmailUno() != null && formularioParteDTO.getEmailDos() != null
					&& formularioParteDTO.getEmailTres() != null) {
				break;
			}

		}
	}
	
	
	
	
	/**
	 * Genera el rol persona caso a partir de una lista de persona solicitud
	 * @param personasSolicitud
	 * @param idUsuario
	 * @param idCaso
	 */
	@Override
	public void migracionPersonaSolicitudARolPersonaCaso(List<PersonaSolicitud> personasSolicitud, String idUsuario,
			Long idCaso) throws SIMASCNegocioExcepcion {

		Collections.sort(personasSolicitud, new Comparator<PersonaSolicitud>() {
			@Override
			public int compare(PersonaSolicitud o1, PersonaSolicitud o2) {
				if (o1.getIdPersonaApoderado() == null) {
					return (o2.getIdPersonaApoderado() == null) ? 0 : -1;
				}
				if (o2.getIdPersonaApoderado() == null) {
					return 1;
				}
				return o2.getIdPersonaApoderado().compareTo(o1.getIdPersonaApoderado());
			}
		});
		
		List<RolPersonaCaso> rolPersonaCasoList = new ArrayList<>();

		for (PersonaSolicitud personaSolicitud : personasSolicitud) {
			RolPersonaCaso rolPersonaCreado = new RolPersonaCaso();
			rolPersonaCreado.setPersona(new Persona());
			rolPersonaCreado.setRol(new Rol());
			rolPersonaCreado.setCaso(new Caso());
			rolPersonaCreado.getPersona().setIdPersona(personaSolicitud.getPersonaSolicitudPK().getIdPersona());
			rolPersonaCreado.getCaso().setIdCaso(idCaso);
			rolPersonaCreado.getRol().setIdRol(personaSolicitud.getPersonaSolicitudPK().getIdRol());
			rolPersonaCreado.setEstado(UtilDominios.ESTADO_ROL_PERSONA_CASO_NO_APLICA);
			rolPersonaCreado.setEsPresidente(false);
			rolPersonaCreado.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			rolPersonaCreado.setFechaUltimaModificacion(new Date());
			rolPersonaCreado.setIdUsuarioModificacion(idUsuario);
			
			// apoderados
			if (personaSolicitud.getIdSolicitudServicioApoderado() != null) {
				rolPersonaCreado.setIdCasoApoderado(idCaso);
			}
			
			rolPersonaCreado.setIdPersonaApoderado(personaSolicitud.getIdPersonaApoderado());
			rolPersonaCreado.setIdRolApoderado(personaSolicitud.getIdRolApoderado());

			manejadorRolPersonaCaso.crear(rolPersonaCreado);
			
			// Migración Correos Electronicos Persona Solicitud a Rol Persona Caso
			if (personaSolicitud.getCorreoElectronicoPersonaSolicitudList() != null) {
				for(CorreoElectronicoPersonaSolicitud correoPersonaSolicitud : personaSolicitud.getCorreoElectronicoPersonaSolicitudList()) {
					correoElectronicoRolPersonaCasoFacade.crearCorreoElectronicoRolPersonaCaso(rolPersonaCreado, correoPersonaSolicitud.getCorreoElectronico());
				}				
			}
			
			// Migración Ubicaciones Persona Solicitud a Rol Persona Caso
			if (personaSolicitud.getUbicacionPersonaSolicitudList() != null) {
				for(UbicacionPersonaSolicitud ubicacionPersonaSolicitud : personaSolicitud.getUbicacionPersonaSolicitudList()) {
					ubicacionRolPersonaCasoFacade.crearUbicacionRolPersonaCaso(rolPersonaCreado, ubicacionPersonaSolicitud.getUbicacion());
				}
			}	
		}

		crearApoderadosCaso(rolPersonaCasoList, idCaso, idUsuario);
	}
	
	/**
	 * Inserta la información de los representados
	 * 
	 * @param rolPersonaCasoList
	 * @param idCaso
	 * @param idUsuario
	 */
	
	private void crearApoderadosCaso(List<RolPersonaCaso> rolPersonaCasoList, Long idCaso, String idUsuario) {

		for (RolPersonaCaso rolPersonaCaso : rolPersonaCasoList) {
						
			if (rolPersonaCaso.getIdPersonaApoderado() != null) {
				
				RolPersonaCaso apoderado = manejadorRolPersonaCaso.consultaRolPersonaId(rolPersonaCaso.getIdPersonaApoderado(), idCaso, UtilDominios.ESTADO_REGISTRO_ACTIVO);
				Long idRolApoderado = apoderado.getRolPersonaCasoPK().getIdRol();
				Long idPersonaApoderado = apoderado.getRolPersonaCasoPK().getIdPersona();
				Long idCasoApoderado = apoderado.getRolPersonaCasoPK().getIdCaso();
				Long idRolRepresentado = rolPersonaCaso.getRolPersonaCasoPK().getIdRol();
				Long idPersonaRepresentado = rolPersonaCaso.getRolPersonaCasoPK().getIdPersona();
				Long idCasoRepresentado = rolPersonaCaso.getRolPersonaCasoPK().getIdCaso();
				
				Apoderados apoderadosEntity = new Apoderados();
				ApoderadosPK apoderadosKey = new ApoderadosPK(idRolApoderado, idPersonaApoderado, idCasoApoderado, 
						idRolRepresentado, idPersonaRepresentado, idCasoRepresentado);
				apoderadosEntity.setApoderadosPK(apoderadosKey);
				apoderadosEntity.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				apoderadosEntity.setFechaUltimaModificacion(new Date());
				apoderadosEntity.setIdUsuarioModificacion(idUsuario);
				manejadorApoderados.crear(apoderadosEntity);
								
			}
				
			
		}
		
	}

	/**
	 * Obtiene las partes representadas de un apoderado en una soplicitud de
	 * servicio
	 * 
	 * @param formularioParteDTO
	 * @param idRol 
	 * @param idSolicitudServicio 
	 */
	@SuppressWarnings("unchecked")
	private void obtenerRepresentadosParteSolicitudServicio(FormularioParteDTO formularioParteDTO, Long idSolicitudServicio, Long idRol) {
		// Obtiene la solicitud de servicio para saber el tipo de servicio
		SolicitudServicio solicitudServicio = manejadorSolicitudServicio
				.buscar(formularioParteDTO.getIdSolicitudServicio());
		Servicio servicio = manejadorServicio.buscar(solicitudServicio.getIdServicio());
		List<Persona> personasRepresentadas = new ArrayList<Persona>();

		// Obtiene las partes dependiendo del tipo de servicio de la solicitud
		switch (servicio.getTipo()) {
		case UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA:
			
			List<ApoderadosSolicitud> representadosList = manejadorApoderadosSolicitud.obtenerRepresentadosPorApoderado(idRol, formularioParteDTO.getIdPersona(),
					idSolicitudServicio);
			
			for (ApoderadosSolicitud representado : representadosList) {
				Persona personaRepresentada = manejadorPersona.buscar(representado.getApoderadosSolicitudPK().getIdPersonaRepresentado());
				personasRepresentadas.add(personaRepresentada);
			}

			break;

		case UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS:
			
			// éste método no se modificó ya que es de conciliación.
			personasRepresentadas = manejadorPersonaSolicitud.consultarPartesSolicitudPorRolApoderado(
					formularioParteDTO.getIdSolicitudServicio(),
					formularioParteDTO.getRol().getDominioPK().getCodigo()
							.equals(UtilDominios.ROL_PERSONA_APODERADO_CONVOCADO) ? UtilDominios.ROL_PERSONA_CONVOCADO
									: UtilDominios.ROL_PERSONA_CONVOCANTE,
					formularioParteDTO.getIdPersona());

			break;

		}

		// Adiciona la lista de representados a la persona apoderada
		formularioParteDTO.setRepresentada(
				(List<Persona>) new PersonaDTO().transformarColeccionEntidadesSinDependencias(personasRepresentadas));

	}

	@Override
	public List<FormularioParteEnmascaradoDTO> consultarPartesEnmascarandoSolicitudServicio(Long idSolicitudServicio)
			throws SIMASCNegocioExcepcion {
		SolicitudServicio solicitudServicio = manejadorSolicitudServicio.buscar(idSolicitudServicio);
		List<String> roles = manejadorDominio.consultarCodigosDominio(UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA
				.equals(solicitudServicio.getServicioMateria().getServicio().getTipo())
				? UtilDominios.DOMINIO_ROL_PERSONA_PARTE : UtilDominios.DOMINIO_ROL_PERSONA_PARTE_CON);
		List<PersonaSolicitud> personasSolicitud = manejadorPersonaSolicitud
				.consultarPersonasSolicitud(idSolicitudServicio, roles, false);
		List<FormularioParteEnmascaradoDTO> listaFormularioPartesEnmascarado = new ArrayList<>();		

		for (PersonaSolicitud personaSolicitud : personasSolicitud) {
			FormularioParteEnmascaradoDTO formularioParteEnmascaradoDTO = new FormularioParteEnmascaradoDTO();
			DominioEnmascaradoDTO dominioEnmascaradoDTO = new DominioEnmascaradoDTO();
			Persona persona = personaSolicitud.getPersona();
			
			formularioParteEnmascaradoDTO.setIdPersona(persona.getIdPersona());
			formularioParteEnmascaradoDTO.setPrimerNombre(persona.getPrimerNombreORazonSocial());
			formularioParteEnmascaradoDTO.setSegundoNombre(persona.getSegundoNombre());
			formularioParteEnmascaradoDTO.setPrimerApellidoORazonSocial(persona.getPrimerApellido());
			formularioParteEnmascaradoDTO.setSegundoApellido(persona.getSegundoApellido());
			formularioParteEnmascaradoDTO.setNombreCompleto(persona.getNombreCompleto());
			formularioParteEnmascaradoDTO.setNumeroIdentificacion(persona.getNumeroDocumento());
			formularioParteEnmascaradoDTO.setIdSolicitudServicio(idSolicitudServicio);

			Dominio dominioTipoPersona = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_PERSONA,
					persona.getTipoPersona());
			formularioParteEnmascaradoDTO
					.setTipoPersona(new DominioDTO().transformarEntidadSinDependencias(dominioTipoPersona));
			Dominio dominioTipoDocumento = dominioFacade
					.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA, persona.getTipoDocumento());
			formularioParteEnmascaradoDTO.setTipoIdentificacion(
					new DominioDTO().transformarEntidadSinDependencias(dominioTipoDocumento));
			
			Dominio dominioRol = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ROL_PERSONA,
					personaSolicitud.getRol().getNombre());
			formularioParteEnmascaradoDTO.setRol(dominioRol);
			
			ZonaGeografica ciudad = manejadorZonaGeografica.buscar(persona.getCiudadDelDocumento());
			if (ciudad != null)
				formularioParteEnmascaradoDTO.setCiudadIdentificacionTexto(ciudad.getNombre());
			ZonaGeografica pais = manejadorZonaGeografica.buscar(persona.getIdPaisOrigen());
			if (pais != null)
				formularioParteEnmascaradoDTO.setNacionalidad(pais.getNombre());
			
			if (UtilDominios.TIPO_PERSONA_NATURAL.equals(persona.getTipoPersona())) {
				
				if(personaSolicitud.getPersona().getFechaDeNacimiento() != null) {
					formularioParteEnmascaradoDTO.setFechaNacimientoEnmascarado(UtilMascaraTexto.replaceCharacterByDot(personaSolicitud.getPersona().getFechaDeNacimiento().toString()));
				}
				
				formularioParteEnmascaradoDTO.setFechaGrado(personaSolicitud.getPersona().getFechaDeGrado());
				formularioParteEnmascaradoDTO.setNumeroTarjetaProfesional(personaSolicitud.getPersona().getNumeroTarjetaProfesional());			
				formularioParteEnmascaradoDTO.setProfesion(personaSolicitud.getPersona().getProfesion());
				
				Dominio dominioSexo = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SEXOS, persona.getSexo());
				formularioParteEnmascaradoDTO.setTipoSexo(dominioSexo);
				
				Dominio dominioEntidadTarjetaProfesional = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ENTIDAD_TARJETA_PROFESIONAL, persona.getSexo());
				formularioParteEnmascaradoDTO.setEntidadTarjetaProfesional(dominioEntidadTarjetaProfesional);
				
				Dominio dominioInstitucion = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_INSTITUCIONES_EDUCATIVAS, persona.getInstitucionEducativa());							
				DominioEnmascaradoDTO dominioInstitucionEducativaDTO = dominioEnmascaradoDTO.transformarSinDependencias(dominioInstitucion);
				if(dominioInstitucion.getDominioPK() != null) {				
					DominioPKDTO dominioPKInstitucionEducativa = dominioInstitucionEducativaDTO.getDominioPK();				
					if(dominioPKInstitucionEducativa.getCodigo() != null) {					
						dominioPKInstitucionEducativa.setCodigo(UtilMascaraTexto.replaceCharacterByDot("Institucion "+dominioPKInstitucionEducativa.getCodigo()));
						dominioInstitucionEducativaDTO.setDominioPK(dominioPKInstitucionEducativa);					
					}
				}
				dominioInstitucionEducativaDTO.setNombre(UtilMascaraTexto.replaceCharacterByDot(dominioInstitucionEducativaDTO.getNombre()));
				dominioInstitucionEducativaDTO.setDescripcion(UtilMascaraTexto.replaceCharacterByDot(dominioInstitucionEducativaDTO.getDescripcion()));
				formularioParteEnmascaradoDTO.setInstitucionEducativa(dominioInstitucionEducativaDTO);
				
				
				Dominio dominioEstrato = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESTRATOS, persona.getEstrato());
				DominioEnmascaradoDTO dominioEstratoDTO = dominioEnmascaradoDTO.transformarSinDependencias(dominioEstrato);
				if(dominioEstrato.getDominioPK() != null) {											
					DominioPKDTO dominioPKEstrato = dominioEstratoDTO.getDominioPK();				
					if(dominioPKEstrato.getCodigo() != null) {
						String codigoEstratoSinEnmascarar = dominioPKEstrato.getCodigo();
						dominioPKEstrato.setCodigo(UtilMascaraTexto.replaceCharacterByDot(codigoEstratoSinEnmascarar));
						dominioEstratoDTO.setDominioPK(dominioPKEstrato);					
					}					
				}
				dominioEstratoDTO.setNombre(UtilMascaraTexto.replaceCharacterByDot("Estrato"+dominioEstratoDTO.getNombre()));
				dominioEstratoDTO.setDescripcion(UtilMascaraTexto.replaceCharacterByDot(dominioEstratoDTO.getDescripcion()));
				formularioParteEnmascaradoDTO.setEstrato(dominioEstratoDTO);
				
				Dominio dominioEscolaridad = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESCOLARIDADES, persona.getEscolaridad());
				DominioEnmascaradoDTO dominioEscolaridadDTO = dominioEnmascaradoDTO.transformarSinDependencias(dominioEscolaridad);
				if(dominioEscolaridad.getDominioPK() != null) {				
					DominioPKDTO dominioPKEscolaridad = dominioEscolaridadDTO.getDominioPK();
					if(dominioPKEscolaridad.getCodigo() != null) {					
						dominioPKEscolaridad.setCodigo(UtilMascaraTexto.replaceCharacterByDot(dominioPKEscolaridad.getCodigo()));
						dominioEscolaridadDTO.setDominioPK(dominioPKEscolaridad);					
					}
				}
				dominioEscolaridadDTO.setNombre(UtilMascaraTexto.replaceCharacterByDot(dominioEscolaridadDTO.getNombre()));
				dominioEscolaridadDTO.setDescripcion(UtilMascaraTexto.replaceCharacterByDot(dominioEscolaridadDTO.getDescripcion()));
				formularioParteEnmascaradoDTO.setEscolaridad(dominioEscolaridadDTO);
			} else {
				formularioParteEnmascaradoDTO.setRepresentanteLegal(persona.getRepresentanteLegal());
				formularioParteEnmascaradoDTO.setPaginaWeb(persona.getPaginaWeb());
				
				Dominio dominioSector = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SECTOR_EMPRESA, persona.getSectorDeLaEmpresa());
				formularioParteEnmascaradoDTO.setSectorEmpresa(dominioSector);				
			}
			
			UbicacionDTO ubicacionPrincipal = manejadorUbicacion.consultarPrimeraUbicacionPersona(persona.getIdPersona());
			if (ubicacionPrincipal.getIdUbicacion() != null) {
				ZonaGeografica ciudadDireccion = manejadorZonaGeografica.buscar(ubicacionPrincipal.getIdZonaGeografica());
				ubicacionPrincipal.setCiudad(ciudadDireccion);
				ZonaGeografica paisDireccion = ciudadDireccion.getZonaGeografica().getZonaGeografica();
				ubicacionPrincipal.setPais(paisDireccion);
				formularioParteEnmascaradoDTO.setLstUbicacion(Arrays.asList(ubicacionPrincipal));
			}
			
			List<Telefono> telefonos =  manejadorTelefono.consultarPorTipoYPersona(Arrays.asList(UtilDominios.TIPO_TELEFONO_FIJO), persona.getIdPersona());
			
			if(!telefonos.isEmpty()) {
				TelefonoDTO telefono = new TelefonoDTO().transformarSinDependencias(telefonos.get(0)); 
				telefono.setNumeroEnmascarado(UtilMascaraTexto.replaceCharacterByDot(telefono.getNumero()));
				formularioParteEnmascaradoDTO.setTelefonos(Arrays.asList(telefono));
			}
			
			CorreoElectronico correoPrincipal = manejadorCorreoElectronico.consultarPorTipoCorreoIdPersona(
					persona.getIdPersona(), UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
			
			if (correoPrincipal != null) {
				
				List<CorreoElectronicoEnmascaradoDTO> correosElectronicosDTO = Arrays.asList(new CorreoElectronicoDTO().transformarEnmascarandoDireccion(correoPrincipal));				
				formularioParteEnmascaradoDTO.setCorreosElectronicos(correosElectronicosDTO);				
			}
			listaFormularioPartesEnmascarado.add(formularioParteEnmascaradoDTO);
		}

		return listaFormularioPartesEnmascarado;
	}

	@Override
	public FormularioParteEnmascaradoDTO consultarParteEnmascaradaSolicitudServicio(Long idSolicitudServicio, Long idPersona)
			throws SIMASCNegocioExcepcion {

		FormularioParteEnmascaradoDTO formularioParteEnmascaradoDTO = new FormularioParteEnmascaradoDTO();

		try {
			List<PersonaSolicitud> personaListaAnterior = manejadorPersonaSolicitud
					.consultarPersonaSolicitud(idSolicitudServicio, idPersona, null);

			PersonaSolicitud personaSolicitud = null;
			for (PersonaSolicitud personaSolicitudL : personaListaAnterior) {
				if (!UtilDominios.ROL_PERSONA_SOLICITANTE.equals(personaSolicitudL.getRol().getNombre()))
					personaSolicitud = personaSolicitudL;
			}
			
			Persona persona = personaSolicitud.getPersona();
			DominioEnmascaradoDTO dominioEnmascaradoDTO = new DominioEnmascaradoDTO();

			formularioParteEnmascaradoDTO.setIdSolicitudServicio(idSolicitudServicio);
			Dominio dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_PERSONA, persona.getTipoPersona());
			formularioParteEnmascaradoDTO.setTipoPersona(dominio);

			Dominio dominioRol = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ROL_PERSONA,
					personaSolicitud.getRol().getNombre());
			formularioParteEnmascaradoDTO.setRol(dominioRol);

			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA,
					persona.getTipoDocumento());
			formularioParteEnmascaradoDTO.setTipoIdentificacion(dominio);
			formularioParteEnmascaradoDTO.setNumeroIdentificacion(persona.getNumeroDocumento());

			// Ubicación de la parte			
			asignarUbicacionEnmascaradaParte(persona, formularioParteEnmascaradoDTO);

			formularioParteEnmascaradoDTO.setNacionalidad(persona.getIdPaisOrigen());
			formularioParteEnmascaradoDTO.setPrimerNombre(persona.getPrimerNombreORazonSocial());
			formularioParteEnmascaradoDTO.setSegundoNombre(persona.getSegundoNombre());
			formularioParteEnmascaradoDTO.setPrimerApellidoORazonSocial(persona.getPrimerApellido());
			formularioParteEnmascaradoDTO.setSegundoApellido(persona.getSegundoApellido());
			formularioParteEnmascaradoDTO.setIdPersona(persona.getIdPersona());

			// Teléfonos y correos electrónicos de las partes			
			asignarTelefonosYCorreosEnmascaradosParte(persona, formularioParteEnmascaradoDTO);			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SEXOS, persona.getSexo());
			formularioParteEnmascaradoDTO.setTipoSexo(dominio);
						
			if(persona.getFechaDeNacimiento() != null) {
				formularioParteEnmascaradoDTO.setFechaNacimientoEnmascarado(UtilMascaraTexto.replaceCharacterByDot(persona.getFechaDeNacimiento().toString()));
			}			
						
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESTRATOS, persona.getEstrato());						
			if(dominio.getDominioPK() != null) {								
				DominioEnmascaradoDTO dominioEstratoDTO = dominioEnmascaradoDTO.transformarSinDependencias(dominio);
				DominioPKDTO dominioPKEstrato = dominioEstratoDTO.getDominioPK();	
				
				if(dominioPKEstrato.getCodigo() != null) {					
					String codigoEstratoSinEnmascarar = dominioPKEstrato.getCodigo();
					dominioPKEstrato.setCodigo(UtilMascaraTexto.replaceCharacterByDot(codigoEstratoSinEnmascarar));
					dominioEstratoDTO.setDominioPK(dominioPKEstrato);					
				}	
				dominioEstratoDTO.setNombre(UtilMascaraTexto.replaceCharacterByDot("Estrato"+dominioEstratoDTO.getNombre()));
				dominioEstratoDTO.setDescripcion(UtilMascaraTexto.replaceCharacterByDot(dominioEstratoDTO.getDescripcion()));
				formularioParteEnmascaradoDTO.setEstrato(dominioEstratoDTO);
			}
			
						
			formularioParteEnmascaradoDTO.setProfesion(persona.getProfesion());
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESCOLARIDADES, persona.getEscolaridad());					
			if(dominio.getDominioPK() != null) {					
				DominioEnmascaradoDTO dominioEscolaridadDTO = dominioEnmascaradoDTO.transformarSinDependencias(dominio);
				DominioPKDTO dominioPKEscolaridad = dominioEscolaridadDTO.getDominioPK();
				if(dominioPKEscolaridad.getCodigo() != null) {							
					dominioPKEscolaridad.setCodigo(UtilMascaraTexto.replaceCharacterByDot(dominioPKEscolaridad.getCodigo()));
					dominioEscolaridadDTO.setDominioPK(dominioPKEscolaridad);					
				}
				dominioEscolaridadDTO.setNombre(UtilMascaraTexto.replaceCharacterByDot(dominioEscolaridadDTO.getNombre()));
				dominioEscolaridadDTO.setDescripcion(UtilMascaraTexto.replaceCharacterByDot(dominioEscolaridadDTO.getDescripcion()));
				formularioParteEnmascaradoDTO.setEscolaridad(dominioEscolaridadDTO);
			}			
						
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_INSTITUCIONES_EDUCATIVAS,
					persona.getInstitucionEducativa());				
			
			if(dominio.getDominioPK() != null) {					
				DominioEnmascaradoDTO dominioInstitucionEducativaDTO = dominioEnmascaradoDTO.transformarSinDependencias(dominio);
				DominioPKDTO dominioPKInstitucionEducativa = dominioInstitucionEducativaDTO.getDominioPK();				
				if(dominioPKInstitucionEducativa.getCodigo() != null) {						
					dominioPKInstitucionEducativa.setCodigo(UtilMascaraTexto.replaceCharacterByDot("Institucion "+dominioPKInstitucionEducativa.getCodigo()));
					dominioInstitucionEducativaDTO.setDominioPK(dominioPKInstitucionEducativa);					
				}
				dominioInstitucionEducativaDTO.setNombre(UtilMascaraTexto.replaceCharacterByDot(dominioInstitucionEducativaDTO.getNombre()));
				dominioInstitucionEducativaDTO.setDescripcion(UtilMascaraTexto.replaceCharacterByDot(dominioInstitucionEducativaDTO.getDescripcion()));
				formularioParteEnmascaradoDTO.setInstitucionEducativa(dominioInstitucionEducativaDTO);
			}
						
			formularioParteEnmascaradoDTO.setFechaGrado(persona.getFechaDeGrado());
			formularioParteEnmascaradoDTO.setNumeroTarjetaProfesional(persona.getNumeroTarjetaProfesional());
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ENTIDAD_TARJETA_PROFESIONAL,
					persona.getEntidadExpideTarjetaProfesional());
			formularioParteEnmascaradoDTO.setEntidadTarjetaProfesional(dominio);
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_EMPRESA, persona.getTipoDeEmpresa());
			formularioParteEnmascaradoDTO.setTipoEmpresa(dominio);
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_ENTIDAD_PUBLICA,
					persona.getTipoDeEntidadPublica());
			formularioParteEnmascaradoDTO.setTipoEntidadPublica(dominio);
			formularioParteEnmascaradoDTO.setRepresentanteLegal(persona.getRepresentanteLegal());
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SECTOR_EMPRESA,
					persona.getSectorDeLaEmpresa());
			formularioParteEnmascaradoDTO.setSectorEmpresa(dominio);
			formularioParteEnmascaradoDTO.setPaginaWeb(persona.getPaginaWeb());
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_CONSULTA_PREVIA,
					persona.getTieneEmpleo());
			formularioParteEnmascaradoDTO.setTieneEmpleo(dominio);
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_TIPO_DE_EMPLEO,
					persona.getTipoEmpleo());
			formularioParteEnmascaradoDTO.setTipoEmpleo(dominio);
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_SECTOR_EMPRESA,
					persona.getSectorEconomico());
			formularioParteEnmascaradoDTO.setSectorEconomico(dominio);
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ESTADOS_CIVILES,
					persona.getEstadoCivil());
			formularioParteEnmascaradoDTO.setEstadoCivil(dominio);
			
			if(persona.getNumeroPersonasAcargo() != null) {
				formularioParteEnmascaradoDTO.setNumeroPersonasAcargo(Integer.parseInt(persona.getNumeroPersonasAcargo()));
			}
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_CONSULTA_PREVIA,
					persona.getTieneSociedadConyugal());
			formularioParteEnmascaradoDTO.setTieneSociedadConyugal(dominio);
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_CONSULTA_PREVIA,
					persona.getSociedadConyugalVigente());
			formularioParteEnmascaradoDTO.setSociedadConyugalVigente(dominio);	
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_OCUPACION_EQUIDAD,
					persona.getOcupacion());
			formularioParteEnmascaradoDTO.setOcupacion(dominio);
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_ACTIV_ECONOMICA_EQUIDAD,
					persona.getActividadEconomica());			
			formularioParteEnmascaradoDTO.setActividadEconomica(dominio);
			
			dominio = dominioFacade.getDominioSinClasificadores(UtilDominios.DOMINIO_PROPIO_NEGOCIO_EQUIDAD,
					persona.getNombreNegocio());				
			formularioParteEnmascaradoDTO.setNombreNegocio(dominio);
			
			String nitEmpresa=(persona.getNitEmpresa()==null)?"":persona.getNitEmpresa();
			formularioParteEnmascaradoDTO.setNitEmpresa(nitEmpresa);
			
			String nombreEmpresa=(persona.getNombreEmpresa()==null)?"":persona.getNombreEmpresa();
			formularioParteEnmascaradoDTO.setNombreEmpresa(nombreEmpresa);
			
			formularioParteEnmascaradoDTO.setOtraActividadEconomica(persona.getOtraActividadEconomica());
			formularioParteEnmascaradoDTO.setOtraNombreNegocio(persona.getOtraNombreNegocio());
			
			// Obtiene las partes representadas de un apoderado en caso de que
			// la persona tenga el último rol dentro de la solicitud de servicio
			if (dominioRol != null) {
				obtenerRepresentadosParteEnmascaradoSolicitudServicio(formularioParteEnmascaradoDTO, idSolicitudServicio, personaSolicitud.getRol().getIdRol());
			}
			
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e);			
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		return formularioParteEnmascaradoDTO;
	}
	
	/**
	 * Metodo encargado de asignar los telefonos y correos de la persona enmascarandolos
	 * 
	 * @param persona
	 * @param formularioParteDTO
	 */
	private void asignarTelefonosYCorreosEnmascaradosParte(Persona persona, FormularioParteEnmascaradoDTO formularioParteEnmascaradoDTO) {
		int i = 0;

		for (Telefono telefono : telefonoFacade.transformarEntidadesColeccionSinDependencias(persona.getTelefonoList(),
				new ArrayList<Telefono>())) {
			if (telefono.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_CELULAR) && telefono.getNumero() != null) {
				formularioParteEnmascaradoDTO.setNumeroCelularEnmascarado(UtilMascaraTexto.replaceCharacterByDot(telefono.getNumero()));
			}
			if (i > 0 && telefono.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO) && telefono.getNumero() != null) {
				formularioParteEnmascaradoDTO.setNumeroTelefonoDosEnmascarado(UtilMascaraTexto.replaceCharacterByDot(telefono.getNumero()));
				i++;
			}
			if (i == 0 && telefono.getTipoTelefono().equals(UtilDominios.TIPO_TELEFONO_FIJO) && telefono.getNumero() != null) {
				formularioParteEnmascaradoDTO.setNumeroTelefonoUnoEnmascarado(UtilMascaraTexto.replaceCharacterByDot(telefono.getNumero()));
				i++;
			}
		}

		for (CorreoElectronico correoElectronico : correoElectronicoFacade.transformarEntidadesColeccionSinDependencias(
				persona.getCorreoElectronicoList(), new ArrayList<CorreoElectronico>())){
			if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
					&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL.equals(correoElectronico.getTipo())
					&& correoElectronico.getDireccion() != null) {
				formularioParteEnmascaradoDTO.setEmailUnoEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(correoElectronico.getDireccion()));
				break;
			}
		}			
		
		PersonaSolicitudPK personaSolicitudPk = new PersonaSolicitudPK();
		personaSolicitudPk.setIdPersona(persona.getIdPersona());
		personaSolicitudPk.setIdRol(persona.getPersonaSolicitudList().get(0).getRol().getIdRol());
		personaSolicitudPk.setIdSolicitudServicio(formularioParteEnmascaradoDTO.getIdSolicitudServicio());
		
		List<CorreoElectronico> correoElectronicoList = manejadorCorreoElectronicoPersonaSolicitud.consultarCorreosPorPersonaSolicitud(personaSolicitudPk);

		for (CorreoElectronico correoElectronico : correoElectronicoList ) {	

			if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
					&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO.equals(correoElectronico.getTipo())
					&& correoElectronico.getDireccion() != null) {
				formularioParteEnmascaradoDTO.setEmailDosEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(correoElectronico.getDireccion()));
			}
			
			if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(correoElectronico.getEstadoRegistro())
					&& UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_TERCIARIO.equals(correoElectronico.getTipo())
					&& correoElectronico.getDireccion() != null) {
				formularioParteEnmascaradoDTO.setEmailTresEnmascarado(UtilMascaraTexto.replaceEmailCharactersByDot(correoElectronico.getDireccion()));
			}
			
			if (formularioParteEnmascaradoDTO.getEmailUnoEnmascarado() != null && formularioParteEnmascaradoDTO.getEmailDosEnmascarado() != null
					&& formularioParteEnmascaradoDTO.getEmailTresEnmascarado() != null) {
				break;
			}

		}
	}
	/**
	 * Obtiene las partes representadas de un apoderado en una soplicitud de
	 * servicio de un FormularioEnmascarado
	 * 
	 * @param formularioParteDTO
	 * @param idRol 
	 * @param idSolicitudServicio 
	 */
	@SuppressWarnings("unchecked")
	private void obtenerRepresentadosParteEnmascaradoSolicitudServicio(FormularioParteEnmascaradoDTO formularioParteEnmascaradoDTO, Long idSolicitudServicio, Long idRol) {
		// Obtiene la solicitud de servicio para saber el tipo de servicio		
		SolicitudServicio solicitudServicio = manejadorSolicitudServicio
				.buscar(formularioParteEnmascaradoDTO.getIdSolicitudServicio());
		Servicio servicio = manejadorServicio.buscar(solicitudServicio.getIdServicio());
		List<Persona> personasRepresentadas = new ArrayList<>();

		// Obtiene las partes dependiendo del tipo de servicio de la solicitud
		switch (servicio.getTipo()) {
		case UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA:
			
			List<ApoderadosSolicitud> representadosList = manejadorApoderadosSolicitud.obtenerRepresentadosPorApoderado(idRol, formularioParteEnmascaradoDTO.getIdPersona(),
					idSolicitudServicio);
			
			for (ApoderadosSolicitud representado : representadosList) {
				Persona personaRepresentada = manejadorPersona.buscar(representado.getApoderadosSolicitudPK().getIdPersonaRepresentado());
				personasRepresentadas.add(personaRepresentada);
			}

			break;

		case UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS:
			
			personasRepresentadas = manejadorPersonaSolicitud.consultarPartesSolicitudPorRolApoderado(
					formularioParteEnmascaradoDTO.getIdSolicitudServicio(),
					formularioParteEnmascaradoDTO.getRol().getDominioPK().getCodigo()
							.equals(UtilDominios.ROL_PERSONA_APODERADO_CONVOCADO) ? UtilDominios.ROL_PERSONA_CONVOCADO
									: UtilDominios.ROL_PERSONA_CONVOCANTE,
									formularioParteEnmascaradoDTO.getIdPersona());

			break;

		}

		// Adiciona la lista de representados a la persona apoderada
		formularioParteEnmascaradoDTO.setRepresentada(
				(List<Persona>) new PersonaDTO().transformarColeccionEntidadesSinDependencias(personasRepresentadas));

	}
	// protected region metodos adicionales end

}
