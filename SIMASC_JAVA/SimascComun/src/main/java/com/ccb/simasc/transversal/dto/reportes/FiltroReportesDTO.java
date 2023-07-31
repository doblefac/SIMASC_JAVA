package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;
import java.util.List;

import com.ccb.simasc.transversal.dto.CentroDTO;

/**
 * DTO de los filtros de consulta de los reportes
 * @author cbenavides
 *
 */
public class FiltroReportesDTO {
	private Long idConciliador;
	private Date fechaInicio;
	private Date fechaFin;
	private List<String> centros;
	private Long sedePago;
	private Long sedeRadicacion;
	private Date fechaFacturacionInicio;
	private Date fechaFacturacionFin;
	private Long idServicio;
	private Long idPersona;
	private Long idConvenio;
	private Long idSede;
	private Long idCaso;
	private Date fechaRadicacionIncial;
	private Date fechaRadicacionFinal;
	private Date fechaCierreCasoInicial;
	private Date fechaCierreCasoFinal;
	private String tipoCaso;
	private List<CentroDTO> idCentros;
	private String motivoDevolucion;
	private String tipoPersona;
	private String nombreParte;
	private String nombreSolicitante;    
	private List<String> tipos;
	private Long idLista;
	private Long idCentro;
	private List<Long> idConvenios;
	private String anio;
	
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
	public FiltroReportesDTO() {
		super();
	}
	/**
	 * @return the idServicio
	 */
	public Long getIdServicio() {
		return idServicio;
	}
	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	/**
	 * @return the fechaFacturacionInicio
	 */
	public Date getFechaFacturacionInicio() {
		return fechaFacturacionInicio;
	}
	/**
	 * @param fechaFacturacionInicio the fechaFacturacionInicio to set
	 */
	public void setFechaFacturacionInicio(Date fechaFacturacionInicio) {
		this.fechaFacturacionInicio = fechaFacturacionInicio;
	}
	/**
	 * @return the fechaFacturacionFin
	 */
	public Date getFechaFacturacionFin() {
		return fechaFacturacionFin;
	}
	/**
	 * @param fechaFacturacionFin the fechaFacturacionFin to set
	 */
	public void setFechaFacturacionFin(Date fechaFacturacionFin) {
		this.fechaFacturacionFin = fechaFacturacionFin;
	}
	/**
	 * @return the idConciliador
	 */
	public Long getIdConciliador() {
		return idConciliador;
	}
	/**
	 * @param idConciliador the idConciliador to set
	 */
	public void setIdConciliador(Long idConciliador) {
		this.idConciliador = idConciliador;
	}
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the centros
	 */
	public List<String> getCentros() {
		return centros;
	}
	/**
	 * @param centros the centros to set
	 */
	public void setCentros(List<String> centros) {
		this.centros = centros;
	}
	/**
	 * @return the sedePago
	 */
	public Long getSedePago() {
		return sedePago;
	}
	/**
	 * @param sedePago the sedePago to set
	 */
	public void setSedePago(Long sedePago) {
		this.sedePago = sedePago;
	}
	/**
	 * @return the sedeRadicacion
	 */
	public Long getSedeRadicacion() {
		return sedeRadicacion;
	}
	/**
	 * @param sedeRadicacion the sedeRadicacion to set
	 */
	public void setSedeRadicacion(Long sedeRadicacion) {
		this.sedeRadicacion = sedeRadicacion;
	}
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public Long getIdConvenio() {
		return idConvenio;
	}
	public void setIdConvenio(Long idConvenio) {
		this.idConvenio = idConvenio;
	}
	/**
	 * @return the idSede
	 */
	public Long getIdSede() {
		return idSede;
	}
	/**
	 * @param idSede the idSede to set
	 */
	public void setIdSede(Long idSede) {
		this.idSede = idSede;
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
	public Date getFechaRadicacionIncial() {
		return fechaRadicacionIncial;
	}
	public void setFechaRadicacionIncial(Date fechaRadicacionIncial) {
		this.fechaRadicacionIncial = fechaRadicacionIncial;
	}
	public Date getFechaRadicacionFinal() {
		return fechaRadicacionFinal;
	}
	public void setFechaRadicacionFinal(Date fechaRadicacionFinal) {
		this.fechaRadicacionFinal = fechaRadicacionFinal;
	}
	public Date getFechaCierreCasoInicial() {
		return fechaCierreCasoInicial;
	}
	public void setFechaCierreCasoInicial(Date fechaCierreCasoInicial) {
		this.fechaCierreCasoInicial = fechaCierreCasoInicial;
	}
	public Date getFechaCierreCasoFinal() {
		return fechaCierreCasoFinal;
	}
	public void setFechaCierreCasoFinal(Date fechaCierreCasoFinal) {
		this.fechaCierreCasoFinal = fechaCierreCasoFinal;
	}
	public String getTipoCaso() {
		return tipoCaso;
	}
	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}
	public List<CentroDTO> getIdCentros() {
		return idCentros;
	}
	public void setIdCentros(List<CentroDTO> idCentros) {
		this.idCentros = idCentros;
	}
	public String getMotivoDevolucion() {
		return motivoDevolucion;
	}
	public void setMotivoDevolucion(String motivoDevolucion) {
		this.motivoDevolucion = motivoDevolucion;
	}
	public String getNombreSolicitante() {
		return nombreSolicitante;
	}
	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}
	public List<String> getTipos() {
		return tipos;
	}
	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}
	public Long getIdLista() {
		return idLista;
	}
	public void setIdLista(Long idLista) {
		this.idLista = idLista;
	}
	public Long getIdCentro() {
		return idCentro;
	}
	public void setIdCentro(Long idCentro) {
		this.idCentro = idCentro;
	}
	/**
	 * @return the idConvenios
	 */
	public List<Long> getIdConvenios() {
		return idConvenios;
	}
	/**
	 * @param idConvenios the idConvenios to set
	 */
	public void setIdConvenios(List<Long> idConvenios) {
		this.idConvenios = idConvenios;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}

	

}
