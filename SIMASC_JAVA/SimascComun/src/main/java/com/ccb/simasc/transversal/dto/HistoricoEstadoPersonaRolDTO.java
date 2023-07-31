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

import com.ccb.simasc.transversal.entidades.HistoricoEstadoPersonaRol;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad HistoricoEstadoPersonaRolDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class HistoricoEstadoPersonaRolDTO extends IDTO<HistoricoEstadoPersonaRol> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	private static final long serialVersionUID = 1540037415508310804L;

	// protected region atributos end
	private Long idHistoricoEstadoPersonaRol;

	private Long idPersona;		
	private String rol;		
	private String Motivo;		
	private String estado;		
	private Date fecha;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public HistoricoEstadoPersonaRolDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdHistoricoEstadoPersonaRol(){
		return this.idHistoricoEstadoPersonaRol;
	}
	
	public void setIdHistoricoEstadoPersonaRol(Long idHistoricoEstadoPersonaRol){
		this.idHistoricoEstadoPersonaRol = idHistoricoEstadoPersonaRol;
	}
	
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public String getRol(){
		return this.rol;
	}

	public void setRol(String rol){
		this.rol = rol;
	}
		
	public String getMotivo(){
		return this.Motivo;
	}
	
	public void setMotivo(String Motivo){
		this.Motivo = Motivo;
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
        
        hash = 37 * hash + Objects.hashCode(this.idHistoricoEstadoPersonaRol);        
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.rol);
        hash = 37 * hash + Objects.hashCode(this.Motivo);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad HistoricoEstadoPersonaRolDTO que se pasa
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
        final HistoricoEstadoPersonaRolDTO other = (HistoricoEstadoPersonaRolDTO) obj;
                
        if (!Objects.equals(this.idHistoricoEstadoPersonaRol, other.idHistoricoEstadoPersonaRol)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.rol, other.rol)) {
            return false;
        }
        
        if (!Objects.equals(this.Motivo, other.Motivo)) {
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
	public HistoricoEstadoPersonaRolDTO transformarSinDependencias(HistoricoEstadoPersonaRol obj) {
		HistoricoEstadoPersonaRolDTO historicoEstadoPersonaRolDTO = new HistoricoEstadoPersonaRolDTO();
		
		historicoEstadoPersonaRolDTO.setIdHistoricoEstadoPersonaRol(obj.getIdHistoricoEstadoPersonaRol());
		historicoEstadoPersonaRolDTO.setIdPersona(obj.getIdPersona());
		historicoEstadoPersonaRolDTO.setRol(obj.getRol().getNombre());
		historicoEstadoPersonaRolDTO.setMotivo(obj.getIdMotivo());
//		historicoEstadoPersonaRolDTO.setEstado(obj.getEstadoMotivoDispo().getEstado());
		historicoEstadoPersonaRolDTO.setFecha(obj.getFecha());
		historicoEstadoPersonaRolDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		historicoEstadoPersonaRolDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		historicoEstadoPersonaRolDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return historicoEstadoPersonaRolDTO;
	}

	@Override
	public HistoricoEstadoPersonaRolDTO transformarConDependencias(HistoricoEstadoPersonaRol obj) {
		HistoricoEstadoPersonaRolDTO historicoEstadoPersonaRolDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return historicoEstadoPersonaRolDTO;
	}

	@Override
	public HistoricoEstadoPersonaRol transformarEntidadSinDependencias(HistoricoEstadoPersonaRol obj) {
		HistoricoEstadoPersonaRol historicoEstadoPersonaRol = new HistoricoEstadoPersonaRol();
		
		historicoEstadoPersonaRol.setIdHistoricoEstadoPersonaRol(obj.getIdHistoricoEstadoPersonaRol());
		
		historicoEstadoPersonaRol.setIdPersona(obj.getIdPersona());
		historicoEstadoPersonaRol.setIdRol(obj.getIdRol());
		historicoEstadoPersonaRol.setIdMotivo(obj.getIdMotivo());
		historicoEstadoPersonaRol.setFecha(obj.getFecha());
		historicoEstadoPersonaRol.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		historicoEstadoPersonaRol.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		historicoEstadoPersonaRol.setEstadoRegistro(obj.getEstadoRegistro());
		
		return historicoEstadoPersonaRol;
	}


	@Override
	public HistoricoEstadoPersonaRol transformarEntidadConDependencias(HistoricoEstadoPersonaRol obj) {
		HistoricoEstadoPersonaRol historicoEstadoPersonaRol = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return historicoEstadoPersonaRol;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<HistoricoEstadoPersonaRol> coleccion) {
		List<HistoricoEstadoPersonaRolDTO> historicoEstadoPersonaRolDTOList = new ArrayList<>();
		for(HistoricoEstadoPersonaRol c : coleccion)
			historicoEstadoPersonaRolDTOList.add(transformarConDependencias(c));
		return historicoEstadoPersonaRolDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<HistoricoEstadoPersonaRol> coleccion) {
		List<HistoricoEstadoPersonaRolDTO> historicoEstadoPersonaRolDTOList = new ArrayList<>();
		for(HistoricoEstadoPersonaRol c : coleccion)
			historicoEstadoPersonaRolDTOList.add(transformarSinDependencias(c));
		return historicoEstadoPersonaRolDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<HistoricoEstadoPersonaRol> coleccion) {
		List<HistoricoEstadoPersonaRol> historicoEstadoPersonaRolList = new ArrayList<>();
		for(HistoricoEstadoPersonaRol c : coleccion)
			historicoEstadoPersonaRolList.add(transformarEntidadConDependencias(c));
		return historicoEstadoPersonaRolList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<HistoricoEstadoPersonaRol> coleccion) {
		List<HistoricoEstadoPersonaRol> historicoEstadoPersonaRolList = new ArrayList<>();
		for(HistoricoEstadoPersonaRol c : coleccion)
			historicoEstadoPersonaRolList.add(transformarEntidadSinDependencias(c));
		return historicoEstadoPersonaRolList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public static List<HistoricoEstadoPersonaRolDTO> transformarListaSinDependencias(List<HistoricoEstadoPersonaRol> lista) {
		List<HistoricoEstadoPersonaRolDTO> historicoEstadoPersonaRolDTOList = new ArrayList<>();
		for(HistoricoEstadoPersonaRol c : lista)
			historicoEstadoPersonaRolDTOList.add((new HistoricoEstadoPersonaRolDTO()).transformarSinDependencias(c));
		return historicoEstadoPersonaRolDTOList;
	}
	
	// protected region metodos adicionales end

}
