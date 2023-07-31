package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.GastoTribunalDTO;
import com.ccb.simasc.transversal.entidades.GastoTribunal;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link GastoTribunal}
 * @author sMartinez
 *
 */
@Local
public interface IGastoTribunalFacade extends IAccesoFacade<GastoTribunal, Long, GastoTribunalDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Crea o actualiza un gasto de tribunal
	 * 
	 * @param gastoTribunalDTO
	 * @param actualizar
	 */
	public void crearGastoTribunal(GastoTribunalDTO gastoTribunalDTO, boolean actualizar);

	/**
	 * Consulta los gasto de tribunal
	 * 
	 * @param idCaso
	 * @return
	 */
	public List<GastoTribunalDTO> consultarGastosTribunalCaso(Long idCaso);

	/**
	 * actualiza el estado del gasto a inactivo eliminandolo de la consulta
	 * @param idCaso
	 * @param idGasto
	 */
	public void eliminarGastoTribunal(Long idCaso, Long idGasto);
	/**
	 * Consulta los gastos totales
	 * @param idCaso
	 * @return
	 */
	public GastoTribunalDTO consultarTotales(Long idCaso);

	// protected region metodos adicionales end
	
}
