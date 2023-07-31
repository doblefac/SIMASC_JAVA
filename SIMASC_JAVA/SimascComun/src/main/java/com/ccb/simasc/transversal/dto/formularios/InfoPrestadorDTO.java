package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;

/**
 * DTO utilizado en la consulta del requerimietno ADM-C-003 Consulta de lista de prestadores
 * Representa la información consultada de cada prestador de servicio
 * @author jsoto
 *
 */
public class InfoPrestadorDTO {
	
	private String apellidos;
	private String nombres;
	private String tipoDocumento;
	private String numeroDocumento;
	private String tarjetaProfesional;
	private Date fechaNacimiento;
	private String lista;
	private String materias;
	private String direcciones;
	private String telefonos;
	private String celular;
	private String correoElectronico;
	private String actaAprobacion;
	private Date fechaAprobacion;
	private Date fechaUltimaActualizacion;
	private String reportadoSicaac;
	//Nombre y apellidos de la persona jurídica
	private String personaJuridica;
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
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
	public String getTarjetaProfesional() {
		return tarjetaProfesional;
	}
	public void setTarjetaProfesional(String tarjetaProfesional) {
		this.tarjetaProfesional = tarjetaProfesional;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getLista() {
		return lista;
	}
	public void setLista(String lista) {
		this.lista = lista;
	}
	public String getMaterias() {
		return materias;
	}
	public void setMaterias(String materias) {
		this.materias = materias;
	}
	public String getDirecciones() {
		return direcciones;
	}
	public void setDirecciones(String direcciones) {
		this.direcciones = direcciones;
	}
	public String getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getActaAprobacion() {
		return actaAprobacion;
	}
	public void setActaAprobacion(String actaAprobacion) {
		this.actaAprobacion = actaAprobacion;
	}
	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}
	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}
	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}
	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}
	public String isReportadoSicaac() {
		return reportadoSicaac;
	}
	public void setReportadoSicaac(String reportadoSicaac) {
		this.reportadoSicaac = reportadoSicaac;
	}
	public String getPersonaJuridica() {
		return personaJuridica;
	}
	public void setPersonaJuridica(String personaJuridica) {
		this.personaJuridica = personaJuridica;
	}
	
	


}



