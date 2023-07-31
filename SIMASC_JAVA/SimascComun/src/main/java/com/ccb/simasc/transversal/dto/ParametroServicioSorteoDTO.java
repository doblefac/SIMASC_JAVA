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

import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ParametroServicioSorteoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ParametroServicioSorteoDTO extends IDTO<ParametroServicioSorteo> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idParametrosServiciosSorteo;

	private Long idServicio;		
	private Long idParametrosSorteo;		
	private boolean sorteoConLista;		
	private boolean sorteoConMateria;		
	private Integer cantidadDeMaterias;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idRol;		
	private boolean bloqueaSuplente;
	private String audienciaLiberaSuplente;
	private boolean liberaArbitrosCierre;
	private boolean suspendeNoPronunciamiento;
	private boolean suspendeRechazo;
	private boolean suspendeExtemporaneo;
	
	
    public ParametroServicioSorteoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdParametrosServiciosSorteo(){
		return this.idParametrosServiciosSorteo;
	}
	
	public void setIdParametrosServiciosSorteo(Long idParametrosServiciosSorteo){
		this.idParametrosServiciosSorteo = idParametrosServiciosSorteo;
	}
	
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
	public Long getIdParametrosSorteo(){
		return this.idParametrosSorteo;
	}
	
	public void setIdParametrosSorteo(Long idParametrosSorteo){
		this.idParametrosSorteo = idParametrosSorteo;
	}
		
	public boolean getSorteoConLista(){
		return this.sorteoConLista;
	}
	
	public void setSorteoConLista(boolean sorteoConLista){
		this.sorteoConLista = sorteoConLista;
	}
		
	public boolean getSorteoConMateria(){
		return this.sorteoConMateria;
	}
	
	public void setSorteoConMateria(boolean sorteoConMateria){
		this.sorteoConMateria = sorteoConMateria;
	}
		
	public Integer getCantidadDeMaterias(){
		return this.cantidadDeMaterias;
	}
	
	public void setCantidadDeMaterias(Integer cantidadDeMaterias){
		this.cantidadDeMaterias = cantidadDeMaterias;
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
		
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idParametrosServiciosSorteo);        
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        hash = 37 * hash + Objects.hashCode(this.idParametrosSorteo);
        hash = 37 * hash + (this.sorteoConLista ? 0 : 1);
        hash = 37 * hash + (this.sorteoConMateria ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.cantidadDeMaterias);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idRol);     
        hash = 37 * hash + (this.bloqueaSuplente ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.audienciaLiberaSuplente);
        hash = 37 * hash + (this.liberaArbitrosCierre ? 0 : 1);
        hash = 37 * hash + (this.suspendeNoPronunciamiento ? 0 : 1);
        hash = 37 * hash + (this.suspendeRechazo ? 0 : 1);
        hash = 37 * hash + (this.suspendeExtemporaneo ? 0 : 1); 
 
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametroServicioSorteoDTO que se pasa
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
        final ParametroServicioSorteoDTO other = (ParametroServicioSorteoDTO) obj;
                
        if (!Objects.equals(this.idParametrosServiciosSorteo, other.idParametrosServiciosSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.idParametrosSorteo, other.idParametrosSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.sorteoConLista, other.sorteoConLista)) {
            return false;
        }
        
        if (!Objects.equals(this.sorteoConMateria, other.sorteoConMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.cantidadDeMaterias, other.cantidadDeMaterias)) {
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
        
        if (!Objects.equals(this.bloqueaSuplente, other.bloqueaSuplente)) {
            return false;
        }
        if (!Objects.equals(this.audienciaLiberaSuplente, other.audienciaLiberaSuplente)) {
            return false;
        }
        if (!Objects.equals(this.liberaArbitrosCierre, other.liberaArbitrosCierre)) {
            return false;
        }
        if (!Objects.equals(this.suspendeNoPronunciamiento, other.suspendeNoPronunciamiento)) {
            return false;
        }
        if (!Objects.equals(this.suspendeRechazo, other.suspendeRechazo)) {
            return false;
        }
        if (!Objects.equals(this.suspendeExtemporaneo, other.suspendeExtemporaneo)) {
            return false;
        }
        
        return Objects.equals(this.idRol, other.idRol);
                
    }
    
    @Override
	public ParametroServicioSorteoDTO transformarSinDependencias(ParametroServicioSorteo obj) {
		ParametroServicioSorteoDTO parametroServicioSorteoDTO = new ParametroServicioSorteoDTO();
		
		parametroServicioSorteoDTO.setIdParametrosServiciosSorteo(obj.getIdParametrosServiciosSorteo());
		parametroServicioSorteoDTO.setIdServicio(obj.getIdServicio());
		parametroServicioSorteoDTO.setIdParametrosSorteo(obj.getIdParametrosSorteo());
		parametroServicioSorteoDTO.setSorteoConLista(obj.getSorteoConLista());
		parametroServicioSorteoDTO.setSorteoConMateria(obj.getSorteoConMateria());
		parametroServicioSorteoDTO.setCantidadDeMaterias(obj.getCantidadDeMaterias());
		parametroServicioSorteoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parametroServicioSorteoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parametroServicioSorteoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		parametroServicioSorteoDTO.setIdRol(obj.getIdRol());
		parametroServicioSorteoDTO.setBloqueaSuplente(obj.getBloqueaSuplente());
		parametroServicioSorteoDTO.setAudienciaLiberaSuplente(obj.getAudienciaLiberaSuplente());
		parametroServicioSorteoDTO.setLiberaArbitrosCierre(obj.getLiberaArbitrosCierre());
		parametroServicioSorteoDTO.setSuspendeNoPronunciamiento(obj.getSuspendeNoPronunciamiento());
		parametroServicioSorteoDTO.setSuspendeRechazo(obj.getSuspendeRechazo());
		parametroServicioSorteoDTO.setSuspendeExtemporaneo(obj.getSuspendeExtemporaneo());
		
		return parametroServicioSorteoDTO;
	}

	@Override
	public ParametroServicioSorteoDTO transformarConDependencias(ParametroServicioSorteo obj) {
		ParametroServicioSorteoDTO parametroServicioSorteoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return parametroServicioSorteoDTO;
	}

	@Override
	public ParametroServicioSorteo transformarEntidadSinDependencias(ParametroServicioSorteo obj) {
		ParametroServicioSorteo parametroServicioSorteo = new ParametroServicioSorteo();
		
		parametroServicioSorteo.setIdParametrosServiciosSorteo(obj.getIdParametrosServiciosSorteo());
		
		parametroServicioSorteo.setIdServicio(obj.getIdServicio());
		parametroServicioSorteo.setIdParametrosSorteo(obj.getIdParametrosSorteo());
		parametroServicioSorteo.setSorteoConLista(obj.getSorteoConLista());
		parametroServicioSorteo.setSorteoConMateria(obj.getSorteoConMateria());
		parametroServicioSorteo.setCantidadDeMaterias(obj.getCantidadDeMaterias());
		parametroServicioSorteo.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parametroServicioSorteo.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parametroServicioSorteo.setEstadoRegistro(obj.getEstadoRegistro());
		parametroServicioSorteo.setIdRol(obj.getIdRol());
		parametroServicioSorteo.setBloqueaSuplente(obj.getBloqueaSuplente());
		parametroServicioSorteo.setAudienciaLiberaSuplente(obj.getAudienciaLiberaSuplente());
		parametroServicioSorteo.setliberaArbitrosCierre(obj.getLiberaArbitrosCierre());
		parametroServicioSorteo.setSuspendeNoPronunciamiento(obj.getSuspendeNoPronunciamiento());
		parametroServicioSorteo.setSuspendeRechazo(obj.getSuspendeRechazo());
		parametroServicioSorteo.setSuspendeExtemporaneo(obj.getSuspendeExtemporaneo());
		
		return parametroServicioSorteo;
	}


	@Override
	public ParametroServicioSorteo transformarEntidadConDependencias(ParametroServicioSorteo obj) {
		ParametroServicioSorteo parametroServicioSorteo = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		
		ServicioDTO dto = new ServicioDTO();
		parametroServicioSorteo.setServicio(dto.transformarEntidadSinDependencias(obj.getServicio()));

		// protected region modificaciones transformarEntidadConDependencias end
		
		return parametroServicioSorteo;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ParametroServicioSorteo> coleccion) {
		List<ParametroServicioSorteoDTO> parametroServicioSorteoDTOList = new ArrayList<>();
		for(ParametroServicioSorteo c : coleccion)
			parametroServicioSorteoDTOList.add(transformarConDependencias(c));
		return parametroServicioSorteoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ParametroServicioSorteo> coleccion) {
		List<ParametroServicioSorteoDTO> parametroServicioSorteoDTOList = new ArrayList<>();
		for(ParametroServicioSorteo c : coleccion)
			parametroServicioSorteoDTOList.add(transformarSinDependencias(c));
		return parametroServicioSorteoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ParametroServicioSorteo> coleccion) {
		List<ParametroServicioSorteo> parametroServicioSorteoList = new ArrayList<>();
		for(ParametroServicioSorteo c : coleccion)
			parametroServicioSorteoList.add(transformarEntidadConDependencias(c));
		return parametroServicioSorteoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ParametroServicioSorteo> coleccion) {
		List<ParametroServicioSorteo> parametroServicioSorteoList = new ArrayList<>();
		for(ParametroServicioSorteo c : coleccion)
			parametroServicioSorteoList.add(transformarEntidadSinDependencias(c));
		return parametroServicioSorteoList;
	}



	public boolean getBloqueaSuplente() {
		return bloqueaSuplente;
	}



	public void setBloqueaSuplente(boolean bloqueaSuplente) {
		this.bloqueaSuplente = bloqueaSuplente;
	}



	public String getAudienciaLiberaSuplente() {
		return audienciaLiberaSuplente;
	}



	public void setAudienciaLiberaSuplente(String audienciaLiberaSuplente) {
		this.audienciaLiberaSuplente = audienciaLiberaSuplente;
	}



	public boolean getLiberaArbitrosCierre() {
		return liberaArbitrosCierre;
	}



	public void setLiberaArbitrosCierre(boolean liberaArbitrosCierre) {
		this.liberaArbitrosCierre = liberaArbitrosCierre;
	}



	public boolean getSuspendeNoPronunciamiento() {
		return suspendeNoPronunciamiento;
	}



	public void setSuspendeNoPronunciamiento(boolean suspendeNoPronunciamiento) {
		this.suspendeNoPronunciamiento = suspendeNoPronunciamiento;
	}



	public boolean getSuspendeRechazo() {
		return suspendeRechazo;
	}



	public void setSuspendeRechazo(boolean suspendeRechazo) {
		this.suspendeRechazo = suspendeRechazo;
	}



	public boolean getSuspendeExtemporaneo() {
		return suspendeExtemporaneo;
	}



	public void setSuspendeExtemporaneo(boolean suspendeExtemporaneo) {
		this.suspendeExtemporaneo = suspendeExtemporaneo;
	}



	
	
	
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
