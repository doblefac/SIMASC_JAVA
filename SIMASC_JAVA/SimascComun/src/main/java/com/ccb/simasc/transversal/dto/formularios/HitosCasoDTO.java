package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;

/**
 * DTO para almacenar los documentos de un digitalizador asociados a un caso
 * Utilizado en el requerimiento TRANS-F-009
 * @author jsoto
 *
 */
public class HitosCasoDTO {
		
	private Long idCaso;
	private Long idUsuario;
	private Date admisionDemanda;
	private Date audienciaAclaracion;
	private Date audienciaAlegatos;
	private Date audienciaConciliacion;
	private Date audienciaLaudo;
	private Date audienciaPruebas;
	private Date audienciaPrimeraTramite;
	private Date cierreEtapaProbatoria;
	private Date cierre;
	private Date contestacionDemanda;
	private Date demanda;
	private Date demandaReconvencion;
	private Date designacionArbitros;
	private Date fijacionGastosHonorarios;
	private Date instalacion;
	private Date llamamientoGarantia;
	private Date notificacionDemandado;
	private Date tramiteRecursoAnulacion;
	private Date trasladoExcepcionesMerito;
	private Date tribunalConsignaCAC;
	private Date inicioConteoTerminos;
	
	
	public Date getAdmisionDemanda() {
		return admisionDemanda;
	}
	public void setAdmisionDemanda(Date admisionDemanda) {
		this.admisionDemanda = admisionDemanda;
	}
	public Date getAudienciaAclaracion() {
		return audienciaAclaracion;
	}
	public void setAudienciaAclaracion(Date audienciaAclaracion) {
		this.audienciaAclaracion = audienciaAclaracion;
	}
	public Date getAudienciaAlegatos() {
		return audienciaAlegatos;
	}
	public void setAudienciaAlegatos(Date audienciaAlegatos) {
		this.audienciaAlegatos = audienciaAlegatos;
	}
	public Date getAudienciaConciliacion() {
		return audienciaConciliacion;
	}
	public void setAudienciaConciliacion(Date audienciaConciliacion) {
		this.audienciaConciliacion = audienciaConciliacion;
	}
	public Date getAudienciaLaudo() {
		return audienciaLaudo;
	}
	public void setAudienciaLaudo(Date audienciaLaudo) {
		this.audienciaLaudo = audienciaLaudo;
	}
	public Date getAudienciaPruebas() {
		return audienciaPruebas;
	}
	public void setAudienciaPruebas(Date audienciaPruebas) {
		this.audienciaPruebas = audienciaPruebas;
	}
	public Date getAudienciaPrimeraTramite() {
		return audienciaPrimeraTramite;
	}
	public void setAudienciaPrimeraTramite(Date audienciaPrimeraTramite) {
		this.audienciaPrimeraTramite = audienciaPrimeraTramite;
	}
	public Date getCierreEtapaProbatoria() {
		return cierreEtapaProbatoria;
	}
	public void setCierreEtapaProbatoria(Date cierreEtapaAprobatoria) {
		this.cierreEtapaProbatoria = cierreEtapaAprobatoria;
	}
	public Date getCierre() {
		return cierre;
	}
	public void setCierre(Date cierre) {
		this.cierre = cierre;
	}
	public Date getContestacionDemanda() {
		return contestacionDemanda;
	}
	public void setContestacionDemanda(Date contestacionDemanda) {
		this.contestacionDemanda = contestacionDemanda;
	}
	public Date getDemanda() {
		return demanda;
	}
	public void setDemanda(Date demanda) {
		this.demanda = demanda;
	}
	public Date getDemandaReconvencion() {
		return demandaReconvencion;
	}
	public void setDemandaReconvencion(Date demandaReconvencion) {
		this.demandaReconvencion = demandaReconvencion;
	}
	public Date getDesignacionArbitros() {
		return designacionArbitros;
	}
	public void setDesignacionArbitros(Date designacionArbitros) {
		this.designacionArbitros = designacionArbitros;
	}
	public Date getFijacionGastosHonorarios() {
		return fijacionGastosHonorarios;
	}
	public void setFijacionGastosHonorarios(Date fijacionGastosHonorarios) {
		this.fijacionGastosHonorarios = fijacionGastosHonorarios;
	}
	public Date getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Date instalacion) {
		this.instalacion = instalacion;
	}
	public Date getLlamamientoGarantia() {
		return llamamientoGarantia;
	}
	public void setLlamamientoGarantia(Date llamamientoGarantia) {
		this.llamamientoGarantia = llamamientoGarantia;
	}
	public Date getNotificacionDemandado() {
		return notificacionDemandado;
	}
	public void setNotificacionDemandado(Date notificacionDemandado) {
		this.notificacionDemandado = notificacionDemandado;
	}
	public Date getTramiteRecursoAnulacion() {
		return tramiteRecursoAnulacion;
	}
	public void setTramiteRecursoAnulacion(Date tramiteRecursoAnulacion) {
		this.tramiteRecursoAnulacion = tramiteRecursoAnulacion;
	}
	public Date getTrasladoExcepcionesMerito() {
		return trasladoExcepcionesMerito;
	}
	public void setTrasladoExcepcionesMerito(Date trasladoExcepcionesMerito) {
		this.trasladoExcepcionesMerito = trasladoExcepcionesMerito;
	}
	public Date getTribunalConsignaCAC() {
		return tribunalConsignaCAC;
	}
	public void setTribunalConsignaCAC(Date tribunalConsignaCAC) {
		this.tribunalConsignaCAC = tribunalConsignaCAC;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return the inicioConteoTerminos
	 */
	public Date getInicioConteoTerminos() {
		return inicioConteoTerminos;
	}
	/**
	 * @param inicioConteoTerminos the inicioConteoTerminos to set
	 */
	public void setInicioConteoTerminos(Date inicioConteoTerminos) {
		this.inicioConteoTerminos = inicioConteoTerminos;
	}
	
	
	
	
}
