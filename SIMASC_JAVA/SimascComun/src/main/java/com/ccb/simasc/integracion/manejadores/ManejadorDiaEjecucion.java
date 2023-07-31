package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.DiaEjecucion;
import com.ccb.simasc.transversal.entidades.DiaEjecucionPK;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad DiaEjecucion.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorDiaEjecucion extends ManejadorCrud<DiaEjecucion,DiaEjecucionPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorDiaEjecucion() {
        super(DiaEjecucion.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    // protected region metodos adicionales end
        
}

