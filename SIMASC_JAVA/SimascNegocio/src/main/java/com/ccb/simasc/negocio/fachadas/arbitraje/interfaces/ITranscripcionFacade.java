package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.transversal.dto.AudienciaPendienteTranscripcionDTO;
import com.ccb.simasc.transversal.dto.DatosReasignacionTranscripcionDTO;
import com.ccb.simasc.transversal.dto.TranscripcionDTO;
import com.ccb.simasc.transversal.dto.TranscripcionPendienteDTO;
import com.ccb.simasc.transversal.entidades.Transcripcion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Transcripcion}
 * @author sMartinez
 *
 */
@Local
public interface ITranscripcionFacade extends IAccesoFacade<Transcripcion, Long, TranscripcionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * 
	 * 
	 * @param idCaso
	 * @param idAudiencia
	 * @return
	 */
	public List<TranscripcionPendienteDTO> consultarTranscripcionesPendientes(Long idCaso, Long idAudiencia);

	/**
	 * 
	 * 
	 * @param idUsuario
	 * @return
	 */
	public List<AudienciaPendienteTranscripcionDTO> casosAudienciasPendientesTranscripciones(String idUsuario);

	/**
	 * 
	 * 
	 * @param listAudienciaPendienteTranscripcionDTO
	 * @param idUsuario
	 * @return
	 */
	public List<AudienciaPendienteTranscripcionDTO> actualizarCasosAudienciasPendientesTranscripciones(
			List<AudienciaPendienteTranscripcionDTO> listAudienciaPendienteTranscripcionDTO, String idUsuario);

	/**
	 * Metodo que persiste una transcripcion pasada por parámetro
	 * 
	 * @param transcripcion
	 *            Transcripción a ser persistida
	 */
	public void crearTranscripcion(Transcripcion transcripcion, ContextoDeSesion sesion);

	/**
	 * Metodo que hace la carga de una transcripcion pasada por parámetro,
	 * implementa reglas de negocio
	 * 
	 * @param transcripcion
	 *            Transcripción a ser cargada
	 */
	public void cargarTranscripciones(List<Transcripcion> transcripcion, ContextoDeSesion sesion);
	
	/**
	 * Método encargado de realizar la reasignación completa de una
	 * transcripción
	 * 
	 * @param idTranscripcion
	 * @param idPersona
	 * @throws SIMASCNegocioExcepcion
	 */
	public void reasignarTranscripcionCompleta(TranscripcionDTO transcripcionDTO) throws SIMASCNegocioExcepcion;
	
	/**
	 * Método encargado de realizar la reasigmación parcial de una
	 * transcripción a una o mas personas
	 * 
	 * @param transcripciones
	 * @throws SIMASCNegocioExcepcion
	 */
	public void reasignarTranscripcionParcial(Long idTranscripcionOriginal,
			List<DatosReasignacionTranscripcionDTO> datosTranscripcionesNuevas) throws SIMASCNegocioExcepcion;

	public List<Transcripcion> consultarTranscripcionesPorDocumento(Long idDocumento);
	public void actualizarTranscripcion(Transcripcion transcripcion, String usuario);
	
	/**
	 * Crea la transcripción de un documento de audio
	 * 
	 * @param idDocumento
	 */
	public void crearTranscripcionDocumento(Long idDocumento);

	// protected region metodos adicionales end

}
