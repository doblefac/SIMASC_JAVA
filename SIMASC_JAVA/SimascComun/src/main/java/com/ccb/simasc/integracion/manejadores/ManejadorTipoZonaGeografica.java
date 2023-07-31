package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.TipoZonaGeografica;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad TipoZonaGeografica.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorTipoZonaGeografica extends ManejadorCrud<TipoZonaGeografica,Integer>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
    
    public ManejadorTipoZonaGeografica() {
        super(TipoZonaGeografica.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones

    // protected region metodos adicionales end
        
}

