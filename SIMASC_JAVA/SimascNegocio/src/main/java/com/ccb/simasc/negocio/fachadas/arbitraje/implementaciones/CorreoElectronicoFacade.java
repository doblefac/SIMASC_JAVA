package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilMascaraTexto;

import co.org.ccb.simasc.comun.correos.ArrayOfString;
import co.org.ccb.simasc.comun.correos.EnvioCorreoInDTO;
import co.org.ccb.simasc.comun.correos.EnvioCorreoServiceSoapProxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link CorreoElectronico}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class CorreoElectronicoFacade extends AccesoFacade<CorreoElectronico, Long, CorreoElectronicoDTO>
		implements ICorreoElectronicoFacade {

	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	private static final Logger logger = LogManager.getLogger(AudienciaFacade.class.getName());


	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorCorreoElectronico manejadorCorreoElectronico;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	@EJB
	private IParametrosGeneralesFacade parametrosGeneralesFacade;
	


	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorCorreoElectronico;
	}

	@Override
	public CorreoElectronicoDTO transformarSinDependencias(CorreoElectronico obj) {
		CorreoElectronicoDTO dto = new CorreoElectronicoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public CorreoElectronicoDTO transformarConDependencias(CorreoElectronico obj) {
		CorreoElectronicoDTO dto = new CorreoElectronicoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public CorreoElectronico transformarEntidadConDependencias(CorreoElectronico obj) {
		return new CorreoElectronicoDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public CorreoElectronico transformarEntidadSinDependencias(CorreoElectronico obj) {
		return new CorreoElectronicoDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	@Override
	public List<CorreoElectronico> consultaCorreosPersona(Long idPersona) {
		return manejadorCorreoElectronico.consultaCorreosPersona(idPersona);
	}
	
	@Override
	public List<CorreoElectronico> consultaCorreosPersona(Long idPersona, Boolean incluirInactivos) {
		return manejadorCorreoElectronico.consultaCorreosPersona(idPersona, incluirInactivos);
	}
	
	@Override
	public List<CorreoElectronico> consultaCorreosPersona(Long idPersona, Boolean incluirInactivos, String tipo) {
		return manejadorCorreoElectronico.consultaCorreosPersona(idPersona, incluirInactivos, tipo);
	}

	@Override
	public List<Map<String, String>> consultarDestinatarios(Long idCaso) {
		List<Map<String, String>> destinatarios = new ArrayList<Map<String, String>>();

		List<String> rolesDestinatarios = Arrays.asList(UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO,
				UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE, UtilDominios.ROL_PERSONA_PARTE_DEMANDADA,
				UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE, UtilDominios.ROL_PERSONA_ARBITRO);
		List<RolPersonaCaso> roles = manejadorRolPersonaCaso.consultarPersonasAsignadasCasoPorRol(idCaso,
				rolesDestinatarios);
		for (RolPersonaCaso rpc : roles) {
			if (!consultaCorreosPersona(rpc.getPersona().getIdPersona()).isEmpty()
					&& (!UtilDominios.ROL_PERSONA_ARBITRO.equals(rpc.getRol().getNombre())
							|| rpc.getEstado().equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO))) {
				Map<String, String> mapArbitro = new HashMap<>();
				mapArbitro.put("id", rpc.getRolPersonaCasoPK().getIdPersona().toString());
				mapArbitro.put("nombreCompleto", rpc.getPersona().getNombreCompleto());
				destinatarios.add(mapArbitro);
			}
		}
		return destinatarios;
	}

	@Override
	public void actualizarCorreosPersona(List<GenericoDTO> genericoDTOs, ContextoDeSesion contextoDeSesion) {
		try {
			for (GenericoDTO it1 : genericoDTOs) {
				CorreoElectronico correoElectronico = new CorreoElectronico();
				correoElectronico.setIdCorreo(Long.valueOf(it1.getId()));
				correoElectronico.setDireccion(it1.getNombre());
				correoElectronico.setIdUsuarioModificacion(contextoDeSesion.getIdUsuario());
				correoElectronico.setFechaUltimaModificacion(new Date());
				manejadorCorreoElectronico.crearCorreoElectronico(correoElectronico);
			}
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
	}
	
	@Override
	public CorreoElectronico crearCorreoElectronico(CorreoElectronicoDTO correoDTO) {
		CorreoElectronico correo; 
		if(correoDTO.getIdPersona() != null)
			correo = manejadorCorreoElectronico.consultarPorTipoCorreoIdPersona(correoDTO.getIdPersona(), correoDTO.getTipo());
		else
			correo = buscar(correoDTO.getIdCorreo());
		if(correo == null)
			correo = new CorreoElectronico();
		
		correo.setDireccion(correoDTO.getDireccion());
		correo.setEstadoRegistro(correoDTO.getEstadoRegistro());
		if(correo.getIdPersona() == null)
			correo.setIdPersona(correoDTO.getIdPersona());
		else
			correo.setIdUbicacion(correoDTO.getIdUbicacion());
		correo.setTipo(correoDTO.getTipo());
		
		if(correo.getIdCorreo() != null)
			actualizar(correo);
		else
			crear(correo);
		
		return correo;
	}

	@Override
	public void eliminarCorreosElectronicos(Long idPersona) {
		List<CorreoElectronico> correosElectronicosActivos = manejadorCorreoElectronico
				.consultaCorreosPersona(idPersona);
		for (CorreoElectronico correoFor : correosElectronicosActivos) {
			correoFor.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_INACTIVO);
			correoFor.setFechaUltimaModificacion(new Date());
			manejadorCorreoElectronico.actualizar(correoFor);
		}
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ICorreoElectronicoFacade#crearCorreoElectronicoPersona(java.util.List,
	 * java.lang.Long)
	 */
	@Override
	public void crearCorreoElectronicoPersona(List<CorreoElectronicoDTO> correosElectronicosPersona, Long idPersona) {
		for (CorreoElectronicoDTO iterator : correosElectronicosPersona) {
			CorreoElectronico correoElectronico = new CorreoElectronico();
			CorreoElectronico correoElectronicoBD = new CorreoElectronico();
			// Campos obligatorios			
			
			if (iterator.getIdCorreo() != null) {
				correoElectronico.setIdCorreo(iterator.getIdCorreo());
				correoElectronicoBD =manejadorCorreoElectronico.buscar(iterator.getIdCorreo());
			}
			if(!UtilMascaraTexto.hasEmailOnlyDots(iterator.getDireccion())) {				
				correoElectronico.setDireccion(iterator.getDireccion());
			}else if(correoElectronicoBD != null) {
				correoElectronico.setDireccion(correoElectronicoBD.getDireccion());
			}
			correoElectronico.setTipo(iterator.getTipo());
			correoElectronico.setFechaUltimaModificacion(new Date());
			correoElectronico.setEstadoRegistro(iterator.getEstadoRegistro() != null ? iterator.getEstadoRegistro()
					: UtilDominios.ESTADO_REGISTRO_ACTIVO);
			// Campos opcionales
			correoElectronico.setIdPersona(iterator.getIdPersona() != null ? iterator.getIdPersona() : idPersona);				
			manejadorCorreoElectronico.crearCorreoElectronico(correoElectronico);
		}
	}
	
	@Override
	public void enviarCorreoSinPersistencia(List<String> destinos, String asunto, String contenido) {
		ParametrosGenerales llaveEnvio = parametrosGeneralesFacade.consultarPorCodigo("LLAVE"); 
		ParametrosGenerales codContenido = parametrosGeneralesFacade.consultarPorCodigo("CONTENID");
		Persona sistema = manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		List<CorreoElectronico> correoEmisor  = consultaCorreosPersona(sistema.getIdPersona());
		
		EnvioCorreoServiceSoapProxy servicio = new EnvioCorreoServiceSoapProxy();
		EnvioCorreoInDTO inDTO = new EnvioCorreoInDTO();
		inDTO.setAsunto(asunto);
		ArrayOfString arrayDestino = new ArrayOfString();
		for (String iterator : destinos) {
			arrayDestino.getString().add(iterator);
		}
		inDTO.setPara(arrayDestino);
		inDTO.setContenido(contenido);
		inDTO.setDe(correoEmisor.get(0).getDireccion());
		inDTO.setLlave(llaveEnvio.getValorTexto());													
		inDTO.setTipoContenido(codContenido.getValorTexto());
		
		try {
			servicio.envioCorreo(inDTO);
		} catch (javax.xml.ws.soap.SOAPFaultException ex) {
			logger.error(ex.getMessage());
			
		}
		
	}

	@Override
	public CorreoElectronico traerPrimerCorreoPrimario(long idPersona) {
		return manejadorCorreoElectronico.traerPrimerCorreoPrimario(idPersona);
	}
	
	
	@Override
	public List<CorreoElectronicoDTO> consultarCorreosPersona(Long idPersona){
		return (List<CorreoElectronicoDTO>) transformarColeccionSinDependencias(manejadorCorreoElectronico.consultaCorreosPersona(idPersona), new ArrayList<CorreoElectronicoDTO>());
	}
	
	// protected region metodos adicionales end

}
