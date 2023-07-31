package com.ccb.simasc.transversal.dto;

import java.util.Date;

public class UsuarioClaveDTO {

	
	// protected region atributos end;		
	
	private String usuarioLogin;
	private String estadoUsuario;
	private Long idPersona;		
	private String observaciones;		
	private Date ultimoAcceso;		
	private Boolean claveBloqueada;
	private Date fechaVencimientoClave;
	private String estadoRegistroClave;
	private boolean esFuncionarioCCB;
	
	/**
	 * @return the usuarioLogin
	 */
	public String getUsuarioLogin() {
		return usuarioLogin;
	}
	/**
	 * @param usuarioLogin the usuarioLogin to set
	 */
	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}
	/**
	 * @return the estadoUsuario
	 */
	public String getEstadoUsuario() {
		return estadoUsuario;
	}
	/**
	 * @param estadoUsuario the estadoUsuario to set
	 */
	public void setEstadoUsuario(String estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
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
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return the ultimoAcceso
	 */
	public Date getUltimoAcceso() {
		return ultimoAcceso;
	}
	/**
	 * @param ultimoAcceso the ultimoAcceso to set
	 */
	public void setUltimoAcceso(Date ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}
	/**
	 * @return the claveBloqueada
	 */
	public Boolean isClaveBloqueada() {
		return claveBloqueada;
	}
	/**
	 * @param claveBloqueada the claveBloqueada to set
	 */
	public void setClaveBloqueada(Boolean claveBloqueada) {
		this.claveBloqueada = claveBloqueada;
	}
	/**
	 * @return the fechaVebcimientoClave
	 */
	public Date getFechaVencimientoClave() {
		return fechaVencimientoClave;
	}
	/**
	 * @param fechaVencimientoClave the fechaVencimientoClave to set
	 */
	public void setFechaVencimientoClave(Date fechaVencimientoClave) {
		this.fechaVencimientoClave = fechaVencimientoClave;
	}
	/**
	 * @return the estadoRegistroClave
	 */
	public String getEstadoRegistroClave() {
		return estadoRegistroClave;
	}
	/**
	 * @param estadoRegistroClave the estadoRegistroClave to set
	 */
	public void setEstadoRegistroClave(String estadoRegistroClave) {
		this.estadoRegistroClave = estadoRegistroClave;
	}
	/**
	 * @return the esFuncionarioCCB
	 */
	public boolean isEsFuncionarioCCB() {
		return esFuncionarioCCB;
	}
	/**
	 * @param esFuncionarioCCB the esFuncionarioCCB to set
	 */
	public void setEsFuncionarioCCB(boolean esFuncionarioCCB) {
		this.esFuncionarioCCB = esFuncionarioCCB;
	}
	
	
	
}
