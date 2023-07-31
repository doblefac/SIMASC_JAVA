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

import com.ccb.simasc.transversal.entidades.DesarrolloProfesional;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad DesarrolloProfesionalDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class DesarrolloProfesionalDTO extends IDTO<DesarrolloProfesional> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idDesarrolloProfesional;

	private String tipoDesarrolloProfesional;		
	private String nombre;		
	private String institucion;		
	private Date fechaInicial;		
	private Date fechaFinal;		
	private String funciones;		
	private Long idMateria;		
	private String nivelAcademico;		
	private Integer horasCursadas;		
	private String tipoCurso;		
	private String materiaCurso;		
	private Integer numeroHoras;		
	private String tipoCursoNoDefinido;		
	private String materiaCursoNoDefinida;		
	private Integer numeroAudiencias;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idPersona;		
	private Long idDocumentoConstancia;		
	private Double calificacion;		
	private String lugar;		
	
    public DesarrolloProfesionalDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdDesarrolloProfesional(){
		return this.idDesarrolloProfesional;
	}
	
	public void setIdDesarrolloProfesional(Long idDesarrolloProfesional){
		this.idDesarrolloProfesional = idDesarrolloProfesional;
	}
	
	public String getTipoDesarrolloProfesional(){
		return this.tipoDesarrolloProfesional;
	}
	
	public void setTipoDesarrolloProfesional(String tipoDesarrolloProfesional){
		this.tipoDesarrolloProfesional = tipoDesarrolloProfesional;
	}
		
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getInstitucion(){
		return this.institucion;
	}
	
	public void setInstitucion(String institucion){
		this.institucion = institucion;
	}
		
	public Date getFechaInicial(){
		return this.fechaInicial;
	}
	
	public void setFechaInicial(Date fechaInicial){
		this.fechaInicial = fechaInicial;
	}
		
	public Date getFechaFinal(){
		return this.fechaFinal;
	}
	
	public void setFechaFinal(Date fechaFinal){
		this.fechaFinal = fechaFinal;
	}
		
	public String getFunciones(){
		return this.funciones;
	}
	
	public void setFunciones(String funciones){
		this.funciones = funciones;
	}
		
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
		
	public String getNivelAcademico(){
		return this.nivelAcademico;
	}
	
	public void setNivelAcademico(String nivelAcademico){
		this.nivelAcademico = nivelAcademico;
	}
		
	public Integer getHorasCursadas(){
		return this.horasCursadas;
	}
	
	public void setHorasCursadas(Integer horasCursadas){
		this.horasCursadas = horasCursadas;
	}
		
	public String getTipoCurso(){
		return this.tipoCurso;
	}
	
	public void setTipoCurso(String tipoCurso){
		this.tipoCurso = tipoCurso;
	}
		
	public String getMateriaCurso(){
		return this.materiaCurso;
	}
	
	public void setMateriaCurso(String materiaCurso){
		this.materiaCurso = materiaCurso;
	}
		
	public Integer getNumeroHoras(){
		return this.numeroHoras;
	}
	
	public void setNumeroHoras(Integer numeroHoras){
		this.numeroHoras = numeroHoras;
	}
		
	public String getTipoCursoNoDefinido(){
		return this.tipoCursoNoDefinido;
	}
	
	public void setTipoCursoNoDefinido(String tipoCursoNoDefinido){
		this.tipoCursoNoDefinido = tipoCursoNoDefinido;
	}
		
	public String getMateriaCursoNoDefinida(){
		return this.materiaCursoNoDefinida;
	}
	
	public void setMateriaCursoNoDefinida(String materiaCursoNoDefinida){
		this.materiaCursoNoDefinida = materiaCursoNoDefinida;
	}
		
	public Integer getNumeroAudiencias(){
		return this.numeroAudiencias;
	}
	
	public void setNumeroAudiencias(Integer numeroAudiencias){
		this.numeroAudiencias = numeroAudiencias;
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
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdDocumentoConstancia(){
		return this.idDocumentoConstancia;
	}
	
	public void setIdDocumentoConstancia(Long idDocumentoConstancia){
		this.idDocumentoConstancia = idDocumentoConstancia;
	}
		
	public Double getCalificacion(){
		return this.calificacion;
	}
	
	public void setCalificacion(Double calificacion){
		this.calificacion = calificacion;
	}
		
	public String getLugar(){
		return this.lugar;
	}
	
	public void setLugar(String lugar){
		this.lugar = lugar;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idDesarrolloProfesional);        
        hash = 37 * hash + Objects.hashCode(this.tipoDesarrolloProfesional);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.institucion);
        hash = 37 * hash + Objects.hashCode(this.fechaInicial);
        hash = 37 * hash + Objects.hashCode(this.fechaFinal);
        hash = 37 * hash + Objects.hashCode(this.funciones);
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.nivelAcademico);
        hash = 37 * hash + Objects.hashCode(this.horasCursadas);
        hash = 37 * hash + Objects.hashCode(this.tipoCurso);
        hash = 37 * hash + Objects.hashCode(this.materiaCurso);
        hash = 37 * hash + Objects.hashCode(this.numeroHoras);
        hash = 37 * hash + Objects.hashCode(this.tipoCursoNoDefinido);
        hash = 37 * hash + Objects.hashCode(this.materiaCursoNoDefinida);
        hash = 37 * hash + Objects.hashCode(this.numeroAudiencias);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idDocumentoConstancia);
        hash = 37 * hash + Objects.hashCode(this.calificacion);
        hash = 37 * hash + Objects.hashCode(this.lugar);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DesarrolloProfesionalDTO que se pasa
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
        final DesarrolloProfesionalDTO other = (DesarrolloProfesionalDTO) obj;
                
        if (!Objects.equals(this.idDesarrolloProfesional, other.idDesarrolloProfesional)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDesarrolloProfesional, other.tipoDesarrolloProfesional)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.institucion, other.institucion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicial, other.fechaInicial)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFinal, other.fechaFinal)) {
            return false;
        }
        
        if (!Objects.equals(this.funciones, other.funciones)) {
            return false;
        }
        
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.nivelAcademico, other.nivelAcademico)) {
            return false;
        }
        
        if (!Objects.equals(this.horasCursadas, other.horasCursadas)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCurso, other.tipoCurso)) {
            return false;
        }
        
        if (!Objects.equals(this.materiaCurso, other.materiaCurso)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroHoras, other.numeroHoras)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCursoNoDefinido, other.tipoCursoNoDefinido)) {
            return false;
        }
        
        if (!Objects.equals(this.materiaCursoNoDefinida, other.materiaCursoNoDefinida)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroAudiencias, other.numeroAudiencias)) {
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
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumentoConstancia, other.idDocumentoConstancia)) {
            return false;
        }
        
        if (!Objects.equals(this.calificacion, other.calificacion)) {
            return false;
        }
        
        return Objects.equals(this.lugar, other.lugar);
                
    }
    
    @Override
	public DesarrolloProfesionalDTO transformarSinDependencias(DesarrolloProfesional obj) {
		DesarrolloProfesionalDTO desarrolloProfesionalDTO = new DesarrolloProfesionalDTO();
		
		desarrolloProfesionalDTO.setIdDesarrolloProfesional(obj.getIdDesarrolloProfesional());
		desarrolloProfesionalDTO.setTipoDesarrolloProfesional(obj.getTipoDesarrolloProfesional());
		desarrolloProfesionalDTO.setNombre(obj.getNombre());
		desarrolloProfesionalDTO.setInstitucion(obj.getInstitucion());
		desarrolloProfesionalDTO.setFechaInicial(obj.getFechaInicial());
		desarrolloProfesionalDTO.setFechaFinal(obj.getFechaFinal());
		desarrolloProfesionalDTO.setFunciones(obj.getFunciones());
		desarrolloProfesionalDTO.setIdMateria(obj.getIdMateria());
		desarrolloProfesionalDTO.setNivelAcademico(obj.getNivelAcademico());
		desarrolloProfesionalDTO.setHorasCursadas(obj.getHorasCursadas());
		desarrolloProfesionalDTO.setTipoCurso(obj.getTipoCurso());
		desarrolloProfesionalDTO.setMateriaCurso(obj.getMateriaCurso());
		desarrolloProfesionalDTO.setNumeroHoras(obj.getNumeroHoras());
		desarrolloProfesionalDTO.setTipoCursoNoDefinido(obj.getTipoCursoNoDefinido());
		desarrolloProfesionalDTO.setMateriaCursoNoDefinida(obj.getMateriaCursoNoDefinida());
		desarrolloProfesionalDTO.setNumeroAudiencias(obj.getNumeroAudiencias());
		desarrolloProfesionalDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		desarrolloProfesionalDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		desarrolloProfesionalDTO.setEstadoRegistro(obj.getEstadoRegistro());
		desarrolloProfesionalDTO.setIdPersona(obj.getIdPersona());
		desarrolloProfesionalDTO.setIdDocumentoConstancia(obj.getIdDocumentoConstancia());
		desarrolloProfesionalDTO.setCalificacion(obj.getCalificacion());
		desarrolloProfesionalDTO.setLugar(obj.getLugar());
		
		return desarrolloProfesionalDTO;
	}

	@Override
	public DesarrolloProfesionalDTO transformarConDependencias(DesarrolloProfesional obj) {
		DesarrolloProfesionalDTO desarrolloProfesionalDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return desarrolloProfesionalDTO;
	}

	@Override
	public DesarrolloProfesional transformarEntidadSinDependencias(DesarrolloProfesional obj) {
		DesarrolloProfesional desarrolloProfesional = new DesarrolloProfesional();
		
		desarrolloProfesional.setIdDesarrolloProfesional(obj.getIdDesarrolloProfesional());
		
		desarrolloProfesional.setTipoDesarrolloProfesional(obj.getTipoDesarrolloProfesional());
		desarrolloProfesional.setNombre(obj.getNombre());
		desarrolloProfesional.setInstitucion(obj.getInstitucion());
		desarrolloProfesional.setFechaInicial(obj.getFechaInicial());
		desarrolloProfesional.setFechaFinal(obj.getFechaFinal());
		desarrolloProfesional.setFunciones(obj.getFunciones());
		desarrolloProfesional.setIdMateria(obj.getIdMateria());
		desarrolloProfesional.setNivelAcademico(obj.getNivelAcademico());
		desarrolloProfesional.setHorasCursadas(obj.getHorasCursadas());
		desarrolloProfesional.setTipoCurso(obj.getTipoCurso());
		desarrolloProfesional.setMateriaCurso(obj.getMateriaCurso());
		desarrolloProfesional.setNumeroHoras(obj.getNumeroHoras());
		desarrolloProfesional.setTipoCursoNoDefinido(obj.getTipoCursoNoDefinido());
		desarrolloProfesional.setMateriaCursoNoDefinida(obj.getMateriaCursoNoDefinida());
		desarrolloProfesional.setNumeroAudiencias(obj.getNumeroAudiencias());
		desarrolloProfesional.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		desarrolloProfesional.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		desarrolloProfesional.setEstadoRegistro(obj.getEstadoRegistro());
		desarrolloProfesional.setIdPersona(obj.getIdPersona());
		desarrolloProfesional.setIdDocumentoConstancia(obj.getIdDocumentoConstancia());
		desarrolloProfesional.setCalificacion(obj.getCalificacion());
		desarrolloProfesional.setLugar(obj.getLugar());
		
		return desarrolloProfesional;
	}


	@Override
	public DesarrolloProfesional transformarEntidadConDependencias(DesarrolloProfesional obj) {
		DesarrolloProfesional desarrolloProfesional = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		if(obj.getDocumento() != null) {
			desarrolloProfesional.setDocumento(obj.getDocumento());
		}
		// protected region modificaciones transformarEntidadConDependencias end
		
		return desarrolloProfesional;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<DesarrolloProfesional> coleccion) {
		List<DesarrolloProfesionalDTO> desarrolloProfesionalDTOList = new ArrayList<>();
		for(DesarrolloProfesional c : coleccion)
			desarrolloProfesionalDTOList.add(transformarConDependencias(c));
		return desarrolloProfesionalDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<DesarrolloProfesional> coleccion) {
		List<DesarrolloProfesionalDTO> desarrolloProfesionalDTOList = new ArrayList<>();
		for(DesarrolloProfesional c : coleccion)
			desarrolloProfesionalDTOList.add(transformarSinDependencias(c));
		return desarrolloProfesionalDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<DesarrolloProfesional> coleccion) {
		List<DesarrolloProfesional> desarrolloProfesionalList = new ArrayList<>();
		for(DesarrolloProfesional c : coleccion)
			desarrolloProfesionalList.add(transformarEntidadConDependencias(c));
		return desarrolloProfesionalList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<DesarrolloProfesional> coleccion) {
		List<DesarrolloProfesional> desarrolloProfesionalList = new ArrayList<>();
		for(DesarrolloProfesional c : coleccion)
			desarrolloProfesionalList.add(transformarEntidadSinDependencias(c));
		return desarrolloProfesionalList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
