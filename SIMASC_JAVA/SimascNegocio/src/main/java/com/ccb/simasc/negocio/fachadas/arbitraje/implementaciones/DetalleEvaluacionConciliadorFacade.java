package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorDesarrolloProfesional;
import com.ccb.simasc.integracion.manejadores.ManejadorDetalleEvaluacionConciliador;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorEventoCcb;
import com.ccb.simasc.integracion.manejadores.ManejadorPeticion;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDetalleEvaluacionConciliadorFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEvaluacionConciliadorFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.DetalleEvaluacionConciliadorDTO;
import com.ccb.simasc.transversal.entidades.DesarrolloProfesional;
import com.ccb.simasc.transversal.entidades.DetalleEvaluacionConciliador;
import com.ccb.simasc.transversal.entidades.DetalleEvaluacionConciliadorPK;
import com.ccb.simasc.transversal.entidades.EvaluacionConciliador;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link DetalleEvaluacionConciliador}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class DetalleEvaluacionConciliadorFacade extends AccesoFacade<DetalleEvaluacionConciliador, DetalleEvaluacionConciliadorPK, DetalleEvaluacionConciliadorDTO> implements IDetalleEvaluacionConciliadorFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorDetalleEvaluacionConciliador manejadorDetalleEvaluacionConciliador;
	
	@EJB
	private ManejadorCaso manejadorCaso;
	
	@EJB
	private ManejadorDocumento manejadorDocumento;
	
	@EJB
	private IEvaluacionConciliadorFacade evaluacionConciliadorFacade;
	
	@EJB
	private ManejadorDesarrolloProfesional manejadorDesarrolloProfesional;
	
	@EJB
	private ManejadorConvenio manejadorConvenio;
	
	@EJB
	private ManejadorEventoCcb manejadorEventoCcb;
	
	@EJB
	private ManejadorAudiencia manejadorAudiencia;
	
	@EJB
	private ManejadorPeticion manejadorPeticion;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorDetalleEvaluacionConciliador;
	}

	@Override
	public DetalleEvaluacionConciliadorDTO transformarSinDependencias(DetalleEvaluacionConciliador obj) {
		return new DetalleEvaluacionConciliadorDTO().transformarSinDependencias(obj);
	}

	@Override
	public DetalleEvaluacionConciliadorDTO transformarConDependencias(DetalleEvaluacionConciliador obj) {
		return new DetalleEvaluacionConciliadorDTO().transformarConDependencias(obj);
	}

	@Override
	public DetalleEvaluacionConciliador transformarEntidadConDependencias(DetalleEvaluacionConciliador obj) {
		return new DetalleEvaluacionConciliadorDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public DetalleEvaluacionConciliador transformarEntidadSinDependencias(DetalleEvaluacionConciliador obj) {
		return new DetalleEvaluacionConciliadorDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IDetalleEvaluacionConciliadorFacade#calcularCriterio(java.lang.Long,
	 * java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public List<DetalleEvaluacionConciliador> calcularCriterio(Long idPersona, Date periodoDesde, Date periodoHasta,
			String codigoCriterio) {
		EvaluacionConciliador evaluacionConciliador;
		List<DetalleEvaluacionConciliador> detalles;
		List<EvaluacionConciliador> evaluacion = evaluacionConciliadorFacade.consultarEvaluacionConciliador(idPersona,
				periodoDesde, periodoHasta);
		if (evaluacion.isEmpty()) {
			evaluacionConciliador = evaluacionConciliadorFacade.actualizarEvaluacion(null, idPersona, periodoDesde,
					periodoHasta);
		} else {
			evaluacionConciliador = evaluacion.get(0);
		}
		detalles = calculoTotales(evaluacionConciliador.getIdEvaluacionConciliador(), idPersona, periodoDesde,
				periodoHasta, codigoCriterio);

		return detalles;
	}

	/**
	 * Método que realiza el llamado a los calculoe de los totales dependiendo
	 * del criterio
	 * 
	 * @param idEvaluacion
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @param codigoCriterio
	 * @return
	 */
	private List<DetalleEvaluacionConciliador> calculoTotales(Long idEvaluacion, Long idPersona, Date periodoDesde,
			Date periodoHasta, String codigoCriterio) {
		List<DetalleEvaluacionConciliador> detalles; 
//		= (List<DetalleEvaluacionConciliador>) transformarEntidadesColeccionSinDependencias(
//				manejadorDetalleEvaluacionConciliador.consultarDetallesPorCriterio(codigoCriterio, idEvaluacion),
//				new ArrayList<DetalleEvaluacionConciliador>());
//		if (detalles.isEmpty()) {
		switch (codigoCriterio) {
			case UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_CALIDAD:
				detalles = (List<DetalleEvaluacionConciliador>) transformarEntidadesColeccionSinDependencias(
						calcularCriterioCalidad(idEvaluacion, idPersona, periodoDesde, periodoHasta),
						new ArrayList<DetalleEvaluacionConciliador>());
				break;
			case UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_EDUCACION_CON:
				detalles = (List<DetalleEvaluacionConciliador>) transformarEntidadesColeccionSinDependencias(
						calcularCriterioEducacionContinua(idEvaluacion, idPersona, periodoDesde, periodoHasta),
						new ArrayList<DetalleEvaluacionConciliador>());
				break;
			case UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PARTICIPACION:
				detalles = (List<DetalleEvaluacionConciliador>) transformarEntidadesColeccionSinDependencias(
						calcularCriterioParticipacion(idEvaluacion, idPersona, periodoDesde, periodoHasta),
						new ArrayList<DetalleEvaluacionConciliador>());
				break;
			default:
				detalles = (List<DetalleEvaluacionConciliador>) transformarEntidadesColeccionSinDependencias(
						calcularCriterioProcedimientos(idEvaluacion, idPersona, periodoDesde, periodoHasta),
						new ArrayList<DetalleEvaluacionConciliador>());
				break;
		}
//		}
		return detalles;
	}

	/**
	 * Método que realiza las operaciones necesarios para la evaluación del
	 * criterio de calidad de un conciliador
	 * 
	 * @param idEvaluacion
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @return
	 */
	private List<DetalleEvaluacionConciliador> calcularCriterioCalidad(Long idEvaluacion, Long idPersona,
			Date periodoDesde, Date periodoHasta) {
		List<DetalleEvaluacionConciliador> detalles = new ArrayList<DetalleEvaluacionConciliador>();
		calcularTotalesAcuerdos(detalles, idEvaluacion, idPersona, periodoDesde,
				periodoHasta);
		calculcarTotalesNoDevueltos(detalles, idEvaluacion, idPersona, periodoDesde,
				periodoHasta);

		return detalles;
	}

	/**
	 * Método que realiza el calculo de los totales correspondientes a el
	 * porcentaje de acuerdos del criterio de calidad
	 * 
	 * @param detalles
	 * @param idEvaluacion
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @return
	 */
	private BigDecimal calcularTotalesAcuerdos(List<DetalleEvaluacionConciliador> detalles, Long idEvaluacion,
			Long idPersona, Date periodoDesde, Date periodoHasta) {
		BigDecimal casosLlevados = manejadorCaso.cantidadCasosConciliadorPorEstado(null, idPersona, periodoDesde,
				periodoHasta);
		detalles.add(actualizarDetalleEvaluacion(casosLlevados, UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_CALIDAD,
				UtilDominios.CRITERIO_CALIDAD_TOTAL_CASOS, idEvaluacion, UtilDominios.ESTADO_REGISTRO_ACTIVO));
		BigDecimal acuerdos = manejadorCaso.cantidadCasosConciliadorPorEstado(
				UtilDominios.RESULTADO_CASO_CONCILIACION_ACUERDO, idPersona, periodoDesde, periodoHasta);
		detalles.add(actualizarDetalleEvaluacion(acuerdos, UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_CALIDAD,
				UtilDominios.CRITERIO_CALIDAD_TOTAL_ACUERDOS, idEvaluacion, UtilDominios.ESTADO_REGISTRO_ACTIVO));
		BigDecimal arregloDirecto = manejadorCaso.cantidadCasosConciliadorPorEstado(
				UtilDominios.RESULTADO_CASO_CONCILIACION_ARREGLO_DIRECTO, idPersona, periodoDesde, periodoHasta);
		detalles.add(actualizarDetalleEvaluacion(arregloDirecto, UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_CALIDAD,
				UtilDominios.CRITERIO_CALIDAD_TOTAL_ARREGLOS, idEvaluacion, UtilDominios.ESTADO_REGISTRO_ACTIVO));
		BigDecimal imposibilidad = manejadorCaso.cantidadCasosConciliadorPorEstado(
				UtilDominios.RESULTADO_CASO_CONCILIACION_IMPOSIBILIDAD, idPersona, periodoDesde, periodoHasta);
		detalles.add(actualizarDetalleEvaluacion(imposibilidad, UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_CALIDAD,
				UtilDominios.CRITERIO_CALIDAD_TOTAL_IMPOSIBILIDAD, idEvaluacion, UtilDominios.ESTADO_REGISTRO_ACTIVO));
		BigDecimal porcentajeAcuerdos;
		if (casosLlevados.longValue() > 0)
			porcentajeAcuerdos = new BigDecimal(acuerdos.longValue() * 100 / casosLlevados.longValue());
		else
			porcentajeAcuerdos = new BigDecimal(0);
		detalles.add(actualizarDetalleEvaluacion(porcentajeAcuerdos.setScale(2, RoundingMode.DOWN),
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_CALIDAD, UtilDominios.CRITERIO_CALIDAD_PORCENTAJE_ACUERDOS,
				idEvaluacion, UtilDominios.ESTADO_REGISTRO_ACTIVO));

		return porcentajeAcuerdos;
	}

	/**
	 * Método que realiza el calculo de los totales correspondientes a el
	 * porcentaje de documentos no devueltos del criterio de calidad
	 * 
	 * @param detalles
	 * @param idEvaluacion
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @return
	 */
	private BigDecimal calculcarTotalesNoDevueltos(List<DetalleEvaluacionConciliador> detalles, Long idEvaluacion,
			Long idPersona, Date periodoDesde, Date periodoHasta) {
		BigDecimal actasGeneradas = manejadorDocumento.obtenerTotalesCriterioCalidad(false, periodoDesde, periodoHasta,
				idPersona);
		detalles.add(actualizarDetalleEvaluacion(actasGeneradas, UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_CALIDAD,
				UtilDominios.CRITERIO_CALIDAD_TOTAL_ACTAS_GENERADAS, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		BigDecimal documentosDevueltos = manejadorDocumento.obtenerTotalesCriterioCalidad(true, periodoDesde,
				periodoHasta, idPersona);
		detalles.add(actualizarDetalleEvaluacion(documentosDevueltos,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_CALIDAD, UtilDominios.CRITERIO_CALIDAD_TOTAL_DEVOLUCIONES,
				idEvaluacion, UtilDominios.ESTADO_REGISTRO_ACTIVO));
		BigDecimal porcentajeNoDevueltos;
		if (actasGeneradas.longValue() > 0)
			porcentajeNoDevueltos = new BigDecimal(
					actasGeneradas.subtract(documentosDevueltos).longValue() * 100 / actasGeneradas.longValue());
		else
			porcentajeNoDevueltos = new BigDecimal(0);
		detalles.add(actualizarDetalleEvaluacion(porcentajeNoDevueltos.setScale(2, RoundingMode.DOWN),
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_CALIDAD,
				UtilDominios.CRITERIO_CALIDAD_PORCENTAJE_NO_DEVUELTOS, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		return porcentajeNoDevueltos;
	}

	/**
	 * Método que realiza las operaciones necesarios para la evaluación del
	 * criterio de educación continua de un conciliador
	 * 
	 * @param idEvaluacion
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @return
	 */
	private List<DetalleEvaluacionConciliador> calcularCriterioEducacionContinua(Long idEvaluacion, Long idPersona,
			Date periodoDesde, Date periodoHasta) {
		List<DetalleEvaluacionConciliador> detalles = new ArrayList<DetalleEvaluacionConciliador>();
		List<DesarrolloProfesional> cursos = manejadorDesarrolloProfesional.consultarDesarrolloProfesional(idPersona,
				UtilDominios.CODIGO_TIPO_DESARROLLO_PROFESIONAL_CURSO, periodoDesde, periodoHasta,null,null);
		Integer horasCursadas = 0;
		for (DesarrolloProfesional desarrolloProfesional : cursos) {
			horasCursadas += desarrolloProfesional.getNumeroHoras();
		}
		BigDecimal porcentajeTotal = new BigDecimal(horasCursadas * 100 / 80);
		if (porcentajeTotal.doubleValue() > 100)
			porcentajeTotal = new BigDecimal(100);
		else
			porcentajeTotal = porcentajeTotal.setScale(2, RoundingMode.DOWN);
		
		detalles.add(actualizarDetalleEvaluacion(porcentajeTotal,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_EDUCACION_CON,
				UtilDominios.CRITERIO_PORCENTAJE_TOTAL, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		
		return detalles;
	}

	/**
	 * Método que realiza las operaciones necesarios para la evaluación del
	 * criterio de participación de un conciliador
	 * 
	 * @param idEvaluacion
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @return
	 */
	private List<DetalleEvaluacionConciliador> calcularCriterioParticipacion(Long idEvaluacion, Long idPersona,
			Date periodoDesde, Date periodoHasta) {
		List<DetalleEvaluacionConciliador> detalles = new ArrayList<DetalleEvaluacionConciliador>();
		calcularPorcentajeAsistenciaEventos(idEvaluacion, idPersona, periodoDesde, periodoHasta, detalles);
		calcularPorcentajeAsistenciaJornadas(idEvaluacion, idPersona, periodoDesde, periodoHasta, detalles);
		
		return detalles;
	}
	
	/**
	 * Método que realiza el calculo de los totales correspondientes al
	 * porcentaje de asistencia a comités y talleres
	 * 
	 * @param idEvaluacion
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @param detalles
	 * @return
	 */
	private BigDecimal calcularPorcentajeAsistenciaEventos(Long idEvaluacion, Long idPersona,
			Date periodoDesde, Date periodoHasta, List<DetalleEvaluacionConciliador> detalles) {
		BigDecimal comitesTalleresProgramados = manejadorEventoCcb.obtenerCursosEvaluacion(periodoDesde, periodoHasta,
				null,
				Arrays.asList(UtilDominios.TIPO_ACTIVIDAD_AGENDA_CAPACITACION, UtilDominios.TIPO_EVENTO_CCB_COMITE));
		detalles.add(actualizarDetalleEvaluacion(comitesTalleresProgramados,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PARTICIPACION,
				UtilDominios.CRITERIO_PARTICIPACION_TOTAL_EVENTOS, idEvaluacion, UtilDominios.ESTADO_REGISTRO_ACTIVO));
		BigDecimal comitesTalleresAsistio = manejadorEventoCcb.obtenerCursosEvaluacion(periodoDesde, periodoHasta,
				idPersona,
				Arrays.asList(UtilDominios.TIPO_ACTIVIDAD_AGENDA_CAPACITACION, UtilDominios.TIPO_EVENTO_CCB_COMITE));
		detalles.add(actualizarDetalleEvaluacion(comitesTalleresAsistio,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PARTICIPACION,
				UtilDominios.CRITERIO_PARTICIPACION_TOTAL_EVENTOS_ASISTIO, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		BigDecimal porcentajeEventosAsistio;
		if (comitesTalleresProgramados.longValue() > 0)
			porcentajeEventosAsistio = new BigDecimal(
					comitesTalleresAsistio.longValue() * 100 / comitesTalleresProgramados.longValue()).setScale(2,
							RoundingMode.DOWN);
		else
			porcentajeEventosAsistio = new BigDecimal(0);

		detalles.add(actualizarDetalleEvaluacion(porcentajeEventosAsistio,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PARTICIPACION,
				UtilDominios.CRITERIO_PARTICIPACION_PORCENTAJE_EVENTO_ASISTENCIA, idEvaluacion, UtilDominios.ESTADO_REGISTRO_ACTIVO));

		return porcentajeEventosAsistio;
	}
	
	/**
	 * Método que realiza el calculo de los totales correspondientes al
	 * porcentaje de asistencia a jornadas
	 * 
	 * @param idEvaluacion
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @param detalles
	 * @return
	 */
	private BigDecimal calcularPorcentajeAsistenciaJornadas(Long idEvaluacion, Long idPersona,
			Date periodoDesde, Date periodoHasta, List<DetalleEvaluacionConciliador> detalles) {
		BigDecimal jornadasProgramadas = manejadorConvenio.obtenerJornadasProgramadas(periodoDesde, periodoHasta, null);
		detalles.add(actualizarDetalleEvaluacion(jornadasProgramadas,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PARTICIPACION,
				UtilDominios.CRITERIO_PARTICIPACION_TOTAL_JORNADAS_PROGRAMADAS, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		BigDecimal jornadasAsistio = manejadorConvenio.obtenerJornadasProgramadas(periodoDesde, periodoHasta,
				idPersona);
		detalles.add(
				actualizarDetalleEvaluacion(jornadasAsistio, UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PARTICIPACION,
						UtilDominios.CRITERIO_PARTICIPACION_TOTAL_JORNADAS_ASISTIO, idEvaluacion,
						UtilDominios.ESTADO_REGISTRO_ACTIVO));
		BigDecimal porcentajeJornadasAsistio;
		if (jornadasProgramadas.longValue() > 0)
			porcentajeJornadasAsistio = new BigDecimal(
					jornadasAsistio.longValue() * 100 / jornadasProgramadas.longValue()).setScale(2, RoundingMode.DOWN);
		else
			porcentajeJornadasAsistio = new BigDecimal(0);

		detalles.add(
				actualizarDetalleEvaluacion(porcentajeJornadasAsistio, UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PARTICIPACION,
						UtilDominios.CRITERIO_PARTICIPACION_PORCENTAJE_JORNADA_ASISTENCIA, idEvaluacion,
						UtilDominios.ESTADO_REGISTRO_ACTIVO));
		return porcentajeJornadasAsistio;
	}

	/**
	 * Método que realiza las operaciones necesarios para la evaluación del
	 * criterio de procedimientos de un conciliador
	 * 
	 * @param idEvaluacion
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @return
	 */
	private List<DetalleEvaluacionConciliador> calcularCriterioProcedimientos(Long idEvaluacion, Long idPersona,
			Date periodoDesde, Date periodoHasta) {
		List<DetalleEvaluacionConciliador> detalles = new ArrayList<DetalleEvaluacionConciliador>();
		calcularPorcentajeAsistenciaAudiencias(idEvaluacion, idPersona, periodoDesde, periodoHasta, detalles);
		calcularPorcentajeRespuestasEmitidas(idEvaluacion, idPersona, periodoDesde, periodoHasta, detalles);
		calcularPorcentajeCumplimientoCierreCasos(idEvaluacion, idPersona, periodoDesde, periodoHasta, detalles);
		
		return detalles;
	}
	
	/**
	 * Método que calcula los totales del porcentaje de asistencia a audiencias
	 * @param idEvaluacion
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @param detalles
	 * @return porcentaje de asistencia a audiencias
	 */
	private BigDecimal calcularPorcentajeAsistenciaAudiencias(Long idEvaluacion, Long idPersona,
			Date periodoDesde, Date periodoHasta, List<DetalleEvaluacionConciliador> detalles) {
		BigDecimal totalAudienciasProgramadas = manejadorAudiencia.consultarAudienciasProgramadas(idPersona,
				periodoDesde, periodoHasta, false, false);
		detalles.add(actualizarDetalleEvaluacion(totalAudienciasProgramadas,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PROCEDIMIENTOS,
				UtilDominios.CRITERIO_PROCEDIMIENTOS_AUDIENCIAS_PROGRAMADAS, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		BigDecimal audienciasIncumplioHorario = manejadorAudiencia.consultarAudienciasProgramadas(idPersona,
				periodoDesde, periodoHasta, true, false);
		detalles.add(actualizarDetalleEvaluacion(audienciasIncumplioHorario,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PROCEDIMIENTOS,
				UtilDominios.CRITERIO_PROCEDIMIENTOS_AUDIENCIAS_INCUMPLIO, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		BigDecimal audienciasJustificacionValida = manejadorAudiencia.consultarAudienciasProgramadas(idPersona,
				periodoDesde, periodoHasta, true, true);
		detalles.add(actualizarDetalleEvaluacion(audienciasJustificacionValida,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PROCEDIMIENTOS,
				UtilDominios.CRITERIO_PROCEDIMIENTOS_AUDIENCIAS_JUSTIFICACION, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		
		BigDecimal porcentajeAsistenciaAudiencias;
		if (totalAudienciasProgramadas.longValue() > 0)
			porcentajeAsistenciaAudiencias = new BigDecimal((totalAudienciasProgramadas.longValue()
					- (audienciasIncumplioHorario.longValue() - audienciasJustificacionValida.longValue())) * 100
					/ totalAudienciasProgramadas.longValue()).setScale(2, RoundingMode.DOWN);
		else
			porcentajeAsistenciaAudiencias = new BigDecimal(0);
		detalles.add(actualizarDetalleEvaluacion(porcentajeAsistenciaAudiencias,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PROCEDIMIENTOS,
				UtilDominios.CRITERIO_PROCEDIMIENTOS_PORCENTAJE_ASIS_AUDIENCIAS, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		return porcentajeAsistenciaAudiencias;
	}
	
	/**
	 * Método que calcula los totales del porcentaje de respuestas emitidas oportunamente
	 * @param idEvaluacion
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @param detalles
	 * @return porcentaje de respuestas emitidas oportunamente
	 */
	private BigDecimal calcularPorcentajeRespuestasEmitidas(Long idEvaluacion, Long idPersona,
			Date periodoDesde, Date periodoHasta, List<DetalleEvaluacionConciliador> detalles) {
		BigDecimal totalPeticiones = manejadorPeticion.consultarPeticiones(idPersona, periodoDesde, periodoHasta, false,
				Arrays.asList(UtilDominios.TIPO_PETICION_CAMBIO_FECHA,
						UtilDominios.TIPO_PETICION_CAMBIO_INCUMPLIMIENTO));
		detalles.add(actualizarDetalleEvaluacion(totalPeticiones,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PROCEDIMIENTOS,
				UtilDominios.CRITERIO_PROCEDIMIENTOS_PETICIONES_ESPECIALES, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		
		BigDecimal respuestasOportunas = manejadorPeticion.consultarPeticiones(idPersona, periodoDesde, periodoHasta, true,
				Arrays.asList(UtilDominios.TIPO_PETICION_CAMBIO_FECHA,
						UtilDominios.TIPO_PETICION_CAMBIO_INCUMPLIMIENTO));
		detalles.add(actualizarDetalleEvaluacion(respuestasOportunas,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PROCEDIMIENTOS,
				UtilDominios.CRITERIO_PROCEDIMIENTOS_RESPUESTAS_EMITIDAS, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		
		BigDecimal porcentajeRespuestasEmitidas = null;
		if(totalPeticiones.longValue() > 0)
			porcentajeRespuestasEmitidas = new BigDecimal(respuestasOportunas.longValue() * 100
					/ totalPeticiones.longValue()).setScale(2, RoundingMode.DOWN);
		else
			porcentajeRespuestasEmitidas = new BigDecimal(0);
		detalles.add(actualizarDetalleEvaluacion(porcentajeRespuestasEmitidas,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PROCEDIMIENTOS,
				UtilDominios.CRITERIO_PROCEDIMIENTOS_PORCENTAJE_RESPUESTAS, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		
		return porcentajeRespuestasEmitidas;
	}
	
	/**
	 * Método que obtiene los totales para el porcentaje de cumplimiento de cierre de casos
	 * @param idEvaluacion
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @param detalles
	 * @return el porcentaje de cumplimiento
	 */
	private BigDecimal calcularPorcentajeCumplimientoCierreCasos(Long idEvaluacion, Long idPersona,
			Date periodoDesde, Date periodoHasta, List<DetalleEvaluacionConciliador> detalles) {
		BigDecimal totalCasosAceptados = manejadorRolPersonaCaso.cantidadCasosAceptadosPorConciliador(idPersona,
				periodoDesde, periodoHasta);
		detalles.add(actualizarDetalleEvaluacion(totalCasosAceptados,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PROCEDIMIENTOS,
				UtilDominios.CRITERIO_PROCEDIMIENTOS_CASOS_ACEPTADOS, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		
		BigDecimal casosCerradosAntes = manejadorCaso.cantidadCasosNoCerradosConSolicitud(idPersona, periodoDesde,
				periodoHasta, true);
		detalles.add(actualizarDetalleEvaluacion(casosCerradosAntes,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PROCEDIMIENTOS,
				UtilDominios.CRITERIO_PROCEDIMIENTOS_CASOS_CERRADOS, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		
		BigDecimal casosProrroga = manejadorCaso.cantidadCasosNoCerradosConSolicitud(idPersona, periodoDesde,
				periodoHasta, false);
		detalles.add(actualizarDetalleEvaluacion(casosProrroga,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PROCEDIMIENTOS,
				UtilDominios.CRITERIO_PROCEDIMIENTOS_CASOS_SOLICITUD, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		
		BigDecimal porcentajeCumplimientoCierre;
		if (totalCasosAceptados.longValue() > 0)
			porcentajeCumplimientoCierre = new BigDecimal((casosCerradosAntes.longValue() + casosProrroga.longValue())
					* 100 / totalCasosAceptados.longValue()).setScale(2, RoundingMode.DOWN);
		else
			porcentajeCumplimientoCierre = new BigDecimal(0);
		detalles.add(actualizarDetalleEvaluacion(porcentajeCumplimientoCierre,
				UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PROCEDIMIENTOS,
				UtilDominios.CRITERIO_PROCEDIMIENTOS_PORCENTAJE_CIERRE, idEvaluacion,
				UtilDominios.ESTADO_REGISTRO_ACTIVO));
		
		return porcentajeCumplimientoCierre;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IDetalleEvaluacionConciliadorFacade#actualizarDetalleEvaluacion(java.math
	 * .BigDecimal, java.lang.String, java.lang.String, java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public DetalleEvaluacionConciliador actualizarDetalleEvaluacion(BigDecimal valor, String codigoCriterio,
			String codigoTotal, Long idEvaluacion, String estadoRegistro) {
		DetalleEvaluacionConciliadorPK pk = new DetalleEvaluacionConciliadorPK(idEvaluacion, codigoCriterio,
				codigoTotal);
		DetalleEvaluacionConciliador detalle = buscar(pk);
		if (detalle != null) {
			detalle.setValor(valor);
			detalle.setEstadoRegistro(estadoRegistro);
			actualizar(detalle);
		} else {
			detalle = new DetalleEvaluacionConciliador();
			detalle.setDetalleEvaluacionConciliadorPK(pk);
			detalle.setEstadoRegistro(estadoRegistro);
			detalle.setValor(valor);
			crear(detalle);
		}

		return detalle;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IDetalleEvaluacionConciliadorFacade#consultarTotales(java.lang.Long)
	 */
	@Override
	public List<DetalleEvaluacionConciliador> consultarTotales(Long idPersona, Date periodoDesde, Date periodoHasta) {
		List<EvaluacionConciliador> evaluacion = evaluacionConciliadorFacade.consultarEvaluacionConciliador(idPersona,
				periodoDesde, periodoHasta);
		Long idEvaluacion = null;
		if(!evaluacion.isEmpty())
			idEvaluacion = evaluacion.get(0).getIdEvaluacionConciliador();
		return (List<DetalleEvaluacionConciliador>) transformarEntidadesColeccionSinDependencias(
				manejadorDetalleEvaluacionConciliador.consultarDetallesPorCriterio(null, idEvaluacion),
				new ArrayList<DetalleEvaluacionConciliador>());
	}
	// protected region metodos adicionales end
	
}
