package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IHomologacionSistemaExternoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.entidades.HomologacionSistemaExterno;
import com.ccb.simasc.transversal.entidades.HomologacionSistemaExternoPK;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports recurso end

/**
 * Recurso REST HomologacionSistemaExterno
 * @author sMartinez
 */
@Path("homologacionsistemaexterno")
@Stateless
public class HomologacionSistemaExternoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(HomologacionSistemaExternoRecurso.class);
    private static final Class<HomologacionSistemaExterno> enClass= HomologacionSistemaExterno.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IHomologacionSistemaExternoFacade homologacionSistemaExternoFacade; 
	
    @Inject
    private ApplicationRequestContext appContext;
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

   // protected region metodos adicionales end
	
	@POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
	@Path("/modificarHomologacionesExternas")
	public Response modificarHomologacionesExternas(List<HomologacionSistemaExternoPK> homologaciones) {
		Response response = null;
		try {
			for (HomologacionSistemaExternoPK homologacionpk : homologaciones) {
				
				HomologacionSistemaExterno  homosistema = new HomologacionSistemaExterno(); 
				homosistema.setHomologacionSistemaExternoPK(homologacionpk);
				
				HomologacionSistemaExterno homologacionUpdate = homologacionSistemaExternoFacade.
						obtenerHomologacionPorSistemaExterno(homosistema);
				
				String usuarioActual = appContext.getContextoSesion().getIdUsuario() != null 
						? appContext.getContextoSesion().getNombreUsuario() : UtilConstantes.USUARIO_DEFECTO_SIMASC;
						homosistema.setFechaUltimaModificacion(new Date());
						homosistema.setIdUsuarioModificacion(usuarioActual);
						
				if(homologacionUpdate != null) {
							
					if(homologacionpk.getCodigoSistemaExterno().equals("0")) {
						homologacionSistemaExternoFacade.desactivarHomologacionSistemaExterno(homosistema);
					}else {
							//	homosistema.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
						homologacionSistemaExternoFacade.actulizarHomologacionSistemaExterno(homosistema);
					}
	
				}else if(!homologacionpk.getCodigoSistemaExterno().equals("0")){
							homosistema.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					homologacionSistemaExternoFacade.crear(homosistema);
				}
			}
			  response = Response.status(Response.Status.OK)     
					  .entity(new GenericEntity<String>("OK") {})
						.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build(); 
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/consultarHomologaciones/{nombreSistema}")
	public Response consultarHomologacionesPorSistemaExterno(@PathParam("nombreSistema") String nombreSistemaExterno) {
		Response response = null;        
        try {
        	List<HomologacionSistemaExternoPK> homologacionespk = new ArrayList<HomologacionSistemaExternoPK>();
        	
        	List<HomologacionSistemaExterno> homologaciones = homologacionSistemaExternoFacade.
        			consultarHomologacionesPorSistemaExterno(nombreSistemaExterno);
        	
		    if(homologaciones.size() > 0) {
		    	for(HomologacionSistemaExterno homo :homologaciones ) {
	        		homologacionespk.add(homo.getHomologacionSistemaExternoPK());
	        	}
		    }
        	
            response = Response.status(Response.Status.OK)        		
                    .entity(new GenericEntity<List<HomologacionSistemaExternoPK>>(homologacionespk){})
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();      
            
        } catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
        return response;
	}


}
