package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin

import java.util.List;

// Escriba en esta seccion sus modificaciones

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ResultadoAudienciaDTO;
import com.ccb.simasc.transversal.dto.ResultadoCasoDTO;
import com.ccb.simasc.transversal.entidades.ResultadoAudiencia;
import com.ccb.simasc.transversal.excepciones.EstadosCasoException;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de
 * {@link ResultadoAudiencia}
 * 
 * @author sMartinez
 *
 */
@Local
public interface IResultadoAudienciaFacade extends IAccesoFacade<ResultadoAudiencia, Long, ResultadoAudienciaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Metodo que permite registrar el resultado de la audiencia de la audiencia
	 * realizada (CON-F-110).
	 * 
	 * @author aperez.
	 * 
	 * @param resultadoDTO:
	 *            DTO que contiene la informacion de resultadoAudiencia.
	 */
	public void registrarResultadoAudiencia(ResultadoAudienciaDTO resultadoDTO, String idPersonaModificacion);

	/**
	 * Metodo que permite modificar el resultado de la audiencia realizada
	 * (CON-F-110).
	 * 
	 * @param resultadoDTO:
	 *            DTO que contiene la informacion de resultadoAudiencia.
	 */
	public void modificarResultadoAudiencia(ResultadoAudienciaDTO resultadoDTO, String idPersonaModificacion);

	/**
	 * Metodo que permite consultar los resultados de una audiencia, con la
	 * informacion de las partes involucradas y el valor total acuerdo.
	 * 
	 * @param idAudiencia:
	 *            Identificador de la audiencia.
	 * @return List<ResultadoAudienciaDTO>.
	 */
	public List<ResultadoAudienciaDTO> consultarResultadosAudiencia(Long idAudiencia);

	/**
	 * Metodo que permite actualizar el documento y el estado del resultado de
	 * la audiencia a por validar. Tambien permite hacer el llamado a reparto
	 * para asignar el analista de conciliacion que realizara el control de
	 * legalidad del caso.
	 * 
	 * @author aperez.
	 * 
	 * @param idDocumento:
	 *            Identificador del documento.
	 * @param idResultadoAudiencia:
	 *            Identificador del resultado de la audiencia.
	 */
	public void actualizarDocumentoResultadoAudiencia(Long idDocumento, Long idResultadoAudiencia,
			String idUsuarioModificacion, Long idServicio) throws EstadosCasoException;

	/**
	 * Metodo que permite consultar los resultados de audiencia de un caso.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @return List<ResultadoCasoDTO> : Lista de resultados del caso.
	 */
	public List<ResultadoCasoDTO> consultarResultadosAudienciasCaso(Long idCaso);

	/**
	 * Metodo que permite actualizar el estado del resultado de la audiencia.
	 * 
	 * @param idResultadoAudiencia:
	 *            Identificador del resultado de la audiencia.
	 * @param estadoResultado:
	 *            Estado del resultado de audiencia.
	 */
	public void actualizarEstadoResultado(String estadoResultado, Long idResultadoAudiencia, String usuarioModificacion);

	void eliminarResultadoAudiencia(Long idResultadoAudiencia);

	// protected region metodos adicionales end

}
