package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class NombramientoPersonaPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_caso")
    
    private Long idCaso;       
	@Basic(optional = false)
    @NotNull
    @Column(name="metodo_de_nombramiento")
    
    private String metodoDeNombramiento;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_persona")
    
    private Long idPersona;       

	public NombramientoPersonaPK(){
		
	}

    public NombramientoPersonaPK(Long idCaso, String metodoDeNombramiento, Long idPersona) {
		this.idCaso = idCaso;       
		this.metodoDeNombramiento = metodoDeNombramiento;       
		this.idPersona = idPersona;       
    }

    
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
    
	public String getMetodoDeNombramiento(){
		return this.metodoDeNombramiento;
	}
	
	public void setMetodoDeNombramiento(String metodoDeNombramiento){
		this.metodoDeNombramiento = metodoDeNombramiento;
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
                
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.metodoDeNombramiento);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad NombramientoPersonasPK que se pasa
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
        final NombramientoPersonaPK other = (NombramientoPersonaPK) obj;
        
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.metodoDeNombramiento, other.metodoDeNombramiento)) {
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
	     cadena.append("idCaso");
		 cadena.append(this.idCaso);
	 	cadena.append(", ");
         
	     cadena.append("metodoDeNombramiento");
		 cadena.append(this.metodoDeNombramiento);
	 	cadena.append(", ");
         
	     cadena.append("idPersona");
		 cadena.append(this.idPersona);
         
     	return cadena.toString(); 
     }

} 
