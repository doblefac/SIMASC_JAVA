package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorInasistencia;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IInasistenciaFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.InasistenciaDTO;
import com.ccb.simasc.transversal.entidades.Inasistencia;
import com.ccb.simasc.transversal.entidades.InasistenciaPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Inasistencia}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class InasistenciaFacade extends AccesoFacade<Inasistencia, InasistenciaPK, InasistenciaDTO> implements IInasistenciaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorInasistencia manejadorInasistencia;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorInasistencia;
	}

	@Override
	public InasistenciaDTO transformarSinDependencias(Inasistencia obj) {
		InasistenciaDTO dto = new InasistenciaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public InasistenciaDTO transformarConDependencias(Inasistencia obj) {
		InasistenciaDTO dto = new InasistenciaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Inasistencia transformarEntidadConDependencias(Inasistencia obj) {
		Inasistencia dto = new Inasistencia();
		dto = new InasistenciaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Inasistencia transformarEntidadSinDependencias(Inasistencia obj) {
		Inasistencia dto = new Inasistencia();
		dto = new InasistenciaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public List<InasistenciaDTO> consultarInasistencias(InasistenciaDTO inasistencia){
		
		return (List<InasistenciaDTO>) transformarColeccionSinDependencias(manejadorInasistencia.consultarInasistencias(inasistencia), new ArrayList<InasistenciaDTO>());
	}

	// protected region metodos adicionales end
	
}
