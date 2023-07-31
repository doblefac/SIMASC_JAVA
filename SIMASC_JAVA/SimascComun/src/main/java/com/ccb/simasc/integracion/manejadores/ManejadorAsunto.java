package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.Asunto;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Asunto.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorAsunto extends ManejadorCrud<Asunto,Long>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
    
    public ManejadorAsunto() {
        super(Asunto.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones

    // protected region metodos adicionales end
        
}

