/**
 * 
 */
package com.ccb.simasc.web.comun;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.ccb.simasc.transversal.entidades.Funcionalidad;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

/**
 * Clase genérica con los comportamientos comunes de los Mangedbean de la aplicación.
 * @author lvalbuena
 *
 */
public class BaseMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Adiciona un objeto de sesion a la sesion WEB del usuario
	 * @param llave
	 * @param objeto
	 */
	public void adicionarVariableSesion(String llave, Object objeto) {	
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false);
	
//		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
//				.getExternalContext().getSession(true);
		if(session != null) {
			session.setAttribute(llave, objeto);
		}
	}
	
	/**
	 * Recupera un objeto de la sesión WEB del usuario
	 * @param llave
	 * @return
	 */
	public Object obtenerVariableSesion(String llave) {
		//Obtiene la sesión WEB
		FacesContext contextoFaces = FacesContext.getCurrentInstance();
        ExternalContext contextoExterno = contextoFaces.getExternalContext();
        HttpSession sesion = (HttpSession)contextoExterno.getSession(true);
        if( sesion != null ) {        	
        	return sesion.getAttribute(llave);
        }
        else {
        	return null;
        }      
	}
	
	/**
	 * Retorna el usuario activo del sistema
	 * @return Persona
	 */
	public Persona getSesion() {
		return (Persona)obtenerVariableSesion(
				UtilConstantes.PERSONA_SESSION_ATTRIBUTE_KEY);
	}
	
	/**
	 * Retorna el username de la sesion activa
	 * @return
	 */
	public String getUserNameSession() {
		return (String)this.obtenerVariableSesion(
				UtilConstantes.USERNAME_SESSION_ATTRIBUTE_KEY);
	}
	
	/**
	 * Obtiene la funcionalidades del usuario en session
	 * @return
	 */
	public List<Funcionalidad> obtenerFuncionalidadesSession() {
		List<Funcionalidad> funcionalidades = new ArrayList<Funcionalidad>();				
		Persona personaSession = this.getSesion();				
		if (personaSession.getFuncionalidadesList() != null 
				&& !personaSession.getFuncionalidadesList().isEmpty()) {
			funcionalidades = personaSession.getFuncionalidadesList();
		}
		return funcionalidades;
	}
	
	/**
	 * Valida si el usuario activo en session tiene permisos
	 * para realizar sorteo
	 * @return
	 */
	public boolean tienePermisosSorteo() {
		boolean permiso = false;
		for (Funcionalidad funcion : this.obtenerFuncionalidadesSession()) {
			if (UtilConstantes.FUNCIONALIDAD_SORTEO.equalsIgnoreCase(funcion.getNombre())) {
				permiso = true;
				break;
			}
		}
		return permiso;
	}
	
	/**
	 * Muestar el mensaje en pantalla.
	 * 
	 * @param tipoMensaje: E(error), W(advertencia) y I(información)
	 * @param mensaje
	 */
	public void adicionarMensaje(char tipoMensaje, String mensaje){
		switch (tipoMensaje) {
		case UtilConstantes.ERROR:
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", mensaje));
			break;
		case UtilConstantes.ADVERTENCIA:
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", mensaje));
			break;
		case UtilConstantes.INFO:
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", mensaje));
			break;
		default:
			break;
		}
	}
}
