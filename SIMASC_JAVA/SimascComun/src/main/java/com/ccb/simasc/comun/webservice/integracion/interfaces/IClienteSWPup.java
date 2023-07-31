package com.ccb.simasc.comun.webservice.integracion.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarLiquidacionDTO;
import com.ccb.simasc.transversal.dto.formularios.ProcesoReliquidacionDTO;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;

/**
 * 
 * @author fguzman
 *
 */
@Local
public interface IClienteSWPup {

	/**
	 * 
	 * @param datosLiquidacion
	 * @return
	 */
	public FormularioGenerarLiquidacionDTO generarLiquidacionPup(ProcesoReliquidacionDTO reliquidacion);
	/**
	 * 
	 * @param datosLiquidacion
	 * @return
	 */
	public String generarOrdenPagoPup(FormularioGenerarLiquidacionDTO datosLiquidacion, List<ParametrosGenerales> parametros, String numeroCliente);
}
