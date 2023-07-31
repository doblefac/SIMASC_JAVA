/**
 * 
 */
package com.ccb.simasc.negocio.fachadas.reportes.implementacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorPagoCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.reportes.ManejadorReportesConciliacion;
import com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones.AsesoriaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones.CorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones.TelefonoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones.UbicacionFacade;
import com.ccb.simasc.negocio.fachadas.reportes.interfaces.IReportesConciliacionFacade;
import com.ccb.simasc.transversal.dto.AsesoriaDTO;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.UbicacionDetalladaDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
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
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

/**
 * 
 * @author cbenavides
 *
 */
@Stateless
public class ReportesConciliacionFacade implements IReportesConciliacionFacade {

	/**
	 * 
	 */
	@EJB
	private ManejadorReportesConciliacion manejadorReportesConciliacion;

	@EJB
	private AsesoriaFacade asesoriaFacade;

	@EJB
	private TelefonoFacade telefonoFacade;

	@EJB
	private CorreoElectronicoFacade correoFacade;

	@EJB
	private UbicacionFacade ubicacionFacade;

	@EJB
	private ManejadorPagoCaso manejadorPagoCaso;

	@EJB
	private ManejadorPersona manejadorPersona;

	/**
	 * Recurso que retorna los casos de uso no aceptados por el conciliador
	 */
	@Override
	public List<CasosNoAceptadosDTO> casosNoAceptadosConciliador(FiltroReportesDTO datosConsulta) {
		return manejadorReportesConciliacion.casosNoAceptadosConciliador(datosConsulta);

	}

