package com.ccb.simasc.web.controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
/**
 * Controlador de vista para pagina de error 
 * @author sMartinez
 *
 */
@ManagedBean( name = "controladorError")
@ViewScoped
public class ControladorError {
	
	public String toIndex() {
		  return "index?faces-redirect=true";
    }
}
