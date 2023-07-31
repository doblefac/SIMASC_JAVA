package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.util.ArrayList;
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

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorTipoServicioSede;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITipoServicioSedeFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.entidades.TipoServicioSede;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST TipoServicioSede
 * @author sMartinez
 */
@Path( "tiposerviciosede" )
@Stateless
public class TipoServicioSedeRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(TipoServicioSedeRecurso.class);
    private static final Class<TipoServicioSede> enClass= TipoServicioSede.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private ITipoServicioSedeFacade tipoServicioSedeFacade; 
	
	@EJB
    private ManejadorTipoServicioSede manejadorTipoServicioSede; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	
    /**
     * Retorna la lista de sedes para la pantalla de Administracion consultadas por id
     * con las dependencias correspondientes.
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/obtenerTipoServicioSedeBySede/{idSede}")
    public Response obtenerSedesAdminByid(@PathParam("idSede") Long idSede) {
        Response response = null;
        try {
        	   		
    		InformacionFiltro filtroTipoServicio = new InformacionFiltro(TipoFiltro.EXACTO,
    				TipoServicioSede.ENTIDAD_TIPO_SERVICIO_SEDE_PK_ID_SEDE,
    				idSede);
        	List<InformacionFiltro> filtrosTipoServicioConsulta = new ArrayList<>();
        	filtrosTipoServicioConsulta.add(filtroTipoServicio);

        	List<TipoServicioSede> tipoServSede = (List<TipoServicioSede>)manejadorTipoServicioSede.consultar(filtrosTipoServicioConsulta, null, null);
        	
        	response = Response.status(Response.Status.OK)
                    .entity(new GenericEntity<List<TipoServicioSede>>(tipoServSede) {
                     })
                    .header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
             
        } catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
        return response;
    }

   // protected region metodos adicionales end


}
