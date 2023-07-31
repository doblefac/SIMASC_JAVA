package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.UbicacionDetalladaDTO;
import com.ccb.simasc.transversal.dto.UbicacionPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Ubicacion}
 * @author sMartinez
 *
 */
@Local
public interface IUbicacionFacade extends IAccesoFacade<Ubicacion, Long, UbicacionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	/**
	 * Metodo que realiza una consulta de las ubicaciones asociadas a una Persona
	 * @param idPersona
	 */
	public List<Ubicacion> consultarUbicacionesPorPersona(Long idPersona, Boolean incluirInactivos);
	
	/**
	 * Metodo encargado de persistir una direccion asociado a una persona
	 * @param ubicacionDTO
	 * @return 
	 * @throws SIMASCNegocioExcepcion
	 */
	public Long adicionarDireccion(UbicacionDTO ubicacionDTO) throws SIMASCNegocioExcepcion;
	
	/**
	 * Metodo encargado de eliminar logicamente una direccion asociado a una persona
	 * @param ubicacionDTO
	 * @return 
	 * @throws SIMASCNegocioExcepcion
	 */
	public void eliminarDireccion(Long idUbicacion) throws SIMASCNegocioExcepcion;
	
	/**
	 * Devuelve las ubicaciones detallas de una persona
	 * CON-C-006
	 * @param idPersona
	 * @return
	 */
	public List<UbicacionDetalladaDTO> ubicacionDetallaByIdpersona(long idPersona);
	
	/**
	 * Consulta la primera ubicacion de una persona
	 * CON-C-013
	 * @param idPersona
	 * @return
	 */
	public UbicacionDTO consultarPrimeraUbicacionPersona(Long idPersona);
	
	/**
	 * Consulta las direcciones principal y secundarias de una persona, según el
	 * caso al que pertenezca y rol que tenga en el mismo
	 * 
	 * @param ubicacionPersonaCasoDTO
	 * @return
	 */
	
	public List<UbicacionDTO> consutarDireccionesRolPersonaCaso(UbicacionPersonaCasoDTO ubicacionPersonaCasoDTO);
	
	/**
	 * Método que realiza la creación o actualizacion de una ubicacion principal
	 * @param idPersona
	 * @param ubicacion
	 * @return
	 */
	public Long gestionarDireccionPrincipal(Long idPersona, UbicacionDTO ubicacion);
	// protected region metodos adicionales end
	
	public List<UbicacionDTO> consutarDireccionesEnmascaradasRolPersonaCaso(UbicacionPersonaCasoDTO ubicacionPersonaCasoDTO);
	
	public List<UbicacionDTO> consultaDireccionesEnmascaradasPersona(Long idPersona);
	
}
