package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorFuncionalidadRol;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFuncionalidadRolFacade;
import com.ccb.simasc.transversal.dto.FuncionalidadRolDTO;
import com.ccb.simasc.transversal.entidades.FuncionalidadRol;
import com.ccb.simasc.transversal.entidades.FuncionalidadRolPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link FuncionalidadRol}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class FuncionalidadRolFacade extends AccesoFacade<FuncionalidadRol, FuncionalidadRolPK, FuncionalidadRolDTO> implements IFuncionalidadRolFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorFuncionalidadRol manejadorFuncionalidadRol;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorFuncionalidadRol;
	}

	@Override
	public FuncionalidadRolDTO transformarSinDependencias(FuncionalidadRol obj) {
		FuncionalidadRolDTO dto = new FuncionalidadRolDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public FuncionalidadRolDTO transformarConDependencias(FuncionalidadRol obj) {
		FuncionalidadRolDTO dto = new FuncionalidadRolDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public FuncionalidadRol transformarEntidadConDependencias(FuncionalidadRol obj) {
		FuncionalidadRol dto = new FuncionalidadRol();
		dto = new FuncionalidadRolDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public FuncionalidadRol transformarEntidadSinDependencias(FuncionalidadRol obj) {
		FuncionalidadRol dto = new FuncionalidadRol();
		dto = new FuncionalidadRolDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
