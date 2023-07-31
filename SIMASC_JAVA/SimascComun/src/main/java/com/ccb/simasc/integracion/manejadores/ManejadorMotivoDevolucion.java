package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.MotivoDevolucion;
import com.ccb.simasc.transversal.entidades.MotivoDevolucionPK;
import javax.ejb.Stateless;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad MotivoDevolucion.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorMotivoDevolucion extends ManejadorCrud<MotivoDevolucion,MotivoDevolucionPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorMotivoDevolucion() {
        super(MotivoDevolucion.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    // protected region metodos adicionales end
        
}

