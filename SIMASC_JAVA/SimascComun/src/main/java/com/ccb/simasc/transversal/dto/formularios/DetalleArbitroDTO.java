package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.dto.RecusacionDTO;
import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Pronunciamiento;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;

/**
 * DAO que contiene la información del formulario para la creacion de parte representante sea
 * abogado demandante o abogado demandado.
 * Este formulario esta construido para los servicios REST
 * 
 * @author dpachon
 */
@XmlRootElement
public class DetalleArbitroDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Persona persona;
	private String nombreCompleto;
	private RolPersonaCaso rolPersonaCaso;
	private EventoRolPersonaCaso eventoDesignacion;
	private EventoRolPersonaCaso eventoComunicacion;
	private EventoRolPersonaCaso eventoPronunciamiento;
	private Pronunciamiento pronunciamiento;
	private ArrayList<RecusacionDTO> recusacion;
	private boolean cumplePlazoPronunciamiento;
	private boolean muestraPlazoPronunciamiento;
	private boolean recusado;
	//private Pronunciamiento pronunciamiento;
	//private List<Recusacion> recusaciones;
	private ZonaGeografica pais;
	private ZonaGeografica ciudad;
	private String direccion;
	private String numeroTelefono;
	private String numeroCelular;
	private String emailUno;
	private String emailDos;
	private String rolDescripcion;
	
	
	
	public boolean getMuestraPlazoPronunciamiento() {
		return muestraPlazoPronunciamiento;
	}
	public void setMuestraPlazoPronunciamiento(boolean muestraPlazoPronunciamiento) {
		this.muestraPlazoPronunciamiento = muestraPlazoPronunciamiento;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public RolPersonaCaso getRolPersonaCaso() {
		return rolPersonaCaso;
	}
	public void setRolPersonaCaso(RolPersonaCaso rolPersonaCaso) {
		this.rolPersonaCaso = rolPersonaCaso;
	}

	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public ZonaGeografica getPais() {
		return pais;
	}
	public void setPais(ZonaGeografica pais) {
		this.pais = pais;
	}
	public ZonaGeografica getCiudad() {
		return ciudad;
	}
	public void setCiudad(ZonaGeografica ciudad) {
		this.ciudad = ciudad;
	}
	public String getNumeroTelefonoUno() {
		return numeroTelefono;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}
	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	public String getEmailUno() {
		return emailUno;
	}
	public void setEmailUno(String emailUno) {
		this.emailUno = emailUno;
	}
	public String getEmailDos() {
		return emailDos;
	}
	public void setEmailDos(String emailDos) {
		this.emailDos = emailDos;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public EventoRolPersonaCaso getEventoDesignacion() {
		return eventoDesignacion;
	}
	public void setEventoDesignacion(EventoRolPersonaCaso eventoDesignacion) {
		this.eventoDesignacion = eventoDesignacion;
	}
	public EventoRolPersonaCaso getEventoComunicacion() {
		return eventoComunicacion;
	}
	public void setEventoComunicacion(EventoRolPersonaCaso eventoComunicacion) {
		this.eventoComunicacion = eventoComunicacion;
	}
	public boolean isCumplePlazoPronunciamiento() {
		return cumplePlazoPronunciamiento;
	}
	public void setCumplePlazoPronunciamiento(boolean cumplePlazoPronunciamiento) {
		this.cumplePlazoPronunciamiento = cumplePlazoPronunciamiento;
	}
	public boolean isRecusado() {
		return recusado;
	}
	public void setRecusado(boolean recusado) {
		this.recusado = recusado;
	}
	public Pronunciamiento getPronunciamiento() {
		return pronunciamiento;
	}
	public void setPronunciamiento(Pronunciamiento pronunciamiento) {
		this.pronunciamiento = pronunciamiento;
	}
	public ArrayList<RecusacionDTO> getRecusacion() {
		return recusacion;
	}
	public void setRecusacion(ArrayList<RecusacionDTO> recusacion) {
		this.recusacion = recusacion;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	/**
	 * @return the eventoPronunciamiento
	 */
	public EventoRolPersonaCaso getEventoPronunciamiento() {
		return eventoPronunciamiento;
	}
	/**
	 * @param eventoPronunciamiento the eventoPronunciamiento to set
	 */
	public void setEventoPronunciamiento(EventoRolPersonaCaso eventoPronunciamiento) {
		this.eventoPronunciamiento = eventoPronunciamiento;
	}
	public String getRolDescripcion() {
		return rolDescripcion;
	}
	public void setRolDescripcion(String rolDescripcion) {
		this.rolDescripcion = rolDescripcion;
	}
	
	
			
}
