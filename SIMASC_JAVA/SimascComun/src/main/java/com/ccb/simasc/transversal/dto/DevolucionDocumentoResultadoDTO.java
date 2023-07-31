package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.DevolucionDocumentoResultado;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;


// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad DevolucionDocumentoResultadoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class DevolucionDocumentoResultadoDTO extends IDTO<DevolucionDocumentoResultado> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idDevolucionDocumentoResultado;

	private Date fecha;		
	private boolean corrige;		
	private String observaciones;		
	private Date fechaCorreccion;		
	private String observacionesRespuesta;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idPersona;	
	private Long idDocumento;		
	
    public DevolucionDocumentoResultadoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdDevolucionDocumentoResultado(){
		return this.idDevolucionDocumentoResultado;
	}
	
	public void setIdDevolucionDocumentoResultado(Long idDevolucionDocumentoResultado){
		this.idDevolucionDocumentoResultado = idDevolucionDocumentoResultado;
	}
	
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public boolean getCorrige(){
		return this.corrige;
	}
	
	public void setCorrige(boolean corrige){
		this.corrige = corrige;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public Date getFechaCorreccion(){
		return this.fechaCorreccion;
	}
	
	public void setFechaCorreccion(Date fechaCorreccion){
		this.fechaCorreccion = fechaCorreccion;
	}
		
	public String getObservacionesRespuesta(){
		return this.observacionesRespuesta;
	}
	
	public void setObservacionesRespuesta(String observacionesRespuesta){
		this.observacionesRespuesta = observacionesRespuesta;
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
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idDevolucionDocumentoResultado);        
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + (this.corrige ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.fechaCorreccion);
        hash = 37 * hash + Objects.hashCode(this.observacionesRespuesta);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DevolucionDocumentoResultadoDTO que se pasa
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
        final DevolucionDocumentoResultadoDTO other = (DevolucionDocumentoResultadoDTO) obj;
                
        if (!Objects.equals(this.idDevolucionDocumentoResultado, other.idDevolucionDocumentoResultado)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.corrige, other.corrige)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaCorreccion, other.fechaCorreccion)) {
            return false;
        }
        
        if (!Objects.equals(this.observacionesRespuesta, other.observacionesRespuesta)) {
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
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        return Objects.equals(this.idDocumento, other.idDocumento);
                
    }
    
    @Override
	public DevolucionDocumentoResultadoDTO transformarSinDependencias(DevolucionDocumentoResultado obj) {
		DevolucionDocumentoResultadoDTO devolucionDocumentoResultadoDTO = new DevolucionDocumentoResultadoDTO();
		
		devolucionDocumentoResultadoDTO.setIdDevolucionDocumentoResultado(obj.getIdDevolucionDocumentoResultado());
		devolucionDocumentoResultadoDTO.setFecha(obj.getFecha());
		devolucionDocumentoResultadoDTO.setCorrige(obj.getCorrige());
		devolucionDocumentoResultadoDTO.setObservaciones(obj.getObservaciones());
		devolucionDocumentoResultadoDTO.setFechaCorreccion(obj.getFechaCorreccion());
		devolucionDocumentoResultadoDTO.setObservacionesRespuesta(obj.getObservacionesRespuesta());
		devolucionDocumentoResultadoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		devolucionDocumentoResultadoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		devolucionDocumentoResultadoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		devolucionDocumentoResultadoDTO.setIdPersona(obj.getIdPersona());
		devolucionDocumentoResultadoDTO.setIdDocumento(obj.getIdDocumento());
		
		return devolucionDocumentoResultadoDTO;
	}

	@Override
	public DevolucionDocumentoResultadoDTO transformarConDependencias(DevolucionDocumentoResultado obj) {
		DevolucionDocumentoResultadoDTO devolucionDocumentoResultadoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return devolucionDocumentoResultadoDTO;
	}

	@Override
	public DevolucionDocumentoResultado transformarEntidadSinDependencias(DevolucionDocumentoResultado obj) {
		DevolucionDocumentoResultado devolucionDocumentoResultado = new DevolucionDocumentoResultado();
		
		devolucionDocumentoResultado.setIdDevolucionDocumentoResultado(obj.getIdDevolucionDocumentoResultado());
		
		devolucionDocumentoResultado.setFecha(obj.getFecha());
		devolucionDocumentoResultado.setCorrige(obj.getCorrige());
		devolucionDocumentoResultado.setObservaciones(obj.getObservaciones());
		devolucionDocumentoResultado.setFechaCorreccion(obj.getFechaCorreccion());
		devolucionDocumentoResultado.setObservacionesRespuesta(obj.getObservacionesRespuesta());
		devolucionDocumentoResultado.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		devolucionDocumentoResultado.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		devolucionDocumentoResultado.setEstadoRegistro(obj.getEstadoRegistro());
		devolucionDocumentoResultado.setIdPersona(obj.getIdPersona());
		devolucionDocumentoResultado.setIdDocumento(obj.getIdDocumento());
		
		return devolucionDocumentoResultado;
	}


	@Override
	public DevolucionDocumentoResultado transformarEntidadConDependencias(DevolucionDocumentoResultado obj) {
		DevolucionDocumentoResultado devolucionDocumentoResultado = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return devolucionDocumentoResultado;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<DevolucionDocumentoResultado> coleccion) {
		List<DevolucionDocumentoResultadoDTO> devolucionDocumentoResultadoDTOList = new ArrayList<>();
		for(DevolucionDocumentoResultado c : coleccion)
			devolucionDocumentoResultadoDTOList.add(transformarConDependencias(c));
		return devolucionDocumentoResultadoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<DevolucionDocumentoResultado> coleccion) {
		List<DevolucionDocumentoResultadoDTO> devolucionDocumentoResultadoDTOList = new ArrayList<>();
		for(DevolucionDocumentoResultado c : coleccion)
			devolucionDocumentoResultadoDTOList.add(transformarSinDependencias(c));
		return devolucionDocumentoResultadoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<DevolucionDocumentoResultado> coleccion) {
		List<DevolucionDocumentoResultado> devolucionDocumentoResultadoList = new ArrayList<>();
		for(DevolucionDocumentoResultado c : coleccion)
			devolucionDocumentoResultadoList.add(transformarEntidadConDependencias(c));
		return devolucionDocumentoResultadoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<DevolucionDocumentoResultado> coleccion) {
		List<DevolucionDocumentoResultado> devolucionDocumentoResultadoList = new ArrayList<>();
		for(DevolucionDocumentoResultado c : coleccion)
			devolucionDocumentoResultadoList.add(transformarEntidadSinDependencias(c));
		return devolucionDocumentoResultadoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
