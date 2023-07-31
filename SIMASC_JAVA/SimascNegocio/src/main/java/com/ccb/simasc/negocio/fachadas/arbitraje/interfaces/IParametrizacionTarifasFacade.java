package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ParametrizacionTarifasDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasDTO;
import com.ccb.simasc.transversal.dto.formularios.ResultadosTarifaDTO;
import com.ccb.simasc.transversal.entidades.ParametrizacionTarifas;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link ParametrizacionTarifas}
 * @author sMartinez
 *
 */
@Local
public interface IParametrizacionTarifasFacade extends IAccesoFacade<ParametrizacionTarifas, Long, ParametrizacionTarifasDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Consulta la parametrizacion de tarifas
	 * @param parametrosTarifasDTO
	 * @return
	 */
	public ParametrizacionTarifasDTO consultarParametrizacion(ParametrosTarifasDTO parametrosTarifasDTO);
	
	/**
	 * Realizar el calculo de la tarifa del modulo publico 
	 * @param parametrosTarifasDTO
	 */
	public ResultadosTarifaDTO calcularTarifaPublico(ParametrosTarifasDTO parametrosTarifasDTO);
	
	/**
	 * Método para obtener los rangos de tarifas dependiendo del tipo de tarifa y la cantidad de arbitros
	 * @param parametrosTarifasDTO
	 * @param idServicio
	 */
	public List<ParametrizacionTarifasDTO> obtenerRangosTarifa(ParametrosTarifasDTO parametrosTarifasDTO);
	
	/**
	 * Método que realiza la actualizacion de una tarifa validando que no se presenten cruces entre las mismas
	 * @param tarifa
	 */
	public void actualizarParametrizacion(ParametrizacionTarifas tarifa);
	
	/**
	 * Método para realizar el borrado logico de un registro
	 * @param idParametrizacion
	 */
	public void eliminarParametrizacion(Long idParametrizacion);
	
	// protected region metodos adicionales end
	
}
