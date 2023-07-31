package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.AgrupamientoRolDTO;
import com.ccb.simasc.transversal.entidades.AgrupamientoRol;
import com.ccb.simasc.transversal.entidades.AgrupamientoRolPK;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link AgrupamientoRol}
 * @author sMartinez
 *
 */
@Local
public interface IAgrupamientoRolFacade extends IAccesoFacade<AgrupamientoRol, AgrupamientoRolPK, AgrupamientoRolDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * metodo encargado de regresar la lista de roles, de un servicio con un tipo agrupador
	 * @param idServicio
	 * @param tipoAgrupador
	 * @return roles de un servicio por tipo de agrupador
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<Rol> obtenerRolesPorServicioYTipoAgrupador(Long idServicio, String tipoAgrupador) throws SIMASCNegocioExcepcion;


	// protected region metodos adicionales end
	
}
