package com.ccb.simasc.comun.seguridad.servicio.implementacion;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import com.ibm.websphere.security.auth.callback.WSCallbackHandlerImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.ccb.simasc.comun.seguridad.servicios.interfaces.IProveedorAutenticacion;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorUsuario;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.transversal.entidades.Clave;
import com.ccb.simasc.transversal.entidades.Usuario;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



/**
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ProveedorAutenticacionSIMASC implements IProveedorAutenticacion {
	
	public static final Integer VALIDAR_USUARIO = 0;
	public static final Integer VALIDAR_CONTRASEÑA = 1;
	public static final Integer VALIDAR_BLOQUEO_CONTRASEÑA = 2;
	public static final Integer RESULTADO_AUTENTIACIONES = 3;
	public static final Integer CONTRASEÑA_EXPIRADA = 4;
	public static final Integer USUARIO_INVALIDO = 5;
	/**
	 * Manejador que provee los servicios de un usuario
	 */
	@EJB
	private ManejadorUsuario manejadorUsuario;

	/**
	 * @author sMartinez
	 * @descripcion retorna una validación para validar si es posible autenticar
	 *              al usuario con el nombre y otros parametros
	 * @param nombreUsuario
	 * @param parametrosValidacion
	 * @return retorna falso si la autenticacion es invalida y verdadero si es
	 *         valida
	 */
	@Override
	public boolean[] autenticar(String nombreUsuario, List<Object> parametrosValidacion,
			List<String> metodosAutenticacion) {
		boolean[] ret = { false, false, false, false, false, false };
		ret[VALIDAR_USUARIO] = autenticarSIMASCNombreUsuario(nombreUsuario);
		ret[VALIDAR_CONTRASEÑA] = autenticarSIMASCKode(nombreUsuario, (String) parametrosValidacion.get(0));
		ret[VALIDAR_BLOQUEO_CONTRASEÑA] = autenticarSIMASCblockkode(nombreUsuario,
				(String) parametrosValidacion.get(0));
		
		// Validación de métodos de autenticación
		if (metodosAutenticacion.contains(UtilConstantes.METODO_AUTENTICACION_INTRANET)
				&& metodosAutenticacion.contains(UtilConstantes.METODO_AUTENTICACION_LDAP)) {
			ret[RESULTADO_AUTENTIACIONES] = autenticarSIMASC(nombreUsuario, (String) parametrosValidacion.get(0))
					&& (autenticarLDAP(nombreUsuario, (String) parametrosValidacion.get(1))
							&& autenticarLDAP(nombreUsuario, (String) parametrosValidacion.get(0)));
		} else if (metodosAutenticacion.contains(UtilConstantes.METODO_AUTENTICACION_INTRANET)
				&& !metodosAutenticacion.contains(UtilConstantes.METODO_AUTENTICACION_LDAP)) {
			ret[RESULTADO_AUTENTIACIONES] = autenticarSIMASC(nombreUsuario, (String) parametrosValidacion.get(0));
		} else if (!metodosAutenticacion.contains(UtilConstantes.METODO_AUTENTICACION_INTRANET)
				&& metodosAutenticacion.contains(UtilConstantes.METODO_AUTENTICACION_LDAP)) {
			ret[RESULTADO_AUTENTIACIONES] = (autenticarLDAP(nombreUsuario, (String) parametrosValidacion.get(1))
					&& autenticarLDAP(nombreUsuario, (String) parametrosValidacion.get(0)));
		} else {
			ret[RESULTADO_AUTENTIACIONES] = autenticarSIMASC(nombreUsuario, (String) parametrosValidacion.get(0));
		}
		
		ret[CONTRASEÑA_EXPIRADA] = isContraseñaExpirada(nombreUsuario, (String) parametrosValidacion.get(0));
		ret[USUARIO_INVALIDO] = usuarioInactivo(nombreUsuario);
		return ret;
	}
	
	/**
	 * Metodo que verifiaca su in ususrio esta inactivo 
	 * @param nombreUsuario
	 * @return true si el usuario esta activo false de lo contrario 
	 */
	private boolean usuarioInactivo(String nombreUsuario){
		Usuario us = manejadorUsuario.buscar(nombreUsuario);
		return  (us != null) && !UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(us.getEstado());
	}
	
	/**
	 * Metodo que verifica si una contraseña esta expirada 
	 * @param nombreUsuario
	 * @param clave
	 * @return
	 */
	private boolean isContraseñaExpirada( String nombreUsuario, String clave){
		Usuario us = manejadorUsuario.buscar(nombreUsuario);
		if(us != null){
			Date d = new Date();
			List<Clave> claveList = us.getClaveList();
			for(Clave c : claveList)
				if(c.getClavePK().getClave().compareTo(clave)==0)
					return d.after(c.getFechaVencimiento());
			
		}
		return false;
	}
	
	/**
	 * Metodo que verifiaca si una clave corresponde con alguna de las contraseñas del usuario
	 * @param nombreUsuario
	 * @param clave
	 * @return
	 */
	private boolean autenticarSIMASC( String nombreUsuario,String clave){
		Usuario us = manejadorUsuario.buscar(nombreUsuario);
		if(us != null){
			List<Clave> claveList = us.getClaveList();
			for(Clave c : claveList)
				if(c.getClavePK().getClave().compareTo(clave)==0 && UtilDominios.ESTADO_REGISTRO_CLAVE_ACTIVO.equals(c.getEstadoRegistro()))
					return c.getClaveBloqueada();
			
		}
		return true;
	}
	
	/**
	 * Metodo que autentica contra el LDAP
	 * @param nombreUsuario
	 * @param kode
	 * @return
	 */
	private boolean autenticarLDAP(String nombreUsuario,String kode){
		return authenticateCCBLDAP(nombreUsuario, kode);
	}
	
	/**
	 * Autentica y retorna un SecuredToken.
	 * 
	 * @param idUsuario
	 *            - codigo del usuario.
	 * @param pwd
	 *            Password del usuario.
	 * 
	 * @return SecuredToken con los datos de autenticación.
	 */
	private boolean authenticateCCBLDAP(String idUsuario, String pwd) {
		if (idUsuario != null && !idUsuario.trim().equals("") && pwd != null && !pwd.trim().equals("")) {
			LoginContext lc = null;
			String realm = UtilConstantes.REALM;
			String strLogin = UtilConstantes.LOGIN;
			javax.security.auth.Subject subject = null;
			try {
				// Autenticar basado en la seguridad de WAS
				lc = new LoginContext(strLogin, new WSCallbackHandlerImpl(idUsuario, realm, pwd));
				lc.login();
				subject = lc.getSubject();

			} catch (LoginException loginEx) {
				// Error de Login usuario, contraseña
				return true;
			} catch (Exception loginLdap) {
				// Error de contexto de Servidor
				return true;
			}
			return false;
		}
		return true;
	}
		
	
	/**
	 * Verigfica si el nombre del usuario es correcto
	 * @param nombreUsuario
	 * @return
	 */
	private boolean autenticarSIMASCNombreUsuario(String nombreUsuario){
		return !manejadorUsuario.consultar(filtro(TipoFiltro.EXACTO, Usuario.ENTIDAD_USUARIO_PK, nombreUsuario), null, null).isEmpty();
	}
	
	/**
	 * Verifica si la clave del usuario es correcta
	 * @param clave
	 * @return
	 */
	private boolean autenticarSIMASCKode( String nombreUsuario,String clave){
		return true;
	}
	
	/**
	 * Verifica si el usuario esta bloqueado
	 * @param nombreUsuario 
	 * @param clave
	 * @return
	 */
	private boolean autenticarSIMASCblockkode(String nombreUsuario, String clave){
		Usuario us = manejadorUsuario.buscar(nombreUsuario);
		if(us != null){
			List<Clave> claveList = us.getClaveList();
			for(Clave c : claveList)
				if(c.getClavePK().getClave().compareTo(clave)==0)
					return c.getClaveBloqueada();
			
		}
		return false;
	}
	
	/**
	 * Verifica si la clave del usuario valido
	 * @param tipo
	 * @param campo
	 * @param parametro
	 * @return
	 */
	private List<InformacionFiltro> filtro(TipoFiltro tipo, String campo, Object parametro){
		List<InformacionFiltro> filtros = new ArrayList<InformacionFiltro>();
		InformacionFiltro filtro2 = new InformacionFiltro(tipo, campo, parametro);
		filtros.add(filtro2);	
		return filtros;
	}
	
	/**
	 * @author sMartinez
	 * @descripcion retorna una validación para validar si es posible autenticar
	 *              con otros parametros
	 * @param parametrosValidacion
	 * @return retorna falso si la autenticacion es invalida y verdadero si es
	 *         valida
	 */
	@Override
	public boolean autenticar(List<Object> parametros) {
		return true;
	}

}
