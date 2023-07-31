package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

// Escriba en esta seccion sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.EvaluacionConciliador;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad EvaluacionConciliador.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorEvaluacionConciliador extends ManejadorCrud<EvaluacionConciliador, Long> {

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private transient EntityManager em;
	// protected region atributos end

	public ManejadorEvaluacionConciliador() {
		super(EvaluacionConciliador.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * CON-C-004 Importante: no cambiar el ORDER BY de la consulta consulta las
	 * evaluaciones realizadas a un conciliador por idPersona y opcionalmente
	 * por el periodo de la evaluacion
	 * 
	 * @param idPersona
	 *            idPersona del conciliador
	 * @param periodoDesde
	 * @param periodoHasta
	 * @return List<EvaluacionConciliador>
	 */
	public List<EvaluacionConciliador> consultarEvaluacionConciliador(Long idPersona, Date periodoDesde,
			Date periodoHasta) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" select e from EvaluacionConciliador e ");
		jpqlQuery.append(" where e.estadoRegistro =: ")
				.append(EvaluacionConciliador.ENTIDAD_EVALUACION_CONCILIADOR_ESTADO_REGISTRO);
		if (periodoDesde != null && periodoHasta != null) {
			jpqlQuery.append(" and e.periodoDesde = :")
					.append(EvaluacionConciliador.ENTIDAD_EVALUACION_CONCILIADOR_PERIODO_DESDE);
			jpqlQuery.append(" and e.periodoHasta = :")
					.append(EvaluacionConciliador.ENTIDAD_EVALUACION_CONCILIADOR_PERIODO_HASTA);
		}
		jpqlQuery.append(" and e.persona.estadoRegistro =: ").append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
		jpqlQuery.append(" and e.persona.idPersona =: ").append(Persona.ENTIDAD_PERSONA_PK);
		jpqlQuery.append(" ORDER BY e.periodoDesde DESC, e.periodoHasta DESC");

		Query query = em.createQuery(jpqlQuery.toString(), EvaluacionConciliador.class);
		query.setParameter(EvaluacionConciliador.ENTIDAD_EVALUACION_CONCILIADOR_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		if (periodoDesde != null && periodoHasta != null) {
			query.setParameter(EvaluacionConciliador.ENTIDAD_EVALUACION_CONCILIADOR_PERIODO_DESDE, periodoDesde);
			query.setParameter(EvaluacionConciliador.ENTIDAD_EVALUACION_CONCILIADOR_PERIODO_HASTA, periodoHasta);
		}
		return query.getResultList();
	}

	/**
	 * Método que obtiene el listado de conciliadores que se encuentran
	 * habilitados para la evaluciación
	 * 
	 * @param centros
	 * @return
	 */
	public List<PersonaBasicaDTO> consultarConciliadoresAEvaluar(List<Long> centros) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(
				"select p.id_persona as idPersona, p.numero_documento numeroDocumento, CONCAT(p.primer_nombre_o_razon_social");
		nativeQuery.append(", ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) nombreCompleto ");
		nativeQuery.append(", l.nombre as lista, ev.fecha_ultima_modificacion as fechaEvaluacion ");
		nativeQuery.append("FROM PERSONA P ");
		nativeQuery.append("CROSS APPLY (SELECT TOP 1 r.* ");
		nativeQuery.append("FROM ROL_PERSONA r ");
		nativeQuery.append("INNER JOIN TIPO_DE_SERVICIO_ROL as tdsr ON tdsr.tipo_servicio = ?2 ");
		nativeQuery.append("and r.id_rol = tdsr.id_rol and tdsr.estado_registro = ?1 ");
		nativeQuery.append("INNER JOIN ESTADO_PERSONA_TIPO_SERVICIO as epts on p.id_persona = epts.id_persona ");
		nativeQuery.append("and epts.tipo_servicio = ?2 ");
		nativeQuery.append("and epts.estado = ?3 and epts.estado_registro = ?1 ");
		nativeQuery.append("WHERE p.id_persona = r.id_persona and r.id_centro ")
				.append(UtilConsultasSQL.clausulaInSQLSNumeros(centros));
		nativeQuery.append(" AND (r.fecha_fin_vigencia IS NULL OR r.fecha_fin_vigencia >= CAST(GETDATE() as Date)) ");
		nativeQuery.append(" AND r.fecha_inicio_vigencia <= CAST(GETDATE() as Date) ");
		nativeQuery.append(" and r.estado_registro = ?1) rp ");
		nativeQuery.append(
				"OUTER APPLY (select TOP 1 ev.* from evaluacion_conciliador ev where id_persona = rP.id_persona ");
		nativeQuery.append(
				"and estado_registro = ?1 and ev.total_evaluacion is not null order by ev.periodo_desde, ev.periodo_hasta desc) as ev ");
		nativeQuery.append("INNER JOIN LISTA l on l.id_lista = rp.id_lista and l.estado_registro = ?1 ");
		nativeQuery.append("WHERE p.estado_registro = ?1 ");
		nativeQuery.append("ORDER BY p.primer_nombre_o_razon_social");
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), PersonaBasicaDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(3, UtilDominios.ESTADO_CONCILIADOR_ACTIVO);
		return query.getResultList();
	}

	/**
	 * Metodo que permite consultar los anios en los que se han registrado
	 * evaluaciones.
	 * 
	 * @return List<String>: lista de anios que corresponden a los anios que se
	 *         han registrado evaluaciones.
	 */
	public List<String> consultarAniosRegistroEvaluaciones() {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT DISTINCT(YEAR(periodo_desde)) AS anio ");
		nativeQuery.append(" FROM evaluacion_conciliador ");
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString());
		return query.getResultList();
	}

	// protected region metodos adicionales end

}
