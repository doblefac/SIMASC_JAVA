package com.ccb.simasc.transversal.dto.formularios;

/**
 * DTO que contiene la informacion de las condicioens generales de insolvencia
 * @author aacevedo
 *
 */
public class CondicionesGeneralesDTO {	
	

	private String valorMora;
	private String cantAcreedor;
	private String cantDeuda;
	private String domicilio;
	private String saldoCapital;
	private String tipoPersona;
	
	
	public String getValorMora() {
		return valorMora;
	}
	public void setValorMora(String valorMora) {
		this.valorMora = valorMora;
	}
	public String getCantAcreedor() {
		return cantAcreedor;
	}
	public void setCantAcreedor(String cantAcreedor) {
		this.cantAcreedor = cantAcreedor;
	}
	public String getCantDeuda() {
		return cantDeuda;
	}
	public void setCantDeuda(String cantDeuda) {
		this.cantDeuda = cantDeuda;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getSaldoCapital() {
		return saldoCapital;
	}
	public void setSaldoCapital(String saldoCapital) {
		this.saldoCapital = saldoCapital;
	}
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	
	
}
