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

import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.entidades.Llamada;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad LlamadaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class LlamadaDTO extends IDTO<Llamada> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private Boolean modificoCorreo;
	private String TipoCaso;
	private String nombreCaso;
	private String parteNoNotificada;
	private String telefonoDestino;
	private List<GenericoDTO> listCorreosActualizados;
	private Long idAudiencia;
	// protected region atributos end
	private Long idLlamada;

	private String tipoLlamada;		
	private Date fecha;		
	private boolean contactado;		
	private String personaQueContesta;		
	private String observaciones;		
	private boolean confirmacion;		
	private boolean reenvioCorreo;		
	private Long idCorreoRolPersonaCaso;		
	private boolean confirmacionAsistencia;		
	private boolean dioInformacion;		
	private String cumplio;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private String usuarioLlamada;
	private String nombreRol;
	private Long idRol;		
	private Long idPersona;		
	private Long idCaso;		
	private Long idTelefono;		
	private Long idCartaPersona;
	
	private boolean soloRegistroLlamada;
	
    public LlamadaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdLlamada(){
		return this.idLlamada;
	}
	
	public void setIdLlamada(Long idLlamada){
		this.idLlamada = idLlamada;
	}
	
	public String getTipoLlamada(){
		return this.tipoLlamada;
	}
	
	public void setTipoLlamada(String tipoLlamada){
		this.tipoLlamada = tipoLlamada;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public boolean getContactado(){
		return this.contactado;
	}
	
	public void setContactado(boolean contactado){
		this.contactado = contactado;
	}
		
	public String getPersonaQueContesta(){
		return this.personaQueContesta;
	}
	
	public void setPersonaQueContesta(String personaQueContesta){
		this.personaQueContesta = personaQueContesta;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public boolean getConfirmacion(){
		return this.confirmacion;
	}
	
	public void setConfirmacion(boolean confirmacion){
		this.confirmacion = confirmacion;
	}
		
	public boolean getReenvioCorreo(){
		return this.reenvioCorreo;
	}
	
	public void setReenvioCorreo(boolean reenvioCorreo){
		this.reenvioCorreo = reenvioCorreo;
	}
		
	public Long getIdCorreoRolPersonaCaso(){
		return this.idCorreoRolPersonaCaso;
	}
	
	public void setIdCorreoRolPersonaCaso(Long idCorreoRolPersonaCaso){
		this.idCorreoRolPersonaCaso = idCorreoRolPersonaCaso;
	}
		
	public boolean getConfirmacionAsistencia(){
		return this.confirmacionAsistencia;
	}
	
	public void setConfirmacionAsistencia(boolean confirmacionAsistencia){
		this.confirmacionAsistencia = confirmacionAsistencia;
	}
		
	public boolean getDioInformacion(){
		return this.dioInformacion;
	}
	
	public void setDioInformacion(boolean dioInformacion){
		this.dioInformacion = dioInformacion;
	}
		
	public String getCumplio(){
		return this.cumplio;
	}
	
	public void setCumplio(String cumplio){
		this.cumplio = cumplio;
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
		
	public String getUsuarioLlamada(){
		return this.usuarioLlamada;
	}
	
	public void setUsuarioLlamada(String usuarioLlamada){
		this.usuarioLlamada = usuarioLlamada;
	}
		
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public Long getIdTelefono(){
		return this.idTelefono;
	}
	
	public void setIdTelefono(Long idTelefono){
		this.idTelefono = idTelefono;
	}
		
	public Long getIdCartaPersona(){
		return this.idCartaPersona;
	}
	
	public void setIdCartaPersona(Long idCartaPersona){
		this.idCartaPersona = idCartaPersona;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idLlamada);        
        hash = 37 * hash + Objects.hashCode(this.tipoLlamada);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + (this.contactado ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.personaQueContesta);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + (this.confirmacion ? 0 : 1);
        hash = 37 * hash + (this.reenvioCorreo ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idCorreoRolPersonaCaso);
        hash = 37 * hash + (this.confirmacionAsistencia ? 0 : 1);
        hash = 37 * hash + (this.dioInformacion ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.cumplio);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.usuarioLlamada);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idTelefono);
        hash = 37 * hash + Objects.hashCode(this.idCartaPersona);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad LlamadaDTO que se pasa
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
        final LlamadaDTO other = (LlamadaDTO) obj;
                
        if (!Objects.equals(this.idLlamada, other.idLlamada)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoLlamada, other.tipoLlamada)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.contactado, other.contactado)) {
            return false;
        }
        
        if (!Objects.equals(this.personaQueContesta, other.personaQueContesta)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.confirmacion, other.confirmacion)) {
            return false;
        }
        
        if (!Objects.equals(this.reenvioCorreo, other.reenvioCorreo)) {
            return false;
        }
        
        if (!Objects.equals(this.idCorreoRolPersonaCaso, other.idCorreoRolPersonaCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.confirmacionAsistencia, other.confirmacionAsistencia)) {
            return false;
        }
        
        if (!Objects.equals(this.dioInformacion, other.dioInformacion)) {
            return false;
        }
        
        if (!Objects.equals(this.cumplio, other.cumplio)) {
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
        
        if (!Objects.equals(this.usuarioLlamada, other.usuarioLlamada)) {
            return false;
        }
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.idTelefono, other.idTelefono)) {
            return false;
        }
        
        return Objects.equals(this.idCartaPersona, other.idCartaPersona);
                
    }
    
    @Override
	public LlamadaDTO transformarSinDependencias(Llamada obj) {
		LlamadaDTO llamadaDTO = new LlamadaDTO();
		
		llamadaDTO.setIdLlamada(obj.getIdLlamada());
		llamadaDTO.setTipoLlamada(obj.getTipoLlamada());
		llamadaDTO.setFecha(obj.getFecha());
		llamadaDTO.setContactado(obj.getContactado());
		llamadaDTO.setPersonaQueContesta(obj.getPersonaQueContesta());
		llamadaDTO.setObservaciones(obj.getObservaciones());
		llamadaDTO.setConfirmacion(obj.getConfirmacion());
		llamadaDTO.setReenvioCorreo(obj.getReenvioCorreo());
		llamadaDTO.setIdCorreoRolPersonaCaso(obj.getIdCorreoRolPersonaCaso());
		llamadaDTO.setConfirmacionAsistencia(obj.getConfirmacionAsistencia());
		llamadaDTO.setDioInformacion(obj.getDioInformacion());
		llamadaDTO.setCumplio(obj.getCumplio());
		llamadaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		llamadaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		llamadaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		llamadaDTO.setUsuarioLlamada(obj.getUsuarioLlamada());
		llamadaDTO.setIdRol(obj.getIdRol());
		llamadaDTO.setIdPersona(obj.getIdPersona());
		llamadaDTO.setIdCaso(obj.getIdCaso());
		llamadaDTO.setIdTelefono(obj.getIdTelefono());
		llamadaDTO.setIdCartaPersona(obj.getIdCartaPersona());
		
		return llamadaDTO;
	}

	@Override
	public LlamadaDTO transformarConDependencias(Llamada obj) {
		LlamadaDTO llamadaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return llamadaDTO;
	}

	@Override
	public Llamada transformarEntidadSinDependencias(Llamada obj) {
		Llamada llamada = new Llamada();
		
		llamada.setIdLlamada(obj.getIdLlamada());
		
		llamada.setTipoLlamada(obj.getTipoLlamada());
		llamada.setFecha(obj.getFecha());
		llamada.setContactado(obj.getContactado());
		llamada.setPersonaQueContesta(obj.getPersonaQueContesta());
		llamada.setObservaciones(obj.getObservaciones());
		llamada.setConfirmacion(obj.getConfirmacion());
		llamada.setReenvioCorreo(obj.getReenvioCorreo());
		llamada.setIdCorreoRolPersonaCaso(obj.getIdCorreoRolPersonaCaso());
		llamada.setConfirmacionAsistencia(obj.getConfirmacionAsistencia());
		llamada.setDioInformacion(obj.getDioInformacion());
		llamada.setCumplio(obj.getCumplio());
		llamada.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		llamada.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		llamada.setEstadoRegistro(obj.getEstadoRegistro());
		llamada.setUsuarioLlamada(obj.getUsuarioLlamada());
		llamada.setIdRol(obj.getIdRol());
		llamada.setIdPersona(obj.getIdPersona());
		llamada.setIdCaso(obj.getIdCaso());
		llamada.setIdTelefono(obj.getIdTelefono());
		llamada.setIdCartaPersona(obj.getIdCartaPersona());
		
		return llamada;
	}


	@Override
	public Llamada transformarEntidadConDependencias(Llamada obj) {
		Llamada llamada = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return llamada;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Llamada> coleccion) {
		List<LlamadaDTO> llamadaDTOList = new ArrayList<>();
		for(Llamada c : coleccion)
			llamadaDTOList.add(transformarConDependencias(c));
		return llamadaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Llamada> coleccion) {
		List<LlamadaDTO> llamadaDTOList = new ArrayList<>();
		for(Llamada c : coleccion)
			llamadaDTOList.add(transformarSinDependencias(c));
		return llamadaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Llamada> coleccion) {
		List<Llamada> llamadaList = new ArrayList<>();
		for(Llamada c : coleccion)
			llamadaList.add(transformarEntidadConDependencias(c));
		return llamadaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Llamada> coleccion) {
		List<Llamada> llamadaList = new ArrayList<>();
		for(Llamada c : coleccion)
			llamadaList.add(transformarEntidadSinDependencias(c));
		return llamadaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public Boolean getModificoCorreo() {
		return modificoCorreo;
	}
	public void setModificoCorreo(Boolean modificoCorreo) {
		this.modificoCorreo = modificoCorreo;
	}
	public Long getIdAudiencia() {
		return idAudiencia;
	}
	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}
	public String getTipoCaso() {
		return TipoCaso;
	}
	public void setTipoCaso(String tipoCaso) {
		TipoCaso = tipoCaso;
	}
	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public String getParteNoNotificada() {
		return parteNoNotificada;
	}
	public void setParteNoNotificada(String parteNoNotificada) {
		this.parteNoNotificada = parteNoNotificada;
	}
	public String getTelefonoDestino() {
		return telefonoDestino;
	}
	public void setTelefonoDestino(String telefonoDestino) {
		this.telefonoDestino = telefonoDestino;
	}
	public List<GenericoDTO> getListCorreosActualizados() {
		return listCorreosActualizados;
	}
	public void setListCorreosActualizados(List<GenericoDTO> listCorreosActualizados) {
		this.listCorreosActualizados = listCorreosActualizados;
	}



	public boolean isSoloRegistroLlamada() {
		return soloRegistroLlamada;
	}



	public void setSoloRegistroLlamada(boolean soloRegistroLlamada) {
		this.soloRegistroLlamada = soloRegistroLlamada;
	}
	
	// protected region metodos adicionales end

}
