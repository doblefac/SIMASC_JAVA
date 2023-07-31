package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.ApoderadosSolicitud;
import com.ccb.simasc.transversal.entidades.ApoderadosSolicitudPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad ApoderadosSolicitud.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorApoderadosSolicitud extends ManejadorCrud<ApoderadosSolicitud,ApoderadosSolicitudPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	@PersistenceContext
	private transient EntityManager em;
	
	// protected region atributos end
    
    public ManejadorApoderadosSolicitud() {
        super(ApoderadosSolicitud.class);
    }

	public List<ApoderadosSolicitud> obtenerRepresentadosPorApoderado(Long idRol, Long idPersona, Long idSolicitud) {
		Query q = em.createNativeQuery("SELECT * FROM apoderados_solicitud a "
				+ "WHERE a.id_rol_apoderado = ?1 "
				+ "AND a.id_persona_apoderado = ?2 " 
				+ "AND a.id_solicitud_servicio_apoderado = ?3 "
				+ "AND a.estado_registro = ?4 ", ApoderadosSolicitud.class);
		q.setParameter(1, idRol);
		q.setParameter(2, idPersona);
		q.setParameter(3, idSolicitud);
		q.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		List<ApoderadosSolicitud> representados = q.getResultList();
		return representados;
	}
	
	public List<ApoderadosSolicitud> obtenerApoderadosPorSolicitud(Long idSolicitud) {
		Query q = em.createNativeQuery("SELECT * FROM apoderados_solicitud a "
				+ "WHERE a.id_solicitud_servicio_apoderado = ?1 "
				+ "AND a.estado_registro = ?2 ", ApoderadosSolicitud.class);
		q.setParameter(1, idSolicitud);
		q.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		List<ApoderadosSolicitud> representados = q.getResultList();
		return representados;
	}

	public List<ApoderadosSolicitud> obtenerApoderadoPorPersonaYCaso(Long idPersona, Long idSolicitudServicio) {
		Query q = em.createNativeQuery("SELECT * FROM apoderados_solicitud a "
				+ "WHERE a.id_persona_apoderado = ?1 " 
				+ "AND a.id_solicitud_servicio_apoderado = ?2 "
				+ "AND a.estado_registro = ?3 ", ApoderadosSolicitud.class);
		q.setParameter(1, idPersona);
		q.setParameter(2, idSolicitudServicio);
		q.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		List<ApoderadosSolicitud> representado = q.getResultList();
		return representado;
	}

	public List<ApoderadosSolicitud> obtenerRepresentadosPorPersonaCaso(Long idPersona, Long idSolicitudServicio) {
		Query q = em.createNativeQuery("SELECT * FROM apoderados_solicitud a "
				+ "WHERE a.id_persona_representado = ?1 " 
				+ "AND a.id_solicitud_servicio_representado = ?2 "
				+ "AND a.estado_registro = ?3 ", ApoderadosSolicitud.class);
		q.setParameter(1, idPersona);
		q.setParameter(2, idSolicitudServicio);
		q.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		List<ApoderadosSolicitud> representado = q.getResultList();
		return representado;
	}
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    // protected region metodos adicionales end
        
}

