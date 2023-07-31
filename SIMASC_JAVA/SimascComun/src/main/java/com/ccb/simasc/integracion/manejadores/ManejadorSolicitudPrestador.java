package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.SolicitudPrestadorDTO;
import com.ccb.simasc.transversal.entidades.SolicitudPrestador;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad SolicitudPrestador.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorSolicitudPrestador extends ManejadorCrud<SolicitudPrestador,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private EntityManager em;

	// protected region atributos end
	

    
    public ManejadorSolicitudPrestador() {
        super(SolicitudPrestador.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    /** CON-F-118
     * @author prendon
     * Metodo que consulta una solicitud de cambio de lista por idRolPersona, estadoSolicitud y tipo de solicitud
     * @param idRolPersona
     * @param estadoSolicitud
     * @return SolicitudCambioLista
     */
    public SolicitudPrestador consultarSolicitudPrestadorPersonaEstadoTipo( Long idRolPersona, String estadoSolicitud, String tipo ){
    	SolicitudPrestador solicitudCambioLista;
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT s FROM SolicitudPrestador s ");
		jpqlQuery.append(" WHERE s.idRolPersona = : ").append(SolicitudPrestador.ENTIDAD_SOLICITUD_PRESTADOR_ID_ROL_PERSONA);
		jpqlQuery.append(" AND s.estadoSolicitud = : ").append(SolicitudPrestador.ENTIDAD_SOLICITUD_PRESTADOR_ESTADO_SOLICITUD);
		jpqlQuery.append(" AND s.tipo = : ").append(SolicitudPrestador.ENTIDAD_SOLICITUD_PRESTADOR_TIPO);
		jpqlQuery.append(" AND s.estadoRegistro = : ").append(SolicitudPrestador.ENTIDAD_SOLICITUD_PRESTADOR_ESTADO_REGISTRO);

		Query query = em.createQuery(jpqlQuery.toString());
		query.setParameter(SolicitudPrestador.ENTIDAD_SOLICITUD_PRESTADOR_ID_ROL_PERSONA, idRolPersona);
		query.setParameter(SolicitudPrestador.ENTIDAD_SOLICITUD_PRESTADOR_ESTADO_SOLICITUD, estadoSolicitud);
		query.setParameter(SolicitudPrestador.ENTIDAD_SOLICITUD_PRESTADOR_TIPO, tipo);
		query.setParameter(SolicitudPrestador.ENTIDAD_SOLICITUD_PRESTADOR_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		try{
			solicitudCambioLista = (SolicitudPrestador) query.getSingleResult();
		} catch (NoResultException e) {
			solicitudCambioLista = null;
		} catch (NonUniqueResultException ne) {
			throw new SIMASCNegocioExcepcion(String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR560.toString())));
		}
		return solicitudCambioLista;
    }
    
    /** CON-C-002
     * query que consulta las solicitudes de cambio de lista por nombre de solicitante, estado de solicitud, tipos y centros 
     * @param nombres
     * @param estadoSolicitud
     * @param idCentros
     * @return
     */
    public List<SolicitudPrestador> consultarSolicitudesCambioLista( String nombres, String estadoSolicitud, List<String> tipos, List<String> idCentros ){

    	StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT s.* FROM Solicitud_Prestador s ");
		nativeQuery.append(" INNER JOIN rol_Persona rp ");
		nativeQuery.append(" ON s.id_rol_persona = rp.id_rol_persona ");
		nativeQuery.append(" INNER JOIN PERSONA p ");
		nativeQuery.append(" ON p.id_persona = rp.id_persona ");
		nativeQuery.append(" INNER JOIN rol r ");
		nativeQuery.append(" ON r.id_rol = rp.id_rol ");
		nativeQuery.append(" WHERE s.estado_Registro = ?1  ");
		if( estadoSolicitud != null )
			nativeQuery.append(" AND s.estado_solicitud = ?2");
		if( nombres != null ){
			String sentencia  = "((CONCAT(p.primer_nombre_o_razon_social,p.segundo_nombre,p.primer_apellido,p.segundo_apellido) like '%?3%' ) or "+
					"(CONCAT(p.primer_nombre_o_razon_social,p.segundo_nombre) like  '%?3%') or "+  
					"(CONCAT(p.primer_apellido,p.primer_nombre_o_razon_social) like '%?3%') or "+
					"(CONCAT(p.primer_nombre_o_razon_social,p.primer_apellido) like '%?3%')  )";
			nativeQuery.append(" AND ").append( sentencia );
		}
			
		if( idCentros != null && !idCentros.isEmpty() )
			nativeQuery.append(" AND rp.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(idCentros));
		if( tipos != null && !tipos.isEmpty() )
			nativeQuery.append(" AND s.tipo ").append(UtilConsultasSQL.clausulaInSQLStrings(tipos));
		nativeQuery.append(" AND rp.estado_Registro = ?1 ");
		nativeQuery.append(" AND p.estado_Registro = ?1 ");
		nativeQuery.append(" AND r.estado_Registro = ?1 ");
		nativeQuery.append(" ORDER BY s.fecha_Solicitud DESC");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), SolicitudPrestador.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, estadoSolicitud);
		query.setParameter(3, nombres);
		
		return query.getResultList();
    }

    public List<SolicitudPrestadorDTO> consultarSolicitudesRealizadas(String tipoServicio, String tipoSolicitud){
    	StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select concat(p.primer_nombre_o_razon_social,' ',p.segundo_nombre, ' ',p.primer_apellido,' ',p.segundo_apellido) as nombreCompleto, ");
		nativeQuery.append(" sp.fecha_solicitud as fechaSolicitud, d.nombre as tipo, ");
		nativeQuery.append(" ISNULL(sp.descripcion_solicitud,'') as descripcionSolicitud, ");
		nativeQuery.append(" sp.id_documento as idDocumento ");
		nativeQuery.append(" from solicitud_prestador sp  ");
		nativeQuery.append(" inner join rol_persona rp on rp.id_rol_persona=sp.id_rol_persona ");
		nativeQuery.append(" inner join persona p on p.id_persona=rp.id_persona ");
		nativeQuery.append(" inner join rol r on r.id_rol=rp.id_rol  ");
		nativeQuery.append(" inner join dominio d on d.codigo=sp.tipo  ");
		nativeQuery.append(" where r.tipo_servicio=?1 ");
		nativeQuery.append(" and sp.tipo=?4 ");
		nativeQuery.append(" and sp.estado_solicitud=?2  ");
		nativeQuery.append(" and r.estado_registro=?3  ");
		nativeQuery.append(" AND rp.estado_registro=?3  ");
		nativeQuery.append(" AND P.estado_registro=?3 ");
		nativeQuery.append(" ORDER BY sp.fecha_solicitud desc ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), SolicitudPrestadorDTO.class);
		query.setParameter(1, tipoServicio);
		query.setParameter(2, UtilDominios.ESTADO_SOLICITUD_PRESTADOR_PENDIENTE);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(4, tipoSolicitud );
		
		return query.getResultList();
    }
    
    public List<SolicitudPrestador> consultarSolicitudesTipoRolPersona(List<Long> idRolPersona, String tipoSolicitud){
    	StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT s.* FROM Solicitud_Prestador s ");
		nativeQuery.append(" WHERE s.estado_Registro = ?1  ");
		if( idRolPersona != null )
			nativeQuery.append(" AND s.id_rol_persona ").append(UtilConsultasSQL.clausulaInSQLSNumeros(idRolPersona));
		if( tipoSolicitud != null )
			nativeQuery.append(" AND s.estado_solicitud = ?2 ");
		
		nativeQuery.append(" order by fecha_solicitud desc ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), SolicitudPrestador.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		if( tipoSolicitud != null ){
			query.setParameter(2, tipoSolicitud);	
		}
		
		return query.getResultList();
    }
    // protected region metodos adicionales end
        
}

