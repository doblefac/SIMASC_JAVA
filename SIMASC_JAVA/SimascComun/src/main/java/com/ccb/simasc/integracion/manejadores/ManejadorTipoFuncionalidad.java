package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.TipoFuncionalidad;
import com.ccb.simasc.transversal.entidades.TipoFuncionalidadPK;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad TipoFuncionalidad.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorTipoFuncionalidad extends ManejadorCrud<TipoFuncionalidad,TipoFuncionalidadPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorTipoFuncionalidad() {
        super(TipoFuncionalidad.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    // protected region metodos adicionales end
        
}

