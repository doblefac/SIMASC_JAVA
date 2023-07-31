package com.ccb.simasc.negocio.transversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.entidades.Dominio;

/**
 *  
 * @author jsoto
 *
 */	
@Singleton
public class FachadaDominios {

	@EJB 
	private ManejadorDominio manejadorDominio;
	
	private List<Dominio> dominios;
	
	@PostConstruct
	public void init(){
	    dominios = manejadorDominio.consultar(null, null, null);
	}
	
	/**
	 * Vuelve a cargar los dominios desde base de datos
	 */
	public void recargarDominios(){
		dominios = manejadorDominio.consultar(null, null, null);
	}

	/**
	 * Retorna todos los dominios existentes en la base de datos desde la última carga.
	 * @return the dominios
	 */
	public List<Dominio> getDominios() {
		return dominios;
	}	
	
	/**
	 * Devuelve cada uno de los registros(Dominios) de un dominio especifico
	 * @param nombreDominio
	 * @return
	 */
	public List<Dominio> getDominios(String nombreDominio) {
		List<Dominio> dominiosNombre = new ArrayList<>();
		for(Dominio dominio : dominios){
			if(dominio.getDominioPK().getDominio().equals(nombreDominio)){
				dominiosNombre.add(dominio);
			}
		}
		
		return dominiosNombre;
	}	
	
	/**
	 * 
	 * @param nombreDominio
	 * @return
	 */
	public List<DominioDTO> getDominiosDTO(String nombreDominio) {
		List<DominioDTO> dominiosNombre = new ArrayList<>();
		for(Dominio dominio : dominios){
			if(dominio.getDominioPK().getDominio().equals(nombreDominio)){
				DominioDTO dominioDTO = new DominioDTO();
				dominioDTO.setNombre(dominio.getNombre());
				dominioDTO.setDescripcion(dominio.getDescripcion());
				dominioDTO.setDominioPK(dominio.getDominioPK());			
				dominiosNombre.add(dominioDTO);
			}
		}
		if (!dominiosNombre.isEmpty()) {
			Collections.sort(dominiosNombre, new Comparator<DominioDTO>() {
		        @Override
		        public int compare(DominioDTO m1, DominioDTO m2) {
		            return  m1.getDescripcion().compareTo(m2.getDescripcion());
		        }
		    });
		}
		return dominiosNombre;
	}	
	
	/**
	 * Devuelve el nombre del dominio
	 * @param dominio : Dominio al que pertenece el codigo
	 * @param codigo : Codigo de dominio
	 * @return
	 */
	public String getNombreDominio(String dominio,String codigo) {
		String nombreDominio = null;
		for(Dominio dominioObjeto : dominios){
			if(dominioObjeto.getDominioPK(). getDominio().equals(dominio) && dominioObjeto.getDominioPK().getCodigo().equals(codigo)){
				nombreDominio = dominioObjeto.getNombre();
				return nombreDominio;
			}
		}
		return nombreDominio;
	}	
	
	public List<DominioDTO> dominioPorServicio(Long idServicio){
		
		List<Dominio> tiposServicios = manejadorDominio.tipoSorteoPorServicio(idServicio);		
		List<DominioDTO> dominiosTiposServicios = new ArrayList<>();
		
		for(Dominio dominio : tiposServicios){
			DominioDTO dominioDTO = new DominioDTO();
			dominioDTO.setNombre(dominio.getNombre());
			dominioDTO.setDescripcion(dominio.getDescripcion());
			dominioDTO.setDominioPK(dominio.getDominioPK());			
			dominiosTiposServicios.add(dominioDTO);
		}
		return dominiosTiposServicios;
	}

}
