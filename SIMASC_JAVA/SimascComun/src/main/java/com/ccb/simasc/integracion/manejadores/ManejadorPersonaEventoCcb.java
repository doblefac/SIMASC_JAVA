package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.EventoCcb;
import com.ccb.simasc.transversal.entidades.PersonaEventoCcb;
import com.ccb.simasc.transversal.entidades.PersonaEventoCcbPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import javax.ejb.Stateless;
import javax.persistence.Query;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad PersonaEventoCcb.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorPersonaEventoCcb extends ManejadorCrud<PersonaEventoCcb,PersonaEventoCcbPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorPersonaEventoCcb() {
        super(PersonaEventoCcb.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    /**
     * Método que marca como no asistencia a todos los conciliadores asociados a un evento
     * @param idEventoCcb
     */
    public void marcarNoAsistencia(Long idEventoCcb) {
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append(" UPDATE PersonaEventoCcb p SET p.estado = :").append(PersonaEventoCcb.ENTIDAD_PERSONA_EVENTO_CCB_ESTADO);
    	jpqlQuery.append(" WHERE p.personaEventoCcbPK.idEventoCcb = :").append(EventoCcb.ENTIDAD_EVENTO_CCB_PK);
    	jpqlQuery.append(" AND p.estadoRegistro = ").append("'ACT'");
    	
    	Query query = mp.createQuery(jpqlQuery.toString());
    	query.setParameter(PersonaEventoCcb.ENTIDAD_PERSONA_EVENTO_CCB_ESTADO, UtilDominios.ESTADO_ASISTENCIA_EVENTO_NO_ASISTE);
    	query.setParameter(EventoCcb.ENTIDAD_EVENTO_CCB_PK, idEventoCcb);
    	
    	query.executeUpdate();
    }
    // protected region metodos adicionales end
        
}

