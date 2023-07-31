package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.CorreoElectronicoRolPersonaCaso;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.CorreoElectronicoRolPersonaCasoPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad CorreoElectronicoRolPersonaCasoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class CorreoElectronicoRolPersonaCasoDTO extends IDTO<CorreoElectronicoRolPersonaCaso> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private CorreoElectronicoRolPersonaCasoPK correoElectronicoRolPersonaCasoPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public CorreoElectronicoRolPersonaCasoDTO(){
		correoElectronicoRolPersonaCasoPK = new CorreoElectronicoRolPersonaCasoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public CorreoElectronicoRolPersonaCasoPK getCorreoElectronicoRolPersonaCasoPK(){
		return this.correoElectronicoRolPersonaCasoPK;
	}
	
	public void setCorreoElectronicoRolPersonaCasoPK(CorreoElectronicoRolPersonaCasoPK correoElectronicoRolPersonaCasoPK){
		this.correoElectronicoRolPersonaCasoPK   = correoElectronicoRolPersonaCasoPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.correoElectronicoRolPersonaCasoPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CorreoElectronicoRolPersonaCasoDTO que se pasa
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
        final CorreoElectronicoRolPersonaCasoDTO other = (CorreoElectronicoRolPersonaCasoDTO) obj;
                
        if (!Objects.equals(this.correoElectronicoRolPersonaCasoPK, other.correoElectronicoRolPersonaCasoPK)) {
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
	public CorreoElectronicoRolPersonaCasoDTO transformarSinDependencias(CorreoElectronicoRolPersonaCaso obj) {
		CorreoElectronicoRolPersonaCasoDTO correoElectronicoRolPersonaCasoDTO = new CorreoElectronicoRolPersonaCasoDTO();
		
		correoElectronicoRolPersonaCasoDTO.setCorreoElectronicoRolPersonaCasoPK(obj.getCorreoElectronicoRolPersonaCasoPK());
		correoElectronicoRolPersonaCasoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		correoElectronicoRolPersonaCasoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		correoElectronicoRolPersonaCasoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return correoElectronicoRolPersonaCasoDTO;
	}

	@Override
	public CorreoElectronicoRolPersonaCasoDTO transformarConDependencias(CorreoElectronicoRolPersonaCaso obj) {
		CorreoElectronicoRolPersonaCasoDTO correoElectronicoRolPersonaCasoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return correoElectronicoRolPersonaCasoDTO;
	}

	@Override
	public CorreoElectronicoRolPersonaCaso transformarEntidadSinDependencias(CorreoElectronicoRolPersonaCaso obj) {
		CorreoElectronicoRolPersonaCaso correoElectronicoRolPersonaCaso = new CorreoElectronicoRolPersonaCaso();
		
		correoElectronicoRolPersonaCaso.setCorreoElectronicoRolPersonaCasoPK(obj.getCorreoElectronicoRolPersonaCasoPK());
		
		correoElectronicoRolPersonaCaso.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		correoElectronicoRolPersonaCaso.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		correoElectronicoRolPersonaCaso.setEstadoRegistro(obj.getEstadoRegistro());
		
		return correoElectronicoRolPersonaCaso;
	}


	@Override
	public CorreoElectronicoRolPersonaCaso transformarEntidadConDependencias(CorreoElectronicoRolPersonaCaso obj) {
		CorreoElectronicoRolPersonaCaso correoElectronicoRolPersonaCaso = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return correoElectronicoRolPersonaCaso;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<CorreoElectronicoRolPersonaCaso> coleccion) {
		List<CorreoElectronicoRolPersonaCasoDTO> correoElectronicoRolPersonaCasoDTOList = new ArrayList<>();
		for(CorreoElectronicoRolPersonaCaso c : coleccion)
			correoElectronicoRolPersonaCasoDTOList.add(transformarConDependencias(c));
		return correoElectronicoRolPersonaCasoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<CorreoElectronicoRolPersonaCaso> coleccion) {
		List<CorreoElectronicoRolPersonaCasoDTO> correoElectronicoRolPersonaCasoDTOList = new ArrayList<>();
		for(CorreoElectronicoRolPersonaCaso c : coleccion)
			correoElectronicoRolPersonaCasoDTOList.add(transformarSinDependencias(c));
		return correoElectronicoRolPersonaCasoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<CorreoElectronicoRolPersonaCaso> coleccion) {
		List<CorreoElectronicoRolPersonaCaso> correoElectronicoRolPersonaCasoList = new ArrayList<>();
		for(CorreoElectronicoRolPersonaCaso c : coleccion)
			correoElectronicoRolPersonaCasoList.add(transformarEntidadConDependencias(c));
		return correoElectronicoRolPersonaCasoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<CorreoElectronicoRolPersonaCaso> coleccion) {
		List<CorreoElectronicoRolPersonaCaso> correoElectronicoRolPersonaCasoList = new ArrayList<>();
		for(CorreoElectronicoRolPersonaCaso c : coleccion)
			correoElectronicoRolPersonaCasoList.add(transformarEntidadSinDependencias(c));
		return correoElectronicoRolPersonaCasoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
