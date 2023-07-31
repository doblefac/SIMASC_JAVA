package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.RolDTO;
import com.ccb.simasc.transversal.dto.TipoDeServicioRolDTO;
import com.ccb.simasc.transversal.entidades.TipoDeServicioRol;
import com.ccb.simasc.transversal.entidades.TipoDeServicioRolPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link TipoDeServicioRol}
 * @author sMartinez
 *
 */
@Local
public interface ITipoDeServicioRolFacade extends IAccesoFacade<TipoDeServicioRol, TipoDeServicioRolPK, TipoDeServicioRolDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/** ADM-C-004 consulta los roles de tipo prestador
	 * @param tipoServicio: el tipo de servicio plan dialogos o plan justicia
	 * @return List<RolDTO>: lista de roles de prestadores
	 */
	public List<RolDTO> consultarRolesPrestador( String tipoServicio );
	
	// protected region metodos adicionales end
	
}
