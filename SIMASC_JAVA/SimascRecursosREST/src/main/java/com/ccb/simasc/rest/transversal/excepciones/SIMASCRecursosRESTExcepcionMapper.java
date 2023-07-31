package com.ccb.simasc.rest.transversal.excepciones;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SIMASCRecursosRESTExcepcionMapper implements ExceptionMapper<SIMASCRecursosRESTExcepcion> {

	@Override
	public Response toResponse(SIMASCRecursosRESTExcepcion exception) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
				.entity(exception)
				.type(MediaType.APPLICATION_JSON).
				build();
	}

}