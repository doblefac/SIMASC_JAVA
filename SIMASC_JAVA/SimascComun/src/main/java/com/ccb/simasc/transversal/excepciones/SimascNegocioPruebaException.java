/**
 * 20/06/2018
 * @author jnmartinez
 */
package com.ccb.simasc.transversal.excepciones;

/**
 * @author jnmartinez
 *
 */
public class SimascNegocioPruebaException extends Exception {

	/**
	   * Id de la version
	   */
	   private static final long serialVersionUID = 9135968719305029300L;
	   public static String WRONG_PARAMETER="El Parametro no es Valido.";
	   public static String BUSINESS_RULE="La operación no es valida.";
	  
	  
	   
	   /**
	    * Codigo de error
	    */
	   private int status;
	   
	   
	   /**
	    * Transforma SimascException a SimascNegocioPruebaException
	    * @param allTrackable
	    * @return
	    */
	   public static SimascNegocioPruebaException transformTopException(SimascException allTrackable){
		   SimascNegocioPruebaException es = new SimascNegocioPruebaException(allTrackable.getMessage());
		      es.setStatus(es.getStatus());
		      StackTraceElement[] elements = allTrackable.getStackTrace();
		      es.setStackTrace(new StackTraceElement[]{elements[0],elements[1]});
		      return es;
	   } 
	  
	   
	   /**
	    * Constructor indicando estado de la excepción
	    *
	    * @param status código de estado
	    */
	   public SimascNegocioPruebaException(int status) {
	       this.setStatus(status);
	   }

	   /**
	    * Nueva excepción asociada a servicios indicando mensaje
	    *
	    * @param message mensaje mensaje de excepción
	    */
	   public SimascNegocioPruebaException(String message) {
	       super(message);
	   }

	   /**
	    * Constructor indicando estado y mensaje de excepción
	    *
	    * @param status código de estado
	    * @param string mensaje
	    */
	   public SimascNegocioPruebaException(int status, String string) {
	       super(string);
	       this.setStatus(status);
	   }

	   /**
	    * Constructor indicando estado, mensaje y causa de la excepción
	    *
	    * @param status código de estado
	    * @param string mensaje
	    * @param thrwbl causa
	    */
	   public SimascNegocioPruebaException(int status, String string, Throwable thrwbl) {
	       super(string, thrwbl);
	       this.setStatus(status);
	   }

	   /**
	    * Constructor indicando estado y causa de la excepcion
	    *
	    * @param status codigo de estado
	    * @param thrwbl causa
	    */
	   public SimascNegocioPruebaException(int status, Throwable thrwbl) {
	       super(thrwbl);
	       this.setStatus(status);
	   }

	   /**
	    * Constructor indicando mensaje y causa de la excepción
	    *
	    * @param message mensaje
	    * @param cause causa
	    */
	   public SimascNegocioPruebaException(String message, Throwable cause) {
	       super(message, cause);
	   }

	   /**
	    * Constructor indicando estado, mensaje y causa. Indicando:
	    * enableSuppression y writableStackTrace.
	    *
	    * @param status codigo de estado
	    * @param string mensaje
	    * @param thrwbl causa
	    * @param bln enableSuppression
	    * @param bln1 writableStackTrace
	    */
	   public SimascNegocioPruebaException(int status, String string, Throwable thrwbl, boolean bln, boolean bln1) {
	       super(string, thrwbl, bln, bln1);
	       this.setStatus(status);
	   }

	   /**
	    * Obtiene el codigo de estado de la excepcion
	    *
	    * @return codigo
	    */
	   public int getStatus() {
	       return status;
	   }

	   /**
	    * Modifica el codigo de estado de la excepcion
	    *
	    * @param status codigo de estado
	    */
	   public void setStatus(int status) {
	       this.status = status;
	   }
}
