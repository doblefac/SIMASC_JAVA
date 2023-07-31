package com.ccb.simasc.negocio.arbitraje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorLista;
import com.ccb.simasc.transversal.dto.ListaDTO;
import com.ccb.simasc.transversal.entidades.Lista;

/**
*
* @author dpachon
*/
@Stateless
@LocalBean
public class ListaFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	ManejadorLista manejadorLista;
	
	public Lista getBusquedaLista(long idLista){		
		Lista lista = manejadorLista.buscar(idLista);	
		return lista;
	}
	
	public List<Lista> getListas(){		
		List<Lista> casos = manejadorLista.consultar(null, null, null);	
		return casos;
	}
	
	public List<ListaDTO> getListasDTO(){		
		List<Lista> casos = manejadorLista.consultar(null, null, null);	
		List<ListaDTO> listaDTOs = new ArrayList<>();
		for (Lista lista : casos) {
			ListaDTO listaDTO = new ListaDTO();
			listaDTO.setIdLista(lista.getIdLista());
			listaDTO.setEstadoRegistro(lista.getEstadoRegistro());
			listaDTO.setFechaUltimaModificacion(lista.getFechaUltimaModificacion());
			listaDTO.setIdUsuarioModificacion(lista.getIdUsuarioModificacion());
			listaDTO.setNombre(lista.getNombre());
			listaDTOs.add(listaDTO);
		}
		
		return listaDTOs;
	}
}
