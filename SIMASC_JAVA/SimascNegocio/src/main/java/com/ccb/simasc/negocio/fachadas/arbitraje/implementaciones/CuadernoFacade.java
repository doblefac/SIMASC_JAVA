package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorCuaderno;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICuadernoFacade;
import com.ccb.simasc.transversal.dto.CuadernoDTO;
import com.ccb.simasc.transversal.entidades.Cuaderno;

@Stateless
@LocalBean
public class CuadernoFacade extends AccesoFacade<Cuaderno,Long,CuadernoDTO> implements ICuadernoFacade{

	@EJB
	private ManejadorCuaderno manejadorCuaderno;
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorCuaderno;
	}

	@Override
	public CuadernoDTO transformarSinDependencias(Cuaderno obj) {
		CuadernoDTO dto = new CuadernoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public Cuaderno transformarEntidadConDependencias(Cuaderno obj) {
		Cuaderno dto = new Cuaderno();
		dto = new CuadernoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Cuaderno transformarEntidadSinDependencias(Cuaderno obj) {
		Cuaderno dto = new Cuaderno();
		dto = new CuadernoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	@Override
	public CuadernoDTO transformarConDependencias(Cuaderno obj) {
		CuadernoDTO dto = new CuadernoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public List<CuadernoDTO> obtenerCuadernos() {
		List<CuadernoDTO> lista = new ArrayList<CuadernoDTO>();
		List<Cuaderno>  listaEntidades= manejadorCuaderno.obtenerCuadernos();
		for(Cuaderno obj: listaEntidades){
			lista.add(this.transformarSinDependencias(obj));
		}
		return lista;
	}


}
