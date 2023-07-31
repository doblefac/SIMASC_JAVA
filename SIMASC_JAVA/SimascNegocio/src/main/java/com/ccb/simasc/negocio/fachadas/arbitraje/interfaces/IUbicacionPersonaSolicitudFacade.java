package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.UbicacionPersonaSolicitudDTO;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.PersonaSolicitudPK;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.entidades.UbicacionPersonaSolicitud;
import com.ccb.simasc.transversal.entidades.UbicacionPersonaSolicitudPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link UbicacionPersonaSolicitud}
 * @author sMartinez
 *
 */
@Local
public interface IUbicacionPersonaSolicitudFacade extends IAccesoFacade<UbicacionPersonaSolicitud, UbicacionPersonaSolicitudPK, UbicacionPersonaSolicitudDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Consulta los Ubicacion asociadas a una Persona Solicitud
	 * @param personaSolicitudPk
	 * @return
	 */
	public List<Ubicacion> consultarUbicacionesPorPersonaSolicitud(PersonaSolicitudPK personaSolicitud);
	
	/**
	 * Consulta las Personas Solicitud asociadas a una Ubicacion
	 * @param idCorreo
	 * @return
	 */
	public List<PersonaSolicitud> consultarPersonasSolicitudPorUbicacion(Long idUbicacion);
	
	/**
	 * Crea un nuevo registro para UbicacionPersonaSolicitud a partir de las PKs de ambas entidades
	 * @param personaSolicitud
	 * @param ubicacion
	 */
	public UbicacionPersonaSolicitud crearUbicacionPersonaSolicitud(PersonaSolicitud personaSolicitud, Ubicacion ubicacion);
	
	/**
	 * Crear un nuevo registro para UbicacionPersonaSolicitud a partir de los datos asociados a la Ubicacion y la llave de Persona Solicitud
	 * @param personaSolicitud
	 * @param direccion
	 * @param latitud
	 * @param longitud
	 * @param zonaGeografica
	 * @return
	 */
	public UbicacionPersonaSolicitud crearUbicacionPersonaSolicitud(PersonaSolicitud personaSolicitud, UbicacionDTO ubicacionDTO);
	
	/**
	 * Asocia una lista de Ubicaciones a una PersonaSolicitud
	 * @param ubicaciones
	 * @param personaSolicitud
	 */
	public UbicacionDTO asociarUbicacionesPersonaSolicitud(List<UbicacionDTO> ubicaciones, PersonaSolicitud personaSolicitud);
	
	// protected region metodos adicionales end
	
}
