package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ObligacionPartePK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="tipo_obligacion")
    
    private String tipoObligacion;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_resultado_audiencia")
    
    private Long idResultadoAudiencia;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_caso")
    
    private Long idCaso;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_rol")
    
    private Long idRol;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_persona")
    
    private Long idPersona;       

	public ObligacionPartePK(){
		
	}

    public ObligacionPartePK(String tipoObligacion, Long idResultadoAudiencia, Long idCaso, Long idRol, Long idPersona) {
		this.tipoObligacion = tipoObligacion;       
		this.idResultadoAudiencia = idResultadoAudiencia;       
		this.idCaso = idCaso;       
		this.idRol = idRol;       
		this.idPersona = idPersona;       
    }

    
	public String getTipoObligacion(){
		return this.tipoObligacion;
	}
	
	public void setTipoObligacion(String tipoObligacion){
		this.tipoObligacion = tipoObligacion;
	}
		
    
	public Long getIdResultadoAudiencia(){
		return this.idResultadoAudiencia;
	}
	
	public void setIdResultadoAudiencia(Long idResultadoAudiencia){
		this.idResultadoAudiencia = idResultadoAudiencia;
	}
		
    
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
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
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.tipoObligacion);
        hash = 37 * hash + Objects.hashCode(this.idResultadoAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ObligacionPartePK que se pasa
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
        final ObligacionPartePK other = (ObligacionPartePK) obj;
        
        
        if (!Objects.equals(this.tipoObligacion, other.tipoObligacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idResultadoAudiencia, other.idResultadoAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("tipoObligacion");
		 cadena.append(this.tipoObligacion);
	 	cadena.append(", ");
         
	     cadena.append("idResultadoAudiencia");
		 cadena.append(this.idResultadoAudiencia);
	 	cadena.append(", ");
         
	     cadena.append("idCaso");
		 cadena.append(this.idCaso);
	 	cadena.append(", ");
         
	     cadena.append("idRol");
		 cadena.append(this.idRol);
	 	cadena.append(", ");
         
	     cadena.append("idPersona");
		 cadena.append(this.idPersona);
         
     	return cadena.toString(); 
     }

} 
