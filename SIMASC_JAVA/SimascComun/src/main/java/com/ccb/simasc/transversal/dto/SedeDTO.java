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

import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.entidades.TelefonoSede;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad SedeDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class SedeDTO extends IDTO<Sede> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idSede;

	private String nombre;		
	private boolean virtual;		
	private String tipoSede;		
	private String direccion;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idCentro;		
	
    public SedeDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public boolean getVirtual(){
		return this.virtual;
	}
	
	public void setVirtual(boolean virtual){
		this.virtual = virtual;
	}
		
	public String getTipoSede(){
		return this.tipoSede;
	}
	
	public void setTipoSede(String tipoSede){
		this.tipoSede = tipoSede;
	}
		
	public String getDireccion(){
		return this.direccion;
	}
	
	public void setDireccion(String direccion){
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
		
	public Long getIdCentro(){
		return this.idCentro;
	}
	
	public void setIdCentro(Long idCentro){
		this.idCentro = idCentro;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idSede);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + (this.virtual ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.tipoSede);
        hash = 37 * hash + Objects.hashCode(this.direccion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCentro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad SedeDTO que se pasa
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
        final SedeDTO other = (SedeDTO) obj;
                
        if (!Objects.equals(this.idSede, other.idSede)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.virtual, other.virtual)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoSede, other.tipoSede)) {
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
        
        return Objects.equals(this.idCentro, other.idCentro);
                
    }
    
    @Override
	public SedeDTO transformarSinDependencias(Sede obj) {
		SedeDTO sedeDTO = new SedeDTO();
		
		sedeDTO.setIdSede(obj.getIdSede());
		sedeDTO.setNombre(obj.getNombre());
		sedeDTO.setVirtual(obj.getVirtual());
		sedeDTO.setTipoSede(obj.getTipoSede());
		sedeDTO.setDireccion(obj.getDireccion());
		sedeDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		sedeDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		sedeDTO.setEstadoRegistro(obj.getEstadoRegistro());
		sedeDTO.setIdCentro(obj.getIdCentro());
		
		return sedeDTO;
	}

	@Override
	public SedeDTO transformarConDependencias(Sede obj) {
		SedeDTO sedeDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return sedeDTO;
	}

	@Override
	public Sede transformarEntidadSinDependencias(Sede obj) {
		Sede sede = new Sede();
		
		sede.setIdSede(obj.getIdSede());
		
		sede.setNombre(obj.getNombre());
		sede.setVirtual(obj.getVirtual());
		sede.setTipoSede(obj.getTipoSede());
		sede.setDireccion(obj.getDireccion());
		sede.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		sede.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		sede.setEstadoRegistro(obj.getEstadoRegistro());
		sede.setIdCentro(obj.getIdCentro());
		
		return sede;
	}


	@Override
	public Sede transformarEntidadConDependencias(Sede obj) {
		Sede sede = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		
		TelefonoSedeDTO telefonoSedeDto = new TelefonoSedeDTO();
		sede.setTelefonoSedeList((List<TelefonoSede>)telefonoSedeDto.transformarColeccionEntidadesSinDependencias(
				obj.getTelefonoSedeList()));		
		
		
		// protected region modificaciones transformarEntidadConDependencias end
		
		return sede;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Sede> coleccion) {
		List<SedeDTO> sedeDTOList = new ArrayList<>();
		for(Sede c : coleccion)
			sedeDTOList.add(transformarConDependencias(c));
		return sedeDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Sede> coleccion) {
		List<SedeDTO> sedeDTOList = new ArrayList<>();
		for(Sede c : coleccion)
			sedeDTOList.add(transformarSinDependencias(c));
		return sedeDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Sede> coleccion) {
		List<Sede> sedeList = new ArrayList<>();
		for(Sede c : coleccion)
			sedeList.add(transformarEntidadConDependencias(c));
		return sedeList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Sede> coleccion) {
		List<Sede> sedeList = new ArrayList<>();
		for(Sede c : coleccion)
			sedeList.add(transformarEntidadSinDependencias(c));
		return sedeList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
