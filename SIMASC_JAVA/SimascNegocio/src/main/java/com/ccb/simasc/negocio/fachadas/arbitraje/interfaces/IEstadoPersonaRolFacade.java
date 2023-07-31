package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.EstadoPersonaRolDTO;
import com.ccb.simasc.transversal.dto.HistoricoEstadoMotivoPersonaDTO;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRol;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRolPK;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link EstadoPersonaRol}
 * @author sMartinez
 *
 */
@Local
public interface IEstadoPersonaRolFacade extends IAccesoFacade<EstadoPersonaRol, EstadoPersonaRolPK, EstadoPersonaRolDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 *envia un correo al abogado cuando encuentra una parte que esta en inactivo litigando 
	 * @param idPersona
	 * @param idRol
	 * @param idMotivo
	 * @param idCaso
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public Boolean notificacionArbitroLitigante(Long idPersona, Long idRol, String idMotivo, Long idCaso) throws SIMASCNegocioExcepcion;
	
	/**
	 * ADM-C-022
	 * Servicio que consulta el historial de estados del funcionario
	 * 
	 * @return
	 */
	public List<HistoricoEstadoMotivoPersonaDTO> consultarHistoricoEstados(Long idPersona);
	
	/**
	 * ADM-C-022
	 * Servicio que cambia el estado del funcionario actualizando el historial de estados del mismo.
	 * @param estadoPersona
	 * @return
	 */
	public void cambiarEstadoFuncionario(EstadoPersonaRol estadoPersona);

	/**
	 * ADM-F-051
	 * Servicio que consulta el estado persona tipo servicio.
	 * @param estadoPersona
	 * @return
	 */
	public List<EstadoPersonaRolDTO> consultarEstadoPersonaRol(Long idPersona, Long idRol,
    		String idMotivo, Boolean idMotivoIgual);
	// protected region metodos adicionales end
	
	public List<DominioDTO> consultarMotivosEstadoPersonaRol(Long idRol);
	
}
