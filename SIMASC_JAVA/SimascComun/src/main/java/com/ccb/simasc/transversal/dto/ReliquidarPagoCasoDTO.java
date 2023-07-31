package com.ccb.simasc.transversal.dto;

public class ReliquidarPagoCasoDTO {

	private long idCaso;
	private String numeroRecibo;
	private String codigoServicio;
	private String detallePago;
	private long valor;
	private Long codigoItem;

	public ReliquidarPagoCasoDTO() {

	}
	
	

	public ReliquidarPagoCasoDTO(long idCaso, String numeroRecibo, String codigoServicio, String detallePago,
			long valor) {
		this.idCaso = idCaso;
		this.numeroRecibo = numeroRecibo;
		this.codigoServicio = codigoServicio;
		this.detallePago = detallePago;
		this.valor = valor;
	}

	


	/**
	 * @return the codigoItem
	 */
	public Long getCodigoItem() {
		return codigoItem;
	}



	/**
	 * @param codigoItem the codigoItem to set
	 */
	public void setCodigoItem(Long codigoItem) {
		this.codigoItem = codigoItem;
	}



	public long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(long idCaso) {
		this.idCaso = idCaso;
	}

	public String getNumeroRecibo() {
		return numeroRecibo;
	}

	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}

	public String getCodigoServicio() {
		return codigoServicio;
	}

	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	public String getDetallePago() {
		return detallePago;
	}

	public void setDetallePago(String detallePago) {
		this.detallePago = detallePago;
	}

	public long getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}

}
