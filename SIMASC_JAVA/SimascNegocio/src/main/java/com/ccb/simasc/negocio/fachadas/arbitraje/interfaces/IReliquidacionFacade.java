package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DtoGenericoDTO;
import com.ccb.simasc.transversal.dto.ReliquidacionDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarLiquidacionDTO;
import com.ccb.simasc.transversal.entidades.Reliquidacion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Reliquidacion}
 * @author sMartinez
 *
 */
@Local
public interface IReliquidacionFacade extends IAccesoFacade<Reliquidacion, Long, ReliquidacionDTO> {


	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	

	
	
	/**
	 * calcula el valor de devolucion del caso, dependiendo del tipo de devolucion
	 * @param idCaso
	 * @param motivoReliquidacion
	 * @return DtoGenericoDTO (valorGenerico1 = valor devolucion. valorGenerico2= porcentaje devolucion)
	 */ 
	public DtoGenericoDTO calcularReliquidacionPorTipo(Long idCaso, String motivoReliquidacion);

	/**
	 * genera la reliquidacion de un caso cerrado por no competencia.
	 * @param idCaso
	 */
	public void generarReliquidacionTipoDevolucion(Long idCaso, String motivo);

	/**
	 * Método que realiza el proceso de registro de una reliquidacion de caso
	 * 
	 * @param datos
	 * @param idCaso
	 * @param motivo
	 * @param nuevaCuantia
	 */
	public void registroProcesoReliquidacion(FormularioGenerarLiquidacionDTO datos, Long idCaso, String motivo, String nuevaCuantia);
	// protected region metodos adicionales end
	
}
