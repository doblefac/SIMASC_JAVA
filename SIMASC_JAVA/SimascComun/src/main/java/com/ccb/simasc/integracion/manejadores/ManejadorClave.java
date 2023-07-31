package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Clave;
import com.ccb.simasc.transversal.entidades.ClavePK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Clave.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorClave extends ManejadorCrud<Clave,ClavePK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorClave() {
        super(Clave.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    public List<Clave> validaCoincidenciaClave(String idUsuario, String clave){
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("SELECT p FROM Clave p  WHERE ");
    	jpqlQuery.append("Clave.clavePK.usuarioLogin =  :");
    	jpqlQuery.append("idUsuario");
    	jpqlQuery.append("AND Clave.clavePK.clave =  :");
    	jpqlQuery.append("clave");
    	jpqlQuery.append("AND p.estadoRegistro = ");
    	jpqlQuery.append(UtilDominios.ESTADO_REGISTRO_CLAVE_ACTIVO);
    	Query query = mp.createQuery(jpqlQuery.toString());
    	query.setParameter("idUsuario" ,idUsuario);
    	query.setParameter("clave" ,clave);
    	return query.getResultList();
    }
    // protected region metodos adicionales end
        
}

