package com.ccb.simasc.rest.recursos;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.webservice.integracion.interfaces.IClienteSWMinisterio;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.simasc.clientes.ministerio.Mensaje;

@Path( "pruebas" )
@Stateless
public class Pruebas {
	
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(Pruebas.class);
    private static final Class<Pruebas> enClass= Pruebas.class;
   
   	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@EJB
	private IClienteSWMinisterio clienteSWMinisterio;
	


	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
	// protected region metodos adicionales on begin
		// Escriba en esta sección sus modificaciones
	
	/**
	 * Consultar el listado de materias
	 * ADM-F-026
	 * @return
	 */
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/ejecutarPruebas/")
	public Mensaje ejecutarPruebas() {
		Mensaje mensaje = new Mensaje();
		try{
			
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return mensaje;
	}
}
