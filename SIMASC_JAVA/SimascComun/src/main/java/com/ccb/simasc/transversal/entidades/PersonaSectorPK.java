package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class PersonaSectorPK implements Serializable {
       
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
    @NotNull
    @Column(name="id_sector")
    private String idSector;       

	@Basic(optional = false)
    @NotNull
    @Column(name="id_persona")
    private Long idPersona;
	
	public PersonaSectorPK() {
	}

	public PersonaSectorPK(String idSector, Long idPersona) {
		this.idSector = idSector;
		this.idPersona = idPersona;
	}
	
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idSector);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        
        return hash;
    }
	
	   @Override
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final PersonaSectorPK other = (PersonaSectorPK) obj;
	        
	        
	        if (!Objects.equals(this.idSector, other.idSector)) {
	            return false;
	        }
	        
	        return Objects.equals(this.idPersona, other.idPersona);
	                
	    }

	public String getIdSector() {
		return idSector;
	}

	public void setIdSector(String idSector) {
		this.idSector = idSector;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}    
}
