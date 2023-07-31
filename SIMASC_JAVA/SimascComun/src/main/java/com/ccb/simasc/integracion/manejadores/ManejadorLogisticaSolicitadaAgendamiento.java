package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.LogisticaSolicitadaAgendamiento;
import com.ccb.simasc.transversal.entidades.LogisticaSolicitadaAgendamientoPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad LogisticaSolicitadaAgendamiento.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorLogisticaSolicitadaAgendamiento extends ManejadorCrud<LogisticaSolicitadaAgendamiento,LogisticaSolicitadaAgendamientoPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	@PersistenceContext
	private transient EntityManager em;
	// protected region atributos end
    
    public ManejadorLogisticaSolicitadaAgendamiento() {
        super(LogisticaSolicitadaAgendamiento.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    public List<LogisticaSolicitadaAgendamiento> buscarPorIdAgendamiento(Long idAgendamiento){
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ag.* FROM LOGISTICA_SOLICITADA_AGENDAMIENTO ag ");
		jpqlQuery.append(" WHERE ");
		jpqlQuery.append(" ag.estado_registro = '");
		jpqlQuery.append(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		jpqlQuery.append("' AND ag.id_agendamiento = ");
		jpqlQuery.append(idAgendamiento);
		Query q = em.createNativeQuery(jpqlQuery.toString(),LogisticaSolicitadaAgendamiento.class);		
		return q.getResultList();
		}
    
    // protected region metodos adicionales end
        
}

