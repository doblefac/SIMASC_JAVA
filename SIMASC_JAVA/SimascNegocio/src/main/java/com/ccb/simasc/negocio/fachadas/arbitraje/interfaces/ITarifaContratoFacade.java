package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.TarifaContratoDTO;
import com.ccb.simasc.transversal.entidades.TarifaContrato;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link TarifaContrato}
 * @author sMartinez
 *
 */
@Local
public interface ITarifaContratoFacade extends IAccesoFacade<TarifaContrato, Long, TarifaContratoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Metodo para consultar las tarifas contrato
	 * @param tarifaContrato
	 * @return List<tarifaContratoDTO>
	 */
	public List<TarifaContratoDTO> consultarTarifasContrato(TarifaContratoDTO tarifaContrato);

	/**
	 * Metodo que crea las tarifas de contrato 
	 * @param tarifaContrato
	 */
	public void crearTarifasContrato(List<TarifaContrato> tarifaContrato);
	
	/**
	 * Metodo eliminar o actualizar las tarifa(s) de un contrato
	 * @param List<tarifaContratoDTO>
	 * @return void
	 */	
	public void actualizarTarifasContrato(List<TarifaContratoDTO> tarifas, String idUsuario);
	// protected region metodos adicionales end
	
}
