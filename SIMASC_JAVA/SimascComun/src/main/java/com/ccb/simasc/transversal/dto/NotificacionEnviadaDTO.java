package com.ccb.simasc.transversal.dto;

import java.util.Date;

import com.ccb.simasc.transversal.entidades.CartaPersona;

/**
 * DTO que retorna las notificaciones enviadas por correo
 * @author ymoreno
 *
 */
public class NotificacionEnviadaDTO {

    private Long idCorreoRolPersonaCaso;
	private Date fechaEnvio;
    private String nombreParte;
    private String correo;
    private Boolean mensaje;
    private Boolean acuseEnvio;
    private Boolean acuseRecibido;
    private Boolean acuseLectura;
    private Boolean fallaEntrega;
    private Boolean respuesta;
    private CartaPersona cartaPersona;
    private Long numeroAudiencias;
    
	public CartaPersona getCartaPersona() {
		return cartaPersona;
	}
	public void setCartaPersona(CartaPersona cartaPersona) {
		this.cartaPersona = cartaPersona;
	}
	public Date getFechaEnvio() {
		return fechaEnvio;
	}
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	public String getNombreParte() {
		return nombreParte;
	}
	public void setNombreParte(String nombreParte) {
		this.nombreParte = nombreParte;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Boolean getMensaje() {
		return mensaje;
	}
	public void setMensaje(Boolean mensaje) {
		this.mensaje = mensaje;
	}
	public Boolean getAcuseEnvio() {
		return acuseEnvio;
	}
	public void setAcuseEnvio(Boolean acuseEnvio) {
		this.acuseEnvio = acuseEnvio;
	}
	public Boolean getAcuseRecibido() {
		return acuseRecibido;
	}
	public void setAcuseRecibido(Boolean acuseRecibido) {
		this.acuseRecibido = acuseRecibido;
	}
	public Boolean getAcuseLectura() {
		return acuseLectura;
	}
	public void setAcuseLectura(Boolean acuseLectura) {
		this.acuseLectura = acuseLectura;
	}
	public Boolean getFallaEntrega() {
		return fallaEntrega;
	}
	public void setFallaEntrega(Boolean fallaEntrega) {
		this.fallaEntrega = fallaEntrega;
	}
	public Boolean getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Boolean respuesta) {
		this.respuesta = respuesta;
	}
	public Long getIdCorreoRolPersonaCaso() {
		return idCorreoRolPersonaCaso;
	}
	public void setIdCorreoRolPersonaCaso(Long idCorreoRolPersonaCaso) {
		this.idCorreoRolPersonaCaso = idCorreoRolPersonaCaso;
	}
	public Long getNumeroAudiencias() {
		return numeroAudiencias;
	}
	public void setNumeroAudiencias(Long numeroAudiencias) {
		this.numeroAudiencias = numeroAudiencias;
	}
	
    
}
