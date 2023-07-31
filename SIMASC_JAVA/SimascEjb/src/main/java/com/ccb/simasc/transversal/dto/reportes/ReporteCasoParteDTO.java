package com.ccb.simasc.transversal.dto.reportes;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO con campos definidos para el reporte casos por parte
 * 
 * @author dpachon
 */
public class ReporteCasoParteDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long codigoCaso;
	private String nombreCaso;
	private Date fechaRadicacion;
	private String tipoCuantia;
	private String valorPretension;
	private String rol;
	private String tipoEmpresa;
	private String tipoPersona;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String tarjetaProfesional;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String telefono_uno;
	private String tipoTelefono;
	private String fax;
	private String correo_uno;
	private String consumo;
	private String servicio;
	

	/**
	 * @return the codigoCaso
	 */
	public Long getCodigoCaso() {
		return codigoCaso;
	}
	/**
	 * @param codigoCaso the codigoCaso to set
	 */
	public void setCodigoCaso(Long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}
	/**
	 * @return the nombreCaso
	 */
	public String getNombreCaso() {
		return nombreCaso;
	}
	/**
	 * @param nombreCaso the nombreCaso to set
	 */
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	/**
	 * @return the fechaRadicacion
	 */
	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}
	/**
	 * @param fechaRadicacion the fechaRadicacion to set
	 */
	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}
	/**
	 * @return the tipoCuantia
	 */
	public String getTipoCuantia() {
		return tipoCuantia;
	}
	/**
	 * @param tipoCuantia the tipoCuantia to set
	 */
	public void setTipoCuantia(String tipoCuantia) {
		this.tipoCuantia = tipoCuantia;
	}
	/**
	 * @return the valorPretension
	 */
	public String getValorPretension() {
		return valorPretension;
	}
	/**
	 * @param valorPretension the valorPretension to set
	 */
	public void setValorPretension(String valorPretension) {
		this.valorPretension = valorPretension;
	}
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
	 * @return the tipoEmpresa
	 */
	public String getTipoEmpresa() {
		return tipoEmpresa;
	}
	/**
	 * @param tipoEmpresa the tipoEmpresa to set
	 */
	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}
	/**
	 * @return the tipoPersona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}
	/**
	 * @param tipoPersona the tipoPersona to set
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	/**
	 * @return the tipoIdentificacion
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	/**
	 * @param tipoIdentificacion the tipoIdentificacion to set
	 */
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	/**
	 * @return the numeroIdentificacion
	 */
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	/**
	 * @param numeroIdentificacion the numeroIdentificacion to set
	 */
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	/**
	 * @return the tarjetaProfesional
	 */
	public String getTarjetaProfesional() {
		return tarjetaProfesional;
	}
	/**
	 * @param tarjetaProfesional the tarjetaProfesional to set
	 */
	public void setTarjetaProfesional(String tarjetaProfesional) {
		this.tarjetaProfesional = tarjetaProfesional;
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
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @return the telefono_uno
	 */
	public String getTelefono_uno() {
		return telefono_uno;
	}
	/**
	 * @param telefono_uno the telefono_uno to set
	 */
	public void setTelefono_uno(String telefono_uno) {
		this.telefono_uno = telefono_uno;
	}
	/**
	 * @return the tipoTelefono
	 */
	public String getTipoTelefono() {
		return tipoTelefono;
	}
	/**
	 * @param tipoTelefono the tipoTelefono to set
	 */
	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}
	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * @return the correo_uno
	 */
	public String getCorreo_uno() {
		return correo_uno;
	}
	/**
	 * @param correo_uno the correo_uno to set
	 */
	public void setCorreo_uno(String correo_uno) {
		this.correo_uno = correo_uno;
	}
	/**
	 * @return the consumo
	 */
	public String getConsumo() {
		return consumo;
	}
	/**
	 * @param consumo the consumo to set
	 */
	public void setConsumo(String consumo) {
		this.consumo = consumo;
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
	
}
