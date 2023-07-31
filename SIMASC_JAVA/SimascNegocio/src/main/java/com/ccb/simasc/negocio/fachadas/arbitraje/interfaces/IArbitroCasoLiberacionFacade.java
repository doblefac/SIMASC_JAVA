package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ArbitroCasoLiberacionDTO;
import com.ccb.simasc.transversal.entidades.ArbitroCasoLiberacion;
import com.ccb.simasc.transversal.entidades.ArbitroCasoLiberacionPK;

@Local
public interface IArbitroCasoLiberacionFacade extends IAccesoFacade<ArbitroCasoLiberacion, ArbitroCasoLiberacionPK, ArbitroCasoLiberacionDTO> {

	public ArbitroCasoLiberacion consultaArbitroCasoLiberacion(Long idPersona);
	
	public void crear(ArbitroCasoLiberacion arbitroCasoLiberacion);
	
	public void habilitaArbitroSorteo(Long idPersona, Long idCaso, String idUsuario);
	
	public void habilitaArbitroSorteoCierreCaso(Long idPersona, Long idCaso, String idUsuario);
	
}
