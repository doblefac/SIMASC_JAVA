package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.PersonaSuspension;
import com.ccb.simasc.transversal.entidades.PersonaSuspensionPK;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad PersonaSuspension.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorPersonaSuspension extends ManejadorCrud<PersonaSuspension,PersonaSuspensionPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorPersonaSuspension() {
        super(PersonaSuspension.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    // protected region metodos adicionales end
        
}

