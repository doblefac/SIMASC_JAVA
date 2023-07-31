package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class TipoFuncionalidadPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="nombre")
    
    private String nombre;       
	@Basic(optional = false)
    @NotNull
    @Column(name="aplicacion")
    
    private String aplicacion;       

	public TipoFuncionalidadPK(){
		
	}

    public TipoFuncionalidadPK(String nombre, String aplicacion) {
		this.nombre = nombre;       
		this.aplicacion = aplicacion;       
    }

    
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
    
	public String getAplicacion(){
		return this.aplicacion;
	}
	
	public void setAplicacion(String aplicacion){
		this.aplicacion = aplicacion;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.aplicacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TipoFuncionalidadPK que se pasa
     * como parametro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relacion con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * @return Verdadero si esta instancia y la que se pasan como parametros son
     * iguales.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoFuncionalidadPK other = (TipoFuncionalidadPK) obj;
        
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        return Objects.equals(this.aplicacion, other.aplicacion);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("nombre");
		 cadena.append(this.nombre);
	 	cadena.append(", ");
         
	     cadena.append("aplicacion");
		 cadena.append(this.aplicacion);
         
     	return cadena.toString(); 
     }

} 
