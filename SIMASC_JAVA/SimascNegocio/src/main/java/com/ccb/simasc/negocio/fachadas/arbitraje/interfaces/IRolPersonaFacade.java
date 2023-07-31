package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.ConsultaPrestadoresDTO;
import com.ccb.simasc.transversal.dto.RolPersonaDTO;
import com.ccb.simasc.transversal.dto.formularios.BusquedaRolesActivosDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltroConsultaPrestadoresDTO;
import com.ccb.simasc.transversal.dto.formularios.InfoPrestadorDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.SolicitudPrestador;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link RolPersona}
 * @author sMartinez
 *
 */
@Local
public interface IRolPersonaFacade extends IAccesoFacade<RolPersona, Long, RolPersonaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Obtiene las personas que pueden ser Ã¡rbitros en un Caso
	 * 
	 * @param roles
	 * @return List<RolPersona>
	 */
	public List<RolPersona> obtenerRolPersonasPorRoles(List<String> roles);
	
	/**
	 * Asignar un nuevo rol a un usuario
	 * @param rolPersona
	 * @param nombreRol
	 */
	public void asignarRol(RolPersona rolPersona, String nombreRol);
	
	/**
	 * Método para obtener los rol persona activos asociados a la persona
	 * @param idPersona
	 * @return
	 */
	public List<RolPersona> consultarRolPersona(Long idPersona);
	
	/**
	 * Método que obtiene los centros a los cuales se encuentra asociado un usuario
	 * @param codigoUsuario
	 * @return
	 */
	public Collection<CentroDTO> obtenerCentrosActor(Long codigoUsuario);
	
	/**
     * ADM-C-003 COnsulta lista de prestadores
     * Consulta los prestadores que cumplan los criterios de filtrado que se pasan como parámetro
     * @param filtro
     * @return
     */
	public  List<InfoPrestadorDTO> consultarListaPrestadores(FiltroConsultaPrestadoresDTO filtro);
	
	/**
	 * Retorna el registro de RolPersona basado en el id del rol y el id de la persona
	 * @param idRol
	 * @param idPersona
	 * @return
	 */
	public List<RolPersona> obtenerRolPersona(Long idRol, Long idPersona);
	
	
	/**
	 * Método que consulta las personas naturales con rol perito con sin asociacion a un persona juridica
	 * @param idPersonaJuridica
	 * @return
	 */
	public List<Persona> consultarPeritoSinAsociar(Long idPersonaJuridica);
	
	/**
	 * Metodo que retorna los conciliadores activos por estado
	 * @param estado
	 * @return
	 */
	public List<PersonaBasicaDTO>conciliadoresActivosPorEstado(BusquedaRolesActivosDTO datosConsulta);
	
	/**
	 * Consulta la persona basicas por rolPersona y lista de centros
	 * @param roles
	 * @param centro
	 * @param fechaVigencia
	 * @return
	 */
	public List<PersonaBasicaDTO> consultarPesonasBasicaPorRolPersonaCentro(List<String> roles, List<Long> centro,Date fechaVigencia);
	
	/**
	 * Transforma la lista de RolPersona de consultarRolPersonaPrestadorPersona del manejadorRolPersona a una lista RolPersona con dependencias
	 * @param idPersona
	 * @return List<RolPersonaDTO>
	 */
	public List<RolPersona> consultarRolPersonaPrestadorPersonaRolLista( Long idPersona, String nombreRol, String nombreLista,
																		 List<String> estadoEPTS, List<String> idCentros );
	
	/** CON-C-004
	 * Metodo que cambia a un List<RolPersona> a la lista A
	 * @param solicitudCambioListaDTO contiene el acta de aprobacion y la fecha del acta de aprobacion
	 * @param rolesPersona los roles persona que se van a cambiar de lista
	 */
	public void cambiarPrestadorDeLista( SolicitudPrestador solicitudCambioLista, List<RolPersona> rolesPersona );
	
	/**
	 * ADM-C-004
	 * @param filtros: nombreRol, nombrePrestador, idMateria, nombreLista, nombrePersonaJuridica
	 * @return List<ConsultaPrestadoresDTO>
	 */
	public List<ConsultaPrestadoresDTO> consultarPersonasPorRolMateria( ConsultaPrestadoresDTO filtros );
	
	/**
	 * ADM-F-061 Retorna los nombres de los roles y el idrolpersona vigentes
	 * @param  idPersona, idCentros
	 * @return List<RolpersonaDTO>
	 */
	public List<RolPersonaDTO> consultarRolPersonaVigentes( Long idPersona);
	
	public void finalizarRolArbitroSocial(Long idPersona);

	public void finalizarRolArbitroSocialCambioEstado(Long idPersona);
	
	public List<RolPersonaDTO> consultarRolPersonaPorServicio(Long idPersona, Long idServicio);
	// protected region metodos adicionales end
	
}
