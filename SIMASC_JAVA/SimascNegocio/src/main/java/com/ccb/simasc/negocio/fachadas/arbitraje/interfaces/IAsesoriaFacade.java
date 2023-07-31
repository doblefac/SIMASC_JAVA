package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.AsesoriaDTO;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.formularios.NuevaAsesoriaDTO;
import com.ccb.simasc.transversal.entidades.Asesoria;
import com.ccb.simasc.transversal.entidades.Persona;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Asesoria}
 * @author sMartinez
 *
 */
@Local
public interface IAsesoriaFacade extends IAccesoFacade<Asesoria, Long, AsesoriaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Consulta las accesorias realizadas
	 * CON-C-1003
	 * @author LRUIZ
	 * @param filtros
	 * @return
	 */
	public List<AsesoriaDTO> consultaAsesorias(AsesoriaDTO filtros);
	
	/**
	 * Devuelve el listado de personas que han asesorado
	 * CON-C-1003
	 * @author LRUIZ
	 * @return
	 */
	public List<Persona> listadoAsesores(List<CentroDTO> centros);
	
	/**
	 * Crea la nueva asesoria
	 * CON-C-006
	 * @author LRUIZ
	 * @param asesoria
	 */
	public void nuevaAsesoria(NuevaAsesoriaDTO asesoria);

	// protected region metodos adicionales end
	
}
