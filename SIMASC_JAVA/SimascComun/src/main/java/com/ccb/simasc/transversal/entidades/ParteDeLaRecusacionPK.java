package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ParteDeLaRecusacionPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_rol_parte")
    
    private Long idRolParte;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_persona_parte")
    
    private Long idPersonaParte;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_caso_parte")
    
    private Long idCasoParte;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_recusacion")
    
    private Long idRecusacion;       

	public ParteDeLaRecusacionPK(){
		
	}

    public ParteDeLaRecusacionPK(Long idRolParte, Long idPersonaParte, Long idCasoParte, Long idRecusacion) {
		this.idRolParte = idRolParte;       
		this.idPersonaParte = idPersonaParte;       
		this.idCasoParte = idCasoParte;       
		this.idRecusacion = idRecusacion;       
    }

    
	public Long getIdRolParte(){
		return this.idRolParte;
	}
	
	public void setIdRolParte(Long idRolParte){
		this.idRolParte = idRolParte;
	}
		
    
	public Long getIdPersonaParte(){
		return this.idPersonaParte;
	}
	
	public void setIdPersonaParte(Long idPersonaParte){
		this.idPersonaParte = idPersonaParte;
	}
		
    
	public Long getIdCasoParte(){
		return this.idCasoParte;
	}
	
	public void setIdCasoParte(Long idCasoParte){
		this.idCasoParte = idCasoParte;
	}
		
    
	public Long getIdRecusacion(){
		return this.idRecusacion;
	}
	
	public void setIdRecusacion(Long idRecusacion){
		this.idRecusacion = idRecusacion;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idRolParte);
        hash = 37 * hash + Objects.hashCode(this.idPersonaParte);
        hash = 37 * hash + Objects.hashCode(this.idCasoParte);
        hash = 37 * hash + Objects.hashCode(this.idRecusacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParteDeLaRecusacionPK que se pasa
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
        final ParteDeLaRecusacionPK other = (ParteDeLaRecusacionPK) obj;
        
        
        if (!Objects.equals(this.idRolParte, other.idRolParte)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaParte, other.idPersonaParte)) {
            return false;
        }
        
        if (!Objects.equals(this.idCasoParte, other.idCasoParte)) {
            return false;
        }
        
        return Objects.equals(this.idRecusacion, other.idRecusacion);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idRolParte");
		 cadena.append(this.idRolParte);
	 	cadena.append(", ");
         
	     cadena.append("idPersonaParte");
		 cadena.append(this.idPersonaParte);
	 	cadena.append(", ");
         
	     cadena.append("idCasoParte");
		 cadena.append(this.idCasoParte);
	 	cadena.append(", ");
         
	     cadena.append("idRecusacion");
		 cadena.append(this.idRecusacion);
         
     	return cadena.toString(); 
     }

} 
