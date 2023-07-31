package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="PERSONA_SECTOR")
@NamedQuery(name = "PersonaSector.findAll", query = "SELECT p FROM PersonaSector p")
public class PersonaSector implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PersonaSectorPK personaSectorPk; 
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;	
	
	@Column(name="estado_registro")
	private String estadoRegistro;
	
	public PersonaSector() {
		personaSectorPk = new PersonaSectorPK();
	}

	public PersonaSector(PersonaSectorPK personaSectorPk,
			String idUsuarioModificacion, Date fechaUltimaModificacion,
			String estadoRegistro) {
		this.personaSectorPk = personaSectorPk;
		this.idUsuarioModificacion = idUsuarioModificacion;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoRegistro = estadoRegistro;
	}

	  /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.personaSectorPk);        
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
        final PersonaSector other = (PersonaSector) obj;
        
        if (!Objects.equals(this.personaSectorPk, other.personaSectorPk)) {
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
    
	public PersonaSectorPK getPersonaSectorPk() {
		return personaSectorPk;
	}

	public void setPersonaSectorPk(PersonaSectorPK personaSectorPk) {
		this.personaSectorPk = personaSectorPk;
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
	
}
