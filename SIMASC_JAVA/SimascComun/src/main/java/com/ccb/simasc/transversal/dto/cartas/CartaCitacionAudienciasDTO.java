package com.ccb.simasc.transversal.dto.cartas;

import java.util.Date;

import com.ccb.simasc.transversal.utilidades.UtilConstantes;

public class CartaCitacionAudienciasDTO {
	
	private String ciudad;
	private Date fechaActual;
	private String nombreParteDirigida;
	private String rolParteDirigida;
	private String direccion;
	private String casoServicio;
	private String nombreConvocante;
	private String nombreConvocado;
	private String casoCodigo;
	private String tipoAudiencia;
	private Date fechaAudiencia;
	private Integer totalArbitros;
	private String nombreArbitros;
	private String direccionCentro;
	private String [] meses;
	private String nombreDirector;
	private String inicialesDirector;
	private String cargoDirector;
	private String correoDirector;
	
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public Date getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}
	public String getNombreParteDirigida() {
		return nombreParteDirigida;
	}
	public void setNombreParteDirigida(String nombreParteDirigida) {
		this.nombreParteDirigida = nombreParteDirigida;
	}
	public String getRolParteDirigida() {
		return rolParteDirigida;
	}
	public void setRolParteDirigida(String rolParteDirigida) {
		this.rolParteDirigida = rolParteDirigida;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCasoServicio() {
		return casoServicio;
	}
	public void setCasoServicio(String casoServicio) {
		this.casoServicio = casoServicio;
	}
	public String getNombreConvocante() {
		return nombreConvocante;
	}
	public void setNombreConvocante(String nombreConvocante) {
		this.nombreConvocante = nombreConvocante;
	}
	public String getNombreConvocado() {
		return nombreConvocado;
	}
	public void setNombreConvocado(String nombreConvocado) {
		this.nombreConvocado = nombreConvocado;
	}
	public String getCasoCodigo() {
		return casoCodigo;
	}
	public void setCasoCodigo(String casoCodigo) {
		this.casoCodigo = casoCodigo;
	}
	public String getTipoAudiencia() {
		return tipoAudiencia;
	}
	public void setTipoAudiencia(String tipoAudiencia) {
		this.tipoAudiencia = tipoAudiencia;
	}
	public Date getFechaAudiencia() {
		return fechaAudiencia;
	}
	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}
	public Integer getTotalArbitros() {
		return totalArbitros;
	}
	public void setTotalArbitros(Integer totalArbitros) {
		this.totalArbitros = totalArbitros;
	}
	public String getNombreArbitros() {
		return nombreArbitros;
	}
	public void setNombreArbitros(String nombreArbitros) {
		this.nombreArbitros = nombreArbitros;
	}
	public String getDireccionCentro() {
		return direccionCentro;
	}
	public void setDireccionCentro(String direccionCentro) {
		this.direccionCentro = direccionCentro;
	}
	public String[] getMeses() {
		return meses = UtilConstantes.MESES;
	}
	public void setMeses(String[] meses) {
		this.meses = UtilConstantes.MESES;
	}
	public String getNombreDirector() {
		return nombreDirector;
	}
	public void setNombreDirector(String nombreDirector) {
		this.nombreDirector = nombreDirector;
	}
	public String getInicialesDirector() {
		return inicialesDirector;
	}
	public void setInicialesDirector(String inicialesDirector) {
		this.inicialesDirector = inicialesDirector;
	}
	public String getCargoDirector() {
		return cargoDirector;
	}
	public void setCargoDirector(String cargoDirector) {
		this.cargoDirector = cargoDirector;
	}
	public String getCorreoDirector() {
		return correoDirector;
	}
	public void setCorreoDirector(String correoDirector) {
		this.correoDirector = correoDirector;
	}		
}

