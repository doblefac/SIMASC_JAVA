/**
 * 
 */
package com.ccb.simasc.negocio.fachadas.reportes.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

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

/**
 * Interfaz que contiene la logica de negocio de los repores de conciliacion
 * 
 * @author cbenavides
 *
 */
@Local
public interface IReportesConciliacionFacade {

	/**
	 * Obtiene el reporte de los casos no aceptados por el conciliador
	 * 
	 * @param datosConsulta
	 * @return
	 */
	public List<CasosNoAceptadosDTO> casosNoAceptadosConciliador(FiltroReportesDTO datosConsulta);

	/**
	 * 
	 * @param datosConsulta
	 * @return
	 */
	public List<CasosRechazadosCompetenciaDTO> casosRechazadosCompetencia(FiltroReportesDTO datosConsulta);

	/**
	 * Realiza la consulta para el reporte: CON-C-039_SC-Casos-pagados-por-sede
	 * 
	 * @param datosConsulta
	 * @return
	 */
	public List<CasosPagadosDTO> casosPagadosPorSede(FiltroReportesDTO datosConsulta);

	/**
	 * Realiza la consulta de los casos cobrados CON-C-035
	 * 
	 * @param consulta
	 * @return
	 */
	public List<CasosCobradosDTO> casosCobrados(FiltroReportesDTO consulta);

	/**
	 * Realiza la consulta de los casos no cobrados CON-C-038
	 * 
	 * @param consulta
	 * @return
	 */
	public List<CasosCobradosDTO> casosNoCobrados(FiltroReportesDTO consulta);

	/**
	 * Realiza la consulta de las cartas junto con los demás datos que se
	 * encuentran en estado impreso y que además la audiencia para la cual
	 * fueron generadas se encuentre pendiente por realizar CON-C-053
	 * 
	 * @param idSede
	 *            sede sobre la cual se consulta
	 * @return la planilla generada
	 */
	public List<PlanillaCorrespondenciaDTO> planillaCorrespondencia(Long idSede);
	
	/**
	 * Consulta los casos que tengan un apoderado convocante y/o que pertenecan a un convenio CON-C-040
	 * 
	 * @param FiltroReportesDTO filtros, filtros genericos para consultas. 
	 *            
	 * @return List<CasosApoderadoConvenioDTO>
	 */
	public List<CasosApoderadoConvenioDTO> consultarCasosApoderadoConvenio( FiltroReportesDTO filtros );

	/**
	 * Consulta las audiencias sea por fechas o por idCaso.
	 * CON-C-032
	 * @author LRUIZ
	 * @param filtros
	 * @return
	 */
	public List<AudienciasPorFechasDTO> consultaAudienciasPorFechas(FiltroReportesDTO filtros);
	/**
	 * Consulta los casos sin cerrar por mas de 90 dias
	 * CON-C-042
	 * @author LRUIZ
	 * @return
	 */
	public List<CasosSinCerrar90DTO> consultaCasosSinCerrar90Dias(List<CentroDTO> centros);
	
	/**
	 * Consulta los casos a los cuales se les a hecho seguimiento en estado acuerdo
	 * CON-C-051
	 * @author LRUIZ
	 * @param filtros
	 * @return
	 */
	public List<SeguimientoCasosDTO> seguimientoCasosEnAcuerdo(FiltroReportesDTO filtros);
	/**
	 * Consulta los casos a los cuales se les ha generado devolucion de dinero.
	 * CON-C-044
	 * @author LRUIZ
	 * @param filtros
	 * @return
	 */
	public List<DevolucionDeDineroDTO> consultaDevolucionDeDinero(FiltroReportesDTO filtros);
	
	/**
	 * Consulta las partes de los casos conciliacion
	 * CON-C-048
	 * @author cagonzalez
	 * @param filtros
	 * @return
	 */
	public List<ReportePartesDeCasoDTO> consultarPartesDeCaso(FiltroReportesDTO filtros);

	/**
	 * Reporte de la facturacion de los casos de Convenio
	 * CON-C-043
	 * @param filtros
	 * @return
	 */
	public List<FacturacionCasosConvenioDTO> facturacionCasosConvenio(FiltroReportesDTO filtros);

	/**
	 * Reporte de los convenios
	 * CON-C-043
	 * @param filtros
	 * @return
	 */
	public List<ReporteConveniosDTO> reporteConvenios(FiltroReportesDTO filtros);
	
	/**
	 * Reporte de las asesorias
	 * CON-C-049
	 * @author LRUIZ
	 * @param filtros
	 * @return
	 */
	public List<AsesoriaDTO> reporteAsesorias(AsesoriaDTO filtros); 
	
	/**
	 * CON-C-047 : Devuelve la consulta con los conciliadores por sus listas y centros
	 * @author LRUIZ
	 * @return
	 */
	public List<ListaConciliadoresDTO> listaConciliadores(FiltroReportesDTO filtros);
	
	
	/**
	 * CON-C-034 : Devuelve la consulta con los casos cerrados diferentes a convenios
	 * @author cagonzalez
	 * @return
	 */
	public List<ReporteCasosCerradosConciliacionDTO> consultarCasosCerradosConciliacion(FiltroReportesDTO filtros);
	
	/**
	 * CON-C-033: Devuelve la consulta con los casos cerrados de convenios
	 * @param filtros
	 * @return List<ReporteCasosCerradosConveniosDTO>
	 */
	public List<ReporteCasosCerradosConveniosDTO> consultarCasosCerradosConvenios( FiltroReportesDTO filtros );
	
	/**
	 * CON-C-036: Devuelve la consulta con los casos devueltos en control de legalidad
	 * @param filtros
	 * @return List<ReporteCasosCerradosConveniosDTO>
	 */
	public List<ReporteCasosDevueltosControlLegalidadDTO> consultarCasosDevueltosControlLegalidad( FiltroReportesDTO filtros );
	
	/**
	 * CON-C-050: Reporte general de conciliacion
	 * @param filtros
	 * @return List<ReporteGeneralCasosConciliacionDTO>
	 */
	public List<ReporteGeneralCasosConciliacionDTO> consultarReporteGeneralConciliacion( FiltroReportesDTO filtros );
	
	/**
	 * CON-F-098
	 * @param idCentros
	 * @param tipoLibro
	 * @param anio
	 * @return List<ReporteActasConstanciasDTO>
	 */
	public List<ReporteActasConstanciasDTO> consultarReporteActasConstancias(String tipoLibro, Date anio, List<Long> idCentros);
	
	/**
	 * CON-C-045: Reporte evaluacion de conciliadores.
	 * @param filtros
	 * @return List<ReporteEvaluacionConciliadoresDTO>
	 */
	public List<ReporteEvaluacionConciliadoresDTO> consultarReporteEvaluacionConciliadores(FiltroReportesDTO filtros);
	
	/**
	 * CON-C-050: Reporte general de Insolvencia
	 * @param filtros
	 * @return List<ReporteGeneralCasosInsolvenciaDTO>
	 */
	public List<ReporteGeneralCasosInsolvenciaDTO> consultarReporteGeneralInsolvencia( FiltroReportesDTO filtros );
}
	