package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class PartePeticionPK implements Serializable{

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
    @Column(name="id_caso")
    
    private Long idCaso;       
	@Basic(optional = false)
    @NotNull
    @Column(name="tipo")
    
    private String tipo;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_peticion")
    
    private Long idPeticion;       

	public PartePeticionPK(){
		
	}

    public PartePeticionPK(Long idRol, Long idPersona, Long idCaso, String tipo, Long idPeticion) {
		this.idRol = idRol;       
		this.idPersona = idPersona;       
		this.idCaso = idCaso;       
		this.tipo = tipo;       
		this.idPeticion = idPeticion;       
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
		
    
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
    
	public Long getIdPeticion(){
		return this.idPeticion;
	}
	
	public void setIdPeticion(Long idPeticion){
		this.idPeticion = idPeticion;
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
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.idPeticion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PartePeticionPK que se pasa
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
        final PartePeticionPK other = (PartePeticionPK) obj;
        
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        return Objects.equals(this.idPeticion, other.idPeticion);
                
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
         
	     cadena.append("idCaso");
		 cadena.append(this.idCaso);
	 	cadena.append(", ");
         
	     cadena.append("tipo");
		 cadena.append(this.tipo);
	 	cadena.append(", ");
         
	     cadena.append("idPeticion");
		 cadena.append(this.idPeticion);
         
     	return cadena.toString(); 
     }

} 
