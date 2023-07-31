package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.ParteDeLaRecusacion;
import com.ccb.simasc.transversal.entidades.ParteDeLaRecusacionPK;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Recusacion;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad ParteDeLaRecusacion.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorParteDeLaRecusacion extends ManejadorCrud<ParteDeLaRecusacion,ParteDeLaRecusacionPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorParteDeLaRecusacion() {
        super(ParteDeLaRecusacion.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

	/**
	 * Guarda el rol de una persona en un caso
	 * 
	 * @param rolPersonaCaso
	 * @return
	 */
	public ParteDeLaRecusacion crearParteDeRecusacion(ParteDeLaRecusacion pr) {			
		return (ParteDeLaRecusacion) mp.updateObject(pr);
	}	
	

	public Collection<Persona> consultarPartesRecusacion(Long idRecusacion) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT p");
		jpqlQuery.append("  FROM ParteDeLaRecusacion pr");
		jpqlQuery.append("  	,Persona p ");
		jpqlQuery.append(" WHERE pr.parteDeLaRecusacionPK.idPersonaParte = p.idPersona ");
		jpqlQuery.append("   AND pr.parteDeLaRecusacionPK.idRecusacion =:");
		jpqlQuery.append(Recusacion.ENTIDAD_RECUSACION_PK);
		jpqlQuery.append("   AND pr.estadoRegistro =:");
		jpqlQuery.append(Recusacion.ENTIDAD_RECUSACION_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		
		query.setParameter(Recusacion.ENTIDAD_RECUSACION_PK, idRecusacion);
		query.setParameter(Recusacion.ENTIDAD_RECUSACION_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();

	}
	
	
    // protected region metodos adicionales end
        
}

