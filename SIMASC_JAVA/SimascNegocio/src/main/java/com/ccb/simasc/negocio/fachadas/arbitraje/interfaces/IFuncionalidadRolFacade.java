package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.FuncionalidadRolDTO;
import com.ccb.simasc.transversal.entidades.FuncionalidadRol;
import com.ccb.simasc.transversal.entidades.FuncionalidadRolPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link FuncionalidadRol}
 * @author sMartinez
 *
 */
@Local
public interface IFuncionalidadRolFacade extends IAccesoFacade<FuncionalidadRol, FuncionalidadRolPK, FuncionalidadRolDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
