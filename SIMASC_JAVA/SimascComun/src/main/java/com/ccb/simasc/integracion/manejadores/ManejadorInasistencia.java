package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.InasistenciaDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Inasistencia;
import com.ccb.simasc.transversal.entidades.InasistenciaPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Inasistencia.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorInasistencia extends ManejadorCrud<Inasistencia,InasistenciaPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorInasistencia() {
        super(Inasistencia.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    @PersistenceContext
   	private EntityManager em;
    
    @SuppressWarnings("unchecked")
	public List<Inasistencia> consultarInasistencias(InasistenciaDTO inasistencia){
		StringBuilder jpqlQuery = new StringBuilder();
		
		jpqlQuery.append("SELECT i FROM Inasistencia i ");
		jpqlQuery.append(" WHERE ");
		jpqlQuery.append("  i.estadoRegistro =:   ").append(Inasistencia.ENTIDAD_INASISTENCIA_ESTADO_REGISTRO);
		if(inasistencia.getInasistenciaPK().getIdAudiencia()!=null){
			jpqlQuery.append(" AND i.inasistenciaPK.idAudiencia =:").append(Audiencia.ENTIDAD_AUDIENCIA_PK);
			
		}
		if(inasistencia.getTipo()!=null){
			jpqlQuery.append(" AND i.tipo =:").append(Inasistencia.ENTIDAD_INASISTENCIA_TIPO);
		}
		
		
		Query query = em.createQuery(jpqlQuery.toString(), Inasistencia.class);
		query.setParameter(Inasistencia.ENTIDAD_INASISTENCIA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(inasistencia.getInasistenciaPK().getIdAudiencia()!=null){
			query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_PK, inasistencia.getInasistenciaPK().getIdAudiencia() );
		}
		if(inasistencia.getTipo()!=null){
			query.setParameter(Inasistencia.ENTIDAD_INASISTENCIA_TIPO, inasistencia.getTipo());
		}
		return query.getResultList();
		
	}

    // protected region metodos adicionales end
        
}

