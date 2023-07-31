package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.ObligacionParte;
import com.ccb.simasc.transversal.entidades.ObligacionPartePK;
import com.ccb.simasc.transversal.entidades.ResultadoAudiencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad ObligacionParte.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorObligacionParte extends ManejadorCrud<ObligacionParte,ObligacionPartePK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private EntityManager em;
	// protected region atributos end
    
    public ManejadorObligacionParte() {
        super(ObligacionParte.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    @SuppressWarnings("unchecked")
	public List<ObligacionParte> consultarObligacionParteByIdResultadoAudiencia(Long idResultadoAudiencia){
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append(" select op from ObligacionParte op ");
    	jpqlQuery.append(" where op.obligacionPartePK.idResultadoAudiencia =:");
    	jpqlQuery.append(ResultadoAudiencia.ENTIDAD_RESULTADO_AUDIENCIA_PK);
    	
    	Query query = em.createQuery(jpqlQuery.toString(),ObligacionParte.class);
    	query.setParameter(ResultadoAudiencia.ENTIDAD_RESULTADO_AUDIENCIA_PK, idResultadoAudiencia);
    	return query.getResultList();
    }
    // protected region metodos adicionales end
        
}

