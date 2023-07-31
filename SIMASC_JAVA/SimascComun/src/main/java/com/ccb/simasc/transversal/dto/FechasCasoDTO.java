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

import com.ccb.simasc.transversal.entidades.FechasCaso;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad FechasCasoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class FechasCasoDTO extends IDTO<FechasCaso> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idCaso;

	private Date fechaDeDemanda;		
	private Date fechaDeDesignacionDeArbitros;		
	private Date fechaDeInstalacion;		
	private Date fechaDeAdmisionDeDemanda;		
	private Date fechaDeNotificacionDelDemandado;		
	private Date fechaDeContestacionDeDemanda;		
	private Date fechaDeDemandaDeReconvencion;		
	private Date fechaDeLlamamientoEnGarantia;		
	private Date fechaDeTrasladoAExcepcionesDeMerito;		
	private Date fechaDeAudienciaDeConciliacion;		
	private Date fechaDeFijacionDeGastosYHonorarios;		
	private Date fechaDeAudienciaPrimeraDeTramite;		
	private Date fechaDeAudienciasDePruebas;		
	private Date fechaDeCierreDeEtapaAprobatoria;		
	private Date fechaDeAudienciasDeAlegatos;		
	private Date fechaDeAudienciaDeLaudo;		
	private Date fAudAclaracionesComplementacionesCorreccionesLaudo;		
	private Date fechaDeTramiteRecursoAnulacion;		
	private Date fechaTribunalConsignaAlCac;		
	private Date fechaDeCierre;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public FechasCasoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
	
	public Date getFechaDeDemanda(){
		return this.fechaDeDemanda;
	}
	
	public void setFechaDeDemanda(Date fechaDeDemanda){
		this.fechaDeDemanda = fechaDeDemanda;
	}
		
	public Date getFechaDeDesignacionDeArbitros(){
		return this.fechaDeDesignacionDeArbitros;
	}
	
	public void setFechaDeDesignacionDeArbitros(Date fechaDeDesignacionDeArbitros){
		this.fechaDeDesignacionDeArbitros = fechaDeDesignacionDeArbitros;
	}
		
	public Date getFechaDeInstalacion(){
		return this.fechaDeInstalacion;
	}
	
	public void setFechaDeInstalacion(Date fechaDeInstalacion){
		this.fechaDeInstalacion = fechaDeInstalacion;
	}
		
	public Date getFechaDeAdmisionDeDemanda(){
		return this.fechaDeAdmisionDeDemanda;
	}
	
	public void setFechaDeAdmisionDeDemanda(Date fechaDeAdmisionDeDemanda){
		this.fechaDeAdmisionDeDemanda = fechaDeAdmisionDeDemanda;
	}
		
	public Date getFechaDeNotificacionDelDemandado(){
		return this.fechaDeNotificacionDelDemandado;
	}
	
	public void setFechaDeNotificacionDelDemandado(Date fechaDeNotificacionDelDemandado){
		this.fechaDeNotificacionDelDemandado = fechaDeNotificacionDelDemandado;
	}
		
	public Date getFechaDeContestacionDeDemanda(){
		return this.fechaDeContestacionDeDemanda;
	}
	
	public void setFechaDeContestacionDeDemanda(Date fechaDeContestacionDeDemanda){
		this.fechaDeContestacionDeDemanda = fechaDeContestacionDeDemanda;
	}
		
	public Date getFechaDeDemandaDeReconvencion(){
		return this.fechaDeDemandaDeReconvencion;
	}
	
	public void setFechaDeDemandaDeReconvencion(Date fechaDeDemandaDeReconvencion){
		this.fechaDeDemandaDeReconvencion = fechaDeDemandaDeReconvencion;
	}
		
	public Date getFechaDeLlamamientoEnGarantia(){
		return this.fechaDeLlamamientoEnGarantia;
	}
	
	public void setFechaDeLlamamientoEnGarantia(Date fechaDeLlamamientoEnGarantia){
		this.fechaDeLlamamientoEnGarantia = fechaDeLlamamientoEnGarantia;
	}
		
	public Date getFechaDeTrasladoAExcepcionesDeMerito(){
		return this.fechaDeTrasladoAExcepcionesDeMerito;
	}
	
	public void setFechaDeTrasladoAExcepcionesDeMerito(Date fechaDeTrasladoAExcepcionesDeMerito){
		this.fechaDeTrasladoAExcepcionesDeMerito = fechaDeTrasladoAExcepcionesDeMerito;
	}
		
	public Date getFechaDeAudienciaDeConciliacion(){
		return this.fechaDeAudienciaDeConciliacion;
	}
	
	public void setFechaDeAudienciaDeConciliacion(Date fechaDeAudienciaDeConciliacion){
		this.fechaDeAudienciaDeConciliacion = fechaDeAudienciaDeConciliacion;
	}
		
	public Date getFechaDeFijacionDeGastosYHonorarios(){
		return this.fechaDeFijacionDeGastosYHonorarios;
	}
	
	public void setFechaDeFijacionDeGastosYHonorarios(Date fechaDeFijacionDeGastosYHonorarios){
		this.fechaDeFijacionDeGastosYHonorarios = fechaDeFijacionDeGastosYHonorarios;
	}
		
	public Date getFechaDeAudienciaPrimeraDeTramite(){
		return this.fechaDeAudienciaPrimeraDeTramite;
	}
	
	public void setFechaDeAudienciaPrimeraDeTramite(Date fechaDeAudienciaPrimeraDeTramite){
		this.fechaDeAudienciaPrimeraDeTramite = fechaDeAudienciaPrimeraDeTramite;
	}
		
	public Date getFechaDeAudienciasDePruebas(){
		return this.fechaDeAudienciasDePruebas;
	}
	
	public void setFechaDeAudienciasDePruebas(Date fechaDeAudienciasDePruebas){
		this.fechaDeAudienciasDePruebas = fechaDeAudienciasDePruebas;
	}
		
	public Date getFechaDeCierreDeEtapaAprobatoria(){
		return this.fechaDeCierreDeEtapaAprobatoria;
	}
	
	public void setFechaDeCierreDeEtapaAprobatoria(Date fechaDeCierreDeEtapaAprobatoria){
		this.fechaDeCierreDeEtapaAprobatoria = fechaDeCierreDeEtapaAprobatoria;
	}
		
	public Date getFechaDeAudienciasDeAlegatos(){
		return this.fechaDeAudienciasDeAlegatos;
	}
	
	public void setFechaDeAudienciasDeAlegatos(Date fechaDeAudienciasDeAlegatos){
		this.fechaDeAudienciasDeAlegatos = fechaDeAudienciasDeAlegatos;
	}
		
	public Date getFechaDeAudienciaDeLaudo(){
		return this.fechaDeAudienciaDeLaudo;
	}
	
	public void setFechaDeAudienciaDeLaudo(Date fechaDeAudienciaDeLaudo){
		this.fechaDeAudienciaDeLaudo = fechaDeAudienciaDeLaudo;
	}
		
	public Date getFAudAclaracionesComplementacionesCorreccionesLaudo(){
		return this.fAudAclaracionesComplementacionesCorreccionesLaudo;
	}
	
	public void setFAudAclaracionesComplementacionesCorreccionesLaudo(Date fAudAclaracionesComplementacionesCorreccionesLaudo){
		this.fAudAclaracionesComplementacionesCorreccionesLaudo = fAudAclaracionesComplementacionesCorreccionesLaudo;
	}
		
	public Date getFechaDeTramiteRecursoAnulacion(){
		return this.fechaDeTramiteRecursoAnulacion;
	}
	
	public void setFechaDeTramiteRecursoAnulacion(Date fechaDeTramiteRecursoAnulacion){
		this.fechaDeTramiteRecursoAnulacion = fechaDeTramiteRecursoAnulacion;
	}
		
	public Date getFechaTribunalConsignaAlCac(){
		return this.fechaTribunalConsignaAlCac;
	}
	
	public void setFechaTribunalConsignaAlCac(Date fechaTribunalConsignaAlCac){
		this.fechaTribunalConsignaAlCac = fechaTribunalConsignaAlCac;
	}
		
	public Date getFechaDeCierre(){
		return this.fechaDeCierre;
	}
	
	public void setFechaDeCierre(Date fechaDeCierre){
		this.fechaDeCierre = fechaDeCierre;
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
        
        hash = 37 * hash + Objects.hashCode(this.idCaso);        
        hash = 37 * hash + Objects.hashCode(this.fechaDeDemanda);
        hash = 37 * hash + Objects.hashCode(this.fechaDeDesignacionDeArbitros);
        hash = 37 * hash + Objects.hashCode(this.fechaDeInstalacion);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAdmisionDeDemanda);
        hash = 37 * hash + Objects.hashCode(this.fechaDeNotificacionDelDemandado);
        hash = 37 * hash + Objects.hashCode(this.fechaDeContestacionDeDemanda);
        hash = 37 * hash + Objects.hashCode(this.fechaDeDemandaDeReconvencion);
        hash = 37 * hash + Objects.hashCode(this.fechaDeLlamamientoEnGarantia);
        hash = 37 * hash + Objects.hashCode(this.fechaDeTrasladoAExcepcionesDeMerito);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAudienciaDeConciliacion);
        hash = 37 * hash + Objects.hashCode(this.fechaDeFijacionDeGastosYHonorarios);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAudienciaPrimeraDeTramite);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAudienciasDePruebas);
        hash = 37 * hash + Objects.hashCode(this.fechaDeCierreDeEtapaAprobatoria);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAudienciasDeAlegatos);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAudienciaDeLaudo);
        hash = 37 * hash + Objects.hashCode(this.fAudAclaracionesComplementacionesCorreccionesLaudo);
        hash = 37 * hash + Objects.hashCode(this.fechaDeTramiteRecursoAnulacion);
        hash = 37 * hash + Objects.hashCode(this.fechaTribunalConsignaAlCac);
        hash = 37 * hash + Objects.hashCode(this.fechaDeCierre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad FechasCasoDTO que se pasa
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
        final FechasCasoDTO other = (FechasCasoDTO) obj;
                
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeDemanda, other.fechaDeDemanda)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeDesignacionDeArbitros, other.fechaDeDesignacionDeArbitros)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeInstalacion, other.fechaDeInstalacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAdmisionDeDemanda, other.fechaDeAdmisionDeDemanda)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeNotificacionDelDemandado, other.fechaDeNotificacionDelDemandado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeContestacionDeDemanda, other.fechaDeContestacionDeDemanda)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeDemandaDeReconvencion, other.fechaDeDemandaDeReconvencion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeLlamamientoEnGarantia, other.fechaDeLlamamientoEnGarantia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeTrasladoAExcepcionesDeMerito, other.fechaDeTrasladoAExcepcionesDeMerito)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAudienciaDeConciliacion, other.fechaDeAudienciaDeConciliacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeFijacionDeGastosYHonorarios, other.fechaDeFijacionDeGastosYHonorarios)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAudienciaPrimeraDeTramite, other.fechaDeAudienciaPrimeraDeTramite)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAudienciasDePruebas, other.fechaDeAudienciasDePruebas)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeCierreDeEtapaAprobatoria, other.fechaDeCierreDeEtapaAprobatoria)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAudienciasDeAlegatos, other.fechaDeAudienciasDeAlegatos)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAudienciaDeLaudo, other.fechaDeAudienciaDeLaudo)) {
            return false;
        }
        
        if (!Objects.equals(this.fAudAclaracionesComplementacionesCorreccionesLaudo, other.fAudAclaracionesComplementacionesCorreccionesLaudo)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeTramiteRecursoAnulacion, other.fechaDeTramiteRecursoAnulacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaTribunalConsignaAlCac, other.fechaTribunalConsignaAlCac)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeCierre, other.fechaDeCierre)) {
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
	public FechasCasoDTO transformarSinDependencias(FechasCaso obj) {
		FechasCasoDTO fechasCasoDTO = new FechasCasoDTO();
		
		fechasCasoDTO.setIdCaso(obj.getIdCaso());
		fechasCasoDTO.setFechaDeDemanda(obj.getFechaDeDemanda());
		fechasCasoDTO.setFechaDeDesignacionDeArbitros(obj.getFechaDeDesignacionDeArbitros());
		fechasCasoDTO.setFechaDeInstalacion(obj.getFechaDeInstalacion());
		fechasCasoDTO.setFechaDeAdmisionDeDemanda(obj.getFechaDeAdmisionDeDemanda());
		fechasCasoDTO.setFechaDeNotificacionDelDemandado(obj.getFechaDeNotificacionDelDemandado());
		fechasCasoDTO.setFechaDeContestacionDeDemanda(obj.getFechaDeContestacionDeDemanda());
		fechasCasoDTO.setFechaDeDemandaDeReconvencion(obj.getFechaDeDemandaDeReconvencion());
		fechasCasoDTO.setFechaDeLlamamientoEnGarantia(obj.getFechaDeLlamamientoEnGarantia());
		fechasCasoDTO.setFechaDeTrasladoAExcepcionesDeMerito(obj.getFechaDeTrasladoAExcepcionesDeMerito());
		fechasCasoDTO.setFechaDeAudienciaDeConciliacion(obj.getFechaDeAudienciaDeConciliacion());
		fechasCasoDTO.setFechaDeFijacionDeGastosYHonorarios(obj.getFechaDeFijacionDeGastosYHonorarios());
		fechasCasoDTO.setFechaDeAudienciaPrimeraDeTramite(obj.getFechaDeAudienciaPrimeraDeTramite());
		fechasCasoDTO.setFechaDeAudienciasDePruebas(obj.getFechaDeAudienciasDePruebas());
		fechasCasoDTO.setFechaDeCierreDeEtapaAprobatoria(obj.getFechaDeCierreDeEtapaAprobatoria());
		fechasCasoDTO.setFechaDeAudienciasDeAlegatos(obj.getFechaDeAudienciasDeAlegatos());
		fechasCasoDTO.setFechaDeAudienciaDeLaudo(obj.getFechaDeAudienciaDeLaudo());
		fechasCasoDTO.setFAudAclaracionesComplementacionesCorreccionesLaudo(obj.getFAudAclaracionesComplementacionesCorreccionesLaudo());
		fechasCasoDTO.setFechaDeTramiteRecursoAnulacion(obj.getFechaDeTramiteRecursoAnulacion());
		fechasCasoDTO.setFechaTribunalConsignaAlCac(obj.getFechaTribunalConsignaAlCac());
		fechasCasoDTO.setFechaDeCierre(obj.getFechaDeCierre());
		fechasCasoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		fechasCasoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		fechasCasoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return fechasCasoDTO;
	}

	@Override
	public FechasCasoDTO transformarConDependencias(FechasCaso obj) {
		FechasCasoDTO fechasCasoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return fechasCasoDTO;
	}

	@Override
	public FechasCaso transformarEntidadSinDependencias(FechasCaso obj) {
		FechasCaso fechasCaso = new FechasCaso();
		
		fechasCaso.setIdCaso(obj.getIdCaso());
		
		fechasCaso.setFechaDeDemanda(obj.getFechaDeDemanda());
		fechasCaso.setFechaDeDesignacionDeArbitros(obj.getFechaDeDesignacionDeArbitros());
		fechasCaso.setFechaDeInstalacion(obj.getFechaDeInstalacion());
		fechasCaso.setFechaDeAdmisionDeDemanda(obj.getFechaDeAdmisionDeDemanda());
		fechasCaso.setFechaDeNotificacionDelDemandado(obj.getFechaDeNotificacionDelDemandado());
		fechasCaso.setFechaDeContestacionDeDemanda(obj.getFechaDeContestacionDeDemanda());
		fechasCaso.setFechaDeDemandaDeReconvencion(obj.getFechaDeDemandaDeReconvencion());
		fechasCaso.setFechaDeLlamamientoEnGarantia(obj.getFechaDeLlamamientoEnGarantia());
		fechasCaso.setFechaDeTrasladoAExcepcionesDeMerito(obj.getFechaDeTrasladoAExcepcionesDeMerito());
		fechasCaso.setFechaDeAudienciaDeConciliacion(obj.getFechaDeAudienciaDeConciliacion());
		fechasCaso.setFechaDeFijacionDeGastosYHonorarios(obj.getFechaDeFijacionDeGastosYHonorarios());
		fechasCaso.setFechaDeAudienciaPrimeraDeTramite(obj.getFechaDeAudienciaPrimeraDeTramite());
		fechasCaso.setFechaDeAudienciasDePruebas(obj.getFechaDeAudienciasDePruebas());
		fechasCaso.setFechaDeCierreDeEtapaAprobatoria(obj.getFechaDeCierreDeEtapaAprobatoria());
		fechasCaso.setFechaDeAudienciasDeAlegatos(obj.getFechaDeAudienciasDeAlegatos());
		fechasCaso.setFechaDeAudienciaDeLaudo(obj.getFechaDeAudienciaDeLaudo());
		fechasCaso.setFAudAclaracionesComplementacionesCorreccionesLaudo(obj.getFAudAclaracionesComplementacionesCorreccionesLaudo());
		fechasCaso.setFechaDeTramiteRecursoAnulacion(obj.getFechaDeTramiteRecursoAnulacion());
		fechasCaso.setFechaTribunalConsignaAlCac(obj.getFechaTribunalConsignaAlCac());
		fechasCaso.setFechaDeCierre(obj.getFechaDeCierre());
		fechasCaso.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		fechasCaso.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		fechasCaso.setEstadoRegistro(obj.getEstadoRegistro());
		
		return fechasCaso;
	}


	@Override
	public FechasCaso transformarEntidadConDependencias(FechasCaso obj) {
		FechasCaso fechasCaso = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return fechasCaso;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<FechasCaso> coleccion) {
		List<FechasCasoDTO> fechasCasoDTOList = new ArrayList<>();
		for(FechasCaso c : coleccion)
			fechasCasoDTOList.add(transformarConDependencias(c));
		return fechasCasoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<FechasCaso> coleccion) {
		List<FechasCasoDTO> fechasCasoDTOList = new ArrayList<>();
		for(FechasCaso c : coleccion)
			fechasCasoDTOList.add(transformarSinDependencias(c));
		return fechasCasoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<FechasCaso> coleccion) {
		List<FechasCaso> fechasCasoList = new ArrayList<>();
		for(FechasCaso c : coleccion)
			fechasCasoList.add(transformarEntidadSinDependencias(c));
		return fechasCasoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<FechasCaso> coleccion) {
		List<FechasCaso> fechasCasoList = new ArrayList<>();
		for(FechasCaso c : coleccion)
			fechasCasoList.add(transformarEntidadConDependencias(c));
		return fechasCasoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
