package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ccb.simasc.transversal.dto.DetalleLiquidacionDTO;

public class FormularioGenerarLiquidacionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<DetalleLiquidacionDTO> detalle;
	private String mensaje;
	private String moneda;
	private String servicio;
	private Double valor;
	private String cuerpo;
	private Long idSolicitudAplicativo;
	private Boolean isSolicitud;
	private int idServicio;
	private Date fechaVigencia;
	
	
	
	public Date getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	/**
	 * @return the detalle
	 */
	public List<DetalleLiquidacionDTO> getDetalle() {
		return detalle;
	}



	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(List<DetalleLiquidacionDTO> detalle) {
		this.detalle = detalle;
	}



	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the servicio
	 */
	public String getServicio() {
		return servicio;
	}

	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	/**
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * @return the cuerpo
	 */
	public String getCuerpo() {
		return cuerpo;
	}

	/**
	 * @param cuerpo the cuerpo to set
	 */
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	/**
	 * @return the idSolicitudAplicativo
	 */
	public Long getIdSolicitudAplicativo() {
		return idSolicitudAplicativo;
	}

	/**
	 * @param idSolicitudAplicativo the idSolicitudAplicativo to set
	 */
	public void setIdSolicitudAplicativo(Long idSolicitudAplicativo) {
		this.idSolicitudAplicativo = idSolicitudAplicativo;
	}

	/**
	 * @return the isSolicitud
	 */
	public Boolean getIsSolicitud() {
		if(isSolicitud == null)
			isSolicitud = true;
		return isSolicitud;
	}

	/**
	 * @param isSolicitud the isSolicitud to set
	 */
	public void setIsSolicitud(Boolean isSolicitud) {
		this.isSolicitud = isSolicitud;
	}
	
	

}
