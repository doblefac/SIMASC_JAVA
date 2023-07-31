package com.ccb.simasc.rest.transversal.excepciones;

import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;

/**
 * Clase que encapsula los mensajes para tratamiento de excepciones en capa de servicios REST
 * @author Asesoftware
 *
 */
public class SIMASCRecursosRESTExcepcion extends RuntimeException{
    
  /**
   * Id de la version
   */
   private static final long serialVersionUID = 9135968719305029300L;
   public static String WRONG_PARAMETER = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR019.toString());
   public static String BUSINESS_RULE = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR022.toString());
   
   /**
    * Transforma SIMASCNegocioExcepcion a SIMASCRecursosRESTExcepcion
    * @param allTrackable
    * @return
    */
   public static SIMASCRecursosRESTExcepcion transformTopException(SIMASCNegocioExcepcion allTrackable){
      SIMASCRecursosRESTExcepcion es = new SIMASCRecursosRESTExcepcion(allTrackable.getLocalizedMessage());
      es.setStatus(es.getStatus());
      StackTraceElement[] elements = allTrackable.getStackTrace();
      es.setStackTrace(new StackTraceElement[]{elements[0],elements[1]});
      return es;
   } 
   
   /**
    * Transforma Exception a SIMASCNegocioExcepcion 
    * @param allTrackable
    * @return
    */
   public static SIMASCRecursosRESTExcepcion transformTopException(Throwable allTrackable) {	   
       if(allTrackable.getCause()==null){
    	   SIMASCRecursosRESTExcepcion es = new SIMASCRecursosRESTExcepcion(allTrackable.getMessage());
    	   return es;
       }else{
    	     if(allTrackable.getCause().getCause()==null){
    			  SIMASCRecursosRESTExcepcion es = new SIMASCRecursosRESTExcepcion(allTrackable.getCause().getMessage());
    		      es.setStatus(es.getStatus());
    		      StackTraceElement[] elements = allTrackable.getStackTrace();
    		      es.setStackTrace(new StackTraceElement[]{elements[0],elements[1]});
    		      return es;
    	      }else
    	     	   return (SIMASCRecursosRESTExcepcion)transformTopException(allTrackable.getCause());
    		 
       }
   }
   
   
   public static SIMASCRecursosRESTExcepcion transformTopException(SIMASCRecursosRESTExcepcion allTrackable){
	      SIMASCRecursosRESTExcepcion es = new SIMASCRecursosRESTExcepcion(allTrackable.getMessage());
	      es.setStatus(es.getStatus());
	      StackTraceElement[] elements = allTrackable.getStackTrace();
	      es.setStackTrace(new StackTraceElement[]{elements[0],elements[1]});
	      return es;
   } 
   
   /**
    * Codigo de error
    */
   private int status;
  
   
   /**
    * Constructor indicando estado de la excepción
    *
    * @param status código de estado
    */
   public SIMASCRecursosRESTExcepcion(int status) {
       this.setStatus(status);
   }

   /**
    * Nueva excepción asociada a servicios indicando mensaje
    *
    * @param message mensaje mensaje de excepción
    */
   public SIMASCRecursosRESTExcepcion(String message) {
       super(message);
   }

   /**
    * Constructor indicando estado y mensaje de excepción
    *
    * @param status código de estado
    * @param string mensaje
    */
   public SIMASCRecursosRESTExcepcion(int status, String string) {
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
   public SIMASCRecursosRESTExcepcion(int status, String string, Throwable thrwbl) {
       super(string, thrwbl);
       this.setStatus(status);
   }

   /**
    * Constructor indicando estado y causa de la excepcion
    *
    * @param status codigo de estado
    * @param thrwbl causa
    */
   public SIMASCRecursosRESTExcepcion(int status, Throwable thrwbl) {
       super(thrwbl);
       this.setStatus(status);
   }

   /**
    * Constructor indicando mensaje y causa de la excepción
    *
    * @param message mensaje
    * @param cause causa
    */
   public SIMASCRecursosRESTExcepcion(String message, Throwable cause) {
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
   public SIMASCRecursosRESTExcepcion(int status, String string, Throwable thrwbl, boolean bln, boolean bln1) {
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
