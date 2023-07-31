package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.Inasistencia;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.InasistenciaPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad InasistenciaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class InasistenciaDTO extends IDTO<Inasistencia> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private InasistenciaPK inasistenciaPK;

	private boolean presentaExcusa;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private String tipo;		
	private Long idDocumento;
	
    public InasistenciaDTO(){
		inasistenciaPK = new InasistenciaPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public InasistenciaPK getInasistenciaPK(){
		return this.inasistenciaPK;
	}
	
	public void setInasistenciaPK(InasistenciaPK inasistenciaPK){
		this.inasistenciaPK   = inasistenciaPK ;
	}  
	
	public boolean getPresentaExcusa(){
		return this.presentaExcusa;
	}
	
	public void setPresentaExcusa(boolean presentaExcusa){
		this.presentaExcusa = presentaExcusa;
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
		
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	
	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
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
        
        hash = 37 * hash + Objects.hashCode(this.inasistenciaPK);        
        hash = 37 * hash + (this.presentaExcusa ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad InasistenciaDTO que se pasa
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
        final InasistenciaDTO other = (InasistenciaDTO) obj;
                
        if (!Objects.equals(this.inasistenciaPK, other.inasistenciaPK)) {
            return false;
        }
        
        if (!Objects.equals(this.presentaExcusa, other.presentaExcusa)) {
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
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        return Objects.equals(this.tipo, other.tipo);
                
    }
    
    @Override
	public InasistenciaDTO transformarSinDependencias(Inasistencia obj) {
		InasistenciaDTO inasistenciaDTO = new InasistenciaDTO();
		
		inasistenciaDTO.setInasistenciaPK(obj.getInasistenciaPK());
		inasistenciaDTO.setPresentaExcusa(obj.getPresentaExcusa());
		inasistenciaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		inasistenciaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		inasistenciaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		inasistenciaDTO.setTipo(obj.getTipo());
		inasistenciaDTO.setIdDocumento(obj.getIdDocumento());
		
		return inasistenciaDTO;
	}

	@Override
	public InasistenciaDTO transformarConDependencias(Inasistencia obj) {
		InasistenciaDTO inasistenciaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return inasistenciaDTO;
	}

	@Override
	public Inasistencia transformarEntidadSinDependencias(Inasistencia obj) {
		Inasistencia inasistencia = new Inasistencia();
		
		inasistencia.setInasistenciaPK(obj.getInasistenciaPK());
		
		inasistencia.setPresentaExcusa(obj.getPresentaExcusa());
		inasistencia.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		inasistencia.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		inasistencia.setEstadoRegistro(obj.getEstadoRegistro());
		inasistencia.setTipo(obj.getTipo());
		inasistencia.setIdDocumento(obj.getIdDocumento());
		
		return inasistencia;
	}


	@Override
	public Inasistencia transformarEntidadConDependencias(Inasistencia obj) {
		Inasistencia inasistencia = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return inasistencia;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Inasistencia> coleccion) {
		List<InasistenciaDTO> inasistenciaDTOList = new ArrayList<>();
		for(Inasistencia c : coleccion)
			inasistenciaDTOList.add(transformarConDependencias(c));
		return inasistenciaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Inasistencia> coleccion) {
		List<InasistenciaDTO> inasistenciaDTOList = new ArrayList<>();
		for(Inasistencia c : coleccion)
			inasistenciaDTOList.add(transformarSinDependencias(c));
		return inasistenciaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Inasistencia> coleccion) {
		List<Inasistencia> inasistenciaList = new ArrayList<>();
		for(Inasistencia c : coleccion)
			inasistenciaList.add(transformarEntidadConDependencias(c));
		return inasistenciaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Inasistencia> coleccion) {
		List<Inasistencia> inasistenciaList = new ArrayList<>();
		for(Inasistencia c : coleccion)
			inasistenciaList.add(transformarEntidadSinDependencias(c));
		return inasistenciaList;
	}



	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
