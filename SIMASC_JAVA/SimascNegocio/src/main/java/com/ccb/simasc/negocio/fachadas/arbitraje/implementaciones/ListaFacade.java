package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorLista;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IListaFacade;
import com.ccb.simasc.transversal.dto.ListaDTO;
import com.ccb.simasc.transversal.entidades.Lista;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Lista}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ListaFacade extends AccesoFacade<Lista, Long, ListaDTO> implements IListaFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorLista manejadorLista;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorLista;
	}

	@Override
	public ListaDTO transformarSinDependencias(Lista obj) {
		ListaDTO dto = new ListaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ListaDTO transformarConDependencias(Lista obj) {
		ListaDTO dto = new ListaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Lista transformarEntidadConDependencias(Lista obj) {
		Lista dto = new Lista();
		dto = new ListaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Lista transformarEntidadSinDependencias(Lista obj) {
		Lista dto = new Lista();
		dto = new ListaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	@Override
	public List<ListaDTO> consultarListaParametros( String nombre ){
		return (List<ListaDTO>) transformarColeccionSinDependencias( manejadorLista.consultarListaParametros(nombre), new ArrayList<ListaDTO>() );
	}
	
	// protected region metodos adicionales end
	
}
