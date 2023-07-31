package com.ccb.simasc.transversal.excepciones;

/**
 * Excepción lanzada cuando se produce en error la capa de negocio.
 * 
 * @author jsoto
 */
public class SimascException extends RuntimeException {

    public SimascException (String message) {
        super (message);
    }

    public SimascException (Throwable cause) {
        super (cause);
    }

    public SimascException (String message, Throwable cause) {
        super (message, cause);
    }
}
