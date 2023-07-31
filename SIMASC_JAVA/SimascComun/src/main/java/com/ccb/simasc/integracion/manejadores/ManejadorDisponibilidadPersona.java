package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.DisponibilidadPersonaDTO;
import com.ccb.simasc.transversal.entidades.DisponibilidadPersona;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad DisponibilidadPersona.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorDisponibilidadPersona extends ManejadorCrud<DisponibilidadPersona,Long> {

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	
	// protected region atributos end
    
    public ManejadorDisponibilidadPersona() {
        super(DisponibilidadPersona.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    public boolean validarDisponibilidadPersona(Date fecha, Date horaInicio, Date horaFin, Long idSede, Long idPersona) {
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("select 1 from DISPONIBILIDAD_PERSONA d ");
		if (idSede != null) {
			jpqlQuery.append(" join persona_sede p on p.id_persona = d.id_persona");
		}
		jpqlQuery.append(" where codigo = datepart(dw, ?1) ");
		jpqlQuery.append(" and d.id_persona = ?5 ");
		jpqlQuery.append(" and d.estado_registro = ?6 ");
		jpqlQuery.append(" and convert (time, ?2, 108 ) between hora_inicio and hora_fin ");
		jpqlQuery.append(" and convert (time, ?3, 108 ) between hora_inicio and hora_fin ");
		if (idSede != null) {
			jpqlQuery.append(" and p.estado_registro = ?6 ");
			jpqlQuery.append(" and p.id_sede = ?4");
		}
		
		Query query = mp.getEntityManager().createNativeQuery(jpqlQuery.toString());
		query.setParameter(1, fecha);
		query.setParameter(2, horaInicio);
		query.setParameter(3, horaFin);
		if (idSede != null)
			query.setParameter(4, idSede);
		query.setParameter(5, idPersona);
		query.setParameter(6, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return !query.getResultList().isEmpty();
    }
    
    
    public List<DisponibilidadPersonaDTO> consultarDisponibilidadPersona(Long idPersona) {
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("select  d.id_disponibildad_persona as idDisponibildadPersona, ");
		jpqlQuery.append(" d.codigo as codigo, ");
		jpqlQuery.append(" d.hora_inicio as horaInicio, ");
		jpqlQuery.append(" d.hora_fin as horaFin, ");
		jpqlQuery.append(" d.estado_registro as estadoRegistro, ");
		jpqlQuery.append(" d.id_persona as idPersona ");
		jpqlQuery.append(" from DISPONIBILIDAD_PERSONA d ");	
		jpqlQuery.append(" where d.estado_registro = ?2 ");
		if(idPersona!=null){
			jpqlQuery.append(" and d.id_persona = ?1");	
		}
		jpqlQuery.append(" order by d.codigo asc ");
		Query query = mp.getEntityManager().createNativeQuery(jpqlQuery.toString(), DisponibilidadPersonaDTO.class);
		
		if(idPersona!=null){
			query.setParameter(1, idPersona);	
		}
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
    }
    
    // protected region metodos adicionales end
        
}

