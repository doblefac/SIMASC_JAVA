package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.NombramientoPersona;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad NombramientoPersona.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorNombramientoPersona extends ManejadorCrud<NombramientoPersona,Long>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
    
    public ManejadorNombramientoPersona() {
        super(NombramientoPersona.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones
    
    
    /**
	 * Consulta los arbitros preseleccionados al caso
	 * @param idCaso
	 * @return
	 */
    public int consultarNumeroArbitrosPactados(Long idCaso,String metodoNombramiento,String tipoDesignacion , Long idTercero){
    	
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT np.cantFuncionariosPrincipales, ");
		jpqlQuery.append("		 np.cantFuncionariosSuplentes ");
		jpqlQuery.append(" FROM NombramientoPersona np");
		jpqlQuery.append(" WHERE np.idCaso = :");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND np.metodoDeNombramiento =:");
		jpqlQuery.append("metodoNombramiento");
		jpqlQuery.append(" AND np.estadoRegistro=:");
		jpqlQuery.append(NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_ESTADO_REGISTRO);
		if(idTercero != null && !UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES.equals(metodoNombramiento))
        	jpqlQuery.append(" AND np.idPersona = :").append(NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_ID_PERSONA);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter("metodoNombramiento", metodoNombramiento);
		query.setParameter(NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(idTercero != null &&  !UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES.equals(metodoNombramiento))
    		query.setParameter(NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_ID_PERSONA, idTercero);
		
		List<Object[]> resultadoQuery = query.getResultList();
		
		if(resultadoQuery == null || resultadoQuery.isEmpty())
			return 0;
		
		if(tipoDesignacion.equals(UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE))			
			return (int)resultadoQuery.get(0)[1];
		
		return (int)resultadoQuery.get(0)[0];
    }
    
    public NombramientoPersona crearNombramientoPersona(NombramientoPersona nPersona){
    	return (NombramientoPersona) mp.updateObject(nPersona);
    }
    
    public List<NombramientoPersona> obtenerNombramientoPorPersonaCaso(Long idCaso, String metodoNombramiento,Long idTerceroAutoridad){
    	List<NombramientoPersona> nombramientoPersona = new ArrayList<NombramientoPersona>();
    	StringBuilder jpqlQuery = new StringBuilder();
    	
    	jpqlQuery.append(" SELECT np FROM NombramientoPersona np " );
    	jpqlQuery.append(" WHERE np.idCaso = :  ").append(NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_ID_CASO);
    	jpqlQuery.append(" AND np.estadoRegistro =:").append(NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_ESTADO_REGISTRO);
    	jpqlQuery.append(" AND np.metodoDeNombramiento = :").append(NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_METODO_DE_NOMBRAMIENTO);
    	if(idTerceroAutoridad != null && !UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES.equals(metodoNombramiento ))
        	jpqlQuery.append(" AND np.idPersona = :").append(NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_ID_PERSONA);
    	Query query = mp.createQuery(jpqlQuery.toString());
    	query.setParameter(NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	query.setParameter(NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_ID_CASO, idCaso);
    	query.setParameter(NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_METODO_DE_NOMBRAMIENTO, metodoNombramiento);
    	if(idTerceroAutoridad != null &&  !UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES.equals(metodoNombramiento ))
    		query.setParameter(NombramientoPersona.ENTIDAD_NOMBRAMIENTO_PERSONA_ID_PERSONA, idTerceroAutoridad);
    	
    	
    	nombramientoPersona = (List<NombramientoPersona>)query.getResultList();    		
   	
    	return nombramientoPersona;
    	
    	
    	
    }
   

    // protected region metodos adicionales end
        
}

