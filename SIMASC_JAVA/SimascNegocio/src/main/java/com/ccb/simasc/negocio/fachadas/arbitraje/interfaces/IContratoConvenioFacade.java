package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ContratoConvenioDTO;
import com.ccb.simasc.transversal.entidades.ContratoConvenio;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link ContratoConvenio}
 * @author sMartinez
 *
 */
@Local
public interface IContratoConvenioFacade extends IAccesoFacade<ContratoConvenio, String, ContratoConvenioDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Metodo para crear un nuevo contrato de un convenio
	 * @param contrato
	 * @param idPersonaModificación
	 * @return void()
	 */
	public void crearContratoConvenio(ContratoConvenioDTO contrato, String idPersonaModificacion);
	
	
	/**
	 * Metodo para consultar un contrato por filtro
	 * @param contrato
	 * @return List<ContratoConvenioDTO>
	 */
	public List<ContratoConvenioDTO> consultarContratoConvenio(ContratoConvenioDTO contrato);
	
	/**
	 * Metodo para actualizar un contrato 
	 * @param contrato
	 * @return ContratoConvenioDTO
	 */
	public void actualizarContratoConvenio(ContratoConvenioDTO contrato, String idUsuario);
	// protected region metodos adicionales end
	
}
