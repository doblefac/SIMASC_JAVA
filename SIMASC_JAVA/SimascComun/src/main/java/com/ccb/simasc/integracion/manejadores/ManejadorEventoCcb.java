package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.EventoCcb;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad EventoCcb.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorEventoCcb extends ManejadorCrud<EventoCcb,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorEventoCcb() {
        super(EventoCcb.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    /**
     * Método para consultar los eventos de la CCB que se encuentren entre las dos fechas
     * @param fechaDesde
     * @param fechaHasta
     * @return
     */
    public List<EventoCcb> consultarEventos(Date fechaDesde, Date fechaHasta) {
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append(" SELECT * FROM evento_ccb e ");
    	jpqlQuery.append(" WHERE CAST(e.fecha_inicio as Date) BETWEEN ?1 AND ?2");
    	jpqlQuery.append(" AND e.estado_registro = ?3");    	
    	
    	Query query = getEntityManager().createNativeQuery(jpqlQuery.toString(), EventoCcb.class);
    	query.setParameter(1, fechaDesde);
    	query.setParameter(2, fechaHasta);
    	query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	return query.getResultList();
    }
    
    /**
     * Método que obtiene la cantidad de eventos programados entre dos fechas que sea de los tipos que se reciben como parametro
     * o a los cuales haya asistido la persona si se recibe el parametro de la misma 
     * @param periodoDesde
     * @param periodoHasta
     * @param idPersona
     * @param eventos
     * @return
     */
    public BigDecimal obtenerCursosEvaluacion(Date periodoDesde, Date periodoHasta, Long idPersona, List<String> eventos) {
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append("select count(*) from EVENTO_CCB e ");
    	if (idPersona != null) {
    		nativeQuery.append("inner join PERSONA_EVENTO_CCB p on e.id_evento_ccb = p.id_evento_ccb and p.id_persona = ?1 and p.estado = ?2 and p.estado_registro = ?3 ");
    	}
    	nativeQuery.append("where tipo_evento_ccb ").append(UtilConsultasSQL.clausulaInSQLStrings(eventos));
    	nativeQuery.append(" and e.estado = ?4");
    	nativeQuery.append(" and CAST(e.fecha_fin as Date) between ?5 and ?6");
    	nativeQuery.append(" and e.estado_registro = ?3");
    	Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), BigDecimal.class);
    	query.setParameter(1, idPersona);
    	query.setParameter(2, UtilDominios.ESTADO_ASISTENCIA_EVENTO_ASISTE);
    	query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	query.setParameter(4, UtilDominios.ESTADO_EVENTO_CCB_REALIZADO);
    	query.setParameter(5, periodoDesde);
    	query.setParameter(6, periodoHasta);
    	
    	return (BigDecimal) query.getSingleResult();
    }
    
    // protected region metodos adicionales end
        
}

