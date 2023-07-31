package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.List;

import com.ccb.simasc.transversal.entidades.ParametrosGenerales;

public class FormularioGenerarPagoPup implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private FormularioGenerarLiquidacionDTO datosLiquidacion;
	private List<ParametrosGenerales> parametros;
	private String numeroCliente;
	private int tipoServicio;
	private String tipoDocumento;
	private String numeroDocumento;
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	/**
	 * @return the datosLiquidacion
	 */
	public FormularioGenerarLiquidacionDTO getDatosLiquidacion() {
		return datosLiquidacion;
	}
	public int getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(int tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	/**
	 * @param datosLiquidacion the datosLiquidacion to set
	 */
	public void setDatosLiquidacion(FormularioGenerarLiquidacionDTO datosLiquidacion) {
		this.datosLiquidacion = datosLiquidacion;
	}
	/**
	 * @return the parametros
	 */
	public List<ParametrosGenerales> getParametros() {
		return parametros;
	}
	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(List<ParametrosGenerales> parametros) {
		this.parametros = parametros;
	}
	/**
	 * @return the numeroCliente
	 */
	public String getNumeroCliente() {
		return numeroCliente;
	}
	/**
	 * @param numeroCliente the numeroCliente to set
	 */
	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}
	
	
}
