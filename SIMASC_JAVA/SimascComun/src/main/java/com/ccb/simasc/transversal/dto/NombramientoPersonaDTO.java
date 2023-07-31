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

import com.ccb.simasc.transversal.entidades.NombramientoPersona;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad NombramientoPersonaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class NombramientoPersonaDTO extends IDTO<NombramientoPersona> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idNombramientoPersonas;

	private Long idCaso;		
	private String metodoDeNombramiento;		
	private Integer cantFuncionariosPrincipales;		
	private Integer cantFuncionariosSuplentes;		
	private Long idPersona;		
	private String manejoDeSuplencia;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public NombramientoPersonaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdNombramientoPersonas(){
		return this.idNombramientoPersonas;
	}
	
	public void setIdNombramientoPersonas(Long idNombramientoPersonas){
		this.idNombramientoPersonas = idNombramientoPersonas;
	}
	
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public String getMetodoDeNombramiento(){
		return this.metodoDeNombramiento;
	}
	
	public void setMetodoDeNombramiento(String metodoDeNombramiento){
		this.metodoDeNombramiento = metodoDeNombramiento;
	}
		
	public Integer getCantFuncionariosPrincipales(){
		return this.cantFuncionariosPrincipales;
	}
	
	public void setCantFuncionariosPrincipales(Integer cantFuncionariosPrincipales){
		this.cantFuncionariosPrincipales = cantFuncionariosPrincipales;
	}
		
	public Integer getCantFuncionariosSuplentes(){
		return this.cantFuncionariosSuplentes;
	}
	
	public void setCantFuncionariosSuplentes(Integer cantFuncionariosSuplentes){
		this.cantFuncionariosSuplentes = cantFuncionariosSuplentes;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public String getManejoDeSuplencia(){
		return this.manejoDeSuplencia;
	}
	
	public void setManejoDeSuplencia(String manejoDeSuplencia){
		this.manejoDeSuplencia = manejoDeSuplencia;
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
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idNombramientoPersonas);        
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.metodoDeNombramiento);
        hash = 37 * hash + Objects.hashCode(this.cantFuncionariosPrincipales);
        hash = 37 * hash + Objects.hashCode(this.cantFuncionariosSuplentes);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.manejoDeSuplencia);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad NombramientoPersonaDTO que se pasa
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
        final NombramientoPersonaDTO other = (NombramientoPersonaDTO) obj;
                
        if (!Objects.equals(this.idNombramientoPersonas, other.idNombramientoPersonas)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.metodoDeNombramiento, other.metodoDeNombramiento)) {
            return false;
        }
        
        if (!Objects.equals(this.cantFuncionariosPrincipales, other.cantFuncionariosPrincipales)) {
            return false;
        }
        
        if (!Objects.equals(this.cantFuncionariosSuplentes, other.cantFuncionariosSuplentes)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.manejoDeSuplencia, other.manejoDeSuplencia)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
    
    @Override
	public NombramientoPersonaDTO transformarSinDependencias(NombramientoPersona obj) {
		NombramientoPersonaDTO nombramientoPersonaDTO = new NombramientoPersonaDTO();
		
		nombramientoPersonaDTO.setIdNombramientoPersonas(obj.getIdNombramientoPersonas());
		nombramientoPersonaDTO.setIdCaso(obj.getIdCaso());
		nombramientoPersonaDTO.setMetodoDeNombramiento(obj.getMetodoDeNombramiento());
		nombramientoPersonaDTO.setCantFuncionariosPrincipales(obj.getCantFuncionariosPrincipales());
		nombramientoPersonaDTO.setCantFuncionariosSuplentes(obj.getCantFuncionariosSuplentes());
		nombramientoPersonaDTO.setIdPersona(obj.getIdPersona());
		nombramientoPersonaDTO.setManejoDeSuplencia(obj.getManejoDeSuplencia());
		nombramientoPersonaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		nombramientoPersonaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		nombramientoPersonaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return nombramientoPersonaDTO;
	}

	@Override
	public NombramientoPersonaDTO transformarConDependencias(NombramientoPersona obj) {
		NombramientoPersonaDTO nombramientoPersonaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return nombramientoPersonaDTO;
	}

	@Override
	public NombramientoPersona transformarEntidadSinDependencias(NombramientoPersona obj) {
		NombramientoPersona nombramientoPersona = new NombramientoPersona();
		
		nombramientoPersona.setIdNombramientoPersonas(obj.getIdNombramientoPersonas());
		
		nombramientoPersona.setIdCaso(obj.getIdCaso());
		nombramientoPersona.setMetodoDeNombramiento(obj.getMetodoDeNombramiento());
		nombramientoPersona.setCantFuncionariosPrincipales(obj.getCantFuncionariosPrincipales());
		nombramientoPersona.setCantFuncionariosSuplentes(obj.getCantFuncionariosSuplentes());
		nombramientoPersona.setIdPersona(obj.getIdPersona());
		nombramientoPersona.setManejoDeSuplencia(obj.getManejoDeSuplencia());
		nombramientoPersona.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		nombramientoPersona.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		nombramientoPersona.setEstadoRegistro(obj.getEstadoRegistro());
		
		return nombramientoPersona;
	}


	@Override
	public NombramientoPersona transformarEntidadConDependencias(NombramientoPersona obj) {
		NombramientoPersona nombramientoPersona = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return nombramientoPersona;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<NombramientoPersona> coleccion) {
		List<NombramientoPersonaDTO> nombramientoPersonaDTOList = new ArrayList<>();
		for(NombramientoPersona c : coleccion)
			nombramientoPersonaDTOList.add(transformarConDependencias(c));
		return nombramientoPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<NombramientoPersona> coleccion) {
		List<NombramientoPersonaDTO> nombramientoPersonaDTOList = new ArrayList<>();
		for(NombramientoPersona c : coleccion)
			nombramientoPersonaDTOList.add(transformarSinDependencias(c));
		return nombramientoPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<NombramientoPersona> coleccion) {
		List<NombramientoPersona> nombramientoPersonaList = new ArrayList<>();
		for(NombramientoPersona c : coleccion)
			nombramientoPersonaList.add(transformarEntidadConDependencias(c));
		return nombramientoPersonaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<NombramientoPersona> coleccion) {
		List<NombramientoPersona> nombramientoPersonaList = new ArrayList<>();
		for(NombramientoPersona c : coleccion)
			nombramientoPersonaList.add(transformarEntidadSinDependencias(c));
		return nombramientoPersonaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
