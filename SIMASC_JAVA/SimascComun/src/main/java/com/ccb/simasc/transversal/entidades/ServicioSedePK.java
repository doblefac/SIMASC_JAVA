package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ServicioSedePK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_sede")
    
    private Long idSede;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_servicio")
    
    private Long idServicio;       

	public ServicioSedePK(){
		
	}

    public ServicioSedePK(Long idSede, Long idServicio) {
		this.idSede = idSede;       
		this.idServicio = idServicio;       
    }

    
	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
		
    
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idSede);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ServicioSedePK que se pasa
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
        final ServicioSedePK other = (ServicioSedePK) obj;
        
        
        if (!Objects.equals(this.idSede, other.idSede)) {
            return false;
        }
        
        return Objects.equals(this.idServicio, other.idServicio);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idSede");
		 cadena.append(this.idSede);
	 	cadena.append(", ");
         
	     cadena.append("idServicio");
		 cadena.append(this.idServicio);
         
     	return cadena.toString(); 
     }

} 
