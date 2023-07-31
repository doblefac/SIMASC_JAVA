package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.CorreoElectronicoPersonaSolicitud;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.CorreoElectronicoPersonaSolicitudPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad CorreoElectronicoPersonaSolicitudDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class CorreoElectronicoPersonaSolicitudDTO extends IDTO<CorreoElectronicoPersonaSolicitud> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private CorreoElectronicoPersonaSolicitudPK correoElectronicoPersonaSolicitudPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	
    public CorreoElectronicoPersonaSolicitudDTO(){
		correoElectronicoPersonaSolicitudPK = new CorreoElectronicoPersonaSolicitudPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public CorreoElectronicoPersonaSolicitudPK getCorreoElectronicoPersonaSolicitudPK(){
		return this.correoElectronicoPersonaSolicitudPK;
	}
	
	public void setCorreoElectronicoPersonaSolicitudPK(CorreoElectronicoPersonaSolicitudPK correoElectronicoPersonaSolicitudPK){
		this.correoElectronicoPersonaSolicitudPK   = correoElectronicoPersonaSolicitudPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.correoElectronicoPersonaSolicitudPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CorreoElectronicoPersonaSolicitudDTO que se pasa
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
        final CorreoElectronicoPersonaSolicitudDTO other = (CorreoElectronicoPersonaSolicitudDTO) obj;
                
        if (!Objects.equals(this.correoElectronicoPersonaSolicitudPK, other.correoElectronicoPersonaSolicitudPK)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        return Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion);
                
    }
    
    @Override
	public CorreoElectronicoPersonaSolicitudDTO transformarSinDependencias(CorreoElectronicoPersonaSolicitud obj) {
		CorreoElectronicoPersonaSolicitudDTO correoElectronicoPersonaSolicitudDTO = new CorreoElectronicoPersonaSolicitudDTO();
		
		correoElectronicoPersonaSolicitudDTO.setCorreoElectronicoPersonaSolicitudPK(obj.getCorreoElectronicoPersonaSolicitudPK());
		correoElectronicoPersonaSolicitudDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		correoElectronicoPersonaSolicitudDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		
		return correoElectronicoPersonaSolicitudDTO;
	}

	@Override
	public CorreoElectronicoPersonaSolicitudDTO transformarConDependencias(CorreoElectronicoPersonaSolicitud obj) {
		CorreoElectronicoPersonaSolicitudDTO correoElectronicoPersonaSolicitudDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return correoElectronicoPersonaSolicitudDTO;
	}

	@Override
	public CorreoElectronicoPersonaSolicitud transformarEntidadSinDependencias(CorreoElectronicoPersonaSolicitud obj) {
		CorreoElectronicoPersonaSolicitud correoElectronicoPersonaSolicitud = new CorreoElectronicoPersonaSolicitud();
		
		correoElectronicoPersonaSolicitud.setCorreoElectronicoPersonaSolicitudPK(obj.getCorreoElectronicoPersonaSolicitudPK());
		
		correoElectronicoPersonaSolicitud.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		correoElectronicoPersonaSolicitud.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		
		return correoElectronicoPersonaSolicitud;
	}


	@Override
	public CorreoElectronicoPersonaSolicitud transformarEntidadConDependencias(CorreoElectronicoPersonaSolicitud obj) {
		CorreoElectronicoPersonaSolicitud correoElectronicoPersonaSolicitud = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return correoElectronicoPersonaSolicitud;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<CorreoElectronicoPersonaSolicitud> coleccion) {
		List<CorreoElectronicoPersonaSolicitudDTO> correoElectronicoPersonaSolicitudDTOList = new ArrayList<>();
		for(CorreoElectronicoPersonaSolicitud c : coleccion)
			correoElectronicoPersonaSolicitudDTOList.add(transformarConDependencias(c));
		return correoElectronicoPersonaSolicitudDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<CorreoElectronicoPersonaSolicitud> coleccion) {
		List<CorreoElectronicoPersonaSolicitudDTO> correoElectronicoPersonaSolicitudDTOList = new ArrayList<>();
		for(CorreoElectronicoPersonaSolicitud c : coleccion)
			correoElectronicoPersonaSolicitudDTOList.add(transformarSinDependencias(c));
		return correoElectronicoPersonaSolicitudDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<CorreoElectronicoPersonaSolicitud> coleccion) {
		List<CorreoElectronicoPersonaSolicitud> correoElectronicoPersonaSolicitudList = new ArrayList<>();
		for(CorreoElectronicoPersonaSolicitud c : coleccion)
			correoElectronicoPersonaSolicitudList.add(transformarEntidadConDependencias(c));
		return correoElectronicoPersonaSolicitudList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<CorreoElectronicoPersonaSolicitud> coleccion) {
		List<CorreoElectronicoPersonaSolicitud> correoElectronicoPersonaSolicitudList = new ArrayList<>();
		for(CorreoElectronicoPersonaSolicitud c : coleccion)
			correoElectronicoPersonaSolicitudList.add(transformarEntidadSinDependencias(c));
		return correoElectronicoPersonaSolicitudList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
