package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.SedeDTO;
import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Sede.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorSede extends ManejadorCrud<Sede,Long>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
    
    public ManejadorSede() {
        super(Sede.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones

    /**
	 * Consulta que obtiene todas las Sedes activas. Opcional enviar el idCentro
	 * para obtener las Sedes asociadas al Centro
	 * 
	 * @param idCentro
	 * @return List<Sede>
	 */

	public List<Sede> consultarSedesPorCentro(List<Long> idCentro){    	
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT s FROM Sede s ");
		jpqlQuery.append(" WHERE s.estadoRegistro =: ");
		jpqlQuery.append(Sede.ENTIDAD_SEDE_ESTADO_REGISTRO);
		if(idCentro != null){
			jpqlQuery.append(" AND s.idCentro in : ");
			jpqlQuery.append(Sede.ENTIDAD_SEDE_ID_CENTRO);
		}	
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Sede.ENTIDAD_SEDE_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(idCentro != null)
			query.setParameter(Sede.ENTIDAD_SEDE_ID_CENTRO, idCentro);
		
    	return query.getResultList();
    }
    
    public List<Sede> consultarSedesPorCasoServicio( Long idCaso, Long idPersona ){
    	StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT * FROM Sede sedes ");
		nativeQuery.append(" INNER JOIN Servicio_Sede ss ");
		nativeQuery.append(" ON ss.id_sede = sedes.id_sede ");
		nativeQuery.append(" INNER JOIN Caso c ");
		nativeQuery.append(" ON ss.id_servicio = c.id_servicio ");
		if(idPersona!=null){
			nativeQuery.append(" inner join PERSONA_SEDE ps on ps.id_sede=sedes.id_sede ");
		}
		nativeQuery.append(" WHERE sedes.id_centro IN (  select id_centro from sede s ");
									nativeQuery.append(" where c.id_sede = s.id_sede ");
									nativeQuery.append(" and s.estado_registro = ?2 ) ");
		nativeQuery.append(" AND c.id_caso = ?1 ");
		
		if(idPersona!=null){
			nativeQuery.append(" and ps.id_persona=?3 ");	
		}
		
		nativeQuery.append(" AND sedes.estado_registro = ?2 ");
		nativeQuery.append(" AND ss.estado_registro = ?2 ");
		nativeQuery.append(" AND c.estado_registro = ?2 ");
		
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Sede.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(idPersona!=null){
			query.setParameter(3, idPersona);
		}
		return query.getResultList();
    }
    
    
    public List<Sede> consultarSedesPorServicio( Long idServicio ){
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append(" SELECT se.* FROM SEDE se  ");
    	nativeQuery.append(" INNER JOIN SERVICIO_SEDE ss ON se.id_sede = ss.id_sede ");
    	nativeQuery.append(" WHERE se.estado_registro = ?1 ");
    	nativeQuery.append("  AND  ss.estado_registro = ?1 ");
    	nativeQuery.append("  AND ss.id_servicio = ?2 ");   
    	nativeQuery.append("  ORDER BY se.nombre ");   
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Sede.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idServicio);		
		return query.getResultList();
    	
    }
    
	
	/**
	 * Método que consulta las sedes en las que el conciliador tiene audiencia en la fecha recibida
	 * @param fecha fecha para la cual se consultan las sedes de las audiencias
	 * @param idPersona identificador del conciliador 
	 * @return
	 */
	public List<SedeDTO> consultarSedesAudienciaConciliador(Date fecha, Long idPersona) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select distinct se.id_sede as idSede, se.nombre from AGENDA_PERSONA ap  ");
		nativeQuery.append(" inner join ROL_PERSONA_CASO rpc on rpc.id_persona = ap.id_persona ");
		nativeQuery.append(" and id_rol in (select id_rol from TIPO_DE_SERVICIO_ROL where tipo_servicio = ?6) ");
		nativeQuery.append(" and rpc.estado <> ?7 ");
		nativeQuery.append(" and tipo_nombramiento = ?8 ");
		nativeQuery.append(" and rpc.estado_registro = ?3 ");
		nativeQuery.append(" inner join AGENDAMIENTO a on a.id_audiencia = ap.id_audiencia and a.estado_registro = ?3 ");
		nativeQuery.append(" inner join SALA s on s.id_sala = a.id_sala and s.estado_registro = ?3 ");
		nativeQuery.append(" inner join SEDE se on se.id_sede = s.id_sede and se.estado_registro = ?3 ");
		nativeQuery.append(" where tipo_actividad_agenda = ?4 ");
		nativeQuery.append(" and ap.estado <> ?5 ");
		nativeQuery.append(" and CAST(ap.fecha_inicio as Date) = CAST(?1 as Date) ");
		nativeQuery.append(" and ap.id_audiencia is not null ");
		nativeQuery.append(" and ap.estado_registro = 'ACT' ");
		nativeQuery.append(" and ap.id_persona = ?2 ");
		
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), SedeDTO.class);
		query.setParameter(1, fecha);
		query.setParameter(2, idPersona);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(4, UtilDominios.TIPO_ACTIVIDAD_AGENDA_AUDIENCIA_CONCILIACION);
		query.setParameter(5, UtilDominios.ESTADO_AGENDA_PERSONA_CANCELADA);
		query.setParameter(6, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(7, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
		query.setParameter(8, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		
		return query.getResultList();
	}
    // protected region metodos adicionales end
        
}

