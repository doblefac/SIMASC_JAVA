package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.PersonaSolicitudPK;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.entidades.UbicacionPersonaSolicitud;
import com.ccb.simasc.transversal.entidades.UbicacionPersonaSolicitudPK;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad UbicacionPersonaSolicitud.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorUbicacionPersonaSolicitud extends ManejadorCrud<UbicacionPersonaSolicitud,UbicacionPersonaSolicitudPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorUbicacionPersonaSolicitud() {
        super(UbicacionPersonaSolicitud.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    /**
	 * Consulta la lista de Ubicaciones asociadas a una Persona Solicitud
	 * @param personaSolicitud
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public List<Ubicacion> consultarUbicacionesPorPersonaSolicitud(PersonaSolicitudPK personaSolicitud) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ups.ubicacion FROM UbicacionPersonaSolicitud ups");
		jpqlQuery.append(" WHERE ups.personaSolicitud.personaSolicitudPK.idPersona = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(UbicacionPersonaSolicitud.ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_PERSONA));
		jpqlQuery.append(" AND ups.personaSolicitud.personaSolicitudPK.idSolicitudServicio = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(UbicacionPersonaSolicitud.ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_SOLICITUD_SERVICIO));
		jpqlQuery.append(" AND ups.personaSolicitud.personaSolicitudPK.idRol = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(UbicacionPersonaSolicitud.ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_ROL));
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(UbicacionPersonaSolicitud.ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_PERSONA), personaSolicitud.getIdPersona());
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(UbicacionPersonaSolicitud.ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_SOLICITUD_SERVICIO), personaSolicitud.getIdSolicitudServicio());
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(UbicacionPersonaSolicitud.ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_ROL), personaSolicitud.getIdRol());

		return query.getResultList();
	}
	
	/**
	 * Consulta la lista de Personas Solicitud asociadas a una Ubicacion
	 * @param idUbicacion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PersonaSolicitud> consultarPersonasSolicitudPorUbicacion(Long idUbicacion) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ups.personaSolicitud FROM UbicacionPersonaSolicitud ups");
		jpqlQuery.append(" WHERE ups.ubicacion.idUbicacion = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(UbicacionPersonaSolicitud.ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_UBICACION));
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(UbicacionPersonaSolicitud.ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_UBICACION), idUbicacion);
		
		return query.getResultList();
	}		            
}

