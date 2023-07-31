package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;

/**
 * DTO utilizado para retornar los registros de casos incompletos ARB Continuar Radicacion
 * @author dgomezb
 *
 */
public class CasoIncompletoDTO {
	
	private Long codigoCaso;
	private String nombreCaso;
	private Date fechaRadicacion;
	private String estadoCaso;
	private Date fechaUltimoEstado;
	private String sede;
	private String nombreServicio;
	private String etapa;
	private Long idServicio;
	private String tipoCuantia;
	private String valorPretensiones;
	private Long idMateria;
	private Long idSede;
	private Boolean amparoDePobreza;
	private Boolean pendienteDePago;
	private String tipoPeritaje;
	
	public Long getCodigoCaso() {
		return codigoCaso;
	}
	public void setCodigoCaso(Long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}
	
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}
	public void setFechaRadicacion(Date fechaRadiacion) {
		this.fechaRadicacion = fechaRadiacion;
	}
	public String getEstadoCaso() {
		return estadoCaso;
	}
	public void setEstadoCaso(String estadoCaso) {
		this.estadoCaso = estadoCaso;
	}
	public Date getFechaUltimoEstado() {
		return fechaUltimoEstado;
	}
	public void setFechaUltimoEstado(Date fechaUltimoEstado) {
		this.fechaUltimoEstado = fechaUltimoEstado;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}		
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	public String getTipoCuantia() {
		return tipoCuantia;
	}
	public void setTipoCuantia(String tipoCuantia) {
		this.tipoCuantia = tipoCuantia;
	}
	public String getValorPretensiones() {
		return valorPretensiones;
	}
	public void setValorPretensiones(String valorPretensiones) {
		this.valorPretensiones = valorPretensiones;
	}
	public Long getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}
	public Long getIdSede() {
		return idSede;
	}
	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}
	public Boolean getAmparoDePobreza() {
		return amparoDePobreza;
	}
	public void setAmparoDePobreza(Boolean amparoDePobreza) {
		this.amparoDePobreza = amparoDePobreza;
	}
	public Boolean getPendienteDePago() {
		return pendienteDePago;
	}
	public void setPendienteDePago(Boolean pendienteDePago) {
		this.pendienteDePago = pendienteDePago;
	}
	public String getTipoPeritaje() {
		return tipoPeritaje;
	}
	public void setTipoPeritaje(String tipoPeritaje) {
		this.tipoPeritaje = tipoPeritaje;
	}
	
	
}
