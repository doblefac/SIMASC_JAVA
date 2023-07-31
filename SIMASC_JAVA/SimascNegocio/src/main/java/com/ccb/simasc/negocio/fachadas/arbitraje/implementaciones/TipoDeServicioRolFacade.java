package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorTipoDeServicioRol;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITipoDeServicioRolFacade;
import com.ccb.simasc.transversal.dto.RolDTO;
import com.ccb.simasc.transversal.dto.TipoDeServicioRolDTO;
import com.ccb.simasc.transversal.entidades.TipoDeServicioRol;
import com.ccb.simasc.transversal.entidades.TipoDeServicioRolPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link TipoDeServicioRol}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class TipoDeServicioRolFacade extends AccesoFacade<TipoDeServicioRol, TipoDeServicioRolPK, TipoDeServicioRolDTO> implements ITipoDeServicioRolFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorTipoDeServicioRol manejadorTipoDeServicioRol;
	
	@EJB
	private IRolFacade rolFacade;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorTipoDeServicioRol;
	}

	@Override
	public TipoDeServicioRolDTO transformarSinDependencias(TipoDeServicioRol obj) {
		TipoDeServicioRolDTO dto = new TipoDeServicioRolDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public TipoDeServicioRolDTO transformarConDependencias(TipoDeServicioRol obj) {
		TipoDeServicioRolDTO dto = new TipoDeServicioRolDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public TipoDeServicioRol transformarEntidadConDependencias(TipoDeServicioRol obj) {
		TipoDeServicioRol dto = new TipoDeServicioRol();
		dto = new TipoDeServicioRolDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public TipoDeServicioRol transformarEntidadSinDependencias(TipoDeServicioRol obj) {
		TipoDeServicioRol dto = new TipoDeServicioRol();
		dto = new TipoDeServicioRolDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	@Override
	public List<RolDTO> consultarRolesPrestador( String tipoServicio ){
		return (List<RolDTO>) rolFacade.transformarColeccionSinDependencias( manejadorTipoDeServicioRol.consultarRolesPrestador(tipoServicio), new ArrayList<RolDTO>() );
	}
	
	// protected region metodos adicionales end
	
}
