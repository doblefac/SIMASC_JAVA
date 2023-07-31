package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorParametrizarServicioRol;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrizarServicioRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IServicioFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.ParametrizarServicioRolDTO;
import com.ccb.simasc.transversal.entidades.ParametrizarServicioRol;
import com.ccb.simasc.transversal.entidades.ParametrizarServicioRolPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ParametrizarServicioRol}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ParametrizarServicioRolFacade extends AccesoFacade<ParametrizarServicioRol, ParametrizarServicioRolPK, ParametrizarServicioRolDTO> implements IParametrizarServicioRolFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorParametrizarServicioRol manejadorParametrizarServicioRol;
	
	@EJB
	private IRolFacade rolFacade;
	
	@EJB
	private IServicioFacade servicioFacade;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorParametrizarServicioRol;
	}

	@Override
	public ParametrizarServicioRolDTO transformarSinDependencias(ParametrizarServicioRol obj) {
		ParametrizarServicioRolDTO dto = new ParametrizarServicioRolDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ParametrizarServicioRolDTO transformarConDependencias(ParametrizarServicioRol obj) {
		ParametrizarServicioRolDTO dto = new ParametrizarServicioRolDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ParametrizarServicioRol transformarEntidadConDependencias(ParametrizarServicioRol obj) {
		return new ParametrizarServicioRolDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public ParametrizarServicioRol transformarEntidadSinDependencias(ParametrizarServicioRol obj) {
		return new ParametrizarServicioRolDTO().transformarEntidadSinDependencias(obj);
	}

	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	@Override
	public ParametrizarServicioRol actualizarParametrizarServicioRol(ParametrizarServicioRolDTO param) {
		
		ParametrizarServicioRolPK pk = new ParametrizarServicioRolPK(param.getIdServicio(), param.getIdRol());
		
		ParametrizarServicioRol entity = new ParametrizarServicioRol();
		entity.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		entity.setFechaUltimaModificacion(param.getFechaUltimaModificacion());
		entity.setIdUsuarioModificacion(param.getIdUsuarioModificacion());
		entity.setParametrizarServicioRolPK(pk);
		this.crear(entity);
		return null;
		
	}
	
	@Override
	public void eliminarParametrizarServicioRol(List<ParametrizarServicioRol> params){
		
		for (ParametrizarServicioRol param : params){
			this.eliminar(param);
		}
		
	}

	// protected region metodos adicionales end
	
}
