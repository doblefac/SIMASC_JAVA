package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class RelacionadoConvenioPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_convenio")
    
    private Long idConvenio;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_persona")
    
    private Long idPersona;       
	@Basic(optional = false)
    @NotNull
    @Column(name="id_rol")
    
    private Long idRol;       

	public RelacionadoConvenioPK(){
		
	}

    public RelacionadoConvenioPK(Long idConvenio, Long idPersona, Long idRol) {
		this.idConvenio = idConvenio;       
		this.idPersona = idPersona;       
		this.idRol = idRol;       
    }

    
	public Long getIdConvenio(){
		return this.idConvenio;
	}
	
	public void setIdConvenio(Long idConvenio){
		this.idConvenio = idConvenio;
	}
		
    
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
    
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idConvenio);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RelacionadoConvenioPK que se pasa
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
        final RelacionadoConvenioPK other = (RelacionadoConvenioPK) obj;
        
        
        if (!Objects.equals(this.idConvenio, other.idConvenio)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        return Objects.equals(this.idRol, other.idRol);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("idConvenio");
		 cadena.append(this.idConvenio);
	 	cadena.append(", ");
         
	     cadena.append("idPersona");
		 cadena.append(this.idPersona);
	 	cadena.append(", ");
         
	     cadena.append("idRol");
		 cadena.append(this.idRol);
         
     	return cadena.toString(); 
     }

} 
