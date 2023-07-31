package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.CriterioTotal;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.CriterioTotalPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad CriterioTotalDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class CriterioTotalDTO extends IDTO<CriterioTotal> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private CriterioTotalPK criterioTotalPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public CriterioTotalDTO(){
		criterioTotalPK = new CriterioTotalPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public CriterioTotalPK getCriterioTotalPK(){
		return this.criterioTotalPK;
	}
	
	public void setCriterioTotalPK(CriterioTotalPK criterioTotalPK){
		this.criterioTotalPK   = criterioTotalPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.criterioTotalPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CriterioTotalDTO que se pasa
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
        final CriterioTotalDTO other = (CriterioTotalDTO) obj;
                
        if (!Objects.equals(this.criterioTotalPK, other.criterioTotalPK)) {
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
	public CriterioTotalDTO transformarSinDependencias(CriterioTotal obj) {
		CriterioTotalDTO criterioTotalDTO = new CriterioTotalDTO();
		
		criterioTotalDTO.setCriterioTotalPK(obj.getCriterioTotalPK());
		criterioTotalDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		criterioTotalDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		criterioTotalDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return criterioTotalDTO;
	}

	@Override
	public CriterioTotalDTO transformarConDependencias(CriterioTotal obj) {
		CriterioTotalDTO criterioTotalDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return criterioTotalDTO;
	}

	@Override
	public CriterioTotal transformarEntidadSinDependencias(CriterioTotal obj) {
		CriterioTotal criterioTotal = new CriterioTotal();
		
		criterioTotal.setCriterioTotalPK(obj.getCriterioTotalPK());
		
		criterioTotal.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		criterioTotal.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		criterioTotal.setEstadoRegistro(obj.getEstadoRegistro());
		
		return criterioTotal;
	}


	@Override
	public CriterioTotal transformarEntidadConDependencias(CriterioTotal obj) {
		CriterioTotal criterioTotal = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return criterioTotal;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<CriterioTotal> coleccion) {
		List<CriterioTotalDTO> criterioTotalDTOList = new ArrayList<>();
		for(CriterioTotal c : coleccion)
			criterioTotalDTOList.add(transformarConDependencias(c));
		return criterioTotalDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<CriterioTotal> coleccion) {
		List<CriterioTotalDTO> criterioTotalDTOList = new ArrayList<>();
		for(CriterioTotal c : coleccion)
			criterioTotalDTOList.add(transformarSinDependencias(c));
		return criterioTotalDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<CriterioTotal> coleccion) {
		List<CriterioTotal> criterioTotalList = new ArrayList<>();
		for(CriterioTotal c : coleccion)
			criterioTotalList.add(transformarEntidadConDependencias(c));
		return criterioTotalList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<CriterioTotal> coleccion) {
		List<CriterioTotal> criterioTotalList = new ArrayList<>();
		for(CriterioTotal c : coleccion)
			criterioTotalList.add(transformarEntidadSinDependencias(c));
		return criterioTotalList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
