package com.ccb.simasc.web.controladores;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.seguridad.fachada.interfaces.ISeguridadFacade;
import com.ccb.simasc.negocio.transversal.UsuarioFachada;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Usuario;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.web.comun.BaseMBean;

/**
 * 
 * 
 * @author jcepeda
 *
 */
@ManagedBean(name = "autenticacion")
@SessionScoped
public class AutenticacionMB extends BaseMBean {
	private static final Logger logger = LogManager.getLogger(AutenticacionMB.class.getName());
	private static final long serialVersionUID = 1L;
	private static final String ENCRYPTION_ALGORITHM = "SHA-512";
	private Long username;
	private String password;
	private List<Object> parameters;
	private List<String> metodosAutenticacion;
	private String usuarioSesion;

	@EJB
	private ISeguridadFacade seguridadFacade;
	
	@EJB
	private UsuarioFachada usuarioFachada;

	/**
	 * Método encargado de autenticar (iniciar sesión) un usuario en el sistema
	 */
	public String autenticarUsuario() {
		String redirect = UtilConstantes.CADENA_VACIA;
		try {
			if (password == null)
				password = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
						.get("password");
			parameters = inicializarParametros(password);
			metodosAutenticacion = inicializarMetodosAutenticacion();
			String token = seguridadFacade.autenticarUsuario(String.valueOf(username), parameters,
					metodosAutenticacion);

			if (token != null && !token.isEmpty() && !UtilConstantes.EMPTY_RESPONSE.equals(token)) {
				Usuario usuario = usuarioFachada.obtenerUsuario(String.valueOf(username));
				Persona persona = usuarioFachada.obtenerPersona(usuario.getIdPersona());
				this.adicionarVariableSesion(UtilConstantes.PERSONA_SESSION_ATTRIBUTE_KEY, persona);
				this.adicionarVariableSesion(UtilConstantes.USERNAME_SESSION_ATTRIBUTE_KEY, String.valueOf(username));

				redirect = UtilConstantes.PAGINA_BIENVENIDA;
			} else {
				redirect = UtilConstantes.CADENA_VACIA;
			}
		} catch(Exception ex) {
			adicionarMensaje(UtilConstantes.ERROR, ex.getCause().getLocalizedMessage());	
		}
		return redirect;
	}

	/**
	 * Método encargado de finalizar la sesión de un usuario en el sistema
	 */
	public void finalizarSesion() {
		// Obtiene las variables de contexto
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Object session = externalContext.getSession(false);

		// Intenta eliminar las cookies de sesión
		Cookie[] cookies = ((HttpServletRequest) externalContext.getRequest()).getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie != null && cookie.getName().equalsIgnoreCase("JSESSIONID")) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					cookie.setValue("");
					((HttpServletResponse) externalContext.getResponse()).addCookie(cookie);
				}
			}
		}

		// Redirecciona a la página de login
		try {
			externalContext.redirect(
					((HttpServletRequest) externalContext.getRequest()).getContextPath() + "/faces/login.xhtml");
		} catch (Exception e) {
			logger.error("Error: ", e);
		}

		// Cierra la sesión del usuario e invalida la sesión http
		HttpSession httpSession = (HttpSession) session;
//		Long nombreUsuario = (Long) httpSession.getAttribute(UtilConstantes.USERNAME_SESSION_ATTRIBUTE_KEY);		
		String nombreUsuario = (String)this.obtenerVariableSesion(
				UtilConstantes.USERNAME_SESSION_ATTRIBUTE_KEY);
		
		if (nombreUsuario != null) {
			seguridadFacade.cerrarSesionUsuario(nombreUsuario);
		}

		httpSession.invalidate();

		// Indica que se ha completado la respuesta
		facesContext.responseComplete();
	}

	/**
	 * Método encargado de inicializar los parametros de autenticación de un
	 * usuario
	 * 
	 * @param password
	 * @return
	 */
	public List<Object> inicializarParametros(String password) {
		List<Object> parametros = new ArrayList<Object>();

		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
			byte[] passwordDigested = messageDigest.digest(password.getBytes());
			byte[] passwordEncoded = new String(Hex.encodeHex(passwordDigested)).getBytes();
			final char[] passwordDecripted = (new String(passwordEncoded, StandardCharsets.UTF_8)).toCharArray();
			
			parametros = (new ArrayList<Object>(Arrays.asList(String.valueOf(passwordDecripted),password,false)));
			
			Arrays.fill(passwordDecripted, ' ');
			
		} catch (NoSuchAlgorithmException e) {
			logger.error("Error: ", e);
		}
		return parametros;
	}

	/**
	 * Método encargado de inicializar los métodos de autenticación necesarios
	 * para el usuario
	 * 
	 * @return
	 */
	public List<String> inicializarMetodosAutenticacion() {
		List<String> metodosAutenticacion = new ArrayList<String>();

		// Adición de los métodos de autenticación necesarios
		metodosAutenticacion.add(UtilConstantes.METODO_AUTENTICACION_LDAP);

		return metodosAutenticacion;
	}

	// Métodos accesores a los atributos de clase

	public Long getUsername() {
		return username;
	}

	public void setUsername(Long username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}

	public List<String> getMetodosAutenticacion() {
		return metodosAutenticacion;
	}

	public void setMetodosAutenticacion(List<String> metodosAutenticacion) {
		this.metodosAutenticacion = metodosAutenticacion;
	}

	/**
	 * @return the usuarioSesion
	 */
	public String getUsuarioSesion() {		
		Persona persona = this.getSesion();
		usuarioSesion = persona.getNombreCompleto();		
		return usuarioSesion;
	}

	/**
	 * @param usuarioSesion the usuarioSesion to set
	 */
	public void setUsuarioSesion(String usuarioSesion) {
		this.usuarioSesion = usuarioSesion;
	}
	
	/**
	 * Retorna la version de la aplicacion back
	 * @return
	 */
	public String getVersion() {
		return UtilConstantes.VERSION;
	}

}
