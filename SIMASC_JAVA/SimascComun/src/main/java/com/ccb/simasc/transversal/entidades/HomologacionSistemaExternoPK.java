package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class HomologacionSistemaExternoPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="codigo_simasc")
    
    private String codigoSimasc;       
	@Basic(optional = false)
    @NotNull
    @Column(name="dominio_simasc")
    
    private String dominioSimasc;       
	@Basic(optional = false)
    @NotNull
    @Column(name="codigo_sistema_externo")
    
    private String codigoSistemaExterno;       
	@Basic(optional = false)
    @NotNull
    @Column(name="sistema_externo")
    
    private String sistemaExterno;       

	public HomologacionSistemaExternoPK(){
		
	}

    public HomologacionSistemaExternoPK(String codigoSimasc, String dominioSimasc, String codigoSistemaExterno, String sistemaExterno) {
		this.codigoSimasc = codigoSimasc;       
		this.dominioSimasc = dominioSimasc;       
		this.codigoSistemaExterno = codigoSistemaExterno;       
		this.sistemaExterno = sistemaExterno;       
    }

    
	public String getCodigoSimasc(){
		return this.codigoSimasc;
	}
	
	public void setCodigoSimasc(String codigoSimasc){
		this.codigoSimasc = codigoSimasc;
	}
		
    
	public String getDominioSimasc(){
		return this.dominioSimasc;
	}
	
	public void setDominioSimasc(String dominioSimasc){
		this.dominioSimasc = dominioSimasc;
	}
		
    
	public String getCodigoSistemaExterno(){
		return this.codigoSistemaExterno;
	}
	
	public void setCodigoSistemaExterno(String codigoSistemaExterno){
		this.codigoSistemaExterno = codigoSistemaExterno;
	}
		
    
	public String getSistemaExterno(){
		return this.sistemaExterno;
	}
	
	public void setSistemaExterno(String sistemaExterno){
		this.sistemaExterno = sistemaExterno;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.codigoSimasc);
        hash = 37 * hash + Objects.hashCode(this.dominioSimasc);
        hash = 37 * hash + Objects.hashCode(this.codigoSistemaExterno);
        hash = 37 * hash + Objects.hashCode(this.sistemaExterno);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad HomologacionSistemaExternoPK que se pasa
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
        final HomologacionSistemaExternoPK other = (HomologacionSistemaExternoPK) obj;
        
        
        if (!Objects.equals(this.codigoSimasc, other.codigoSimasc)) {
            return false;
        }
        
        if (!Objects.equals(this.dominioSimasc, other.dominioSimasc)) {
            return false;
        }
        
        if (!Objects.equals(this.codigoSistemaExterno, other.codigoSistemaExterno)) {
            return false;
        }
        
        return Objects.equals(this.sistemaExterno, other.sistemaExterno);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("codigoSimasc");
		 cadena.append(this.codigoSimasc);
	 	cadena.append(", ");
         
	     cadena.append("dominioSimasc");
		 cadena.append(this.dominioSimasc);
	 	cadena.append(", ");
         
	     cadena.append("codigoSistemaExterno");
		 cadena.append(this.codigoSistemaExterno);
	 	cadena.append(", ");
         
	     cadena.append("sistemaExterno");
		 cadena.append(this.sistemaExterno);
         
     	return cadena.toString(); 
     }

} 
