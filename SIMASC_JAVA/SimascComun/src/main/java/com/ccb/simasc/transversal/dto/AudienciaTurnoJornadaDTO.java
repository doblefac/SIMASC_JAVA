package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.AudienciaTurnoJornada;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.AudienciaTurnoJornadaPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad AudienciaTurnoJornadaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class AudienciaTurnoJornadaDTO extends IDTO<AudienciaTurnoJornada> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private AudienciaTurnoJornadaPK audienciaTurnoJornadaPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public AudienciaTurnoJornadaDTO(){
		audienciaTurnoJornadaPK = new AudienciaTurnoJornadaPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public AudienciaTurnoJornadaPK getAudienciaTurnoJornadaPK(){
		return this.audienciaTurnoJornadaPK;
	}
	
	public void setAudienciaTurnoJornadaPK(AudienciaTurnoJornadaPK audienciaTurnoJornadaPK){
		this.audienciaTurnoJornadaPK   = audienciaTurnoJornadaPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.audienciaTurnoJornadaPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AudienciaTurnoJornadaDTO que se pasa
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
        final AudienciaTurnoJornadaDTO other = (AudienciaTurnoJornadaDTO) obj;
                
        if (!Objects.equals(this.audienciaTurnoJornadaPK, other.audienciaTurnoJornadaPK)) {
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
	public AudienciaTurnoJornadaDTO transformarSinDependencias(AudienciaTurnoJornada obj) {
		AudienciaTurnoJornadaDTO audienciaTurnoJornadaDTO = new AudienciaTurnoJornadaDTO();
		
		audienciaTurnoJornadaDTO.setAudienciaTurnoJornadaPK(obj.getAudienciaTurnoJornadaPK());
		audienciaTurnoJornadaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		audienciaTurnoJornadaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		audienciaTurnoJornadaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return audienciaTurnoJornadaDTO;
	}

	@Override
	public AudienciaTurnoJornadaDTO transformarConDependencias(AudienciaTurnoJornada obj) {
		AudienciaTurnoJornadaDTO audienciaTurnoJornadaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return audienciaTurnoJornadaDTO;
	}

	@Override
	public AudienciaTurnoJornada transformarEntidadSinDependencias(AudienciaTurnoJornada obj) {
		AudienciaTurnoJornada audienciaTurnoJornada = new AudienciaTurnoJornada();
		
		audienciaTurnoJornada.setAudienciaTurnoJornadaPK(obj.getAudienciaTurnoJornadaPK());
		
		audienciaTurnoJornada.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		audienciaTurnoJornada.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		audienciaTurnoJornada.setEstadoRegistro(obj.getEstadoRegistro());
		
		return audienciaTurnoJornada;
	}


	@Override
	public AudienciaTurnoJornada transformarEntidadConDependencias(AudienciaTurnoJornada obj) {
		AudienciaTurnoJornada audienciaTurnoJornada = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return audienciaTurnoJornada;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<AudienciaTurnoJornada> coleccion) {
		List<AudienciaTurnoJornadaDTO> audienciaTurnoJornadaDTOList = new ArrayList<>();
		for(AudienciaTurnoJornada c : coleccion)
			audienciaTurnoJornadaDTOList.add(transformarConDependencias(c));
		return audienciaTurnoJornadaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<AudienciaTurnoJornada> coleccion) {
		List<AudienciaTurnoJornadaDTO> audienciaTurnoJornadaDTOList = new ArrayList<>();
		for(AudienciaTurnoJornada c : coleccion)
			audienciaTurnoJornadaDTOList.add(transformarSinDependencias(c));
		return audienciaTurnoJornadaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<AudienciaTurnoJornada> coleccion) {
		List<AudienciaTurnoJornada> audienciaTurnoJornadaList = new ArrayList<>();
		for(AudienciaTurnoJornada c : coleccion)
			audienciaTurnoJornadaList.add(transformarEntidadConDependencias(c));
		return audienciaTurnoJornadaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<AudienciaTurnoJornada> coleccion) {
		List<AudienciaTurnoJornada> audienciaTurnoJornadaList = new ArrayList<>();
		for(AudienciaTurnoJornada c : coleccion)
			audienciaTurnoJornadaList.add(transformarEntidadSinDependencias(c));
		return audienciaTurnoJornadaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
