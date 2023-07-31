/**
 * 
 */
package com.ccb.simasc.integracion.manejadores.reportes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

/**
 * Manejador que contiene la logica de negocio de los reportes de conciliacion
 * 
 * @author cbenavides
 *
 */
@Stateless
public class ManejadorReportesConciliacion {
	@PersistenceContext
	private EntityManager em;

	/**
	 * reporte de los casos aceptados No aceptados por el conciliador
	 * 
	 * @param datosConsulta
	 * @return
	 */
	public List<CasosNoAceptadosDTO> casosNoAceptadosConciliador(FiltroReportesDTO datosConsulta) {

		String nombreConciliador = " (select stuff((select ', ' + concat (pe.primer_nombre_o_razon_social, ' ', "
				+ " pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) "
				+ " for xml path('')), 1, 1, '')) as conciliador, ";

		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append(" SELECT ca.id_caso AS idCaso, ");
		nativeQuery.append(" ca.id_servicio AS idServicio, ");
		nativeQuery.append(" (SELECT ser.nombre FROM SERVICIO ser WHERE id_servicio = ca.id_servicio) AS servicio, ");
		nativeQuery.append(" (SELECT nombre FROM MATERIA ma WHERE ma.id_materia = ca.id_materia ) AS materia, ");
		nativeQuery.append(" (SELECT nombre FROM CONVENIO WHERE id_convenio = ca.id_convenio AND estado_registro = ?1)AS convenio, ");
		nativeQuery.append(nombreConciliador);
		nativeQuery.append(" rpc.id_persona AS idPersona, ");
		nativeQuery.append(" pr.pronunciamiento AS pronunciamiento, ");
		nativeQuery.append(" pr.fecha AS fechaPronunciamiento, ");
		nativeQuery.append(" pr.motivo_declinacion AS codMotivoDeclinacion, ");
		nativeQuery.append(" (SELECT nombre FROM DOMINIO WHERE dominio = ?2 AND ");
		nativeQuery.append(" codigo = pr.motivo_declinacion )AS motivoDeclinacion, ");
		nativeQuery.append(" pr.observaciones AS observaciones ");

		nativeQuery.append(" FROM ROL_PERSONA_CASO rpc ");
		nativeQuery.append(" LEFT JOIN CASO ca ON  rpc.id_caso = ca.id_caso ");
		nativeQuery.append(" LEFT JOIN PERSONA pe ON rpc.id_persona=pe.id_persona ");
		nativeQuery.append(" LEFT JOIN PRONUNCIAMIENTO pr ON rpc.id_pronunciamiento = pr.id_pronunciamiento ");
		nativeQuery.append(" AND pr.estado_registro = ?1");

		nativeQuery.append(" WHERE rpc.id_rol IN (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ?4 ) ");
		nativeQuery.append(" AND rpc.estado = ?3 ");
		nativeQuery.append(" AND rpc.estado_registro = ?1 ");
		if (datosConsulta.getCentros() != null && !datosConsulta.getCentros().isEmpty()) {
			nativeQuery.append(sqlSede(datosConsulta));
		}
		if (datosConsulta.getFechaInicio() != null) {
			nativeQuery.append(" AND Cast(pr.fecha as DATE)  >=  cast( ?6 as DATE) ");
		}
		if (datosConsulta.getFechaFin() != null) {
			nativeQuery.append(" AND Cast(pr.fecha as DATE)  <=  cast( ?7 as DATE) ");
		}
		if (datosConsulta.getIdConciliador() != null && !datosConsulta.getIdConciliador().equals(-1L)) {
			nativeQuery.append(" AND rpc.id_persona = ?5 ");
		}

		nativeQuery.append(" order by pr.fecha DESC ");

		Query q = em.createNativeQuery(nativeQuery.toString(), CasosNoAceptadosDTO.class);
		q.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(2, UtilDominios.DOMINIO_MOTIVO_RECHAZO_CONCILIADOR);
		q.setParameter(3, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
		q.setParameter(4, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);

		if (datosConsulta.getIdConciliador() != null && !datosConsulta.getIdConciliador().equals(-1L)) {
			q.setParameter(5, datosConsulta.getIdConciliador());
		}
		if (datosConsulta.getFechaInicio() != null) {
			q.setParameter(6, datosConsulta.getFechaInicio());
		}
		if (datosConsulta.getFechaFin() != null) {
			q.setParameter(7, datosConsulta.getFechaFin());
		}

		return q.getResultList();
	}
	
	public StringBuilder sqlSede(FiltroReportesDTO datosConsulta) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" AND ca.id_sede IN (SELECT id_sede FROM SEDE WHERE id_centro ");
		nativeQuery.append(UtilConsultasSQL.clausulaInSQLSNumeros(datosConsulta.getCentros()));
		nativeQuery.append(" )");
		return nativeQuery;
	}

	/**
	 * Reporte de los casos rechazados por competencia
	 * 
	 * @param datosConsulta
	 * @return
	 */
	public List<CasosRechazadosCompetenciaDTO> casosRechazadosCompetencia(FiltroReportesDTO datosConsulta) {
		String nombreConciliador = " (select stuff((select ', ' + concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', "
				+ "pe.primer_apellido, ' ', pe.segundo_apellido)  for xml path('')), 1, 1, '')) as conciliador, ";

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT ca.id_caso AS idCaso, ");
		nativeQuery.append(" ca.id_servicio AS idServicio, ");
		nativeQuery.append(" (SELECT s.nombre FROM SERVICIO s WHERE s.id_servicio = ca.id_servicio) AS servicio, ");
		nativeQuery.append(" ca.nombre AS nombreCaso, ");
		nativeQuery.append(" ca.id_materia AS idMateria, ");
		nativeQuery.append(" (SELECT m.nombre FROM MATERIA m WHERE m.id_materia = ca.id_materia) AS materia, ");
		nativeQuery.append(" pe.id_persona AS idPersona, ");
		nativeQuery.append(nombreConciliador);
		nativeQuery.append(" rpc.id_rol AS idRol, ");
		nativeQuery.append(" e.fecha_evento AS fechaNoAceptacion, ");
		nativeQuery.append(" ca.fecha_radicacion AS fechaRadicacion, ");
		nativeQuery.append(" e.observaciones AS observacionesRechazo, ");
		nativeQuery.append(" pr.observaciones AS observacionesPronunciamiento, ");
		nativeQuery.append(
				" (SELECT d.nombre FROM DOMINIO d WHERE d.dominio =?5 AND d.codigo = pr.motivo_declinacion) AS motivoDeclinacion ");
		nativeQuery.append(" FROM CASO ca ");
		nativeQuery.append(
				" INNER JOIN EVENTO e ON e.id_caso = ca.id_caso AND e.estado_registro = ?1 AND e.tipo_evento = ?6 ");
		nativeQuery.append(
				" LEFT JOIN ROL_PERSONA_CASO rpc ON rpc.id_caso = ca.id_caso AND rpc.id_rol IN (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ?7 ) AND rpc.estado_registro = ?1 AND rpc.tipo_nombramiento = ?2 ");
		nativeQuery.append(
				" LEFT JOIN PRONUNCIAMIENTO pr ON rpc.id_pronunciamiento = pr.id_pronunciamiento AND pr.estado_registro = ?1 AND pr.motivo_declinacion = ?3 ");
		nativeQuery.append(" INNER JOIN PERSONA pe ON rpc.id_persona = pe.id_persona AND pe.estado_registro = ?1 ");
		nativeQuery.append(" WHERE ca.resultado = ?4 ");
		nativeQuery.append(" AND ca.estado_registro = ?1 ");
		if (datosConsulta.getCentros() != null && !datosConsulta.getCentros().isEmpty()) {
			nativeQuery.append(sqlSede(datosConsulta));
		}
		if (datosConsulta.getIdConciliador() != null && !datosConsulta.getIdConciliador().equals(-1L)) {
			nativeQuery.append(" AND rpc.id_persona =?8 ");
		}
		if (datosConsulta.getFechaInicio() != null) {
			nativeQuery.append(" AND Cast(pr.fecha as DATE)  >=  cast( ?9 as DATE) ");
		}
		if (datosConsulta.getFechaFin() != null) {
			nativeQuery.append(" AND Cast(pr.fecha as DATE)  <=  cast( ?10 as DATE) ");
		}
		nativeQuery.append(" order by ca.id_caso ");

		Query q = em.createNativeQuery(nativeQuery.toString(), CasosRechazadosCompetenciaDTO.class);
		q.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(2, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		q.setParameter(3, UtilDominios.MOTIVO_RECHAZO_CONCILIADOR_FALTA_COMPETENCIA);
		q.setParameter(4, UtilDominios.RESULTADO_CASO_CONCILIACION_FALTA_COMPETENCIA);
		q.setParameter(5, UtilDominios.DOMINIO_MOTIVO_RECHAZO_CONCILIADOR);
		q.setParameter(6, UtilDominios.TIPO_EVENTO_FALTA_DE_COMPENTENCIA);
		q.setParameter(7, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);

		if (datosConsulta.getIdConciliador() != null && !datosConsulta.getIdConciliador().equals(-1L)) {
			q.setParameter(8, datosConsulta.getIdConciliador());
		}
		if (datosConsulta.getFechaInicio() != null) {
			q.setParameter(9, datosConsulta.getFechaInicio());
		}
		if (datosConsulta.getFechaFin() != null) {
			q.setParameter(10, datosConsulta.getFechaFin());
		}
		return q.getResultList();

	}

	/**
	 * Realiza la consulta para el reporte: CON-C-039_SC-Casos-pagados-por-sede
	 * 
	 * @param datosConsulta
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CasosPagadosDTO> casosPagadosPorSede(FiltroReportesDTO datosConsulta) {
		// formateo de fechas
		String fechaHoraIncial = UtilSimasc.ajustarFechaParaReporte(datosConsulta.getFechaInicio(),
				UtilConstantes.FORMATO_HORA_INICIO);
		String fechaHoraFinal = UtilSimasc.ajustarFechaParaReporte(datosConsulta.getFechaFin(),
				UtilConstantes.FORMATO_HORA_FIN);

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT ");
		nativeQuery.append("ca.id_caso AS codigoCaso, ");
		nativeQuery.append("ca.nombre AS nombreCaso, ");
		nativeQuery.append("(SELECT nombre FROM SEDE WHERE id_sede=pc.id_sede) AS sedePago, ");
		nativeQuery.append("(SELECT nombre FROM SEDE WHERE id_sede=ca.id_sede) AS sedeRadicacion, ");
		nativeQuery.append("pc.valor_cuantia AS cuantia, ");
		nativeQuery.append(
				"(select valor from DETALLE_PAGO_CASO where numero_recibo=pc.numero_recibo AND estado_registro=?3 AND servicio_caja=?1) AS valorGastosAdmin, ");
		nativeQuery.append(
				"(select valor from DETALLE_PAGO_CASO where numero_recibo=pc.numero_recibo AND estado_registro=?3 AND servicio_caja in (select dom.codigo from dominio dom where dom.nombre=?7 and dom.dominio=?8)) AS valorHonorariosConciliador ");
		nativeQuery.append("FROM PAGO_CASO pc ");
		nativeQuery.append("INNER JOIN CASO ca ON pc.id_caso=ca.id_caso ");
		nativeQuery.append("WHERE pc.FECHA_PAGO BETWEEN ?4 AND ?5 ");
		nativeQuery.append("AND pc.estado_registro=?3 ");
		nativeQuery.append("AND ca.id_servicio=?6 ");

		if (datosConsulta.getSedePago() >= 0) {
			nativeQuery.append("AND pc.id_sede = " + datosConsulta.getSedePago() + " ");
		}
		if (datosConsulta.getSedeRadicacion() >= 0) {
			nativeQuery.append("AND ca.id_sede = " + datosConsulta.getSedeRadicacion() + " ");
		}

		Query query = em.createNativeQuery(nativeQuery.toString(), CasosPagadosDTO.class);
		query.setParameter(1, UtilDominios.TIPO_SERVICIO_CAJA_GASTOS_ADMINISTRATIVOS);
		query.setParameter(2, UtilDominios.TIPO_SERVICIO_CAJA_HONORARIOS_DEL_CONCILIADOR);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(4, fechaHoraIncial);
		query.setParameter(5, fechaHoraFinal);
		query.setParameter(6, UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO);
		query.setParameter(7, UtilDominios.NOMBRE_DOMINIO_HONORARIOS_CONCILIADOR);
		query.setParameter(8, UtilDominios.DOMINIO_TIPO_SERVICIO_CAJA);
		return query.getResultList();
	}

	/**
	 * Consulta de los casos de conciliacion que han sido cobrados
	 * 
	 * @param consulta
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CasosCobradosDTO> consultarCasosCobrados(FiltroReportesDTO consulta, boolean cobrados) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select c.id_caso idCaso, c.id_servicio idServicio, s.nombre nombreServicio, c.nombre, ");
		nativeQuery.append(" c.fecha_estado fechaCierre, f.fecha_de_facturacion fechaFacturacion");
		if (cobrados)
			nativeQuery.append(", f.fecha_de_cobro fechaCobro ");
		nativeQuery.append(" , (select ISNULL(SUM(valor), 0) from DETALLE_PAGO_CASO ");
		nativeQuery.append(" where numero_recibo in (select numero_recibo from PAGO_CASO where id_caso = c.id_caso and estado_registro = ?2 ");
		nativeQuery.append(" ) and servicio_caja in (select dom.codigo from dominio dom where dom.nombre=?11 and dom.dominio=?12)  and estado_registro = ?2 ");
		nativeQuery.append(" ) + ");
		nativeQuery.append(" (select ISNULL(SUM(valor), 0) from DETALLE_RELIQUIDACION ");
		nativeQuery.append(" where id_reliquidacion in (select id_reliquidacion from RELIQUIDACION where id_caso = c.id_caso and estado_registro = ?2 ");
		nativeQuery.append(" ) and servicio_caja in (select dom.codigo from dominio dom where dom.nombre=?11 and dom.dominio=?12)  and estado_registro = ?2 ");
		nativeQuery.append(" ) valorACobrar");
		nativeQuery.append(" from caso c ");
		nativeQuery.append(" inner join SERVICIO s on c.id_servicio = s.id_servicio ");
		nativeQuery.append(" inner join FACTURACION_CASO f ON c.id_caso = f.id_caso and f.estado_registro = ?2 ");
		nativeQuery.append(" and f.fecha_de_facturacion is not null ");
		if (cobrados)
			nativeQuery.append(" and cobrado = 1 and f.fecha_de_cobro is not null ");
		else
			nativeQuery.append(" and cobrado = 0 and f.fecha_de_cobro is null ");
		if (consulta.getIdConciliador() != null) {
			nativeQuery.append(" INNER JOIN ROL_PERSONA_CASO rpc on c.id_caso = rpc.id_caso ");
			nativeQuery.append(" and rpc.id_rol in (select id_rol from TIPO_DE_SERVICIO_ROL where tipo_servicio = ?3) ");
			nativeQuery.append(" and rpc.id_persona = ?4");
			nativeQuery.append(" and rpc.estado = ?5");
			nativeQuery.append(" and rpc.estado_registro = ?2 ");
		}
		nativeQuery.append(" where c.id_sede in (select id_sede from sede where id_centro ")
				.append(UtilConsultasSQL.clausulaInSQLSNumeros(consulta.getCentros()));
		nativeQuery.append(" ) and c.estado_registro = ?2 ");
		if (consulta.getIdServicio() != null)
			nativeQuery.append(" and c.id_servicio = ?6");
		else
			nativeQuery.append(" and c.id_servicio in (1,8)");

		if (consulta.getFechaInicio() != null && consulta.getFechaFin() != null)
			nativeQuery.append(" and c.fecha_radicacion between ?7 and ?8");
		else if (consulta.getFechaInicio() != null)
			nativeQuery.append(" and c.fecha_radicacion >= ?7");
		else if (consulta.getFechaFin() != null)
			nativeQuery.append(" and c.fecha_radicacion <= ?8");

		if (consulta.getFechaFacturacionInicio() != null && consulta.getFechaFacturacionFin() != null)
			nativeQuery.append(" and f.fecha_de_facturacion between ?9 and ?10");
		else if (consulta.getFechaFacturacionInicio() != null)
			nativeQuery.append(" and f.fecha_de_facturacion >= ?9");
		else if (consulta.getFechaFacturacionFin() != null)
			nativeQuery.append(" and f.fecha_de_facturacion <= ?10");

		Query query = em.createNativeQuery(nativeQuery.toString(), CasosCobradosDTO.class);
		asignacionDeParametrosCasosPagados(query, consulta);
		return query.getResultList();
	}

	private void asignacionDeParametrosCasosPagados(Query query, FiltroReportesDTO consulta) {
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		if (consulta.getIdConciliador() != null) {
			query.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
			query.setParameter(4, consulta.getIdConciliador());
			query.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		}
		if (consulta.getIdServicio() != null)
			query.setParameter(6, consulta.getIdServicio());
		if (consulta.getFechaInicio() != null)
			query.setParameter(7, consulta.getFechaInicio());
		if (consulta.getFechaInicio() != null)
			query.setParameter(8, consulta.getFechaFin());
		if (consulta.getFechaFacturacionInicio() != null)
			query.setParameter(9, consulta.getFechaFacturacionInicio());
		if (consulta.getFechaFacturacionFin() != null)
			query.setParameter(10, consulta.getFechaFacturacionFin());
		query.setParameter(11, UtilDominios.NOMBRE_DOMINIO_HONORARIOS_CONCILIADOR);
		query.setParameter(12, UtilDominios.DOMINIO_TIPO_SERVICIO_CAJA);
	}

	/**
	 * Consulta que devuelve la planilla de correspondencia de acuerdo a la sede
	 * sobre la cual se realiza la consulta, y en la cual se encuentren
	 * audiencias pendientes por realizar
	 * 
	 * @param idSede
	 * @return
	 */
	public List<PlanillaCorrespondenciaDTO> consultarPlanillaCorrespondencia(Long idSede) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select cp.id_carta_persona As idCartaPersona, ");
		nativeQuery.append(" GETDATE() As fecha, cp.id_caso As codigoCaso, ");
		nativeQuery.append(
				" (select concat(primer_nombre_o_razon_social, ' ', segundo_nombre, ' ', primer_apellido, ' ', segundo_apellido) ");
		nativeQuery.append("  from PERSONA where id_persona = cp.id_persona and estado_registro = ?5");
		nativeQuery.append(" ) As nombreParte, ");
		nativeQuery.append(
				" (select d.nombre from rol r, dominio d where r.id_rol = rpc.id_rol and d.codigo = r.nombre and d.dominio = ?1");
		nativeQuery.append(" ) As rol, ");
		nativeQuery.append(
				" cp.direccion_correspondencia As direccion, cp.ciudad_correspondencia As ciudad, cp.telefonos_contacto As telefono, ");
		nativeQuery.append(" a.hora_inicio As fechaAudiencia,");
		nativeQuery.append(
				" (select nombre from sede s, sala sa where s.id_sede = sa.id_sede and sa.id_sala = ag.id_sala) As sede, ");
		nativeQuery.append(
				" (select concat(primer_nombre_o_razon_social, ' ', segundo_nombre, ' ', primer_apellido, ' ', segundo_apellido) ");
		nativeQuery.append(" from PERSONA where estado_registro = ?5");
		nativeQuery.append(" and id_persona in (select id_persona ");
		nativeQuery
				.append(" from rol_persona_caso rpc2 left join tipo_de_servicio_rol tsr on tsr.id_rol = rpc2.id_rol ");
		nativeQuery.append(" where c.id_caso = rpc2.id_caso and tsr.tipo_servicio = ?2");
		nativeQuery.append(" and rpc2.estado <> ?3 and rpc2.tipo_nombramiento = ?4 ");
		nativeQuery.append(" and tsr.estado_registro = ?5");
		nativeQuery.append(" and rpc2.estado_registro = ?5");
		nativeQuery.append(" )) As conciliador");
		nativeQuery.append(" from CARTA_PERSONA cp");
		nativeQuery.append(" left join CASO c on c.id_caso = cp.id_caso");
		nativeQuery.append(
				" left join ROL_PERSONA_CASO rpc on cp.id_persona = rpc.id_persona and c.id_caso = rpc.id_caso");
		nativeQuery.append(" right join AUDIENCIA a on a.id_audiencia = cp.id_audiencia");
		nativeQuery.append(" left join AGENDAMIENTO ag on a.id_audiencia = ag.id_audiencia");
		nativeQuery.append(" where c.id_sede = ?6");
		nativeQuery.append(" and c.id_servicio in (select id_servicio from servicio where tipo = ?2)");
		nativeQuery.append(" and cp.estado_carta = ?7");
		nativeQuery.append(" and cp.estado_registro = ?5 and c.estado_registro = ?5");
		nativeQuery.append(" and a.estado = ?8");
		nativeQuery.append(" and a.estado_registro = ?5 and ag.estado_registro = ?5");
		nativeQuery.append(" and rpc.estado <> ?9");
		nativeQuery.append(" and rpc.estado_registro = ?5");

		Query query = em.createNativeQuery(nativeQuery.toString(), PlanillaCorrespondenciaDTO.class);
		query.setParameter(1, UtilDominios.DOMINIO_ROL_PERSONA);
		query.setParameter(2, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(3, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
		query.setParameter(4, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(6, idSede);
		query.setParameter(7, UtilDominios.ESTADO_CARTA_IMPRESA);
		query.setParameter(8, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		query.setParameter(9, UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO);

		return query.getResultList();
	}

	/**
	 * Consulta las audiencias en un rango de fechas CON-C-032
	 * 
	 * @param filtros
	 * @return
	 */
	@SuppressWarnings("all")
	public List<AudienciasPorFechasDTO> consultaAudienciasPorFechas(FiltroReportesDTO filtros) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select c.id_caso as codigoCaso, ");
		nativeQuery.append(" a.id_audiencia as idAudiencia, ");
		nativeQuery.append(" c.nombre as descripcionCaso, ");
		nativeQuery.append(" isnull(a.consecutivo,0) as audiencia, ");
		nativeQuery.append(" a.hora_inicio as fechaAudiencia, ");
		nativeQuery.append(" isnull(sede.nombre,'') as sede, ");
		nativeQuery.append(" isnull(con.conciliador,'') as conciliador, ");
		nativeQuery.append(" (select nombre from dominio where codigo=a.estado and dominio=?1) estadoAudiencia ");
		nativeQuery.append(" from AUDIENCIA a ");
		nativeQuery.append(" inner join CASO c on c.id_caso=a.id_caso and a.estado_registro=c.estado_registro ");
		nativeQuery
				.append(" left join (select distinct s.id_sede,s.nombre, a.id_caso,a.id_audiencia, s.estado_registro ");
		nativeQuery.append(" from sede s ");
		nativeQuery.append(" inner join sala sa on sa.id_sede=s.id_sede and sa.estado_registro=s.estado_registro ");
		nativeQuery
				.append(" inner join AGENDAMIENTO A on a.id_sala=sa.id_sala and a.estado_registro=sa.estado_registro ");
		nativeQuery
				.append(" ) as sede on sede.id_audiencia=a.id_audiencia and sede.estado_registro=a.estado_registro ");
		nativeQuery.append(" left join ( select rpc.id_caso, ");
		nativeQuery.append(
				" concat(rtrim(p.primer_nombre_o_razon_social),' ',rtrim(p.segundo_nombre),' ',rtrim(p.primer_apellido),' ',rtrim(p.segundo_apellido)) as conciliador, ");
		nativeQuery.append("  p.estado_registro ");
		nativeQuery.append(" from persona p ");
		nativeQuery.append(
				" inner join rol_persona_caso rpc on rpc.id_persona = p.id_persona and rpc.estado_registro=p.estado_registro ");
		nativeQuery.append(" inner join rol r on r.id_rol = rpc.id_rol and r.estado_registro=rpc.estado_registro ");
		nativeQuery.append(" where r.nombre in (?2,?3,?4) ");
		nativeQuery.append(
				" and rpc.estado=?5) as con on con.id_caso=c.id_caso and con.estado_registro=c.estado_registro ");
		nativeQuery.append(" where a.estado_registro=?6 ");
		nativeQuery.append(" and exists (select 1 from SERVICIO where id_servicio=c.id_servicio and tipo=?11 ) ");
		if (filtros.getFechaInicio() != null && filtros.getFechaFin() != null) {
			nativeQuery.append(" and  cast(a.hora_inicio as date) between  cast(?7 as date) and  cast(?8 as date) ");
		}
		if (Long.valueOf(filtros.getIdSede()) != 0 && Long.valueOf(filtros.getIdSede()) != null)
			nativeQuery.append(" and sede.id_sede = ?9");
		if (filtros.getIdCaso() != null && Long.valueOf(filtros.getIdCaso()) >= 0)
			nativeQuery.append(" and c.id_caso= ?10");
		nativeQuery.append(" order by a.hora_inicio desc ");

		Query query = em.createNativeQuery(nativeQuery.toString(), AudienciasPorFechasDTO.class);
		query.setParameter(1, UtilDominios.DOMINIO_ESTADO_AUDIENCIA);
		query.setParameter(2, UtilDominios.ROL_PERSONA_CONCILIADOR);
		query.setParameter(3, UtilDominios.ROL_PERSONA_CONCILIADOR_EN_INSOLVENCIA);
		query.setParameter(4, UtilDominios.ROL_PERSONA_CONCILIADOR_COMUNITARIO);
		query.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(6, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if (filtros.getFechaInicio() != null && filtros.getFechaFin() != null) {
			query.setParameter(7, filtros.getFechaInicio());
			query.setParameter(8, filtros.getFechaFin());
		}
		if (Long.valueOf(filtros.getIdSede()) != 0 && Long.valueOf(filtros.getIdSede()) != null)
			query.setParameter(9, filtros.getIdSede());
		if (filtros.getIdCaso() != null && Long.valueOf(filtros.getIdCaso()) >= 0)
			query.setParameter(10, filtros.getIdCaso());
		query.setParameter(11, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);

		return query.getResultList();

	}

	/**
	 * Consulta los casos que no han sido cerrados en mas de 90 dias CON-C-042
	 * 
	 * @param centrosIn
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CasosSinCerrar90DTO> consultaCasosSinCerrar90Dias(String centrosIn) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select c.id_caso as codigoCaso, ");
		nativeQuery.append(" c.fecha_radicacion as fechaRadicacion, ");
		nativeQuery.append(" c.nombre nombreCaso, ");
		nativeQuery.append(" con.conciliador, ");
		nativeQuery.append(" datediff(day,fecha_radicacion,getdate()) numeroDias, ");
		nativeQuery.append(" sp.fecha_prorroga fechaProrroga, ");
		nativeQuery.append(" sp.observaciones ");
		nativeQuery.append(" from CASO c ");
		nativeQuery.append(
				" inner join servicio s on c.id_servicio = s.id_servicio and c.estado_registro=s.estado_registro ");
		nativeQuery.append(" inner join sede se on se.id_sede=c.id_sede ");
		nativeQuery.append(" left join ( select rpc.id_caso, ");
		nativeQuery.append(
				" concat(rtrim(p.primer_nombre_o_razon_social),' ',rtrim(p.segundo_nombre),' ',rtrim(p.primer_apellido),' ',rtrim(p.segundo_apellido)) as conciliador, ");
		nativeQuery.append(" p.estado_registro ");
		nativeQuery.append(" from persona p ");
		nativeQuery.append(
				" inner join rol_persona_caso rpc on rpc.id_persona = p.id_persona and rpc.estado_registro=p.estado_registro ");
		nativeQuery.append(" inner join rol r on r.id_rol = rpc.id_rol and r.estado_registro=rpc.estado_registro ");
		nativeQuery.append(" where r.nombre in (?1,?2,?3) ");
		nativeQuery
				.append(" and rpc.estado=?4)  con on con.id_caso=c.id_caso and con.estado_registro=c.estado_registro ");
		nativeQuery.append(
				" left join SOLICITUD_PRORROGA sp on sp.id_caso=c.id_caso and sp.estado_registro=c.estado_registro ");
		nativeQuery.append(" where estado_caso not in (?5, ?8 )");
		nativeQuery.append(" and s.tipo=?6 ");
		nativeQuery.append(" and c.estado_registro= ?7 ");
		nativeQuery.append(" and se.id_centro ").append(centrosIn);
		nativeQuery.append(" and datediff(day,fecha_radicacion,getdate()) > 90 ");
		nativeQuery.append(" order by c.id_caso ");
		Query query = em.createNativeQuery(nativeQuery.toString(), CasosSinCerrar90DTO.class);
		query.setParameter(1, UtilDominios.ROL_PERSONA_CONCILIADOR);
		query.setParameter(2, UtilDominios.ROL_PERSONA_CONCILIADOR_EN_INSOLVENCIA);
		query.setParameter(3, UtilDominios.ROL_PERSONA_CONCILIADOR_COMUNITARIO);
		query.setParameter(4, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(5, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(6, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(7, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(8, UtilDominios.ESTADO_CASO_REGISTRADO);

		return query.getResultList();
	}

	/**
	 * 29-01-2018 pRendon CON-F-040: Consulta de los casos por �apoderado
	 * convocante� del servicio convenio de conciliaci�n.
	 */
	@SuppressWarnings("unchecked")
	public List<CasosApoderadoConvenioDTO> consultarCasosApoderadoConvenio(FiltroReportesDTO filtros) {
		String nombreConciliador = " (select stuff((select ', ' + concat (pc.primer_nombre_o_razon_social, ' ', pc.segundo_nombre, ' ', "
				+ "pc.primer_apellido, ' ', pc.segundo_apellido)  for xml path('')), 1, 1, '')) as nombreConciliador, ";

		String nombreApoderado = " (select stuff((select ', ' + concat (pa.primer_nombre_o_razon_social, ' ', pa.segundo_nombre, ' ', "
				+ "pa.primer_apellido, ' ', pa.segundo_apellido)  for xml path('')), 1, 1, '')) as nombreApoderado, ";

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT c.id_caso as idCaso, ");
		nativeQuery.append(" c.nombre as nombreCaso, ");
		nativeQuery.append(" c.hechos as casoHechos, ");
		nativeQuery.append(
				" ( select top 1 hora_inicio from AUDIENCIA where id_caso = c.id_caso and estado_registro = ?4 order by hora_inicio ) as horaAudiencia, ");
		nativeQuery.append(" sede.nombre as nombreSede, ");
		nativeQuery.append(nombreConciliador);
		nativeQuery.append(nombreApoderado);
		nativeQuery.append(" conv.nombre as nombreConvenio ");
		nativeQuery.append(" FROM CASO c ");
		nativeQuery.append(" INNER JOIN SEDE sede ");
		nativeQuery.append(" ON sede.id_sede=c.id_sede ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA_CASO apode ");
		nativeQuery.append(" ON apode.id_caso = c.id_caso ");
		nativeQuery.append(" AND apode.id_rol = (select id_rol from rol where nombre = ?3 and estado_registro = ?4) ");
		nativeQuery.append(" INNER JOIN persona pa ");
		nativeQuery.append(" ON pa.id_persona = apode.id_persona ");
		nativeQuery.append(" LEFT JOIN ROL_PERSONA_CASO con ");
		nativeQuery.append(" ON con.id_caso = c.id_caso ");
		nativeQuery.append(" AND con.estado_registro = ?4 ");
		nativeQuery.append(
				" AND con.id_rol in (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ?5 and estado_registro = ?4) ");
		nativeQuery.append(" LEFT JOIN persona pc ");
		nativeQuery.append(" ON pc.id_persona = con.id_persona ");
		nativeQuery.append(" AND pc.estado_registro = ?4 ");
		nativeQuery.append(" INNER JOIN CONVENIO conv ");
		nativeQuery.append(" ON c.id_convenio = conv.id_convenio ");
		nativeQuery.append(" WHERE Cast(c.fecha_radicacion as DATE) BETWEEN cast( ?6 as DATE) AND cast( ?7 as DATE) ");
		nativeQuery.append(" AND c.id_servicio = ?8 ");
		if (filtros.getIdPersona() != null) {
			nativeQuery.append(" AND apode.id_persona = ?1 ");
		}
		if (filtros.getIdConvenio() != null) {
			nativeQuery.append(" AND conv.id_convenio = ?2 ");
		}
		if (filtros.getCentros() != null && !filtros.getCentros().isEmpty()) {
			nativeQuery.append(" AND c.id_sede in (select id_sede from sede where id_centro ");
			nativeQuery.append(UtilConsultasSQL.clausulaInSQLSNumeros(filtros.getCentros()));
			nativeQuery.append(" )");
		}
		nativeQuery.append(" AND c.estado_registro = ?4 ");
		nativeQuery.append(" AND sede.estado_registro = ?4 ");
		nativeQuery.append(" AND apode.estado_registro = ?4 ");
		nativeQuery.append(" AND pa.estado_registro = ?4 ");
		nativeQuery.append(" AND conv.estado_registro = ?4 ");
		nativeQuery.append(" ORDER BY c.id_caso ");

		Query query = em.createNativeQuery(nativeQuery.toString(), CasosApoderadoConvenioDTO.class);
		query.setParameter(1, filtros.getIdPersona());
		query.setParameter(2, filtros.getIdConvenio());
		query.setParameter(3, UtilDominios.ROL_PERSONA_APODERADO_CONVOCANTE);
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(5, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(6, filtros.getFechaInicio());
		query.setParameter(7, filtros.getFechaFin());
		query.setParameter(8, UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO);
		return query.getResultList();
	}

	/**
	 * Consulta los casos que se le han hecho seguimientos en estado de acuerdo
	 * CON-C-051
	 * 
	 * @author LRUIZ
	 * @param filtros
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SeguimientoCasosDTO> seguimientoCasosEnAcuerdo(FiltroReportesDTO filtros) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select distinct ll.id_llamada as idLlamada, c.id_caso as codigoCaso, ");
		nativeQuery.append(" s.nombre as tipoCaso, ");
		nativeQuery.append(" c.nombre as nombreCaso, ");
		nativeQuery.append(" se.nombre as sede, ");
		nativeQuery.append(" concat(rtrim(p.primer_nombre_o_razon_social),' ',rtrim(p.segundo_nombre),' ',");
		nativeQuery.append(" rtrim(p.primer_apellido),' ',rtrim(p.segundo_apellido)) as conciliador, ");
		nativeQuery.append(" m.nombre as materia, ");
		nativeQuery.append(" CONVERT(VARCHAR(MAX),o.compromiso) as  acuerdos, ");
		nativeQuery.append(" concat(rtrim(ps.primer_nombre_o_razon_social),' ',rtrim(ps.segundo_nombre),' ',");
		nativeQuery.append(" rtrim(ps.primer_apellido),' ',rtrim(ps.segundo_apellido)) as personaSeguimiento, ");
		nativeQuery.append(" ll.fecha as fechaSeguimiento, ");
		nativeQuery.append(" replace(replace(ll.contactado,1,'Si'),0,'No') as respondio, ");
		nativeQuery.append(" replace(replace(ll.dio_informacion,1,'Si'),0,'No') as dioInformacion, ");
		nativeQuery.append(" ll.cumplio as cumplio, ");
		nativeQuery.append(" ll.observaciones ");
		nativeQuery.append(" from caso c ");
		nativeQuery.append(" inner join AUDIENCIA a on a.id_caso=c.id_caso and a.estado_registro=c.estado_registro ");
		nativeQuery.append(
				" inner join servicio s on s.id_servicio=c.id_servicio and c.estado_registro=s.estado_registro ");
		nativeQuery.append(
				" inner join Agendamiento ag on ag.id_audiencia=a.id_audiencia and ag.estado_registro=a.estado_registro ");
		nativeQuery.append(" inner join sala sa on sa.id_sala=ag.id_sala and sa.estado_registro=ag.estado_registro ");
		nativeQuery.append(" inner join sede se on se.id_sede=sa.id_sede and se.estado_registro=sa.estado_registro ");
		nativeQuery.append(
				" inner join rol_persona_caso rpc on rpc.id_caso=c.id_caso and rpc.estado_registro=c.estado_registro ");
		nativeQuery.append(" inner join rol r on r.id_rol=rpc.id_rol and r.estado_registro=rpc.estado_registro ");
		nativeQuery.append(
				" inner join persona p on p.id_persona = rpc.id_persona and p.estado_registro=rpc.estado_registro ");
		nativeQuery
				.append(" inner join MATERIA m on m.id_materia=c.id_materia and m.estado_registro=c.estado_registro ");
		nativeQuery.append(" inner join LLAMADA ll on ll.id_caso=c.id_caso and ll.estado_registro=c.estado_registro ");
		nativeQuery.append(" inner join USUARIO u on u.usuario_login=ll.usuario_llamada ");
		nativeQuery.append(
				" inner join persona ps on ps.id_persona=u.id_persona and ps.estado_registro=c.estado_registro ");
		nativeQuery.append(
				" inner join OBLIGACION_PARTE op on op.id_caso=c.id_caso and op.estado_registro=c.estado_registro ");
		nativeQuery.append(
				" inner join OBLIGACION o on o.id_resultado_audiencia=op.id_resultado_audiencia and o.estado_registro=op.estado_registro");
		nativeQuery.append(" where c.estado_registro=?1 ");
		nativeQuery.append(" and RPC.ESTADO = ?2 ");
		nativeQuery.append(" and s.id_servicio in (?3,?4) ");
		nativeQuery.append(" and c.estado_caso in (?5,?18) ");
		nativeQuery.append(" and c.resultado=?6 ");
		nativeQuery.append(" and ll.tipo_llamada=?7 ");
		if (filtros.getFechaRadicacionIncial() != null && filtros.getFechaRadicacionFinal() != null)
			nativeQuery.append(
					" and cast(c.fecha_radicacion as date) between  cast(?9 as date) and cast(?10 as date) ");
		if (filtros.getFechaCierreCasoInicial() != null && filtros.getFechaCierreCasoFinal() != null)
			nativeQuery.append(" and cast(c.fecha_estado as date) between cast(?11 as date) and cast(?12 as date) ");
		if (filtros.getIdConciliador() > 0)
			nativeQuery.append(" and    p.id_persona = ?13 ");
		if (filtros.getIdServicio() > 0)
			nativeQuery.append(" and s.id_servicio = ?14 ");
		nativeQuery.append(" and se.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(filtros.getCentros()));
		nativeQuery.append(" and     r.nombre in (?15,?16,?17) ");
		nativeQuery.append(" order by c.id_caso ");
		Query query = em.createNativeQuery(nativeQuery.toString(), SeguimientoCasosDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(3, UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO);
		query.setParameter(4, UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO);
		query.setParameter(5, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(6, UtilDominios.RESULTADO_CASO_CONCILIACION_ACUERDO);
		query.setParameter(7, UtilDominios.LLAMADA_SEGUIMIENTO_CASO);
		if (filtros.getFechaRadicacionIncial() != null && filtros.getFechaRadicacionFinal() != null) {
			query.setParameter(9, filtros.getFechaRadicacionIncial());
			query.setParameter(10, filtros.getFechaRadicacionFinal());
		}
		if (filtros.getFechaCierreCasoInicial() != null && filtros.getFechaCierreCasoFinal() != null) {
			query.setParameter(11, filtros.getFechaCierreCasoInicial());
			query.setParameter(12, filtros.getFechaCierreCasoFinal());
		}
		if (filtros.getIdConciliador() > 0)
			query.setParameter(13, filtros.getIdConciliador());
		if (filtros.getIdServicio() > 0)
			query.setParameter(14, filtros.getIdServicio());
		query.setParameter(15, UtilDominios.ROL_PERSONA_CONCILIADOR);
		query.setParameter(16, UtilDominios.ROL_PERSONA_CONCILIADOR_EN_INSOLVENCIA);
		query.setParameter(17, UtilDominios.ROL_PERSONA_CONCILIADOR_COMUNITARIO);
		query.setParameter(18, UtilDominios.ESTADO_CASO_REGISTRADO);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DevolucionDeDineroDTO> consultaDevolucionDeDinero(FiltroReportesDTO filtros) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select distinct re.id_reliquidacion as idReliquidacion, ");
		nativeQuery.append(" re.id_caso as idCaso, ");
		nativeQuery.append(" c.nombre, ");
		nativeQuery.append(" con.conciliador, ");
		nativeQuery.append(" (select nombre from DOMINIO where codigo=re.motivo and dominio=?1) motivo, ");
		nativeQuery.append(" re.valor, ");
		nativeQuery.append(" carta.fecha_generacion fechaGeneracion ");
		nativeQuery.append(" from RELIQUIDACION re ");
		nativeQuery.append(" inner join caso c on c.id_caso=re.id_caso ");
		nativeQuery.append(" inner join SEDE s on s.id_sede=c.id_sede ");
		nativeQuery.append(" left join (select rpc.id_caso, ");
		nativeQuery.append(
				" concat(rtrim(p.primer_nombre_o_razon_social),' ',rtrim(p.segundo_nombre),' ',rtrim(p.primer_apellido),' ',rtrim(p.segundo_apellido)) as conciliador ");
		nativeQuery.append(" ,p.estado_registro ");
		nativeQuery.append(" from persona p ");
		nativeQuery.append(
				" inner join rol_persona_caso rpc on rpc.id_persona = p.id_persona and p.estado_registro=rpc.estado_registro ");
		nativeQuery.append(" inner join rol r on r.id_rol = rpc.id_rol and r.estado_registro=rpc.estado_registro ");
		nativeQuery.append(" where r.nombre in (?2,?3,?4) ");
		nativeQuery.append(" and rpc.estado = ?5 ");
		nativeQuery.append(" ) as con on con.id_caso=c.id_caso and con.estado_registro=c.estado_registro ");
		nativeQuery.append(" left join ( select cp.id_caso, ");
		nativeQuery.append(" min(cp.fecha_generacion) fecha_generacion, ");
		nativeQuery.append(" cp.estado_registro ");
		nativeQuery.append(" from CARTA_PERSONA cp ");
		nativeQuery.append(
				" inner join plantilla_carta pc on cp.id_plantilla_carta=pc.id_plantilla_carta and cp.estado_registro=pc.estado_registro ");
		nativeQuery.append(" where pc.nombre=?6 ");
		nativeQuery.append(" and cp.estado_carta= ?7 ");
		nativeQuery.append(" group by cp.id_caso,cp.estado_registro) ");
		nativeQuery.append(" as carta on carta.id_caso=re.id_caso and carta.estado_registro=re.estado_registro ");
		nativeQuery.append(" where re.estado_registro=?8 ");
		nativeQuery.append(" and re.tipo= ?9 ");
		nativeQuery.append(" and s.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(filtros.getCentros()));
		nativeQuery.append(" and re.fecha between ?10 and ?11 ");
		if (filtros.getMotivoDevolucion() != null)
			nativeQuery.append(" and re.motivo = ?12");
		nativeQuery.append(" order by re.id_reliquidacion ");

		Query query = em.createNativeQuery(nativeQuery.toString(), DevolucionDeDineroDTO.class);
		query.setParameter(1, UtilDominios.DOMINIO_RESULTADO_CASO_CONCILIACION);
		query.setParameter(2, UtilDominios.ROL_PERSONA_CONCILIADOR);
		query.setParameter(3, UtilDominios.ROL_PERSONA_CONCILIADOR_EN_INSOLVENCIA);
		query.setParameter(4, UtilDominios.ROL_PERSONA_CONCILIADOR_COMUNITARIO);
		query.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(6, UtilDominios.NOMBRE_PLANTILLA_CARTA_DEVOLUCION_DE_DINERO);
		query.setParameter(7, UtilDominios.ESTADO_CARTA_GENERADA);
		query.setParameter(8, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(9, UtilDominios.TIPO_RELIQUIDACION_DEVOLUCION);
		query.setParameter(10, filtros.getFechaInicio());
		query.setParameter(11, filtros.getFechaFin());
		if (filtros.getMotivoDevolucion() != null)
			query.setParameter(12, filtros.getMotivoDevolucion());

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ReportePartesDeCasoDTO> consultarPartesDeCaso(FiltroReportesDTO filtros, List<Long> idCentros) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select ");
		nativeQuery.append(" CASE WHEN c.id_servicio = ?15 THEN ser.nombre ELSE co.nombre END AS tipoCaso, ");
		nativeQuery.append(" c.id_caso as codigoCaso, ");
		nativeQuery.append(" c.nombre as nombreCaso, ");
		nativeQuery.append(" (select d.nombre from DOMINIO d ");
		nativeQuery.append(" where d.codigo = r.nombre ");
		nativeQuery.append(" and d.dominio = ?9) as tipoParte, ");
		nativeQuery.append(" (select d.nombre from DOMINIO d ");
		nativeQuery.append(" where d.codigo = p.tipo_persona ");
		nativeQuery.append(" and d.dominio = ?1) as tipoPersona, ");
		nativeQuery.append(" c.fecha_radicacion as fechaRadicacion, ");
		nativeQuery.append(" (select d.nombre from DOMINIO d ");
		nativeQuery.append(" where d.codigo = p.tipo_documento ");
		nativeQuery.append(" and d.dominio = ?8) as tipoIdentificacion, ");
		nativeQuery.append(" p.numero_documento as numeroIdentificacion, ");
		nativeQuery.append(" concat(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre) as nombres, ");
		nativeQuery.append(" concat(p.primer_apellido, ' ', p.segundo_apellido) as apellidos, ");
		nativeQuery.append(" stuff((select u.direccion + ' - ' + z.nombre + CHAR(10) ");
		nativeQuery.append(" from UBICACION u ");
		nativeQuery.append(" inner join ZONA_GEOGRAFICA z on u.id_zona_geografica = z.id_zona_geografica ");
		nativeQuery.append(" and z.estado_registro = ?7 ");
		nativeQuery.append(" where u.id_persona = p.id_persona ");
		nativeQuery.append(" and u.estado_registro = ?7 ");
		nativeQuery.append(" for xml path('')), 1, 0, '') as direccion, ");
		nativeQuery.append(" (select top 1 z.nombre from ");
		nativeQuery.append(" UBICACION u ");
		nativeQuery.append(" left join ZONA_GEOGRAFICA z ");
		nativeQuery.append(" on u.id_zona_geografica = z.id_zona_geografica ");
		nativeQuery.append(" where u.id_persona = rpc.id_persona ");
		nativeQuery.append(" and u.estado_registro = 'ACT')as ciudad, ");		
		nativeQuery.append(" stuff((select co.direccion + CHAR(10) ");
		nativeQuery.append(" from CORREO_ELECTRONICO co ");
		nativeQuery.append(" where co.estado_registro = ?7 ");
		nativeQuery.append(" and co.id_persona = p.id_persona ");
		nativeQuery.append(" for xml path('')), 1, 0, '') as email, ");
		nativeQuery.append(" stuff((select numero + CHAR(10) from TELEFONO t ");
		nativeQuery.append(" where t.id_persona = p.id_persona ");
		nativeQuery.append(" and t.estado_registro = ?7 ");
		nativeQuery.append(" and t.tipo_telefono <> ?2 ");
		nativeQuery.append(" for xml path('')), 1, 0, '') as telefono, ");
		nativeQuery.append(" p.numero_tarjeta_profesional as tarjetaProfesional, ");
		nativeQuery.append(" case r.nombre ");
		nativeQuery.append(" when ?3 then ");
		nativeQuery.append(" (select  top(1) ");
		nativeQuery.append(" concat(pa.primer_nombre_o_razon_social, ' ', isnull(pa.segundo_nombre, ''), ' ', pa.primer_apellido, ' ', isnull(pa.segundo_apellido, ' ')) from persona pa ");
		nativeQuery.append(" inner join ROL_PERSONA_CASO ap ");
		nativeQuery.append(" on ap.id_persona = pa.id_persona ");
		nativeQuery.append(" and ap.id_caso = c.id_caso ");
		nativeQuery.append(" and ap.estado_registro = ?7 ");
		nativeQuery.append(" inner join rol ra on ap.id_rol = ra.id_rol ");
		nativeQuery.append(" and ra.nombre = ?5 ");
		nativeQuery.append(" ) ");
		nativeQuery.append(" else ");
		nativeQuery.append(" (select  top(1) ");
		nativeQuery.append(" concat(pa.primer_nombre_o_razon_social, ' ', isnull(pa.segundo_nombre, ''), ' ', pa.primer_apellido, ' ', isnull(pa.segundo_apellido, ' ')) from persona pa ");
		nativeQuery.append(" inner join ROL_PERSONA_CASO ap ");
		nativeQuery.append(" on ap.id_persona = pa.id_persona ");
		nativeQuery.append(" and ap.id_caso = c.id_caso ");
		nativeQuery.append(" and ap.estado_registro = ?7 ");
		nativeQuery.append(" inner join rol ra on ap.id_rol = ra.id_rol ");
		nativeQuery.append(" and ra.nombre = ?6 ");
		nativeQuery.append(" ) ");
		nativeQuery.append(" end as apoderado, ");
		nativeQuery.append(" sd.nombre as sede, ");
		nativeQuery.append(" (select max(hora_inicio) from audiencia a where a.id_caso = c.id_caso) as fechaAudiencia ");
		nativeQuery.append(" from CASO c with (nolock) ");
		nativeQuery.append(" inner join SERVICIO ser with (nolock) on c.id_servicio = ser.id_servicio ");
		nativeQuery.append(" left join convenio co with (nolock) ");
		nativeQuery.append(" on co.id_convenio = c.id_convenio  ");
		nativeQuery.append(" inner join sede sd ");
		nativeQuery.append(" on c.id_sede = sd.id_sede ");
		nativeQuery.append(" inner join ROL_PERSONA_CASO rpc with (nolock) on rpc.id_caso = c.id_caso ");
		nativeQuery.append(" and rpc.estado_registro = ?7 ");
		nativeQuery.append(" inner join ROL r with (nolock) on r.id_rol = rpc.id_rol ");
		nativeQuery.append(" and r.estado_registro = ?7 ");
		nativeQuery.append(" and r.nombre in (?3, ?4, ?5, ?6) ");
		nativeQuery.append(" inner join PERSONA p with (nolock) on p.id_persona = rpc.id_persona ");
		nativeQuery.append(" and p.estado_registro = ?7 ");
		nativeQuery.append(" and p.estado_persona = ?7 ");
		nativeQuery.append(" inner join SEDE s with (nolock) on s.id_sede = c.id_sede ");
		nativeQuery.append(" and s.estado_registro = ?7 ");
		nativeQuery.append(" and s.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(idCentros));
		nativeQuery.append(" where c.estado_registro = ?7 ");
		nativeQuery.append(" and c.estado_caso <> ?14 ");
		nativeQuery.append(" and c.id_servicio in (?15, ?16, ?17) ");

		if (filtros.getFechaRadicacionIncial() != null && filtros.getFechaRadicacionFinal() != null) {
			nativeQuery.append(" and CAST(c.fecha_radicacion AS DATE) between CAST(?10 AS DATE) and CAST(?11 AS DATE) ");
		}

		if (filtros.getIdCaso() != null) {
			nativeQuery.append(" and rpc.id_caso = ?12 ");
		}

		if (filtros.getTipoPersona() != null) {
			nativeQuery.append(" and p.tipo_persona = ?13 ");
		}

		if (filtros.getNombreParte() != null) {
			String nombres  = "((CONCAT(p.primer_nombre_o_razon_social,p.segundo_nombre,p.primer_apellido,p.segundo_apellido) like ?18 ) or "+
					"(CONCAT(p.primer_nombre_o_razon_social,p.segundo_nombre) like  ?18) or "+  
					"(CONCAT(p.primer_apellido,p.primer_nombre_o_razon_social) like ?18) or "+
					"(CONCAT(p.primer_nombre_o_razon_social,p.primer_apellido) like ?18)  )";
			nativeQuery.append(" and ").append(nombres);
		}

		nativeQuery.append(" order by rpc.id_caso ");

		Query query = em.createNativeQuery(nativeQuery.toString(), ReportePartesDeCasoDTO.class);
		query.setParameter(1, UtilDominios.DOMINIO_TIPO_PERSONA);
		query.setParameter(2, UtilDominios.TIPO_TELEFONO_FAX);
		query.setParameter(3, UtilDominios.ROL_PERSONA_CONVOCANTE);
		query.setParameter(4, UtilDominios.ROL_PERSONA_CONVOCADO);
		query.setParameter(5, UtilDominios.ROL_PERSONA_APODERADO_CONVOCANTE);
		query.setParameter(6, UtilDominios.ROL_PERSONA_APODERADO_CONVOCADO);
		query.setParameter(7, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(8, UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);
		query.setParameter(9, UtilDominios.DOMINIO_ROL_PERSONA);
		query.setParameter(14, UtilDominios.ESTADO_REGISTRO_ENCREACION);
		query.setParameter(15, UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO);
		query.setParameter(16, UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO);
		query.setParameter(17, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA);
		query.setParameter(18,"%"+ filtros.getNombreParte()+"%");

		if (filtros.getFechaRadicacionIncial() != null && filtros.getFechaRadicacionFinal() != null) {

			query.setParameter(10, filtros.getFechaRadicacionIncial());
			query.setParameter(11, filtros.getFechaRadicacionFinal());
		}

		if (filtros.getIdCaso() != null) {
			query.setParameter(12, filtros.getIdCaso());
		}

		if (filtros.getTipoPersona() != null) {

			query.setParameter(13, filtros.getTipoPersona());
		}

		return query.getResultList();
	}

	/**
	 * Consulta reporte de facturacion casos convenio
	 * 
	 * @param filtros
	 * @return
	 */
	public  List<FacturacionCasosConvenioDTO> facturacionCasosConvenio(FiltroReportesDTO filtros) {
		List<String> eventosResultados = new ArrayList<>();
		eventosResultados.add(UtilDominios.TIPO_EVENTO_CASO_CANCELADO);
		eventosResultados.add(UtilDominios.TIPO_EVENTO_CASO_PENDIENTE_ENTREGA_DOCUMENTO_RESULTADO);
		eventosResultados.add(UtilDominios.TIPO_EVENTO_FALTA_DE_COMPENTENCIA);
		
		
		String apoderado = "(select stuff(( SELECT DISTINCT (', ' +  concat (pec.primer_nombre_o_razon_social, ' ', pec.segundo_nombre, ' ', " 
				+ " pec.primer_apellido, ' ', pec.segundo_apellido) )"
				+ " FROM RELACIONADO_CONVENIO rco INNER JOIN PERSONA pec ON "
				+ "pec.id_persona = rco.id_persona AND rco.id_rol IN (SELECT id_rol FROM ROL WHERE nombre = ?2 ) "
				+ " WHERE rco.estado_registro = ?1 AND rco.id_convenio = co.id_convenio ORDER BY 1  for xml path('')), 1, 1, '')) AS apoderado, ";
		String fechaResultado = "(SELECT TOP 1 ev.fecha_evento FROM EVENTO ev WHERE ev.id_caso = ca.id_caso AND ev.estado_registro = ?1 "
				+ "AND ev.tipo_evento " + UtilConsultasSQL.clausulaInSQLStrings(eventosResultados)
				+ "ORDER BY ev.fecha_ultima_modificacion DESC) AS fechaResultado , ";
		String diasCancelacion = "DATEDIFF ( day , ca.fecha_radicacion , (SELECT top 1 e.fecha_evento FROM EVENTO e WHERE e.id_caso = ca.id_caso "
				+ " AND e.estado_registro = ?1 AND e.tipo_evento = ?3 ORDER BY fecha_ultima_modificacion) ) AS diasCancelacion, ";
		String valorAcuerdo = "(SELECT SUM(ISNULL (obli.valor_total_acuerdo,0)) FROM RESULTADO_AUDIENCIA rau "
				+" INNER JOIN OBLIGACION obli ON rau.id_resultado_audiencia = obli.id_resultado_audiencia AND obli.tipo_obligacion = ?4 "
				+" WHERE rau.estado_registro = ?1 AND obli.estado_registro = ?1 AND rau.id_audiencia = au.id_audiencia GROUP BY id_audiencia)  AS valorAcuerdo, ";
		
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append(" SELECT ca.fecha_radicacion  AS fechaRadicacion, ");
		nativeQuery.append(" fc.fecha_de_facturacion AS fechaFacturacion, ");
		nativeQuery.append(" ca.id_caso AS codigoCaso, ");
		nativeQuery.append(" co.nombre AS convenio, ");
		nativeQuery.append(" ca.nombre AS partes, ");
		nativeQuery.append(apoderado);
		nativeQuery.append(" au.hora_inicio AS fechaAudiencia, ");
		nativeQuery.append(fechaResultado);
		nativeQuery.append(" (SELECT nombre FROM DOMINIO WHERE dominio = ?5 AND codigo = ca.resultado) AS resultado , ");
		nativeQuery.append(" (SELECT nombre FROM DOMINIO WHERE dominio = ?8 AND codigo = au.resultado AND codigo IN ( ?6 , ?7 )) AS tipoAcuerdo, ");
		nativeQuery.append(" CONCAT(pe.primer_nombre_o_razon_social,' ', pe.segundo_nombre,' ',pe.primer_apellido,' ',pe.segundo_apellido) AS conciliador, ");
		nativeQuery.append(" se.nombre AS sedeAudiencia , ");
		nativeQuery.append(" ca.hechos AS motivo, ");
		nativeQuery.append(" ca.valor_pretensiones AS cuantia, ");
		nativeQuery.append(diasCancelacion);
		nativeQuery.append(valorAcuerdo);
		nativeQuery.append(" (SELECT COUNT(ad.id_audiencia) FROM AUDIENCIA ad WHERE ad.id_caso = ca.id_caso AND ad.estado_registro = ?1 AND ad.estado = ?9 ) AS numeroAudiencias, ");
		nativeQuery.append(" fc.valor_total AS total, ");
		nativeQuery.append(" (SELECT SUM (ISNULL(valor,0)) FROM DETALLE_PAGO_CASO dp WHERE pa.numero_recibo = dp.numero_recibo AND dp.estado_registro = ?1 AND dp.servicio_caja in (select dom.codigo from dominio dom where dom.dominio = ?19 and dom.nombre=?20) ) AS gastosAdministrativos, ");
		nativeQuery.append(" (SELECT SUM (ISNULL(valor,0)) FROM DETALLE_PAGO_CASO dp WHERE pa.numero_recibo = dp.numero_recibo AND dp.estado_registro = ?1 AND dp.servicio_caja in (select dom.codigo from dominio dom where dom.nombre=?18 and dom.dominio=?19)  ) AS honorariosConciliador ");
		nativeQuery.append(" FROM CASO ca  ");
		nativeQuery.append(" INNER JOIN FACTURACION_CASO fc ON ca.id_caso = fc.id_caso ");
		nativeQuery.append(" INNER JOIN SEDE sec ON sec.id_sede = ca.id_sede AND sec.estado_registro = ?1 AND sec.id_centro " ).append(UtilConsultasSQL.clausulaInSQLSNumeros(filtros.getCentros()));
		nativeQuery.append(" INNER JOIN CONVENIO co ON ca.id_convenio = co.id_convenio AND co.estado_registro = ?1 ");
		nativeQuery.append(" LEFT JOIN ROL_PERSONA_CASO rpc ON rpc.id_caso = ca.id_caso AND rpc.estado_registro = ?1 AND "); 
		nativeQuery.append(" rpc.id_rol IN (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ?12 ) AND rpc.estado = ?13 AND rpc.tipo_nombramiento = ?14 ");
		nativeQuery.append(" LEFT JOIN PERSONA pe ON pe.estado_registro = ?1 AND pe.id_persona = rpc.id_persona ");
		nativeQuery.append(" OUTER APPLY (SELECT TOP 1 * FROM AUDIENCIA a WHERE a.id_caso = ca.id_caso AND estado = ?9  ORDER BY consecutivo DESC, hora_inicio DESC) au ");
		nativeQuery.append(" LEFT JOIN AGENDAMIENTO ag ON au.id_audiencia = ag.id_audiencia AND ag.estado_registro = ?1 ");
		nativeQuery.append(" LEFT JOIN SALA sa ON ag.id_sala = sa.id_sala AND sa.estado_registro = ?1 ");
		nativeQuery.append(" LEFT JOIN SEDE se ON se.id_sede = sa.id_sede AND sa.estado_registro = ?1 ");
		nativeQuery.append(" LEFT JOIN RESULTADO_AUDIENCIA rau ON rau.id_audiencia = au.id_audiencia AND rau.estado_registro = ?1 ");
		nativeQuery.append(" LEFT JOIN PAGO_CASO pa ON pa.id_caso = ca.id_caso AND pa.estado_registro = ?1 ");
		nativeQuery.append(" WHERE ca.id_servicio = ?15  ");
		nativeQuery.append(" AND ca.estado_registro = ?1 ");
		nativeQuery.append(" AND fc.estado_registro = ?1 ");
		if(filtros.getIdConvenios() != null && !filtros.getIdConvenios().isEmpty()){
			nativeQuery.append(" AND co.id_convenio ").append(UtilConsultasSQL.clausulaInSQLSNumeros(filtros.getIdConvenios()));			
		}else{
			nativeQuery.append(" AND co.id_convenio IN (-1) ");
		}
		
		if(filtros.getFechaInicio() != null){			
			nativeQuery.append(" AND CAST(ca.fecha_radicacion AS DATE) >= CAST( ?16 AS DATE  ) "); 
		}
		
		if(filtros.getFechaFin() != null){
			nativeQuery.append(" AND CAST(ca.fecha_radicacion AS DATE) <= CAST( ?17 AS DATE ) ");			
		}
		nativeQuery.append(" ORDER BY ca.fecha_radicacion ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), FacturacionCasosConvenioDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ROL_PERSONA_APODERADO_CONVENIO);
		query.setParameter(3, UtilDominios.TIPO_EVENTO_CASO_CANCELADO);
		query.setParameter(4, UtilDominios.OBLIGACION_DAR);		
		query.setParameter(5, UtilDominios.DOMINIO_RESULTADO_CASO_CONCILIACION);		
		query.setParameter(6, UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_PARCIAL);
		query.setParameter(7, UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_TOTAL);
		query.setParameter(8, UtilDominios.DOMINIO_RESULTADOS_AUDIENCIA);			
		query.setParameter(9, UtilDominios.ESTADO_AUDIENCIA_REALIZADA);		
		query.setParameter(10, UtilDominios.TIPO_SERVICIO_CAJA_GASTOS_ADMINISTRATIVOS);
		query.setParameter(11, UtilDominios.TIPO_SERVICIO_CAJA_HONORARIOS_DEL_CONCILIADOR);		
		query.setParameter(12, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);			
		query.setParameter(13, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);		
		query.setParameter(14, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);		
		query.setParameter(15, UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO);
		query.setParameter(16, filtros.getFechaInicio());
		query.setParameter(17, filtros.getFechaFin());
		query.setParameter(18, UtilDominios.NOMBRE_DOMINIO_HONORARIOS_CONCILIADOR);
		query.setParameter(19, UtilDominios.DOMINIO_TIPO_SERVICIO_CAJA);
		query.setParameter(20, UtilDominios.NOMBRE_DOMINIO_GASTOS_ADMINISTRATIVOS);
	
		return query.getResultList();
		
	}

	/**
	 * Consulta reporte Convenios
	 * 
	 * @param filtros
	 * @return
	 */
	public List<ReporteConveniosDTO> reporteConvenios(FiltroReportesDTO filtros) {
		String ubicacion = "(select stuff((  SELECT ', ' + CONCAT (ub.direccion, ' (',zg.nombre, ') ' )  FROM UBICACION ub "
				+ " INNER JOIN ZONA_GEOGRAFICA zg ON zg.id_zona_geografica = ub.id_zona_geografica"
				+ " WHERE ub.id_persona = pe.id_persona AND ub.estado_registro = ?1  for xml path('')), 1, 1, '')) as direccion, ";
		String telefono = "(select stuff((  SELECT ', ' + te.numero FROM TELEFONO te"
				+ "  WHERE te.id_persona = pe.id_persona AND te.estado_registro = ?1 for xml path('')), 1, 1, '')) as telefono, ";
		String correo = "(select stuff((  SELECT ', ' + cor.direccion FROM CORREO_ELECTRONICO cor"
				+ " WHERE cor.id_persona = pe.id_persona AND cor.estado_registro = ?1  for xml path('')), 1, 1, '')) as correo, ";
		String renovaciones = " (SELECT  CASE   WHEN  COUNT(id_convenio) > 0 THEN COUNT(id_convenio) -1  ELSE 0"
				+ " END  FROM CONTRATO_CONVENIO WHERE id_convenio = co.id_convenio AND estado_registro = ?1  ) AS renovaciones,";
		String conciliadores = "(select stuff(( SELECT DISTINCT (', ' +  concat (pec.primer_nombre_o_razon_social, ' ',"
				+ " pec.segundo_nombre, ' ', pec.primer_apellido, ' ', pec.segundo_apellido) ) FROM RELACIONADO_CONVENIO rco "
				+ " INNER JOIN PERSONA pec ON pec.id_persona = rco.id_persona AND rco.id_rol IN "
				+ " (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL  WHERE tipo_servicio = ?3 AND estado_registro = ?1 ) "
				+ " WHERE rco.estado_registro = ?1 AND rco.id_convenio = co.id_convenio ORDER BY 1  for xml path('')), 1, 1, '')) AS conciliadores";
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT DISTINCT "); 
		nativeQuery.append(" co.nombre AS nombreConvenio, ");
		nativeQuery.append(" pe.representante_legal AS representanteConvenio, ");
		nativeQuery.append(ubicacion);
		nativeQuery.append(telefono);
		nativeQuery.append(correo);		
		nativeQuery.append(" co.fecha_inicio_vigencia  AS fechaInicio, ");
		nativeQuery.append(" co.fecha_fin_vigencia AS fechaFin, ");
		nativeQuery.append(" cc.fecha_inicio AS fechaInicioContrato, ");
		nativeQuery.append(" cc.fecha_fin AS fechaFinContrato, ");
		nativeQuery.append("  (SELECT nombre FROM DOMINIO WHERE dominio = ?4 AND codigo = ta.tipo_tarifa) AS tipoTarifa, ");
		nativeQuery.append(" ta.minimo_casos AS minimoCasos, ");
		nativeQuery.append(" ta.maximo_casos AS maximoCasos, ");
		nativeQuery.append(" ta.cuantia_minima AS cuantiaMinima, ");
		nativeQuery.append(" ta.cuantia_maxima AS cuantiaMaxima, ");
		nativeQuery.append(" (SELECT nombre FROM DOMINIO WHERE dominio = ?6 AND codigo = ta.resultado) AS resultado, ");
		nativeQuery.append(" ta.porcentaje AS porcentaje, ");
		nativeQuery.append(" ta.valor AS valor, ");
		nativeQuery.append(" cc.codigo_contrato AS codigoContrato, ");
		nativeQuery.append(renovaciones);
		nativeQuery.append(conciliadores);
		nativeQuery.append(" FROM CONVENIO co ");
		nativeQuery.append(" INNER JOIN SEDE_CONVENIO sc ON sc.id_convenio = co.id_convenio AND co.estado_registro = ?1 ");
		nativeQuery.append(" INNER JOIN SEDE se ON se.id_sede = sc.id_sede AND se.estado_registro = ?1 ");
		nativeQuery.append(" INNER JOIN CENTRO ce ON ce.id_centro = se.id_centro AND ce.estado_registro = ?1 ");		
		nativeQuery.append(" LEFT JOIN PERSONA pe ON co.id_persona = pe.id_persona "); 
		nativeQuery.append(" LEFT JOIN CONTRATO_CONVENIO cc ON cc.id_convenio = co.id_convenio AND cc.estado_registro = ?1 ");
		nativeQuery.append(" LEFT JOIN TARIFA_CONTRATO ta ON cc.codigo_contrato = ta.codigo_contrato AND ta.estado_registro = ?1 ");
		nativeQuery.append(" WHERE co.tipo_convenio = ?5 ");
		nativeQuery.append(" AND co.estado_registro = ?1 ");
		if(filtros.getCentros() != null && !filtros.getCentros().isEmpty() ){
			nativeQuery.append("AND ce.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(filtros.getCentros()));			
		}
		
		nativeQuery.append(" AND co.id_convenio = ?2 ");
		nativeQuery.append(" ORDER BY co.fecha_fin_vigencia desc , co.fecha_inicio_vigencia desc ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), ReporteConveniosDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, filtros.getIdConvenio());
		query.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(4, UtilDominios.DOMINIO_TIPO_TARIFA_CONTRATO);		
		query.setParameter(5, UtilDominios.TIPO_CONVENIO_CONVENIO);		
		query.setParameter(6, UtilDominios.DOMINIO_RESULTADO_CASO_CONCILIACION);		

		return query.getResultList();

	}

	/**
	 * CON-C-047 : Devuelve la consulta con los conciliadores por sus listas y
	 * centros
	 * 
	 * @author LRUIZ
	 * @param idCentros
	 * @param inCentros
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ListaConciliadoresDTO> listaConciliadores(Long idLista, List<Long> idCentros) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(
				" select concat(rtrim(p.primer_nombre_o_razon_social),' ',rtrim(p.segundo_nombre)) as nombres, ");
		nativeQuery.append(" concat(rtrim(p.primer_apellido),' ',rtrim(p.segundo_apellido)) as apellidos, ");
		nativeQuery.append(" p.numero_documento as numeroDocumento, ");
		nativeQuery.append(" p.numero_tarjeta_profesional as tarjetaProfesional, ");
		nativeQuery.append(" p.registro_conciliador as numeroRegistro, ");
		nativeQuery.append(
				" (select stuff((  SELECT ', ' + CONCAT (ub.direccion, ' (',zg.nombre, ') ' )  FROM UBICACION ub ");
		nativeQuery.append(" INNER JOIN ZONA_GEOGRAFICA zg ON zg.id_zona_geografica = ub.id_zona_geografica ");
		nativeQuery.append(
				"  WHERE ub.id_persona = p.id_persona AND ub.estado_registro = p.estado_registro  for xml path('')), 1, 1, '')) as direccion, ");
		nativeQuery.append(
				" (select stuff((  SELECT ', ' + te.numero FROM TELEFONO te WHERE te.id_persona = p.id_persona ");
		nativeQuery.append(
				" AND te.estado_registro = p.estado_registro and te.tipo_telefono=?1 for xml path('')), 1, 1, '')) as telefono, ");
		nativeQuery.append(
				" (select stuff((  SELECT ', ' + te.numero FROM TELEFONO te WHERE te.id_persona = p.id_persona ");
		nativeQuery.append(
				" AND te.estado_registro = p.estado_registro and te.tipo_telefono=?2 for xml path('')), 1, 1, '')) as celular, ");
		nativeQuery.append(" (select stuff((  SELECT ', ' + cor.direccion FROM CORREO_ELECTRONICO cor ");
		nativeQuery.append(
				" WHERE cor.id_persona = p.id_persona AND cor.estado_registro = p.estado_registro  for xml path('')), 1, 1, '')) as correo, ");
		nativeQuery.append(" (select stuff((  select  distinct ', ' + m.nombre from PERSONA_SERVICIO_MATERIA psm ");
		nativeQuery.append(
				" inner join servicio s on s.id_servicio=psm.id_servicio and s.estado_registro=psm.estado_registro ");
		nativeQuery.append(
				" inner join MATERIA m on m.id_materia=psm.id_materia and m.estado_registro=psm.estado_registro ");
		nativeQuery.append(" where psm.estado_registro=p.estado_registro and psm.id_persona=p.id_persona ");
		nativeQuery.append(" and s.tipo=?3   for xml path('')), 1, 1, '')) as materias, ");
		nativeQuery.append(" m.fecha_fin vencimientoMembresia, ");
		nativeQuery.append(
				" (select nombre from dominio where codigo=epts.estado and dominio =?4) estado ");
		nativeQuery.append(" , l.nombre as lista ");
		nativeQuery.append(" from persona p ");
		nativeQuery.append(
				" inner join ROL_PERSONA rp on rp.id_persona=p.id_persona and p.estado_registro=rp.estado_registro ");
		nativeQuery.append(" inner join rol r on r.id_rol= rp.id_rol and r.estado_registro=rp.estado_registro ");
		nativeQuery.append(" and r.nombre = ?5 ");
		nativeQuery.append(
				" left join estado_persona_tipo_servicio epts on epts.id_persona=p.id_persona and epts.tipo_servicio=?3 ");
		nativeQuery.append(" left join (select max(id_membresia) as max_id_membresia, id_persona, estado_registro ");
		nativeQuery.append(" from membresia where cast(fecha_fin as date) >= cast(getdate() as date) ");
		nativeQuery.append(" group by id_persona, estado_registro) ");
		nativeQuery.append(" mm on mm.id_persona= p.id_persona and mm.estado_registro=p.estado_registro ");
		nativeQuery.append(
				" left join membresia m on mm.max_id_membresia=m.id_membresia and m.estado_registro=p.estado_registro ");
		nativeQuery.append(" left join LISTA l on l.id_lista=rp.id_lista and l.estado_registro=rp.estado_registro ");
		nativeQuery.append(" where p.estado_registro=?6 ");
		nativeQuery.append(" and fecha_fin_vigencia is null ");
		if (idLista != null && idLista >0)
			nativeQuery.append(" and l.id_lista=?7 ");
		
		nativeQuery.append(" and rp.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(idCentros));
		
		Query query = em.createNativeQuery(nativeQuery.toString(), ListaConciliadoresDTO.class);
		query.setParameter(1, UtilDominios.TIPO_TELEFONO_FIJO);
		query.setParameter(2, UtilDominios.TIPO_TELEFONO_CELULAR);
		query.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(4, UtilDominios.DOMINIO_ESTADO_CONCILIADORES);
		query.setParameter(5, UtilDominios.ROL_PERSONA_CONCILIADOR);		
		query.setParameter(6, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(7, idLista);
		
		return query.getResultList();
		
	}
	
	public List<ReporteCasosCerradosConciliacionDTO> consultarCasosCerradosConciliacion(FiltroReportesDTO filtro, List<String> nombreRoles){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT DISTINCT fecha_radicacion                       as fechaRadicacion, ");
		nativeQuery.append("                DATEPART(mm, e.fecha_evento)           AS mesResultado , ");
		nativeQuery.append("                c.id_caso                              AS codigoCaso   , ");
		nativeQuery.append("                c.nombre                               AS nombreCaso   , ");
		nativeQuery.append("                ISNULL(cv.nombre, 'TRAMITE ORDINARIO') AS tipoCaso     , ");
		nativeQuery.append("                e.fecha_evento                         AS fechaRegistro, ");
		nativeQuery.append("                estado_caso                            AS estadoCaso, ");		
		nativeQuery.append("                dr.nombre                              AS resultado    , ");
		nativeQuery.append("                dre.nombre                             AS estadoResultado    , ");
		nativeQuery.append("                ISNULL( ");
		nativeQuery.append("                (SELECT    top 1 concat(p.primer_nombre_o_razon_social, ' ' , ISNULL(p.segundo_nombre, ''), ' ', p.primer_apellido, ' ', ISNULL(p.segundo_apellido, '')) AS nombre ");
		nativeQuery.append("                FROM       persona p ");
		nativeQuery.append("                           INNER JOIN ROL_PERSONA_CASO rp ");
		nativeQuery.append("                           ON         p.id_persona = rp.id_persona ");
		nativeQuery.append("                WHERE      id_rol = ?1 ");
		nativeQuery.append("                AND        rp.id_caso = c.id_caso AND rp.estado = ?2 AND rp.estado_registro = ?3 ) , '') AS nombreConciliador, ");
		nativeQuery.append("                ISNULL( ");
		nativeQuery.append("                (SELECT    top 1 concat(p.primer_nombre_o_razon_social, ' ' , ISNULL(p.segundo_nombre, ''), ' ', p.primer_apellido, ' ', ISNULL(p.segundo_apellido, '')) AS nombre ");
		nativeQuery.append("                FROM       persona p ");
		nativeQuery.append("                           INNER JOIN ROL_PERSONA_CASO rp ");
		nativeQuery.append("                           ON         p.id_persona = rp.id_persona ");
		nativeQuery.append("                WHERE      id_rol                  = ?4 ");
		nativeQuery.append("                AND        rp.id_caso              = c.id_caso), '') AS apoderado   , ");
		nativeQuery.append("                CONVERT(VARCHAR(MAX),hechos)       AS hechos      , ");
		nativeQuery.append("                CONVERT(VARCHAR(MAX),pretensiones) AS pretensiones, ");
		nativeQuery.append("                (SELECT   top 1                       o.valor_total_acuerdo ");
		nativeQuery.append("                FROM      audiencia a ");
		nativeQuery.append("                          LEFT JOIN RESULTADO_AUDIENCIA r WITH (NOLOCK) ");
		nativeQuery.append("                          ON        r.id_audiencia = a.id_audiencia ");
		nativeQuery.append("                          LEFT JOIN obligacion o WITH (NOLOCK) ");
		nativeQuery.append("                          ON        o.id_resultado_audiencia = r.id_resultado_audiencia ");
		nativeQuery.append("                WHERE     id_caso                            = c.id_caso ");
		nativeQuery.append("                AND       r.estado                          <> ?5 ) AS valorAcuerdo, ");
		nativeQuery.append("                (SELECT    top 1 ss.nombre ");
		nativeQuery.append("                FROM       audiencia a ");
		nativeQuery.append("                           INNER JOIN AGENDAMIENTO ag WITH (NOLOCK) ");
		nativeQuery.append("                           ON         a.id_audiencia = ag.id_audiencia ");
		nativeQuery.append("                           INNER JOIN SALA s WITH (NOLOCK) ");
		nativeQuery.append("                           ON         ag.id_sala = s.id_sala ");
		nativeQuery.append("                           INNER JOIN sede ss WITH (NOLOCK) ");
		nativeQuery.append("                           ON         s.id_sede = ss.id_sede ");
		nativeQuery.append("                WHERE      a.id_caso            = c.id_caso ) AS sedeAudiencia, ");
		nativeQuery.append("                (SELECT COUNT(*) ");
		nativeQuery.append("                FROM    AUDIENCIA a WITH (NOLOCK) ");
		nativeQuery.append("                WHERE   a.id_caso = c.id_caso ) AS numeroAudiencias, ");
		nativeQuery.append("                a.hora_inicio AS fechaUltimaAudiencia ");
		nativeQuery.append("FROM            caso c WITH (NOLOCK) ");
		nativeQuery.append("                INNER JOIN evento e WITH (NOLOCK) ");
		nativeQuery.append("                ON              c.id_caso = e.id_caso ");
		nativeQuery.append("                LEFT JOIN CONVENIO cv WITH (NOLOCK) ");
		nativeQuery.append("                ON              c.id_convenio = cv.id_convenio ");
		nativeQuery.append("                INNER JOIN audiencia a WITH (NOLOCK) ");
		nativeQuery.append("                ON              a.id_caso = c.id_caso ");
		nativeQuery.append("                LEFT JOIN RESULTADO_AUDIENCIA r WITH (NOLOCK) ");
		nativeQuery.append("                ON              r.id_audiencia = a.id_audiencia ");
		nativeQuery.append("                AND ");
		nativeQuery.append("                                ( ");
		nativeQuery.append("                                                ( ");
		nativeQuery.append("                                                                r.tipo_resultado_audiencia IN (?8 , ");
		nativeQuery.append("                                                                                               ?9 , ");
		nativeQuery.append("                                                                                               ?10, ");
		nativeQuery.append("                                                                                               ?11, ");
		nativeQuery.append("                                                                                               ?12, ");
		nativeQuery.append("                                                                                               ?13) ");
		nativeQuery.append("                                                AND             r.estado = ?5 ");
		nativeQuery.append("                                                ) ");
		nativeQuery.append("                                OR ");
		nativeQuery.append("                                                ( ");
		nativeQuery.append("                                                                r.tipo_resultado_audiencia IN (?14, ");
		nativeQuery.append("                                                                                               ?15, ");
		nativeQuery.append("                                                                                               ?16, ");
		nativeQuery.append("                                                                                               ?17) ");
		nativeQuery.append("                                                AND             r.estado <> ?5 ");
		nativeQuery.append("                                                ) ");
		nativeQuery.append("                                ) ");
		nativeQuery.append("                LEFT JOIN dominio dr WITH (NOLOCK) ");
		nativeQuery.append("                ON              r.tipo_resultado_audiencia = dr.codigo ");
		nativeQuery.append("                AND             dr.dominio                 = ?6 ");
		nativeQuery.append("                LEFT JOIN dominio dre WITH (NOLOCK) ");
		nativeQuery.append("                ON              r.estado    = dre.codigo ");
		nativeQuery.append("                AND             dre.dominio = ?7 ");
		nativeQuery.append("WHERE           c.id_servicio IN (?18, ");
		nativeQuery.append("                                  ?19, ");
		nativeQuery.append("                                  ?20) ");
		nativeQuery.append("AND             tipo_evento IN (?21, ");
		nativeQuery.append("                                ?22, ");
		nativeQuery.append("                                ?23) ");
		nativeQuery.append("AND             estado_caso IN (?21, ");
		nativeQuery.append("                                ?24, ");
		nativeQuery.append("                                ?25) ");
		nativeQuery.append("AND             CONVERT(VARCHAR(10), fecha_evento, 111) BETWEEN ?26 AND             ?27 ");
		nativeQuery.append("AND             a.consecutivo = ");
		nativeQuery.append("                (SELECT MAX(consecutivo) ");
		nativeQuery.append("                FROM    AUDIENCIA ");
		nativeQuery.append("                WHERE   id_caso = c.id_caso) ");
		nativeQuery.append("AND             EXISTS ");
		nativeQuery.append("                (SELECT fecha_evento ");
		nativeQuery.append("                FROM    evento ee ");
		nativeQuery.append("                WHERE   tipo_evento IN (?22, ");
		nativeQuery.append("                                        ?23) ");
		nativeQuery.append("                AND     CONVERT(VARCHAR(10), ee.fecha_evento, 111) BETWEEN ?26 AND     ?27 ");
		nativeQuery.append("                AND     ee.id_caso = c.id_caso) ");
		nativeQuery.append("AND             fecha_evento = ");
		nativeQuery.append("                (SELECT MAX(fecha_evento) ");
		nativeQuery.append("                FROM    evento er ");
		nativeQuery.append("                WHERE   tipo_evento IN (?21, ");
		nativeQuery.append("                                        ?22, ");
		nativeQuery.append("                                        ?23) ");
		nativeQuery.append("                AND     er.id_caso = c.id_caso) ");
		nativeQuery.append("        UNION ");
		nativeQuery.append("SELECT DISTINCT fecha_radicacion                       AS fechaRadicacion, ");
		nativeQuery.append("                DATEPART(mm, e.fecha_evento)           AS mesResultado , ");
		nativeQuery.append("                c.id_caso                              AS codigoCaso   , ");
		nativeQuery.append("                c.nombre                               AS nombreCaso   , ");
		nativeQuery.append("                ISNULL(cv.nombre, 'TRAMITE ORDINARIO') AS tipoCaso     , ");
		nativeQuery.append("                e.fecha_evento                         AS fechaRegistro, ");
		nativeQuery.append("                estado_caso                            AS estadoCaso, ");		
		nativeQuery.append("                dr.nombre                              AS resultado    , ");
		nativeQuery.append("                ''                                     AS estadoResultado    , ");
		nativeQuery.append("                ISNULL( ");
		nativeQuery.append("                (SELECT    concat(p.primer_nombre_o_razon_social, ' ' , ISNULL(p.segundo_nombre, ''), ' ', p.primer_apellido, ' ', ISNULL(p.segundo_apellido, '')) AS nombre ");
		nativeQuery.append("                FROM       persona p ");
		nativeQuery.append("                           INNER JOIN ROL_PERSONA_CASO rp ");
		nativeQuery.append("                           ON         p.id_persona = rp.id_persona ");
		nativeQuery.append("                WHERE      id_rol                  = ?1 ");
		nativeQuery.append("                AND        rp.id_caso              = c.id_caso ");
		nativeQuery.append("                AND        rp.estado               = ?2 ");
		nativeQuery.append("                AND        rp.estado_registro      = ?3), '') AS nombreConciliador, ");
		nativeQuery.append("                ISNULL( ");
		nativeQuery.append("                (SELECT    top 1 concat(p.primer_nombre_o_razon_social, ' ' , ISNULL(p.segundo_nombre, ''), ' ', p.primer_apellido, ' ', ISNULL(p.segundo_apellido, '')) AS nombre ");
		nativeQuery.append("                FROM       persona p ");
		nativeQuery.append("                           INNER JOIN ROL_PERSONA_CASO rp ");
		nativeQuery.append("                           ON         p.id_persona = rp.id_persona ");
		nativeQuery.append("                WHERE      id_rol                  = ?4 ");
		nativeQuery.append("                AND        rp.id_caso              = c.id_caso), '') AS apoderado   , ");
		nativeQuery.append("                CONVERT(VARCHAR(MAX),hechos)       AS hechos      , ");
		nativeQuery.append("                CONVERT(VARCHAR(MAX),pretensiones) AS pretensiones, ");
		nativeQuery.append("                (SELECT   top 1                       o.valor_total_acuerdo ");
		nativeQuery.append("                FROM      audiencia a ");
		nativeQuery.append("                          LEFT JOIN RESULTADO_AUDIENCIA r WITH (NOLOCK) ");
		nativeQuery.append("                          ON        r.id_audiencia = a.id_audiencia ");
		nativeQuery.append("                          LEFT JOIN obligacion o WITH (NOLOCK) ");
		nativeQuery.append("                          ON        o.id_resultado_audiencia = r.id_resultado_audiencia ");
		nativeQuery.append("                WHERE     id_caso                            = c.id_caso ");
		nativeQuery.append("                AND       r.estado                          <> ?5) AS valorAcuerdo, ");
		nativeQuery.append("                (SELECT    top 1 ss.nombre ");
		nativeQuery.append("                FROM       audiencia a WITH (NOLOCK) ");
		nativeQuery.append("                           INNER JOIN AGENDAMIENTO ag WITH (NOLOCK) ");
		nativeQuery.append("                           ON         a.id_audiencia = ag.id_audiencia ");
		nativeQuery.append("                           INNER JOIN SALA s WITH (NOLOCK) ");
		nativeQuery.append("                           ON         ag.id_sala = s.id_sala ");
		nativeQuery.append("                           INNER JOIN sede ss WITH (NOLOCK) ");
		nativeQuery.append("                           ON         s.id_sede = ss.id_sede ");
		nativeQuery.append("                WHERE      a.id_caso            = c.id_caso) AS sedeAudiencia, ");
		nativeQuery.append("                (SELECT COUNT(*) ");
		nativeQuery.append("                FROM    AUDIENCIA a WITH (NOLOCK) ");
		nativeQuery.append("                WHERE   a.id_caso = c.id_caso ) ");
		nativeQuery.append("                AS sedeAudiencia, ");
		nativeQuery.append("                (SELECT MAX(hora_inicio) ");
		nativeQuery.append("                FROM    audiencia aa ");
		nativeQuery.append("                WHERE   aa.id_caso = c.id_caso ");
		nativeQuery.append("                ) ");
		nativeQuery.append("                AS fechaUltimaAudiencia ");
		nativeQuery.append("FROM            caso c WITH (NOLOCK) ");
		nativeQuery.append("                INNER JOIN evento e WITH (NOLOCK) ");
		nativeQuery.append("                ON              c.id_caso = e.id_caso ");
		nativeQuery.append("                LEFT JOIN CONVENIO cv WITH (NOLOCK) ");
		nativeQuery.append("                ON              c.id_convenio = cv.id_convenio ");
		nativeQuery.append("                LEFT JOIN dominio dr WITH (NOLOCK) ");
		nativeQuery.append("                ON              c.resultado = dr.codigo ");
		nativeQuery.append("                AND             dr.dominio  = ?28 ");
		nativeQuery.append("WHERE           c.id_servicio IN (?18, ");
		nativeQuery.append("                                  ?19, ");
		nativeQuery.append("                                  ?20) ");
		nativeQuery.append("AND             tipo_evento IN (?21) ");
		nativeQuery.append("AND             e.id_evento = ");
		nativeQuery.append("                (SELECT top 1 id_evento ");
		nativeQuery.append("                FROM    evento ee ");
		nativeQuery.append("                WHERE   ee.id_caso     = c.id_caso ");
		nativeQuery.append("                AND     ee.tipo_evento = ?21 ");
		nativeQuery.append("                ) ");
		nativeQuery.append("AND             CONVERT(VARCHAR(10), fecha_evento, 111) BETWEEN ?26 AND             ?27 ");
		nativeQuery.append("ORDER BY        c.fecha_radicacion");
				
		Query query = em.createNativeQuery(nativeQuery.toString(), ReporteCasosCerradosConciliacionDTO.class);

		query.setParameter(1, UtilConstantes.ID_ROL_CONCILIADOR);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(4, UtilConstantes.ID_ROL_APODERADO_CONVOCANTE);
		query.setParameter(5, UtilDominios.ESTADO_RESULTADO_SIN_DOCUMENTO);
		query.setParameter(6, UtilDominios.DOMINIO_RESULTADOS_AUDIENCIA);
		query.setParameter(7, UtilDominios.DOMINIO_ESTADO_RESULTADO_AUDIENCIA);
		query.setParameter(8, UtilDominios.RESULTADO_AUDIENCIA_FALTA_COMPETENCIA);
		query.setParameter(9, UtilDominios.RESULTADO_AUDIENCIA_CANCELADA);
		query.setParameter(10, UtilDominios.RESULTADO_AUDIENCIA_SUSPENSION);
		query.setParameter(11, UtilDominios.RESULTADO_AUDIENCIA_RECESO);
		query.setParameter(12, UtilDominios.RESULTADO_AUDIENCIA_REPROGRAMACION);
		query.setParameter(13, UtilDominios.RESULTADO_AUDIENCIA_ARREGLO_DIRECTO);
		query.setParameter(14, UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_TOTAL);
		query.setParameter(15, UtilDominios.RESULTADO_AUDIENCIA_IMPOSIBILIDAD_DE_ACUERDO);
		query.setParameter(16, UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_PARCIAL);
		query.setParameter(17, UtilDominios.RESULTADO_AUDIENCIA_INASISTENCIA);
		query.setParameter(18, UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO);
		query.setParameter(19, UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO);
		query.setParameter(20, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA);
		query.setParameter(21, UtilDominios.TIPO_EVENTO_CASO_CANCELADO);
		query.setParameter(22, UtilDominios.TIPO_EVENTO_CASO_CONTROL_LEGALIDAD);
		query.setParameter(23, UtilDominios.TIPO_EVENTO_CASO_REGISTRADO);
		query.setParameter(24, UtilDominios.ESTADO_CASO_ASIGNADO_CONTROL_DE_LEGALIDAD);
		query.setParameter(25, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(26, filtro.getFechaInicio());
		query.setParameter(27, filtro.getFechaFin());
		query.setParameter(28, UtilDominios.DOMINIO_RESULTADO_CASO_CONCILIACION);
		
		return query.getResultList();
	}
	
	/** CON-C-033
	 * Reporte de casos cerrados de convenio
	 * @param filtro
	 * @param nombreRoles
	 * @return List<ReporteCasosCerradosConciliacionDTO>
	 */
	public List<ReporteCasosCerradosConveniosDTO> consultarCasosCerradosConvenios( FiltroReportesDTO filtros ){
		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append("SELECT CAST(ca.fecha_radicacion AS DATE) AS fechaRadicacion ");
		nativeQuery.append(" , ca.id_caso AS codigoCaso ");
		nativeQuery.append(" , cnv.nombre AS nombreConvenio ");
		nativeQuery.append(" , ca.nombre AS parte ");
		nativeQuery.append(" , (SELECT nombre ");
		nativeQuery.append("        FROM dominio ");
		nativeQuery.append("       WHERE codigo      = ca.resultado ");
		nativeQuery.append("             AND dominio = ?5) AS resultado ");
		nativeQuery.append(" , ev_s.fecha_evento  AS fechaRegistro ");
		nativeQuery.append(" , concat(rtrim(pcon.primer_nombre_o_razon_social), rtrim(' '+pcon.segundo_nombre), rtrim(' '+pcon.primer_apellido), rtrim(' '+pcon.segundo_apellido)) AS nombreConciliador ");
		nativeQuery.append(" , (SELECT stuff((SELECT ', ' + CONCAT (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) ");
		nativeQuery.append("                    FROM ROL_PERSONA_CASO rpc WITH (nolock) ");
		nativeQuery.append("                         INNER JOIN PERSONA pe WITH (nolock) ");
		nativeQuery.append("                            ON rpc.id_persona=pe.id_persona ");
		nativeQuery.append("                   WHERE rpc.id_caso            =ca.id_caso ");
		nativeQuery.append("                         AND rpc.estado_registro=?1 ");
		nativeQuery.append("                         AND rpc.id_rol         IN (?12, ?13) FOR XML path('')), 1, 1, '')) AS apoderados ");
		nativeQuery.append(" , se.nombre AS sedeAudiencia ");
		nativeQuery.append(" , convert(varchar(MAX), hechos)       AS hechos ");
		nativeQuery.append(" , (select case when ca.tipo_cuantia= ?6 then ca.valor_pretensiones end) as cuantia  ");
		nativeQuery.append(" , (SELECT sum(valor_total_acuerdo) AS valor_total_acuerdo ");
		nativeQuery.append("      FROM OBLIGACION o WITH (nolock) ");
		nativeQuery.append("     WHERE o.id_resultado_audiencia = ra.id_resultado_audiencia) AS valorAcuerdo ");
		nativeQuery.append(" , (SELECT count(*) FROM audiencia au WHERE au.estado_registro= ?1 and au.estado= ?8 and au.id_caso =ca.id_caso) as numeroAudiencias  ");
		nativeQuery.append(" FROM ");
		nativeQuery.append(" CASO ca WITH (nolock) ");
		nativeQuery.append("INNER JOIN CONVENIO cnv ");
		nativeQuery.append("    ON cnv.id_convenio = ca.id_convenio ");
		nativeQuery.append("INNER JOIN SERVICIO ser ");
		nativeQuery.append("    ON ser.id_servicio = ca.id_servicio ");
		nativeQuery.append("LEFT JOIN evento ev_s WITH (nolock) ");
		nativeQuery.append("    ON ev_s.id_caso              = ca.id_caso ");
		nativeQuery.append("    AND ev_s.tipo_evento      = ?16 ");
		nativeQuery.append("    AND ev_s.estado_registro  = ?1 ");
		nativeQuery.append("    AND ev_s.observaciones like 'Caso reportado al SICAAC%' ");
		nativeQuery.append("LEFT JOIN AUDIENCIA ult_au WITH (nolock) ");
		nativeQuery.append("    ON ca.id_caso                 = ult_au.id_caso ");
		nativeQuery.append("    AND ult_au.estado_registro = ?1 ");
		nativeQuery.append("    AND ult_au.hora_inicio = ");
		nativeQuery.append("        (SELECT max(hora_inicio) FROM audiencia aa WHERE aa.id_caso = ca.id_caso ) ");
		nativeQuery.append("LEFT JOIN AGENDAMIENTO ag  ");
		nativeQuery.append(" ON ag.id_audiencia = ult_au.id_audiencia  ");
		nativeQuery.append(" AND ag.estado_registro = ?1  ");
		nativeQuery.append(" LEFT JOIN SALA sa  ");
		nativeQuery.append(" ON sa.id_sala = ag.id_sala  ");
		nativeQuery.append(" AND sa.estado_registro = ?1 ");
		nativeQuery.append("LEFT JOIN SEDE se WITH (nolock) ");
		nativeQuery.append("    ON se.id_sede             = sa.id_sede ");
		nativeQuery.append("    AND se.estado_registro = ?1 ");
		nativeQuery.append("LEFT JOIN RESULTADO_AUDIENCIA ra WITH (nolock) ");
		nativeQuery.append("    ON ra.id_audiencia = ult_au.id_audiencia ");
		nativeQuery.append("       AND ra.tipo_resultado_audiencia in (1, 3) ");
		nativeQuery.append("LEFT JOIN ROL_PERSONA_CASO rpc_con WITH (nolock) ");
		nativeQuery.append("    ON ca.id_caso = rpc_con.id_caso ");
		nativeQuery.append("       AND rpc_con.id_rol IN ");
		nativeQuery.append("        (SELECT id_rol FROM tipo_de_servicio_rol ");
		nativeQuery.append("          WHERE tipo_servicio = ?9 ");
		nativeQuery.append("        AND rpc_con.tipo_nombramiento = ?10 ");
		nativeQuery.append("        AND rpc_con.estado            = '?11 ");
		nativeQuery.append("        AND rpc_con.estado_registro   = ?1 ");
		nativeQuery.append("LEFT JOIN PERSONA pcon WITH (nolock) ");
		nativeQuery.append("    ON pcon.id_persona          =rpc_con.id_persona ");
		nativeQuery.append("       AND pcon.estado_registro = ?1 ");
		nativeQuery.append("WHERE ");
		nativeQuery.append("    ser.tipo = ?9 ");
		nativeQuery.append("    AND convert(varchar(10), ev_s.fecha_evento, 111) BETWEEN ?14 AND ?15 ");
		nativeQuery.append("    AND ser.id_servicio = 8 ");
		nativeQuery.append("    AND ca.estado_registro  = ?1 ");
		nativeQuery.append("    AND ser.estado_registro = ?1 ");
		if( filtros.getIdConvenio() != null )
			nativeQuery.append(" AND co.id_convenio = ?19 ");
		if( filtros.getCentros() != null )
			nativeQuery.append(" AND se.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros( filtros.getCentros() ));
		nativeQuery.append("ORDER BY ");
		nativeQuery.append("    ca.id_caso ");

		Query query = em.createNativeQuery(nativeQuery.toString(), ReporteCasosCerradosConveniosDTO.class);

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(5, UtilDominios.DOMINIO_RESULTADO_CASO_CONCILIACION);
		query.setParameter(6, UtilDominios.TIPO_CUANTIA_CONCILIACION_DETERMINADO);
		query.setParameter(8, UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
		query.setParameter(9, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(10, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(11, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(12, UtilConstantes.ID_ROL_APODERADO_CONVOCANTE);
		query.setParameter(13, UtilConstantes.ID_ROL_APODERADO_CONVOCADO);
		query.setParameter(14, filtros.getFechaInicio());
		query.setParameter(15, filtros.getFechaFin());
		query.setParameter(16, UtilDominios.TIPO_EVENTO_CASO_REGISTRADO);
		query.setParameter(17, UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO);
		query.setParameter(19, filtros.getIdConvenio());
		
		return query.getResultList();
	}

	/** CON-C-036
	 * Reporte de casos devueltos en control de legalidad
	 * @param filtro
	 * @param nombreRoles
	 * @return List<ReporteCasosCerradosConciliacionDTO>
	 */
	public List<ReporteCasosDevueltosControlLegalidadDTO> consultarCasosDevueltosControlLegalidad( FiltroReportesDTO filtros ){
		
		String motivoDevolucion= UtilDominios.DOMINIO_MOTIVO_DEVOLUCION_CASO;
		
		if(filtros.getIdServicio() != null && filtros.getIdServicio() == 17){
			
			motivoDevolucion= UtilDominios.DOMINIO_MOTIVO_DEVOLUCION_CASO_CALIDAD;
		}
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT d.id_caso as idCaso, ");
		nativeQuery.append(" (select nombre from servicio where id_servicio = ca.id_servicio) as tipoCaso, ");
		nativeQuery.append(" ca.nombre as nombreCaso, ");
		nativeQuery.append(" concat(rtrim(pcon.primer_nombre_o_razon_social), rtrim(' '+pcon.segundo_nombre), rtrim(' '+pcon.primer_apellido), rtrim(' '+pcon.segundo_apellido)) as nombreConciliador, ");
		nativeQuery.append(" concat(rtrim(paco.primer_nombre_o_razon_social), rtrim(' '+paco.segundo_nombre), rtrim(' '+paco.primer_apellido), rtrim(' '+paco.segundo_apellido)) as nombreAnalista, ");
		nativeQuery.append(" ddr.fecha as fechaDevolucion, ");
		nativeQuery.append(" stuff((select ', ', (select nombre from dominio where codigo = motivo and dominio = ?2 ) ");
				nativeQuery.append(" from MOTIVO_DEVOLUCION md_stuff ");
				nativeQuery.append(" where md_stuff.id_devolucion = ddr.id_devolucion_documento_resultado ");
		nativeQuery.append(" FOR xml PATH ('')), 1, 2, '') AS causalDevolucion, ");
		
		nativeQuery.append(" ddr.fecha_correccion as fechaCorrecion, ");
		
		nativeQuery.append(" case when ddr.corrige = 'true' then 'SI' when ddr.corrige = 'false' then 'NO' end as corrige, ");
		nativeQuery.append(" ddr.observaciones as observaciones ");
		nativeQuery.append(" FROM DEVOLUCION_DOCUMENTO_RESULTADO ddr ");
		nativeQuery.append(" INNER JOIN DOCUMENTO d ");
		nativeQuery.append(" ON ddr.id_documento = d.id_documento ");
		nativeQuery.append(" INNER JOIN CASO ca ");
		nativeQuery.append(" ON ca.id_caso = d.id_caso ");
		if( filtros.getMotivoDevolucion() != null ){
			nativeQuery.append(" INNER JOIN MOTIVO_DEVOLUCION md ");
			nativeQuery.append(" ON md.id_devolucion = ddr.id_devolucion_documento_resultado ");
		}
		if( filtros.getIdConvenio() != null && filtros.getIdServicio().equals(UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO) ){
			nativeQuery.append(" INNER JOIN CONVENIO co ");
			nativeQuery.append(" ON ca.id_convenio = co.id_convenio ");
		}
		if( filtros.getCentros() != null && !filtros.getCentros().isEmpty() ){
			nativeQuery.append(" INNER JOIN SEDE sede ");
			nativeQuery.append(" ON sede.id_sede = ca.id_sede ");
		}
		nativeQuery.append(" INNER JOIN ROL_PERSONA_CASO rpc_con ");
		nativeQuery.append(" ON rpc_con.id_caso = ca.id_caso ");
		nativeQuery.append(" AND rpc_con.id_rol IN (select id_rol from tipo_de_servicio_rol where tipo_servicio = ?3 ) ");
		nativeQuery.append(" AND rpc_con.tipo_nombramiento = ?4 ");
		nativeQuery.append(" AND rpc_con.estado = ?5 ");
		nativeQuery.append(" INNER JOIN PERSONA pcon ");
		nativeQuery.append(" ON pcon.id_persona=rpc_con.id_persona ");
		nativeQuery.append(" INNER JOIN PERSONA paco ");
		nativeQuery.append(" ON ddr.id_persona = paco.id_persona ");
		
		nativeQuery.append(" WHERE ddr.estado_registro = ?1 ");
		
		nativeQuery.append(" AND ca.estado_registro = ?1 ");
		nativeQuery.append(" AND rpc_con.estado_registro = ?1 ");
		nativeQuery.append(" AND pcon.estado_registro = ?1 ");
		if( filtros.getIdConciliador() != null )
			nativeQuery.append(" AND rpc_con.id_persona = ?6 ");
		if( filtros.getFechaInicio() != null && filtros.getFechaFin() != null )
			nativeQuery.append(" AND (cast(ddr.fecha as DATE) between cast( ?8 as DATE) AND cast( ?9 as DATE)) ");
		if( filtros.getMotivoDevolucion() != null )
			nativeQuery.append(" AND md.motivo = ?10 ");
		if( filtros.getIdPersona() != null )
			nativeQuery.append(" AND ddr.id_persona = ?11 ");
		if( filtros.getIdConvenio() != null && filtros.getIdServicio().equals(UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO) ){
			nativeQuery.append(" AND co.id_convenio = ?12 ");
			nativeQuery.append(" AND ca.id_servicio = ?13 ");
			nativeQuery.append(" AND co.estado_registro = ?1 ");
		}else if( filtros.getIdConvenio() != null && filtros.getIdServicio().equals(UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO) )
			nativeQuery.append(" AND ca.id_servicio = ?13 ");
		if( filtros.getIdCaso() != null )
			nativeQuery.append(" AND ca.id_caso = ?14 ");
		if( filtros.getCentros() != null && !filtros.getCentros().isEmpty() ){
			nativeQuery.append(" AND sede.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros( filtros.getCentros() ));
			nativeQuery.append(" AND sede.estado_registro = ?1 ");
		}
		nativeQuery.append(" ORDER BY ddr.fecha DESC ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), ReporteCasosDevueltosControlLegalidadDTO.class);

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, motivoDevolucion);
		query.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(4, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		
		query.setParameter(6, filtros.getIdConciliador());
		query.setParameter(8, filtros.getFechaInicio());
		query.setParameter(9, filtros.getFechaFin());
		query.setParameter(10, filtros.getMotivoDevolucion());
		query.setParameter(11, filtros.getIdPersona());
		query.setParameter(12, filtros.getIdConvenio());
		query.setParameter(13, filtros.getIdServicio());
		query.setParameter(14, filtros.getIdCaso());
		
		return query.getResultList();
	}
	
	/** CON-C-050, Reporte general de conciliacion
	 * @param filtros
	 * @return
	 */
	public List<ReporteGeneralCasosConciliacionDTO> consultarReporteGeneralConciliacion( FiltroReportesDTO filtros ){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT ");
		nativeQuery.append("     CASE WHEN ca.id_servicio = ?16 THEN ser.nombre ELSE cnv.nombre END AS tipoCaso ");
		nativeQuery.append("   , ca.id_caso AS idCaso ");
		nativeQuery.append("   , CASE WHEN ca.id_servicio = ?15 THEN co.lugar ELSE se.nombre END AS sedeCaso ");
		nativeQuery.append("   , ca.nombre                           AS nombreCaso ");
		nativeQuery.append("   , convert(varchar(MAX), hechos)       AS hechos ");
		nativeQuery.append("   , convert(varchar(MAX), pretensiones) AS pretensiones ");
		nativeQuery.append("   , CASE WHEN ca.id_convenio IS NOT NULL THEN NULL ELSE ");
		nativeQuery.append("   (SELECT fecha_pago FROM pago_caso pc WHERE pc.id_caso = ca.id_caso) END AS fechaPagoCaso ");
		nativeQuery.append("   , CAST(ca.fecha_radicacion AS DATE) AS fechaRadicacionCaso ");
		nativeQuery.append("   , 0 AS numeroDiasRadicacion ");
		nativeQuery.append("   , ev.fecha_evento as fechaReparto ");
		nativeQuery.append("   , 0 AS numeroDiasReparto ");
		nativeQuery.append("   , (SELECT Cast(max(fecha_ultima_modificacion) AS DATE) ");
		nativeQuery.append("        FROM CARTA_PERSONA cp ");
		nativeQuery.append("       WHERE id_audiencia = au.id_audiencia ");
		nativeQuery.append("             AND estado_registro = ?1 ");
		nativeQuery.append("             AND (estado_carta in (?25, ?26)  OR  ");
		nativeQuery.append("             exists(SELECT 1 from CORREO_ROL_PERSONA_CASO where id_carta_persona = cp.id_carta_persona) )) AS fechaCitacion ");
		nativeQuery.append("   , au.hora_inicio AS fechaPrimeraAudiencia ");
		nativeQuery.append("   , 0 AS numeroDiasAudiencia ");
		nativeQuery.append("   , 0 AS totalDias ");
		nativeQuery.append("   , (select count(id_audiencia) from audiencia where id_caso = ca.id_caso and estado = ?3 and estado_registro = ?1 ) AS numeroAudienciasRealizadas ");
		nativeQuery.append("   , (SELECT fecha_radicacion FROM documento da WHERE da.id_documento = ra.id_documento AND da.tipo_documento = ?18) AS fechaActa ");
		nativeQuery.append("   , (SELECT fecha_radicacion FROM documento da WHERE da.id_documento = ra.id_documento AND da.tipo_documento = ?21) AS fechaConstancia ");
		nativeQuery.append("   , (SELECT nombre FROM dominio WHERE codigo = ca.estado_caso AND dominio = ?4) AS ultimoEstadoCaso ");
		nativeQuery.append("   , (SELECT nombre FROM dominio WHERE codigo = ca.resultado AND dominio = ?5) AS resultadoCaso ");
		nativeQuery.append("   , concat(rtrim(pcon.primer_nombre_o_razon_social), rtrim(' '+pcon.segundo_nombre), rtrim(' '+pcon.primer_apellido), rtrim(' '+pcon.segundo_apellido)) AS nombreConciliador ");
		nativeQuery.append("   , m.nombre AS materiaCaso ");
		nativeQuery.append("   , (SELECT CASE WHEN ca.tipo_cuantia= ?6 THEN ca.valor_pretensiones END) AS cuantiaCaso ");
		nativeQuery.append("   , ca.observaciones   AS observacionesCaso ");
		nativeQuery.append("   , ev_s.fecha_evento  AS fechaSICAAC ");
		nativeQuery.append("   , ev_s.observaciones AS codigoSICAAC ");
		nativeQuery.append("   , (SELECT stuff((SELECT ', ' + CONCAT (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) ");
		nativeQuery.append("     FROM ROL_PERSONA_CASO rpc WITH (nolock) INNER JOIN PERSONA pe WITH (nolock) ON rpc.id_persona=pe.id_persona ");
		nativeQuery.append("     WHERE rpc.id_caso =ca.id_caso AND rpc.estado_registro=?1 AND rpc.id_rol = 103 FOR XML path('')), 1, 1, '')) AS apoderado ");
		nativeQuery.append("   , (SELECT sum(valor_total_acuerdo) AS valor_total_acuerdo FROM OBLIGACION o WITH (nolock) ");
		nativeQuery.append("       WHERE o.id_resultado_audiencia = ra.id_resultado_audiencia) AS valorTotalAcuerdo ");
		nativeQuery.append("   , a.nombre   AS area ");
		nativeQuery.append("   , asu.nombre AS asunto ");
		nativeQuery.append("   , cl.nombre  AS clasificacion ");
		nativeQuery.append(" FROM ");
		nativeQuery.append(" CASO ca WITH (nolock) ");
		nativeQuery.append(" INNER JOIN SERVICIO ser ");
		nativeQuery.append("    ON ser.id_servicio = ca.id_servicio ");
		nativeQuery.append(" INNER JOIN SEDE se WITH (nolock) ");
		nativeQuery.append("    ON se.id_sede             = ca.id_sede ");
		nativeQuery.append("    AND se.estado_registro = ?1 ");
		nativeQuery.append(" INNER JOIN MATERIA m ");
		nativeQuery.append("    ON ca.id_materia = m.id_materia ");
		nativeQuery.append(" LEFT JOIN AREA_ASUNTO_CLASIFICACION aac ");
		nativeQuery.append("    ON aac.id_area_asunto_clasificacion = ca.id_area_asunto_clasificacion ");
		nativeQuery.append(" LEFT JOIN asunto asu ");
		nativeQuery.append("    ON aac.id_asunto = asu.id_asunto ");
		nativeQuery.append(" LEFT JOIN CLASIFICACION cl ");
		nativeQuery.append("    ON aac.id_clasificacion = cl.id_clasificacion ");
		nativeQuery.append(" LEFT JOIN area a ");
		nativeQuery.append("    ON asu.id_area = a.id_area ");
		nativeQuery.append(" LEFT JOIN evento ev_s WITH (nolock) ");
		nativeQuery.append("    ON ev_s.id_caso              = ca.id_caso ");
		nativeQuery.append("    AND ev_s.tipo_evento      = ?2 ");
		nativeQuery.append("    AND ev_s.estado_registro  = ?1 ");
		nativeQuery.append("    AND ev_s.observaciones like 'Caso reportado al SICAAC%' ");
		nativeQuery.append(" LEFT JOIN CONVENIO cnv ");
		nativeQuery.append("    ON cnv.id_convenio = ca.id_convenio ");
		nativeQuery.append(" LEFT JOIN audiencia au WITH (nolock) ");
		nativeQuery.append("    ON au.id_caso             = ca.id_caso ");
		nativeQuery.append("    AND au.estado_registro = ?1 ");
		nativeQuery.append("    AND au.consecutivo     = 1 ");
		nativeQuery.append(" LEFT JOIN AUDIENCIA ult_au WITH (nolock) ");
		nativeQuery.append("    ON ca.id_caso                 = ult_au.id_caso ");
		nativeQuery.append("    AND ult_au.estado_registro = ?1 ");
		nativeQuery.append("    AND ult_au.estado          = ?3 ");
		nativeQuery.append("    AND ult_au.resultado in (?19, ?20, ?22, ?23) ");
		nativeQuery.append("    AND ult_au.id_audiencia = ");
		nativeQuery.append("        (SELECT max(hora_inicio) FROM audiencia aa WHERE aa.id_caso = ca.id_caso ) ");
		nativeQuery.append(" LEFT JOIN RESULTADO_AUDIENCIA ra WITH (nolock) ");
		nativeQuery.append("    ON ra.id_audiencia = ult_au.id_audiencia ");
		nativeQuery.append("       AND ra.tipo_resultado_audiencia in (?19, ?20) ");
		nativeQuery.append(" LEFT JOIN ROL_PERSONA_CASO rpc_con WITH (nolock) ");
		nativeQuery.append("    ON ca.id_caso = rpc_con.id_caso ");
		nativeQuery.append("       AND rpc_con.id_rol IN ");
		nativeQuery.append("        (SELECT id_rol FROM tipo_de_servicio_rol ");
		nativeQuery.append("          WHERE tipo_servicio = ?7) ");
		nativeQuery.append("        AND rpc_con.tipo_nombramiento = ?8 ");
		nativeQuery.append("        AND rpc_con.estado            = ?9 ");
		nativeQuery.append("        AND rpc_con.estado_registro   = ?1 ");
		nativeQuery.append(" LEFT JOIN PERSONA pcon WITH (nolock) ");
		nativeQuery.append("    ON pcon.id_persona          =rpc_con.id_persona ");
		nativeQuery.append("       AND pcon.estado_registro = ?1 ");
		nativeQuery.append(" LEFT JOIN  convenio co WITH (nolock) ");
		nativeQuery.append("    ON co.id_convenio         = ca.id_convenio ");
		nativeQuery.append("       AND co.estado_registro = ?1 ");
		nativeQuery.append(" LEFT JOIN evento ev with (nolock)  ");
		nativeQuery.append("    ON ca.id_caso = ev.id_caso  ");
		nativeQuery.append("    AND ev.tipo_evento = ?10 ");
		nativeQuery.append("    AND ev.estado_registro = ?1 ");
		nativeQuery.append("    AND ev.fecha_evento = (select max(fecha_evento) from evento evr where evr.id_caso = ca.id_caso and evr.tipo_evento = ?10) ");
		nativeQuery.append(" , CENTRO ce ");
		nativeQuery.append(" WHERE ser.tipo = ?7 ");
		nativeQuery.append("    AND ser.id_servicio in (?16, ?17, ?15) ");
		nativeQuery.append("    AND ce.id_centro = case when ca.id_servicio = ?15 then co.id_centro else se.id_centro end");
		nativeQuery.append("    AND ca.estado_registro = ?1 ");
		nativeQuery.append("    AND ser.estado_registro = ?1 ");
		if( filtros.getIdCentro() != null )
			nativeQuery.append(" AND ce.id_centro = ?11 ");
		if( filtros.getFechaInicio() != null )
			nativeQuery.append(" AND Cast(ca.fecha_radicacion as DATE) >= cast( ?12 as DATE) ");
		if( filtros.getFechaFin() != null )
			nativeQuery.append(" AND Cast(ca.fecha_radicacion as DATE) <= cast( ?13 as DATE) ");
		if( filtros.getIdServicio() != null )
			nativeQuery.append(" AND ser.id_servicio = ?14 ");
		if( filtros.getIdCaso() != null )
			nativeQuery.append(" AND ca.id_caso = ?28 ");
		if( filtros.getNombreParte() != null )
			nativeQuery.append(" AND ca.nombre like ?29 ");
		nativeQuery.append(" ORDER BY fechaRadicacionCaso ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), ReporteGeneralCasosConciliacionDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_EVENTO_CASO_REGISTRADO);
		query.setParameter(3, UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
		query.setParameter(4, UtilDominios.DOMINIO_ESTADO_CASO);
		query.setParameter(5, UtilDominios.DOMINIO_RESULTADO_CASO_CONCILIACION);
		query.setParameter(6, UtilDominios.TIPO_CUANTIA_CONCILIACION_DETERMINADO);
		query.setParameter(7, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(8, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(9, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(10, UtilDominios.TIPO_EVENTO_REPARTO);
		query.setParameter(11, filtros.getIdCentro());
		query.setParameter(12, filtros.getFechaInicio());
		query.setParameter(13, filtros.getFechaFin());
		query.setParameter(14, filtros.getIdServicio());
		query.setParameter(15, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA);
		query.setParameter(16, UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO);
		query.setParameter(17, UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO);
		query.setParameter(18, UtilDominios.TIPO_DOCUMENTO_DIG_ACTA_CONCILIACION);
		query.setParameter(19, UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_TOTAL);
		query.setParameter(20, UtilDominios.RESULTADO_AUDIENCIA_ACUERDO_PARCIAL);
		query.setParameter(21, UtilDominios.TIPO_DOCUMENTO_DIG_CONSTANCIA_RESULTADO_AUDIENCIA);
		query.setParameter(22, UtilDominios.RESULTADO_AUDIENCIA_IMPOSIBILIDAD_DE_ACUERDO);
		query.setParameter(23, UtilDominios.RESULTADO_AUDIENCIA_INASISTENCIA);
		query.setParameter(25, UtilDominios.ESTADO_CARTA_ENVIADA);
		query.setParameter(26, UtilDominios.ESTADO_CARTA_IMPRESA);
		query.setParameter(27, UtilDominios.ROL_PERSONA_APODERADO_CONVOCANTE);
		query.setParameter(28, filtros.getIdCaso());
		query.setParameter(29,"%"+ filtros.getNombreParte()+"%");
		
		return query.getResultList();
	}
	
	public List<ReporteActasConstanciasDTO> consultarReporteActasConstancias(String tipoLibro, Date anio, List<Long> idCentros, List<String> resultados){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select * from ( ");
		nativeQuery.append(" SELECT distinct ROW_NUMBER() over (order by e.fecha_evento asc) as consecutivo,  ");
		nativeQuery.append(" e.fecha_evento as fechaRegistro,  ");
		nativeQuery.append(" (select stuff((   ");
		nativeQuery.append(" SELECT ', '+pe.numero_documento   ");		
		nativeQuery.append(" from persona pe inner join ROL_PERSONA_CASO rolpc on rolpc.id_persona=pe.id_persona ");
		nativeQuery.append(" WHERE rolpc.tipo_nombramiento=?1 ");
		nativeQuery.append(" AND rolpc.estado in (?2,?3) ");
		nativeQuery.append(" AND rolpc.id_rol in (select tps.ID_ROL from TIPO_DE_SERVICIO_ROL tps where tps.tipo_servicio=?4 ");
		nativeQuery.append(" AND tps.estado_registro=?5) ");
		nativeQuery.append(" and rolpc.id_caso=c.id_caso and pe.estado_registro=?5 and rolpc.estado_registro=?5  ");
		nativeQuery.append(" for xml path('')), 1, 1, '')) as identificacionConciliador, ");
		nativeQuery.append(" c.fecha_radicacion as fechaRadicacion, ");
		nativeQuery.append(" c.nombre as nombreCaso, ");
		nativeQuery.append(" d.nombre as resultadoCaso, ");
		nativeQuery.append(" m.nombre as materia, ");
		nativeQuery.append(" CE.NOMBRE AS nombreCentro ");
		nativeQuery.append(" FROM CASO C  ");
		nativeQuery.append(" CROSS APPLY (select top 1 * from evento e where e.id_caso=c.id_caso and  e.tipo_evento=?9  order by e.fecha_evento) as e ");
		nativeQuery.append(" inner join sede s on s.id_sede=c.id_sede ");
		nativeQuery.append(" INNER JOIN CENTRO CE ON CE.ID_CENTRO=S.ID_CENTRO ");
		
		if(UtilDominios.TIPO_LIBRO_CONSTANCIA.equals(tipoLibro) ||
				UtilDominios.TIPO_LIBRO_ACTA.equals(tipoLibro)){
			nativeQuery.append(" cross apply (select top 1 * from DOCUMENTO DOC where DOC.ID_CASO=C.id_caso and DOC.tipo_documento=?6 ");
			nativeQuery.append(" AND DOC.estado_registro=?5 order by fecha_ultima_modificacion) doc");
		}
		
		nativeQuery.append(" INNER JOIN DOMINIO D ON D.codigo=c.resultado and d.dominio=?10 ");
		nativeQuery.append(" inner join materia m on m.id_materia = c.id_materia ");
		nativeQuery.append(" WHERE s.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(idCentros));
		nativeQuery.append(" AND C.ESTADO_REGISTRO=?5 ");
		nativeQuery.append(" AND C.ESTADO_CASO=?7 ");
		nativeQuery.append(" AND  year(C.fecha_estado) = year(?8)  ");
		nativeQuery.append(" AND S.estado_registro=?5 ");
		
		if(!UtilDominios.TIPO_LIBRO_CONSTANCIA.equals(tipoLibro) &&
				!UtilDominios.TIPO_LIBRO_ACTA.equals(tipoLibro)){
			nativeQuery.append(" and c.resultado NOT ").append(UtilConsultasSQL.clausulaInSQLStrings(resultados));	
		}
		nativeQuery.append(" ) r order by r.nombreCentro");
		  
		Query query = em.createNativeQuery(nativeQuery.toString(), ReporteActasConstanciasDTO.class);

		query.setParameter(1, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(3, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(4, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(UtilDominios.TIPO_LIBRO_CONSTANCIA.equals(tipoLibro)){
			query.setParameter(6, UtilDominios.TIPO_DOCUMENTO_DIG_CONSTANCIA_RESULTADO_AUDIENCIA);	
		}
		if(UtilDominios.TIPO_LIBRO_ACTA.equals(tipoLibro)){
			query.setParameter(6, UtilDominios.TIPO_DOCUMENTO_DIG_ACTA_CONCILIACION);
		}
		query.setParameter(7, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(8, anio);
		query.setParameter(9, UtilDominios.TIPO_EVENTO_CASO_REGISTRADO);
		query.setParameter(10, UtilDominios.DOMINIO_RESULTADO_CASO_CONCILIACION);
		
		return query.getResultList();
	}
	
	public List<ReporteEvaluacionConciliadoresDTO> consultarReporteEvaluacionConciliadores(FiltroReportesDTO filtros) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT p.numero_documento AS numeroDocumento,  ");
		nativeQuery.append(
				" CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) AS nombreConciliador, ");
		nativeQuery.append(" l.nombre AS lista, ");
		nativeQuery.append(" SUM(crca.valor * pgca.valor_numerico) / 100 AS totalCriterioCalidad, ");
		nativeQuery.append(" SUM(crec.valor * pgec.valor_numerico) / 100 AS totalCriterioEducacionContinua, ");
		nativeQuery.append(" SUM(crpar.valor * pgpar.valor_numerico) / 100 AS totalCriterioParticipacion, ");
		nativeQuery.append(" SUM(crpro.valor * pgpro.valor_numerico) / 100 AS totalCriterioProcedimientos, ");
		nativeQuery.append(" ev.total_evaluacion AS notaFinal, ");
		nativeQuery.append(" year(ev.periodo_desde) AS anio ");
		nativeQuery.append(" FROM PERSONA p ");

		nativeQuery.append(" CROSS APPLY (SELECT TOP 1 r.* ");
		nativeQuery.append(" FROM ROL_PERSONA r ");
		nativeQuery.append(" INNER JOIN TIPO_DE_SERVICIO_ROL as tdsr  ");
		nativeQuery.append(" ON tdsr.tipo_servicio = ?2 ");
		nativeQuery.append(" AND r.id_rol = tdsr.id_rol  ");
		nativeQuery.append(" AND tdsr.estado_registro = ?1 ");
		nativeQuery.append(" WHERE r.id_persona = p.id_persona ");
		nativeQuery.append(" AND (r.fecha_fin_vigencia IS NULL OR r.fecha_fin_vigencia >= CAST(GETDATE() as Date)) ");
		nativeQuery.append(" AND r.fecha_inicio_vigencia <= CAST(GETDATE() as Date) ");
		nativeQuery.append(" AND r.estado_registro = ?1) rp ");
		nativeQuery
				.append(" OUTER APPLY ( SELECT ev.* from EVALUACION_CONCILIADOR ev where id_persona = rp.id_persona ");
		nativeQuery.append(" and estado_registro = 'ACT' and ev.total_evaluacion is not null) AS ev ");

		nativeQuery.append(" INNER JOIN LISTA l  ");
		nativeQuery.append(" ON l.id_lista = rp.id_lista  ");
		nativeQuery.append(" AND l.estado_registro = ?1 ");

		/* criterio calidad */
		nativeQuery.append(" INNER JOIN DETALLE_EVALUACION_CONCILIADOR crca  ");
		nativeQuery.append(" ON ev.id_evaluacion_conciliador = crca.id_evaluacion_conciliador ");
		nativeQuery.append(" AND crca.codigo_criterio = ?3  ");
		nativeQuery.append(" AND crca.estado_registro = ?1  ");

		nativeQuery.append(" INNER JOIN PARAMETROS_GENERALES pgca  ");
		nativeQuery.append(" ON pgca.codigo = crca.codigo_total  ");
		nativeQuery.append(" AND pgca.tipo = ?4  ");
		nativeQuery.append(" AND pgca.estado_registro = ?1 ");

		/* criterio educacion continua */
		nativeQuery.append(" INNER JOIN DETALLE_EVALUACION_CONCILIADOR crec  ");
		nativeQuery.append(" ON ev.id_evaluacion_conciliador = crec.id_evaluacion_conciliador ");
		nativeQuery.append(" AND crec.codigo_criterio = ?5  ");
		nativeQuery.append(" AND crca.estado_registro = ?1  ");

		nativeQuery.append(" INNER JOIN PARAMETROS_GENERALES pgec  ");
		nativeQuery.append(" ON  pgec.codigo = crec.codigo_total  ");
		nativeQuery.append(" AND pgec.tipo = ?4  ");
		nativeQuery.append(" AND pgec.estado_registro = ?1 ");

		/* criterio participacion */

		nativeQuery.append(" INNER JOIN DETALLE_EVALUACION_CONCILIADOR crpar  ");
		nativeQuery.append(" ON ev.id_evaluacion_conciliador = crpar.id_evaluacion_conciliador ");
		nativeQuery.append(" AND crpar.codigo_criterio = ?6  ");
		nativeQuery.append(" AND crpar.estado_registro = ?1  ");

		nativeQuery.append(" INNER JOIN PARAMETROS_GENERALES pgpar  ");
		nativeQuery.append(" ON  pgpar.codigo = crpar.codigo_total  ");
		nativeQuery.append(" AND pgpar.tipo = ?4  ");
		nativeQuery.append(" AND pgec.estado_registro = ?1 ");

		/* criterio procedimientos */

		nativeQuery.append(" INNER JOIN DETALLE_EVALUACION_CONCILIADOR crpro  ");
		nativeQuery.append(" ON ev.id_evaluacion_conciliador = crpro.id_evaluacion_conciliador ");
		nativeQuery.append(" AND crpro.codigo_criterio = ?7  ");
		nativeQuery.append(" AND crpro.estado_registro = ?1  ");

		nativeQuery.append(" INNER JOIN PARAMETROS_GENERALES pgpro  ");
		nativeQuery.append(" ON  pgpro.codigo = crpro.codigo_total  ");
		nativeQuery.append(" AND pgpro.tipo = ?4  ");
		nativeQuery.append(" AND pgpro.estado_registro = ?1 ");

		nativeQuery.append(" WHERE p.estado_registro = ?1 ");

		if (filtros.getIdConciliador() != null)
			nativeQuery.append(" AND p.id_persona = ?8 ");
		if (filtros.getAnio() != null)
			nativeQuery.append(" AND year(ev.periodo_desde) = ?9 ");
		if (filtros.getIdLista() != null)
			nativeQuery.append(" AND l.id_lista = ?10 ");

		if (filtros.getIdConciliador() == null && filtros.getCentros() != null) {
			nativeQuery.append(" AND rp.id_centro ");
			nativeQuery.append(UtilConsultasSQL.clausulaInSQLSNumeros(filtros.getCentros()));
		}

		nativeQuery.append(
				" GROUP BY p.numero_documento, p.primer_nombre_o_razon_social,  p.segundo_nombre, p.primer_apellido, p.segundo_apellido, l.nombre,  ev.total_evaluacion, ev.periodo_desde ");
		nativeQuery.append(" ORDER BY ev.periodo_desde ");

		Query query = em.createNativeQuery(nativeQuery.toString(), ReporteEvaluacionConciliadoresDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(3, UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_CALIDAD);
		query.setParameter(4, UtilParamGenerales.TIPO_PESO_CRITERIOS_EVALUACION);
		query.setParameter(5, UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_EDUCACION_CON);
		query.setParameter(6, UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PARTICIPACION);
		query.setParameter(7, UtilDominios.EVALUACION_CONCILIADOR_CRITERIO_PROCEDIMIENTOS);

		if (filtros.getIdConciliador() != null)
			query.setParameter(8, filtros.getIdConciliador());
		if (filtros.getAnio() != null)
			query.setParameter(9, filtros.getAnio());
		if (filtros.getIdLista() != null)
			query.setParameter(10, filtros.getIdLista());

		return query.getResultList();
	}
	
	
	public List<ReporteGeneralCasosInsolvenciaDTO> consultarReporteGeneralInsolvencia( FiltroReportesDTO filtros ){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT     ca.id_caso                                                                                                           AS idCaso                     , ");
		nativeQuery.append("           concat(p.primer_nombre_o_razon_social,' ',p.segundo_nombre,' ',p.primer_apellido,' ',p.segundo_apellido ) AS nombreSolicitante          , ");
		nativeQuery.append("           p.tipo_documento                                                                                                     AS tipoDocumentoSolicitante   , ");
		nativeQuery.append("           p.numero_documento                                                                                                   AS numeroDocumentoSolicitante , ");
		nativeQuery.append("           (SELECT top(1)                                                                                                          ce.direccion ");
		nativeQuery.append("           FROM    CORREO_ELECTRONICO ce ");
		nativeQuery.append("           WHERE   ce.id_persona = p.id_persona) ");
		nativeQuery.append("           AS             emailSolicitante, ");
		nativeQuery.append("           (SELECT top(1) t.numero ");
		nativeQuery.append("           FROM    TELEFONO t ");
		nativeQuery.append("           WHERE   t.id_persona = p.id_persona) ");
		nativeQuery.append("           AS telefonoSolicitante, ");
		nativeQuery.append("           (SELECT fecha_estado ");
		nativeQuery.append("           FROM    caso cc ");
		nativeQuery.append("           WHERE   cc.id_caso  = ca.id_caso ");
		nativeQuery.append("           AND     estado_caso = ?2 ) AS fechaCierreCaso      , ");
		nativeQuery.append("           u.direccion AS direccionSolicitante , ");
		nativeQuery.append("           au.hora_inicio AS fechaPrimeraAudiencia, ");
		nativeQuery.append("           ca.fecha_radicacion AS fechaRadicacionCaso  , ");
		nativeQuery.append("           ca.observaciones                                      , ");
		nativeQuery.append("           d.nombre    AS ultimoEstadoCaso                       , ");
		nativeQuery.append("           ca.resultado AS resultadoCaso                         , ");
		nativeQuery.append("           (SELECT    p2.fecha ");
		nativeQuery.append("           FROM       ROL_PERSONA_CASO rpc WITH (NOLOCK) ");
		nativeQuery.append("                      LEFT JOIN PRONUNCIAMIENTO p2 WITH (NOLOCK) ");
		nativeQuery.append("                      ON         p2.id_pronunciamiento = rpc.id_pronunciamiento ");
		nativeQuery.append("                      INNER JOIN ROL r ");
		nativeQuery.append("                      ON         rpc.id_rol = r.id_rol ");
		nativeQuery.append("           WHERE      rpc.id_caso           = ca.id_caso ");
		nativeQuery.append("           AND        r.nombre              = ?3 ");
		nativeQuery.append("           AND        rpc.estado_registro   = ?1 ");
		nativeQuery.append("           ) ");
		nativeQuery.append("           AS fechaAceptacionCaso ");
		nativeQuery.append("FROM       CASO ca ");
		nativeQuery.append("           INNER JOIN SERVICIO ser WITH (NOLOCK) ");
		nativeQuery.append("           ON         ser.id_servicio = ca.id_servicio ");
		nativeQuery.append("           INNER JOIN ROL_PERSONA_CASO rpc WITH (NOLOCK) ");
		nativeQuery.append("           ON         rpc.id_caso = ca.id_caso ");
		nativeQuery.append("           INNER JOIN ROL r WITH (NOLOCK) ");
		nativeQuery.append("           ON         rpc.id_rol = r.id_rol ");
		nativeQuery.append("           INNER JOIN PERSONA p WITH (NOLOCK) ");
		nativeQuery.append("           ON         rpc.id_persona = p.id_persona ");
		nativeQuery.append("           LEFT JOIN UBICACION u WITH (NOLOCK) ");
		nativeQuery.append("           ON         u.id_persona      = p.id_persona ");
		nativeQuery.append("           AND        u.estado_registro = ?1 ");
		nativeQuery.append("           LEFT JOIN DOMINIO d WITH (NOLOCK) ");
		nativeQuery.append("           ON         d.codigo  = ca.estado_caso ");
		nativeQuery.append("           AND        d.dominio = ?4  ");
		nativeQuery.append("           LEFT JOIN audiencia au WITH (nolock)  ");
		nativeQuery.append("           ON         au.id_caso = ca.id_caso ");
		nativeQuery.append("           AND        au.estado_registro = ?1  ");
		nativeQuery.append("           AND        au.consecutivo = 1  ");
		nativeQuery.append(" WHERE ser.id_servicio = ?6 ");
		nativeQuery.append("    AND ca.estado_registro = ?1 ");
		nativeQuery.append("    AND ser.estado_registro = ?1 "); 
		nativeQuery.append("    AND r.nombre    = ?5 ");
		if( filtros.getIdCentro() != null )
			nativeQuery.append(" AND ce.id_centro = ?7 ");
		if( filtros.getFechaInicio() != null )
			nativeQuery.append(" AND Cast(ca.fecha_radicacion as DATE) >= cast( ?8 as DATE) ");
		if( filtros.getFechaFin() != null )
			nativeQuery.append(" AND Cast(ca.fecha_radicacion as DATE) <= cast( ?9 as DATE) ");
		if( filtros.getIdCaso() != null )
			nativeQuery.append(" AND ca.id_caso = ?10 ");
		if( filtros.getNombreParte() != null )
			nativeQuery.append(" AND ca.nombre like ?11 ");
		nativeQuery.append(" ORDER BY fechaRadicacionCaso DESC ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), ReporteGeneralCasosInsolvenciaDTO.class);
		
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(3, UtilDominios.ROL_PERSONA_CONCILIADOR_EN_INSOLVENCIA);		
		query.setParameter(4, UtilDominios.DOMINIO_ESTADO_CASO);
		query.setParameter(5, UtilDominios.ROL_PERSONA_DEUDOR);
		query.setParameter(6, UtilConstantes.ID_SERVICIO_INSOLVENCIA);
		query.setParameter(7, filtros.getIdCentro());
		query.setParameter(8, filtros.getFechaInicio());
		query.setParameter(9, filtros.getFechaFin());
		query.setParameter(10, filtros.getIdCaso());
		query.setParameter(11,"%"+ filtros.getNombreParte()+"%");
		
		return query.getResultList();		
	}
	
}
