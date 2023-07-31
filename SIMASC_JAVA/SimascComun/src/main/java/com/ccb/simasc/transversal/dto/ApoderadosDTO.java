package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.Apoderados;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.ApoderadosPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ApoderadosDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ApoderadosDTO extends IDTO<Apoderados> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private ApoderadosPK apoderadosPK;

	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private String idUsuarioModificacion;		
	
    public ApoderadosDTO(){
		apoderadosPK = new ApoderadosPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ApoderadosPK getApoderadosPK(){
		return this.apoderadosPK;
	}
	
	public void setApoderadosPK(ApoderadosPK apoderadosPK){
		this.apoderadosPK   = apoderadosPK ;
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
		
	public String getIdUsuarioModificacion(){
		return this.idUsuarioModificacion;
	}
	
	public void setIdUsuarioModificacion(String idUsuarioModificacion){
		this.idUsuarioModificacion = idUsuarioModificacion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.apoderadosPK);        
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ApoderadosDTO que se pasa
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
        final ApoderadosDTO other = (ApoderadosDTO) obj;
                
        if (!Objects.equals(this.apoderadosPK, other.apoderadosPK)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoRegistro, other.estadoRegistro)) {
            return false;
        }
        
        return Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion);
                
    }
    
    @Override
	public ApoderadosDTO transformarSinDependencias(Apoderados obj) {
		ApoderadosDTO apoderadosDTO = new ApoderadosDTO();
		
		apoderadosDTO.setApoderadosPK(obj.getApoderadosPK());
		apoderadosDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		apoderadosDTO.setEstadoRegistro(obj.getEstadoRegistro());
		apoderadosDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		
		return apoderadosDTO;
	}

	@Override
	public ApoderadosDTO transformarConDependencias(Apoderados obj) {
		ApoderadosDTO apoderadosDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return apoderadosDTO;
	}

	@Override
	public Apoderados transformarEntidadSinDependencias(Apoderados obj) {
		Apoderados apoderados = new Apoderados();
		
		apoderados.setApoderadosPK(obj.getApoderadosPK());
		
		apoderados.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		apoderados.setEstadoRegistro(obj.getEstadoRegistro());
		apoderados.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		
		return apoderados;
	}


	@Override
	public Apoderados transformarEntidadConDependencias(Apoderados obj) {
		Apoderados apoderados = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return apoderados;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Apoderados> coleccion) {
		List<ApoderadosDTO> apoderadosDTOList = new ArrayList<>();
		for(Apoderados c : coleccion)
			apoderadosDTOList.add(transformarConDependencias(c));
		return apoderadosDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Apoderados> coleccion) {
		List<ApoderadosDTO> apoderadosDTOList = new ArrayList<>();
		for(Apoderados c : coleccion)
			apoderadosDTOList.add(transformarSinDependencias(c));
		return apoderadosDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Apoderados> coleccion) {
		List<Apoderados> apoderadosList = new ArrayList<>();
		for(Apoderados c : coleccion)
			apoderadosList.add(transformarEntidadConDependencias(c));
		return apoderadosList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Apoderados> coleccion) {
		List<Apoderados> apoderadosList = new ArrayList<>();
		for(Apoderados c : coleccion)
			apoderadosList.add(transformarEntidadSinDependencias(c));
		return apoderadosList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
