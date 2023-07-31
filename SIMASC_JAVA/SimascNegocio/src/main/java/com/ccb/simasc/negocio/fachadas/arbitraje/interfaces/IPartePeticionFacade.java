package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.PartePeticionDTO;
import com.ccb.simasc.transversal.dto.RolPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.PartePeticion;
import com.ccb.simasc.transversal.entidades.PartePeticionPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link PartePeticion}
 * @author sMartinez
 *
 */
@Local
public interface IPartePeticionFacade extends IAccesoFacade<PartePeticion, PartePeticionPK, PartePeticionDTO> {


	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * crea las parte por tipo de peticion
	 * @param partes
	 * @param idPeticion
	 */
	public 	void crearPartePeticionPorListaPartes(List<RolPersonaCasoDTO> partes, Long idPeticion , String tipo);


	// protected region metodos adicionales end
	
}
