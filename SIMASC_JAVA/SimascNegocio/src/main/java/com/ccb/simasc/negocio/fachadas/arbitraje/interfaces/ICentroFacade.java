package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.entidades.Centro;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Centro}
 * 
 * @author sMartinez
 *
 */
@Local
public interface ICentroFacade extends IAccesoFacade<Centro, Long, CentroDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Metodo encargado de obtener el o los Centros de acuerdo a los parametros
	 * o filtros enviados. Si no existe idServicio e idMateria traera todos los
	 * centros.
	 * 
	 * CON-F-124
	 * 
	 * @param idServicio
	 * @param idMateria
	 * @return List<CentroDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<CentroDTO> obtenerCentros(Long idServicio, Long idMateria);

	/**
	 * Método para actualizar los parámetros del centro ADM-C-009
	 * 
	 * @param parametrosCentro
	 * @param idUsuario
	 * @return
	 */
	public void actualizarParametrosCentro(List<CentroDTO> parametrosCentro, String idUsuario);

	/**
	 * Metodo encargado de obtener los centros activos.
	 * 
	 * @return List<CentroDTO>: Lista de centros.
	 */
	public List<CentroDTO> obtenerCentros();

	// protected region metodos adicionales end

}
