package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.Audiencia;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad AudienciaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class AudienciaDTO extends IDTO<Audiencia> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	
	private String nombreSala;
	private String nombreCaso;
	private Long idUsuario;
	private String etapa;
	private Long idSala;	


	// protected region atributos end
	private Long idAudiencia;

	private boolean virtual;		
	private String estado;		
	private Date horaInicio;		
	private Date horaFin;		
	private String tipoAudiencia;		
	private Integer cantidadAsistentes;		
	private String observaciones;		
	private String resultado;		
	private Long consecutivo;
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idSorteo;		
	private Long idCaso;		
	private Long idRolSecretario;		
	private Long idPersonaSecretario;		
	private Long idCasoSecretario;		
	private Integer cantidadPrincipales;		
	private Integer cantidadSuplentes;		
	private List<InasistenciaDTO> inasistenciaList;
	private String tipoSorteo;
	
    public AudienciaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
	
	public boolean getVirtual(){
		return this.virtual;
	}
	
	public void setVirtual(boolean virtual){
		this.virtual = virtual;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Date getHoraInicio(){
		return this.horaInicio;
	}
	
	public void setHoraInicio(Date horaInicio){
		this.horaInicio = horaInicio;
	}
		
	public Date getHoraFin(){
		return this.horaFin;
	}
	
	public void setHoraFin(Date horaFin){
		this.horaFin = horaFin;
	}
		
	public String getTipoAudiencia(){
		return this.tipoAudiencia;
	}
	
	public void setTipoAudiencia(String tipoAudiencia){
		this.tipoAudiencia = tipoAudiencia;
	}
		
		
	public Integer getCantidadAsistentes(){
		return this.cantidadAsistentes;
	}
	
	public void setCantidadAsistentes(Integer cantidadAsistentes){
		this.cantidadAsistentes = cantidadAsistentes;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public String getResultado(){
		return this.resultado;
	}
	
	public void setResultado(String resultado){
		this.resultado = resultado;
	}
	
	public Long getConsecutivo() {
		return consecutivo;
	}
	
	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
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
		
	public Long getIdSorteo(){
		return this.idSorteo;
	}
	
	public void setIdSorteo(Long idSorteo){
		this.idSorteo = idSorteo;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public Long getIdRolSecretario(){
		return this.idRolSecretario;
	}
	
	public void setIdRolSecretario(Long idRolSecretario){
		this.idRolSecretario = idRolSecretario;
	}
		
	public Long getIdPersonaSecretario(){
		return this.idPersonaSecretario;
	}
	
	public void setIdPersonaSecretario(Long idPersonaSecretario){
		this.idPersonaSecretario = idPersonaSecretario;
	}
		
	public Long getIdCasoSecretario(){
		return this.idCasoSecretario;
	}
	
	public void setIdCasoSecretario(Long idCasoSecretario){
		this.idCasoSecretario = idCasoSecretario;
	}	
    public String getTipoSorteo() {
		return tipoSorteo;
	}
	public void setTipoSorteo(String tipoSorteo) {
		this.tipoSorteo = tipoSorteo;
	}

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);        
        hash = 37 * hash + (this.virtual ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.horaInicio);
        hash = 37 * hash + Objects.hashCode(this.horaFin);
        hash = 37 * hash + Objects.hashCode(this.tipoAudiencia);
        hash = 37 * hash + Objects.hashCode(this.cantidadAsistentes);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.resultado);
        hash = 37 * hash + Objects.hashCode(this.consecutivo);  
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idSorteo);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idRolSecretario);
        hash = 37 * hash + Objects.hashCode(this.idPersonaSecretario);
        hash = 37 * hash + Objects.hashCode(this.idCasoSecretario);
        hash = 37 * hash + Objects.hashCode(this.tipoSorteo);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AudienciaDTO que se pasa
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
        final AudienciaDTO other = (AudienciaDTO) obj;
                
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.virtual, other.virtual)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.horaFin, other.horaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoAudiencia, other.tipoAudiencia)) {
            return false;
        }
        
        
        if (!Objects.equals(this.cantidadAsistentes, other.cantidadAsistentes)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.resultado, other.resultado)) {
            return false;
        }
        
        if (!Objects.equals(this.consecutivo, other.consecutivo)) {
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
        
        if (!Objects.equals(this.idSorteo, other.idSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.idRolSecretario, other.idRolSecretario)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaSecretario, other.idPersonaSecretario)) {
            return false;
        }
        if (!Objects.equals(this.tipoSorteo, other.tipoSorteo)) {
            return false;
        }
        
        return Objects.equals(this.idCasoSecretario, other.idCasoSecretario);
                
    }
    
    @Override
	public AudienciaDTO transformarSinDependencias(Audiencia obj) {
		AudienciaDTO audienciaDTO = new AudienciaDTO();
		
		audienciaDTO.setIdAudiencia(obj.getIdAudiencia());
		audienciaDTO.setVirtual(obj.getVirtual());
		audienciaDTO.setEstado(obj.getEstado());
		audienciaDTO.setHoraInicio(obj.getHoraInicio());
		audienciaDTO.setHoraFin(obj.getHoraFin());
		audienciaDTO.setTipoAudiencia(obj.getTipoAudiencia());
		audienciaDTO.setCantidadAsistentes(obj.getCantidadAsistentes());
		audienciaDTO.setObservaciones(obj.getObservaciones());
		audienciaDTO.setResultado(obj.getResultado());
		audienciaDTO.setConsecutivo(obj.getConsecutivo());
		audienciaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		audienciaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		audienciaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		audienciaDTO.setIdSorteo(obj.getIdSorteo());
		audienciaDTO.setIdCaso(obj.getIdCaso());
		audienciaDTO.setIdRolSecretario(obj.getIdRolSecretario());
		audienciaDTO.setIdPersonaSecretario(obj.getIdPersonaSecretario());
		audienciaDTO.setIdCasoSecretario(obj.getIdCasoSecretario());
		audienciaDTO.setTipoSorteo(obj.getTipoSorteo());
		
		return audienciaDTO;
	}

	@Override
	public AudienciaDTO transformarConDependencias(Audiencia obj) {
		AudienciaDTO audienciaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		if (obj.getInasistenciaList() != null) {
			List<InasistenciaDTO> inasistenciasDTO = (List<InasistenciaDTO>) new InasistenciaDTO()
					.transformarColeccionSinDependencias(obj.getInasistenciaList());
			audienciaDTO.setInasistenciaList(inasistenciasDTO);
		}
		// protected region modificaciones transformarConDependencias end

		return audienciaDTO;
	}

	@Override
	public Audiencia transformarEntidadSinDependencias(Audiencia obj) {
		Audiencia audiencia = new Audiencia();
		
		audiencia.setIdAudiencia(obj.getIdAudiencia());
		
		audiencia.setVirtual(obj.getVirtual());
		audiencia.setEstado(obj.getEstado());
		audiencia.setHoraInicio(obj.getHoraInicio());
		audiencia.setHoraFin(obj.getHoraFin());
		audiencia.setTipoAudiencia(obj.getTipoAudiencia());
		audiencia.setCantidadAsistentes(obj.getCantidadAsistentes());
		audiencia.setObservaciones(obj.getObservaciones());
		audiencia.setResultado(obj.getResultado());
		audiencia.setConsecutivo(obj.getConsecutivo());		
		audiencia.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		audiencia.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		audiencia.setEstadoRegistro(obj.getEstadoRegistro());
		audiencia.setIdSorteo(obj.getIdSorteo());
		audiencia.setIdCaso(obj.getIdCaso());
		audiencia.setIdRolSecretario(obj.getIdRolSecretario());
		audiencia.setIdPersonaSecretario(obj.getIdPersonaSecretario());
		audiencia.setIdCasoSecretario(obj.getIdCasoSecretario());
		audiencia.setTipoSorteo(obj.getTipoSorteo());
		
		return audiencia;
	}


	@Override
	public Audiencia transformarEntidadConDependencias(Audiencia obj) {
		Audiencia audiencia = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		
		// protected region modificaciones transformarEntidadConDependencias end
		
		return audiencia;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Audiencia> coleccion) {
		List<AudienciaDTO> audienciaDTOList = new ArrayList<>();
		for(Audiencia c : coleccion)
			audienciaDTOList.add(transformarConDependencias(c));
		return audienciaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Audiencia> coleccion) {
		List<AudienciaDTO> audienciaDTOList = new ArrayList<>();
		for(Audiencia c : coleccion)
			audienciaDTOList.add(transformarSinDependencias(c));
		return audienciaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Audiencia> coleccion) {
		List<Audiencia> audienciaList = new ArrayList<>();
		for(Audiencia c : coleccion)
			audienciaList.add(transformarEntidadConDependencias(c));
		return audienciaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Audiencia> coleccion) {
		List<Audiencia> audienciaList = new ArrayList<>();
		for(Audiencia c : coleccion)
			audienciaList.add(transformarEntidadSinDependencias(c));
		return audienciaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
	
	public String getNombreCaso() {
		return nombreCaso;
	}

	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}	

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	
	public Long getIdSala() {
		return idSala;
	}

	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}



	public List<InasistenciaDTO> getInasistenciaList() {
		return inasistenciaList;
	}

	public void setInasistenciaList(List<InasistenciaDTO> inasistenciaList) {
		this.inasistenciaList = inasistenciaList;
	}
	
	public Audiencia transformarDTOAEntidad( AudienciaDTO obj ){
		Audiencia audiencia = new Audiencia();
		
		audiencia.setIdAudiencia(obj.getIdAudiencia());
		audiencia.setVirtual(obj.getVirtual());
		audiencia.setEstado(obj.getEstado());
		audiencia.setHoraInicio(obj.getHoraInicio());
		audiencia.setHoraFin(obj.getHoraFin());
		audiencia.setTipoAudiencia(obj.getTipoAudiencia());
		audiencia.setCantidadAsistentes(obj.getCantidadAsistentes());
		audiencia.setObservaciones(obj.getObservaciones());
		audiencia.setResultado(obj.getResultado());
		audiencia.setConsecutivo(obj.getConsecutivo());		
		audiencia.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		audiencia.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		audiencia.setEstadoRegistro(obj.getEstadoRegistro());
		audiencia.setIdSorteo(obj.getIdSorteo());
		audiencia.setIdCaso(obj.getIdCaso());
		audiencia.setIdRolSecretario(obj.getIdRolSecretario());
		audiencia.setIdPersonaSecretario(obj.getIdPersonaSecretario());
		audiencia.setIdCasoSecretario(obj.getIdCasoSecretario());
		audiencia.setTipoSorteo(obj.getTipoSorteo());
		return audiencia;
	}

	public Integer getCantidadSuplentes() {
		return cantidadSuplentes;
	}

	public void setCantidadSuplentes(Integer cantidadSuplentes) {
		this.cantidadSuplentes = cantidadSuplentes;
	}

	public Integer getCantidadPrincipales() {
		return cantidadPrincipales;
	}

	public void setCantidadPrincipales(Integer cantidadPrincipales) {
		this.cantidadPrincipales = cantidadPrincipales;
	}
	
	// protected region metodos adicionales end

}
