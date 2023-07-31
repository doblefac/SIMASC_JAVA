package com.ccb.simasc.transversal.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO que contiene los datos necesarios para la generación de la clave segura
 * para la firma de actas de audiencias virtuales.
 * 
 * @author Asesoftware - Juan Diego Cepeda Mosquera
 */
@XmlRootElement
public class DatosVerificacionParteFirmaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tipoDocumento;
	private String numeroDocumento;
	private String nombres;
	private String primerApellido;
	private String segundoApellido;
	private String telefonoMovil;
	private String tokenEntrada;

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento
	 *            the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @param nombres
	 *            the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return the primerApellido
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}

	/**
	 * @param primerApellido
	 *            the primerApellido to set
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	/**
	 * @return the segundoApellido
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}

	/**
	 * @param segundoApellido
	 *            the segundoApellido to set
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	/**
	 * @return the telefonoMovil
	 */
	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	/**
	 * @param telefonoMovil
	 *            the telefonoMovil to set
	 */
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	/**
	 * @return the tokenEntrada
	 */
	public String getTokenEntrada() {
		return tokenEntrada;
	}

	/**
	 * @param tokenEntrada
	 *            the tokenEntrada to set
	 */
	public void setTokenEntrada(String tokenEntrada) {
		this.tokenEntrada = tokenEntrada;
	}

}
