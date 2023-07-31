package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;
import java.util.List;

import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.PagoCaso;

/**
 * DAO que contene la información del formulario para la creacion de parte representante sea
 * abogado demandante o abogado demandado.
 * Este formulario esta construido para los servicios REST
 * 
 * @author dpacho
 */
public class DatosBasicosCasoDTO {
	
	private Long idUsuario;
	private Caso caso;
	private Date fechaCierre;
	private Date fechaEntregaExpendienteCac;
	private Date fechaEnvioArchivoCentral;
	private PagoCaso pagoCaso;
	private Long idAbogado;
	private Long idAuxiliar;
	private String motivoCierre;
	private List<Dominio> tipoRadicacion;
	private List<Dominio> tipoCaso;
	private List<Dominio> tipoCuantia;
	private String nombreAuxiliar;
	private String nombreSecretario;
	private Date fechaInicioConteoTerminos;
	private int diasAntesCierreProferirLaudo;
	private int diasTranscurridos;
	private int diasFaltantes;
	private int totalDiasSuspension;
	private int totalDiasInterrupcion;
	private int totalDiasSuspensionCalendario;
	private int totalDiasInterrupcionCalendario;
	private Date fechaPosibleProferirLaudo;
	private Date fechaLimiteCierreCaso;
	private String correoSecretario;
	private Long idPresidente;
	private Long idSecretario;
	private String observaciones;
	private Long idServicio;
	private String materiaCaso;
	private String abogadoAsignado;
	private Date fechaConstitucion;
	private boolean restauraEstadoSorteabilidad;
	private String observacionesReAperturaCaso;	
	private boolean reAperturaCaso;
	
	/**
	 * ConteoTerminos
	 */
	private int diasSinSuspencionInterrupcion;
	
