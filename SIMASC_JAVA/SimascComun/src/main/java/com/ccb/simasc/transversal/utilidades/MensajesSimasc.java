package com.ccb.simasc.transversal.utilidades;


import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Clase que permite leer el archivo de mensajes.properties
 * Implementa el patron Singleton para asegurar una unica instancia de la clase
 * y una unica carga del archivo de propiedades en memoria
 * 
 * @author Leonardo Valbuena Calderon
 * 
 */
public class MensajesSimasc {
	private static final Logger logger = LogManager.getLogger(MensajesSimasc.class.getName());
	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------	
	/**
	 * String constante para la ruta del archivo properties
	 */
	private static String RUTA_PROPERTIES_MENSAJES = "/";
	    
    /**
	 * String con el nombre del archivo properties
	 */
	private static String NOMBRE_PROPERTIES_MENSAJES = "mensajes";

	/**
	 * messagesSimasc para la implementacion del patron singleton.
	 */
	private static MensajesSimasc messagesSimasc;
	
	/**
	 * 
	 */
	private static ResourceBundle bundle = null;
	

    //-----------------------------------------------------------------
	// Constructor
	//-----------------------------------------------------------------
	/**
	 * Constructor de la clase. Lee el archivo de propiedades y lo carga en la
	 * variable bundle
	 */
	private MensajesSimasc() {
		try {			
			bundle = ResourceBundle.getBundle(RUTA_PROPERTIES_MENSAJES + 
			        NOMBRE_PROPERTIES_MENSAJES, Locale.getDefault());
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * Metodo del patron singleton para obtener la instancia de la clase, si la
	 * instancia no esta creada se genera una nueva, si existe, se pasa la ya
	 * existente
	 * 
	 * @return
	 */
	public static MensajesSimasc getInstancia() {
		if (messagesSimasc == null) {
			messagesSimasc = new MensajesSimasc();
		}
		return messagesSimasc;
	}

	/**
	 * Metodo para obtener un valor del properties a traves de su KEY
	 * 
	 * @param key
	 *            String con el identificador llave de un mensaje
	 * @return String con el valor de la consntate parametrizada en el archivo
	 */
	public String getMensajePorKey(String key) {
		return bundle.getString(key);
	}

	/**
	 * @param key
	 * @param params
	 * @return
	 */
	public String getMensajePorKey(String key, String... params) {
		String mensaje = MessageFormat.format(bundle.getString(key), params);
		return mensaje;
	}
	
	/**
	 * En los parametros usados en el key se debe especificar el tipo de dato
	 * @param key
	 * @param params
	 * @return
	 */
	public String getMensajePorKey(String key, Object... params) {
		String mensaje = MessageFormat.format(bundle.getString(key), params);
		return mensaje;
	}

}
