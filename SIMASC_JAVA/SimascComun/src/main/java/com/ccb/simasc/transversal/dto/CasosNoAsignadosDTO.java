package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto de los casos pendientes por asignar a conciliador.
 * 
 * @author aperez.
 *
 */
public class CasosNoAsignadosDTO implements Serializable {

	private static final long serialVersionUID = -7562962060032176880L;
	private Long idCaso;
	private String nombreCaso;
	private String nombreConvenio;
	private Date fechaRadicacion;
	private Long idServicio;

	public Long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * @return the idCaso
	 */
	public Long getIdCaso() {
		return idCaso;
	}

	/**
	 * @param idCaso
	 *            the idCaso to set
	 */
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}

	/**
	 * @return the nombreCaso
	 */
	public String getNombreCaso() {
		return nombreCaso;
	}

	/**
	 * @param nombreCaso
	 *            the nombreCaso to set
	 */
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}

	/**
	 * @return the nombreConvenio
	 */
	public String getNombreConvenio() {
		return nombreConvenio;
	}

	/**
	 * @param nombreConvenio
	 *            the nombreConvenio to set
	 */
	public void setNombreConvenio(String nombreConvenio) {
		this.nombreConvenio = nombreConvenio;
	}

	/**
	 * @return the fechaRadicacion
	 */
	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}

	/**
	 * @param fechaRadicacion
	 *            the fechaRadicacion to set
	 */
	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaRadicacion == null) ? 0 : fechaRadicacion.hashCode());
		result = prime * result + ((idCaso == null) ? 0 : idCaso.hashCode());
		result = prime * result + ((nombreCaso == null) ? 0 : nombreCaso.hashCode());
		result = prime * result + ((nombreConvenio == null) ? 0 : nombreConvenio.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CasosNoAsignadosDTO other = (CasosNoAsignadosDTO) obj;
		if (fechaRadicacion == null) {
			if (other.fechaRadicacion != null)
				return false;
		} else if (!fechaRadicacion.equals(other.fechaRadicacion))
			return false;
		if (idCaso == null) {
			if (other.idCaso != null)
				return false;
		} else if (!idCaso.equals(other.idCaso))
			return false;
		if (nombreCaso == null) {
			if (other.nombreCaso != null)
				return false;
		} else if (!nombreCaso.equals(other.nombreCaso))
			return false;
		if (nombreConvenio == null) {
			if (other.nombreConvenio != null)
				return false;
		} else if (!nombreConvenio.equals(other.nombreConvenio))
			return false;
		return true;
	}
	
	

}
