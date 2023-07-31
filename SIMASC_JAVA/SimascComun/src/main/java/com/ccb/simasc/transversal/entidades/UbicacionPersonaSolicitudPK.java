package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class UbicacionPersonaSolicitudPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_rol")
    
    private Long idRol;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_persona")
    
    private Long idPersona;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_solicitud_servicio")
    
    private Long idSolicitudServicio;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_ubicacion")
    
    private Long idUbicacion;       

	public UbicacionPersonaSolicitudPK(){
		
	}

    public UbicacionPersonaSolicitudPK(Long idRol, Long idPersona, Long idSolicitudServicio, Long idUbicacion) {
		this.idRol = idRol;       
		this.idPersona = idPersona;       
		this.idSolicitudServicio = idSolicitudServicio;       
		this.idUbicacion = idUbicacion;       
    }

    
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		
    
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
    
	public Long getIdSolicitudServicio(){
		return this.idSolicitudServicio;
	}
	
	public void setIdSolicitudServicio(Long idSolicitudServicio){
		this.idSolicitudServicio = idSolicitudServicio;
	}
		
    
	public Long getIdUbicacion(){
		return this.idUbicacion;
	}
	
	public void setIdUbicacion(Long idUbicacion){
		this.idUbicacion = idUbicacion;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idSolicitudServicio);
        hash = 37 * hash + Objects.hashCode(this.idUbicacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad UbicacionPersonaSolicitudPK que se pasa
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
        final UbicacionPersonaSolicitudPK other = (UbicacionPersonaSolicitudPK) obj;
        
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idSolicitudServicio, other.idSolicitudServicio)) {
            return false;
        }
        
        return Objects.equals(this.idUbicacion, other.idUbicacion);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idRol");
		 cadena.append(this.idRol);
	 	cadena.append(", ");
         
	     cadena.append("idPersona");
		 cadena.append(this.idPersona);
	 	cadena.append(", ");
         
	     cadena.append("idSolicitudServicio");
		 cadena.append(this.idSolicitudServicio);
	 	cadena.append(", ");
         
	     cadena.append("idUbicacion");
		 cadena.append(this.idUbicacion);
         
     	return cadena.toString(); 
     }

} 
