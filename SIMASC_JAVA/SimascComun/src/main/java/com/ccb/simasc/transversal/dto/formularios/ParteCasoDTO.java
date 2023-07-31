package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;
import java.util.List;

import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;

/**
 * ARB-F-052 Pronunciamiento del arbitro a la designacion. 
 * DTO con la información básica de una parte de un caso
 * @author jsoto
 *
 */
public class ParteCasoDTO {

	// Nombre completo de la persona
	private Long idPersona;
	private String nombre;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String direcciones;
	private String telefonos;
	private String celulares;
	private List<CorreoElectronicoDTO> correosElectronicos;
	// Nombre del rol (Demandante/Demandado)
	private Long idRol;
	private String rol;
	private String tipoParte;

	// Informacion del caso
	private Long idCaso;
	private String tipoCaso;
	private String nombreCaso;

	// Informacion de la audiencia
	private Long idSede;
	private Long idAudiencia;
	private Date fechaAudiencia;
	private String nombreSede;
	
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
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
	public String getCelulares() {
		return celulares;
	}
	public void setCelulares(String celulares) {
		this.celulares = celulares;
	}
	public List<CorreoElectronicoDTO> getCorreosElectronicos() {
		return correosElectronicos;
	}
	public void setCorreosElectronicos(List<CorreoElectronicoDTO> correosElectronicos) {
		this.correosElectronicos = correosElectronicos;
	}
	public Long getIdRol() {
		return idRol;
	}
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getTipoParte() {
		return tipoParte;
	}
	public void setTipoParte(String tipoParte) {
		this.tipoParte = tipoParte;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public String getTipoCaso() {
		return tipoCaso;
	}
	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public Long getIdSede() {
		return idSede;
	}
	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}
	public Date getFechaAudiencia() {
		return fechaAudiencia;
	}
	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}
	public String getNombreSede() {
		return nombreSede;
	}
	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}
	public Long getIdAudiencia() {
		return idAudiencia;
	}
	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}
}
