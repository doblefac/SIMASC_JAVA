package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class DominioPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="codigo")
    
    private String codigo;       
	@Basic(optional = false)
    @NotNull
    @Column(name="dominio")
    
    private String dominio;       

	public DominioPK(){
		
	}

    public DominioPK(String codigo, String dominio) {
		this.codigo = codigo;       
		this.dominio = dominio;       
    }
        
	public String getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
		
    
	public String getDominio(){
		return this.dominio;
	}
	
	public void setDominio(String dominio){
		this.dominio = dominio;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.codigo);
        hash = 37 * hash + Objects.hashCode(this.dominio);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DominioPK que se pasa
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
        final DominioPK other = (DominioPK) obj;
        
        
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        
        return Objects.equals(this.dominio, other.dominio);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("codigo");
		 cadena.append(this.codigo);
	 	cadena.append(", ");
         
	     cadena.append("dominio");
		 cadena.append(this.dominio);
         
     	return cadena.toString(); 
     }

} 
