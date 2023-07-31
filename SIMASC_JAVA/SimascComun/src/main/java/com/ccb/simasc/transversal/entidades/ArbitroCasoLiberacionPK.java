package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ArbitroCasoLiberacionPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
    @NotNull
	@Column(name = "id_persona")
	private long idPersona;
	
	@Basic(optional = false)
    @NotNull
	@Column(name = "id_caso")
	private long idCaso;
	
    public ArbitroCasoLiberacionPK(Long idPersona, Long idCaso) {
		this.idPersona = idPersona;       
		this.idCaso = idCaso;       		
    }
    
    public ArbitroCasoLiberacionPK() {
    	
    }
    

	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}

	public long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(long idCaso) {
		this.idCaso = idCaso;
	}
	
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idCaso);        
        
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
        final ArbitroCasoLiberacionPK other = (ArbitroCasoLiberacionPK) obj;
        
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        return Objects.equals(this.idCaso, other.idCaso);
                
    }
	
	
}
