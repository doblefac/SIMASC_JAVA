package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.EstadoPersonaTipoServicio;
import com.ccb.simasc.transversal.entidades.EstadoPersonaTipoServicioPK;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad EstadoPersonaTipoServicio.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorEstadoPersonaTipoServicio 
	extends ManejadorCrud<EstadoPersonaTipoServicio,EstadoPersonaTipoServicioPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorEstadoPersonaTipoServicio() {
        super(EstadoPersonaTipoServicio.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    public List<EstadoPersonaTipoServicio> consultarEstadoPersonaFiltros(Long idPersona, String tipoServicio,
    		String estado, Boolean estadoIgual){
		List<EstadoPersonaTipoServicio> estadosPersona =null;
    	
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT e FROM EstadoPersonaTipoServicio e");
		jpqlQuery.append(" WHERE e.estadoRegistro =: ")
			.append(EstadoPersonaTipoServicio.ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO_REGISTRO);
		if(idPersona != null)
			jpqlQuery.append(" AND e.estadoPersonaTipoServicioPK.idPersona = :").append(Persona.ENTIDAD_PERSONA_PK);
		
		if(tipoServicio != null)
			jpqlQuery.append(" AND e.estadoPersonaTipoServicioPK.tipoServicio = :")
				.append(Servicio.ENTIDAD_SERVICIO_TIPO);
		
		if(estado != null && (estadoIgual==null || estadoIgual) ){
			jpqlQuery.append(" AND e.estado = :")
				.append(EstadoPersonaTipoServicio.ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO);
		}
		
		if(estado != null && estadoIgual!=null && !estadoIgual ){
			jpqlQuery.append(" AND e.estado <> :")
				.append(EstadoPersonaTipoServicio.ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO);
		}			
		
		Query query = mp.createQuery(jpqlQuery.toString());
		
		query.setParameter(EstadoPersonaTipoServicio.ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO_REGISTRO, 
				UtilDominios.ESTADO_REGISTRO_ACTIVO);	
		if(idPersona != null)
			query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		if(tipoServicio != null)
			query.setParameter(Servicio.ENTIDAD_SERVICIO_TIPO, tipoServicio);
		if(estado != null)
			query.setParameter(EstadoPersonaTipoServicio.ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO, estado);

    	estadosPersona  = query.getResultList();
    	
    	
    	return estadosPersona;
    	
    	
    	
    }
    
    /**
     * ADM-C-022
     * Crea o actualiza la instancia de la entidad que se pasa como parámetro
     * 
     * @param estadoPersona
     */
    public void crearOActualizarEstadoPersonaEstadoTipoServicio(EstadoPersonaTipoServicio estadoPersona){
    	actualizar(estadoPersona);
    }

    // protected region metodos adicionales end
        
}

