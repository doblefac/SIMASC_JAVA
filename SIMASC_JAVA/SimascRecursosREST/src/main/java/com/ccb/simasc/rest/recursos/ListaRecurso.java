package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IListaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.ListaDTO;
import com.ccb.simasc.transversal.entidades.Lista;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Lista
 * @author sMartinez
 */
@Path( "lista" )
@Stateless
public class ListaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(ListaRecurso.class);
    private static final Class<Lista> enClass= Lista.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
	@EJB
    private IListaFacade listaFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response obtenerListas() {
		Response response = null;
		Collection<ListaDTO> lista = new ArrayList<ListaDTO>(); 
		try {
			listaFacade.obtenerTodos(lista, false);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Collection<ListaDTO>>(lista) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/** ADM-C-004: servicio que expone la consulta de las listas 
	 * @param nombreLista
	 * @return List<ListaDTO>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarListaParametros/{nombreLista}")
	public Response consultarListaParametros( @PathParam("nombreLista") String nombreDeLista ){
		Response response = null;
		String nombreLista = nombreDeLista.equals(UtilConstantes.VALOR_UNDEFINED)? null: nombreDeLista;
		try {
			List<ListaDTO> listas = listaFacade.consultarListaParametros(nombreLista);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ListaDTO>>(listas) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
   // protected region metodos adicionales end

}
