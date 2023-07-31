package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class DetallePagoCasoPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="numero_recibo")
    
    private String numeroRecibo;       
	@Basic(optional = false)
    @NotNull
    @Column(name="codigo_item")
    
    private Long codigoItem;       

	public DetallePagoCasoPK(){
		
	}

    public DetallePagoCasoPK(String numeroRecibo, Long codigoItem) {
		this.numeroRecibo = numeroRecibo;       
		this.codigoItem = codigoItem;       
    }

    
	public String getNumeroRecibo(){
		return this.numeroRecibo;
	}
	
	public void setNumeroRecibo(String numeroRecibo){
		this.numeroRecibo = numeroRecibo;
	}
		
    
	public Long getCodigoItem(){
		return this.codigoItem;
	}
	
	public void setCodigoItem(Long codigoItem){
		this.codigoItem = codigoItem;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.numeroRecibo);
        hash = 37 * hash + Objects.hashCode(this.codigoItem);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DetallePagoCasoPK que se pasa
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
        final DetallePagoCasoPK other = (DetallePagoCasoPK) obj;
        
        
        if (!Objects.equals(this.numeroRecibo, other.numeroRecibo)) {
            return false;
        }
        
        return Objects.equals(this.codigoItem, other.codigoItem);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("numeroRecibo");
		 cadena.append(this.numeroRecibo);
	 	cadena.append(", ");
         
	     cadena.append("codigoItem");
		 cadena.append(this.codigoItem);
         
     	return cadena.toString(); 
     }

} 
