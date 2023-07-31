package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorValorPlantillaCarta;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IValorPlantillaCartaFacade;
import com.ccb.simasc.transversal.dto.ValorPlantillaCartaDTO;
import com.ccb.simasc.transversal.entidades.ValorPlantillaCarta;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ValorPlantillaCarta}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ValorPlantillaCartaFacade extends AccesoFacade<ValorPlantillaCarta, Long, ValorPlantillaCartaDTO> implements IValorPlantillaCartaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorValorPlantillaCarta manejadorValorPlantillaCarta;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorValorPlantillaCarta;
	}

	@Override
	public ValorPlantillaCartaDTO transformarSinDependencias(ValorPlantillaCarta obj) {
		ValorPlantillaCartaDTO dto = new ValorPlantillaCartaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ValorPlantillaCartaDTO transformarConDependencias(ValorPlantillaCarta obj) {
		ValorPlantillaCartaDTO dto = new ValorPlantillaCartaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ValorPlantillaCarta transformarEntidadConDependencias(ValorPlantillaCarta obj) {
		ValorPlantillaCarta dto = new ValorPlantillaCarta();
		dto = new ValorPlantillaCartaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ValorPlantillaCarta transformarEntidadSinDependencias(ValorPlantillaCarta obj) {
		ValorPlantillaCarta dto = new ValorPlantillaCarta();
		dto = new ValorPlantillaCartaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
