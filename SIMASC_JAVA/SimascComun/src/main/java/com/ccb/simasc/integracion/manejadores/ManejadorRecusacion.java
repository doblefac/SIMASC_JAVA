package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Recusacion;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Recusacion.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorRecusacion extends ManejadorCrud<Recusacion,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorRecusacion() {
        super(Recusacion.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

	public Recusacion crearRecusacion(Recusacion recusacion){
		return (Recusacion) mp.updateObject(recusacion);
		
	}
	
	
	public Collection<Object[]> consultarRecusacionesArbitro(Long idCaso, Long idArbitro) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT r.idRecusacion,");
		jpqlQuery.append("       r.idCasoArbitro,");
		jpqlQuery.append("       r.rolPersonaCaso.persona.idPersona,");
		jpqlQuery.append("       r.fechaRecusacion,");//3
		jpqlQuery.append("       r.motivoRecusacion,");
		jpqlQuery.append("       r.idDocRecusacion,");
		jpqlQuery.append("       r.aceptaRecusacion,");
		jpqlQuery.append("       r.fechaRespuestaArbitro,");
		jpqlQuery.append("       r.idDocRespuesta,");
		jpqlQuery.append("       r.confirmacionNombramiento,");
		jpqlQuery.append("       r.fechaConfirmacion,");
		jpqlQuery.append("       r.idDocConfirmacion,");
		jpqlQuery.append("       r.juzgadoConfirmacion,");
		jpqlQuery.append("       r.tipoDeConfirmacion, ");
		jpqlQuery.append("       r.rolPersonaCaso.caso.nombre ");
		jpqlQuery.append("  FROM Recusacion r");
		jpqlQuery.append(" WHERE r.idPersonaArbitro =:");
		jpqlQuery.append(Recusacion.ENTIDAD_RECUSACION_ID_PERSONA_ARBITRO);
		jpqlQuery.append(" AND r.idCasoArbitro =:");
		jpqlQuery.append(Recusacion.ENTIDAD_RECUSACION_ID_CASO_ARBITRO);
		jpqlQuery.append(" AND r.estadoRegistro =:");
		jpqlQuery.append(Recusacion.ENTIDAD_RECUSACION_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		
		query.setParameter(Recusacion.ENTIDAD_RECUSACION_ID_PERSONA_ARBITRO, idArbitro);
		query.setParameter(Recusacion.ENTIDAD_RECUSACION_ID_CASO_ARBITRO, idCaso);
		query.setParameter(Recusacion.ENTIDAD_RECUSACION_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();

	}
	
	
	public Collection<Object[]> consultarRecusacionesArbitro(Long idArbitro) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT r.idRecusacion,");
		jpqlQuery.append("       r.idCasoArbitro,");
		jpqlQuery.append("       r.rolPersonaCaso.persona.idPersona,");
		jpqlQuery.append("       r.fechaRecusacion,");
		jpqlQuery.append("       r.motivoRecusacion,");
		jpqlQuery.append("       r.idDocRecusacion,");
		jpqlQuery.append("       r.aceptaRecusacion,");
		jpqlQuery.append("       r.fechaRespuestaArbitro,");
		jpqlQuery.append("       r.idDocRespuesta,");
		jpqlQuery.append("       r.confirmacionNombramiento,");
		jpqlQuery.append("       r.fechaConfirmacion,");
		jpqlQuery.append("       r.idDocConfirmacion,");
		jpqlQuery.append("       r.juzgadoConfirmacion,");
		jpqlQuery.append("       r.tipoDeConfirmacion,");
		jpqlQuery.append("       r.rolPersonaCaso.caso.nombre");
		jpqlQuery.append("  FROM Recusacion r");
		jpqlQuery.append(" WHERE r.idPersonaArbitro =:");
		jpqlQuery.append(Recusacion.ENTIDAD_RECUSACION_ID_PERSONA_ARBITRO);
		jpqlQuery.append(" AND r.estadoRegistro =:");
		jpqlQuery.append(Recusacion.ENTIDAD_RECUSACION_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		
		query.setParameter(Recusacion.ENTIDAD_RECUSACION_ID_PERSONA_ARBITRO, idArbitro);
		query.setParameter(Recusacion.ENTIDAD_RECUSACION_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();

	}
    
    // protected region metodos adicionales end
        
}

