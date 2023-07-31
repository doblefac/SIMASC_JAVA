package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class InasistenciaPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_audiencia")
    
    private Long idAudiencia;       
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
    @Column(name="id_caso")
    
    private Long idCaso;       

	public InasistenciaPK(){
		
	}

    public InasistenciaPK(Long idAudiencia, Long idRol, Long idPersona, Long idCaso) {
		this.idAudiencia = idAudiencia;       
		this.idRol = idRol;       
		this.idPersona = idPersona;       
		this.idCaso = idCaso;       
    }

    
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
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
                
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad InasistenciaPK que se pasa
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
        final InasistenciaPK other = (InasistenciaPK) obj;
        
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
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
	     cadena.append("idAudiencia");
		 cadena.append(this.idAudiencia);
	 	cadena.append(", ");
         
	     cadena.append("idRol");
		 cadena.append(this.idRol);
	 	cadena.append(", ");
         
	     cadena.append("idPersona");
		 cadena.append(this.idPersona);
	 	cadena.append(", ");
         
	     cadena.append("idCaso");
		 cadena.append(this.idCaso);
         
     	return cadena.toString(); 
     }

} 
