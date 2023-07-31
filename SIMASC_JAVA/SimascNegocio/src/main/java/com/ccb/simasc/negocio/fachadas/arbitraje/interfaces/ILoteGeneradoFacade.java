package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.LoteGeneradoDTO;
import com.ccb.simasc.transversal.dto.cartas.GeneracionLoteDTO;
import com.ccb.simasc.transversal.entidades.Agendamiento;
import com.ccb.simasc.transversal.entidades.LoteGenerado;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Agendamiento}
 * @author sMartinez
 *
 */
@Local
public interface ILoteGeneradoFacade extends IAccesoFacade<LoteGenerado, Long, LoteGeneradoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Método que se encarga de obtener el primer lote generado por la persona y los datos
	 * de las partes a las cuales no se realizo la generacion de carta
	 * @param idPersona
	 * @return
	 */
	public GeneracionLoteDTO consultarLote(Long idPersona);
	
	public void borrarLoteGenerado(Long idLote);
	// protected region metodos adicionales end
	
	
	
}
