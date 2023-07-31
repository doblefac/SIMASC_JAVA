package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ContestacionDemandaDTO;
import com.ccb.simasc.transversal.entidades.ContestacionDemanda;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;




// protected region imports interfaz fachada end

/**
 * Registra la contestacion de la demanda {@link ContestacionDemanda}
 * @author sMartinez
 *
 */
@Local
public interface IContestacionDemandaFacade extends IAccesoFacade<ContestacionDemanda, Long, ContestacionDemandaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * registra la contestacion de la demanda
	 * @param contestacionDemanda
	 * @throws SIMASCNegocioExcepcion
	 */
	public void registrarContestacion(ContestacionDemanda contestacionDemanda) throws SIMASCNegocioExcepcion;
	
	/*
	 * busca la contestacion demanda por un caso en particular
	 */
	public List<ContestacionDemanda>  consultarContestacionDemandaPorCaso(Long idCaso) throws SIMASCNegocioExcepcion;

	/**
	 * valida si es posible realizar la contestacion de demanda en los plazos estipulados
	 * @param idCaso
	 * @param nombreRol
	 * @return
	 */
	public Boolean validarFechaContestacion(Long idCaso, String nombreRol);
	
	
	/**
	 * valida si ya se relizo la contestacion de la demanda de reconvencion
	 * @param idCaso
	 * @param nombreRol
	 * @return
	 */
	public Boolean validarContestacionReconvencion(Long idCaso);
	
	/**
	 * registra la contestacion de la reconvencion
	 * @param contestacionDemanda
	 * @throws SIMASCNegocioExcepcion
	 */
	public void registrarReconvencion(ContestacionDemanda contestacionDemanda) throws SIMASCNegocioExcepcion;
	
	


	// protected region metodos adicionales end
	
}
