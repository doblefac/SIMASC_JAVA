package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.HistoricoExpedienteDTO;
import com.ccb.simasc.transversal.entidades.HistoricoExpediente;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link HistoricoExpediente}
 * @author sMartinez
 *
 */
@Local
public interface IHistoricoExpedienteFacade extends IAccesoFacade<HistoricoExpediente, Long, HistoricoExpedienteDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	public List<HistoricoExpedienteDTO> consultarHistoricoExpedientePorCaso(Long idCaso);
	
	public void registrarUbicacionFisicaExpediente(HistoricoExpedienteDTO expediente, String idPersonaModificacion);
	// protected region metodos adicionales end
	
}
