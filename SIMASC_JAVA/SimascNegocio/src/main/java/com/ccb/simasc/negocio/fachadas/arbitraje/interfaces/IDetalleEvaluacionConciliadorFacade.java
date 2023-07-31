package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DetalleEvaluacionConciliadorDTO;
import com.ccb.simasc.transversal.entidades.DetalleEvaluacionConciliador;
import com.ccb.simasc.transversal.entidades.DetalleEvaluacionConciliadorPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link DetalleEvaluacionConciliador}
 * @author sMartinez
 *
 */
@Local
public interface IDetalleEvaluacionConciliadorFacade extends IAccesoFacade<DetalleEvaluacionConciliador, DetalleEvaluacionConciliadorPK, DetalleEvaluacionConciliadorDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Método para realizar u obtener los calculos de los totales de la evaluacion por cada criterio
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @param codigoCriterio
	 * @return
	 */
	public List<DetalleEvaluacionConciliador> calcularCriterio(Long idPersona, Date periodoDesde, Date periodoHasta,
			String codigoCriterio);
	
	/**
	 * Método para crear o actualizar el detalle de una evaluación de conciliador
	 * @param valor
	 * @param codigoCriterio
	 * @param codigoTotal
	 * @param idEvaluacion
	 * @param estadoRegistro
	 * @return
	 */
	public DetalleEvaluacionConciliador actualizarDetalleEvaluacion(BigDecimal valor, String codigoCriterio,
			String codigoTotal, Long idEvaluacion, String estadoRegistro);
	
	/**
	 * Método que realiza la consulta de los totales de cada criterio de la evaluacion de un conciliador
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @return
	 */
	public List<DetalleEvaluacionConciliador> consultarTotales(Long idPersona, Date periodoDesde, Date periodoHasta);
	// protected region metodos adicionales end
	
}
