package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.PartePeticion;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.PartePeticionPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PartePeticionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PartePeticionDTO extends IDTO<PartePeticion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private PartePeticionPK partePeticionPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public PartePeticionDTO(){
		partePeticionPK = new PartePeticionPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public PartePeticionPK getPartePeticionPK(){
		return this.partePeticionPK;
	}
	
	public void setPartePeticionPK(PartePeticionPK partePeticionPK){
		this.partePeticionPK   = partePeticionPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.partePeticionPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PartePeticionDTO que se pasa
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
        final PartePeticionDTO other = (PartePeticionDTO) obj;
                
        if (!Objects.equals(this.partePeticionPK, other.partePeticionPK)) {
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
	public PartePeticionDTO transformarSinDependencias(PartePeticion obj) {
		PartePeticionDTO partePeticionDTO = new PartePeticionDTO();
		
		partePeticionDTO.setPartePeticionPK(obj.getPartePeticionPK());
		partePeticionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		partePeticionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		partePeticionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return partePeticionDTO;
	}

	@Override
	public PartePeticionDTO transformarConDependencias(PartePeticion obj) {
		PartePeticionDTO partePeticionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return partePeticionDTO;
	}

	@Override
	public PartePeticion transformarEntidadSinDependencias(PartePeticion obj) {
		PartePeticion partePeticion = new PartePeticion();
		
		partePeticion.setPartePeticionPK(obj.getPartePeticionPK());
		
		partePeticion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		partePeticion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		partePeticion.setEstadoRegistro(obj.getEstadoRegistro());
		
		return partePeticion;
	}


	@Override
	public PartePeticion transformarEntidadConDependencias(PartePeticion obj) {
		PartePeticion partePeticion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return partePeticion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<PartePeticion> coleccion) {
		List<PartePeticionDTO> partePeticionDTOList = new ArrayList<>();
		for(PartePeticion c : coleccion)
			partePeticionDTOList.add(transformarConDependencias(c));
		return partePeticionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<PartePeticion> coleccion) {
		List<PartePeticionDTO> partePeticionDTOList = new ArrayList<>();
		for(PartePeticion c : coleccion)
			partePeticionDTOList.add(transformarSinDependencias(c));
		return partePeticionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<PartePeticion> coleccion) {
		List<PartePeticion> partePeticionList = new ArrayList<>();
		for(PartePeticion c : coleccion)
			partePeticionList.add(transformarEntidadConDependencias(c));
		return partePeticionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<PartePeticion> coleccion) {
		List<PartePeticion> partePeticionList = new ArrayList<>();
		for(PartePeticion c : coleccion)
			partePeticionList.add(transformarEntidadSinDependencias(c));
		return partePeticionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
