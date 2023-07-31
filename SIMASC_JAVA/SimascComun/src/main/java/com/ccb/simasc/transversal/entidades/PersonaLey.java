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

@Entity
@Table(name="PERSONA_LEY")
@NamedQuery(name = "PersonaLey.findAll", query = "SELECT p FROM PersonaLey p")
public class PersonaLey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PersonaLeyPK personaLeyPk; 
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;	
	
	@Column(name="estado_registro")
	private String estadoRegistro;
	
	
	 public PersonaLey(PersonaLeyPK personaLeyPk, String idUsuarioModificacion,
			Date fechaUltimaModificacion, String estadoRegistro) {
		this.personaLeyPk = personaLeyPk;
		this.idUsuarioModificacion = idUsuarioModificacion;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoRegistro = estadoRegistro;
	}

	public PersonaLey() {
		personaLeyPk = new PersonaLeyPK();
	}

	public PersonaLeyPK getPersonaLeyPk() {
		return personaLeyPk;
	}

	public void setPersonaLeyPk(PersonaLeyPK personaLeyPk) {
		this.personaLeyPk = personaLeyPk;
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

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.personaLeyPk);        
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
        final PersonaLey other = (PersonaLey) obj;
        
        if (!Objects.equals(this.personaLeyPk, other.personaLeyPk)) {
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
	
}
