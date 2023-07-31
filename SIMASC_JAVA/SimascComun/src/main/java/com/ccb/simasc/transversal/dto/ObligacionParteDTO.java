package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.ObligacionParte;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.ObligacionPartePK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ObligacionParteDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ObligacionParteDTO extends IDTO<ObligacionParte> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private ObligacionPartePK obligacionPartePK;

	private String tipoParteResultado;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private String idUsuarioModificacion;		
	
    public ObligacionParteDTO(){
		obligacionPartePK = new ObligacionPartePK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ObligacionPartePK getObligacionPartePK(){
		return this.obligacionPartePK;
	}
	
	public void setObligacionPartePK(ObligacionPartePK obligacionPartePK){
		this.obligacionPartePK   = obligacionPartePK ;
	}  
	
	public String getTipoParteResultado(){
		return this.tipoParteResultado;
	}
	
	public void setTipoParteResultado(String tipoParteResultado){
		this.tipoParteResultado = tipoParteResultado;
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
        
        hash = 37 * hash + Objects.hashCode(this.obligacionPartePK);        
        hash = 37 * hash + Objects.hashCode(this.tipoParteResultado);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ObligacionParteDTO que se pasa
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
        final ObligacionParteDTO other = (ObligacionParteDTO) obj;
                
        if (!Objects.equals(this.obligacionPartePK, other.obligacionPartePK)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoParteResultado, other.tipoParteResultado)) {
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
	public ObligacionParteDTO transformarSinDependencias(ObligacionParte obj) {
		ObligacionParteDTO obligacionParteDTO = new ObligacionParteDTO();
		
		obligacionParteDTO.setObligacionPartePK(obj.getObligacionPartePK());
		obligacionParteDTO.setTipoParteResultado(obj.getTipoParteResultado());
		obligacionParteDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		obligacionParteDTO.setEstadoRegistro(obj.getEstadoRegistro());
		obligacionParteDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		
		return obligacionParteDTO;
	}

	@Override
	public ObligacionParteDTO transformarConDependencias(ObligacionParte obj) {
		ObligacionParteDTO obligacionParteDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return obligacionParteDTO;
	}

	@Override
	public ObligacionParte transformarEntidadSinDependencias(ObligacionParte obj) {
		ObligacionParte obligacionParte = new ObligacionParte();
		
		obligacionParte.setObligacionPartePK(obj.getObligacionPartePK());
		
		obligacionParte.setTipoParteResultado(obj.getTipoParteResultado());
		obligacionParte.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		obligacionParte.setEstadoRegistro(obj.getEstadoRegistro());
		obligacionParte.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		
		return obligacionParte;
	}


	@Override
	public ObligacionParte transformarEntidadConDependencias(ObligacionParte obj) {
		ObligacionParte obligacionParte = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return obligacionParte;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ObligacionParte> coleccion) {
		List<ObligacionParteDTO> obligacionParteDTOList = new ArrayList<>();
		for(ObligacionParte c : coleccion)
			obligacionParteDTOList.add(transformarConDependencias(c));
		return obligacionParteDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ObligacionParte> coleccion) {
		List<ObligacionParteDTO> obligacionParteDTOList = new ArrayList<>();
		for(ObligacionParte c : coleccion)
			obligacionParteDTOList.add(transformarSinDependencias(c));
		return obligacionParteDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ObligacionParte> coleccion) {
		List<ObligacionParte> obligacionParteList = new ArrayList<>();
		for(ObligacionParte c : coleccion)
			obligacionParteList.add(transformarEntidadConDependencias(c));
		return obligacionParteList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ObligacionParte> coleccion) {
		List<ObligacionParte> obligacionParteList = new ArrayList<>();
		for(ObligacionParte c : coleccion)
			obligacionParteList.add(transformarEntidadSinDependencias(c));
		return obligacionParteList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
