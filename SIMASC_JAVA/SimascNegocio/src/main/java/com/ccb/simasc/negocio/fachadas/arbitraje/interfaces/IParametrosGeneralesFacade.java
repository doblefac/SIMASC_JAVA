package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ParametrosGeneralesDTO;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link ParametrosGenerales}
 * @author sMartinez
 *
 */
@Local
public interface IParametrosGeneralesFacade extends IAccesoFacade<ParametrosGenerales, String, ParametrosGeneralesDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Consulta los parametros generales por codigo
	 * @param codigo
	 * @return
	 */
	public ParametrosGenerales consultarPorCodigo(String codigo);

	public List<ParametrosGenerales> consultarPorTipo(String tipo);

	public ParametrosGenerales consultarPorNombre(String nombre);

	/**
	 * Actualiza los parametros generales
	 * @param parametrosGenerales
	 * @return void
	 */
	public void actualizarParametrosGenerales(List<ParametrosGenerales> parametrosGenerales, String idUsuario);

	// protected region metodos adicionales end
	
}
