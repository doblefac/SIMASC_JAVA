package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.ParametrizarServicioRol;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.ParametrizarServicioRolPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ParametrizarServicioRolDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ParametrizarServicioRolDTO extends IDTO<ParametrizarServicioRol> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private ParametrizarServicioRolPK parametrizarServicioRolPK;

	private Long idRol;
	private Long idServicio;
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ParametrizarServicioRolDTO(){
		parametrizarServicioRolPK = new ParametrizarServicioRolPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ParametrizarServicioRolPK getParametrizarServicioRolPK(){
		return this.parametrizarServicioRolPK;
	}
	
	public void setParametrizarServicioRolPK(ParametrizarServicioRolPK parametrizarServicioRolPK){
		this.parametrizarServicioRolPK   = parametrizarServicioRolPK ;
	}  
	
	public Long getIdRol() {
		return idRol;
	}


	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}


	public Long getIdServicio() {
		return idServicio;
	}


	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
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
        
        hash = 37 * hash + Objects.hashCode(this.parametrizarServicioRolPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametrizarServicioRolDTO que se pasa
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
        final ParametrizarServicioRolDTO other = (ParametrizarServicioRolDTO) obj;
                
        if (!Objects.equals(this.parametrizarServicioRolPK, other.parametrizarServicioRolPK)) {
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
	public ParametrizarServicioRolDTO transformarSinDependencias(ParametrizarServicioRol obj) {
		ParametrizarServicioRolDTO parametrizarServicioRolDTO = new ParametrizarServicioRolDTO();
		
		parametrizarServicioRolDTO.setParametrizarServicioRolPK(obj.getParametrizarServicioRolPK());
		parametrizarServicioRolDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parametrizarServicioRolDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parametrizarServicioRolDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return parametrizarServicioRolDTO;
	}

	@Override
	public ParametrizarServicioRolDTO transformarConDependencias(ParametrizarServicioRol obj) {
		ParametrizarServicioRolDTO parametrizarServicioRolDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return parametrizarServicioRolDTO;
	}

	@Override
	public ParametrizarServicioRol transformarEntidadSinDependencias(ParametrizarServicioRol obj) {
		ParametrizarServicioRol parametrizarServicioRol = new ParametrizarServicioRol();
		
		parametrizarServicioRol.setParametrizarServicioRolPK(obj.getParametrizarServicioRolPK());
		
		parametrizarServicioRol.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parametrizarServicioRol.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parametrizarServicioRol.setEstadoRegistro(obj.getEstadoRegistro());
		
		return parametrizarServicioRol;
	}


	@Override
	public ParametrizarServicioRol transformarEntidadConDependencias(ParametrizarServicioRol obj) {
		ParametrizarServicioRol parametrizarServicioRol = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return parametrizarServicioRol;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ParametrizarServicioRol> coleccion) {
		List<ParametrizarServicioRolDTO> parametrizarServicioRolDTOList = new ArrayList<>();
		for(ParametrizarServicioRol c : coleccion)
			parametrizarServicioRolDTOList.add(transformarConDependencias(c));
		return parametrizarServicioRolDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ParametrizarServicioRol> coleccion) {
		List<ParametrizarServicioRolDTO> parametrizarServicioRolDTOList = new ArrayList<>();
		for(ParametrizarServicioRol c : coleccion)
			parametrizarServicioRolDTOList.add(transformarSinDependencias(c));
		return parametrizarServicioRolDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ParametrizarServicioRol> coleccion) {
		List<ParametrizarServicioRol> parametrizarServicioRolList = new ArrayList<>();
		for(ParametrizarServicioRol c : coleccion)
			parametrizarServicioRolList.add(transformarEntidadConDependencias(c));
		return parametrizarServicioRolList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ParametrizarServicioRol> coleccion) {
		List<ParametrizarServicioRol> parametrizarServicioRolList = new ArrayList<>();
		for(ParametrizarServicioRol c : coleccion)
			parametrizarServicioRolList.add(transformarEntidadSinDependencias(c));
		return parametrizarServicioRolList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
