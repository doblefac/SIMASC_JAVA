package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class CriterioTotalPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="codigo_criterio")
    
    private String codigoCriterio;       
	@Basic(optional = false)
    @NotNull
    @Column(name="codigo_total")
    
    private String codigoTotal;       

	public CriterioTotalPK(){
		
	}

    public CriterioTotalPK(String codigoCriterio, String codigoTotal) {
		this.codigoCriterio = codigoCriterio;       
		this.codigoTotal = codigoTotal;       
    }

    
	public String getCodigoCriterio(){
		return this.codigoCriterio;
	}
	
	public void setCodigoCriterio(String codigoCriterio){
		this.codigoCriterio = codigoCriterio;
	}
		
    
	public String getCodigoTotal(){
		return this.codigoTotal;
	}
	
	public void setCodigoTotal(String codigoTotal){
		this.codigoTotal = codigoTotal;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.codigoCriterio);
        hash = 37 * hash + Objects.hashCode(this.codigoTotal);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CriterioTotalPK que se pasa
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
        final CriterioTotalPK other = (CriterioTotalPK) obj;
        
        
        if (!Objects.equals(this.codigoCriterio, other.codigoCriterio)) {
            return false;
        }
        
        return Objects.equals(this.codigoTotal, other.codigoTotal);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("codigoCriterio");
		 cadena.append(this.codigoCriterio);
	 	cadena.append(", ");
         
	     cadena.append("codigoTotal");
		 cadena.append(this.codigoTotal);
         
     	return cadena.toString(); 
     }

} 
