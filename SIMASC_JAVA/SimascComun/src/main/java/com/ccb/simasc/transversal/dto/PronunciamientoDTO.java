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

import com.ccb.simasc.transversal.entidades.Pronunciamiento;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PronunciamientoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PronunciamientoDTO extends IDTO<Pronunciamiento> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private DocumentoDTO documento;
	// protected region atributos end
	private Long idPronunciamiento;

	private String pronunciamiento;		
	private boolean tieneRelacionConPartes;		
	private boolean maximoDeTribunalesEntidadPublica;		
	private boolean maximoDeTribunalesEntidadPublicaLey1682;
	private boolean procedimientoRecuperacionEmpresarial;
	private boolean tramitesAdministrativos;	
	private Date fecha;		
	private String revelaciones;		
	private String motivoDeclinacion;		
	private String observaciones;		
	private Long idDocumento;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public PronunciamientoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdPronunciamiento(){
		return this.idPronunciamiento;
	}
	
	public void setIdPronunciamiento(Long idPronunciamiento){
		this.idPronunciamiento = idPronunciamiento;
	}
	
	public String getPronunciamiento(){
		return this.pronunciamiento;
	}
	
	public void setPronunciamiento(String pronunciamiento){
		this.pronunciamiento = pronunciamiento;
	}
		
	public boolean getTieneRelacionConPartes(){
		return this.tieneRelacionConPartes;
	}
	
	public void setTieneRelacionConPartes(boolean tieneRelacionConPartes){
		this.tieneRelacionConPartes = tieneRelacionConPartes;
	}
		
	public boolean getMaximoDeTribunalesEntidadPublica(){
		return this.maximoDeTribunalesEntidadPublica;
	}
	
	public void setMaximoDeTribunalesEntidadPublica(boolean maximoDeTribunalesEntidadPublica){
		this.maximoDeTribunalesEntidadPublica = maximoDeTribunalesEntidadPublica;
	}
		
	public boolean getMaximoDeTribunalesEntidadPublicaLey1682(){
		return this.maximoDeTribunalesEntidadPublicaLey1682;
	}
	
	public void setMaximoDeTribunalesEntidadPublicaLey1682(boolean maximoDeTribunalesEntidadPublicaLey1682){
		this.maximoDeTribunalesEntidadPublicaLey1682 = maximoDeTribunalesEntidadPublicaLey1682;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public String getRevelaciones(){
		return this.revelaciones;
	}
	
	public void setRevelaciones(String revelaciones){
		this.revelaciones = revelaciones;
	}
		
	public String getMotivoDeclinacion(){
		return this.motivoDeclinacion;
	}
	
	public void setMotivoDeclinacion(String motivoDeclinacion){
		this.motivoDeclinacion = motivoDeclinacion;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
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
	


	public boolean isProcedimientoRecuperacionEmpresarial() {
		return procedimientoRecuperacionEmpresarial;
	}



	public void setProcedimientoRecuperacionEmpresarial(boolean procedimientoRecuperacionEmpresarial) {
		this.procedimientoRecuperacionEmpresarial = procedimientoRecuperacionEmpresarial;
	}



	public boolean isTramitesAdministrativos() {
		return tramitesAdministrativos;
	}



	public void setTramitesAdministrativos(boolean tramitesAdministrativos) {
		this.tramitesAdministrativos = tramitesAdministrativos;
	}



	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idPronunciamiento);        
        hash = 37 * hash + Objects.hashCode(this.pronunciamiento);
        hash = 37 * hash + (this.tieneRelacionConPartes ? 0 : 1);
        hash = 37 * hash + (this.maximoDeTribunalesEntidadPublica ? 0 : 1);
        hash = 37 * hash + (this.maximoDeTribunalesEntidadPublicaLey1682 ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.revelaciones);
        hash = 37 * hash + Objects.hashCode(this.motivoDeclinacion);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.procedimientoRecuperacionEmpresarial);
        hash = 37 * hash + Objects.hashCode(this.tramitesAdministrativos);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PronunciamientoDTO que se pasa
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
        final PronunciamientoDTO other = (PronunciamientoDTO) obj;
                
        if (!Objects.equals(this.idPronunciamiento, other.idPronunciamiento)) {
            return false;
        }
        
        if (!Objects.equals(this.pronunciamiento, other.pronunciamiento)) {
            return false;
        }
        
        if (!Objects.equals(this.tieneRelacionConPartes, other.tieneRelacionConPartes)) {
            return false;
        }
        
        if (!Objects.equals(this.maximoDeTribunalesEntidadPublica, other.maximoDeTribunalesEntidadPublica)) {
            return false;
        }
        
        if (!Objects.equals(this.maximoDeTribunalesEntidadPublicaLey1682, other.maximoDeTribunalesEntidadPublicaLey1682)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.revelaciones, other.revelaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.motivoDeclinacion, other.motivoDeclinacion)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.procedimientoRecuperacionEmpresarial, other.procedimientoRecuperacionEmpresarial)) {
            return false;
        }
        
        if (!Objects.equals(this.tramitesAdministrativos, other.tramitesAdministrativos)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
    
    @Override
	public PronunciamientoDTO transformarSinDependencias(Pronunciamiento obj) {
		PronunciamientoDTO pronunciamientoDTO = new PronunciamientoDTO();
		
		pronunciamientoDTO.setIdPronunciamiento(obj.getIdPronunciamiento());
		pronunciamientoDTO.setPronunciamiento(obj.getPronunciamiento());
		pronunciamientoDTO.setTieneRelacionConPartes(obj.getTieneRelacionConPartes());
		pronunciamientoDTO.setMaximoDeTribunalesEntidadPublica(obj.getMaximoDeTribunalesEntidadPublica());
		pronunciamientoDTO.setMaximoDeTribunalesEntidadPublicaLey1682(obj.getMaximoDeTribunalesEntidadPublicaLey1682());
		pronunciamientoDTO.setFecha(obj.getFecha());
		pronunciamientoDTO.setRevelaciones(obj.getRevelaciones());
		pronunciamientoDTO.setMotivoDeclinacion(obj.getMotivoDeclinacion());
		pronunciamientoDTO.setObservaciones(obj.getObservaciones());
		pronunciamientoDTO.setIdDocumento(obj.getIdDocumento());
		pronunciamientoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		pronunciamientoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		pronunciamientoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		pronunciamientoDTO.setProcedimientoRecuperacionEmpresarial(obj.isProcedimientoRecuperacionEmpresarial());
		pronunciamientoDTO.setTramitesAdministrativos(obj.isTramitesAdministrativos());
		
		return pronunciamientoDTO;
	}

	@Override
	public PronunciamientoDTO transformarConDependencias(Pronunciamiento obj) {
		PronunciamientoDTO pronunciamientoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones
		if(obj.getDocumento()!=null){
			pronunciamientoDTO.setDocumento(new DocumentoDTO().transformarSinDependencias(obj.getDocumento()));
		}		
		// protected region modificaciones transformarConDependencias end
		
		return pronunciamientoDTO;
	}

	@Override
	public Pronunciamiento transformarEntidadSinDependencias(Pronunciamiento obj) {
		Pronunciamiento pronunciamiento = new Pronunciamiento();
		
		pronunciamiento.setIdPronunciamiento(obj.getIdPronunciamiento());
		
		pronunciamiento.setPronunciamiento(obj.getPronunciamiento());
		pronunciamiento.setTieneRelacionConPartes(obj.getTieneRelacionConPartes());
		pronunciamiento.setMaximoDeTribunalesEntidadPublica(obj.getMaximoDeTribunalesEntidadPublica());
		pronunciamiento.setMaximoDeTribunalesEntidadPublicaLey1682(obj.getMaximoDeTribunalesEntidadPublicaLey1682());
		pronunciamiento.setFecha(obj.getFecha());
		pronunciamiento.setRevelaciones(obj.getRevelaciones());
		pronunciamiento.setMotivoDeclinacion(obj.getMotivoDeclinacion());
		pronunciamiento.setObservaciones(obj.getObservaciones());
		pronunciamiento.setIdDocumento(obj.getIdDocumento());
		pronunciamiento.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		pronunciamiento.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		pronunciamiento.setEstadoRegistro(obj.getEstadoRegistro());
		pronunciamiento.setProcedimientoRecuperacionEmpresarial(obj.isProcedimientoRecuperacionEmpresarial());
		pronunciamiento.setTramitesAdministrativos(obj.isTramitesAdministrativos());
		
		return pronunciamiento;
	}


	@Override
	public Pronunciamiento transformarEntidadConDependencias(Pronunciamiento obj) {
		Pronunciamiento pronunciamiento = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return pronunciamiento;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Pronunciamiento> coleccion) {
		List<PronunciamientoDTO> pronunciamientoDTOList = new ArrayList<>();
		for(Pronunciamiento c : coleccion)
			pronunciamientoDTOList.add(transformarConDependencias(c));
		return pronunciamientoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Pronunciamiento> coleccion) {
		List<PronunciamientoDTO> pronunciamientoDTOList = new ArrayList<>();
		for(Pronunciamiento c : coleccion)
			pronunciamientoDTOList.add(transformarSinDependencias(c));
		return pronunciamientoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Pronunciamiento> coleccion) {
		List<Pronunciamiento> pronunciamientoList = new ArrayList<>();
		for(Pronunciamiento c : coleccion)
			pronunciamientoList.add(transformarEntidadConDependencias(c));
		return pronunciamientoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Pronunciamiento> coleccion) {
		List<Pronunciamiento> pronunciamientoList = new ArrayList<>();
		for(Pronunciamiento c : coleccion)
			pronunciamientoList.add(transformarEntidadSinDependencias(c));
		return pronunciamientoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones


	public DocumentoDTO getDocumento() {
		return documento;
	}



	public void setDocumento(DocumentoDTO documento) {
		this.documento = documento;
	}
	
	public Pronunciamiento transformarEntidadSinDependencias(PronunciamientoDTO obj) {
		Pronunciamiento pronunciamiento = new Pronunciamiento();
		
		pronunciamiento.setIdPronunciamiento(obj.getIdPronunciamiento());
		
		pronunciamiento.setPronunciamiento(obj.getPronunciamiento());
		pronunciamiento.setTieneRelacionConPartes(obj.getTieneRelacionConPartes());
		pronunciamiento.setMaximoDeTribunalesEntidadPublica(obj.getMaximoDeTribunalesEntidadPublica());
		pronunciamiento.setMaximoDeTribunalesEntidadPublicaLey1682(obj.getMaximoDeTribunalesEntidadPublicaLey1682());
		pronunciamiento.setFecha(obj.getFecha());
		pronunciamiento.setRevelaciones(obj.getRevelaciones());
		pronunciamiento.setMotivoDeclinacion(obj.getMotivoDeclinacion());
		pronunciamiento.setObservaciones(obj.getObservaciones());
		pronunciamiento.setIdDocumento(obj.getIdDocumento());
		pronunciamiento.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		pronunciamiento.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		pronunciamiento.setEstadoRegistro(obj.getEstadoRegistro());
		pronunciamiento.setProcedimientoRecuperacionEmpresarial(obj.isProcedimientoRecuperacionEmpresarial());
		pronunciamiento.setTramitesAdministrativos(obj.isTramitesAdministrativos());
		
		return pronunciamiento;
	}
	
	public Pronunciamiento transformarEntidadConDocumento(PronunciamientoDTO obj) {
		Pronunciamiento pronunciamiento = transformarEntidadSinDependencias(obj);
		if(obj.getDocumento()!=null){
			pronunciamiento.setDocumento(new DocumentoDTO().transformarEntidadSinDependencias(obj.getDocumento()));
		}		
		
		return pronunciamiento;
	}

	// protected region metodos adicionales end

}
