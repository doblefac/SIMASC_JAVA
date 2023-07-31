package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="CORRER_TRASLADO")
@NamedQuery(name = "CorrerTraslado.findAll", query = "SELECT p FROM CorrerTraslado p")
public class CorrerTraslado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CorrerTrasladoPK correrTrasladoPK;
		
	@ManyToOne
	@JoinColumn(name="id_documento", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
	
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;

	
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;
	

    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.correrTrasladoPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
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
        final CorrerTraslado other = (CorrerTraslado) obj;
        
        if (!Objects.equals(this.correrTrasladoPK, other.correrTrasladoPK)) {
            return false;
        }
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
	
	public CorrerTrasladoPK getCorrerTrasladoPK() {
		return correrTrasladoPK;
	}

	public void setCorrerTrasladoPK(CorrerTrasladoPK correrTrasladoPK) {
		this.correrTrasladoPK = correrTrasladoPK;
	}


	public String getIdUsuarioModificacion() {
		return idUsuarioModificacion;
	}

	public void setIdUsuarioModificacion(String idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}		

}
