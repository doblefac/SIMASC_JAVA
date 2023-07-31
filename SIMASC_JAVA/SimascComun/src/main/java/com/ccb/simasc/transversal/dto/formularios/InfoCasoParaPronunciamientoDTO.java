package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;
import java.util.List;

import com.ccb.simasc.transversal.dto.ApoderadosParteDTO;
import com.ccb.simasc.transversal.dto.PronunciamientoDTO;


/**
 * ARB-F-052 Pronunciamiento del arbitro a la designacion. 
 * DTO con información general del caso para mostrar al arbitro que va a pronunciarse.
 * @author jsoto
 *
 */
public class InfoCasoParaPronunciamientoDTO {

	/*-------------- Información del pacto -------------------*/
	private String tipoServicio; 	
	private List<ParteCasoDTO> demandantes;	
	private List<ParteCasoDTO> demandados;
	private List<ApoderadosParteDTO> apoderadosParte;
	private Long idServicio;
	
	//Nombre del rol asignado al arbitro en el caso
	private String tipoOperador;
	//Lista con los nombres de los otros arbitros asignados al caso
	private List<String> operadores;
	//Ej. Amigable composición / Arbitral
	private String tipoPacto;	
	private String documentoPacto;	
	private String descripcionPacto;
	
	/*-------------- Información general del caso -------------------*/
	private String numeroTramite;	
	private Date fechaPago;	
	private Date fechaRadicacion;	
	private String tipoRadicacion;	
	private String tipoCaso;
	private String cuantia;	
	private String valorPretensiones;	
	//Nombre de la materia del caso
	private String materia;	
	//Nombre de la sede del caso
	private String sede;
	//Nombre completo del abogado asignado al caso
	private String abogado;
	
	private PronunciamientoDTO pronunciamiento;
	
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public List<ParteCasoDTO> getDemandantes() {
		return demandantes;
	}
	public void setDemandantes(List<ParteCasoDTO> demandantes) {
		this.demandantes = demandantes;
	}
	public List<ParteCasoDTO> getDemandados() {
		return demandados;
	}
	public void setDemandados(List<ParteCasoDTO> demandados) {
		this.demandados = demandados;
	}
	public String getTipoOperador() {
		return tipoOperador;
	}
	public void setTipoOperador(String tipoOperador) {
		this.tipoOperador = tipoOperador;
	}
	public List<String> getOperadores() {
		return operadores;
	}
	public void setOperadores(List<String> operadores) {
		this.operadores = operadores;
	}
	public String getTipoPacto() {
		return tipoPacto;
	}
	public void setTipoPacto(String tipoPacto) {
		this.tipoPacto = tipoPacto;
	}
	public String getDocumentoPacto() {
		return documentoPacto;
	}
	public void setDocumentoPacto(String documentoPacto) {
		this.documentoPacto = documentoPacto;
	}
	public String getDescripcionPacto() {
		return descripcionPacto;
	}
	public void setDescripcionPacto(String descripcionPacto) {
		this.descripcionPacto = descripcionPacto;
	}	
	
	public String getNumeroTramite() {
		return numeroTramite;
	}
	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}
	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}
	public String getTipoRadicacion() {
		return tipoRadicacion;
	}
	public void setTipoRadicacion(String tipoRadicacion) {
		this.tipoRadicacion = tipoRadicacion;
	}
	public String getTipoCaso() {
		return tipoCaso;
	}
	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}
	public String getCuantia() {
		return cuantia;
	}
	public void setCuantia(String cuantia) {
		this.cuantia = cuantia;
	}
	public String getValorPretensiones() {
		return valorPretensiones;
	}
	public void setValorPretensiones(String valorPretensiones) {
		this.valorPretensiones = valorPretensiones;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public String getAbogado() {
		return abogado;
	}
	public void setAbogado(String abogado) {
		this.abogado = abogado;
	}
	public PronunciamientoDTO getPronunciamiento() {
		return pronunciamiento;
	}
	public void setPronunciamiento(PronunciamientoDTO pronunciamiento) {
		this.pronunciamiento = pronunciamiento;
	}
	public List<ApoderadosParteDTO> getApoderadosParte() {
		return apoderadosParte;
	}
	public void setApoderadosParte(List<ApoderadosParteDTO> apoderadosParte) {
		this.apoderadosParte = apoderadosParte;
	}
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	
}
