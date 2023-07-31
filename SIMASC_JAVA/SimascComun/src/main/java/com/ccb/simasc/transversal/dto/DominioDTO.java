package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.DominioPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad DominioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class DominioDTO extends IDTO<Dominio> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	private static final long serialVersionUID = 8357035024479665750L;

	// protected region atributos end
	private DominioPK dominioPK;

	private String nombre;		
	private String descripcion;		
	private String codigoDomPadre;		
	private String dominioPadre;		
	
    public DominioDTO(){
		dominioPK = new DominioPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public DominioPK getDominioPK(){
		return this.dominioPK;
	}
	
	public void setDominioPK(DominioPK dominioPK){
		this.dominioPK   = dominioPK ;
	}  
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	public String getCodigoDomPadre(){
		return this.codigoDomPadre;
	}
	
	public void setCodigoDomPadre(String codigoDomPadre){
		this.codigoDomPadre = codigoDomPadre;
	}
		
	public String getDominioPadre(){
		return this.dominioPadre;
	}
	
	public void setDominioPadre(String dominioPadre){
		this.dominioPadre = dominioPadre;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.dominioPK);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.codigoDomPadre);
        hash = 37 * hash + Objects.hashCode(this.dominioPadre);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DominioDTO que se pasa
     * como parametro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relacion con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * iguales.
     * @return Verdadero si esta instancia y la que se pasan como parametros son
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DominioDTO other = (DominioDTO) obj;
                
        if (!Objects.equals(this.dominioPK, other.dominioPK)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.codigoDomPadre, other.codigoDomPadre)) {
            return false;
        }
        
        return Objects.equals(this.dominioPadre, other.dominioPadre);
                
    }
    
    @Override
	public DominioDTO transformarSinDependencias(Dominio obj) {
		DominioDTO dominioDTO = new DominioDTO();
		
		dominioDTO.setDominioPK(obj.getDominioPK());
		dominioDTO.setNombre(obj.getNombre());
		dominioDTO.setDescripcion(obj.getDescripcion());
		dominioDTO.setCodigoDomPadre(obj.getCodigoDomPadre());
		dominioDTO.setDominioPadre(obj.getDominioPadre());
		
		return dominioDTO;
	}

	@Override
	public DominioDTO transformarConDependencias(Dominio obj) {
		DominioDTO dominioDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return dominioDTO;
	}

	@Override
	public Dominio transformarEntidadSinDependencias(Dominio obj) {
		Dominio dominio = new Dominio();
		
		dominio.setDominioPK(obj.getDominioPK());
		
		dominio.setNombre(obj.getNombre());
		dominio.setDescripcion(obj.getDescripcion());
		dominio.setCodigoDomPadre(obj.getCodigoDomPadre());
		dominio.setDominioPadre(obj.getDominioPadre());
		
		return dominio;
	}


	@Override
	public Dominio transformarEntidadConDependencias(Dominio obj) {
		Dominio dominio = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return dominio;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Dominio> coleccion) {
		List<DominioDTO> dominioDTOList = new ArrayList<>();
		for(Dominio c : coleccion)
			dominioDTOList.add(transformarConDependencias(c));
		return dominioDTOList;
	}



	@Override
	public List<DominioDTO> transformarColeccionSinDependencias(Collection<Dominio> coleccion) {
		List<DominioDTO> dominioDTOList = new ArrayList<>();
		for(Dominio c : coleccion)
			dominioDTOList.add(transformarSinDependencias(c));
		return dominioDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Dominio> coleccion) {
		List<Dominio> dominioList = new ArrayList<>();
		for(Dominio c : coleccion)
			dominioList.add(transformarEntidadConDependencias(c));
		return dominioList;
	}



	@Override
	public List<Dominio> transformarColeccionEntidadesSinDependencias(Collection<Dominio> coleccion) {
		List<Dominio> dominioList = new ArrayList<>();
		for(Dominio c : coleccion)
			dominioList.add(transformarEntidadSinDependencias(c));
		return dominioList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	public static List<String> obtenerCodigosDominios(List<Dominio> dominios){
		List<String> codigos = new ArrayList<>();
		if(dominios!=null && !dominios.isEmpty()){
			for(Dominio dominio : dominios){
				codigos.add(dominio.getDominioPK().getCodigo());				
			}
		}		
		
		return codigos;
	}
	
	public static List<String> obtenerCodigosDominiosDTO(List<DominioDTO> dominios){
		List<String> codigos = new ArrayList<>();
		if(dominios!=null && !dominios.isEmpty()){
			for(DominioDTO dominio : dominios){
				codigos.add(dominio.getDominioPK().getCodigo());				
			}
		}		
		
		return codigos;
	}
	
	
	
	// protected region metodos adicionales end

}
