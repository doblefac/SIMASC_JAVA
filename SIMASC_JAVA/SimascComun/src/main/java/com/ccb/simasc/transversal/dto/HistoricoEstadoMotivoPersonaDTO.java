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

import com.ccb.simasc.transversal.entidades.HistoricoEstadoMotivoPersona;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad HistoricoEstadoPersonaRolDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class HistoricoEstadoMotivoPersonaDTO extends IDTO<HistoricoEstadoMotivoPersona> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	private static final long serialVersionUID = 1540037415508310804L;

	// protected region atributos end
	private Long idHistoricoEstadoMotivoPersona;

	private Long idPersona;		
	private String rol;		
	private String estado;
	private String motivo;		
	private String idMotivo;
	private String estadoParaSorteo;		
	private Long idMateria;	
	private String materia;
	private Date fecha;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;	
	private String servicio;
	
    public HistoricoEstadoMotivoPersonaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdHistoricoEstadoMotivoPersona(){
		return this.idHistoricoEstadoMotivoPersona;
	}
	
	public void setIdHistoricoEstadoMotivoPersona(Long idHistoricoEstadoMotivoPersona){
		this.idHistoricoEstadoMotivoPersona = idHistoricoEstadoMotivoPersona;
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
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
	
	public String getMotivo(){
		return this.motivo;
	}
	
	public void setMotivo(String motivo){
		this.motivo = motivo;
	}
	
	public String getEstadoParaSorteo(){
		return this.estadoParaSorteo;
	}
	
	public void setEstadoParaSorteo(String estadoParaSorteo){
		this.estadoParaSorteo = estadoParaSorteo;
	}
	
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
	
	public String getMateria(){
		return this.materia;
	}
	
	public void setMateria(String materia){
		this.materia = materia;
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
		
    public String getIdMotivo() {
		return idMotivo;
	}
	public void setIdMotivo(String idMotivo) {
		this.idMotivo = idMotivo;
	}
	
	public String getServicio() {
		return servicio;
	}
	
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idHistoricoEstadoMotivoPersona);        
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.rol);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.motivo);
        hash = 37 * hash + Objects.hashCode(this.estadoParaSorteo);
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.materia);
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
        final HistoricoEstadoMotivoPersonaDTO other = (HistoricoEstadoMotivoPersonaDTO) obj;
                
        if (!Objects.equals(this.idHistoricoEstadoMotivoPersona, other.idHistoricoEstadoMotivoPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.rol, other.rol)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.motivo, other.motivo)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoParaSorteo, other.estadoParaSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.materia, other.materia)) {
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
	public HistoricoEstadoMotivoPersonaDTO transformarSinDependencias(HistoricoEstadoMotivoPersona obj) {
		HistoricoEstadoMotivoPersonaDTO historicoEstadoMotivoPersonaDTO = new HistoricoEstadoMotivoPersonaDTO();
		
		historicoEstadoMotivoPersonaDTO.setIdHistoricoEstadoMotivoPersona(obj.getIdHistoricoEstadoMotivoPersona());
		historicoEstadoMotivoPersonaDTO.setIdPersona(obj.getIdPersona());
		historicoEstadoMotivoPersonaDTO.setRol(obj.getRol().getNombre());
		historicoEstadoMotivoPersonaDTO.setMotivo(obj.getIdMotivo());
		historicoEstadoMotivoPersonaDTO.setEstadoParaSorteo(obj.getEstadoParaSorteo());
		historicoEstadoMotivoPersonaDTO.setIdMateria(obj.getIdMateria());
		historicoEstadoMotivoPersonaDTO.setFecha(obj.getFecha());
		historicoEstadoMotivoPersonaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		historicoEstadoMotivoPersonaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		historicoEstadoMotivoPersonaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return historicoEstadoMotivoPersonaDTO;
	}

	@Override
	public HistoricoEstadoMotivoPersonaDTO transformarConDependencias(HistoricoEstadoMotivoPersona obj) {
		HistoricoEstadoMotivoPersonaDTO historicoEstadoMotivoPersonaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return historicoEstadoMotivoPersonaDTO;
	}

	@Override
	public HistoricoEstadoMotivoPersona transformarEntidadSinDependencias(HistoricoEstadoMotivoPersona obj) {
		HistoricoEstadoMotivoPersona historicoEstadoMotivoPersona = new HistoricoEstadoMotivoPersona();
		
		historicoEstadoMotivoPersona.setIdHistoricoEstadoMotivoPersona(obj.getIdHistoricoEstadoMotivoPersona());
		
		historicoEstadoMotivoPersona.setIdPersona(obj.getIdPersona());
		historicoEstadoMotivoPersona.setIdRol(obj.getIdRol());
		historicoEstadoMotivoPersona.setIdMotivo(obj.getIdMotivo());
		historicoEstadoMotivoPersona.setFecha(obj.getFecha());
		historicoEstadoMotivoPersona.setEstadoParaSorteo(obj.getEstadoParaSorteo());
		historicoEstadoMotivoPersona.setIdMateria(obj.getIdMateria());
		historicoEstadoMotivoPersona.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		historicoEstadoMotivoPersona.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		historicoEstadoMotivoPersona.setEstadoRegistro(obj.getEstadoRegistro());
		
		return historicoEstadoMotivoPersona;
	}


	@Override
	public HistoricoEstadoMotivoPersona transformarEntidadConDependencias(HistoricoEstadoMotivoPersona obj) {
		HistoricoEstadoMotivoPersona historicoEstadoMotivoPersona = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return historicoEstadoMotivoPersona;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<HistoricoEstadoMotivoPersona> coleccion) {
		List<HistoricoEstadoMotivoPersonaDTO> historicoEstadoMotivoPersonaDTOList = new ArrayList<>();
		for(HistoricoEstadoMotivoPersona c : coleccion)
			historicoEstadoMotivoPersonaDTOList.add(transformarConDependencias(c));
		return historicoEstadoMotivoPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<HistoricoEstadoMotivoPersona> coleccion) {
		List<HistoricoEstadoMotivoPersonaDTO> historicoEstadoMotivoPersonaDTOList = new ArrayList<>();
		for(HistoricoEstadoMotivoPersona c : coleccion)
			historicoEstadoMotivoPersonaDTOList.add(transformarSinDependencias(c));
		return historicoEstadoMotivoPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<HistoricoEstadoMotivoPersona> coleccion) {
		List<HistoricoEstadoMotivoPersona> historicoEstadoMotivoPersonaDTOList = new ArrayList<>();
		for(HistoricoEstadoMotivoPersona c : coleccion)
			historicoEstadoMotivoPersonaDTOList.add(transformarEntidadConDependencias(c));
		return historicoEstadoMotivoPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<HistoricoEstadoMotivoPersona> coleccion) {
		List<HistoricoEstadoMotivoPersona> historicoEstadoMotivoPersonaDTOList = new ArrayList<>();
		for(HistoricoEstadoMotivoPersona c : coleccion)
			historicoEstadoMotivoPersonaDTOList.add(transformarEntidadSinDependencias(c));
		return historicoEstadoMotivoPersonaDTOList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public static List<HistoricoEstadoMotivoPersonaDTO> transformarListaSinDependencias(List<HistoricoEstadoMotivoPersona> lista) {
		List<HistoricoEstadoMotivoPersonaDTO> historicoEstadoMotivoPersonaDTOList = new ArrayList<>();
		for(HistoricoEstadoMotivoPersona c : lista)
			historicoEstadoMotivoPersonaDTOList.add((new HistoricoEstadoMotivoPersonaDTO()).transformarSinDependencias(c));
		return historicoEstadoMotivoPersonaDTOList;
	}
	
	// protected region metodos adicionales end

}
