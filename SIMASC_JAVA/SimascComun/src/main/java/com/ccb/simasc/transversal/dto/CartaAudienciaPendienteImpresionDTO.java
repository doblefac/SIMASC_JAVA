package com.ccb.simasc.transversal.dto;

import java.math.BigDecimal;

public class CartaAudienciaPendienteImpresionDTO {

	public BigDecimal idCaso;
	public Integer cartasPendientes;
	public String direccionSecretario;
	public String direccionAbogado;
	public BigDecimal idPersona;
	public BigDecimal idAudiencia;

	
	/**
	 * @return the idAudiencia
	 */
	public BigDecimal getIdAudiencia() {
		return idAudiencia;
	}
	/**
	 * @param idAudiencia the idAudiencia to set
	 */
	public void setIdAudiencia(BigDecimal idAudiencia) {
		this.idAudiencia = idAudiencia;
	}
	public BigDecimal getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(BigDecimal idCaso) {
		this.idCaso = idCaso;
	}
	public Integer getCartasPendientes() {
		return cartasPendientes;
	}
	public void setCartasPendientes(Integer cartasPendientes) {
		this.cartasPendientes = cartasPendientes;
	}
	public String getDireccionSecretario() {
		return direccionSecretario;
	}
	public void setDireccionSecretario(String direccionSecretario) {
		this.direccionSecretario = direccionSecretario;
	}
	public String getDireccionAbogado() {
		return direccionAbogado;
	}
	public void setDireccionAbogado(String direccionAbogado) {
		this.direccionAbogado = direccionAbogado;
	}
	
	/**
	 * @return the idPersona
	 */
	public BigDecimal getIdPersona() {
		return idPersona;
	}
	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(BigDecimal idPersona) {
		this.idPersona = idPersona;
	}
	
	
}
