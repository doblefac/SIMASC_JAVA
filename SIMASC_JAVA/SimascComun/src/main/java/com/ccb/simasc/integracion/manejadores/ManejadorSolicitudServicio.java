package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.SolicitudServicio;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad SolicitudServicio.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorSolicitudServicio extends ManejadorCrud<SolicitudServicio,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	
	@PersistenceContext
	private EntityManager em;

	// protected region atributos end
    
    public ManejadorSolicitudServicio() {
        super(SolicitudServicio.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    /**
	 * Registra una Solicitud de Servicio en la base de datos
	 * 
	 * @param solicitudServicio
	 * @return SolicitudServicio
	 */
	public SolicitudServicio crearSolicitudServicio(SolicitudServicio solicitudServicio) {
		return (SolicitudServicio) mp.updateObject(solicitudServicio);
	}
	
	/**
	 * Consulta una solicitud de Servicio a traves de un id de solicitud.
	 * 
	 * @param idSolicitud
	 * @return SolicitudServicio
	 */
	public SolicitudServicio consultarSolicitudServicio(Long idSolicitud) {
		SolicitudServicio solicitudServicio = new SolicitudServicio();
		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append("SELECT s FROM SolicitudServicio s ");
			jpqlQuery.append(" WHERE s.idSolicitudServicio =: ")
					.append(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_PK);
			jpqlQuery.append(" AND s.estadoRegistro =: ")
					.append(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_ESTADO_REGISTRO);

			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_PK, idSolicitud);
			query.setParameter(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			solicitudServicio = (SolicitudServicio) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return solicitudServicio;
	}
	
	/**
	 * Consulta una solicitud de Servicio a traves de un id de solicitud.
	 * 
	 * @param idSolicitud
	 * @return SolicitudServicio
	 */
	public SolicitudServicio consultarConciliadorSolicitud(Long idSolicitud) {
		SolicitudServicio solicitudServicio = new SolicitudServicio();
		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append("SELECT s FROM SolicitudServicio s ");
			jpqlQuery.append(" WHERE s.idSolicitudServicio =: ")
					.append(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_PK);
			jpqlQuery.append(" AND s.estadoRegistro =: ")
					.append(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_ESTADO_REGISTRO);

			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_PK, idSolicitud);
			query.setParameter(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			solicitudServicio = (SolicitudServicio) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return solicitudServicio;
	}
	
	/**
	 * Consulta la lista de solicitudes vigentes (menos de 5 días hábiles)
	 * asociadas a un solicitante
	 * 
	 * @param idPersona
	 * @return
	 */
	public List<SolicitudServicio> consultarSolicitudesVigentesPorSolicitante(Long idPersona) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT s.* ");
		nativeQuery.append("FROM SOLICITUD_SERVICIO s, ");
		nativeQuery.append("PERSONA_SOLICITUD ps, ");
		nativeQuery.append("ROL r ");
		nativeQuery.append("WHERE s.id_solicitud_servicio = ps.id_solicitud_servicio ");
		nativeQuery.append("AND (select dbo.diasHabilesEntreDosFechas(s.fecha_creacion,GETDATE())+1)  <= 5  ");
		nativeQuery.append("AND ps.id_rol = r.id_rol ");
		nativeQuery.append("AND r.nombre = ?1 ");
		nativeQuery.append("AND ps.id_persona = ?2 ");
		nativeQuery.append("AND s.estado_registro = ?3 ");
		nativeQuery.append("AND ps.estado_registro = ?4 ");
		nativeQuery.append("AND r.estado_registro = ?5 ");
		nativeQuery.append("AND not exists (select 1 from caso c where c.id_solicitud_servicio = s.id_solicitud_servicio) ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), SolicitudServicio.class);
		query.setParameter(1, UtilDominios.ROL_PERSONA_SOLICITANTE);
		query.setParameter(2, idPersona);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);		
		
		return query.getResultList();
	}
	
	public List<SolicitudServicio> consultarSolicitudPorOrden(Long numeroOrden) {
		StringBuilder jpqlQuery = new StringBuilder();
		
		jpqlQuery.append(" select s from SolicitudServicio s");
		jpqlQuery.append(" join fetch s.personaSolicitudList ");
		jpqlQuery.append(" where s.estadoRegistro = :");
		jpqlQuery.append(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_ESTADO_REGISTRO);
		jpqlQuery.append(" and s.idOrdenDePago = :");
		jpqlQuery.append(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_ID_ORDEN_DE_PAGO);
		
		Query query = em.createQuery(jpqlQuery.toString());
		query.setParameter(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_ID_ORDEN_DE_PAGO, numeroOrden);
		
		return query.getResultList();
	}
	
	public boolean validarPagoSolicitudServicio(Long numeroOrden) {
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT SS.* ");
		nativeQuery.append("FROM SOLICITUD_SERVICIO SS ");
		nativeQuery.append("INNER JOIN CASO C on C.id_solicitud_servicio = SS.id_solicitud_servicio ");
		nativeQuery.append("WHERE SS.id_orden_de_pago = ?1 ");
		Query query = em.createNativeQuery(nativeQuery.toString(), SolicitudServicio.class);
		query.setParameter(1, numeroOrden);
		
		return query.getResultList().isEmpty();
	}

    // protected region metodos adicionales end
        
}

