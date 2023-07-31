package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ProfesionDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.entidades.Profesion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Profesion}
 * @author sMartinez
 *
 */
@Local
public interface IProfesionFacade extends IAccesoFacade<Profesion, Long, ProfesionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Se obtiene el id y el nombre de todas las profesiones
	 * @return List<GenericoDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<GenericoDTO> obtenerProfesiones() throws SIMASCNegocioExcepcion;
	// protected region metodos adicionales end
	
}
