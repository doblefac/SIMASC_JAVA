/**
 * 1/08/2018
 * @author jnmartinez
 */
package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

/**
 * @author jnmartinez
 *
 */
public class ReporteEstadosArbitroMateriaDTO {

	private String rol;
	private String nombreMateria;
	private Date fechaAsignacion;
	private Date fechaFinalizacion;
	private String disponibleSorteo;
	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}
	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}
	/**
	 * @return the nombreMateria
	 */
	public String getNombreMateria() {
		return nombreMateria;
	}
	/**
	 * @param nombreMateria the nombreMateria to set
	 */
	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}
	/**
	 * @return the fechaAsignacion
	 */
	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}
	/**
	 * @param fechaAsignacion the fechaAsignacion to set
	 */
	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
	/**
	 * @return the fechaFinalizacion
	 */
	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	/**
	 * @param fechaFinalizacion the fechaFinalizacion to set
	 */
	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	/**
	 * @return the disponibleSorteo
	 */
	public String getDisponibleSorteo() {
		return disponibleSorteo;
	}
	/**
	 * @param disponibleSorteo the disponibleSorteo to set
	 */
	public void setDisponibleSorteo(String disponibleSorteo) {
		this.disponibleSorteo = disponibleSorteo;
	}
	
	
}
