package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.EspecialidadDTO;
import com.ccb.simasc.transversal.dto.MateriaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Materia;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Materia.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorMateria extends ManejadorCrud<Materia,Long>{

	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	@PersistenceContext
	private transient EntityManager em;
	
	// protected region atributos end
    
    public ManejadorMateria() {
        super(Materia.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones
    
    public List<Materia> consultarMateriasPorServicio(Long idServicio){

    	
    	StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select m.* from SERVICIO_MATERIA sm  ");
		nativeQuery.append(" inner join materia m on sm.id_materia=m.id_materia ");
		nativeQuery.append(" where  ");
		nativeQuery.append(" sm.estado_registro=?1 ");
		nativeQuery.append(" and m.estado_registro=?1 ");
		if( idServicio != null ){
			nativeQuery.append(" and sm.id_servicio= ?2 ");
		}
		
		nativeQuery.append(" ORDER BY m.nombre ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), Materia.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if( idServicio != null ){
			query.setParameter(2, idServicio);
		}
		
    	return query.getResultList();
    }
    

    public List<Materia>  consultarMateriasConServicios(){
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("SELECT DISTINCT m FROM Materia m");
    	jpqlQuery.append(" WHERE size(m.servicioMateriaList)>0");
    	Query query = mp.createQuery(jpqlQuery.toString());
    	return query.getResultList();
    }
    
    /**
     * Retorna la materia SIM MATERIA
     * @return
     */
    public Materia consultaSinMateria() {
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("SELECT m FROM Materia m WHERE m.nombre=?1");
    	Query query = mp.createQuery(jpqlQuery.toString());
    	query.setParameter(1, UtilConstantes.SIN_MATERIA);
    	return (Materia)query.getSingleResult();
    }
    
    /**
     * ADM-C-003
     * Consulta las materias del servicio asociado al rol a través de la tabla
     * ParametroServicioSorteo y el sorteo indicador sorteo_con_materia es verdadero.
     * 
     * @param nombreRol Codigo de dominio del rol
     * @return
     */
    public List<MateriaBasicaDTO> consultarMateriasRol(String nombreRol){
    	
    	StringBuilder nativeQuery = new StringBuilder();
    	
    	nativeQuery.append("SELECT m.id_materia as idMateria, m.nombre as nombre ");
    	nativeQuery.append("    	FROM MATERIA m ");
    	nativeQuery.append("    		INNER JOIN SERVICIO_MATERIA sm ON sm.id_materia=m.id_materia ");
    	nativeQuery.append("    		INNER JOIN SERVICIO s ON s.id_servicio = sm.id_servicio ");
    	nativeQuery.append("    		INNER JOIN PARAMETRO_SERVICIO_SORTEO pss ON pss.id_servicio=s.id_servicio ");
    	nativeQuery.append("    		INNER JOIN ROL rol ON rol.id_rol = pss.id_rol ");
    	nativeQuery.append("    	WHERE  ");
    	nativeQuery.append("    		rol.nombre = ?1 ");
    	nativeQuery.append("    		AND m.estado_registro='ACT' ");
    	nativeQuery.append("    		AND sm.estado_registro='ACT' ");
    	nativeQuery.append("    		AND pss.estado_registro='ACT' ");
    	nativeQuery.append("    		AND pss.sorteo_con_materia = 1 ");
    	nativeQuery.append("    	ORDER BY ");
    	nativeQuery.append("    		m.nombre ");
    	
    	Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), MateriaBasicaDTO.class);
    	query.setParameter(1, nombreRol);
    	return query.getResultList();
    	
    }
    
    /**
     * ADM-C-029
     * Consulta las materias del servicio asociado al rol para conciliación y arbitraje.
     * 
     * @param nombreRol Codigo de dominio del rol
     * @return
     */
    public List<MateriaBasicaDTO> consultarMateriasporRolCONARB(String nombreRol){
    	
    	StringBuilder nativeQuery = new StringBuilder();
    	
    	nativeQuery.append("SELECT m.id_materia as idMateria, m.nombre as nombre ");
    	nativeQuery.append("    	FROM MATERIA m ");
    	nativeQuery.append("    		INNER JOIN SERVICIO_MATERIA as sm ON sm.id_materia=m.id_materia  ");
    	nativeQuery.append("    		LEFT JOIN AGRUPAMIENTO_ROL as agr ON agr.id_servicio=sm.id_servicio and agr.tipo_agrupamiento = ?2 ");
    	nativeQuery.append("    		INNER JOIN ROL rol ON rol.id_rol = agr.id_rol ");
    	nativeQuery.append("    	WHERE  ");
    	nativeQuery.append("    		rol.nombre = ?1 ");
    	nativeQuery.append("    		AND m.estado_registro='ACT' ");
    	nativeQuery.append("    		AND sm.estado_registro='ACT' ");
    	nativeQuery.append("    	ORDER BY ");
    	nativeQuery.append("    		m.nombre ");
    	
    	Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), MateriaBasicaDTO.class);
    	query.setParameter(1, nombreRol);
    	query.setParameter(2, UtilDominios.AGRUPAMIENTO_MATERIA_ROL);
    	return query.getResultList();
    	
    }
    
    /**
     * ADM-C-029
     * Consulta las especialidades por materia
     * 
     * @return
     */
    public List<EspecialidadDTO> consultarEspecialidadesMaterias(String idMateria){
    	
	List<EspecialidadDTO> especialidades;
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" Select id_especialidad as idEspecialidad, id_materia as idMateria, nombre as nombre, descripcion as descripcion, id_usuario_modificacion as idUsuarioModificacion, fecha_ultima_modificacion as fechaUltimaModificacion, estado_registro as estadoRegistro ");
		nativeQuery.append(" FROM ESPECIALIDAD ");
		nativeQuery.append(" WHERE id_materia = ?1 ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), EspecialidadDTO.class);
		query.setParameter(1, idMateria);

		especialidades = query.getResultList();	
		return especialidades;
   	
    }
    
	
    /** ADM-C-004
     * Retorna la lista de las materias que se encuentren activas
     * @return List<Materia>
     */
    public List<Materia> consultarMateriasParametros() {
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("SELECT m FROM Materia m ");
    	jpqlQuery.append(" WHERE m.estadoRegistro =:").append(Materia.ENTIDAD_MATERIA_ESTADO_REGISTRO);
    	jpqlQuery.append(" ORDER BY m.nombre");
    	
    	Query query = mp.createQuery(jpqlQuery.toString());
    	query.setParameter( Materia.ENTIDAD_MATERIA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO );
    	return query.getResultList();
    }
    
    // protected region metodos adicionales end
        
}

