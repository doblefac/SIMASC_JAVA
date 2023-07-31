/**
 * 
 */
package com.ccb.simasc.transversal.dto;

/**
 * @author dbarrera
 *
 */
public class InformacionFiltroDTO {
	private String campo;
	private String tipo;
	private Object valor;
	private String operador;
	
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Object getValor() {
		return valor;
	}
	public void setValor(Object valor) {
		this.valor = valor;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	
}
