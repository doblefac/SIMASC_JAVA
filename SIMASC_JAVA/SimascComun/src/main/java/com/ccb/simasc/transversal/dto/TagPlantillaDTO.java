package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.TagPlantilla;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad TagPlantillaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class TagPlantillaDTO extends IDTO<TagPlantilla> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idTagPlantilla;

	private String codigo;		
	private String nombre;
	private String consulta;		
	private String tipoServicio;		
	private String descripcion;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private String tipoConsulta;
	
    public TagPlantillaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdTagPlantilla(){
		return this.idTagPlantilla;
	}
	
	public void setIdTagPlantilla(Long idTagPlantilla){
		this.idTagPlantilla = idTagPlantilla;
	}
	
	public String getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		
	public String getConsulta(){
		return this.consulta;
	}
	
	public void setConsulta(String consulta){
		this.consulta = consulta;
	}
		
	public String getTipoServicio(){
		return this.tipoServicio;
	}
	
	public void setTipoServicio(String tipoServicio){
		this.tipoServicio = tipoServicio;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
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
	
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idTagPlantilla);        
        hash = 37 * hash + Objects.hashCode(this.codigo);
        hash = 37 * hash + Objects.hashCode(this.nombre);        
        hash = 37 * hash + Objects.hashCode(this.consulta);
        hash = 37 * hash + Objects.hashCode(this.tipoServicio);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.tipoConsulta);   
  
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TagPlantillaDTO que se pasa
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
        final TagPlantillaDTO other = (TagPlantillaDTO) obj;
                
        if (!Objects.equals(this.idTagPlantilla, other.idTagPlantilla)) {
            return false;
        }
        
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.consulta, other.consulta)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoServicio, other.tipoServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoConsulta, other.tipoConsulta)) {
            return false;
        }  
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
    
    @Override
	public TagPlantillaDTO transformarSinDependencias(TagPlantilla obj) {
		TagPlantillaDTO tagPlantillaDTO = new TagPlantillaDTO();
		
		tagPlantillaDTO.setIdTagPlantilla(obj.getIdTagPlantilla());
		tagPlantillaDTO.setCodigo(obj.getCodigo());
		tagPlantillaDTO.setNombre(obj.getNombre());
		tagPlantillaDTO.setConsulta(obj.getConsulta());
		tagPlantillaDTO.setTipoServicio(obj.getTipoServicio());
		tagPlantillaDTO.setDescripcion(obj.getDescripcion());
		tagPlantillaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		tagPlantillaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		tagPlantillaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		tagPlantillaDTO.setTipoConsulta(obj.getTipoConsulta());	
		
		return tagPlantillaDTO;
	}

	@Override
	public TagPlantillaDTO transformarConDependencias(TagPlantilla obj) {
		TagPlantillaDTO tagPlantillaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return tagPlantillaDTO;
	}

	@Override
	public TagPlantilla transformarEntidadSinDependencias(TagPlantilla obj) {
		TagPlantilla tagPlantilla = new TagPlantilla();
		
		tagPlantilla.setIdTagPlantilla(obj.getIdTagPlantilla());		
		tagPlantilla.setCodigo(obj.getCodigo());
		tagPlantilla.setNombre(obj.getNombre());
		tagPlantilla.setConsulta(obj.getConsulta());
		tagPlantilla.setTipoServicio(obj.getTipoServicio());
		tagPlantilla.setDescripcion(obj.getDescripcion());
		tagPlantilla.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		tagPlantilla.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		tagPlantilla.setEstadoRegistro(obj.getEstadoRegistro());
		tagPlantilla.setTipoConsulta(obj.getTipoConsulta());
		
		return tagPlantilla;
	}


	@Override
	public TagPlantilla transformarEntidadConDependencias(TagPlantilla obj) {
		TagPlantilla tagPlantilla = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return tagPlantilla;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<TagPlantilla> coleccion) {
		List<TagPlantillaDTO> tagPlantillaDTOList = new ArrayList<>();
		for(TagPlantilla c : coleccion)
			tagPlantillaDTOList.add(transformarConDependencias(c));
		return tagPlantillaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<TagPlantilla> coleccion) {
		List<TagPlantillaDTO> tagPlantillaDTOList = new ArrayList<>();
		for(TagPlantilla c : coleccion)
			tagPlantillaDTOList.add(transformarSinDependencias(c));
		return tagPlantillaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<TagPlantilla> coleccion) {
		List<TagPlantilla> tagPlantillaList = new ArrayList<>();
		for(TagPlantilla c : coleccion)
			tagPlantillaList.add(transformarEntidadConDependencias(c));
		return tagPlantillaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<TagPlantilla> coleccion) {
		List<TagPlantilla> tagPlantillaList = new ArrayList<>();
		for(TagPlantilla c : coleccion)
			tagPlantillaList.add(transformarEntidadSinDependencias(c));
		return tagPlantillaList;
	}








	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
