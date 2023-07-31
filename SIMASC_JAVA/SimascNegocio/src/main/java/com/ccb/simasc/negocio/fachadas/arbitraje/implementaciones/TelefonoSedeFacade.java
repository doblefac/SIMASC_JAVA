package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefonoSede;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITelefonoSedeFacade;
import com.ccb.simasc.transversal.dto.TelefonoSedeDTO;
import com.ccb.simasc.transversal.entidades.TelefonoSede;
import com.ccb.simasc.transversal.entidades.TelefonoSedePK;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link TelefonoSede}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class TelefonoSedeFacade extends AccesoFacade<TelefonoSede, TelefonoSedePK, TelefonoSedeDTO> implements ITelefonoSedeFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorTelefonoSede manejadorTelefonoSede;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorTelefonoSede;
	}

	@Override
	public TelefonoSedeDTO transformarSinDependencias(TelefonoSede obj) {
		TelefonoSedeDTO dto = new TelefonoSedeDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public TelefonoSedeDTO transformarConDependencias(TelefonoSede obj) {
		TelefonoSedeDTO dto = new TelefonoSedeDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public TelefonoSede transformarEntidadConDependencias(TelefonoSede obj) {
		TelefonoSede dto = new TelefonoSede();
		dto = new TelefonoSedeDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public TelefonoSede transformarEntidadSinDependencias(TelefonoSede obj) {
		TelefonoSede dto = new TelefonoSede();
		dto = new TelefonoSedeDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
