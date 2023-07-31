package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.enumeraciones.TipoOrdenamiento;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.HistoricoEstadoPersonaRol;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad HistoricoEstadoPersonaRol.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorHistoricoEstadoPersonaRol extends ManejadorCrud<HistoricoEstadoPersonaRol,Long>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorHistoricoEstadoPersonaRol() {
        super(HistoricoEstadoPersonaRol.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
    
    /**
     * crear el historico estado persona servicio 
     * @param idPersona  
     * @param idRol
     * @param idMotivo
     * @param fecha  si esta fecha es nula se coloca la fecha actual
     */
    public void crearHistoricoEstadoPersonaServicio(Long idPersona, Long idRol, String idMotivo, Date fecha, Long idServicio){
    	HistoricoEstadoPersonaRol historicoPersona = new HistoricoEstadoPersonaRol();
    	historicoPersona.setIdPersona(idPersona);
    	historicoPersona.setIdRol(idRol);
    	historicoPersona.setIdMotivo(idMotivo);   
    	historicoPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	historicoPersona.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
    	historicoPersona.setFechaUltimaModificacion(new Date());
		historicoPersona.setIdServicio(idServicio);
    	if(fecha != null){
    		historicoPersona.setFecha(fecha);
    	}else{
    		historicoPersona.setFecha(new Date());
    	}
    	crear(historicoPersona);
    }
    
    /**
     * ADM-C-022
     * Consulta el historial de estados de la persona que se pasa como parámetro

     * @param idPersona
     * @return
     */
    public List<HistoricoEstadoPersonaRol> consultarHistoricoPersona(Long idPersona){
    	List<InformacionFiltro> filtros = new ArrayList<>();
    	filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, 
    			HistoricoEstadoPersonaRol.ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ID_PERSONA, idPersona));
    	filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, 
    			HistoricoEstadoPersonaRol.ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO));
    	
    	List<InformacionOrdenamiento> ordenamientos = new ArrayList<>();    	
    	ordenamientos.add(new InformacionOrdenamiento(TipoOrdenamiento.DESCENDENTE, 
    			HistoricoEstadoPersonaRol.ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_FECHA));
    	
    	return consultar(filtros, ordenamientos, null);
    }

    // protected region metodos adicionales end
        
}

