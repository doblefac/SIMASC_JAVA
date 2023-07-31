package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.SolicitudPrestador;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad SolicitudPrestadorDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class SolicitudPrestadorDTO extends IDTO<SolicitudPrestador> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private List<Long> idCentros;
	private RolPersona rolPersona;
	private String nombreCompleto;
	// protected region atributos end
	private Long idSolicitud;

	private String estadoSolicitud;		
	private String tipo;		
	private Date fechaSolicitud;		
	private String observaciones;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idRolPersona;		
	private Long idDocumento;		
	private String descripcionSolicitud;		
	
    public SolicitudPrestadorDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdSolicitud(){
		return this.idSolicitud;
	}
	
	public void setIdSolicitud(Long idSolicitud){
		this.idSolicitud = idSolicitud;
	}
	
	public String getEstadoSolicitud(){
		return this.estadoSolicitud;
	}
	
	public void setEstadoSolicitud(String estadoSolicitud){
		this.estadoSolicitud = estadoSolicitud;
	}
		
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public Date getFechaSolicitud(){
		return this.fechaSolicitud;
	}
	
	public void setFechaSolicitud(Date fechaSolicitud){
		this.fechaSolicitud = fechaSolicitud;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
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
		
	public Long getIdRolPersona(){
		return this.idRolPersona;
	}
	
	public void setIdRolPersona(Long idRolPersona){
		this.idRolPersona = idRolPersona;
	}
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		
	public String getDescripcionSolicitud(){
		return this.descripcionSolicitud;
	}
	
	public void setDescripcionSolicitud(String descripcionSolicitud){
		this.descripcionSolicitud = descripcionSolicitud;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idSolicitud);        
        hash = 37 * hash + Objects.hashCode(this.estadoSolicitud);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.fechaSolicitud);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idRolPersona);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.descripcionSolicitud);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad SolicitudPrestadorDTO que se pasa
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
        final SolicitudPrestadorDTO other = (SolicitudPrestadorDTO) obj;
                
        if (!Objects.equals(this.idSolicitud, other.idSolicitud)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoSolicitud, other.estadoSolicitud)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaSolicitud, other.fechaSolicitud)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
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
        
        if (!Objects.equals(this.idRolPersona, other.idRolPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        return Objects.equals(this.descripcionSolicitud, other.descripcionSolicitud);
                
    }
    
    @Override
	public SolicitudPrestadorDTO transformarSinDependencias(SolicitudPrestador obj) {
		SolicitudPrestadorDTO solicitudPrestadorDTO = new SolicitudPrestadorDTO();
		
		solicitudPrestadorDTO.setIdSolicitud(obj.getIdSolicitud());
		solicitudPrestadorDTO.setEstadoSolicitud(obj.getEstadoSolicitud());
		solicitudPrestadorDTO.setTipo(obj.getTipo());
		solicitudPrestadorDTO.setFechaSolicitud(obj.getFechaSolicitud());
		solicitudPrestadorDTO.setObservaciones(obj.getObservaciones());
		solicitudPrestadorDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		solicitudPrestadorDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		solicitudPrestadorDTO.setEstadoRegistro(obj.getEstadoRegistro());
		solicitudPrestadorDTO.setIdRolPersona(obj.getIdRolPersona());
		solicitudPrestadorDTO.setIdDocumento(obj.getIdDocumento());
		solicitudPrestadorDTO.setDescripcionSolicitud(obj.getDescripcionSolicitud());
		
		return solicitudPrestadorDTO;
	}

	@Override
	public SolicitudPrestadorDTO transformarConDependencias(SolicitudPrestador obj) {
		SolicitudPrestadorDTO solicitudPrestadorDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return solicitudPrestadorDTO;
	}

	@Override
	public SolicitudPrestador transformarEntidadSinDependencias(SolicitudPrestador obj) {
		SolicitudPrestador solicitudPrestador = new SolicitudPrestador();
		
		solicitudPrestador.setIdSolicitud(obj.getIdSolicitud());
		
		solicitudPrestador.setEstadoSolicitud(obj.getEstadoSolicitud());
		solicitudPrestador.setTipo(obj.getTipo());
		solicitudPrestador.setFechaSolicitud(obj.getFechaSolicitud());
		solicitudPrestador.setObservaciones(obj.getObservaciones());
		solicitudPrestador.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		solicitudPrestador.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		solicitudPrestador.setEstadoRegistro(obj.getEstadoRegistro());
		solicitudPrestador.setIdRolPersona(obj.getIdRolPersona());
		solicitudPrestador.setIdDocumento(obj.getIdDocumento());
		solicitudPrestador.setDescripcionSolicitud(obj.getDescripcionSolicitud());
		
		return solicitudPrestador;
	}


	@Override
	public SolicitudPrestador transformarEntidadConDependencias(SolicitudPrestador obj) {
		SolicitudPrestador solicitudPrestador = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		if( obj.getRolPersona() != null )
			solicitudPrestador.setRolPersona(new RolPersonaDTO().transformarEntidadConDependencias(obj.getRolPersona())); 

		// protected region modificaciones transformarEntidadConDependencias end
		
		return solicitudPrestador;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<SolicitudPrestador> coleccion) {
		List<SolicitudPrestadorDTO> solicitudPrestadorDTOList = new ArrayList<>();
		for(SolicitudPrestador c : coleccion)
			solicitudPrestadorDTOList.add(transformarConDependencias(c));
		return solicitudPrestadorDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<SolicitudPrestador> coleccion) {
		List<SolicitudPrestadorDTO> solicitudPrestadorDTOList = new ArrayList<>();
		for(SolicitudPrestador c : coleccion)
			solicitudPrestadorDTOList.add(transformarSinDependencias(c));
		return solicitudPrestadorDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<SolicitudPrestador> coleccion) {
		List<SolicitudPrestador> solicitudPrestadorList = new ArrayList<>();
		for(SolicitudPrestador c : coleccion)
			solicitudPrestadorList.add(transformarEntidadConDependencias(c));
		return solicitudPrestadorList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<SolicitudPrestador> coleccion) {
		List<SolicitudPrestador> solicitudPrestadorList = new ArrayList<>();
		for(SolicitudPrestador c : coleccion)
			solicitudPrestadorList.add(transformarEntidadSinDependencias(c));
		return solicitudPrestadorList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public List<Long> getIdCentros() {
		return idCentros;
	}

	public void setIdCentros(List<Long> idCentros) {
		this.idCentros = idCentros;
	}

	public RolPersona getRolPersona() {
		return rolPersona;
	}

	public void setRolPersona(RolPersona rolPersona) {
		this.rolPersona = rolPersona;
	}
	
	/**
	 * Metodo que convierte un solicitudPrestadorDTO a entidad SolicitudPrestador sin dependencias
	 * @param solicitudPrestadorDTO
	 * @return SolicitudPrestador
	 */
	public static SolicitudPrestador convertirDTOaEntidadSinDependencias( SolicitudPrestadorDTO solicitudPrestadorDTO ){
		SolicitudPrestador solicitudPrestador = new SolicitudPrestador();
		solicitudPrestador.setIdDocumento(solicitudPrestadorDTO.getIdDocumento());
		solicitudPrestador.setEstadoRegistro(solicitudPrestadorDTO.getEstadoRegistro());
		solicitudPrestador.setEstadoSolicitud(solicitudPrestadorDTO.getEstadoSolicitud());
		solicitudPrestador.setFechaSolicitud(solicitudPrestadorDTO.getFechaSolicitud());
		solicitudPrestador.setIdSolicitud(solicitudPrestadorDTO.getIdSolicitud());
		solicitudPrestador.setObservaciones(solicitudPrestadorDTO.getObservaciones());
		solicitudPrestador.setIdRolPersona(solicitudPrestadorDTO.getIdRolPersona());
		solicitudPrestador.setTipo(solicitudPrestadorDTO.getTipo());

		return solicitudPrestador;
	}



	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}



	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	

	// protected region metodos adicionales end

	
}
