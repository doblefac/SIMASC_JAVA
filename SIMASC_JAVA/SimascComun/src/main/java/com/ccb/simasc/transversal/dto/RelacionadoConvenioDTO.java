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

import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.RelacionadoConvenio;
import com.ccb.simasc.transversal.entidades.RelacionadoConvenioPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad RelacionadoConvenioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class RelacionadoConvenioDTO extends IDTO<RelacionadoConvenio> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private PersonaBasicaDTO persona;

	// protected region atributos end
	private RelacionadoConvenioPK relacionadoConvenioPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public RelacionadoConvenioDTO(){
		relacionadoConvenioPK = new RelacionadoConvenioPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public RelacionadoConvenioPK getRelacionadoConvenioPK(){
		return this.relacionadoConvenioPK;
	}
	
	public void setRelacionadoConvenioPK(RelacionadoConvenioPK relacionadoConvenioPK){
		this.relacionadoConvenioPK   = relacionadoConvenioPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.relacionadoConvenioPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RelacionadoConvenioDTO que se pasa
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
        final RelacionadoConvenioDTO other = (RelacionadoConvenioDTO) obj;
                
        if (!Objects.equals(this.relacionadoConvenioPK, other.relacionadoConvenioPK)) {
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
	public RelacionadoConvenioDTO transformarSinDependencias(RelacionadoConvenio obj) {
		RelacionadoConvenioDTO relacionadoConvenioDTO = new RelacionadoConvenioDTO();
		
		relacionadoConvenioDTO.setRelacionadoConvenioPK(obj.getRelacionadoConvenioPK());
		relacionadoConvenioDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		relacionadoConvenioDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		relacionadoConvenioDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return relacionadoConvenioDTO;
	}

	@Override
	public RelacionadoConvenioDTO transformarConDependencias(RelacionadoConvenio obj) {
		RelacionadoConvenioDTO relacionadoConvenioDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		relacionadoConvenioDTO.setPersona(new PersonaBasicaDTO().newPersonaBasicaDTO(obj.getPersona()));
		// protected region modificaciones transformarConDependencias end
		
		return relacionadoConvenioDTO;
	}

	@Override
	public RelacionadoConvenio transformarEntidadSinDependencias(RelacionadoConvenio obj) {
		RelacionadoConvenio relacionadoConvenio = new RelacionadoConvenio();
		
		relacionadoConvenio.setRelacionadoConvenioPK(obj.getRelacionadoConvenioPK());
		
		relacionadoConvenio.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		relacionadoConvenio.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		relacionadoConvenio.setEstadoRegistro(obj.getEstadoRegistro());
		
		return relacionadoConvenio;
	}


	@Override
	public RelacionadoConvenio transformarEntidadConDependencias(RelacionadoConvenio obj) {
		RelacionadoConvenio relacionadoConvenio = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return relacionadoConvenio;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<RelacionadoConvenio> coleccion) {
		List<RelacionadoConvenioDTO> relacionadoConvenioDTOList = new ArrayList<>();
		for(RelacionadoConvenio c : coleccion)
			relacionadoConvenioDTOList.add(transformarConDependencias(c));
		return relacionadoConvenioDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<RelacionadoConvenio> coleccion) {
		List<RelacionadoConvenioDTO> relacionadoConvenioDTOList = new ArrayList<>();
		for(RelacionadoConvenio c : coleccion)
			relacionadoConvenioDTOList.add(transformarSinDependencias(c));
		return relacionadoConvenioDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<RelacionadoConvenio> coleccion) {
		List<RelacionadoConvenio> relacionadoConvenioList = new ArrayList<>();
		for(RelacionadoConvenio c : coleccion)
			relacionadoConvenioList.add(transformarEntidadConDependencias(c));
		return relacionadoConvenioList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<RelacionadoConvenio> coleccion) {
		List<RelacionadoConvenio> relacionadoConvenioList = new ArrayList<>();
		for(RelacionadoConvenio c : coleccion)
			relacionadoConvenioList.add(transformarEntidadSinDependencias(c));
		return relacionadoConvenioList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public PersonaBasicaDTO getPersona() {
		return persona;
	}


	public void setPersona(PersonaBasicaDTO persona) {
		this.persona = persona;
	}
	// protected region metodos adicionales end

}
