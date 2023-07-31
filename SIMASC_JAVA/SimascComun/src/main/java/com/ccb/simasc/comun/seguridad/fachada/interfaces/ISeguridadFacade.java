package com.ccb.simasc.comun.seguridad.fachada.interfaces;

import java.util.Calendar;
import java.util.List;

import javax.ejb.SessionContext;

import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.transversal.dto.AutenticacionUsuarioDTO;
import com.ccb.simasc.transversal.dto.FuncionalidadDTO;
import com.ccb.simasc.transversal.dto.ObjetoCifradoDTO;
import com.ccb.simasc.transversal.dto.UsuarioDTO;
import com.ccb.simasc.transversal.dto.UsuarioSesionDTO;
import com.ccb.simasc.transversal.entidades.Usuario;
import com.ccb.simasc.transversal.excepciones.SimascException;
/**
 * Interface general que provee los servicios de la fachada de seguridad. 
 * @author dbarrera
 *
 */
public interface ISeguridadFacade {
	/**
	 * @author fguzman
	 * @descripcion autentica el usuario
	 * @param String
	 *            nombreUsuario
	 * @param List<Object
	 *            parametrosValidacion
	 */
	public String autenticarUsuario(String nombreUsuario, List<Object> parametros, List<String> metodosAutenticacion)
			throws SimascException;
	
	/**
	 * Metodo que permite bloquear un usuario
	 * @param nombreUsuario pk del usuario
	 */
	public void bloquearUsuario(String nombreUsuario, String pwd);
	
	/**
	 * @author fguzman
	 * @descripcion Obtiene el contexto de la sesion
	 * @param UsuarioDTO
	 *            usuario
	 * @return contexto de sesion
	 */
	public SessionContext obtenerConetexto(UsuarioDTO usuario);
	
	/**
	 * @author fguzman
	 * @descripcion Revisa sobre las reglas de los usuarios si pueden ver la
	 *              funcionalidad
	 * @param UsuarioDTO
	 *            usuario
	 * @param funcionalidad
	 *            funcionalidad
	 * @return retorna falso si no tiene permisos y verdadero si cuenta con los
	 *         permisos
	 * 
	 */
	public boolean validarFuncionalidad(UsuarioDTO usuario, FuncionalidadDTO funcionalidad);
	
	/**
	 * @author fguzman
	 * @descripcion cierra la sesion
	 * @param usuario
	 */
	public void cerrarSesion(UsuarioDTO usuario);
	
	/**
	 * @author fguzman
	 * @descripcion Valida el token de la autenticación
	 * @param token
	 * @return la validacion del token
	 * 
	 */
	public boolean validarJWT(String token);
	
	/**
	 * Metodo que quita al usuario que le llega por parametro del mapa de contexto.
	 * @param nombreUsuario
	 */
	public void cerrarSesionUsuario(String nombreUsuario);
	
	/**
	 * Metodo que permite cambiar la contraseña de un usuario exixtente
	 * @param usuario
	 */
	public void cambioClave(UsuarioDTO usuario, String idModificador);
	
	/**
	 * Metodo que permite generar la contraseña de un usuario exixtente
	 * @param usuario
	 */
	public void generarClave(UsuarioDTO usuario, String idModificador);
	/**
	 * Metodo que permite generar la contraseña de un usuario exixtente sin enviar correo de notificacion
	 * @param usuario
	 */
	public String generarClaveSinEnvioCorreo(UsuarioDTO usuario, String idModificador);
	
	/**
	 * Metodo que descifra un objeto cifrado en AES
	 * @param objetoCifrado
	 */
	public String descifrarEncabezadoPeticion(ObjetoCifradoDTO objetoCifrado) throws SimascException;
	
	/**
	 * Metodo que permite recuperación de contraseña de un usuario exixtente
	 * @param usuario
	 */
	public void recuperarClave(String usuario, String correo);
	
	public void generarClaveSinUsuarioModificacion(UsuarioDTO usuario);
	
	/**
	 * ADM-C-021
	 * Genera la clave para el usuario que se pasa como parámetro
	 * @param usuario
	 */
	public void generarClaveUsuario(Usuario usuario);
	
	/**
	 * Método encargado de autenticar una persona en el sistema que intenta
	 * retomar una slicitud de servicio por número de documento y correo
	 * electrónico
	 * 
	 * TRANS-F-041
	 * 
	 * @param numeroDocumento
	 * @param correoElectronico
	 * @return
	 */
	public String autenticarPersonaRetomaSolicitud(String numeroDocumento, String correoElectronico);
	
	public void enviarCorreo(String correoR, Long idReceptor, String asunto,
			String cuerpoCorreo);
	

	public boolean usuarioAplicaMauc(String numeroDocumento);
	public String validaMetodoAutenticacionUsuario(AutenticacionUsuarioDTO autenticacionUsuarioDTO);
	
	public String autenticarUsuarioMauc(String tokenMauc);
	public UsuarioSesionDTO obtenerUsuario(ContextoDeSesion ctx);
	
	public String obtenerExpToken(String jwt);
}
