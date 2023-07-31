package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.Collection;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ParteDeLaRecusacionDTO;
import com.ccb.simasc.transversal.entidades.ParteDeLaRecusacion;
import com.ccb.simasc.transversal.entidades.ParteDeLaRecusacionPK;
import com.ccb.simasc.transversal.entidades.Persona;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link ParteDeLaRecusacion}
 * @author sMartinez
 *
 */
@Local
public interface IParteDeLaRecusacionFacade extends IAccesoFacade<ParteDeLaRecusacion, ParteDeLaRecusacionPK, ParteDeLaRecusacionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	ParteDeLaRecusacion crearParteDeRecusacion(Long idRolParte, Long idPersonaParte, Long idCasoParte, Long idRecusacion);

	Collection<Persona> consultarPartesRecusacion(Long idRecusacion);
	// protected region metodos adicionales end
	
}
