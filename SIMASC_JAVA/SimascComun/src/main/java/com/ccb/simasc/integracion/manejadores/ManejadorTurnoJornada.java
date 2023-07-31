package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.TurnoJornadaDTO;
import com.ccb.simasc.transversal.entidades.Convenio;
import com.ccb.simasc.transversal.entidades.TurnoJornada;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad TurnoJornada.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorTurnoJornada extends ManejadorCrud<TurnoJornada,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorTurnoJornada() {
        super(TurnoJornada.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    /**
     * Método para obtener los turnos disponibles de la jornada
     * @param idConvenio
     * @return listado de turnos disponibles
     */
    public List<TurnoJornada> obtenerTurnosDisponibles(Long idConvenio) {
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT tj FROM TurnoJornada tj ");
		jpqlQuery.append(" where tj.limiteAudiencias > ( ");
		jpqlQuery.append(" SELECT COUNT(atj.audienciaTurnoJornadaPK.idTurnoJornada) FROM AudienciaTurnoJornada atj ");
		jpqlQuery.append(" where atj.estadoRegistro = ").append("'ACT'");
		jpqlQuery.append(" and tj.idTurnoJornada = atj.audienciaTurnoJornadaPK.idTurnoJornada ");
		jpqlQuery.append(" ) and tj.idConvenio = :").append(Convenio.ENTIDAD_CONVENIO_PK);
		jpqlQuery.append(" and tj.estadoRegistro = ").append("'ACT'");
		jpqlQuery.append(" ORDER BY tj.horaInicio");
		
		Query query = getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter(Convenio.ENTIDAD_CONVENIO_PK, idConvenio);
		
		return query.getResultList();
    }
    
    /**
     * Método para obtener la lista de turnos consecutivos disponibles para la jornada
     * @param idConvenio
     * @return
     */
    public List<TurnoJornadaDTO> consultarTurnosConsecutivos(Long idConvenio) {
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("select t1.id_turno_jornada as idTurnoJornada, t2.id_turno_jornada as consecutivo ");
    	jpqlQuery.append(", concat(FORMAT(t1.hora_inicio, 'HH:mm'), ' - ', FORMAT(t2.hora_fin, 'HH:mm')) as intervalo ");
    	jpqlQuery.append("from TURNO_JORNADA t1, TURNO_JORNADA t2 ");
    	jpqlQuery.append("where t1.hora_fin = t2.hora_inicio ");
    	jpqlQuery.append("and t1.limite_audiencias > (select count(atj.id_turno_jornada)  ");
    	jpqlQuery.append(" from audiencia_turno_jornada atj where t1.id_turno_jornada = atj.id_turno_jornada) ");
    	jpqlQuery.append("and t2.limite_audiencias > (select count(atj.id_turno_jornada) ");
    	jpqlQuery.append(" from audiencia_turno_jornada atj where t2.id_turno_jornada = atj.id_turno_jornada) ");
    	jpqlQuery.append("and t1.id_convenio = ?1 ");
    	jpqlQuery.append("and t2.id_convenio = ?1");
    	Query query = getEntityManager().createNativeQuery(jpqlQuery.toString(), TurnoJornadaDTO.class);
    	query.setParameter(1, idConvenio);
    	return query.getResultList();
    }
    
    /**
     * Método para validar si las horas que se envian no se cruzan con los turnos existentes
     * @param horaInicio
     * @param horaFin
     * @param idConvenio
     * @return true si no se presenta cruce
     */
    public boolean validarCruceTurnos(Date horaInicio, Date horaFin, Long idConvenio, Long idTurnoJornada) {
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("select * from TURNO_JORNADA ");
    	jpqlQuery.append("where (DATEADD(s, 1, ?1) between hora_inicio and hora_fin ");
    	jpqlQuery.append("and DATEADD(s, -1, ?2) between hora_inicio and hora_fin ");
    	jpqlQuery.append("or DATEADD(s, 1, hora_inicio) BETWEEN ?1 and ?2 ");
    	jpqlQuery.append("or DATEADD(s,-1, hora_fin) BETWEEN ?1 and ?2) ");
    	jpqlQuery.append("and id_convenio = ?3 ");
    	jpqlQuery.append("and estado_registro = ").append("'ACT'");
    	if(idTurnoJornada != null)
    		jpqlQuery.append("and id_turno_jornada <> ?4 ");
    	
    	Query query = getEntityManager().createNativeQuery(jpqlQuery.toString());
    	query.setParameter(1, horaInicio);
    	query.setParameter(2, horaFin);
    	query.setParameter(3, idConvenio);
    	if(idTurnoJornada != null)
    		query.setParameter(4, idTurnoJornada);
    	
    	return query.getResultList().isEmpty();
    }
    
    public List<Object[]> obtenerCupoDisponibleTurno(Long idConvenio) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select id_turno_jornada, turnos_disponibles from ");
		nativeQuery.append(
				"(select id_turno_jornada, hora_inicio, limite_audiencias - (select count(*) from audiencia_turno_jornada where id_turno_jornada = t.id_turno_jornada and estado_registro = 'ACT') as turnos_disponibles ");
		nativeQuery.append("from TURNO_JORNADA t ");
		nativeQuery.append("where id_convenio = ?1 ");
		nativeQuery.append("and estado_registro = ").append("'ACT'");
		nativeQuery.append(" ) a where turnos_disponibles > 0 ");
		nativeQuery.append("order by hora_inicio desc ");
    	
    	Query query = getEntityManager().createNativeQuery(nativeQuery.toString());
    	query.setParameter(1, idConvenio);
    	
    	return query.getResultList();
    }
    // protected region metodos adicionales end
        
}

