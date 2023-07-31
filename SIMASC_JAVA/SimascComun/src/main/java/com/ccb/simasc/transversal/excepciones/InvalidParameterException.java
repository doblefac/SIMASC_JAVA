package com.ccb.simasc.transversal.excepciones;

/**
 * Excepción lanzada cuando alguno de los parámetros enviados en la url no son 
 * válidos.
 * 
 * @author jsoto
 */
public class InvalidParameterException extends Exception {
    
    public InvalidParameterException (String message) {
        super (message);
    }

    public InvalidParameterException (Throwable cause) {
        super (cause);
    }

    public InvalidParameterException (String message, Throwable cause) {
        super (message, cause);
    }
}
