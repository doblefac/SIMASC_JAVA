package com.ccb.simasc.web.controladores;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.ccb.simasc.web.comun.BaseMBean;

/**
 * Controlador de vista menu principal de aplicación  
 * @author sMartinez
 *
 */
@ManagedBean( name = "controladorMenu")
@SessionScoped
public class ControladorMenu extends BaseMBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String url = "/arbitraje/contenedorReportes.xhtml";
	private Map<String, String> functionalitiesMap = new HashMap<>();
	private String breadMenu="reportes";
	/**
	 * Constructor por defecto 
	 */
	public ControladorMenu() {
	}   

	 @PostConstruct
	 public void init() {	
		 String urlBase = "/arbitraje/";
		 functionalitiesMap.put("reportes", urlBase+"contenedorReportes.xhtml");		 
		 if (!this.isBloqueoSorteo()) {
			 functionalitiesMap.put("1","/sorteo.xhtml");
			 functionalitiesMap.put("3","/sorteo.xhtml");
			 functionalitiesMap.put("8","/sorteo.xhtml");
			 functionalitiesMap.put("18","/sorteo.xhtml");
			 functionalitiesMap.put("19","/sorteo.xhtml");
		 }
	 }
	 
	 public void setUrl(){
		 String key = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");
		 this.breadMenu = key;
		 this.url = (functionalitiesMap.containsKey(key))?functionalitiesMap.get(key):"";
		 if(key.equals("1") || key.equals("3") || key.equals("8") || key.equals("18") || key.equals("19"))
			 RequestContext.getCurrentInstance().update("@all");
		 RequestContext.getCurrentInstance().update("breadcrumbForm");
	 }

	
	public String getUrl(){
		return url; 
	 }

	public String getBreadMenu() {
		return breadMenu;
	}

	public void setBreadMenu(String breadMenu) {
		this.breadMenu = breadMenu;
	}
	
	/**
	 * Indica si cuenta con permisos para realizar sorteo
	 * @return
	 */
	public boolean isBloqueoSorteo() {
		return !this.tienePermisosSorteo();
	}
	 
	 
}
