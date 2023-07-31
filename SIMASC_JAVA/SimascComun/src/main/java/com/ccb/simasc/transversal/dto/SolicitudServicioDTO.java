package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.dto.formularios.CondicionesGeneralesDTO;
import com.ccb.simasc.transversal.entidades.SolicitudServicio;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad SolicitudServicioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class SolicitudServicioDTO extends IDTO<SolicitudServicio> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private Long idConciliador;
	private static final long serialVersionUID = -1757072874322509441L;

	// protected region atributos end
	private Long idSolicitudServicio;

	private String tipoCuantia;		
	private String cuantia;		
	private Date fechaCreacion;		
	private String hechos;		
	private String pretensiones;		
	private String inicioDeConflicto;		
	private String idLugarConflicto;		
	private String parteQueSolicitaServicio;		
	private Long idSede;		
	private Long idServicio;		
	private Long idMateria;		
	private Date fechaInicioAudiencia;		
	private Date fechaFinAudiencia;		
	private String tipoDeAudiencia;		
	private Long idOrdenDePago;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idAutorizacionTratamientoDatos;	
	private String tipoPeritaje;
	private String tipoTramite;
	private Long idCasoAnterior;
	private BigDecimal pagoMediacion;
	private boolean medidasCautelares;
	private String medidasCautelare;
	private boolean arbitrajeConsumo;
	private String tipoConflicto;
	private String enteroServicio;
	//Seccion Condiciones Generales insolvencia
	private CondicionesGeneralesDTO condicionesGeneralesDTO;
	
	
    public SolicitudServicioDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdSolicitudServicio(){
		return this.idSolicitudServicio;
	}
	
	public void setIdSolicitudServicio(Long idSolicitudServicio){
		this.idSolicitudServicio = idSolicitudServicio;
	}
	
	public String getTipoCuantia(){
		return this.tipoCuantia;
	}
	
	public void setTipoCuantia(String tipoCuantia){
		this.tipoCuantia = tipoCuantia;
	}
		
	public String getCuantia(){
		return this.cuantia;
	}
	
	public void setCuantia(String cuantia){
		this.cuantia = cuantia;
	}
		
	public Date getFechaCreacion(){
		return this.fechaCreacion;
	}
	
	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion = fechaCreacion;
	}
		
	public String getHechos(){
		return this.hechos;
	}
	
	public void setHechos(String hechos){
		this.hechos = hechos;
	}
		
	public String getPretensiones(){
		return this.pretensiones;
	}
	
	public void setPretensiones(String pretensiones){
		this.pretensiones = pretensiones;
	}
		
	public String getInicioDeConflicto(){
		return this.inicioDeConflicto;
	}
	
	public void setInicioDeConflicto(String inicioDeConflicto){
		this.inicioDeConflicto = inicioDeConflicto;
	}
		
	public String getIdLugarConflicto(){
		return this.idLugarConflicto;
	}
	
	public void setIdLugarConflicto(String idLugarConflicto){
		this.idLugarConflicto = idLugarConflicto;
	}
		
	public String getParteQueSolicitaServicio(){
		return this.parteQueSolicitaServicio;
	}
	
	public void setParteQueSolicitaServicio(String parteQueSolicitaServicio){
		this.parteQueSolicitaServicio = parteQueSolicitaServicio;
	}
		
	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
		
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
		
	public Date getFechaInicioAudiencia(){
		return this.fechaInicioAudiencia;
	}
	
	public void setFechaInicioAudiencia(Date fechaInicioAudiencia){
		this.fechaInicioAudiencia = fechaInicioAudiencia;
	}
		
	public Date getFechaFinAudiencia(){
		return this.fechaFinAudiencia;
	}
	
	public void setFechaFinAudiencia(Date fechaFinAudiencia){
		this.fechaFinAudiencia = fechaFinAudiencia;
	}
		
	public String getTipoDeAudiencia(){
		return this.tipoDeAudiencia;
	}
	
	public void setTipoDeAudiencia(String tipoDeAudiencia){
		this.tipoDeAudiencia = tipoDeAudiencia;
	}
		
	public Long getIdOrdenDePago(){
		return this.idOrdenDePago;
	}
	
	public void setIdOrdenDePago(Long idOrdenDePago){
		this.idOrdenDePago = idOrdenDePago;
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
		
	public Long getIdAutorizacionTratamientoDatos(){
		return this.idAutorizacionTratamientoDatos;
	}
	
	public void setIdAutorizacionTratamientoDatos(Long idAutorizacionTratamientoDatos){
		this.idAutorizacionTratamientoDatos = idAutorizacionTratamientoDatos;
	}
			
    public String getTipoTramite() {
		return tipoTramite;
	}

	public void setTipoTramite(String tipoTramite) {
		this.tipoTramite = tipoTramite;
	}

	public Long getIdCasoAnterior() {
		return idCasoAnterior;
	}

	public void setIdCasoAnterior(Long idCasoAnterior) {
		this.idCasoAnterior = idCasoAnterior;
	}

	public BigDecimal getPagoMediacion() {
		return pagoMediacion;
	}



	public void setPagoMediacion(BigDecimal pagoMediacion) {
		this.pagoMediacion = pagoMediacion;
	}



	public boolean isMedidasCautelares() {
		return medidasCautelares;
	}



	public void setMedidasCautelares(boolean medidasCautelares) {
		this.medidasCautelares = medidasCautelares;
	}



	public String getMedidasCautelare() {
		return medidasCautelare;
	}



	public void setMedidasCautelare(String medidasCautelare) {
		this.medidasCautelare = medidasCautelare;
	}
	
	public boolean isArbitrajeConsumo() {
		return arbitrajeConsumo;
	}

	public void setArbitrajeConsumo(boolean arbitrajeConsumo) {
		this.arbitrajeConsumo = arbitrajeConsumo;
	}



	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idSolicitudServicio);        
        hash = 37 * hash + Objects.hashCode(this.tipoCuantia);
        hash = 37 * hash + Objects.hashCode(this.cuantia);
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.hechos);
        hash = 37 * hash + Objects.hashCode(this.pretensiones);
        hash = 37 * hash + Objects.hashCode(this.inicioDeConflicto);
        hash = 37 * hash + Objects.hashCode(this.idLugarConflicto);
        hash = 37 * hash + Objects.hashCode(this.parteQueSolicitaServicio);
        hash = 37 * hash + Objects.hashCode(this.idSede);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.fechaInicioAudiencia);
        hash = 37 * hash + Objects.hashCode(this.fechaFinAudiencia);
        hash = 37 * hash + Objects.hashCode(this.tipoDeAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idOrdenDePago);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idAutorizacionTratamientoDatos);
        hash = 37 * hash + Objects.hashCode(this.tipoPeritaje);
        hash = 37 * hash + Objects.hashCode(this.tipoTramite);
        hash = 37 * hash + Objects.hashCode(this.idCasoAnterior);
        hash = 37 * hash + Objects.hashCode(this.pagoMediacion);
        hash = 37 * hash + Objects.hashCode(this.medidasCautelares);
        hash = 37 * hash + Objects.hashCode(this.arbitrajeConsumo);   
        hash = 37 * hash + Objects.hashCode(this.tipoConflicto);
        hash = 37 * hash + Objects.hashCode(this.enteroServicio); 
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad SolicitudServicioDTO que se pasa
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
        final SolicitudServicioDTO other = (SolicitudServicioDTO) obj;
                
        if (!Objects.equals(this.idSolicitudServicio, other.idSolicitudServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCuantia, other.tipoCuantia)) {
            return false;
        }
        
        if (!Objects.equals(this.cuantia, other.cuantia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
            return false;
        }
        
        if (!Objects.equals(this.hechos, other.hechos)) {
            return false;
        }
        
        if (!Objects.equals(this.pretensiones, other.pretensiones)) {
            return false;
        }
        
        if (!Objects.equals(this.inicioDeConflicto, other.inicioDeConflicto)) {
            return false;
        }
        
        if (!Objects.equals(this.idLugarConflicto, other.idLugarConflicto)) {
            return false;
        }
        
        if (!Objects.equals(this.parteQueSolicitaServicio, other.parteQueSolicitaServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.idSede, other.idSede)) {
            return false;
        }
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicioAudiencia, other.fechaInicioAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFinAudiencia, other.fechaFinAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDeAudiencia, other.tipoDeAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.idOrdenDePago, other.idOrdenDePago)) {
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
        if (!Objects.equals(this.tipoPeritaje, other.tipoPeritaje)) {
            return false;
        }
        if (!Objects.equals(this.tipoTramite, other.tipoTramite)) {
            return false;
        }
        if (!Objects.equals(this.idCasoAnterior, other.idCasoAnterior)) {
            return false;
        }
        if (!Objects.equals(this.pagoMediacion, other.pagoMediacion)) {
            return false;
        }
        if (!Objects.equals(this.medidasCautelares, other.medidasCautelares)) {
            return false;
        }
        
        if (!Objects.equals(this.arbitrajeConsumo, other.arbitrajeConsumo)) {
            return false;
        }
        if (!Objects.equals(this.tipoConflicto, other.tipoConflicto)) {
            return false;
        }
        if (!Objects.equals(this.enteroServicio, other.enteroServicio)) {
            return false;
        }
        
        return Objects.equals(this.idAutorizacionTratamientoDatos, other.idAutorizacionTratamientoDatos);
                
    }
    
    @Override
	public SolicitudServicioDTO transformarSinDependencias(SolicitudServicio obj) {
		SolicitudServicioDTO solicitudServicioDTO = new SolicitudServicioDTO();
		
		solicitudServicioDTO.setIdSolicitudServicio(obj.getIdSolicitudServicio());
		solicitudServicioDTO.setTipoCuantia(obj.getTipoCuantia());
		solicitudServicioDTO.setCuantia(obj.getCuantia());
		solicitudServicioDTO.setFechaCreacion(obj.getFechaCreacion());
		solicitudServicioDTO.setHechos(obj.getHechos());
		solicitudServicioDTO.setPretensiones(obj.getPretensiones());
		solicitudServicioDTO.setInicioDeConflicto(obj.getInicioDeConflicto());
		solicitudServicioDTO.setIdLugarConflicto(obj.getIdLugarConflicto());
		solicitudServicioDTO.setParteQueSolicitaServicio(obj.getParteQueSolicitaServicio());
		solicitudServicioDTO.setIdSede(obj.getIdSede());
		solicitudServicioDTO.setIdServicio(obj.getIdServicio());
		solicitudServicioDTO.setIdMateria(obj.getIdMateria());
		solicitudServicioDTO.setFechaInicioAudiencia(obj.getFechaInicioAudiencia());
		solicitudServicioDTO.setFechaFinAudiencia(obj.getFechaFinAudiencia());
		solicitudServicioDTO.setTipoDeAudiencia(obj.getTipoDeAudiencia());
		solicitudServicioDTO.setIdOrdenDePago(obj.getIdOrdenDePago());
		solicitudServicioDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		solicitudServicioDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		solicitudServicioDTO.setEstadoRegistro(obj.getEstadoRegistro());
		solicitudServicioDTO.setIdAutorizacionTratamientoDatos(obj.getIdAutorizacionTratamientoDatos());
		solicitudServicioDTO.setTipoPeritaje(obj.getTipoPeritaje());
		solicitudServicioDTO.setTipoTramite(obj.getTipoTramite());
		solicitudServicioDTO.setIdCasoAnterior(obj.getIdCasoAnterior());
		solicitudServicioDTO.setPagoMediacion(obj.getPagoMediacion());
		solicitudServicioDTO.setMedidasCautelares(obj.isMedidasCautelares());
		solicitudServicioDTO.setArbitrajeConsumo(obj.isArbitrajeConsumo());
		solicitudServicioDTO.setTipoConflicto(obj.getTipoConflicto());
		solicitudServicioDTO.setEnteroServicio(obj.getEnteroServicio());
		
		return solicitudServicioDTO;
	}

	@Override
	public SolicitudServicioDTO transformarConDependencias(SolicitudServicio obj) {
		SolicitudServicioDTO solicitudServicioDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return solicitudServicioDTO;
	}

	@Override
	public SolicitudServicio transformarEntidadSinDependencias(SolicitudServicio obj) {
		SolicitudServicio solicitudServicio = new SolicitudServicio();
		
		solicitudServicio.setIdSolicitudServicio(obj.getIdSolicitudServicio());
		
		solicitudServicio.setTipoCuantia(obj.getTipoCuantia());
		solicitudServicio.setCuantia(obj.getCuantia());
		solicitudServicio.setFechaCreacion(obj.getFechaCreacion());
		solicitudServicio.setHechos(obj.getHechos());
		solicitudServicio.setPretensiones(obj.getPretensiones());
		solicitudServicio.setInicioDeConflicto(obj.getInicioDeConflicto());
		solicitudServicio.setIdLugarConflicto(obj.getIdLugarConflicto());
		solicitudServicio.setParteQueSolicitaServicio(obj.getParteQueSolicitaServicio());
		solicitudServicio.setIdSede(obj.getIdSede());
		solicitudServicio.setIdServicio(obj.getIdServicio());
		solicitudServicio.setIdMateria(obj.getIdMateria());
		solicitudServicio.setFechaInicioAudiencia(obj.getFechaInicioAudiencia());
		solicitudServicio.setFechaFinAudiencia(obj.getFechaFinAudiencia());
		solicitudServicio.setTipoDeAudiencia(obj.getTipoDeAudiencia());
		solicitudServicio.setIdOrdenDePago(obj.getIdOrdenDePago());
		solicitudServicio.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		solicitudServicio.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		solicitudServicio.setEstadoRegistro(obj.getEstadoRegistro());
		solicitudServicio.setIdAutorizacionTratamientoDatos(obj.getIdAutorizacionTratamientoDatos());
		solicitudServicio.setTipoPeritaje(obj.getTipoPeritaje());
		solicitudServicio.setArbitrajeConsumo(obj.isArbitrajeConsumo());
		solicitudServicio.setTipoConflicto(obj.getTipoConflicto());
		solicitudServicio.setEnteroServicio(obj.getEnteroServicio());
		
		return solicitudServicio;
	}


	@Override
	public SolicitudServicio transformarEntidadConDependencias(SolicitudServicio obj) {
		SolicitudServicio solicitudServicio = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return solicitudServicio;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<SolicitudServicio> coleccion) {
		List<SolicitudServicioDTO> solicitudServicioDTOList = new ArrayList<>();
		for(SolicitudServicio c : coleccion)
			solicitudServicioDTOList.add(transformarConDependencias(c));
		return solicitudServicioDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<SolicitudServicio> coleccion) {
		List<SolicitudServicioDTO> solicitudServicioDTOList = new ArrayList<>();
		for(SolicitudServicio c : coleccion)
			solicitudServicioDTOList.add(transformarSinDependencias(c));
		return solicitudServicioDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<SolicitudServicio> coleccion) {
		List<SolicitudServicio> solicitudServicioList = new ArrayList<>();
		for(SolicitudServicio c : coleccion)
			solicitudServicioList.add(transformarEntidadConDependencias(c));
		return solicitudServicioList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<SolicitudServicio> coleccion) {
		List<SolicitudServicio> solicitudServicioList = new ArrayList<>();
		for(SolicitudServicio c : coleccion)
			solicitudServicioList.add(transformarEntidadSinDependencias(c));
		return solicitudServicioList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public Long getIdConciliador() {
		return idConciliador;
	}
	public void setIdConciliador(Long idConciliador) {
		this.idConciliador = idConciliador;
	}
	// protected region metodos adicionales end



	public String getTipoPeritaje() {
		return tipoPeritaje;
	}



	public void setTipoPeritaje(String tipoPeritaje) {
		this.tipoPeritaje = tipoPeritaje;
	}



	public CondicionesGeneralesDTO getCondicionesGeneralesDTO() {
		return condicionesGeneralesDTO;
	}



	public void setCondicionesGeneralesDTO(CondicionesGeneralesDTO condicionesGeneralesDTO) {
		this.condicionesGeneralesDTO = condicionesGeneralesDTO;
	}



	public String getTipoConflicto() {
		return tipoConflicto;
	}



	public void setTipoConflicto(String tipoConflicto) {
		this.tipoConflicto = tipoConflicto;
	}



	public String getEnteroServicio() {
		return enteroServicio;
	}



	public void setEnteroServicio(String enteroServicio) {
		this.enteroServicio = enteroServicio;
	}




}
