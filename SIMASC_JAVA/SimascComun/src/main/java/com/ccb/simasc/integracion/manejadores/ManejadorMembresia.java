package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Membresia;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Membresia.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorMembresia extends ManejadorCrud<Membresia,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private EntityManager em;
	// protected region atributos end
    
    public ManejadorMembresia() {
        super(Membresia.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    /** CON-C-004
     * query que consulta las membresias de una persona ordenada por la que tenga la fecha de fin mayor
     * @param idPersona
     * @return
     */
    public List<Membresia> consultarMembresiasPersona( Long idPersona ){
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT m FROM Membresia m ");
		jpqlQuery.append(" WHERE m.estadoRegistro = : ").append(Membresia.ENTIDAD_MEMBRESIA_ESTADO_REGISTRO);
		jpqlQuery.append(" AND m.idPersona = : ").append(Membresia.ENTIDAD_MEMBRESIA_ID_PERSONA);
		jpqlQuery.append(" ORDER BY m.fechaFin DESC");

		Query query = em.createQuery(jpqlQuery.toString());
		query.setParameter(Membresia.ENTIDAD_MEMBRESIA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Membresia.ENTIDAD_MEMBRESIA_ID_PERSONA, idPersona);

		return query.getResultList();
    }
    
    // protected region metodos adicionales end
        
}

