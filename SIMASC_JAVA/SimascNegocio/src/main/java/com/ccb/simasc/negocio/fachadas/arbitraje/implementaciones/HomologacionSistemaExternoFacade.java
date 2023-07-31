package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorHomologacionSistemaExterno;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IHomologacionSistemaExternoFacade;
import com.ccb.simasc.transversal.dto.HomologacionSistemaExternoDTO;
import com.ccb.simasc.transversal.entidades.HomologacionSistemaExterno;
import com.ccb.simasc.transversal.entidades.HomologacionSistemaExternoPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link HomologacionSistemaExterno}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class HomologacionSistemaExternoFacade extends AccesoFacade<HomologacionSistemaExterno, HomologacionSistemaExternoPK, HomologacionSistemaExternoDTO> implements IHomologacionSistemaExternoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorHomologacionSistemaExterno manejadorHomologacionSistemaExterno;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorHomologacionSistemaExterno;
	}

	@Override
	public HomologacionSistemaExternoDTO transformarSinDependencias(HomologacionSistemaExterno obj) {
		HomologacionSistemaExternoDTO dto = new HomologacionSistemaExternoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public HomologacionSistemaExternoDTO transformarConDependencias(HomologacionSistemaExterno obj) {
		HomologacionSistemaExternoDTO dto = new HomologacionSistemaExternoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public HomologacionSistemaExterno transformarEntidadConDependencias(HomologacionSistemaExterno obj) {
		HomologacionSistemaExterno dto = new HomologacionSistemaExterno();
		dto = new HomologacionSistemaExternoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public HomologacionSistemaExterno transformarEntidadSinDependencias(HomologacionSistemaExterno obj) {
		HomologacionSistemaExterno dto = new HomologacionSistemaExterno();
		dto = new HomologacionSistemaExternoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	@Override
	public HomologacionSistemaExterno obtenerHomologacionPorSistemaExterno(HomologacionSistemaExterno homologacion) {
		return manejadorHomologacionSistemaExterno.obtenerHomologacionPorSistemaExterno(homologacion);
	}

	@Override
	public List<HomologacionSistemaExterno> consultarHomologacionesPorSistemaExterno(String nombreSistemaExterno) {
		return manejadorHomologacionSistemaExterno.consultarHomologacionesPorSistemaExterno(nombreSistemaExterno);
	}

	@Override
	public void actulizarHomologacionSistemaExterno(HomologacionSistemaExterno homologacion) {
		manejadorHomologacionSistemaExterno.actulizarHomologacionSistemaExterno(homologacion);
	}

	@Override
	public void desactivarHomologacionSistemaExterno(HomologacionSistemaExterno homologacion) {
		manejadorHomologacionSistemaExterno.desactivarHomologacionSistemaExterno(homologacion);
		
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
