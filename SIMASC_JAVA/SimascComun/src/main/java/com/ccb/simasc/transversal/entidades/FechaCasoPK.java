package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class FechaCasoPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="tipo_fecha")
    
    private String tipoFecha;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_caso")
    
    private Long idCaso;       

	public FechaCasoPK(){
		
	}

    public FechaCasoPK(String tipoFecha, Long idCaso) {
		this.tipoFecha = tipoFecha;       
		this.idCaso = idCaso;       
    }

    
	public String getTipoFecha(){
		return this.tipoFecha;
	}
	
	public void setTipoFecha(String tipoFecha){
		this.tipoFecha = tipoFecha;
	}
		
    
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.tipoFecha);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad FechaCasoPK que se pasa
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
        final FechaCasoPK other = (FechaCasoPK) obj;
        
        
        if (!Objects.equals(this.tipoFecha, other.tipoFecha)) {
            return false;
        }
        
        return Objects.equals(this.idCaso, other.idCaso);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("tipoFecha");
		 cadena.append(this.tipoFecha);
	 	cadena.append(", ");
         
	     cadena.append("idCaso");
		 cadena.append(this.idCaso);
         
     	return cadena.toString(); 
     }

} 
