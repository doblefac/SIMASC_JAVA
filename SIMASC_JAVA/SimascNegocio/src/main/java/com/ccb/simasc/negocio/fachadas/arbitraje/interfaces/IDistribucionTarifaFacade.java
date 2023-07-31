package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DistribucionTarifaDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasDTO;
import com.ccb.simasc.transversal.entidades.DistribucionTarifa;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link DistribucionTarifa}
 * @author sMartinez
 *
 */
@Local
public interface IDistribucionTarifaFacade extends IAccesoFacade<DistribucionTarifa, Long, DistribucionTarifaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Consulta los porcentajes de los actores arbitro, secretario y CAC
	 * @param parametrosTarifasDTO
	 * @return
	 */
	public List<DistribucionTarifaDTO> consultarDistribucion(ParametrosTarifasDTO parametrosTarifasDTO);
	
	/**
	 * Actualizar las distribuciones dadas
	 * @param distribuciones
	 */
	public void actualizarDisitribucionTarifas(List<DistribucionTarifa> distribuciones);
	
	// protected region metodos adicionales end
	
}
