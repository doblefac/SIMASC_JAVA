package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class EstadoPersonaTipoServicioPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_persona")
    
    private Long idPersona;       
	@Basic(optional = false)
    @NotNull
    @Column(name="tipo_servicio")
    
    private String tipoServicio;       

	public EstadoPersonaTipoServicioPK(){
		
	}

    public EstadoPersonaTipoServicioPK(Long idPersona, String tipoServicio) {
		this.idPersona = idPersona;       
		this.tipoServicio = tipoServicio;       
    }

    
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
    
	public String getTipoServicio(){
		return this.tipoServicio;
	}
	
	public void setTipoServicio(String tipoServicio){
		this.tipoServicio = tipoServicio;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.tipoServicio);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EstadoPersonaTipoServicioPK que se pasa
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
        final EstadoPersonaTipoServicioPK other = (EstadoPersonaTipoServicioPK) obj;
        
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        return Objects.equals(this.tipoServicio, other.tipoServicio);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idPersona");
		 cadena.append(this.idPersona);
	 	cadena.append(", ");
         
	     cadena.append("tipoServicio");
		 cadena.append(this.tipoServicio);
         
     	return cadena.toString(); 
     }

} 
