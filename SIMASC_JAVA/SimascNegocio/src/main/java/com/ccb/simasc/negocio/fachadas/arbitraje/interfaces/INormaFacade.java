package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.NormaDTO;
import com.ccb.simasc.transversal.entidades.Norma;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Norma}
 * @author sMartinez
 *
 */
@Local
public interface INormaFacade extends IAccesoFacade<Norma, Long, NormaDTO> {

	// protected region metodos adicionales on begin

	/**
	 * consulta las nomras activas
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<Norma>  cosultarNormas(String estadoRegistro, String nombre) throws SIMASCNegocioExcepcion;
	
	
	/**
	 * elimina logicamente una norma por su id
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public void  eliminarNorma(Long idNorma) throws SIMASCNegocioExcepcion;
	
	/**
	 * Crea una norma 
	 * @param norma
	 * @throws SIMASCNegocioExcepcion
	 */
	public void  crearNorma(Norma norma) throws SIMASCNegocioExcepcion;
	
	

	// protected region metodos adicionales end
	
}
