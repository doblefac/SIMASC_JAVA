package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ApoderadosPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_rol_apoderado")
    
    private Long idRolApoderado;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_persona_apoderado")
    
    private Long idPersonaApoderado;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_caso_apoderado")
    
    private Long idCasoApoderado;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_rol_representado")
    
    private Long idRolRepresentado;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_persona_representado")
    
    private Long idPersonaRepresentado;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_caso_representado")
    
    private Long idCasoRepresentado;       

	public ApoderadosPK(){
		
	}

    public ApoderadosPK(Long idRolApoderado, Long idPersonaApoderado, Long idCasoApoderado, Long idRolRepresentado, Long idPersonaRepresentado, Long idCasoRepresentado) {
		this.idRolApoderado = idRolApoderado;       
		this.idPersonaApoderado = idPersonaApoderado;       
		this.idCasoApoderado = idCasoApoderado;       
		this.idRolRepresentado = idRolRepresentado;       
		this.idPersonaRepresentado = idPersonaRepresentado;       
		this.idCasoRepresentado = idCasoRepresentado;       
    }

    
	public Long getIdRolApoderado(){
		return this.idRolApoderado;
	}
	
	public void setIdRolApoderado(Long idRolApoderado){
		this.idRolApoderado = idRolApoderado;
	}
		
    
	public Long getIdPersonaApoderado(){
		return this.idPersonaApoderado;
	}
	
	public void setIdPersonaApoderado(Long idPersonaApoderado){
		this.idPersonaApoderado = idPersonaApoderado;
	}
		
    
	public Long getIdCasoApoderado(){
		return this.idCasoApoderado;
	}
	
	public void setIdCasoApoderado(Long idCasoApoderado){
		this.idCasoApoderado = idCasoApoderado;
	}
		
    
	public Long getIdRolRepresentado(){
		return this.idRolRepresentado;
	}
	
	public void setIdRolRepresentado(Long idRolRepresentado){
		this.idRolRepresentado = idRolRepresentado;
	}
		
    
	public Long getIdPersonaRepresentado(){
		return this.idPersonaRepresentado;
	}
	
	public void setIdPersonaRepresentado(Long idPersonaRepresentado){
		this.idPersonaRepresentado = idPersonaRepresentado;
	}
		
    
	public Long getIdCasoRepresentado(){
		return this.idCasoRepresentado;
	}
	
	public void setIdCasoRepresentado(Long idCasoRepresentado){
		this.idCasoRepresentado = idCasoRepresentado;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idRolApoderado);
        hash = 37 * hash + Objects.hashCode(this.idPersonaApoderado);
        hash = 37 * hash + Objects.hashCode(this.idCasoApoderado);
        hash = 37 * hash + Objects.hashCode(this.idRolRepresentado);
        hash = 37 * hash + Objects.hashCode(this.idPersonaRepresentado);
        hash = 37 * hash + Objects.hashCode(this.idCasoRepresentado);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ApoderadosPK que se pasa
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
        final ApoderadosPK other = (ApoderadosPK) obj;
        
        
        if (!Objects.equals(this.idRolApoderado, other.idRolApoderado)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaApoderado, other.idPersonaApoderado)) {
            return false;
        }
        
        if (!Objects.equals(this.idCasoApoderado, other.idCasoApoderado)) {
            return false;
        }
        
        if (!Objects.equals(this.idRolRepresentado, other.idRolRepresentado)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaRepresentado, other.idPersonaRepresentado)) {
            return false;
        }
        
        return Objects.equals(this.idCasoRepresentado, other.idCasoRepresentado);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idRolApoderado");
		 cadena.append(this.idRolApoderado);
	 	cadena.append(", ");
         
	     cadena.append("idPersonaApoderado");
		 cadena.append(this.idPersonaApoderado);
	 	cadena.append(", ");
         
	     cadena.append("idCasoApoderado");
		 cadena.append(this.idCasoApoderado);
	 	cadena.append(", ");
         
	     cadena.append("idRolRepresentado");
		 cadena.append(this.idRolRepresentado);
	 	cadena.append(", ");
         
	     cadena.append("idPersonaRepresentado");
		 cadena.append(this.idPersonaRepresentado);
	 	cadena.append(", ");
         
	     cadena.append("idCasoRepresentado");
		 cadena.append(this.idCasoRepresentado);
         
     	return cadena.toString(); 
     }

} 
