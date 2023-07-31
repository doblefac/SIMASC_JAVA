package com.ccb.simasc.integracion.manejadores;

// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.AgendaPersona;
import com.ccb.simasc.transversal.entidades.EventoCcb;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad AgendaPersona.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorAgendaPersona extends ManejadorCrud<AgendaPersona, Long> {

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private transient EntityManager em;

	// protected region atributos end

	public ManejadorAgendaPersona() {
		super(AgendaPersona.class);
	}

	@EJB
	public ManejadorDiaFestivo manejadorDiaFestivo;

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Retorna un listado de objetos AgendaPersona donde cada uno representa un
	 * turno
	 * 
	 * @param idPersona
	 *            identificador de la perosna sobre la cual se consulta la
	 *            agenda
	 * @param fecha
	 *            dia a consultar
	 * @author Javier Estevez
	 * @return
	 */
	public List<AgendaPersona> consultarTurnos(Long idPersona, Date fecha) {

		Date fechaUsar = UtilOperaciones.removerHora(fecha);

		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append(" SELECT * FROM AGENDA_PERSONA ag  ");
		nativeQuery.append(" WHERE ag.estado != ?1  ");
		nativeQuery.append(" AND ag.estado_registro = ?2 ");
		nativeQuery.append(" AND ag.id_persona = ?3  ");
		nativeQuery.append(" AND cast(fecha_inicio As Date) = ?4 ");

		Query query = em.createNativeQuery(nativeQuery.toString(),
				AgendaPersona.class);

		query.setParameter(1, UtilDominios.ESTADO_AGENDA_PERSONA_CANCELADA);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, idPersona);
		query.setParameter(4, fechaUsar);

		return query.getResultList();

	}

	/**
	 * @param idPersona
	 * @param horaInicio
	 * @param horaFin
	 * @return boolean, retorna true si la agenda de la persona esta disponible.
	 */
	public boolean validarDisponibilidadPersona(Long idPersona,
			Date horaInicio, Date horaFin) {
		return validarDisponibilidadPersonaExcluirPeriodo(idPersona,
				horaInicio, horaFin, null, null);
	}

	/**
	 * Retorna true si la persona está disponible. Tiene en cuenta otro periodo,
	 * este periodo se usa cuando se desea reprogramar la agenda.
	 * 
	 * @param idPersona
	 * @param horaInicio
	 * @param horaFin
	 * @param horaInicioExistente
	 * @param horaFinExistente
	 * @return boolean: true: es porque si esta disponible (valido), false: no
	 *         esta disponible.
	 */
	public boolean validarDisponibilidadPersonaExcluirPeriodo(Long idPersona,
			Date horaInicio, Date horaFin, Date horaInicioExistente,
			Date horaFinExistente) {

		if (manejadorDiaFestivo.consultarFecha(horaInicio) != null) {
			return false;
		}

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT top 1 1 FROM AGENDA_PERSONA ag  ");
		nativeQuery.append(" WHERE ag.estado <> ?1  ");
		nativeQuery.append(" AND ag.estado_registro = ?2 ");
		nativeQuery.append(" AND ag.id_persona = ?3  ");
		nativeQuery
				.append(" AND (cast(ag.fecha_inicio as date) = cast(?4 as date)   ");
		nativeQuery
				.append(" AND (DATEADD(s, 1, ?4) between ag.fecha_inicio and ag.fecha_fin ");
		nativeQuery
				.append(" AND DATEADD(s, -1, ?5) between ag.fecha_inicio and ag.fecha_fin ");
		nativeQuery
				.append(" OR DATEADD(s, 1, ag.fecha_inicio) BETWEEN ?4 and ?5) ");
		nativeQuery
				.append(" OR ( DATEADD(s, 1, ?4) between ag.fecha_inicio and ag.fecha_fin ");
		nativeQuery
				.append(" OR DATEADD(s, -1, ?5) between ag.fecha_inicio and ag.fecha_fin)) ");

		if (horaInicioExistente != null)
			nativeQuery.append(" AND ag.fecha_inicio <> ?6 ");
		if (horaFinExistente != null)
			nativeQuery.append(" AND ag.fecha_fin <> ?7 ");

		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, UtilDominios.ESTADO_AGENDA_PERSONA_CANCELADA);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, idPersona);
		query.setParameter(4, horaInicio);
		query.setParameter(5, horaFin);
		query.setParameter(6, horaInicioExistente);
		query.setParameter(7, horaFinExistente);

		return query.getResultList().isEmpty();

	}

	/**
	 * Metodo que consulta las salas por filtros y los organiza por hora y sala
	 * 
	 * @param filtrosSalasDTO
	 * @return List<SalaDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> buscaPersonasAgenda(Long idCentro) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT DISTINCT PER.* ");
		nativeQuery.append(" FROM            DOMINIO DomPER ");
		nativeQuery.append("                 INNER JOIN DOMINIO DomROL ");
		nativeQuery.append("                 ON              DomPER.nombre  = DomROL.nombre ");
		nativeQuery.append("                 AND             DomPER.dominio = ?1 ");
		nativeQuery.append("                 LEFT JOIN CLASIFICADOR_DOMINIO AS CLA ");
		nativeQuery.append("                 ON              CLA.codigo_clasificador = DomROL.codigo ");
		nativeQuery.append("                 INNER JOIN ROL AS ROL ");
		nativeQuery.append("                 ON              ROL.nombre = CLA.codigo_clasificado ");
		nativeQuery.append("                 INNER JOIN TIPO_DE_SERVICIO_ROL AS TSR ");
		nativeQuery.append("                 ON              TSR.id_rol        = ROL.id_rol ");
		nativeQuery.append("                 AND             TSR.tipo_servicio = ?4 ");
		nativeQuery.append("                 INNER JOIN ROL_PERSONA AS RP ");
		nativeQuery.append("                 ON              RP.id_rol                = TSR.id_rol ");
		nativeQuery.append("                 AND 			 RP.fecha_fin_vigencia IS NULL ");
		nativeQuery.append("                 AND 			 RP.estado_registro    = ?5 ");		
		nativeQuery.append("                 INNER JOIN ESTADO_PERSONA_ROL AS ETPR ");
		nativeQuery.append("                 ON              RP.id_persona  = ETPR.id_persona ");
		nativeQuery.append("                 AND             RP.id_rol      = ETPR.id_rol ");
		nativeQuery.append("                 AND             ETPR.id_motivo = ?3 ");
		nativeQuery.append("                 INNER JOIN PERSONA AS PER ");
		nativeQuery.append("                 ON              PER.id_persona = ETPR.id_persona ");
		nativeQuery.append(" WHERE           RP.id_centro                   = ?2 ");
		nativeQuery.append(" ORDER BY        PER.primer_nombre_o_razon_social");
		
		Query q = em.createNativeQuery(nativeQuery.toString(), Persona.class);
		q.setParameter(1, UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA);
		q.setParameter(2, idCentro);
		q.setParameter(3, UtilDominios.ESTADO_CONCILIADOR_ACTIVO);
		q.setParameter(4, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		q.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return q.getResultList();
	}

	/**
	 * Método para obtener el listado de las agendas de las personas que ya no
	 * se encuentran asociadas a un evento de la ccb
	 * 
	 * @param idEventoCcb
	 *            evento por el cual se busca
	 * @param personas
	 *            lista opcional de las personas que se quieren incluir en la
	 *            consulta
	 * @return lista de agendas de la personas asociadas al evento
	 */
	public List<AgendaPersona> obtenerAgendaEvento(Long idEventoCcb,
			List<Long> personas) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery
				.append(" SELECT ap FROM PersonaEventoCcb pe, AgendaPersona ap ");
		jpqlQuery.append(" INNER JOIN pe.eventoCcb e ");
		jpqlQuery.append(" WHERE e.estadoRegistro = ").append("'ACT'");
		jpqlQuery
				.append(" AND ap.idPersona = pe.personaEventoCcbPK.idPersona AND pe.estadoRegistro = 'INA' ");
		jpqlQuery.append(" AND ap.fechaInicio = e.fechaInicio");
		jpqlQuery.append(" AND ap.fechaFin = e.fechaFin");
		jpqlQuery.append(" AND ap.tipoActividadAgenda = e.tipoEventoCcb");
		jpqlQuery.append(" AND ap.estado <> :").append(
				AgendaPersona.ENTIDAD_AGENDA_PERSONA_ESTADO);
		jpqlQuery.append(" AND e.estado <> :").append(
				AgendaPersona.ENTIDAD_AGENDA_PERSONA_ESTADO);
		jpqlQuery.append(" AND ap.estadoRegistro = ").append("'ACT'");
		jpqlQuery.append(" AND e.idEventoCcb = :").append(
				EventoCcb.ENTIDAD_EVENTO_CCB_PK);
		if (personas != null && !personas.isEmpty())
			jpqlQuery.append(" AND ap.idPersona IN :").append(
					AgendaPersona.ENTIDAD_AGENDA_PERSONA_ID_PERSONA);

		Query q = em.createQuery(jpqlQuery.toString());
		q.setParameter(AgendaPersona.ENTIDAD_AGENDA_PERSONA_ESTADO,
				UtilDominios.ESTADO_AGENDA_PERSONA_CANCELADA);
		q.setParameter(EventoCcb.ENTIDAD_EVENTO_CCB_PK, idEventoCcb);
		if (personas != null && !personas.isEmpty())
			q.setParameter(AgendaPersona.ENTIDAD_AGENDA_PERSONA_ID_PERSONA,
					personas);

		return q.getResultList();
	}

	/**
	 * Metodo que consulta la agenda de un conciliador
	 * 
	 * @param filtrosSalasDTO
	 * @return List<SalaDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<AgendaPersona> consultaAgendaPersona(Long idPersona) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select id_agenda_persona, ");
		nativeQuery.append(" estado, ");
		nativeQuery.append(" tipo_actividad_agenda, ");
		nativeQuery.append(" id_persona, ");
		nativeQuery.append(" fecha_inicio, ");
		nativeQuery.append(" fecha_fin, ");
		nativeQuery.append(" id_audiencia, ");
		nativeQuery.append(" case tipo_actividad_agenda when 'AUDICON' then ");
		nativeQuery.append(" (select ag.observaciones + ' ' + s.nombre + ' ' + s.direccion from sede s inner join caso c on c.id_sede = s.id_sede inner join audiencia au on au.id_caso = c.id_caso where au.id_audiencia = ag.id_audiencia) ");
		nativeQuery.append(" else observaciones end as observaciones, ");
		nativeQuery.append(" id_usuario_modificacion, ");
		nativeQuery.append(" fecha_ultima_modificacion,  ");
		nativeQuery.append(" estado_registro, ");
		nativeQuery.append(" cumplio_horario, ");
		nativeQuery.append(" justificacion_valida ");
		nativeQuery.append(" from AGENDA_PERSONA ag ");
		nativeQuery.append(" where id_persona = ?1 and estado = ?2 and estado_registro = ?3 ");
		nativeQuery.append(" and fecha_inicio between dateadd(mm, -6, getdate()) and dateadd(mm, 9, getdate()) ");
		nativeQuery.append(" order by fecha_inicio ");
		
		Query q = em.createNativeQuery(nativeQuery.toString(), AgendaPersona.class);
		q.setParameter(1, idPersona);
		q.setParameter(2, UtilDominios.ESTADO_AGENDA_PERSONA_PROGRAMADA);
		q.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return q.getResultList();
	}
	// protected region metodos adicionales end

}
