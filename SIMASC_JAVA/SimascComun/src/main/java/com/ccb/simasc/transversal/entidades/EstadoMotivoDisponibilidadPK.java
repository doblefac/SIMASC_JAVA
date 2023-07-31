package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class EstadoMotivoDisponibilidadPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_motivo_estado")
    
    private String idMotivoEstado;       
	@Basic(optional = false)
    @NotNull
    @Column(name="estado_para_sorteo")
    
    private String estadoParaSorteo;       

	public EstadoMotivoDisponibilidadPK(){
		
	}

    public EstadoMotivoDisponibilidadPK(String idMotivoEstado, String estadoParaSorteo) {
		this.idMotivoEstado = idMotivoEstado;       
		this.estadoParaSorteo = estadoParaSorteo;       
    }

    
	public String getIdMotivoEstado(){
		return this.idMotivoEstado;
	}
	
	public void setIdMotivoEstado(String idMotivoEstado){
		this.idMotivoEstado = idMotivoEstado;
	}
		
    
	public String getEstadoParaSorteo(){
		return this.estadoParaSorteo;
	}
	
	public void setEstadoParaSorteo(String estadoParaSorteo){
		this.estadoParaSorteo = estadoParaSorteo;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idMotivoEstado);
        hash = 37 * hash + Objects.hashCode(this.estadoParaSorteo);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EstadoMotivoDisponibilidadPK que se pasa
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
        final EstadoMotivoDisponibilidadPK other = (EstadoMotivoDisponibilidadPK) obj;
        
        
        if (!Objects.equals(this.idMotivoEstado, other.idMotivoEstado)) {
            return false;
        }
        
        return Objects.equals(this.estadoParaSorteo, other.estadoParaSorteo);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idMotivoEstado");
		 cadena.append(this.idMotivoEstado);
	 	cadena.append(", ");
         
	     cadena.append("estadoParaSorteo");
		 cadena.append(this.estadoParaSorteo);
         
     	return cadena.toString(); 
     }

} 
