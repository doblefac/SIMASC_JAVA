package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.UsuarioClaveDTO;
import com.ccb.simasc.transversal.dto.UsuarioDTO;
import com.ccb.simasc.transversal.dto.UsuarioSistemaConsultaDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Usuario;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Usuario}
 * @author sMartinez
 *
 */
@Local
public interface IUsuarioFacade extends IAccesoFacade<Usuario, String, UsuarioDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	public List<UsuarioSistemaConsultaDTO> consultarUsuariosSistema(String nombres, String apellidos, String nombreRol, String nombreRolUsuario);
	/**
	 * Crea el usuario de una parte.
	 * @param idPersona
	 * @throws SIMASCNegocioExcepcion
	 */
	public void crearUsuarioParte(Long idPersona) throws SIMASCNegocioExcepcion;
	public Usuario crearUsuarioApersona(Long idPersona);
	/**
	 * Método que obtiene los centros a los cuales se encuentra asociado un usuario
	 * @param codigoUsuario
	 * @return
	 */
	public Collection<CentroDTO> obtenerCentrosActor(Long codigoUsuario);	// protected region metodos adicionales end
	
	/**
	 * ADM-C-021
	 * @param usuarioSistemaDTO
	 * @return
	 */
	public Long crearUsuarioSistema(FormularioParteDTO usuarioSistemaDTO);
	
	/**
	 * ADM-C-021
	 * @param tipoIdentificacion
	 * @param numeroIdentificacion
	 * @return
	 */
	public FormularioParteDTO consultarUsuarioSistema(String tipoIdentificacion, String numeroIdentificacion);
	

	
	

	/**
	 * Método que obtiene los dtaos básicos de un usuario del sistema por el
	 * identificador de persona
	 * 
	 * ADM-C-031
	 * 
	 * @param idPersona
	 * @return
	 */
	public FormularioParteDTO consultarUsuarioPorIdPersona(Long idPersona);
	
	/**
	 * Método que consulta los registros de la tabla usuario junto a sus claves por persona, 
	 * devuelve 1 si la persona es funcionario del CCB, 0 en caso contrario
	 * 
	 * ADM-C-052
	 * 
	 * @param idPersona
	 * @param usuario
	 * @return
	 */
	public List<UsuarioClaveDTO> consultarEstadoUsuario(Long idPersona);
	
	
	/**
	 * Método que actualiza el estado de un persona (activar/inactivar)
	 * 
	 * ADM-C-052
	 * 
	 * @param usuario
	 * @return
	 */
	public void cambiarEstadoUsuario(UsuarioClaveDTO usuario);
	
	/**
	 * Método que actualiza el estado de una clave 
	 * 
	 * ADM-C-052
	 * 
	 * @param usuario
	 * @return
	 */
	public void desbloquearClaveUsuario(UsuarioClaveDTO usuario);
	
	public String crearUsuarioParteSinEnvioCorreo(Long idPersona ,boolean aplicaMauc);
	
	public void crearUsuarioParteConNotificacion(Caso caso, List<RolPersonaCaso> rolPersonaCasoList);
	
	
	public boolean validarFuncionarioAplicaMauc(Persona persona);	
	
	// protected region metodos adicionales end
	
}
