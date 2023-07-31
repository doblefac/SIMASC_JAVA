/**
 * 
 */
package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;
import java.util.List;

import com.ccb.simasc.transversal.entidades.Caso;

/**
 * @author dbarrera
 *
 */
public class RadicarCasoConvenioDTO {
	private Caso caso;
	private List<FormularioParteDTO> parteInfo;
	private Long cuantia;
	private Long conciliador;
	private String tipoAudiencia;
	private String sede;
	private String centro;
	private Date fechaAudiencia;
	private Date fechaHoraInicio;
	private Date fechaHoraFin;
	private Date fechaRadicacion;
	
	
	private CondicionesGeneralesDTO condicionesGeneralesDTO;
	
	public Caso getCaso() {
		return caso;
	}
	public void setCaso(Caso caso) {
		this.caso = caso;
	}
	public List<FormularioParteDTO> getParteInfo() {
		return parteInfo;
	}
	public void setParteInfo(List<FormularioParteDTO> parteInfo) {
		this.parteInfo = parteInfo;
	}
	public Long getCuantia() {
		return cuantia;
	}
	public void setCuantia(Long cuantia) {
		this.cuantia = cuantia;
	}
	public Long getConciliador() {
		return conciliador;
	}
	public void setConciliador(Long conciliador) {
		this.conciliador = conciliador;
	}
	public String getTipoAudiencia() {
		return tipoAudiencia;
	}
	public void setTipoAudiencia(String tipoAudiencia) {
		this.tipoAudiencia = tipoAudiencia;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public Date getFechaAudiencia() {
		return fechaAudiencia;
	}
	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}
	public Date getFechaHoraInicio() {
		return fechaHoraInicio;
	}
	public void setFechaHoraInicio(Date fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}
	public Date getFechaHoraFin() {
		return fechaHoraFin;
	}
	public void setFechaHoraFin(Date fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}
	public CondicionesGeneralesDTO getCondicionesGeneralesDTO() {
		return condicionesGeneralesDTO;
	}
	public void setCondicionesGeneralesDTO(CondicionesGeneralesDTO condicionesGeneralesDTO) {
		this.condicionesGeneralesDTO = condicionesGeneralesDTO;
	}
	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}
	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}
	public String getCentro() {
		return centro;
	}
	public void setCentro(String centro) {
		this.centro = centro;
	}
	
	
}
