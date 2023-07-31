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

import com.ccb.simasc.transversal.entidades.FuncionalidadRol;
import com.ccb.simasc.transversal.entidades.FuncionalidadRolPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad FuncionalidadRolDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class FuncionalidadRolDTO extends IDTO<FuncionalidadRol> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private FuncionalidadRolPK funcionalidadRolPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public FuncionalidadRolDTO(){
		funcionalidadRolPK = new FuncionalidadRolPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public FuncionalidadRolPK getFuncionalidadRolPK(){
		return this.funcionalidadRolPK;
	}
	
	public void setFuncionalidadRolPK(FuncionalidadRolPK funcionalidadRolPK){
		this.funcionalidadRolPK   = funcionalidadRolPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.funcionalidadRolPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad FuncionalidadRolDTO que se pasa
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
        final FuncionalidadRolDTO other = (FuncionalidadRolDTO) obj;
                
        if (!Objects.equals(this.funcionalidadRolPK, other.funcionalidadRolPK)) {
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
	public FuncionalidadRolDTO transformarSinDependencias(FuncionalidadRol obj) {
		FuncionalidadRolDTO funcionalidadRolDTO = new FuncionalidadRolDTO();
		
		funcionalidadRolDTO.setFuncionalidadRolPK(obj.getFuncionalidadRolPK());
		funcionalidadRolDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		funcionalidadRolDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		funcionalidadRolDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return funcionalidadRolDTO;
	}

	@Override
	public FuncionalidadRolDTO transformarConDependencias(FuncionalidadRol obj) {
		FuncionalidadRolDTO funcionalidadRolDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return funcionalidadRolDTO;
	}

	@Override
	public FuncionalidadRol transformarEntidadSinDependencias(FuncionalidadRol obj) {
		FuncionalidadRol funcionalidadRol = new FuncionalidadRol();
		
		funcionalidadRol.setFuncionalidadRolPK(obj.getFuncionalidadRolPK());
		
		funcionalidadRol.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		funcionalidadRol.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		funcionalidadRol.setEstadoRegistro(obj.getEstadoRegistro());
		
		return funcionalidadRol;
	}


	@Override
	public FuncionalidadRol transformarEntidadConDependencias(FuncionalidadRol obj) {
		FuncionalidadRol funcionalidadRol = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return funcionalidadRol;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<FuncionalidadRol> coleccion) {
		List<FuncionalidadRolDTO> funcionalidadRolDTOList = new ArrayList<>();
		for(FuncionalidadRol c : coleccion)
			funcionalidadRolDTOList.add(transformarConDependencias(c));
		return funcionalidadRolDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<FuncionalidadRol> coleccion) {
		List<FuncionalidadRolDTO> funcionalidadRolDTOList = new ArrayList<>();
		for(FuncionalidadRol c : coleccion)
			funcionalidadRolDTOList.add(transformarSinDependencias(c));
		return funcionalidadRolDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<FuncionalidadRol> coleccion) {
		List<FuncionalidadRol> funcionalidadRolList = new ArrayList<>();
		for(FuncionalidadRol c : coleccion)
			funcionalidadRolList.add(transformarEntidadConDependencias(c));
		return funcionalidadRolList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<FuncionalidadRol> coleccion) {
		List<FuncionalidadRol> funcionalidadRolList = new ArrayList<>();
		for(FuncionalidadRol c : coleccion)
			funcionalidadRolList.add(transformarEntidadSinDependencias(c));
		return funcionalidadRolList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
