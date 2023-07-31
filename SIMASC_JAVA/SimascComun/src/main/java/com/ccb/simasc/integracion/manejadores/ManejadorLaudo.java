package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.obtenerFechaFinDelDia;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Laudo;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Laudo.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorLaudo extends ManejadorCrud<Laudo,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorLaudo() {
        super(Laudo.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    public Laudo actualizarLaudo(Laudo laudo) {
		return (Laudo) mp.updateObject(laudo);
	}
    
    public List<Laudo> obtenerLaudos(Date fechaIni, Date fechaFin, Long codigoCaso, Long materia){	

  		StringBuilder jpqlQuery = new StringBuilder();
  		jpqlQuery.append(" SELECT l");					 		  		
  		jpqlQuery.append(" FROM Laudo l");
  		jpqlQuery.append(" LEFT JOIN l.caso c");
  		jpqlQuery.append(" LEFT JOIN FETCH c.rolPersonaCasoList");
  		jpqlQuery.append(" LEFT JOIN FETCH l.recursoLaudoList");
  		jpqlQuery.append(" WHERE ");
  		
  		if(fechaIni != null && fechaFin != null)
  			jpqlQuery.append(" l.fecha BETWEEN :fechaIni AND :fechaFin AND");
  		 		
  		if(codigoCaso != null)
  			jpqlQuery.append(" c.idCaso = :idCaso AND");
  		
  		if(materia != null)
  			jpqlQuery.append(" c.servicioMateria.servicioMateriaPK.idMateria =: materia AND");

  		jpqlQuery.delete(jpqlQuery.length()-4, jpqlQuery.length());

  		Query query = mp.createQuery(jpqlQuery.toString());
  		
  		if(fechaIni != null && fechaFin != null){
  			query.setParameter("fechaIni",new Date(fechaIni.getTime()),TemporalType.DATE);	
  			query.setParameter("fechaFin",new Date(obtenerFechaFinDelDia(fechaFin).getTime()),TemporalType.DATE);
  		}
  	  		
  		if(codigoCaso != null)
  			query.setParameter("idCaso" ,codigoCaso);
  		
  		if(materia != null)
  			query.setParameter("materia",materia);
  		return (List<Laudo>)query.getResultList();
  	}    
    
    // protected region metodos adicionales end
        
}

