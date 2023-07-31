package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.ClasificadorDominio;
import com.ccb.simasc.transversal.entidades.ClasificadorDominioPK;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad ClasificadorDominio.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorClasificadorDominio extends ManejadorCrud<ClasificadorDominio,ClasificadorDominioPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorClasificadorDominio() {
        super(ClasificadorDominio.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    // protected region metodos adicionales end
        
}

