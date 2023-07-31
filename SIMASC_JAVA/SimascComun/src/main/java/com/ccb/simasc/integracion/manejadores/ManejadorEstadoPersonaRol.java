package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRol;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRolPK;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad EstadoPersonaRol.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorEstadoPersonaRol 
	extends ManejadorCrud<EstadoPersonaRol,EstadoPersonaRolPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorEstadoPersonaRol() {
        super(EstadoPersonaRol.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    public List<EstadoPersonaRol> consultarEstadoPersonaFiltros(Long idPersona, Long idRol,
    		String idMotivo, Boolean idMotivoIgual){
		List<EstadoPersonaRol> estadosPersona =null;
    	
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT e FROM EstadoPersonaRol e");
		jpqlQuery.append(" WHERE e.estadoRegistro =: ")
			.append(EstadoPersonaRol.ENTIDAD_ESTADO_PERSONA_ROL_ESTADO_REGISTRO);
		if(idPersona != null)
			jpqlQuery.append(" AND e.estadoPersonaRolPK.idPersona = :").append(Persona.ENTIDAD_PERSONA_PK);
		
		if(idRol != null)
			jpqlQuery.append(" AND e.estadoPersonaRolPK.idRol = :")
				.append(Rol.ENTIDAD_ROL_PK);
		
		if(idMotivo != null && (idMotivoIgual==null || idMotivoIgual) ){
			jpqlQuery.append(" AND e.idMotivo = :")
				.append(EstadoPersonaRol.ENTIDAD_ESTADO_PERSONA_ROL_ID_MOTIVO);
		}
		
		if(idMotivo != null && idMotivoIgual!=null && !idMotivoIgual ){
			jpqlQuery.append(" AND e.idMotivo <> :")
				.append(EstadoPersonaRol.ENTIDAD_ESTADO_PERSONA_ROL_ID_MOTIVO);
		}			
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(EstadoPersonaRol.ENTIDAD_ESTADO_PERSONA_ROL_ESTADO_REGISTRO, 
				UtilDominios.ESTADO_REGISTRO_ACTIVO);	
		if(idPersona != null)
			query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		if(idRol != null)
			query.setParameter(Rol.ENTIDAD_ROL_PK, idRol);
		if(idMotivo != null)
			query.setParameter(EstadoPersonaRol.ENTIDAD_ESTADO_PERSONA_ROL_ID_MOTIVO, idMotivo);

    	estadosPersona  = query.getResultList();
    	
    	
    	return estadosPersona;
    	
    	
    	
    }
    
    /**
     * ADM-C-022
     * Crea o actualiza la instancia de la entidad que se pasa como parámetro
     * 
     * @param estadoPersona
     */
    public void crearOActualizarEstadoPersonaEstadoRol(EstadoPersonaRol estadoPersona){
    	actualizar(estadoPersona);
    }

    // protected region metodos adicionales end
        
}

