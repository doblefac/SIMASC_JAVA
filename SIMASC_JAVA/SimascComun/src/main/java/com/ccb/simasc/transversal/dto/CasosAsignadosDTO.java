package com.ccb.simasc.transversal.dto;

import java.util.Date;
import java.util.List;

import com.ccb.simasc.transversal.entidades.Persona;


/**
 * dto utilizado en el pronunimineto del arbitro 
 * @author cbenavides
 *
 */
public class CasosAsignadosDTO {
	
	private Long idCaso;
	private String idUsuario;
	private Long idPersona;
	private Long idRol;
	private String estadoRolPersonaCaso;
	private String etapaDelCaso;
	private String nombreRol;
	private Date fechaAsignacion;
	private String nombreCaso;
	private String nombrePersona;
	private List<Persona> listaPersonaCaso;
	private String nombresArbitros;
		
	/**
	 * @return the idCaso
	 */
	public Long getIdCaso() {
		return idCaso;
	}
	/**
	 * @param idCaso the idCaso to set
	 */
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}

	/**
	 * @return the idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}
	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	/**
	 * @return the idRol
	 */
	public Long getIdRol() {
		return idRol;
	}
	/**
	 * @param idRol the idRol to set
	 */
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	/**
	 * @return the estadoRpc
	 */
	public String getEstadoRpc() {
		return estadoRolPersonaCaso;
	}
	/**
	 * @param estadoRpc the estadoRpc to set
	 */
	public void setEstadoRpc(String estadoRpc) {
		this.estadoRolPersonaCaso = estadoRpc;
	}
	/**
	 * @return the etapaDelCaso
	 */
	public String getEtapaDelCaso() {
		return etapaDelCaso;
	}
	/**
	 * @param etapaDelCaso the etapaDelCaso to set
	 */
	public void setEtapaDelCaso(String etapaDelCaso) {
		this.etapaDelCaso = etapaDelCaso;
	}
	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return the nombreRol
	 */
	public String getNombreRol() {
		return nombreRol;
	}
	/**
	 * @param nombreRol the nombreRol to set
	 */
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	/**
	 * @return the fechaAsignacion
	 */
	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}
	/**
	 * @param fechaAsignacion the fechaAsignacion to set
	 */
	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
	/**
	 * @return the estadoRolPersonaCaso
	 */
	public String getEstadoRolPersonaCaso() {
		return estadoRolPersonaCaso;
	}
	/**
	 * @param estadoRolPersonaCaso the estadoRolPersonaCaso to set
	 */
	public void setEstadoRolPersonaCaso(String estadoRolPersonaCaso) {
		this.estadoRolPersonaCaso = estadoRolPersonaCaso;
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
	 * @return the nombrePersona
	 */
	public String getNombrePersona() {
		return nombrePersona;
	}
	/**
	 * @param nombrePersona the nombrePersona to set
	 */
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	/**
	 * @return the nombresArbitros
	 */
	public String getNombresArbitros() {
		return nombresArbitros;
	}
	/**
	 * @param nombresArbitros the nombresArbitros to set
	 */
	public void setNombresArbitros(String nombresArbitros) {
		this.nombresArbitros = nombresArbitros;
	}
	/**
	 * @return the listaPersonaCaso
	 */
	public List<Persona> getListaPersonaCaso() {
		return listaPersonaCaso;
	}
	/**
	 * @param listaPersonaCaso the listaPersonaCaso to set
	 */
	public void setListaPersonaCaso(List<Persona> listaPersonaCaso) {
		this.listaPersonaCaso = listaPersonaCaso;
	}
	
	

}
