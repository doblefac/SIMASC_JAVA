package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

public class FacturacionCasosConvenioDTO {
	private Date fechaRadicacion;
	private Date fechaFacturacion;
	private Long codigoCaso;
	private String convenio;
	private String partes;
	private Date fechaAudiencia;
	private Date fechaResultado;
	private Long diasCancelacion;
	private String resultado;
	private String tipoAcuerdo;
	private String conciliador;
	private String apoderado;
	private String sedeAudiencia;
	private String motivo;
	private String cuantia;
	private Double valorAcuerdo;
	private Long numeroAudiencias;
	private Double gastosAdministrativos;
	private Double honorariosConciliador;
	private Double total; 
	
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
	 * @return the fechaFacturacion
	 */
	public Date getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(Date fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return the partes
	 */
	public String getPartes() {
		return partes;
	}
	/**
	 * @param partes the partes to set
	 */
	public void setPartes(String partes) {
		this.partes = partes;
	}
	/**
	 * @return the fechaResultado
	 */
	public Date getFechaResultado() {
		return fechaResultado;
	}
	/**
	 * @param fechaResultado the fechaResultado to set
	 */
	public void setFechaResultado(Date fechaResultado) {
		this.fechaResultado = fechaResultado;
	}
	/**
	 * @return the diasCancelacion
	 */
	public Long getDiasCancelacion() {
		return diasCancelacion;
	}
	/**
	 * @param diasCancelacion the diasCancelacion to set
	 */
	public void setDiasCancelacion(Long diasCancelacion) {
		this.diasCancelacion = diasCancelacion;
	}
	/**
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}
	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	/**
	 * @return the tipoAcuerdo
	 */
	public String getTipoAcuerdo() {
		return tipoAcuerdo;
	}
	/**
	 * @param tipoAcuerdo the tipoAcuerdo to set
	 */
	public void setTipoAcuerdo(String tipoAcuerdo) {
		this.tipoAcuerdo = tipoAcuerdo;
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
	 * @return the apoderado
	 */
	public String getApoderado() {
		return apoderado;
	}
	/**
	 * @param apoderado the apoderado to set
	 */
	public void setApoderado(String apoderado) {
		this.apoderado = apoderado;
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
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
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
	 * @return the convenio
	 */
	public String getConvenio() {
		return convenio;
	}
	/**
	 * @param convenio the convenio to set
	 */
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	/**
	 * @return the valorAcuerdo
	 */
	public Double getValorAcuerdo() {
		return valorAcuerdo;
	}
	/**
	 * @param valorAcuerdo the valorAcuerdo to set
	 */
	public void setValorAcuerdo(Double valorAcuerdo) {
		this.valorAcuerdo = valorAcuerdo;
	}
	/**
	 * @return the numeroAudiencias
	 */
	public Long getNumeroAudiencias() {
		return numeroAudiencias;
	}
	/**
	 * @param numeroAudiencias the numeroAudiencias to set
	 */
	public void setNumeroAudiencias(Long numeroAudiencias) {
		this.numeroAudiencias = numeroAudiencias;
	}
	/**
	 * @return the gastosAdministrativos
	 */
	public Double getGastosAdministrativos() {
		return gastosAdministrativos;
	}
	/**
	 * @param gastosAdministrativos the gastosAdministrativos to set
	 */
	public void setGastosAdministrativos(Double gastosAdministrativos) {
		this.gastosAdministrativos = gastosAdministrativos;
	}
	/**
	 * @return the honorariosConciliador
	 */
	public Double getHonorariosConciliador() {
		return honorariosConciliador;
	}
	/**
	 * @param honorariosConciliador the honorariosConciliador to set
	 */
	public void setHonorariosConciliador(Double honorariosConciliador) {
		this.honorariosConciliador = honorariosConciliador;
	}
	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
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
	 * @return the cuantia
	 */
	public String getCuantia() {
		return cuantia;
	}
	/**
	 * @param cuantia the cuantia to set
	 */
	public void setCuantia(String cuantia) {
		this.cuantia = cuantia;
	}
	
	

}
