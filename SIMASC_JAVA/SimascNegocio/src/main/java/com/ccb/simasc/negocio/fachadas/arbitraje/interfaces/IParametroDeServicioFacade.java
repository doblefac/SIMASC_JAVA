package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.InformacionFiltroDTO;
import com.ccb.simasc.transversal.dto.ParametroDeServicioDTO;
import com.ccb.simasc.transversal.entidades.ParametroDeServicio;
import com.ccb.simasc.transversal.entidades.ParametroDeServicioPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link ParametroDeServicio}
 * @author sMartinez
 *
 */
@Local
public interface IParametroDeServicioFacade extends IAccesoFacade<ParametroDeServicio, ParametroDeServicioPK, ParametroDeServicioDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Metodo que consulta los ParametroServicioSorteo dada una lista de filtros 
	 * @param filtrosParametro
	 * @return List<ConvenioDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<ParametroDeServicioDTO> consultarParametroDeServicioFiltros(List<InformacionFiltroDTO> filtrosParametro);
	
	/**
	 * Método para consultar los parametros de servicio que pertenezcan al mismo tipo y servicio
	 * @param nombres
	 * @param idServicio
	 * @param tipoFuncionalidad
	 * @return
	 */
	public List<ParametroDeServicioDTO> consultarParametroDeServicio(List<String> nombres, Long idServicio, String tipoParametro);
	
	public List<ParametroDeServicioDTO> consultarParametroDeServicioTipo(String tipo);
	
	/**
	 * Método para actualizar los parámetros de servicio
	 * @param parametrosDeServicio
	 * @param idUsuario
	 * @return
	 */
	public void actualizarParametrosDeServicio(List<ParametroDeServicioDTO> parametrosDeServicio, String idUsuario);
	// protected region metodos adicionales end
	
	
	public ParametroDeServicioDTO consultarParametroDeServicioPorNombre(String nombre);
}
