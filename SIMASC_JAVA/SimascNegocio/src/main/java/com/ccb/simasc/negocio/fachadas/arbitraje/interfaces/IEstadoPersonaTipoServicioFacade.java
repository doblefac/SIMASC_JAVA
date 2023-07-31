package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.EstadoPersonaTipoServicioDTO;
import com.ccb.simasc.transversal.dto.HistoricoEstadoPersonaTipoServicioDTO;
import com.ccb.simasc.transversal.entidades.EstadoPersonaTipoServicio;
import com.ccb.simasc.transversal.entidades.EstadoPersonaTipoServicioPK;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link EstadoPersonaTipoServicio}
 * @author sMartinez
 *
 */
@Local
public interface IEstadoPersonaTipoServicioFacade extends IAccesoFacade<EstadoPersonaTipoServicio, EstadoPersonaTipoServicioPK, EstadoPersonaTipoServicioDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 *envia un correo al abogado cuando encuentra una parte que esta en inactivo litigando 
	 * @param idPersona
	 * @param tipoServicio
	 * @param estado
	 * @param idCaso
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public Boolean notificacionArbitroLitigante(Long idPersona, String tipoServicio, String estado, Long idCaso) throws SIMASCNegocioExcepcion;
	
	/**
	 * ADM-C-022
	 * Servicio que consulta el historial de estados del funcionario
	 * 
	 * @return
	 */
	public List<HistoricoEstadoPersonaTipoServicioDTO> consultarHistoricoEstados(Long idPersona);
	
	/**
	 * ADM-C-022
	 * Servicio que cambia el estado del funcionario actualizando el historial de estados del mismo.
	 * @param estadoPersona
	 * @return
	 */
	public void cambiarEstadoFuncionario(EstadoPersonaTipoServicio estadoPersona);

	/**
	 * ADM-F-051
	 * Servicio que consulta el estado persona tipo servicio.
	 * @param estadoPersona
	 * @return
	 */
	public List<EstadoPersonaTipoServicioDTO> consultarEstadoPersonaTipoServicio(Long idPersona, String tipoServicio,
    		String estado, Boolean estadoIgual);
	// protected region metodos adicionales end
	
}
