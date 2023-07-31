package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.DetalleReliquidacion;
import com.ccb.simasc.transversal.entidades.DetalleReliquidacionPK;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad DetalleReliquidacion.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorDetalleReliquidacion extends ManejadorCrud<DetalleReliquidacion,DetalleReliquidacionPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorDetalleReliquidacion() {
        super(DetalleReliquidacion.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    // protected region metodos adicionales end
        
}

