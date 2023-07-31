package com.ccb.simasc.transversal.dto.alertas;
import java.util.Date;

public class InfoBasicaAlertasDTO {
	
	private Long idCaso;
	private String nombreCaso;
	private Long idPersona;
	private String nombrePersona;
	private Date fechaRadicacionCaso;
	private String nombreCentro;
	private String valorParametro;
	private Long valorParametroNumerico;
	private Long idCentro;
	private Date fechaAudiencia;
	private Long diasHabiles;
	private Long idConvenio;
	private String nombreConvenio;
	private String tabla;
	private Long diasTranscurridosCaso;
	private String nombreSede;
	private Long numeroCartas;
	private Date fechaEnvio;
	private String direccion;
	private Long idCartaPersona;
	private Date fecha;
	private String tipoPeticion;
	private Long idPeticion;
	private Date fechaDesignacion;
	private String tipoAudiencia;
	private String nombreRol;
	private String nombreAbogado;

	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
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
	 * @return the fechaRadicacionCaso
	 */
	public Date getFechaRadicacionCaso() {
		return fechaRadicacionCaso;
	}
	/**
	 * @param fechaRadicacionCaso the fechaRadicacionCaso to set
	 */
	public void setFechaRadicacionCaso(Date fechaRadicacionCaso) {
		this.fechaRadicacionCaso = fechaRadicacionCaso;
	}
	/**
	 * @return the nombreCentro
	 */
	public String getNombreCentro() {
		return nombreCentro;
	}
	/**
	 * @param nombreCentro the nombreCentro to set
	 */
	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}
	/**
	 * @return the valorParametro
	 */
	public String getValorParametro() {
		return valorParametro;
	}
	/**
	 * @param valorParametro the valorParametro to set
	 */
	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}
	/**
	 * @return the idCentro
	 */
	public Long getIdCentro() {
		return idCentro;
	}
	
	/**
	 * @param idCentro the idCentro to set
	 */
	public void setIdCentro(Long idCentro) {
		this.idCentro = idCentro;
	}
	
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	
	public String getNombrePersona() {
		return nombrePersona;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	/**
	 * @return the valorParametroNumerico
	 */
	public Long getValorParametroNumerico() {
		return valorParametroNumerico;
	}
	/**
	 * @param valorParametroNumerico the valorParametroNumerico to set
	 */
	public void setValorParametroNumerico(Long valorParametroNumerico) {
		this.valorParametroNumerico = valorParametroNumerico;
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
	 * @return the diasHabiles
	 */
	public Long getDiasHabiles() {
		return diasHabiles;
	}
	/**
	 * @param diasHabiles the diasHabiles to set
	 */
	public void setDiasHabiles(Long diasHabiles) {
		this.diasHabiles = diasHabiles;
	}
	
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	public Long getIdConvenio() {
		return idConvenio;
	}
	public void setIdConvenio(Long idConvenio) {
		this.idConvenio = idConvenio;
	}
	public String getNombreConvenio() {
		return nombreConvenio;
	}
	public void setNombreConvenio(String nombreConvenio) {
		this.nombreConvenio = nombreConvenio;
	}
	/**
	 * @return the diasTranscurridosCaso
	 */
	public Long getDiasTranscurridosCaso() {
		return diasTranscurridosCaso;
	}
	/**
	 * @param diasTranscurridosCaso the diasTranscurridosCaso to set
	 */
	public void setDiasTranscurridosCaso(Long diasTranscurridosCaso) {
		this.diasTranscurridosCaso = diasTranscurridosCaso;
	}
	/**
	 * @return the nombreSede
	 */
	public String getNombreSede() {
		return nombreSede;
	}
	/**
	 * @param nombreSede the nombreSede to set
	 */
	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}
	/**
	 * @return the numeroCartas
	 */
	public Long getNumeroCartas() {
		return numeroCartas;
	}
	/**
	 * @param numeroCartas the numeroCartas to set
	 */
	public void setNumeroCartas(Long numeroCartas) {
		this.numeroCartas = numeroCartas;
	}
	/**
	 * @return the fechaEnvio
	 */
	public Date getFechaEnvio() {
		return fechaEnvio;
	}
	/**
	 * @param fechaEnvio the fechaEnvio to set
	 */
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
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
	 * @return the idCartaPersona
	 */
	public Long getIdCartaPersona() {
		return idCartaPersona;
	}
	/**
	 * @param idCartaPersona the idCartaPersona to set
	 */
	public void setIdCartaPersona(Long idCartaPersona) {
		this.idCartaPersona = idCartaPersona;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the tipoPeticion
	 */
	public String getTipoPeticion() {
		return tipoPeticion;
	}
	/**
	 * @param tipoPeticion the tipoPeticion to set
	 */
	public void setTipoPeticion(String tipoPeticion) {
		this.tipoPeticion = tipoPeticion;
	}
	/**
	 * @return the idPeticion
	 */
	public Long getIdPeticion() {
		return idPeticion;
	}
	/**
	 * @param idPeticion the idPeticion to set
	 */
	public void setIdPeticion(Long idPeticion) {
		this.idPeticion = idPeticion;
	}
	/**
	 * @return the fechaDesignacion
	 */
	public Date getFechaDesignacion() {
		return fechaDesignacion;
	}
	/**
	 * @param fechaDesignacion the fechaDesignacion to set
	 */
	public void setFechaDesignacion(Date fechaDesignacion) {
		this.fechaDesignacion = fechaDesignacion;
	}
	
	public String getTipoAudiencia() {
		return tipoAudiencia;
	}
	public void setTipoAudiencia(String tipoAudiencia) {
		this.tipoAudiencia = tipoAudiencia;
	}
	public String getNombreAbogado() {
		return nombreAbogado;
	}
	public void setNombreAbogado(String nombreAbogado) {
		this.nombreAbogado = nombreAbogado;
	}
	
	
}
