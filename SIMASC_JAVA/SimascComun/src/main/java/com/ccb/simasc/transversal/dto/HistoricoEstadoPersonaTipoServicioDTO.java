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

import com.ccb.simasc.transversal.entidades.HistoricoEstadoPersonaTipoServicio;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad HistoricoEstadoPersonaTipoServicioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class HistoricoEstadoPersonaTipoServicioDTO extends IDTO<HistoricoEstadoPersonaTipoServicio> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	private static final long serialVersionUID = 1540037415508310804L;

	// protected region atributos end
	private Long idHistoricoEstadoPersonaTs;

	private Long idPersona;		
	private String tipoServicio;		
	private String estado;		
	private Date fecha;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public HistoricoEstadoPersonaTipoServicioDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdHistoricoEstadoPersonaTs(){
		return this.idHistoricoEstadoPersonaTs;
	}
	
	public void setIdHistoricoEstadoPersonaTs(Long idHistoricoEstadoPersonaTs){
		this.idHistoricoEstadoPersonaTs = idHistoricoEstadoPersonaTs;
	}
	
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public String getTipoServicio(){
		return this.tipoServicio;
	}
	
	public void setTipoServicio(String tipoServicio){
		this.tipoServicio = tipoServicio;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
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
        
        hash = 37 * hash + Objects.hashCode(this.idHistoricoEstadoPersonaTs);        
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.tipoServicio);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad HistoricoEstadoPersonaTipoServicioDTO que se pasa
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
        final HistoricoEstadoPersonaTipoServicioDTO other = (HistoricoEstadoPersonaTipoServicioDTO) obj;
                
        if (!Objects.equals(this.idHistoricoEstadoPersonaTs, other.idHistoricoEstadoPersonaTs)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoServicio, other.tipoServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
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
	public HistoricoEstadoPersonaTipoServicioDTO transformarSinDependencias(HistoricoEstadoPersonaTipoServicio obj) {
		HistoricoEstadoPersonaTipoServicioDTO historicoEstadoPersonaTipoServicioDTO = new HistoricoEstadoPersonaTipoServicioDTO();
		
		historicoEstadoPersonaTipoServicioDTO.setIdHistoricoEstadoPersonaTs(obj.getIdHistoricoEstadoPersonaTs());
		historicoEstadoPersonaTipoServicioDTO.setIdPersona(obj.getIdPersona());
		historicoEstadoPersonaTipoServicioDTO.setTipoServicio(obj.getTipoServicio());
		historicoEstadoPersonaTipoServicioDTO.setEstado(obj.getEstado());
		historicoEstadoPersonaTipoServicioDTO.setFecha(obj.getFecha());
		historicoEstadoPersonaTipoServicioDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		historicoEstadoPersonaTipoServicioDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		historicoEstadoPersonaTipoServicioDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return historicoEstadoPersonaTipoServicioDTO;
	}

	@Override
	public HistoricoEstadoPersonaTipoServicioDTO transformarConDependencias(HistoricoEstadoPersonaTipoServicio obj) {
		HistoricoEstadoPersonaTipoServicioDTO historicoEstadoPersonaTipoServicioDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return historicoEstadoPersonaTipoServicioDTO;
	}

	@Override
	public HistoricoEstadoPersonaTipoServicio transformarEntidadSinDependencias(HistoricoEstadoPersonaTipoServicio obj) {
		HistoricoEstadoPersonaTipoServicio historicoEstadoPersonaTipoServicio = new HistoricoEstadoPersonaTipoServicio();
		
		historicoEstadoPersonaTipoServicio.setIdHistoricoEstadoPersonaTs(obj.getIdHistoricoEstadoPersonaTs());
		
		historicoEstadoPersonaTipoServicio.setIdPersona(obj.getIdPersona());
		historicoEstadoPersonaTipoServicio.setTipoServicio(obj.getTipoServicio());
		historicoEstadoPersonaTipoServicio.setEstado(obj.getEstado());
		historicoEstadoPersonaTipoServicio.setFecha(obj.getFecha());
		historicoEstadoPersonaTipoServicio.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		historicoEstadoPersonaTipoServicio.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		historicoEstadoPersonaTipoServicio.setEstadoRegistro(obj.getEstadoRegistro());
		
		return historicoEstadoPersonaTipoServicio;
	}


	@Override
	public HistoricoEstadoPersonaTipoServicio transformarEntidadConDependencias(HistoricoEstadoPersonaTipoServicio obj) {
		HistoricoEstadoPersonaTipoServicio historicoEstadoPersonaTipoServicio = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return historicoEstadoPersonaTipoServicio;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<HistoricoEstadoPersonaTipoServicio> coleccion) {
		List<HistoricoEstadoPersonaTipoServicioDTO> historicoEstadoPersonaTipoServicioDTOList = new ArrayList<>();
		for(HistoricoEstadoPersonaTipoServicio c : coleccion)
			historicoEstadoPersonaTipoServicioDTOList.add(transformarConDependencias(c));
		return historicoEstadoPersonaTipoServicioDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<HistoricoEstadoPersonaTipoServicio> coleccion) {
		List<HistoricoEstadoPersonaTipoServicioDTO> historicoEstadoPersonaTipoServicioDTOList = new ArrayList<>();
		for(HistoricoEstadoPersonaTipoServicio c : coleccion)
			historicoEstadoPersonaTipoServicioDTOList.add(transformarSinDependencias(c));
		return historicoEstadoPersonaTipoServicioDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<HistoricoEstadoPersonaTipoServicio> coleccion) {
		List<HistoricoEstadoPersonaTipoServicio> historicoEstadoPersonaTipoServicioList = new ArrayList<>();
		for(HistoricoEstadoPersonaTipoServicio c : coleccion)
			historicoEstadoPersonaTipoServicioList.add(transformarEntidadConDependencias(c));
		return historicoEstadoPersonaTipoServicioList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<HistoricoEstadoPersonaTipoServicio> coleccion) {
		List<HistoricoEstadoPersonaTipoServicio> historicoEstadoPersonaTipoServicioList = new ArrayList<>();
		for(HistoricoEstadoPersonaTipoServicio c : coleccion)
			historicoEstadoPersonaTipoServicioList.add(transformarEntidadSinDependencias(c));
		return historicoEstadoPersonaTipoServicioList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public static List<HistoricoEstadoPersonaTipoServicioDTO> transformarListaSinDependencias(List<HistoricoEstadoPersonaTipoServicio> lista) {
		List<HistoricoEstadoPersonaTipoServicioDTO> historicoEstadoPersonaTipoServicioDTOList = new ArrayList<>();
		for(HistoricoEstadoPersonaTipoServicio c : lista)
			historicoEstadoPersonaTipoServicioDTOList.add((new HistoricoEstadoPersonaTipoServicioDTO()).transformarSinDependencias(c));
		return historicoEstadoPersonaTipoServicioDTOList;
	}
	
	// protected region metodos adicionales end

}
