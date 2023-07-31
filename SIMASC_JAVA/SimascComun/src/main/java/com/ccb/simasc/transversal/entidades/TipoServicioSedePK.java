package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class TipoServicioSedePK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="tipo_servicio")
    
    private String tipoServicio;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_sede")
    
    private Long idSede;       

	public TipoServicioSedePK(){
		
	}

    public TipoServicioSedePK(String tipoServicio, Long idSede) {
		this.tipoServicio = tipoServicio;       
		this.idSede = idSede;       
    }

    
	public String getTipoServicio(){
		return this.tipoServicio;
	}
	
	public void setTipoServicio(String tipoServicio){
		this.tipoServicio = tipoServicio;
	}
		
    
	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.tipoServicio);
        hash = 37 * hash + Objects.hashCode(this.idSede);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TipoServicioSedePK que se pasa
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
        final TipoServicioSedePK other = (TipoServicioSedePK) obj;
        
        
        if (!Objects.equals(this.tipoServicio, other.tipoServicio)) {
            return false;
        }
        
        return Objects.equals(this.idSede, other.idSede);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("tipoServicio");
		 cadena.append(this.tipoServicio);
	 	cadena.append(", ");
         
	     cadena.append("idSede");
		 cadena.append(this.idSede);
         
     	return cadena.toString(); 
     }

} 
