package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.FechasCaso;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad FechasCaso.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorFechasCaso extends ManejadorCrud<FechasCaso,Long>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
    
    public ManejadorFechasCaso() {
        super(FechasCaso.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones
    /**
	 * Registra fechas de caso en la bd
	 * 
	 * @param caso
	 * @return
	 */
	public FechasCaso crearFechasCaso(FechasCaso fechasCaso) {
		return (FechasCaso) mp.updateObject(fechasCaso);
	}
    // protected region metodos adicionales end
        
}

