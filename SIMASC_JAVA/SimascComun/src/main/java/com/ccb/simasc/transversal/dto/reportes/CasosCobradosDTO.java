package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

/**
 * 
 * @author jnmartinez
 *
 */
public class CasosCobradosDTO {

	private Long idCaso;
	private Long idServicio;
	private String nombreServicio;
	private String nombre;
	private Date fechaCierre;
	private Date fechaCobro;
	private Date fechaFacturacion;
	private Long valorACobrar;
	
	public CasosCobradosDTO() {
	}

	/**
	 * @return the idCaso
	 */
	public Long getIdCaso() {
		return idCaso;
	}

	/**
	 * @param idCaso the idCaso to set
	 */
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}

	/**
	 * @return the idServicio
	 */
	public Long getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * @return the nombreServicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * @param nombreServicio the nombreServicio to set
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the fechaCierre
	 */
	public Date getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * @param fechaCierre the fechaCierre to set
	 */
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public Date getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(Date fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return the valorACobrar
	 */
	public Long getValorACobrar() {
		return valorACobrar;
	}

	/**
	 * @param valorACobrar the valorACobrar to set
	 */
	public void setValorACobrar(Long valorACobrar) {
		this.valorACobrar = valorACobrar;
	}

	/**
	 * @return the fechaCobro
	 */
	public Date getFechaCobro() {
		return fechaCobro;
	}

	/**
	 * @param fechaCobro the fechaCobro to set
	 */
	public void setFechaCobro(Date fechaCobro) {
		this.fechaCobro = fechaCobro;
	}
	
	
	
}
