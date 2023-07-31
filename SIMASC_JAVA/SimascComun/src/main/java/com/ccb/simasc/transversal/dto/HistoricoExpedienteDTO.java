package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.HistoricoExpediente;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad HistoricoExpedienteDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class HistoricoExpedienteDTO extends IDTO<HistoricoExpediente> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	
	private String nombreCompleto;

	// protected region atributos end
	private Long idHistoricoExpediente;
	private Date fecha;		
	private String observaciones;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idCaso;		
	private Long idPersona;
	private String tipoEntrega;		
	
    public HistoricoExpedienteDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdHistoricoExpediente(){
		return this.idHistoricoExpediente;
	}
	
	public void setIdHistoricoExpediente(Long idHistoricoExpediente){
		this.idHistoricoExpediente = idHistoricoExpediente;
	}
	
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
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
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
	
	/**
	 * @return the tipoEntrega
	 */
	public String getTipoEntrega() {
		return tipoEntrega;
	}



	/**
	 * @param tipoEntrega the tipoEntrega to set
	 */
	public void setTipoEntrega(String tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idHistoricoExpediente);        
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.tipoEntrega);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad HistoricoExpedienteDTO que se pasa
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
        final HistoricoExpedienteDTO other = (HistoricoExpedienteDTO) obj;
                
        if (!Objects.equals(this.idHistoricoExpediente, other.idHistoricoExpediente)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
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
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        if (!Objects.equals(this.tipoEntrega, other.tipoEntrega)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
    
    @Override
	public HistoricoExpedienteDTO transformarSinDependencias(HistoricoExpediente obj) {
		HistoricoExpedienteDTO historicoExpedienteDTO = new HistoricoExpedienteDTO();
		
		historicoExpedienteDTO.setIdHistoricoExpediente(obj.getIdHistoricoExpediente());
		historicoExpedienteDTO.setFecha(obj.getFecha());
		historicoExpedienteDTO.setObservaciones(obj.getObservaciones());
		historicoExpedienteDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		historicoExpedienteDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		historicoExpedienteDTO.setEstadoRegistro(obj.getEstadoRegistro());
		historicoExpedienteDTO.setIdCaso(obj.getIdCaso());
		historicoExpedienteDTO.setIdPersona(obj.getIdPersona());
		historicoExpedienteDTO.setTipoEntrega(obj.getTipoEntrega());
		
		return historicoExpedienteDTO;
	}

	@Override
	public HistoricoExpedienteDTO transformarConDependencias(HistoricoExpediente obj) {
		HistoricoExpedienteDTO historicoExpedienteDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return historicoExpedienteDTO;
	}

	@Override
	public HistoricoExpediente transformarEntidadSinDependencias(HistoricoExpediente obj) {
		HistoricoExpediente historicoExpediente = new HistoricoExpediente();
		
		historicoExpediente.setIdHistoricoExpediente(obj.getIdHistoricoExpediente());
		
		historicoExpediente.setFecha(obj.getFecha());
		historicoExpediente.setObservaciones(obj.getObservaciones());
		historicoExpediente.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		historicoExpediente.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		historicoExpediente.setEstadoRegistro(obj.getEstadoRegistro());
		historicoExpediente.setIdCaso(obj.getIdCaso());
		historicoExpediente.setIdPersona(obj.getIdPersona());
		historicoExpediente.setTipoEntrega(obj.getTipoEntrega());
		
		return historicoExpediente;
	}


	@Override
	public HistoricoExpediente transformarEntidadConDependencias(HistoricoExpediente obj) {
		HistoricoExpediente historicoExpediente = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return historicoExpediente;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<HistoricoExpediente> coleccion) {
		List<HistoricoExpedienteDTO> historicoExpedienteDTOList = new ArrayList<>();
		for(HistoricoExpediente c : coleccion)
			historicoExpedienteDTOList.add(transformarConDependencias(c));
		return historicoExpedienteDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<HistoricoExpediente> coleccion) {
		List<HistoricoExpedienteDTO> historicoExpedienteDTOList = new ArrayList<>();
		for(HistoricoExpediente c : coleccion)
			historicoExpedienteDTOList.add(transformarSinDependencias(c));
		return historicoExpedienteDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<HistoricoExpediente> coleccion) {
		List<HistoricoExpediente> historicoExpedienteList = new ArrayList<>();
		for(HistoricoExpediente c : coleccion)
			historicoExpedienteList.add(transformarEntidadConDependencias(c));
		return historicoExpedienteList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<HistoricoExpediente> coleccion) {
		List<HistoricoExpediente> historicoExpedienteList = new ArrayList<>();
		for(HistoricoExpediente c : coleccion)
			historicoExpedienteList.add(transformarEntidadSinDependencias(c));
		return historicoExpedienteList;
	}



	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}



	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	// protected region metodos adicionales end

}
