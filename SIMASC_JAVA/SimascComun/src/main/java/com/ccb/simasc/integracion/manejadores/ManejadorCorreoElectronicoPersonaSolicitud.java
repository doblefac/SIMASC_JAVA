package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoPersonaSolicitud;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoPersonaSolicitudPK;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.PersonaSolicitudPK;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad CorreoElectronicoPersonaSolicitud.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorCorreoElectronicoPersonaSolicitud extends ManejadorCrud<CorreoElectronicoPersonaSolicitud,CorreoElectronicoPersonaSolicitudPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorCorreoElectronicoPersonaSolicitud() {
        super(CorreoElectronicoPersonaSolicitud.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    /**
	 * Consulta la lista de Correos asociados a una Persona Solicitud
	 * @param personaSolicitud
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CorreoElectronico> consultarCorreosPorPersonaSolicitud(PersonaSolicitudPK personaSolicitud) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ceps.correoElectronico FROM CorreoElectronicoPersonaSolicitud ceps");
		jpqlQuery.append(" WHERE ceps.personaSolicitud.personaSolicitudPK.idPersona = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(CorreoElectronicoPersonaSolicitud.ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_PERSONA));
		jpqlQuery.append(" AND ceps.personaSolicitud.personaSolicitudPK.idSolicitudServicio = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(CorreoElectronicoPersonaSolicitud.ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_SOLICITUD_SERVICIO));
		//jpqlQuery.append(" AND ceps.personaSolicitud.personaSolicitudPK.idRol = :");
		//jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(CorreoElectronicoPersonaSolicitud.ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_ROL));
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(CorreoElectronicoPersonaSolicitud.ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_PERSONA), personaSolicitud.getIdPersona());
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(CorreoElectronicoPersonaSolicitud.ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_SOLICITUD_SERVICIO), personaSolicitud.getIdSolicitudServicio());
		//query.setParameter(UtilConsultasSQL.obtenerNombreParametro(CorreoElectronicoPersonaSolicitud.ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_ROL), personaSolicitud.getIdRol());

		return query.getResultList();
	}
	
	/**
	 * Consulta la lista de Personas Solicitud asociados a un Correo
	 * @param idCorreo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PersonaSolicitud> consultarPersonasSolicitudPorCorreo(Long idCorreo) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ceps.personaSolicitud FROM CorreoElectronicoPersonaSolicitud ceps");
		jpqlQuery.append(" WHERE ceps.correoElectronico.idCorreo = :");
		jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(CorreoElectronicoPersonaSolicitud.ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_CORREO));
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(UtilConsultasSQL.obtenerNombreParametro(CorreoElectronicoPersonaSolicitud.ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_CORREO), idCorreo);
		
		return query.getResultList();
	}
    
    // protected region metodos adicionales end
        
}

