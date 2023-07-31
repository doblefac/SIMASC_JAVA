package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.TrmDTO;
import com.ccb.simasc.transversal.entidades.Trm;


@Local
public interface ITrmFacade extends IAccesoFacade<Trm, Long, TrmDTO> {

	public List<TrmDTO> obtenerTodosTRM();
	public void crearTRM(TrmDTO trmDTO);
	public void actualizarTRM(TrmDTO trm);
	public TrmDTO consultarTRMPorId(Long idTrm);
	public TrmDTO consultarTRMActual();
}
