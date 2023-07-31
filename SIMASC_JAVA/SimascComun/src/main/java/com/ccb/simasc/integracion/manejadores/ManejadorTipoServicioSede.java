package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.TipoServicioSede;
import com.ccb.simasc.transversal.entidades.TipoServicioSedePK;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad TipoServicioSede.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorTipoServicioSede extends ManejadorCrud<TipoServicioSede,TipoServicioSedePK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorTipoServicioSede() {
        super(TipoServicioSede.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    // protected region metodos adicionales end
        
}

