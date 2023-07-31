package com.ccb.simasc.negocio.transversal;
// protected region imports fachada on begin

// Escriba en esta sección sus modificaciones

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link CorreoElectronico}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class CorreoElectronicoFacade extends AccesoFacade<CorreoElectronico, Long, CorreoElectronicoDTO> {

	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	@EJB
	private ManejadorCorreoElectronico manejadorCorreoElectronico;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorCorreoElectronico;
	}

	@Override
	public CorreoElectronicoDTO transformarSinDependencias(CorreoElectronico obj) {
		CorreoElectronicoDTO dto = new CorreoElectronicoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public CorreoElectronicoDTO transformarConDependencias(CorreoElectronico obj) {
		CorreoElectronicoDTO dto = new CorreoElectronicoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public CorreoElectronico transformarEntidadConDependencias(CorreoElectronico obj) {
		return new CorreoElectronicoDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public CorreoElectronico transformarEntidadSinDependencias(CorreoElectronico obj) {
		return new CorreoElectronicoDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	public List<CorreoElectronico> consultaCorreosPersona(Long idPersona) {
		return manejadorCorreoElectronico.consultaCorreosPersona(idPersona);
	}
	
	
	

}
