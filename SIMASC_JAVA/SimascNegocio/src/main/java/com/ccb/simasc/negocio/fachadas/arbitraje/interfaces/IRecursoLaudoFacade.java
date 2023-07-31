package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.RecursoLaudoDTO;
import com.ccb.simasc.transversal.entidades.RecursoLaudo;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link RecursoLaudo}
 * @author sMartinez
 *
 */
@Local
public interface IRecursoLaudoFacade extends IAccesoFacade<RecursoLaudo, Long, RecursoLaudoDTO> {



	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public RecursoLaudo consultarRecurso(Long idCaso, Long idRecurso);

	public void actualizarRecurso(RecursoLaudo recursoLaudo, String idModificador);
	
	public void crearRecurso(Long idCaso, RecursoLaudo recursoLaudo);
	// protected region metodos adicionales end


	
}
