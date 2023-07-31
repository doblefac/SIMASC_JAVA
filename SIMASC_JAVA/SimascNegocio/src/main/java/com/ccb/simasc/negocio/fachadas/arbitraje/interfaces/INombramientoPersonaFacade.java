package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.HashMap;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.NombramientoPersonaDTO;
import com.ccb.simasc.transversal.entidades.NombramientoPersona;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link NombramientoPersona}
 * @author sMartinez
 *
 */
@Local
public interface INombramientoPersonaFacade extends IAccesoFacade<NombramientoPersona, Long, NombramientoPersonaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public int obtenerNumeroArbitrosPactados(Long idCaso,String metodoNombramiento,String tipoDesignacion);
	public int obtenerNumeroArbitrosPorNombrar(Long idCaso,String metodoNombramiento,String tipoDesignacion, Long idTercero);
	public NombramientoPersona crearNombramiento(NombramientoPersona nPersona);
	
	/**
	 * obtiene el pronunciamento de un tercero en especial o autoridad judicial
	 * @param idCaso
	 * @param metodoNombramiento
	 * @param idTerceroAutoridad
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	HashMap<String, Object> obtenerMensajePacto(Long idCaso, String metodoNombramiento, Long idTerceroAutoridad)
			throws SIMASCNegocioExcepcion;
	
	// protected region metodos adicionales end

	
}
