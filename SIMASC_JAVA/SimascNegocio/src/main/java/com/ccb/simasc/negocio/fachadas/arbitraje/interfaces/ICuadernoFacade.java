package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CuadernoDTO;
import com.ccb.simasc.transversal.entidades.Cuaderno;

@Local
public interface ICuadernoFacade extends IAccesoFacade<Cuaderno,Long,CuadernoDTO> {

	public List<CuadernoDTO> obtenerCuadernos ();
}
