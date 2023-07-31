package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta seccion sus modificaciones


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.AudienciaDTO;
import com.ccb.simasc.transversal.entidades.InfraestructuraSala;
import com.ccb.simasc.transversal.entidades.InfraestructuraSalaPK;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad InfraestructuraSala.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorInfraestructuraSala extends ManejadorCrud<InfraestructuraSala,InfraestructuraSalaPK>{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
    
    public ManejadorInfraestructuraSala() {
        super(InfraestructuraSala.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta seccion sus modificaciones
   
	private List<AudienciaDTO> convertirResultadoAudienciasDTO(List<Object[]> registros){
		List<AudienciaDTO> audiencias = new ArrayList<>();
		for(Object[] registro : registros){
			audiencias.add(convertirRegistroAudienciaDTO(registro));
		}
		
		return audiencias;
	}
	
	private AudienciaDTO convertirRegistroAudienciaDTO(Object[] registro){
		AudienciaDTO audiencia = new AudienciaDTO();
		Object reg = new Object();

		audiencia.setIdCaso( ( ( reg = registro[0]) ==null ? null : ( (BigDecimal) reg).longValue() ) );
		audiencia.setTipoAudiencia( ( ( reg = registro[1]) ==null ? null : (String) reg) );
		audiencia.setNombreCaso( ( ( reg = registro[2]) ==null ? null : (String) reg) );
		audiencia.setHoraInicio( ( ( reg = registro[3]) ==null ? null : (Date) reg) );
		audiencia.setNombreSala( ( ( reg = registro[4]) ==null ? "Sin Sala" : (String) reg) );
		audiencia.setEstado( ( ( reg = registro[5]) ==null ? null : (String) reg) );
		audiencia.setObservaciones( ( ( reg = registro[6]) ==null ? null : (String) reg) );	
		audiencia.setIdAudiencia( ( ( reg = registro[7]) ==null ? null : ( (BigDecimal) reg).longValue() ) );
		audiencia.setEtapa( ( ( reg = registro[8]) ==null ? null : (String) reg) );	
		return audiencia;
	}
	   
	
    // protected region metodos adicionales end
        
}

