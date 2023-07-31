package com.ccb.simasc.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.ccb.simasc.negocio.transversal.FachadaDominios;
import com.ccb.simasc.transversal.entidades.Dominio;

@ManagedBean(name = "controladorDominios", eager=true)
@ApplicationScoped
public class ControladorDominios implements Serializable {
	
	@EJB
	private FachadaDominios fachadaDominios;
	
	private List<Dominio> dominios;
	
	private static final long serialVersionUID = 1L;		
	
	public ControladorDominios(){}
	
	@PostConstruct
	public void postConstruct(){
		//dominios = fachadaDominios.getDominios();
	}		
	
	/**
	 * Vuelve a cargar los dominios desde base de datos
	 */
	public void recargarDominios(){
		fachadaDominios.recargarDominios();
		dominios = fachadaDominios.getDominios();		
	}
	
	/**
	 * Devuelve cada uno de los registros(Dominios) de un dominio especifico
	 * @param nombreDominio
	 * @return
	 */
	public List<Dominio> getDominios(String nombreDominio) {
		return fachadaDominios.getDominios(nombreDominio);
	}	

}
