package com.ccb.simasc.transversal.dto;

public class DetalleLiquidacionDTO {

	
	private String servicio;
	private String servicioId;
	private Integer valorTotal;
	private Integer item;
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
	 * @return the servicioId
	 */
	public String getServicioId() {
		return servicioId;
	}
	/**
	 * @param servicioId the servicioId to set
	 */
	public void setServicioId(String servicioId) {
		this.servicioId = servicioId;
	}
	/**
	 * @return the valorTotal
	 */
	public Integer getValorTotal() {
		return valorTotal;
	}
	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(Integer valorTotal) {
		this.valorTotal = valorTotal;
	}
	/**
	 * @return the item
	 */
	public Integer getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(Integer item) {
		this.item = item;
	}
	
	
}
