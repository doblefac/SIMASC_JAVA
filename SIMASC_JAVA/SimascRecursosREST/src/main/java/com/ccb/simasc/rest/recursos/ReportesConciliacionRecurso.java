/**
 * 
 */
package com.ccb.simasc.rest.recursos;

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

import com.ccb.simasc.negocio.fachadas.reportes.interfaces.IReportesConciliacionFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.transversal.dto.AsesoriaDTO;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.reportes.AudienciasPorFechasDTO;
import com.ccb.simasc.transversal.dto.reportes.CasosApoderadoConvenioDTO;
import com.ccb.simasc.transversal.dto.reportes.CasosCobradosDTO;
import com.ccb.simasc.transversal.dto.reportes.CasosNoAceptadosDTO;
import com.ccb.simasc.transversal.dto.reportes.CasosPagadosDTO;
import com.ccb.simasc.transversal.dto.reportes.CasosRechazadosCompetenciaDTO;
import com.ccb.simasc.transversal.dto.reportes.CasosSinCerrar90DTO;
import com.ccb.simasc.transversal.dto.reportes.DevolucionDeDineroDTO;
import com.ccb.simasc.transversal.dto.reportes.FacturacionCasosConvenioDTO;
import com.ccb.simasc.transversal.dto.reportes.FiltroReportesDTO;
import com.ccb.simasc.transversal.dto.reportes.ListaConciliadoresDTO;
import com.ccb.simasc.transversal.dto.reportes.PlanillaCorrespondenciaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteActasConstanciasDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosCerradosConciliacionDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosCerradosConveniosDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosDevueltosControlLegalidadDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteConveniosDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteEvaluacionConciliadoresDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteGeneralCasosConciliacionDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteGeneralCasosInsolvenciaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReportePartesDeCasoDTO;
import com.ccb.simasc.transversal.dto.reportes.SeguimientoCasosDTO;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

/**
 * Recurso que expone los recursos
 * 
 * @author cbenavides
 *
 */
@Path("reportesConciliacion")
@Stateless
public class ReportesConciliacionRecurso {

	private static final Logger LOG = LogManager.getLogger(RolPersonaCasoRecurso.class);

	/**
	 * Facade que contiene la logica de los reportes de conciliacion
	 */
	@EJB
	private IReportesConciliacionFacade reportesConciliacionFacade;

	/**
	 * Headers enviados por el recurso
	 */
	@Context
	private HttpHeaders httpHeaders;

