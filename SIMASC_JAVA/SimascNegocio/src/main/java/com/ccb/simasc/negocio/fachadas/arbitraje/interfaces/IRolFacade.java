package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.DominioBasicoDTO;
import com.ccb.simasc.transversal.dto.RolBasicoDTO;
import com.ccb.simasc.transversal.dto.RolDTO;
import com.ccb.simasc.transversal.entidades.Rol;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Rol}
 * @author sMartinez
 *
 */
@Local
public interface IRolFacade extends IAccesoFacade<Rol, Long, RolDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	public List<Rol> obtenerRolesPorPersona(Long idPersona);
	public List<Rol> obtenerMapaFuncionalidades();
	public Rol obtenerRolPorNombre(String nombreRol);
	public List<RolBasicoDTO> consultarListaRolesParaConsultaUsuarios(String nombreRolUsuario);
	public List<DominioBasicoDTO> consultarRolesPorClasificador(String codigoClasificador);
	
	public String consultarGrupoUsuario(String nombreRolUsuario);
	
	/**
	 * Método que realiza la consulta de los roles por servicio
	 * @param idTipoServicio
	 * @return
	 */
	public List<RolDTO> consultarRolesPorServicio(String tipoServicio);
	
	/**
	 * Método para creara un nuevo rol y asignarle un tipo de servicio
	 * @param rolDTO
	 * @param tipoServicio
	 * @return el id del rol creado
	 */
	public Long crearRol(RolDTO rolDTO, String tipoServicio);
	
	/**
	 * ADM-C-021
	 * ADM-C-003
	 * Consulta los roles que definen lista, es decir, aquellos definidos en la tabla
	 * ParametroServicioSorteo con el indicador sorteo_con_lista en verdadero.
	 * @return
	 */
	public List<String> consultarNombreRolesQueManejanLista();
	
	/**
	 * ADM-C-003
	 * Consulta los roles que definen materia, es decir, aquellos definidos en la tabla
	 * ParametroServicioSorteo con el indicador sorteo_con_materia en verdadero.
	 * @return
	 */
	public List<String> consultarNombreRolesQueManejanMateria();
	
	/**
	 * Consulta los códigos de dominios de los roles agrupados por el agrupador que se pase como parámetro del grupo AGRUPADOR_ROL_PERSONA 
	 */
	public List<String> consultarCodigosRolesPorClasificador(String codigoClasificador);
	
	/**
	 * Verifica si una persona tiene el rol solicitante dentro de al menos una
	 * solicitud
	 * 
	 * @param idPersona
	 * @return
	 */
	public Rol obtenerRolPersonaSolicitante(Long idPersona);
	
	/**
	 * convierte un rol a rolBasicoDTO
	 * @param rol
	 * @return
	 */
	public RolBasicoDTO convertirRolARolBasico(Rol rol);
	
	/**
	 * Convertir lista de roles a lista de rolesBasicosDTO
	 * @param roles
	 * @return
	 */
	public List<RolBasicoDTO> convertirRolesARolBasico(List<Rol> roles);
	
	/**
	 * Consulta la lista basica por tipo de servicio 
	 * @param tipoServicio
	 * @param transversales si se desea traer los roles con tipo de servicio NULL
	 * @return
	 */
	public List<RolBasicoDTO> consutarRolesBasicosPorListaTipoServicio(List<String> tipoServicio, boolean transversales);
	
	
	/** ADM-C-019
	 * Retorna la lista de roles asociados a una persona
	 * @param idPersona
	 */
	public List<RolDTO> consultarRolesPorPersona(Long idPersona);
		/**
	 * ADM-C-022
	 * método que obtiene los servicios asociados a un rol dado en la tabla Parametro_servicio_sorteo
	 *  @param codigoRol
	 */
	List<Long> obtenerServiciosPorRolSorteo(Long codigoRol);
	/**
	 *  Retorna la lista de roles en parametro_servicio_sorteo
	 * 
	 * @param tipoServicio
	 * @param transversales
	 * @return
	 */
	List<RolDTO> consultarRolesSorteo();
	// protected region metodos adicionales end
	
	public void actualizarRol(Rol rol);
	
	
	public Rol consultarRolPorId(Long idRol);
	
}
