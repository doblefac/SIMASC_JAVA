package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.PersonaServicioMateriaDTO;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link PersonaServicioMateria}
 * @author sMartinez
 *
 */
@Local
public interface IPersonaServicioMateriaFacade extends IAccesoFacade<PersonaServicioMateria, Long, PersonaServicioMateriaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	public void crearMateriaServicio(PersonaServicioMateria materia);
	// protected region metodos adicionales end
	
}
