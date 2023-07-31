package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorLlamada;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefono;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ILlamadaFacade;
import com.ccb.simasc.transversal.dto.LlamadaDTO;
import com.ccb.simasc.transversal.dto.LlamadaPlanillaCorrespondenciaDTO;
import com.ccb.simasc.transversal.dto.ReporteCorreosDevueltosDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.CorreoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Llamada;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Llamada}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class LlamadaFacade extends AccesoFacade<Llamada, Long, LlamadaDTO> implements ILlamadaFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorLlamada manejadorLlamada;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorCorreoRolPersonaCaso manejadorCorreoRolPersonaCaso;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorRol manejadorRol;
	
	@EJB
	private ManejadorTelefono manejadorTelefono;

	@EJB
	private CartaPersonaFacade cartaPersonaFacade;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorLlamada;
	}

	@Override
	public LlamadaDTO transformarSinDependencias(Llamada obj) {
		LlamadaDTO dto = new LlamadaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public LlamadaDTO transformarConDependencias(Llamada obj) {
		LlamadaDTO dto = new LlamadaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Llamada transformarEntidadConDependencias(Llamada obj) {
		Llamada dto = new Llamada();
		dto = new LlamadaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Llamada transformarEntidadSinDependencias(Llamada obj) {
		Llamada dto = new Llamada();
		dto = new LlamadaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	@Override
	public LlamadaDTO registrarLlamada(LlamadaDTO llamadaDTO, ContextoDeSesion contextoSesion) {
		LlamadaDTO llamadaDTO2 = new LlamadaDTO();
		try {
			Llamada llamada = new Llamada();

			if (llamadaDTO.getIdLlamada() != null)
				llamada.setIdLlamada(llamadaDTO.getIdLlamada());

			llamada.setTipoLlamada(UtilDominios.LLAMADA_CONFIRMACION_ASISTENCIA);
			llamada.setFecha(llamadaDTO.getFecha());
			llamada.setContactado(llamadaDTO.getContactado());

			if (llamadaDTO.getContactado()) {
				llamada.setPersonaQueContesta(
						llamadaDTO.getPersonaQueContesta() != null ? llamadaDTO.getPersonaQueContesta() : null);
				llamada.setObservaciones(llamadaDTO.getObservaciones() != null ? llamadaDTO.getObservaciones() : null);
				llamada.setConfirmacionAsistencia(llamadaDTO.getConfirmacionAsistencia());

				if (!llamadaDTO.isSoloRegistroLlamada()) {
					llamada.setReenvioCorreo(llamadaDTO.getReenvioCorreo());

					List<CorreoRolPersonaCaso> correoRolPersonaCasos = manejadorCorreoRolPersonaCaso.obtenerCorreos(
							UtilDominios.TIPO_ACUSE_FALLA, llamadaDTO.getIdCaso(), llamadaDTO.getIdAudiencia(),
							llamadaDTO.getIdPersona());

					for (CorreoRolPersonaCaso it1 : correoRolPersonaCasos) {
						it1.setGestionado(true);
						it1.setFechaUltimaModificacion(new Date());
						it1.setIdUsuarioModificacion(contextoSesion.getIdUsuario());
						manejadorCorreoRolPersonaCaso.actualizar(it1);

						if (llamadaDTO.getListCorreosActualizados() != null) {
							for (GenericoDTO it2 : llamadaDTO.getListCorreosActualizados()) {
								it1.getCartaPersona().setCorreoElectronico(it2.getNombre());
								cartaPersonaFacade.enviarCarta(it1.getCartaPersona(), false,
										llamadaDTO.getReenvioCorreo());
							}
						}
					}
				}
			}

			llamada.setConfirmacion(llamadaDTO.getConfirmacion());
			llamada.setIdCorreoRolPersonaCaso(
					llamadaDTO.getIdCorreoRolPersonaCaso() != null ? llamadaDTO.getIdCorreoRolPersonaCaso() : null);
			llamada.setIdUsuarioModificacion(contextoSesion.getIdUsuario());
			llamada.setFechaUltimaModificacion(new Date());
			llamada.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			llamada.setUsuarioLlamada(contextoSesion.getNombreUsuario());
			llamada.setIdRol(llamadaDTO.getIdRol() != null ? llamadaDTO.getIdRol() : null);
			llamada.setIdPersona(llamadaDTO.getIdPersona() != null ? llamadaDTO.getIdPersona() : null);
			llamada.setIdCaso(llamadaDTO.getIdCaso() != null ? llamadaDTO.getIdCaso() : null);
			llamada.setIdTelefono(llamadaDTO.getIdTelefono());
			llamada = manejadorLlamada.crearLlamada(llamada);
			llamadaDTO2 = this.transformarSinDependencias(llamada);
			
			Persona persona = manejadorPersona.buscar(llamada.getIdPersona());
			Rol rol = manejadorRol.buscar(llamada.getIdRol());
			Telefono telefono = manejadorTelefono.buscar(llamada.getIdTelefono());
			llamadaDTO2.setTelefonoDestino(telefono.getNumero());
			llamadaDTO2.setNombreRol(rol.getNombre());
			llamadaDTO2.setParteNoNotificada(persona.getNombreCompleto());

		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		} catch (Exception e) {
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		return llamadaDTO2;
	}

	@Override
	public List<LlamadaDTO> gestionCorreosDevueltos(Long idCaso, Date fechaInicial, Date fechaFinal) {
		List<LlamadaDTO> llamadaDTOs = new ArrayList<>();
		try {
			List<Llamada> llamadas = manejadorLlamada.consultarLlamadas(idCaso, fechaInicial, fechaFinal);
			for (Llamada it1 : llamadas) {
				Caso caso = manejadorCaso.buscar(it1.getIdCaso());
				Persona persona = manejadorPersona.buscar(it1.getIdPersona());
				Rol rol = manejadorRol.buscar(it1.getIdRol());
				LlamadaDTO llamadaDTO = new LlamadaDTO();
				llamadaDTO.setIdCaso(caso.getIdCaso());
				llamadaDTO.setTipoCaso(caso.getServicioMateria().getServicio().getNombre());
				llamadaDTO.setNombreCaso(caso.getNombre());
				llamadaDTO.setParteNoNotificada(persona.getNombreCompleto());
				llamadaDTO.setUsuarioLlamada(it1.getUsuarioLlamada());
				llamadaDTO.setFecha(it1.getFecha());
				llamadaDTO.setTelefonoDestino(it1.getTelefono().getNumero());
				llamadaDTO.setPersonaQueContesta(it1.getPersonaQueContesta());
				llamadaDTO.setObservaciones(it1.getObservaciones());
				llamadaDTO.setConfirmacion(it1.getConfirmacion());
				llamadaDTO.setNombreRol(rol.getNombre());
				llamadaDTO.setConfirmacionAsistencia(it1.getConfirmacionAsistencia());
				llamadaDTOs.add(llamadaDTO);
			}
		} catch (Exception e) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString());
			throw new SIMASCNegocioExcepcion(mensaje);
		}
		return llamadaDTOs;
	}
	
	@Override
	public void registrarLlamadaCorreo(LlamadaPlanillaCorrespondenciaDTO llamada, String usuario){
		
		Llamada llamadaCorreo = new Llamada();
		llamadaCorreo.setTipoLlamada(UtilDominios.LLAMADA_CARTA_NO_ENTREGADA);
		llamadaCorreo.setFechaUltimaModificacion(new Date());
		llamadaCorreo.setFecha(llamada.getFecha());
		llamadaCorreo.setContactado(false);
		llamadaCorreo.setPersonaQueContesta(llamada.getPersonaQueContesta());
		llamadaCorreo.setObservaciones(llamada.getObservaciones());
		llamadaCorreo.setConfirmacionAsistencia(llamada.isConfirmacionAsistencia());
		llamadaCorreo.setIdUsuarioModificacion(usuario);
		llamadaCorreo.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		llamadaCorreo.setUsuarioLlamada(usuario);
		llamadaCorreo.setIdTelefono(llamada.getIdTelefono());
		llamadaCorreo.setIdCartaPersona(llamada.getNumeroCarta());
		
		manejadorLlamada.crear(llamadaCorreo);
	}
	
	// protected region metodos adicionales end

	@Override
	public void registrarLlamadaSeguimiento(Llamada llamada, String idUsuario) {
		if (llamada != null) {
			llamada.setIdUsuarioModificacion(idUsuario);
			llamada.setTipoLlamada(UtilDominios.LLAMADA_SEGUIMIENTO_CASO);
			llamada.setFechaUltimaModificacion(new Date());
			llamada.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			llamada.setUsuarioLlamada(idUsuario);
			manejadorLlamada.crear(llamada);
		}		
	}
	
	@Override
	public List<ReporteCorreosDevueltosDTO> reporteCorreosDevueltos(Long idCaso, Date fechaInicial, Date FechaFinal){
		return manejadorLlamada.reporteCorreosDevueltos(idCaso, fechaInicial, FechaFinal);
	}
	
	
	
	
}
