package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DAO que contiene la información del formulario para la creacion de parte representante sea
 * abogado demandante o abogado demandado.
 * Este formulario esta construido para los servicios REST
 * 
 * @author dpachon
 */
@XmlRootElement
public class RegistroRecusacionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idRecusacion;
	private Long idCaso;
	private Long idRecusado;
	private String motivo;
	private List<Long> idRecusan;
	private Long idDocumentoRecusacion;
	private Long idUsuario;
	private Date fechaRespuesta;
	private boolean aceptaRecusacion;
	private Long idDocumentoRespuesta;
	private Date fechaConfirmacion;
	private boolean confirmaNombramiento;
	private String juzgadoConfirmacion;
	private Long idDocumentoConfirmacion;
	private List<Long> idRecusados;
	
	
	public Long getIdRecusado() {
		return idRecusado;
	}
	public void setIdRecusado(Long idRecusado) {
		this.idRecusado = idRecusado;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public List<Long> getIdRecusan() {
		return idRecusan;
	}
	public void setIdRecusan(List<Long> idRecusan) {
		this.idRecusan = idRecusan;
	}
	public Long getIdDocumentoRecusacion() {
		return idDocumentoRecusacion;
	}
	public void setIdDocumentoRecusacion(Long idDocumento) {
		this.idDocumentoRecusacion = idDocumento;
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
	public Long getIdRecusacion() {
		return idRecusacion;
	}
	public void setIdRecusacion(Long idRecusacion) {
		this.idRecusacion = idRecusacion;
	}
	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}
	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}
	public boolean isAceptaRecusacion() {
		return aceptaRecusacion;
	}
	public void setAceptaRecusacion(boolean aceptaRecusacion) {
		this.aceptaRecusacion = aceptaRecusacion;
	}
	public Long getIdDocumentoRespuesta() {
		return idDocumentoRespuesta;
	}
	public void setIdDocumentoRespuesta(Long idDocumentoRespuesta) {
		this.idDocumentoRespuesta = idDocumentoRespuesta;
	}
	public Date getFechaConfirmacion() {
		return fechaConfirmacion;
	}
	public void setFechaConfirmacion(Date fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}
	public boolean isConfirmaNombramiento() {
		return confirmaNombramiento;
	}
	public void setConfirmaNombramiento(boolean confirmaNombramiento) {
		this.confirmaNombramiento = confirmaNombramiento;
	}
	public String getJuzgadoConfirmacion() {
		return juzgadoConfirmacion;
	}
	public void setJuzgadoConfirmacion(String juzgadoConfirmacion) {
		this.juzgadoConfirmacion = juzgadoConfirmacion;
	}
	public Long getIdDocumentoConfirmacion() {
		return idDocumentoConfirmacion;
	}
	public void setIdDocumentoConfirmacion(Long idDocumentoConfirmacion) {
		this.idDocumentoConfirmacion = idDocumentoConfirmacion;
	}
	/**
	 * @return the idRecusados
	 */
	public List<Long> getIdRecusados() {
		return idRecusados;
	}
	/**
	 * @param idRecusados the idRecusados to set
	 */
	public void setIdRecusados(List<Long> idRecusados) {
		this.idRecusados = idRecusados;
	}
	
	
				
}
