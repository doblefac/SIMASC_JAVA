package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
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

import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFacturacionCasoFacade;
import com.ccb.simasc.negocio.fachadas.reportes.interfaces.IReportesConciliacionFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.LiquidacionCasosConvenioDTO;
import com.ccb.simasc.transversal.dto.FiltrosTramiteOrdinarioDTO;
import com.ccb.simasc.transversal.dto.TramiteOrdinarioDTO;
import com.ccb.simasc.transversal.dto.reportes.FiltroReportesDTO;
import com.ccb.simasc.transversal.entidades.FacturacionCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST FacturacionCaso
 * 
 * @author sMartinez
 */
@Path("facturacioncaso")
@Stateless
public class FacturacionCasoRecurso {
	/**
	 * REST resource logging
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger(FacturacionCasoRecurso.class);
	@SuppressWarnings("unused")
	private static final Class<FacturacionCaso> enClass = FacturacionCaso.class;

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso

	@EJB
	private IFacturacionCasoFacade facturacionCasoFacade;

	@EJB
	private IReportesConciliacionFacade reportesConciliacionFacade;

	// protected region atributos end

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/casosPendienteCobroByConciliador")
	public Response casosPendienteCobroByConciliador(FiltrosTramiteOrdinarioDTO filtros) {
		Response response = null;
		List<TramiteOrdinarioDTO> casos = facturacionCasoFacade.casosPendienteCobroByConciliador(filtros);
		response = Response.status(Response.Status.OK).entity(new GenericEntity<List<TramiteOrdinarioDTO>>(casos) {
		}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		return response;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/aprobarFacturaByConciliador/{idConciliador}")
	public Response aprobarFacturaByConciliador(List<TramiteOrdinarioDTO> tramites,
			@PathParam("idConciliador") long idConciliador) {
		Response response = null;
		long idUsuario = Long.parseLong(ContextoDeSesion.obtenerContextoDeSesion(httpHeaders).getNombreUsuario());
		List<TramiteOrdinarioDTO> casosAprobados = facturacionCasoFacade.aprobarFacturaByConciliador(tramites,
				idConciliador, idUsuario);
		response = Response.status(Response.Status.OK)
				.entity(new GenericEntity<List<TramiteOrdinarioDTO>>(casosAprobados) {
				}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		return response;
	}
	
	/**
	 * Actualiza o crea el pago y la fecha de cobro de la facturacion del caso
	 * @param facturacion
	 * @return
	 * CON-F-086
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarFacturacionRutaCaso/")
	public Response actualizarFacturacionRutaCaso(FacturacionCaso facturacion) {
		Response response = null;
		try {
			facturacionCasoFacade.actualizarFacturacionRutaCaso(facturacion);
			response = Response.status(Response.Status.OK)
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

	/**
	 * Consulta la liquidacion de los casos del convenio que se seleccione
	 * @param FiltroReportesDTO filtros
	 * @return casosConvenioLiquidar: List<FacturacionCasosConvenioDTO>
	 * CON-C-014 facturacion de casos de convenio
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarLiquidacionCasosConvenio/")
	public Response consultarLiquidacionCasosConvenio( FiltroReportesDTO filtros ) {
		Response response = null;
		try {
			List<LiquidacionCasosConvenioDTO> casosConvenioLiquidar = facturacionCasoFacade.consultarLiquidacionCasosConvenio( filtros );
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<LiquidacionCasosConvenioDTO>>( casosConvenioLiquidar ) {
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
	
	
	/**
	 * Guarda la informacion de la facturacion para una lista de casos de convenio y envía correo
	 * @param LiquidacionCasosConvenioDTO listaFacturacion
	 * CON-C-014 facturacion de casos de convenio
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/guardarFacturacionCasosConvenio/{idConvenio}")
	public Response guardarFacturacionCasosConvenio(@PathParam("idConvenio") Long idConvenio, List<LiquidacionCasosConvenioDTO> listaFacturacion ) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idPersonaModificacion = cs.getIdUsuario();
		try {
			facturacionCasoFacade.guardarFacturacionCasosConvenio(idConvenio, listaFacturacion, idPersonaModificacion);
			response = Response.status(Response.Status.OK)
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
