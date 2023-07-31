package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorCuota;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICuotaFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.CuotaDTO;
import com.ccb.simasc.transversal.entidades.Cuota;
import com.ccb.simasc.transversal.entidades.CuotaPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Cuota}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class CuotaFacade extends AccesoFacade<Cuota, CuotaPK, CuotaDTO> implements ICuotaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorCuota manejadorCuota;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorCuota;
	}

	@Override
	public CuotaDTO transformarSinDependencias(Cuota obj) {
		CuotaDTO dto = new CuotaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public CuotaDTO transformarConDependencias(Cuota obj) {
		CuotaDTO dto = new CuotaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Cuota transformarEntidadConDependencias(Cuota obj) {
		Cuota dto = new Cuota();
		dto = new CuotaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Cuota transformarEntidadSinDependencias(Cuota obj) {
		Cuota dto = new Cuota();
		dto = new CuotaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
