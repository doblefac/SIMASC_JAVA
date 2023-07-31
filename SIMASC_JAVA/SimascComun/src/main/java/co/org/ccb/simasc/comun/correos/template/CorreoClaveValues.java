/**
 * 
 */
package co.org.ccb.simasc.comun.correos.template;

/**
 * Clase que contiene los parametros para modificar el template de correoclaveTemplate
 * @author dbarrera
 *
 */
public class CorreoClaveValues {
	/**
	 * Nombre de usuario
	 */
	private String nombreUsuario;
	private String urlSimasc;
	/**
	 * Clave generada
	 */
	private String clave;
	
	/**
	 * Constructor
	 * @param nombreUsuario
	 * @param clave
	 */
	public CorreoClaveValues(String nombreUsuario, String clave, String urlSimasc){
		this.nombreUsuario = nombreUsuario;
		this.clave = clave;
		this.urlSimasc= urlSimasc;
	}

	/**
	 * metodo que retorna el nombre de usuario 
	 * @return nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	/**
	 * metodo que retorna la clave
	 * @returnclave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @return the urlSimasc
	 */
	public String getUrlSimasc() {
		return urlSimasc;
	}

	/**
	 * @param urlSimasc the urlSimasc to set
	 */
	public void setUrlSimasc(String urlSimasc) {
		this.urlSimasc = urlSimasc;
	}
	
}
