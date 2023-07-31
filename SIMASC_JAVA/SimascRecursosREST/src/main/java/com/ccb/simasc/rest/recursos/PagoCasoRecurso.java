package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin

import java.util.ArrayList;
import java.util.Calendar;

// Escriba en esta sección sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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

import com.ccb.simasc.integracion.manejadores.ManejadorProgramacionAlerta;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoTramiteOrdinarioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDetallePagoCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPagoCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroDeServicioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.InfoReliquidacionPagoCaso;
import com.ccb.simasc.transversal.dto.ParametroDeServicioDTO;
import com.ccb.simasc.transversal.dto.ReliquidarPagoCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarPagoPup;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.ProgramacionAlerta;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports recurso end

/**
 * Recurso REST PagoCaso
 * 
 * @author sMartinez
 */
@Path("pagocaso")
@Stateless
public class PagoCasoRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(PagoCasoRecurso.class);
	private static final Class<PagoCaso> enClass = PagoCaso.class;

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@EJB
	private IDetallePagoCasoFacade detallePagoCasoFacade;

	@EJB
	private IPagoCasoFacade pagoCasoFacade;
	// protected region atributos end
	@EJB
	private IDiaFestivoFacade diaFestivoFacade;

	@EJB
	private ManejadorProgramacionAlerta manejadorProgramacionAlerta;

	@EJB
	private IPersonaFacade personaFacade;

	@EJB
	private ICasoTramiteOrdinarioFacade casoTramiteOrdinarioFacade;

	@EJB
	private IParametroDeServicioFacade parametroDeServicioFacade;

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Obtiene un caso dado su id
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerPago/{numeroRecibo}")
	public Response consultarPagoCaso(@PathParam("numeroRecibo") String numeroRecibo) {
		Response response = null;
		try {

			PagoCaso pagoCaso = pagoCasoFacade
					.transformarEntidadSinDependencias(pagoCasoFacade.obtenerPagoSolicitud(numeroRecibo));

			pagoCaso.setDetallePagoCasoList(detallePagoCasoFacade.obtenerDetallesPago(numeroRecibo));
			response = Response.status(Response.Status.OK).entity(new GenericEntity<PagoCaso>(pagoCaso) {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

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
	 * Obtiene los pagos del caso dependiendo del estado
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerPagosCasosPorEstado/{estadoPago}")
	public Response obtenerPagosCasosPorEstado(@PathParam("estadoPago") String estadoPago) {
		Response response = null;

		try {
			List<PagoCaso> pagosCaso = (List<PagoCaso>) pagoCasoFacade.obtenerPagosCasosPorEstado(estadoPago);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PagoCaso>>(pagosCaso) {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

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
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/reliquidarDetallePagoCasos/{idCaso}")
	public Response listarPagoCasos(@PathParam("idCaso") long idCaso) {
		Response response = null;
		try {
			List<ReliquidarPagoCasoDTO> pagosCaso = (List<ReliquidarPagoCasoDTO>) pagoCasoFacade.listarPagoCaso(idCaso);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ReliquidarPagoCasoDTO>>(pagosCaso) {
					}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

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
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/reliquidacionesPagoCasos/{idCaso}")
	public Response listarReliquidacionesPagoCasos(@PathParam("idCaso") long idCaso) {
		Response response = null;
		try {
			List<InfoReliquidacionPagoCaso> pagosCaso = (List<InfoReliquidacionPagoCaso>) pagoCasoFacade
					.listarReliquidacionPagoCaso(idCaso);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<InfoReliquidacionPagoCaso>>(pagosCaso) {
					}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}

		return response;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/generarOrden")
	public Response generarOrderCompra(FormularioGenerarPagoPup datosPup) {
		Response response = null;
		try {

			datosPup.getDatosLiquidacion().setFechaVigencia(diaFestivoFacade.sumarDiasHabilesAFecha(new Date(),
					UtilConstantes.DIAS_MAXIMOS_VIGENCIA_MEDIACION));
			String numeroOrden = pagoCasoFacade.generarOrdenPago(datosPup);

			List<String> nombreParametros = new ArrayList<String>();
			nombreParametros.add(UtilConstantes.APLICA_NOTIFICACION_VENCIMIENTO_ORDEN);

			List<ParametroDeServicioDTO> parametroDeServicioList = parametroDeServicioFacade
					.consultarParametroDeServicio(nombreParametros, new Long(datosPup.getDatosLiquidacion().getIdServicio()),
							UtilConstantes.TIPO_PARAMETRO_APLICA_NOTIFICACION_VENCIMIENTO_ORDEN);
					
			if (parametroDeServicioList != null && !parametroDeServicioList.isEmpty()
					&& parametroDeServicioList.get(0).getValor().equals(UtilConstantes.SI)) {
				
				Persona persona = personaFacade.consultarPersonaPorIdentificacion(datosPup.getTipoDocumento(),
						datosPup.getNumeroDocumento(), false);
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(datosPup.getDatosLiquidacion().getFechaVigencia());
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				
				ProgramacionAlerta programacionAlerta = new ProgramacionAlerta();
				programacionAlerta.setIdCaso(new Long(numeroOrden));
				programacionAlerta.setIdPersona(persona.getIdPersona());
				programacionAlerta.setEstado(UtilDominios.PROGRAMACION_ALERTA_PENDIENTE);
				programacionAlerta.setFechaEjecucion(calendar.getTime());
				programacionAlerta.setIdAlerta(UtilConstantes.ID_ALERTA_MEDIACION);

				manejadorProgramacionAlerta.crear(programacionAlerta);
			}
			

			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>(numeroOrden) {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

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
