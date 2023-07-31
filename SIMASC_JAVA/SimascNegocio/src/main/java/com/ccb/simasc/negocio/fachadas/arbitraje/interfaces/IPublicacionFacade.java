package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.PublicacionDTO;
import com.ccb.simasc.transversal.entidades.Publicacion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Publicacion}
 * @author sMartinez
 *
 */
@Local
public interface IPublicacionFacade extends IAccesoFacade<Publicacion, Long, PublicacionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public List<Publicacion> consultarPublicacionesPersona(Long idPersona);
	
	public void actualizarPublicacion(Publicacion publicacion);
	// protected region metodos adicionales end
	
}
