package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorDiaSorteo;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaSorteoFacade;
import com.ccb.simasc.transversal.dto.DiaSorteoDTO;
import com.ccb.simasc.transversal.entidades.DiaSorteo;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link DiaSorteo}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class DiaSorteoFacade extends AccesoFacade<DiaSorteo, String, DiaSorteoDTO> implements IDiaSorteoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorDiaSorteo manejadorDiaSorteo;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorDiaSorteo;
	}

	@Override
	public DiaSorteoDTO transformarSinDependencias(DiaSorteo obj) {
		return new DiaSorteoDTO().transformarSinDependencias(obj);
	}

	@Override
	public DiaSorteoDTO transformarConDependencias(DiaSorteo obj) {
		return new DiaSorteoDTO().transformarConDependencias(obj);
	}

	@Override
	public DiaSorteo transformarEntidadConDependencias(DiaSorteo obj) {
		return new DiaSorteoDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public DiaSorteo transformarEntidadSinDependencias(DiaSorteo obj) {
		return new DiaSorteoDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public List<DiaSorteoDTO> consultarDiasSorteo() {
		return (List<DiaSorteoDTO>) transformarColeccionSinDependencias(manejadorDiaSorteo.consultar(null, null, null),
				new ArrayList<DiaSorteoDTO>());
	}
	// protected region metodos adicionales end
	
}
