package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.HonorariosFijadosDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasDTO;
import com.ccb.simasc.transversal.entidades.HonorariosFijados;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link HonorariosFijados}
 * @author sMartinez
 *
 */
@Local
public interface IHonorariosFijadosFacade extends IAccesoFacade<HonorariosFijados, Long, HonorariosFijadosDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Consulta los honorarios fijados para el caso
	 * @param idCaso
	 * @return
	 */
	public List<HonorariosFijadosDTO> consultarHonorariosFijados(Long idCaso);
	/**
	 * 
	 * @param parametrosTarifasDTO
	 * @return
	 */
	public List<HonorariosFijadosDTO> calcularTarifas(ParametrosTarifasDTO parametrosTarifasDTO);
	/**
	 * Guarda las tarifas calculadas
	 * 
	 * @param honorariosFijadosDTO
	 */
	public void guardarCalculoTarifas(HonorariosFijadosDTO honorariosFijadosDTO);
	
	
	// protected region metodos adicionales end
	
}
