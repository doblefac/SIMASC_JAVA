package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.DetalleEvaluacionConciliador;
import com.ccb.simasc.transversal.entidades.DetalleEvaluacionConciliadorPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad DetalleEvaluacionConciliador.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorDetalleEvaluacionConciliador extends ManejadorCrud<DetalleEvaluacionConciliador,DetalleEvaluacionConciliadorPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorDetalleEvaluacionConciliador() {
        super(DetalleEvaluacionConciliador.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    /**
     * Método que obtiene los totales de la evaluacion de un conciliador por criterio
     * @param nombreCriterio
     * @param idEvaluacion
     * @return
     */
    public List<DetalleEvaluacionConciliador> consultarDetallesPorCriterio(String nombreCriterio, Long idEvaluacion) {
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" select e.* from Detalle_Evaluacion_Conciliador e ");
		if(nombreCriterio == null) {
			jpqlQuery.append(", Dominio d");
			jpqlQuery.append(", Clasificador_Dominio p");
		}
		jpqlQuery.append(" where e.estado_Registro = ?5 ");
		jpqlQuery.append(" and e.id_Evaluacion_Conciliador = ?1");
		
		if(nombreCriterio != null) {
			jpqlQuery.append(" and e.codigo_Criterio = ?2");
		} else {
			jpqlQuery.append(" and e.codigo_Total = d.codigo ");
			jpqlQuery.append(" and e.codigo_Criterio = d.codigo_Dom_Padre ");

			jpqlQuery.append(" and d.codigo = p.codigo_Clasificado ");
			jpqlQuery.append(" and d.dominio = p.dominio_Clasificado ");

			jpqlQuery.append(" and p.codigo_Clasificador = ?3");
			jpqlQuery.append(" and p.dominio_Clasificador = ?4");
			jpqlQuery.append(" and p.estado_Registro = ?5 ");
		}
		
		Query query = getEntityManager().createNativeQuery(jpqlQuery.toString(), DetalleEvaluacionConciliador.class);
		if(nombreCriterio != null) {
			query.setParameter(2, nombreCriterio);
		} else {
			query.setParameter(3, UtilDominios.CODIGO_CLASIFICADOR_EVALUACION);
			query.setParameter(4, UtilDominios.DOMINIO_CLASIFICADOR_EVALUACION);
		}
		query.setParameter(1, idEvaluacion);
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();
    }
    // protected region metodos adicionales end
        
}

