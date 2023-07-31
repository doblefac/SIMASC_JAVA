package com.ccb.simasc.rest.recursos.configuracion.manejoDeErrores;



import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
/**
 * Manejador de excepciones principal
 * @author sMartinez
 */


import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.WebApplicationException;

@Provider
public class MapeadorGeneralDeExcepciones implements ExceptionMapper<Throwable> {
	 
		public Response toResponse(Throwable ex) {
			MensajeDeError mensajeDeError = new MensajeDeError();		
			setHttpStatus(ex, mensajeDeError);
			mensajeDeError.setCode(ConstantesDeAplicacion.GENERIC_APP_ERROR_CODE);
			mensajeDeError.setMessage(ex.getLocalizedMessage());
			Throwable ej = (Exception)ex;
			while(ej.getCause() != null){
				ej=ej.getCause();
				if(ej.getCause()==null)
					mensajeDeError.setMessage(ej.getLocalizedMessage());
			}
			StringWriter errorStackTrace = new StringWriter();
			ex.printStackTrace(new PrintWriter(errorStackTrace));
			mensajeDeError.setDeveloperMessage(errorStackTrace.toString());
			mensajeDeError.setLink(ConstantesDeAplicacion.BLOG_POST_URL);
					
			return Response
	            .status(Status.BAD_REQUEST)
	            .type( MediaType.APPLICATION_JSON )
	            .entity(mensajeDeError)
	            .build();
		}
		
		private void setHttpStatus(Throwable ex, MensajeDeError errorMessage) {
			if(ex instanceof WebApplicationException ) { 
				errorMessage.setStatus(((WebApplicationException)ex).getResponse().getStatus());
			} else {
				errorMessage.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()); 
			}
		}
	}