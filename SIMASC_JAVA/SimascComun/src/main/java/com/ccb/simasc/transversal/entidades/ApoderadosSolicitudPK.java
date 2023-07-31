package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ApoderadosSolicitudPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_persona_apoderado")
    
    private Long idPersonaApoderado;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_solicitud_servicio_apoderado")
    
    private Long idSolicitudServicioApoderado;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_rol_apoderado")
    
    private Long idRolApoderado;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_persona_representado")
    
    private Long idPersonaRepresentado;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_solicitud_servicio_representado")
    
    private Long idSolicitudServicioRepresentado;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_rol_representado")
    
    private Long idRolRepresentado;       

	public ApoderadosSolicitudPK(){
		
	}

    public ApoderadosSolicitudPK(Long idPersonaApoderado, Long idSolicitudServicioApoderado, Long idRolApoderado, Long idPersonaRepresentado, Long idSolicitudServicioRepresentado, Long idRolRepresentado) {
		this.idPersonaApoderado = idPersonaApoderado;       
		this.idSolicitudServicioApoderado = idSolicitudServicioApoderado;       
		this.idRolApoderado = idRolApoderado;       
		this.idPersonaRepresentado = idPersonaRepresentado;       
		this.idSolicitudServicioRepresentado = idSolicitudServicioRepresentado;       
		this.idRolRepresentado = idRolRepresentado;       
    }

    
	public Long getIdPersonaApoderado(){
		return this.idPersonaApoderado;
	}
	
	public void setIdPersonaApoderado(Long idPersonaApoderado){
		this.idPersonaApoderado = idPersonaApoderado;
	}
		
    
	public Long getIdSolicitudServicioApoderado(){
		return this.idSolicitudServicioApoderado;
	}
	
	public void setIdSolicitudServicioApoderado(Long idSolicitudServicioApoderado){
		this.idSolicitudServicioApoderado = idSolicitudServicioApoderado;
	}
		
    
	public Long getIdRolApoderado(){
		return this.idRolApoderado;
	}
	
	public void setIdRolApoderado(Long idRolApoderado){
		this.idRolApoderado = idRolApoderado;
	}
		
    
	public Long getIdPersonaRepresentado(){
		return this.idPersonaRepresentado;
	}
	
	public void setIdPersonaRepresentado(Long idPersonaRepresentado){
		this.idPersonaRepresentado = idPersonaRepresentado;
	}
		
    
	public Long getIdSolicitudServicioRepresentado(){
		return this.idSolicitudServicioRepresentado;
	}
	
	public void setIdSolicitudServicioRepresentado(Long idSolicitudServicioRepresentado){
		this.idSolicitudServicioRepresentado = idSolicitudServicioRepresentado;
	}
		
    
	public Long getIdRolRepresentado(){
		return this.idRolRepresentado;
	}
	
	public void setIdRolRepresentado(Long idRolRepresentado){
		this.idRolRepresentado = idRolRepresentado;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idPersonaApoderado);
        hash = 37 * hash + Objects.hashCode(this.idSolicitudServicioApoderado);
        hash = 37 * hash + Objects.hashCode(this.idRolApoderado);
        hash = 37 * hash + Objects.hashCode(this.idPersonaRepresentado);
        hash = 37 * hash + Objects.hashCode(this.idSolicitudServicioRepresentado);
        hash = 37 * hash + Objects.hashCode(this.idRolRepresentado);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ApoderadosSolicitudPK que se pasa
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
        final ApoderadosSolicitudPK other = (ApoderadosSolicitudPK) obj;
        
        
        if (!Objects.equals(this.idPersonaApoderado, other.idPersonaApoderado)) {
            return false;
        }
        
        if (!Objects.equals(this.idSolicitudServicioApoderado, other.idSolicitudServicioApoderado)) {
            return false;
        }
        
        if (!Objects.equals(this.idRolApoderado, other.idRolApoderado)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaRepresentado, other.idPersonaRepresentado)) {
            return false;
        }
        
        if (!Objects.equals(this.idSolicitudServicioRepresentado, other.idSolicitudServicioRepresentado)) {
            return false;
        }
        
        return Objects.equals(this.idRolRepresentado, other.idRolRepresentado);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idPersonaApoderado");
		 cadena.append(this.idPersonaApoderado);
	 	cadena.append(", ");
         
	     cadena.append("idSolicitudServicioApoderado");
		 cadena.append(this.idSolicitudServicioApoderado);
	 	cadena.append(", ");
         
	     cadena.append("idRolApoderado");
		 cadena.append(this.idRolApoderado);
	 	cadena.append(", ");
         
	     cadena.append("idPersonaRepresentado");
		 cadena.append(this.idPersonaRepresentado);
	 	cadena.append(", ");
         
	     cadena.append("idSolicitudServicioRepresentado");
		 cadena.append(this.idSolicitudServicioRepresentado);
	 	cadena.append(", ");
         
	     cadena.append("idRolRepresentado");
		 cadena.append(this.idRolRepresentado);
         
     	return cadena.toString(); 
     }

} 
