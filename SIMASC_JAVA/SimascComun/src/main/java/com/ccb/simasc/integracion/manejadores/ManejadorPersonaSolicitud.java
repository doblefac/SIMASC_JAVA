package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.PersonaSolicitudPK;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.SolicitudServicio;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad PersonaSolicitud.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorPersonaSolicitud extends ManejadorCrud<PersonaSolicitud,PersonaSolicitudPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private transient EntityManager em;


	// protected region atributos end
    


	
    public ManejadorPersonaSolicitud() {
        super(PersonaSolicitud.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
	/**
	 * Registra una Solicitud de Persona en la base de datos
	 * 
	 * @param personaSolicitud
	 * @return PersonaSolicitud
	 */
	public PersonaSolicitud crearPersonaSolicitud(PersonaSolicitud personaSolicitud) {
		return (PersonaSolicitud) mp.updateObject(personaSolicitud);
	}
	
	/**
	 * Consulta de las personas asociadas a una Solicitud de Servicio y que se
	 * encuentren activas.
	 * 
	 * @param idSolicitudServicio
	 * @return List<PersonaSolicitud>
	 */
	@SuppressWarnings("unchecked")
	public List<PersonaSolicitud> consultarPersonasSolicitud(Long idSolicitudServicio, List<String> roles, boolean conCorreoUbicacion) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ps FROM ");
		jpqlQuery.append(" PersonaSolicitud ps ");
		if (conCorreoUbicacion) {
			jpqlQuery.append(" left join fetch ps.correoElectronicoPersonaSolicitudList ");
			jpqlQuery.append(" left join fetch ps.ubicacionPersonaSolicitudList ");
		}
		jpqlQuery.append(" WHERE ps.personaSolicitudPK.idSolicitudServicio = :");
		jpqlQuery.append(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_PK);
		jpqlQuery.append(" AND ps.estadoRegistro = :");
		jpqlQuery.append(PersonaSolicitud.ENTIDAD_PERSONA_SOLICITUD_ESTADO_REGISTRO);
		if (roles != null && !roles.isEmpty()) {
			jpqlQuery.append(" AND ps.rol.nombre IN :");
			jpqlQuery.append(UtilConsultasSQL.obtenerNombreParametro(Dominio.ENTIDAD_DOMINIO_PK_DOMINIO));
		}

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_PK, idSolicitudServicio);
		query.setParameter(PersonaSolicitud.ENTIDAD_PERSONA_SOLICITUD_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if (roles != null && !roles.isEmpty())
			query.setParameter(UtilConsultasSQL.obtenerNombreParametro(Dominio.ENTIDAD_DOMINIO_PK_DOMINIO), roles);

		return query.getResultList();
	}

	/**
	 * Consulta de una Persona Solicitud por el id de la solicitud del servicio
	 * Opcional nombre de rol o idPersona.
	 * 
	 * @param idSolicitudServicio
	 * @param nombreRol
	 * @return PersonaSolicitud
	 */
	public List<PersonaSolicitud> consultarPersonaSolicitud(Long idSolicitudServicio, Long idPersona, String nombreRol) {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append("SELECT ps FROM PersonaSolicitud ps ");
			jpqlQuery.append(" WHERE ps.personaSolicitudPK.idSolicitudServicio = :");
			jpqlQuery.append(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_PK);
			jpqlQuery.append(" AND ps.estadoRegistro = :");
			jpqlQuery.append(PersonaSolicitud.ENTIDAD_PERSONA_SOLICITUD_ESTADO_REGISTRO);

			if (idPersona != null) {
				jpqlQuery.append(" AND ps.personaSolicitudPK.idPersona = :");
				jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
			}

			if (nombreRol != null) {
				jpqlQuery.append(" AND ps.rol.nombre = :");
				jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
			}

			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_PK, idSolicitudServicio);
			query.setParameter(PersonaSolicitud.ENTIDAD_PERSONA_SOLICITUD_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);

			if (nombreRol != null)
				query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombreRol);

			if (idPersona != null)
				query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);

		return query.getResultList();
	}
	
	/**
    * Elimina a una persona de una solicitud l�gicamente
    * 
    * @param personaSolicitud
    */
	public void eliminarPersonaSolicitud(PersonaSolicitud personaSolicitud) {
        personaSolicitud.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
        mp.update(personaSolicitud);
    }
	
	
	public List<PersonaSolicitud> consultarApoderadosPorIdPersonaEnCaso(Long idPersona, Long idSolicitud){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("Select ps from PersonaSolicitud as ps ");
		jpqlQuery.append("where ps.idPersonaApoderado =:idApoderado ");		
		jpqlQuery.append("And ps.personaSolicitudPK.idSolicitudServicio = :idServicioSolicitud ");	
		
		Query tipoQuey = em.createQuery(jpqlQuery.toString());
		tipoQuey.setParameter("idApoderado", idPersona);
		tipoQuey.setParameter("idServicioSolicitud", idSolicitud);
			
		return tipoQuey.getResultList();
	}
	
	public List<Persona> getConsultarPartesSolicitudPorRolApoderado(Long idSolicitud, String nombreRol, Long idPersona){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT p FROM PersonaSolicitud prs ");
		jpqlQuery.append("INNER JOIN prs.persona p ");
		jpqlQuery.append("INNER JOIN prs.rol r ");
		jpqlQuery.append("WHERE prs.personaSolicitudPK.idSolicitudServicio = ?1 ");
		jpqlQuery.append("AND r.nombre = ?2 ");
		jpqlQuery.append("AND prs.estadoRegistro = ?3 ");
		jpqlQuery.append("AND prs.idPersonaApoderado = ?4 ");
		
		Query query = em.createQuery(jpqlQuery.toString(), Persona.class);
		query.setParameter(1, idSolicitud);
		query.setParameter(2, nombreRol);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(4, idPersona);
		return query.getResultList();
	}
	
		
	
	/**
	 * Obtiene las personas representadas por un apoderado en una solicitud de
	 * servicio
	 * 
	 * @param idPersonaSolicitudApoderado
	 * @param idSolicitudServicio
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PersonaSolicitud> consultarRepresentadosSolicitudPorApoderado(Long idPersonaSolicitudApoderado,
			Long idSolicitudServicio) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ps FROM PersonaSolicitud ps ");
		jpqlQuery.append(" WHERE ps.idPersonaApoderado = :")
				.append(PersonaSolicitud.ENTIDAD_PERSONA_SOLICITUD_ID_PERSONA_APODERADO);
		jpqlQuery.append(" AND ps.personaSolicitudPK.idSolicitudServicio = :")
				.append(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_PK);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(PersonaSolicitud.ENTIDAD_PERSONA_SOLICITUD_ID_PERSONA_APODERADO,
				idPersonaSolicitudApoderado);
		query.setParameter(SolicitudServicio.ENTIDAD_SOLICITUD_SERVICIO_PK, idSolicitudServicio);

		return query.getResultList();
	}
	
	/**
	 * Obtiene las partes representadas por un apoderado en uns solicitud de
	 * servicio
	 * 
	 * @param idSolicitud
	 * @param nombreRol
	 * @param idPersona
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultarPartesSolicitudPorRolApoderado(Long idSolicitud, String nombreRol, Long idPersona) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT p FROM PersonaSolicitud prs ");
		jpqlQuery.append("INNER JOIN prs.persona p ");
		jpqlQuery.append("INNER JOIN prs.rol r ");
		jpqlQuery.append("WHERE prs.personaSolicitudPK.idSolicitudServicio = ?1 ");
		jpqlQuery.append("AND r.nombre = ?2 ");
		jpqlQuery.append("AND prs.estadoRegistro = ?3 ");
		jpqlQuery.append("AND prs.idPersonaApoderado = ?4 ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(1, idSolicitud);
		query.setParameter(2, nombreRol);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(4, idPersona);

		return query.getResultList();
	}

    // protected region metodos adicionales end
        
}

