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

import com.ccb.simasc.transversal.entidades.SedeConvenio;
import com.ccb.simasc.transversal.entidades.SedeConvenioPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad SedeConvenioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class SedeConvenioDTO extends IDTO<SedeConvenio> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private SedeConvenioPK sedeConvenioPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public SedeConvenioDTO(){
		sedeConvenioPK = new SedeConvenioPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public SedeConvenioPK getSedeConvenioPK(){
		return this.sedeConvenioPK;
	}
	
	public void setSedeConvenioPK(SedeConvenioPK sedeConvenioPK){
		this.sedeConvenioPK   = sedeConvenioPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.sedeConvenioPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad SedeConvenioDTO que se pasa
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
        final SedeConvenioDTO other = (SedeConvenioDTO) obj;
                
        if (!Objects.equals(this.sedeConvenioPK, other.sedeConvenioPK)) {
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
	public SedeConvenioDTO transformarSinDependencias(SedeConvenio obj) {
		SedeConvenioDTO sedeConvenioDTO = new SedeConvenioDTO();
		
		sedeConvenioDTO.setSedeConvenioPK(obj.getSedeConvenioPK());
		sedeConvenioDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		sedeConvenioDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		sedeConvenioDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return sedeConvenioDTO;
	}

	@Override
	public SedeConvenioDTO transformarConDependencias(SedeConvenio obj) {
		SedeConvenioDTO sedeConvenioDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return sedeConvenioDTO;
	}

	@Override
	public SedeConvenio transformarEntidadSinDependencias(SedeConvenio obj) {
		SedeConvenio sedeConvenio = new SedeConvenio();
		
		sedeConvenio.setSedeConvenioPK(obj.getSedeConvenioPK());
		
		sedeConvenio.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		sedeConvenio.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		sedeConvenio.setEstadoRegistro(obj.getEstadoRegistro());
		
		return sedeConvenio;
	}


	@Override
	public SedeConvenio transformarEntidadConDependencias(SedeConvenio obj) {
		SedeConvenio sedeConvenio = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return sedeConvenio;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<SedeConvenio> coleccion) {
		List<SedeConvenioDTO> sedeConvenioDTOList = new ArrayList<>();
		for(SedeConvenio c : coleccion)
			sedeConvenioDTOList.add(transformarConDependencias(c));
		return sedeConvenioDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<SedeConvenio> coleccion) {
		List<SedeConvenioDTO> sedeConvenioDTOList = new ArrayList<>();
		for(SedeConvenio c : coleccion)
			sedeConvenioDTOList.add(transformarSinDependencias(c));
		return sedeConvenioDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<SedeConvenio> coleccion) {
		List<SedeConvenio> sedeConvenioList = new ArrayList<>();
		for(SedeConvenio c : coleccion)
			sedeConvenioList.add(transformarEntidadConDependencias(c));
		return sedeConvenioList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<SedeConvenio> coleccion) {
		List<SedeConvenio> sedeConvenioList = new ArrayList<>();
		for(SedeConvenio c : coleccion)
			sedeConvenioList.add(transformarEntidadSinDependencias(c));
		return sedeConvenioList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
