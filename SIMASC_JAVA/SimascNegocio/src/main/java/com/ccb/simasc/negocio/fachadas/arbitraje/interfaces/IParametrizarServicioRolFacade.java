package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;
import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ParametrizarServicioRolDTO;
import com.ccb.simasc.transversal.entidades.ParametrizarServicioRol;
import com.ccb.simasc.transversal.entidades.ParametrizarServicioRolPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link ParametrizarServicioRol}
 * @author sMartinez
 *
 */
@Local
public interface IParametrizarServicioRolFacade extends IAccesoFacade<ParametrizarServicioRol, ParametrizarServicioRolPK, ParametrizarServicioRolDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	public ParametrizarServicioRol actualizarParametrizarServicioRol(ParametrizarServicioRolDTO param);

	public void eliminarParametrizarServicioRol(List<ParametrizarServicioRol> listParam);

	// protected region metodos adicionales end
	
}
