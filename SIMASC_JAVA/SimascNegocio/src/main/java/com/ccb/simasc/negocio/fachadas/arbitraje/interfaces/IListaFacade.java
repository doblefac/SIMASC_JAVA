package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ListaDTO;
import com.ccb.simasc.transversal.entidades.Lista;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Lista}
 * @author sMartinez
 *
 */
@Local
public interface IListaFacade extends IAccesoFacade<Lista, Long, ListaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	/** ADM-C-004 consulta la lista por parametros, se puede parametrizar la consulta
	 * @param nombre
	 * @return  List<ListaDTO>: Listas que coincidan con los paramteros de busqueda.
	 */
	public List<ListaDTO> consultarListaParametros( String nombre );
	
	// protected region metodos adicionales end
	
}
