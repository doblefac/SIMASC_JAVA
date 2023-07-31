package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.UbicacionPersonaSolicitud;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.UbicacionPersonaSolicitudPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad UbicacionPersonaSolicitudDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class UbicacionPersonaSolicitudDTO extends IDTO<UbicacionPersonaSolicitud> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private UbicacionPersonaSolicitudPK ubicacionPersonaSolicitudPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	
    public UbicacionPersonaSolicitudDTO(){
		ubicacionPersonaSolicitudPK = new UbicacionPersonaSolicitudPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public UbicacionPersonaSolicitudPK getUbicacionPersonaSolicitudPK(){
		return this.ubicacionPersonaSolicitudPK;
	}
	
	public void setUbicacionPersonaSolicitudPK(UbicacionPersonaSolicitudPK ubicacionPersonaSolicitudPK){
		this.ubicacionPersonaSolicitudPK   = ubicacionPersonaSolicitudPK ;
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
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.ubicacionPersonaSolicitudPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad UbicacionPersonaSolicitudDTO que se pasa
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
        final UbicacionPersonaSolicitudDTO other = (UbicacionPersonaSolicitudDTO) obj;
                
        if (!Objects.equals(this.ubicacionPersonaSolicitudPK, other.ubicacionPersonaSolicitudPK)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        return Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion);
                
    }
    
    @Override
	public UbicacionPersonaSolicitudDTO transformarSinDependencias(UbicacionPersonaSolicitud obj) {
		UbicacionPersonaSolicitudDTO ubicacionPersonaSolicitudDTO = new UbicacionPersonaSolicitudDTO();
		
		ubicacionPersonaSolicitudDTO.setUbicacionPersonaSolicitudPK(obj.getUbicacionPersonaSolicitudPK());
		ubicacionPersonaSolicitudDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		ubicacionPersonaSolicitudDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		
		return ubicacionPersonaSolicitudDTO;
	}

	@Override
	public UbicacionPersonaSolicitudDTO transformarConDependencias(UbicacionPersonaSolicitud obj) {
		UbicacionPersonaSolicitudDTO ubicacionPersonaSolicitudDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return ubicacionPersonaSolicitudDTO;
	}

	@Override
	public UbicacionPersonaSolicitud transformarEntidadSinDependencias(UbicacionPersonaSolicitud obj) {
		UbicacionPersonaSolicitud ubicacionPersonaSolicitud = new UbicacionPersonaSolicitud();
		
		ubicacionPersonaSolicitud.setUbicacionPersonaSolicitudPK(obj.getUbicacionPersonaSolicitudPK());
		
		ubicacionPersonaSolicitud.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		ubicacionPersonaSolicitud.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		
		return ubicacionPersonaSolicitud;
	}


	@Override
	public UbicacionPersonaSolicitud transformarEntidadConDependencias(UbicacionPersonaSolicitud obj) {
		UbicacionPersonaSolicitud ubicacionPersonaSolicitud = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return ubicacionPersonaSolicitud;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<UbicacionPersonaSolicitud> coleccion) {
		List<UbicacionPersonaSolicitudDTO> ubicacionPersonaSolicitudDTOList = new ArrayList<>();
		for(UbicacionPersonaSolicitud c : coleccion)
			ubicacionPersonaSolicitudDTOList.add(transformarConDependencias(c));
		return ubicacionPersonaSolicitudDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<UbicacionPersonaSolicitud> coleccion) {
		List<UbicacionPersonaSolicitudDTO> ubicacionPersonaSolicitudDTOList = new ArrayList<>();
		for(UbicacionPersonaSolicitud c : coleccion)
			ubicacionPersonaSolicitudDTOList.add(transformarSinDependencias(c));
		return ubicacionPersonaSolicitudDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<UbicacionPersonaSolicitud> coleccion) {
		List<UbicacionPersonaSolicitud> ubicacionPersonaSolicitudList = new ArrayList<>();
		for(UbicacionPersonaSolicitud c : coleccion)
			ubicacionPersonaSolicitudList.add(transformarEntidadConDependencias(c));
		return ubicacionPersonaSolicitudList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<UbicacionPersonaSolicitud> coleccion) {
		List<UbicacionPersonaSolicitud> ubicacionPersonaSolicitudList = new ArrayList<>();
		for(UbicacionPersonaSolicitud c : coleccion)
			ubicacionPersonaSolicitudList.add(transformarEntidadSinDependencias(c));
		return ubicacionPersonaSolicitudList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
