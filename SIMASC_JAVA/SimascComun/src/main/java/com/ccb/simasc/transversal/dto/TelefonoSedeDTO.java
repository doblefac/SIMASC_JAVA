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

import com.ccb.simasc.transversal.entidades.TelefonoSede;
import com.ccb.simasc.transversal.entidades.TelefonoSedePK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad TelefonoSedeDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class TelefonoSedeDTO extends IDTO<TelefonoSede> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private TelefonoSedePK telefonoSedePK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idSede;		
	
    public TelefonoSedeDTO(){
		telefonoSedePK = new TelefonoSedePK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public TelefonoSedePK getTelefonoSedePK(){
		return this.telefonoSedePK;
	}
	
	public void setTelefonoSedePK(TelefonoSedePK telefonoSedePK){
		this.telefonoSedePK   = telefonoSedePK ;
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
		
	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.telefonoSedePK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idSede);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TelefonoSedeDTO que se pasa
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
        final TelefonoSedeDTO other = (TelefonoSedeDTO) obj;
                
        if (!Objects.equals(this.telefonoSedePK, other.telefonoSedePK)) {
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
        
        return Objects.equals(this.idSede, other.idSede);
                
    }
    
    @Override
	public TelefonoSedeDTO transformarSinDependencias(TelefonoSede obj) {
		TelefonoSedeDTO telefonoSedeDTO = new TelefonoSedeDTO();
		
		telefonoSedeDTO.setTelefonoSedePK(obj.getTelefonoSedePK());
		telefonoSedeDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		telefonoSedeDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		telefonoSedeDTO.setEstadoRegistro(obj.getEstadoRegistro());
		telefonoSedeDTO.setIdSede(obj.getIdSede());
		
		return telefonoSedeDTO;
	}

	@Override
	public TelefonoSedeDTO transformarConDependencias(TelefonoSede obj) {
		TelefonoSedeDTO telefonoSedeDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return telefonoSedeDTO;
	}

	@Override
	public TelefonoSede transformarEntidadSinDependencias(TelefonoSede obj) {
		TelefonoSede telefonoSede = new TelefonoSede();
		
		telefonoSede.setTelefonoSedePK(obj.getTelefonoSedePK());
		
		telefonoSede.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		telefonoSede.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		telefonoSede.setEstadoRegistro(obj.getEstadoRegistro());
		telefonoSede.setIdSede(obj.getIdSede());
		
		return telefonoSede;
	}


	@Override
	public TelefonoSede transformarEntidadConDependencias(TelefonoSede obj) {
		TelefonoSede telefonoSede = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return telefonoSede;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<TelefonoSede> coleccion) {
		List<TelefonoSedeDTO> telefonoSedeDTOList = new ArrayList<>();
		for(TelefonoSede c : coleccion)
			telefonoSedeDTOList.add(transformarConDependencias(c));
		return telefonoSedeDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<TelefonoSede> coleccion) {
		List<TelefonoSedeDTO> telefonoSedeDTOList = new ArrayList<>();
		for(TelefonoSede c : coleccion)
			telefonoSedeDTOList.add(transformarSinDependencias(c));
		return telefonoSedeDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<TelefonoSede> coleccion) {
		List<TelefonoSede> telefonoSedeList = new ArrayList<>();
		for(TelefonoSede c : coleccion)
			telefonoSedeList.add(transformarEntidadConDependencias(c));
		return telefonoSedeList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<TelefonoSede> coleccion) {
		List<TelefonoSede> telefonoSedeList = new ArrayList<>();
		for(TelefonoSede c : coleccion)
			telefonoSedeList.add(transformarEntidadSinDependencias(c));
		return telefonoSedeList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
