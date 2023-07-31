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

import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Ubicacion;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad CartaPersonaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class CartaPersonaDTO extends IDTO<CartaPersona> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idCartaPersona;

	private Long idPlantillaCarta;		
	private boolean envioCertimail;		
	private String contenido;		
	private String estadoCarta;		
	private Long idCaso;		
	private String asunto;		
	private String correoElectronico;		
	private String direccionCorrespondencia;		
	private String ciudadCorrespondencia;		
	private String telefonosContacto;		
	private Date fechaEnvio;		
	private Date fechaGeneracion;		
	private Long numeroDeRadicado;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idDocumento;		
	private Long idPersona;		
	private Long idInvitado;	
	private Long idAudiencia;		
	private Long idSede;		
	private String numeroGuia;		
	private Date fechaDevolucion;
	
    public CartaPersonaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdCartaPersona(){
		return this.idCartaPersona;
	}
	
	public void setIdCartaPersona(Long idCartaPersona){
		this.idCartaPersona = idCartaPersona;
	}
	
	public Long getIdPlantillaCarta(){
		return this.idPlantillaCarta;
	}
	
	public void setIdPlantillaCarta(Long idPlantillaCarta){
		this.idPlantillaCarta = idPlantillaCarta;
	}
		
	public boolean getEnvioCertimail(){
		return this.envioCertimail;
	}
	
	public void setEnvioCertimail(boolean envioCertimail){
		this.envioCertimail = envioCertimail;
	}
		
	public String getContenido(){
		return this.contenido;
	}
	
	public void setContenido(String contenido){
		this.contenido = contenido;
	}
		
	public String getEstadoCarta(){
		return this.estadoCarta;
	}
	
	public void setEstadoCarta(String estadoCarta){
		this.estadoCarta = estadoCarta;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public String getAsunto(){
		return this.asunto;
	}
	
	public void setAsunto(String asunto){
		this.asunto = asunto;
	}
		
	public String getCorreoElectronico(){
		return this.correoElectronico;
	}
	
	public void setCorreoElectronico(String correoElectronico){
		this.correoElectronico = correoElectronico;
	}
		
	public String getDireccionCorrespondencia(){
		return this.direccionCorrespondencia;
	}
	
	public void setDireccionCorrespondencia(String direccionCorrespondencia){
		this.direccionCorrespondencia = direccionCorrespondencia;
	}
		
	public String getCiudadCorrespondencia(){
		return this.ciudadCorrespondencia;
	}
	
	public void setCiudadCorrespondencia(String ciudadCorrespondencia){
		this.ciudadCorrespondencia = ciudadCorrespondencia;
	}
		
	public String getTelefonosContacto(){
		return this.telefonosContacto;
	}
	
	public void setTelefonosContacto(String telefonosContacto){
		this.telefonosContacto = telefonosContacto;
	}
		
	public Date getFechaEnvio(){
		return this.fechaEnvio;
	}
	
	public void setFechaEnvio(Date fechaEnvio){
		this.fechaEnvio = fechaEnvio;
	}
		
	public Date getFechaGeneracion(){
		return this.fechaGeneracion;
	}
	
	public void setFechaGeneracion(Date fechaGeneracion){
		this.fechaGeneracion = fechaGeneracion;
	}
		
	public Long getNumeroDeRadicado(){
		return this.numeroDeRadicado;
	}
	
	public void setNumeroDeRadicado(Long numeroDeRadicado){
		this.numeroDeRadicado = numeroDeRadicado;
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
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdInvitado(){
		return this.idInvitado;
	}
	
	public void setIdInvitado(Long idInvitado){
		this.idInvitado = idInvitado;
	}
		
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
		
	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
		
	public String getNumeroGuia(){
		return this.numeroGuia;
	}
	
	public void setNumeroGuia(String numeroGuia){
		this.numeroGuia = numeroGuia;
	}
	
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idCartaPersona);        
        hash = 37 * hash + Objects.hashCode(this.idPlantillaCarta);
        hash = 37 * hash + (this.envioCertimail ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.contenido);
        hash = 37 * hash + Objects.hashCode(this.estadoCarta);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.asunto);
        hash = 37 * hash + Objects.hashCode(this.correoElectronico);
        hash = 37 * hash + Objects.hashCode(this.direccionCorrespondencia);
        hash = 37 * hash + Objects.hashCode(this.ciudadCorrespondencia);
        hash = 37 * hash + Objects.hashCode(this.telefonosContacto);
        hash = 37 * hash + Objects.hashCode(this.fechaEnvio);
        hash = 37 * hash + Objects.hashCode(this.fechaGeneracion);
        hash = 37 * hash + Objects.hashCode(this.numeroDeRadicado);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idInvitado);
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idSede);
        hash = 37 * hash + Objects.hashCode(this.numeroGuia);
        hash = 37 * hash + Objects.hashCode(this.fechaDevolucion);    

        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CartaPersonaDTO que se pasa
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
        final CartaPersonaDTO other = (CartaPersonaDTO) obj;
                
        if (!Objects.equals(this.idCartaPersona, other.idCartaPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idPlantillaCarta, other.idPlantillaCarta)) {
            return false;
        }
        
        if (!Objects.equals(this.envioCertimail, other.envioCertimail)) {
            return false;
        }
        
        if (!Objects.equals(this.contenido, other.contenido)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoCarta, other.estadoCarta)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.asunto, other.asunto)) {
            return false;
        }
        
        if (!Objects.equals(this.correoElectronico, other.correoElectronico)) {
            return false;
        }
        
        if (!Objects.equals(this.direccionCorrespondencia, other.direccionCorrespondencia)) {
            return false;
        }
        
        if (!Objects.equals(this.ciudadCorrespondencia, other.ciudadCorrespondencia)) {
            return false;
        }
        
        if (!Objects.equals(this.telefonosContacto, other.telefonosContacto)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaEnvio, other.fechaEnvio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaGeneracion, other.fechaGeneracion)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroDeRadicado, other.numeroDeRadicado)) {
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
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idInvitado, other.idInvitado)) {
            return false;
        }
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.idSede, other.idSede)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDevolucion, other.fechaDevolucion)) {
            return false;
        }
        
        
        
        return Objects.equals(this.numeroGuia, other.numeroGuia);
                
    }
    
    @Override
	public CartaPersonaDTO transformarSinDependencias(CartaPersona obj) {
		CartaPersonaDTO cartaPersonaDTO = new CartaPersonaDTO();
		
		cartaPersonaDTO.setIdCartaPersona(obj.getIdCartaPersona());
		cartaPersonaDTO.setIdPlantillaCarta(obj.getIdPlantillaCarta());
		cartaPersonaDTO.setEnvioCertimail(obj.getEnvioCertimail());
		cartaPersonaDTO.setContenido(obj.getContenido());
		cartaPersonaDTO.setEstadoCarta(obj.getEstadoCarta());
		cartaPersonaDTO.setIdCaso(obj.getIdCaso());
		cartaPersonaDTO.setAsunto(obj.getAsunto());
		cartaPersonaDTO.setCorreoElectronico(obj.getCorreoElectronico());
		cartaPersonaDTO.setDireccionCorrespondencia(obj.getDireccionCorrespondencia());
		cartaPersonaDTO.setCiudadCorrespondencia(obj.getCiudadCorrespondencia());
		cartaPersonaDTO.setTelefonosContacto(obj.getTelefonosContacto());
		cartaPersonaDTO.setFechaEnvio(obj.getFechaEnvio());
		cartaPersonaDTO.setFechaGeneracion(obj.getFechaGeneracion());
		cartaPersonaDTO.setNumeroDeRadicado(obj.getNumeroDeRadicado());
		cartaPersonaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		cartaPersonaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		cartaPersonaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		cartaPersonaDTO.setIdDocumento(obj.getIdDocumento());
		cartaPersonaDTO.setIdPersona(obj.getIdPersona());
		cartaPersonaDTO.setIdInvitado(obj.getIdInvitado());
		cartaPersonaDTO.setIdAudiencia(obj.getIdAudiencia());
		cartaPersonaDTO.setIdSede(obj.getIdSede());
		cartaPersonaDTO.setNumeroGuia(obj.getNumeroGuia());
		cartaPersonaDTO.setFechaDevolucion(obj.getFechaDevolucion());
		
		return cartaPersonaDTO;
	}

	@Override
	public CartaPersonaDTO transformarConDependencias(CartaPersona obj) {
		CartaPersonaDTO cartaPersonaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return cartaPersonaDTO;
	}

	@Override
	public CartaPersona transformarEntidadSinDependencias(CartaPersona obj) {
		CartaPersona cartaPersona = new CartaPersona();
		
		cartaPersona.setIdCartaPersona(obj.getIdCartaPersona());
		
		cartaPersona.setIdPlantillaCarta(obj.getIdPlantillaCarta());
		cartaPersona.setEnvioCertimail(obj.getEnvioCertimail());
		cartaPersona.setContenido(obj.getContenido());
		cartaPersona.setEstadoCarta(obj.getEstadoCarta());
		cartaPersona.setIdCaso(obj.getIdCaso());
		cartaPersona.setAsunto(obj.getAsunto());
		cartaPersona.setCorreoElectronico(obj.getCorreoElectronico());
		cartaPersona.setDireccionCorrespondencia(obj.getDireccionCorrespondencia());
		cartaPersona.setCiudadCorrespondencia(obj.getCiudadCorrespondencia());
		cartaPersona.setTelefonosContacto(obj.getTelefonosContacto());
		cartaPersona.setFechaEnvio(obj.getFechaEnvio());
		cartaPersona.setFechaGeneracion(obj.getFechaGeneracion());
		cartaPersona.setNumeroDeRadicado(obj.getNumeroDeRadicado());
		cartaPersona.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		cartaPersona.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		cartaPersona.setEstadoRegistro(obj.getEstadoRegistro());
		cartaPersona.setIdDocumento(obj.getIdDocumento());
		cartaPersona.setIdPersona(obj.getIdPersona());
		cartaPersona.setIdInvitado(obj.getIdInvitado());
		cartaPersona.setIdAudiencia(obj.getIdAudiencia());
		cartaPersona.setIdSede(obj.getIdSede());
		cartaPersona.setNumeroGuia(obj.getNumeroGuia());
		cartaPersona.setFechaDevolucion(obj.getFechaDevolucion());
		
		return cartaPersona;
	}


	@Override
	public CartaPersona transformarEntidadConDependencias(CartaPersona obj) {
		CartaPersona cartaPersona = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		if(obj.getPersona()!=null){
			Persona persona=new Persona();
			persona.setPrimerNombreORazonSocial(obj.getPersona().getPrimerNombreORazonSocial());
			persona.setSegundoNombre(obj.getPersona().getSegundoNombre());
			persona.setPrimerApellido(obj.getPersona().getPrimerApellido());
			persona.setSegundoApellido(obj.getPersona().getSegundoApellido());
			UbicacionDTO dto = new UbicacionDTO();
			persona.setUbicacionList((List<Ubicacion>)dto.transformarColeccionEntidadesSinDependencias(obj.getPersona().getUbicacionList()));
			cartaPersona.setPersona(persona);
		}
		if(obj.getInvitado()!=null){
			cartaPersona.setInvitado(new InvitadoDTO().transformarEntidadSinDependencias(obj.getInvitado()));
		}
		// protected region modificaciones transformarEntidadConDependencias end
		
		return cartaPersona;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<CartaPersona> coleccion) {
		List<CartaPersonaDTO> cartaPersonaDTOList = new ArrayList<>();
		for(CartaPersona c : coleccion)
			cartaPersonaDTOList.add(transformarConDependencias(c));
		return cartaPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<CartaPersona> coleccion) {
		List<CartaPersonaDTO> cartaPersonaDTOList = new ArrayList<>();
		for(CartaPersona c : coleccion)
			cartaPersonaDTOList.add(transformarSinDependencias(c));
		return cartaPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<CartaPersona> coleccion) {
		List<CartaPersona> cartaPersonaList = new ArrayList<>();
		for(CartaPersona c : coleccion)
			cartaPersonaList.add(transformarEntidadConDependencias(c));
		return cartaPersonaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<CartaPersona> coleccion) {
		List<CartaPersona> cartaPersonaList = new ArrayList<>();
		for(CartaPersona c : coleccion)
			cartaPersonaList.add(transformarEntidadSinDependencias(c));
		return cartaPersonaList;
	}




	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
