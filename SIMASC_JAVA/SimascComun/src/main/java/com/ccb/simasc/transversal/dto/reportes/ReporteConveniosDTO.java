package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

public class ReporteConveniosDTO {
	
	private String nombreConvenio;
	private String representanteConvenio;
	private String direccion;
	private String telefono;
	private String correo;
	private Date fechaInicio;
	private Date fechaFin;
	private String codigoContrato;
	private Date fechaInicioContrato;
	private Date fechaFinContrato;
	private String tipoTarifa;
	private Integer minimoCasos;
	private Integer maximoCasos;
	private Double cuantiaMinima;
	private Double cuantiaMaxima;
	private String resultado;
	private Double porcentaje;
	private Double valor;	
	private Long renovaciones;
	private String conciliadores;
	
	
	/**
	 * @return the nombreConvenio
	 */
	public String getNombreConvenio() {
		return nombreConvenio;
	}
	/**
	 * @param nombreConvenio the nombreConvenio to set
	 */
	public void setNombreConvenio(String nombreConvenio) {
		this.nombreConvenio = nombreConvenio;
	}
	/**
	 * @return the representanteConvenio
	 */
	public String getRepresentanteConvenio() {
		return representanteConvenio;
	}
	/**
	 * @param representanteConvenio the representanteConvenio to set
	 */
	public void setRepresentanteConvenio(String representanteConvenio) {
		this.representanteConvenio = representanteConvenio;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the fechaInicioContrato
	 */
	public Date getFechaInicioContrato() {
		return fechaInicioContrato;
	}
	/**
	 * @param fechaInicioContrato the fechaInicioContrato to set
	 */
	public void setFechaInicioContrato(Date fechaInicioContrato) {
		this.fechaInicioContrato = fechaInicioContrato;
	}
	/**
	 * @return the fechaFinContrato
	 */
	public Date getFechaFinContrato() {
		return fechaFinContrato;
	}
	/**
	 * @param fechaFinContrato the fechaFinContrato to set
	 */
	public void setFechaFinContrato(Date fechaFinContrato) {
		this.fechaFinContrato = fechaFinContrato;
	}
	/**
	 * @return the tipoTarifa
	 */
	public String getTipoTarifa() {
		return tipoTarifa;
	}
	/**
	 * @param tipoTarifa the tipoTarifa to set
	 */
	public void setTipoTarifa(String tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}
	/**
	 * @return the minimoCasos
	 */
	public Integer getMinimoCasos() {
		return minimoCasos;
	}
	/**
	 * @param minimoCasos the minimoCasos to set
	 */
	public void setMinimoCasos(Integer minimoCasos) {
		this.minimoCasos = minimoCasos;
	}
	/**
	 * @return the maximoCasos
	 */
	public Integer getMaximoCasos() {
		return maximoCasos;
	}
	/**
	 * @param maximoCasos the maximoCasos to set
	 */
	public void setMaximoCasos(Integer maximoCasos) {
		this.maximoCasos = maximoCasos;
	}
	/**
	 * @return the cuantiaMinima
	 */
	public Double getCuantiaMinima() {
		return cuantiaMinima;
	}
	/**
	 * @param cuantiaMinima the cuantiaMinima to set
	 */
	public void setCuantiaMinima(Double cuantiaMinima) {
		this.cuantiaMinima = cuantiaMinima;
	}
	/**
	 * @return the cuantiaMaxima
	 */
	public Double getCuantiaMaxima() {
		return cuantiaMaxima;
	}
	/**
	 * @param cuantiaMaxima the cuantiaMaxima to set
	 */
	public void setCuantiaMaxima(Double cuantiaMaxima) {
		this.cuantiaMaxima = cuantiaMaxima;
	}
	/**
	 * @return the porcentaje
	 */
	public Double getPorcentaje() {
		return porcentaje;
	}
	/**
	 * @param porcentaje the porcentaje to set
	 */
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	/**
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}
	/**
	 * @return the codigoContrato
	 */
	public String getCodigoContrato() {
		return codigoContrato;
	}
	/**
	 * @param codigoContrato the codigoContrato to set
	 */
	public void setCodigoContrato(String codigoContrato) {
		this.codigoContrato = codigoContrato;
	}
	/**
	 * @return the renovaciones
	 */
	public Long getRenovaciones() {
		return renovaciones;
	}
	/**
	 * @param renovaciones the renovaciones to set
	 */
	public void setRenovaciones(Long renovaciones) {
		this.renovaciones = renovaciones;
	}
	/**
	 * @return the conciliadores
	 */
	public String getConciliadores() {
		return conciliadores;
	}
	/**
	 * @param conciliadores the conciliadores to set
	 */
	public void setConciliadores(String conciliadores) {
		this.conciliadores = conciliadores;
	}
	/**
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}
	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}	
	
	

}