	/**
	 * Obtiene el reporte de los casos no aceptados por el conciliador
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/casosNoAceptadosConciliador/")
	public Response casosNoAceptadosConciliador(FiltroReportesDTO datosConsulta) {
		Response response = null;
		try {
			List<CasosNoAceptadosDTO> casosNoAceptados = reportesConciliacionFacade
					.casosNoAceptadosConciliador(datosConsulta);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<CasosNoAceptadosDTO>>(casosNoAceptados) {
					}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Obtiene el reporte de los casos no aceptados por el conciliador
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/casosRechazadosCompetencia/")
	public Response casosRechazadosCompetencia(FiltroReportesDTO datosConsulta) {
		Response response = null;
		try {
			List<CasosRechazadosCompetenciaDTO> casosRechazadosCompetencia = reportesConciliacionFacade
					.casosRechazadosCompetencia(datosConsulta);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<CasosRechazadosCompetenciaDTO>>(casosRechazadosCompetencia) {
					}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * 
	 * @param datosConsulta
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/casosPagadosPorSede/")
	public Response casosPagadosPorSede(FiltroReportesDTO datosConsulta) {
		Response response = null;

		List<CasosPagadosDTO> respuesta = reportesConciliacionFacade.casosPagadosPorSede(datosConsulta);

		response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CasosPagadosDTO>>(respuesta) {
		}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		return response;
	}

	/**
	 * CON-C-035
	 * 
	 * @param datosConsulta
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/casosCobrados/")
	public Response casosCobrados(FiltroReportesDTO datosConsulta) {
		Response response = null;

		List<CasosCobradosDTO> respuesta = reportesConciliacionFacade.casosCobrados(datosConsulta);

		response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CasosCobradosDTO>>(respuesta) {
		}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		return response;
	}

	/**
	 * CON-C-038
	 * 
	 * @param datosConsulta
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/casosNoCobrados/")
	public Response casosNoCobrados(FiltroReportesDTO datosConsulta) {
		Response response = null;

		List<CasosCobradosDTO> respuesta = reportesConciliacionFacade.casosNoCobrados(datosConsulta);

		response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CasosCobradosDTO>>(respuesta) {
		}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		return response;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/planillaCorrespondencia/{idSede}")
	public Response planillaCorrespondencia(@PathParam("idSede") String sede) {
		Response response = null;
		Long idSede = null;
		if (!sede.equals(UtilConstantes.VALOR_UNDEFINED) && !sede.isEmpty())
			idSede = Long.parseLong(sede);

		List<PlanillaCorrespondenciaDTO> planilla = reportesConciliacionFacade.planillaCorrespondencia(idSede);

		response = Response.status(Response.Status.OK)
				.entity(new GenericEntity<List<PlanillaCorrespondenciaDTO>>(planilla) {
				}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		return response;
	}

	/**
	 * CON-C-032
	 * 
	 * @author LRUIZ
	 * @param filtros
	 * @return
	 */

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/AudienciasPorFechas/")
	public Response consultaAudienciasPorFechas(FiltroReportesDTO filtros) {
		Response response = null;

		List<AudienciasPorFechasDTO> respuesta = reportesConciliacionFacade.consultaAudienciasPorFechas(filtros);

		response = Response.status(Response.Status.OK)
				.entity(new GenericEntity<List<AudienciasPorFechasDTO>>(respuesta) {
				}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		return response;
	}

	/**
	 * Servicio que expone la colsulta del reporte para el caso de uso CON-C-040
	 * 
	 * @param filtros
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarCasosApoderadoConvenio/")
	public Response consultarCasosApoderadoConvenio(FiltroReportesDTO filtros) {
		Response response = null;

		List<CasosApoderadoConvenioDTO> casosApoderadoConvenioDTO = reportesConciliacionFacade
				.consultarCasosApoderadoConvenio(filtros);

		response = Response.status(Response.Status.OK)
				.entity(new GenericEntity<List<CasosApoderadoConvenioDTO>>(casosApoderadoConvenioDTO) {
				}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		return response;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/CasosSinCerrar90Dias/")
	public Response consultaCasosSinCerrar90Dias(List<CentroDTO> centros) {
		Response response = null;
		List<CasosSinCerrar90DTO> respuesta = reportesConciliacionFacade.consultaCasosSinCerrar90Dias(centros);

		response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CasosSinCerrar90DTO>>(respuesta) {
		}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		return response;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/seguimientoCasosEnAcuerdo/")
	public Response seguimientoCasosEnAcuerdo(FiltroReportesDTO filtros) {
		Response response = null;
		List<SeguimientoCasosDTO> respuesta = reportesConciliacionFacade.seguimientoCasosEnAcuerdo(filtros);

		response = Response.status(Response.Status.OK).entity(new GenericEntity<List<SeguimientoCasosDTO>>(respuesta) {
		}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		return response;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/devolucionDeDinero/")
	public Response consultaDevolucionDeDinero(FiltroReportesDTO filtros) {
		Response response = null;
		List<DevolucionDeDineroDTO> respuesta = reportesConciliacionFacade.consultaDevolucionDeDinero(filtros);

		response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DevolucionDeDineroDTO>>(respuesta) {
		}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		return response;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarPartesDeCaso/")
	public Response consultarPartesDeCaso(FiltroReportesDTO filtros) {
		Response response = null;
		List<ReportePartesDeCasoDTO> respuesta = reportesConciliacionFacade.consultarPartesDeCaso(filtros);

		response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ReportePartesDeCasoDTO>>(respuesta) {
		}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		return response;
	}
	

	 
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/reporteConvenios/")
	public Response reporteConvenios(FiltroReportesDTO filtros) {
		Response response = null;
		List<ReporteConveniosDTO> reporteConvenios = reportesConciliacionFacade.reporteConvenios(filtros);

		response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ReporteConveniosDTO>>(reporteConvenios) {
		}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		return response;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/facturacionCasosConvenio/")
	public Response facturacionCasosConvenio(FiltroReportesDTO filtros) {
		Response response = null;
		List<FacturacionCasosConvenioDTO> respuesta = reportesConciliacionFacade.facturacionCasosConvenio(filtros);

		response = Response.status(Response.Status.OK).entity(new GenericEntity<List<FacturacionCasosConvenioDTO>>(respuesta) {
		}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		return response;
	}
	

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/reporteAsesorias/")
	public Response reporteAsesorias(AsesoriaDTO filtros) {
		Response response = null;
		List<AsesoriaDTO> asesorias = reportesConciliacionFacade.reporteAsesorias(filtros);
		response = Response.status(Response.Status.OK).entity(new GenericEntity<List<AsesoriaDTO>>(asesorias) {
		}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		return response;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/listaConciliadores/")
	public Response listaConciliadores(FiltroReportesDTO filtros) {
		Response response = null;
		List<ListaConciliadoresDTO> conciliadores = reportesConciliacionFacade.listaConciliadores(filtros);
		response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ListaConciliadoresDTO>>(conciliadores){
		}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		return response;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarCasosCerradosConciliacion/")
	public Response consultarCasosCerradosConciliacion(FiltroReportesDTO filtros) {
		Response response = null;
		List<ReporteCasosCerradosConciliacionDTO> respuesta = reportesConciliacionFacade.consultarCasosCerradosConciliacion(filtros);

		response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ReporteCasosCerradosConciliacionDTO>>(respuesta) {
		}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		return response;
	}
	
	/**
	 * CON-C-033
	 * @param filtros
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarCasosCerradosConvenios/")
	public Response consultarCasosCerradosConvenio( FiltroReportesDTO filtros ){
		Response response = null;
		try{
		List<ReporteCasosCerradosConveniosDTO> casosCerradosConvenios = reportesConciliacionFacade.consultarCasosCerradosConvenios(filtros);

		response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ReporteCasosCerradosConveniosDTO>>(casosCerradosConvenios) {
		}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		
		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * CON-C-036
	 * @param filtros
	 * @return casosDevueltos: List<ReporteCasosDevueltosControlLegalidadDTO>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarCasosDevueltosControlLegalidad/")
	public Response consultarCasosDevueltosControlLegalidad( FiltroReportesDTO filtros ){
		Response response = null;
		try{
			List<ReporteCasosDevueltosControlLegalidadDTO> casosDevueltos = reportesConciliacionFacade.consultarCasosDevueltosControlLegalidad(filtros);
	
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ReporteCasosDevueltosControlLegalidadDTO>>(casosDevueltos) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * CON-C-050
	 * @param filtros
	 * @return casosDevueltos: List<ReporteCasosDevueltosControlLegalidadDTO>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarReporteGeneralConciliacion/")
	public Response consultarReporteGeneralConciliacion( FiltroReportesDTO filtros ){
		Response response = null;
		try{
			List<ReporteGeneralCasosConciliacionDTO> reporteGeneral = reportesConciliacionFacade.consultarReporteGeneralConciliacion(filtros);
	
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ReporteGeneralCasosConciliacionDTO>>(reporteGeneral) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * 
	 * @param idCentros
	 * 
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarReporteActasConstancias/{tipoLibro}/{anio}")
	public Response consultarReporteActasConstancias(@PathParam("tipoLibro") String tipoLibro,
			@PathParam("anio") Date anio, List<Long> idCentros  ){
		Response response = null;
		try{
			List<ReporteActasConstanciasDTO> reporteActasConstancias = reportesConciliacionFacade.consultarReporteActasConstancias(tipoLibro,anio, idCentros);
	
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ReporteActasConstanciasDTO>>(reporteActasConstancias) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * CON-C-045
	 * 
	 * @param filtros
	 * @return casosDevueltos: List<ReporteCasosDevueltosControlLegalidadDTO>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarReporteEvaluacionConciliadores/")
	public Response consultarReporteEvaluacionConciliadores(FiltroReportesDTO filtros) {
		Response response = null;
		try {
			List<ReporteEvaluacionConciliadoresDTO> evaluaciones = reportesConciliacionFacade
					.consultarReporteEvaluacionConciliadores(filtros);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ReporteEvaluacionConciliadoresDTO>>(evaluaciones) {
					}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	
	/**
	 * CON-C-050
	 * @param filtros
	 * @return casosDevueltos: List<ReporteCasosDevueltosControlLegalidadDTO>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarReporteGeneralInsolvencia/")
	public Response consultarReporteGeneralInsolvencia( FiltroReportesDTO filtros ){
		Response response = null;
		try{
			List<ReporteGeneralCasosInsolvenciaDTO> reporteGeneral = reportesConciliacionFacade.
					consultarReporteGeneralInsolvencia(filtros);
	
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ReporteGeneralCasosInsolvenciaDTO>>(reporteGeneral) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
}
