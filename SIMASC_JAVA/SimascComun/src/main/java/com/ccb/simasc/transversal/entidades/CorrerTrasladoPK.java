package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

@Embeddable
public class CorrerTrasladoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
    @NotNull
    @Column(name="id_documento")
	private Long idDocumento;
	
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
    @Column(name="id_rol")
	private Long idRol;

	@Basic(optional = false)
    @NotNull
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_traslado")
	private Date fechaTraslado;
	
	
	public CorrerTrasladoPK() {
	}

	public CorrerTrasladoPK(Long idDocumento, Long idPersona, Long idCaso,
			Long idRol, Date fechaTraslado) {
		this.idDocumento = idDocumento;
		this.idPersona = idPersona;
		this.idCaso = idCaso;
		this.idRol = idRol;
		this.fechaTraslado = fechaTraslado;
	}
	
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CorrerTrasladoPK other = (CorrerTrasladoPK) obj;
        
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
        	return false;
        }
        if (!Objects.equals(this.idCaso, other.idCaso)) {
        	return false;
        }
        if (!Objects.equals(this.idRol, other.idRol)) {
        	return false;
        }
        if (!Objects.equals(this.fechaTraslado, other.fechaTraslado)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }

	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.fechaTraslado);
        
        return hash;
    }
	
	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
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

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public Date getFechaTraslado() {
		return fechaTraslado;
	}

	public void setFechaTraslado(Date fechaTraslado) {
		this.fechaTraslado = fechaTraslado;
	}
	
	
}