	public Caso getCaso() {
		return caso;
	}
	public void setCaso(Caso caso) {
		this.caso = caso;
	}
	public List<Dominio> getTipoRadicacion() {
		return tipoRadicacion;
	}
	public void setTipoRadicacion(List<Dominio> tipoRadicacion) {
		this.tipoRadicacion = tipoRadicacion;
	}
	public List<Dominio> getTipoCaso() {
		return tipoCaso;
	}
	public void setTipoCaso(List<Dominio> tipoCaso) {
		this.tipoCaso = tipoCaso;
	}
	public List<Dominio> getTipoCuantia() {
		return tipoCuantia;
	}
	public void setTipoCuantia(List<Dominio> tipoCuantia) {
		this.tipoCuantia = tipoCuantia;
	}
	public PagoCaso getPagoCaso() {
		return pagoCaso;
	}
	public void setPagoCaso(PagoCaso pagoCaso) {
		this.pagoCaso = pagoCaso;
	}
	public Long getIdAbogado() {
		return idAbogado;
	}
	public void setIdAbogado(Long idAbogado) {
		this.idAbogado = idAbogado;
	}
	public Long getIdAuxiliar() {
		return idAuxiliar;
	}
	public void setIdAuxiliar(Long idAuxiliar) {
		this.idAuxiliar = idAuxiliar;
	}
	public String getMotivoCierre() {
		return motivoCierre;
	}
	public void setMotivoCierre(String motivoCierre) {
		this.motivoCierre = motivoCierre;
	}
	public String getNombreAuxiliar() {
		return nombreAuxiliar;
	}
	public void setNombreAuxiliar(String nombreAuxiliar) {
		this.nombreAuxiliar = nombreAuxiliar;
	}
	public String getNombreSecretario() {
		return nombreSecretario;
	}
	public void setNombreSecretario(String nombreSecretario) {
		this.nombreSecretario = nombreSecretario;
	}
	public Date getFechaInicioConteoTerminos() {
		return fechaInicioConteoTerminos;
	}
	public void setFechaInicioConteoTerminos(Date fechaInicioConteoTerminos) {
		this.fechaInicioConteoTerminos = fechaInicioConteoTerminos;
	}
	public int getDiasAntesCierreProferirLaudo() {
		return diasAntesCierreProferirLaudo;
	}
	public void setDiasAntesCierreProferirLaudo(int diasAntesCierreProferirLaudo) {
		this.diasAntesCierreProferirLaudo = diasAntesCierreProferirLaudo;
	}
	public int getDiasTranscurridos() {
		return diasTranscurridos;
	}
	public void setDiasTranscurridos(int diasTranscurridos) {
		this.diasTranscurridos = diasTranscurridos;
	}
	public int getDiasFaltantes() {
		return diasFaltantes;
	}
	public void setDiasFaltantes(int diasFaltantes) {
		this.diasFaltantes = diasFaltantes;
	}
	public int getTotalDiasSuspension() {
		return totalDiasSuspension;
	}
	public void setTotalDiasSuspension(int totalDiasSuspension) {
		this.totalDiasSuspension = totalDiasSuspension;
	}
	public int getTotalDiasInterrupcion() {
		return totalDiasInterrupcion;
	}
	public void setTotalDiasInterrupcion(int totalDiasInterrupcion) {
		this.totalDiasInterrupcion = totalDiasInterrupcion;
	}
	public Date getFechaPosibleProferirLaudo() {
		return fechaPosibleProferirLaudo;
	}
	public void setFechaPosibleProferirLaudo(Date fechaPosibleProferirLaudo) {
		this.fechaPosibleProferirLaudo = fechaPosibleProferirLaudo;
	}
	public Date getFechaLimiteCierreCaso() {
		return fechaLimiteCierreCaso;
	}
	public void setFechaLimiteCierreCaso(Date fechaLimiteCierreCaso) {
		this.fechaLimiteCierreCaso = fechaLimiteCierreCaso;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public Date getFechaEntregaExpendienteCac() {
		return fechaEntregaExpendienteCac;
	}
	public void setFechaEntregaExpendienteCac(Date fechaEntregaExpendienteCac) {
		this.fechaEntregaExpendienteCac = fechaEntregaExpendienteCac;
	}
	public Date getFechaEnvioArchivoCentral() {
		return fechaEnvioArchivoCentral;
	}
	public void setFechaEnvioArchivoCentral(Date fechaEnvioArchivoCentral) {
		this.fechaEnvioArchivoCentral = fechaEnvioArchivoCentral;
	}
	public String getCorreoSecretario() {
		return correoSecretario;
	}
	public void setCorreoSecretario(String correoSecretario) {
		this.correoSecretario = correoSecretario;
	}
	public Long getIdPresidente() {
		return idPresidente;
	}
	public void setIdPresidente(Long idPresidente) {
		this.idPresidente = idPresidente;
	}
	public Long getIdSecretario() {//modificacion
		return idSecretario;
	}
	public void setIdSecretario(Long idSecretario) {
		this.idSecretario = idSecretario;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getMateriaCaso() {
		return materiaCaso;
	}
	public void setMateriaCaso(String materiaCaso) {
		this.materiaCaso = materiaCaso;
	}
	
	public String getAbogadoAsignado() {
		return abogadoAsignado;
	}
	public void setAbogadoAsignado(String abogadoAsignado) {
		this.abogadoAsignado = abogadoAsignado;
	}
	
	
	public Date getFechaConstitucion() {
		return fechaConstitucion;
	}
	public void setFechaConstitucion(Date fechaConstitucion) {
		this.fechaConstitucion = fechaConstitucion;
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
	public int getDiasSinSuspencionInterrupcion() {
		return diasSinSuspencionInterrupcion;
	}
	public void setDiasSinSuspencionInterrupcion(int diasSinSuspencionInterrupcion) {
		this.diasSinSuspencionInterrupcion = diasSinSuspencionInterrupcion;
	}
	public int getTotalDiasSuspensionCalendario() {
		return totalDiasSuspensionCalendario;
	}
	public void setTotalDiasSuspensionCalendario(int totalDiasSuspensionCalendario) {
		this.totalDiasSuspensionCalendario = totalDiasSuspensionCalendario;
	}
	public int getTotalDiasInterrupcionCalendario() {
		return totalDiasInterrupcionCalendario;
	}
	public void setTotalDiasInterrupcionCalendario(
			int totalDiasInterrupcionCalendario) {
		this.totalDiasInterrupcionCalendario = totalDiasInterrupcionCalendario;
	}
	public String getObservacionesReAperturaCaso() {
		return observacionesReAperturaCaso;
	}
	public void setObservacionesReAperturaCaso(String observacionesReAperturaCaso) {
		this.observacionesReAperturaCaso = observacionesReAperturaCaso;
	}
	public boolean isRestauraEstadoSorteabilidad() {
		return restauraEstadoSorteabilidad;
	}
	public void setRestauraEstadoSorteabilidad(boolean restauraEstadoSorteabilidad) {
		this.restauraEstadoSorteabilidad = restauraEstadoSorteabilidad;
	}
	public boolean isReAperturaCaso() {
		return reAperturaCaso;
	}
	public void setReAperturaCaso(boolean reAperturaCaso) {
		this.reAperturaCaso = reAperturaCaso;
	}
	
	
}
