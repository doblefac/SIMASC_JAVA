package com.ccb.simasc.rest.recursos;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrizacionTarifasInternacionalFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasInternacionalesDTO;
import com.ccb.simasc.transversal.dto.formularios.ResultadosTarifaInternacionalDTO;
import com.ccb.simasc.transversal.entidades.ParametrizacionTarifas;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

@Path( "parametrizaciontarifasinternacionales" )
@Stateless
public class ParametrizacionTarifasInternacionalesRecurso {
	
    private static final Logger LOG = LogManager.getLogger(ParametrizacionTarifasInternacionalesRecurso.class);
    private static final Class<ParametrizacionTarifas> enClass= ParametrizacionTarifas.class;   
	
	@EJB
    private IParametrizacionTarifasInternacionalFacade parametrizacionTarifasInternacionalFacade; 
	
	@Context
    private HttpHeaders httpHeaders;
              
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/calcularTarifaInternacionalPublico")
	public Response calcularTarifa(ParametrosTarifasInternacionalesDTO parametrosTarifasInternacionalDTO) {
		Response response = null;
		try {
			ResultadosTarifaInternacionalDTO resultados = 
					parametrizacionTarifasInternacionalFacade
					.calcularTarifaPublico(parametrosTarifasInternacionalDTO);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<ResultadosTarifaInternacionalDTO>(resultados) {
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
}
