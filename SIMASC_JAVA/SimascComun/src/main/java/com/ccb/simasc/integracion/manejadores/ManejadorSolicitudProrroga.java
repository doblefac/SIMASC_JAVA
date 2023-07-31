package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.SolicitudProrroga;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad SolicitudProrroga.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorSolicitudProrroga extends ManejadorCrud<SolicitudProrroga,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorSolicitudProrroga() {
        super(SolicitudProrroga.class);
    }    
    
    @Override
    public void crear(SolicitudProrroga pData) {
    	
    	super.crear(pData);
    }
    
    @Override
    public void actualizar(SolicitudProrroga pData) {
    	
    	super.actualizar(pData);
    }
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    public List<SolicitudProrroga> consultarSolicitudesProrroga(Long idCaso) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT sr FROM SolicitudProrroga sr");
		jpqlQuery.append(" WHERE sr.caso.idCaso=:");
		jpqlQuery.append(SolicitudProrroga.ENTIDAD_SOLICITUD_PRORROGA_ID_CASO);
		jpqlQuery.append(" AND sr.estadoRegistro=:");
		jpqlQuery.append(SolicitudProrroga.ENTIDAD_SOLICITUD_PRORROGA_ESTADO_REGISTRO_SOLICITUDPRORROGA);
		jpqlQuery.append(" ORDER BY sr.fechaSolicitud DESC ");
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(SolicitudProrroga.ENTIDAD_SOLICITUD_PRORROGA_ID_CASO, idCaso);	
		query.setParameter(SolicitudProrroga.ENTIDAD_SOLICITUD_PRORROGA_ESTADO_REGISTRO_SOLICITUDPRORROGA, UtilDominios.ESTADO_REGISTRO_ACTIVO);	
		
		return query.getResultList();
	}

    
    // protected region metodos adicionales end
        
}

