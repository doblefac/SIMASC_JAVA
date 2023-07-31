package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorMotivoDevolucion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IMotivoDevolucionFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.MotivoDevolucionDTO;
import com.ccb.simasc.transversal.entidades.MotivoDevolucion;
import com.ccb.simasc.transversal.entidades.MotivoDevolucionPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link MotivoDevolucion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class MotivoDevolucionFacade extends AccesoFacade<MotivoDevolucion, MotivoDevolucionPK, MotivoDevolucionDTO> implements IMotivoDevolucionFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorMotivoDevolucion manejadorMotivoDevolucion;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorMotivoDevolucion;
	}

	@Override
	public MotivoDevolucionDTO transformarSinDependencias(MotivoDevolucion obj) {
		MotivoDevolucionDTO dto = new MotivoDevolucionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public MotivoDevolucionDTO transformarConDependencias(MotivoDevolucion obj) {
		MotivoDevolucionDTO dto = new MotivoDevolucionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public MotivoDevolucion transformarEntidadConDependencias(MotivoDevolucion obj) {
		MotivoDevolucion dto = new MotivoDevolucion();
		dto = new MotivoDevolucionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public MotivoDevolucion transformarEntidadSinDependencias(MotivoDevolucion obj) {
		MotivoDevolucion dto = new MotivoDevolucion();
		dto = new MotivoDevolucionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
