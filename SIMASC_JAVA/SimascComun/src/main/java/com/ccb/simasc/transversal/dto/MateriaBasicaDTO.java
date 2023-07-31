package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

// protected region imports dto end


/**
 * DAO que contiene la informacion básica de la entidad MateriaDTO que se transmite
 * por los servicios REST. 
 * 
 * @author jsoto
 */
@XmlRootElement
public class MateriaBasicaDTO implements Serializable{	

	private static final long serialVersionUID = -2121977002008537460L;
	
	private Long idMateria;
	private String nombre;		

	
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idMateria);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
 
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad MateriaDTO que se pasa
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
        final MateriaBasicaDTO other = (MateriaBasicaDTO) obj;
                
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        
       return Objects.equals(this.nombre, other.nombre); 
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
