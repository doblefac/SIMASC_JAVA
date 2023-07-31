package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.Peticion;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PeticionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PeticionDTO extends IDTO<Peticion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idPeticion;

	private String tipo;		
	private String texto;		
	private Date fechaRespuesta;	
	private Date fechaRadicacion;
	private String respuesta;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idCaso;		
	private List<Long> idCentros;
	
	

	
    public PeticionDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdPeticion(){
		return this.idPeticion;
	}
	
	public void setIdPeticion(Long idPeticion){
		this.idPeticion = idPeticion;
	}
	
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public String getTexto(){
		return this.texto;
	}
	
	public void setTexto(String texto){
		this.texto = texto;
	}
		
	public Date getFechaRespuesta(){
		return this.fechaRespuesta;
	}
	
	public void setFechaRespuesta(Date fechaRespuesta){
		this.fechaRespuesta = fechaRespuesta;
	}
		
	public String getRespuesta(){
		return this.respuesta;
	}
	
	public void setRespuesta(String respuesta){
		this.respuesta = respuesta;
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
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}

	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}

	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idPeticion);        
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.texto);
        hash = 37 * hash + Objects.hashCode(this.fechaRespuesta);
        hash = 37 * hash + Objects.hashCode(this.fechaRadicacion);  
        hash = 37 * hash + Objects.hashCode(this.respuesta);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PeticionDTO que se pasa
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
        final PeticionDTO other = (PeticionDTO) obj;
                
        if (!Objects.equals(this.idPeticion, other.idPeticion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRespuesta, other.fechaRespuesta)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRadicacion, other.fechaRadicacion)) {
            return false;
        }
        
        if (!Objects.equals(this.respuesta, other.respuesta)) {
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
        
        return Objects.equals(this.idCaso, other.idCaso);
                
    }
    
    @Override
	public PeticionDTO transformarSinDependencias(Peticion obj) {
		PeticionDTO peticionDTO = new PeticionDTO();
		
		peticionDTO.setIdPeticion(obj.getIdPeticion());
		peticionDTO.setTipo(obj.getTipo());
		peticionDTO.setTexto(obj.getTexto());
		peticionDTO.setFechaRespuesta(obj.getFechaRespuesta());
		peticionDTO.setFechaRadicacion(obj.getFechaRadicacion());
		peticionDTO.setRespuesta(obj.getRespuesta());
		peticionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		peticionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		peticionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		peticionDTO.setIdCaso(obj.getIdCaso());
		
		return peticionDTO;
	}

	@Override
	public PeticionDTO transformarConDependencias(Peticion obj) {
		PeticionDTO peticionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return peticionDTO;
	}

	@Override
	public Peticion transformarEntidadSinDependencias(Peticion obj) {
		Peticion peticion = new Peticion();
		
		peticion.setIdPeticion(obj.getIdPeticion());
		
		peticion.setTipo(obj.getTipo());
		peticion.setTexto(obj.getTexto());
		peticion.setFechaRespuesta(obj.getFechaRespuesta());
		peticion.setFechaRadicacion(obj.getFechaRadicacion());
		peticion.setRespuesta(obj.getRespuesta());
		peticion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		peticion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		peticion.setEstadoRegistro(obj.getEstadoRegistro());
		peticion.setIdCaso(obj.getIdCaso());
		
		return peticion;
	}


	@Override
	public Peticion transformarEntidadConDependencias(Peticion obj) {
		Peticion peticion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return peticion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Peticion> coleccion) {
		List<PeticionDTO> peticionDTOList = new ArrayList<>();
		for(Peticion c : coleccion)
			peticionDTOList.add(transformarConDependencias(c));
		return peticionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Peticion> coleccion) {
		List<PeticionDTO> peticionDTOList = new ArrayList<>();
		for(Peticion c : coleccion)
			peticionDTOList.add(transformarSinDependencias(c));
		return peticionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Peticion> coleccion) {
		List<Peticion> peticionList = new ArrayList<>();
		for(Peticion c : coleccion)
			peticionList.add(transformarEntidadConDependencias(c));
		return peticionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Peticion> coleccion) {
		List<Peticion> peticionList = new ArrayList<>();
		for(Peticion c : coleccion)
			peticionList.add(transformarEntidadSinDependencias(c));
		return peticionList;
	}



	/**
	 * @return the idCentros
	 */
	public List<Long> getIdCentros() {
		return idCentros;
	}



	/**
	 * @param idCentros the idCentros to set
	 */
	public void setIdCentros(List<Long> idCentros) {
		this.idCentros = idCentros;
	}




	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
