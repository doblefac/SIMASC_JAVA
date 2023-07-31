package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.PeticionDTO;
import com.ccb.simasc.transversal.dto.PartePeticionDTO;
import com.ccb.simasc.transversal.dto.PeticionBasicaDTO;
import com.ccb.simasc.transversal.entidades.Peticion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Peticion}
 * @author sMartinez
 *
 */
@Local
public interface IPeticionFacade extends IAccesoFacade<Peticion, Long, PeticionDTO> {	

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * consulta las peticiones por caso y tipo peticion
	 * @param idCaso
	 * @param tipoPeticion
	 * @return
	 */
	public  List<PeticionBasicaDTO> peticionesPorCaso(Long idCaso, String tipoPeticion);
	
	
	/**
	 * registra la contestacion de la peticon
	 * @param peticion
	 */
	public void contestarPeticion(PeticionBasicaDTO peticion);
	
	
	/**
	 * Realiza la petición especial 
	 * @param idCaso
	 * @param idPersona
	 * @param idRol
	 * @param peticionEspecial
	 * @param idUsuario
	 * @return partePeticion
	 */
	public PartePeticionDTO realizarPeticionEspecial(Long idCaso, Long idPersona, Long idRol, PeticionDTO peticionEspecial,  String idUsuario);
	
	/**
	 * Elimina una peticion especial cuando el cargue del documento falla
	 * @param peticion
	 */
	public void eliminarPeticionEspecial(PartePeticionDTO peticion);
	
	
	// protected region metodos adicionales end
	
}
