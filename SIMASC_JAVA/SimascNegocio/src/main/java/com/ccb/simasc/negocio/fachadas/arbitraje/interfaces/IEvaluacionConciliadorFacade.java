package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.EvaluacionConciliadorDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.EvaluacionConciliador;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de
 * {@link EvaluacionConciliador}
 * 
 * @author sMartinez
 *
 */
@Local
public interface IEvaluacionConciliadorFacade
		extends IAccesoFacade<EvaluacionConciliador, Long, EvaluacionConciliadorDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * CON-C-004 transforma un List<EvaluacionConciliador> a un List
	 * <EvaluacionConciliador> con dependencias
	 * 
	 * @param idPersona
	 * @return List<EvaluacionConciliador>
	 */
	public List<EvaluacionConciliador> consultarEvaluacionConciliador(Long idPersona, Date periodoDesde,
			Date periodoHasta);

	/**
	 * Método que obtiene el listado de conciliadores que se encuentran
	 * habilitados para la evaluciación
	 * 
	 * @param centros
	 * @return
	 */
	public List<PersonaBasicaDTO> consultaConciliadoresEvaluar(List<Long> centros);

	/**
	 * Método para crear o actualizar una evaluación
	 * 
	 * @param idEvaluacion
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @return
	 */
	public EvaluacionConciliador actualizarEvaluacion(Long idEvaluacion, Long idPersona, Date periodoDesde,
			Date periodoHasta);

	/**
	 * Método que registra el total de la evaluacion del concilidador
	 * 
	 * @param idEvaluacion
	 * @param resultado
	 */
	public void registrarResultadoEvaluacion(Long idEvaluacion, Double resultado);

	/**
	 * Metodo que permite consultar los anios en los que se han registrado
	 * evaluaciones.
	 * 
	 * @return List<String>: lista de anios que corresponden a los anios que se
	 *         han registrado evaluaciones.
	 */
	public List<String> consultarAniosRegistroEvaluaciones();

	// protected region metodos adicionales end

}
