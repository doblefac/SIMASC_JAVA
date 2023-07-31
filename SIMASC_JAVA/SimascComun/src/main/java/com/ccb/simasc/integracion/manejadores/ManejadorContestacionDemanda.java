package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.ContestacionDemanda;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad ContestacionDemanda.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorContestacionDemanda extends ManejadorCrud<ContestacionDemanda,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorContestacionDemanda() {
        super(ContestacionDemanda.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    /*
     * consulta la contestacion de demanda que haya tenido un caso previamente.
     */
    public List<ContestacionDemanda> consultarContestacionDemandaPorCaso(Long idCaso) {
    	
    	List<ContestacionDemanda> demandasContestadas = null;

    		StringBuilder jpqlQuery = new StringBuilder();
    		jpqlQuery.append("SELECT c FROM ContestacionDemanda c WHERE c.idCaso =:");
    		jpqlQuery.append(ContestacionDemanda.ENTIDAD_CONTESTACION_DEMANDA_ID_CASO);
    		jpqlQuery.append(" AND c.estadoRegistro = :");
    		jpqlQuery.append(ContestacionDemanda.ENTIDAD_CONTESTACION_DEMANDA_ESTADO_REGISTRO);

    		Query query = mp.createQuery(jpqlQuery.toString());
    		query.setParameter(ContestacionDemanda.ENTIDAD_CONTESTACION_DEMANDA_ID_CASO, idCaso);
    		query.setParameter(ContestacionDemanda.ENTIDAD_CONTESTACION_DEMANDA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

    		demandasContestadas = query.getResultList(); 		

		return demandasContestadas;
	}
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    /*
     * consulta la contestacion de demanda que haya tenido un caso previamente.
     */
    public ContestacionDemanda consultarContestacionDemandaPorDemandaReconvencion(Long idDemandaReconvencion) {
    	
    	ContestacionDemanda demandasContestadas = null;
    	try{

    		StringBuilder jpqlQuery = new StringBuilder();
    		jpqlQuery.append("SELECT c FROM ContestacionDemanda c WHERE c.idContestacionDemReconvencion =:");
    		jpqlQuery.append(ContestacionDemanda.ENTIDAD_CONTESTACION_DEMANDA_ID_CONTESTACION_DEM_RECONVENCION);
    		jpqlQuery.append(" AND c.estadoRegistro = :");
    		jpqlQuery.append(ContestacionDemanda.ENTIDAD_CONTESTACION_DEMANDA_ESTADO_REGISTRO);

    		Query query = mp.createQuery(jpqlQuery.toString());
    		query.setParameter(ContestacionDemanda.ENTIDAD_CONTESTACION_DEMANDA_ID_CONTESTACION_DEM_RECONVENCION, idDemandaReconvencion);
    		query.setParameter(ContestacionDemanda.ENTIDAD_CONTESTACION_DEMANDA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

    		demandasContestadas = (ContestacionDemanda)query.getSingleResult(); 		
    	}catch(NonUniqueResultException e){
    		throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR517.toString()));
    	}catch (NoResultException e) {
			demandasContestadas = null;
		}
    		

		return demandasContestadas;
	}
    
     

    // protected region metodos adicionales end
        
}

