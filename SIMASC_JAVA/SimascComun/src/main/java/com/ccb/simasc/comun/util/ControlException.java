package com.ccb.simasc.comun.util;

public class ControlException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	private Exception exception;
	
	public ControlException(String mensaje, Exception exception) throws ControlException {
		if(exception instanceof ControlException) {
			throw (ControlException) exception;
		}
		this.mensaje = mensaje;
		this.exception = exception;
	}

	public String getMensaje() {
		return mensaje;
	}

	public Exception getException() {
		return exception;
	}

}
