package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.TelefonoSede;
import com.ccb.simasc.transversal.entidades.TelefonoSedePK;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad TelefonoSede.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorTelefonoSede extends ManejadorCrud<TelefonoSede,TelefonoSedePK>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
    
    public ManejadorTelefonoSede() {
        super(TelefonoSede.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones

    // protected region metodos adicionales end
        
}

