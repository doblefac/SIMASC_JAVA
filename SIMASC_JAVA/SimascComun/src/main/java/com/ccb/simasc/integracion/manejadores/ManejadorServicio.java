package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.ServicioDTO;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Servicio.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorServicio extends ManejadorCrud<Servicio,Long>{

	// protected region atributos on begin
  	// Escriba en esta sección sus modificaciones
    @PersistenceContext
    private transient EntityManager em;
    // protected region atributos end
    
    public ManejadorServicio() {
        super(Servicio.class);
    }    
    
    // protected region metodos adicionales on begin
   	// Escriba en esta sección sus modificaciones
    
    /**
     * Consulta que trae el servicio desde su nombre
     * @param servicio
     * @return
     */
    public Servicio consultarServicioporNombre(String nombre){
    	
    	
    	Servicio servicio;
    	
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT s FROM Servicio s WHERE s.nombre = :");
		jpqlQuery.append(Servicio.ENTIDAD_SERVICIO_NOMBRE);
		jpqlQuery.append(" AND s.estadoRegistro = :");
		jpqlQuery.append(Servicio.ENTIDAD_SERVICIO_ESTADO_REGISTRO);
		
		Query query = em.createQuery(jpqlQuery.toString());
		query.setParameter(Servicio.ENTIDAD_SERVICIO_NOMBRE, nombre);
		query.setParameter(Servicio.ENTIDAD_SERVICIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		try{
			servicio =  (Servicio) query.getSingleResult();
		} catch (NoResultException e) {
			servicio = null;
		}
		return servicio != null ? servicio : null;
    	
    }
    
    /**
     * Consulta que trae una lista de servicios por tipo
     * @param servicio
     * @return List<Servicio>
     */
    @SuppressWarnings("unchecked")
    public List<Servicio> consultarServiciosPorTipo(String tipo){
    	
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT s FROM Servicio s ");
		jpqlQuery.append("WHERE s.tipo = :");
		jpqlQuery.append(Servicio.ENTIDAD_SERVICIO_TIPO);
		jpqlQuery.append(" AND s.estadoRegistro = :");
		jpqlQuery.append(Servicio.ENTIDAD_SERVICIO_ESTADO_REGISTRO);
		jpqlQuery.append(" ORDER BY s.nombre");
		
		Query query = em.createQuery(jpqlQuery.toString());
		query.setParameter(Servicio.ENTIDAD_SERVICIO_TIPO, tipo);
		query.setParameter(Servicio.ENTIDAD_SERVICIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
    }
    
    /**
     * Consulta el servicio del caso
     * @param idCaso: 
     * @return Servicio
     */
    public Servicio consultarServicioDelCaso( Long idCaso ){
    	Servicio servicio;
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT s.* FROM caso (nolock) c inner join Servicio (nolock) s on s.id_servicio = c.id_servicio and s.estado_registro = ?2 ");
		jpqlQuery.append(" WHERE c.id_caso = ?1 ");
		jpqlQuery.append(" AND c.estado_Registro = ?2");
		
		Query query = getEntityManager().createNativeQuery(jpqlQuery.toString(), Servicio.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		try{
			servicio = (Servicio) query.getSingleResult();
		} catch( NoResultException ne ){
			servicio = new Servicio();
		}
		return servicio;
    }
    
    @SuppressWarnings("unchecked")
    public List<ServicioDTO> consultarServiciosPorPersona(long idPersona){
    	List<ServicioDTO> servicios;
    	StringBuilder nativeQuery = new StringBuilder();
    	
    	nativeQuery.append("select distinct s.id_servicio as idServicio, s.nombre, s.tipo ");
    	nativeQuery.append(" from ROL_PERSONA rp ");
    	nativeQuery.append(" inner join rol r on r.id_rol = rp.id_rol  ");
    	nativeQuery.append(" inner join SERVICIO s on s.tipo = r.tipo_servicio ");
    	nativeQuery.append(" inner join PARAMETRO_SERVICIO_SORTEO ps on ps.id_servicio = s.id_servicio and ps.id_rol = r.id_rol ");
    	nativeQuery.append(" where rp.id_persona = ?1 and rp.estado_registro = ?2  and ");
    	nativeQuery.append(" s.estado_registro = ?2  and ps.estado_registro = ?2 and rp.fecha_fin_vigencia is null   ");
    	
		Query query = em.createNativeQuery(nativeQuery.toString(), ServicioDTO.class);
		query.setParameter(1, idPersona);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		servicios = query.getResultList();
		return servicios;
    }
    // protected region metodos adicionales end
    @SuppressWarnings("unchecked")
    public List<ServicioDTO> consultarServiciosPorParametroServicioSorteo(long suspExt, long suspNoPro, long suspRec){
    	List<ServicioDTO> servicios;
    	StringBuilder ssQuery = new StringBuilder();
    	ssQuery.append("select s.id_servicio as idServicio, s.nombre, s.tipo ,s.estado_registro  as estadoRegistro  from SERVICIO s, PARAMETRO_SERVICIO_SORTEO p ");
    	ssQuery.append("where s.id_servicio  = p.id_servicio ");
    	ssQuery.append("and (p.suspende_extemporaneo = ?1 or p.suspende_no_pronunciamiento = ?2 or p.suspende_rechazo = ?3) ");
    	Query querySS = em.createNativeQuery(ssQuery.toString(), ServicioDTO.class);
    	querySS.setParameter(1, suspExt);
    	querySS.setParameter(2, suspNoPro);
    	querySS.setParameter(3, suspRec);
    	servicios = querySS.getResultList();
    	return servicios;
    }
    
    @SuppressWarnings("unchecked")
    public List<ServicioDTO> consultarServiciosConTarifador(){
    	
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT s FROM Servicio s ");
		jpqlQuery.append("WHERE s.tarifador = :");
		jpqlQuery.append(Servicio.ENTIDAD_SERVICIO_TARIFADOR);
		jpqlQuery.append(" AND s.estadoRegistro = :");
		jpqlQuery.append(Servicio.ENTIDAD_SERVICIO_ESTADO_REGISTRO);
		jpqlQuery.append(" ORDER BY s.nombre");
		
		Query query = em.createQuery(jpqlQuery.toString());
		query.setParameter(Servicio.ENTIDAD_SERVICIO_TARIFADOR, true);
		query.setParameter(Servicio.ENTIDAD_SERVICIO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<ServicioDTO> consultarServiciosPorPersonaMateria(long idPersona){
    	List<ServicioDTO> servicios;
    	StringBuilder nativeQuery = new StringBuilder();
    	    	
    	nativeQuery.append("select distinct s.id_servicio as idServicio, s.nombre, s.tipo ");
    	nativeQuery.append(" from ROL_PERSONA rp ");
    	nativeQuery.append(" inner join rol r on r.id_rol = rp.id_rol  ");
    	nativeQuery.append(" inner join SERVICIO s on s.tipo = r.tipo_servicio ");
    	nativeQuery.append(" inner join PERSONA_SERVICIO_MATERIA psm on s.id_servicio = psm.id_servicio and rp.id_persona = psm.id_persona ");
    	nativeQuery.append(" inner join PARAMETRO_SERVICIO_SORTEO ps on ps.id_servicio = s.id_servicio and ps.id_rol = r.id_rol ");
    	nativeQuery.append(" where rp.id_persona = ?1 and rp.estado_registro = ?2  and s.estado_registro = ?2 ");
    	nativeQuery.append(" and psm.estado_registro = ?2 and ps.estado_registro = ?2 and rp.fecha_fin_vigencia is null   ");
    	
		Query query = em.createNativeQuery(nativeQuery.toString(), ServicioDTO.class);
		query.setParameter(1, idPersona);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		servicios = query.getResultList();
		return servicios;
    }
        
}

