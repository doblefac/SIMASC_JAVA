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

import com.ccb.simasc.transversal.entidades.Convenio;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ConvenioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ConvenioDTO extends IDTO<Convenio> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idConvenio;

	private String tipoConvenio;		
	private String nombre;		
	private Date fechaInicioVigencia;		
	private Date fechaFinVigencia;		
	private Integer limiteInferiorDiasProgramacionAudiencias;		
	private Integer limiteSuperiorDiasProgramacionAudiencias;		
	private boolean requiereSuplente;		
	private String duracionAudiencias;		
	private Integer numeroDeAudienciasRecobro;		
	private String lugar;
	private String direccion;
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idServicio;		
	private Long idMateria;		
	private String idZonaGeografica;		
	private Long idCentro;		
	private Long idPersona;		
	private Long idDocumento;		
	
    public ConvenioDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdConvenio(){
		return this.idConvenio;
	}
	
	public void setIdConvenio(Long idConvenio){
		this.idConvenio = idConvenio;
	}
	
	public String getTipoConvenio(){
		return this.tipoConvenio;
	}
	
	public void setTipoConvenio(String tipoConvenio){
		this.tipoConvenio = tipoConvenio;
	}
		
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
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
		
	public Integer getLimiteInferiorDiasProgramacionAudiencias(){
		return this.limiteInferiorDiasProgramacionAudiencias;
	}
	
	public void setLimiteInferiorDiasProgramacionAudiencias(Integer limiteInferiorDiasProgramacionAudiencias){
		this.limiteInferiorDiasProgramacionAudiencias = limiteInferiorDiasProgramacionAudiencias;
	}
		
	public Integer getLimiteSuperiorDiasProgramacionAudiencias(){
		return this.limiteSuperiorDiasProgramacionAudiencias;
	}
	
	public void setLimiteSuperiorDiasProgramacionAudiencias(Integer limiteSuperiorDiasProgramacionAudiencias){
		this.limiteSuperiorDiasProgramacionAudiencias = limiteSuperiorDiasProgramacionAudiencias;
	}
		
	public boolean getRequiereSuplente(){
		return this.requiereSuplente;
	}
	
	public void setRequiereSuplente(boolean requiereSuplente){
		this.requiereSuplente = requiereSuplente;
	}
		
	public String getDuracionAudiencias(){
		return this.duracionAudiencias;
	}
	
	public void setDuracionAudiencias(String duracionAudiencias){
		this.duracionAudiencias = duracionAudiencias;
	}
		
	public Integer getNumeroDeAudienciasRecobro(){
		return this.numeroDeAudienciasRecobro;
	}
	
	public void setNumeroDeAudienciasRecobro(Integer numeroDeAudienciasRecobro){
		this.numeroDeAudienciasRecobro = numeroDeAudienciasRecobro;
	}
		
	public String getLugar(){
		return this.lugar;
	}
	
	public void setLugar(String lugar){
		this.lugar = lugar;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
		
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
		
	public String getIdZonaGeografica(){
		return this.idZonaGeografica;
	}
	
	public void setIdZonaGeografica(String idZonaGeografica){
		this.idZonaGeografica = idZonaGeografica;
	}
		
	public Long getIdCentro(){
		return this.idCentro;
	}
	
	public void setIdCentro(Long idCentro){
		this.idCentro = idCentro;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idConvenio);        
        hash = 37 * hash + Objects.hashCode(this.tipoConvenio);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.fechaInicioVigencia);
        hash = 37 * hash + Objects.hashCode(this.fechaFinVigencia);
        hash = 37 * hash + Objects.hashCode(this.limiteInferiorDiasProgramacionAudiencias);
        hash = 37 * hash + Objects.hashCode(this.limiteSuperiorDiasProgramacionAudiencias);
        hash = 37 * hash + (this.requiereSuplente ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.duracionAudiencias);
        hash = 37 * hash + Objects.hashCode(this.numeroDeAudienciasRecobro);
        hash = 37 * hash + Objects.hashCode(this.lugar);
        hash = 37 * hash + Objects.hashCode(this.direccion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.idZonaGeografica);
        hash = 37 * hash + Objects.hashCode(this.idCentro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ConvenioDTO que se pasa
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
        final ConvenioDTO other = (ConvenioDTO) obj;
                
        if (!Objects.equals(this.idConvenio, other.idConvenio)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoConvenio, other.tipoConvenio)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicioVigencia, other.fechaInicioVigencia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFinVigencia, other.fechaFinVigencia)) {
            return false;
        }
        
        if (!Objects.equals(this.limiteInferiorDiasProgramacionAudiencias, other.limiteInferiorDiasProgramacionAudiencias)) {
            return false;
        }
        
        if (!Objects.equals(this.limiteSuperiorDiasProgramacionAudiencias, other.limiteSuperiorDiasProgramacionAudiencias)) {
            return false;
        }
        
        if (!Objects.equals(this.requiereSuplente, other.requiereSuplente)) {
            return false;
        }
        
        if (!Objects.equals(this.duracionAudiencias, other.duracionAudiencias)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroDeAudienciasRecobro, other.numeroDeAudienciasRecobro)) {
            return false;
        }
        
        if (!Objects.equals(this.lugar, other.lugar)) {
            return false;
        }
        
        if (!Objects.equals(this.direccion, other.direccion)) {
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
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.idZonaGeografica, other.idZonaGeografica)) {
            return false;
        }
        
        if (!Objects.equals(this.idCentro, other.idCentro)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        return Objects.equals(this.idDocumento, other.idDocumento);
                
    }
    
    @Override
	public ConvenioDTO transformarSinDependencias(Convenio obj) {
		ConvenioDTO convenioDTO = new ConvenioDTO();
		
		convenioDTO.setIdConvenio(obj.getIdConvenio());
		convenioDTO.setTipoConvenio(obj.getTipoConvenio());
		convenioDTO.setNombre(obj.getNombre());
		convenioDTO.setFechaInicioVigencia(obj.getFechaInicioVigencia());
		convenioDTO.setFechaFinVigencia(obj.getFechaFinVigencia());
		convenioDTO.setLimiteInferiorDiasProgramacionAudiencias(obj.getLimiteInferiorDiasProgramacionAudiencias());
		convenioDTO.setLimiteSuperiorDiasProgramacionAudiencias(obj.getLimiteSuperiorDiasProgramacionAudiencias());
		convenioDTO.setRequiereSuplente(obj.getRequiereSuplente());
		convenioDTO.setDuracionAudiencias(obj.getDuracionAudiencias());
		convenioDTO.setNumeroDeAudienciasRecobro(obj.getNumeroDeAudienciasRecobro());
		convenioDTO.setLugar(obj.getLugar());
		convenioDTO.setDireccion(obj.getDireccion());
		convenioDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		convenioDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		convenioDTO.setEstadoRegistro(obj.getEstadoRegistro());
		convenioDTO.setIdServicio(obj.getIdServicio());
		convenioDTO.setIdMateria(obj.getIdMateria());
		convenioDTO.setIdZonaGeografica(obj.getIdZonaGeografica());
		convenioDTO.setIdCentro(obj.getIdCentro());
		convenioDTO.setIdPersona(obj.getIdPersona());
		convenioDTO.setIdDocumento(obj.getIdDocumento());
		
		return convenioDTO;
	}

	@Override
	public ConvenioDTO transformarConDependencias(Convenio obj) {
		ConvenioDTO convenioDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return convenioDTO;
	}

	@Override
	public Convenio transformarEntidadSinDependencias(Convenio obj) {
		Convenio convenio = new Convenio();
		
		convenio.setIdConvenio(obj.getIdConvenio());		
		convenio.setTipoConvenio(obj.getTipoConvenio());
		convenio.setNombre(obj.getNombre());
		convenio.setFechaInicioVigencia(obj.getFechaInicioVigencia());
		convenio.setFechaFinVigencia(obj.getFechaFinVigencia());
		convenio.setLimiteInferiorDiasProgramacionAudiencias(obj.getLimiteInferiorDiasProgramacionAudiencias());
		convenio.setLimiteSuperiorDiasProgramacionAudiencias(obj.getLimiteSuperiorDiasProgramacionAudiencias());
		convenio.setRequiereSuplente(obj.getRequiereSuplente());
		convenio.setDuracionAudiencias(obj.getDuracionAudiencias());
		convenio.setNumeroDeAudienciasRecobro(obj.getNumeroDeAudienciasRecobro());
		convenio.setLugar(obj.getLugar());
		convenio.setDireccion(obj.getDireccion());
		convenio.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		convenio.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		convenio.setEstadoRegistro(obj.getEstadoRegistro());
		convenio.setIdServicio(obj.getIdServicio());
		convenio.setIdMateria(obj.getIdMateria());
		convenio.setIdZonaGeografica(obj.getIdZonaGeografica());
		convenio.setIdCentro(obj.getIdCentro());
		convenio.setIdPersona(obj.getIdPersona());
		convenio.setIdDocumento(obj.getIdDocumento());
		
		return convenio;
	}


	@Override
	public Convenio transformarEntidadConDependencias(Convenio obj) {
		Convenio convenio = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return convenio;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Convenio> coleccion) {
		List<ConvenioDTO> convenioDTOList = new ArrayList<>();
		for(Convenio c : coleccion)
			convenioDTOList.add(transformarConDependencias(c));
		return convenioDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Convenio> coleccion) {
		List<ConvenioDTO> convenioDTOList = new ArrayList<>();
		for(Convenio c : coleccion)
			convenioDTOList.add(transformarSinDependencias(c));
		return convenioDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Convenio> coleccion) {
		List<Convenio> convenioList = new ArrayList<>();
		for(Convenio c : coleccion)
			convenioList.add(transformarEntidadConDependencias(c));
		return convenioList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Convenio> coleccion) {
		List<Convenio> convenioList = new ArrayList<>();
		for(Convenio c : coleccion)
			convenioList.add(transformarEntidadSinDependencias(c));
		return convenioList;
	}



	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
