/**
 * 25/02/2019
 * @author jnmartinez
 */
package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * @author jnmartinez
 *
 */
@Embeddable
public class PersonaLotePK implements Serializable {

	@Basic(optional = false)
    @NotNull
    @Column(name="id_lote")
    private Long idLote;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_persona")
    private Long idPersona;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="id_caso")
    private Long idCaso;
	
	/**
	 * 
	 */
	public PersonaLotePK() {
		
	}

	public PersonaLotePK(Long idLote, Long idPersona, Long idCaso) {
		super();
		this.idLote = idLote;
		this.idPersona = idPersona;
		this.idCaso = idCaso;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idLote);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        
        return hash;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PersonaLotePK other = (PersonaLotePK) obj;
		if (!Objects.equals(this.idCaso, other.idCaso)) {
				return false;
		}
		if (!Objects.equals(this.idPersona, other.idPersona)) {
			return false;
		}
		return Objects.equals(this.idLote, other.idLote);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonaLotePK [idLote=" + idLote + ", idPersona=" + idPersona + ", idCaso=" + idCaso + "]";
	}

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public Long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	
}
