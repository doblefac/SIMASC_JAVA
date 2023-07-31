package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;
import java.util.List;

import com.ccb.simasc.transversal.dto.DocumentoDTO;

public class ParametrosCarpetaDTO {
	private long idCaso;
	private String descripcion;
	private Long numeroFolios;
	private Date fechaRadicacion;
	private long idRemitente;
	private boolean publicado;
	private long idCuaderno;
	private long idCarpeta;
	private boolean notificarPartes;
	private String tipoNotificacion;
	private String norma;
	private String asunto;
	private String termino;
	private Date fechaInicio;
	private Date fechaFin;
	private String providencia;
	private Date fechaNotificacion;
	private boolean enviarCorreoCertificado;
	private boolean remplazarDocumento;
	private DocumentoDTO documento;
	private String nombreDocumento;
	private String tipoArchivo;
	private long idDocumento;
	private long idDocumentoAnterior;
	private List<PersonaBasicaDTO> personasTraslado;
		
	public long getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(long idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getNumeroFolios() {
		return numeroFolios;
	}
	public void setNumeroFolios(Long numeroFolios) {
		this.numeroFolios = numeroFolios;
	}
	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}
	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}
	public long getIdRemitente() {
		return idRemitente;
	}
	public void setIdRemitente(long idRemitente) {
		this.idRemitente = idRemitente;
	}
	public boolean isPublicado() {
		return publicado;
	}
	public void setPublicado(boolean publicado) {
		this.publicado = publicado;
	}

	public long getIdCuaderno() {
		return idCuaderno;
	}
	public void setIdCuaderno(long idCuaderno) {
		this.idCuaderno = idCuaderno;
	}
	public long getIdCarpeta() {
		return idCarpeta;
	}
	public void setIdCarpeta(long idCarpeta) {
		this.idCarpeta = idCarpeta;
	}
	public boolean isNotificarPartes() {
		return notificarPartes;
	}
	public void setNotificarPartes(boolean notificarPartes) {
		this.notificarPartes = notificarPartes;
	}
	public String getTipoNotificacion() {
		return tipoNotificacion;
	}
	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}
	public String getNorma() {
		return norma;
	}
	public void setNorma(String norma) {
		this.norma = norma;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getTermino() {
		return termino;
	}
	public void setTermino(String termino) {
		this.termino = termino;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getProvidencia() {
		return providencia;
	}
	public void setProvidencia(String providencia) {
		this.providencia = providencia;
	}
	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}
	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}
	public boolean isEnviarCorreoCertificado() {
		return enviarCorreoCertificado;
	}
	public void setEnviarCorreoCertificado(boolean enviarCorreoCertificado) {
		this.enviarCorreoCertificado = enviarCorreoCertificado;
	}
	public long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(long idCaso) {
		this.idCaso = idCaso;
	}
	public long getIdDocumentoAnterior() {
		return idDocumentoAnterior;
	}
	public void setIdDocumentoAnterior(long idDocumentoAnterior) {
		this.idDocumentoAnterior = idDocumentoAnterior;
	}
	public boolean isRemplazarDocumento() {
		return remplazarDocumento;
	}
	public void setRemplazarDocumento(boolean remplazarDocumento) {
		this.remplazarDocumento = remplazarDocumento;
	}
	public DocumentoDTO getDocumento() {
		return documento;
	}
	public void setDocumento(DocumentoDTO documento) {
		this.documento = documento;
	}
	public List<PersonaBasicaDTO> getPersonasTraslado() {
		return personasTraslado;
	}
	public void setPersonasTraslado(List<PersonaBasicaDTO> personasTraslado) {
		this.personasTraslado = personasTraslado;
	}
	
}
