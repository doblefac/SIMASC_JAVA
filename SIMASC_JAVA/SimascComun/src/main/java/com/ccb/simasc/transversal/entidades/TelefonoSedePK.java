package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class TelefonoSedePK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="numero")
    
    private String numero;       
	@Basic(optional = false)
    @NotNull
    @Column(name="tipo")
    
    private String tipo;       
	@Basic(optional = false)
    @NotNull
    @Column(name="extension")
    
    private String extension;       

	public TelefonoSedePK(){
		
	}

    public TelefonoSedePK(String numero, String tipo, String extension) {
		this.numero = numero;       
		this.tipo = tipo;       
		this.extension = extension;       
    }

    
	public String getNumero(){
		return this.numero;
	}
	
	public void setNumero(String numero){
		this.numero = numero;
	}
		
    
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
    
	public String getExtension(){
		return this.extension;
	}
	
	public void setExtension(String extension){
		this.extension = extension;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.numero);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.extension);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TelefonoSedePK que se pasa
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
        final TelefonoSedePK other = (TelefonoSedePK) obj;
        
        
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        return Objects.equals(this.extension, other.extension);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("numero");
		 cadena.append(this.numero);
	 	cadena.append(", ");
         
	     cadena.append("tipo");
		 cadena.append(this.tipo);
	 	cadena.append(", ");
         
	     cadena.append("extension");
		 cadena.append(this.extension);
         
     	return cadena.toString(); 
     }

} 
