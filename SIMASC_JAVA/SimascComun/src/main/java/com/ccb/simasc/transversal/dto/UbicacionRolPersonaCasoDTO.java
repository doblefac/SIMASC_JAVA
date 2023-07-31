package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.UbicacionRolPersonaCaso;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.UbicacionRolPersonaCasoPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad UbicacionRolPersonaCasoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class UbicacionRolPersonaCasoDTO extends IDTO<UbicacionRolPersonaCaso> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private UbicacionRolPersonaCasoPK ubicacionRolPersonaCasoPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public UbicacionRolPersonaCasoDTO(){
		ubicacionRolPersonaCasoPK = new UbicacionRolPersonaCasoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public UbicacionRolPersonaCasoPK getUbicacionRolPersonaCasoPK(){
		return this.ubicacionRolPersonaCasoPK;
	}
	
	public void setUbicacionRolPersonaCasoPK(UbicacionRolPersonaCasoPK ubicacionRolPersonaCasoPK){
		this.ubicacionRolPersonaCasoPK   = ubicacionRolPersonaCasoPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.ubicacionRolPersonaCasoPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad UbicacionRolPersonaCasoDTO que se pasa
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
        final UbicacionRolPersonaCasoDTO other = (UbicacionRolPersonaCasoDTO) obj;
                
        if (!Objects.equals(this.ubicacionRolPersonaCasoPK, other.ubicacionRolPersonaCasoPK)) {
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
	public UbicacionRolPersonaCasoDTO transformarSinDependencias(UbicacionRolPersonaCaso obj) {
		UbicacionRolPersonaCasoDTO ubicacionRolPersonaCasoDTO = new UbicacionRolPersonaCasoDTO();
		
		ubicacionRolPersonaCasoDTO.setUbicacionRolPersonaCasoPK(obj.getUbicacionRolPersonaCasoPK());
		ubicacionRolPersonaCasoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		ubicacionRolPersonaCasoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		ubicacionRolPersonaCasoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return ubicacionRolPersonaCasoDTO;
	}

	@Override
	public UbicacionRolPersonaCasoDTO transformarConDependencias(UbicacionRolPersonaCaso obj) {
		UbicacionRolPersonaCasoDTO ubicacionRolPersonaCasoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return ubicacionRolPersonaCasoDTO;
	}

	@Override
	public UbicacionRolPersonaCaso transformarEntidadSinDependencias(UbicacionRolPersonaCaso obj) {
		UbicacionRolPersonaCaso ubicacionRolPersonaCaso = new UbicacionRolPersonaCaso();
		
		ubicacionRolPersonaCaso.setUbicacionRolPersonaCasoPK(obj.getUbicacionRolPersonaCasoPK());
		
		ubicacionRolPersonaCaso.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		ubicacionRolPersonaCaso.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		ubicacionRolPersonaCaso.setEstadoRegistro(obj.getEstadoRegistro());
		
		return ubicacionRolPersonaCaso;
	}


	@Override
	public UbicacionRolPersonaCaso transformarEntidadConDependencias(UbicacionRolPersonaCaso obj) {
		UbicacionRolPersonaCaso ubicacionRolPersonaCaso = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return ubicacionRolPersonaCaso;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<UbicacionRolPersonaCaso> coleccion) {
		List<UbicacionRolPersonaCasoDTO> ubicacionRolPersonaCasoDTOList = new ArrayList<>();
		for(UbicacionRolPersonaCaso c : coleccion)
			ubicacionRolPersonaCasoDTOList.add(transformarConDependencias(c));
		return ubicacionRolPersonaCasoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<UbicacionRolPersonaCaso> coleccion) {
		List<UbicacionRolPersonaCasoDTO> ubicacionRolPersonaCasoDTOList = new ArrayList<>();
		for(UbicacionRolPersonaCaso c : coleccion)
			ubicacionRolPersonaCasoDTOList.add(transformarSinDependencias(c));
		return ubicacionRolPersonaCasoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<UbicacionRolPersonaCaso> coleccion) {
		List<UbicacionRolPersonaCaso> ubicacionRolPersonaCasoList = new ArrayList<>();
		for(UbicacionRolPersonaCaso c : coleccion)
			ubicacionRolPersonaCasoList.add(transformarEntidadConDependencias(c));
		return ubicacionRolPersonaCasoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<UbicacionRolPersonaCaso> coleccion) {
		List<UbicacionRolPersonaCaso> ubicacionRolPersonaCasoList = new ArrayList<>();
		for(UbicacionRolPersonaCaso c : coleccion)
			ubicacionRolPersonaCasoList.add(transformarEntidadSinDependencias(c));
		return ubicacionRolPersonaCasoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
