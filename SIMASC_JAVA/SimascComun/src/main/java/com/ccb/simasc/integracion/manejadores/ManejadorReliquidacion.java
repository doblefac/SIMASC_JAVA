package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Reliquidacion;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Reliquidacion.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorReliquidacion extends ManejadorCrud<Reliquidacion,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorReliquidacion() {
        super(Reliquidacion.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    public Double obtenerReliquidacionCasoTipo( Long idCaso, String tipo ){
    	BigDecimal sumaReliquidaciones;
		StringBuilder jpqlQuery = new StringBuilder();			
		jpqlQuery.append("SELECT SUM(r.valor) FROM Reliquidacion r ");
		jpqlQuery.append(" WHERE r.idCaso = : ").append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND r.tipo = : ").append(Reliquidacion.ENTIDAD_RELIQUIDACION_TIPO);
		jpqlQuery.append(" AND r.estadoRegistro = : ").append(Reliquidacion.ENTIDAD_RELIQUIDACION_ESTADO_REGISTRO);
		
		Query query = getEntityManager().createQuery(jpqlQuery.toString(),BigDecimal.class);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(Reliquidacion.ENTIDAD_RELIQUIDACION_TIPO, tipo);
		query.setParameter(Reliquidacion.ENTIDAD_RELIQUIDACION_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		try{
			sumaReliquidaciones = (BigDecimal) query.getSingleResult();
		}catch(NoResultException e){
			sumaReliquidaciones = null;
		}

		return sumaReliquidaciones != null? sumaReliquidaciones.doubleValue(): null;
	}

    // protected region metodos adicionales end

}

