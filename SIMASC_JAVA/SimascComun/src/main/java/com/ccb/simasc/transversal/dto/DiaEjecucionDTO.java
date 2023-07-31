package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.DiaEjecucion;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.DiaEjecucionPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad DiaEjecucionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class DiaEjecucionDTO extends IDTO<DiaEjecucion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private DiaEjecucionPK diaEjecucionPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public DiaEjecucionDTO(){
		diaEjecucionPK = new DiaEjecucionPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public DiaEjecucionPK getDiaEjecucionPK(){
		return this.diaEjecucionPK;
	}
	
	public void setDiaEjecucionPK(DiaEjecucionPK diaEjecucionPK){
		this.diaEjecucionPK   = diaEjecucionPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.diaEjecucionPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DiaEjecucionDTO que se pasa
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
        final DiaEjecucionDTO other = (DiaEjecucionDTO) obj;
                
        if (!Objects.equals(this.diaEjecucionPK, other.diaEjecucionPK)) {
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
	public DiaEjecucionDTO transformarSinDependencias(DiaEjecucion obj) {
		DiaEjecucionDTO diaEjecucionDTO = new DiaEjecucionDTO();
		
		diaEjecucionDTO.setDiaEjecucionPK(obj.getDiaEjecucionPK());
		diaEjecucionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		diaEjecucionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		diaEjecucionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return diaEjecucionDTO;
	}

	@Override
	public DiaEjecucionDTO transformarConDependencias(DiaEjecucion obj) {
		DiaEjecucionDTO diaEjecucionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return diaEjecucionDTO;
	}

	@Override
	public DiaEjecucion transformarEntidadSinDependencias(DiaEjecucion obj) {
		DiaEjecucion diaEjecucion = new DiaEjecucion();
		
		diaEjecucion.setDiaEjecucionPK(obj.getDiaEjecucionPK());
		
		diaEjecucion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		diaEjecucion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		diaEjecucion.setEstadoRegistro(obj.getEstadoRegistro());
		
		return diaEjecucion;
	}


	@Override
	public DiaEjecucion transformarEntidadConDependencias(DiaEjecucion obj) {
		DiaEjecucion diaEjecucion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return diaEjecucion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<DiaEjecucion> coleccion) {
		List<DiaEjecucionDTO> diaEjecucionDTOList = new ArrayList<>();
		for(DiaEjecucion c : coleccion)
			diaEjecucionDTOList.add(transformarConDependencias(c));
		return diaEjecucionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<DiaEjecucion> coleccion) {
		List<DiaEjecucionDTO> diaEjecucionDTOList = new ArrayList<>();
		for(DiaEjecucion c : coleccion)
			diaEjecucionDTOList.add(transformarSinDependencias(c));
		return diaEjecucionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<DiaEjecucion> coleccion) {
		List<DiaEjecucion> diaEjecucionList = new ArrayList<>();
		for(DiaEjecucion c : coleccion)
			diaEjecucionList.add(transformarEntidadConDependencias(c));
		return diaEjecucionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<DiaEjecucion> coleccion) {
		List<DiaEjecucion> diaEjecucionList = new ArrayList<>();
		for(DiaEjecucion c : coleccion)
			diaEjecucionList.add(transformarEntidadSinDependencias(c));
		return diaEjecucionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
