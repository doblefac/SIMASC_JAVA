package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Date;

public class LlamadaPlanillaCorrespondenciaDTO  implements Serializable {
	
	private Long codigoCaso;
	private String nombreParte;
	private String rolParte;
	private String direccionEnviada;
	private String ciudadDireccion;
	private String telefono;
	private Date fechaAudiencia;
	private Date horaAudiencia;
	private String sedeAudiencia;
	private String conciliador;
	private Long numeroCarta;
	private String numeroGuia;
	private String estadoCarta;
	private Date fechaDevolucion;
	
	
	//Llamada asociada al correo
	private String usuarioLlamada;
	private String personaQueContesta;
	private Long idPersona;
	private Long idRol;
	private Date fecha;
	private Long idTelefono;
	private String observaciones;
	private boolean confirmacionAsistencia;
	
	
	
	
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
	 * @return the nombreParte
	 */
	public String getNombreParte() {
		return nombreParte;
	}
	/**
	 * @param nombreParte the nombreParte to set
	 */
	public void setNombreParte(String nombreParte) {
		this.nombreParte = nombreParte;
	}
	/**
	 * @return the rolParte
	 */
	public String getRolParte() {
		return rolParte;
	}
	/**
	 * @param rolParte the rolParte to set
	 */
	public void setRolParte(String rolParte) {
		this.rolParte = rolParte;
	}
	/**
	 * @return the direccionEnviada
	 */
	public String getDireccionEnviada() {
		return direccionEnviada;
	}
	/**
	 * @param direccionEnviada the direccionEnviada to set
	 */
	public void setDireccionEnviada(String direccionEnviada) {
		this.direccionEnviada = direccionEnviada;
	}
	/**
	 * @return the ciudadDireccion
	 */
	public String getCiudadDireccion() {
		return ciudadDireccion;
	}
	/**
	 * @param ciudadDireccion the ciudadDireccion to set
	 */
	public void setCiudadDireccion(String ciudadDireccion) {
		this.ciudadDireccion = ciudadDireccion;
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
	 * @return the fechaAudiencia
	 */
	public Date getFechaAudiencia() {
		return fechaAudiencia;
	}
	/**
	 * @param fechaAudiencia the fechaAudiencia to set
	 */
	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}
	/**
	 * @return the horaAudiencia
	 */
	public Date getHoraAudiencia() {
		return horaAudiencia;
	}
	/**
	 * @param horaAudiencia the horaAudiencia to set
	 */
	public void setHoraAudiencia(Date horaAudiencia) {
		this.horaAudiencia = horaAudiencia;
	}
	/**
	 * @return the sedeAudiencia
	 */
	public String getSedeAudiencia() {
		return sedeAudiencia;
	}
	/**
	 * @param sedeAudiencia the sedeAudiencia to set
	 */
	public void setSedeAudiencia(String sedeAudiencia) {
		this.sedeAudiencia = sedeAudiencia;
	}
	/**
	 * @return the conciliador
	 */
	public String getConciliador() {
		return conciliador;
	}
	/**
	 * @param conciliador the conciliador to set
	 */
	public void setConciliador(String conciliador) {
		this.conciliador = conciliador;
	}
	/**
	 * @return the numeroCarta
	 */
	public Long getNumeroCarta() {
		return numeroCarta;
	}
	/**
	 * @param numeroCarta the numeroCarta to set
	 */
	public void setNumeroCarta(Long numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	/**
	 * @return the numeroGuia
	 */
	public String getNumeroGuia() {
		return numeroGuia;
	}
	/**
	 * @param numeroGuia the numeroGuia to set
	 */
	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}
	/**
	 * @return the estadoCarta
	 */
	public String getEstadoCarta() {
		return estadoCarta;
	}
	/**
	 * @param estadoCarta the estadoCarta to set
	 */
	public void setEstadoCarta(String estadoCarta) {
		this.estadoCarta = estadoCarta;
	}
	/**
	 * @return the usuarioLlamada
	 */
	public String getUsuarioLlamada() {
		return usuarioLlamada;
	}
	/**
	 * @param usuarioLlamada the usuarioLlamada to set
	 */
	public void setUsuarioLlamada(String usuarioLlamada) {
		this.usuarioLlamada = usuarioLlamada;
	}
	/**
	 * @return the personaQueContesta
	 */
	public String getPersonaQueContesta() {
		return personaQueContesta;
	}
	/**
	 * @param personaQueContesta the personaQueContesta to set
	 */
	public void setPersonaQueContesta(String personaQueContesta) {
		this.personaQueContesta = personaQueContesta;
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
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the idTelefono
	 */
	public Long getIdTelefono() {
		return idTelefono;
	}
	/**
	 * @param idTelefono the idTelefono to set
	 */
	public void setIdTelefono(Long idTelefono) {
		this.idTelefono = idTelefono;
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
	 * @return the confirmacionAsistencia
	 */
	public boolean isConfirmacionAsistencia() {
		return confirmacionAsistencia;
	}
	/**
	 * @param confirmacionAsistencia the confirmacionAsistencia to set
	 */
	public void setConfirmacionAsistencia(boolean confirmacionAsistencia) {
		this.confirmacionAsistencia = confirmacionAsistencia;
	}
	/**
	 * @return the fechaDevolucion
	 */
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}
	/**
	 * @param fechaDevolucion the fechaDevolucion to set
	 */
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	
}
