package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.AgrupamientoRol;
import com.ccb.simasc.transversal.entidades.AgrupamientoRolPK;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad AgrupamientoRol.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorAgrupamientoRol extends ManejadorCrud<AgrupamientoRol,AgrupamientoRolPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorAgrupamientoRol() {
        super(AgrupamientoRol.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    /**
     * Retorna los objetos AgrupamientoRol correspondientes al servicio y tipo de agrupamiento que se reciben por parámetro
     * @author Javier Estévez
     * @param idServicio
     * @return
     */
    public List<AgrupamientoRol> consutarAgrupamiento(Long idServicio, String tipoAgrupamiento){
    	
    	StringBuilder sbSql = new StringBuilder();
    	sbSql.append("select ar ");
    	sbSql.append("from AgrupamientoRol ar ");
    	sbSql.append(" where ar.agrupamientoRolPK.idServicio =:").append(UtilConstantes.PARAM_ID_SERVICIO);
    	sbSql.append(" and ar.agrupamientoRolPK.tipoAgrupamiento =:").append(UtilConstantes.PARAM_TIPO_AGRUPAMIENTO);
    	
    	Query query = this.mp.createQuery(sbSql.toString());
    	
    	query.setParameter(UtilConstantes.PARAM_ID_SERVICIO, idServicio);
    	query.setParameter(UtilConstantes.PARAM_TIPO_AGRUPAMIENTO, tipoAgrupamiento);
    	
    	return query.getResultList();
    	
    }

    // protected region metodos adicionales end
        
}

