package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.ApoderadosParteDTO;
import com.ccb.simasc.transversal.entidades.Apoderados;
import com.ccb.simasc.transversal.entidades.ApoderadosPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Apoderados.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorApoderados extends ManejadorCrud<Apoderados,ApoderadosPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	@PersistenceContext
	private transient EntityManager em;
	
	// protected region atributos end
    
    public ManejadorApoderados() {
        super(Apoderados.class);
    }    
    
    public List<Apoderados> obtenerRepresentadosPorApoderado(Long idRol, Long idPersona, Long idCaso) {
		Query q = em.createNativeQuery("SELECT * FROM apoderados a "
				+ "WHERE a.id_rol_apoderado = ?1 "
				+ "AND a.id_persona_apoderado = ?2 " 
				+ "AND a.id_caso_apoderado = ?3 "
				+ "AND a.estado_registro = ?4 ", Apoderados.class);
		q.setParameter(1, idRol);
		q.setParameter(2, idPersona);
		q.setParameter(3, idCaso);
		q.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		List<Apoderados> representados = q.getResultList();
		return representados;
	}
    
    public Apoderados obtenerRepresentadoPorApoderado(Long idRolRepresentado, Long idPersonaRepresentado, Long idCasoRepresentado, 
    		Long idRolApoderado, Long idPersonaApoderado, Long idCasoApoderado) {
		Query q = em.createNativeQuery("SELECT * FROM apoderados a "
				+ "WHERE a.id_rol_representado = ?1 "
				+ "AND a.id_persona_representado = ?2 "
				+ "AND a.id_caso_representado = ?3 "
				+ "AND a.id_rol_apoderado = ?4 "
				+ "AND a.id_persona_apoderado = ?5 " 
				+ "AND a.id_caso_apoderado = ?6 "
				+ "AND a.estado_registro = ?7 ", Apoderados.class);
		q.setParameter(1, idRolRepresentado);
		q.setParameter(2, idPersonaRepresentado);
		q.setParameter(3, idCasoRepresentado);
		q.setParameter(4, idRolApoderado);
		q.setParameter(5, idPersonaApoderado);
		q.setParameter(6, idCasoApoderado);
		q.setParameter(7, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		Apoderados representado = (Apoderados) q.getSingleResult();
		return representado;
	}
    
    public List<Apoderados> obtenerApoderadoPorPersonaYCaso(Long idPersona, Long idCaso) {
		Query q = em.createNativeQuery("SELECT * FROM apoderados a "
				+ "WHERE a.id_persona_apoderado = ?1 " 
				+ "AND a.id_caso_apoderado = ?2 "
				+ "AND a.estado_registro = ?3 ", Apoderados.class);
		q.setParameter(1, idPersona);
		q.setParameter(2, idCaso);
		q.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		List<Apoderados> representado = q.getResultList();
		return representado;
	}
    

    public List<Apoderados> obtenerRepresentadosPorPersonaCaso(Long idPersona,Long idCaso) {
		Query q = em.createNativeQuery("SELECT * FROM apoderados a "
				+ "WHERE a.id_persona_representado = ?1 " 
				+ "AND a.id_caso_representado = ?2 "
				+ "AND a.estado_registro = ?3 ", Apoderados.class);
		q.setParameter(1, idPersona);
		q.setParameter(2, idCaso);
		q.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		List<Apoderados> representado = q.getResultList();
		return representado;
	}
    
    public List<ApoderadosParteDTO> obtenerApoderadosParte(Long idCaso) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT "); 
		sql.append(" ISNULL(p1.primer_nombre_o_razon_social + ' ','') + "); 
		sql.append(" ISNULL(p1.segundo_nombre + ' ' , '') +   ");
		sql.append(" ISNULL(p1.primer_apellido + ' ', '' ) +  ");
		sql.append(" ISNULL(p1.segundo_apellido, '' ) as nombreApoderado,  "); 
		sql.append(" ISNULL(p2.primer_nombre_o_razon_social, + ' ') +  ");
		sql.append(" ISNULL(p2.segundo_nombre + ' ','') +  ");
		sql.append(" ISNULL(p2.primer_apellido + ' ','') +  ");
		sql.append(" ISNULL(p2.segundo_apellido, '') as parteRepresenta  ");
		sql.append(" FROM APODERADOS a  INNER JOIN PERSONA p1 ON a.id_persona_apoderado = p1.id_persona  "); 
		sql.append(" INNER JOIN PERSONA p2 ON a.id_persona_representado  = p2.id_persona   ");
		sql.append(" WHERE a.id_caso_representado = ?1 ");
    	
    	Query q = em.createNativeQuery(sql.toString(), ApoderadosParteDTO.class);
		q.setParameter(1, idCaso);
		List<ApoderadosParteDTO> parte = q.getResultList();
		return parte;
	}
    
    
    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    // protected region metodos adicionales end
        
}

