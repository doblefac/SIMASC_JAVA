package com.ccb.simasc.transversal.excepciones;

/**
 * Clase que encapsula los mensajes para tratamiento de excepciones en capa de Negocio Simasc
 * @author Asesoftware
 *
 */
public class SIMASCNegocioExcepcion extends RuntimeException{
    
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
    * Transforma SimascException a SIMASCNegocioExcepcion
    * @param allTrackable
    * @return
    */
   public static SIMASCNegocioExcepcion transformTopException(SimascException allTrackable){
	   SIMASCNegocioExcepcion es = new SIMASCNegocioExcepcion(allTrackable.getMessage());
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
   public SIMASCNegocioExcepcion(int status) {
       this.setStatus(status);
   }

   /**
    * Nueva excepción asociada a servicios indicando mensaje
    *
    * @param message mensaje mensaje de excepción
    */
   public SIMASCNegocioExcepcion(String message) {
       super(message);
   }

   /**
    * Constructor indicando estado y mensaje de excepción
    *
    * @param status código de estado
    * @param string mensaje
    */
   public SIMASCNegocioExcepcion(int status, String string) {
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
   public SIMASCNegocioExcepcion(int status, String string, Throwable thrwbl) {
       super(string, thrwbl);
       this.setStatus(status);
   }

   /**
    * Constructor indicando estado y causa de la excepcion
    *
    * @param status codigo de estado
    * @param thrwbl causa
    */
   public SIMASCNegocioExcepcion(int status, Throwable thrwbl) {
       super(thrwbl);
       this.setStatus(status);
   }

   /**
    * Constructor indicando mensaje y causa de la excepción
    *
    * @param message mensaje
    * @param cause causa
    */
   public SIMASCNegocioExcepcion(String message, Throwable cause) {
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
   public SIMASCNegocioExcepcion(int status, String string, Throwable thrwbl, boolean bln, boolean bln1) {
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
