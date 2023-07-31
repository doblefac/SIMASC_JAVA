package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.InfraestructuraSolicitadaAgendamiento;
import com.ccb.simasc.transversal.entidades.InfraestructuraSolicitadaAgendamientoPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad InfraestructuraSolicitadaAgendamiento.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorInfraestructuraSolicitadaAgendamiento extends ManejadorCrud<InfraestructuraSolicitadaAgendamiento,InfraestructuraSolicitadaAgendamientoPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private transient EntityManager em;
	// protected region atributos end
    
    public ManejadorInfraestructuraSolicitadaAgendamiento() {
        super(InfraestructuraSolicitadaAgendamiento.class);
    }    
    
    // protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	public List<InfraestructuraSolicitadaAgendamiento> buscarPorIdAgendamiento(Long idAgendamiento) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ag.* FROM INFRAESTRUCTURA_SOLICITADA_AGENDAMIENTO ag ");
		jpqlQuery.append(" WHERE ");
		jpqlQuery.append(" ag.estado_registro = '");
		jpqlQuery.append(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		jpqlQuery.append("' AND ag.id_agendamiento = ");
		jpqlQuery.append(idAgendamiento);
		Query q = em.createNativeQuery(jpqlQuery.toString(),InfraestructuraSolicitadaAgendamiento.class);		
		return q.getResultList();

	}

	// protected region metodos adicionales end
        
}

