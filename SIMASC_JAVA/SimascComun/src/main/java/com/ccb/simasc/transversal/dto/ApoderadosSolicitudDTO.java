package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.ApoderadosSolicitud;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.ApoderadosSolicitudPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ApoderadosSolicitudDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ApoderadosSolicitudDTO extends IDTO<ApoderadosSolicitud> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private ApoderadosSolicitudPK apoderadosSolicitudPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ApoderadosSolicitudDTO(){
		apoderadosSolicitudPK = new ApoderadosSolicitudPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ApoderadosSolicitudPK getApoderadosSolicitudPK(){
		return this.apoderadosSolicitudPK;
	}
	
	public void setApoderadosSolicitudPK(ApoderadosSolicitudPK apoderadosSolicitudPK){
		this.apoderadosSolicitudPK   = apoderadosSolicitudPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.apoderadosSolicitudPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ApoderadosSolicitudDTO que se pasa
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
        final ApoderadosSolicitudDTO other = (ApoderadosSolicitudDTO) obj;
                
        if (!Objects.equals(this.apoderadosSolicitudPK, other.apoderadosSolicitudPK)) {
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
	public ApoderadosSolicitudDTO transformarSinDependencias(ApoderadosSolicitud obj) {
		ApoderadosSolicitudDTO apoderadosSolicitudDTO = new ApoderadosSolicitudDTO();
		
		apoderadosSolicitudDTO.setApoderadosSolicitudPK(obj.getApoderadosSolicitudPK());
		apoderadosSolicitudDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		apoderadosSolicitudDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		apoderadosSolicitudDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return apoderadosSolicitudDTO;
	}

	@Override
	public ApoderadosSolicitudDTO transformarConDependencias(ApoderadosSolicitud obj) {
		ApoderadosSolicitudDTO apoderadosSolicitudDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return apoderadosSolicitudDTO;
	}

	@Override
	public ApoderadosSolicitud transformarEntidadSinDependencias(ApoderadosSolicitud obj) {
		ApoderadosSolicitud apoderadosSolicitud = new ApoderadosSolicitud();
		
		apoderadosSolicitud.setApoderadosSolicitudPK(obj.getApoderadosSolicitudPK());
		
		apoderadosSolicitud.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		apoderadosSolicitud.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		apoderadosSolicitud.setEstadoRegistro(obj.getEstadoRegistro());
		
		return apoderadosSolicitud;
	}


	@Override
	public ApoderadosSolicitud transformarEntidadConDependencias(ApoderadosSolicitud obj) {
		ApoderadosSolicitud apoderadosSolicitud = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return apoderadosSolicitud;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ApoderadosSolicitud> coleccion) {
		List<ApoderadosSolicitudDTO> apoderadosSolicitudDTOList = new ArrayList<>();
		for(ApoderadosSolicitud c : coleccion)
			apoderadosSolicitudDTOList.add(transformarConDependencias(c));
		return apoderadosSolicitudDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ApoderadosSolicitud> coleccion) {
		List<ApoderadosSolicitudDTO> apoderadosSolicitudDTOList = new ArrayList<>();
		for(ApoderadosSolicitud c : coleccion)
			apoderadosSolicitudDTOList.add(transformarSinDependencias(c));
		return apoderadosSolicitudDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ApoderadosSolicitud> coleccion) {
		List<ApoderadosSolicitud> apoderadosSolicitudList = new ArrayList<>();
		for(ApoderadosSolicitud c : coleccion)
			apoderadosSolicitudList.add(transformarEntidadConDependencias(c));
		return apoderadosSolicitudList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ApoderadosSolicitud> coleccion) {
		List<ApoderadosSolicitud> apoderadosSolicitudList = new ArrayList<>();
		for(ApoderadosSolicitud c : coleccion)
			apoderadosSolicitudList.add(transformarEntidadSinDependencias(c));
		return apoderadosSolicitudList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
