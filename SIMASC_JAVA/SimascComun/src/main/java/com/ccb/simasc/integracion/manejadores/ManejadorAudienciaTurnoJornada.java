package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.AudienciaTurnoJornada;
import com.ccb.simasc.transversal.entidades.AudienciaTurnoJornadaPK;
import javax.ejb.Stateless;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad AudienciaTurnoJornada.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorAudienciaTurnoJornada extends ManejadorCrud<AudienciaTurnoJornada,AudienciaTurnoJornadaPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorAudienciaTurnoJornada() {
        super(AudienciaTurnoJornada.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    // protected region metodos adicionales end
        
}

