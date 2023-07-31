package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.PartePeticion;
import com.ccb.simasc.transversal.entidades.PartePeticionPK;
import javax.ejb.Stateless;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad PartePeticion.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorPartePeticion extends ManejadorCrud<PartePeticion,PartePeticionPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorPartePeticion() {
        super(PartePeticion.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    // protected region metodos adicionales end
        
}

