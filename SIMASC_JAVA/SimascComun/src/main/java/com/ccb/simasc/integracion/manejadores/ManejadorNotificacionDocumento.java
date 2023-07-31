package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.obtenerFechaComienzoDelDia;
import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.obtenerFechaFinDelDia;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.NotificacionDocumento;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad NotificacionDocumento.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorNotificacionDocumento extends ManejadorCrud<NotificacionDocumento,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorNotificacionDocumento() {
        super(NotificacionDocumento.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    public List<NotificacionDocumento> obtenerEstadosOFijacionListas(String tipo,String palabraClave,Date fechaInicio,Date fechaFinal){
    	StringBuilder strQuery = new StringBuilder();
    	
    	strQuery.append("SELECT nd ");
    	strQuery.append("  FROM NotificacionDocumento nd ");
    	strQuery.append("  INNER JOIN nd.caso c ");
    	strQuery.append(" WHERE nd.estadoRegistro = :estadoRegistro ");
    	
    	if(tipo != null)
    		strQuery.append(" AND nd.tipoNotificacion = :tipoNotificacion ");
    	if(palabraClave != null && !palabraClave.equals(""))
    		strQuery.append(" AND LOWER(c.nombre) LIKE :palabraClave ");
    	if(fechaInicio != null)
    		strQuery.append(" AND nd.fechaFijacion >= :fechaInicio ");
    	if(fechaFinal != null)
    		strQuery.append(" AND nd.fechaFijacion <= :fechaFinal ");
    	if(fechaFinal == null && fechaInicio == null) 
    		strQuery.append(" AND nd.fechaFijacion BETWEEN :fechaInicio AND :fechaFinal ");
    	
    	strQuery.append(" ORDER BY nd.fechaFijacion");
    	
    	Query query = mp.createQuery(strQuery.toString());
    	
    	query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	
    	if(tipo != null)
    		query.setParameter("tipoNotificacion", tipo);
    	if(palabraClave != null && !palabraClave.equals(""))
    		query.setParameter("palabraClave", "%"+ palabraClave +"%");
    	if(fechaInicio != null)
    		query.setParameter("fechaInicio", fechaInicio);
    	if(fechaFinal != null)
    		query.setParameter("fechaFinal", obtenerFechaFinDelDia(fechaFinal),TemporalType.DATE);
    	if(fechaFinal == null && fechaInicio == null){
    		Date fechaIni = obtenerFechaComienzoDelDia(new Date());
    		query.setParameter("fechaInicio", fechaIni);
    		Date fechaFin = obtenerFechaFinDelDia(new Date());
    		query.setParameter("fechaFinal", fechaFin);    		
    	}
    	
    	
    	return (List<NotificacionDocumento>)query.getResultList();
    }

    public List<NotificacionDocumento> obtenerNofitificaciones(Long idCaso, Long idNotificacionDocumento){
    	StringBuilder strQuery = new StringBuilder();
    	
    	strQuery.append("SELECT nd ");
    	strQuery.append("  FROM NotificacionDocumento nd ");
    	strQuery.append("  INNER JOIN nd.caso c ");
    	strQuery.append(" WHERE nd.estadoRegistro = :estadoRegistro ");
    	if(idCaso != null){
    		strQuery.append(" AND c.idCaso = :idCaso ");
    	}
    	if(idNotificacionDocumento != null){
    		strQuery.append(" AND nd.idNotificacionDocumento = :idNotificacionDocumento ");
    	}
    	Query query = mp.createQuery(strQuery.toString());
    	query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	if(idCaso != null){
    		query.setParameter("idCaso", idCaso);
    	}
    	if(idNotificacionDocumento != null){
    		query.setParameter("idNotificacionDocumento", idNotificacionDocumento);
    	}
    	return (List<NotificacionDocumento>)query.getResultList();
    }
    // protected region metodos adicionales end
        
}

