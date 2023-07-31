package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;

/**
 * DTO para el guardar los datos de la persona de SIREP
 * 
 * @author fguzman
 */
public class FormularioDatosClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/*Inicio datos Basicos*/
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String tipoIdentificacion;
    private String descTipoIdentificacion;
	private String numeroIdentificacion;
	private String numeroTelefonoEnmascarado;
	private String numeroTelefono;
	private Long idTelefono;
	private ZonaGeografica ciudadIdentificacion;
	private String direccionEnmascarado;
	private String direccion;
	private Long idDireccion;
	private String pais;
	private String ciudad;
	private String emailEnmascarado;
	private String email;
	private Long idEmail;
	private String numCliente;
	
	/*Fin datos basicos*/
	
	private Long idPersona;
	private Integer tipoCliente;
	private String digitoVerificacion;
	private String numRut;
	private Integer ctrCopropiedad;
	private String ctrPropMatriculado;
	private String fechaExp;
    protected String idPaisDoc;
    protected String idNacionalidad;
    
	//Datos de direccion
	private String idZonaPostal;
	private String idZonaGeogrfica;
	private String numeroTel2;
	private String numeroFax;
	private String numeroAa;
	private String dirURL;
	private String numeroMovil;
	private Integer ctrMensajes;
	private String barrioTextual;
	private String idBarrio;
	private Integer ctrMensajesMail;
	
	/**
	 * @return the numCliente
	 */
	public String getNumCliente() {
		return numCliente;
	}

	/**
	 * @param numCliente the numCliente to set
	 */
	public void setNumCliente(String numCliente) {
		this.numCliente = numCliente;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNumeroTelefonoEnmascarado() {
		return numeroTelefonoEnmascarado;
	}

	public void setNumeroTelefonoEnmascarado(String numeroTelefonoEnmascarado) {
		this.numeroTelefonoEnmascarado = numeroTelefonoEnmascarado;
	}

	public ZonaGeografica getCiudadIdentificacion() {
		return ciudadIdentificacion;
	}

	public void setCiudadIdentificacion(ZonaGeografica ciudadIdentificacion) {
		this.ciudadIdentificacion = ciudadIdentificacion;
	}

	public String getDireccionEnmascarado() {
		return direccionEnmascarado;
	}

	public void setDireccionEnmascarado(String direccionEnmascarado) {
		this.direccionEnmascarado = direccionEnmascarado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEmailEnmascarado() {
		return emailEnmascarado;
	}

	public void setEmailEnmascarado(String emailEnmascarado) {
		this.emailEnmascarado = emailEnmascarado;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	
	public String getDescTipoIdentificacion() {
		return descTipoIdentificacion;
	}

	public void setDescTipoIdentificacion(String descTipoIdentificacion) {
		this.descTipoIdentificacion = descTipoIdentificacion;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getDigitoVerificacion() {
		return digitoVerificacion;
	}

	public void setDigitoVerificacion(String digitoVerificacion) {
		this.digitoVerificacion = digitoVerificacion;
	}

	public String getNumRut() {
		return numRut;
	}

	public void setNumRut(String numRut) {
		this.numRut = numRut;
	}

	public Integer getCtrCopropiedad() {
		return ctrCopropiedad;
	}

	public void setCtrCopropiedad(Integer ctrCopropiedad) {
		this.ctrCopropiedad = ctrCopropiedad;
	}

	public String getCtrPropMatriculado() {
		return ctrPropMatriculado;
	}

	public void setCtrPropMatriculado(String ctrPropMatriculado) {
		this.ctrPropMatriculado = ctrPropMatriculado;
	}

	public String getFechaExp() {
		return fechaExp;
	}

	public void setFechaExp(String fechaExp) {
		this.fechaExp = fechaExp;
	}

	public String getIdPaisDoc() {
		return idPaisDoc;
	}

	public void setIdPaisDoc(String idPaisDoc) {
		this.idPaisDoc = idPaisDoc;
	}

	public String getIdNacionalidad() {
		return idNacionalidad;
	}

	public void setIdNacionalidad(String idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}

	public String getIdZonaPostal() {
		return idZonaPostal;
	}

	public void setIdZonaPostal(String idZonaPostal) {
		this.idZonaPostal = idZonaPostal;
	}

	public String getIdZonaGeogrfica() {
		return idZonaGeogrfica;
	}

	public void setIdZonaGeogrfica(String idZonaGeogrfica) {
		this.idZonaGeogrfica = idZonaGeogrfica;
	}

	public String getNumeroTel2() {
		return numeroTel2;
	}

	public void setNumeroTel2(String numeroTel2) {
		this.numeroTel2 = numeroTel2;
	}

	public String getNumeroFax() {
		return numeroFax;
	}

	public void setNumeroFax(String numeroFax) {
		this.numeroFax = numeroFax;
	}

	public String getNumeroAa() {
		return numeroAa;
	}

	public void setNumeroAa(String numeroAa) {
		this.numeroAa = numeroAa;
	}

	public String getDirURL() {
		return dirURL;
	}

	public void setDirURL(String dirURL) {
		this.dirURL = dirURL;
	}

	public String getNumeroMovil() {
		return numeroMovil;
	}

	public void setNumeroMovil(String numeroMovil) {
		this.numeroMovil = numeroMovil;
	}

	public Integer getCtrMensajes() {
		return ctrMensajes;
	}

	public void setCtrMensajes(Integer ctrMensajes) {
		this.ctrMensajes = ctrMensajes;
	}

	public String getBarrioTextual() {
		return barrioTextual;
	}

	public void setBarrioTextual(String barrioTextual) {
		this.barrioTextual = barrioTextual;
	}

	public String getIdBarrio() {
		return idBarrio;
	}

	public void setIdBarrio(String idBarrio) {
		this.idBarrio = idBarrio;
	}

	public Integer getCtrMensajesMail() {
		return ctrMensajesMail;
	}

	public void setCtrMensajesMail(Integer ctrMensajesMail) {
		this.ctrMensajesMail = ctrMensajesMail;
	}

	/**
	 * @return the idTelefono
	 */
	public Long getIdTelefono() {
		return idTelefono;
	}

	/**
	 * @param idTelefono the idTelefono to set
	 */
	public void setIdTelefono(Long idTelefono) {
		this.idTelefono = idTelefono;
	}

	/**
	 * @return the idDireccion
	 */
	public Long getIdDireccion() {
		return idDireccion;
	}

	/**
	 * @param idDireccion the idDireccion to set
	 */
	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}

	/**
	 * @return the idEmail
	 */
	public Long getIdEmail() {
		return idEmail;
	}

	/**
	 * @param idEmail the idEmail to set
	 */
	public void setIdEmail(Long idEmail) {
		this.idEmail = idEmail;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
