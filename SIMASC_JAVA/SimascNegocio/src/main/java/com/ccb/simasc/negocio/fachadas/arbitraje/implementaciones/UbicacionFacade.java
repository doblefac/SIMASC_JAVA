package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefono;
import com.ccb.simasc.integracion.manejadores.ManejadorUbicacion;
import com.ccb.simasc.integracion.manejadores.ManejadorUbicacionPersonaSolicitud;
import com.ccb.simasc.integracion.manejadores.ManejadorUbicacionRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionFacade;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.UbicacionDetalladaDTO;
import com.ccb.simasc.transversal.dto.UbicacionPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.PersonaSolicitudPK;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilMascaraTexto;

/**
 * Implementacion de fachada RESTFULL para {@link Ubicacion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class UbicacionFacade extends AccesoFacade<Ubicacion, Long, UbicacionDTO> implements IUbicacionFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorUbicacion manejadorUbicacion;
	
	@EJB
	private ManejadorTelefono telefonoManejador;
	
	@EJB
	private ManejadorUbicacionRolPersonaCaso manejadorUbicacionRolPersonaCaso;
	
	@EJB
	private ManejadorRol manejadorRol;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private ManejadorUbicacionPersonaSolicitud manejadorUbicacionPersonaSolicitud;
	
	//contexto de sesion de usuario.
    @Inject
    private ApplicationRequestContext appContext;

	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorUbicacion;
	}

	@Override
	public UbicacionDTO transformarSinDependencias(Ubicacion obj) {
		UbicacionDTO dto = new UbicacionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public UbicacionDTO transformarConDependencias(Ubicacion obj) {
		UbicacionDTO dto = new UbicacionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Ubicacion transformarEntidadConDependencias(Ubicacion obj) {
		return  new UbicacionDTO().transformarEntidadConDependencias(obj);		
	}

	@Override
	public Ubicacion transformarEntidadSinDependencias(Ubicacion obj) {
		return new UbicacionDTO().transformarEntidadSinDependencias(obj);		
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	@Override
	public List<Ubicacion> consultarUbicacionesPorPersona(Long idPersona, Boolean incluirInactivos) {
		return manejadorUbicacion.consultarPorIdPersona(idPersona, incluirInactivos);
	}
	
	@Override
	public Long adicionarDireccion(UbicacionDTO ubicacionDTO) throws SIMASCNegocioExcepcion {
		try {
			Ubicacion ubicacion = buscar(ubicacionDTO.getIdUbicacion());			
			if(ubicacion == null)
				ubicacion = new Ubicacion();
			ubicacion.setDireccion(ubicacionDTO.getDireccion());
			ubicacion.setLatitud(ubicacionDTO.getLatitud());
			ubicacion.setLongitud(ubicacionDTO.getLongitud());
			ubicacion.setFechaUltimaModificacion(new Date());
			ubicacion.setEstadoRegistro(ubicacionDTO.getEstadoRegistro());
			ubicacion.setIdZonaGeografica(ubicacionDTO.getIdZonaGeografica());
			ubicacion.setIdPersona(ubicacionDTO.getIdPersona());
			ubicacion.setTipo(ubicacionDTO.getTipo());
				
			if(ubicacion.getIdUbicacion() != null)
				manejadorUbicacion.actualizar(ubicacion);
			else
				manejadorUbicacion.crear(ubicacion);				
				
			
			return ubicacion.getIdUbicacion();
		} catch (Exception e) {
			String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString());
			throw new SIMASCNegocioExcepcion(msg);
		}
	}
	
	@Override
	public void eliminarDireccion(Long idUbicacion) throws SIMASCNegocioExcepcion {
		if(appContext != null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getIdUsuario() !=null){

			String usuarioActual = appContext.getContextoSesion().getIdUsuario();

			Ubicacion ubicacionDTOs = manejadorUbicacion.buscar(idUbicacion);
			ubicacionDTOs.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			manejadorUbicacion.actualizar(ubicacionDTOs);
			
	   		InformacionFiltro filtroTelefono = new InformacionFiltro(TipoFiltro.EXACTO,
					Telefono.ENTIDAD_TELEFONO_ID_UBICACION,
					idUbicacion);
	    	List<InformacionFiltro> filtrosTipoTelefono = new ArrayList<>();
	    	filtrosTipoTelefono.add(filtroTelefono);
	    	
			List<Telefono> telefonoDTOs = telefonoManejador.consultar(filtrosTipoTelefono, null, null);		
			
			if(!telefonoDTOs.isEmpty()){
				for (Telefono element : telefonoDTOs) {
					element.setFechaUltimaModificacion(new Date());
					element.setIdUsuarioModificacion(usuarioActual);
					element.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
					telefonoManejador.actualizar(element);
	        	}
			}
		}
	}
	
	@Override
	public UbicacionDTO consultarPrimeraUbicacionPersona(Long idPersona){
		return manejadorUbicacion.consultarPrimeraUbicacionPersona(idPersona);
	}
	
	
	public List<UbicacionDetalladaDTO> ubicacionDetallaByIdpersona(long idPersona){
		return manejadorUbicacion.ubicacionDetallaByIdpersona(idPersona);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionFacade#
	 * consutarDireccionesRolPersonaCaso(com.ccb.simasc.transversal.dto.
	 * UbicacionPersonaCasoDTO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UbicacionDTO> consutarDireccionesRolPersonaCaso(UbicacionPersonaCasoDTO ubicacionPersonaCasoDTO) {
		
		List<UbicacionDTO> ubicacionesRolPersonaCaso = new ArrayList<>();

		// Ubicación principal
		UbicacionDTO ubicacionPrincipal = consultarPrimeraUbicacionPersona(ubicacionPersonaCasoDTO.getIdPersona());
		if(ubicacionPrincipal.getIdUbicacion() != null)
			ubicacionesRolPersonaCaso.add(ubicacionPrincipal);

		// Ubicaciones secundarias
		if (ubicacionPersonaCasoDTO.getNombreRol() != null && !ubicacionPersonaCasoDTO.getNombreRol().isEmpty()
				&& ubicacionPersonaCasoDTO.getIdCasoOSolicitud() != null) {
			Rol rol = manejadorRol.consultarRolPorNombre(ubicacionPersonaCasoDTO.getNombreRol());

			if (rol != null) {
				List<UbicacionDTO> ubicacionesSecundarias = null;
				if(ubicacionPersonaCasoDTO.getEsSolicitud()) {
					PersonaSolicitudPK personaSolicitudPK = new PersonaSolicitudPK(rol.getIdRol(), ubicacionPersonaCasoDTO.getIdPersona(),
							ubicacionPersonaCasoDTO.getIdCasoOSolicitud());
					ubicacionesSecundarias = (List<UbicacionDTO>) new UbicacionDTO()
							.transformarColeccionEntidadesSinDependencias(manejadorUbicacionPersonaSolicitud
									.consultarUbicacionesPorPersonaSolicitud(personaSolicitudPK));
				} else {
					RolPersonaCasoPK rolPersonaCasoPK = new RolPersonaCasoPK(ubicacionPersonaCasoDTO.getIdPersona(),
							ubicacionPersonaCasoDTO.getIdCasoOSolicitud(), rol.getIdRol());
					ubicacionesSecundarias = (List<UbicacionDTO>) new UbicacionDTO()
							.transformarColeccionEntidadesSinDependencias(manejadorUbicacionRolPersonaCaso
									.consultarUbicacionesPorRolPersonaCaso(rolPersonaCasoPK));					
				}
				ubicacionesRolPersonaCaso.addAll(ubicacionesSecundarias);
			}

		}

		return ubicacionesRolPersonaCaso;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionFacade#
	 * gestionarDireccionPrincipal(java.lang.Long,
	 * com.ccb.simasc.transversal.entidades.Ubicacion)
	 */
	@Override
	public Long gestionarDireccionPrincipal(Long idPersona, UbicacionDTO ubicacion) {
		// Obtener Ubicacion Principal Actual
		UbicacionDTO ubicacionPrincipal = consultarPrimeraUbicacionPersona(idPersona);
		Ubicacion ubicacionPrincipalActual = new Ubicacion();
		ubicacionPrincipalActual.setDireccion(ubicacion.getDireccion());
		ubicacionPrincipalActual.setBarrio(ubicacion.getBarrio());
		ubicacionPrincipalActual.setLocalidad(ubicacion.getLocalidad());
		ubicacionPrincipalActual.setIdZonaGeografica(ubicacion.getIdZonaGeografica());
		ubicacionPrincipalActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		ubicacionPrincipalActual.setTipo(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
		if(ubicacionPrincipal.getIdUbicacion() == null) {
			ubicacionPrincipalActual.setIdPersona(idPersona);
			crear(ubicacionPrincipalActual);
		}
		else {
			ubicacionPrincipalActual.setIdUbicacion(ubicacionPrincipal.getIdUbicacion());
			ubicacionPrincipalActual.setIdPersona(ubicacionPrincipal.getIdPersona());
			actualizar(ubicacionPrincipalActual);
		}	
		
		return ubicacionPrincipalActual.getIdUbicacion();
	}

	// protected region metodos adicionales end

	@SuppressWarnings("unchecked")
	@Override
	public List<UbicacionDTO> consutarDireccionesEnmascaradasRolPersonaCaso(UbicacionPersonaCasoDTO ubicacionPersonaCasoObject) {
		
		List<UbicacionDTO> ubicacionesRolPersonaCasoList = new ArrayList<>();

		// Ubicación principal
		UbicacionDTO ubicacionPrincipalPersona = consultarPrimeraUbicacionPersona(ubicacionPersonaCasoObject.getIdPersona());
		if(ubicacionPrincipalPersona.getIdUbicacion() != null)				
			ubicacionPrincipalPersona.setDireccionEnmascarada(UtilMascaraTexto.replaceCharacterByDot(ubicacionPrincipalPersona.getDireccion()));						
		ubicacionesRolPersonaCasoList.add(ubicacionPrincipalPersona);

		// Ubicaciones secundarias
		if (ubicacionPersonaCasoObject.getNombreRol() != null && !ubicacionPersonaCasoObject.getNombreRol().isEmpty()
				&& ubicacionPersonaCasoObject.getIdCasoOSolicitud() != null) {
			Rol rolEntity = manejadorRol.consultarRolPorNombre(ubicacionPersonaCasoObject.getNombreRol());

			if (rolEntity != null) {
				List<UbicacionDTO> ubicacionesSecundariasList = null;
				if(ubicacionPersonaCasoObject.getEsSolicitud()) {
					PersonaSolicitudPK personaSolicitudPK = new PersonaSolicitudPK(rolEntity.getIdRol(), ubicacionPersonaCasoObject.getIdPersona(),
							ubicacionPersonaCasoObject.getIdCasoOSolicitud());
					ubicacionesSecundariasList = (List<UbicacionDTO>) new UbicacionDTO()
							.transformarColeccionEntidadesSinDependencias(manejadorUbicacionPersonaSolicitud
									.consultarUbicacionesPorPersonaSolicitud(personaSolicitudPK));
				} else {
					RolPersonaCasoPK rolPersonaCasoPK = new RolPersonaCasoPK(ubicacionPersonaCasoObject.getIdPersona(),
							ubicacionPersonaCasoObject.getIdCasoOSolicitud(), rolEntity.getIdRol());
					ubicacionesSecundariasList = (List<UbicacionDTO>) new UbicacionDTO()
							.transformarColeccionEntidadesSinDependencias(manejadorUbicacionRolPersonaCaso
									.consultarUbicacionesPorRolPersonaCaso(rolPersonaCasoPK));					
				}
				for (UbicacionDTO ubicacionSecundariaDTO : ubicacionesSecundariasList) {
					ubicacionSecundariaDTO.setDireccionEnmascarada(UtilMascaraTexto.replaceCharacterByDot(ubicacionSecundariaDTO.getDireccion()));
				}
				ubicacionesRolPersonaCasoList.addAll(ubicacionesSecundariasList);
			}
		}
		return ubicacionesRolPersonaCasoList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UbicacionDTO> consultaDireccionesEnmascaradasPersona(Long idPersona) {
		
		List<UbicacionDTO> ubicacionesPersona = new ArrayList<>();
		UbicacionDTO ubicacionDTO = new UbicacionDTO();
		
		List<Ubicacion> ubicaciones = manejadorUbicacion.consultarPorIdPersona(idPersona);
		
		for (Ubicacion ubicacion : ubicaciones) {
			UbicacionDTO ubicacionPersonaDTO = ubicacionDTO.transformarConDependencias(ubicacion);
			ubicacionPersonaDTO.setDireccionEnmascarada(UtilMascaraTexto.replaceCharacterByDot(ubicacionPersonaDTO.getDireccion()));
			ubicacionPersonaDTO.setDireccion(null);
			ubicacionesPersona.add(ubicacionPersonaDTO);
		}

		return ubicacionesPersona;
	}
	
}
