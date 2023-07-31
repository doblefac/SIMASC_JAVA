package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorProgramacionAlerta;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IProgramacionAlertaFacade;
import com.ccb.simasc.transversal.dto.ProgramacionAlertaDTO;
import com.ccb.simasc.transversal.entidades.ProgramacionAlerta;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ProgramacionAlerta}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ProgramacionAlertaFacade extends AccesoFacade<ProgramacionAlerta, Long, ProgramacionAlertaDTO> implements IProgramacionAlertaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorProgramacionAlerta manejadorProgramacionAlerta;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorProgramacionAlerta;
	}

	@Override
	public ProgramacionAlertaDTO transformarSinDependencias(ProgramacionAlerta obj) {
		ProgramacionAlertaDTO dto = new ProgramacionAlertaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ProgramacionAlertaDTO transformarConDependencias(ProgramacionAlerta obj) {
		ProgramacionAlertaDTO dto = new ProgramacionAlertaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ProgramacionAlerta transformarEntidadConDependencias(ProgramacionAlerta obj) {
		ProgramacionAlerta dto = new ProgramacionAlerta();
		dto = new ProgramacionAlertaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ProgramacionAlerta transformarEntidadSinDependencias(ProgramacionAlerta obj) {
		ProgramacionAlerta dto = new ProgramacionAlerta();
		dto = new ProgramacionAlertaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@Override
	public List<ProgramacionAlerta> consultaProgramacionesPorEjecutar(){
		return 	manejadorProgramacionAlerta.consultaProgramacionesPorEjecutar();
		
	}

	@Override
	public void crearProgramacionAlerta(ProgramacionAlerta programacionAlerta) {
		manejadorProgramacionAlerta.crear(programacionAlerta);
		
	}

	// protected region metodos adicionales end
	
}
