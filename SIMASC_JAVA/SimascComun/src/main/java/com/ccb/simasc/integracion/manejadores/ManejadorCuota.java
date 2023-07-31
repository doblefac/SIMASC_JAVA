package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Cuota;
import com.ccb.simasc.transversal.entidades.CuotaPK;import com.ccb.simasc.transversal.entidades.ResultadoAudiencia;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Cuota.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorCuota extends ManejadorCrud<Cuota,CuotaPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private EntityManager em;
	// protected region atributos end
    
    public ManejadorCuota() {
        super(Cuota.class);
    }   
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    @SuppressWarnings("unchecked")
	public List<Cuota> obtenerCuotasByIdResultadoConciliacion(Long idResultadoAudiencia){
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append(" select c from Cuota c ");
    	jpqlQuery.append(" where c.cuotaPK.idResultadoAudiencia =: ");
    	jpqlQuery.append(ResultadoAudiencia.ENTIDAD_RESULTADO_AUDIENCIA_PK);
    	
    	Query query = em.createQuery(jpqlQuery.toString(),Cuota.class);
    	query.setParameter(ResultadoAudiencia.ENTIDAD_RESULTADO_AUDIENCIA_PK, idResultadoAudiencia);
    	return query.getResultList();
    }
    // protected region metodos adicionales end
        
}

