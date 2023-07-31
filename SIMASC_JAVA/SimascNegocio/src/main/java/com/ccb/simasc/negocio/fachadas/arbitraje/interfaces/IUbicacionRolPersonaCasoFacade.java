package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.UbicacionPersonaCasoDTO;
import com.ccb.simasc.transversal.dto.UbicacionRolPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.entidades.UbicacionRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.UbicacionRolPersonaCasoPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link UbicacionRolPersonaCaso}
 * @author sMartinez
 *
 */
@Local
public interface IUbicacionRolPersonaCasoFacade extends IAccesoFacade<UbicacionRolPersonaCaso, UbicacionRolPersonaCasoPK, UbicacionRolPersonaCasoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Consulta los Ubicacion asociadas a un Rol Presona Caso
	 * @param rolPersonaCaso
	 * @return
	 */
	public List<Ubicacion> consultarUbicacionesPorRolPersonaCaso(RolPersonaCasoPK rolPersonaCasoPk);
	
	/**
	 * Consulta los Roles Persona Caso asociados a una Ubicacion
	 * @param idCorreo
	 * @return
	 */
	public List<RolPersonaCaso> consultarRolesPersonaCasoPorUbicacion(Long idUbicacion);
	
	/**
	 * Crea un nuevo registro para UbicacionRolPersonaCaso a partir de las PKs de ambas entidades
	 * @param rolPersonaCasoPk
	 * @param ubicacion
	 */
	public UbicacionRolPersonaCaso crearUbicacionRolPersonaCaso(RolPersonaCaso rolPersonaCasoPk, Ubicacion ubicacion);
	
	/**
	 * Crear un nuevo registro para UbicacionRolPersonaCaso a partir de los datos asociados a la Ubicacion y la llave de Rol Persona Caso
	 * @param rolPersonaCasoPk
	 * @param direccion
	 * @param latitud
	 * @param longitud
	 * @param zonaGeografica
	 * @return
	 */
	public UbicacionRolPersonaCaso crearUbicacionRolPersonaCaso(RolPersonaCaso rolPersonaCasoPk, String direccion, BigDecimal latitud, BigDecimal longitud, String zonaGeografica);
	
	/**
	 * Asocia una Ubicación a un Rol Persona Caso
	 * 
	 * @param ubicacionPersonaCasoDTO
	 */
	public UbicacionDTO asociarUbicacionRolPersonaCaso(UbicacionPersonaCasoDTO ubicacionPersonaCasoDTO);
	
	/**
	 * Asocia una lista de Ubicaciones a un Rol Persona Caso 
	 * @param ubicaciones
	 * @param rolPersonaCaso
	 */
	public UbicacionDTO asociarUbicacionesRolPersonaCaso(List<UbicacionDTO> ubicaciones, RolPersonaCaso rolPersonaCaso);
	
	public List<UbicacionDTO> consultarUbicacionesRolPersonaCaso(UbicacionPersonaCasoDTO ubicacionPersonaCasoDTO);
	// protected region metodos adicionales end
	
}
