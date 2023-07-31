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
@Table(name = "ARBITRO_CASO_LIBERACION")
@NamedQuery(name = "ArbitroCasoLiberacion.findAll", query = "SELECT p FROM ArbitroCasoLiberacion p")
public class ArbitroCasoLiberacion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String ENTIDAD_PERSONA_PK = "idPersona";

	@EmbeddedId
	private ArbitroCasoLiberacionPK arbitroCasoLiberacionPK;
	
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		

	
	
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

	public ArbitroCasoLiberacionPK getArbitroCasoLiberacionPK() {
		return arbitroCasoLiberacionPK;
	}

	public void setArbitroCasoLiberacionPK(ArbitroCasoLiberacionPK arbitroCasoLiberacionPK) {
		this.arbitroCasoLiberacionPK = arbitroCasoLiberacionPK;
	}
	
	public ArbitroCasoLiberacion(){
		arbitroCasoLiberacionPK = new ArbitroCasoLiberacionPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }

	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.arbitroCasoLiberacionPK);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);

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
		final ArbitroCasoLiberacion other = (ArbitroCasoLiberacion) obj;
		
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
		
		return Objects.equals(this.arbitroCasoLiberacionPK, other.arbitroCasoLiberacionPK);

	}

}
