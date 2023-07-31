/**
 * 
 */
package com.ccb.simasc.transversal.excepciones;

/**
 * Excepción lanzada cuando se intenta pasar de un estado a otro no valido
 * @author lvalbuena
 *
 */
public class EstadosCasoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Mensaje de la excepcion
	 */
	private String mensaje;
	
	/**
	 * 
	 * @param mensaje
	 */
	public EstadosCasoException(String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;		
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
