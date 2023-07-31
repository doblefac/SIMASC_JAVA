package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;

import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.HistoricoPersonaServicioMateria;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad HistoricoPersonaServicioMateria.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorHistoricoPersonaServicioMateria extends ManejadorCrud<HistoricoPersonaServicioMateria,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorHistoricoPersonaServicioMateria() {
        super(HistoricoPersonaServicioMateria.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones

    /**
     * Actualiza en base de datos el registro de Persona Servicio Materia
     * @param personaServicioMateria
     * @return
     */
    public HistoricoPersonaServicioMateria actualizarHistoricoPersonaServicioMateria(HistoricoPersonaServicioMateria historicoPersonaServicioMateria){
		return (HistoricoPersonaServicioMateria) mp.updateObject(historicoPersonaServicioMateria);		
	}
    
    /**
     * registra historico de cambio para sorteo ya sea cuando se libera lista o se asigna como arbitro
     * @param psm
     * @param observaciones
     */
    public void registroHistoricoSorteo(PersonaServicioMateria psm, 
    		String observaciones, String usuarioSesion) {
    	HistoricoPersonaServicioMateria historicoPerSerMateria = new HistoricoPersonaServicioMateria();
		historicoPerSerMateria.setEstadoParaSorteo(psm.getEstadoParaSorteo());
		historicoPerSerMateria.setFechaAsignacionEstado(new Date());
		historicoPerSerMateria.setObservaciones(observaciones);
		historicoPerSerMateria.setIdPersonaServicioMateria(psm.getIdPersonaServicioMateria());
		historicoPerSerMateria.setIdUsuarioModificacion(usuarioSesion);
		historicoPerSerMateria.setFechaUltimaModificacion(new Date());
		historicoPerSerMateria.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		historicoPerSerMateria.setMotivoEstadoSorteo(psm.getMotivoEstadoSorteo());
        historicoPerSerMateria.setIdCaso(psm.getIdCaso());
		this.crear(historicoPerSerMateria);
    }
    
    // protected region metodos adicionales end
        
}

