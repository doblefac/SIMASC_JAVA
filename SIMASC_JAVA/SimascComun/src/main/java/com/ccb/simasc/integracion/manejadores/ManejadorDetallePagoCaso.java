package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.DetallePagoCaso;
import com.ccb.simasc.transversal.entidades.DetallePagoCasoPK;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad DetallePagoCaso.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorDetallePagoCaso extends ManejadorCrud<DetallePagoCaso,DetallePagoCasoPK>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
    
    public ManejadorDetallePagoCaso() {
        super(DetallePagoCaso.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones

    public List<DetallePagoCaso> obtenerDetalles(String numeroRecibo) {
    	StringBuilder nativeQuery = new StringBuilder();
    	nativeQuery.append("select d from DetallePagoCaso d");
    	nativeQuery.append(" where d.detallePagoCasoPK.numeroRecibo = :").append(PagoCaso.ENTIDAD_PAGO_CASO_PK);
    	nativeQuery.append(" and  d.estadoRegistro = :").append(DetallePagoCaso.ENTIDAD_DETALLE_PAGO_CASO_ESTADO_REGISTRO);
    	
    	Query query = mp.createQuery(nativeQuery.toString());
    	query.setParameter(PagoCaso.ENTIDAD_PAGO_CASO_PK, numeroRecibo);
    	query.setParameter(DetallePagoCaso.ENTIDAD_DETALLE_PAGO_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	
    	return query.getResultList();
    }
    // protected region metodos adicionales end
        
}