	/**
	 * Recurso que retorna el informe de los casos rechazados por competencia
	 */
	@Override
	public List<CasosRechazadosCompetenciaDTO> casosRechazadosCompetencia(FiltroReportesDTO datosConsulta) {
		return manejadorReportesConciliacion.casosRechazadosCompetencia(datosConsulta);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.reportes.interfaces.
	 * IReportesConciliacionFacade#casosPagadosPorSede(
	 * com.ccb.simasc.transversal.dto.reportes.DatosGeneralesConsultaDTO)
	 */
	@Override
	public List<CasosPagadosDTO> casosPagadosPorSede(FiltroReportesDTO datosConsulta) {
		return manejadorReportesConciliacion.casosPagadosPorSede(datosConsulta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.reportes.interfaces.
	 * IReportesConciliacionFacade#casosCobrados(com.ccb.simasc.transversal.dto.
	 * reportes.DatosGeneralesConsultaDTO)
	 */
	@Override
	public List<CasosCobradosDTO> casosCobrados(FiltroReportesDTO consulta) {
		return manejadorReportesConciliacion.consultarCasosCobrados(consulta, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.reportes.interfaces.
	 * IReportesConciliacionFacade#casosNoCobrados(com.ccb.simasc.transversal.
	 * dto.reportes.DatosGeneralesConsultaDTO)
	 */
	@Override
	public List<CasosCobradosDTO> casosNoCobrados(FiltroReportesDTO consulta) {
		return manejadorReportesConciliacion.consultarCasosCobrados(consulta, false);
	}

	@Override
	public List<PlanillaCorrespondenciaDTO> planillaCorrespondencia(Long idSede) {
		return manejadorReportesConciliacion.consultarPlanillaCorrespondencia(idSede);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.reportes.interfaces.
	 * IReportesConciliacionFacade#consultarCasosApoderadoConvenio(com.ccb.
	 * simasc.transversal. dto.reportes.DatosGeneralesConsultaDTO)
	 */
	@Override
	public List<CasosApoderadoConvenioDTO> consultarCasosApoderadoConvenio(FiltroReportesDTO filtros) {
		return manejadorReportesConciliacion.consultarCasosApoderadoConvenio(filtros);
	}

	@Override
	public List<AudienciasPorFechasDTO> consultaAudienciasPorFechas(FiltroReportesDTO filtros) {
		return manejadorReportesConciliacion.consultaAudienciasPorFechas(filtros);
	}

	@Override
	public List<CasosSinCerrar90DTO> consultaCasosSinCerrar90Dias(List<CentroDTO> centros) {
		List<Long> idCentros = new ArrayList<>();
		for (CentroDTO centro : centros) {
			idCentros.add(centro.getIdCentro());
		}
		String centrosIn = UtilConsultasSQL.clausulaInSQLSNumeros(idCentros);
		return manejadorReportesConciliacion.consultaCasosSinCerrar90Dias(centrosIn);
	}

	@Override
	public List<SeguimientoCasosDTO> seguimientoCasosEnAcuerdo(FiltroReportesDTO filtros) {
		List<String> idCentros = new ArrayList<>();
		for (CentroDTO centro : filtros.getIdCentros()) {
			idCentros.add(Long.toString(centro.getIdCentro()));
		}
		filtros.setCentros(idCentros);
		return manejadorReportesConciliacion.seguimientoCasosEnAcuerdo(filtros);
	}

	@Override
	public List<DevolucionDeDineroDTO> consultaDevolucionDeDinero(FiltroReportesDTO filtros) {
		List<String> idCentros = new ArrayList<>();
		for (CentroDTO centro : filtros.getIdCentros()) {
			idCentros.add(Long.toString(centro.getIdCentro()));
		}
		filtros.setCentros(idCentros);
		return manejadorReportesConciliacion.consultaDevolucionDeDinero(filtros);
	}

	@Override
	public List<ReportePartesDeCasoDTO> consultarPartesDeCaso(FiltroReportesDTO filtros) {

		List<Long> idCentros = new ArrayList<Long>();
		List<CentroDTO> centros = filtros.getIdCentros();
		for (int i = 0; i < centros.size(); i++) {
			idCentros.add(centros.get(i).getIdCentro());
		}
		return manejadorReportesConciliacion.consultarPartesDeCaso(filtros, idCentros);
	}

	@Override
	public List<FacturacionCasosConvenioDTO> facturacionCasosConvenio(FiltroReportesDTO filtros) {
		return manejadorReportesConciliacion.facturacionCasosConvenio(filtros);
	}

	@Override
	public List<ReporteConveniosDTO> reporteConvenios(FiltroReportesDTO filtros) {
		return manejadorReportesConciliacion.reporteConvenios(filtros);
	}

	@Override
	public List<AsesoriaDTO> reporteAsesorias(AsesoriaDTO filtros) {
		List<AsesoriaDTO> asesorias = asesoriaFacade.consultaAsesorias(filtros);
		List<AsesoriaDTO> reporteAsesorias = new ArrayList<>();
		if (!asesorias.isEmpty()) {
			for (AsesoriaDTO asesoria : asesorias) {
				Persona persona = manejadorPersona.buscar(asesoria.getIdPersona());
				List<GenericoDTO> telefonos = telefonoFacade.consultarTelefonosPersona(asesoria.getIdPersona());
				CorreoElectronico correo = correoFacade.traerPrimerCorreoPrimario(asesoria.getIdPersona());
				List<UbicacionDetalladaDTO> ubicaciones = ubicacionFacade
						.ubicacionDetallaByIdpersona(asesoria.getIdPersona());
				asesoria.setRadicaCaso(radicaCasoAsesoria(asesoria.getFechaAsesoria(), persona));
				asesoria.setTelefono(!telefonos.isEmpty() ? telefonos.get(0).getNombre() : "");
				asesoria.setEmail(correo != null ? correo.getDireccion() : "");
				asesoria.setDireccion(!ubicaciones.isEmpty() ? ubicaciones.get(0).getDireccion() : "");
				reporteAsesorias.add(asesoria);
			}
		}
		return reporteAsesorias;
	}

	private String radicaCasoAsesoria(Date fechaAsesoria, Persona persona) {
		String radica = "";
		if (persona.getNumeroDocumento() != null && !persona.getNumeroDocumento().isEmpty()) {
			List<PagoCaso> pagoCasos = manejadorPagoCaso.obtenerPagosCasosPorfiltros(fechaAsesoria,
					persona.getTipoDocumento(), persona.getNumeroDocumento());
			if (!pagoCasos.isEmpty())
				radica = UtilConstantes.SI;
			else
				radica = UtilConstantes.NO;
		} else
			radica = UtilConstantes.NO;
		return radica;
	}

	@Override
	public List<ListaConciliadoresDTO> listaConciliadores(FiltroReportesDTO filtros) {
		List<Long> idCentros = new ArrayList<Long>();
		List<CentroDTO> centros = filtros.getIdCentros();
		for (int i = 0; i < centros.size(); i++) {
			idCentros.add(centros.get(i).getIdCentro());
		}
		return manejadorReportesConciliacion.listaConciliadores(filtros.getIdLista(), idCentros);
	}
	
	@Override
	public List<ReporteCasosCerradosConciliacionDTO> consultarCasosCerradosConciliacion(FiltroReportesDTO filtros) {
		List<String> roles = Arrays.asList(UtilDominios.ROL_PERSONA_CONVOCANTE,UtilDominios.ROL_PERSONA_CONVOCADO);
		return manejadorReportesConciliacion.consultarCasosCerradosConciliacion(filtros,roles);
	}
	
	/*
	 * @see com.ccb.simasc.negocio.fachadas.reportes.interfaces.
	 * IReportesConciliacionFacade#consultarCasosCerradosConvenios(
	 * com.ccb.simasc.transversal.dto.reportes.ReporteCasosCerradosConveniosDTO)
	 */
	@Override
	public List<ReporteCasosCerradosConveniosDTO> consultarCasosCerradosConvenios( FiltroReportesDTO filtros ){
		return manejadorReportesConciliacion.consultarCasosCerradosConvenios( filtros );
	}

	/*
	 * @see com.ccb.simasc.negocio.fachadas.reportes.interfaces.
	 * IReportesConciliacionFacade#consultarCasosDevueltosControlLegalidad(
	 * com.ccb.simasc.transversal.dto.reportes.ReporteCasosDevueltosControlLegalidadDTO)
	 */
	@Override
	public List<ReporteCasosDevueltosControlLegalidadDTO> consultarCasosDevueltosControlLegalidad( FiltroReportesDTO filtros ){
		return manejadorReportesConciliacion.consultarCasosDevueltosControlLegalidad( filtros );
	}
	
	/*
	 * @see com.ccb.simasc.negocio.fachadas.reportes.interfaces.
	 * IReportesConciliacionFacade#consultarReporteGeneralConciliacion(
	 * com.ccb.simasc.transversal.dto.reportes.ReporteGeneralCasosConciliacionDTO)
	 */
	@Override
	public List<ReporteGeneralCasosConciliacionDTO> consultarReporteGeneralConciliacion( FiltroReportesDTO filtros ){
		return manejadorReportesConciliacion.consultarReporteGeneralConciliacion( filtros );
	}

	@Override
	public List<ReporteEvaluacionConciliadoresDTO> consultarReporteEvaluacionConciliadores(FiltroReportesDTO filtros) {
		return manejadorReportesConciliacion.consultarReporteEvaluacionConciliadores(filtros);
	}
	
	/*
	 * @see com.ccb.simasc.negocio.fachadas.reportes.interfaces.
	 * IReportesConciliacionFacade#consultarReporteActasConstancias(
	 * com.ccb.simasc.transversal.dto.reportes.ReporteActasConstanciasDTO)
	 */
	@Override
	public List<ReporteActasConstanciasDTO> consultarReporteActasConstancias(String tipoLibro, Date anio, List<Long> idCentros){
		
		return manejadorReportesConciliacion.consultarReporteActasConstancias(tipoLibro, anio, idCentros, Arrays.asList(UtilDominios.RESULTADO_CASO_CONCILIACION_ACUERDO,
				UtilDominios.RESULTADO_CASO_CONCILIACION_IMPOSIBILIDAD, UtilDominios.RESULTADO_CASO_CONCILIACION_INASISTENCIA));
	}

	@Override
	public List<ReporteGeneralCasosInsolvenciaDTO> consultarReporteGeneralInsolvencia(FiltroReportesDTO filtros) {
		List<ReporteGeneralCasosInsolvenciaDTO> reporteInsolvencia = manejadorReportesConciliacion.consultarReporteGeneralInsolvencia(filtros);
		for(ReporteGeneralCasosInsolvenciaDTO reporteinsolvencia : reporteInsolvencia) {
			List<PersonaBasicaDTO> acreedores = manejadorPersona.consultarAcreedorerPorCaso(reporteinsolvencia.getIdCaso());
			
			String nombres = "";
			String tipoDocumentos ="";
			String numeroDocumentoAcreedores="";
			String emailAcreedores="";
			String telefonoAcreedores="";
			String direccionAcreedores="";
			
			for(PersonaBasicaDTO acreedor : acreedores) {
				if(acreedor.getNombreCompleto()!= null)nombres = nombres +acreedor.getNombreCompleto() +',';
				else nombres = nombres +' ' +',';
				if(acreedor.getTipoDocumento()!= null)tipoDocumentos = tipoDocumentos +acreedor.getTipoDocumento()+',';
				else tipoDocumentos = tipoDocumentos +' ' +',';
				if(acreedor.getNumeroDocumento()!= null)numeroDocumentoAcreedores = numeroDocumentoAcreedores +acreedor.getNumeroDocumento() +',';
				else numeroDocumentoAcreedores = numeroDocumentoAcreedores +' ' +',';
				if(acreedor.getCorreoElectronico()!= null)emailAcreedores = emailAcreedores +acreedor.getCorreoElectronico() +',';
				else emailAcreedores = emailAcreedores +' ' +',';
				if(acreedor.getTelefono()!= null)telefonoAcreedores = telefonoAcreedores +acreedor.getTelefono() +',';
				else telefonoAcreedores = telefonoAcreedores +' ' +',';
				if(acreedor.getDireccion()!= null)direccionAcreedores = direccionAcreedores +acreedor.getDireccion() +',';
				else direccionAcreedores = direccionAcreedores +' ' +',';
			}
			reporteinsolvencia.setNombreAcreedores(nombres);
			reporteinsolvencia.setTipoDocumentoAcreedores(tipoDocumentos);
			reporteinsolvencia.setDireccionAcreedores(direccionAcreedores);
			reporteinsolvencia.setEmailAcreedores(emailAcreedores);
			reporteinsolvencia.setNumeroDocumentoAcreedores(numeroDocumentoAcreedores);
			reporteinsolvencia.setTelefonoAcreedores(telefonoAcreedores);
			
			reporteinsolvencia.setConciliadorAsignado(manejadorPersona.consultarConciliadorPorCaso(reporteinsolvencia.getIdCaso()));
		}
		return reporteInsolvencia;
	}
	
	
	
}
