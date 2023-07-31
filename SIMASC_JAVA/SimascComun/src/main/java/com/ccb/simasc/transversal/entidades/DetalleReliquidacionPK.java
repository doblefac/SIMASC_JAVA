package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class DetalleReliquidacionPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="codigo_item")
    
    private Long codigoItem;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_reliquidacion")
    
    private Long idReliquidacion;       

	public DetalleReliquidacionPK(){
		
	}

    public DetalleReliquidacionPK(Long codigoItem, Long idReliquidacion) {
		this.codigoItem = codigoItem;       
		this.idReliquidacion = idReliquidacion;       
    }

    
	public Long getCodigoItem(){
		return this.codigoItem;
	}
	
	public void setCodigoItem(Long codigoItem){
		this.codigoItem = codigoItem;
	}
		
    
	public Long getIdReliquidacion(){
		return this.idReliquidacion;
	}
	
	public void setIdReliquidacion(Long idReliquidacion){
		this.idReliquidacion = idReliquidacion;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.codigoItem);
        hash = 37 * hash + Objects.hashCode(this.idReliquidacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DetalleReliquidacionPK que se pasa
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
        final DetalleReliquidacionPK other = (DetalleReliquidacionPK) obj;
        
        
        if (!Objects.equals(this.codigoItem, other.codigoItem)) {
            return false;
        }
        
        return Objects.equals(this.idReliquidacion, other.idReliquidacion);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("codigoItem");
		 cadena.append(this.codigoItem);
	 	cadena.append(", ");
         
	     cadena.append("idReliquidacion");
		 cadena.append(this.idReliquidacion);
         
     	return cadena.toString(); 
     }

} 
