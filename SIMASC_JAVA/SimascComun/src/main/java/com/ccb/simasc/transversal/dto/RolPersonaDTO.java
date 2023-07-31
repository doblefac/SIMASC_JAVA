package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersona;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad RolPersonaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class RolPersonaDTO extends IDTO<RolPersona> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	private String nombreRol;
	private String tipoServicio;
	private String estadoRolServicio;
	// protected region atributos end
	private Long idRolPersona;

	private Date fechaInicioVigencia;		
	private Date fechaFinVigencia;		
	private String actaAprobacion;		
	private Date fechaActaAprobacion;		
	private boolean reportadoSicaac;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idRol;		
	private Long idPersona;		
	private Long idCentro;		
	private Long idLista;		
	
    public RolPersonaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdRolPersona(){
		return this.idRolPersona;
	}
	
	public void setIdRolPersona(Long idRolPersona){
		this.idRolPersona = idRolPersona;
	}
	
	public Date getFechaInicioVigencia(){
		return this.fechaInicioVigencia;
	}
	
	public void setFechaInicioVigencia(Date fechaInicioVigencia){
		this.fechaInicioVigencia = fechaInicioVigencia;
	}
		
	public Date getFechaFinVigencia(){
		return this.fechaFinVigencia;
	}
	
	public void setFechaFinVigencia(Date fechaFinVigencia){
		this.fechaFinVigencia = fechaFinVigencia;
	}
		
	public String getActaAprobacion(){
		return this.actaAprobacion;
	}
	
	public void setActaAprobacion(String actaAprobacion){
		this.actaAprobacion = actaAprobacion;
	}
		
	public Date getFechaActaAprobacion(){
		return this.fechaActaAprobacion;
	}
	
	public void setFechaActaAprobacion(Date fechaActaAprobacion){
		this.fechaActaAprobacion = fechaActaAprobacion;
	}
		
	public boolean getReportadoSicaac(){
		return this.reportadoSicaac;
	}
	
	public void setReportadoSicaac(boolean reportadoSicaac){
		this.reportadoSicaac = reportadoSicaac;
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
		
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdCentro(){
		return this.idCentro;
	}
	
	public void setIdCentro(Long idCentro){
		this.idCentro = idCentro;
	}
		
	public Long getIdLista(){
		return this.idLista;
	}
	
	public void setIdLista(Long idLista){
		this.idLista = idLista;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idRolPersona);        
        hash = 37 * hash + Objects.hashCode(this.fechaInicioVigencia);
        hash = 37 * hash + Objects.hashCode(this.fechaFinVigencia);
        hash = 37 * hash + Objects.hashCode(this.actaAprobacion);
        hash = 37 * hash + Objects.hashCode(this.fechaActaAprobacion);
        hash = 37 * hash + (this.reportadoSicaac ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idCentro);
        hash = 37 * hash + Objects.hashCode(this.idLista);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RolPersonaDTO que se pasa
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
        final RolPersonaDTO other = (RolPersonaDTO) obj;
                
        if (!Objects.equals(this.idRolPersona, other.idRolPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicioVigencia, other.fechaInicioVigencia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFinVigencia, other.fechaFinVigencia)) {
            return false;
        }
        
        if (!Objects.equals(this.actaAprobacion, other.actaAprobacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaActaAprobacion, other.fechaActaAprobacion)) {
            return false;
        }
        
        if (!Objects.equals(this.reportadoSicaac, other.reportadoSicaac)) {
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
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idCentro, other.idCentro)) {
            return false;
        }
        
        return Objects.equals(this.idLista, other.idLista);
                
    }
    
    @Override
	public RolPersonaDTO transformarSinDependencias(RolPersona obj) {
		RolPersonaDTO rolPersonaDTO = new RolPersonaDTO();
		
		rolPersonaDTO.setIdRolPersona(obj.getIdRolPersona());
		rolPersonaDTO.setFechaInicioVigencia(obj.getFechaInicioVigencia());
		rolPersonaDTO.setFechaFinVigencia(obj.getFechaFinVigencia());
		rolPersonaDTO.setActaAprobacion(obj.getActaAprobacion());
		rolPersonaDTO.setFechaActaAprobacion(obj.getFechaActaAprobacion());
		rolPersonaDTO.setReportadoSicaac(obj.getReportadoSicaac());
		rolPersonaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		rolPersonaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		rolPersonaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		rolPersonaDTO.setIdRol(obj.getIdRol());
		rolPersonaDTO.setIdPersona(obj.getIdPersona());
		rolPersonaDTO.setIdCentro(obj.getIdCentro());
		rolPersonaDTO.setIdLista(obj.getIdLista());
		
		return rolPersonaDTO;
	}

	@Override
	public RolPersonaDTO transformarConDependencias(RolPersona obj) {
		RolPersonaDTO rolPersonaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return rolPersonaDTO;
	}

	@Override
	public RolPersona transformarEntidadSinDependencias(RolPersona obj) {
		RolPersona rolPersona = new RolPersona();
		
		rolPersona.setIdRolPersona(obj.getIdRolPersona());
		
		rolPersona.setFechaInicioVigencia(obj.getFechaInicioVigencia());
		rolPersona.setFechaFinVigencia(obj.getFechaFinVigencia());
		rolPersona.setActaAprobacion(obj.getActaAprobacion());
		rolPersona.setFechaActaAprobacion(obj.getFechaActaAprobacion());
		rolPersona.setReportadoSicaac(obj.getReportadoSicaac());
		rolPersona.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		rolPersona.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		rolPersona.setEstadoRegistro(obj.getEstadoRegistro());
		rolPersona.setIdRol(obj.getIdRol());
		rolPersona.setIdPersona(obj.getIdPersona());
		rolPersona.setIdCentro(obj.getIdCentro());
		rolPersona.setIdLista(obj.getIdLista());
		
		return rolPersona;
	}


	@Override
	public RolPersona transformarEntidadConDependencias(RolPersona obj) {
		RolPersona rolPersona = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		if( obj.getRol() != null )
			rolPersona.setRol(new RolDTO().transformarEntidadSinDependencias(obj.getRol()));
		if( obj.getPersona() != null )
			rolPersona.setPersona( new PersonaDTO().transformarEntidadSinDependencias( obj.getPersona() ));
		// protected region modificaciones transformarEntidadConDependencias end
		
		return rolPersona;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<RolPersona> coleccion) {
		List<RolPersonaDTO> rolPersonaDTOList = new ArrayList<>();
		for(RolPersona c : coleccion)
			rolPersonaDTOList.add(transformarConDependencias(c));
		return rolPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<RolPersona> coleccion) {
		List<RolPersonaDTO> rolPersonaDTOList = new ArrayList<>();
		for(RolPersona c : coleccion)
			rolPersonaDTOList.add(transformarSinDependencias(c));
		return rolPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<RolPersona> coleccion) {
		List<RolPersona> rolPersonaList = new ArrayList<>();
		for(RolPersona c : coleccion)
			rolPersonaList.add(transformarEntidadConDependencias(c));
		return rolPersonaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<RolPersona> coleccion) {
		List<RolPersona> rolPersonaList = new ArrayList<>();
		for(RolPersona c : coleccion)
			rolPersonaList.add(transformarEntidadSinDependencias(c));
		return rolPersonaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	public static RolPersona transformarDTOAEntidad(RolPersonaDTO obj) {
		RolPersona rolPersona = new RolPersona();
		
		rolPersona.setIdRolPersona(obj.getIdRolPersona());
		
		rolPersona.setFechaInicioVigencia(obj.getFechaInicioVigencia());
		rolPersona.setFechaFinVigencia(obj.getFechaFinVigencia());
		rolPersona.setActaAprobacion(obj.getActaAprobacion());
		rolPersona.setFechaActaAprobacion(obj.getFechaActaAprobacion());
		rolPersona.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		rolPersona.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		rolPersona.setEstadoRegistro(obj.getEstadoRegistro());
		rolPersona.setIdRol(obj.getIdRol());
		rolPersona.setIdPersona(obj.getIdPersona());
		rolPersona.setIdLista(obj.getIdLista());
		rolPersona.setIdCentro(obj.getIdCentro());
		
		return rolPersona;
	}



	public String getNombreRol() {
		return nombreRol;
	}



	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	
	/**
	 * 
	 * @param rolPersonas
	 * @return
	 */
	public static List<Persona> obtenerPersonas(List<RolPersona> rolPersonas){		
		List<Persona> personas = new ArrayList<>();
		if(rolPersonas!=null && !rolPersonas.isEmpty()){
			Set<Persona> personasSet = new HashSet<>();
			for(RolPersona rolPersona : rolPersonas){
				personasSet.add(rolPersona.getPersona());
			}
			if(!personasSet.isEmpty()){
				personas.addAll(personasSet);
			}
		}		
		
		return personas;
	}



	/**
	 * @return the tipoServicio
	 */
	public String getTipoServicio() {
		return tipoServicio;
	}



	/**
	 * @param tipoServicio the tipoServicio to set
	 */
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}



	/**
	 * @return the estadoRolServicio
	 */
	public String getEstadoRolServicio() {
		return estadoRolServicio;
	}



	/**
	 * @param estadoRolServicio the estadoRolServicio to set
	 */
	public void setEstadoRolServicio(String estadoRolServicio) {
		this.estadoRolServicio = estadoRolServicio;
	}
	
	
	// protected region metodos adicionales end

}
