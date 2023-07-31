package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ClasificadorDominioPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="codigo_clasificado")
    
    private String codigoClasificado;       
	@Basic(optional = false)
    @NotNull
    @Column(name="dominio_clasificado")
    
    private String dominioClasificado;       
	@Basic(optional = false)
    @NotNull
    @Column(name="codigo_clasificador")
    
    private String codigoClasificador;       
	@Basic(optional = false)
    @NotNull
    @Column(name="dominio_clasificador")
    
    private String dominioClasificador;       

	public ClasificadorDominioPK(){
		
	}

    public ClasificadorDominioPK(String codigoClasificado, String dominioClasificado, String codigoClasificador, String dominioClasificador) {
		this.codigoClasificado = codigoClasificado;       
		this.dominioClasificado = dominioClasificado;       
		this.codigoClasificador = codigoClasificador;       
		this.dominioClasificador = dominioClasificador;       
    }

    
	public String getCodigoClasificado(){
		return this.codigoClasificado;
	}
	
	public void setCodigoClasificado(String codigoClasificado){
		this.codigoClasificado = codigoClasificado;
	}
		
    
	public String getDominioClasificado(){
		return this.dominioClasificado;
	}
	
	public void setDominioClasificado(String dominioClasificado){
		this.dominioClasificado = dominioClasificado;
	}
		
    
	public String getCodigoClasificador(){
		return this.codigoClasificador;
	}
	
	public void setCodigoClasificador(String codigoClasificador){
		this.codigoClasificador = codigoClasificador;
	}
		
    
	public String getDominioClasificador(){
		return this.dominioClasificador;
	}
	
	public void setDominioClasificador(String dominioClasificador){
		this.dominioClasificador = dominioClasificador;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.codigoClasificado);
        hash = 37 * hash + Objects.hashCode(this.dominioClasificado);
        hash = 37 * hash + Objects.hashCode(this.codigoClasificador);
        hash = 37 * hash + Objects.hashCode(this.dominioClasificador);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ClasificadorDominioPK que se pasa
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
        final ClasificadorDominioPK other = (ClasificadorDominioPK) obj;
        
        
        if (!Objects.equals(this.codigoClasificado, other.codigoClasificado)) {
            return false;
        }
        
        if (!Objects.equals(this.dominioClasificado, other.dominioClasificado)) {
            return false;
        }
        
        if (!Objects.equals(this.codigoClasificador, other.codigoClasificador)) {
            return false;
        }
        
        return Objects.equals(this.dominioClasificador, other.dominioClasificador);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("codigoClasificado");
		 cadena.append(this.codigoClasificado);
	 	cadena.append(", ");
         
	     cadena.append("dominioClasificado");
		 cadena.append(this.dominioClasificado);
	 	cadena.append(", ");
         
	     cadena.append("codigoClasificador");
		 cadena.append(this.codigoClasificador);
	 	cadena.append(", ");
         
	     cadena.append("dominioClasificador");
		 cadena.append(this.dominioClasificador);
         
     	return cadena.toString(); 
     }

} 
