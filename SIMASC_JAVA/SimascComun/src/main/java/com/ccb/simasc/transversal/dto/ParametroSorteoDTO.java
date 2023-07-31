package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.DiaSorteo;
import com.ccb.simasc.transversal.entidades.ParamEstadoArbitroPreseleccion;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;
import com.ccb.simasc.transversal.entidades.ParametroSorteo;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ParametroSorteoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ParametroSorteoDTO extends IDTO<ParametroSorteo> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idParametrosSorteo;

	private Date horaInicio;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ParametroSorteoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdParametrosSorteo(){
		return this.idParametrosSorteo;
	}
	
	public void setIdParametrosSorteo(Long idParametrosSorteo){
		this.idParametrosSorteo = idParametrosSorteo;
	}
	
	public Date getHoraInicio(){
		return this.horaInicio;
	}
	
	public void setHoraInicio(Date horaInicio){
		this.horaInicio = horaInicio;
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
        
        hash = 37 * hash + Objects.hashCode(this.idParametrosSorteo);        
        hash = 37 * hash + Objects.hashCode(this.horaInicio);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametroSorteoDTO que se pasa
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
        final ParametroSorteoDTO other = (ParametroSorteoDTO) obj;
                
        if (!Objects.equals(this.idParametrosSorteo, other.idParametrosSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
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
	public ParametroSorteoDTO transformarSinDependencias(ParametroSorteo obj) {
		ParametroSorteoDTO parametroSorteoDTO = new ParametroSorteoDTO();
		
		parametroSorteoDTO.setIdParametrosSorteo(obj.getIdParametrosSorteo());
		parametroSorteoDTO.setHoraInicio(obj.getHoraInicio());
		parametroSorteoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parametroSorteoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parametroSorteoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return parametroSorteoDTO;
	}

	@Override
	public ParametroSorteoDTO transformarConDependencias(ParametroSorteo obj) {
		ParametroSorteoDTO parametroSorteoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return parametroSorteoDTO;
	}

	@Override
	public ParametroSorteo transformarEntidadSinDependencias(ParametroSorteo obj) {
		ParametroSorteo parametroSorteo = new ParametroSorteo();
		
		parametroSorteo.setIdParametrosSorteo(obj.getIdParametrosSorteo());
		
		parametroSorteo.setHoraInicio(obj.getHoraInicio());
		parametroSorteo.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parametroSorteo.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parametroSorteo.setEstadoRegistro(obj.getEstadoRegistro());
		
		return parametroSorteo;
	}


	@Override
	public ParametroSorteo transformarEntidadConDependencias(ParametroSorteo obj) {
		ParametroSorteo parametroSorteo = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		
		DiaSorteoDTO dto = new DiaSorteoDTO();		
		ParamEstadoArbitroPreseleccionDTO arbDto = new ParamEstadoArbitroPreseleccionDTO();
		ParametroServicioSorteoDTO paramSort = new ParametroServicioSorteoDTO();
		parametroSorteo.setDiaSorteoList((List<DiaSorteo>)dto.transformarColeccionEntidadesSinDependencias(
				obj.getDiaSorteoList()));
		parametroSorteo.setParamEstadoArbitroPreseleccionList((List<ParamEstadoArbitroPreseleccion>)arbDto.transformarColeccionEntidadesSinDependencias(
				obj.getParamEstadoArbitroPreseleccionList()));
		parametroSorteo.setParametroServicioSorteoList((List<ParametroServicioSorteo>)paramSort.transformarColeccionEntidadesConDependencias(
				obj.getParametroServicioSorteoList()));
						
		// protected region modificaciones transformarEntidadConDependencias end
		
		return parametroSorteo;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ParametroSorteo> coleccion) {
		List<ParametroSorteoDTO> parametroSorteoDTOList = new ArrayList<>();
		for(ParametroSorteo c : coleccion)
			parametroSorteoDTOList.add(transformarConDependencias(c));
		return parametroSorteoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ParametroSorteo> coleccion) {
		List<ParametroSorteoDTO> parametroSorteoDTOList = new ArrayList<>();
		for(ParametroSorteo c : coleccion)
			parametroSorteoDTOList.add(transformarSinDependencias(c));
		return parametroSorteoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ParametroSorteo> coleccion) {
		List<ParametroSorteo> parametroSorteoList = new ArrayList<>();
		for(ParametroSorteo c : coleccion)
			parametroSorteoList.add(transformarEntidadConDependencias(c));
		return parametroSorteoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ParametroSorteo> coleccion) {
		List<ParametroSorteo> parametroSorteoList = new ArrayList<>();
		for(ParametroSorteo c : coleccion)
			parametroSorteoList.add(transformarEntidadSinDependencias(c));
		return parametroSorteoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
