package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;

/**
 * DTO utilizado en la consulta del requerimietno TRANSF009 Listar casos asignados
 * Representa los filtros seleccionados por el usuario y el id de usuario registrado en el sistema
 * @author jsoto
 *
 */
public class FiltroCasosAsignadosDTO {
	
	//Id de la persona registrada en el sistema
	private Long idPersonaUsuario;
	//Tipo del servicio seleccionado por el usuario en la vista, corresponde al dominio TIPO_SERVICIO (Plan justicia o Plan Dialogos)
	private String tipoServicio;
	//Aplica para la consulta de prestadores de servicio
	private Date fechaDesde;
	//Aplica para la consulta de prestadores de servicio
	private Date fechaHasta;
	//Arbitro, Conciliador o Secretario de tribunal; Aplica para la consulta de prestadores de servicio
	private Long idPrestadorDeServicio;
	//Aplica para la consulta de prestadores de servicio
	private String estadoCaso;
	//Aplica para la consulta de prestadores de servicio
	private String nombreCaso;
	//Aplica para la consulta de prestadores de servicio
	private Long codigoCaso;
	//Nombre del convenio; Aplica para la consulta de prestadores de servicio
	private Long idConvenio;
	//Aplica para la consulta de prestadores de servicio
	private String apoderado;
	//Aplica para la consulta de prestadores de servicio
	private String numeroIdentificacionParte;	
	//Los siguientes filtros solo pueden ser seleccionados cuando el usuario no es prestador de servicio
	private Long idServicio;
	private Long idMateria;
	private String cuantia;
	private Long idSede;
	//Abogado asignado al caso (se pasa el idPersona)
	private String idAbogado;
	//Aplica para cuando la consulta el actor es prestador de servicio
	private String nombreRol;
	//Aplicar para arbitraje abreviado
	private boolean arbitrajeConsumo;
	

	
	public Long getIdPersonaUsuario() {
		return idPersonaUsuario;
	}
	public void setIdPersonaUsuario(Long idPersonaUsuario) {
		this.idPersonaUsuario = idPersonaUsuario;
	}
	
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	public Long getIdPrestadorDeServicio() {
		return idPrestadorDeServicio;
	}
	public void setIdPrestadorDeServicio(Long idPrestadorDeServicio) {
		this.idPrestadorDeServicio = idPrestadorDeServicio;
	}
	public String getEstadoCaso() {
		return estadoCaso;
	}
	public void setEstadoCaso(String estadoCaso) {
		this.estadoCaso = estadoCaso;
	}
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public Long getCodigoCaso() {
		return codigoCaso;
	}
	public void setCodigoCaso(Long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}	
	public Long getIdConvenio() {
		return idConvenio;
	}
	public void setIdConvenio(Long idConvenio) {
		this.idConvenio = idConvenio;
	}
	public String getApoderado() {
		return apoderado;
	}
	public void setApoderado(String apoderado) {
		this.apoderado = apoderado;
	}
	
	public String getNumeroIdentificacionParte() {
		return numeroIdentificacionParte;
	}
	public void setNumeroIdentificacionParte(String numeroIdentificacionParte) {
		this.numeroIdentificacionParte = numeroIdentificacionParte;
	}
	
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	public Long getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}
	public String getCuantia() {
		return cuantia;
	}
	public void setCuantia(String cuantia) {
		this.cuantia = cuantia;
	}
	public Long getIdSede() {
		return idSede;
	}
	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}
	public String getIdAbogado() {
		return idAbogado;
	}
	public void setIdAbogado(String idAbogado) {
		this.idAbogado = idAbogado;
	}
	public boolean tieneIdPersonaUsuario(){
		return this.idPersonaUsuario != null;
	}
	
	public boolean tieneTipoServicio(){
		return this.tipoServicio != null;
	}
	
	public boolean tieneFechaDesde(){
		return this.fechaDesde != null;
	}
	
	public boolean tieneFechaHasta(){
		return this.fechaHasta != null;
	}
	
	public boolean tieneIdPrestadorDeServicio(){
		return this.idPrestadorDeServicio != null;
	}
	
	public boolean tieneEstadoCaso(){
		return this.estadoCaso != null;
	}
	
	public boolean tieneNombreCaso(){
		return this.nombreCaso != null;
	}
	
	public boolean tieneCodigoCaso(){
		return this.codigoCaso != null;
	}
	
	public boolean tieneIdConvenio(){
		return this.idConvenio != null;
	}
	
	public boolean tieneApoderado(){
		return this.apoderado != null;
	}
	
	public boolean tieneNumeroIdentificacionParte(){
		return this.numeroIdentificacionParte != null;
	}
	
	public boolean tieneIdServicio(){
		return this.idServicio != null;
	}
	
	public boolean tieneIdMateria(){
		return this.idMateria != null;
	}
	
	public boolean tieneCuantia(){
		return this.cuantia != null;
	}
	
	public boolean tieneIdSede(){
		return this.idSede != null;
	}
	
	public boolean tieneIdAbogado(){
		return this.idAbogado != null;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	public boolean isArbitrajeConsumo() {
		return arbitrajeConsumo;
	}
	public void setArbitrajeConsumo(boolean arbitrajeConsumo) {
		this.arbitrajeConsumo = arbitrajeConsumo;
	}
	
}



