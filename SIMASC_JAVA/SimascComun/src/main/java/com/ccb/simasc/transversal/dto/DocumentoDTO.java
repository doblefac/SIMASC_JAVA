package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.Documento;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad DocumentoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class DocumentoDTO extends IDTO<Documento> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	private String nombreCaso;
	// protected region atributos end
	private Long idDocumento;

	private String nombre;		
	private String tipoDocumento;		
	private boolean publicado;		
	private String tipoArchivo;		
	private String estadoDigitalizacion;		
	private Date fechaAsignacion;		
	private Date fechaDigitalizacion;		
	private String descripcion;		
	private String observaciones;		
	private Long numeroFolios;		
	private boolean radicado;		
	private Date fechaRadicacion;		
	private Long codigoGestorDocumental;		
	private String url;
	private boolean nuevo;
	private Integer duracion;		
	private Date fechaDeGrabacion;		
	private Integer numeroDePista;		
	private Integer version;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idCaso;		
	private Long idAudiencia;		
	private Long idDigitalizador;		
	private Long idCarpeta;		
	private Long idRolRemitente;		
	private Long idPersonaRemitente;		
	private Long idCasoRemitente;		
	private Long idPersonaDocumento;		
	private Long idSolicitudServicio;
	private Long idPeticion;		
	private Long idEventoCcb;
	private Date fechaCargue;
	private String estado;
	
    public DocumentoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getTipoDocumento(){
		return this.tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento){
		this.tipoDocumento = tipoDocumento;
	}
		
	public boolean getPublicado(){
		return this.publicado;
	}
	
	public void setPublicado(boolean publicado){
		this.publicado = publicado;
	}
		
	public String getTipoArchivo(){
		return this.tipoArchivo;
	}
	
	public void setTipoArchivo(String tipoArchivo){
		this.tipoArchivo = tipoArchivo;
	}
		
	public String getEstadoDigitalizacion(){
		return this.estadoDigitalizacion;
	}
	
	public void setEstadoDigitalizacion(String estadoDigitalizacion){
		this.estadoDigitalizacion = estadoDigitalizacion;
	}
		
	public Date getFechaAsignacion(){
		return this.fechaAsignacion;
	}
	
	public void setFechaAsignacion(Date fechaAsignacion){
		this.fechaAsignacion = fechaAsignacion;
	}
		
	public Date getFechaDigitalizacion(){
		return this.fechaDigitalizacion;
	}
	
	public void setFechaDigitalizacion(Date fechaDigitalizacion){
		this.fechaDigitalizacion = fechaDigitalizacion;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public Long getNumeroFolios(){
		return this.numeroFolios;
	}
	
	public void setNumeroFolios(Long numeroFolios){
		this.numeroFolios = numeroFolios;
	}
		
	public boolean getRadicado(){
		return this.radicado;
	}
	
	public void setRadicado(boolean radicado){
		this.radicado = radicado;
	}
		
	public Date getFechaRadicacion(){
		return this.fechaRadicacion;
	}
	
	public void setFechaRadicacion(Date fechaRadicacion){
		this.fechaRadicacion = fechaRadicacion;
	}
		
	public Long getCodigoGestorDocumental(){
		return this.codigoGestorDocumental;
	}
	
	public void setCodigoGestorDocumental(Long codigoGestorDocumental){
		this.codigoGestorDocumental = codigoGestorDocumental;
	}
		
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
		
	public Integer getDuracion(){
		return this.duracion;
	}
	
	public void setDuracion(Integer duracion){
		this.duracion = duracion;
	}
		
	public Date getFechaDeGrabacion(){
		return this.fechaDeGrabacion;
	}
	
	public void setFechaDeGrabacion(Date fechaDeGrabacion){
		this.fechaDeGrabacion = fechaDeGrabacion;
	}
		
	public Integer getNumeroDePista(){
		return this.numeroDePista;
	}
	
	public void setNumeroDePista(Integer numeroDePista){
		this.numeroDePista = numeroDePista;
	}
		
	public Integer getVersion(){
		return this.version;
	}
	
	public void setVersion(Integer version){
		this.version = version;
	}
		
	public String getIdUsuarioModificacion(){
		return this.idUsuarioModificacion;
	}
	
	public void setIdUsuarioModificacion(String idUsuarioModificacion){
		this.idUsuarioModificacion = idUsuarioModificacion;
	}
		
	public Date getFechaUltimaModificacion(){
		return this.fechaUltimaModificacion;
	}
	
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion){
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
		
	public String getEstadoRegistro(){
		return this.estadoRegistro;
	}
	
	public void setEstadoRegistro(String estadoRegistro){
		this.estadoRegistro = estadoRegistro;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
		
	public Long getIdDigitalizador(){
		return this.idDigitalizador;
	}
	
	public void setIdDigitalizador(Long idDigitalizador){
		this.idDigitalizador = idDigitalizador;
	}
		
	public Long getIdCarpeta(){
		return this.idCarpeta;
	}
	
	public void setIdCarpeta(Long idCarpeta){
		this.idCarpeta = idCarpeta;
	}
		
	public Long getIdRolRemitente(){
		return this.idRolRemitente;
	}
	
	public void setIdRolRemitente(Long idRolRemitente){
		this.idRolRemitente = idRolRemitente;
	}
		
	public Long getIdPersonaRemitente(){
		return this.idPersonaRemitente;
	}
	
	public void setIdPersonaRemitente(Long idPersonaRemitente){
		this.idPersonaRemitente = idPersonaRemitente;
	}
		
	public Long getIdCasoRemitente(){
		return this.idCasoRemitente;
	}
	
	public void setIdCasoRemitente(Long idCasoRemitente){
		this.idCasoRemitente = idCasoRemitente;
	}
		
	public Long getIdPersonaDocumento(){
		return this.idPersonaDocumento;
	}
	
	public void setIdPersonaDocumento(Long idPersonaDocumento){
		this.idPersonaDocumento = idPersonaDocumento;
	}
		
	public Long getIdSolicitudServicio(){
		return this.idSolicitudServicio;
	}
	
	public void setIdSolicitudServicio(Long idSolicitudServicio){
		this.idSolicitudServicio = idSolicitudServicio;
	}
		
	public Long getIdPeticion(){
		return this.idPeticion;
	}
	
	public void setIdPeticion(Long idPeticion){
		this.idPeticion = idPeticion;
	}
		
	public Long getIdEventoCcb(){
		return this.idEventoCcb;
	}
	
	public void setIdEventoCcb(Long idEventoCcb){
		this.idEventoCcb = idEventoCcb;
	}
		
	public Date getFechaCargue(){
		return this.fechaCargue;
	}
	
	public void setFechaCargue(Date fechaCargue){
		this.fechaCargue = fechaCargue;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}



	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}



	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idDocumento);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.tipoDocumento);
        hash = 37 * hash + (this.publicado ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.tipoArchivo);
        hash = 37 * hash + Objects.hashCode(this.estadoDigitalizacion);
        hash = 37 * hash + Objects.hashCode(this.fechaAsignacion);
        hash = 37 * hash + Objects.hashCode(this.fechaDigitalizacion);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.numeroFolios);
        hash = 37 * hash + (this.radicado ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.fechaRadicacion);
        hash = 37 * hash + Objects.hashCode(this.codigoGestorDocumental);
        hash = 37 * hash + Objects.hashCode(this.url);
        hash = 37 * hash + Objects.hashCode(this.duracion);
        hash = 37 * hash + Objects.hashCode(this.fechaDeGrabacion);
        hash = 37 * hash + Objects.hashCode(this.numeroDePista);
        hash = 37 * hash + Objects.hashCode(this.version);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idDigitalizador);
        hash = 37 * hash + Objects.hashCode(this.idCarpeta);
        hash = 37 * hash + Objects.hashCode(this.idRolRemitente);
        hash = 37 * hash + Objects.hashCode(this.idPersonaRemitente);
        hash = 37 * hash + Objects.hashCode(this.idCasoRemitente);
        hash = 37 * hash + Objects.hashCode(this.idPersonaDocumento);
        hash = 37 * hash + Objects.hashCode(this.idSolicitudServicio);
        hash = 37 * hash + Objects.hashCode(this.idPeticion);
        hash = 37 * hash + Objects.hashCode(this.idEventoCcb);
        hash = 37 * hash + Objects.hashCode(this.fechaCargue);
        hash = 37 * hash + Objects.hashCode(this.estado);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DocumentoDTO que se pasa
     * como parametro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relacion con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * iguales.
     * @return Verdadero si esta instancia y la que se pasan como parametros son
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DocumentoDTO other = (DocumentoDTO) obj;
                
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDocumento, other.tipoDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.publicado, other.publicado)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoArchivo, other.tipoArchivo)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoDigitalizacion, other.estadoDigitalizacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaAsignacion, other.fechaAsignacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDigitalizacion, other.fechaDigitalizacion)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroFolios, other.numeroFolios)) {
            return false;
        }
        
        if (!Objects.equals(this.radicado, other.radicado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRadicacion, other.fechaRadicacion)) {
            return false;
        }
        
        if (!Objects.equals(this.codigoGestorDocumental, other.codigoGestorDocumental)) {
            return false;
        }
        
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        
        if (!Objects.equals(this.duracion, other.duracion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeGrabacion, other.fechaDeGrabacion)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroDePista, other.numeroDePista)) {
            return false;
        }
        
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoRegistro, other.estadoRegistro)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.idDigitalizador, other.idDigitalizador)) {
            return false;
        }
        
        if (!Objects.equals(this.idCarpeta, other.idCarpeta)) {
            return false;
        }
        
        if (!Objects.equals(this.idRolRemitente, other.idRolRemitente)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaRemitente, other.idPersonaRemitente)) {
            return false;
        }
        
        if (!Objects.equals(this.idCasoRemitente, other.idCasoRemitente)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaDocumento, other.idPersonaDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.idSolicitudServicio, other.idSolicitudServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.idPeticion, other.idPeticion)) {
            return false;
        }
        
		if (!Objects.equals(this.idEventoCcb, other.idEventoCcb)) {
			return false;
		}
		
		if (!Objects.equals(this.fechaCargue, other.fechaCargue)) {
			return false;
		}
        
        return Objects.equals(this.estado, other.estado);
        
                
    }
    
    @Override
	public DocumentoDTO transformarSinDependencias(Documento obj) {
		DocumentoDTO documentoDTO = new DocumentoDTO();
		
		documentoDTO.setIdDocumento(obj.getIdDocumento());
		documentoDTO.setNombre(obj.getNombre());
		documentoDTO.setTipoDocumento(obj.getTipoDocumento());
		documentoDTO.setPublicado(obj.getPublicado());
		documentoDTO.setTipoArchivo(obj.getTipoArchivo());
		documentoDTO.setEstadoDigitalizacion(obj.getEstadoDigitalizacion());
		documentoDTO.setFechaAsignacion(obj.getFechaAsignacion());
		documentoDTO.setFechaDigitalizacion(obj.getFechaDigitalizacion());
		documentoDTO.setDescripcion(obj.getDescripcion());
		documentoDTO.setObservaciones(obj.getObservaciones());
		documentoDTO.setNumeroFolios(obj.getNumeroFolios());
		documentoDTO.setRadicado(obj.getRadicado());
		documentoDTO.setFechaRadicacion(obj.getFechaRadicacion());
		documentoDTO.setCodigoGestorDocumental(obj.getCodigoGestorDocumental());
		documentoDTO.setUrl(obj.getUrl());
		documentoDTO.setDuracion(obj.getDuracion());
		documentoDTO.setFechaDeGrabacion(obj.getFechaDeGrabacion());
		documentoDTO.setNumeroDePista(obj.getNumeroDePista());
		documentoDTO.setVersion(obj.getVersion());
		documentoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		documentoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		documentoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		documentoDTO.setIdCaso(obj.getIdCaso());
		documentoDTO.setIdAudiencia(obj.getIdAudiencia());
		documentoDTO.setIdDigitalizador(obj.getIdDigitalizador());
		documentoDTO.setIdCarpeta(obj.getIdCarpeta());
		documentoDTO.setIdRolRemitente(obj.getIdRolRemitente());
		documentoDTO.setIdPersonaRemitente(obj.getIdPersonaRemitente());
		documentoDTO.setIdCasoRemitente(obj.getIdCasoRemitente());
		documentoDTO.setIdPersonaDocumento(obj.getIdPersonaDocumento());
		documentoDTO.setIdSolicitudServicio(obj.getIdSolicitudServicio());
		documentoDTO.setIdPeticion(obj.getIdPeticion());
		documentoDTO.setIdEventoCcb(obj.getIdEventoCcb());
		documentoDTO.setFechaCargue(obj.getFechaCargue());
		documentoDTO.setEstado(obj.getEstado());
		
		return documentoDTO;
	}

	@Override
	public DocumentoDTO transformarConDependencias(Documento obj) {
		DocumentoDTO documentoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return documentoDTO;
	}

	@Override
	public Documento transformarEntidadSinDependencias(Documento obj) {
		Documento documento = new Documento();
		
		documento.setIdDocumento(obj.getIdDocumento());
		
		documento.setNombre(obj.getNombre());
		documento.setTipoDocumento(obj.getTipoDocumento());
		documento.setPublicado(obj.getPublicado());
		documento.setTipoArchivo(obj.getTipoArchivo());
		documento.setEstadoDigitalizacion(obj.getEstadoDigitalizacion());
		documento.setFechaAsignacion(obj.getFechaAsignacion());
		documento.setFechaDigitalizacion(obj.getFechaDigitalizacion());
		documento.setDescripcion(obj.getDescripcion());
		documento.setObservaciones(obj.getObservaciones());
		documento.setNumeroFolios(obj.getNumeroFolios());
		documento.setRadicado(obj.getRadicado());
		documento.setFechaRadicacion(obj.getFechaRadicacion());
		documento.setCodigoGestorDocumental(obj.getCodigoGestorDocumental());
		documento.setUrl(obj.getUrl());
		documento.setDuracion(obj.getDuracion());
		documento.setFechaDeGrabacion(obj.getFechaDeGrabacion());
		documento.setNumeroDePista(obj.getNumeroDePista());
		documento.setVersion(obj.getVersion());
		documento.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		documento.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		documento.setEstadoRegistro(obj.getEstadoRegistro());
		documento.setIdCaso(obj.getIdCaso());
		documento.setIdAudiencia(obj.getIdAudiencia());
		documento.setIdDigitalizador(obj.getIdDigitalizador());
		documento.setIdCarpeta(obj.getIdCarpeta());
		documento.setIdRolRemitente(obj.getIdRolRemitente());
		documento.setIdPersonaRemitente(obj.getIdPersonaRemitente());
		documento.setIdCasoRemitente(obj.getIdCasoRemitente());
		documento.setIdPersonaDocumento(obj.getIdPersonaDocumento());
		documento.setIdSolicitudServicio(obj.getIdSolicitudServicio());
		documento.setIdPeticion(obj.getIdPeticion());
		documento.setIdEventoCcb(obj.getIdEventoCcb());
		documento.setFechaCargue(obj.getFechaCargue());
		documento.setEstado(obj.getEstado());
		
		return documento;
	}


	@Override
	public Documento transformarEntidadConDependencias(Documento obj) {
		Documento documento = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		CarpetaDTO transformadorCarpeta = new CarpetaDTO();
		if (obj.getCarpeta() != null)
			documento.setCarpeta(transformadorCarpeta.transformarEntidadConDependencias(obj.getCarpeta()));
		
		// protected region modificaciones transformarEntidadConDependencias end
		
		return documento;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Documento> coleccion) {
		List<DocumentoDTO> documentoDTOList = new ArrayList<>();
		for(Documento c : coleccion)
			documentoDTOList.add(transformarConDependencias(c));
		return documentoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Documento> coleccion) {
		List<DocumentoDTO> documentoDTOList = new ArrayList<>();
		for(Documento c : coleccion)
			documentoDTOList.add(transformarSinDependencias(c));
		return documentoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Documento> coleccion) {
		List<Documento> documentoList = new ArrayList<>();
		for(Documento c : coleccion)
			documentoList.add(transformarEntidadConDependencias(c));
		return documentoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Documento> coleccion) {
		List<Documento> documentoList = new ArrayList<>();
		for(Documento c : coleccion)
			documentoList.add(transformarEntidadSinDependencias(c));
		return documentoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	public String getNombreCaso() {
		return nombreCaso;
	}

	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	
	public Documento transformarEntidadSinDependencias(DocumentoDTO obj) {
		Documento documento = new Documento();
		
		documento.setIdDocumento(obj.getIdDocumento());
		
		documento.setNombre(obj.getNombre());
		documento.setTipoDocumento(obj.getTipoDocumento());
		documento.setEstadoDigitalizacion(obj.getEstadoDigitalizacion());
		documento.setPublicado(obj.getPublicado());
		documento.setFechaAsignacion(obj.getFechaAsignacion());
		documento.setFechaDigitalizacion(obj.getFechaDigitalizacion());
		documento.setDescripcion(obj.getDescripcion());
		documento.setObservaciones(obj.getObservaciones());
		documento.setNumeroFolios(obj.getNumeroFolios());
		documento.setRadicado(obj.getRadicado());
		documento.setFechaRadicacion(obj.getFechaRadicacion());
		documento.setCodigoGestorDocumental(obj.getCodigoGestorDocumental());
		//documento.setCuaderno(obj.getCuaderno());
		documento.setUrl(obj.getUrl());
		documento.setTipoArchivo(obj.getTipoArchivo());
		documento.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		documento.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		documento.setEstadoRegistro(obj.getEstadoRegistro());
		documento.setIdCaso(obj.getIdCaso());
		documento.setIdAudiencia(obj.getIdAudiencia());
		documento.setIdDigitalizador(obj.getIdDigitalizador());
		documento.setDuracion(obj.getDuracion());
		documento.setFechaDeGrabacion(obj.getFechaDeGrabacion());
		documento.setNumeroDePista(obj.getNumeroDePista());
		documento.setVersion(obj.getVersion());
		documento.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		documento.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		documento.setEstadoRegistro(obj.getEstadoRegistro());
		documento.setIdCaso(obj.getIdCaso());
		documento.setIdAudiencia(obj.getIdAudiencia());
		documento.setIdDigitalizador(obj.getIdDigitalizador());
		documento.setIdCarpeta(obj.getIdCarpeta());
		documento.setIdRolRemitente(obj.getIdRolRemitente());
		documento.setIdPersonaRemitente(obj.getIdPersonaRemitente());
		documento.setIdCasoRemitente(obj.getIdCasoRemitente());
		documento.setIdPersonaDocumento(obj.getIdPersonaDocumento());
		documento.setIdSolicitudServicio(obj.getIdSolicitudServicio());
		documento.setIdPeticion(obj.getIdPeticion());
		documento.setIdEventoCcb(obj.getIdEventoCcb());
		documento.setFechaCargue(obj.getFechaCargue());
		documento.setEstado(obj.getEstado());
		
		return documento;
	}
	
	// protected region metodos adicionales end

}
