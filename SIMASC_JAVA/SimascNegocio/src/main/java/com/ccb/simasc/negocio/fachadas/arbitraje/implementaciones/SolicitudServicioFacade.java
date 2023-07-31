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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IIntegracionSWFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroDeServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaSolicitud;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorSolicitudServicio;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISolicitudServicioFacade;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.SolicitudServicioDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarLiquidacionDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.dto.formularios.ProcesoReliquidacionDTO;
import com.ccb.simasc.transversal.dto.formularios.RadicacionSolicitudDTO;
import com.ccb.simasc.transversal.dto.formularios.SolicitudConciliacionDTO;
import com.ccb.simasc.transversal.dto.formularios.SolicitudConciliacionEnmascaradoDTO;
import com.ccb.simasc.transversal.entidades.Centro;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.ParametroDeServicio;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.SolicitudServicio;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilMascaraTexto;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;
import com.ccb.simasc.transversal.utilidades.UtilParamServicio;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link SolicitudServicio}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class SolicitudServicioFacade extends AccesoFacade<SolicitudServicio, Long, SolicitudServicioDTO>
		implements ISolicitudServicioFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	private static final Logger logger = LogManager.getLogger(SolicitudServicioFacade.class.getName());
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;
	
	@EJB
	private ManejadorSolicitudServicio manejadorSolicitudServicio;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorPersonaSolicitud manejadorPersonaSolicitud;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;
	
	@EJB
	private IRolFacade rolFacade;
	
	@EJB
	private IIntegracionSWFacade integracionSWFacade;
	
	@EJB
	private IDiaFestivoFacade diaFestivoFacade;
	
	@EJB
	private IParametrosGeneralesFacade parametrosGeneralesFacade;
	
	@EJB
	private ManejadorParametroDeServicio manejadorParametroDeServicio;
	
	@EJB
	private ManejadorDominio manejadorDominio;
	
	@EJB
	private ManejadorCaso manejadorCaso;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorSolicitudServicio;
	}

	@Override
	public SolicitudServicioDTO transformarSinDependencias(SolicitudServicio obj) {
		SolicitudServicioDTO dto = new SolicitudServicioDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public SolicitudServicioDTO transformarConDependencias(SolicitudServicio obj) {
		SolicitudServicioDTO dto = new SolicitudServicioDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public SolicitudServicio transformarEntidadConDependencias(SolicitudServicio obj) {
		return new SolicitudServicioDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public SolicitudServicio transformarEntidadSinDependencias(SolicitudServicio obj) {
		return new SolicitudServicioDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public Long crearSolicitudConciliacion(SolicitudConciliacionDTO dto) throws SIMASCNegocioExcepcion {
		Long idSolicitudServicio = 0L;
		
		try {
			//Validar fecha de Audiencia
			if(dto.getSolicitudServicioDTO().getFechaInicioAudiencia() != null){
				Boolean esValida = this.fechaValidaAudiencia(new Date(),
						dto.getSolicitudServicioDTO().getFechaInicioAudiencia());
				
				if (!esValida)
					throw new SimascException(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR251.toString()));
			}		
			
			//Creacion de Persona, rol y correo electronico
			Persona persona = this.crearPersona(dto.getPersonaBasicaDTO());			
			Rol rol = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_SOLICITANTE);	
			correoElectronicoFacade.crearCorreoElectronicoPersona(dto.getCorreoElectronicoPersona(), persona.getIdPersona());
			
			// Creacion de solicitud de Servicio
			SolicitudServicio solicitudServicio = this.crearSolicitudServicio(dto.getSolicitudServicioDTO());
			this.crearPersonaSolicitud(rol, persona.getIdPersona(),
					solicitudServicio.getIdSolicitudServicio());

			// Asociar conciliador a la solicitud
			if (dto.getSolicitudServicioDTO().getIdConciliador() != null) {
				Persona conciliador = manejadorPersona.buscar(dto.getSolicitudServicioDTO().getIdConciliador());
				for (RolPersona it : conciliador.getRolPersonaList()) {
					if (UtilDominios.ROL_PERSONA_CONCILIADOR.equals(it.getRol().getNombre())) {
						this.crearPersonaSolicitud(it.getRol(), conciliador.getIdPersona(),
								solicitudServicio.getIdSolicitudServicio());
						break;
					}
				}
			}else {
				
				Long idRolCon = rolFacade.obtenerRolPorNombre(UtilDominios.ROL_PERSONA_CONCILIADOR).getIdRol(); 
	    		InformacionFiltro filtroRol = new InformacionFiltro(TipoFiltro.EXACTO, PersonaSolicitud.ENTIDAD_PERSONA_SOLICITUD_PK_ID_ROL,
	    				idRolCon);
	    		InformacionFiltro filtroSolicitud = new InformacionFiltro(TipoFiltro.EXACTO, PersonaSolicitud.ENTIDAD_PERSONA_SOLICITUD_PK_ID_SOLICITUD_SERVICIO,
	    				solicitudServicio.getIdSolicitudServicio());
	    		InformacionFiltro filtroActivo = new InformacionFiltro(TipoFiltro.EXACTO, PersonaSolicitud.ENTIDAD_PERSONA_SOLICITUD_ESTADO_REGISTRO,
	    				UtilDominios.ESTADO_REGISTRO_ACTIVO);
	    		    		
	        	List<InformacionFiltro> filtrosPersonaS = new ArrayList<>();
	        	filtrosPersonaS.add(filtroRol);
	        	filtrosPersonaS.add(filtroSolicitud);
	        	filtrosPersonaS.add(filtroActivo);

	        	List<PersonaSolicitud> personas = (List<PersonaSolicitud>)manejadorPersonaSolicitud.consultar(filtrosPersonaS, null, null);
	        	
	        	for (PersonaSolicitud element : personas) {
					element.setFechaUltimaModificacion(new Date());
					element.setEstadoRegistro("INA");
					manejadorPersonaSolicitud.actualizar(element);
	        	}
			}
			
			// Envio de correo electronico
			if (dto.getCorreoElectronicoPersona().size() >= 1) {
				List<String> destinos = new ArrayList<String>();
				ParametrosGenerales urlRetomaSolicitud = parametrosGeneralesFacade
						.consultarPorCodigo(UtilParamGenerales.CODIGO_URL_RETOMA_SOLICITUD);
				String cuerpoCorreo = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO231.toString(),
						solicitudServicio.getIdSolicitudServicio().toString(),
						(urlRetomaSolicitud != null && urlRetomaSolicitud.getValorTexto() != null
								? urlRetomaSolicitud.getValorTexto() : UtilConstantes.CADENA_VACIA));
				String asunto = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO230.toString());
				for (CorreoElectronicoDTO it : dto.getCorreoElectronicoPersona()) {
					if(it.getDireccion() == null) {
						CorreoElectronico correo = correoElectronicoFacade.buscar(it.getIdCorreo());
						destinos.add(correo.getDireccion().trim());	
					}else {
						destinos.add(it.getDireccion().trim());	
					}													
				}
				correoElectronicoFacade.enviarCorreoSinPersistencia(destinos, asunto, cuerpoCorreo);
			}	
			idSolicitudServicio = solicitudServicio.getIdSolicitudServicio();
		}catch (SimascException e) {		
			throw new SIMASCNegocioExcepcion(e.getMessage());
		}catch (Exception e) {
			logger.error("Error: ", e);			
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString()), e);
		}
		return idSolicitudServicio;
	}
	

	/**
	 * Metodo encargado de crear una solicitud de servicio
	 * 
	 * CON-F-124
	 * 
	 * @param dto
	 * @param sesion
	 * @return SolicitudServicio
	 */
	private SolicitudServicio crearSolicitudServicio(SolicitudServicioDTO dto){
		SolicitudServicio solicitudServicio;
		
		if (dto.getIdSolicitudServicio() != null) {
			solicitudServicio = manejadorSolicitudServicio.buscar(dto.getIdSolicitudServicio());
		} else {
			solicitudServicio = new SolicitudServicio();
		}
		
		//Campos obligatorios
		solicitudServicio.setHechos(dto.getHechos());
		solicitudServicio.setPretensiones(dto.getPretensiones());
		solicitudServicio.setTipoCuantia(dto.getTipoCuantia());
		solicitudServicio.setCuantia(dto.getCuantia());
		solicitudServicio.setInicioDeConflicto(dto.getInicioDeConflicto());
		solicitudServicio.setIdLugarConflicto(dto.getIdLugarConflicto());
		solicitudServicio.setParteQueSolicitaServicio(dto.getParteQueSolicitaServicio());
		solicitudServicio.setIdSede(dto.getIdSede());
		solicitudServicio.setIdServicio(dto.getIdServicio());
		solicitudServicio.setIdMateria(dto.getIdMateria());
		solicitudServicio.setFechaCreacion(dto.getFechaCreacion() != null ? dto.getFechaCreacion() : new Date());
		solicitudServicio.setFechaUltimaModificacion(new Date());
		solicitudServicio.setTipoPeritaje(dto.getTipoPeritaje());
		solicitudServicio.setEstadoRegistro(
				dto.getEstadoRegistro() != null ? dto.getEstadoRegistro() : UtilDominios.ESTADO_REGISTRO_ACTIVO);
		solicitudServicio.setIdAutorizacionTratamientoDatos(dto.getIdAutorizacionTratamientoDatos());
		//Campos opcionales
		solicitudServicio
				.setFechaInicioAudiencia(dto.getFechaInicioAudiencia() != null ? dto.getFechaInicioAudiencia() : null);
		solicitudServicio.setFechaFinAudiencia(dto.getFechaFinAudiencia() != null ? dto.getFechaFinAudiencia() : null);
		solicitudServicio.setTipoDeAudiencia(dto.getTipoDeAudiencia() != null ? dto.getTipoDeAudiencia() : null);
		//Campo para actualizar Solicitud de Servicio
		if(dto.getIdSolicitudServicio() != null)
			solicitudServicio.setIdSolicitudServicio(dto.getIdSolicitudServicio());
		
		return manejadorSolicitudServicio.crearSolicitudServicio(solicitudServicio);
	}
	
	/**
	 * Metodo encargado de validar la siguiente regla:
	 * 
	 * El calendario del campo Fecha de audiencia debe permitir seleccionar la
	 * fecha entre el rango que inicia desde la radicacion del caso mas el
	 * numero de dias habiles especificado en el parametro de límite inferior
	 * para programar audiencias y finaliza sumandole a la fecha del resultado
	 * anterior, el número de dias habiles especificado en el parametro limite
	 * superior para fijar audiencias. Estos parametros estan definidos para
	 * casos de tramite ordinario y cada uno de los convenios.
	 * 
	 * @param fechaRadicacionCaso
	 * @param fechaSeleccionadaAudiencia
	 * @return Boolean
	 */
	private Boolean fechaValidaAudiencia(Date fechaRadicacionCaso, Date fechaSeleccionadaAudiencia){		
		List<ParametroDeServicio> parametroDeServicios = manejadorParametroDeServicio
				.consultarParametrosDeServicio(UtilParamServicio.TIPO_PARAMETRO_PROGRAMACION_AUDIENCIA, null);
		
		Integer minDias = 0, maxDias = 0;
		
		for(ParametroDeServicio it : parametroDeServicios){
			if (UtilParamServicio.MIN_DIAS_PROGRAMACION_AUDIENCIA
					.equalsIgnoreCase(it.getParametroDeServicioPK().getNombre())) {
				minDias = Integer.valueOf(it.getValor());
			}else if(UtilParamServicio.MAX_DIAS_PROGRAMACION_AUDIENCIA
					.equalsIgnoreCase(it.getParametroDeServicioPK().getNombre())){
				maxDias = Integer.valueOf(it.getValor());
			}
		}
		
		
		//Se resta minDias-1 para que metodo after funcione correctamente como un >=
		Date fechaInicioAudiencia = diaFestivoFacade.adicionarDiasHabilesFecha(fechaRadicacionCaso,
				minDias-1);
		
		//Se suma maxDias+1 para que metodo before funcione correctamente como un <=
		Date fechaFinAudiencia = diaFestivoFacade.adicionarDiasHabilesFecha(fechaInicioAudiencia,
				maxDias+1);		
		
		if (fechaSeleccionadaAudiencia.after(fechaInicioAudiencia)
				&& fechaSeleccionadaAudiencia.before(fechaFinAudiencia)) {
			return true;
		}else
			return false;
	}
	
	
	@Override
	public Long crearSolicitudServicio(RadicacionSolicitudDTO radicacionSolicitudDTO) throws SIMASCNegocioExcepcion {
		Long idSolicitudServicio = 0L;

		try {
			// Validar fecha de Audiencia
			if (radicacionSolicitudDTO.getSolicitudServicioDTO().getFechaInicioAudiencia() != null) {
				// TODO: Validar fecha de la audiencia, aplica para conciliación
			}

			// Creación de Persona y Rol
			Persona persona = this.crearPersona(radicacionSolicitudDTO.getPersonaBasicaDTO());
			Rol rol = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_SOLICITANTE);
			
			
			
			if(radicacionSolicitudDTO.getCondicionesGeneralesDTO()!= null) {
				radicacionSolicitudDTO.getSolicitudServicioDTO().setCondicionesGeneralesDTO(radicacionSolicitudDTO.getCondicionesGeneralesDTO());
			}

			// Creacion de solicitud de Servicio
			SolicitudServicio solicitudServicio = this
					.registrarSolicitudServicio(radicacionSolicitudDTO.getSolicitudServicioDTO());
			this.crearPersonaSolicitud(rol, persona.getIdPersona(), solicitudServicio.getIdSolicitudServicio());

			// Asociar conciliador a la solicitud
			// TODO: Asociar conciliador a la solicitud, aplica para conciliación

			// Creación y envio de correo electronico			
			correoElectronicoFacade.crearCorreoElectronicoPersona(radicacionSolicitudDTO.getCorreoElectronicoPersona(),
					persona.getIdPersona());		

			if (!radicacionSolicitudDTO.getCorreoElectronicoPersona().isEmpty()) {
				
				List<String> destinos = new ArrayList<String>();
				ParametrosGenerales urlRetomaSolicitud = parametrosGeneralesFacade
						.consultarPorCodigo(UtilParamGenerales.CODIGO_URL_RETOMA_SOLICITUD);
				String cuerpoCorreo = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO231.toString(),
						solicitudServicio.getIdSolicitudServicio().toString(),
						(urlRetomaSolicitud != null && urlRetomaSolicitud.getValorTexto() != null
								? urlRetomaSolicitud.getValorTexto() : UtilConstantes.CADENA_VACIA));
				String asunto = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO230.toString());				
				for (CorreoElectronicoDTO it : radicacionSolicitudDTO.getCorreoElectronicoPersona()) {					
					if(!UtilMascaraTexto.hasEmailOnlyDots(it.getDireccion()) && it.getDireccion() != null) {
						destinos.add(it.getDireccion().trim());
					}else if(it.getIdCorreo() != null){
						CorreoElectronico correoElectronicoBD =correoElectronicoFacade.buscar(it.getIdCorreo());
						destinos.add(correoElectronicoBD.getDireccion());
					}
					if(!UtilMascaraTexto.hasEmailOnlyDots(it.getDireccionAnterior()) && it.getDireccionAnterior() != null) {						
						destinos.add(it.getDireccionAnterior().trim());
					}
				}	
				if(!destinos.isEmpty()) {
					correoRolPersonaCasoFacade.enviarCorreoSinPersistencia(destinos, asunto, cuerpoCorreo);
				}				
			}

			idSolicitudServicio = solicitudServicio.getIdSolicitudServicio();
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString()), e);
		}
		return idSolicitudServicio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ISolicitudServicioFacade#consultarSolicitudServicio(java.lang.Long)
	 */
	@Override
	public RadicacionSolicitudDTO consultarSolicitudServicio(Long idSolicitudServicio) throws SIMASCNegocioExcepcion {
		SolicitudServicio solicitudServicio = manejadorSolicitudServicio
				.consultarSolicitudServicio(idSolicitudServicio);
		RadicacionSolicitudDTO radicacionSolicitudDTO = new RadicacionSolicitudDTO();
		if (solicitudServicio != null) {
			for (PersonaSolicitud personaSolicitud : solicitudServicio.getPersonaSolicitudList()) {
				if (UtilDominios.ROL_PERSONA_SOLICITANTE.equalsIgnoreCase(personaSolicitud.getRol().getNombre())) {
					Persona persona = personaSolicitud.getPersona();
					PersonaBasicaDTO personaBasicaDTO = personaSolicitud.getPersona().convertirEntidadToPersonaBasicoDTO(persona);
					radicacionSolicitudDTO.setPersonaBasicaDTO(personaBasicaDTO);

					List<CorreoElectronicoDTO> electronicoDTOs = new ArrayList<CorreoElectronicoDTO>();

					for (CorreoElectronico correoElectronico : persona.getCorreoElectronicoList()) {
						electronicoDTOs
								.add(correoElectronico.convertirEntidadToCorreoElectronicoDto(correoElectronico));
					}

					radicacionSolicitudDTO.setCorreoElectronicoPersona(electronicoDTOs);
					break;
				}
			}

			SolicitudServicioDTO solicitudServicioDTO = solicitudServicio
					.convertirSolicitudServicioEntidadADTO(solicitudServicio);
			radicacionSolicitudDTO.setSolicitudServicioDTO(solicitudServicioDTO);
		} else {
			radicacionSolicitudDTO = null;
		}

		return radicacionSolicitudDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ISolicitudServicioFacade#validarPartesSolicitudServicio(java.lang.Long)
	 */
	@Override
	public boolean validarPartesSolicitudServicio(Long idSolicitudServicio) {
		boolean existeParteDemandante = false;
		boolean existeParteDemandada = false;

		List<String> roles = manejadorDominio.consultarCodigosDominio(UtilDominios.DOMINIO_ROL_PERSONA_PARTE);
		List<PersonaSolicitud> personasSolicitud = manejadorPersonaSolicitud
				.consultarPersonasSolicitud(idSolicitudServicio, roles, false);
		
		for (PersonaSolicitud personaSolicitud : personasSolicitud) {
			Rol rol = personaSolicitud.getRol();
			if( UtilConstantes.ID_SERVICIO_PERITAJE.equals(personaSolicitud.getSolicitudServicio().getIdServicio()) || UtilConstantes.ID_SERVICIO_DESIGNACION_ARBITROS.equals(personaSolicitud.getSolicitudServicio().getIdServicio()) )
				existeParteDemandada = true;
			if (rol != null && UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE.equals(rol.getNombre())) {
				existeParteDemandante = true;
			} else if (rol != null && UtilDominios.ROL_PERSONA_PARTE_DEMANDADA.equals(rol.getNombre())) {
				existeParteDemandada = true;
			}
		}

		return (existeParteDemandante && existeParteDemandada);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ISolicitudServicioFacade#consultarSolicitudesVigentesPorSolicitante(java.
	 * lang.Long)
	 */
	@Override
	public List<SolicitudServicioDTO> consultarSolicitudesVigentesPorSolicitante(Long idPersona) {
		List<SolicitudServicioDTO> solicitudesServicioVigentes = new ArrayList<>();

		List<SolicitudServicio> solicitudesServicio = manejadorSolicitudServicio
				.consultarSolicitudesVigentesPorSolicitante(idPersona);
		for (SolicitudServicio solicitudServicio : solicitudesServicio) {
			solicitudesServicioVigentes.add(solicitudServicio.convertirSolicitudServicioEntidadADTO(solicitudServicio));
		}

		return solicitudesServicioVigentes;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ISolicitudServicioFacade#liquidarSolicitudServicio(java.lang.Long)
	 */
	@Override
	public FormularioGenerarLiquidacionDTO liquidar(ProcesoReliquidacionDTO reliquidacion) {
		FormularioGenerarLiquidacionDTO formularioGenerarLiquidacionDTO = integracionSWFacade
				.realizarLiquidacion(reliquidacion);
		if (reliquidacion.getIsSolicitud()) {
			SolicitudServicio solicitudServicio = manejadorSolicitudServicio.buscar(reliquidacion.getIdSolicitud());
			if (solicitudServicio != null && solicitudServicio.getServicioMateria() != null
					&& solicitudServicio.getServicioMateria().getServicio() != null) {
				formularioGenerarLiquidacionDTO
						.setServicio(solicitudServicio.getServicioMateria().getServicio().getNombre());
			}
		}
		return formularioGenerarLiquidacionDTO;
	}

	/**
	 * Método encargado de crear una persona que realiza una Solicitud de
	 * Servicio
	 * 
	 * @param PersonaBasicaDTO
	 * @return Persona
	 */
	private Persona crearPersona(PersonaBasicaDTO personaBasicaDTO) {
		// Valida si la persona existe por tipo y número de documento
		Persona persona = manejadorPersona.consultarPersonaPorIdentificacion(personaBasicaDTO.getTipoDocumento(),
				personaBasicaDTO.getNumeroDocumento());

		// Si la persona existe la retorna, de lo contrario la crea
		if (persona != null) {
			return persona;
		} else {
			persona = new Persona();
			// Campos obligatorios
			persona.setTipoPersona(UtilDominios.TIPO_PERSONA_NATURAL);
			persona.setPrimerNombreORazonSocial(personaBasicaDTO.getPrimerNombreORazonSocial());
			persona.setEstadoPersona(UtilDominios.ESTADO_PERSONA_ACTIVO);
			persona.setFechaUltimaModificacion(new Date());
			persona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			// Campos opcionales
			persona.setTipoDocumento(
					personaBasicaDTO.getTipoDocumento() != null ? personaBasicaDTO.getTipoDocumento() : null);
			persona.setNumeroDocumento(
					personaBasicaDTO.getNumeroDocumento() != null ? personaBasicaDTO.getNumeroDocumento() : null);
			persona.setSegundoNombre(
					personaBasicaDTO.getSegundoNombre() != null ? personaBasicaDTO.getSegundoNombre() : null);
			persona.setPrimerApellido(
					personaBasicaDTO.getPrimerApellido() != null ? personaBasicaDTO.getPrimerApellido() : null);
			persona.setSegundoApellido(
					personaBasicaDTO.getSegundoApellido() != null ? personaBasicaDTO.getSegundoApellido() : null);
			// Campo para actualizar el registro
			if (personaBasicaDTO.getIdPersona() != null)
				persona.setIdPersona(personaBasicaDTO.getIdPersona());
			return manejadorPersona.crearPersona(persona);
		}
	}

	/**
	 * Método encargado de registrar la información de una solicitud de servicio
	 * 
	 * ARB-F-108
	 * 
	 * @param solicitudServicioDTO
	 * @param sesion
	 * @return SolicitudServicio
	 */
	private SolicitudServicio registrarSolicitudServicio(SolicitudServicioDTO solicitudServicioDTO) {
		SolicitudServicio solicitudServicio = new SolicitudServicio();
		// Campos obligatorios
		solicitudServicio.setHechos(solicitudServicioDTO.getHechos());
		solicitudServicio.setPretensiones(solicitudServicioDTO.getPretensiones());
		solicitudServicio.setTipoCuantia(solicitudServicioDTO.getTipoCuantia());
		solicitudServicio.setCuantia(solicitudServicioDTO.getCuantia());
		solicitudServicio.setInicioDeConflicto(solicitudServicioDTO.getInicioDeConflicto());
		solicitudServicio.setIdLugarConflicto(solicitudServicioDTO.getIdLugarConflicto());
		solicitudServicio.setParteQueSolicitaServicio(solicitudServicioDTO.getParteQueSolicitaServicio());
		solicitudServicio.setIdSede(solicitudServicioDTO.getIdSede());
		solicitudServicio.setIdServicio(solicitudServicioDTO.getIdServicio());
		solicitudServicio.setIdMateria(solicitudServicioDTO.getIdMateria());
		solicitudServicio.setIdAutorizacionTratamientoDatos(solicitudServicioDTO.getIdAutorizacionTratamientoDatos());
		solicitudServicio.setFechaCreacion(
				solicitudServicioDTO.getFechaCreacion() != null ? solicitudServicioDTO.getFechaCreacion() : new Date());
		solicitudServicio.setFechaUltimaModificacion(new Date());
		solicitudServicio.setEstadoRegistro(solicitudServicioDTO.getEstadoRegistro() != null
				? solicitudServicioDTO.getEstadoRegistro() : UtilDominios.ESTADO_REGISTRO_ACTIVO);
		// Campos opcionales
		solicitudServicio.setFechaInicioAudiencia(solicitudServicioDTO.getFechaInicioAudiencia() != null
				? solicitudServicioDTO.getFechaInicioAudiencia() : null);
		solicitudServicio.setFechaFinAudiencia(solicitudServicioDTO.getFechaFinAudiencia() != null
				? solicitudServicioDTO.getFechaFinAudiencia() : null);
		solicitudServicio.setTipoDeAudiencia(
				solicitudServicioDTO.getTipoDeAudiencia() != null ? solicitudServicioDTO.getTipoDeAudiencia() : null);
		solicitudServicio.setTipoTramite(
				solicitudServicioDTO.getTipoTramite() != null ? solicitudServicioDTO.getTipoTramite() : null);
		solicitudServicio.setIdCasoAnterior(
				solicitudServicioDTO.getIdCasoAnterior() != null ? solicitudServicioDTO.getIdCasoAnterior() : null);
		solicitudServicio.setPagoMediacion(
				solicitudServicioDTO.getPagoMediacion() != null ? solicitudServicioDTO.getPagoMediacion() : null);
		solicitudServicio.setMedidasCautelares(solicitudServicioDTO.isMedidasCautelares());
		// Campo para actualizar Solicitud de Servicio
		if (solicitudServicioDTO.getIdSolicitudServicio() != null)
			solicitudServicio.setIdSolicitudServicio(solicitudServicioDTO.getIdSolicitudServicio());
		//Campos condiciones generales insolvencia		
		if(solicitudServicioDTO.getCondicionesGeneralesDTO()!= null) {
			solicitudServicio.setValorMora(solicitudServicioDTO.getCondicionesGeneralesDTO().getValorMora()!= null ? solicitudServicioDTO.getCondicionesGeneralesDTO().getValorMora() : null);
			solicitudServicio.setCantAcreedor(solicitudServicioDTO.getCondicionesGeneralesDTO().getCantAcreedor()!= null ? solicitudServicioDTO.getCondicionesGeneralesDTO().getCantAcreedor() : null);
			solicitudServicio.setCantDeuda(solicitudServicioDTO.getCondicionesGeneralesDTO().getCantDeuda()!= null ? solicitudServicioDTO.getCondicionesGeneralesDTO().getCantDeuda() : null);
			solicitudServicio.setDomicilio(solicitudServicioDTO.getCondicionesGeneralesDTO().getDomicilio()!= null ? solicitudServicioDTO.getCondicionesGeneralesDTO().getDomicilio() : null);
			solicitudServicio.setSaldoCapital(solicitudServicioDTO.getCondicionesGeneralesDTO().getSaldoCapital()!= null ? solicitudServicioDTO.getCondicionesGeneralesDTO().getSaldoCapital() : null);
			solicitudServicio.setTipoPersona(solicitudServicioDTO.getCondicionesGeneralesDTO().getTipoPersona()!= null ? solicitudServicioDTO.getCondicionesGeneralesDTO().getTipoPersona() : null);
		}
		solicitudServicio.setArbitrajeConsumo(solicitudServicioDTO.isArbitrajeConsumo());
		solicitudServicio.setTipoConflicto(solicitudServicioDTO.getTipoConflicto()!= null?solicitudServicioDTO.getTipoConflicto():null);
		solicitudServicio.setEnteroServicio(solicitudServicioDTO.getEnteroServicio()!=null?solicitudServicioDTO.getEnteroServicio():null);
		solicitudServicio.setTipoPeritaje(solicitudServicioDTO.getTipoPeritaje());
		
		return manejadorSolicitudServicio.crearSolicitudServicio(solicitudServicio);
	}

	/**
	 * Método encargado de crear una Persona Solicitud de servicio conciliacion
	 * 
	 * ARB-F-108
	 * 
	 * @param idRol
	 * @param idPersona
	 * @param idSolicitudServicio
	 * @param sesion
	 * @return PersonaSolicitud
	 */
	private PersonaSolicitud crearPersonaSolicitud(Rol rol, Long idPersona, Long idSolicitudServicio) {
		// Valida si ya existe una persona asignada a la solicitud de servicio
		// con el rol de Solicitante
		List<PersonaSolicitud> personaSolicitudList = manejadorPersonaSolicitud
				.consultarPersonaSolicitud(idSolicitudServicio, null, rol.getNombre());

		PersonaSolicitud personaSolicitudAnterior = null;
		if(!personaSolicitudList.isEmpty()) {
			personaSolicitudAnterior = personaSolicitudList.get(0);
		}
		
		// Si ya existe una persona con rol Solicitante asociada a la solicitud
		// la elimina lógicamente y crea una nueva
		if (personaSolicitudAnterior != null) {
			manejadorPersonaSolicitud.eliminarPersonaSolicitud(personaSolicitudAnterior);
		}

		
		if(appContext != null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getRolPrincipal() !=null){
			
			
			String usuarioActual = appContext.getContextoSesion().getIdUsuario();
    		InformacionFiltro filtroPersonaSolicitud = new InformacionFiltro(TipoFiltro.EXACTO, PersonaSolicitud.ENTIDAD_PERSONA_SOLICITUD_PK_ID_SOLICITUD_SERVICIO,
    				idSolicitudServicio);
    		
    		InformacionFiltro filtroRol = new InformacionFiltro(TipoFiltro.EXACTO, PersonaSolicitud.ENTIDAD_PERSONA_SOLICITUD_PK_ID_ROL,
    				rol.getIdRol());
    		
        	List<InformacionFiltro> filtrosPersonaS = new ArrayList<>();
        	filtrosPersonaS.add(filtroPersonaSolicitud);
        	filtrosPersonaS.add(filtroRol);

        	List<PersonaSolicitud> personas = (List<PersonaSolicitud>)manejadorPersonaSolicitud.consultar(filtrosPersonaS, null, null);
        	
        	for (PersonaSolicitud element : personas) {
				element.setFechaUltimaModificacion(new Date());
				element.setIdUsuarioModificacion(usuarioActual);
				element.setEstadoRegistro("INA");
				manejadorPersonaSolicitud.actualizar(element);
        	}
        	
		}
		PersonaSolicitud personaSolicitud = new PersonaSolicitud();
		// Campos obligatorios
		personaSolicitud.getPersonaSolicitudPK().setIdRol(rol.getIdRol());
		personaSolicitud.getPersonaSolicitudPK().setIdPersona(idPersona);
		personaSolicitud.getPersonaSolicitudPK().setIdSolicitudServicio(idSolicitudServicio);
		personaSolicitud.setFechaUltimaModificacion(new Date());
		personaSolicitud.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return manejadorPersonaSolicitud.crearPersonaSolicitud(personaSolicitud);

	}
	

	@Override
	public SolicitudConciliacionDTO consultarSolicitudConciliacionServicio(Long idSolicitud) throws SIMASCNegocioExcepcion {
		SolicitudServicio solicitudServicio = manejadorSolicitudServicio.consultarSolicitudServicio(idSolicitud);
		SolicitudConciliacionDTO dto = new SolicitudConciliacionDTO();
		if(solicitudServicio != null){
			for(PersonaSolicitud it : solicitudServicio.getPersonaSolicitudList()){
				if(UtilDominios.ROL_PERSONA_SOLICITANTE.equalsIgnoreCase(it.getRol().getNombre())){
					PersonaBasicaDTO personaBasicaDTO = new PersonaBasicaDTO();
					Persona persona = it.getPersona();
					personaBasicaDTO = it.getPersona().convertirEntidadToPersonaBasicoDTO(persona);
					dto.setPersonaBasicaDTO(personaBasicaDTO);
					
					List<CorreoElectronicoDTO> electronicoDTOs = new ArrayList<CorreoElectronicoDTO>();
					
					for(CorreoElectronico it2 : persona.getCorreoElectronicoList()){
						electronicoDTOs.add(it2.convertirEntidadToCorreoElectronicoDto(it2));
					}
					
					dto.setCorreoElectronicoPersona(electronicoDTOs);		
					break;
				}
			}
			
			Centro centro = solicitudServicio.getSede().getCentro();
			dto.setCentro(centro.convertirEntidadToCentroDto(centro));
			
			List<PersonaSolicitud> personaSolicitudList = manejadorPersonaSolicitud
					.consultarPersonaSolicitud(idSolicitud, null, UtilDominios.ROL_PERSONA_CONCILIADOR);

			PersonaSolicitud personaConciliadora = null;
			if(!personaSolicitudList.isEmpty()) {
				personaConciliadora = personaSolicitudList.get(0);
			}

			SolicitudServicioDTO solicitudServicioDTO = solicitudServicio.convertirSolicitudServicioEntidadADTO(solicitudServicio);
			dto.setSolicitudServicioDTO(solicitudServicioDTO);
			
			if(personaConciliadora != null && dto.getSolicitudServicioDTO() != null){
				dto.getSolicitudServicioDTO().setIdConciliador(personaConciliadora.getPersonaSolicitudPK().getIdPersona());
			}
			
		}else
			dto = null;
		return dto;
	}

	/**
	 * TRANS-041 Transversal-Generacion-de-orden
	 * actualiza la solicitud de servicio
	 * @param idSolicitud,idOrdenPago
	 * @return
	 */
	@Override
	public void actualizarSolicitudServicio(Long idSolicitud,Long idOrdenPago, ContextoDeSesion cs) {
		SolicitudServicio solicitudServicio = new SolicitudServicio();		
		solicitudServicio.setIdSolicitudServicio(idSolicitud);
		solicitudServicio.setIdOrdenDePago(idOrdenPago);
		solicitudServicio.setIdUsuarioModificacion(cs.getIdUsuario());
		solicitudServicio.setFechaUltimaModificacion(new Date());
		
		manejadorSolicitudServicio.actualizar(solicitudServicio);
	}	
	

	@Override
	public void modificarSolicitudConciliacion(SolicitudConciliacionDTO dto)
			throws SIMASCNegocioExcepcion {

		try {
			// Validar fecha de Audiencia
			Boolean esValida = this.fechaValidaAudiencia(dto.getSolicitudServicioDTO().getFechaCreacion(),
					dto.getSolicitudServicioDTO().getFechaInicioAudiencia());

			if (!esValida)
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR250.toString()));

			// Actualizar Persona
			Persona persona = this.crearPersona(dto.getPersonaBasicaDTO());
			
			// Actualizar o crear nuevos correos electronicos
			correoElectronicoFacade.crearCorreoElectronicoPersona(dto.getCorreoElectronicoPersona(),
					persona.getIdPersona());

			// Actualizar datos de solicitud de Servicio
			SolicitudServicio solicitudServicio = this.crearSolicitudServicio(dto.getSolicitudServicioDTO());

			
			List<PersonaSolicitud> personaSolicitudList = manejadorPersonaSolicitud
					.consultarPersonaSolicitud(dto.getSolicitudServicioDTO().getIdSolicitudServicio(), null, UtilDominios.ROL_PERSONA_CONCILIADOR);

			PersonaSolicitud conciliador = null;
			if(!personaSolicitudList.isEmpty()) {
				conciliador = personaSolicitudList.get(0);
			}

			int compare = 0;

			if (conciliador != null) {
				/*
				 * Se compara el Conciliador que ya existe con el id de
				 * Conciliador que proviene desde el formulario y si es
				 * diferente se inactiva el que estaba y se crea el nuevo
				 * Conciliador
				 */
				compare = conciliador.getPersonaSolicitudPK().getIdPersona()
						.compareTo(dto.getSolicitudServicioDTO().getIdConciliador());
				if (compare != 0) {
					conciliador.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
					manejadorPersonaSolicitud.actualizar(conciliador);
				}
			}

			if (compare != 0 || conciliador == null) {
				// Asociar nuevo conciliador a la solicitud
				if (dto.getSolicitudServicioDTO().getIdConciliador() != null) {
					Persona nuevoConciliador = manejadorPersona
							.buscar(dto.getSolicitudServicioDTO().getIdConciliador());
					for (RolPersona it : nuevoConciliador.getRolPersonaList()) {
						if (UtilDominios.ROL_PERSONA_CONCILIADOR.equals(it.getRol().getNombre())) {
							this.crearPersonaSolicitud(it.getRol(), nuevoConciliador.getIdPersona(),
									solicitudServicio.getIdSolicitudServicio());
							break;
						}
					}
				}
			}

			// Envio de correo electronico
			if (dto.getCorreoElectronicoPersona().size() >= 1) {
				List<String> destinos = new ArrayList<String>();
				String cuerpoCorreo = String.format(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO210.toString()),
						solicitudServicio.getIdSolicitudServicio().toString());
				String asunto = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO200.toString());
				for (CorreoElectronicoDTO it : dto.getCorreoElectronicoPersona()) {
					destinos.add(it.getDireccion().trim());
				}
				correoElectronicoFacade.enviarCorreoSinPersistencia(destinos, asunto, cuerpoCorreo);
			}
		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString()));
		}
	}
	
	
	@Override
	public SolicitudServicio consultarSolicitudPorOrden(Long numeroOrden) {		
			return manejadorSolicitudServicio.consultarSolicitudPorOrden(numeroOrden).get(0);
				
	}
	// protected region metodos adicionales end
	
	@Override
	public SolicitudConciliacionEnmascaradoDTO consultarSolicitudConciliacionEnmascarando(Long idSolicitud) throws SIMASCNegocioExcepcion {
		
		return new SolicitudConciliacionEnmascaradoDTO().transformarAEnmascarando(consultarSolicitudConciliacionServicio(idSolicitud));				
	}

}
